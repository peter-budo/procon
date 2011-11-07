package procon.controls;

public class UscPlayerListPanel {

    /*public partial class uscPlayerListPanel : UserControl {

        private frmMain m_frmMain;
        private uscServerConnection m_uscConnectionPanel;

        private PRoConClient m_prcClient;

        private CLocalization m_clocLanguage;
        private PlayerListColumnSorter m_lvwColumnSorter;

        private CPrivileges m_spPrivileges;

        private const int INT_NEUTRAL_SQUAD = 0;
        private const int INT_NEUTRAL_TEAM = 0;
        private const int INT_MAX_TEAMS = 5; //0 = neutral, 1, 2, 3, 4..

        public uscPlayerListPanel() {
            InitializeComponent();

            this.m_clocLanguage = null;
            this.m_lvwColumnSorter = new PlayerListColumnSorter();
            this.m_frmMain = null;
            this.m_uscConnectionPanel = null;

            this.m_spPrivileges = new CPrivileges();
            this.m_spPrivileges.PrivilegesFlags = CPrivileges.FullPrivilegesFlags;

            this.spltListAdditionalInfo.Panel2Collapsed = true;
            this.spltTwoSplit.Panel2Collapsed = true;
            this.spltFourSplit.Panel2Collapsed = true;
        }

        private bool m_isSplitterBeingSet;
        private void SetSplitterDistances() {

            this.m_isSplitterBeingSet = true;

            if (this.m_prcClient != null && this.m_prcClient.PlayerListSettings != null) {
                int iTwoSplitterDistance = (int)(this.spltTwoSplit.Width * this.m_prcClient.PlayerListSettings.TwoSplitterPercentage);
                int iFourSplitterDistance = (int)(this.spltFourSplit.Height * this.m_prcClient.PlayerListSettings.FourSplitterPercentage);

                if (iTwoSplitterDistance < this.spltTwoSplit.Panel1MinSize) {
                    this.spltBottomTwoSplit.SplitterDistance = this.spltTwoSplit.SplitterDistance = this.spltTwoSplit.Panel1MinSize;
                }
                else if (iTwoSplitterDistance > this.spltTwoSplit.Width - this.spltTwoSplit.Panel2MinSize) {
                    this.spltBottomTwoSplit.SplitterDistance = this.spltTwoSplit.SplitterDistance = this.spltTwoSplit.Width - this.spltTwoSplit.Panel2MinSize;
                }
                else {
                    this.spltBottomTwoSplit.SplitterDistance = this.spltTwoSplit.SplitterDistance = iTwoSplitterDistance;
                }

                if (iFourSplitterDistance < this.spltFourSplit.Panel1MinSize) {
                    this.spltFourSplit.SplitterDistance = this.spltFourSplit.Panel1MinSize;
                }
                else if (iFourSplitterDistance > this.spltFourSplit.Height - this.spltFourSplit.Panel2MinSize) {
                    this.spltFourSplit.SplitterDistance = this.spltFourSplit.Height - this.spltFourSplit.Panel2MinSize;
                }
                else {
                    this.spltFourSplit.SplitterDistance = iFourSplitterDistance;
                }

                for (int i = 0; i < this.lsvTeamOnePlayers.Columns.Count; i++) {
                    this.lsvTeamOnePlayers.Columns[i].Width = -2;
                    this.lsvTeamTwoPlayers.Columns[i].Width = -2;
                    this.lsvTeamThreePlayers.Columns[i].Width = -2;
                    this.lsvTeamFourPlayers.Columns[i].Width = -2;
                }

                this.Invalidate();
            }

            this.m_isSplitterBeingSet = false;
        }

        private void uscPlayerListPanel_Load(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_clocLanguage = this.m_prcClient.Language;

            }
        }

        public void Initialize(frmMain frmMainWindow, uscServerConnection uscConnectionPanel) {
            this.m_frmMain = frmMainWindow;
            this.m_uscConnectionPanel = uscConnectionPanel;

            this.kbpPunkbusterPunishPanel.Punkbuster = true;
            this.kbpPunkbusterPunishPanel.PunishPlayer += new uscPlayerPunishPanel.PunishPlayerDelegate(kbpPunkbusterPunishPanel_PunishPlayer);
            this.kbpPunkbusterPunishPanel.Initialize(uscConnectionPanel);
            this.kbpBfbcPunishPanel.PunishPlayer += new uscPlayerPunishPanel.PunishPlayerDelegate(kbpBfbcPunishPanel_PunishPlayer);
            this.kbpBfbcPunishPanel.Initialize(uscConnectionPanel);

            this.lsvTeamOnePlayers.SmallImageList = this.m_frmMain.iglFlags;
            this.lsvTeamTwoPlayers.SmallImageList = this.m_frmMain.iglFlags;
            this.lsvTeamThreePlayers.SmallImageList = this.m_frmMain.iglFlags;
            this.lsvTeamFourPlayers.SmallImageList = this.m_frmMain.iglFlags;
            this.lsvTeamOnePlayers.ListViewItemSorter = this.m_lvwColumnSorter;
            this.lsvTeamTwoPlayers.ListViewItemSorter = this.m_lvwColumnSorter;
            this.lsvTeamThreePlayers.ListViewItemSorter = this.m_lvwColumnSorter;
            this.lsvTeamFourPlayers.ListViewItemSorter = this.m_lvwColumnSorter;

            this.btnCloseAdditionalInfo.ImageList = this.m_frmMain.iglIcons;
            this.btnCloseAdditionalInfo.ImageKey = "cross.png";

            this.btnSplitTeams.ImageList = this.m_frmMain.iglIcons;
            this.btnSplitTeams.ImageKey = "application_tile_horizontal.png";
        }

        // If we disconnect clear the player list so it's fresh on reconnection.
        private void m_prcClient_ConnectionClosed(PRoConClient sender) {
            foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                kvpPlayer.Value.Remove();
            }

            this.m_dicPlayers.Clear();
        }

        //public void OnConnectionClosed() {

        //}

        internal class AdditionalPlayerInfo {
            public CPunkbusterInfo m_pbInfo;
            //public string m_strCountryName;
            public string m_strResolvedHostName;
            public CPlayerInfo m_cpiPlayer;
            public Inventory m_spawnedInventory;

            public Dictionary<Kits, int> KitCounter {
                get;
                private set;
            }

            public AdditionalPlayerInfo() {
                this.KitCounter = new Dictionary<Kits, int>();
            }

            public void AddKitCount(Kits kit) {

                if (this.KitCounter.ContainsKey(kit) == true) {
                    this.KitCounter[kit] = this.KitCounter[kit] + 1;
                }
                else {
                    this.KitCounter.Add(kit, 1);
                }

            }
        }

        public void PlayerSelectionChange(string strSoldierName) {
            this.SelectPlayer(strSoldierName);
        }

        private void kbpBfbcPunishPanel_PunishPlayer(List<string> lstWords) {
            this.m_prcClient.SendRequest(lstWords);

            this.m_prcClient.Game.SendBanListSavePacket();
            this.m_prcClient.Game.SendBanListListPacket();
        }

        private void kbpPunkbusterPunishPanel_PunishPlayer(List<string> lstWords) {
            this.m_prcClient.SendRequest(lstWords);
        }

        public void SetConnection(PRoConClient prcClient) {
            if ((this.m_prcClient = prcClient) != null) {
                if (this.m_prcClient.Game != null) {
                    this.m_prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    this.m_prcClient.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcClient_GameTypeDiscovered);
                }
            }
        }

        private void m_prcClient_GameTypeDiscovered(PRoConClient sender) {

            this.m_prcClient.Game.ListPlayers += new FrostbiteClient.ListPlayersHandler(m_prcClient_ListPlayers);
            this.m_prcClient.Game.PlayerJoin += new FrostbiteClient.PlayerEventHandler(m_prcClient_PlayerJoin);
            this.m_prcClient.Game.PlayerLeft += new FrostbiteClient.PlayerLeaveHandler(m_prcClient_PlayerLeft);
            this.m_prcClient.PunkbusterPlayerInfo += new PRoConClient.PunkbusterPlayerInfoHandler(m_prcClient_PunkbusterPlayerInfo);
            this.m_prcClient.PlayerKilled += new PRoConClient.PlayerKilledHandler(m_prcClient_PlayerKilled);

            this.m_prcClient.Game.PlayerChangedTeam += new FrostbiteClient.PlayerTeamChangeHandler(m_prcClient_PlayerChangedTeam);
            this.m_prcClient.Game.PlayerChangedSquad += new FrostbiteClient.PlayerTeamChangeHandler(m_prcClient_PlayerChangedSquad);

            this.m_prcClient.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcClient_ProconPrivileges);

            this.m_prcClient.ConnectionClosed += new PRoConClient.EmptyParamterHandler(m_prcClient_ConnectionClosed);

            this.m_prcClient.Game.LevelStarted += new FrostbiteClient.EmptyParamterHandler(m_prcClient_LevelStarted);

            this.kbpPunkbusterPunishPanel.SetConnection(this.m_prcClient);
            this.kbpBfbcPunishPanel.SetConnection(this.m_prcClient);

            this.m_prcClient.Reasons.ItemAdded += new NotificationList<string>.ItemModifiedHandler(Reasons_ItemAdded);
            this.m_prcClient.Reasons.ItemRemoved += new NotificationList<string>.ItemModifiedHandler(Reasons_ItemRemoved);

            this.m_prcClient.PlayerListSettings.SplitTypeChanged += new PlayerListSettings.IndexChangedHandler(PlayerListSettings_SplitTypeChanged);
            this.m_prcClient.PlayerListSettings.TwoSplitterPercentageChanged += new PlayerListSettings.PercentageChangedHandler(PlayerListSettings_TwoSplitterPercentageChanged);
            this.m_prcClient.PlayerListSettings.FourSplitterPercentageChanged += new PlayerListSettings.PercentageChangedHandler(PlayerListSettings_FourSplitterPercentageChanged);

            this.m_prcClient.PlayerSpawned += new PRoConClient.PlayerSpawnedHandler(m_prcClient_PlayerSpawned);

            foreach (string strReason in this.m_prcClient.Reasons) {
                this.Reasons_ItemAdded(0, strReason);
            }

            this.m_prcClient.PlayerListSettings.SplitType = this.m_prcClient.PlayerListSettings.SplitType;

            this.m_prcClient_ListPlayers(this.m_prcClient.Game, new List<CPlayerInfo>(this.m_prcClient.PlayerList), new CPlayerSubset(CPlayerSubset.PlayerSubsetType.All));

            if (sender.Game.HasSquads == false) {
                this.lsvTeamOnePlayers.Columns.Remove(this.colSquad1);
                this.lsvTeamTwoPlayers.Columns.Remove(this.colSquad2);
                this.lsvTeamThreePlayers.Columns.Remove(this.colSquad3);
                this.lsvTeamFourPlayers.Columns.Remove(this.colSquad4);

                //this.colSquad1.Text = this.colSquad2.Text = this.colSquad3.Text = this.colSquad4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colSquad", null);

            }

            this.SetSplitterDistances();
        }

        private void PlayerListSettings_SplitTypeChanged(int index) {
            if (index == 1) {
                this.btnSplitTeams.ImageKey = "application_tile_horizontal.png";

                this.spltTwoSplit.Panel2Collapsed = true;
                this.spltFourSplit.Panel2Collapsed = true;
            }
            else if (index == 2) {
                this.btnSplitTeams.ImageKey = "application_tile.png";

                this.spltTwoSplit.Panel2Collapsed = false;
                this.spltFourSplit.Panel2Collapsed = true;
            }
            else if (index == 4) {
                this.btnSplitTeams.ImageKey = "application.png";

                this.spltTwoSplit.Panel2Collapsed = false;
                this.spltFourSplit.Panel2Collapsed = false;
            }
        }

        private void Reasons_ItemRemoved(int iIndex, string item) {
            if (this.kbpBfbcPunishPanel.Reasons.Contains(item) == true) {
                this.kbpBfbcPunishPanel.Reasons.Remove(item);
            }

            if (this.kbpPunkbusterPunishPanel.Reasons.Contains(item) == true) {
                this.kbpPunkbusterPunishPanel.Reasons.Remove(item);
            }
        }

        private void Reasons_ItemAdded(int iIndex, string item) {
            this.kbpBfbcPunishPanel.Reasons.Add(item);
            this.kbpPunkbusterPunishPanel.Reasons.Add(item);
        }

        private void m_prcClient_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {
            this.m_spPrivileges = spPrivs;

            this.kbpPunkbusterPunishPanel.Enabled = (!this.m_spPrivileges.CannotPunishPlayers && this.m_spPrivileges.CanIssueLimitedPunkbusterCommands);
            this.kbpPunkbusterPunishPanel.SetPrivileges(this.m_spPrivileges);

            this.kbpBfbcPunishPanel.Enabled = !this.m_spPrivileges.CannotPunishPlayers;
            this.kbpBfbcPunishPanel.SetPrivileges(this.m_spPrivileges);
        }

        public void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            this.colSlotID1.Text = this.colSlotID2.Text = this.colSlotID3.Text = this.colSlotID4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colSlotID", null);
            this.colTags1.Text = this.colTags2.Text = this.colTags3.Text = this.colTags4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colTags", null);
            this.colPlayerName1.Text = this.colPlayerName2.Text = this.colPlayerName3.Text = this.colPlayerName4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colPlayerName", null);
            this.colSquad1.Text = this.colSquad2.Text = this.colSquad3.Text = this.colSquad4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colSquad", null);
            this.colKit1.Text = this.colKit2.Text = this.colKit3.Text = this.colKit4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colKit", null);
            this.colKills1.Text = this.colKills2.Text = this.colKills3.Text = this.colKills4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colKills", null);
            this.colDeaths1.Text = this.colDeaths2.Text = this.colDeaths3.Text = this.colDeaths4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colDeaths", null);
            this.colKdr1.Text = this.colKdr2.Text = this.colKdr3.Text = this.colKdr4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colKdr", null);
            this.colScore1.Text = this.colScore2.Text = this.colScore3.Text = this.colScore4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colScore", null);
            this.colPing1.Text = this.colPing2.Text = this.colPing3.Text = this.colPing4.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colPing", null);

            this.btnPlayerListSelectedCheese.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.btnPlayerListSelectedCheese", null);

            this.chkPlayerListShowTeams.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.chkPlayerListShowTeams", null);

            this.tabCourtMartialBFBC.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.tabCourtMartialBFBC", null);
            this.tabCourtMartialPunkbuster.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.tabCourtMartialPunkbuster", null);

            this.lblInventory.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lblInventory") + ":";

            // Player Context Menu
            this.textChatModerationToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.ctxPlayerOptions.textChatModerationToolStripMenuItem");
            this.reservedSlotToolStripMenuItem.Text = this.m_clocLanguage.GetLocalized("uscPlayerListPanel.ctxPlayerOptions.reservedSlotToolStripMenuItem");

            this.statsLookupToolStripMenuItem.Text = this.m_clocLanguage.GetDefaultLocalized("Stats Lookup", "uscPlayerListPanel.ctxPlayerOptions.statsLookupToolStripMenuItem");

            this.kbpBfbcPunishPanel.SetLocalization(this.m_clocLanguage);
            this.kbpPunkbusterPunishPanel.SetLocalization(this.m_clocLanguage);
        }

        //private CServerInfo m_csiLastServerInfo = null;

        *//*
        private string GetTeamName(int iTeamID) {

            string strReturnTeamName = String.Empty;

            if (this.m_csiLastServerInfo != null) {

                strReturnTeamName = this.m_prcClient.GetLocalizedTeamName(iTeamID, this.m_prcClient.CurrentServerInfo.Map);

            }

            return strReturnTeamName;
        }
        *//*
        *//*
        private int GetTotalTeamsForMap() {

            int iTotalTeams = this.m_prcClient.GetLocalizedTeamNameCount(this.m_prcClient.CurrentServerInfo.Map);

            return iTotalTeams;
        }
        *//*

        //public void OnServerInfo(CServerInfo csiInfo) {
        //    this.m_csiLastServerInfo = csiInfo;
        //}

        private readonly object m_objPlayerDictionaryLocker = new object();
        private Dictionary<string, ListViewItem> m_dicPlayers = new Dictionary<string, ListViewItem>();
        //private bool m_blSplitList = false;
        //private int m_iSplitPlayerLists = 1;

        private ListViewItem CreateTotalsPlayer(CPlayerInfo cpiDummyPlayer, int iTeamID) {
            ListViewItem lviReturn = this.CreatePlayer(new CPlayerInfo(cpiDummyPlayer.SoldierName, String.Empty, iTeamID, 0));
            lviReturn.Name = cpiDummyPlayer.ClanTag;
            lviReturn.Font = new Font(this.Font, FontStyle.Bold);

            return lviReturn;
        }

        private ListViewItem CreatePlayer(CPlayerInfo cpiPlayer) {
            ListViewItem lviNewPlayer = new ListViewItem("");
            lviNewPlayer.Name = cpiPlayer.SoldierName;
            lviNewPlayer.Tag = null;
            lviNewPlayer.UseItemStyleForSubItems = true;

            AdditionalPlayerInfo sapiAdditional = new AdditionalPlayerInfo();
            sapiAdditional.m_cpiPlayer = cpiPlayer;
            sapiAdditional.m_strResolvedHostName = String.Empty;
            lviNewPlayer.Tag = sapiAdditional;

            ListViewItem.ListViewSubItem lviTags = new ListViewItem.ListViewSubItem();
            lviTags.Name = "tags";
            lviTags.Text = cpiPlayer.ClanTag;
            lviNewPlayer.SubItems.Add(lviTags);

            ListViewItem.ListViewSubItem lviTagsName = new ListViewItem.ListViewSubItem();
            lviTagsName.Name = "soldiername";
            lviTagsName.Text = cpiPlayer.SoldierName;
            lviNewPlayer.SubItems.Add(lviTagsName);

            if (this.m_prcClient != null && this.m_prcClient.Game != null && this.m_prcClient.Game.HasSquads == true) {
                ListViewItem.ListViewSubItem lviSquad = new ListViewItem.ListViewSubItem();
                lviSquad.Name = "squad";
                if (cpiPlayer.SquadID != uscPlayerListPanel.INT_NEUTRAL_SQUAD) {
                    lviSquad.Text = this.m_clocLanguage.GetLocalized("global.Squad" + cpiPlayer.SquadID.ToString(), null);
                }
                lviNewPlayer.SubItems.Add(lviSquad);
            }

            ListViewItem.ListViewSubItem lviKit = new ListViewItem.ListViewSubItem();
            lviKit.Name = "kit";
            lviKit.Text = String.Empty;
            lviNewPlayer.SubItems.Add(lviKit);

            ListViewItem.ListViewSubItem lviScore = new ListViewItem.ListViewSubItem();
            lviScore.Name = "score";
            lviScore.Text = cpiPlayer.Score.ToString();
            lviNewPlayer.SubItems.Add(lviScore);

            ListViewItem.ListViewSubItem lviKills = new ListViewItem.ListViewSubItem();
            lviKills.Name = "kills";
            lviKills.Tag = (Double)cpiPlayer.Kills;
            lviKills.Text = cpiPlayer.Kills.ToString();
            lviNewPlayer.SubItems.Add(lviKills);

            ListViewItem.ListViewSubItem lviDeaths = new ListViewItem.ListViewSubItem();
            lviDeaths.Name = "deaths";
            lviDeaths.Tag = (Double)cpiPlayer.Deaths;
            lviDeaths.Text = cpiPlayer.Deaths.ToString();
            lviNewPlayer.SubItems.Add(lviDeaths);

            ListViewItem.ListViewSubItem lviKDr = new ListViewItem.ListViewSubItem();
            lviKDr.Name = "kdr";
            lviKDr.Text = cpiPlayer.Deaths > 0 ? String.Format("{0:0.00}", (Double)cpiPlayer.Kills / (Double)cpiPlayer.Deaths) : String.Format("{0:0.00}", (Double)cpiPlayer.Kills);
            lviNewPlayer.SubItems.Add(lviKDr);

            ListViewItem.ListViewSubItem lviPing = new ListViewItem.ListViewSubItem();
            lviPing.Name = "ping";
            lviPing.Text = cpiPlayer.Ping.ToString();
            lviNewPlayer.SubItems.Add(lviPing);

            return lviNewPlayer;
        }

        private int GetPlayerTeamID(ListViewItem lviPlayer) {

            int iReturnTeamID = 0;

            if (lviPlayer.Tag != null && ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer != null) {
                iReturnTeamID = ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.TeamID;
            }

            return iReturnTeamID;
        }

        private int GetTotalPlayersByTeamID(int iTeamID) {
            int iTotalPlayers = 0;

            foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                if (this.GetPlayerTeamID(kvpPlayer.Value) == iTeamID) {
                    iTotalPlayers++;
                }
            }

            // - 2 to account for the totals.
            return iTotalPlayers - 2;
        }

        private void SetPlayerTeamID(ListViewItem lviPlayer, int iTeamID) {
            if (lviPlayer.Tag != null && ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer != null) {

                //if (((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.TeamID != iTeamID && iTeamID != uscPlayerListPanel.INT_NEUTRAL_TEAM) {
                //    this.m_uscConnectionPanel.ThrowEvent(this, uscEventsPanel.CapturableEvents.PlayerSwitchedTeams, new string[] { ((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SoldierName, this.GetTeamName(((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.TeamID), this.GetTeamName(iTeamID) });
                //}

                ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.TeamID = iTeamID;
            }
        }

        private void SetPlayerSquadID(ListViewItem lviPlayer, int iSquadID) {
            if (lviPlayer.Tag != null && ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer != null) {

                *//*
                if (((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SquadID != iSquadID) {
                    if (iSquadID != uscPlayerListPanel.INT_NEUTRAL_SQUAD) {

                        if (((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SquadID != uscPlayerListPanel.INT_NEUTRAL_SQUAD) {
                            this.m_uscConnectionPanel.ThrowEvent(this, uscEventsPanel.CapturableEvents.PlayerSwitchedSquads, new string[] { ((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SoldierName, this.m_clocLanguage.GetLocalized("global.Squad" + ((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SquadID.ToString(), null), this.m_clocLanguage.GetLocalized("global.Squad" + iSquadID.ToString(), null) });
                        }
                        else {
                            // TO DO: Localize None
                            this.m_uscConnectionPanel.ThrowEvent(this, uscEventsPanel.CapturableEvents.PlayerSwitchedSquads, new string[] { ((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SoldierName, "None", this.m_clocLanguage.GetLocalized("global.Squad" + iSquadID.ToString(), null) });
                        }
                    }
                    else {
                        this.m_uscConnectionPanel.ThrowEvent(this, uscEventsPanel.CapturableEvents.PlayerSwitchedSquads, new string[] { ((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SoldierName, this.m_clocLanguage.GetLocalized("global.Squad" + ((SAdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SquadID.ToString(), null), "None" });
                    }
                }
                *//*

                if (this.m_prcClient != null && this.m_prcClient.Game != null && this.m_prcClient.Game.HasSquads == true) {
                    ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SquadID = iSquadID;
                    if (iSquadID != uscPlayerListPanel.INT_NEUTRAL_SQUAD) {
                        lviPlayer.SubItems["squad"].Text = this.m_clocLanguage.GetLocalized("global.Squad" + ((AdditionalPlayerInfo)lviPlayer.Tag).m_cpiPlayer.SquadID.ToString(), null);
                    }
                    else {
                        lviPlayer.SubItems["squad"].Text = String.Empty;
                    }
                }
            }
        }


        private void UpdateTeamNames() {
            // All four lists have the same number of groups in them..
            for (int i = 0; i < uscPlayerListPanel.INT_MAX_TEAMS; i++) {

                string score = String.Empty;

                if (this.m_prcClient != null && this.m_prcClient.CurrentServerInfo != null && this.m_prcClient.CurrentServerInfo.TeamScores != null) {

                    if (i > 0 && this.m_prcClient.CurrentServerInfo.TeamScores.Count > i - 1) {
                        score = String.Format(" - {0} {1}", this.m_prcClient.CurrentServerInfo.TeamScores[i - 1].Score, this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.Groups.Tickets"));
                    }
                }

                this.lsvTeamOnePlayers.Groups[i].Header = String.Format("{1} - {0}{2}", this.m_prcClient.GetLocalizedTeamName(i, this.m_prcClient.CurrentServerInfo.Map), this.lsvTeamOnePlayers.Groups[i].Items.Count - 2, score);
                this.lsvTeamTwoPlayers.Groups[i].Header = String.Format("{1} - {0}{2}", this.m_prcClient.GetLocalizedTeamName(i, this.m_prcClient.CurrentServerInfo.Map), this.lsvTeamTwoPlayers.Groups[i].Items.Count - 2, score);
                this.lsvTeamThreePlayers.Groups[i].Header = String.Format("{1} - {0}{2}", this.m_prcClient.GetLocalizedTeamName(i, this.m_prcClient.CurrentServerInfo.Map), this.lsvTeamThreePlayers.Groups[i].Items.Count - 2, score);
                this.lsvTeamFourPlayers.Groups[i].Header = String.Format("{1} - {0}{2}", this.m_prcClient.GetLocalizedTeamName(i, this.m_prcClient.CurrentServerInfo.Map), this.lsvTeamFourPlayers.Groups[i].Items.Count - 2, score);
            }
        }

        private void SetTotalsZero(int iTeamID) {
            if (this.m_dicPlayers.ContainsKey(String.Format("procon.playerlist.totals{0}", iTeamID)) == true) {

                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).KitCounter.Clear();
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills = 0;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths = 0;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score = 0;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping = 0;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID = 0;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr = 0.0F;

                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kit"].Text = String.Empty;
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Text = "0";
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Text = "0";
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Text = "0";
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Text = String.Empty;
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kdr"].Text = "0.00";

                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kit"].Text = String.Empty;
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kills"].Text = "0.00";
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["deaths"].Text = "0.00";
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["score"].Text = "0.00";
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["ping"].Text = "0.00";
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kdr"].Text = "0.00";
            }
        }

        private void AddTotalsPlayerDetails(int iTeamID, AdditionalPlayerInfo player) {
            if (this.m_dicPlayers.ContainsKey(String.Format("procon.playerlist.totals{0}", iTeamID)) == true) {

                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills += player.m_cpiPlayer.Kills;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths += player.m_cpiPlayer.Deaths;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score += player.m_cpiPlayer.Score; ;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping += player.m_cpiPlayer.Ping;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr += (player.m_cpiPlayer.Deaths > 0 ? (float)player.m_cpiPlayer.Kills / (float)player.m_cpiPlayer.Deaths : player.m_cpiPlayer.Kills);
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID++;

                if (player.m_spawnedInventory != null) {
                    ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).AddKitCount(player.m_spawnedInventory.Kit);
                }

                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Text = ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills.ToString();
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Text = ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths.ToString();
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Text = ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score.ToString();
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Text = ((SAdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping.ToString();
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kdr"].Text = String.Format("{0:0.00}", ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr);

                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kills"].Text = String.Format("{0:0.00}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["deaths"].Text = String.Format("{0:0.00}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["score"].Text = String.Format("{0:0.00}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["ping"].Text = String.Format("{0:0}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kdr"].Text = String.Format("{0:0.00}", ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);

                int mostUsedKitCount = 0;
                Kits mostUsedKit = Kits.None;
                List<string> kitTotals = new List<string>();

                foreach (KeyValuePair<Kits, int> kitCount in ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).KitCounter) {
                    if (kitCount.Value > mostUsedKitCount) {
                        mostUsedKitCount = kitCount.Value;
                        mostUsedKit = kitCount.Key;
                    }

                    kitTotals.Add(String.Format("{0}{1}", kitCount.Value, this.m_clocLanguage.GetLocalized(String.Format("global.Kits.{0}.Short", kitCount.Key.ToString()))));
                }

                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kit"].Text = String.Join(",", kitTotals.ToArray());

                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kit"].Text = this.m_clocLanguage.GetLocalized(String.Format("global.Kits.{0}", mostUsedKit.ToString()));

                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Tag) + cpiPlayer.Kills;
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Tag) + cpiPlayer.Deaths;
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Tag) + cpiPlayer.Score;
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Tag) + cpiPlayer.Ping;
            }
        }

        *//*
        private void AddTotalsPlayerDetails(int iTeamID, CPlayerInfo cpiPlayer) {
            if (this.m_dicPlayers.ContainsKey(String.Format("procon.playerlist.totals{0}", iTeamID)) == true) {

                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills += cpiPlayer.Kills;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths += cpiPlayer.Deaths;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score += cpiPlayer.Score; ;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping += cpiPlayer.Ping;
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr += (cpiPlayer.Deaths > 0 ? (float)cpiPlayer.Kills / (float)cpiPlayer.Deaths : cpiPlayer.Kills);
                ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID++;

                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Text = ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills.ToString();
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Text = ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths.ToString();
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Text = ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score.ToString();
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Text = ((SAdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping.ToString();
                this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kdr"].Text = String.Format("{0:0.00}", ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr);

                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kills"].Text = String.Format("{0:0.00}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kills / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["deaths"].Text = String.Format("{0:0.00}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Deaths / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["score"].Text = String.Format("{0:0.00}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Score / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["ping"].Text = String.Format("{0:0}", (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Ping / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);
                this.m_dicPlayers[String.Format("procon.playerlist.averages{0}", iTeamID)].SubItems["kdr"].Text = String.Format("{0:0.00}", ((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.Kdr / (float)((AdditionalPlayerInfo)this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].Tag).m_cpiPlayer.SquadID);



                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["kills"].Tag) + cpiPlayer.Kills;
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["deaths"].Tag) + cpiPlayer.Deaths;
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["score"].Tag) + cpiPlayer.Score;
                //this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Tag = (int)(this.m_dicPlayers[String.Format("procon.playerlist.totals{0}", iTeamID)].SubItems["ping"].Tag) + cpiPlayer.Ping;
            }
        }
        *//*

        // Simply puts the players into the correct list.
        private void ArrangePlayers() {

            this.m_blPropogatingIndexChange = true;
            if (this.m_prcClient != null) {

                this.SetTotalsZero(1);
                this.SetTotalsZero(2);
                this.SetTotalsZero(3);
                this.SetTotalsZero(4);

                if (this.m_prcClient.PlayerListSettings.SplitType == 1) {
                    this.lsvTeamOnePlayers.BeginUpdate();

                    foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {

                        int iPlayerTeamID = this.GetPlayerTeamID(kvpPlayer.Value);
                        bool isTotalsPlayer = this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(kvpPlayer.Key);

                        if (isTotalsPlayer == false) {
                            this.AddTotalsPlayerDetails(iPlayerTeamID, (AdditionalPlayerInfo)kvpPlayer.Value.Tag);
                        }

                        if (isTotalsPlayer == false || this.GetTotalPlayersByTeamID(iPlayerTeamID) > 0) {

                            if (this.lsvTeamOnePlayers.Items.ContainsKey(kvpPlayer.Key) == false) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamOnePlayers.Groups[iPlayerTeamID];
                                this.lsvTeamOnePlayers.Items.Add(kvpPlayer.Value);
                            }
                            else {
                                kvpPlayer.Value.Group = this.lsvTeamOnePlayers.Groups[iPlayerTeamID];
                            }

                        }
                    }

                    this.lsvTeamOnePlayers.EndUpdate();
                }
                else if (this.m_prcClient.PlayerListSettings.SplitType == 2) {
                    this.lsvTeamOnePlayers.BeginUpdate();
                    this.lsvTeamTwoPlayers.BeginUpdate();

                    foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                        int iTeamID = this.GetPlayerTeamID(kvpPlayer.Value);
                        bool isTotalsPlayer = this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(kvpPlayer.Key); ;

                        if (isTotalsPlayer == false) {
                            this.AddTotalsPlayerDetails(iTeamID, (AdditionalPlayerInfo)kvpPlayer.Value.Tag);
                        }

                        if (isTotalsPlayer == false || this.GetTotalPlayersByTeamID(iTeamID) > 0) {

                            if (this.lsvTeamOnePlayers.Items.ContainsKey(kvpPlayer.Key) == false && iTeamID == 1 || iTeamID == 3) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamOnePlayers.Groups[iTeamID];
                                this.lsvTeamOnePlayers.Items.Add(kvpPlayer.Value);
                            }
                            else if (this.lsvTeamOnePlayers.Items.ContainsKey(kvpPlayer.Key) == true && iTeamID == 1 || iTeamID == 3) {
                                kvpPlayer.Value.Group = this.lsvTeamOnePlayers.Groups[iTeamID];
                            }
                            else if (this.lsvTeamTwoPlayers.Items.ContainsKey(kvpPlayer.Key) == false && iTeamID == 2 || iTeamID == 4) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamTwoPlayers.Groups[iTeamID];
                                this.lsvTeamTwoPlayers.Items.Add(kvpPlayer.Value);
                            }
                            else if (this.lsvTeamOnePlayers.Items.ContainsKey(kvpPlayer.Key) == true && iTeamID == 2 || iTeamID == 4) {
                                kvpPlayer.Value.Group = this.lsvTeamOnePlayers.Groups[iTeamID];
                            }
                        }
                    }

                    this.lsvTeamTwoPlayers.EndUpdate();
                    this.lsvTeamOnePlayers.EndUpdate();
                }
                else if (this.m_prcClient.PlayerListSettings.SplitType == 4) {
                    this.lsvTeamOnePlayers.BeginUpdate();
                    this.lsvTeamTwoPlayers.BeginUpdate();
                    this.lsvTeamThreePlayers.BeginUpdate();
                    this.lsvTeamFourPlayers.BeginUpdate();

                    foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                        int iTeamID = this.GetPlayerTeamID(kvpPlayer.Value);
                        bool isTotalsPlayer = this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(kvpPlayer.Key);

                        if (isTotalsPlayer == false) {
                            this.AddTotalsPlayerDetails(iTeamID, (AdditionalPlayerInfo)kvpPlayer.Value.Tag);
                        }

                        if (isTotalsPlayer == false || this.GetTotalPlayersByTeamID(iTeamID) > 0) {

                            if (this.lsvTeamOnePlayers.Items.ContainsKey(kvpPlayer.Key) == false && iTeamID == 1) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamOnePlayers.Groups[iTeamID];
                                this.lsvTeamOnePlayers.Items.Add(kvpPlayer.Value);
                            }
                            else if (this.lsvTeamTwoPlayers.Items.ContainsKey(kvpPlayer.Key) == false && iTeamID == 2) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamTwoPlayers.Groups[iTeamID];
                                this.lsvTeamTwoPlayers.Items.Add(kvpPlayer.Value);
                            }
                            else if (this.lsvTeamThreePlayers.Items.ContainsKey(kvpPlayer.Key) == false && iTeamID == 3) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamThreePlayers.Groups[iTeamID];
                                this.lsvTeamThreePlayers.Items.Add(kvpPlayer.Value);
                            }
                            else if (this.lsvTeamFourPlayers.Items.ContainsKey(kvpPlayer.Key) == false && iTeamID == 4) {
                                kvpPlayer.Value.Remove();

                                kvpPlayer.Value.Group = this.lsvTeamFourPlayers.Groups[iTeamID];
                                this.lsvTeamFourPlayers.Items.Add(kvpPlayer.Value);
                            }
                        }
                    }

                    this.lsvTeamFourPlayers.EndUpdate();
                    this.lsvTeamThreePlayers.EndUpdate();
                    this.lsvTeamTwoPlayers.EndUpdate();
                    this.lsvTeamOnePlayers.EndUpdate();

                    GC.Collect();
                }

                this.UpdateTeamNames();

                // All three lists have the same number of columns in them..
                for (int i = 0; i < this.lsvTeamOnePlayers.Columns.Count; i++) {
                    this.lsvTeamOnePlayers.Columns[i].Width = -2;
                    this.lsvTeamTwoPlayers.Columns[i].Width = -2;
                    this.lsvTeamThreePlayers.Columns[i].Width = -2;
                    this.lsvTeamFourPlayers.Columns[i].Width = -2;
                }

                this.lsvTeamOnePlayers.Sort();
                this.lsvTeamTwoPlayers.Sort();
                this.lsvTeamThreePlayers.Sort();
                this.lsvTeamFourPlayers.Sort();
            }
            this.m_blPropogatingIndexChange = false;
        }

        private void m_prcClient_PlayerLeft(FrostbiteClient sender, string playerName, CPlayerInfo cpiPlayer) {
            if (this.m_dicPlayers.ContainsKey(playerName) == true) {
                this.m_dicPlayers[playerName].Remove();
                this.m_dicPlayers.Remove(playerName);
            }

            this.UpdateTeamNames();

            this.RefreshSelectedPlayer();
        }

        private void m_prcClient_PlayerJoin(FrostbiteClient sender, string playerName) {
            if (this.m_dicPlayers.ContainsKey(playerName) == false) {
                this.m_dicPlayers.Add(playerName, this.CreatePlayer(new CPlayerInfo(playerName, String.Empty, 0, 0)));

                this.ArrangePlayers();
            }
        }

        *//*
        public void OnPlayerJoin(string strSoldierName) {

        }

        public void OnPlayerLeave(string strSoldierName) {


        }
         *
        *//*

        private void m_prcClient_PlayerSpawned(PRoConClient sender, string soldierName, Inventory spawnedInventory) {

            this.m_blPropogatingIndexChange = true;

            if (this.m_dicPlayers.ContainsKey(soldierName) == true) {
                AdditionalPlayerInfo sapiAdditional;

                if (this.m_dicPlayers[soldierName].Tag != null) {
                    sapiAdditional = (AdditionalPlayerInfo)this.m_dicPlayers[soldierName].Tag;

                    sapiAdditional.m_spawnedInventory = spawnedInventory;

                    if (this.m_dicPlayers.ContainsKey(soldierName) == true) {
                        this.m_dicPlayers[soldierName].SubItems["kit"].Text = this.m_clocLanguage.GetLocalized(String.Format("global.Kits.{0}", spawnedInventory.Kit.ToString()));
                    }

                    if (sapiAdditional.m_pbInfo != null) {
                        if (this.m_frmMain.iglFlags.Images.ContainsKey(sapiAdditional.m_pbInfo.PlayerCountryCode + ".png") == true) {
                            this.m_dicPlayers[sapiAdditional.m_pbInfo.SoldierName].ImageIndex = this.m_frmMain.iglFlags.Images.IndexOfKey(sapiAdditional.m_pbInfo.PlayerCountryCode + ".png");
                        }
                    }

                    this.m_dicPlayers[soldierName].Tag = sapiAdditional;
                }

                this.RefreshSelectedPlayer();
            }

            this.ArrangePlayers();

            this.m_blPropogatingIndexChange = false;
        }

        private void m_prcClient_PunkbusterPlayerInfo(PRoConClient sender, CPunkbusterInfo pbInfo) {
            this.m_blPropogatingIndexChange = true;

            if (this.m_dicPlayers.ContainsKey(pbInfo.SoldierName) == true) {

                AdditionalPlayerInfo sapiAdditional;

                if (this.m_dicPlayers[pbInfo.SoldierName].Tag == null) {
                    sapiAdditional = new AdditionalPlayerInfo();
                    sapiAdditional.m_strResolvedHostName = String.Empty;
                }
                else {
                    sapiAdditional = (AdditionalPlayerInfo)this.m_dicPlayers[pbInfo.SoldierName].Tag;
                }

                sapiAdditional.m_pbInfo = pbInfo;

                this.m_dicPlayers[pbInfo.SoldierName].Tag = sapiAdditional;

                this.m_dicPlayers[pbInfo.SoldierName].Text = pbInfo.SlotID;

                //string strCountryCode = this.m_frmMain.GetCountryCode(pbInfo.Ip);
                if (this.m_frmMain.iglFlags.Images.ContainsKey(pbInfo.PlayerCountryCode + ".png") == true && this.m_dicPlayers[sapiAdditional.m_pbInfo.SoldierName].ImageIndex < 0) {
                    this.m_dicPlayers[pbInfo.SoldierName].ImageIndex = this.m_frmMain.iglFlags.Images.IndexOfKey(pbInfo.PlayerCountryCode + ".png");
                }

                this.RefreshSelectedPlayer();
            }

            this.m_blPropogatingIndexChange = false;
        }

        *//*
        public void OnPlayerPunkbusterInfo(CPunkbusterInfo pbInfo) {


        }
        *//*

        private void m_prcClient_ListPlayers(FrostbiteClient sender, List<CPlayerInfo> lstPlayers, CPlayerSubset cpsSubset) {
            if (cpsSubset.Subset == CPlayerSubset.PlayerSubsetType.All) {
                foreach (CPlayerInfo cpiPlayer in lstPlayers) {
                    if (this.m_dicPlayers.ContainsKey(cpiPlayer.SoldierName) == true) {

                        //if (this.m_blSplitList == false) {
                        //    this.m_dicPlayers[cpiPlayer.SoldierName].Group = this.lsvSinglePlayers.Groups[cpiPlayer.TeamId];
                        //}

                        if (this.m_prcClient != null && this.m_prcClient.Game != null && this.m_prcClient.Game.HasSquads == true) {
                            if (cpiPlayer.SquadID != uscPlayerListPanel.INT_NEUTRAL_SQUAD) {
                                this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["squad"].Text = this.m_clocLanguage.GetLocalized("global.Squad" + cpiPlayer.SquadID.ToString(), null);
                            }
                            else {
                                this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["squad"].Text = String.Empty;
                            }
                        }

                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["tags"].Text = cpiPlayer.ClanTag;

                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["score"].Text = cpiPlayer.Score.ToString();
                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["kills"].Tag = (Double)cpiPlayer.Kills;
                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["kills"].Text = cpiPlayer.Kills.ToString();

                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["deaths"].Tag = (Double)cpiPlayer.Deaths;
                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["deaths"].Text = cpiPlayer.Deaths.ToString();

                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["kdr"].Text = cpiPlayer.Deaths > 0 ? String.Format("{0:0.00}", (Double)cpiPlayer.Kills / (Double)cpiPlayer.Deaths) : String.Format("{0:0.00}", (Double)cpiPlayer.Kills);

                        this.m_dicPlayers[cpiPlayer.SoldierName].SubItems["ping"].Text = cpiPlayer.Ping.ToString();

                        AdditionalPlayerInfo sapiAdditional;

                        if (this.m_dicPlayers[cpiPlayer.SoldierName].Tag == null) {
                            sapiAdditional = new AdditionalPlayerInfo();
                            sapiAdditional.m_strResolvedHostName = String.Empty;
                        }
                        else {
                            sapiAdditional = (AdditionalPlayerInfo)this.m_dicPlayers[cpiPlayer.SoldierName].Tag;
                        }

                        sapiAdditional.m_cpiPlayer = cpiPlayer;
                        this.m_dicPlayers[cpiPlayer.SoldierName].Tag = sapiAdditional;
                    }
                    else {
                        this.m_dicPlayers.Add(cpiPlayer.SoldierName, this.CreatePlayer(cpiPlayer));
                    }
                }

                List<string> lstKeys = new List<string>(this.m_dicPlayers.Keys);

                for (int i = 0; i < lstKeys.Count; i++) {
                    bool blFoundPlayer = false;

                    foreach (CPlayerInfo cpiPlayer in lstPlayers) {
                        if (String.Compare(cpiPlayer.SoldierName, this.m_dicPlayers[lstKeys[i]].Name) == 0) {
                            blFoundPlayer = true;
                            break;
                        }
                    }

                    if (blFoundPlayer == false) {
                        this.m_dicPlayers[lstKeys[i]].Remove();
                        this.m_dicPlayers.Remove(lstKeys[i]);
                    }
                }

                this.m_dicPlayers.Add("procon.playerlist.totals1", this.CreateTotalsPlayer(new CPlayerInfo("Totals", "procon.playerlist.totals1", 1, 0), 1));
                this.m_dicPlayers.Add("procon.playerlist.totals2", this.CreateTotalsPlayer(new CPlayerInfo("Totals", "procon.playerlist.totals2", 2, 0), 2));
                this.m_dicPlayers.Add("procon.playerlist.totals3", this.CreateTotalsPlayer(new CPlayerInfo("Totals", "procon.playerlist.totals3", 3, 0), 3));
                this.m_dicPlayers.Add("procon.playerlist.totals4", this.CreateTotalsPlayer(new CPlayerInfo("Totals", "procon.playerlist.totals4", 4, 0), 4));

                this.m_dicPlayers.Add("procon.playerlist.averages1", this.CreateTotalsPlayer(new CPlayerInfo("Averages", "procon.playerlist.averages1", 1, 0), 1));
                this.m_dicPlayers.Add("procon.playerlist.averages2", this.CreateTotalsPlayer(new CPlayerInfo("Averages", "procon.playerlist.averages2", 2, 0), 2));
                this.m_dicPlayers.Add("procon.playerlist.averages3", this.CreateTotalsPlayer(new CPlayerInfo("Averages", "procon.playerlist.averages3", 3, 0), 3));
                this.m_dicPlayers.Add("procon.playerlist.averages4", this.CreateTotalsPlayer(new CPlayerInfo("Averages", "procon.playerlist.averages4", 4, 0), 4));

                this.ArrangePlayers();
            }
        }

        *//*
        public void OnPlayerList(List<CPlayerInfo> lstPlayers) {


        }
        *//*

        private void m_prcClient_LevelStarted(FrostbiteClient sender) {
            foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                kvpPlayer.Value.SubItems["score"].Text = String.Empty;
                kvpPlayer.Value.SubItems["kills"].Tag = new Double();
                kvpPlayer.Value.SubItems["kills"].Text = String.Empty;
                kvpPlayer.Value.SubItems["deaths"].Tag = new Double();
                kvpPlayer.Value.SubItems["deaths"].Text = String.Empty;
                kvpPlayer.Value.SubItems["kdr"].Text = String.Empty;
                kvpPlayer.Value.SubItems["kit"].Text = String.Empty;

                if (kvpPlayer.Value.ImageIndex >= 0) {
                    kvpPlayer.Value.ImageIndex = this.m_frmMain.iglFlags.Images.IndexOfKey("flag_death.png");
                }

                if (kvpPlayer.Value.Tag != null) {
                    ((AdditionalPlayerInfo)kvpPlayer.Value.Tag).m_spawnedInventory = null;
                }
            }
        }

        private void chkPlayerListShowTeams_CheckedChanged(object sender, EventArgs e) {
            this.lsvTeamOnePlayers.ShowGroups = this.lsvTeamTwoPlayers.ShowGroups = this.lsvTeamThreePlayers.ShowGroups = this.lsvTeamFourPlayers.ShowGroups = this.chkPlayerListShowTeams.Checked;

            this.ArrangePlayers();
        }

        private void btnCloseAdditionalInfo_Click(object sender, EventArgs e) {
            this.SelectNoPlayer();

            this.ClearPunishmentPanel();
        }

        private void uscPlayerListPanel_Resize(object sender, EventArgs e) {

            this.lsvTeamOnePlayers.Scrollable = false;
            this.lsvTeamTwoPlayers.Scrollable = false;
            this.lsvTeamThreePlayers.Scrollable = false;
            this.lsvTeamFourPlayers.Scrollable = false;

            this.SetSplitterDistances();

            this.lsvTeamOnePlayers.Scrollable = true;
            this.lsvTeamTwoPlayers.Scrollable = true;
            this.lsvTeamThreePlayers.Scrollable = true;
            this.lsvTeamFourPlayers.Scrollable = true;
        }

        private void ClearPunishmentPanel() {

            if (this.m_blPropogatingIndexChange == false) {

                this.kbpBfbcPunishPanel.SoldierName = String.Empty;
                this.kbpPunkbusterPunishPanel.SoldierName = String.Empty;

                this.txtPlayerListSelectedIP.Text = String.Empty;
                this.lblPlayerListSelectedName.Text = String.Empty;
                this.txtPlayerListSelectedGUID.Text = String.Empty;
                this.txtPlayerListSelectedBc2GUID.Text = String.Empty;

                this.pnlAdditionalInfo.Enabled = false;
                this.tbcCourtMartial.Enabled = false;

                this.spltListAdditionalInfo.Panel2Collapsed = true;
                this.btnCloseAdditionalInfo.Enabled = false;
            }
        }

        private bool m_blPropogatingIndexChange = false;
        private void SelectPlayer(string strPlayerName) {

            this.m_blPropogatingIndexChange = true;

            foreach (ListViewItem lviPlayer in this.lsvTeamOnePlayers.Items) {
                if (String.Compare(lviPlayer.Name, strPlayerName) == 0) {
                    lviPlayer.Selected = true;
                }
                else {
                    lviPlayer.Selected = false;
                }
            }

            foreach (ListViewItem lviPlayer in this.lsvTeamTwoPlayers.Items) {
                if (String.Compare(lviPlayer.Name, strPlayerName) == 0) {
                    lviPlayer.Selected = true;
                }
                else {
                    lviPlayer.Selected = false;
                }
            }

            foreach (ListViewItem lviPlayer in this.lsvTeamThreePlayers.Items) {
                if (String.Compare(lviPlayer.Name, strPlayerName) == 0) {
                    lviPlayer.Selected = true;
                }
                else {
                    lviPlayer.Selected = false;
                }
            }

            foreach (ListViewItem lviPlayer in this.lsvTeamFourPlayers.Items) {
                if (String.Compare(lviPlayer.Name, strPlayerName) == 0) {
                    lviPlayer.Selected = true;
                }
                else {
                    lviPlayer.Selected = false;
                }
            }

            this.m_blPropogatingIndexChange = false;
        }

        private void SelectNoPlayer() {
            this.m_blPropogatingIndexChange = true;

            foreach (ListViewItem lviPlayer in this.lsvTeamOnePlayers.Items) {
                lviPlayer.Selected = false;
            }

            foreach (ListViewItem lviPlayer in this.lsvTeamTwoPlayers.Items) {
                lviPlayer.Selected = false;
            }

            foreach (ListViewItem lviPlayer in this.lsvTeamThreePlayers.Items) {
                lviPlayer.Selected = false;
            }

            foreach (ListViewItem lviPlayer in this.lsvTeamFourPlayers.Items) {
                lviPlayer.Selected = false;
            }

            this.m_blPropogatingIndexChange = false;

            this.ClearPunishmentPanel();
        }

        private void lsvTeamOnePlayers_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.lsvTeamOnePlayers.SelectedItems.Count > 0 && this.m_blPropogatingIndexChange == false) {
                this.SelectPlayer(this.lsvTeamOnePlayers.SelectedItems[0].Name);
            }
            else if (this.lsvTeamOnePlayers.FocusedItem != null && this.m_blPropogatingIndexChange == false) {
                this.SelectNoPlayer();
            }
        }

        private void lsvTeamTwoPlayers_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.lsvTeamTwoPlayers.SelectedItems.Count > 0 && this.m_blPropogatingIndexChange == false) {
                this.SelectPlayer(this.lsvTeamTwoPlayers.SelectedItems[0].Name);
            }
            else if (this.lsvTeamTwoPlayers.FocusedItem != null && this.m_blPropogatingIndexChange == false) {
                this.SelectNoPlayer();
            }
        }

        private void RefreshSelectedPlayer() {
            if (this.lsvTeamOnePlayers.SelectedItems.Count > 0) {
                this.lsvPlayers_SelectedIndexChanged(this.lsvTeamOnePlayers, null);
            }
            else if (this.lsvTeamTwoPlayers.SelectedItems.Count > 0) {
                this.lsvPlayers_SelectedIndexChanged(this.lsvTeamTwoPlayers, null);
            }
            else if (this.lsvTeamThreePlayers.SelectedItems.Count > 0) {
                this.lsvPlayers_SelectedIndexChanged(this.lsvTeamThreePlayers, null);
            }
            else if (this.lsvTeamFourPlayers.SelectedItems.Count > 0) {
                this.lsvPlayers_SelectedIndexChanged(this.lsvTeamFourPlayers, null);
            }
            else {
                this.ClearPunishmentPanel();
            }
        }

        private void lsvPlayers_SelectedIndexChanged(object sender, EventArgs e) {

            if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems.Count > 0 && this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Name)== false) {

                if (this.m_blPropogatingIndexChange == false) {
                    this.SelectPlayer(((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Name);
                }

                if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems.Count > 0) {

                    this.spltListAdditionalInfo.Panel2Collapsed = false;
                    this.spltInfoPunish.Panel1MinSize = 306;
                    this.spltInfoPunish.Panel2MinSize = 191;
                    this.spltInfoPunish.FixedPanel = FixedPanel.Panel2;
                    this.btnCloseAdditionalInfo.Enabled = true;

                    this.kbpBfbcPunishPanel.SoldierName = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Name;
                    this.kbpPunkbusterPunishPanel.SoldierName = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Name;
                    this.tbcCourtMartial.Enabled = true;

                    //PunkbusterInfo pbInfo = null;
                    //string strCountryName = String.Empty;

                    AdditionalPlayerInfo sapiAdditional = (AdditionalPlayerInfo)((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Tag;

                    if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Tag != null && sapiAdditional.m_pbInfo != null) {

                        //string strResolvedHost = (string)((object[])this.lsvPlayers.SelectedItems[0].Tag)[2];

                        this.lblPlayerListSelectedName.Text = String.Format("{0} {1} ({2})", ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].SubItems["tags"].Text, ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].SubItems["soldiername"].Text, sapiAdditional.m_pbInfo.PlayerCountry);

                        // new string[] { strID, strSoldierName, strGUID, strIP, this.m_frmParent.GetCountryName(strIP) }
                        this.txtPlayerListSelectedGUID.Text = sapiAdditional.m_pbInfo.GUID;

                        string[] a_strSplitIp = sapiAdditional.m_pbInfo.Ip.Split(':');

                        if (sapiAdditional.m_strResolvedHostName.Length > 0) {
                            this.txtPlayerListSelectedIP.Text = String.Format("{0} ({1})", sapiAdditional.m_pbInfo.Ip, sapiAdditional.m_strResolvedHostName);
                        }
                        else {
                            if (this.ResolvePlayerHost() == true && a_strSplitIp.Length >= 1) {
                                try {
                                    SResolvePlayerIP srpResolve = new SResolvePlayerIP();
                                    srpResolve.lviPlayer = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0];
                                    srpResolve.plpListPanel = this;

                                    Dns.BeginGetHostEntry(IPAddress.Parse(a_strSplitIp[0]), m_asyncResolvePlayerIP, srpResolve);
                                }
                                catch (Exception) { }
                            }

                            this.txtPlayerListSelectedIP.Text = sapiAdditional.m_pbInfo.Ip;
                        }

                        this.pnlAdditionalInfo.Enabled = true;

                        this.kbpPunkbusterPunishPanel.SlotID = sapiAdditional.m_pbInfo.SlotID;
                        this.kbpPunkbusterPunishPanel.IP = a_strSplitIp.Length > 0 ? a_strSplitIp[0] : String.Empty;
                        this.kbpPunkbusterPunishPanel.GUID = sapiAdditional.m_pbInfo.GUID;
                        this.kbpBfbcPunishPanel.IP = a_strSplitIp.Length > 0 ? a_strSplitIp[0] : String.Empty;

                        this.kbpPunkbusterPunishPanel.Enabled = true && (!this.m_spPrivileges.CannotPunishPlayers && this.m_spPrivileges.CanIssueLimitedPunkbusterCommands);
                    }
                    else {
                        this.lblPlayerListSelectedName.Text = String.Format("{0} {1}", ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].SubItems["tags"].Text, ((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].SubItems["soldiername"].Text);
                        //this.tabCourtMartialPunkbuster.Hide();
                        this.txtPlayerListSelectedGUID.Text = String.Empty;
                        this.txtPlayerListSelectedIP.Text = String.Empty;

                        this.kbpPunkbusterPunishPanel.SlotID = String.Empty;
                        this.kbpPunkbusterPunishPanel.IP = String.Empty;
                        this.kbpPunkbusterPunishPanel.GUID = String.Empty;
                        this.kbpBfbcPunishPanel.IP = String.Empty;
                        this.kbpPunkbusterPunishPanel.Enabled = false;
                    }

                    if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Tag != null && sapiAdditional.m_cpiPlayer != null) {
                        this.txtPlayerListSelectedBc2GUID.Text = sapiAdditional.m_cpiPlayer.GUID;
                        this.kbpBfbcPunishPanel.GUID = sapiAdditional.m_cpiPlayer.GUID;

                        this.pnlAdditionalInfo.Enabled = true;
                    }
                    else {
                        this.txtPlayerListSelectedBc2GUID.Text = String.Empty;
                        this.kbpBfbcPunishPanel.GUID = String.Empty;
                    }

                    if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).SelectedItems[0].Tag != null && sapiAdditional.m_spawnedInventory != null) {

                        //List<string> inventory = new List<string>();

                        string[] inventory = new string[6];

                        foreach (Weapon weapon in sapiAdditional.m_spawnedInventory.Weapons) {

                            int weaponSlot = -1;

                            if (weapon.Slot == WeaponSlots.Primary) {
                                weaponSlot = 0;
                            }
                            else if (weapon.Slot == WeaponSlots.Auxiliary) {
                                weaponSlot = 1;
                            }
                            else if (weapon.Slot == WeaponSlots.Secondary) {
                                weaponSlot = 2;
                            }

                            if (weaponSlot >= 0) {
                                inventory[weaponSlot] = this.m_clocLanguage.GetLocalized(String.Format("global.Weapons.{0}", weapon.Name.ToLower()));
                            }
                        }

                        int specializationSlot = 3;

                        foreach (Specialization spec in sapiAdditional.m_spawnedInventory.Specializations) {
                            inventory[specializationSlot++] = this.m_clocLanguage.GetLocalized(String.Format("global.Specialization.{0}", spec.Name));
                        }

                        List<string> inventoryList = new List<string>(inventory);
                        inventoryList.RemoveAll(String.IsNullOrEmpty);
                        this.lblPlayersInventory.Text = String.Join(", ", inventoryList.ToArray());
                    }
                    else {
                        this.lblPlayersInventory.Text = String.Empty;
                    }

                }
            }
            else if ((((PRoCon.Controls.ControlsEx.ListViewNF)sender).FocusedItem != null || ((((PRoCon.Controls.ControlsEx.ListViewNF)sender).FocusedItem != null && this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(((PRoCon.Controls.ControlsEx.ListViewNF)sender).FocusedItem.Name) == true) && this.m_blPropogatingIndexChange == false))) {
                this.SelectNoPlayer();
            }

            this.kbpBfbcPunishPanel.RefreshPanel();
            this.kbpPunkbusterPunishPanel.RefreshPanel();
        }

        private void m_prcClient_PlayerChangedTeam(FrostbiteClient sender, string strSoldierName, int iTeamID, int iSquadID) {
            this.m_blPropogatingIndexChange = true;

            lock (this.m_objPlayerDictionaryLocker) {

                if (this.m_dicPlayers.ContainsKey(strSoldierName) == true) {
                    this.SetPlayerTeamID(this.m_dicPlayers[strSoldierName], iTeamID);

                    this.ArrangePlayers();
                    // Save the SquadChange event for onSquadChange
                    //this.SetPlayerSquadID(this.m_dicPlayers[strSoldierName], iSquadID);
                }

            }

            this.m_blPropogatingIndexChange = false;
        }

        private void m_prcClient_PlayerChangedSquad(FrostbiteClient sender, string strSoldierName, int iTeamID, int iSquadID) {

            lock (this.m_objPlayerDictionaryLocker) {
                if (this.m_dicPlayers.ContainsKey(strSoldierName) == true) {
                    this.SetPlayerTeamID(this.m_dicPlayers[strSoldierName], iTeamID);
                    this.SetPlayerSquadID(this.m_dicPlayers[strSoldierName], iSquadID);
                }
            }
        }

        //public void OnPlayerTeamChange(string strSoldierName, int iTeamID, int iSquadID) {

        //}

        //public void OnPlayerSquadChange(string strSoldierName, int iTeamID, int iSquadID) {

        //}

        // Called by all three lists..
        private void lsvPlayers_ColumnClick(object sender, ColumnClickEventArgs e) {
            // Determine if clicked column is already the column that is being sorted.
            if (e.Column == this.m_lvwColumnSorter.SortColumn) {
                // Reverse the current sort direction for this column.
                if (this.m_lvwColumnSorter.Order == SortOrder.Ascending) {
                    this.m_lvwColumnSorter.Order = SortOrder.Descending;
                }
                else {
                    this.m_lvwColumnSorter.Order = SortOrder.Ascending;
                }
            }
            else {
                // Set the column number that is to be sorted; default to ascending.
                this.m_lvwColumnSorter.SortColumn = e.Column;
                this.m_lvwColumnSorter.Order = SortOrder.Ascending;
            }

            // Perform the sort with these new sort options.
            this.lsvTeamOnePlayers.Sort();
            this.lsvTeamTwoPlayers.Sort();
            this.lsvTeamThreePlayers.Sort();
            this.lsvTeamFourPlayers.Sort();

            //this.lsvTeamOnePlayers.
        }

        private AsyncCallback m_asyncResolvePlayerIP = new AsyncCallback(uscPlayerListPanel.ResolvePlayerIP);

        private struct SResolvePlayerIP {
            public ListViewItem lviPlayer;
            public uscPlayerListPanel plpListPanel;
        }

        public delegate void PlayerIPResolvedDelegate(ListViewItem lviPlayer, string strHostName);
        private void PlayerIPResolved(ListViewItem lviPlayer, string strHostName) {

            AdditionalPlayerInfo sapiAdditional = (AdditionalPlayerInfo)lviPlayer.Tag;
            sapiAdditional.m_strResolvedHostName = strHostName;
            lviPlayer.Tag = sapiAdditional;

            this.RefreshSelectedPlayer();
        }
        private static void ResolvePlayerIP(IAsyncResult ar) {

            try {
                if (ar != null) {
                    SResolvePlayerIP srpResolve = (SResolvePlayerIP)ar.AsyncState;
                    IPHostEntry ipHost = Dns.EndGetHostEntry(ar);
                    srpResolve.plpListPanel.Invoke(new PlayerIPResolvedDelegate(srpResolve.plpListPanel.PlayerIPResolved), new object[] { srpResolve.lviPlayer, ipHost.HostName });
                }
            }
            catch (Exception) { }
        }

        public bool ResolvePlayerHost() {
            return this.m_prcClient.Variables.GetVariable<bool>("RESOLVE_PLAYER_HOST", false);
        }

        private void btnSplitTeams_Click(object sender, EventArgs e) {

            if (this.m_prcClient != null) {

                if (this.m_prcClient.PlayerListSettings.SplitType == 1) {
                    this.m_prcClient.PlayerListSettings.SplitType = 2;
                    //this.btnSplitTeams.ImageKey = "application_tile.png";

                    //this.spltTwoSplit.Panel2Collapsed = false;
                    //this.spltFourSplit.Panel2Collapsed = true;
                }
                else if (this.m_prcClient.PlayerListSettings.SplitType == 2) {
                    this.m_prcClient.PlayerListSettings.SplitType = 4;
                    //this.btnSplitTeams.ImageKey = "application.png";

                    //this.spltTwoSplit.Panel2Collapsed = false;
                    //this.spltFourSplit.Panel2Collapsed = false;
                }
                else if (this.m_prcClient.PlayerListSettings.SplitType == 4) {
                    this.m_prcClient.PlayerListSettings.SplitType = 1;
                    //this.btnSplitTeams.ImageKey = "application_tile_horizontal.png";

                    //this.spltTwoSplit.Panel2Collapsed = true;
                    //this.spltFourSplit.Panel2Collapsed = true;
                }

                this.ArrangePlayers();
            }
        }

        private void m_prcClient_PlayerKilled(PRoConClient sender, Kill kKillerVictimDetails) {

            lock (this.m_objPlayerDictionaryLocker) {

                if (this.m_dicPlayers.ContainsKey(kKillerVictimDetails.Killer.SoldierName) == true && String.Compare(kKillerVictimDetails.Killer.SoldierName, kKillerVictimDetails.Victim.SoldierName, true) != 0) {

                    if (this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].Tag != null && ((AdditionalPlayerInfo)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].Tag).m_cpiPlayer != null) {
                        ((AdditionalPlayerInfo)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].Tag).m_cpiPlayer.Kills++;
                    }

                    this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kills"].Tag = ((Double)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kills"].Tag) + 1;
                    this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kills"].Text = ((Double)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kills"].Tag).ToString();

                    this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kdr"].Tag = kKillerVictimDetails;

                    if (this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["deaths"].Tag != null && (Double)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["deaths"].Tag > 0) {
                        this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kdr"].Text = String.Format("{0:0.00}", ((Double)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kills"].Tag / (Double)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["deaths"].Tag));
                    }
                    else {
                        this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kdr"].Text = String.Format("{0:0.00}", (Double)this.m_dicPlayers[kKillerVictimDetails.Killer.SoldierName].SubItems["kills"].Tag);
                    }
                }

                if (this.m_dicPlayers.ContainsKey(kKillerVictimDetails.Victim.SoldierName) == true || String.Compare(kKillerVictimDetails.Killer.SoldierName, kKillerVictimDetails.Victim.SoldierName, true) == 0) {
                    if (this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].Tag != null && ((AdditionalPlayerInfo)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].Tag).m_cpiPlayer != null) {
                        ((AdditionalPlayerInfo)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].Tag).m_cpiPlayer.Deaths++;
                    }

                    this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Tag = (Double)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Tag + 1;
                    this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Text = ((Double)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Tag).ToString();

                    this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["kdr"].Tag = kKillerVictimDetails;

                    if (this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Tag != null && (Double)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Tag > 0) {
                        this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["kdr"].Text = String.Format("{0:0.00}", ((Double)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["kills"].Tag / (Double)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["deaths"].Tag));
                    }
                    else {
                        this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["kdr"].Text = String.Format("{0:0.00}", (Double)this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].SubItems["kills"].Tag);
                    }

                    this.m_dicPlayers[kKillerVictimDetails.Victim.SoldierName].ImageIndex = this.m_frmMain.iglFlags.Images.IndexOfKey("flag_death.png");

                }

                this.tmrKillDeathHighlight.Enabled = true;
            }

            this.ArrangePlayers();
        }

        *//*
        public void OnPlayerKilled(string strKillerName, string strVictimName) {

            if (this.m_dicPlayers.ContainsKey(strKillerName) == true && String.Compare(strKillerName, strVictimName, true) != 0) {

                this.m_dicPlayers[strKillerName].SubItems["kills"].Tag = ((Double)this.m_dicPlayers[strKillerName].SubItems["kills"].Tag) + 1;
                this.m_dicPlayers[strKillerName].SubItems["kills"].Text = ((Double)this.m_dicPlayers[strKillerName].SubItems["kills"].Tag).ToString();

                if (this.m_dicPlayers[strKillerName].SubItems["deaths"].Tag != null && (Double)this.m_dicPlayers[strKillerName].SubItems["deaths"].Tag > 0) {
                    this.m_dicPlayers[strKillerName].SubItems["kdr"].Text = String.Format("{0:0.00}", ((Double)this.m_dicPlayers[strKillerName].SubItems["kills"].Tag / (Double)this.m_dicPlayers[strKillerName].SubItems["deaths"].Tag));
                }
                else {
                    this.m_dicPlayers[strKillerName].SubItems["kdr"].Text = String.Format("{0:0.00}", (Double)this.m_dicPlayers[strKillerName].SubItems["kills"].Tag);
                }
            }

            if (this.m_dicPlayers.ContainsKey(strVictimName) == true || String.Compare(strKillerName, strVictimName, true) == 0) {
                this.m_dicPlayers[strVictimName].SubItems["deaths"].Tag = (Double)this.m_dicPlayers[strVictimName].SubItems["deaths"].Tag + 1;
                this.m_dicPlayers[strVictimName].SubItems["deaths"].Text = ((Double)this.m_dicPlayers[strVictimName].SubItems["deaths"].Tag).ToString();

                if (this.m_dicPlayers[strVictimName].SubItems["deaths"].Tag != null && (Double)this.m_dicPlayers[strVictimName].SubItems["deaths"].Tag > 0) {
                    this.m_dicPlayers[strVictimName].SubItems["kdr"].Text = String.Format("{0:0.00}", ((Double)this.m_dicPlayers[strVictimName].SubItems["kills"].Tag / (Double)this.m_dicPlayers[strVictimName].SubItems["deaths"].Tag));
                }
                else {
                    this.m_dicPlayers[strVictimName].SubItems["kdr"].Text = String.Format("{0:0.00}", (Double)this.m_dicPlayers[strVictimName].SubItems["kills"].Tag);
                }
            }
        }
        *//*


        private void lsvPlayers_DragDrop(object sender, DragEventArgs e) {
            Point pntClient = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).PointToClient(new Point(e.X, e.Y));
            ListViewItem lviHover = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y);

            lviHover = lviHover == null ? ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y - 1) : lviHover;
            lviHover = lviHover == null ? ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y + 1) : lviHover;

            if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).Items.Count > 0 && lviHover == null) {
                // This includes the group header (team name)
                lviHover = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y + ((PRoCon.Controls.ControlsEx.ListViewNF)sender).Items[0].Bounds.Height);
            }

            CPlayerInfo cpiSwitchingPlayer = ((CPlayerInfo)e.Data.GetData(typeof(CPlayerInfo)));

            // TO DO: PunishPlayer should be renamed to SendCommand
            if (lviHover != null && lviHover.Tag != null && ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer != null && cpiSwitchingPlayer != null && cpiSwitchingPlayer.TeamID != ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer.TeamID) {
                if (Program.m_application.OptionsSettings.AdminMoveMessage)
                    this.m_prcClient.Game.SendAdminSayPacket("You have been moved to another team/squad by an admin.", new CPlayerSubset(CPlayerSubset.PlayerSubsetType.Player, cpiSwitchingPlayer.SoldierName));
                this.m_prcClient.Game.SendAdminMovePlayerPacket(cpiSwitchingPlayer.SoldierName, ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer.TeamID, this.m_prcClient.GetDefaultSquadIDByMapname(this.m_prcClient.CurrentServerInfo.Map), true);

                //this.m_prcClient.SendRequest(new List<string>() { "admin.say", "You have been moved to another team/squad by an admin.", "player", cpiSwitchingPlayer.SoldierName });
                //this.m_prcClient.SendRequest((new List<string>() { "admin.movePlayer", cpiSwitchingPlayer.SoldierName, ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer.TeamID.ToString(), this.m_prcClient.GetDefaultSquadIDByMapname(this.m_prcClient.CurrentServerInfo.Map).ToString(), "true" }));
            }

            this.HoverTeamBackground(null, -1);
        }

        private void lsvPlayers_DragEnter(object sender, DragEventArgs e) {
            e.Effect = DragDropEffects.Move;
        }

        private ListViewItem CreatePlaceHolder(PRoCon.Controls.ControlsEx.ListViewNF lsvList, int iTeamID) {
            ListViewItem lviPlaceHolder = new ListViewItem(".");
            lviPlaceHolder.ForeColor = SystemColors.WindowText;
            lviPlaceHolder.UseItemStyleForSubItems = true;

            AdditionalPlayerInfo sapiInfo = new AdditionalPlayerInfo();
            sapiInfo.m_strResolvedHostName = String.Empty;
            sapiInfo.m_pbInfo = null;
            sapiInfo.m_cpiPlayer = new CPlayerInfo("", String.Empty, iTeamID, 0);
            lviPlaceHolder.Tag = sapiInfo;

            lviPlaceHolder.Group = lsvList.Groups[iTeamID];

            return lviPlaceHolder;
        }

        private bool m_blPlaceHoldersDrawn = false;

        private void AddTeamPlaceHolders() {

            this.m_blPlaceHoldersDrawn = true;

            if (this.m_prcClient != null) {

                int iTeams = this.m_prcClient.GetLocalizedTeamNameCount(this.m_prcClient.CurrentServerInfo.Map);

                if (this.m_prcClient.PlayerListSettings.SplitType == 1) {
                    for (int i = 1; i < iTeams && i < this.lsvTeamOnePlayers.Groups.Count; i++) {
                        if (this.lsvTeamOnePlayers.Groups[i].Items.Count <= 0) {
                            this.lsvTeamOnePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamOnePlayers, i));
                        }
                    }
                }
                else if (this.m_prcClient.PlayerListSettings.SplitType == 2) {

                    if (iTeams == 5) {
                        if (this.lsvTeamOnePlayers.Groups[1].Items.Count <= 0) {
                            this.lsvTeamOnePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamOnePlayers, 1));
                        }

                        if (this.lsvTeamOnePlayers.Groups[3].Items.Count <= 0) {
                            this.lsvTeamOnePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamOnePlayers, 3));
                        }

                        if (this.lsvTeamTwoPlayers.Groups[2].Items.Count <= 0) {
                            this.lsvTeamTwoPlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamTwoPlayers, 2));
                        }

                        if (this.lsvTeamTwoPlayers.Groups[4].Items.Count <= 0) {
                            this.lsvTeamTwoPlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamTwoPlayers, 4));
                        }
                    }
                    else if (iTeams == 3) {
                        if (this.lsvTeamOnePlayers.Groups[1].Items.Count <= 0) {
                            this.lsvTeamOnePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamOnePlayers, 1));
                        }

                        if (this.lsvTeamTwoPlayers.Groups[2].Items.Count <= 0) {
                            this.lsvTeamTwoPlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamTwoPlayers, 2));
                        }
                    }
                }
                else if (this.m_prcClient.PlayerListSettings.SplitType == 4) {
                    if (iTeams == 5) {
                        if (this.lsvTeamOnePlayers.Groups[1].Items.Count <= 0) {
                            this.lsvTeamOnePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamOnePlayers, 1));
                        }

                        if (this.lsvTeamTwoPlayers.Groups[2].Items.Count <= 0) {
                            this.lsvTeamTwoPlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamTwoPlayers, 2));
                        }

                        if (this.lsvTeamThreePlayers.Groups[3].Items.Count <= 0) {
                            this.lsvTeamThreePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamThreePlayers, 3));
                        }

                        if (this.lsvTeamFourPlayers.Groups[4].Items.Count <= 0) {
                            this.lsvTeamFourPlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamFourPlayers, 4));
                        }
                    }
                    else if (iTeams == 3) {
                        if (this.lsvTeamOnePlayers.Groups[1].Items.Count <= 0) {
                            this.lsvTeamOnePlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamOnePlayers, 1));
                        }

                        if (this.lsvTeamTwoPlayers.Groups[2].Items.Count <= 0) {
                            this.lsvTeamTwoPlayers.Items.Add(this.CreatePlaceHolder(this.lsvTeamTwoPlayers, 2));
                        }
                    }
                }
            }
        }

        private void RemovePlaceHolders(PRoCon.Controls.ControlsEx.ListViewNF lsvList) {
            for (int i = 0; i < lsvList.Items.Count; i++) {
                if (String.Compare(lsvList.Items[i].Name, String.Empty) == 0) {
                    lsvList.Items[i].Remove();
                    i--;
                }
            }

            this.m_blPlaceHoldersDrawn = false;

            //this.ArrangePlayers();
        }

        public void BeginDragDrop() {
            this.AddTeamPlaceHolders();
        }

        public void EndDragDrop() {
            this.RemovePlaceHolders(this.lsvTeamOnePlayers);
            this.RemovePlaceHolders(this.lsvTeamTwoPlayers);
            this.RemovePlaceHolders(this.lsvTeamThreePlayers);
            this.RemovePlaceHolders(this.lsvTeamFourPlayers);
        }

        private void lsvPlayers_ItemDrag(object sender, ItemDragEventArgs e) {
            ListViewItem lviSelected = (ListViewItem)e.Item;

            if (e.Button == MouseButtons.Left) {

                if (lviSelected != null && lviSelected.Tag != null && ((AdditionalPlayerInfo)lviSelected.Tag).m_cpiPlayer != null && this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(lviSelected.Name) == false) {

                    if (this.m_uscConnectionPanel.BeginDragDrop() == true) {
                        ((PRoCon.Controls.ControlsEx.ListViewNF)sender).DoDragDrop(((AdditionalPlayerInfo)lviSelected.Tag).m_cpiPlayer, DragDropEffects.None | DragDropEffects.Move);

                        this.m_uscConnectionPanel.EndDragDrop();
                    }
                }
            }
        }

        private void SetPlaceHoldersColours(PRoCon.Controls.ControlsEx.ListViewNF lsvList, ListViewItem lviIgnoreItem, Color clForeColor, Color clBackColor) {
            for (int i = 0; i < lsvList.Items.Count; i++) {
                if (String.Compare(lsvList.Items[i].Name, String.Empty) == 0 && lsvList.Items[i] != lviIgnoreItem) {
                    lsvList.Items[i].BackColor = clBackColor;
                    lsvList.Items[i].ForeColor = clForeColor;
                }
            }
        }

        private void HoverTeamBackground(ListViewItem lviReserved, int iTeamID) {

            Color clLighLightHighlight = ControlPaint.LightLight(SystemColors.Highlight);

            foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                if (kvpPlayer.Value.Tag != null && ((AdditionalPlayerInfo)kvpPlayer.Value.Tag).m_cpiPlayer != null) {
                    if (((AdditionalPlayerInfo)kvpPlayer.Value.Tag).m_cpiPlayer.TeamID == iTeamID) {
                        kvpPlayer.Value.BackColor = clLighLightHighlight;
                        kvpPlayer.Value.ForeColor = SystemColors.HighlightText;
                    }
                    else {
                        kvpPlayer.Value.BackColor = SystemColors.Window;
                        kvpPlayer.Value.ForeColor = SystemColors.WindowText;
                    }
                }
            }

            if (lviReserved != null && String.Compare(lviReserved.Name, String.Empty) == 0) {
                lviReserved.BackColor = clLighLightHighlight;
                lviReserved.ForeColor = clLighLightHighlight;
            }

            this.SetPlaceHoldersColours(this.lsvTeamOnePlayers, lviReserved, SystemColors.Window, SystemColors.Window);
            this.SetPlaceHoldersColours(this.lsvTeamTwoPlayers, lviReserved, SystemColors.Window, SystemColors.Window);
            this.SetPlaceHoldersColours(this.lsvTeamThreePlayers, lviReserved, SystemColors.Window, SystemColors.Window);
            this.SetPlaceHoldersColours(this.lsvTeamFourPlayers, lviReserved, SystemColors.Window, SystemColors.Window);
        }

        private void lsvPlayers_DragOver(object sender, DragEventArgs e) {

            Point pntClient = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).PointToClient(new Point(e.X, e.Y));
            ListViewItem lviHover = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y);

            lviHover = lviHover == null ? ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y - 1) : lviHover;
            lviHover = lviHover == null ? ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y + 1) : lviHover;

            if (((PRoCon.Controls.ControlsEx.ListViewNF)sender).Items.Count > 0 && lviHover == null) {
                // This includes the group header (team name)
                lviHover = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(pntClient.X, pntClient.Y + ((PRoCon.Controls.ControlsEx.ListViewNF)sender).Items[0].Bounds.Height);
            }

            if (lviHover == null) {
                e.Effect = DragDropEffects.None;
                this.HoverTeamBackground(null , -1);
            }
            else {
                if (lviHover.Tag != null && ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer != null && ((CPlayerInfo)e.Data.GetData(typeof(CPlayerInfo))).TeamID != ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer.TeamID) {
                    e.Effect = DragDropEffects.Move;

                    this.HoverTeamBackground(lviHover, ((AdditionalPlayerInfo)lviHover.Tag).m_cpiPlayer.TeamID);
                }
                else {
                    e.Effect = DragDropEffects.None;
                    this.HoverTeamBackground(lviHover , -1);
                }
            }
        }

        private void PlayerListSettings_TwoSplitterPercentageChanged(float percentage) {
            this.SetSplitterDistances();
        }

        private void PlayerListSettings_FourSplitterPercentageChanged(float percentage) {
            this.SetSplitterDistances();
        }

        private bool m_isSettingSlaveSplitter;

        private void spltTwoSplit_SplitterMoved(object sender, SplitterEventArgs e) {
            if (this.m_prcClient != null && this.m_isSplitterBeingSet == false && this.m_isSettingSlaveSplitter == false) {
                this.m_prcClient.PlayerListSettings.TwoSplitterPercentage = (float)e.SplitX / (float)this.spltTwoSplit.Width;
            }
        }

        private void spltBottomTwoSplit_SplitterMoved(object sender, SplitterEventArgs e) {
            if (this.m_prcClient != null && this.m_isSplitterBeingSet == false && this.m_isSettingSlaveSplitter == false) {
                //    this.m_prcClient.PlayerListSettings.TwoSplitterPercentage = (float)e.SplitX / (float)this.spltBottomTwoSplit.Width;
                this.m_isSettingSlaveSplitter = true;
                this.spltTwoSplit.SplitterDistance = e.SplitX;
                this.m_isSettingSlaveSplitter = false;
            }
        }

        private void spltFourSplit_SplitterMoved(object sender, SplitterEventArgs e) {
            if (this.m_prcClient != null && this.m_isSplitterBeingSet == false) {
                this.m_prcClient.PlayerListSettings.FourSplitterPercentage = (float)this.spltFourSplit.SplitterDistance / (float)this.spltFourSplit.Height;
            }
        }

        private void tmrKillDeathHighlight_Tick(object sender, EventArgs e) {

            if (this.m_blPlaceHoldersDrawn == false) {

                lock (this.m_objPlayerDictionaryLocker) {

                    bool isStillFadingKill = false;

                    foreach (KeyValuePair<string, ListViewItem> kvpPlayer in this.m_dicPlayers) {
                        if (kvpPlayer.Value.SubItems["kdr"].Tag != null && kvpPlayer.Value.SubItems["kdr"].Tag is Kill) {

                            TimeSpan tsDifference = DateTime.Now - ((Kill)kvpPlayer.Value.SubItems["kdr"].Tag).TimeOfDeath;

                            float Opacity = ((5000.0F - (float)tsDifference.TotalMilliseconds) / 5000.0F) * 0.5F;

                            if (Opacity <= 0.0F) {
                                kvpPlayer.Value.SubItems["kdr"].Tag = null;
                                kvpPlayer.Value.BackColor = SystemColors.Window;
                            }
                            else if (Opacity > 0.0F && Opacity <= 1.0F) {
                                if (String.Compare(kvpPlayer.Key, ((Kill)kvpPlayer.Value.SubItems["kdr"].Tag).Victim.SoldierName) == 0) {
                                    kvpPlayer.Value.BackColor = Color.FromArgb((int)((Color.Maroon.R - 255) * Opacity + 255), (int)((Color.Maroon.G - 255) * Opacity + 255), (int)((Color.Maroon.B - 255) * Opacity + 255));
                                }
                                else {
                                    kvpPlayer.Value.BackColor = Color.FromArgb((int)((Color.LightSeaGreen.R - 255) * Opacity + 255), (int)((Color.LightSeaGreen.G - 255) * Opacity + 255), (int)((Color.LightSeaGreen.B - 255) * Opacity + 255));
                                    //kvpPlayer.Value.BackColor = Color.FromArgb((int)(255 * Opacity), Color.Maroon);
                                }

                                isStillFadingKill = true;
                            }
                        }
                    }

                    if (isStillFadingKill == false) {
                        this.tmrKillDeathHighlight.Enabled = false;
                    }
                }
            }
        }

        #region Right Click Context Menu

        private void lsvPlayers_MouseDown(object sender, MouseEventArgs e) {

            //Point pntClient = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).PointToClient(new Point(e.X, e.Y));
            ListViewItem lviSelected = ((PRoCon.Controls.ControlsEx.ListViewNF)sender).GetItemAt(e.X, e.Y);

            if (e.Button == MouseButtons.Left) {

                if (lviSelected == null) {
                    this.SelectNoPlayer();
                }
                else {
                    if (this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(lviSelected.Name) == true) {
                        this.SelectNoPlayer();
                    }
                    //else {
                    //    this.SelectPlayer(lviSelected.Name);
                    //}
                }
            }
            else if (e.Button == MouseButtons.Right && lviSelected != null && lviSelected.Tag != null && lviSelected.Tag is AdditionalPlayerInfo) {

                CPlayerInfo player = ((AdditionalPlayerInfo)lviSelected.Tag).m_cpiPlayer;

                if (player != null && lviSelected != null && this.m_lvwColumnSorter.TotalsAveragesChecker.IsMatch(lviSelected.Name) == false) {

                    this.moveToSquadToolStripMenuItem.Text = this.m_clocLanguage.GetDefaultLocalized("Move Player to..", "uscPlayerListPanel.ctxPlayerOptions.moveToSquadToolStripMenuItem", player.SoldierName);
                    this.moveToSquadToolStripMenuItem.DropDownItems.Clear();

                    foreach (CTeamName team in this.m_prcClient.TeamNameList) {
                        if (String.Compare(team.MapFilename, this.m_prcClient.CurrentServerInfo.Map, true) == 0 && team.TeamID != uscPlayerListPanel.INT_NEUTRAL_TEAM) {

                            ToolStripMenuItem teamChange = new ToolStripMenuItem(this.m_clocLanguage.GetDefaultLocalized(String.Format("Team {0}", team.TeamID), "uscPlayerListPanel.ctxPlayerOptions.moveToSquadToolStripMenuItem.Team", this.m_clocLanguage.GetLocalized(team.LocalizationKey)));
                            teamChange.Tag = new object[] { player, team };
                            teamChange.Click += new EventHandler(teamChange_Click);

                            if (team.TeamID == player.TeamID) {
                                teamChange.Checked = true;
                                teamChange.Enabled = false;
                            }

                            this.moveToSquadToolStripMenuItem.DropDownItems.Add(teamChange);
                        }
                    }
                        // uscPlayerListPanel.INT_NEUTRAL_TEAM
                    if (this.m_prcClient.Game.HasSquads == true) {

                        this.moveToSquadToolStripMenuItem.DropDownItems.Add(new ToolStripSeparator());

                        for (int i = 0; i <= 8; i++) {

                            ToolStripMenuItem squadChange = new ToolStripMenuItem(this.m_clocLanguage.GetDefaultLocalized(String.Format("Squad {0}", i), "uscPlayerListPanel.ctxPlayerOptions.moveToSquadToolStripMenuItem.Squad", this.m_clocLanguage.GetLocalized(String.Format("global.Squad{0}", i))));
                            squadChange.Tag = new object[] { player, i };
                            squadChange.Click += new EventHandler(squadChange_Click);

                            if (player.SquadID == i) {
                                squadChange.Checked = true;
                                squadChange.Enabled = false;
                            }

                            this.moveToSquadToolStripMenuItem.DropDownItems.Add(squadChange);
                        }
                    }

                    this.reservedSlotToolStripMenuItem.Checked = this.m_prcClient.ReservedSlotList.Contains(player.SoldierName);
                    this.reservedSlotToolStripMenuItem.Tag = player;

                    if (this.m_prcClient.FullTextChatModerationList.Contains(player.SoldierName) == true) {

                        TextChatModerationEntry entry = this.m_prcClient.FullTextChatModerationList[player.SoldierName];

                        this.mutedToolStripMenuItem.Checked = (entry.PlayerModerationLevel == PlayerModerationLevelType.Muted);
                        this.normalToolStripMenuItem.Checked = (entry.PlayerModerationLevel == PlayerModerationLevelType.Normal);
                        this.voiceToolStripMenuItem.Checked = (entry.PlayerModerationLevel == PlayerModerationLevelType.Voice);
                        this.adminToolStripMenuItem.Checked = (entry.PlayerModerationLevel == PlayerModerationLevelType.Admin);
                    }
                    else {
                        this.mutedToolStripMenuItem.Checked = this.voiceToolStripMenuItem.Checked = this.adminToolStripMenuItem.Checked = false;
                        this.normalToolStripMenuItem.Checked = true;
                    }

                    this.mutedToolStripMenuItem.Enabled = !this.mutedToolStripMenuItem.Checked;
                    this.normalToolStripMenuItem.Enabled = !this.normalToolStripMenuItem.Checked;
                    this.voiceToolStripMenuItem.Enabled = !this.voiceToolStripMenuItem.Checked;
                    this.adminToolStripMenuItem.Enabled = !this.adminToolStripMenuItem.Checked;

                    this.statsLookupToolStripMenuItem.Tag = this.mutedToolStripMenuItem.Tag = this.normalToolStripMenuItem.Tag = this.voiceToolStripMenuItem.Tag = this.adminToolStripMenuItem.Tag = player;

                    if (this.m_prcClient != null && this.m_prcClient.GameType == "MOH") {
                        this.reservedSlotToolStripMenuItem.Enabled = false;
                    }

                    //show the context menu strip
                    Point menuPosition = Cursor.Position;
                    menuPosition.Offset(1, 1);
                    ctxPlayerOptions.Show(this, this.PointToClient(menuPosition));
                }
            }
        }

        private void squadChange_Click(object sender, EventArgs e) {
            if (sender is ToolStripMenuItem) {
                ToolStripMenuItem squadChange = (ToolStripMenuItem)sender;
                CPlayerInfo player = (CPlayerInfo)((object[])squadChange.Tag)[0];
                int destinationSquadId = (int)((object[])squadChange.Tag)[1];

                this.m_prcClient.Game.SendAdminMovePlayerPacket(player.SoldierName, player.TeamID, destinationSquadId, true);
            }
        }

        private void teamChange_Click(object sender, EventArgs e) {
            if (sender is ToolStripMenuItem) {
                ToolStripMenuItem teamChange = (ToolStripMenuItem)sender;

                CPlayerInfo player = (CPlayerInfo)((object[])teamChange.Tag)[0];
                CTeamName destinationTeam = (CTeamName)((object[])teamChange.Tag)[1];

                this.m_prcClient.Game.SendAdminMovePlayerPacket(player.SoldierName, destinationTeam.TeamID, this.m_prcClient.GetDefaultSquadIDByMapname(destinationTeam.MapFilename), true);
            }
        }

        private void reservedSlotToolStripMenuItem_Click(object sender, EventArgs e) {

            if (this.reservedSlotToolStripMenuItem.Tag is CPlayerInfo) {
                if (this.reservedSlotToolStripMenuItem.Checked == false) {
                    this.m_prcClient.Game.SendReservedSlotsAddPlayerPacket(((CPlayerInfo)this.reservedSlotToolStripMenuItem.Tag).SoldierName);
                }
                else {
                    this.m_prcClient.Game.SendReservedSlotsRemovePlayerPacket(((CPlayerInfo)this.reservedSlotToolStripMenuItem.Tag).SoldierName);
                }

                this.m_prcClient.Game.SendReservedSlotsSavePacket();
            }
        }

        private void mutedToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.mutedToolStripMenuItem.Tag is CPlayerInfo) {
                this.m_prcClient.Game.SendTextChatModerationListAddPacket(new TextChatModerationEntry(PlayerModerationLevelType.Muted, ((CPlayerInfo)this.reservedSlotToolStripMenuItem.Tag).SoldierName));

                this.m_prcClient.Game.SendTextChatModerationListSavePacket();
            }
        }

        private void normalToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.normalToolStripMenuItem.Tag is CPlayerInfo) {
                this.m_prcClient.Game.SendTextChatModerationListAddPacket(new TextChatModerationEntry(PlayerModerationLevelType.Normal, ((CPlayerInfo)this.reservedSlotToolStripMenuItem.Tag).SoldierName));

                this.m_prcClient.Game.SendTextChatModerationListSavePacket();
            }
        }

        private void voiceToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.voiceToolStripMenuItem.Tag is CPlayerInfo) {
                this.m_prcClient.Game.SendTextChatModerationListAddPacket(new TextChatModerationEntry(PlayerModerationLevelType.Voice, ((CPlayerInfo)this.reservedSlotToolStripMenuItem.Tag).SoldierName));

                this.m_prcClient.Game.SendTextChatModerationListSavePacket();
            }
        }

        private void adminToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.adminToolStripMenuItem.Tag is CPlayerInfo) {
                this.m_prcClient.Game.SendTextChatModerationListAddPacket(new TextChatModerationEntry(PlayerModerationLevelType.Admin, ((CPlayerInfo)this.reservedSlotToolStripMenuItem.Tag).SoldierName));

                this.m_prcClient.Game.SendTextChatModerationListSavePacket();
            }
        }

        private void statsLookupToolStripMenuItem_Click(object sender, EventArgs e) {
            if (this.voiceToolStripMenuItem.Tag is CPlayerInfo) {
                if (this.m_prcClient.Game is MoHClient) {
                    System.Diagnostics.Process.Start("http://mohstats.com/stats_pc/" + ((CPlayerInfo)this.voiceToolStripMenuItem.Tag).SoldierName);
                }
                else if (this.m_prcClient.Game is BFBC2Client) {
                    System.Diagnostics.Process.Start("http://bfbcs.com/stats_pc/" + ((CPlayerInfo)this.voiceToolStripMenuItem.Tag).SoldierName);
                }
            }
        }

        #endregion


    }*/

    /*partial class uscPlayerListPanel {
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
            System.Windows.Forms.ListViewGroup listViewGroup1 = new System.Windows.Forms.ListViewGroup("Neutral", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup2 = new System.Windows.Forms.ListViewGroup("Team: 1", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup3 = new System.Windows.Forms.ListViewGroup("Team: 2", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup4 = new System.Windows.Forms.ListViewGroup("Team: 3", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup5 = new System.Windows.Forms.ListViewGroup("Team: 4", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup6 = new System.Windows.Forms.ListViewGroup("Team: 5", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup7 = new System.Windows.Forms.ListViewGroup("Team: 6", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup8 = new System.Windows.Forms.ListViewGroup("Team: 7", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup9 = new System.Windows.Forms.ListViewGroup("Team: 8", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup10 = new System.Windows.Forms.ListViewGroup("Team: 9", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup11 = new System.Windows.Forms.ListViewGroup("Team: 10", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup12 = new System.Windows.Forms.ListViewGroup("Team: 11", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup13 = new System.Windows.Forms.ListViewGroup("Team: 12", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup14 = new System.Windows.Forms.ListViewGroup("Team: 13", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup15 = new System.Windows.Forms.ListViewGroup("Team: 14", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup16 = new System.Windows.Forms.ListViewGroup("Team: 15", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup17 = new System.Windows.Forms.ListViewGroup("Team: 16", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup18 = new System.Windows.Forms.ListViewGroup("Neutral", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup19 = new System.Windows.Forms.ListViewGroup("Team: 1", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup20 = new System.Windows.Forms.ListViewGroup("Team: 2", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup21 = new System.Windows.Forms.ListViewGroup("Team: 3", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup22 = new System.Windows.Forms.ListViewGroup("Team: 4", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup23 = new System.Windows.Forms.ListViewGroup("Team: 5", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup24 = new System.Windows.Forms.ListViewGroup("Team: 6", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup25 = new System.Windows.Forms.ListViewGroup("Team: 7", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup26 = new System.Windows.Forms.ListViewGroup("Team: 8", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup27 = new System.Windows.Forms.ListViewGroup("Team: 9", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup28 = new System.Windows.Forms.ListViewGroup("Team: 10", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup29 = new System.Windows.Forms.ListViewGroup("Team: 11", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup30 = new System.Windows.Forms.ListViewGroup("Team: 12", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup31 = new System.Windows.Forms.ListViewGroup("Team: 13", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup32 = new System.Windows.Forms.ListViewGroup("Team: 14", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup33 = new System.Windows.Forms.ListViewGroup("Team: 15", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup34 = new System.Windows.Forms.ListViewGroup("Team: 16", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup35 = new System.Windows.Forms.ListViewGroup("Neutral", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup36 = new System.Windows.Forms.ListViewGroup("Team: 1", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup37 = new System.Windows.Forms.ListViewGroup("Team: 2", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup38 = new System.Windows.Forms.ListViewGroup("Team: 3", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup39 = new System.Windows.Forms.ListViewGroup("Team: 4", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup40 = new System.Windows.Forms.ListViewGroup("Team: 5", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup41 = new System.Windows.Forms.ListViewGroup("Team: 6", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup42 = new System.Windows.Forms.ListViewGroup("Team: 7", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup43 = new System.Windows.Forms.ListViewGroup("Team: 8", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup44 = new System.Windows.Forms.ListViewGroup("Team: 9", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup45 = new System.Windows.Forms.ListViewGroup("Team: 10", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup46 = new System.Windows.Forms.ListViewGroup("Team: 11", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup47 = new System.Windows.Forms.ListViewGroup("Team: 12", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup48 = new System.Windows.Forms.ListViewGroup("Team: 13", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup49 = new System.Windows.Forms.ListViewGroup("Team: 14", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup50 = new System.Windows.Forms.ListViewGroup("Team: 15", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup51 = new System.Windows.Forms.ListViewGroup("Team: 16", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup52 = new System.Windows.Forms.ListViewGroup("Neutral", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup53 = new System.Windows.Forms.ListViewGroup("Team: 1", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup54 = new System.Windows.Forms.ListViewGroup("Team: 2", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup55 = new System.Windows.Forms.ListViewGroup("Team: 3", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup56 = new System.Windows.Forms.ListViewGroup("Team: 4", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup57 = new System.Windows.Forms.ListViewGroup("Team: 5", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup58 = new System.Windows.Forms.ListViewGroup("Team: 6", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup59 = new System.Windows.Forms.ListViewGroup("Team: 7", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup60 = new System.Windows.Forms.ListViewGroup("Team: 8", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup61 = new System.Windows.Forms.ListViewGroup("Team: 9", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup62 = new System.Windows.Forms.ListViewGroup("Team: 10", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup63 = new System.Windows.Forms.ListViewGroup("Team: 11", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup64 = new System.Windows.Forms.ListViewGroup("Team: 12", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup65 = new System.Windows.Forms.ListViewGroup("Team: 13", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup66 = new System.Windows.Forms.ListViewGroup("Team: 14", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup67 = new System.Windows.Forms.ListViewGroup("Team: 15", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup68 = new System.Windows.Forms.ListViewGroup("Team: 16", System.Windows.Forms.HorizontalAlignment.Left);
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(uscPlayerListPanel));
            this.spltListAdditionalInfo = new System.Windows.Forms.SplitContainer();
            this.spltFourSplit = new System.Windows.Forms.SplitContainer();
            this.spltTwoSplit = new System.Windows.Forms.SplitContainer();
            this.lsvTeamOnePlayers = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colSlotID1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colTags1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPlayerName1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colSquad1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKit1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colScore1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKills1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colDeaths1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKdr1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPing1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.lsvTeamTwoPlayers = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colSlotID2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colTags2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPlayerName2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colSquad2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKit2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colScore2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKills2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colDeaths2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKdr2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPing2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.spltBottomTwoSplit = new System.Windows.Forms.SplitContainer();
            this.lsvTeamThreePlayers = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colSlotID3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colTags3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPlayerName3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colSquad3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKit3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colScore3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKills3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colDeaths3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKdr3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPing3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.lsvTeamFourPlayers = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colSlotID4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colTags4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPlayerName4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colSquad4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKit4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colScore4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKills4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colDeaths4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colKdr4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPing4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.btnSplitTeams = new System.Windows.Forms.Button();
            this.btnCloseAdditionalInfo = new System.Windows.Forms.Button();
            this.chkPlayerListShowTeams = new System.Windows.Forms.CheckBox();
            this.spltInfoPunish = new System.Windows.Forms.SplitContainer();
            this.pnlAdditionalInfo = new System.Windows.Forms.Panel();
            this.lblPlayersInventory = new System.Windows.Forms.Label();
            this.lblInventory = new System.Windows.Forms.Label();
            this.txtPlayerListSelectedBc2GUID = new System.Windows.Forms.TextBox();
            this.lblEAGuid = new System.Windows.Forms.Label();
            this.txtPlayerListSelectedIP = new System.Windows.Forms.TextBox();
            this.txtPlayerListSelectedGUID = new System.Windows.Forms.TextBox();
            this.lblPlayerListSelectedName = new System.Windows.Forms.Label();
            this.panel15 = new System.Windows.Forms.Panel();
            this.btnPlayerListSelectedCheese = new System.Windows.Forms.Button();
            this.lblIP = new System.Windows.Forms.Label();
            this.lblGUID = new System.Windows.Forms.Label();
            this.tbcCourtMartial = new System.Windows.Forms.TabControl();
            this.tabCourtMartialBFBC = new System.Windows.Forms.TabPage();
            this.kbpBfbcPunishPanel = new PRoCon.uscPlayerPunishPanel();
            this.tabCourtMartialPunkbuster = new System.Windows.Forms.TabPage();
            this.kbpPunkbusterPunishPanel = new PRoCon.uscPlayerPunishPanel();
            this.tmrKillDeathHighlight = new System.Windows.Forms.Timer(this.components);
            this.ctxPlayerOptions = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.moveToSquadToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.attackersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.defendersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.noSquadToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.alphaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.betaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.charlieToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.textChatModerationToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.mutedToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.normalToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.voiceToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.adminToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.reservedSlotToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem2 = new System.Windows.Forms.ToolStripSeparator();
            this.statsLookupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.spltListAdditionalInfo.Panel1.SuspendLayout();
            this.spltListAdditionalInfo.Panel2.SuspendLayout();
            this.spltListAdditionalInfo.SuspendLayout();
            this.spltFourSplit.Panel1.SuspendLayout();
            this.spltFourSplit.Panel2.SuspendLayout();
            this.spltFourSplit.SuspendLayout();
            this.spltTwoSplit.Panel1.SuspendLayout();
            this.spltTwoSplit.Panel2.SuspendLayout();
            this.spltTwoSplit.SuspendLayout();
            this.spltBottomTwoSplit.Panel1.SuspendLayout();
            this.spltBottomTwoSplit.Panel2.SuspendLayout();
            this.spltBottomTwoSplit.SuspendLayout();
            this.spltInfoPunish.Panel1.SuspendLayout();
            this.spltInfoPunish.Panel2.SuspendLayout();
            this.spltInfoPunish.SuspendLayout();
            this.pnlAdditionalInfo.SuspendLayout();
            this.tbcCourtMartial.SuspendLayout();
            this.tabCourtMartialBFBC.SuspendLayout();
            this.tabCourtMartialPunkbuster.SuspendLayout();
            this.ctxPlayerOptions.SuspendLayout();
            this.SuspendLayout();
            //
            // spltListAdditionalInfo
            //
            this.spltListAdditionalInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltListAdditionalInfo.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.spltListAdditionalInfo.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.spltListAdditionalInfo.IsSplitterFixed = true;
            this.spltListAdditionalInfo.Location = new System.Drawing.Point(0, 0);
            this.spltListAdditionalInfo.Name = "spltListAdditionalInfo";
            this.spltListAdditionalInfo.Orientation = System.Windows.Forms.Orientation.Horizontal;
            //
            // spltListAdditionalInfo.Panel1
            //
            this.spltListAdditionalInfo.Panel1.Controls.Add(this.spltFourSplit);
            this.spltListAdditionalInfo.Panel1.Controls.Add(this.btnSplitTeams);
            this.spltListAdditionalInfo.Panel1.Controls.Add(this.btnCloseAdditionalInfo);
            this.spltListAdditionalInfo.Panel1.Controls.Add(this.chkPlayerListShowTeams);
            //
            // spltListAdditionalInfo.Panel2
            //
            this.spltListAdditionalInfo.Panel2.Controls.Add(this.spltInfoPunish);
            this.spltListAdditionalInfo.Size = new System.Drawing.Size(896, 708);
            this.spltListAdditionalInfo.SplitterDistance = 509;
            this.spltListAdditionalInfo.TabIndex = 20;
            //
            // spltFourSplit
            //
            this.spltFourSplit.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.spltFourSplit.Location = new System.Drawing.Point(0, 0);
            this.spltFourSplit.Name = "spltFourSplit";
            this.spltFourSplit.Orientation = System.Windows.Forms.Orientation.Horizontal;
            //
            // spltFourSplit.Panel1
            //
            this.spltFourSplit.Panel1.Controls.Add(this.spltTwoSplit);
            //
            // spltFourSplit.Panel2
            //
            this.spltFourSplit.Panel2.Controls.Add(this.spltBottomTwoSplit);
            this.spltFourSplit.Size = new System.Drawing.Size(896, 483);
            this.spltFourSplit.SplitterDistance = 241;
            this.spltFourSplit.TabIndex = 32;
            this.spltFourSplit.SplitterMoved += new System.Windows.Forms.SplitterEventHandler(this.spltFourSplit_SplitterMoved);
            //
            // spltTwoSplit
            //
            this.spltTwoSplit.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltTwoSplit.Location = new System.Drawing.Point(0, 0);
            this.spltTwoSplit.Name = "spltTwoSplit";
            //
            // spltTwoSplit.Panel1
            //
            this.spltTwoSplit.Panel1.Controls.Add(this.lsvTeamOnePlayers);
            //
            // spltTwoSplit.Panel2
            //
            this.spltTwoSplit.Panel2.Controls.Add(this.lsvTeamTwoPlayers);
            this.spltTwoSplit.Size = new System.Drawing.Size(896, 241);
            this.spltTwoSplit.SplitterDistance = 443;
            this.spltTwoSplit.TabIndex = 31;
            this.spltTwoSplit.SplitterMoved += new System.Windows.Forms.SplitterEventHandler(this.spltTwoSplit_SplitterMoved);
            //
            // lsvTeamOnePlayers
            //
            this.lsvTeamOnePlayers.AllowDrop = true;
            this.lsvTeamOnePlayers.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colSlotID1,
            this.colTags1,
            this.colPlayerName1,
            this.colSquad1,
            this.colKit1,
            this.colScore1,
            this.colKills1,
            this.colDeaths1,
            this.colKdr1,
            this.colPing1});
            this.lsvTeamOnePlayers.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lsvTeamOnePlayers.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lsvTeamOnePlayers.FullRowSelect = true;
            listViewGroup1.Header = "Neutral";
            listViewGroup1.Name = "team0";
            listViewGroup2.Header = "Team: 1";
            listViewGroup2.Name = "team1";
            listViewGroup3.Header = "Team: 2";
            listViewGroup3.Name = "team2";
            listViewGroup4.Header = "Team: 3";
            listViewGroup4.Name = "team3";
            listViewGroup5.Header = "Team: 4";
            listViewGroup5.Name = "team4";
            listViewGroup6.Header = "Team: 5";
            listViewGroup6.Name = "team5";
            listViewGroup7.Header = "Team: 6";
            listViewGroup7.Name = "team6";
            listViewGroup8.Header = "Team: 7";
            listViewGroup8.Name = "team7";
            listViewGroup9.Header = "Team: 8";
            listViewGroup9.Name = "team8";
            listViewGroup10.Header = "Team: 9";
            listViewGroup10.Name = "team9";
            listViewGroup11.Header = "Team: 10";
            listViewGroup11.Name = "team10";
            listViewGroup12.Header = "Team: 11";
            listViewGroup12.Name = "team11";
            listViewGroup13.Header = "Team: 12";
            listViewGroup13.Name = "team12";
            listViewGroup14.Header = "Team: 13";
            listViewGroup14.Name = "team13";
            listViewGroup15.Header = "Team: 14";
            listViewGroup15.Name = "team14";
            listViewGroup16.Header = "Team: 15";
            listViewGroup16.Name = "team15";
            listViewGroup17.Header = "Team: 16";
            listViewGroup17.Name = "team16";
            this.lsvTeamOnePlayers.Groups.AddRange(new System.Windows.Forms.ListViewGroup[] {
            listViewGroup1,
            listViewGroup2,
            listViewGroup3,
            listViewGroup4,
            listViewGroup5,
            listViewGroup6,
            listViewGroup7,
            listViewGroup8,
            listViewGroup9,
            listViewGroup10,
            listViewGroup11,
            listViewGroup12,
            listViewGroup13,
            listViewGroup14,
            listViewGroup15,
            listViewGroup16,
            listViewGroup17});
            this.lsvTeamOnePlayers.HideSelection = false;
            this.lsvTeamOnePlayers.Location = new System.Drawing.Point(0, 0);
            this.lsvTeamOnePlayers.MultiSelect = false;
            this.lsvTeamOnePlayers.Name = "lsvTeamOnePlayers";
            this.lsvTeamOnePlayers.ShowItemToolTips = true;
            this.lsvTeamOnePlayers.Size = new System.Drawing.Size(443, 241);
            this.lsvTeamOnePlayers.TabIndex = 27;
            this.lsvTeamOnePlayers.UseCompatibleStateImageBehavior = false;
            this.lsvTeamOnePlayers.View = System.Windows.Forms.View.Details;
            this.lsvTeamOnePlayers.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsvPlayers_ColumnClick);
            this.lsvTeamOnePlayers.ItemDrag += new System.Windows.Forms.ItemDragEventHandler(this.lsvPlayers_ItemDrag);
            this.lsvTeamOnePlayers.SelectedIndexChanged += new System.EventHandler(this.lsvPlayers_SelectedIndexChanged);
            this.lsvTeamOnePlayers.DragDrop += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragDrop);
            this.lsvTeamOnePlayers.DragEnter += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragEnter);
            this.lsvTeamOnePlayers.DragOver += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragOver);
            this.lsvTeamOnePlayers.MouseDown += new System.Windows.Forms.MouseEventHandler(this.lsvPlayers_MouseDown);
            //
            // colSlotID1
            //
            this.colSlotID1.Text = "ID#";
            //
            // colTags1
            //
            this.colTags1.Text = "Tags";
            this.colTags1.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            //
            // colPlayerName1
            //
            this.colPlayerName1.Text = "Name";
            //
            // colSquad1
            //
            this.colSquad1.Text = "Squad";
            //
            // colKit1
            //
            this.colKit1.Text = "Kit";
            //
            // colScore1
            //
            this.colScore1.Text = "Score";
            //
            // colKills1
            //
            this.colKills1.Text = "Kills";
            //
            // colDeaths1
            //
            this.colDeaths1.Text = "Deaths";
            //
            // colKdr1
            //
            this.colKdr1.Text = "K/D r";
            //
            // colPing1
            //
            this.colPing1.Text = "Ping";
            //
            // lsvTeamTwoPlayers
            //
            this.lsvTeamTwoPlayers.AllowDrop = true;
            this.lsvTeamTwoPlayers.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colSlotID2,
            this.colTags2,
            this.colPlayerName2,
            this.colSquad2,
            this.colKit2,
            this.colScore2,
            this.colKills2,
            this.colDeaths2,
            this.colKdr2,
            this.colPing2});
            this.lsvTeamTwoPlayers.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lsvTeamTwoPlayers.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lsvTeamTwoPlayers.FullRowSelect = true;
            listViewGroup18.Header = "Neutral";
            listViewGroup18.Name = "team0";
            listViewGroup19.Header = "Team: 1";
            listViewGroup19.Name = "team1";
            listViewGroup20.Header = "Team: 2";
            listViewGroup20.Name = "team2";
            listViewGroup21.Header = "Team: 3";
            listViewGroup21.Name = "team3";
            listViewGroup22.Header = "Team: 4";
            listViewGroup22.Name = "team4";
            listViewGroup23.Header = "Team: 5";
            listViewGroup23.Name = "team5";
            listViewGroup24.Header = "Team: 6";
            listViewGroup24.Name = "team6";
            listViewGroup25.Header = "Team: 7";
            listViewGroup25.Name = "team7";
            listViewGroup26.Header = "Team: 8";
            listViewGroup26.Name = "team8";
            listViewGroup27.Header = "Team: 9";
            listViewGroup27.Name = "team9";
            listViewGroup28.Header = "Team: 10";
            listViewGroup28.Name = "team10";
            listViewGroup29.Header = "Team: 11";
            listViewGroup29.Name = "team11";
            listViewGroup30.Header = "Team: 12";
            listViewGroup30.Name = "team12";
            listViewGroup31.Header = "Team: 13";
            listViewGroup31.Name = "team13";
            listViewGroup32.Header = "Team: 14";
            listViewGroup32.Name = "team14";
            listViewGroup33.Header = "Team: 15";
            listViewGroup33.Name = "team15";
            listViewGroup34.Header = "Team: 16";
            listViewGroup34.Name = "team16";
            this.lsvTeamTwoPlayers.Groups.AddRange(new System.Windows.Forms.ListViewGroup[] {
            listViewGroup18,
            listViewGroup19,
            listViewGroup20,
            listViewGroup21,
            listViewGroup22,
            listViewGroup23,
            listViewGroup24,
            listViewGroup25,
            listViewGroup26,
            listViewGroup27,
            listViewGroup28,
            listViewGroup29,
            listViewGroup30,
            listViewGroup31,
            listViewGroup32,
            listViewGroup33,
            listViewGroup34});
            this.lsvTeamTwoPlayers.HideSelection = false;
            this.lsvTeamTwoPlayers.Location = new System.Drawing.Point(0, 0);
            this.lsvTeamTwoPlayers.MultiSelect = false;
            this.lsvTeamTwoPlayers.Name = "lsvTeamTwoPlayers";
            this.lsvTeamTwoPlayers.ShowItemToolTips = true;
            this.lsvTeamTwoPlayers.Size = new System.Drawing.Size(449, 241);
            this.lsvTeamTwoPlayers.TabIndex = 28;
            this.lsvTeamTwoPlayers.UseCompatibleStateImageBehavior = false;
            this.lsvTeamTwoPlayers.View = System.Windows.Forms.View.Details;
            this.lsvTeamTwoPlayers.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsvPlayers_ColumnClick);
            this.lsvTeamTwoPlayers.ItemDrag += new System.Windows.Forms.ItemDragEventHandler(this.lsvPlayers_ItemDrag);
            this.lsvTeamTwoPlayers.SelectedIndexChanged += new System.EventHandler(this.lsvPlayers_SelectedIndexChanged);
            this.lsvTeamTwoPlayers.DragDrop += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragDrop);
            this.lsvTeamTwoPlayers.DragEnter += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragEnter);
            this.lsvTeamTwoPlayers.DragOver += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragOver);
            this.lsvTeamTwoPlayers.MouseDown += new System.Windows.Forms.MouseEventHandler(this.lsvPlayers_MouseDown);
            //
            // colSlotID2
            //
            this.colSlotID2.Text = "ID#";
            //
            // colTags2
            //
            this.colTags2.Text = "Tags";
            this.colTags2.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            //
            // colPlayerName2
            //
            this.colPlayerName2.Text = "Name";
            //
            // colSquad2
            //
            this.colSquad2.Text = "Squad";
            //
            // colKit2
            //
            this.colKit2.Text = "Kit";
            //
            // colScore2
            //
            this.colScore2.Text = "Score";
            //
            // colKills2
            //
            this.colKills2.Text = "Kills";
            //
            // colDeaths2
            //
            this.colDeaths2.Text = "Deaths";
            //
            // colKdr2
            //
            this.colKdr2.Text = "K/D r";
            //
            // colPing2
            //
            this.colPing2.Text = "Ping";
            //
            // spltBottomTwoSplit
            //
            this.spltBottomTwoSplit.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltBottomTwoSplit.Location = new System.Drawing.Point(0, 0);
            this.spltBottomTwoSplit.Name = "spltBottomTwoSplit";
            //
            // spltBottomTwoSplit.Panel1
            //
            this.spltBottomTwoSplit.Panel1.Controls.Add(this.lsvTeamThreePlayers);
            //
            // spltBottomTwoSplit.Panel2
            //
            this.spltBottomTwoSplit.Panel2.Controls.Add(this.lsvTeamFourPlayers);
            this.spltBottomTwoSplit.Size = new System.Drawing.Size(896, 238);
            this.spltBottomTwoSplit.SplitterDistance = 443;
            this.spltBottomTwoSplit.TabIndex = 32;
            this.spltBottomTwoSplit.SplitterMoved += new System.Windows.Forms.SplitterEventHandler(this.spltBottomTwoSplit_SplitterMoved);
            //
            // lsvTeamThreePlayers
            //
            this.lsvTeamThreePlayers.AllowDrop = true;
            this.lsvTeamThreePlayers.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colSlotID3,
            this.colTags3,
            this.colPlayerName3,
            this.colSquad3,
            this.colKit3,
            this.colScore3,
            this.colKills3,
            this.colDeaths3,
            this.colKdr3,
            this.colPing3});
            this.lsvTeamThreePlayers.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lsvTeamThreePlayers.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lsvTeamThreePlayers.FullRowSelect = true;
            listViewGroup35.Header = "Neutral";
            listViewGroup35.Name = "team0";
            listViewGroup36.Header = "Team: 1";
            listViewGroup36.Name = "team1";
            listViewGroup37.Header = "Team: 2";
            listViewGroup37.Name = "team2";
            listViewGroup38.Header = "Team: 3";
            listViewGroup38.Name = "team3";
            listViewGroup39.Header = "Team: 4";
            listViewGroup39.Name = "team4";
            listViewGroup40.Header = "Team: 5";
            listViewGroup40.Name = "team5";
            listViewGroup41.Header = "Team: 6";
            listViewGroup41.Name = "team6";
            listViewGroup42.Header = "Team: 7";
            listViewGroup42.Name = "team7";
            listViewGroup43.Header = "Team: 8";
            listViewGroup43.Name = "team8";
            listViewGroup44.Header = "Team: 9";
            listViewGroup44.Name = "team9";
            listViewGroup45.Header = "Team: 10";
            listViewGroup45.Name = "team10";
            listViewGroup46.Header = "Team: 11";
            listViewGroup46.Name = "team11";
            listViewGroup47.Header = "Team: 12";
            listViewGroup47.Name = "team12";
            listViewGroup48.Header = "Team: 13";
            listViewGroup48.Name = "team13";
            listViewGroup49.Header = "Team: 14";
            listViewGroup49.Name = "team14";
            listViewGroup50.Header = "Team: 15";
            listViewGroup50.Name = "team15";
            listViewGroup51.Header = "Team: 16";
            listViewGroup51.Name = "team16";
            this.lsvTeamThreePlayers.Groups.AddRange(new System.Windows.Forms.ListViewGroup[] {
            listViewGroup35,
            listViewGroup36,
            listViewGroup37,
            listViewGroup38,
            listViewGroup39,
            listViewGroup40,
            listViewGroup41,
            listViewGroup42,
            listViewGroup43,
            listViewGroup44,
            listViewGroup45,
            listViewGroup46,
            listViewGroup47,
            listViewGroup48,
            listViewGroup49,
            listViewGroup50,
            listViewGroup51});
            this.lsvTeamThreePlayers.HideSelection = false;
            this.lsvTeamThreePlayers.Location = new System.Drawing.Point(0, 0);
            this.lsvTeamThreePlayers.MultiSelect = false;
            this.lsvTeamThreePlayers.Name = "lsvTeamThreePlayers";
            this.lsvTeamThreePlayers.ShowItemToolTips = true;
            this.lsvTeamThreePlayers.Size = new System.Drawing.Size(443, 238);
            this.lsvTeamThreePlayers.TabIndex = 27;
            this.lsvTeamThreePlayers.UseCompatibleStateImageBehavior = false;
            this.lsvTeamThreePlayers.View = System.Windows.Forms.View.Details;
            this.lsvTeamThreePlayers.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsvPlayers_ColumnClick);
            this.lsvTeamThreePlayers.ItemDrag += new System.Windows.Forms.ItemDragEventHandler(this.lsvPlayers_ItemDrag);
            this.lsvTeamThreePlayers.SelectedIndexChanged += new System.EventHandler(this.lsvPlayers_SelectedIndexChanged);
            this.lsvTeamThreePlayers.DragDrop += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragDrop);
            this.lsvTeamThreePlayers.DragEnter += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragEnter);
            this.lsvTeamThreePlayers.DragOver += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragOver);
            this.lsvTeamThreePlayers.MouseDown += new System.Windows.Forms.MouseEventHandler(this.lsvPlayers_MouseDown);
            //
            // colSlotID3
            //
            this.colSlotID3.Text = "ID#";
            //
            // colTags3
            //
            this.colTags3.Text = "Tags";
            this.colTags3.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            //
            // colPlayerName3
            //
            this.colPlayerName3.Text = "Name";
            //
            // colSquad3
            //
            this.colSquad3.Text = "Squad";
            //
            // colKit3
            //
            this.colKit3.Text = "Kit";
            //
            // colScore3
            //
            this.colScore3.Text = "Score";
            //
            // colKills3
            //
            this.colKills3.Text = "Kills";
            //
            // colDeaths3
            //
            this.colDeaths3.Text = "Deaths";
            //
            // colKdr3
            //
            this.colKdr3.Text = "K/D r";
            //
            // colPing3
            //
            this.colPing3.Text = "Ping";
            //
            // lsvTeamFourPlayers
            //
            this.lsvTeamFourPlayers.AllowDrop = true;
            this.lsvTeamFourPlayers.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colSlotID4,
            this.colTags4,
            this.colPlayerName4,
            this.colSquad4,
            this.colKit4,
            this.colScore4,
            this.colKills4,
            this.colDeaths4,
            this.colKdr4,
            this.colPing4});
            this.lsvTeamFourPlayers.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lsvTeamFourPlayers.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.lsvTeamFourPlayers.FullRowSelect = true;
            listViewGroup52.Header = "Neutral";
            listViewGroup52.Name = "team0";
            listViewGroup53.Header = "Team: 1";
            listViewGroup53.Name = "team1";
            listViewGroup54.Header = "Team: 2";
            listViewGroup54.Name = "team2";
            listViewGroup55.Header = "Team: 3";
            listViewGroup55.Name = "team3";
            listViewGroup56.Header = "Team: 4";
            listViewGroup56.Name = "team4";
            listViewGroup57.Header = "Team: 5";
            listViewGroup57.Name = "team5";
            listViewGroup58.Header = "Team: 6";
            listViewGroup58.Name = "team6";
            listViewGroup59.Header = "Team: 7";
            listViewGroup59.Name = "team7";
            listViewGroup60.Header = "Team: 8";
            listViewGroup60.Name = "team8";
            listViewGroup61.Header = "Team: 9";
            listViewGroup61.Name = "team9";
            listViewGroup62.Header = "Team: 10";
            listViewGroup62.Name = "team10";
            listViewGroup63.Header = "Team: 11";
            listViewGroup63.Name = "team11";
            listViewGroup64.Header = "Team: 12";
            listViewGroup64.Name = "team12";
            listViewGroup65.Header = "Team: 13";
            listViewGroup65.Name = "team13";
            listViewGroup66.Header = "Team: 14";
            listViewGroup66.Name = "team14";
            listViewGroup67.Header = "Team: 15";
            listViewGroup67.Name = "team15";
            listViewGroup68.Header = "Team: 16";
            listViewGroup68.Name = "team16";
            this.lsvTeamFourPlayers.Groups.AddRange(new System.Windows.Forms.ListViewGroup[] {
            listViewGroup52,
            listViewGroup53,
            listViewGroup54,
            listViewGroup55,
            listViewGroup56,
            listViewGroup57,
            listViewGroup58,
            listViewGroup59,
            listViewGroup60,
            listViewGroup61,
            listViewGroup62,
            listViewGroup63,
            listViewGroup64,
            listViewGroup65,
            listViewGroup66,
            listViewGroup67,
            listViewGroup68});
            this.lsvTeamFourPlayers.HideSelection = false;
            this.lsvTeamFourPlayers.Location = new System.Drawing.Point(0, 0);
            this.lsvTeamFourPlayers.MultiSelect = false;
            this.lsvTeamFourPlayers.Name = "lsvTeamFourPlayers";
            this.lsvTeamFourPlayers.ShowItemToolTips = true;
            this.lsvTeamFourPlayers.Size = new System.Drawing.Size(449, 238);
            this.lsvTeamFourPlayers.TabIndex = 28;
            this.lsvTeamFourPlayers.UseCompatibleStateImageBehavior = false;
            this.lsvTeamFourPlayers.View = System.Windows.Forms.View.Details;
            this.lsvTeamFourPlayers.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsvPlayers_ColumnClick);
            this.lsvTeamFourPlayers.ItemDrag += new System.Windows.Forms.ItemDragEventHandler(this.lsvPlayers_ItemDrag);
            this.lsvTeamFourPlayers.SelectedIndexChanged += new System.EventHandler(this.lsvPlayers_SelectedIndexChanged);
            this.lsvTeamFourPlayers.DragDrop += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragDrop);
            this.lsvTeamFourPlayers.DragEnter += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragEnter);
            this.lsvTeamFourPlayers.DragOver += new System.Windows.Forms.DragEventHandler(this.lsvPlayers_DragOver);
            this.lsvTeamFourPlayers.MouseDown += new System.Windows.Forms.MouseEventHandler(this.lsvPlayers_MouseDown);
            //
            // colSlotID4
            //
            this.colSlotID4.Text = "ID#";
            //
            // colTags4
            //
            this.colTags4.Text = "Tags";
            this.colTags4.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            //
            // colPlayerName4
            //
            this.colPlayerName4.Text = "Name";
            //
            // colSquad4
            //
            this.colSquad4.Text = "Squad";
            //
            // colKit4
            //
            this.colKit4.Text = "Kit";
            //
            // colScore4
            //
            this.colScore4.Text = "Score";
            //
            // colKills4
            //
            this.colKills4.Text = "Kills";
            //
            // colDeaths4
            //
            this.colDeaths4.Text = "Deaths";
            //
            // colKdr4
            //
            this.colKdr4.Text = "K/D r";
            //
            // colPing4
            //
            this.colPing4.Text = "Ping";
            //
            // btnSplitTeams
            //
            this.btnSplitTeams.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.btnSplitTeams.FlatAppearance.BorderSize = 0;
            this.btnSplitTeams.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSplitTeams.Location = new System.Drawing.Point(3, 485);
            this.btnSplitTeams.Name = "btnSplitTeams";
            this.btnSplitTeams.Size = new System.Drawing.Size(35, 23);
            this.btnSplitTeams.TabIndex = 30;
            this.btnSplitTeams.UseVisualStyleBackColor = true;
            this.btnSplitTeams.Click += new System.EventHandler(this.btnSplitTeams_Click);
            //
            // btnCloseAdditionalInfo
            //
            this.btnCloseAdditionalInfo.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnCloseAdditionalInfo.Enabled = false;
            this.btnCloseAdditionalInfo.FlatAppearance.BorderSize = 0;
            this.btnCloseAdditionalInfo.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCloseAdditionalInfo.Location = new System.Drawing.Point(861, 485);
            this.btnCloseAdditionalInfo.Name = "btnCloseAdditionalInfo";
            this.btnCloseAdditionalInfo.Size = new System.Drawing.Size(35, 23);
            this.btnCloseAdditionalInfo.TabIndex = 26;
            this.btnCloseAdditionalInfo.UseVisualStyleBackColor = true;
            this.btnCloseAdditionalInfo.Click += new System.EventHandler(this.btnCloseAdditionalInfo_Click);
            //
            // chkPlayerListShowTeams
            //
            this.chkPlayerListShowTeams.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkPlayerListShowTeams.AutoSize = true;
            this.chkPlayerListShowTeams.Checked = true;
            this.chkPlayerListShowTeams.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkPlayerListShowTeams.Location = new System.Drawing.Point(44, 488);
            this.chkPlayerListShowTeams.Name = "chkPlayerListShowTeams";
            this.chkPlayerListShowTeams.Size = new System.Drawing.Size(90, 19);
            this.chkPlayerListShowTeams.TabIndex = 21;
            this.chkPlayerListShowTeams.Text = "Show teams";
            this.chkPlayerListShowTeams.UseVisualStyleBackColor = true;
            this.chkPlayerListShowTeams.Visible = false;
            this.chkPlayerListShowTeams.CheckedChanged += new System.EventHandler(this.chkPlayerListShowTeams_CheckedChanged);
            //
            // spltInfoPunish
            //
            this.spltInfoPunish.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltInfoPunish.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.spltInfoPunish.IsSplitterFixed = true;
            this.spltInfoPunish.Location = new System.Drawing.Point(0, 0);
            this.spltInfoPunish.Name = "spltInfoPunish";
            //
            // spltInfoPunish.Panel1
            //
            this.spltInfoPunish.Panel1.Controls.Add(this.pnlAdditionalInfo);
            //
            // spltInfoPunish.Panel2
            //
            this.spltInfoPunish.Panel2.Controls.Add(this.tbcCourtMartial);
            this.spltInfoPunish.Size = new System.Drawing.Size(896, 195);
            this.spltInfoPunish.SplitterDistance = 441;
            this.spltInfoPunish.TabIndex = 0;
            //
            // pnlAdditionalInfo
            //
            this.pnlAdditionalInfo.Controls.Add(this.lblPlayersInventory);
            this.pnlAdditionalInfo.Controls.Add(this.lblInventory);
            this.pnlAdditionalInfo.Controls.Add(this.txtPlayerListSelectedBc2GUID);
            this.pnlAdditionalInfo.Controls.Add(this.lblEAGuid);
            this.pnlAdditionalInfo.Controls.Add(this.txtPlayerListSelectedIP);
            this.pnlAdditionalInfo.Controls.Add(this.txtPlayerListSelectedGUID);
            this.pnlAdditionalInfo.Controls.Add(this.lblPlayerListSelectedName);
            this.pnlAdditionalInfo.Controls.Add(this.panel15);
            this.pnlAdditionalInfo.Controls.Add(this.btnPlayerListSelectedCheese);
            this.pnlAdditionalInfo.Controls.Add(this.lblIP);
            this.pnlAdditionalInfo.Controls.Add(this.lblGUID);
            this.pnlAdditionalInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlAdditionalInfo.Location = new System.Drawing.Point(0, 0);
            this.pnlAdditionalInfo.Name = "pnlAdditionalInfo";
            this.pnlAdditionalInfo.Size = new System.Drawing.Size(441, 195);
            this.pnlAdditionalInfo.TabIndex = 7;
            //
            // lblPlayersInventory
            //
            this.lblPlayersInventory.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblPlayersInventory.Location = new System.Drawing.Point(77, 118);
            this.lblPlayersInventory.Name = "lblPlayersInventory";
            this.lblPlayersInventory.Size = new System.Drawing.Size(353, 50);
            this.lblPlayersInventory.TabIndex = 25;
            //
            // lblInventory
            //
            this.lblInventory.AutoSize = true;
            this.lblInventory.Location = new System.Drawing.Point(11, 118);
            this.lblInventory.Name = "lblInventory";
            this.lblInventory.Size = new System.Drawing.Size(60, 15);
            this.lblInventory.TabIndex = 24;
            this.lblInventory.Text = "Inventory:";
            //
            // txtPlayerListSelectedBc2GUID
            //
            this.txtPlayerListSelectedBc2GUID.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtPlayerListSelectedBc2GUID.BackColor = System.Drawing.SystemColors.Window;
            this.txtPlayerListSelectedBc2GUID.Location = new System.Drawing.Point(77, 85);
            this.txtPlayerListSelectedBc2GUID.Name = "txtPlayerListSelectedBc2GUID";
            this.txtPlayerListSelectedBc2GUID.ReadOnly = true;
            this.txtPlayerListSelectedBc2GUID.Size = new System.Drawing.Size(353, 23);
            this.txtPlayerListSelectedBc2GUID.TabIndex = 23;
            //
            // lblEAGuid
            //
            this.lblEAGuid.AutoSize = true;
            this.lblEAGuid.Location = new System.Drawing.Point(19, 88);
            this.lblEAGuid.Name = "lblEAGuid";
            this.lblEAGuid.Size = new System.Drawing.Size(52, 15);
            this.lblEAGuid.TabIndex = 22;
            this.lblEAGuid.Text = "EA Guid:";
            //
            // txtPlayerListSelectedIP
            //
            this.txtPlayerListSelectedIP.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtPlayerListSelectedIP.BackColor = System.Drawing.SystemColors.Window;
            this.txtPlayerListSelectedIP.Location = new System.Drawing.Point(77, 56);
            this.txtPlayerListSelectedIP.Name = "txtPlayerListSelectedIP";
            this.txtPlayerListSelectedIP.ReadOnly = true;
            this.txtPlayerListSelectedIP.Size = new System.Drawing.Size(353, 23);
            this.txtPlayerListSelectedIP.TabIndex = 21;
            //
            // txtPlayerListSelectedGUID
            //
            this.txtPlayerListSelectedGUID.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtPlayerListSelectedGUID.BackColor = System.Drawing.SystemColors.Window;
            this.txtPlayerListSelectedGUID.Location = new System.Drawing.Point(77, 27);
            this.txtPlayerListSelectedGUID.Name = "txtPlayerListSelectedGUID";
            this.txtPlayerListSelectedGUID.ReadOnly = true;
            this.txtPlayerListSelectedGUID.Size = new System.Drawing.Size(353, 23);
            this.txtPlayerListSelectedGUID.TabIndex = 20;
            //
            // lblPlayerListSelectedName
            //
            this.lblPlayerListSelectedName.AutoSize = true;
            this.lblPlayerListSelectedName.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPlayerListSelectedName.Location = new System.Drawing.Point(3, 2);
            this.lblPlayerListSelectedName.Name = "lblPlayerListSelectedName";
            this.lblPlayerListSelectedName.Size = new System.Drawing.Size(0, 17);
            this.lblPlayerListSelectedName.TabIndex = 18;
            //
            // panel15
            //
            this.panel15.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel15.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel15.Location = new System.Drawing.Point(12, 13);
            this.panel15.Name = "panel15";
            this.panel15.Size = new System.Drawing.Size(418, 1);
            this.panel15.TabIndex = 19;
            //
            // btnPlayerListSelectedCheese
            //
            this.btnPlayerListSelectedCheese.Image = ((System.Drawing.Image)(resources.GetObject("btnPlayerListSelectedCheese.Image")));
            this.btnPlayerListSelectedCheese.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.btnPlayerListSelectedCheese.Location = new System.Drawing.Point(12, 141);
            this.btnPlayerListSelectedCheese.Name = "btnPlayerListSelectedCheese";
            this.btnPlayerListSelectedCheese.Size = new System.Drawing.Size(47, 27);
            this.btnPlayerListSelectedCheese.TabIndex = 17;
            this.btnPlayerListSelectedCheese.Text = "Cheese ";
            this.btnPlayerListSelectedCheese.UseVisualStyleBackColor = true;
            this.btnPlayerListSelectedCheese.Visible = false;
            //
            // lblIP
            //
            this.lblIP.AutoSize = true;
            this.lblIP.Location = new System.Drawing.Point(51, 59);
            this.lblIP.Name = "lblIP";
            this.lblIP.Size = new System.Drawing.Size(20, 15);
            this.lblIP.TabIndex = 15;
            this.lblIP.Text = "IP:";
            this.lblIP.TextAlign = System.Drawing.ContentAlignment.TopRight;
            //
            // lblGUID
            //
            this.lblGUID.AutoSize = true;
            this.lblGUID.Location = new System.Drawing.Point(19, 30);
            this.lblGUID.Name = "lblGUID";
            this.lblGUID.Size = new System.Drawing.Size(52, 15);
            this.lblGUID.TabIndex = 13;
            this.lblGUID.Text = "PB Guid:";
            //
            // tbcCourtMartial
            //
            this.tbcCourtMartial.Controls.Add(this.tabCourtMartialBFBC);
            this.tbcCourtMartial.Controls.Add(this.tabCourtMartialPunkbuster);
            this.tbcCourtMartial.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbcCourtMartial.Enabled = false;
            this.tbcCourtMartial.Location = new System.Drawing.Point(0, 0);
            this.tbcCourtMartial.Name = "tbcCourtMartial";
            this.tbcCourtMartial.SelectedIndex = 0;
            this.tbcCourtMartial.Size = new System.Drawing.Size(451, 195);
            this.tbcCourtMartial.TabIndex = 10;
            //
            // tabCourtMartialBFBC
            //
            this.tabCourtMartialBFBC.Controls.Add(this.kbpBfbcPunishPanel);
            this.tabCourtMartialBFBC.Location = new System.Drawing.Point(4, 24);
            this.tabCourtMartialBFBC.Name = "tabCourtMartialBFBC";
            this.tabCourtMartialBFBC.Padding = new System.Windows.Forms.Padding(3);
            this.tabCourtMartialBFBC.Size = new System.Drawing.Size(443, 167);
            this.tabCourtMartialBFBC.TabIndex = 0;
            this.tabCourtMartialBFBC.Text = "BFBC2";
            this.tabCourtMartialBFBC.UseVisualStyleBackColor = true;
            //
            // kbpBfbcPunishPanel
            //
            this.kbpBfbcPunishPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.kbpBfbcPunishPanel.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.kbpBfbcPunishPanel.GUID = "";
            this.kbpBfbcPunishPanel.IP = "";
            this.kbpBfbcPunishPanel.Location = new System.Drawing.Point(3, 3);
            this.kbpBfbcPunishPanel.MaximumSize = new System.Drawing.Size(464, 181);
            this.kbpBfbcPunishPanel.Name = "kbpBfbcPunishPanel";
            this.kbpBfbcPunishPanel.Size = new System.Drawing.Size(437, 161);
            this.kbpBfbcPunishPanel.SlotID = "";
            this.kbpBfbcPunishPanel.SoldierName = "";
            this.kbpBfbcPunishPanel.TabIndex = 1;
            //
            // tabCourtMartialPunkbuster
            //
            this.tabCourtMartialPunkbuster.Controls.Add(this.kbpPunkbusterPunishPanel);
            this.tabCourtMartialPunkbuster.Location = new System.Drawing.Point(4, 24);
            this.tabCourtMartialPunkbuster.Name = "tabCourtMartialPunkbuster";
            this.tabCourtMartialPunkbuster.Padding = new System.Windows.Forms.Padding(3);
            this.tabCourtMartialPunkbuster.Size = new System.Drawing.Size(443, 167);
            this.tabCourtMartialPunkbuster.TabIndex = 1;
            this.tabCourtMartialPunkbuster.Text = "Punkbuster";
            this.tabCourtMartialPunkbuster.UseVisualStyleBackColor = true;
            //
            // kbpPunkbusterPunishPanel
            //
            this.kbpPunkbusterPunishPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.kbpPunkbusterPunishPanel.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.kbpPunkbusterPunishPanel.GUID = "";
            this.kbpPunkbusterPunishPanel.IP = "";
            this.kbpPunkbusterPunishPanel.Location = new System.Drawing.Point(3, 3);
            this.kbpPunkbusterPunishPanel.Name = "kbpPunkbusterPunishPanel";
            this.kbpPunkbusterPunishPanel.Size = new System.Drawing.Size(437, 161);
            this.kbpPunkbusterPunishPanel.SlotID = "";
            this.kbpPunkbusterPunishPanel.SoldierName = "";
            this.kbpPunkbusterPunishPanel.TabIndex = 0;
            //
            // tmrKillDeathHighlight
            //
            this.tmrKillDeathHighlight.Tick += new System.EventHandler(this.tmrKillDeathHighlight_Tick);
            //
            // ctxPlayerOptions
            //
            this.ctxPlayerOptions.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.moveToSquadToolStripMenuItem,
            this.textChatModerationToolStripMenuItem,
            this.reservedSlotToolStripMenuItem,
            this.toolStripMenuItem2,
            this.statsLookupToolStripMenuItem});
            this.ctxPlayerOptions.Name = "ctxPlayerOptions";
            this.ctxPlayerOptions.ShowCheckMargin = true;
            this.ctxPlayerOptions.ShowImageMargin = false;
            this.ctxPlayerOptions.Size = new System.Drawing.Size(190, 120);
            //
            // moveToSquadToolStripMenuItem
            //
            this.moveToSquadToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.attackersToolStripMenuItem,
            this.defendersToolStripMenuItem,
            this.toolStripMenuItem1,
            this.noSquadToolStripMenuItem,
            this.alphaToolStripMenuItem,
            this.betaToolStripMenuItem,
            this.charlieToolStripMenuItem});
            this.moveToSquadToolStripMenuItem.Name = "moveToSquadToolStripMenuItem";
            this.moveToSquadToolStripMenuItem.Size = new System.Drawing.Size(189, 22);
            this.moveToSquadToolStripMenuItem.Text = "Move X to..";
            //
            // attackersToolStripMenuItem
            //
            this.attackersToolStripMenuItem.Name = "attackersToolStripMenuItem";
            this.attackersToolStripMenuItem.Size = new System.Drawing.Size(127, 22);
            this.attackersToolStripMenuItem.Text = "Attackers";
            //
            // defendersToolStripMenuItem
            //
            this.defendersToolStripMenuItem.Name = "defendersToolStripMenuItem";
            this.defendersToolStripMenuItem.Size = new System.Drawing.Size(127, 22);
            this.defendersToolStripMenuItem.Text = "Defenders";
            //
            // toolStripMenuItem1
            //
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(124, 6);
            //
            // noSquadToolStripMenuItem
            //
            this.noSquadToolStripMenuItem.Name = "noSquadToolStripMenuItem";
            this.noSquadToolStripMenuItem.Size = new System.Drawing.Size(127, 22);
            this.noSquadToolStripMenuItem.Text = "No Squad";
            //
            // alphaToolStripMenuItem
            //
            this.alphaToolStripMenuItem.Name = "alphaToolStripMenuItem";
            this.alphaToolStripMenuItem.Size = new System.Drawing.Size(127, 22);
            this.alphaToolStripMenuItem.Text = "Alpha";
            //
            // betaToolStripMenuItem
            //
            this.betaToolStripMenuItem.Name = "betaToolStripMenuItem";
            this.betaToolStripMenuItem.Size = new System.Drawing.Size(127, 22);
            this.betaToolStripMenuItem.Text = "Beta";
            //
            // charlieToolStripMenuItem
            //
            this.charlieToolStripMenuItem.Name = "charlieToolStripMenuItem";
            this.charlieToolStripMenuItem.Size = new System.Drawing.Size(127, 22);
            this.charlieToolStripMenuItem.Text = "Charlie";
            //
            // textChatModerationToolStripMenuItem
            //
            this.textChatModerationToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mutedToolStripMenuItem,
            this.normalToolStripMenuItem,
            this.voiceToolStripMenuItem,
            this.adminToolStripMenuItem});
            this.textChatModerationToolStripMenuItem.Name = "textChatModerationToolStripMenuItem";
            this.textChatModerationToolStripMenuItem.Size = new System.Drawing.Size(189, 22);
            this.textChatModerationToolStripMenuItem.Text = "Text Chat Moderation";
            //
            // mutedToolStripMenuItem
            //
            this.mutedToolStripMenuItem.Name = "mutedToolStripMenuItem";
            this.mutedToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
            this.mutedToolStripMenuItem.Text = "Muted";
            this.mutedToolStripMenuItem.Click += new System.EventHandler(this.mutedToolStripMenuItem_Click);
            //
            // normalToolStripMenuItem
            //
            this.normalToolStripMenuItem.Name = "normalToolStripMenuItem";
            this.normalToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
            this.normalToolStripMenuItem.Text = "Normal";
            this.normalToolStripMenuItem.Click += new System.EventHandler(this.normalToolStripMenuItem_Click);
            //
            // voiceToolStripMenuItem
            //
            this.voiceToolStripMenuItem.Name = "voiceToolStripMenuItem";
            this.voiceToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
            this.voiceToolStripMenuItem.Text = "Voice";
            this.voiceToolStripMenuItem.Click += new System.EventHandler(this.voiceToolStripMenuItem_Click);
            //
            // adminToolStripMenuItem
            //
            this.adminToolStripMenuItem.Name = "adminToolStripMenuItem";
            this.adminToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
            this.adminToolStripMenuItem.Text = "Admin";
            this.adminToolStripMenuItem.Click += new System.EventHandler(this.adminToolStripMenuItem_Click);
            //
            // reservedSlotToolStripMenuItem
            //
            this.reservedSlotToolStripMenuItem.Name = "reservedSlotToolStripMenuItem";
            this.reservedSlotToolStripMenuItem.Size = new System.Drawing.Size(189, 22);
            this.reservedSlotToolStripMenuItem.Text = "Reserved Slot";
            this.reservedSlotToolStripMenuItem.Click += new System.EventHandler(this.reservedSlotToolStripMenuItem_Click);
            //
            // toolStripMenuItem2
            //
            this.toolStripMenuItem2.Name = "toolStripMenuItem2";
            this.toolStripMenuItem2.Size = new System.Drawing.Size(186, 6);
            //
            // statsLookupToolStripMenuItem
            //
            this.statsLookupToolStripMenuItem.Name = "statsLookupToolStripMenuItem";
            this.statsLookupToolStripMenuItem.Size = new System.Drawing.Size(189, 22);
            this.statsLookupToolStripMenuItem.Text = "Stats Lookup";
            this.statsLookupToolStripMenuItem.Click += new System.EventHandler(this.statsLookupToolStripMenuItem_Click);
            //
            // uscPlayerListPanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.spltListAdditionalInfo);
            this.DoubleBuffered = true;
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscPlayerListPanel";
            this.Size = new System.Drawing.Size(896, 708);
            this.Load += new System.EventHandler(this.uscPlayerListPanel_Load);
            this.Resize += new System.EventHandler(this.uscPlayerListPanel_Resize);
            this.spltListAdditionalInfo.Panel1.ResumeLayout(false);
            this.spltListAdditionalInfo.Panel1.PerformLayout();
            this.spltListAdditionalInfo.Panel2.ResumeLayout(false);
            this.spltListAdditionalInfo.ResumeLayout(false);
            this.spltFourSplit.Panel1.ResumeLayout(false);
            this.spltFourSplit.Panel2.ResumeLayout(false);
            this.spltFourSplit.ResumeLayout(false);
            this.spltTwoSplit.Panel1.ResumeLayout(false);
            this.spltTwoSplit.Panel2.ResumeLayout(false);
            this.spltTwoSplit.ResumeLayout(false);
            this.spltBottomTwoSplit.Panel1.ResumeLayout(false);
            this.spltBottomTwoSplit.Panel2.ResumeLayout(false);
            this.spltBottomTwoSplit.ResumeLayout(false);
            this.spltInfoPunish.Panel1.ResumeLayout(false);
            this.spltInfoPunish.Panel2.ResumeLayout(false);
            this.spltInfoPunish.ResumeLayout(false);
            this.pnlAdditionalInfo.ResumeLayout(false);
            this.pnlAdditionalInfo.PerformLayout();
            this.tbcCourtMartial.ResumeLayout(false);
            this.tabCourtMartialBFBC.ResumeLayout(false);
            this.tabCourtMartialPunkbuster.ResumeLayout(false);
            this.ctxPlayerOptions.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer spltListAdditionalInfo;
        private System.Windows.Forms.SplitContainer spltInfoPunish;
        private System.Windows.Forms.Panel pnlAdditionalInfo;
        private System.Windows.Forms.TextBox txtPlayerListSelectedIP;
        private System.Windows.Forms.TextBox txtPlayerListSelectedGUID;
        private System.Windows.Forms.Label lblPlayerListSelectedName;
        private System.Windows.Forms.Panel panel15;
        private System.Windows.Forms.Button btnPlayerListSelectedCheese;
        private System.Windows.Forms.Label lblIP;
        private System.Windows.Forms.Label lblGUID;
        private System.Windows.Forms.TabControl tbcCourtMartial;
        private System.Windows.Forms.TabPage tabCourtMartialBFBC;
        private uscPlayerPunishPanel kbpBfbcPunishPanel;
        private System.Windows.Forms.TabPage tabCourtMartialPunkbuster;
        private uscPlayerPunishPanel kbpPunkbusterPunishPanel;
        private System.Windows.Forms.TextBox txtPlayerListSelectedBc2GUID;
        private System.Windows.Forms.Label lblEAGuid;
        private System.Windows.Forms.CheckBox chkPlayerListShowTeams;
        private System.Windows.Forms.Button btnCloseAdditionalInfo;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvTeamTwoPlayers;
        private System.Windows.Forms.ColumnHeader colSlotID2;
        private System.Windows.Forms.ColumnHeader colTags2;
        private System.Windows.Forms.ColumnHeader colPlayerName2;
        private System.Windows.Forms.ColumnHeader colSquad2;
        private System.Windows.Forms.ColumnHeader colScore2;
        private System.Windows.Forms.ColumnHeader colKills2;
        private System.Windows.Forms.ColumnHeader colDeaths2;
        private System.Windows.Forms.ColumnHeader colKdr2;
        private System.Windows.Forms.ColumnHeader colPing2;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvTeamOnePlayers;
        private System.Windows.Forms.ColumnHeader colSlotID1;
        private System.Windows.Forms.ColumnHeader colTags1;
        private System.Windows.Forms.ColumnHeader colPlayerName1;
        private System.Windows.Forms.ColumnHeader colSquad1;
        private System.Windows.Forms.ColumnHeader colScore1;
        private System.Windows.Forms.ColumnHeader colKills1;
        private System.Windows.Forms.ColumnHeader colDeaths1;
        private System.Windows.Forms.ColumnHeader colKdr1;
        private System.Windows.Forms.ColumnHeader colPing1;
        private System.Windows.Forms.Button btnSplitTeams;
        private System.Windows.Forms.SplitContainer spltTwoSplit;
        private System.Windows.Forms.SplitContainer spltFourSplit;
        private System.Windows.Forms.SplitContainer spltBottomTwoSplit;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvTeamThreePlayers;
        private System.Windows.Forms.ColumnHeader colSlotID3;
        private System.Windows.Forms.ColumnHeader colTags3;
        private System.Windows.Forms.ColumnHeader colPlayerName3;
        private System.Windows.Forms.ColumnHeader colSquad3;
        private System.Windows.Forms.ColumnHeader colScore3;
        private System.Windows.Forms.ColumnHeader colKills3;
        private System.Windows.Forms.ColumnHeader colDeaths3;
        private System.Windows.Forms.ColumnHeader colKdr3;
        private System.Windows.Forms.ColumnHeader colPing3;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvTeamFourPlayers;
        private System.Windows.Forms.ColumnHeader colSlotID4;
        private System.Windows.Forms.ColumnHeader colTags4;
        private System.Windows.Forms.ColumnHeader colPlayerName4;
        private System.Windows.Forms.ColumnHeader colSquad4;
        private System.Windows.Forms.ColumnHeader colScore4;
        private System.Windows.Forms.ColumnHeader colKills4;
        private System.Windows.Forms.ColumnHeader colDeaths4;
        private System.Windows.Forms.ColumnHeader colKdr4;
        private System.Windows.Forms.ColumnHeader colPing4;
        private System.Windows.Forms.Timer tmrKillDeathHighlight;
        private System.Windows.Forms.ColumnHeader colKit1;
        private System.Windows.Forms.ColumnHeader colKit2;
        private System.Windows.Forms.ColumnHeader colKit3;
        private System.Windows.Forms.ColumnHeader colKit4;
        private System.Windows.Forms.Label lblPlayersInventory;
        private System.Windows.Forms.Label lblInventory;
        private System.Windows.Forms.ContextMenuStrip ctxPlayerOptions;
        private System.Windows.Forms.ToolStripMenuItem moveToSquadToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem attackersToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem defendersToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem noSquadToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem alphaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem betaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem charlieToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem reservedSlotToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem textChatModerationToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem mutedToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem normalToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem voiceToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem adminToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem2;
        private System.Windows.Forms.ToolStripMenuItem statsLookupToolStripMenuItem;
    }*/
}
