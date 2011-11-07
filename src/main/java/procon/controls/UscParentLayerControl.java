package procon.controls;

public class UscParentLayerControl {

    /*public partial class uscParentLayerControl : UserControl {

        private frmMain m_frmMain;
        private CLocalization m_clocLanguage = null;

        private PRoConClient m_prcClient;

        private bool m_blUpdatingPlugins;
        private Dictionary<string, PluginDetails> m_dicRemotePlugins;

        //public delegate void SendCommandDelegate(List<string> lstCommand);
        //public event SendCommandDelegate SendCommand;

        private Dictionary<string, AsyncStyleSetting> m_dicAsyncSettingControls;

        public uscParentLayerControl() {
            InitializeComponent();

            this.m_dicRemotePlugins = new Dictionary<string, PluginDetails>();
            this.m_blUpdatingPlugins = false;

            this.pnlMainLayerServer.Dock = DockStyle.Fill;
            this.pnlAccountPrivileges.Dock = DockStyle.Fill;

            this.uscPlugins.OnTabChange += new uscServerConnection.OnTabChangeDelegate(uscPlugins_OnTabChange);

            this.m_dicAsyncSettingControls = new Dictionary<string, AsyncStyleSetting>();
            this.m_dicAsyncSettingControls.Add("procon.account.create", new AsyncStyleSetting(this.picCreateAccount, this.lnkCreateAccount, new Control[] { this.lnkEditAccount, this.lnkDeleteAccount, this.lnkCreateAccount, this.lnkChangeAccountPassword }, true));
            this.m_dicAsyncSettingControls.Add("procon.account.setPassword", new AsyncStyleSetting(this.picChangeAccountPassword, this.lnkCreateAccount, new Control[] { this.lnkEditAccount, this.lnkDeleteAccount, this.lnkCreateAccount, this.lnkChangeAccountPassword }, true));
            this.m_dicAsyncSettingControls.Add("procon.account.delete", new AsyncStyleSetting(this.picDeleteAccount, this.lnkCreateAccount, new Control[] { this.lnkEditAccount, this.lnkDeleteAccount, this.lnkCreateAccount, this.lnkChangeAccountPassword }, true));
            this.m_dicAsyncSettingControls.Add("procon.layer.setPrivileges", new AsyncStyleSetting(this.picEditAccount, this.lnkCreateAccount, new Control[] { this.lnkEditAccount, this.lnkDeleteAccount, this.lnkCreateAccount, this.lnkChangeAccountPassword }, true));

            this.uscPrivileges.OnUpdatePrivileges += new uscPrivilegesSelection.OnUpdatePrivilegesDelegate(uscPrivileges_OnUpdatePrivileges);
            this.uscPrivileges.OnCancelPrivileges += new uscPrivilegesSelection.OnCancelPrivilegesDelegate(uscPrivileges_OnCancelPrivileges);
        }

        public void Initialize(frmMain frmMainWindow, uscServerConnection uscParent) {

            this.m_frmMain = frmMainWindow;

            this.uscPlugins.Initialize(this.m_frmMain, uscParent);
            this.lsvLayerAccounts.SmallImageList = this.m_frmMain.iglIcons;
        }

        public void SetColour(string strVariable, string strValue) {
            this.uscPlugins.SetColour(strVariable, strValue);
        }

        public void SetConnection(PRoConClient prcClient) {
            if ((this.m_prcClient = prcClient) != null) {
                this.uscPlugins.SetConnection(prcClient);

                this.m_prcClient.RemoteAccountLoggedIn += new PRoConClient.RemoteAccountLoginStatusHandler(m_prcClient_RemoteAccountLoggedIn);
                this.m_prcClient.RemoteAccountCreated += new PRoConClient.RemoteAccountHandler(m_prcClient_RemoteAccountCreated);
                this.m_prcClient.RemoteAccountChangePassword += new PRoConClient.EmptyParamterHandler(m_prcClient_RemoteAccountChangePassword);
                this.m_prcClient.RemoteAccountDeleted += new PRoConClient.RemoteAccountHandler(m_prcClient_RemoteAccountDeleted);
                this.m_prcClient.RemoteAccountAltered += new PRoConClient.RemoteAccountAlteredHandler(m_prcClient_RemoteAccountAltered);

                this.m_prcClient.RemoteLoadedPlugins += new PRoConClient.RemoteLoadedPluginsHandler(m_prcClient_RemoteLoadedPlugins);
                this.m_prcClient.RemoteEnabledPlugins += new PRoConClient.RemoteEnabledPluginsHandler(m_prcClient_RemoteEnabledPlugins);
                this.m_prcClient.RemotePluginLoaded += new PRoConClient.RemotePluginLoadedHandler(m_prcClient_RemotePluginLoaded);
                this.m_prcClient.RemotePluginEnabled += new PRoConClient.RemotePluginEnabledHandler(m_prcClient_RemotePluginEnabled);
                this.m_prcClient.RemotePluginVariables += new PRoConClient.RemotePluginVariablesHandler(m_prcClient_RemotePluginVariables);
                this.m_prcClient.ReadRemotePluginConsole += new PRoConClient.ReadRemoteConsoleHandler(m_prcClient_ReadRemotePluginConsole);

                this.m_prcClient.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcClient_ProconPrivileges);
            }
        }

        public void SetLocalization(CLocalization clocLanguage) {

            if ((this.m_clocLanguage = clocLanguage) != null) {

                this.tabPlugins.Text = this.tabPlugins.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabPlugins", null);
                this.uscPlugins.SetLocalization(this.m_clocLanguage);
                //this.tbpPRoConLayer.Text = this.m_clocLanguage.GetLocalized("uscAccountsPanel.Title", null);

                this.tabAccounts.Text = this.tabAccounts.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabAccounts", null);
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

                this.lnkEditAccount.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkEditAccount", null);
                this.lnkDeleteAccount.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkDeleteAccount", null);
                this.lblCreateNewAccount.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lblCreateNewAccount", null);
                this.lblUsername.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lblUsername", null);
                this.lblPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lblPassword", null);
                this.lnkCreateAccount.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkCreateAccount", null);
                this.lblUserNameExistsError.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lblUserNameExistsError", null);
                this.lnkChangeAccountPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkChangeAccountPassword.Blank", null);

                this.uscPrivileges.SetLocalization(this.m_clocLanguage);

                this.RefreshLayerPrivilegesPanel();
            }
        }

        public event uscServerConnection.OnTabChangeDelegate OnTabChange;

        private void tbcLayerControl_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.OnTabChange != null) {
                Stack<string> stkTabIndexes = new Stack<string>();
                stkTabIndexes.Push(tbcLayerControl.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        public void SetTabIndexes(Stack<string> stkTabIndexes) {
            if (tbcLayerControl.TabPages.ContainsKey(stkTabIndexes.Peek()) == true) {
                this.tbcLayerControl.SelectedTab = tbcLayerControl.TabPages[stkTabIndexes.Pop()];

                if (stkTabIndexes.Count > 0) {
                    switch (tbcLayerControl.SelectedTab.Name) {
                        case "tabPlugins":
                            this.uscPlugins.SetTabIndexes(stkTabIndexes);
                            break;
                    }
                }
            }
        }

        void uscPlugins_OnTabChange(object sender, Stack<string> stkTabIndexes) {
            if (this.OnTabChange != null) {
                stkTabIndexes.Push(tbcLayerControl.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        #region Remote Plugins

        private void m_prcClient_RemoteLoadedPlugins(PRoConClient sender, Dictionary<string, PluginDetails> loadedPlugins) {
            this.m_dicRemotePlugins = loadedPlugins;
            this.m_blUpdatingPlugins = true;

            this.uscPlugins.SetLoadedPlugins(new List<string>(this.m_dicRemotePlugins.Keys));

            this.m_blUpdatingPlugins = false;
        }
        *//*
        public void SetRemoteLoadedPlugins(Dictionary<string, SPluginDetails> dicRemotePlugins) {

            this.m_dicRemotePlugins = dicRemotePlugins;
            this.m_blUpdatingPlugins = true;

            this.uscPlugins.SetLoadedPlugins(new List<string>(this.m_dicRemotePlugins.Keys));

            this.m_blUpdatingPlugins = false;
        }
        *//*
        private PluginDetails uscPlugins_GetPluginDetails(string strClassName) {

            PluginDetails spdReturnDetails = default(PluginDetails);

            if (this.m_dicRemotePlugins != null) {

                if (this.m_dicRemotePlugins.ContainsKey(strClassName) == true) {
                    spdReturnDetails = this.m_dicRemotePlugins[strClassName];
                }
            }

            return spdReturnDetails;
        }

        void m_prcClient_RemoteEnabledPlugins(PRoConClient sender, List<string> enabledPluginClasses) {
            this.m_blUpdatingPlugins = true;

            foreach (ListViewItem lviPlugin in this.uscPlugins.LoadedPlugins) {

                if (enabledPluginClasses.Contains(lviPlugin.Name) == true) {
                    lviPlugin.Checked = true;
                }
                else {
                    lviPlugin.Checked = false;
                }
            }

            this.m_blUpdatingPlugins = false;
        }

        *//*
        public void SetRemoteEnabledPlugins(List<string> lstClassNames) {


        }
        *//*

        private void m_prcClient_RemotePluginLoaded(PRoConClient sender, PluginDetails spdDetails) {

            if (this.m_dicRemotePlugins.ContainsKey(spdDetails.ClassName) == true) {
                this.m_dicRemotePlugins[spdDetails.ClassName] = spdDetails;
            }
            else {
                this.m_dicRemotePlugins.Add(spdDetails.ClassName, spdDetails);
            }

            this.m_blUpdatingPlugins = true;
            this.uscPlugins.SetLoadedPlugins(new List<string>(this.m_dicRemotePlugins.Keys));
            this.m_blUpdatingPlugins = false;
        }

        *//*
        public void OnRemotePluginLoaded(SPluginDetails spdDetails) {

        }
        *//*

        private void m_prcClient_RemotePluginEnabled(PRoConClient sender, string strClassName, bool isEnabled) {
            this.m_blUpdatingPlugins = true;

            if (this.uscPlugins.LoadedPlugins.ContainsKey(strClassName) == true) {
                this.uscPlugins.LoadedPlugins[strClassName].Checked = isEnabled;
            }

            this.m_blUpdatingPlugins = false;
        }

        *//*
        public void RemotePluginEnabled(string strClassName, bool blEnabled) {

        }
        *//*

        private void m_prcClient_RemotePluginVariables(PRoConClient sender, string strClassName, List<CPluginVariable> lstVariables) {
            if (this.m_dicRemotePlugins.ContainsKey(strClassName) == true) {
                PluginDetails spdUpdatedDetails = this.m_dicRemotePlugins[strClassName];
                spdUpdatedDetails.DisplayPluginVariables = lstVariables;
                this.m_dicRemotePlugins[strClassName] = spdUpdatedDetails;

                this.m_blUpdatingPlugins = true;
                this.uscPlugins.SetLoadedPlugins(new List<string>(this.m_dicRemotePlugins.Keys));
                this.m_blUpdatingPlugins = false;
            }
        }

        private void uscPlugins_SetPluginVariable(string strClassName, string strVariable, string strValue) {

            if (this.m_blUpdatingPlugins == false) {
                this.m_prcClient.SendProconPluginSetVariablePacket(strClassName, strVariable, strValue);
            }
        }

        private void uscPlugins_PluginEnabled(string strClassName, bool blEnabled) {

            if (this.m_blUpdatingPlugins == false) {

                this.m_prcClient.SendProconPluginEnablePacket(strClassName, blEnabled);
            }
        }

        private void m_prcClient_ReadRemotePluginConsole(PRoConClient sender, DateTime loggedTime, string text) {
            this.uscPlugins.Write(loggedTime, text);
        }

        #endregion

        #region Settings Animator

        private void SetControlValue(Control ctrlTarget, object objValue) {

            if (objValue != null) {
                if (ctrlTarget is TextBox) {
                    ((TextBox)ctrlTarget).Text = (string)objValue;
                }
                else if (ctrlTarget is CheckBox) {
                    ((CheckBox)ctrlTarget).Checked = (bool)objValue;
                }
                else if (ctrlTarget is NumericUpDown) {
                    ((NumericUpDown)ctrlTarget).Value = (decimal)objValue;
                }
                else if (ctrlTarget is Label) {
                    ((Label)ctrlTarget).Text = (string)objValue;
                }
            }
        }

        private void WaitForSettingResponse(string strResponseCommand) {

            if (this.m_dicAsyncSettingControls.ContainsKey(strResponseCommand) == true) {
                //this.m_dicAsyncSettingControls[strResponseCommand].m_objOriginalValue = String.Empty;
                this.m_dicAsyncSettingControls[strResponseCommand].m_picStatus.Image = this.m_frmMain.picAjaxStyleLoading.Image;
                this.m_dicAsyncSettingControls[strResponseCommand].m_iTimeout = AsyncStyleSetting.INT_ANIMATEDSETTING_TIMEOUT_TICKS;

                this.tmrTimeoutCheck.Enabled = true;

                foreach (Control ctrlEnable in this.m_dicAsyncSettingControls[strResponseCommand].ma_ctrlEnabledInputs) {
                    if (ctrlEnable is TextBox) {
                        ((TextBox)ctrlEnable).ReadOnly = true;
                    }
                    else {
                        ctrlEnable.Enabled = false;
                    }
                }
            }
        }

        public void OnSettingResponse(string strResponseCommand, bool blSuccess) {

            if (this.m_dicAsyncSettingControls.ContainsKey(strResponseCommand) == true) {

                if (this.m_dicAsyncSettingControls[strResponseCommand].m_blReEnableControls == true) {
                    foreach (Control ctrlEnable in this.m_dicAsyncSettingControls[strResponseCommand].ma_ctrlEnabledInputs) {
                        if (ctrlEnable is TextBox) {
                            ((TextBox)ctrlEnable).ReadOnly = false;
                        }
                        else {
                            ctrlEnable.Enabled = true;
                        }
                    }
                }

                this.m_dicAsyncSettingControls[strResponseCommand].IgnoreEvent = true;

                if (blSuccess == true) {
                    this.m_dicAsyncSettingControls[strResponseCommand].m_picStatus.Image = this.m_frmMain.picAjaxStyleSuccess.Image;
                    this.m_dicAsyncSettingControls[strResponseCommand].m_iTimeout = AsyncStyleSetting.INT_ANIMATEDSETTING_SHOWRESULT_TICKS;
                    this.m_dicAsyncSettingControls[strResponseCommand].m_blSuccess = true;
                }
                else {
                    this.m_dicAsyncSettingControls[strResponseCommand].m_picStatus.Image = this.m_frmMain.picAjaxStyleFail.Image;
                    this.m_dicAsyncSettingControls[strResponseCommand].m_iTimeout = AsyncStyleSetting.INT_ANIMATEDSETTING_SHOWRESULT_TICKS;
                    this.m_dicAsyncSettingControls[strResponseCommand].m_blSuccess = false;
                }

                this.tmrTimeoutCheck.Enabled = true;

                this.m_dicAsyncSettingControls[strResponseCommand].IgnoreEvent = false;
            }
        }


        private int CountTicking() {
            int i = 0;

            foreach (KeyValuePair<string, AsyncStyleSetting> kvpAsync in this.m_dicAsyncSettingControls) {
                if (kvpAsync.Value.m_iTimeout >= 0) {
                    i++;
                }
            }

            return i;
        }

        private void tmrSettingsAnimator_Tick(object sender, EventArgs e) {
            //if (((from o in this.m_dicAsyncSettingControls where o.Value.m_iTimeout >= 0 select o).Count()) > 0) {
            if (this.CountTicking() > 0) {
                foreach (KeyValuePair<string, AsyncStyleSetting> kvpAsyncSetting in this.m_dicAsyncSettingControls) {

                    kvpAsyncSetting.Value.m_iTimeout--;
                    if (kvpAsyncSetting.Value.m_iTimeout == 0 && kvpAsyncSetting.Value.m_blSuccess == false) {
                        kvpAsyncSetting.Value.m_picStatus.Image = this.m_frmMain.picAjaxStyleFail.Image;
                        kvpAsyncSetting.Value.m_iTimeout = AsyncStyleSetting.INT_ANIMATEDSETTING_SHOWRESULT_TICKS;

                        kvpAsyncSetting.Value.m_blSuccess = true;
                    }
                    else if (kvpAsyncSetting.Value.m_iTimeout == 0 && kvpAsyncSetting.Value.m_blSuccess == true) {
                        kvpAsyncSetting.Value.m_picStatus.Image = null;

                        if (kvpAsyncSetting.Value.m_blReEnableControls == true) {
                            foreach (Control ctrlEnable in kvpAsyncSetting.Value.ma_ctrlEnabledInputs) {
                                if (ctrlEnable is TextBox) {
                                    ((TextBox)ctrlEnable).ReadOnly = false;
                                }
                                else {
                                    ctrlEnable.Enabled = true;
                                }
                            }
                        }
                    }
                }
            }
            else {
                this.tmrTimeoutCheck.Enabled = false;
            }
        }

        #endregion

        #region Remote Accounts

        private bool m_blEditingPrivileges = false;
        private void ShowLayerPanel(Panel pnlShow) {
            this.pnlMainLayerServer.Hide();
            this.pnlAccountPrivileges.Hide();

            this.m_blEditingPrivileges = false;

            if (pnlShow == this.pnlMainLayerServer) {
                this.lsvLayerAccounts.SelectedItems.Clear();
            }
            else if (pnlShow == this.pnlAccountPrivileges) {
                this.m_blEditingPrivileges = true;

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
                if (lviItem.SubItems[1].Tag != null && this.m_clocLanguage != null) {
                    CPrivileges spDetails = (CPrivileges)lviItem.SubItems[1].Tag;

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

            if (this.m_blEditingPrivileges == true) {
                this.ShowLayerPanel(this.pnlAccountPrivileges);
            }

            foreach (ColumnHeader ch in this.lsvLayerAccounts.Columns) {
                ch.Width = -2;
            }

        }

        private void m_prcClient_RemoteAccountCreated(PRoConClient sender, string accountName) {
            if (this.lsvLayerAccounts.Items.ContainsKey(accountName) == false) {

                this.OnSettingResponse("procon.account.create", true);

                ListViewItem lviNewAccount = new ListViewItem(accountName);
                lviNewAccount.Name = accountName;
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

                this.lsvLayerAccounts.Items.Add(lviNewAccount);

                this.RefreshLayerPrivilegesPanel();
            }
        }
        *//*
        public void OnAccountCreated(string strAccountName) {

        }
        *//*
        private void m_prcClient_RemoteAccountChangePassword(PRoConClient sender) {
            this.OnSettingResponse("procon.account.setPassword", true);
        }

        //public void OnAccountChangePasswordConfirmation() {
        //    this.OnSettingResponse("procon.account.setPassword", true);
        //}

        private void m_prcClient_RemoteAccountDeleted(PRoConClient sender, string accountName) {
            if (this.lsvLayerAccounts.Items.ContainsKey(accountName) == true) {
                this.OnSettingResponse("procon.account.delete", true);
                this.lsvLayerAccounts.Items.Remove(this.lsvLayerAccounts.Items[accountName]);
            }
        }

        //public void OnAccountDeleted(string strAccountName) {

        //}

        private void m_prcClient_RemoteAccountAltered(PRoConClient sender, string accountName, CPrivileges accountPrivileges) {
            if (this.lsvLayerAccounts.Items.ContainsKey(accountName) == true) {
                this.OnSettingResponse("procon.layer.setPrivileges", true);
                this.lsvLayerAccounts.Items[accountName].SubItems["rconaccess"].Tag = accountPrivileges;

                this.RefreshLayerPrivilegesPanel();
            }
        }

        //public void OnAccountAltered(string strAccountName, CPrivileges spPrivs) {

        //}

        private void m_prcClient_RemoteAccountLoggedIn(PRoConClient sender, string accountName, bool isOnline) {
            if (this.lsvLayerAccounts.Items.ContainsKey(accountName) == true) {
                if (isOnline == true) {
                    this.lsvLayerAccounts.Items[accountName].ImageKey = "status_online.png";
                }
                else {
                    this.lsvLayerAccounts.Items[accountName].ImageKey = "status_offline.png";
                }
            }
        }

        private void uscPrivileges_OnUpdatePrivileges(string strAccountName, CPrivileges spUpdatedPrivs) {
            if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                this.WaitForSettingResponse("procon.layer.setPrivileges");
                this.m_prcClient.SendProconLayerSetPrivilegesPacket(this.lsvLayerAccounts.SelectedItems[0].Text, spUpdatedPrivs.PrivilegesFlags);
            }

            this.ShowLayerPanel(this.pnlMainLayerServer);
        }

        private void uscPrivileges_OnCancelPrivileges() {
            this.ShowLayerPanel(this.pnlMainLayerServer);
        }


        *//*
        private void btnSavePrivileges_Click(object sender, EventArgs e) {

            UInt32 i = 0;

            i |= (1 & Convert.ToUInt32(this.chkAllowConnectionLogin.Checked));
            i |= (1 & Convert.ToUInt32(this.chkAlterServerSettings.Checked)) << 1;
            i |= (1 & Convert.ToUInt32(this.chkChangeCurrentMapFunctions.Checked)) << 2;

            i |= (1 & Convert.ToUInt32(this.rdoNoPlayerPunishment.Checked)) << 3;
            i |= (1 & Convert.ToUInt32(this.rdoKickingPlayersOnly.Checked)) << 4;
            i |= (1 & Convert.ToUInt32(this.rdoKickingTemporaryOnly.Checked)) << 5;
            i |= (1 & Convert.ToUInt32(this.rdoKicingTemporaryPermanent.Checked)) << 6;

            i |= (1 & Convert.ToUInt32(this.rdoNotAllowedToIssuePunkbusterCommands.Checked)) << 7;
            i |= (1 & Convert.ToUInt32(this.rdoLimitedPunkbusterAccess.Checked)) << 8;
            i |= (1 & Convert.ToUInt32(this.rdoFullPunkbusterAccess.Checked)) << 9;

            i |= (1 & Convert.ToUInt32(this.chkEditMapList.Checked)) << 10;
            i |= (1 & Convert.ToUInt32(this.chkEditBanList.Checked)) << 11;
            i |= (1 & Convert.ToUInt32(this.chkEditReservedSlotsList.Checked)) << 12;

            i |= (1 & Convert.ToUInt32(this.rdoNoProconAccess.Checked)) << 13;
            i |= (1 & Convert.ToUInt32(this.rdoLimitedProconAccess.Checked)) << 14;
            i |= (1 & Convert.ToUInt32(this.rdoFullProconAccess.Checked)) << 15;

            Privilege spUpdatedPrivileges = new Privilege();
            spUpdatedPrivileges.PrivilegesFlags = i;

            // TO DO: SendCommand Privs
            if (this.lsvLayerAccounts.SelectedItems.Count > 0 && this.SendCommand != null) {
                this.WaitForSettingResponse("procon.layer.setPrivileges");
                this.SendCommand(new List<string>() { "procon.layer.setPrivileges", this.lsvLayerAccounts.SelectedItems[0].Text, spUpdatedPrivileges.PrivilegesFlags.ToString() });
            }

            this.ShowLayerPanel(this.pnlMainLayerServer);
        }
        *
        private void btnCancelPrivileges_Click(object sender, EventArgs e) {
            this.ShowLayerPanel(this.pnlMainLayerServer);
        }


        private void chkAllowConnectionLogin_CheckedChanged(object sender, EventArgs e) {
            this.pnlRconAccess.Enabled = this.chkAllowConnectionLogin.Checked;

            if (this.pnlRconAccess.Enabled == false) {
                this.rdoNoProconAccess.Checked = true;
            }
        }
        *//*
        private void lnkEditAccount_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                this.ShowLayerPanel(this.pnlAccountPrivileges);
            }
        }

        private void lnkCreateAccount_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            this.WaitForSettingResponse("procon.account.create");
            this.m_prcClient.SendProconAccountCreatePacket(this.txtUsername.Text, this.txtPassword.Text);

            this.txtUsername.Focus();
            this.txtUsername.Clear();
            this.txtPassword.Clear();
        }

        private void lnkDeleteUser_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                this.WaitForSettingResponse("procon.account.delete");
                this.m_prcClient.SendProconAccountDeletePacket(this.lsvLayerAccounts.SelectedItems[0].Text);
            }
        }

        private void txtUsername_TextChanged(object sender, EventArgs e) {

            if (this.txtUsername.Text.Length > 0) {
                if (this.lsvLayerAccounts.Items.ContainsKey(this.txtUsername.Text) == true) {
                    this.lblUserNameExistsError.Visible = true;
                    this.lnkCreateAccount.Enabled = false;

                    this.lnkChangeAccountPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkChangeAccountPassword.Username", new string[] { this.txtUsername.Text });

                    if (this.txtPassword.Text.Length > 0) {
                        this.lnkChangeAccountPassword.Enabled = true;
                    }
                }
                else {
                    if (this.txtPassword.Text.Length > 0) {
                        this.lnkCreateAccount.Enabled = true;
                    }
                    this.lblUserNameExistsError.Visible = false;
                    this.lnkChangeAccountPassword.Enabled = false;
                    this.lnkChangeAccountPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkChangeAccountPassword.Blank", null);
                }
            }
            else {
                this.lnkCreateAccount.Enabled = false;
                this.lblUserNameExistsError.Visible = false;
                this.lnkChangeAccountPassword.Enabled = false;
                this.lnkChangeAccountPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkChangeAccountPassword.Blank", null);
            }
        }

        private void txtPassword_TextChanged(object sender, EventArgs e) {

            if (this.txtUsername.Text.Length > 0 && this.txtPassword.Text.Length > 0 && this.lsvLayerAccounts.Items.ContainsKey(this.txtUsername.Text) == false) {
                this.lnkCreateAccount.Enabled = true;
                this.lnkChangeAccountPassword.Enabled = false;
                this.lnkChangeAccountPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkChangeAccountPassword.Blank", null);
            }
            else if (this.txtUsername.Text.Length > 0 && this.txtPassword.Text.Length > 0 && this.lsvLayerAccounts.Items.ContainsKey(this.txtUsername.Text) == true) {
                this.lnkCreateAccount.Enabled = false;
                this.lnkChangeAccountPassword.Enabled = true;
                this.lnkChangeAccountPassword.Text = this.m_clocLanguage.GetLocalized("uscParentLayerControl.tabAccounts.lnkChangeAccountPassword.Username", new string[] { this.txtUsername.Text });
            }
            else {
                this.lnkCreateAccount.Enabled = false;
                this.lnkChangeAccountPassword.Enabled = false;
            }
        }

        private void lsvLayerAccounts_SelectedIndexChanged(object sender, EventArgs e) {

            if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                this.lnkEditAccount.Enabled = true;
                this.lnkDeleteAccount.Enabled = true;
                this.txtUsername.Text = this.lsvLayerAccounts.SelectedItems[0].Text;
            }
            else {
                this.lnkEditAccount.Enabled = false;
                this.lnkDeleteAccount.Enabled = false;
                this.txtUsername.Text = String.Empty;
            }

        }

        private void lnkChangeAccountPassword_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.lsvLayerAccounts.SelectedItems.Count > 0) {
                this.WaitForSettingResponse("procon.account.setPassword");
                this.m_prcClient.SendProconAccountSetPasswordPacket(this.txtUsername.Text, this.txtPassword.Text);

                this.txtUsername.Focus();
                this.txtUsername.Clear();
                this.txtPassword.Clear();
            }
        }

        #endregion

        private bool m_isFormLoaded = false, m_isConnectionValid = false;
        private void RequestInitialSettings() {
            //if (this.m_isFormLoaded == true && this.m_isConnectionValid == true) {
                this.m_prcClient.SendProconAccountListAccountsPacket();
                this.m_prcClient.SendProconAccountListLoggedInPacket();

                this.m_prcClient.SendProconPluginListLoadedPacket();
                this.m_prcClient.SendProconPluginListEnabledPacket();
            //}
        }

        void m_prcClient_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {
            this.m_isConnectionValid = true;
            this.RequestInitialSettings();
        }

        private void uscParentLayerControl_Load(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_isFormLoaded = true;
                this.RequestInitialSettings();
            }
        }
    }*/

    /*partial class uscParentLayerControl {
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
            this.components = new System.ComponentModel.Container();
            this.tbcLayerControl = new System.Windows.Forms.TabControl();
            this.tabPlugins = new System.Windows.Forms.TabPage();
            this.uscPlugins = new PRoCon.uscPluginPanel();
            this.tabAccounts = new System.Windows.Forms.TabPage();
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
            this.picEditAccount = new System.Windows.Forms.PictureBox();
            this.picDeleteAccount = new System.Windows.Forms.PictureBox();
            this.picCreateAccount = new System.Windows.Forms.PictureBox();
            this.picChangeAccountPassword = new System.Windows.Forms.PictureBox();
            this.lnkChangeAccountPassword = new System.Windows.Forms.LinkLabel();
            this.lnkCreateAccount = new System.Windows.Forms.LinkLabel();
            this.lnkEditAccount = new System.Windows.Forms.LinkLabel();
            this.lnkDeleteAccount = new System.Windows.Forms.LinkLabel();
            this.lblCreateNewAccount = new System.Windows.Forms.Label();
            this.panel4 = new System.Windows.Forms.Panel();
            this.lblUserNameExistsError = new System.Windows.Forms.Label();
            this.txtPassword = new System.Windows.Forms.TextBox();
            this.lblPassword = new System.Windows.Forms.Label();
            this.txtUsername = new System.Windows.Forms.TextBox();
            this.lblUsername = new System.Windows.Forms.Label();
            this.lsvLayerAccounts = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colAccounts = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colRConAccess = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPrivileges = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.lblLayerAssignAccountPrivilegesTitle = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.pnlAccountPrivileges = new System.Windows.Forms.Panel();
            this.uscPrivileges = new PRoCon.uscPrivilegesSelection();
            this.tmrTimeoutCheck = new System.Windows.Forms.Timer(this.components);
            this.tbcLayerControl.SuspendLayout();
            this.tabPlugins.SuspendLayout();
            this.tabAccounts.SuspendLayout();
            this.pnlMainLayerServer.SuspendLayout();
            this.spltLayerSetupPrivs.Panel1.SuspendLayout();
            this.spltLayerSetupPrivs.Panel2.SuspendLayout();
            this.spltLayerSetupPrivs.SuspendLayout();
            this.pnlLayerServerTester.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerForwardedTestStatus)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLayerServerStatus)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picEditAccount)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picDeleteAccount)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picCreateAccount)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picChangeAccountPassword)).BeginInit();
            this.pnlAccountPrivileges.SuspendLayout();
            this.SuspendLayout();
            //
            // tbcLayerControl
            //
            this.tbcLayerControl.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tbcLayerControl.Controls.Add(this.tabPlugins);
            this.tbcLayerControl.Controls.Add(this.tabAccounts);
            this.tbcLayerControl.Location = new System.Drawing.Point(0, 3);
            this.tbcLayerControl.Name = "tbcLayerControl";
            this.tbcLayerControl.SelectedIndex = 0;
            this.tbcLayerControl.Size = new System.Drawing.Size(1001, 1297);
            this.tbcLayerControl.TabIndex = 0;
            this.tbcLayerControl.SelectedIndexChanged += new System.EventHandler(this.tbcLayerControl_SelectedIndexChanged);
            //
            // tabPlugins
            //
            this.tabPlugins.Controls.Add(this.uscPlugins);
            this.tabPlugins.Location = new System.Drawing.Point(4, 24);
            this.tabPlugins.Name = "tabPlugins";
            this.tabPlugins.Padding = new System.Windows.Forms.Padding(9);
            this.tabPlugins.Size = new System.Drawing.Size(993, 1269);
            this.tabPlugins.TabIndex = 0;
            this.tabPlugins.Text = "Plugins";
            this.tabPlugins.UseVisualStyleBackColor = true;
            //
            // uscPlugins
            //
            this.uscPlugins.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.uscPlugins.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscPlugins.LocalPlugins = true;
            this.uscPlugins.Location = new System.Drawing.Point(9, 9);
            this.uscPlugins.Name = "uscPlugins";
            this.uscPlugins.Size = new System.Drawing.Size(975, 1248);
            this.uscPlugins.TabIndex = 0;
            this.uscPlugins.GetPluginDetails += new PRoCon.uscPluginPanel.GetPluginDetailsDelegate(this.uscPlugins_GetPluginDetails);
            this.uscPlugins.SetPluginVariable += new PRoCon.uscPluginPanel.SetPluginVariableDelegate(this.uscPlugins_SetPluginVariable);
            this.uscPlugins.PluginEnabled += new PRoCon.uscPluginPanel.PluginEnabledDelegate(this.uscPlugins_PluginEnabled);
            //
            // tabAccounts
            //
            this.tabAccounts.Controls.Add(this.pnlMainLayerServer);
            this.tabAccounts.Controls.Add(this.pnlAccountPrivileges);
            this.tabAccounts.Location = new System.Drawing.Point(4, 24);
            this.tabAccounts.Name = "tabAccounts";
            this.tabAccounts.Padding = new System.Windows.Forms.Padding(3);
            this.tabAccounts.Size = new System.Drawing.Size(993, 1245);
            this.tabAccounts.TabIndex = 1;
            this.tabAccounts.Text = "Accounts";
            this.tabAccounts.UseVisualStyleBackColor = true;
            //
            // pnlMainLayerServer
            //
            this.pnlMainLayerServer.Controls.Add(this.spltLayerSetupPrivs);
            this.pnlMainLayerServer.Location = new System.Drawing.Point(150, 9);
            this.pnlMainLayerServer.Name = "pnlMainLayerServer";
            this.pnlMainLayerServer.Size = new System.Drawing.Size(661, 592);
            this.pnlMainLayerServer.TabIndex = 33;
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
            this.spltLayerSetupPrivs.Panel1Collapsed = true;
            //
            // spltLayerSetupPrivs.Panel2
            //
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.picEditAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.picDeleteAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.picCreateAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.picChangeAccountPassword);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lnkChangeAccountPassword);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lnkCreateAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lnkEditAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lnkDeleteAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lblCreateNewAccount);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.panel4);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lblUserNameExistsError);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.txtPassword);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lblPassword);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.txtUsername);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lblUsername);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lsvLayerAccounts);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.lblLayerAssignAccountPrivilegesTitle);
            this.spltLayerSetupPrivs.Panel2.Controls.Add(this.panel2);
            this.spltLayerSetupPrivs.Size = new System.Drawing.Size(661, 592);
            this.spltLayerSetupPrivs.SplitterDistance = 195;
            this.spltLayerSetupPrivs.SplitterWidth = 5;
            this.spltLayerSetupPrivs.TabIndex = 21;
            //
            // pnlLayerServerTester
            //
            this.pnlLayerServerTester.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pnlLayerServerTester.Controls.Add(this.picLayerForwardedTestStatus);
            this.pnlLayerServerTester.Controls.Add(this.lblLayerForwardedTestStatus);
            this.pnlLayerServerTester.Controls.Add(this.lnkLayerForwardedTest);
            this.pnlLayerServerTester.Location = new System.Drawing.Point(52, 117);
            this.pnlLayerServerTester.Name = "pnlLayerServerTester";
            this.pnlLayerServerTester.Size = new System.Drawing.Size(598, 76);
            this.pnlLayerServerTester.TabIndex = 27;
            this.pnlLayerServerTester.Visible = false;
            //
            // picLayerForwardedTestStatus
            //
            this.picLayerForwardedTestStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picLayerForwardedTestStatus.Location = new System.Drawing.Point(15, 9);
            this.picLayerForwardedTestStatus.Name = "picLayerForwardedTestStatus";
            this.picLayerForwardedTestStatus.Size = new System.Drawing.Size(37, 37);
            this.picLayerForwardedTestStatus.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLayerForwardedTestStatus.TabIndex = 9;
            this.picLayerForwardedTestStatus.TabStop = false;
            //
            // lblLayerForwardedTestStatus
            //
            this.lblLayerForwardedTestStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerForwardedTestStatus.ForeColor = System.Drawing.Color.Maroon;
            this.lblLayerForwardedTestStatus.Location = new System.Drawing.Point(66, 32);
            this.lblLayerForwardedTestStatus.Name = "lblLayerForwardedTestStatus";
            this.lblLayerForwardedTestStatus.Size = new System.Drawing.Size(528, 44);
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
            this.lnkLayerForwardedTest.Location = new System.Drawing.Point(66, 9);
            this.lnkLayerForwardedTest.Name = "lnkLayerForwardedTest";
            this.lnkLayerForwardedTest.Size = new System.Drawing.Size(214, 15);
            this.lnkLayerForwardedTest.TabIndex = 2;
            this.lnkLayerForwardedTest.TabStop = true;
            this.lnkLayerForwardedTest.Text = "Test connection to PRoCon layer server";
            this.lnkLayerForwardedTest.VisitedLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            //
            // lblLayerServerSetupTitle
            //
            this.lblLayerServerSetupTitle.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerServerSetupTitle.AutoSize = true;
            this.lblLayerServerSetupTitle.Font = new System.Drawing.Font("Calibri", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLayerServerSetupTitle.Location = new System.Drawing.Point(43, 23);
            this.lblLayerServerSetupTitle.Name = "lblLayerServerSetupTitle";
            this.lblLayerServerSetupTitle.Size = new System.Drawing.Size(109, 15);
            this.lblLayerServerSetupTitle.TabIndex = 22;
            this.lblLayerServerSetupTitle.Text = "Layer server setup";
            //
            // panel1
            //
            this.panel1.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel1.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel1.Location = new System.Drawing.Point(48, 33);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(577, 1);
            this.panel1.TabIndex = 25;
            //
            // lnkWhatIsPRoConLayer
            //
            this.lnkWhatIsPRoConLayer.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkWhatIsPRoConLayer.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lnkWhatIsPRoConLayer.AutoSize = true;
            this.lnkWhatIsPRoConLayer.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkWhatIsPRoConLayer.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkWhatIsPRoConLayer.Location = new System.Drawing.Point(63, 200);
            this.lnkWhatIsPRoConLayer.Name = "lnkWhatIsPRoConLayer";
            this.lnkWhatIsPRoConLayer.Size = new System.Drawing.Size(134, 15);
            this.lnkWhatIsPRoConLayer.TabIndex = 23;
            this.lnkWhatIsPRoConLayer.TabStop = true;
            this.lnkWhatIsPRoConLayer.Text = "What is a PRoCon layer?";
            //
            // lnkStartStopLayer
            //
            this.lnkStartStopLayer.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkStartStopLayer.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lnkStartStopLayer.AutoSize = true;
            this.lnkStartStopLayer.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkStartStopLayer.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkStartStopLayer.Location = new System.Drawing.Point(119, 59);
            this.lnkStartStopLayer.Name = "lnkStartStopLayer";
            this.lnkStartStopLayer.Size = new System.Drawing.Size(157, 15);
            this.lnkStartStopLayer.TabIndex = 21;
            this.lnkStartStopLayer.TabStop = true;
            this.lnkStartStopLayer.Text = "Turn PRoCon layer server on";
            //
            // picLayerServerStatus
            //
            this.picLayerServerStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picLayerServerStatus.Enabled = false;
            this.picLayerServerStatus.Location = new System.Drawing.Point(68, 59);
            this.picLayerServerStatus.Name = "picLayerServerStatus";
            this.picLayerServerStatus.Size = new System.Drawing.Size(37, 37);
            this.picLayerServerStatus.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLayerServerStatus.TabIndex = 26;
            this.picLayerServerStatus.TabStop = false;
            //
            // lblLayerServerStatus
            //
            this.lblLayerServerStatus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerServerStatus.ForeColor = System.Drawing.Color.Maroon;
            this.lblLayerServerStatus.Location = new System.Drawing.Point(119, 81);
            this.lblLayerServerStatus.Name = "lblLayerServerStatus";
            this.lblLayerServerStatus.Size = new System.Drawing.Size(499, 80);
            this.lblLayerServerStatus.TabIndex = 24;
            this.lblLayerServerStatus.Text = "Server is offline";
            //
            // picEditAccount
            //
            this.picEditAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.picEditAccount.Location = new System.Drawing.Point(326, 383);
            this.picEditAccount.Name = "picEditAccount";
            this.picEditAccount.Size = new System.Drawing.Size(16, 16);
            this.picEditAccount.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picEditAccount.TabIndex = 39;
            this.picEditAccount.TabStop = false;
            //
            // picDeleteAccount
            //
            this.picDeleteAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.picDeleteAccount.Location = new System.Drawing.Point(502, 383);
            this.picDeleteAccount.Name = "picDeleteAccount";
            this.picDeleteAccount.Size = new System.Drawing.Size(16, 16);
            this.picDeleteAccount.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picDeleteAccount.TabIndex = 38;
            this.picDeleteAccount.TabStop = false;
            //
            // picCreateAccount
            //
            this.picCreateAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.picCreateAccount.Location = new System.Drawing.Point(296, 531);
            this.picCreateAccount.Name = "picCreateAccount";
            this.picCreateAccount.Size = new System.Drawing.Size(16, 16);
            this.picCreateAccount.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picCreateAccount.TabIndex = 37;
            this.picCreateAccount.TabStop = false;
            //
            // picChangeAccountPassword
            //
            this.picChangeAccountPassword.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.picChangeAccountPassword.Location = new System.Drawing.Point(296, 559);
            this.picChangeAccountPassword.Name = "picChangeAccountPassword";
            this.picChangeAccountPassword.Size = new System.Drawing.Size(16, 16);
            this.picChangeAccountPassword.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picChangeAccountPassword.TabIndex = 36;
            this.picChangeAccountPassword.TabStop = false;
            //
            // lnkChangeAccountPassword
            //
            this.lnkChangeAccountPassword.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkChangeAccountPassword.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lnkChangeAccountPassword.Enabled = false;
            this.lnkChangeAccountPassword.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkChangeAccountPassword.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkChangeAccountPassword.Location = new System.Drawing.Point(69, 559);
            this.lnkChangeAccountPassword.Name = "lnkChangeAccountPassword";
            this.lnkChangeAccountPassword.Size = new System.Drawing.Size(221, 15);
            this.lnkChangeAccountPassword.TabIndex = 35;
            this.lnkChangeAccountPassword.TabStop = true;
            this.lnkChangeAccountPassword.Text = "Change Phogues Password";
            this.lnkChangeAccountPassword.TextAlign = System.Drawing.ContentAlignment.TopRight;
            this.lnkChangeAccountPassword.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkChangeAccountPassword_LinkClicked);
            //
            // lnkCreateAccount
            //
            this.lnkCreateAccount.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkCreateAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lnkCreateAccount.Enabled = false;
            this.lnkCreateAccount.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkCreateAccount.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkCreateAccount.Location = new System.Drawing.Point(70, 532);
            this.lnkCreateAccount.Name = "lnkCreateAccount";
            this.lnkCreateAccount.Size = new System.Drawing.Size(220, 15);
            this.lnkCreateAccount.TabIndex = 34;
            this.lnkCreateAccount.TabStop = true;
            this.lnkCreateAccount.Text = "Create account";
            this.lnkCreateAccount.TextAlign = System.Drawing.ContentAlignment.TopRight;
            this.lnkCreateAccount.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkCreateAccount_LinkClicked);
            //
            // lnkEditAccount
            //
            this.lnkEditAccount.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkEditAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lnkEditAccount.AutoSize = true;
            this.lnkEditAccount.Enabled = false;
            this.lnkEditAccount.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkEditAccount.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkEditAccount.Location = new System.Drawing.Point(348, 384);
            this.lnkEditAccount.Name = "lnkEditAccount";
            this.lnkEditAccount.Size = new System.Drawing.Size(73, 15);
            this.lnkEditAccount.TabIndex = 33;
            this.lnkEditAccount.TabStop = true;
            this.lnkEditAccount.Text = "Edit account";
            this.lnkEditAccount.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkEditAccount_LinkClicked);
            //
            // lnkDeleteAccount
            //
            this.lnkDeleteAccount.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkDeleteAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lnkDeleteAccount.AutoSize = true;
            this.lnkDeleteAccount.Enabled = false;
            this.lnkDeleteAccount.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkDeleteAccount.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkDeleteAccount.Location = new System.Drawing.Point(524, 384);
            this.lnkDeleteAccount.Name = "lnkDeleteAccount";
            this.lnkDeleteAccount.Size = new System.Drawing.Size(86, 15);
            this.lnkDeleteAccount.TabIndex = 32;
            this.lnkDeleteAccount.TabStop = true;
            this.lnkDeleteAccount.Text = "Delete account";
            this.lnkDeleteAccount.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkDeleteUser_LinkClicked);
            //
            // lblCreateNewAccount
            //
            this.lblCreateNewAccount.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lblCreateNewAccount.AutoSize = true;
            this.lblCreateNewAccount.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblCreateNewAccount.Location = new System.Drawing.Point(35, 412);
            this.lblCreateNewAccount.Name = "lblCreateNewAccount";
            this.lblCreateNewAccount.Size = new System.Drawing.Size(127, 15);
            this.lblCreateNewAccount.TabIndex = 30;
            this.lblCreateNewAccount.Text = "Create a new account";
            //
            // panel4
            //
            this.panel4.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.panel4.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel4.Location = new System.Drawing.Point(40, 422);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(577, 1);
            this.panel4.TabIndex = 31;
            //
            // lblUserNameExistsError
            //
            this.lblUserNameExistsError.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lblUserNameExistsError.AutoSize = true;
            this.lblUserNameExistsError.ForeColor = System.Drawing.Color.Maroon;
            this.lblUserNameExistsError.Location = new System.Drawing.Point(333, 532);
            this.lblUserNameExistsError.Name = "lblUserNameExistsError";
            this.lblUserNameExistsError.Size = new System.Drawing.Size(132, 15);
            this.lblUserNameExistsError.TabIndex = 29;
            this.lblUserNameExistsError.Text = "Username already exists";
            this.lblUserNameExistsError.Visible = false;
            //
            // txtPassword
            //
            this.txtPassword.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.txtPassword.Location = new System.Drawing.Point(70, 505);
            this.txtPassword.Name = "txtPassword";
            this.txtPassword.Size = new System.Drawing.Size(220, 23);
            this.txtPassword.TabIndex = 28;
            this.txtPassword.TextChanged += new System.EventHandler(this.txtPassword_TextChanged);
            //
            // lblPassword
            //
            this.lblPassword.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lblPassword.AutoSize = true;
            this.lblPassword.Location = new System.Drawing.Point(66, 487);
            this.lblPassword.Name = "lblPassword";
            this.lblPassword.Size = new System.Drawing.Size(57, 15);
            this.lblPassword.TabIndex = 27;
            this.lblPassword.Text = "Password";
            //
            // txtUsername
            //
            this.txtUsername.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.txtUsername.Location = new System.Drawing.Point(70, 460);
            this.txtUsername.Name = "txtUsername";
            this.txtUsername.Size = new System.Drawing.Size(220, 23);
            this.txtUsername.TabIndex = 26;
            this.txtUsername.TextChanged += new System.EventHandler(this.txtUsername_TextChanged);
            //
            // lblUsername
            //
            this.lblUsername.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lblUsername.AutoSize = true;
            this.lblUsername.Location = new System.Drawing.Point(66, 439);
            this.lblUsername.Name = "lblUsername";
            this.lblUsername.Size = new System.Drawing.Size(60, 15);
            this.lblUsername.TabIndex = 25;
            this.lblUsername.Text = "Username";
            //
            // lsvLayerAccounts
            //
            this.lsvLayerAccounts.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)));
            this.lsvLayerAccounts.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colAccounts,
            this.colRConAccess,
            this.colPrivileges});
            this.lsvLayerAccounts.FullRowSelect = true;
            this.lsvLayerAccounts.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.lsvLayerAccounts.Location = new System.Drawing.Point(65, 38);
            this.lsvLayerAccounts.MultiSelect = false;
            this.lsvLayerAccounts.Name = "lsvLayerAccounts";
            this.lsvLayerAccounts.Size = new System.Drawing.Size(551, 339);
            this.lsvLayerAccounts.TabIndex = 20;
            this.lsvLayerAccounts.UseCompatibleStateImageBehavior = false;
            this.lsvLayerAccounts.View = System.Windows.Forms.View.Details;
            this.lsvLayerAccounts.SelectedIndexChanged += new System.EventHandler(this.lsvLayerAccounts_SelectedIndexChanged);
            //
            // colAccounts
            //
            this.colAccounts.Tag = "colAccounts";
            this.colAccounts.Text = "Accounts";
            this.colAccounts.Width = 89;
            //
            // colRConAccess
            //
            this.colRConAccess.Text = "RCon Access";
            this.colRConAccess.Width = 89;
            //
            // colPrivileges
            //
            this.colPrivileges.Tag = "colPrivileges";
            this.colPrivileges.Text = "Local Privileges";
            this.colPrivileges.Width = 111;
            //
            // lblLayerAssignAccountPrivilegesTitle
            //
            this.lblLayerAssignAccountPrivilegesTitle.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblLayerAssignAccountPrivilegesTitle.AutoSize = true;
            this.lblLayerAssignAccountPrivilegesTitle.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLayerAssignAccountPrivilegesTitle.Location = new System.Drawing.Point(35, 7);
            this.lblLayerAssignAccountPrivilegesTitle.Name = "lblLayerAssignAccountPrivilegesTitle";
            this.lblLayerAssignAccountPrivilegesTitle.Size = new System.Drawing.Size(169, 15);
            this.lblLayerAssignAccountPrivilegesTitle.TabIndex = 21;
            this.lblLayerAssignAccountPrivilegesTitle.Text = "Accounts on the procon layer";
            //
            // panel2
            //
            this.panel2.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel2.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel2.Location = new System.Drawing.Point(40, 17);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(577, 1);
            this.panel2.TabIndex = 22;
            //
            // pnlAccountPrivileges
            //
            this.pnlAccountPrivileges.Controls.Add(this.uscPrivileges);
            this.pnlAccountPrivileges.Location = new System.Drawing.Point(150, 609);
            this.pnlAccountPrivileges.Name = "pnlAccountPrivileges";
            this.pnlAccountPrivileges.Size = new System.Drawing.Size(661, 597);
            this.pnlAccountPrivileges.TabIndex = 34;
            this.pnlAccountPrivileges.Visible = false;
            //
            // uscPrivileges
            //
            this.uscPrivileges.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscPrivileges.Font = new System.Drawing.Font("Calibri", 9.75F);
            this.uscPrivileges.Location = new System.Drawing.Point(0, 0);
            this.uscPrivileges.Name = "uscPrivileges";
            this.uscPrivileges.Size = new System.Drawing.Size(661, 597);
            this.uscPrivileges.TabIndex = 0;
            //
            // tmrTimeoutCheck
            //
            this.tmrTimeoutCheck.Tick += new System.EventHandler(this.tmrSettingsAnimator_Tick);
            //
            // uscParentLayerControl
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.Controls.Add(this.tbcLayerControl);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscParentLayerControl";
            this.Size = new System.Drawing.Size(1001, 1300);
            this.Load += new System.EventHandler(this.uscParentLayerControl_Load);
            this.tbcLayerControl.ResumeLayout(false);
            this.tabPlugins.ResumeLayout(false);
            this.tabAccounts.ResumeLayout(false);
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
            ((System.ComponentModel.ISupportInitialize)(this.picEditAccount)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picDeleteAccount)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picCreateAccount)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picChangeAccountPassword)).EndInit();
            this.pnlAccountPrivileges.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tbcLayerControl;
        private System.Windows.Forms.TabPage tabPlugins;
        private System.Windows.Forms.TabPage tabAccounts;
        private System.Windows.Forms.Panel pnlMainLayerServer;
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
        private System.Windows.Forms.LinkLabel lnkCreateAccount;
        private System.Windows.Forms.LinkLabel lnkEditAccount;
        private System.Windows.Forms.LinkLabel lnkDeleteAccount;
        private System.Windows.Forms.Label lblCreateNewAccount;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.Label lblUserNameExistsError;
        private System.Windows.Forms.TextBox txtPassword;
        private System.Windows.Forms.Label lblPassword;
        private System.Windows.Forms.TextBox txtUsername;
        private System.Windows.Forms.Label lblUsername;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvLayerAccounts;
        private System.Windows.Forms.ColumnHeader colAccounts;
        private System.Windows.Forms.ColumnHeader colRConAccess;
        private System.Windows.Forms.ColumnHeader colPrivileges;
        private System.Windows.Forms.Label lblLayerAssignAccountPrivilegesTitle;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Panel pnlAccountPrivileges;
        private uscPluginPanel uscPlugins;
        private System.Windows.Forms.LinkLabel lnkChangeAccountPassword;
        private System.Windows.Forms.PictureBox picEditAccount;
        private System.Windows.Forms.PictureBox picDeleteAccount;
        private System.Windows.Forms.PictureBox picCreateAccount;
        private System.Windows.Forms.PictureBox picChangeAccountPassword;
        private System.Windows.Forms.Timer tmrTimeoutCheck;
        private uscPrivilegesSelection uscPrivileges;
    }*/
}
