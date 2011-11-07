package procon.controls;

public class UscServerConnection {
    /*public partial class uscServerConnection : uscPage {

        private frmMain m_frmParent = null;
        private frmManageAccounts m_frmAccounts = null;
        private PRoConClient m_prcConnection = null;
        private CLocalization m_clocLanguage = null;
        private CPrivileges m_cpPrivileges;

        private PRoConApplication m_praApplication;

        private ListViewColumnSorter m_lvwColumnSorter = new ListViewColumnSorter();

        private TabPage m_tabParentLayerControl;
        private uscParentLayerControl m_uscParentLayerControl;

        private string[] ma_timeDescriptionsShort = new string[] { "y ", "y ", "M ", "M ", "w ", "w ", "d ", "d ", "h ", "h ", "m ", "m ", "s ", "s " };

        #region Delegates

        delegate void DispatchEventDelegate();
        delegate void DispatchExceptionDelegate(Exception eError);
        delegate void DispatchServerInfoDelegate(CServerInfo csiServerDetails);
        delegate void DispatchPlayerInfoListDelegate(List<CPlayerInfo> lstPlayers);
        delegate void DispatchPunkbusterInfoDelegate(CPunkbusterInfo pbInfo);
        delegate void DispatchStringListDelegate(List<string> lstCollection);
        delegate void DispatchConsoleOutputEventCallback(string strConsoleOutput);
        delegate void DispatchBanInfoDelegate(CBanInfo cbiBan);
        delegate void DispatchBanInfoCollectionDelegate(List<CBanInfo> cbiBans);
        delegate void DispatchPrivilegesInfoDelegate(CPrivileges spPrivs);

        delegate void SetLoadedPluginsCallback(List<string> lstClassNames);
        delegate void SetPluginVariablesCallback(string strClassName, string strScriptName, Dictionary<string, string[]> dicSvVariables);

        delegate void DispatchStringEventDelegate(string strValue);

        #endregion

        public PRoConClient Client {
            get { return this.m_prcConnection; }
        }

        public int ConnectingFrame {
            get;
            set;
        }

        private void uscServerConnection_Resize(object sender, EventArgs e) {
            this.Refresh();
        }

        public uscServerConnection(PRoConApplication paProcon, PRoConClient prcConnection, frmMain frmParent, frmManageAccounts frmAccounts) {
        //public uscServerConnection(PRoConApplication paProcon, ProConClient prcConnection, frmMain frmParent, frmManageAccounts frmAccounts, uscServerPlayerTreeview uscServerPlayerTree, string strHost, UInt16 iu16Port, string strUsername, string strPassword) {

            InitializeComponent();

            this.m_praApplication = paProcon;
            this.m_prcConnection = prcConnection;
            this.m_prcConnection.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcConnection_GameTypeDiscovered);

            this.SetStyle(ControlStyles.UserPaint, true);
            this.SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.DoubleBuffer, true);

            this.m_cpPrivileges = new CPrivileges(CPrivileges.FullPrivilegesFlags);

            this.m_frmParent = frmParent;
            this.m_frmAccounts = frmAccounts;

            this.tbcClientTabs.ImageList = this.m_frmParent.iglIcons;

            this.uscLogin.BackgroundHostPort = prcConnection.HostNamePort;

            if (prcConnection.State != ConnectionState.Connected) {
                this.uscLogin.Dock = DockStyle.Fill;
                this.uscLogin.Show();
            }
            else {
                this.uscLogin.Hide();
            }

            this.uscLists.OnTabChange += new OnTabChangeDelegate(uscLists_OnTabChange);

            this.tabPlayerList.ImageKey = "mouse.png";
            this.tabLists.ImageKey = "table.png";
            this.tabChat.ImageKey = "comments.png";
            this.tabEvents.ImageKey = "flag_blue.png";
            this.tabMapView.ImageKey = "map-pin.png";
            this.tabServerSettings.ImageKey = "server_edit.png";
            this.tabPlugins.ImageKey = "plugin.png";
            this.tabAccounts.ImageKey = "vcard.png";
            this.tabConsole.ImageKey = "application_xp_terminal.png";

            #region Map Controls

            this.SettingLoading = this.m_frmParent.picAjaxStyleLoading.Image;
            this.SettingSuccess = this.m_frmParent.picAjaxStyleSuccess.Image;
            this.SettingFail = this.m_frmParent.picAjaxStyleFail.Image;

            this.btnRestartRound.Image = this.m_frmParent.iglIcons.Images["arrow-retweet.png"];
            this.btnNextRound.Image = this.m_frmParent.iglIcons.Images["arrow-step-over.png"];

            this.AsyncSettingControls.Add("admin.runNextRound", new AsyncStyleSetting(this.picNextRound, null, new Control[] { this.btnNextRound }, true));
            this.AsyncSettingControls.Add("admin.restartRound", new AsyncStyleSetting(this.picRestartRound, null, new Control[] { this.btnRestartRound }, true));

            #endregion

            this.uscPlugins.GetPluginDetails += new uscPluginPanel.GetPluginDetailsDelegate(uscPlugins_GetPluginDetails);
            this.uscPlugins.SetPluginVariable += new uscPluginPanel.SetPluginVariableDelegate(uscPlugins_SetPluginVariable);
            this.uscPlugins.PluginEnabled += new uscPluginPanel.PluginEnabledDelegate(uscPlugins_PluginEnabled);
            this.uscPlugins.PluginLoaded += new uscPluginPanel.PluginEventDelegate(uscPlugins_PluginLoaded);
            this.uscPlugins.PluginVariablesAltered += new uscPluginPanel.PluginEventDelegate(uscPlugins_PluginVariablesAltered);
            this.uscPlugins.ReloadPlugins += new uscPluginPanel.EventDelegate(uscPlugins_ReloadPlugins);
            this.uscPlugins.OnTabChange += new OnTabChangeDelegate(uscPlugins_OnTabChange);

            this.uscAccounts.ManageAccountsRequest += new uscAccountsPanel.ManageAccountsRequestDelegate(uscAccounts_ManageAccountsRequest);

            this.uscServerConsole.SendCommand += new uscConsolePanel.SendCommandDelegate(uscServerConsole_SendCommand);
            this.uscServerConsole.SendListCommand += new uscConsolePanel.SendListCommandDelegate(uscServerConsole_SendListCommand);
            this.uscServerConsole.OnTabChange += new OnTabChangeDelegate(uscServerConsole_OnTabChange);

            this.m_tabParentLayerControl = new TabPage("Parent Layer Control");
            this.m_tabParentLayerControl.Name = "tabLayerControl";
            this.m_tabParentLayerControl.Padding = new Padding(8);
            this.m_tabParentLayerControl.UseVisualStyleBackColor = true;

            this.m_uscParentLayerControl = new uscParentLayerControl();
            this.m_uscParentLayerControl.Dock = DockStyle.Fill;
            this.m_uscParentLayerControl.BackColor = Color.Transparent;
            //this.m_uscParentLayerControl.SendCommand += new uscParentLayerControl.SendCommandDelegate(m_uscParentLayerControl_SendCommand);
            this.m_uscParentLayerControl.Initialize(this.m_frmParent, this);
            this.m_tabParentLayerControl.Controls.Add(m_uscParentLayerControl);
            this.m_uscParentLayerControl.OnTabChange += new OnTabChangeDelegate(m_uscParentLayerControl_OnTabChange);

            this.uscPlugins.Initialize(this.m_frmParent, this);
            this.uscPlugins.SetConnection(this.m_prcConnection);
            this.uscLogin.Initalize(this.m_frmParent, this);
            this.uscLogin.SetConnection(this.m_prcConnection);
            this.uscLogin.SetLocalization(this.m_prcConnection.Language);

            this.uscMap.SetConnection(this.m_prcConnection);
            this.uscEvents.SetConnection(this.m_prcConnection);
            this.uscLists.SetConnection(this.m_prcConnection);
            this.uscSettings.SetConnection(this.m_prcConnection);
            this.uscServerConsole.SetConnection(this.m_prcConnection);
            this.uscChat.SetConnection(this.m_prcConnection);
            this.uscPlayers.SetConnection(this.m_prcConnection);
            this.m_uscParentLayerControl.SetConnection(this.m_prcConnection);

            this.uscAccounts.SetConnection(this.m_praApplication, this.m_prcConnection);
        }

        void m_prcConnection_GameTypeDiscovered(PRoConClient sender) {

            this.uscPlayers.Initialize(this.m_frmParent, this);
            this.uscLists.Initialize(this.m_frmParent, this);
            this.uscChat.Initialize(this);
            this.uscEvents.Initalize(this.m_frmParent, this);
            this.uscMap.Initalize(this.m_frmParent);
            this.uscSettings.Initialize(this.m_frmParent);

            this.uscAccounts.Initalize(this.m_frmParent, this);
            this.uscServerConsole.Initialize(this.m_frmParent, this);

            //this.m_prcConnection = new ProConClient(paProcon, strHost, iu16Port, strUsername, strPassword);
            //paProcon.Connections.Add(this.m_prcConnection);

            this.m_prcConnection.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcConnection_ProconPrivileges);
            this.m_prcConnection.ProconVersion += new PRoConClient.ProconVersionHandler(m_prcConnection_ProconVersion);

            this.m_prcConnection.PluginConsole.WriteConsole += new PRoCon.Core.Logging.Loggable.WriteConsoleHandler(PluginConsole_WriteConsole);

            this.m_prcConnection.Game.ServerInfo += new FrostbiteClient.ServerInfoHandler(m_prcConnection_ServerInfo);
            this.m_prcConnection.Game.LoadingLevel += new FrostbiteClient.LoadingLevelHandler(m_prcConnection_LoadingLevel);
            this.m_prcConnection.Game.LevelStarted += new FrostbiteClient.EmptyParamterHandler(Game_LevelStarted);

            //this.m_prcConnection.Game.PlayerJoin += new FrostbiteClient.PlayerEventHandler(m_prcConnection_PlayerJoin);
            //this.m_prcConnection.Game.PlayerLeft += new FrostbiteClient.PlayerLeaveHandler(m_prcConnection_PlayerLeft);
            this.m_prcConnection.Game.Version += new FrostbiteClient.VersionHandler(Game_Version);

            this.m_prcConnection.Game.RunNextRound += new FrostbiteClient.EmptyParamterHandler(Game_RunNextLevel);
            this.m_prcConnection.Game.RestartRound += new FrostbiteClient.EmptyParamterHandler(Game_RestartLevel);
            this.m_prcConnection.Game.ResponseError += new FrostbiteClient.ResponseErrorHandler(Game_ResponseError);

            this.m_prcConnection.PluginsCompiled += new PRoConClient.EmptyParamterHandler(m_prcConnection_PluginsCompiled);

            if (this.m_prcConnection.PluginsManager != null) {
                this.m_prcConnection.PluginsManager.PluginVariableAltered += new PluginManager.PluginVariableAlteredHandler(Plugins_PluginVariableAltered);
                this.m_prcConnection.PluginsManager.PluginEnabled += new PluginManager.PluginEmptyParameterHandler(Plugins_PluginEnabled);
                this.m_prcConnection.PluginsManager.PluginDisabled += new PluginManager.PluginEmptyParameterHandler(Plugins_PluginDisabled);
            }

            if (this.m_prcConnection.PluginsManager != null) {
                this.uscPlugins.SetLoadedPlugins(this.m_prcConnection.PluginsManager.Plugins.LoadedClassNames);
                this.uscPlugins.SetEnabledPlugins(this.m_prcConnection.PluginsManager.Plugins.EnabledClassNames);
            }

            if (this.m_prcConnection.PluginConsole != null) {
                foreach (LogEntry leEntry in this.m_prcConnection.PluginConsole.LogEntries) {
                    this.PluginConsole_WriteConsole(leEntry.Logged, leEntry.Text);
                }
            }

            if (this.m_prcConnection.CurrentServerInfo.ServerName.Length > 0) {
                this.m_prcConnection_ServerInfo(this.m_prcConnection.Game, this.m_prcConnection.CurrentServerInfo);
            }

            this.SetLocalization(this.m_prcConnection.Language);

            this.SetVersionInfoLabels(this.m_prcConnection.Game);
        }

        // Minimizing to tray, then maximizing from tray will fire the Load event again.
        //private bool m_blFormLoaded = false;
        private void uscServerConnection_Load(object sender, EventArgs e) {

            if (Program.m_application.OptionsSettings.ShowRoundTimerConstantly) {
                this.lblRoundTime.Visible = true;
            }
            else {
                this.lblRoundTime.Visible = false;
            }

            if (this.m_prcConnection != null) {
                this.m_clocLanguage = this.m_prcConnection.Language;

                //this.lblVersion.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblVersion", new string[] { this.m_prcConnection.VersionNumber });

                if (this.m_prcConnection.PluginsManager != null) {
                    this.uscPlugins.SetLoadedPlugins(this.m_prcConnection.PluginsManager.Plugins.LoadedClassNames);
                    this.uscPlugins.SetEnabledPlugins(this.m_prcConnection.PluginsManager.Plugins.EnabledClassNames);
                }

                if (this.m_prcConnection.PluginConsole != null) {
                    foreach (LogEntry leEntry in new List<LogEntry>(this.m_prcConnection.PluginConsole.LogEntries)) {
                        this.PluginConsole_WriteConsole(leEntry.Logged, leEntry.Text);
                    }
                }

                if (this.m_prcConnection.State != ConnectionState.Connected) {
                    this.uscLogin.Dock = DockStyle.Fill;
                    this.uscLogin.Show();
                }
                else {
                    this.uscLogin.Hide();
                }

                if (this.m_prcConnection.IsPRoConConnection == true) {
                    this.m_prcConnection_ProconPrivileges(this.m_prcConnection, this.m_prcConnection.Privileges);
                }
            }
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            if (this.m_clocLanguage != null) {

                //this.m_prcConnection.SetLocalization(this.m_clocLanguage);

                this.uscLogin.SetLocalization(this.m_clocLanguage);

                this.tabPlayerList.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabPlayerList", null);
                this.uscPlayers.SetLocalization(this.m_clocLanguage);
                this.tabChat.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabChat", null);
                this.uscChat.SetLocalization(this.m_clocLanguage);

                this.uscMap.SetLocalization(this.m_clocLanguage);

                this.tabMapView.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabMapView");
                this.tabEvents.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabEvents", null);
                this.uscEvents.SetLocalization(this.m_clocLanguage);
                this.tabLists.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabLists", null);
                this.uscLists.SetLocalization(this.m_clocLanguage);
                this.tabServerSettings.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabServerSettings", null);
                this.uscSettings.SetLocalization(this.m_clocLanguage);
                this.tabPlugins.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabPlugins", null);
                this.uscPlugins.SetLocalization(this.m_clocLanguage);
                this.tabAccounts.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabAccounts", null);
                this.uscAccounts.SetLocalization(this.m_clocLanguage);
                this.tabConsole.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabConsole", null);
                this.uscServerConsole.SetLocalization(this.m_clocLanguage);

                this.m_tabParentLayerControl.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabParentLayerControl", null);
                this.m_uscParentLayerControl.SetLocalization(this.m_clocLanguage);

                this.toolTipMapControls.SetToolTip(this.btnNextRound, this.m_clocLanguage.GetLocalized("uscServerConnection.btnNextRound.ToolTip"));
                this.toolTipMapControls.SetToolTip(this.btnRestartRound, this.m_clocLanguage.GetLocalized("uscServerConnection.btnRestartRound.ToolTip"));

                this.ma_timeDescriptionsShort[13] = this.m_clocLanguage.GetLocalized("global.Seconds.Short", null);
                this.ma_timeDescriptionsShort[12] = this.m_clocLanguage.GetLocalized("global.Seconds.Short", null);
                this.ma_timeDescriptionsShort[11] = this.m_clocLanguage.GetLocalized("global.Minutes.Short", null) + " ";
                this.ma_timeDescriptionsShort[10] = this.m_clocLanguage.GetLocalized("global.Minutes.Short", null) + " ";
                this.ma_timeDescriptionsShort[9] = this.m_clocLanguage.GetLocalized("global.Hours.Short", null) + " ";
                this.ma_timeDescriptionsShort[8] = this.m_clocLanguage.GetLocalized("global.Hours.Short", null) + " ";
                this.ma_timeDescriptionsShort[7] = this.m_clocLanguage.GetLocalized("global.Days.Short", null) + " ";
                this.ma_timeDescriptionsShort[6] = this.m_clocLanguage.GetLocalized("global.Days.Short", null) + " ";
                this.ma_timeDescriptionsShort[5] = this.m_clocLanguage.GetLocalized("global.Weeks.Short", null) + " ";
                this.ma_timeDescriptionsShort[4] = this.m_clocLanguage.GetLocalized("global.Weeks.Short", null) + " ";
                this.ma_timeDescriptionsShort[3] = this.m_clocLanguage.GetLocalized("global.Months.Short", null) + " ";
                this.ma_timeDescriptionsShort[2] = this.m_clocLanguage.GetLocalized("global.Months.Short", null) + " ";
                this.ma_timeDescriptionsShort[1] = this.m_clocLanguage.GetLocalized("global.Years.Short", null) + " ";
                this.ma_timeDescriptionsShort[0] = this.m_clocLanguage.GetLocalized("global.Years.Short", null) + " ";

                if (this.m_prcConnection.CurrentServerInfo != null) {
                    this.SetServerInfoLabels(this.m_prcConnection.CurrentServerInfo);
                    //this.lblPlayerCount.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblPlayerCount", new string[] { this.m_prcConnection.CurrentServerInfo.PlayerCount.ToString(), this.m_prcConnection.CurrentServerInfo.MaxPlayerCount.ToString() });
                    //this.lblCurrentGameMode.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentGameMode", new string[] {  });
                    //this.lblCurrentMapName.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentMapName", new string[] { this.m_prcConnection.GetFriendlyMapname(this.m_prcConnection.CurrentServerInfo.Map) });

                    //this.lblCurrentMapName.Text = String.Format("{0} - {1}", this.m_prcConnection.GetFriendlyGamemode(this.m_prcConnection.CurrentServerInfo.GameMode), this.m_prcConnection.GetFriendlyMapname(this.m_prcConnection.CurrentServerInfo.Map));
                    //this.lblCurrentRound.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentRound", this.m_prcConnection.CurrentServerInfo.CurrentRound.ToString(), this.m_prcConnection.CurrentServerInfo.TotalRounds.ToString());
                }
                else {
                    //this.lblPlayerCount.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblPlayerCount", new string[] { "", "" });
                    //this.lblCurrentGameMode.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentGameMode", new string[] { "" });
                    //this.lblCurrentMapName.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentMapName", new string[] { "" });
                    this.lblCurrentMapName.Text = String.Empty;
                    this.lblCurrentRound.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentRound", "", "");
                }

                if (this.m_prcConnection.Game != null) {
                    this.SetVersionInfoLabels(this.m_prcConnection.Game);
                }
            }
        }

        #region Tab Changes

        public delegate void OnTabChangeDelegate(object sender, Stack<string> stkTabIndexes);
        public event OnTabChangeDelegate OnTabChange;

        private void tbcClientTabs_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.OnTabChange != null) {
                Stack<string> stkTabIndexes = new Stack<string>();
                stkTabIndexes.Push(tbcClientTabs.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }

            if (this.tbcClientTabs.SelectedTab == this.tabMapView) {
                this.uscMap.IsMapSelected = true;
            }
            else {
                this.uscMap.IsMapSelected = false;
            }

        }

        public void SetTabIndexes(Stack<string> stkTabIndexes) {
            if (tbcClientTabs.TabPages.ContainsKey(stkTabIndexes.Peek()) == true) {
                this.tbcClientTabs.SelectedTab = tbcClientTabs.TabPages[stkTabIndexes.Pop()];
            }

            if (stkTabIndexes.Count > 0) {
                switch (tbcClientTabs.SelectedTab.Name) {
                    case "tabLists":
                        this.uscLists.SetTabIndexes(stkTabIndexes);
                        break;
                    case "tabPlugins":
                        this.uscPlugins.SetTabIndexes(stkTabIndexes);
                        break;
                    case "tabConsole":
                        this.uscServerConsole.SetTabIndexes(stkTabIndexes);
                        break;
                    case "tabLayerControl":
                        this.m_uscParentLayerControl.SetTabIndexes(stkTabIndexes);
                        break;
                }
            }
        }

        void uscServerConsole_OnTabChange(object sender, Stack<string> stkTabIndexes) {
            if (this.OnTabChange != null) {
                stkTabIndexes.Push(tbcClientTabs.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        void m_uscParentLayerControl_OnTabChange(object sender, Stack<string> stkTabIndexes) {
            if (this.OnTabChange != null) {
                stkTabIndexes.Push(tbcClientTabs.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        void uscPlugins_OnTabChange(object sender, Stack<string> stkTabIndexes) {
            if (this.OnTabChange != null) {
                stkTabIndexes.Push(tbcClientTabs.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        void uscLists_OnTabChange(object sender, Stack<string> stkTabIndexes) {
            if (this.OnTabChange != null) {
                stkTabIndexes.Push(tbcClientTabs.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        #endregion

        #region Connection and Login Events

        #region ServerInfo updates and simulators

        private void m_prcConnection_ServerInfo(FrostbiteClient sender, CServerInfo csiServerInfo) {
            this.SetServerInfoLabels(csiServerInfo);

            this.SetVersionInfoLabels(sender);
        }

        private void SetServerInfoLabels(CServerInfo csiServerInfo) {
            //this.lblServerName.Text = String.Format("{0} [{1}]", csiServerInfo.ServerName, this.m_prcConnection.HostNamePort);
            this.uscAccounts.ServerName = csiServerInfo.ServerName;
            this.lblCurrentMapName.Text = String.Format("{0} - {1}", this.m_prcConnection.GetFriendlyGamemode(csiServerInfo.GameMode), this.m_prcConnection.GetFriendlyMapname(csiServerInfo.Map));
            this.toolTipMapControls.SetToolTip(this.lblCurrentMapName, csiServerInfo.Map);
            this.lblCurrentRound.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblCurrentRound", csiServerInfo.CurrentRound.ToString(), csiServerInfo.TotalRounds.ToString());

            this.lblMappack.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblMappack", csiServerInfo.Mappack.ToString());

            if (csiServerInfo.ConnectionState.Length > 0) {

                this.lblPlasmaStatus.Text = this.m_clocLanguage.GetLocalized(String.Format("uscServerConnection.lblPlasmaStatus.{0}", csiServerInfo.ConnectionState));
                // this.toolTipPlasma.SetToolTip(this.lblPlasmaStatus, this.m_clocLanguage.GetLocalized(String.Format("uscServerConnection.lblPlasmaStatus.{0}.ToolTip", csiServerInfo.ConnectionState)));
                // &#xa; or Environment.NewLine
                if (csiServerInfo.GameMod == GameMods.BC2 || csiServerInfo.GameMod == GameMods.VIETNAM) {
                    this.toolTipPlasma.SetToolTip(this.lblPlasmaStatus,
                        this.m_clocLanguage.GetLocalized(String.Format("uscServerConnection.lblPlasmaStatus.{0}.ToolTip", csiServerInfo.ConnectionState))
                        + Environment.NewLine + Environment.NewLine +
                        this.m_clocLanguage.GetLocalized("uscServerConnection.extServerInfo.ExternalGameIpandPort.ToolTip") + "\t" + csiServerInfo.ExternalGameIpandPort
                        + Environment.NewLine +
                        this.m_clocLanguage.GetLocalized("uscServerConnection.extServerInfo.JoinQueueEnabled.ToolTip") + "\t"
                            + this.m_clocLanguage.GetLocalized(String.Format("uscServerConnection.extServerInfo.JoinQueueEnabled.{0}.ToolTip", csiServerInfo.JoinQueueEnabled))
                        + Environment.NewLine +
                        this.m_clocLanguage.GetLocalized("uscServerConnection.extServerInfo.ServerRegion.ToolTip") + "\t\t"
                            + this.m_clocLanguage.GetLocalized(String.Format("uscServerConnection.extServerInfo.ServerRegion.{0}.ToolTip", csiServerInfo.ServerRegion))
                        + Environment.NewLine +
                        this.m_clocLanguage.GetLocalized("uscServerConnection.extServerInfo.PunkBusterVersion.ToolTip") + "\t" + csiServerInfo.PunkBusterVersion
                        + Environment.NewLine
                        + Environment.NewLine
                    );
                } else {
                    this.toolTipPlasma.SetToolTip(this.lblPlasmaStatus, this.m_clocLanguage.GetLocalized(String.Format("uscServerConnection.lblPlasmaStatus.{0}.ToolTip", csiServerInfo.ConnectionState)));
                }

                switch (csiServerInfo.ConnectionState) {
                    case "NotConnected":
                        this.lblPlasmaStatus.ForeColor = Color.Maroon;
                        break;
                    case "ConnectedToBackend":
                        this.lblPlasmaStatus.ForeColor = Color.Gold;
                        break;
                    case "AcceptingPlayers":
                        this.lblPlasmaStatus.ForeColor = Color.MediumSeaGreen;
                        break;
                    default: break;
                }
            }
        }

        private void m_prcConnection_LoadingLevel(FrostbiteClient sender, string mapFileName, int roundsPlayed, int roundsTotal) {
            if (String.Compare(this.Client.GameType, "MOH", true) == 0) {
                this.SetServerInfoLabels(new CServerInfo(this.m_prcConnection.CurrentServerInfo.ServerName,
                                                        mapFileName,
                                                        this.m_prcConnection.GetPlaylistByMapname(mapFileName),
                                                        this.m_prcConnection.CurrentServerInfo.PlayerCount,
                                                        this.m_prcConnection.CurrentServerInfo.MaxPlayerCount,
                                                        roundsPlayed + 1,
                                                        this.m_prcConnection.CurrentServerInfo.TotalRounds,
                                                        this.m_prcConnection.CurrentServerInfo.TeamScores,
                                                        this.m_prcConnection.CurrentServerInfo.ConnectionState));
            }
            else {
                this.SetServerInfoLabels(new CServerInfo(this.m_prcConnection.CurrentServerInfo.ServerName,
                                                        mapFileName,
                                                        this.m_prcConnection.GetPlaylistByMapname(mapFileName),
                                                        this.m_prcConnection.CurrentServerInfo.PlayerCount,
                                                        this.m_prcConnection.CurrentServerInfo.MaxPlayerCount,
                                                        roundsPlayed,
                                                        this.m_prcConnection.CurrentServerInfo.TotalRounds,
                                                        this.m_prcConnection.CurrentServerInfo.TeamScores,
                                                        this.m_prcConnection.CurrentServerInfo.ConnectionState));
            }

        }

        private void Game_LevelStarted(FrostbiteClient sender) {
            sender.SendServerinfoPacket();
        }

        #endregion

        private void SetVersionInfoLabels(FrostbiteClient sender) {

            string version = sender.VersionNumber;

            if (sender.FriendlyVersionNumber.Length > 0) {
                version = String.Format("{0} ({1})", sender.VersionNumber, sender.FriendlyVersionNumber);
            }

            this.lblVersion.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.lblVersion", version);
        }

        private void Game_Version(FrostbiteClient sender, string serverType, string serverVersion) {
            this.SetVersionInfoLabels(sender);
        }

        private void m_prcConnection_ProconVersion(PRoConClient sender, Version version) {

            this.lblLayerVersion.Text = version.ToString();
            this.lblLayerVersion.Visible =  true;

            if (sender.ConnectedLayerVersion != null) {

                Version assemblyVersion = System.Reflection.Assembly.GetExecutingAssembly().GetName().Version;
                int comparedVersions = assemblyVersion.CompareTo(sender.ConnectedLayerVersion);

                if (comparedVersions > 0) {
                    // Older.
                    this.lblLayerVersion.ForeColor = Color.Maroon;
                    this.toolTipPlasma.SetToolTip(this.lblLayerVersion, this.m_clocLanguage.GetLocalized("uscServerConnection.lblLayerVersion.Older.ToolTip"));
                }
                else if (comparedVersions < 0) {
                    // Newer.
                    this.lblLayerVersion.ForeColor = Color.Maroon;
                    this.toolTipPlasma.SetToolTip(this.lblLayerVersion, this.m_clocLanguage.GetLocalized("uscServerConnection.lblLayerVersion.Newer.ToolTip"));
                }
                else {
                    // Same.
                    this.lblLayerVersion.ForeColor = Color.MediumSeaGreen;
                    this.toolTipPlasma.SetToolTip(this.lblLayerVersion, this.m_clocLanguage.GetLocalized("uscServerConnection.lblLayerVersion.Same.ToolTip"));
                }
            }
        }

        private void m_prcConnection_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {
            if (spPrivs.CanIssueLimitedProconPluginCommands == true && this.tbcClientTabs.TabPages.Contains(this.m_tabParentLayerControl) == false) {
                this.tbcClientTabs.TabPages.Add(this.m_tabParentLayerControl);
                this.m_tabParentLayerControl.ImageKey = "sitemap_color.png";

                *//*
                this.m_prcConnection.SendCommand(new List<string> { "procon.account.listAccounts" });
                this.m_prcConnection.SendCommand(new List<string> { "procon.account.listLoggedIn" });

                this.m_prcConnection.SendCommand(new List<string> { "procon.plugin.listLoaded" });
                this.m_prcConnection.SendCommand(new List<string> { "procon.plugin.listEnabled" });
                *//*
            }
            else if (spPrivs.CanIssueLimitedProconCommands == false && this.tbcClientTabs.TabPages.Contains(this.m_tabParentLayerControl) == true) {
                this.tbcClientTabs.TabPages.Remove(this.m_tabParentLayerControl);
            }

            if (this.m_praApplication.OptionsSettings.LayerHideLocalPlugins == true) {
                this.tbcClientTabs.TabPages.Remove(this.tabPlugins);
                // this.tabPlugins.Hide();
            }

            if (this.m_praApplication.OptionsSettings.LayerHideLocalAccounts == true) {
                this.tbcClientTabs.TabPages.Remove(this.tabAccounts);
                // this.tabAccounts.Hide();
            }

            this.pnlMapControls.Visible = spPrivs.CanUseMapFunctions;

            this.m_prcConnection.SendGetProconVarsPacket("TEMP_BAN_CEILING");
            //this.m_prcConnection.SendRequest(new List<string> { "procon.vars", "TEMP_BAN_CEILING" });
        }

        #endregion

        #region Player List

        *//*
        private void m_prcConnection_PlayerJoin(FrostbiteClient sender, string playerName) {
            this.SetServerInfoLabels(new CServerInfo(this.m_prcConnection.CurrentServerInfo.ServerName,
                                                    this.m_prcConnection.CurrentServerInfo.Map,
                                                    this.m_prcConnection.CurrentServerInfo.GameMode,
                                                    this.m_prcConnection.PlayerList.Count,
                                                    this.m_prcConnection.CurrentServerInfo.MaxPlayerCount,
                                                    this.m_prcConnection.CurrentServerInfo.CurrentRound,
                                                    this.m_prcConnection.CurrentServerInfo.TotalRounds));
        }

        private void m_prcConnection_PlayerLeft(FrostbiteClient sender, string playerName, CPlayerInfo cpiPlayer) {
            this.SetServerInfoLabels(new CServerInfo(this.m_prcConnection.CurrentServerInfo.ServerName,
                                                    this.m_prcConnection.CurrentServerInfo.Map,
                                                    this.m_prcConnection.CurrentServerInfo.GameMode,
                                                    this.m_prcConnection.PlayerList.Count,
                                                    this.m_prcConnection.CurrentServerInfo.MaxPlayerCount,
                                                    this.m_prcConnection.CurrentServerInfo.CurrentRound,
                                                    this.m_prcConnection.CurrentServerInfo.TotalRounds));
        }
        *//*
        public void PlayerSelectionChange(string strSoldierName) {
            this.uscChat.PlayerSelectionChange(strSoldierName);
            this.uscPlayers.PlayerSelectionChange(strSoldierName);
        }

        public bool BeginDragDrop() {

            bool blBeginSuccess = false;

            if (this.m_cpPrivileges.CanMovePlayers == true) {
                //this.m_uscServerPlayerTree.BeginDragDrop(this);
                this.uscPlayers.BeginDragDrop();

                blBeginSuccess = true;
            }

            return blBeginSuccess;
        }

        public void EndDragDrop() {
            //this.m_uscServerPlayerTree.EndDragDrop(this);
            this.uscPlayers.EndDragDrop();
        }

        #endregion

        #region Plugins

        private bool m_blUpdatingPlugins = false;

        private void uscPlugins_ReloadPlugins() {
            this.m_prcConnection.CompilePlugins(this.m_praApplication.OptionsSettings.PluginPermissions);
        }

        private void m_prcConnection_PluginsCompiled(PRoConClient sender) {
            this.uscPlugins.SetLoadedPlugins(this.m_prcConnection.PluginsManager.Plugins.LoadedClassNames);
            this.m_prcConnection.PluginsManager.PluginVariableAltered += new PluginManager.PluginVariableAlteredHandler(Plugins_PluginVariableAltered);
            this.m_prcConnection.PluginsManager.PluginEnabled += new PluginManager.PluginEmptyParameterHandler(Plugins_PluginEnabled);
            this.m_prcConnection.PluginsManager.PluginDisabled += new PluginManager.PluginEmptyParameterHandler(Plugins_PluginDisabled);
        }

        private void Plugins_PluginVariableAltered(PluginDetails spdNewDetails) {
            this.uscPlugins.RefreshPlugin();
        }

        private void uscPlugins_PluginVariablesAltered(PluginDetails spdPlugin) {

        }

        private void uscPlugins_PluginLoaded(PluginDetails spdPlugin) {

        }

        private void Plugins_PluginDisabled(string strClassName) {
            this.m_blUpdatingPlugins = true;

            if (this.uscPlugins.LoadedPlugins.ContainsKey(strClassName) == true) {
                this.uscPlugins.LoadedPlugins[strClassName].Checked = false;
            }

            this.m_blUpdatingPlugins = false;
        }

        private void Plugins_PluginEnabled(string strClassName) {
            this.m_blUpdatingPlugins = true;

            if (this.uscPlugins.LoadedPlugins.ContainsKey(strClassName) == true) {
                this.uscPlugins.LoadedPlugins[strClassName].Checked = true;
            }

            this.m_blUpdatingPlugins = false;
        }

        private void uscPlugins_PluginEnabled(string strClassName, bool blEnabled) {

            if (this.m_blUpdatingPlugins == false) {

                if (blEnabled == true) {
                    this.m_prcConnection.PluginsManager.EnablePlugin(strClassName);
                }
                else {
                    this.m_prcConnection.PluginsManager.DisablePlugin(strClassName);
                }

                this.uscPlugins.RefreshPlugin();
            }
        }

        private void uscPlugins_SetPluginVariable(string strClassName, string strVariable, string strValue) {
            this.m_prcConnection.PluginsManager.SetPluginVariable(strClassName, strVariable, strValue);
        }

        private PluginDetails uscPlugins_GetPluginDetails(string strClassName) {
            PluginDetails spdReturn = new PluginDetails();
            spdReturn.ClassName = strClassName;
            spdReturn.Name = strClassName;

            if (this.m_prcConnection.PluginsManager != null) {
                spdReturn = this.m_prcConnection.PluginsManager.GetPluginDetails(strClassName);
            }

            return spdReturn;
        }

        private void PluginConsole_WriteConsole(DateTime dtLoggedTime, string strLoggedText) {
            this.uscPlugins.Write(dtLoggedTime, strLoggedText);
        }

        #endregion

        #region PRoCon Layer and accounts

        public delegate void ManageAccountsRequestDelegate(object sender, EventArgs e);
        public event ManageAccountsRequestDelegate ManageAccountsRequest;

        void uscAccounts_ManageAccountsRequest(object sender, EventArgs e) {
            this.ManageAccountsRequest(this, e);
        }

        #endregion

        #region Console

        // Redesign note; Leaving these two here instead of putting in ServerConsole.cs until ExecuteCommand is in PRoConClient.cs
        void uscServerConsole_SendListCommand(List<string> lstCommand) {
            this.m_prcConnection.SendRequest(lstCommand);
        }

        void uscServerConsole_SendCommand(string strCommand) {
            if (strCommand.Length > 0) {
                List<string> lstWords = Packet.Wordify(strCommand);

                if (lstWords.Count >= 1 && lstWords[0].Length >= 1 && lstWords[0][0] == '/') {
                    lstWords[0] = lstWords[0].Remove(0, 1);
                    this.m_praApplication.ExecutePRoConCommand(this.m_prcConnection, lstWords, 0);
                    //this.ExecutePRoConCommand(lstWords);
                }
                else if (lstWords.Count >= 2 && String.Compare(lstWords[0], "punkBuster.pb_sv_command", true) == 0) {
                    lstWords.Clear();

                    lstWords.Add("punkBuster.pb_sv_command");
                    lstWords.Add(Regex.Replace(strCommand, "^punkbuster.pb_sv_command ", "", RegexOptions.IgnoreCase));

                    this.m_prcConnection.SendRequest(lstWords);
                }
                else {
                    this.m_prcConnection.SendRequest(lstWords);
                }
            }
        }

        #endregion

        #region Map Controls

        private void Game_RestartLevel(FrostbiteClient sender) {
            this.OnSettingResponse("admin.restartRound", true);
        }

        private void btnRestartRound_Click(object sender, EventArgs e) {
            if (this.m_prcConnection != null && this.m_prcConnection.Game != null) {
                this.WaitForSettingResponse("admin.restartRound");
                this.m_prcConnection.Game.SendAdminRestartRoundPacket();
                //this.m_prcConnection.Game.SendServerinfoPacket();
            }
        }

        private void Game_RunNextLevel(FrostbiteClient sender) {
            this.OnSettingResponse("admin.runNextRound", true);
        }

        private void btnNextRound_Click(object sender, EventArgs e) {
            if (this.m_prcConnection != null && this.m_prcConnection.Game != null) {
                this.WaitForSettingResponse("admin.runNextRound");
                this.m_prcConnection.Game.SendAdminRunNextRoundPacket();
                //this.m_prcConnection.Game.SendServerinfoPacket();
            }
        }

        private void Game_ResponseError(FrostbiteClient sender, Packet originalRequest, string errorMessage) {
            if (originalRequest.Words.Count >= 1) {
                this.OnSettingResponse(originalRequest.Words[0].ToLower(), null, false);
            }
        }

        #endregion

        private void tmrTimerTicks_Tick(object sender, EventArgs e) {

            if (this.m_prcConnection != null) {
                if (this.m_prcConnection.CurrentServerInfo != null) {

                    if (this.m_prcConnection.CurrentServerInfo.RoundTime >= 0) {
                        this.lblRoundTime.Text = uscPlayerPunishPanel.SecondsToText((UInt32)(this.m_prcConnection.CurrentServerInfo.RoundTime++), this.ma_timeDescriptionsShort);
                    }

                    if ((this.lblServerUptime.Visible = (this.m_prcConnection.CurrentServerInfo.ServerUptime >= 0) == true)) {
                        string uptimeText = uscPlayerPunishPanel.SecondsToText((UInt32)(this.m_prcConnection.CurrentServerInfo.ServerUptime++), this.ma_timeDescriptionsShort);
                        this.lblServerUptime.Text = this.m_clocLanguage.GetDefaultLocalized("Uptime: " + uptimeText, "uscServerConnection.lblServerUptime", uptimeText);
                    }
                }
            }
        }

        private void lblCurrentRound_MouseEnter(object sender, EventArgs e) {
            if (this.m_prcConnection != null) {
                if (this.m_prcConnection.CurrentServerInfo != null && this.m_prcConnection.CurrentServerInfo.RoundTime >= 0) {
                    this.lblRoundTime.Visible = true;
                    this.lblCurrentRound.Font = new Font(this.lblCurrentRound.Font, FontStyle.Bold);
                }
            }
        }

        private void lblCurrentRound_MouseLeave(object sender, EventArgs e) {
            if (this.m_prcConnection != null) {
                if (this.m_prcConnection.CurrentServerInfo != null && this.m_prcConnection.CurrentServerInfo.RoundTime >= 0) {
                    if (!Program.m_application.OptionsSettings.ShowRoundTimerConstantly)
                    {
                        this.lblRoundTime.Visible = false;
                    }
                    this.lblCurrentRound.Font = new Font(this.lblCurrentRound.Font, FontStyle.Regular);
                }
            }
        }
    }*/


    /*partial class uscServerConnection {
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
            this.tbcClientTabs = new System.Windows.Forms.TabControl();
            this.tabPlayerList = new System.Windows.Forms.TabPage();
            this.uscPlayers = new PRoCon.uscPlayerListPanel();
            this.tabChat = new System.Windows.Forms.TabPage();
            this.uscChat = new PRoCon.uscChatPanel();
            this.pnlChatEnclosure = new System.Windows.Forms.Panel();
            this.rtbChatBox = new System.Windows.Forms.RichTextBox();
            this.txtMessage = new System.Windows.Forms.TextBox();
            this.lblMessage = new System.Windows.Forms.Label();
            this.lblRecipientUser = new System.Windows.Forms.Label();
            this.cboRecipient = new System.Windows.Forms.ComboBox();
            this.tabMapView = new System.Windows.Forms.TabPage();
            this.uscMap = new PRoCon.Controls.uscMapViewer();
            this.tabEvents = new System.Windows.Forms.TabPage();
            this.uscEvents = new PRoCon.uscEventsPanel();
            this.tabLists = new System.Windows.Forms.TabPage();
            this.uscLists = new PRoCon.uscListControlPanel();
            this.tabServerSettings = new System.Windows.Forms.TabPage();
            this.uscSettings = new PRoCon.uscServerSettingsPanel();
            this.tabPlugins = new System.Windows.Forms.TabPage();
            this.uscPlugins = new PRoCon.uscPluginPanel();
            this.tabAccounts = new System.Windows.Forms.TabPage();
            this.uscAccounts = new PRoCon.uscAccountsPanel();
            this.tabConsole = new System.Windows.Forms.TabPage();
            this.uscServerConsole = new PRoCon.uscConsolePanel();
            this.lblCurrentMapName = new System.Windows.Forms.Label();
            this.lblVersion = new System.Windows.Forms.Label();
            this.tmrPortCheckTester = new System.Windows.Forms.Timer(this.components);
            this.lblCurrentRound = new System.Windows.Forms.Label();
            this.uscLogin = new PRoCon.uscLoginPanel();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            this.lblRoundTime = new System.Windows.Forms.Label();
            this.pnlMapControls = new System.Windows.Forms.FlowLayoutPanel();
            this.picRestartRound = new System.Windows.Forms.PictureBox();
            this.btnRestartRound = new System.Windows.Forms.Button();
            this.picNextRound = new System.Windows.Forms.PictureBox();
            this.btnNextRound = new System.Windows.Forms.Button();
            this.lblPlasmaStatus = new System.Windows.Forms.Label();
            this.lblLayerVersion = new System.Windows.Forms.Label();
            this.lblServerUptime = new System.Windows.Forms.Label();
            this.lblMappack = new System.Windows.Forms.Label();
            this.toolTipPlasma = new System.Windows.Forms.ToolTip(this.components);
            this.toolTipMapControls = new System.Windows.Forms.ToolTip(this.components);
            this.tmrTimerTicks = new System.Windows.Forms.Timer(this.components);
            this.tbcClientTabs.SuspendLayout();
            this.tabPlayerList.SuspendLayout();
            this.tabChat.SuspendLayout();
            this.pnlChatEnclosure.SuspendLayout();
            this.tabMapView.SuspendLayout();
            this.tabEvents.SuspendLayout();
            this.tabLists.SuspendLayout();
            this.tabServerSettings.SuspendLayout();
            this.tabPlugins.SuspendLayout();
            this.tabAccounts.SuspendLayout();
            this.tabConsole.SuspendLayout();
            this.flowLayoutPanel1.SuspendLayout();
            this.pnlMapControls.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picRestartRound)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picNextRound)).BeginInit();
            this.SuspendLayout();
            //
            // tbcClientTabs
            //
            this.tbcClientTabs.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
            | System.Windows.Forms.AnchorStyles.Left)
            | System.Windows.Forms.AnchorStyles.Right)));
            this.tbcClientTabs.Controls.Add(this.tabPlayerList);
            this.tbcClientTabs.Controls.Add(this.tabChat);
            this.tbcClientTabs.Controls.Add(this.tabMapView);
            this.tbcClientTabs.Controls.Add(this.tabEvents);
            this.tbcClientTabs.Controls.Add(this.tabLists);
            this.tbcClientTabs.Controls.Add(this.tabServerSettings);
            this.tbcClientTabs.Controls.Add(this.tabPlugins);
            this.tbcClientTabs.Controls.Add(this.tabAccounts);
            this.tbcClientTabs.Controls.Add(this.tabConsole);
            this.tbcClientTabs.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.tbcClientTabs.Location = new System.Drawing.Point(8, 30);
            this.tbcClientTabs.Name = "tbcClientTabs";
            this.tbcClientTabs.SelectedIndex = 0;
            this.tbcClientTabs.Size = new System.Drawing.Size(1386, 963);
            this.tbcClientTabs.TabIndex = 0;
            this.tbcClientTabs.SelectedIndexChanged += new System.EventHandler(this.tbcClientTabs_SelectedIndexChanged);
            //
            // tabPlayerList
            //
            this.tabPlayerList.Controls.Add(this.uscPlayers);
            this.tabPlayerList.Location = new System.Drawing.Point(4, 24);
            this.tabPlayerList.Name = "tabPlayerList";
            this.tabPlayerList.Padding = new System.Windows.Forms.Padding(9);
            this.tabPlayerList.Size = new System.Drawing.Size(1378, 935);
            this.tabPlayerList.TabIndex = 1;
            this.tabPlayerList.Text = "Players";
            this.tabPlayerList.UseVisualStyleBackColor = true;
            //
            // uscPlayers
            //
            this.uscPlayers.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscPlayers.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscPlayers.Location = new System.Drawing.Point(9, 9);
            this.uscPlayers.Name = "uscPlayers";
            this.uscPlayers.Size = new System.Drawing.Size(1360, 917);
            this.uscPlayers.TabIndex = 0;
            //
            // tabChat
            //
            this.tabChat.Controls.Add(this.uscChat);
            this.tabChat.Controls.Add(this.pnlChatEnclosure);
            this.tabChat.Controls.Add(this.txtMessage);
            this.tabChat.Controls.Add(this.lblMessage);
            this.tabChat.Controls.Add(this.lblRecipientUser);
            this.tabChat.Controls.Add(this.cboRecipient);
            this.tabChat.Location = new System.Drawing.Point(4, 24);
            this.tabChat.Name = "tabChat";
            this.tabChat.Padding = new System.Windows.Forms.Padding(9);
            this.tabChat.Size = new System.Drawing.Size(1378, 935);
            this.tabChat.TabIndex = 0;
            this.tabChat.Text = "Chat";
            this.tabChat.UseVisualStyleBackColor = true;
            //
            // uscChat
            //
            this.uscChat.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscChat.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscChat.Location = new System.Drawing.Point(9, 9);
            this.uscChat.Name = "uscChat";
            this.uscChat.SettingFail = null;
            this.uscChat.SettingLoading = null;
            this.uscChat.SettingSuccess = null;
            this.uscChat.Size = new System.Drawing.Size(1360, 917);
            this.uscChat.TabIndex = 17;
            //
            // pnlChatEnclosure
            //
            this.pnlChatEnclosure.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
            | System.Windows.Forms.AnchorStyles.Left)
            | System.Windows.Forms.AnchorStyles.Right)));
            this.pnlChatEnclosure.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pnlChatEnclosure.Controls.Add(this.rtbChatBox);
            this.pnlChatEnclosure.Location = new System.Drawing.Point(13, 13);
            this.pnlChatEnclosure.Name = "pnlChatEnclosure";
            this.pnlChatEnclosure.Size = new System.Drawing.Size(0, 0);
            this.pnlChatEnclosure.TabIndex = 13;
            //
            // rtbChatBox
            //
            this.rtbChatBox.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rtbChatBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbChatBox.Location = new System.Drawing.Point(0, 0);
            this.rtbChatBox.Name = "rtbChatBox";
            this.rtbChatBox.ReadOnly = true;
            this.rtbChatBox.Size = new System.Drawing.Size(0, 0);
            this.rtbChatBox.TabIndex = 0;
            this.rtbChatBox.Text = "";
            //
            // txtMessage
            //
            this.txtMessage.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txtMessage.Location = new System.Drawing.Point(134, -32860);
            this.txtMessage.MaxLength = 128;
            this.txtMessage.Name = "txtMessage";
            this.txtMessage.Size = new System.Drawing.Size(0, 23);
            this.txtMessage.TabIndex = 7;
            //
            // lblMessage
            //
            this.lblMessage.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lblMessage.AutoSize = true;
            this.lblMessage.Location = new System.Drawing.Point(131, -32860);
            this.lblMessage.Name = "lblMessage";
            this.lblMessage.Size = new System.Drawing.Size(56, 15);
            this.lblMessage.TabIndex = 6;
            this.lblMessage.Text = "Message:";
            //
            // lblRecipientUser
            //
            this.lblRecipientUser.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lblRecipientUser.AutoSize = true;
            this.lblRecipientUser.Location = new System.Drawing.Point(13, -32860);
            this.lblRecipientUser.Name = "lblRecipientUser";
            this.lblRecipientUser.Size = new System.Drawing.Size(59, 15);
            this.lblRecipientUser.TabIndex = 4;
            this.lblRecipientUser.Text = "Recipient:";
            //
            // cboRecipient
            //
            this.cboRecipient.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.cboRecipient.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboRecipient.FormattingEnabled = true;
            this.cboRecipient.Items.AddRange(new object[] {
            "All Players"});
            this.cboRecipient.Location = new System.Drawing.Point(16, -32860);
            this.cboRecipient.Name = "cboRecipient";
            this.cboRecipient.Size = new System.Drawing.Size(112, 23);
            this.cboRecipient.TabIndex = 5;
            //
            // tabMapView
            //
            this.tabMapView.Controls.Add(this.uscMap);
            this.tabMapView.Location = new System.Drawing.Point(4, 24);
            this.tabMapView.Name = "tabMapView";
            this.tabMapView.Padding = new System.Windows.Forms.Padding(8);
            this.tabMapView.Size = new System.Drawing.Size(1378, 935);
            this.tabMapView.TabIndex = 8;
            this.tabMapView.Text = "Map";
            this.tabMapView.UseVisualStyleBackColor = true;
            //
            // uscMap
            //
            this.uscMap.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscMap.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscMap.IsMapLoaded = false;
            this.uscMap.IsMapSelected = false;
            this.uscMap.Location = new System.Drawing.Point(8, 8);
            this.uscMap.Name = "uscMap";
            this.uscMap.Size = new System.Drawing.Size(1362, 919);
            this.uscMap.TabIndex = 0;
            //
            // tabEvents
            //
            this.tabEvents.Controls.Add(this.uscEvents);
            this.tabEvents.Location = new System.Drawing.Point(4, 24);
            this.tabEvents.Name = "tabEvents";
            this.tabEvents.Padding = new System.Windows.Forms.Padding(9);
            this.tabEvents.Size = new System.Drawing.Size(1378, 935);
            this.tabEvents.TabIndex = 7;
            this.tabEvents.Text = "Events";
            this.tabEvents.UseVisualStyleBackColor = true;
            //
            // uscEvents
            //
            this.uscEvents.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscEvents.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscEvents.Location = new System.Drawing.Point(9, 9);
            this.uscEvents.Name = "uscEvents";
            this.uscEvents.Size = new System.Drawing.Size(1360, 917);
            this.uscEvents.TabIndex = 0;
            //
            // tabLists
            //
            this.tabLists.Controls.Add(this.uscLists);
            this.tabLists.Location = new System.Drawing.Point(4, 24);
            this.tabLists.Name = "tabLists";
            this.tabLists.Padding = new System.Windows.Forms.Padding(9);
            this.tabLists.Size = new System.Drawing.Size(1378, 935);
            this.tabLists.TabIndex = 4;
            this.tabLists.Text = "Lists";
            this.tabLists.UseVisualStyleBackColor = true;
            //
            // uscLists
            //
            this.uscLists.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscLists.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscLists.Location = new System.Drawing.Point(9, 9);
            this.uscLists.Name = "uscLists";
            this.uscLists.Size = new System.Drawing.Size(1360, 917);
            this.uscLists.TabIndex = 0;
            //
            // tabServerSettings
            //
            this.tabServerSettings.Controls.Add(this.uscSettings);
            this.tabServerSettings.Location = new System.Drawing.Point(4, 24);
            this.tabServerSettings.Name = "tabServerSettings";
            this.tabServerSettings.Padding = new System.Windows.Forms.Padding(9);
            this.tabServerSettings.Size = new System.Drawing.Size(1378, 935);
            this.tabServerSettings.TabIndex = 5;
            this.tabServerSettings.Text = "Server Settings";
            this.tabServerSettings.UseVisualStyleBackColor = true;
            //
            // uscSettings
            //
            this.uscSettings.BackColor = System.Drawing.Color.Transparent;
            this.uscSettings.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscSettings.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscSettings.Location = new System.Drawing.Point(9, 9);
            this.uscSettings.Name = "uscSettings";
            this.uscSettings.Padding = new System.Windows.Forms.Padding(10);
            this.uscSettings.Size = new System.Drawing.Size(1360, 917);
            this.uscSettings.TabIndex = 0;
            //
            // tabPlugins
            //
            this.tabPlugins.Controls.Add(this.uscPlugins);
            this.tabPlugins.Location = new System.Drawing.Point(4, 24);
            this.tabPlugins.Name = "tabPlugins";
            this.tabPlugins.Padding = new System.Windows.Forms.Padding(9);
            this.tabPlugins.Size = new System.Drawing.Size(1378, 935);
            this.tabPlugins.TabIndex = 2;
            this.tabPlugins.Text = "Plugins";
            this.tabPlugins.UseVisualStyleBackColor = true;
            //
            // uscPlugins
            //
            this.uscPlugins.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscPlugins.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscPlugins.LocalPlugins = false;
            this.uscPlugins.Location = new System.Drawing.Point(9, 9);
            this.uscPlugins.Name = "uscPlugins";
            this.uscPlugins.Size = new System.Drawing.Size(1360, 917);
            this.uscPlugins.TabIndex = 1;
            //
            // tabAccounts
            //
            this.tabAccounts.Controls.Add(this.uscAccounts);
            this.tabAccounts.Location = new System.Drawing.Point(4, 24);
            this.tabAccounts.Name = "tabAccounts";
            this.tabAccounts.Padding = new System.Windows.Forms.Padding(9);
            this.tabAccounts.Size = new System.Drawing.Size(1378, 935);
            this.tabAccounts.TabIndex = 6;
            this.tabAccounts.Text = "Accounts";
            this.tabAccounts.UseVisualStyleBackColor = true;
            //
            // uscAccounts
            //
            this.uscAccounts.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscAccounts.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscAccounts.Location = new System.Drawing.Point(9, 9);
            this.uscAccounts.Name = "uscAccounts";
            this.uscAccounts.Size = new System.Drawing.Size(1360, 917);
            this.uscAccounts.TabIndex = 0;
            //
            // tabConsole
            //
            this.tabConsole.Controls.Add(this.uscServerConsole);
            this.tabConsole.Location = new System.Drawing.Point(4, 24);
            this.tabConsole.Name = "tabConsole";
            this.tabConsole.Padding = new System.Windows.Forms.Padding(9);
            this.tabConsole.Size = new System.Drawing.Size(1378, 935);
            this.tabConsole.TabIndex = 3;
            this.tabConsole.Text = "Console";
            this.tabConsole.UseVisualStyleBackColor = true;
            //
            // uscServerConsole
            //
            this.uscServerConsole.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscServerConsole.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscServerConsole.Location = new System.Drawing.Point(9, 9);
            this.uscServerConsole.Name = "uscServerConsole";
            this.uscServerConsole.Size = new System.Drawing.Size(1360, 917);
            this.uscServerConsole.TabIndex = 0;
            //
            // lblCurrentMapName
            //
            this.lblCurrentMapName.AutoSize = true;
            this.lblCurrentMapName.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCurrentMapName.Location = new System.Drawing.Point(3, 0);
            this.lblCurrentMapName.Name = "lblCurrentMapName";
            this.lblCurrentMapName.Size = new System.Drawing.Size(106, 15);
            this.lblCurrentMapName.TabIndex = 5;
            this.lblCurrentMapName.Text = "Map: blahblahblah";
            //
            // lblVersion
            //
            this.lblVersion.AutoSize = true;
            this.lblVersion.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lblVersion.Location = new System.Drawing.Point(386, 0);
            this.lblVersion.Name = "lblVersion";
            this.lblVersion.Size = new System.Drawing.Size(65, 15);
            this.lblVersion.TabIndex = 6;
            this.lblVersion.Text = "Version: R6";
            //
            // tmrPortCheckTester
            //
            this.tmrPortCheckTester.Interval = 88;
            //
            // lblCurrentRound
            //
            this.lblCurrentRound.AutoSize = true;
            this.lblCurrentRound.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCurrentRound.Location = new System.Drawing.Point(115, 0);
            this.lblCurrentRound.Name = "lblCurrentRound";
            this.lblCurrentRound.Size = new System.Drawing.Size(71, 15);
            this.lblCurrentRound.TabIndex = 8;
            this.lblCurrentRound.Text = "Round: 1 / 5";
            this.lblCurrentRound.MouseEnter += new System.EventHandler(this.lblCurrentRound_MouseEnter);
            this.lblCurrentRound.MouseLeave += new System.EventHandler(this.lblCurrentRound_MouseLeave);
            //
            // uscLogin
            //
            this.uscLogin.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscLogin.Location = new System.Drawing.Point(887, 3);
            this.uscLogin.Name = "uscLogin";
            this.uscLogin.Size = new System.Drawing.Size(182, 78);
            this.uscLogin.TabIndex = 7;
            //
            // flowLayoutPanel1
            //
            this.flowLayoutPanel1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
            | System.Windows.Forms.AnchorStyles.Right)));
            this.flowLayoutPanel1.Controls.Add(this.lblCurrentMapName);
            this.flowLayoutPanel1.Controls.Add(this.lblCurrentRound);
            this.flowLayoutPanel1.Controls.Add(this.lblRoundTime);
            this.flowLayoutPanel1.Controls.Add(this.pnlMapControls);
            this.flowLayoutPanel1.Controls.Add(this.lblPlasmaStatus);
            this.flowLayoutPanel1.Controls.Add(this.lblVersion);
            this.flowLayoutPanel1.Controls.Add(this.lblLayerVersion);
            this.flowLayoutPanel1.Controls.Add(this.lblMappack);
            this.flowLayoutPanel1.Controls.Add(this.lblServerUptime);
            this.flowLayoutPanel1.Location = new System.Drawing.Point(8, 5);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(1382, 19);
            this.flowLayoutPanel1.TabIndex = 9;
            //
            // lblRoundTime
            //
            this.lblRoundTime.AutoSize = true;
            this.lblRoundTime.Location = new System.Drawing.Point(192, 0);
            this.lblRoundTime.Name = "lblRoundTime";
            this.lblRoundTime.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.lblRoundTime.Size = new System.Drawing.Size(13, 15);
            this.lblRoundTime.TabIndex = 121;
            this.lblRoundTime.Text = "  ";
            //
            // pnlMapControls
            //
            this.pnlMapControls.Controls.Add(this.picRestartRound);
            this.pnlMapControls.Controls.Add(this.btnRestartRound);
            this.pnlMapControls.Controls.Add(this.picNextRound);
            this.pnlMapControls.Controls.Add(this.btnNextRound);
            this.pnlMapControls.Location = new System.Drawing.Point(208, 0);
            this.pnlMapControls.Margin = new System.Windows.Forms.Padding(0);
            this.pnlMapControls.Name = "pnlMapControls";
            this.pnlMapControls.Size = new System.Drawing.Size(67, 16);
            this.pnlMapControls.TabIndex = 118;
            //
            // picRestartRound
            //
            this.picRestartRound.Location = new System.Drawing.Point(3, 3);
            this.picRestartRound.Name = "picRestartRound";
            this.picRestartRound.Size = new System.Drawing.Size(8, 8);
            this.picRestartRound.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picRestartRound.TabIndex = 116;
            this.picRestartRound.TabStop = false;
            //
            // btnRestartRound
            //
            this.btnRestartRound.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnRestartRound.FlatAppearance.BorderSize = 0;
            this.btnRestartRound.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRestartRound.Location = new System.Drawing.Point(14, 0);
            this.btnRestartRound.Margin = new System.Windows.Forms.Padding(0);
            this.btnRestartRound.Name = "btnRestartRound";
            this.btnRestartRound.Size = new System.Drawing.Size(19, 16);
            this.btnRestartRound.TabIndex = 114;
            this.toolTipMapControls.SetToolTip(this.btnRestartRound, "Restart round");
            this.btnRestartRound.UseVisualStyleBackColor = true;
            this.btnRestartRound.Click += new System.EventHandler(this.btnRestartRound_Click);
            //
            // picNextRound
            //
            this.picNextRound.Location = new System.Drawing.Point(36, 3);
            this.picNextRound.Name = "picNextRound";
            this.picNextRound.Size = new System.Drawing.Size(8, 8);
            this.picNextRound.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picNextRound.TabIndex = 117;
            this.picNextRound.TabStop = false;
            //
            // btnNextRound
            //
            this.btnNextRound.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnNextRound.FlatAppearance.BorderSize = 0;
            this.btnNextRound.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnNextRound.Location = new System.Drawing.Point(47, 0);
            this.btnNextRound.Margin = new System.Windows.Forms.Padding(0);
            this.btnNextRound.Name = "btnNextRound";
            this.btnNextRound.Size = new System.Drawing.Size(19, 16);
            this.btnNextRound.TabIndex = 115;
            this.toolTipMapControls.SetToolTip(this.btnNextRound, "Next round");
            this.btnNextRound.UseVisualStyleBackColor = true;
            this.btnNextRound.Click += new System.EventHandler(this.btnNextRound_Click);
            //
            // lblPlasmaStatus
            //
            this.lblPlasmaStatus.AutoSize = true;
            this.lblPlasmaStatus.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPlasmaStatus.Location = new System.Drawing.Point(278, 0);
            this.lblPlasmaStatus.Name = "lblPlasmaStatus";
            this.lblPlasmaStatus.Size = new System.Drawing.Size(102, 15);
            this.lblPlasmaStatus.TabIndex = 9;
            this.lblPlasmaStatus.Text = "AcceptingPlayers";
            this.toolTipPlasma.SetToolTip(this.lblPlasmaStatus, "the game server is connected to the Plasma backend, visible in the server browser" +
        ", and players can join the server");
            //
            // lblLayerVersion
            //
            this.lblLayerVersion.AutoSize = true;
            this.lblLayerVersion.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLayerVersion.Location = new System.Drawing.Point(454, 0);
            this.lblLayerVersion.Margin = new System.Windows.Forms.Padding(0, 0, 3, 0);
            this.lblLayerVersion.Name = "lblLayerVersion";
            this.lblLayerVersion.Size = new System.Drawing.Size(28, 15);
            this.lblLayerVersion.TabIndex = 120;
            this.lblLayerVersion.Text = "       ";
            this.lblLayerVersion.Visible = false;
            //
            // lblServerUptime
            //
            this.lblServerUptime.AutoSize = true;
            this.lblServerUptime.Location = new System.Drawing.Point(562, 0);
            this.lblServerUptime.Name = "lblServerUptime";
            this.lblServerUptime.Size = new System.Drawing.Size(25, 15);
            this.lblServerUptime.TabIndex = 122;
            this.lblServerUptime.Text = "      ";
            this.lblServerUptime.Visible = false;
            //
            // lblMappack
            //
            this.lblMappack.AutoSize = true;
            this.lblMappack.Location = new System.Drawing.Point(488, 0);
            this.lblMappack.Name = "lblMappack";
            this.lblMappack.Size = new System.Drawing.Size(68, 15);
            this.lblMappack.TabIndex = 123;
            this.lblMappack.Text = "Mappack: 1";
            //
            // toolTipPlasma
            //
            this.toolTipPlasma.IsBalloon = true;
            //
            // tmrTimerTicks
            //
            this.tmrTimerTicks.Enabled = true;
            this.tmrTimerTicks.Interval = 1000;
            this.tmrTimerTicks.Tick += new System.EventHandler(this.tmrTimerTicks_Tick);
            //
            // uscServerConnection
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.uscLogin);
            this.Controls.Add(this.flowLayoutPanel1);
            this.Controls.Add(this.tbcClientTabs);
            this.DoubleBuffered = true;
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscServerConnection";
            this.Size = new System.Drawing.Size(1400, 998);
            this.Load += new System.EventHandler(this.uscServerConnection_Load);
            this.Resize += new System.EventHandler(this.uscServerConnection_Resize);
            this.tbcClientTabs.ResumeLayout(false);
            this.tabPlayerList.ResumeLayout(false);
            this.tabChat.ResumeLayout(false);
            this.tabChat.PerformLayout();
            this.pnlChatEnclosure.ResumeLayout(false);
            this.tabMapView.ResumeLayout(false);
            this.tabEvents.ResumeLayout(false);
            this.tabLists.ResumeLayout(false);
            this.tabServerSettings.ResumeLayout(false);
            this.tabPlugins.ResumeLayout(false);
            this.tabAccounts.ResumeLayout(false);
            this.tabConsole.ResumeLayout(false);
            this.flowLayoutPanel1.ResumeLayout(false);
            this.flowLayoutPanel1.PerformLayout();
            this.pnlMapControls.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picRestartRound)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picNextRound)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tbcClientTabs;
        private System.Windows.Forms.TabPage tabPlayerList;
        private System.Windows.Forms.TabPage tabPlugins;
        private System.Windows.Forms.TabPage tabConsole;
        private System.Windows.Forms.TabPage tabLists;
        private System.Windows.Forms.TabPage tabChat;
        private System.Windows.Forms.Label lblCurrentMapName;
        private System.Windows.Forms.Label lblVersion;
        private System.Windows.Forms.TextBox txtMessage;
        private System.Windows.Forms.Label lblMessage;
        private System.Windows.Forms.Label lblRecipientUser;
        private System.Windows.Forms.ComboBox cboRecipient;
        private System.Windows.Forms.Panel pnlChatEnclosure;
        private System.Windows.Forms.RichTextBox rtbChatBox;
        private System.Windows.Forms.TabPage tabServerSettings;
        private System.Windows.Forms.TabPage tabAccounts;
        private System.Windows.Forms.Timer tmrPortCheckTester;
        private uscPlayerListPanel uscPlayers;
        private uscConsolePanel uscServerConsole;
        private uscServerSettingsPanel uscSettings;
        private uscListControlPanel uscLists;
        private uscChatPanel uscChat;
        private uscAccountsPanel uscAccounts;
        private uscLoginPanel uscLogin;
        private System.Windows.Forms.TabPage tabEvents;
        private uscEventsPanel uscEvents;
        private System.Windows.Forms.Label lblCurrentRound;
        private System.Windows.Forms.TabPage tabMapView;
        private PRoCon.Controls.uscMapViewer uscMap;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
        private System.Windows.Forms.Label lblPlasmaStatus;
        private System.Windows.Forms.ToolTip toolTipPlasma;
        private System.Windows.Forms.Button btnRestartRound;
        private System.Windows.Forms.Button btnNextRound;
        private System.Windows.Forms.PictureBox picRestartRound;
        private System.Windows.Forms.PictureBox picNextRound;
        private System.Windows.Forms.FlowLayoutPanel pnlMapControls;
        private System.Windows.Forms.ToolTip toolTipMapControls;
        private System.Windows.Forms.Label lblLayerVersion;
        private uscPluginPanel uscPlugins;
        private System.Windows.Forms.Label lblRoundTime;
        private System.Windows.Forms.Timer tmrTimerTicks;
        private System.Windows.Forms.Label lblServerUptime;
        private System.Windows.Forms.Label lblMappack;
    }*/
}
