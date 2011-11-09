package procon.forms;

public class ProConGUI {

    /*public partial class frmMain : Form {

        public delegate PRoConApplication WindowLoadedHandler(bool execute);
        public event WindowLoadedHandler WindowLoaded;

        private Dictionary<string, uscPage> m_dicPages = new Dictionary<string, uscPage>();
        private CLocalization m_clocLanguage;

        private frmAbout m_frmAbout;
        private frmManageAccounts m_frmManageAccounts;
        private frmOptions m_frmOptions;
        //private frmPluginRepository m_frmPluginRepository;


        private ContextMenu m_cnmNotificationMenu;
        private MenuItem m_mnuHideTrayIcon;
        private MenuItem m_mnuSeparator;
        private MenuItem m_mnuExit;

        //private Rectangle m_recNormalBounds;

        //private int m_iMaxGspServers;
        //private int m_iBlockUpdateChecks;
        private bool m_blExit;

        //private CDownloadFile m_cdfVersionChecker;
        //private CDownloadFile m_cdfPRoConUpdate;

        //private static readonly object m_objDownloadingLocalizations = new object();
        //private List<CDownloadFile> m_lstDownloadingLocalizations;

        private PRoConApplication m_paProcon;

        public frmMain(string[] args) {
            InitializeComponent();

            this.SetStyle(ControlStyles.UserPaint, true);
            this.SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.DoubleBuffer, true);

            this.m_blExit = false;

            this.m_frmAbout = new frmAbout();

            this.m_cnmNotificationMenu = new ContextMenu();

            this.m_mnuHideTrayIcon = new MenuItem();
            this.m_mnuHideTrayIcon.Index = 0;
            this.m_mnuHideTrayIcon.Text = "Hide Tray Icon";
            this.m_mnuHideTrayIcon.Click += new System.EventHandler(this.m_mnuHideTrayIcon_Click);
            this.m_cnmNotificationMenu.MenuItems.Add(this.m_mnuHideTrayIcon);

            this.m_mnuSeparator = new MenuItem();
            this.m_mnuSeparator.Index = 1;
            this.m_mnuSeparator.Text = "-";
            this.m_cnmNotificationMenu.MenuItems.Add(this.m_mnuSeparator);

            this.m_mnuExit = new MenuItem();
            this.m_mnuExit.Index = 2;
            this.m_mnuExit.Text = "Exit";
            this.m_mnuExit.Click += new System.EventHandler(this.m_mnuExit_Click);
            this.m_cnmNotificationMenu.MenuItems.Add(this.m_mnuExit);

            this.ntfIcon.ContextMenu = this.m_cnmNotificationMenu;

            this.toolsStripDropDownButton.Image = this.iglIcons.Images["wrench.png"];
            this.manageAccountsToolStripMenuItem.Image = this.iglIcons.Images["vcard.png"];

            this.btnStartPage.Image = this.iglIcons.Images["home.png"];
            this.btnShiftServerPrevious.Image = this.iglIcons.Images["arrow-transition-180.png"];
            this.btnShiftServerNext.Image = this.iglIcons.Images["arrow-transition.png"];

            this.checkForUpdatesToolStripMenuItem.Image = this.iglIcons.Images["check.png"];

            //this.toolStripDownloading.Image = picAjaxStyleLoading.Image;

            this.cboServerList.ComboBox.DrawMode = DrawMode.OwnerDrawFixed;
            this.cboServerList.ComboBox.DrawItem += new DrawItemEventHandler(ComboBox_DrawItem);


            //this.m_cdfVersionChecker = new CDownloadFile("http://www.phogue.net/procon/version3.php");
            //this.m_cdfVersionChecker.DownloadComplete += new CDownloadFile.DownloadFileEventDelegate(m_cdfVersionChecker_DownloadComplete);
            //this.m_cdfVersionChecker.DownloadError += new CDownloadFile.DownloadFileEventDelegate(m_cdfVersionChecker_DownloadError);

            //this.m_lstDownloadingLocalizations = new List<CDownloadFile>();

            // Default language.
            //this.m_frmOptions.LocalizationFilename = "au.loc";
        }

        private void frmMain_Load(object sender, EventArgs e) {

            this.m_paProcon = this.WindowLoaded(false);
            this.SetupStartPage();

            this.m_paProcon.Connections.ConnectionAdded += new ConnectionDictionary.ConnectionAlteredHandler(Connections_ConnectionAdded);
            this.m_paProcon.Connections.ConnectionRemoved += new ConnectionDictionary.ConnectionAlteredHandler(Connections_ConnectionRemoved);
            this.m_paProcon.ShowNotification += new PRoConApplication.ShowNotificationHandler(m_paProcon_ShowNotification);
            this.m_paProcon.CurrentLanguageChanged += new PRoConApplication.CurrentLanguageHandler(m_paProcon_CurrentLanguageChanged);
            this.m_paProcon.OptionsSettings.ShowTrayIconChanged += new PRoCon.Core.Options.OptionsSettings.OptionsEnabledHandler(OptionsSettings_ShowTrayIconChanged);

            this.m_paProcon.AutoUpdater.CustomDownloadError += new AutoUpdater.CustomDownloadErrorHandler(AutoUpdater_CustomDownloadError);
            this.m_paProcon.AutoUpdater.DownloadUnzipComplete += new AutoUpdater.DownloadUnzipCompleteHandler(AutoUpdater_DownloadUnzipComplete);
            this.m_paProcon.AutoUpdater.CheckingUpdates += new AutoUpdater.CheckingUpdatesHandler(m_paProcon_CheckingUpdates);
            this.m_paProcon.AutoUpdater.NoVersionAvailable += new AutoUpdater.CheckingUpdatesHandler(m_paProcon_NoVersionAvailable);
            this.m_paProcon.AutoUpdater.VersionChecker.DownloadError += new CDownloadFile.DownloadFileEventDelegate(VersionChecker_DownloadError);
            this.m_paProcon.AutoUpdater.UpdateDownloading += new AutoUpdater.UpdateDownloadingHandler(m_paProcon_UpdateDownloading);

            this.m_paProcon.execute();

            if (this.m_paProcon.CustomTitle.Length > 0) {
                this.Text = this.m_paProcon.CustomTitle;
            }

            this.m_frmManageAccounts = new frmManageAccounts(this.m_paProcon, this);
            this.m_frmOptions = new frmOptions(this.m_paProcon, this);

            this.WindowState = this.m_paProcon.SavedWindowState;
            this.Bounds = this.m_paProcon.SavedWindowBounds;
            this.Refresh();

            //foreach (PRoConClient prcClient in this.m_paProcon.Connections) {
            //    this.Connections_ConnectionAdded(prcClient);
            //}

            this.m_paProcon.CurrentLanguage = this.m_paProcon.CurrentLanguage;
        }

        public void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            this.optionsToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("frmMain.optionsToolStripMenuItem") + "..";
            this.manageAccountsToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("frmMain.manageAccountsToolStripMenuItem") + "..";

            this.changelogToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("frmMain.changelogToolStripMenuItem");

            this.toolsStripDropDownButton.Text = this.m_clocLanguage.GetLocalized("frmMain.toolsToolStripMenuItem");

            this.checkForUpdatesToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("frmMain.checkForUpdatesToolStripMenuItem");
            this.aboutToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("frmMain.aboutToolStripMenuItem") + "..";

            this.btnStartPage.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnStartPage");
            this.btnShiftServerPrevious.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnShiftServerPrevious");
            this.btnShiftServerNext.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnShiftServerNext");

            this.chkAutomaticallyConnect.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.chkAutomaticallyConnect");

            foreach (KeyValuePair<string, uscPage> kvpPanel in this.m_dicPages) {
                kvpPanel.Value.SetLocalization(this.m_clocLanguage);
            }

            if (this.m_frmManageAccounts != null) {
                this.m_frmManageAccounts.SetLocalization(this.m_clocLanguage);
            }

            if (this.m_frmOptions != null) {
                this.m_frmOptions.SetLocalization(this.m_clocLanguage);
            }

            //if (this.m_frmPluginRepository != null) {
            //    this.m_frmPluginRepository.SetLocalization(this.m_clocLanguage);
            //}

            if (this.m_frmAbout != null) {
                this.m_frmAbout.SetLocalization(this.m_clocLanguage);
            }

            this.m_mnuHideTrayIcon.Text = this.m_clocLanguage.GetLocalized("m_cnmNotificationMenu.m_mnuHideTrayIcon", null);
            this.m_mnuExit.Text = this.m_clocLanguage.GetLocalized("m_cnmNotificationMenu.m_mnuExit", null);

            this.cboServerList.Size = new Size(this.tlsConnections.Bounds.Width - this.toolsStripDropDownButton.Bounds.Width - this.cboServerList.Bounds.Left - 15, 23);
        }

        public void SetupStartPage() {

            uscStartPage startPage = new uscStartPage(this.m_paProcon);
            startPage.ConnectionPage += new uscStartPage.ConnectionPageHandler(startPage_ConnectionPage);

            this.pnlWindows.Controls.Add(startPage);
            startPage.Dock = DockStyle.Fill;
            this.m_dicPages.Add("Start Page", startPage);
            this.cboServerList.ComboBox.Items.Insert(0, startPage);
            this.cboServerList.SelectedItem = startPage;
        }

        private void startPage_ConnectionPage(string hostNamePort) {

            foreach (Object page in this.cboServerList.Items) {

                if (page is uscServerConnection) {

                    if (String.Compare(((uscServerConnection)page).Client.HostNamePort, hostNamePort) == 0) {
                        this.cboServerList.SelectedItem = page;
                        break;
                    }
                }
            }
        }

        #region Manage accounts and options events

        private void m_paProcon_CurrentLanguageChanged(CLocalization language) {
            this.SetLocalization(language);
        }

        void OptionsSettings_ShowTrayIconChanged(bool blEnabled) {
            this.ntfIcon.Visible = blEnabled;
            this.ShowInTaskbar = true;
        }

        #endregion

        private void Connections_ConnectionAdded(PRoConClient item) {
            uscServerConnection uscNewConnectionPanel = null;

            uscNewConnectionPanel = new uscServerConnection(this.m_paProcon, item, this, this.m_frmManageAccounts);
            uscNewConnectionPanel.Dock = DockStyle.Fill;
            //uscNewConnectionPanel.SetLocalization(this.m_clocLanguage);
            //uscNewConnectionPanel.BFBC2Connection.Connect();

            uscNewConnectionPanel.ManageAccountsRequest += new uscServerConnection.ManageAccountsRequestDelegate(uscServerConnection_ManageAccountsRequest);
            uscNewConnectionPanel.OnTabChange += new uscServerConnection.OnTabChangeDelegate(uscNewConnectionPanel_OnTabChange);

            this.pnlWindows.Controls.Add(uscNewConnectionPanel);
            this.m_dicPages.Add(item.HostNamePort, uscNewConnectionPanel);

            this.cboServerList.ComboBox.Items.Add(uscNewConnectionPanel);
            if (this.cboServerList.SelectedItem == null) {
                this.cboServerList.SelectedItem = uscNewConnectionPanel;
            }

            item.ConnectionClosed += new PRoConClient.EmptyParamterHandler(item_ConnectionClosed);
            item.ConnectionFailure += new PRoConClient.FailureHandler(item_ConnectionFailure);
            item.ConnectSuccess += new PRoConClient.EmptyParamterHandler(item_ConnectSuccess);
            item.Login += new PRoConClient.EmptyParamterHandler(item_Login);
            item.ConnectAttempt += new PRoConClient.EmptyParamterHandler(item_ConnectAttempt);
            item.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(item_GameTypeDiscovered);
            item.AutomaticallyConnectChanged += new PRoConClient.AutomaticallyConnectHandler(item_AutomaticallyConnectChanged);

            this.RefreshServerListing();
        }

        private void Connections_ConnectionRemoved(PRoConClient item) {

            if (this.m_dicPages.ContainsKey(item.HostNamePort) == true) {

                if (this.cboServerList.Items.Contains(this.m_dicPages[item.HostNamePort]) == true) {

                    if (this.cboServerList.SelectedItem == this.m_dicPages[item.HostNamePort]) {
                        this.cboServerList.SelectedIndex = 0;
                    }

                    this.cboServerList.Items.Remove(this.m_dicPages[item.HostNamePort]);
                }

                this.m_dicPages.Remove(item.HostNamePort);
            }

            this.RefreshServerListing();
        }

        private void AddServer(string strHost, UInt16 iu16Port, string strUsername, string strPassword, bool blConnect) {

            //uscServerConnection uscNewConnectionPanel = null;

            PRoConClient prcClient = this.m_paProcon.AddConnection(strHost, iu16Port, strUsername, strPassword);

            if (blConnect == true && prcClient != null) {
                prcClient.Connect();
            }
        }

        private void uscNewConnectionPanel_OnTabChange(object sender, Stack<string> stkTabIndexes) {

            string[] a_strReversedStack = stkTabIndexes.ToArray();
            Array.Reverse(a_strReversedStack);

            foreach (KeyValuePair<string, uscPage> kvpPanel in this.m_dicPages) {
                if (kvpPanel.Value != sender && kvpPanel.Value is uscServerConnection) {

                    if (((uscServerConnection)kvpPanel.Value).Client.State == ConnectionState.Connected) {

                        try {
                            ((uscServerConnection)kvpPanel.Value).SetTabIndexes(new Stack<string>(a_strReversedStack));
                        }
                        catch (Exception) { }
                    }
                }
            }
        }

        private void uscServerConnection_ManageAccountsRequest(object sender, EventArgs e) {
            this.m_frmManageAccounts.ShowDialog();
        }

        private void aboutToolStripMenuItem_Click(object sender, EventArgs e) {
            this.m_frmAbout.ShowDialog();
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e) {
            this.m_blExit = true;
            this.Close();
        }

        private void userManagerToolStripMenuItem_Click(object sender, EventArgs e) {
            this.m_frmManageAccounts.ShowDialog();
        }

        private void donateTodayToolStripMenuItem_Click(object sender, EventArgs e) {
            System.Diagnostics.Process.Start("http://phogue.net/?page_id=380");
        }

        private void changelogToolStripMenuItem_Click(object sender, EventArgs e) {
            System.Diagnostics.Process.Start("http://phogue.net/procon/changelog.php");

        }

        private void pRoConHostingProvidersToolStripMenuItem_Click(object sender, EventArgs e) {
            System.Diagnostics.Process.Start("http://phogue.net/procon/proconhosting.php");
        }

        private void optionsToolStripMenuItem1_Click(object sender, EventArgs e) {
            this.m_frmOptions.ShowDialog();
        }

        //private void uscServerPlayerTreeviewListing_AutoConnectRequest(object sender) {
        //    if (this.m_dicConnectionPages.ContainsKey(e.ServerHostnamePort) == true) {
        //        this.m_dicConnectionPages[e.ServerHostnamePort].BFBC2Connection.AutomaticallyConnect = e.AutoConnect;

        //        //this.SaveMainConfig();
        //    }
        //}

        private void frmMain_FormClosed(object sender, FormClosedEventArgs e) {
            foreach (KeyValuePair<string, uscPage> kvpPanel in this.m_dicPages) {
                kvpPanel.Value.Dispose();
            }
        }

        private void frmMain_FormClosing(object sender, FormClosingEventArgs e) {

            if (this.m_blExit == false && e.CloseReason == CloseReason.UserClosing && this.m_paProcon.OptionsSettings.CloseToTray == true) {
                e.Cancel = true;

                this.WindowState = FormWindowState.Minimized;
            }
            else {

                if (this.WindowState == FormWindowState.Normal) {
                    this.m_paProcon.SavedWindowBounds = this.Bounds;
                    //this.m_recNormalBounds = this.Bounds;
                }

                this.m_paProcon.Shutdown();
            }
        }

        #region Notification area and to-tray effect

        private void m_paProcon_ShowNotification(int timeout, string title, string text, bool isError) {

            ToolTipIcon ttiDisplayIcon = ToolTipIcon.Info;

            if (isError == true) {
                ttiDisplayIcon = ToolTipIcon.Error;
            }

            this.ntfIcon.ShowBalloonTip(timeout, title, text, ttiDisplayIcon);
        }

        private void ntfIcon_DoubleClick(object sender, EventArgs e) {

            // Strange bug, without setting first to Normal then back to owner draw
            // the result is a blank server listing, but the items are all still there.
            this.cboServerList.ComboBox.DrawMode = DrawMode.Normal;
            this.cboServerList.ComboBox.DrawMode = DrawMode.OwnerDrawFixed;

            if (this.WindowState == FormWindowState.Minimized) {

                this.ShowInTaskbar = true;
                this.Show();
                this.WindowState = FormWindowState.Normal;
                //this.Show();

                bool bah = this.cboServerList.IsDisposed;
            }

            this.Activate();
        }

        private void m_mnuExit_Click(object Sender, EventArgs e) {
            this.m_blExit = true;
            this.Close();
        }

        private void m_mnuHideTrayIcon_Click(object Sender, EventArgs e) {
            this.m_paProcon.OptionsSettings.ShowTrayIcon = false;
        }

        private void frmMain_Resize(object sender, EventArgs e) {

            this.cboServerList.Size = new Size(this.tlsConnections.Bounds.Width - this.toolsStripDropDownButton.Bounds.Width - this.cboServerList.Bounds.Left - 15, 23);

            if (this.WindowState == FormWindowState.Minimized && (this.m_paProcon.OptionsSettings.MinimizeToTray == true || this.m_paProcon.OptionsSettings.CloseToTray == true)) {
                this.Hide();

                this.ShowInTaskbar = false;
            }
            else {
                this.Show();

                this.ShowInTaskbar = true;
            }

            if (this.WindowState == FormWindowState.Normal) {
                this.m_paProcon.SavedWindowBounds = this.Bounds;
            }

            this.m_paProcon.SavedWindowState = this.WindowState;
        }

        #endregion

        #region Version Checker..

        // Now only used if they manually check for an update and it comes back false.
        private bool m_blPopupVersionResults = false;

        private void m_paProcon_CheckingUpdates() {
            this.toolStripDownloading.ForeColor = SystemColors.WindowText;
            this.toolStripDownloading.IsLink = false;
            this.toolStripDownloading.Text = this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading.Checking");
            this.toolStripDownloading.Image = picAjaxStyleLoading.Image;
            this.toolStripDownloading.Visible = true;
        }

        void m_paProcon_NoVersionAvailable() {
            if (this.m_blPopupVersionResults == true) {
                MessageBox.Show(this.m_clocLanguage.GetLocalized("frmMain.MessageBox.NoUpdateAvailable", null), "PRoCon Frostbite", MessageBoxButtons.OK, MessageBoxIcon.Information);
                this.m_blPopupVersionResults = false;
            }

            this.toolStripDownloading.Visible = false;
        }

        void VersionChecker_DownloadError(CDownloadFile cdfSender) {
            this.toolStripDownloadProgress.Visible = false;

            this.toolStripDownloading.ForeColor = Color.Maroon;
            this.toolStripDownloading.Image = null;
            this.toolStripDownloading.Text = this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading.Error", cdfSender.Error);
        }

        void AutoUpdater_CustomDownloadError(string strError) {
            this.toolStripDownloadProgress.Visible = false;

            this.toolStripDownloading.ForeColor = Color.Maroon;
            this.toolStripDownloading.Image = null;
            this.toolStripDownloading.Text = this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading.Error", strError);
        }


        private void m_paProcon_UpdateDownloading(CDownloadFile cdfDownloading) {
            cdfDownloading.DownloadProgressUpdate += new CDownloadFile.DownloadFileEventDelegate(cdfDownloading_DownloadProgressUpdate);
            cdfDownloading.DownloadError += new CDownloadFile.DownloadFileEventDelegate(cdfDownloading_DownloadError);
            cdfDownloading.DownloadDiscoveredFileSize += new CDownloadFile.DownloadFileEventDelegate(cdfDownloading_DownloadDiscoveredFileSize);
        }

        private void cdfDownloading_DownloadDiscoveredFileSize(CDownloadFile cdfSender) {
            this.toolStripDownloading.ForeColor = SystemColors.WindowText;
            this.toolStripDownloading.Image = picAjaxStyleLoading.Image;
            this.toolStripDownloadProgress.Visible = true;

            this.toolStripDownloadProgress.Maximum = cdfSender.FileSize;
        }

        private void cdfDownloading_DownloadError(CDownloadFile cdfSender) {
            this.toolStripDownloadProgress.Visible = false;

            this.toolStripDownloading.ForeColor = Color.Maroon;
            this.toolStripDownloading.Image = null;
            this.toolStripDownloading.Text = this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading.Error", cdfSender.Error);
        }

        private void cdfDownloading_DownloadProgressUpdate(CDownloadFile cdfSender) {
            this.toolStripDownloadProgress.Value = cdfSender.BytesDownloaded;
            this.toolStripDownloading.Text = String.Format("{0} {1}", this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading", null), cdfSender.GetLabelProgress());
        }



        private void AutoUpdater_DownloadUnzipComplete() {

            this.toolStripDownloading.IsLink = true;
            this.toolStripDownloading.Image = this.iglIcons.Images["star.png"];
            this.toolStripDownloadProgress.Visible = false;

            this.toolStripDownloading.Text = this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading.Complete");
            this.tltpUpdateComplete.Show(this.m_clocLanguage.GetLocalized("frmMain.toolStripDownloading.Complete"), this, this.toolStripDownloading.Bounds.X + (this.toolStripDownloading.Bounds.Width / 2), this.stsMain.Bounds.Y, 5000);


        }

        private void toolStripDownloading_Click(object sender, EventArgs e) {

            if (this.toolStripDownloading.IsLink == true) {
                DialogResult dlgVisitPage = MessageBox.Show(this.m_clocLanguage.GetLocalized("frmMain.MessageBox.RestartProcon"), "PRoCon Frostbite", MessageBoxButtons.YesNo, MessageBoxIcon.Information);

                if (dlgVisitPage == DialogResult.Yes) {

                    AutoUpdater.BeginUpdateProcess(this.m_paProcon);

                    //System.Diagnostics.Process.Start(AppDomain.CurrentDomain.BaseDirectory + "PRoConUpdater.exe", String.Format("\"{0}\" \"{1}\"", this.m_strReleaseNotesLink, this.m_strDownloadSourceLink));
                    //this.Close();
                }
            }
        }

        private void checkForUpdatesToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.m_paProcon.AutoUpdater.VersionChecker.FileDownloading == false) {
                this.m_blPopupVersionResults = true;
                this.m_paProcon.AutoUpdater.CheckVersion();
            }
        }

        #endregion

        private void lblUpdateAvailable_Click(object sender, EventArgs e) {

            DialogResult dlgVisitPage = MessageBox.Show(this.m_clocLanguage.GetLocalized("frmMain.MessageBox.UpdateAvailable", null), "PRoCon Frostbite", MessageBoxButtons.YesNo, MessageBoxIcon.Information);

            if (dlgVisitPage == DialogResult.Yes) {
                //if (Regex.Match(strReleaseNotesLink, "^http://.*?$").Success == false) {
                //    strReleaseNotesLink = "http://" + strReleaseNotesLink;
                //}
                //System.Diagnostics.Process.Start(strReleaseNotesLink);

                //System.Diagnostics.Process.Start(AppDomain.CurrentDomain.BaseDirectory + "PRoConUpdater.exe", String.Format("\"{0}\" \"{1}\"", this.m_strReleaseNotesLink, this.m_strDownloadSourceLink));
                this.Close();
            }
        }


        private void m_frmNewConnection_CreateNewConnection(string hostname, string port, string userName, string password) {
            this.AddServer(hostname, ushort.Parse(port), userName, password, true);
        }

        private void cboServerList_SelectedIndexChanged(object sender, EventArgs e) {

            if (this.cboServerList.SelectedItem != null) {

                uscPage selectedServer = (uscPage)this.cboServerList.SelectedItem;

                selectedServer.Show();

                foreach (KeyValuePair<string, uscPage> kvpPanel in this.m_dicPages) {
                    if (kvpPanel.Value != selectedServer) {
                        kvpPanel.Value.Hide();
                    }
                }

                this.btnConnectDisconnect.Enabled = true;

                this.btnConnectDisconnect_MouseLeave(this, null);
                *//*
                if (selectedServer.BFBC2Connection.Connected == true) {
                    this.btnConnectDisconnect.Image = this.iglIcons.Images["plug-connect.png"];
                }
                else {
                    this.btnConnectDisconnect.Image = this.iglIcons.Images["plug-disconnect.png"];
                }
                *//*

                if (selectedServer is uscServerConnection) {
                    this.chkAutomaticallyConnect.Checked = ((uscServerConnection)selectedServer).Client.AutomaticallyConnect;

                    if (this.chkAutomaticallyConnect.Checked == true) {
                        this.chkAutomaticallyConnect.Image = this.iglIcons.Images["tick.png"];
                    }
                    else {
                        this.chkAutomaticallyConnect.Image = null;
                    }

                    this.btnConnectDisconnect.Enabled = this.chkAutomaticallyConnect.Enabled = true;
                }
                else {
                    this.btnConnectDisconnect.Enabled = this.chkAutomaticallyConnect.Enabled = false;
                }

            }
            else {
                this.btnConnectDisconnect.Enabled = false;
            }
        }

        private void ComboBox_DrawItem(object sender, DrawItemEventArgs e) {

            if (e.Index != -1) {

                e.DrawBackground();
                e.DrawFocusRectangle();

                if (this.cboServerList.ComboBox.Items[e.Index] is uscServerConnection) {

                    uscServerConnection drawItem = ((uscServerConnection)this.cboServerList.ComboBox.Items[e.Index]);

                    if (drawItem.Client.CurrentServerInfo != null) {

                        if (drawItem.Client.State == ConnectionState.Connected && drawItem.Client.IsLoggedIn == true) {
                            e.Graphics.DrawImage(this.iglIcons.Images["tick-button.png"], e.Bounds.Left + 57, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);
                        }
                        else if (drawItem.Client.State == ConnectionState.Error) {
                            e.Graphics.DrawImage(this.iglIcons.Images["cross-button.png"], e.Bounds.Left + 57, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);
                        }
                        else {
                            e.Graphics.DrawImage(this.iglIcons.Images["exclamation-button.png"], e.Bounds.Left + 57, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);
                        }

                        e.Graphics.DrawString(String.Format("[{0}/{1}] {2} [{3}]", drawItem.Client.CurrentServerInfo.PlayerCount, drawItem.Client.CurrentServerInfo.MaxPlayerCount, drawItem.Client.CurrentServerInfo.ServerName, drawItem.Client.HostNamePort), this.cboServerList.Font, SystemBrushes.WindowText, e.Bounds.Left + 75, e.Bounds.Top);

                        if (drawItem.Client.Game != null) {
                            if (drawItem.Client.CurrentServerInfo.GameMod == GameMods.None) {
                                e.Graphics.DrawImage(this.iglGameIcons.Images[String.Format("{0}.png", drawItem.Client.Game.GameType).ToLower()], e.Bounds.Left + 2, e.Bounds.Top + 1, this.iglGameIcons.ImageSize.Width, this.iglGameIcons.ImageSize.Height);
                            }
                            else {
                                e.Graphics.DrawImage(this.iglGameIcons.Images[String.Format("{0}.{1}.png", drawItem.Client.Game.GameType, drawItem.Client.CurrentServerInfo.GameMod).ToLower()], e.Bounds.Left + 2, e.Bounds.Top + 1, this.iglGameIcons.ImageSize.Width, this.iglGameIcons.ImageSize.Height);
                            }
                        }
                    }
                    else {

                        if (drawItem.Client.State == ConnectionState.Connected && drawItem.Client.IsLoggedIn == true) {
                            e.Graphics.DrawImage(this.iglIcons.Images["tick-button.png"], e.Bounds.Left + 2, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);
                        }
                        else if (drawItem.Client.State == ConnectionState.Error) {
                            e.Graphics.DrawImage(this.iglIcons.Images["cross-button.png"], e.Bounds.Left + 2, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);
                        }
                        else {
                            e.Graphics.DrawImage(this.iglIcons.Images["exclamation-button.png"], e.Bounds.Left + 2, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);
                        }

                        e.Graphics.DrawString(drawItem.Client.HostNamePort, this.cboServerList.Font, SystemBrushes.WindowText, e.Bounds.Left + 21, e.Bounds.Top);
                    }

                }
                else if (this.cboServerList.ComboBox.Items[e.Index] is uscStartPage) {
                    uscStartPage drawItem = ((uscStartPage)this.cboServerList.ComboBox.Items[e.Index]);

                    e.Graphics.DrawImage(this.iglIcons.Images["home.png"], e.Bounds.Left + 2, e.Bounds.Top + 1, this.iglIcons.ImageSize.Width, this.iglIcons.ImageSize.Height);

                    if (this.m_clocLanguage == null) {
                        e.Graphics.DrawString("Start Page", this.cboServerList.Font, SystemBrushes.WindowText, e.Bounds.Left + 21, e.Bounds.Top);
                    }
                    else {
                        e.Graphics.DrawString(this.m_clocLanguage.GetLocalized("uscStartPage.Title"), this.cboServerList.Font, SystemBrushes.WindowText, e.Bounds.Left + 21, e.Bounds.Top);
                    }
                }

                e.Graphics.Dispose();
            }
        }

        private void btnShiftServerPrevious_Click(object sender, EventArgs e) {
            if (this.cboServerList.Items.Count > 0) {
                if (this.cboServerList.SelectedIndex - 1 < 0) {
                    this.cboServerList.SelectedIndex = this.cboServerList.Items.Count - 1;
                }
                else {
                    this.cboServerList.SelectedIndex--;
                }
            }
        }

        private void btnShiftServerNext_Click(object sender, EventArgs e) {
            if (this.cboServerList.Items.Count > 0) {
                if (this.cboServerList.SelectedIndex + 1 >= this.cboServerList.Items.Count) {
                    this.cboServerList.SelectedIndex = 0;
                }
                else {
                    this.cboServerList.SelectedIndex++;
                }
            }
        }

        private void btnConnectDisconnect_MouseEnter(object sender, EventArgs e) {
            if (this.cboServerList.SelectedItem != null && this.cboServerList.SelectedItem is uscServerConnection) {

                uscServerConnection selectedServer = (uscServerConnection)this.cboServerList.SelectedItem;

                this.btnConnectDisconnect.Enabled = true;
                if (selectedServer.Client.State == ConnectionState.Connected) {
                    this.btnConnectDisconnect.Image = this.iglIcons.Images["plug-disconnect.png"];
                    if (this.m_clocLanguage != null) {
                        this.btnConnectDisconnect.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnConnectDisconnect.Disconnect");
                    }
                }
                else {
                    this.btnConnectDisconnect.Image = this.iglIcons.Images["plug-connect.png"];
                    if (this.m_clocLanguage != null) {
                        this.btnConnectDisconnect.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnConnectDisconnect.Connect");
                    }
                }
            }
        }

        private void btnConnectDisconnect_MouseLeave(object sender, EventArgs e) {
            if (this.cboServerList.SelectedItem != null && this.cboServerList.SelectedItem is uscServerConnection) {

                uscServerConnection selectedServer = (uscServerConnection)this.cboServerList.SelectedItem;

                this.btnConnectDisconnect.Enabled = true;
                if (selectedServer.Client.State == ConnectionState.Connected) {
                    this.btnConnectDisconnect.Image = this.iglIcons.Images["plug-connect.png"];
                    if (this.m_clocLanguage != null) {
                        this.btnConnectDisconnect.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnConnectDisconnect.Connect");
                    }
                }
                else {
                    this.btnConnectDisconnect.Image = this.iglIcons.Images["plug-disconnect.png"];
                    if (this.m_clocLanguage != null) {
                        this.btnConnectDisconnect.Text = this.m_clocLanguage.GetLocalized("fmrMain.tlsConnections.btnConnectDisconnect.Disconnect");
                    }
                }
            }
        }

        private void btnConnectDisconnect_Click(object sender, EventArgs e) {
            if (this.cboServerList.SelectedItem != null && this.cboServerList.SelectedItem is uscServerConnection) {

                uscServerConnection selectedServer = (uscServerConnection)this.cboServerList.SelectedItem;

                if (selectedServer.Client.State == ConnectionState.Connected) {
                    selectedServer.Client.ForceDisconnect();
                    selectedServer.Client.AutomaticallyConnect = false;
                }
                else {
                    selectedServer.Client.Connect();
                }
            }
        }

        private void RefreshServerListing() {
            //this.cboServerList.BeginUpdate();

            this.cboServerList_SelectedIndexChanged(null, null);

            Point cursor = this.PointToClient(Cursor.Position);
            //cursor.Y -= this.mnuMain.Height;

            if (this.cboServerList.SelectedItem != null && this.cboServerList.SelectedItem is uscServerConnection) {

                uscServerConnection selectedServer = (uscServerConnection)this.cboServerList.SelectedItem;
                object b = this.cboServerList.ComboBox.SelectedItem;
                if (selectedServer.Client.State == ConnectionState.Connecting || (selectedServer.Client.State == ConnectionState.Connected && selectedServer.Client.IsLoggedIn == false)) {
                    if (this.btnConnectDisconnect.Bounds.Contains(cursor) == true) {
                        this.btnConnectDisconnect_MouseEnter(null, null);
                    }
                }
            }


            //this.cboServerList.Invalidate();
            //this.cboServerList.EndUpdate();
        }

        private void item_ConnectAttempt(PRoConClient sender) {
            this.RefreshServerListing();
        }

        private void item_ConnectSuccess(PRoConClient sender) {
            this.RefreshServerListing();
        }

        private void item_Login(PRoConClient sender) {
            this.RefreshServerListing();
        }

        private void item_ConnectionFailure(PRoConClient sender, Exception exception) {
            this.RefreshServerListing();
        }

        private void item_ConnectionClosed(PRoConClient sender) {
            this.RefreshServerListing();
        }

        private void item_AutomaticallyConnectChanged(PRoConClient sender, bool isEnabled) {
            this.RefreshServerListing();
        }

        private void item_GameTypeDiscovered(PRoConClient sender) {
            if (sender.Game != null) {
                sender.Game.ServerInfo += new FrostbiteClient.ServerInfoHandler(Game_ServerInfo);
            }

            this.RefreshServerListing();
        }

        private void Game_ServerInfo(FrostbiteClient sender, CServerInfo csiServerInfo) {

            if (this.cboServerList.SelectedItem is uscServerConnection) {

                if (((uscServerConnection)this.cboServerList.SelectedItem).Client.Game == sender) {
                    this.cboServerList.ComboBox.Refresh();
                }
            }
        }

        *//*
        private void tmrConnectingLoop_Tick(object sender, EventArgs e) {
            Image gifImage = this.picAjaxStyleLoading.Image;
            FrameDimension dimension = new FrameDimension(gifImage.FrameDimensionsList[0]);
            int frameCount = gifImage.GetFrameCount(dimension);

            foreach (KeyValuePair<string, uscServerConnection> kvpPanel in this.m_dicConnectionPages) {
                if ((kvpPanel.Value.BFBC2Connection.IsConnected == false && kvpPanel.Value.BFBC2Connection.IsConnecting == true) || (kvpPanel.Value.BFBC2Connection.IsConnected == true && kvpPanel.Value.BFBC2Connection.IsLoggedIn == false)) {
                    kvpPanel.Value.ConnectingFrame = (kvpPanel.Value.ConnectingFrame + 1) % frameCount;

                    if ((kvpPanel.Value.BFBC2Connection.IsConnected == false && kvpPanel.Value.BFBC2Connection.IsConnecting == true) || (kvpPanel.Value.BFBC2Connection.IsConnected == true && kvpPanel.Value.BFBC2Connection.IsLoggedIn == false)) {

                        gifImage.SelectActiveFrame(dimension, kvpPanel.Value.ConnectingFrame);

                        this.btnConnectDisconnect.Image = gifImage;
                        this.btnConnectDisconnect.Text = "Connecting";
                    }
                }
            }
        }
        *//*

        private void m_frmConfirmation_ConfirmationSuccess() {
            if (this.cboServerList.SelectedItem != null) {
                uscServerConnection selectedServer = (uscServerConnection)this.cboServerList.SelectedItem;

                string strConfigFile = String.Format("{0}.cfg", selectedServer.Client.FileHostNamePort);

                selectedServer.Hide();

                // Find another panel to show while we remove this server.
                foreach (KeyValuePair<string, uscPage> kvpPanel in this.m_dicPages) {
                    if (kvpPanel.Value != selectedServer) {
                        this.cboServerList.SelectedItem = kvpPanel.Value;
                        break;
                    }
                }

                //this.m_dicConnectionPages[strServerHostnamePort].BFBC2Connection.Destroying();
                if (this.cboServerList.ComboBox.Items.Contains(selectedServer) == true) {
                    this.cboServerList.ComboBox.Items.Remove(selectedServer);
                }

                this.m_paProcon.Connections.Remove(selectedServer.Client);
                selectedServer.Dispose();

                this.m_dicPages.Remove(selectedServer.Client.HostNamePort);
                //this.uscServerPlayerTreeviewListing.OnServerDeleted(strServerHostnamePort);

                try {
                    if (File.Exists(Path.Combine("Configs", strConfigFile)) == true) {
                        File.Delete(Path.Combine("Configs", strConfigFile));
                    }
                }
                catch (Exception) { }
            }
        }

        private void chkAutomaticallyConnect_CheckedChanged(object sender, EventArgs e) {

            if (this.cboServerList.SelectedItem is uscServerConnection) {
                ((uscServerConnection)this.cboServerList.SelectedItem).Client.AutomaticallyConnect = this.chkAutomaticallyConnect.Checked;
            }

            if (this.chkAutomaticallyConnect.Checked == true) {
                this.chkAutomaticallyConnect.Image = this.iglIcons.Images["tick.png"];
            }
            else {
                this.chkAutomaticallyConnect.Image = null;
            }

        }

        private void btnStartPage_Click(object sender, EventArgs e) {

            foreach (uscPage page in this.cboServerList.Items) {
                if (page is uscStartPage) {
                    this.cboServerList.SelectedItem = page;
                }
            }

        }

        private void frmMain_ResizeEnd(object sender, EventArgs e) {
            this.cboServerList.Size = new Size(this.tlsConnections.Bounds.Width - this.toolsStripDropDownButton.Bounds.Width - this.cboServerList.Bounds.Left - 15, 23);
        }

        private void tlsConnections_SizeChanged(object sender, EventArgs e) {
            //this.cboServerList.Size = new Size(this.tlsConnections.Bounds.Width - this.toolsStripDropDownButton.Bounds.Width - this.cboServerList.Bounds.Left - 15, 23);
        }

    }*/

    /*partial class frmMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmMain));
            this.stsMain = new System.Windows.Forms.StatusStrip();
            this.toolStripDownloadProgress = new System.Windows.Forms.ToolStripProgressBar();
            this.toolStripDownloading = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStripRssFeed = new System.Windows.Forms.ToolStripStatusLabel();
            this.picLayerOffline = new System.Windows.Forms.PictureBox();
            this.picLayerOnline = new System.Windows.Forms.PictureBox();
            this.picPortCheckerClosed = new System.Windows.Forms.PictureBox();
            this.picPortCheckerOpen = new System.Windows.Forms.PictureBox();
            this.picPortCheckerUnknown = new System.Windows.Forms.PictureBox();
            this.picAjaxStyleSuccess = new System.Windows.Forms.PictureBox();
            this.picAjaxStyleFail = new System.Windows.Forms.PictureBox();
            this.picAjaxStyleLoading = new System.Windows.Forms.PictureBox();
            this.iglFlags = new System.Windows.Forms.ImageList(this.components);
            this.ntfIcon = new System.Windows.Forms.NotifyIcon(this.components);
            this.iglIcons = new System.Windows.Forms.ImageList(this.components);
            this.tltpUpdateComplete = new System.Windows.Forms.ToolTip(this.components);
            this.pnlWindows = new System.Windows.Forms.Panel();
            this.iglGameIcons = new System.Windows.Forms.ImageList(this.components);
            this.tlsConnections = new PRoCon.Controls.ControlsEx.ToolStripNF();
            this.btnStartPage = new System.Windows.Forms.ToolStripButton();
            this.btnShiftServerPrevious = new System.Windows.Forms.ToolStripButton();
            this.btnShiftServerNext = new System.Windows.Forms.ToolStripButton();
            this.cboServerList = new System.Windows.Forms.ToolStripComboBox();
            this.toolsStripDropDownButton = new System.Windows.Forms.ToolStripDropDownButton();
            this.btnConnectDisconnect = new System.Windows.Forms.ToolStripMenuItem();
            this.chkAutomaticallyConnect = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.optionsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.manageAccountsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.checkForUpdatesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.changelogToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.aboutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.stsMain.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerOffline)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerOnline)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picPortCheckerClosed)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picPortCheckerOpen)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picPortCheckerUnknown)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAjaxStyleSuccess)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAjaxStyleFail)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAjaxStyleLoading)).BeginInit();
            this.tlsConnections.SuspendLayout();
            this.SuspendLayout();
            //
            // stsMain
            //
            this.stsMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripDownloadProgress,
            this.toolStripDownloading,
            this.toolStripRssFeed});
            this.stsMain.Location = new System.Drawing.Point(0, 740);
            this.stsMain.Name = "stsMain";
            this.stsMain.Padding = new System.Windows.Forms.Padding(1, 0, 16, 0);
            this.stsMain.Size = new System.Drawing.Size(1008, 22);
            this.stsMain.TabIndex = 1;
            this.stsMain.Text = "statusStrip1";
            //
            // toolStripDownloadProgress
            //
            this.toolStripDownloadProgress.Name = "toolStripDownloadProgress";
            this.toolStripDownloadProgress.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.toolStripDownloadProgress.Size = new System.Drawing.Size(100, 16);
            this.toolStripDownloadProgress.Visible = false;
            //
            // toolStripDownloading
            //
            this.toolStripDownloading.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.toolStripDownloading.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.toolStripDownloading.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.toolStripDownloading.Name = "toolStripDownloading";
            this.toolStripDownloading.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.toolStripDownloading.Size = new System.Drawing.Size(19, 17);
            this.toolStripDownloading.Text = "....";
            this.toolStripDownloading.Visible = false;
            this.toolStripDownloading.Click += new System.EventHandler(this.toolStripDownloading_Click);
            //
            // toolStripRssFeed
            //
            this.toolStripRssFeed.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.toolStripRssFeed.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.toolStripRssFeed.IsLink = true;
            this.toolStripRssFeed.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.toolStripRssFeed.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.toolStripRssFeed.Name = "toolStripRssFeed";
            this.toolStripRssFeed.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.toolStripRssFeed.Size = new System.Drawing.Size(991, 17);
            this.toolStripRssFeed.Spring = true;
            this.toolStripRssFeed.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            //
            // picLayerOffline
            //
            this.picLayerOffline.Image = ((System.Drawing.Image)(resources.GetObject("picLayerOffline.Image")));
            this.picLayerOffline.Location = new System.Drawing.Point(300, 87);
            this.picLayerOffline.Name = "picLayerOffline";
            this.picLayerOffline.Size = new System.Drawing.Size(65, 52);
            this.picLayerOffline.TabIndex = 7;
            this.picLayerOffline.TabStop = false;
            this.picLayerOffline.Visible = false;
            //
            // picLayerOnline
            //
            this.picLayerOnline.Image = ((System.Drawing.Image)(resources.GetObject("picLayerOnline.Image")));
            this.picLayerOnline.Location = new System.Drawing.Point(371, 87);
            this.picLayerOnline.Name = "picLayerOnline";
            this.picLayerOnline.Size = new System.Drawing.Size(65, 52);
            this.picLayerOnline.TabIndex = 6;
            this.picLayerOnline.TabStop = false;
            this.picLayerOnline.Visible = false;
            //
            // picPortCheckerClosed
            //
            this.picPortCheckerClosed.Image = ((System.Drawing.Image)(resources.GetObject("picPortCheckerClosed.Image")));
            this.picPortCheckerClosed.Location = new System.Drawing.Point(285, 180);
            this.picPortCheckerClosed.Name = "picPortCheckerClosed";
            this.picPortCheckerClosed.Size = new System.Drawing.Size(69, 52);
            this.picPortCheckerClosed.TabIndex = 5;
            this.picPortCheckerClosed.TabStop = false;
            this.picPortCheckerClosed.Visible = false;
            //
            // picPortCheckerOpen
            //
            this.picPortCheckerOpen.Image = ((System.Drawing.Image)(resources.GetObject("picPortCheckerOpen.Image")));
            this.picPortCheckerOpen.Location = new System.Drawing.Point(250, 180);
            this.picPortCheckerOpen.Name = "picPortCheckerOpen";
            this.picPortCheckerOpen.Size = new System.Drawing.Size(28, 25);
            this.picPortCheckerOpen.TabIndex = 4;
            this.picPortCheckerOpen.TabStop = false;
            this.picPortCheckerOpen.Visible = false;
            //
            // picPortCheckerUnknown
            //
            this.picPortCheckerUnknown.Image = ((System.Drawing.Image)(resources.GetObject("picPortCheckerUnknown.Image")));
            this.picPortCheckerUnknown.Location = new System.Drawing.Point(178, 180);
            this.picPortCheckerUnknown.Name = "picPortCheckerUnknown";
            this.picPortCheckerUnknown.Size = new System.Drawing.Size(65, 52);
            this.picPortCheckerUnknown.TabIndex = 3;
            this.picPortCheckerUnknown.TabStop = false;
            this.picPortCheckerUnknown.Visible = false;
            //
            // picAjaxStyleSuccess
            //
            this.picAjaxStyleSuccess.Image = ((System.Drawing.Image)(resources.GetObject("picAjaxStyleSuccess.Image")));
            this.picAjaxStyleSuccess.Location = new System.Drawing.Point(250, 87);
            this.picAjaxStyleSuccess.Name = "picAjaxStyleSuccess";
            this.picAjaxStyleSuccess.Size = new System.Drawing.Size(28, 25);
            this.picAjaxStyleSuccess.TabIndex = 2;
            this.picAjaxStyleSuccess.TabStop = false;
            this.picAjaxStyleSuccess.Visible = false;
            //
            // picAjaxStyleFail
            //
            this.picAjaxStyleFail.Image = ((System.Drawing.Image)(resources.GetObject("picAjaxStyleFail.Image")));
            this.picAjaxStyleFail.Location = new System.Drawing.Point(213, 85);
            this.picAjaxStyleFail.Name = "picAjaxStyleFail";
            this.picAjaxStyleFail.Size = new System.Drawing.Size(30, 27);
            this.picAjaxStyleFail.TabIndex = 1;
            this.picAjaxStyleFail.TabStop = false;
            this.picAjaxStyleFail.Visible = false;
            //
            // picAjaxStyleLoading
            //
            this.picAjaxStyleLoading.Image = ((System.Drawing.Image)(resources.GetObject("picAjaxStyleLoading.Image")));
            this.picAjaxStyleLoading.Location = new System.Drawing.Point(160, 85);
            this.picAjaxStyleLoading.Name = "picAjaxStyleLoading";
            this.picAjaxStyleLoading.Size = new System.Drawing.Size(45, 40);
            this.picAjaxStyleLoading.TabIndex = 0;
            this.picAjaxStyleLoading.TabStop = false;
            this.picAjaxStyleLoading.Visible = false;
            //
            // iglFlags
            //
            this.iglFlags.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("iglFlags.ImageStream")));
            this.iglFlags.TransparentColor = System.Drawing.Color.Transparent;
            this.iglFlags.Images.SetKeyName(0, "ad.png");
            this.iglFlags.Images.SetKeyName(1, "ae.png");
            this.iglFlags.Images.SetKeyName(2, "af.png");
            this.iglFlags.Images.SetKeyName(3, "ag.png");
            this.iglFlags.Images.SetKeyName(4, "ai.png");
            this.iglFlags.Images.SetKeyName(5, "al.png");
            this.iglFlags.Images.SetKeyName(6, "am.png");
            this.iglFlags.Images.SetKeyName(7, "an.png");
            this.iglFlags.Images.SetKeyName(8, "ao.png");
            this.iglFlags.Images.SetKeyName(9, "ar.png");
            this.iglFlags.Images.SetKeyName(10, "as.png");
            this.iglFlags.Images.SetKeyName(11, "at.png");
            this.iglFlags.Images.SetKeyName(12, "au.png");
            this.iglFlags.Images.SetKeyName(13, "aw.png");
            this.iglFlags.Images.SetKeyName(14, "ax.png");
            this.iglFlags.Images.SetKeyName(15, "az.png");
            this.iglFlags.Images.SetKeyName(16, "ba.png");
            this.iglFlags.Images.SetKeyName(17, "bb.png");
            this.iglFlags.Images.SetKeyName(18, "bd.png");
            this.iglFlags.Images.SetKeyName(19, "be.png");
            this.iglFlags.Images.SetKeyName(20, "bf.png");
            this.iglFlags.Images.SetKeyName(21, "bg.png");
            this.iglFlags.Images.SetKeyName(22, "bh.png");
            this.iglFlags.Images.SetKeyName(23, "bi.png");
            this.iglFlags.Images.SetKeyName(24, "bj.png");
            this.iglFlags.Images.SetKeyName(25, "bm.png");
            this.iglFlags.Images.SetKeyName(26, "bn.png");
            this.iglFlags.Images.SetKeyName(27, "bo.png");
            this.iglFlags.Images.SetKeyName(28, "br.png");
            this.iglFlags.Images.SetKeyName(29, "bs.png");
            this.iglFlags.Images.SetKeyName(30, "bt.png");
            this.iglFlags.Images.SetKeyName(31, "bv.png");
            this.iglFlags.Images.SetKeyName(32, "bw.png");
            this.iglFlags.Images.SetKeyName(33, "by.png");
            this.iglFlags.Images.SetKeyName(34, "bz.png");
            this.iglFlags.Images.SetKeyName(35, "ca.png");
            this.iglFlags.Images.SetKeyName(36, "catalonia.png");
            this.iglFlags.Images.SetKeyName(37, "cc.png");
            this.iglFlags.Images.SetKeyName(38, "cd.png");
            this.iglFlags.Images.SetKeyName(39, "cf.png");
            this.iglFlags.Images.SetKeyName(40, "cg.png");
            this.iglFlags.Images.SetKeyName(41, "ch.png");
            this.iglFlags.Images.SetKeyName(42, "ci.png");
            this.iglFlags.Images.SetKeyName(43, "ck.png");
            this.iglFlags.Images.SetKeyName(44, "cl.png");
            this.iglFlags.Images.SetKeyName(45, "cm.png");
            this.iglFlags.Images.SetKeyName(46, "cn.png");
            this.iglFlags.Images.SetKeyName(47, "co.png");
            this.iglFlags.Images.SetKeyName(48, "cr.png");
            this.iglFlags.Images.SetKeyName(49, "cs.png");
            this.iglFlags.Images.SetKeyName(50, "cu.png");
            this.iglFlags.Images.SetKeyName(51, "cv.png");
            this.iglFlags.Images.SetKeyName(52, "cx.png");
            this.iglFlags.Images.SetKeyName(53, "cy.png");
            this.iglFlags.Images.SetKeyName(54, "cz.png");
            this.iglFlags.Images.SetKeyName(55, "de.png");
            this.iglFlags.Images.SetKeyName(56, "dj.png");
            this.iglFlags.Images.SetKeyName(57, "dk.png");
            this.iglFlags.Images.SetKeyName(58, "dm.png");
            this.iglFlags.Images.SetKeyName(59, "do.png");
            this.iglFlags.Images.SetKeyName(60, "dz.png");
            this.iglFlags.Images.SetKeyName(61, "ec.png");
            this.iglFlags.Images.SetKeyName(62, "ee.png");
            this.iglFlags.Images.SetKeyName(63, "eg.png");
            this.iglFlags.Images.SetKeyName(64, "eh.png");
            this.iglFlags.Images.SetKeyName(65, "england.png");
            this.iglFlags.Images.SetKeyName(66, "er.png");
            this.iglFlags.Images.SetKeyName(67, "es.png");
            this.iglFlags.Images.SetKeyName(68, "et.png");
            this.iglFlags.Images.SetKeyName(69, "europeanunion.png");
            this.iglFlags.Images.SetKeyName(70, "fam.png");
            this.iglFlags.Images.SetKeyName(71, "fi.png");
            this.iglFlags.Images.SetKeyName(72, "fj.png");
            this.iglFlags.Images.SetKeyName(73, "fk.png");
            this.iglFlags.Images.SetKeyName(74, "fm.png");
            this.iglFlags.Images.SetKeyName(75, "fo.png");
            this.iglFlags.Images.SetKeyName(76, "fr.png");
            this.iglFlags.Images.SetKeyName(77, "ga.png");
            this.iglFlags.Images.SetKeyName(78, "gb.png");
            this.iglFlags.Images.SetKeyName(79, "gd.png");
            this.iglFlags.Images.SetKeyName(80, "ge.png");
            this.iglFlags.Images.SetKeyName(81, "gf.png");
            this.iglFlags.Images.SetKeyName(82, "gh.png");
            this.iglFlags.Images.SetKeyName(83, "gi.png");
            this.iglFlags.Images.SetKeyName(84, "gl.png");
            this.iglFlags.Images.SetKeyName(85, "gm.png");
            this.iglFlags.Images.SetKeyName(86, "gn.png");
            this.iglFlags.Images.SetKeyName(87, "gp.png");
            this.iglFlags.Images.SetKeyName(88, "gq.png");
            this.iglFlags.Images.SetKeyName(89, "gr.png");
            this.iglFlags.Images.SetKeyName(90, "gs.png");
            this.iglFlags.Images.SetKeyName(91, "gt.png");
            this.iglFlags.Images.SetKeyName(92, "gu.png");
            this.iglFlags.Images.SetKeyName(93, "gw.png");
            this.iglFlags.Images.SetKeyName(94, "gy.png");
            this.iglFlags.Images.SetKeyName(95, "hk.png");
            this.iglFlags.Images.SetKeyName(96, "hm.png");
            this.iglFlags.Images.SetKeyName(97, "hn.png");
            this.iglFlags.Images.SetKeyName(98, "hr.png");
            this.iglFlags.Images.SetKeyName(99, "ht.png");
            this.iglFlags.Images.SetKeyName(100, "hu.png");
            this.iglFlags.Images.SetKeyName(101, "id.png");
            this.iglFlags.Images.SetKeyName(102, "ie.png");
            this.iglFlags.Images.SetKeyName(103, "il.png");
            this.iglFlags.Images.SetKeyName(104, "in.png");
            this.iglFlags.Images.SetKeyName(105, "io.png");
            this.iglFlags.Images.SetKeyName(106, "iq.png");
            this.iglFlags.Images.SetKeyName(107, "ir.png");
            this.iglFlags.Images.SetKeyName(108, "is.png");
            this.iglFlags.Images.SetKeyName(109, "it.png");
            this.iglFlags.Images.SetKeyName(110, "jm.png");
            this.iglFlags.Images.SetKeyName(111, "jo.png");
            this.iglFlags.Images.SetKeyName(112, "jp.png");
            this.iglFlags.Images.SetKeyName(113, "ke.png");
            this.iglFlags.Images.SetKeyName(114, "kg.png");
            this.iglFlags.Images.SetKeyName(115, "kh.png");
            this.iglFlags.Images.SetKeyName(116, "ki.png");
            this.iglFlags.Images.SetKeyName(117, "km.png");
            this.iglFlags.Images.SetKeyName(118, "kn.png");
            this.iglFlags.Images.SetKeyName(119, "kp.png");
            this.iglFlags.Images.SetKeyName(120, "kr.png");
            this.iglFlags.Images.SetKeyName(121, "kw.png");
            this.iglFlags.Images.SetKeyName(122, "ky.png");
            this.iglFlags.Images.SetKeyName(123, "kz.png");
            this.iglFlags.Images.SetKeyName(124, "la.png");
            this.iglFlags.Images.SetKeyName(125, "lb.png");
            this.iglFlags.Images.SetKeyName(126, "lc.png");
            this.iglFlags.Images.SetKeyName(127, "li.png");
            this.iglFlags.Images.SetKeyName(128, "lk.png");
            this.iglFlags.Images.SetKeyName(129, "lr.png");
            this.iglFlags.Images.SetKeyName(130, "ls.png");
            this.iglFlags.Images.SetKeyName(131, "lt.png");
            this.iglFlags.Images.SetKeyName(132, "lu.png");
            this.iglFlags.Images.SetKeyName(133, "lv.png");
            this.iglFlags.Images.SetKeyName(134, "ly.png");
            this.iglFlags.Images.SetKeyName(135, "ma.png");
            this.iglFlags.Images.SetKeyName(136, "mc.png");
            this.iglFlags.Images.SetKeyName(137, "md.png");
            this.iglFlags.Images.SetKeyName(138, "me.png");
            this.iglFlags.Images.SetKeyName(139, "mg.png");
            this.iglFlags.Images.SetKeyName(140, "mh.png");
            this.iglFlags.Images.SetKeyName(141, "mk.png");
            this.iglFlags.Images.SetKeyName(142, "ml.png");
            this.iglFlags.Images.SetKeyName(143, "mm.png");
            this.iglFlags.Images.SetKeyName(144, "mn.png");
            this.iglFlags.Images.SetKeyName(145, "mo.png");
            this.iglFlags.Images.SetKeyName(146, "mp.png");
            this.iglFlags.Images.SetKeyName(147, "mq.png");
            this.iglFlags.Images.SetKeyName(148, "mr.png");
            this.iglFlags.Images.SetKeyName(149, "ms.png");
            this.iglFlags.Images.SetKeyName(150, "mt.png");
            this.iglFlags.Images.SetKeyName(151, "mu.png");
            this.iglFlags.Images.SetKeyName(152, "mv.png");
            this.iglFlags.Images.SetKeyName(153, "mw.png");
            this.iglFlags.Images.SetKeyName(154, "mx.png");
            this.iglFlags.Images.SetKeyName(155, "my.png");
            this.iglFlags.Images.SetKeyName(156, "mz.png");
            this.iglFlags.Images.SetKeyName(157, "na.png");
            this.iglFlags.Images.SetKeyName(158, "nc.png");
            this.iglFlags.Images.SetKeyName(159, "ne.png");
            this.iglFlags.Images.SetKeyName(160, "nf.png");
            this.iglFlags.Images.SetKeyName(161, "ng.png");
            this.iglFlags.Images.SetKeyName(162, "ni.png");
            this.iglFlags.Images.SetKeyName(163, "nl.png");
            this.iglFlags.Images.SetKeyName(164, "no.png");
            this.iglFlags.Images.SetKeyName(165, "np.png");
            this.iglFlags.Images.SetKeyName(166, "nr.png");
            this.iglFlags.Images.SetKeyName(167, "nu.png");
            this.iglFlags.Images.SetKeyName(168, "nz.png");
            this.iglFlags.Images.SetKeyName(169, "om.png");
            this.iglFlags.Images.SetKeyName(170, "pa.png");
            this.iglFlags.Images.SetKeyName(171, "pe.png");
            this.iglFlags.Images.SetKeyName(172, "pf.png");
            this.iglFlags.Images.SetKeyName(173, "pg.png");
            this.iglFlags.Images.SetKeyName(174, "ph.png");
            this.iglFlags.Images.SetKeyName(175, "pk.png");
            this.iglFlags.Images.SetKeyName(176, "pl.png");
            this.iglFlags.Images.SetKeyName(177, "pm.png");
            this.iglFlags.Images.SetKeyName(178, "pn.png");
            this.iglFlags.Images.SetKeyName(179, "pr.png");
            this.iglFlags.Images.SetKeyName(180, "ps.png");
            this.iglFlags.Images.SetKeyName(181, "pt.png");
            this.iglFlags.Images.SetKeyName(182, "pw.png");
            this.iglFlags.Images.SetKeyName(183, "py.png");
            this.iglFlags.Images.SetKeyName(184, "qa.png");
            this.iglFlags.Images.SetKeyName(185, "re.png");
            this.iglFlags.Images.SetKeyName(186, "ro.png");
            this.iglFlags.Images.SetKeyName(187, "rs.png");
            this.iglFlags.Images.SetKeyName(188, "ru.png");
            this.iglFlags.Images.SetKeyName(189, "rw.png");
            this.iglFlags.Images.SetKeyName(190, "sa.png");
            this.iglFlags.Images.SetKeyName(191, "sb.png");
            this.iglFlags.Images.SetKeyName(192, "sc.png");
            this.iglFlags.Images.SetKeyName(193, "scotland.png");
            this.iglFlags.Images.SetKeyName(194, "sd.png");
            this.iglFlags.Images.SetKeyName(195, "se.png");
            this.iglFlags.Images.SetKeyName(196, "sg.png");
            this.iglFlags.Images.SetKeyName(197, "sh.png");
            this.iglFlags.Images.SetKeyName(198, "si.png");
            this.iglFlags.Images.SetKeyName(199, "sj.png");
            this.iglFlags.Images.SetKeyName(200, "sk.png");
            this.iglFlags.Images.SetKeyName(201, "sl.png");
            this.iglFlags.Images.SetKeyName(202, "sm.png");
            this.iglFlags.Images.SetKeyName(203, "sn.png");
            this.iglFlags.Images.SetKeyName(204, "so.png");
            this.iglFlags.Images.SetKeyName(205, "sr.png");
            this.iglFlags.Images.SetKeyName(206, "st.png");
            this.iglFlags.Images.SetKeyName(207, "sv.png");
            this.iglFlags.Images.SetKeyName(208, "sy.png");
            this.iglFlags.Images.SetKeyName(209, "sz.png");
            this.iglFlags.Images.SetKeyName(210, "tc.png");
            this.iglFlags.Images.SetKeyName(211, "td.png");
            this.iglFlags.Images.SetKeyName(212, "tf.png");
            this.iglFlags.Images.SetKeyName(213, "tg.png");
            this.iglFlags.Images.SetKeyName(214, "th.png");
            this.iglFlags.Images.SetKeyName(215, "tj.png");
            this.iglFlags.Images.SetKeyName(216, "tk.png");
            this.iglFlags.Images.SetKeyName(217, "tl.png");
            this.iglFlags.Images.SetKeyName(218, "tm.png");
            this.iglFlags.Images.SetKeyName(219, "tn.png");
            this.iglFlags.Images.SetKeyName(220, "to.png");
            this.iglFlags.Images.SetKeyName(221, "tr.png");
            this.iglFlags.Images.SetKeyName(222, "tt.png");
            this.iglFlags.Images.SetKeyName(223, "tv.png");
            this.iglFlags.Images.SetKeyName(224, "tw.png");
            this.iglFlags.Images.SetKeyName(225, "tz.png");
            this.iglFlags.Images.SetKeyName(226, "ua.png");
            this.iglFlags.Images.SetKeyName(227, "ug.png");
            this.iglFlags.Images.SetKeyName(228, "um.png");
            this.iglFlags.Images.SetKeyName(229, "unknown.png");
            this.iglFlags.Images.SetKeyName(230, "us.png");
            this.iglFlags.Images.SetKeyName(231, "uy.png");
            this.iglFlags.Images.SetKeyName(232, "uz.png");
            this.iglFlags.Images.SetKeyName(233, "va.png");
            this.iglFlags.Images.SetKeyName(234, "vc.png");
            this.iglFlags.Images.SetKeyName(235, "ve.png");
            this.iglFlags.Images.SetKeyName(236, "vg.png");
            this.iglFlags.Images.SetKeyName(237, "vi.png");
            this.iglFlags.Images.SetKeyName(238, "vn.png");
            this.iglFlags.Images.SetKeyName(239, "vu.png");
            this.iglFlags.Images.SetKeyName(240, "wales.png");
            this.iglFlags.Images.SetKeyName(241, "wf.png");
            this.iglFlags.Images.SetKeyName(242, "ws.png");
            this.iglFlags.Images.SetKeyName(243, "ye.png");
            this.iglFlags.Images.SetKeyName(244, "yt.png");
            this.iglFlags.Images.SetKeyName(245, "za.png");
            this.iglFlags.Images.SetKeyName(246, "zm.png");
            this.iglFlags.Images.SetKeyName(247, "zw.png");
            this.iglFlags.Images.SetKeyName(248, "flag_death.png");
            //
            // ntfIcon
            //
            this.ntfIcon.Icon = ((System.Drawing.Icon)(resources.GetObject("ntfIcon.Icon")));
            this.ntfIcon.Text = "PRoCon Frostbite";
            this.ntfIcon.Visible = true;
            this.ntfIcon.DoubleClick += new System.EventHandler(this.ntfIcon_DoubleClick);
            //
            // iglIcons
            //
            this.iglIcons.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("iglIcons.ImageStream")));
            this.iglIcons.TransparentColor = System.Drawing.Color.Transparent;
            this.iglIcons.Images.SetKeyName(0, "plugin.png");
            this.iglIcons.Images.SetKeyName(1, "application_xp_terminal.png");
            this.iglIcons.Images.SetKeyName(2, "comments.png");
            this.iglIcons.Images.SetKeyName(3, "table.png");
            this.iglIcons.Images.SetKeyName(4, "vcard.png");
            this.iglIcons.Images.SetKeyName(5, "mouse.png");
            this.iglIcons.Images.SetKeyName(6, "server_edit.png");
            this.iglIcons.Images.SetKeyName(7, "mouse_ban.png");
            this.iglIcons.Images.SetKeyName(8, "world.png");
            this.iglIcons.Images.SetKeyName(9, "user.png");
            this.iglIcons.Images.SetKeyName(10, "money_dollar.png");
            this.iglIcons.Images.SetKeyName(11, "information.png");
            this.iglIcons.Images.SetKeyName(12, "plugin_disabled.png");
            this.iglIcons.Images.SetKeyName(13, "plugin_edit.png");
            this.iglIcons.Images.SetKeyName(14, "status_offline.png");
            this.iglIcons.Images.SetKeyName(15, "sitemap_color.png");
            this.iglIcons.Images.SetKeyName(16, "bullet_arrow_up.png");
            this.iglIcons.Images.SetKeyName(17, "bullet_arrow_down.png");
            this.iglIcons.Images.SetKeyName(18, "flag_blue.png");
            this.iglIcons.Images.SetKeyName(19, "bfbc2server.png");
            this.iglIcons.Images.SetKeyName(20, "connect.png");
            this.iglIcons.Images.SetKeyName(21, "layer.png");
            this.iglIcons.Images.SetKeyName(22, "cross.png");
            this.iglIcons.Images.SetKeyName(23, "new.png");
            this.iglIcons.Images.SetKeyName(24, "key_delete.png");
            this.iglIcons.Images.SetKeyName(25, "key.png");
            this.iglIcons.Images.SetKeyName(26, "punkbuster.png");
            this.iglIcons.Images.SetKeyName(27, "arrow_up.png");
            this.iglIcons.Images.SetKeyName(28, "arrow_down.png");
            this.iglIcons.Images.SetKeyName(29, "tick.png");
            this.iglIcons.Images.SetKeyName(30, "page_copy.png");
            this.iglIcons.Images.SetKeyName(31, "arrow_right.png");
            this.iglIcons.Images.SetKeyName(32, "arrow_left.png");
            this.iglIcons.Images.SetKeyName(33, "add.png");
            this.iglIcons.Images.SetKeyName(34, "arrow_refresh.png");
            this.iglIcons.Images.SetKeyName(35, "application.png");
            this.iglIcons.Images.SetKeyName(36, "application_tile_horizontal.png");
            this.iglIcons.Images.SetKeyName(37, "application_tile.png");
            this.iglIcons.Images.SetKeyName(38, "star.png");
            this.iglIcons.Images.SetKeyName(39, "cursor.png");
            this.iglIcons.Images.SetKeyName(40, "shape_rotate_anticlockwise.png");
            this.iglIcons.Images.SetKeyName(41, "shape_rotate_clockwise.png");
            this.iglIcons.Images.SetKeyName(42, "shape_square_edit.png");
            this.iglIcons.Images.SetKeyName(43, "layer-shape-line.png");
            this.iglIcons.Images.SetKeyName(44, "status_online.png");
            this.iglIcons.Images.SetKeyName(45, "map-pin.png");
            this.iglIcons.Images.SetKeyName(46, "layers-ungroup.png");
            this.iglIcons.Images.SetKeyName(47, "block.png");
            this.iglIcons.Images.SetKeyName(48, "arrow-transition.png");
            this.iglIcons.Images.SetKeyName(49, "arrow-transition-180.png");
            this.iglIcons.Images.SetKeyName(50, "plug-connect.png");
            this.iglIcons.Images.SetKeyName(51, "plug-disconnect.png");
            this.iglIcons.Images.SetKeyName(52, "exclamation-button.png");
            this.iglIcons.Images.SetKeyName(53, "tick-button.png");
            this.iglIcons.Images.SetKeyName(54, "smiley48.ico");
            this.iglIcons.Images.SetKeyName(55, "cross-button.png");
            this.iglIcons.Images.SetKeyName(56, "arrow-curve-000-left.png");
            this.iglIcons.Images.SetKeyName(57, "arrow-curve-180-left.png");
            this.iglIcons.Images.SetKeyName(58, "arrow-retweet.png");
            this.iglIcons.Images.SetKeyName(59, "arrow-step-over.png");
            this.iglIcons.Images.SetKeyName(60, "home.png");
            this.iglIcons.Images.SetKeyName(61, "wrench.png");
            this.iglIcons.Images.SetKeyName(62, "check.png");
            //
            // tltpUpdateComplete
            //
            this.tltpUpdateComplete.IsBalloon = true;
            //
            // pnlWindows
            //
            this.pnlWindows.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlWindows.Location = new System.Drawing.Point(0, 30);
            this.pnlWindows.Name = "pnlWindows";
            this.pnlWindows.Size = new System.Drawing.Size(1008, 710);
            this.pnlWindows.TabIndex = 8;
            //
            // iglGameIcons
            //
            this.iglGameIcons.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("iglGameIcons.ImageStream")));
            this.iglGameIcons.TransparentColor = System.Drawing.Color.Transparent;
            this.iglGameIcons.Images.SetKeyName(0, "bfbc2.png");
            this.iglGameIcons.Images.SetKeyName(1, "moh.png");
            this.iglGameIcons.Images.SetKeyName(2, "bfbc2.bc2.png");
            this.iglGameIcons.Images.SetKeyName(3, "bfbc2.vietnam.png");
            //
            // tlsConnections
            //
            this.tlsConnections.CanOverflow = false;
            this.tlsConnections.GripMargin = new System.Windows.Forms.Padding(0);
            this.tlsConnections.GripStyle = System.Windows.Forms.ToolStripGripStyle.Hidden;
            this.tlsConnections.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.btnStartPage,
            this.btnShiftServerPrevious,
            this.btnShiftServerNext,
            this.cboServerList,
            this.toolsStripDropDownButton});
            this.tlsConnections.LayoutStyle = System.Windows.Forms.ToolStripLayoutStyle.Flow;
            this.tlsConnections.Location = new System.Drawing.Point(0, 0);
            this.tlsConnections.Name = "tlsConnections";
            this.tlsConnections.Padding = new System.Windows.Forms.Padding(1, 1, 1, 0);
            this.tlsConnections.Size = new System.Drawing.Size(1008, 30);
            this.tlsConnections.Stretch = true;
            this.tlsConnections.TabIndex = 4;
            this.tlsConnections.Text = "toolStrip1";
            this.tlsConnections.SizeChanged += new System.EventHandler(this.tlsConnections_SizeChanged);
            //
            // btnStartPage
            //
            this.btnStartPage.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.btnStartPage.Image = ((System.Drawing.Image)(resources.GetObject("btnStartPage.Image")));
            this.btnStartPage.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.btnStartPage.Name = "btnStartPage";
            this.btnStartPage.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.btnStartPage.Padding = new System.Windows.Forms.Padding(3);
            this.btnStartPage.Size = new System.Drawing.Size(26, 26);
            this.btnStartPage.Text = "Start Page";
            this.btnStartPage.Click += new System.EventHandler(this.btnStartPage_Click);
            //
            // btnShiftServerPrevious
            //
            this.btnShiftServerPrevious.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.btnShiftServerPrevious.Image = ((System.Drawing.Image)(resources.GetObject("btnShiftServerPrevious.Image")));
            this.btnShiftServerPrevious.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.btnShiftServerPrevious.Name = "btnShiftServerPrevious";
            this.btnShiftServerPrevious.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.btnShiftServerPrevious.Padding = new System.Windows.Forms.Padding(3);
            this.btnShiftServerPrevious.Size = new System.Drawing.Size(26, 26);
            this.btnShiftServerPrevious.Text = "Previous connection";
            this.btnShiftServerPrevious.Click += new System.EventHandler(this.btnShiftServerPrevious_Click);
            //
            // btnShiftServerNext
            //
            this.btnShiftServerNext.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.btnShiftServerNext.Image = ((System.Drawing.Image)(resources.GetObject("btnShiftServerNext.Image")));
            this.btnShiftServerNext.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.btnShiftServerNext.Name = "btnShiftServerNext";
            this.btnShiftServerNext.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.btnShiftServerNext.Padding = new System.Windows.Forms.Padding(3);
            this.btnShiftServerNext.Size = new System.Drawing.Size(26, 26);
            this.btnShiftServerNext.Text = "Next connection";
            this.btnShiftServerNext.Click += new System.EventHandler(this.btnShiftServerNext_Click);
            //
            // cboServerList
            //
            this.cboServerList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboServerList.DropDownWidth = 500;
            this.cboServerList.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cboServerList.Name = "cboServerList";
            this.cboServerList.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.cboServerList.Padding = new System.Windows.Forms.Padding(2);
            this.cboServerList.Size = new System.Drawing.Size(75, 27);
            this.cboServerList.SelectedIndexChanged += new System.EventHandler(this.cboServerList_SelectedIndexChanged);
            //
            // toolsStripDropDownButton
            //
            this.toolsStripDropDownButton.Alignment = System.Windows.Forms.ToolStripItemAlignment.Right;
            this.toolsStripDropDownButton.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.btnConnectDisconnect,
            this.chkAutomaticallyConnect,
            this.toolStripSeparator3,
            this.optionsToolStripMenuItem,
            this.manageAccountsToolStripMenuItem,
            this.toolStripSeparator1,
            this.checkForUpdatesToolStripMenuItem,
            this.changelogToolStripMenuItem,
            this.aboutToolStripMenuItem});
            this.toolsStripDropDownButton.Image = ((System.Drawing.Image)(resources.GetObject("toolsStripDropDownButton.Image")));
            this.toolsStripDropDownButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolsStripDropDownButton.Margin = new System.Windows.Forms.Padding(5, 1, 0, 2);
            this.toolsStripDropDownButton.Name = "toolsStripDropDownButton";
            this.toolsStripDropDownButton.Overflow = System.Windows.Forms.ToolStripItemOverflow.Never;
            this.toolsStripDropDownButton.Padding = new System.Windows.Forms.Padding(3);
            this.toolsStripDropDownButton.Size = new System.Drawing.Size(71, 26);
            this.toolsStripDropDownButton.Text = "Tools";
            //
            // btnConnectDisconnect
            //
            this.btnConnectDisconnect.Name = "btnConnectDisconnect";
            this.btnConnectDisconnect.Size = new System.Drawing.Size(196, 22);
            this.btnConnectDisconnect.Text = "Connect";
            this.btnConnectDisconnect.Click += new System.EventHandler(this.btnConnectDisconnect_Click);
            this.btnConnectDisconnect.MouseEnter += new System.EventHandler(this.btnConnectDisconnect_MouseEnter);
            this.btnConnectDisconnect.MouseLeave += new System.EventHandler(this.btnConnectDisconnect_MouseLeave);
            //
            // chkAutomaticallyConnect
            //
            this.chkAutomaticallyConnect.CheckOnClick = true;
            this.chkAutomaticallyConnect.Name = "chkAutomaticallyConnect";
            this.chkAutomaticallyConnect.Size = new System.Drawing.Size(196, 22);
            this.chkAutomaticallyConnect.Text = "Automatically Connect";
            this.chkAutomaticallyConnect.Click += new System.EventHandler(this.chkAutomaticallyConnect_CheckedChanged);
            //
            // toolStripSeparator3
            //
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(193, 6);
            //
            // optionsToolStripMenuItem
            //
            this.optionsToolStripMenuItem.Name = "optionsToolStripMenuItem";
            this.optionsToolStripMenuItem.Size = new System.Drawing.Size(196, 22);
            this.optionsToolStripMenuItem.Text = "Options..";
            this.optionsToolStripMenuItem.Click += new System.EventHandler(this.optionsToolStripMenuItem1_Click);
            //
            // manageAccountsToolStripMenuItem
            //
            this.manageAccountsToolStripMenuItem.Name = "manageAccountsToolStripMenuItem";
            this.manageAccountsToolStripMenuItem.Size = new System.Drawing.Size(196, 22);
            this.manageAccountsToolStripMenuItem.Text = "Manage Accounts";
            this.manageAccountsToolStripMenuItem.Click += new System.EventHandler(this.userManagerToolStripMenuItem_Click);
            //
            // toolStripSeparator1
            //
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(193, 6);
            //
            // checkForUpdatesToolStripMenuItem
            //
            this.checkForUpdatesToolStripMenuItem.Name = "checkForUpdatesToolStripMenuItem";
            this.checkForUpdatesToolStripMenuItem.Size = new System.Drawing.Size(196, 22);
            this.checkForUpdatesToolStripMenuItem.Text = "Check for Updates";
            this.checkForUpdatesToolStripMenuItem.Click += new System.EventHandler(this.checkForUpdatesToolStripMenuItem_Click);
            //
            // changelogToolStripMenuItem
            //
            this.changelogToolStripMenuItem.Name = "changelogToolStripMenuItem";
            this.changelogToolStripMenuItem.Size = new System.Drawing.Size(196, 22);
            this.changelogToolStripMenuItem.Text = "Changelog";
            this.changelogToolStripMenuItem.Click += new System.EventHandler(this.changelogToolStripMenuItem_Click);
            //
            // aboutToolStripMenuItem
            //
            this.aboutToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("aboutToolStripMenuItem.Image")));
            this.aboutToolStripMenuItem.Name = "aboutToolStripMenuItem";
            this.aboutToolStripMenuItem.Size = new System.Drawing.Size(196, 22);
            this.aboutToolStripMenuItem.Text = "About..";
            this.aboutToolStripMenuItem.Click += new System.EventHandler(this.aboutToolStripMenuItem_Click);
            //
            // frmMain
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1008, 762);
            this.Controls.Add(this.pnlWindows);
            this.Controls.Add(this.picLayerOffline);
            this.Controls.Add(this.tlsConnections);
            this.Controls.Add(this.picLayerOnline);
            this.Controls.Add(this.picPortCheckerClosed);
            this.Controls.Add(this.picPortCheckerOpen);
            this.Controls.Add(this.stsMain);
            this.Controls.Add(this.picPortCheckerUnknown);
            this.Controls.Add(this.picAjaxStyleSuccess);
            this.Controls.Add(this.picAjaxStyleLoading);
            this.Controls.Add(this.picAjaxStyleFail);
            this.DoubleBuffered = true;
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MinimumSize = new System.Drawing.Size(1024, 800);
            this.Name = "frmMain";
            this.Text = "Procon Frostbite";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.frmMain_FormClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.frmMain_FormClosed);
            this.Load += new System.EventHandler(this.frmMain_Load);
            this.ResizeEnd += new System.EventHandler(this.frmMain_ResizeEnd);
            this.Resize += new System.EventHandler(this.frmMain_Resize);
            this.stsMain.ResumeLayout(false);
            this.stsMain.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerOffline)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerOnline)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picPortCheckerClosed)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picPortCheckerOpen)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picPortCheckerUnknown)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAjaxStyleSuccess)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAjaxStyleFail)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAjaxStyleLoading)).EndInit();
            this.tlsConnections.ResumeLayout(false);
            this.tlsConnections.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.StatusStrip stsMain;
        public System.Windows.Forms.ImageList iglFlags;
        private System.Windows.Forms.NotifyIcon ntfIcon;
        public System.Windows.Forms.PictureBox picAjaxStyleLoading;
        public System.Windows.Forms.PictureBox picAjaxStyleFail;
        public System.Windows.Forms.PictureBox picAjaxStyleSuccess;
        public System.Windows.Forms.PictureBox picLayerOffline;
        public System.Windows.Forms.PictureBox picLayerOnline;
        public System.Windows.Forms.PictureBox picPortCheckerClosed;
        public System.Windows.Forms.PictureBox picPortCheckerOpen;
        public System.Windows.Forms.PictureBox picPortCheckerUnknown;
        public System.Windows.Forms.ImageList iglIcons;
        private System.Windows.Forms.ToolStripStatusLabel toolStripDownloading;
        private System.Windows.Forms.ToolStripProgressBar toolStripDownloadProgress;
        private System.Windows.Forms.ToolTip tltpUpdateComplete;
        private System.Windows.Forms.ToolStripStatusLabel toolStripRssFeed;
        private PRoCon.Controls.ControlsEx.ToolStripNF tlsConnections;
        private System.Windows.Forms.ToolStripComboBox cboServerList;
        private System.Windows.Forms.ToolStripButton btnShiftServerPrevious;
        private System.Windows.Forms.ToolStripButton btnShiftServerNext;
        private System.Windows.Forms.Panel pnlWindows;
        private System.Windows.Forms.ImageList iglGameIcons;
        private System.Windows.Forms.ToolStripButton btnStartPage;
        private System.Windows.Forms.ToolStripDropDownButton toolsStripDropDownButton;
        private System.Windows.Forms.ToolStripMenuItem optionsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem aboutToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem checkForUpdatesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem manageAccountsToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem changelogToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripMenuItem btnConnectDisconnect;
        private System.Windows.Forms.ToolStripMenuItem chkAutomaticallyConnect;
    }*/
}
