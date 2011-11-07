package procon.controls;

public class UscAccountsPanel {

    /*public partial class uscAccountsPanel : UserControl {

        private frmMain m_frmMain;
        private uscServerConnection m_uscConnectionPanel;
        private CLocalization m_clocLanguage;

        private PRoConApplication m_praApplication;
        private PRoConClient m_prcClient;

        //private CPrivileges m_spPrivileges;

        // This variable is only used by this panel to show an example of the Layer Name.
        private string m_strServerName;
        public string ServerName {
            set {
                this.m_strServerName = value;
                this.lblExampleLayerName.Text = this.txtLayerName.Text.Replace("%servername%", this.m_strServerName);
            }
        }

        public uscAccountsPanel() {
            InitializeComponent();

            //this.m_uscParentPanel = null;
            this.m_frmMain = null;
            this.m_clocLanguage = null;

            //this.m_spPrivileges = new CPrivileges();
            //this.m_spPrivileges.PrivilegesFlags = CPrivileges.FullPrivilegesFlags;

            this.uscPrivileges.OnUpdatePrivileges += new uscPrivilegesSelection.OnUpdatePrivilegesDelegate(uscPrivileges_OnUpdatePrivileges);
            this.uscPrivileges.OnCancelPrivileges += new uscPrivilegesSelection.OnCancelPrivilegesDelegate(uscPrivileges_OnCancelPrivileges);
        }

        private void uscAccountsPanel_Load(object sender, EventArgs e) {

            if (this.m_praApplication != null && this.m_prcClient != null) {
                foreach (Account accLoadedAccount in this.m_praApplication.AccountsList) {
                    this.AccountsList_AccountAdded(accLoadedAccount);

                    if (this.m_prcClient.Layer.AccountPrivileges.Contains(accLoadedAccount.Name) == true) {
                        this.uscAccountsPanel_AccountPrivilegesChanged(this.m_prcClient.Layer.AccountPrivileges[accLoadedAccount.Name]);
                    }
                }

                this.txtLayerName.Text = this.m_prcClient.Layer.LayerNameFormat;
                this.txtLayerBindingAddress.Text = this.m_prcClient.Layer.BindingAddress;
                this.txtLayerStartPort.Text = this.m_prcClient.Layer.ListeningPort.ToString();

                if (this.m_prcClient.Layer.IsLayerOnline == true) {
                    this.Layer_LayerOnline();
                }
            }
        }

        public void Initalize(frmMain frmMain, uscServerConnection uscConnectionPanel) {
            this.m_frmMain = frmMain;
            this.m_uscConnectionPanel = uscConnectionPanel;
            //this.m_uscParentPanel = uscParentPanel;

            this.picLayerForwardedTestStatus.Image = this.m_frmMain.picPortCheckerUnknown.Image; //  .iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_PORTCHECK_UNKNOWN];
            this.picLayerServerStatus.Image = this.m_frmMain.picLayerOffline.Image; // .iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_OFFLINE];

            this.pnlMainLayerServer.Dock = DockStyle.Fill;
            this.pnlStartPRoConLayer.Dock = DockStyle.Fill;
            this.pnlAccountPrivileges.Dock = DockStyle.Fill;

            this.lsvLayerAccounts.SmallImageList = this.m_frmMain.iglIcons;
        }

        public void SetConnection(PRoConApplication praApplication, PRoConClient prcClient) {
            if ((this.m_praApplication = praApplication) != null && (this.m_prcClient = prcClient) != null) {
                //this.m_prcClient.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcClient_ProconPrivileges);

                if (this.m_prcClient.Game != null) {
                    this.m_prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    this.m_prcClient.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcClient_GameTypeDiscovered);
                }
            }
        }

        void m_prcClient_GameTypeDiscovered(PRoConClient sender) {

            this.m_praApplication.AccountsList.AccountAdded += new PRoCon.Core.Accounts.AccountDictionary.AccountAlteredHandler(AccountsList_AccountAdded);
            this.m_praApplication.AccountsList.AccountRemoved += new PRoCon.Core.Accounts.AccountDictionary.AccountAlteredHandler(AccountsList_AccountRemoved);

            foreach (Account acAccount in this.m_praApplication.AccountsList) {
                acAccount.AccountPasswordChanged += new Account.AccountPasswordChangedHandler(acAccount_AccountPasswordChanged);

                if (this.m_prcClient.Layer.AccountPrivileges.Contains(acAccount.Name) == true) {
                    this.m_prcClient.Layer.AccountPrivileges[acAccount.Name].AccountPrivilegesChanged += new AccountPrivilege.AccountPrivilegesChangedHandler(uscAccountsPanel_AccountPrivilegesChanged);
                }
            }

            this.m_prcClient.Layer.LayerOnline += new PRoConLayer.LayerEmptyParameterHandler(Layer_LayerOnline);
            this.m_prcClient.Layer.LayerOffline += new PRoConLayer.LayerEmptyParameterHandler(Layer_LayerOffline);
            this.m_prcClient.Layer.LayerSocketError += new PRoConLayer.LayerSocketErrorHandler(Layer_LayerSocketError);

            this.m_prcClient.Layer.ClientConnected += new PRoConLayer.LayerAccountHandler(Layer_ClientConnected);
        }

        void Layer_ClientConnected(PRoConLayerClient client) {
            client.Login += new PRoConLayerClient.LayerClientHandler(client_LayerClientLogin);
            client.Logout += new PRoConLayerClient.LayerClientHandler(client_LayerClientLogout);
        }

        //public void m_prcClient_ProconPrivileges(CPrivileges spPrivs) {
        //    this.m_spPrivileges = spPrivs;
        //}

        public void SetLocalization(CLocalization clocLanguage) {

            if ((this.m_clocLanguage = clocLanguage) != null) {

                //this.tbpPRoConLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.Title", null);

                this.lblLayerServerSetupTitle.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerServerSetupTitle", null);
                this.lnkStartStopLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkStartStopLayer.Start", null);
                //this.lnkStartStopLayer.LinkArea = new LinkArea(0, this.lnkStartStopLayer.Text.Length);
                this.lblLayerServerStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerServerStatus.Offline", null);

                this.lnkLayerForwardedTest.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkLayerForwardedTest", null);
                //this.lnkLayerForwardedTest.LinkArea = new LinkArea(0, this.lnkLayerForwardedTest.Text.Length);
                this.lblLayerForwardedTestStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerForwardedTestStatus.Unknown", null);


                this.lnkWhatIsPRoConLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkWhatIsPRoConLayer", null);
                //this.lnkWhatIsPRoConLayer.LinkArea = new LinkArea(0, this.lnkWhatIsPRoConLayer.Text.Length);

                this.lblLayerAssignAccountPrivilegesTitle.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerAssignAccountPrivilegesTitle", null);

                this.colAccounts.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.colAccounts", null);
                this.colPrivileges.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.colPrivileges", null);
                this.colRConAccess.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.colRConAccess", null);
                this.colIPPort.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.colIPPort", null);

                this.lnkManageAccounts.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkManageAccounts", null);
                //this.lnkManageAccounts.LinkArea = new LinkArea(0, this.lnkManageAccounts.Text.Length);

                this.lblLayerStartTitle.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerStartTitle", null);
                this.lblLayerStartPort.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerStartPort", null);
                this.lblLayerBindingIP.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerBindingIP", null);
                this.lblBindingExplanation.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblBindingExplanation", null);
                this.lblLayerName.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerName", null);
                this.btnInsertName.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.btnInsertName", null);
                this.lblExampleLayerName.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblExampleLayerName", new string[] { this.txtLayerName.Text.Replace("%servername%", this.m_strServerName) });
                this.btnLayerStart.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.btnLayerStart", null);
                this.btnCancelLayerStart.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.btnCancelLayerStart", null);

                this.uscPrivileges.SetLocalization(this.m_clocLanguage);

                this.RefreshLayerPrivilegesPanel();

            }
        }

        public delegate void ManageAccountsRequestDelegate(object sender, EventArgs e);
        public event ManageAccountsRequestDelegate ManageAccountsRequest;

        private UInt16 m_ui16LayerListenerPort = 27260;

        private TcpListener m_tclLayerListener = null;

        private bool m_blEditingPrivileges = false;
        private void ShowLayerPanel(Panel pnlShow) {
            this.pnlMainLayerServer.Hide();
            this.pnlStartPRoConLayer.Hide();
            this.pnlAccountPrivileges.Hide();

            this.m_blEditingPrivileges = false;

            if (pnlShow == this.pnlMainLayerServer) {
                this.lsvLayerAccounts.SelectedItems.Clear();
            }
            else if (pnlShow == this.pnlAccountPrivileges) {
                this.m_blEditingPrivileges = true;

                // Should be but still..
                if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                    this.uscPrivileges.AccountName = this.lsvLayerAccounts.SelectedItems[0].Text;
                }

                if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                    CPrivileges spPrivs = (CPrivileges)this.lsvLayerAccounts.SelectedItems[0].SubItems[1].Tag;

                    this.uscPrivileges.Privileges = spPrivs;
                }
                else {
                    this.uscPrivileges.Privileges = new CPrivileges();
                }
            }

            pnlShow.Show();
        }

        private void RefreshLayerPrivilegesPanel() {
            foreach (ListViewItem lviItem in this.lsvLayerAccounts.Items) {
                if (lviItem.SubItems[1].Tag != null && this.m_clocLanguage != null && this.m_prcClient != null) {

                    if (this.m_prcClient.Layer.AccountPrivileges.Contains(lviItem.Text) == true) {
                        CPrivileges spDetails = this.m_prcClient.Layer.AccountPrivileges[lviItem.Text].Privileges;

                        if (spDetails.HasNoRconAccess == true) {
                            lviItem.SubItems["rconaccess"].Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.Privileges.None", null);
                        }
                        else if (spDetails.HasLimitedRconAccess == true) {
                            lviItem.SubItems["rconaccess"].Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.Privileges.Limited", null);
                        }
                        else {
                            lviItem.SubItems["rconaccess"].Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.Privileges.Full", null);
                        }

                        if (spDetails.HasNoLocalAccess == true) {
                            lviItem.SubItems["localaccess"].Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.Privileges.None", null);
                        }
                        else if (spDetails.HasLimitedLocalAccess == true) {
                            lviItem.SubItems["localaccess"].Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.Privileges.Limited", null);
                        }
                        else {
                            lviItem.SubItems["localaccess"].Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lstLayerAccounts.Privileges.Full", null);
                        }
                    }
                }
            }

            if (this.m_blEditingPrivileges == true) {
                this.ShowLayerPanel(this.pnlAccountPrivileges);
            }

            foreach (ColumnHeader ch in this.lsvLayerAccounts.Columns) {
                ch.Width = -2;
            }
        }


        void uscAccountsPanel_AccountPrivilegesChanged(AccountPrivilege item) {

            if (this.lsvLayerAccounts.Items.ContainsKey(item.Owner.Name) == true) {
                ListViewItem lviAccount = this.lsvLayerAccounts.Items[item.Owner.Name];

                lviAccount.SubItems["rconaccess"].Tag = item.Privileges;

                this.RefreshLayerPrivilegesPanel();
            }
        }

        void acAccount_AccountPasswordChanged(Account item) {

            if (this.lsvLayerAccounts.Items.ContainsKey(item.Name) == true) {
                this.lsvLayerAccounts.Items[item.Name].Tag = item.Password;
            }
        }

        void AccountsList_AccountRemoved(Account item) {
            item.AccountPasswordChanged -= new Account.AccountPasswordChangedHandler(acAccount_AccountPasswordChanged);

            if (this.lsvLayerAccounts.Items.ContainsKey(item.Name) == true) {

                //foreach (KeyValuePair<string, CPRoConLayerClient> kvpConnection in this.m_dicLayerClients) {
                //    kvpConnection.Value.OnAccountDeleted(e.AccountName);
                //}

                this.lsvLayerAccounts.Items.Remove(this.lsvLayerAccounts.Items[item.Name]);
            }
        }

        void AccountsList_AccountAdded(Account item) {
            item.AccountPasswordChanged += new Account.AccountPasswordChangedHandler(acAccount_AccountPasswordChanged);

            if (this.lsvLayerAccounts.Items.ContainsKey(item.Name) == false) {

                ListViewItem lviNewAccount = new ListViewItem(item.Name);
                lviNewAccount.Name = item.Name;
                lviNewAccount.Tag = item.Password;
                lviNewAccount.ImageKey = "status_offline.png";

                ListViewItem.ListViewSubItem lsviNewSubitem = new ListViewItem.ListViewSubItem();
                //lsviNewSubitem.Text = "none";
                lsviNewSubitem.Name = "rconaccess";
                lsviNewSubitem.Tag = new CPrivileges();
                lviNewAccount.SubItems.Add(lsviNewSubitem);

                lsviNewSubitem = new ListViewItem.ListViewSubItem();
                //lsviNewSubitem.Text = "none";
                lsviNewSubitem.Name = "localaccess";
                lviNewAccount.SubItems.Add(lsviNewSubitem);

                lsviNewSubitem = new ListViewItem.ListViewSubItem();
                lsviNewSubitem.Text = String.Empty;
                lsviNewSubitem.Name = "ip";
                lviNewAccount.SubItems.Add(lsviNewSubitem);

                this.lsvLayerAccounts.Items.Add(lviNewAccount);

                //foreach (KeyValuePair<string, CPRoConLayerClient> kvpConnection in this.m_dicLayerClients) {
                //    kvpConnection.Value.OnAccountCreated(e.AccountName);
                //}

                this.RefreshLayerPrivilegesPanel();
            }
        }

        //public event DispatchEventDelegate LayerServerManuallyStopped;
        private void lnkStartStopLayer_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            if (this.m_prcClient.Layer.IsLayerOnline == false) {
                this.ShowLayerPanel(this.pnlStartPRoConLayer);
            }
            else {
                this.m_prcClient.Layer.LayerEnabled = false;
                //this.m_blLayerEnabled = false;

                this.m_prcClient.Layer.ShutdownLayerListener();

                //if (this.LayerServerManuallyStopped != null) {
                //    this.LayerServerManuallyStopped();
                //}
            }
        }

        //public event DispatchEventDelegate LayerServerManuallyStarted;
        private void btnLayerStart_Click(object sender, EventArgs e) {
            this.ShowLayerPanel(this.pnlMainLayerServer);

            this.m_prcClient.Layer.LayerEnabled = true;
            this.m_prcClient.Layer.BindingAddress = this.txtLayerBindingAddress.Text;
            this.m_prcClient.Layer.ListeningPort = Convert.ToUInt16(this.txtLayerStartPort.Text);
            this.m_prcClient.Layer.LayerNameFormat = this.txtLayerName.Text;
            //this.m_ui16LayerListenerPort = Convert.ToUInt16(this.txtLayerStartPort.Text);
            this.m_prcClient.Layer.StartLayerListener();

            //if (this.LayerServerManuallyStarted != null) {
            //    this.LayerServerManuallyStarted();
            //}
        }

        private void btnCancelLayerStart_Click(object sender, EventArgs e) {
            this.ShowLayerPanel(this.pnlMainLayerServer);
        }

        private void lstLayerAccounts_SelectedIndexChanged(object sender, EventArgs e) {

            if (this.lsvLayerAccounts.SelectedItems.Count > 0) {

                this.ShowLayerPanel(this.pnlAccountPrivileges);
            }
        }

        void uscPrivileges_OnCancelPrivileges() {
            this.ShowLayerPanel(this.pnlMainLayerServer);
        }

        void uscPrivileges_OnUpdatePrivileges(string strAccountName, CPrivileges spUpdatedPrivs) {
            //this.SetLayerAccountPrivileges(strAccountName, spUpdatedPrivs);

            if (this.m_prcClient.Layer.AccountPrivileges.Contains(strAccountName) == true) {
                this.m_prcClient.Layer.AccountPrivileges[strAccountName].SetPrivileges(spUpdatedPrivs);
            }

            this.ShowLayerPanel(this.pnlMainLayerServer);

            this.RefreshLayerPrivilegesPanel();
        }

        private void lnkManageAccounts_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.ManageAccountsRequest(this, new EventArgs());
        }

        private void lnkLayerForwardedTest_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.PortCheck("http://www.phogue.net/procon/testport.php?port=" + this.txtLayerStartPort.Text);
        }

        #region Layer Events and Helper Methods

        public delegate void UpdatePortCheckerFeedbackDelegate(string strCheckerFeedback);
        private void OnPortCheckerFeedback(string strCheckerFeedback) {
            this.Invoke(new UpdatePortCheckerFeedbackDelegate(OnPortCheckerFeedback_Callback), new object[] { strCheckerFeedback });
        }
        private void OnPortCheckerFeedback_Callback(string strCheckerFeedback) {

            // Do not environment this \n.  It's from the php script and will always be just \n
            string[] a_strResponses = strCheckerFeedback.Split('\n');

            if (a_strResponses.Length >= 1) {
                if (a_strResponses[0].CompareTo("open") == 0) {
                    //this.picLayerForwardedTestStatus.Image = this.m_frmParent.iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_PORTCHECK_OPEN];
                    this.picLayerForwardedTestStatus.Image = this.m_frmMain.picPortCheckerOpen.Image;
                    if (a_strResponses.Length >= 2) {
                        this.lblLayerForwardedTestStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerForwardedTestStatus.Open", new string[] { this.m_prcClient.Layer.ListeningPort.ToString(), a_strResponses[1] });
                    }
                    else {
                        this.lblLayerForwardedTestStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerForwardedTestStatus.Open", new string[] { this.m_prcClient.Layer.ListeningPort.ToString(), String.Empty });
                    }
                    this.lblLayerForwardedTestStatus.ForeColor = Color.ForestGreen;
                    //this.tmrPortCheckTester.Enabled = false;
                    this.lnkLayerForwardedTest.Enabled = true;
                }
                else if (a_strResponses[0].CompareTo("closed") == 0 || strCheckerFeedback.CompareTo("error") == 0 || a_strResponses[0].CompareTo("denied") == 0) {
                    this.picLayerForwardedTestStatus.Image = this.m_frmMain.picPortCheckerClosed.Image;
                    //this.picLayerForwardedTestStatus.Image = this.m_frmParent.iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_PORTCHECK_CLOSED];
                    this.lblLayerForwardedTestStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerForwardedTestStatus.Closed", new string[] { this.m_prcClient.Layer.ListeningPort.ToString() });
                    this.lblLayerForwardedTestStatus.ForeColor = Color.Maroon;
                    //this.tmrPortCheckTester.Enabled = false;
                    this.lnkLayerForwardedTest.Enabled = true;
                }
                else if (a_strResponses[0].CompareTo("checking") == 0) {
                    this.picLayerForwardedTestStatus.Image = this.m_frmMain.picAjaxStyleLoading.Image;
                    //this.tmrPortCheckTester.Enabled = true;
                    this.lnkLayerForwardedTest.Enabled = false;
                    this.lblLayerForwardedTestStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerForwardedTestStatus.Running", new string[] { this.m_prcClient.Layer.ListeningPort.ToString() });
                    this.lblLayerForwardedTestStatus.ForeColor = Color.Black;
                }
            }
        }

        private void Layer_LayerOnline() {
            //this.picLayerServerStatus.Image = this.m_frmParent.iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_ONLINE];
            this.picLayerServerStatus.Image = this.m_frmMain.picLayerOnline.Image;

            this.lblLayerServerStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerServerStatus.Online", new string[] { this.m_prcClient.Layer.ListeningPort.ToString() });
            this.lblLayerServerStatus.ForeColor = Color.ForestGreen;

            this.lnkStartStopLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkStartStopLayer.Stop", null);
            //this.lnkStartStopLayer.LinkArea = new LinkArea(0, this.lnkStartStopLayer.Text.Length);

            this.pnlLayerServerTester.Visible = true;
            //this.picLayerForwardedTestStatus.Image = this.m_frmParent.iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_PORTCHECK_UNKNOWN];
            this.picLayerForwardedTestStatus.Image = this.m_frmMain.picPortCheckerUnknown.Image;
            this.lblLayerForwardedTestStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerForwardedTestStatus.Unknown", null);

        }

        private void Layer_LayerOffline() {
            this.picLayerServerStatus.Image = this.m_frmMain.picLayerOffline.Image;//this.m_frmParent.iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_OFFLINE];

            this.lblLayerServerStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerServerStatus.Offline", null);
            this.lblLayerServerStatus.ForeColor = Color.Maroon;

            this.lnkStartStopLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkStartStopLayer.Start", null);
            //this.lnkStartStopLayer.LinkArea = new LinkArea(0, this.lnkStartStopLayer.Text.Length);

            this.pnlLayerServerTester.Visible = false;
        }

        private void Layer_LayerSocketError(SocketException se) {
            //this.ShutdownLayerListener();

            this.picLayerServerStatus.Image = this.m_frmMain.picLayerOffline.Image;
            //this.picLayerServerStatus.Image = this.m_frmParent.iglPRoConLayerIcons.Images[uscServerConnection.INT_ICON_LAYERSERVER_OFFLINE];

            this.lblLayerServerStatus.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lblLayerServerStatus.Error", new string[] { se.Message });
            this.lblLayerServerStatus.ForeColor = Color.Maroon;

            this.lnkStartStopLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.lnkStartStopLayer.Start", null);
            //this.lnkStartStopLayer.LinkArea = new LinkArea(0, this.lnkStartStopLayer.Text.Length);

            this.pnlLayerServerTester.Visible = false;
        }

        void client_LayerClientLogin(PRoConLayerClient sender) {

            if (this.lsvLayerAccounts.Items.ContainsKey(sender.Username) == true) {
                // TO DO: Change Icon

                // TODO: Fix
                //this.m_uscConnectionPanel.ThrowEvent(this, uscEventsPanel.CapturableEvents.AccountLogin, new string[] { strUsername, sender.ClientIPPort });

                if (this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag == null) {
                    this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag = new List<string>() { sender.IPPort };
                }
                else {
                    ((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Add(sender.IPPort);
                }

                this.lsvLayerAccounts.Items[sender.Username].ImageKey = "status_online.png";

                this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text = String.Format("({0} CMD/EVNT) ", Math.Floor(((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Count / 2.0));

                for (int i = 0; i < ((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Count; i++) {
                    if (i > 0) {
                        this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text += ", ";
                    }

                    this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text += ((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag)[i];
                }

                //this.lsvLayerAccounts.Items[strUsername].SubItems["ip"].Text = sender.ClientIPPort;
            }
        }

        private void client_LayerClientLogout(PRoConLayerClient sender) {

            if (this.lsvLayerAccounts.Items.ContainsKey(sender.Username) == true) {
                // TO DO: Change Icon
                // TODO: Fix
                //this.m_uscConnectionPanel.ThrowEvent(this, uscEventsPanel.CapturableEvents.AccountLogout, new string[] { strUsername });

                if (this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag != null) {

                    ((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Remove(sender.IPPort);

                    if (Math.Floor(((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Count / 2.0) > 0) {
                        this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text = String.Format("({0} CMD/EVNT) ", Math.Floor(((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Count / 2.0));
                    }
                    else {
                        this.lsvLayerAccounts.Items[sender.Username].ImageKey = "status_offline.png";
                        this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text = String.Empty;
                    }

                    for (int i = 0; i < ((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag).Count; i++) {
                        if (i > 0) {
                            this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text += ", ";
                        }

                        this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text += ((List<string>)this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Tag)[i];
                    }
                }
                else {
                    this.lsvLayerAccounts.Items[sender.Username].SubItems["ip"].Text = String.Empty;
                }
            }
        }

        #endregion

        #region Port forward checker

        private struct SAsyncRequestState {
            public HttpWebRequest WebRequest;
            public uscAccountsPanel uscAccounts;
        }

        private void PortCheckTimeoutCallback(object objState, bool blTimedOut) {
            if (blTimedOut) {
                HttpWebRequest request = ((SAsyncRequestState)objState).WebRequest;
                if (request != null) {
                    //    Console.WriteLine("[" + DateTime.Now.ToString("HH:mm:ss ff") + "] PostCallback -> http://phogue.net Timedout.");
                    request.Abort();
                    ((SAsyncRequestState)objState).uscAccounts.OnPortCheckerFeedback("error");
                }
            }
        }

        private static void PortCheckResponseCallback(IAsyncResult asynchronousResult) {
            try {

                WebResponse wbResponse = ((SAsyncRequestState)asynchronousResult.AsyncState).WebRequest.EndGetResponse(asynchronousResult);

                StreamReader stmReadCheck = new StreamReader(wbResponse.GetResponseStream(), Encoding.UTF8);

                string strResponse = stmReadCheck.ReadToEnd();

                ((SAsyncRequestState)asynchronousResult.AsyncState).uscAccounts.OnPortCheckerFeedback(strResponse);

                stmReadCheck.Close();
                wbResponse.Close();
            }
            catch (WebException) {
                ((SAsyncRequestState)asynchronousResult.AsyncState).uscAccounts.OnPortCheckerFeedback("error");
            }
        }

        private static void PortCheckPostCallback(IAsyncResult asynchronousResult) {

            try {

                HttpWebRequest request = ((SAsyncRequestState)asynchronousResult.AsyncState).WebRequest;

                // End the operation.
                Stream postStream = request.EndGetRequestStream(asynchronousResult);

                // Convert the string into a byte array.
                //byte[] byteArray = Encoding.UTF8.GetBytes(((SAsyncRequestState)asynchronousResult.AsyncState).strPostData);
                // Write to the request stream.
                //postStream.Write(byteArray, 0, ((SAsyncRequestState)asynchronousResult.AsyncState).strPostData.Length);
                postStream.Close();

                request.BeginGetResponse(new AsyncCallback(PortCheckResponseCallback), asynchronousResult.AsyncState);
            }
            catch (WebException) {
                ((SAsyncRequestState)asynchronousResult.AsyncState).uscAccounts.OnPortCheckerFeedback("error");
            }
        }

        public void PortCheck(string strUrl) {

            this.OnPortCheckerFeedback("checking");

            // Open a connection
            HttpWebRequest WebRequestObject = (HttpWebRequest)HttpWebRequest.Create(strUrl);
            //SAsyncRequestState arsPostData = new SAsyncRequestState();

            // You can also specify additional header values like
            // the user agent or the referer:
            WebRequestObject.UserAgent = ".NET Framework/2.0";
            WebRequestObject.Referer = "http://prconserver.phogue.net";
            //WebRequestObject.KeepAlive = false;

            // Set values for the request back
            WebRequestObject.Method = "POST";
            WebRequestObject.ContentType = "application/x-www-form-urlencoded";
            WebRequestObject.Proxy = null;

            //arsPostData.WebRequest = WebRequestObject;

            SAsyncRequestState sarState = new SAsyncRequestState();
            sarState.uscAccounts = this;
            sarState.WebRequest = WebRequestObject;

            IAsyncResult iarResult = WebRequestObject.BeginGetRequestStream(new AsyncCallback(PortCheckPostCallback), sarState);

            // Set a timeout on our Request for a stream.  Timeout in 20 seconds.
            ThreadPool.RegisterWaitForSingleObject(iarResult.AsyncWaitHandle, new WaitOrTimerCallback(PortCheckTimeoutCallback), sarState, 15000, true);
        }

        #endregion

        private void txtLayerName_TextChanged(object sender, EventArgs e) {
            this.lblExampleLayerName.Text = this.txtLayerName.Text.Replace("%servername%", this.m_strServerName);
        }

        private void btnInsertName_Click(object sender, EventArgs e) {

            int iInsertPosition = this.txtLayerName.SelectionStart;

            this.txtLayerName.Text = this.txtLayerName.Text.Remove(iInsertPosition, this.txtLayerName.SelectionLength);
            this.txtLayerName.Text = this.txtLayerName.Text.Insert(iInsertPosition, "%servername%");
        }

        private void lnkWhatIsPRoConLayer_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            System.Diagnostics.Process.Start("http://phogue.net/procon/whatis.php");
        }

        private void txtLayerStartPort_KeyPress(object sender, KeyPressEventArgs e) {
            e.Handled = (!char.IsDigit(e.KeyChar) && e.KeyChar != '\b');
        }

    }*/

    /*partial class uscAccountsPanel {
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

        #region Component Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.pnlAccountPrivileges = new System.Windows.Forms.Panel();
            this.uscPrivileges = new PRoCon.uscPrivilegesSelection();
            this.pnlStartPRoConLayer = new System.Windows.Forms.Panel();
            this.btnInsertName = new System.Windows.Forms.Button();
            this.lblBindingExplanation = new System.Windows.Forms.Label();
            this.txtLayerBindingAddress = new System.Windows.Forms.TextBox();
            this.lblLayerBindingIP = new System.Windows.Forms.Label();
            this.lblExampleLayerName = new System.Windows.Forms.Label();
            this.txtLayerName = new System.Windows.Forms.TextBox();
            this.lblLayerName = new System.Windows.Forms.Label();
            this.btnCancelLayerStart = new System.Windows.Forms.Button();
            this.btnLayerStart = new System.Windows.Forms.Button();
            this.lblLayerStartPort = new System.Windows.Forms.Label();
            this.txtLayerStartPort = new System.Windows.Forms.TextBox();
            this.lblLayerStartTitle = new System.Windows.Forms.Label();
            this.panel4 = new System.Windows.Forms.Panel();
            this.panel5 = new System.Windows.Forms.Panel();
            this.pnlMainLayerServer = new System.Windows.Forms.Panel();
            this.spltLayerSetupPrivs = new System.Windows.Forms.SplitContainer();
            this.pnlLayerServerTester = new System.Windows.Forms.Panel();
            this.picLayerForwardedTestStatus = new System.Windows.Forms.PictureBox();
            this.lblLayerForwardedTestStatus = new System.Windows.Forms.Label();
            this.lnkLayerForwardedTest = new System.Windows.Forms.LinkLabel();
            this.lblLayerServerSetupTitle = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.lnkWhatIsPRoConLayer = new System.Windows.Forms.LinkLabel();
            this.lnkStartStopLayer = new System.Windows.Forms.LinkLabel();
            this.picLayerServerStatus = new System.Windows.Forms.PictureBox();
            this.lblLayerServerStatus = new System.Windows.Forms.Label();
            this.lnkManageAccounts = new System.Windows.Forms.LinkLabel();
            this.lsvLayerAccounts = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colAccounts = new System.Windows.Forms.ColumnHeader();
            this.colRConAccess = new System.Windows.Forms.ColumnHeader();
            this.colPrivileges = new System.Windows.Forms.ColumnHeader();
            this.colIPPort = new System.Windows.Forms.ColumnHeader();
            this.lblLayerAssignAccountPrivilegesTitle = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.pnlAccountPrivileges.SuspendLayout();
            this.pnlStartPRoConLayer.SuspendLayout();
            this.pnlMainLayerServer.SuspendLayout();
            this.spltLayerSetupPrivs.Panel1.SuspendLayout();
            this.spltLayerSetupPrivs.Panel2.SuspendLayout();
            this.spltLayerSetupPrivs.SuspendLayout();
            this.pnlLayerServerTester.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerForwardedTestStatus)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerServerStatus)).BeginInit();
            this.SuspendLayout();
            //
            // pnlAccountPrivileges
            //
            this.pnlAccountPrivileges.Controls.Add(this.uscPrivileges);
            this.pnlAccountPrivileges.Location = new System.Drawing.Point(47, 526);
            this.pnlAccountPrivileges.Name = "pnlAccountPrivileges";
            this.pnlAccountPrivileges.Size = new System.Drawing.Size(567, 517);
            this.pnlAccountPrivileges.TabIndex = 30;
            this.pnlAccountPrivileges.Visible = false;
            //
            // uscPrivileges
            //
            this.uscPrivileges.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscPrivileges.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscPrivileges.Location = new System.Drawing.Point(0, 0);
            this.uscPrivileges.Name = "uscPrivileges";
            this.uscPrivileges.Size = new System.Drawing.Size(567, 517);
            this.uscPrivileges.TabIndex = 0;
            //
            // pnlStartPRoConLayer
            //
            this.pnlStartPRoConLayer.Controls.Add(this.btnInsertName);
            this.pnlStartPRoConLayer.Controls.Add(this.lblBindingExplanation);
            this.pnlStartPRoConLayer.Controls.Add(this.txtLayerBindingAddress);
            this.pnlStartPRoConLayer.Controls.Add(this.lblLayerBindingIP);
            this.pnlStartPRoConLayer.Controls.Add(this.lblExampleLayerName);
            this.pnlStartPRoConLayer.Controls.Add(this.txtLayerName);
            this.pnlStartPRoConLayer.Controls.Add(this.lblLayerName);
            this.pnlStartPRoConLayer.Controls.Add(this.btnCancelLayerStart);
            this.pnlStartPRoConLayer.Controls.Add(this.btnLayerStart);
            this.pnlStartPRoConLayer.Controls.Add(this.lblLayerStartPort);
            this.pnlStartPRoConLayer.Controls.Add(this.txtLayerStartPort);
            this.pnlStartPRoConLayer.Controls.Add(this.lblLayerStartTitle);
            this.pnlStartPRoConLayer.Controls.Add(this.panel4);
            this.pnlStartPRoConLayer.Controls.Add(this.panel5);
            this.pnlStartPRoConLayer.Location = new System.Drawing.Point(630, 39);
            this.pnlStartPRoConLayer.Name = "pnlStartPRoConLayer";
            this.pnlStartPRoConLayer.Size = new System.Drawing.Size(567, 433);
            this.pnlStartPRoConLayer.TabIndex = 29;
            this.pnlStartPRoConLayer.Visible = false;
            //
            // btnInsertName
            //
            this.btnInsertName.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.btnInsertName.Location = new System.Drawing.Point(416, 188);
            this.btnInsertName.Name = "btnInsertName";
            this.btnInsertName.Size = new System.Drawing.Size(119, 23);
            this.btnInsertName.TabIndex = 33;
            this.btnInsertName.Text = "Insert Name";
            this.btnInsertName.UseVisualStyleBackColor = true;
            this.btnInsertName.Click += new System.EventHandler(this.btnInsertName_Click);
            //
            // lblBindingExplanation
            //
            this.lblBindingExplanation.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblBindingExplanation.AutoSize = true;
            this.lblBindingExplanation.Location = new System.Drawing.Point(221, 75);
            this.lblBindingExplanation.Name = "lblBindingExplanation";
            this.lblBindingExplanation.Size = new System.Drawing.Size(211, 15);
            this.lblBindingExplanation.TabIndex = 32;
            this.lblBindingExplanation.Text = "Default \"0.0.0.0\" to bind to any address";
            //
            // txtLayerBindingAddress
            //
            this.txtLayerBindingAddress.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txtLayerBindingAddress.Location = new System.Drawing.Point(78, 72);
            this.txtLayerBindingAddress.Name = "txtLayerBindingAddress";
            this.txtLayerBindingAddress.Size = new System.Drawing.Size(127, 23);
            this.txtLayerBindingAddress.TabIndex = 31;
            this.txtLayerBindingAddress.Text = "0.0.0.0";
            //
            // lblLayerBindingIP
            //
            this.lblLayerBindingIP.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerBindingIP.AutoSize = true;
            this.lblLayerBindingIP.Location = new System.Drawing.Point(75, 51);
            this.lblLayerBindingIP.Name = "lblLayerBindingIP";
            this.lblLayerBindingIP.Size = new System.Drawing.Size(91, 15);
            this.lblLayerBindingIP.TabIndex = 30;
            this.lblLayerBindingIP.Text = "Binding address";
            //
            // lblExampleLayerName
            //
            this.lblExampleLayerName.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblExampleLayerName.AutoSize = true;
            this.lblExampleLayerName.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblExampleLayerName.Location = new System.Drawing.Point(95, 214);
            this.lblExampleLayerName.Name = "lblExampleLayerName";
            this.lblExampleLayerName.Size = new System.Drawing.Size(111, 15);
            this.lblExampleLayerName.TabIndex = 29;
            this.lblExampleLayerName.Text = "Example: PRoCon[]";
            //
            // txtLayerName
            //
            this.txtLayerName.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txtLayerName.Location = new System.Drawing.Point(78, 188);
            this.txtLayerName.Name = "txtLayerName";
            this.txtLayerName.Size = new System.Drawing.Size(332, 23);
            this.txtLayerName.TabIndex = 28;
            this.txtLayerName.Text = "PRoCon[%servername%]";
            this.txtLayerName.TextChanged += new System.EventHandler(this.txtLayerName_TextChanged);
            //
            // lblLayerName
            //
            this.lblLayerName.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerName.AutoSize = true;
            this.lblLayerName.Location = new System.Drawing.Point(75, 170);
            this.lblLayerName.Name = "lblLayerName";
            this.lblLayerName.Size = new System.Drawing.Size(68, 15);
            this.lblLayerName.TabIndex = 27;
            this.lblLayerName.Text = "Layer name";
            //
            // btnCancelLayerStart
            //
            this.btnCancelLayerStart.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.btnCancelLayerStart.Location = new System.Drawing.Point(448, 278);
            this.btnCancelLayerStart.Name = "btnCancelLayerStart";
            this.btnCancelLayerStart.Size = new System.Drawing.Size(87, 23);
            this.btnCancelLayerStart.TabIndex = 7;
            this.btnCancelLayerStart.Text = "Cancel";
            this.btnCancelLayerStart.UseVisualStyleBackColor = true;
            this.btnCancelLayerStart.Click += new System.EventHandler(this.btnCancelLayerStart_Click);
            //
            // btnLayerStart
            //
            this.btnLayerStart.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.btnLayerStart.Location = new System.Drawing.Point(297, 278);
            this.btnLayerStart.Name = "btnLayerStart";
            this.btnLayerStart.Size = new System.Drawing.Size(143, 23);
            this.btnLayerStart.TabIndex = 6;
            this.btnLayerStart.Text = "Start Server";
            this.btnLayerStart.UseVisualStyleBackColor = true;
            this.btnLayerStart.Click += new System.EventHandler(this.btnLayerStart_Click);
            //
            // lblLayerStartPort
            //
            this.lblLayerStartPort.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerStartPort.AutoSize = true;
            this.lblLayerStartPort.Location = new System.Drawing.Point(75, 111);
            this.lblLayerStartPort.Name = "lblLayerStartPort";
            this.lblLayerStartPort.Size = new System.Drawing.Size(80, 15);
            this.lblLayerStartPort.TabIndex = 26;
            this.lblLayerStartPort.Text = "Listening port";
            //
            // txtLayerStartPort
            //
            this.txtLayerStartPort.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txtLayerStartPort.Location = new System.Drawing.Point(78, 132);
            this.txtLayerStartPort.Name = "txtLayerStartPort";
            this.txtLayerStartPort.Size = new System.Drawing.Size(66, 23);
            this.txtLayerStartPort.TabIndex = 5;
            this.txtLayerStartPort.Text = "27260";
            this.txtLayerStartPort.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtLayerStartPort_KeyPress);
            //
            // lblLayerStartTitle
            //
            this.lblLayerStartTitle.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerStartTitle.AutoSize = true;
            this.lblLayerStartTitle.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblLayerStartTitle.Location = new System.Drawing.Point(37, 20);
            this.lblLayerStartTitle.Name = "lblLayerStartTitle";
            this.lblLayerStartTitle.Size = new System.Drawing.Size(150, 15);
            this.lblLayerStartTitle.TabIndex = 1;
            this.lblLayerStartTitle.Text = "Start PRoCon layer server";
            //
            // panel4
            //
            this.panel4.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel4.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel4.Location = new System.Drawing.Point(41, 29);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(495, 1);
            this.panel4.TabIndex = 16;
            //
            // panel5
            //
            this.panel5.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel5.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel5.Location = new System.Drawing.Point(41, 269);
            this.panel5.Name = "panel5";
            this.panel5.Size = new System.Drawing.Size(495, 1);
            this.panel5.TabIndex = 17;
            //
            // pnlMainLayerServer
            //
            this.pnlMainLayerServer.Controls.Add(this.spltLayerSetupPrivs);
            this.pnlMainLayerServer.Location = new System.Drawing.Point(47, 6);
            this.pnlMainLayerServer.Name = "pnlMainLayerServer";
            this.pnlMainLayerServer.Size = new System.Drawing.Size(567, 513);
            this.pnlMainLayerServer.TabIndex = 28;
            //
            // spltLayerSetupPrivs
            //
            this.spltLayerSetupPrivs.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltLayerSetupPrivs.FixedPanel = System.Windows.Forms.FixedPanel.Panel1;
            this.spltLayerSetupPrivs.IsSplitterFixed = true;
            this.spltLayerSetupPrivs.Location = new System.Drawing.Point(0, 0);
            this.spltLayerSetupPrivs.Name = "spltLayerSetupPrivs";
            this.spltLayerSetupPrivs.Orientation = System.Windows.Forms.Orientation.Horizontal;
            //
            // spltLayerSetupPrivs.Panel1
            //
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.pnlLayerServerTester);
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.lblLayerServerSetupTitle);
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.panel1);
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.lnkWhatIsPRoConLayer);
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.lnkStartStopLayer);
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.picLayerServerStatus);
            this.spltLayerSetupPrivs.Panel1.Controls.Add(this.lblLayerServerStatus);
            //
            // spltLayerSetupPrivs.Panel2
            //
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lnkManageAccounts);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lsvLayerAccounts);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lblLayerAssignAccountPrivilegesTitle);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.panel2);
            this.spltLayerSetupPrivs.Size = new System.Drawing.Size(567, 513);
            this.spltLayerSetupPrivs.SplitterDistance = 195;
            this.spltLayerSetupPrivs.TabIndex = 21;
            //
            // pnlLayerServerTester
            //
            this.pnlLayerServerTester.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pnlLayerServerTester.Controls.Add(this.picLayerForwardedTestStatus);
            this.pnlLayerServerTester.Controls.Add(this.lblLayerForwardedTestStatus);
            this.pnlLayerServerTester.Controls.Add(this.lnkLayerForwardedTest);
            this.pnlLayerServerTester.Location = new System.Drawing.Point(45, 101);
            this.pnlLayerServerTester.Name = "pnlLayerServerTester";
            this.pnlLayerServerTester.Size = new System.Drawing.Size(513, 66);
            this.pnlLayerServerTester.TabIndex = 27;
            this.pnlLayerServerTester.Visible = false;
            //
            // picLayerForwardedTestStatus
            //
            this.picLayerForwardedTestStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picLayerForwardedTestStatus.Location = new System.Drawing.Point(13, 8);
            this.picLayerForwardedTestStatus.Name = "picLayerForwardedTestStatus";
            this.picLayerForwardedTestStatus.Size = new System.Drawing.Size(32, 32);
            this.picLayerForwardedTestStatus.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLayerForwardedTestStatus.TabIndex = 9;
            this.picLayerForwardedTestStatus.TabStop = false;
            //
            // lblLayerForwardedTestStatus
            //
            this.lblLayerForwardedTestStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerForwardedTestStatus.ForeColor = System.Drawing.Color.Maroon;
            this.lblLayerForwardedTestStatus.Location = new System.Drawing.Point(57, 28);
            this.lblLayerForwardedTestStatus.Name = "lblLayerForwardedTestStatus";
            this.lblLayerForwardedTestStatus.Size = new System.Drawing.Size(453, 38);
            this.lblLayerForwardedTestStatus.TabIndex = 12;
            this.lblLayerForwardedTestStatus.Text = "Port 5555 is closed to incomming connections";
            //
            // lnkLayerForwardedTest
            //
            this.lnkLayerForwardedTest.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLayerForwardedTest.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lnkLayerForwardedTest.AutoSize = true;
            this.lnkLayerForwardedTest.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkLayerForwardedTest.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLayerForwardedTest.Location = new System.Drawing.Point(57, 8);
            this.lnkLayerForwardedTest.Name = "lnkLayerForwardedTest";
            this.lnkLayerForwardedTest.Size = new System.Drawing.Size(214, 15);
            this.lnkLayerForwardedTest.TabIndex = 2;
            this.lnkLayerForwardedTest.TabStop = true;
            this.lnkLayerForwardedTest.Text = "Test connection to PRoCon layer server";
            this.lnkLayerForwardedTest.VisitedLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLayerForwardedTest.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkLayerForwardedTest_LinkClicked);
            //
            // lblLayerServerSetupTitle
            //
            this.lblLayerServerSetupTitle.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerServerSetupTitle.AutoSize = true;
            this.lblLayerServerSetupTitle.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblLayerServerSetupTitle.Location = new System.Drawing.Point(37, 20);
            this.lblLayerServerSetupTitle.Name = "lblLayerServerSetupTitle";
            this.lblLayerServerSetupTitle.Size = new System.Drawing.Size(110, 15);
            this.lblLayerServerSetupTitle.TabIndex = 22;
            this.lblLayerServerSetupTitle.Text = "Layer server setup";
            //
            // panel1
            //
            this.panel1.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel1.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel1.Location = new System.Drawing.Point(41, 29);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(495, 1);
            this.panel1.TabIndex = 25;
            //
            // lnkWhatIsPRoConLayer
            //
            this.lnkWhatIsPRoConLayer.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkWhatIsPRoConLayer.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lnkWhatIsPRoConLayer.AutoSize = true;
            this.lnkWhatIsPRoConLayer.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkWhatIsPRoConLayer.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkWhatIsPRoConLayer.Location = new System.Drawing.Point(54, 173);
            this.lnkWhatIsPRoConLayer.Name = "lnkWhatIsPRoConLayer";
            this.lnkWhatIsPRoConLayer.Size = new System.Drawing.Size(134, 15);
            this.lnkWhatIsPRoConLayer.TabIndex = 23;
            this.lnkWhatIsPRoConLayer.TabStop = true;
            this.lnkWhatIsPRoConLayer.Text = "What is a PRoCon layer?";
            this.lnkWhatIsPRoConLayer.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkWhatIsPRoConLayer_LinkClicked);
            //
            // lnkStartStopLayer
            //
            this.lnkStartStopLayer.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkStartStopLayer.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lnkStartStopLayer.AutoSize = true;
            this.lnkStartStopLayer.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkStartStopLayer.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkStartStopLayer.Location = new System.Drawing.Point(102, 51);
            this.lnkStartStopLayer.Name = "lnkStartStopLayer";
            this.lnkStartStopLayer.Size = new System.Drawing.Size(157, 15);
            this.lnkStartStopLayer.TabIndex = 21;
            this.lnkStartStopLayer.TabStop = true;
            this.lnkStartStopLayer.Text = "Turn PRoCon layer server on";
            this.lnkStartStopLayer.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkStartStopLayer_LinkClicked);
            //
            // picLayerServerStatus
            //
            this.picLayerServerStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picLayerServerStatus.Enabled = false;
            this.picLayerServerStatus.Location = new System.Drawing.Point(58, 51);
            this.picLayerServerStatus.Name = "picLayerServerStatus";
            this.picLayerServerStatus.Size = new System.Drawing.Size(32, 32);
            this.picLayerServerStatus.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLayerServerStatus.TabIndex = 26;
            this.picLayerServerStatus.TabStop = false;
            //
            // lblLayerServerStatus
            //
            this.lblLayerServerStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerServerStatus.ForeColor = System.Drawing.Color.Maroon;
            this.lblLayerServerStatus.Location = new System.Drawing.Point(102, 70);
            this.lblLayerServerStatus.Name = "lblLayerServerStatus";
            this.lblLayerServerStatus.Size = new System.Drawing.Size(428, 69);
            this.lblLayerServerStatus.TabIndex = 24;
            this.lblLayerServerStatus.Text = "Server is offline";
            //
            // lnkManageAccounts
            //
            this.lnkManageAccounts.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkManageAccounts.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lnkManageAccounts.AutoSize = true;
            this.lnkManageAccounts.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkManageAccounts.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkManageAccounts.Location = new System.Drawing.Point(52, 256);
            this.lnkManageAccounts.Name = "lnkManageAccounts";
            this.lnkManageAccounts.Size = new System.Drawing.Size(103, 15);
            this.lnkManageAccounts.TabIndex = 23;
            this.lnkManageAccounts.TabStop = true;
            this.lnkManageAccounts.Text = "Manage Accounts";
            this.lnkManageAccounts.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkManageAccounts_LinkClicked);
            //
            // lsvLayerAccounts
            //
            this.lsvLayerAccounts.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)));
            this.lsvLayerAccounts.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colAccounts,
            this.colRConAccess,
            this.colPrivileges,
            this.colIPPort});
            this.lsvLayerAccounts.FullRowSelect = true;
            this.lsvLayerAccounts.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.lsvLayerAccounts.Location = new System.Drawing.Point(56, 33);
            this.lsvLayerAccounts.MultiSelect = false;
            this.lsvLayerAccounts.Name = "lsvLayerAccounts";
            this.lsvLayerAccounts.Size = new System.Drawing.Size(472, 220);
            this.lsvLayerAccounts.TabIndex = 20;
            this.lsvLayerAccounts.UseCompatibleStateImageBehavior = false;
            this.lsvLayerAccounts.View = System.Windows.Forms.View.Details;
            this.lsvLayerAccounts.SelectedIndexChanged += new System.EventHandler(this.lstLayerAccounts_SelectedIndexChanged);
            //
            // colAccounts
            //
            this.colAccounts.Tag = "colAccounts";
            this.colAccounts.Text = "Accounts";
            this.colAccounts.Width = 114;
            //
            // colRConAccess
            //
            this.colRConAccess.Text = "RCon Access";
            this.colRConAccess.Width = 88;
            //
            // colPrivileges
            //
            this.colPrivileges.Tag = "colPrivileges";
            this.colPrivileges.Text = "Local Privileges";
            this.colPrivileges.Width = 108;
            //
            // colIPPort
            //
            this.colIPPort.Tag = "colIPPort";
            this.colIPPort.Text = "IpAddress:Port";
            this.colIPPort.Width = 118;
            //
            // lblLayerAssignAccountPrivilegesTitle
            //
            this.lblLayerAssignAccountPrivilegesTitle.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerAssignAccountPrivilegesTitle.AutoSize = true;
            this.lblLayerAssignAccountPrivilegesTitle.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLayerAssignAccountPrivilegesTitle.Location = new System.Drawing.Point(30, 6);
            this.lblLayerAssignAccountPrivilegesTitle.Name = "lblLayerAssignAccountPrivilegesTitle";
            this.lblLayerAssignAccountPrivilegesTitle.Size = new System.Drawing.Size(233, 15);
            this.lblLayerAssignAccountPrivilegesTitle.TabIndex = 21;
            this.lblLayerAssignAccountPrivilegesTitle.Text = "Choose an account to assign privileges to";
            //
            // panel2
            //
            this.panel2.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel2.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel2.Location = new System.Drawing.Point(34, 15);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(495, 1);
            this.panel2.TabIndex = 22;
            //
            // uscAccountsPanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.pnlAccountPrivileges);
            this.Controls.Add(this.pnlStartPRoConLayer);
            this.Controls.Add(this.pnlMainLayerServer);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscAccountsPanel";
            this.Size = new System.Drawing.Size(1245, 1071);
            this.Load += new System.EventHandler(this.uscAccountsPanel_Load);
            this.pnlAccountPrivileges.ResumeLayout(false);
            this.pnlStartPRoConLayer.ResumeLayout(false);
            this.pnlStartPRoConLayer.PerformLayout();
            this.pnlMainLayerServer.ResumeLayout(false);
            this.spltLayerSetupPrivs.Panel1.ResumeLayout(false);
            this.spltLayerSetupPrivs.Panel1.PerformLayout();
            this.spltLayerSetupPrivs.Panel2.ResumeLayout(false);
            this.spltLayerSetupPrivs.Panel2.PerformLayout();
            this.spltLayerSetupPrivs.ResumeLayout(false);
            this.pnlLayerServerTester.ResumeLayout(false);
            this.pnlLayerServerTester.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerForwardedTestStatus)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerServerStatus)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel pnlAccountPrivileges;
        private System.Windows.Forms.Panel pnlStartPRoConLayer;
        private System.Windows.Forms.Button btnCancelLayerStart;
        private System.Windows.Forms.Button btnLayerStart;
        private System.Windows.Forms.Label lblLayerStartPort;
        private System.Windows.Forms.TextBox txtLayerStartPort;
        private System.Windows.Forms.Label lblLayerStartTitle;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.Panel panel5;
        private System.Windows.Forms.Panel pnlMainLayerServer;
        private System.Windows.Forms.Label lblExampleLayerName;
        private System.Windows.Forms.TextBox txtLayerName;
        private System.Windows.Forms.Label lblLayerName;
        private System.Windows.Forms.TextBox txtLayerBindingAddress;
        private System.Windows.Forms.Label lblLayerBindingIP;
        private System.Windows.Forms.Label lblBindingExplanation;
        private System.Windows.Forms.Button btnInsertName;
        private System.Windows.Forms.SplitContainer spltLayerSetupPrivs;
        private System.Windows.Forms.Panel pnlLayerServerTester;
        private System.Windows.Forms.PictureBox picLayerForwardedTestStatus;
        private System.Windows.Forms.Label lblLayerForwardedTestStatus;
        private System.Windows.Forms.LinkLabel lnkLayerForwardedTest;
        private System.Windows.Forms.Label lblLayerServerSetupTitle;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.LinkLabel lnkWhatIsPRoConLayer;
        private System.Windows.Forms.LinkLabel lnkStartStopLayer;
        private System.Windows.Forms.PictureBox picLayerServerStatus;
        private System.Windows.Forms.Label lblLayerServerStatus;
        private System.Windows.Forms.LinkLabel lnkManageAccounts;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvLayerAccounts;
        private System.Windows.Forms.ColumnHeader colAccounts;
        private System.Windows.Forms.ColumnHeader colRConAccess;
        private System.Windows.Forms.ColumnHeader colPrivileges;
        private System.Windows.Forms.ColumnHeader colIPPort;
        private System.Windows.Forms.Label lblLayerAssignAccountPrivilegesTitle;
        private System.Windows.Forms.Panel panel2;
        private uscPrivilegesSelection uscPrivileges;
    }*/
}
