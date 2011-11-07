package procon.core.packages;

public class PackageManager {

    /*public class PackageManager {

        #region Constants

        public static string DIRECTORY_UPDATES = "Updates";
        public static string DIRECTORY_PACKAGES = "Packages";
        public static string DIRECTORY_UPDATES_PACKAGES = "Updates/Packages";

        public static int PACKAGE_STATUSCODE_NOTINSTALLED = 0;
        public static int PACKAGE_STATUSCODE_DOWNLOADBEGIN = 1;
        public static int PACKAGE_STATUSCODE_DOWNLOADSUCCESS = 2;
        public static int PACKAGE_STATUSCODE_DOWNLOADFAIL = 3;
        public static int PACKAGE_STATUSCODE_INSTALLBEGIN = 4;
        public static int PACKAGE_STATUSCODE_INSTALLSUCCESS = 5;
        public static int PACKAGE_STATUSCODE_INSTALLFAIL = 6;
        public static int PACKAGE_STATUSCODE_INSTALLQUEUED = 7;
        public static int PACKAGE_STATUSCODE_PACKAGEUPDATED = 8;

        #endregion

        #region Properties

        public PackageDictionary RemotePackages { get; private set; } // List from phogue.net
        public PackageDictionary InstalledPackages { get; private set; } // Packages in /Packages (installed)
        public PackageDictionary QueuedPackages { get; private set; } // Packages in /Updates/Packages (install on restart)

        #endregion

        #region Events

        public delegate void PackageManagerEventHandler(PackageManager sender);
        public event PackageManagerEventHandler RemotePackagesUpdated;

        public delegate void PackageEventHandler(PackageManager sender, Package target);
        //public event PackageEventHandler PackageInstalled;
        public event PackageEventHandler PackageBeginningDownload;
        public event PackageEventHandler PackageDownloaded;
        public event PackageEventHandler PackageInstalling;
        public event PackageEventHandler PackageAwaitingRestart;
        public event PackageEventHandler PackageDownloadFail;

        #endregion

        public PackageManager() {

            this.RemotePackages = new PackageDictionary();
            this.InstalledPackages = new PackageDictionary();
            this.QueuedPackages = new PackageDictionary();

            this.LoadPackageDirectory(PackageManager.DIRECTORY_PACKAGES, this.InstalledPackages);
            this.LoadPackageDirectory(PackageManager.DIRECTORY_UPDATES_PACKAGES, this.QueuedPackages);
        }

        private void LoadPackageDirectory(string path, PackageDictionary target) {

            try {
                if (Directory.Exists(path) == true) {
                    foreach (string packagePath in Directory.GetFiles(path, "*.xml")) {
                        try {
                            target.AddPackage(new Package(File.ReadAllText(packagePath)));
                        }
                        catch (Exception) { }
                    }
                }
            }
            catch (Exception) { }
        }

        /// <summary>
        /// TO DO: Eventually the RSS download will be within PRoCon.Core,
        ///        This remained incomplete due to time constraints placed on
        ///        development by MoH.
        /// </summary>
        /// <param name="rss">The full rss feed from phogue.net</param>
        public void LoadRemotePackages(XmlDocument rssDocument) {

            this.RemotePackages.Clear();

            try {
                foreach (XmlNode node in rssDocument.SelectNodes("/rss/procon/packages/package")) {
                    this.RemotePackages.AddPackage(new Package(node.OuterXml));
                }
            }
            catch (Exception) { }

            if (this.RemotePackagesUpdated != null) {
                if (this.RemotePackagesUpdated != null) {
                    FrostbiteConnection.RaiseEvent(this.RemotePackagesUpdated.GetInvocationList(), this);
                }
            }
        }

        public bool CanDownloadPackage(string uid) {

            bool canDownloadPackage = false;

            if (this.RemotePackages.Contains(uid) == true) {
                canDownloadPackage = (this.InstalledPackages.IsNewer(this.RemotePackages[uid]) == true && this.QueuedPackages.IsNewer(this.RemotePackages[uid]) == true);
            }

            return canDownloadPackage;
        }

        public bool isInstalled(string uid) {
            return this.InstalledPackages.Contains(uid);
        }

        public bool isAwaitingRestart(string uid) {
            return this.QueuedPackages.Contains(uid);
        }

        public void DownloadInstallPackage(string uid, bool localInstall) {

            if (this.CanDownloadPackage(uid) == true) {

                if (this.PackageBeginningDownload != null) {
                    FrostbiteConnection.RaiseEvent(this.PackageBeginningDownload.GetInvocationList(), this, this.RemotePackages[uid]);
                }

                this.RegisterEvents(this.RemotePackages[uid]);

                if (localInstall == true) {

                    switch (this.RemotePackages[uid].PackageType) {
                        case PackageType.Plugin:
                            //this.RegisterEvents(this.RemotePackages[uid]);
                            this.RemotePackages[uid].DownloadPackage(PackageManager.DIRECTORY_UPDATES);
                            break;
                        case PackageType.Mappack:
                            this.RemotePackages[uid].DownloadPackage("");
                            break;
                    }
                }
                else {
                    // Extract to procon folder
                    this.RemotePackages[uid].DownloadPackage("");
                }

            }
        }

        private void UnregisterEvents(Package focus) {
            focus.DownloadComplete -= new Package.DownloadFileEventHandler(focus_DownloadComplete);
            focus.DownloadError -= new Package.DownloadFileEventHandler(focus_DownloadError);
            //focus.DownloadProgressUpdate -= new Package.DownloadFileEventHandler(focus_DownloadProgressUpdate);
            focus.PackageBeginUnzip -= new Package.PackageEventHandler(focus_PackageBeginUnzip);
            focus.PackageEndUnzip -= new Package.PackageEventHandler(focus_PackageEndUnzip);
        }

        private void RegisterEvents(Package focus) {
            focus.DownloadComplete += new Package.DownloadFileEventHandler(focus_DownloadComplete);
            focus.DownloadError += new Package.DownloadFileEventHandler(focus_DownloadError);
            //focus.DownloadProgressUpdate += new Package.DownloadFileEventHandler(focus_DownloadProgressUpdate);
            focus.PackageBeginUnzip += new Package.PackageEventHandler(focus_PackageBeginUnzip);
            focus.PackageEndUnzip += new Package.PackageEventHandler(focus_PackageEndUnzip);
        }

        private void focus_PackageBeginUnzip(Package sender) {
            if (this.PackageInstalling != null) {
                FrostbiteConnection.RaiseEvent(this.PackageInstalling.GetInvocationList(), this, sender);
            }
        }

        private void focus_PackageEndUnzip(Package sender) {
            this.UnregisterEvents(sender);

            if (this.PackageAwaitingRestart != null) {
                FrostbiteConnection.RaiseEvent(this.PackageAwaitingRestart.GetInvocationList(), this, sender);
            }
        }

        //private void focus_DownloadProgressUpdate(Package sender, CDownloadFile file) {
        //    this.UnregisterEvents(sender);
        //}

        private void focus_DownloadComplete(Package sender, CDownloadFile file) {
            //this.UnregisterEvents(sender);

            if (this.PackageDownloaded != null) {
                FrostbiteConnection.RaiseEvent(this.PackageDownloaded.GetInvocationList(), this, sender);
            }
        }

        private void focus_DownloadError(Package sender, CDownloadFile file) {
            this.UnregisterEvents(sender);

            if (this.PackageDownloadFail != null) {
                FrostbiteConnection.RaiseEvent(this.PackageDownloadFail.GetInvocationList(), this, sender);
            }
        }

        *//*
			oPackageList[row]["uid"],
			oPackageList[row]["type"],
			oPackageList[row]["name"],
			oPackageList[row]["version"],
			oPackageList[row]["lastupdate"],
			oPackageList[row]["status"],
			oPackageList[row]["downloads"],
			oPackageList[row]["imagelink"], // not searchable
			oPackageList[row]["forumlink"], // not searchable
			oPackageList[row]["author"],
			oPackageList[row]["website"], // not searchable
			oPackageList[row]["tags"],
			oPackageList[row]["description"] );
        *//*

        // Done here to include more information about the status and such.
        public string RemoteToJsonString() {

            StringBuilder builder = new StringBuilder();

            ArrayList packages = new ArrayList();

            foreach (Package package in this.RemotePackages) {

                Hashtable hashPackage = package.ToHashTable();

                if (this.isInstalled(package.Uid) == true) {

                    if (this.CanDownloadPackage(package.Uid) == true) {
                        hashPackage.Add("status", "Updated");
                        hashPackage.Add("statuscode", PackageManager.PACKAGE_STATUSCODE_PACKAGEUPDATED);
                    }
                    else {
                        hashPackage.Add("status", "Installed");
                        hashPackage.Add("statuscode", PackageManager.PACKAGE_STATUSCODE_INSTALLSUCCESS);
                    }
                }
                else if (this.isAwaitingRestart(package.Uid) == true) {
                    hashPackage.Add("status", "Restart");
                    hashPackage.Add("statuscode", PackageManager.PACKAGE_STATUSCODE_INSTALLQUEUED);
                }
                else {
                    hashPackage.Add("status", "-");
                    hashPackage.Add("statuscode", PackageManager.PACKAGE_STATUSCODE_NOTINSTALLED);
                }

                // Get type_loc

                packages.Add(hashPackage);
            }

            return JSON.JsonEncode(packages);
        }
    }*/
}
