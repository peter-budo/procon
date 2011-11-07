package procon.forms;

public class GspUpdater {

    /*public partial class GspUpdater : Form {

        private List<string> m_lstIgnoreDirectories;

        private string m_gspUpdatesDirectory;
        private UpdateDownloader m_updateDownloader;
        private Version m_latestVersion;

        private ListViewColumnSorter m_lvwColumnSorter;

        public GspUpdater() {
            InitializeComponent();

            this.m_lvwColumnSorter = new ListViewColumnSorter();
            this.lsvInstalls.ListViewItemSorter = this.m_lvwColumnSorter;

            this.m_latestVersion = null;
            this.m_gspUpdatesDirectory = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "GspLatestVersion");

            this.m_updateDownloader = new UpdateDownloader("GspLatestVersion");
            this.m_updateDownloader.UpdateDownloading += new UpdateDownloader.UpdateDownloadingHandler(m_updateDownloader_UpdateDownloading);

            this.m_updateDownloader.DownloadUnzipComplete += new UpdateDownloader.DownloadUnzipCompleteHandler(m_updateDownloader_DownloadUnzipComplete);
            this.m_updateDownloader.DownloadLatest();

            this.m_lstIgnoreDirectories = new List<string>() { "updates", "gsplatestversion", "localization", "plugins", "configs", "updates", "logs", "media" };
        }

        private void m_updateDownloader_UpdateDownloading(PRoCon.Core.CDownloadFile cdfDownloading) {
            cdfDownloading.DownloadProgressUpdate += new PRoCon.Core.CDownloadFile.DownloadFileEventDelegate(cdfDownloading_DownloadProgressUpdate);
            cdfDownloading.DownloadError += new PRoCon.Core.CDownloadFile.DownloadFileEventDelegate(cdfDownloading_DownloadError);
        }

        void cdfDownloading_DownloadError(PRoCon.Core.CDownloadFile cdfSender) {
            this.lblDownloadStatus.Text = "Error dowloading latest version";
        }

        private void cdfDownloading_DownloadProgressUpdate(PRoCon.Core.CDownloadFile cdfSender) {
            this.lblDownloadStatus.Text = cdfSender.GetLabelProgress();
        }

        private void m_updateDownloader_DownloadUnzipComplete() {
            this.lblDownloadStatus.Text = "Latest version downloaded";

            try {
                AssemblyName proconAssemblyName = AssemblyName.GetAssemblyName(Path.Combine(this.m_gspUpdatesDirectory, "PRoCon.exe"));

                this.m_latestVersion = proconAssemblyName.Version;
            }
            catch (Exception) { }

        }

        private void GspUpdater_Load(object sender, EventArgs e) {

            this.txtBrowseFolder.Text = AppDomain.CurrentDomain.BaseDirectory;
            this.folderBrowser.SelectedPath = this.txtBrowseFolder.Text;

            this.DiscoverProcons(this.txtBrowseFolder.Text);

            this.tmrUpdateChecker.Enabled = true;
        }
        TODO moved out of the class
        public enum RunningStatus {
            None,
            Stopped,
            Running,
            Error,
        }

        private void SetStatus(RunningStatus status, string version, string directory, string proconPath) {

            if (this.lsvInstalls.Items.ContainsKey(proconPath) == true) {
                this.lsvInstalls.Items[proconPath].Text = status.ToString();
                this.lsvInstalls.Items[proconPath].Tag = status;
                this.lsvInstalls.Items[proconPath].SubItems["Version"].Text = version;
                this.lsvInstalls.Items[proconPath].SubItems["Directory"].Text = directory;
                this.lsvInstalls.Items[proconPath].SubItems["Path"].Text = proconPath;
            }
            else {
                ListViewItem newProcon = new ListViewItem();
                newProcon.Name = proconPath;
                newProcon.Text = status.ToString();
                newProcon.Tag = status;

                newProcon.UseItemStyleForSubItems = false;

                ListViewItem.ListViewSubItem newSubitem = new ListViewItem.ListViewSubItem();
                newSubitem.Name = "Version";
                newSubitem.Text = version;
                newSubitem.Font = new Font(this.Font, FontStyle.Bold);
                newProcon.SubItems.Add(newSubitem);

                newSubitem = new ListViewItem.ListViewSubItem();
                newSubitem.Name = "Directory";
                newSubitem.Text = directory;
                newProcon.SubItems.Add(newSubitem);

                newSubitem = new ListViewItem.ListViewSubItem();
                newSubitem.Name = "Path";
                newSubitem.Text = proconPath;
                newProcon.SubItems.Add(newSubitem);

                this.lsvInstalls.Items.Add(newProcon);
            }

            if (this.lsvInstalls.Items[proconPath].Tag != null && ((RunningStatus)this.lsvInstalls.Items[proconPath].Tag) == RunningStatus.Running) {
                this.lsvInstalls.Items[proconPath].ImageKey = "running.png";
                this.lsvInstalls.Items[proconPath].ForeColor = Color.LightSeaGreen;
                this.lsvInstalls.Items[proconPath].Font = new Font(this.Font, FontStyle.Bold);
            }
            else {
                this.lsvInstalls.Items[proconPath].ImageKey = "stopped.png";
                this.lsvInstalls.Items[proconPath].ForeColor = SystemColors.WindowText;
                this.lsvInstalls.Items[proconPath].Font = this.Font;
            }

            try {
                if (this.m_latestVersion != null) {
                    if (new Version(version).CompareTo(this.m_latestVersion) >= 0) {
                        this.lsvInstalls.Items[proconPath].SubItems["Version"].ForeColor = Color.LightSeaGreen;
                    }
                    else {
                        this.lsvInstalls.Items[proconPath].SubItems["Version"].ForeColor = Color.Maroon;
                    }
                }
            }
            catch (Exception) { }
            //

        }

        private void ProconStatus(string proconPath) {

            if (File.Exists(proconPath) == true) {
                RunningStatus status = RunningStatus.Stopped;
                string version = "Unknown";
                string directory = "Unknown";

                foreach (Process pcProcon in Process.GetProcessesByName("PRoCon")) {
                    try {
                        if (string.Compare(proconPath, Path.GetFullPath(pcProcon.MainModule.FileName), true) == 0) {
                            status = RunningStatus.Running;
                        }
                    }
                    catch (Exception) {
                        status = RunningStatus.Error;
                    }
                }

                try {
                    AssemblyName proconAssemblyName = AssemblyName.GetAssemblyName(proconPath);

                    version = proconAssemblyName.Version.ToString();
                }
                catch (Exception) {
                    version = "Error";
                }

                try {
                    DirectoryInfo dirInfo = new DirectoryInfo(proconPath);

                    directory = dirInfo.Parent.Name;
                }
                catch (Exception) {
                    directory = "Error";
                }

                this.SetStatus(status, version, directory, proconPath);
            }
        }

        private void DiscoverProcons(string path) {

            this.lsvInstalls.BeginUpdate();

            if (Directory.Exists(path) == true) {

                List<string> lstDirectories = new List<string>(Directory.GetDirectories(path));

                // Search sub directories, ignoring any updates directories
                foreach (string directory in lstDirectories) {
                    DirectoryInfo dirInfo = new DirectoryInfo(directory);
                    if (this.m_lstIgnoreDirectories.Contains(dirInfo.Name.ToLower()) == false) {
                        this.DiscoverProcons(directory);

                        this.ProconStatus(Path.Combine(directory, "PRoCon.exe"));
                    }
                }
            }

            foreach (ColumnHeader column in this.lsvInstalls.Columns) {
                column.Width = -2;
            }

            this.lsvInstalls.EndUpdate();
        }

        private void tmrUpdateChecker_Tick(object sender, EventArgs e) {

            this.lsvInstalls.BeginUpdate();

            foreach (ListViewItem item in this.lsvInstalls.Items) {
                this.ProconStatus(item.Name);
            }

            this.RefreshControls();

            foreach (ColumnHeader column in this.lsvInstalls.Columns) {
                column.Width = -2;
            }

            this.lsvInstalls.EndUpdate();
        }

        #region Buttons

        private void btnStart_Click(object sender, EventArgs e) {
            foreach (ListViewItem selectedItem in this.lsvInstalls.SelectedItems) {

                if (selectedItem.Tag == null || (selectedItem.Tag != null && ((RunningStatus)selectedItem.Tag) == RunningStatus.Stopped)) {
                    Process.Start(selectedItem.Name, this.txtArguments.Text.Replace("%directory%", selectedItem.SubItems["Directory"].Text));
                }
            }

            this.RefreshControls();
        }

        private void ShutdownProconInstance(string fullProconPath) {
            try {
                foreach (Process pcProcon in Process.GetProcessesByName("PRoCon")) {
                    try {
                        if (string.Compare(fullProconPath, Path.GetFullPath(pcProcon.MainModule.FileName), true) == 0) {
                            pcProcon.Kill();
                        }
                    }
                    catch (Exception) { }
                }
            }
            catch (Exception) { }
        }

        private void btnStop_Click(object sender, EventArgs e) {

            foreach (ListViewItem selectedItem in this.lsvInstalls.SelectedItems) {
                this.ShutdownProconInstance(selectedItem.Name);
            }

            this.RefreshControls();
        }

        public static void CopyAll(DirectoryInfo source, DirectoryInfo target) {
            if (Directory.Exists(target.FullName) == false) {
                Directory.CreateDirectory(target.FullName);
            }

            foreach (FileInfo fi in source.GetFiles()) {
                fi.CopyTo(Path.Combine(target.ToString(), fi.Name), true);
            }

            foreach (DirectoryInfo diSourceSubDir in source.GetDirectories()) {
                DirectoryInfo nextTargetSubDir = target.CreateSubdirectory(diSourceSubDir.Name);
                CopyAll(diSourceSubDir, nextTargetSubDir);
            }
        }

        private void btnUpdate_Click(object sender, EventArgs e) {

            this.Cursor = Cursors.WaitCursor;
            this.Enabled = false;

            int iUpdatedCopies = 0, iSkippedCopies = 0; ;

            if (Directory.Exists(this.m_gspUpdatesDirectory) == true) {
                foreach (ListViewItem selectedItem in this.lsvInstalls.SelectedItems) {
                    if (this.m_latestVersion != null) {
                        if (new Version(selectedItem.SubItems["Version"].Text).CompareTo(this.m_latestVersion) < 0) {
                            this.ShutdownProconInstance(selectedItem.Name);
                            GspUpdater.CopyAll(new DirectoryInfo(this.m_gspUpdatesDirectory), new DirectoryInfo(Path.GetDirectoryName(selectedItem.Name)));

                            iUpdatedCopies++;
                        }
                        else {
                            iSkippedCopies++;
                        }

                        this.lblDownloadStatus.Text = String.Format("Updated {0} of {1} ({2} Skipped)", iUpdatedCopies + iSkippedCopies, this.lsvInstalls.SelectedItems.Count, iSkippedCopies);
                    }
                }
            }

            this.Enabled = true;
            this.Cursor = Cursors.Default;
        }

        #endregion

        #region List Control

        private void RefreshControls() {

            bool IsStopButtonEnabled = false;
            bool IsStartButtonEnabled = false;
            bool IsUpdateButtonEnabled = false;

            if (this.lsvInstalls.SelectedItems.Count > 0) {
                foreach (ListViewItem item in this.lsvInstalls.SelectedItems) {

                    if (item.Tag != null) {
                        if (((RunningStatus)item.Tag) == RunningStatus.Error || ((RunningStatus)item.Tag) == RunningStatus.Running) {
                            IsStartButtonEnabled = true;
                        }

                        if (((RunningStatus)item.Tag) == RunningStatus.Error || ((RunningStatus)item.Tag) == RunningStatus.Stopped) {
                            IsStopButtonEnabled = true;
                        }
                    }

                    try {

                        if (this.m_latestVersion != null) {
                            if (new Version(item.SubItems["Version"].Text).CompareTo(this.m_latestVersion) < 0) {
                                IsUpdateButtonEnabled = true;
                            }
                        }

                    }
                    catch (Exception) { }

                }
            }

            this.btnStart.Enabled = IsStopButtonEnabled;
            this.btnStop.Enabled = IsStartButtonEnabled;

            this.btnUpdate.Enabled = IsUpdateButtonEnabled;
        }

        private void lstInstalls_SelectedIndexChanged(object sender, EventArgs e) {
            this.RefreshControls();
        }

        #endregion

        private void GspUpdater_Activated(object sender, EventArgs e) {

            this.tmrUpdateChecker.Enabled = false;

            this.lsvInstalls.Items.Clear();

            this.DiscoverProcons(this.txtBrowseFolder.Text);

            this.tmrUpdateChecker.Enabled = true;
        }

        private void lsvInstalls_ColumnClick(object sender, ColumnClickEventArgs e) {
            if (e.Column == this.m_lvwColumnSorter.SortColumn) {
                if (this.m_lvwColumnSorter.Order == SortOrder.Ascending) {
                    this.m_lvwColumnSorter.Order = SortOrder.Descending;
                }
                else {
                    this.m_lvwColumnSorter.Order = SortOrder.Ascending;
                }
            }
            else {
                this.m_lvwColumnSorter.SortColumn = e.Column;
                this.m_lvwColumnSorter.Order = SortOrder.Ascending;
            }

            // Perform the sort with these new sort options.
            this.lsvInstalls.Sort();
        }

        private void btnBrowse_Click(object sender, EventArgs e) {
            DialogResult result = this.folderBrowser.ShowDialog();

            if (result == DialogResult.OK) {
                if (Directory.Exists(this.folderBrowser.SelectedPath) == true) {
                    this.txtBrowseFolder.Text = this.folderBrowser.SelectedPath;

                    this.lsvInstalls.Items.Clear();

                    this.tmrUpdateChecker.Enabled = false;

                    this.DiscoverProcons(this.txtBrowseFolder.Text);

                    this.tmrUpdateChecker.Enabled = true;
                }
            }
        }

        private void lsvInstalls_DoubleClick(object sender, EventArgs e) {
            if (this.lsvInstalls.SelectedItems.Count > 0) {

                DirectoryInfo info = new DirectoryInfo(Path.GetDirectoryName(this.lsvInstalls.SelectedItems[0].Name));

                Process.Start(info.ToString());
            }
        }

        private void openFolderToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.lsvInstalls.SelectedItems.Count > 0) {
                DirectoryInfo info = new DirectoryInfo(Path.GetDirectoryName(this.lsvInstalls.SelectedItems[0].Name));

                Process.Start(info.ToString());
            }
        }

        private void lsvInstalls_MouseClick(object sender, MouseEventArgs e) {
            if (e.Button == MouseButtons.Right) {

                this.ctxInstall.Show(this.lsvInstalls.PointToScreen(e.Location));
            }
        }

        private void selectToolStripMenuItem_Click(object sender, EventArgs e) {
            foreach (ListViewItem item in this.lsvInstalls.Items) {
                item.Selected = true;
            }
        }

        private void alToolStripMenuItem_Click(object sender, EventArgs e) {
            foreach (ListViewItem item in this.lsvInstalls.Items) {
                item.Selected = false;
            }
        }

    }*/

    /*partial class GspUpdater {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(GspUpdater));
            this.btnStart = new System.Windows.Forms.Button();
            this.btnStop = new System.Windows.Forms.Button();
            this.btnUpdate = new System.Windows.Forms.Button();
            this.tmrUpdateChecker = new System.Windows.Forms.Timer(this.components);
            this.txtArguments = new System.Windows.Forms.TextBox();
            this.lblArguments = new System.Windows.Forms.Label();
            this.lblDownloadStatus = new System.Windows.Forms.Label();
            this.iglIcons = new System.Windows.Forms.ImageList(this.components);
            this.lblSearchInstalls = new System.Windows.Forms.Label();
            this.folderBrowser = new System.Windows.Forms.FolderBrowserDialog();
            this.txtBrowseFolder = new System.Windows.Forms.TextBox();
            this.btnBrowse = new System.Windows.Forms.Button();
            this.lsvInstalls = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colStatus = new System.Windows.Forms.ColumnHeader();
            this.colVersion = new System.Windows.Forms.ColumnHeader();
            this.colDirectory = new System.Windows.Forms.ColumnHeader();
            this.colPath = new System.Windows.Forms.ColumnHeader();
            this.ctxInstall = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.openFolderToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.selectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.alToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.ctxInstall.SuspendLayout();
            this.SuspendLayout();
            //
            // btnStart
            //
            this.btnStart.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnStart.Enabled = false;
            this.btnStart.Location = new System.Drawing.Point(344, 252);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(87, 27);
            this.btnStart.TabIndex = 1;
            this.btnStart.Text = "Start";
            this.btnStart.UseVisualStyleBackColor = true;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            //
            // btnStop
            //
            this.btnStop.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.btnStop.Enabled = false;
            this.btnStop.Location = new System.Drawing.Point(14, 251);
            this.btnStop.Name = "btnStop";
            this.btnStop.Size = new System.Drawing.Size(87, 27);
            this.btnStop.TabIndex = 3;
            this.btnStop.Text = "Stop";
            this.btnStop.UseVisualStyleBackColor = true;
            this.btnStop.Click += new System.EventHandler(this.btnStop_Click);
            //
            // btnUpdate
            //
            this.btnUpdate.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnUpdate.Enabled = false;
            this.btnUpdate.Location = new System.Drawing.Point(683, 251);
            this.btnUpdate.Name = "btnUpdate";
            this.btnUpdate.Size = new System.Drawing.Size(87, 27);
            this.btnUpdate.TabIndex = 4;
            this.btnUpdate.Text = "Update";
            this.btnUpdate.UseVisualStyleBackColor = true;
            this.btnUpdate.Click += new System.EventHandler(this.btnUpdate_Click);
            //
            // tmrUpdateChecker
            //
            this.tmrUpdateChecker.Interval = 1000;
            this.tmrUpdateChecker.Tick += new System.EventHandler(this.tmrUpdateChecker_Tick);
            //
            // txtArguments
            //
            this.txtArguments.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtArguments.Location = new System.Drawing.Point(182, 255);
            this.txtArguments.Name = "txtArguments";
            this.txtArguments.Size = new System.Drawing.Size(156, 23);
            this.txtArguments.TabIndex = 6;
            this.txtArguments.Text = "-name \"PRoCon: %directory%\" -console 1";
            //
            // lblArguments
            //
            this.lblArguments.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lblArguments.AutoSize = true;
            this.lblArguments.Location = new System.Drawing.Point(107, 257);
            this.lblArguments.Name = "lblArguments";
            this.lblArguments.Size = new System.Drawing.Size(69, 15);
            this.lblArguments.TabIndex = 7;
            this.lblArguments.Text = "Arguments:";
            //
            // lblDownloadStatus
            //
            this.lblDownloadStatus.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.lblDownloadStatus.Location = new System.Drawing.Point(437, 257);
            this.lblDownloadStatus.Name = "lblDownloadStatus";
            this.lblDownloadStatus.Size = new System.Drawing.Size(238, 21);
            this.lblDownloadStatus.TabIndex = 8;
            this.lblDownloadStatus.TextAlign = System.Drawing.ContentAlignment.TopRight;
            //
            // iglIcons
            //
            this.iglIcons.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("iglIcons.ImageStream")));
            this.iglIcons.TransparentColor = System.Drawing.Color.Transparent;
            this.iglIcons.Images.SetKeyName(0, "running.png");
            this.iglIcons.Images.SetKeyName(1, "stopped.png");
            //
            // lblSearchInstalls
            //
            this.lblSearchInstalls.AutoSize = true;
            this.lblSearchInstalls.Location = new System.Drawing.Point(11, 12);
            this.lblSearchInstalls.Name = "lblSearchInstalls";
            this.lblSearchInstalls.Size = new System.Drawing.Size(115, 15);
            this.lblSearchInstalls.TabIndex = 9;
            this.lblSearchInstalls.Text = "Search for installs at:";
            //
            // txtBrowseFolder
            //
            this.txtBrowseFolder.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtBrowseFolder.BackColor = System.Drawing.SystemColors.Window;
            this.txtBrowseFolder.Location = new System.Drawing.Point(133, 9);
            this.txtBrowseFolder.Name = "txtBrowseFolder";
            this.txtBrowseFolder.ReadOnly = true;
            this.txtBrowseFolder.Size = new System.Drawing.Size(544, 23);
            this.txtBrowseFolder.TabIndex = 10;
            //
            // btnBrowse
            //
            this.btnBrowse.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnBrowse.Location = new System.Drawing.Point(683, 6);
            this.btnBrowse.Name = "btnBrowse";
            this.btnBrowse.Size = new System.Drawing.Size(87, 26);
            this.btnBrowse.TabIndex = 11;
            this.btnBrowse.Text = "Browse";
            this.btnBrowse.UseVisualStyleBackColor = true;
            this.btnBrowse.Click += new System.EventHandler(this.btnBrowse_Click);
            //
            // lsvInstalls
            //
            this.lsvInstalls.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lsvInstalls.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colStatus,
            this.colVersion,
            this.colDirectory,
            this.colPath});
            this.lsvInstalls.FullRowSelect = true;
            this.lsvInstalls.GridLines = true;
            this.lsvInstalls.HideSelection = false;
            this.lsvInstalls.Location = new System.Drawing.Point(14, 38);
            this.lsvInstalls.Name = "lsvInstalls";
            this.lsvInstalls.Size = new System.Drawing.Size(756, 206);
            this.lsvInstalls.SmallImageList = this.iglIcons;
            this.lsvInstalls.TabIndex = 0;
            this.lsvInstalls.UseCompatibleStateImageBehavior = false;
            this.lsvInstalls.View = System.Windows.Forms.View.Details;
            this.lsvInstalls.MouseClick += new System.Windows.Forms.MouseEventHandler(this.lsvInstalls_MouseClick);
            this.lsvInstalls.SelectedIndexChanged += new System.EventHandler(this.lstInstalls_SelectedIndexChanged);
            this.lsvInstalls.DoubleClick += new System.EventHandler(this.lsvInstalls_DoubleClick);
            this.lsvInstalls.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsvInstalls_ColumnClick);
            //
            // colStatus
            //
            this.colStatus.Text = "Status";
            //
            // colVersion
            //
            this.colVersion.Text = "Version";
            //
            // colDirectory
            //
            this.colDirectory.Text = "Directory";
            //
            // colPath
            //
            this.colPath.Text = "Path";
            //
            // ctxInstall
            //
            this.ctxInstall.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.openFolderToolStripMenuItem,
            this.toolStripMenuItem1,
            this.selectToolStripMenuItem,
            this.alToolStripMenuItem});
            this.ctxInstall.Name = "ctxInstall";
            this.ctxInstall.Size = new System.Drawing.Size(153, 98);
            //
            // openFolderToolStripMenuItem
            //
            this.openFolderToolStripMenuItem.Name = "openFolderToolStripMenuItem";
            this.openFolderToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.openFolderToolStripMenuItem.Text = "Open Folder...";
            this.openFolderToolStripMenuItem.Click += new System.EventHandler(this.openFolderToolStripMenuItem_Click);
            //
            // toolStripMenuItem1
            //
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(149, 6);
            //
            // selectToolStripMenuItem
            //
            this.selectToolStripMenuItem.Name = "selectToolStripMenuItem";
            this.selectToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.selectToolStripMenuItem.Text = "Select All";
            this.selectToolStripMenuItem.Click += new System.EventHandler(this.selectToolStripMenuItem_Click);
            //
            // alToolStripMenuItem
            //
            this.alToolStripMenuItem.Name = "alToolStripMenuItem";
            this.alToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.alToolStripMenuItem.Text = "Select None";
            this.alToolStripMenuItem.Click += new System.EventHandler(this.alToolStripMenuItem_Click);
            //
            // GspUpdater
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 292);
            this.Controls.Add(this.btnBrowse);
            this.Controls.Add(this.txtBrowseFolder);
            this.Controls.Add(this.lblSearchInstalls);
            this.Controls.Add(this.lblDownloadStatus);
            this.Controls.Add(this.lblArguments);
            this.Controls.Add(this.txtArguments);
            this.Controls.Add(this.btnUpdate);
            this.Controls.Add(this.btnStop);
            this.Controls.Add(this.btnStart);
            this.Controls.Add(this.lsvInstalls);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MinimumSize = new System.Drawing.Size(800, 200);
            this.Name = "GspUpdater";
            this.Text = "GSP PRoCon Updater";
            this.Load += new System.EventHandler(this.GspUpdater_Load);
            this.Activated += new System.EventHandler(this.GspUpdater_Activated);
            this.ctxInstall.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private PRoCon.Controls.ControlsEx.ListViewNF lsvInstalls;
        private System.Windows.Forms.ColumnHeader colStatus;
        private System.Windows.Forms.ColumnHeader colVersion;
        private System.Windows.Forms.ColumnHeader colDirectory;
        private System.Windows.Forms.ColumnHeader colPath;
        private System.Windows.Forms.Button btnStart;
        private System.Windows.Forms.Button btnStop;
        private System.Windows.Forms.Button btnUpdate;
        private System.Windows.Forms.Timer tmrUpdateChecker;
        private System.Windows.Forms.TextBox txtArguments;
        private System.Windows.Forms.Label lblArguments;
        private System.Windows.Forms.Label lblDownloadStatus;
        private System.Windows.Forms.ImageList iglIcons;
        private System.Windows.Forms.Label lblSearchInstalls;
        private System.Windows.Forms.FolderBrowserDialog folderBrowser;
        private System.Windows.Forms.TextBox txtBrowseFolder;
        private System.Windows.Forms.Button btnBrowse;
        private System.Windows.Forms.ContextMenuStrip ctxInstall;
        private System.Windows.Forms.ToolStripMenuItem openFolderToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem selectToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem alToolStripMenuItem;
    }*/
}
