package procon.controls;

public class UscListControlPanel {

    /*public partial class uscListControlPanel : UserControl {

        private frmMain m_frmMain;
        private uscServerConnection m_uscConnectionPanel;

        private PRoConClient m_prcClient;

        private ListViewColumnSorter m_lvwReservedSlotsColumnSorter;
        private ListViewColumnSorter m_lvwBanlistColumnSorter;
        private Font m_fntComboBoxSelectedFont;

        private Dictionary<string, AsyncStyleSetting> m_dicAsyncSettingControls;
        //private Dictionary<string, string> m_dicFriendlyPlaylistNames; // strPlaylist, strGamemode
        //private Dictionary<string, Dictionary<string, string>> m_dicMaplistsPerPlaylist; // strPlaylist, Dictionary<strLevel, strPublicLevelName>>

        //public delegate void SendCommandDelegate(List<string> lstCommand);
        //public event SendCommandDelegate SendCommand;

       // private int m_iReselectShufflingMapIndex = 0;

        private bool m_blSettingAppendingReservedPlayer;
        private bool m_blSettingRemovingReservedPlayer;

        //private bool m_blSettingAppendingSingleMap;
        //private bool m_blSettingNewPlaylist;

        private string[] ma_strTimeDescriptionsShort;
        private string[] ma_strTimeDescriptionsLong;

        private CPrivileges m_spPrivileges;

        private Regex m_regIP = null;
        private Regex m_regPbGUID = null;
        private Regex m_regBc2GUID = null;

        *//*
        public List<string> SetListsSettings {
            set {
                bool blChecked = true;

                if (value.Count >= 1 && bool.TryParse(value[0], out blChecked) == true) {
                    this.spltBanlistManualBans.Panel2Collapsed = !blChecked;
                    this.picCloseOpenManualBans_Click(null, null);
                }
            }
        }

        public string ListsSettings {
            get {
                return String.Format("{0}", this.spltBanlistManualBans.Panel2Collapsed);
            }
        }
        *//*
        public uscListControlPanel() {
            InitializeComponent();

            this.SetStyle(ControlStyles.UserPaint, true);
            this.SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.DoubleBuffer, true);

            this.m_regIP = new Regex(@"^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", RegexOptions.Compiled);
            this.m_regPbGUID = new Regex("^[A-Fa-f0-9]{32}$", RegexOptions.Compiled);
            this.m_regBc2GUID = new Regex("^EA_[A-Fa-f0-9]{32}$", RegexOptions.Compiled);

            this.m_lvwReservedSlotsColumnSorter = new ListViewColumnSorter();
            this.lsvReservedList.ListViewItemSorter = this.m_lvwReservedSlotsColumnSorter;

            this.m_lvwBanlistColumnSorter = new ListViewColumnSorter();
            this.lsvBanlist.ListViewItemSorter = this.m_lvwBanlistColumnSorter;

            //this.pnlCurrentMaplist.Dock = DockStyle.Fill;
            //this.pnlMaplistConfirmation.Dock = DockStyle.Fill;
            this.pnlReservedPanel.Dock = DockStyle.Fill;

            //this.m_dicFriendlyPlaylistNames = new Dictionary<string, string>();
            //this.m_dicMaplistsPerPlaylist = new Dictionary<string, Dictionary<string, string>>();

            this.m_fntComboBoxSelectedFont = new Font("Segoe UI", 10, FontStyle.Bold);

            //this.m_iReselectShufflingMapIndex = 0;
            //this.m_blSettingNewPlaylist = false;

            this.m_blSettingAppendingReservedPlayer = false;
            this.m_blSettingRemovingReservedPlayer = false;

            this.m_dicAsyncSettingControls = new Dictionary<string, AsyncStyleSetting>();

            // Maplist updates

            //this.m_dicAsyncSettingControls.Add("local.playlist.change", new AsyncStyleSetting(this.picMaplistChangePlaylist, this.lsvMaplist, new Control[] { this.pnlMaplistPositionControls, this.pnlMaplistAddMap, this.lsvMaplist, this.lnkMaplistChangePlaylist }, true));
            //this.m_dicAsyncSettingControls.Add("local.maplist.change", new AsyncStyleSetting(this.picMaplistAlterMaplist, this.lsvMaplist, new Control[] { this.pnlMaplistPositionControls, this.pnlMaplistAddMap, this.lsvMaplist, this.lnkMaplistChangePlaylist }, true));

            //this.m_blSettingAppendingSingleMap = false;
            //this.m_dicAsyncSettingControls.Add("local.maplist.append", new AsyncStyleSetting(this.picMaplistAppendMap, this.lsvMaplist, new Control[] { this.lblMaplistPool, this.lnkMaplistAddMapToList, this.lnkMaplistSetNextMap }, true));
            //this.m_dicAsyncSettingControls.Add("local.maplist.setnextlevel", new AsyncStyleSetting(this.picMaplistAppendMap, this.lsvMaplist, new Control[] { this.lblMaplistPool, this.lnkMaplistAddMapToList, this.lnkMaplistSetNextMap }, true));

            //this.m_dicAsyncSettingControls.Add("local.maplist.runnextlevel", new AsyncStyleSetting(this.picMaplistRunNextMap, this.lsvMaplist, new Control[] { this.lnkMaplistRunNextMap }, true));
            //this.m_dicAsyncSettingControls.Add("local.maplist.restartlevel", new AsyncStyleSetting(this.picMaplistRestartMap, this.lsvMaplist, new Control[] { this.lnkMaplistRestartMap }, true));

            // Reservedlist updates
            this.m_dicAsyncSettingControls.Add("local.reservedlist.list", new AsyncStyleSetting(this.picReservedList, this.lsvReservedList, new Control[] { this.btnReservedSlotsListRefresh }, true));
            this.m_dicAsyncSettingControls.Add("local.reservedlist.append", new AsyncStyleSetting(this.picReservedAddSoldierName, this.lsvReservedList, new Control[] { this.lblReservedAddSoldierName, this.txtReservedAddSoldierName, this.lnkReservedAddSoldierName }, true));
            this.m_dicAsyncSettingControls.Add("local.reservedlist.remove", new AsyncStyleSetting(this.picReservedList, this.lsvReservedList, new Control[] { this.btnReservedRemoveSoldier }, true));

            this.m_dicAsyncSettingControls.Add("local.banlist.clearlist", new AsyncStyleSetting(this.picClearLists, this.btnBanlistClearBanlist, new Control[] { this.btnBanlistClearBanlist }, true));
            this.m_dicAsyncSettingControls.Add("local.banlist.unban", new AsyncStyleSetting(this.picUnbanPlayer, this.btnBanlistUnban, new Control[] { this.btnBanlistUnban }, true));

            this.m_dicAsyncSettingControls.Add("local.banlist.banning", new AsyncStyleSetting(this.picBanlistManualBanOkay, this.btnBanlistAddBan, new Control[] { this.btnBanlistAddBan }, false));

            this.ma_strTimeDescriptionsShort = new string[] { "y ", "y ", "M ", "M ", "w ", "w ", "d ", "d ", "h ", "h ", "m ", "m ", "s ", "s " };
            this.ma_strTimeDescriptionsLong = new string[] { " year ", " years ", " month ", " months ", " week ", " weeks ", " day ", " days ", " hour ", " hours ", " minute ", " minutes ", " second", " seconds" };
            this.cboBanlistTimeMultiplier.SelectedIndex = 0;

            this.m_spPrivileges = new CPrivileges(CPrivileges.FullPrivilegesFlags);
            //this.m_spPrivileges.PrivilegesFlags = CPrivileges.FullPrivilegesFlags;
        }

        public void m_prcClient_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {

            this.m_spPrivileges = spPrivs;

            //this.m_dicAsyncSettingControls["local.playlist.change"].m_blReEnableControls = this.m_spPrivileges.CanEditMapList;
            //this.lnkMaplistChangePlaylist.Enabled = this.m_spPrivileges.CanEditMapList;

            //this.m_dicAsyncSettingControls["local.maplist.change"].m_blReEnableControls = this.m_spPrivileges.CanEditMapList;
            //this.m_dicAsyncSettingControls["local.maplist.append"].m_blReEnableControls = this.m_spPrivileges.CanEditMapList;
            //this.lnkMaplistAddMapToList.Enabled = this.m_spPrivileges.CanEditMapList;
            //if (this.lsvMaplist.SelectedItems.Count > 0) {
            //    this.pnlMaplistPositionControls.Enabled = this.m_spPrivileges.CanEditMapList;
            //} // ELSE It'll already be disabled

            //this.m_dicAsyncSettingControls["local.maplist.setnextlevel"].m_blReEnableControls = this.m_spPrivileges.CanUseMapFunctions;
            //this.m_dicAsyncSettingControls["local.maplist.restartlevel"].m_blReEnableControls = this.m_spPrivileges.CanUseMapFunctions;
            //this.m_dicAsyncSettingControls["local.maplist.restartlevel"].m_blReEnableControls = this.m_spPrivileges.CanUseMapFunctions;
            //this.lnkMaplistSetNextMap.Enabled = this.m_spPrivileges.CanUseMapFunctions;
            //this.lnkMaplistRunNextMap.Enabled = this.m_spPrivileges.CanUseMapFunctions;
            //this.lnkMaplistRestartMap.Enabled = this.m_spPrivileges.CanUseMapFunctions;

            this.m_dicAsyncSettingControls["local.reservedlist.append"].m_blReEnableControls = this.m_spPrivileges.CanEditReservedSlotsList;
            this.m_dicAsyncSettingControls["local.reservedlist.remove"].m_blReEnableControls = this.m_spPrivileges.CanEditReservedSlotsList;
            if (this.lsvReservedList.SelectedItems.Count > 0) {
                this.btnReservedRemoveSoldier.Enabled = this.m_spPrivileges.CanEditReservedSlotsList;
            } // ELSE It'll already be disabled
            this.lnkReservedAddSoldierName.Enabled = this.m_spPrivileges.CanEditReservedSlotsList;

            this.m_dicAsyncSettingControls["local.banlist.clearlist"].m_blReEnableControls = this.m_spPrivileges.CanEditBanList;
            this.m_dicAsyncSettingControls["local.banlist.unban"].m_blReEnableControls = this.m_spPrivileges.CanEditBanList;
            this.btnBanlistClearBanlist.Enabled = this.m_spPrivileges.CanEditBanList;

            if (this.lsvBanlist.SelectedItems.Count > 0) {
                this.btnBanlistUnban.Enabled = this.m_spPrivileges.CanEditBanList;
            } // ELSE It'll already be disabled

            // Manual banning..
            this.rdoBanlistPbGUID.Enabled = this.rdoBanlistPermanent.Enabled = this.m_spPrivileges.CanPermanentlyBanPlayers;

            if (this.rdoBanlistPbGUID.Checked == true && this.rdoBanlistPbGUID.Enabled == false) {
                this.rdoBanlistName.Checked = true;
            }

            if (this.rdoBanlistPermanent.Checked == true && this.rdoBanlistPermanent.Enabled == false) {
                this.rdoBanlistTemporary.Checked = true;
            }

            this.spltBanlistManualBans.Panel2.Enabled = this.m_spPrivileges.CanTemporaryBanPlayers;
        }

        public void Initialize(frmMain frmMainWindow, uscServerConnection uscConnectionPanel) {
            this.m_frmMain = frmMainWindow;
            this.m_uscConnectionPanel = uscConnectionPanel;

            this.uscMaplist1.Initialize(frmMainWindow, uscConnectionPanel);

            this.tbcLists.ImageList = this.m_frmMain.iglIcons;

            this.tabBanlist.ImageKey = "mouse_ban.png";
            this.tabMaplist.ImageKey = "world.png";
            this.tabReservedSlots.ImageKey = "user.png";

            this.btnBanlistRefresh.ImageList = this.m_frmMain.iglIcons;
            this.btnBanlistRefresh.ImageKey = "arrow_refresh.png";

            //this.btnMaplistMoveMapUp.ImageList = this.m_frmMain.iglIcons;
            //this.btnMaplistMoveMapUp.ImageKey = "bullet_arrow_up.png";

            //this.btnMaplistRemoveMap.ImageList = this.m_frmMain.iglIcons;
            //this.btnMaplistRemoveMap.ImageKey = "cross.png";

            this.btnReservedRemoveSoldier.ImageList = this.m_frmMain.iglIcons;
            this.btnReservedRemoveSoldier.ImageKey = "cross.png";

            this.btnReservedSlotsListRefresh.ImageList = this.m_frmMain.iglIcons;
            this.btnReservedSlotsListRefresh.ImageKey = "arrow_refresh.png";

            //this.btnMaplistMoveMapDown.ImageList = this.m_frmMain.iglIcons;
            //this.btnMaplistMoveMapDown.ImageKey = "bullet_arrow_down.png";

            this.picCloseOpenManualBans.Image = this.m_frmMain.iglIcons.Images["arrow_down.png"];

            this.picBanlistIPError.Image = this.picBanlistGUIDError.Image = this.m_frmMain.iglIcons.Images["cross.png"];
            //this.picBanlistManualBanOkay.Image = this.m_frmMain.iglIcons.Images["tick.png"];

            this.copyToolStripMenuItem.Image = this.m_frmMain.iglIcons.Images["page_copy.png"];

            this.uscTextChatModerationListcs1.Initialize(frmMainWindow);
        }

        private void uscListControlPanel_Load(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                //this.RefreshLocalMaplist();

                this.SendCommand("mapList.list", "rounds");

                if (this.m_prcClient.GameType == "MOH") {
                    this.tabReservedSlots.Enabled = false;
                    this.lblMohNotice.Visible = true;
                }

            }
        }

        public void SetConnection(PRoConClient prcClient) {
            if ((this.m_prcClient = prcClient) != null) {
                if (this.m_prcClient.Game != null) {
                    this.m_prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    this.m_prcClient.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcClient_GameTypeDiscovered);
                }

                this.uscMaplist1.SetConnection(prcClient);
                this.uscTextChatModerationListcs1.SetConnection(prcClient);
            }
        }

        private void m_prcClient_GameTypeDiscovered(PRoConClient sender) {

            this.m_prcClient.Game.ReservedSlotsSave += new FrostbiteClient.EmptyParamterHandler(this.OnReservedSlotsSave);
            this.m_prcClient.Game.ReservedSlotsList += new FrostbiteClient.ReservedSlotsListHandler(this.OnReservedSlotsList);
            this.m_prcClient.Game.ReservedSlotsPlayerAdded += new FrostbiteClient.ReservedSlotsPlayerHandler(this.OnReservedSlotsPlayerAdded);
            this.m_prcClient.Game.ReservedSlotsPlayerRemoved += new FrostbiteClient.ReservedSlotsPlayerHandler(this.OnReservedSlotsPlayerRemoved);

            this.m_prcClient.Game.BanListClear += new FrostbiteClient.EmptyParamterHandler(this.OnClearBanList);
            this.m_prcClient.FullBanListList += new PRoConClient.FullBanListListHandler(this.OnBanList);
            this.m_prcClient.PunkbusterPlayerUnbanned += new PRoConClient.PunkbusterBanHandler(m_prcClient_PunkbusterPlayerUnbanned);
            this.m_prcClient.Game.BanListRemove += new FrostbiteClient.BanListRemoveHandler(this.OnUnban);
            this.m_prcClient.PunkbusterPlayerBanned += new PRoConClient.PunkbusterBanHandler(this.OnPbGuidBan);


            this.m_prcClient.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcClient_ProconPrivileges);

            //this.m_prcClient.Reasons.ItemRemoved += new NotificationList<string>.ItemModifiedHandler(Reasons_ItemRemoved);
            this.m_prcClient.Reasons.ItemAdded += new NotificationList<string>.ItemModifiedHandler(Reasons_ItemAdded);

            this.m_prcClient.ListSettings.ManualBansVisibleChange += new PRoCon.Core.Lists.ListsSettings.ManualBansVisibleChangeHandler(ListSettings_ManualBansVisibleChange);

            this.m_prcClient.ListSettings.ManualBansVisible = this.m_prcClient.ListSettings.ManualBansVisible;

            this.cboBanlistReason.Items.Clear();
            foreach (string strReason in this.m_prcClient.Reasons) {
                this.Reasons_ItemAdded(0, strReason);
            }

            if (this.m_prcClient.FullVanillaBanList != null) {
                this.OnBanList(this.m_prcClient, this.m_prcClient.FullVanillaBanList);
            }

            if (this.m_prcClient.ReservedSlotList != null) {
                this.OnReservedSlotsList(this.m_prcClient.Game, new List<string>(this.m_prcClient.ReservedSlotList));
            }
        }

        public void SetLocalization(CLocalization clocLanguage) {
            //this.m_prcClient.Language = clocLanguage;

            // private string[] m_astrTimeDescriptionsShort = new string[] { "y ", "y ", "M ", "M ", "w ", "w ", "d ", "d ", "h ", "h ", "m ", "m ", "s ", "s " };
            this.ma_strTimeDescriptionsShort[13] = clocLanguage.GetLocalized("global.Seconds.Short", null);
            this.ma_strTimeDescriptionsShort[12] = clocLanguage.GetLocalized("global.Seconds.Short", null);
            this.ma_strTimeDescriptionsShort[11] = clocLanguage.GetLocalized("global.Minutes.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[10] = clocLanguage.GetLocalized("global.Minutes.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[9] = clocLanguage.GetLocalized("global.Hours.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[8] = clocLanguage.GetLocalized("global.Hours.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[7] = clocLanguage.GetLocalized("global.Days.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[6] = clocLanguage.GetLocalized("global.Days.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[5] = clocLanguage.GetLocalized("global.Weeks.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[4] = clocLanguage.GetLocalized("global.Weeks.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[3] = clocLanguage.GetLocalized("global.Months.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[2] = clocLanguage.GetLocalized("global.Months.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[1] = clocLanguage.GetLocalized("global.Years.Short", null) + " ";
            this.ma_strTimeDescriptionsShort[0] = clocLanguage.GetLocalized("global.Years.Short", null) + " ";

            this.ma_strTimeDescriptionsLong[13] = " " + clocLanguage.GetLocalized("global.Seconds.Plural", null).ToLower();
            this.ma_strTimeDescriptionsLong[12] = " " + clocLanguage.GetLocalized("global.Seconds.Singular", null).ToLower();
            this.ma_strTimeDescriptionsLong[11] = " " + clocLanguage.GetLocalized("global.Minutes.Plural", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[10] = " " + clocLanguage.GetLocalized("global.Minutes.Singular", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[9] = " " + clocLanguage.GetLocalized("global.Hours.Plural", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[8] = " " + clocLanguage.GetLocalized("global.Hours.Singular", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[7] = " " + clocLanguage.GetLocalized("global.Days.Plural", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[6] = " " + clocLanguage.GetLocalized("global.Days.Singular", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[5] = " " + clocLanguage.GetLocalized("global.Weeks.Plural", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[4] = " " + clocLanguage.GetLocalized("global.Weeks.Singular", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[3] = " " + clocLanguage.GetLocalized("global.Months.Plural", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[2] = " " + clocLanguage.GetLocalized("global.Months.Singular", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[1] = " " + clocLanguage.GetLocalized("global.Years.Plural", null).ToLower() + " ";
            this.ma_strTimeDescriptionsLong[0] = " " + clocLanguage.GetLocalized("global.Years.Singular", null).ToLower() + " ";

            this.cboBanlistTimeMultiplier.Items[0] = clocLanguage.GetLocalized("global.Minutes.Plural", null);
            this.cboBanlistTimeMultiplier.Items[1] = clocLanguage.GetLocalized("global.Hours.Plural", null);
            this.cboBanlistTimeMultiplier.Items[2] = clocLanguage.GetLocalized("global.Days.Plural", null);
            this.cboBanlistTimeMultiplier.Items[3] = clocLanguage.GetLocalized("global.Weeks.Plural", null);
            this.cboBanlistTimeMultiplier.Items[4] = clocLanguage.GetLocalized("global.Months.Plural", null);

            this.tabBanlist.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist", null);
            this.colName.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colName", null);
            this.colIP.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colIP", null);
            this.colGUID.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colGUID", null);
            this.colType.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colType", null);
            //this.colTime.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colTime", null);
            //this.colBanLength.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colBanLength", null);
            this.colTimeRemaining.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colTimeRemaining", null);
            this.colReason.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colReason", null);
            this.btnBanlistClearBanlist.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistClearBanlist", null);
            //this.btnBanlistClearBanlist.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistClearNameList", null);
            //this.btnBanlistClearIPList.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistClearIPList", null);
            this.btnBanlistUnban.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistUnban", null);

            this.tabMaplist.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist");

            this.tabReservedSlots.Text = clocLanguage.GetLocalized("uscListControlPanel.tabReservedSlots", null);
            this.lblReservedCurrent.Text = clocLanguage.GetLocalized("uscListControlPanel.tabReservedSlots.lblReservedCurrent", null);
            this.colSoldierNames.Text = clocLanguage.GetLocalized("uscListControlPanel.tabReservedSlots.lsvReservedList.colSoldierNames", null);
            this.lblReservedAddSoldierName.Text = clocLanguage.GetLocalized("uscListControlPanel.tabReservedSlots.lblReservedAddSoldierName", null);
            this.lnkReservedAddSoldierName.Text = clocLanguage.GetLocalized("uscListControlPanel.tabReservedSlots.lnkReservedAddSoldierName", null);

            this.lnkCloseOpenManualBans.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lnkCloseOpenManualBans.Close", null);
            this.rdoBanlistName.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.rdoBanlistName", null);
            //this.rdoBanlistPbGUID.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.rdoBanlistGUID", null);
            this.lblBanlistReason.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lblBanlistReason", null) + ":";
            this.rdoBanlistPermanent.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.rdoBanlistPermanent", null);
            this.rdoBanlistTemporary.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.rdoBanlistTemporary", null);
            this.lblBanlistTime.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.lblBanlistTime", null) + ":";
            this.btnBanlistAddBan.Text = clocLanguage.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistAddBan", null);

            this.uscMaplist1.SetLocalization(clocLanguage);

            this.tabTextChatModeration.Text = clocLanguage.GetLocalized("uscTextChatModerationList.Title", null);
            this.uscTextChatModerationListcs1.SetLocalization(clocLanguage);
        }

        //public delegate void OnTabChangeDelegate(object sender, Stack<string> stkTabIndexes);
        public event uscServerConnection.OnTabChangeDelegate OnTabChange;

        private void tbcLists_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.OnTabChange != null) {
                Stack<string> stkTabIndexes = new Stack<string>();
                stkTabIndexes.Push(tbcLists.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        public void SetTabIndexes(Stack<string> stkTabIndexes) {
            if (tbcLists.TabPages.ContainsKey(stkTabIndexes.Peek()) == true) {
                this.tbcLists.SelectedTab = tbcLists.TabPages[stkTabIndexes.Pop()];
            }
        }

        private void SendCommand(params string[] a_strCommand) {
            if (this.m_prcClient != null) {
                this.m_prcClient.SendRequest(new List<string>(a_strCommand));
            }
        }

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

        #region Reserved Slots

        public void OnReservedSlotsList(FrostbiteClient sender, List<string> lstSoldierNames) {
            this.lsvReservedList.BeginUpdate();
            this.lsvReservedList.Items.Clear();
            foreach (string strSoldierName in lstSoldierNames) {
                if (this.lsvReservedList.Items.ContainsKey(strSoldierName) == false) {

                    ListViewItem lsvNewSoldier = new ListViewItem(strSoldierName);
                    lsvNewSoldier.Name = strSoldierName;

                    this.lsvReservedList.Items.Add(lsvNewSoldier);
                }
            }
            this.lsvReservedList.EndUpdate();
        }

        public void OnReservedSlotsPlayerRemoved(FrostbiteClient sender, string strSoldierName) {
            if (this.lsvReservedList.Items.ContainsKey(strSoldierName) == true) {
                this.lsvReservedList.Items.RemoveByKey(strSoldierName);
            }
        }

        public void OnReservedSlotsPlayerAdded(FrostbiteClient sender, string strSoldierName) {
            if (this.lsvReservedList.Items.ContainsKey(strSoldierName) == false) {

                ListViewItem lsvNewSoldier = new ListViewItem(strSoldierName);
                lsvNewSoldier.Name = strSoldierName;

                this.lsvReservedList.Items.Add(lsvNewSoldier);
            }
        }

        public void OnReservedSlotsSave(FrostbiteClient sender) {
            if (this.m_blSettingAppendingReservedPlayer == true) {
                this.OnSettingResponse("local.reservedlist.append", true);
                this.m_blSettingAppendingReservedPlayer = false;
            }
            else if (this.m_blSettingRemovingReservedPlayer == true) {
                this.OnSettingResponse("local.reservedlist.remove", true);
                this.m_blSettingRemovingReservedPlayer = false;
            }

        }

        private void lnkReservedAddSoldierName_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            if (this.txtReservedAddSoldierName.Text.Length > 0) {
                this.m_blSettingAppendingReservedPlayer = true;
                this.WaitForSettingResponse("local.reservedlist.append");

                this.m_prcClient.Game.SendReservedSlotsAddPlayerPacket(this.txtReservedAddSoldierName.Text);
                //this.SendCommand("reservedSlots.addPlayer", );

                this.m_prcClient.Game.SendReservedSlotsSavePacket();
                //this.SendCommand("reservedSlots.save");

                this.txtReservedAddSoldierName.Clear();
                this.txtReservedAddSoldierName.Focus();
            }
        }

        private void lsvReservedList_SelectedIndexChanged(object sender, EventArgs e) {

            if (this.lsvReservedList.SelectedItems.Count > 0 && this.lsvReservedList.FocusedItem != null) {
                this.btnReservedRemoveSoldier.Enabled = true && this.m_spPrivileges.CanEditReservedSlotsList;
            }

        }

        private void btnReservedRemoveSoldier_Click(object sender, EventArgs e) {

            if (this.lsvReservedList.SelectedItems.Count > 0) {

                this.m_blSettingRemovingReservedPlayer = true;
                this.WaitForSettingResponse("local.reservedlist.remove");

                this.m_prcClient.Game.SendReservedSlotsRemovePlayerPacket(this.lsvReservedList.SelectedItems[0].Name);
                //this.SendCommand("reservedSlots.removePlayer", this.lsvReservedList.SelectedItems[0].Name);

                this.m_prcClient.Game.SendReservedSlotsSavePacket();
                //this.SendCommand("reservedSlots.save");
            }
        }

        private void txtReservedAddSoldierName_TextChanged(object sender, EventArgs e) {

            if (this.txtReservedAddSoldierName.Text.Length > 0) {
                this.lnkReservedAddSoldierName.Enabled = true && this.m_spPrivileges.CanEditReservedSlotsList;
            }
            else {
                this.lnkReservedAddSoldierName.Enabled = false;
            }
        }

        private void txtReservedAddSoldierName_KeyDown(object sender, KeyEventArgs e) {
            if (this.txtReservedAddSoldierName.Text.Length > 0 && e.KeyData == Keys.Enter) {
                this.lnkReservedAddSoldierName_LinkClicked(this, null);
                e.SuppressKeyPress = true;
            }
        }

        private void lsvReservedList_ColumnClick(object sender, ColumnClickEventArgs e) {
            // Determine if clicked column is already the column that is being sorted.
            if (e.Column == this.m_lvwReservedSlotsColumnSorter.SortColumn) {
                // Reverse the current sort direction for this column.
                if (this.m_lvwReservedSlotsColumnSorter.Order == SortOrder.Ascending) {
                    this.m_lvwReservedSlotsColumnSorter.Order = SortOrder.Descending;
                }
                else {
                    this.m_lvwReservedSlotsColumnSorter.Order = SortOrder.Ascending;
                }
            }
            else {
                // Set the column number that is to be sorted; default to ascending.
                this.m_lvwReservedSlotsColumnSorter.SortColumn = e.Column;
                this.m_lvwReservedSlotsColumnSorter.Order = SortOrder.Ascending;
            }

            // Perform the sort with these new sort options.
            this.lsvReservedList.Sort();
        }

        private void btnReservedSlotsListRefresh_Click(object sender, EventArgs e)
        {
            this.m_prcClient.Game.SendReservedSlotsLoadPacket();
            this.m_prcClient.Game.SendReservedSlotsListPacket();
        }

        #endregion

        #region Banlist

        private void Reasons_ItemRemoved(int iIndex, string item) {
            if (this.cboBanlistReason.Items.Contains(item) == true) {
                this.cboBanlistReason.Items.Remove(item);
            }
        }

        private void Reasons_ItemAdded(int iIndex, string item) {
            this.cboBanlistReason.Items.Add(item);
        }

        *//*
        public ComboBox.ObjectCollection PunkbusterReasons {
            get {
                return this.cboBanlistReason.Items;
            }
        }
        *//*

        public string SecondsToText(UInt32 iSeconds, string[] a_strTimeDescriptions) {
            string strReturn = String.Empty;

            double dblSeconds = iSeconds;
            double dblMinutes = (iSeconds / 60);
            double dblHours = (dblMinutes / 60);
            double dblDays = (dblHours / 24);
            double dblWeeks = (dblDays / 7);
            double dblMonths = (dblWeeks / 4);
            double dblYears = (dblMonths / 12);

            if ((Int32)dblYears > 0) {
                strReturn += String.Empty + ((Int32)dblYears) + (((Int32)dblYears) == 1 ? a_strTimeDescriptions[0] : a_strTimeDescriptions[1]);
            }
            if ((Int32)dblMonths % 12 > 0) {
                strReturn += String.Empty + ((Int32)dblMonths) % 12 + (((Int32)dblMonths % 12) == 1 ? a_strTimeDescriptions[2] : a_strTimeDescriptions[3]);
            }
            if ((Int32)dblWeeks % 4 > 0) {
                strReturn += String.Empty + ((Int32)dblWeeks) % 4 + (((Int32)dblWeeks % 4) == 1 ? a_strTimeDescriptions[4] : a_strTimeDescriptions[5]);
            }
            if ((Int32)dblDays % 7 > 0) {
                strReturn += String.Empty + ((Int32)dblDays) % 7 + (((Int32)dblDays % 7) == 1 ? a_strTimeDescriptions[6] : a_strTimeDescriptions[7]);
            }
            if ((Int32)dblHours % 24 > 0) {
                strReturn += String.Empty + ((Int32)dblHours) % 24 + (((Int32)dblHours % 24) == 1 ? a_strTimeDescriptions[8] : a_strTimeDescriptions[9]);
            }
            if ((Int32)dblMinutes % 60 > 0) {
                strReturn += String.Empty + ((Int32)dblMinutes) % 60 + (((Int32)dblMinutes % 60) == 1 ? a_strTimeDescriptions[10] : a_strTimeDescriptions[11]);
            }

            if (iSeconds < 60) {
                if ((Int32)dblSeconds % 60 > 0) {
                    strReturn += String.Empty + ((Int32)dblSeconds) % 60 + (((Int32)dblSeconds % 60) == 1 ? a_strTimeDescriptions[12] : a_strTimeDescriptions[13]);
                }
            }

            return strReturn;
        }

        private ListViewItem CreateBlankBanEntry(string strName) {
            ListViewItem lviNewBanEntry = new ListViewItem();
            lviNewBanEntry.Name = strName;
            lviNewBanEntry.Text = String.Empty;

            ListViewItem.ListViewSubItem lvisIp = new ListViewItem.ListViewSubItem();
            lvisIp.Name = "ip";
            lvisIp.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisIp);

            ListViewItem.ListViewSubItem lvisGuid = new ListViewItem.ListViewSubItem();
            lvisGuid.Name = "guid";
            lvisGuid.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisGuid);

            ListViewItem.ListViewSubItem lvisType = new ListViewItem.ListViewSubItem();
            lvisType.Name = "type";
            lvisType.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisType);

            *//*
            ListViewItem.ListViewSubItem lvisTimeOfBan = new ListViewItem.ListViewSubItem();
            lvisTimeOfBan.Name = "timedate";
            lvisTimeOfBan.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisTimeOfBan);

            ListViewItem.ListViewSubItem lvisBanLength = new ListViewItem.ListViewSubItem();
            lvisBanLength.Name = "banlength";
            lvisBanLength.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisBanLength);
            *//*

            ListViewItem.ListViewSubItem lvisTimeRemaining = new ListViewItem.ListViewSubItem();
            lvisTimeRemaining.Name = "timeremaining";
            lvisTimeRemaining.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisTimeRemaining);

            ListViewItem.ListViewSubItem lvisReason = new ListViewItem.ListViewSubItem();
            lvisReason.Name = "reason";
            lvisReason.Text = String.Empty;
            lviNewBanEntry.SubItems.Add(lvisReason);

            return lviNewBanEntry;
        }

        private string GetFriendlyTypeName(string strType) {
            string strFriendlyTypeName = String.Empty;

            if (String.Compare(strType, "name", true) == 0) {
                strFriendlyTypeName = this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colType.Name", null);
            }
            else if (String.Compare(strType, "ip", true) == 0) {
                strFriendlyTypeName = "IpAddress";
            }
            else if (String.Compare(strType, "guid", true) == 0) {
                strFriendlyTypeName = "Guid";
            }
            else if (String.Compare(strType, "pbguid", true) == 0) {
                strFriendlyTypeName = "PB Guid";
            }

            return strFriendlyTypeName;
        }

        private ListViewItem CreateBanEntry(CBanInfo cbiPlayerBan) {

            ListViewItem lviNewBanEntry = null;

            if (String.Compare("name", cbiPlayerBan.IdType, true) == 0) {
                lviNewBanEntry = this.CreateBlankBanEntry(String.Format("{0}\r\n\r\n", cbiPlayerBan.SoldierName));
                lviNewBanEntry.Text = cbiPlayerBan.SoldierName;

                lviNewBanEntry.SubItems["type"].Tag = cbiPlayerBan.IdType;
                lviNewBanEntry.SubItems["type"].Text = this.GetFriendlyTypeName(cbiPlayerBan.IdType);

                //lviNewBanEntry.SubItems["banlength"].Tag = cbiPlayerBan.BanLength;
                lviNewBanEntry.SubItems["timeremaining"].Tag = cbiPlayerBan.BanLength;

                lviNewBanEntry.SubItems["reason"].Text = cbiPlayerBan.Reason;
            }
            else if (String.Compare("ip", cbiPlayerBan.IdType, true) == 0) {

                lviNewBanEntry = this.CreateBlankBanEntry(String.Format("\r\n{0}\r\n", cbiPlayerBan.IpAddress));
                lviNewBanEntry.Text = String.Empty;

                lviNewBanEntry.SubItems["ip"].Text = cbiPlayerBan.IpAddress;

                lviNewBanEntry.SubItems["type"].Tag = cbiPlayerBan.IdType;
                lviNewBanEntry.SubItems["type"].Text = this.GetFriendlyTypeName(cbiPlayerBan.IdType);

                //lviNewBanEntry.SubItems["banlength"].Tag = cbiPlayerBan.BanLength;
                lviNewBanEntry.SubItems["timeremaining"].Tag = cbiPlayerBan.BanLength;

                lviNewBanEntry.SubItems["reason"].Text = cbiPlayerBan.Reason;

            }
            else if (String.Compare("guid", cbiPlayerBan.IdType, true) == 0) {

                lviNewBanEntry = this.CreateBlankBanEntry(String.Format("\r\n\r\n{0}", cbiPlayerBan.Guid));
                lviNewBanEntry.Text = cbiPlayerBan.SoldierName;
                lviNewBanEntry.SubItems["guid"].Text = cbiPlayerBan.Guid;
                lviNewBanEntry.SubItems["ip"].Text = cbiPlayerBan.IpAddress;

                lviNewBanEntry.SubItems["type"].Tag = cbiPlayerBan.IdType;
                lviNewBanEntry.SubItems["type"].Text = this.GetFriendlyTypeName(cbiPlayerBan.IdType);

                //lviNewBanEntry.SubItems["banlength"].Tag = cbiPlayerBan.BanLength;
                lviNewBanEntry.SubItems["timeremaining"].Tag = cbiPlayerBan.BanLength;

                lviNewBanEntry.SubItems["reason"].Text = cbiPlayerBan.Reason;
            }

            else if (String.Compare("pbguid", cbiPlayerBan.IdType, true) == 0) {

                lviNewBanEntry = this.CreateBlankBanEntry(String.Format("\r\n\r\n{0}", cbiPlayerBan.Guid));
                lviNewBanEntry.Text = cbiPlayerBan.SoldierName;
                lviNewBanEntry.SubItems["guid"].Text = cbiPlayerBan.Guid;
                lviNewBanEntry.SubItems["ip"].Text = cbiPlayerBan.IpAddress;

                lviNewBanEntry.SubItems["type"].Tag = cbiPlayerBan.IdType;
                lviNewBanEntry.SubItems["type"].Text = this.GetFriendlyTypeName(cbiPlayerBan.IdType);

                //lviNewBanEntry.SubItems["banlength"].Tag = cbiPlayerBan.BanLength;
                lviNewBanEntry.SubItems["timeremaining"].Tag = cbiPlayerBan.BanLength;

                lviNewBanEntry.SubItems["reason"].Text = cbiPlayerBan.Reason.TrimEnd('"'); ;
            }

            return lviNewBanEntry;
        }

        public void RemoveDeletedBans(List<CBanInfo> lstBans) {

            for (int i = 0; i < this.lsvBanlist.Items.Count; i++) {
                bool blFoundBan = false;
                foreach (CBanInfo cbiBan in lstBans) {

                    switch ((string)this.lsvBanlist.Items[i].SubItems["type"].Tag) {
                        case "name":
                            blFoundBan = (String.Compare(this.lsvBanlist.Items[i].Name, String.Format("{0}\r\n\r\n", cbiBan.SoldierName)) == 0);
                            break;
                        case "ip":
                            blFoundBan = (String.Compare(this.lsvBanlist.Items[i].Name, String.Format("\r\n{0}\r\n", cbiBan.IpAddress)) == 0);
                            break;
                        case "guid":
                            blFoundBan = (String.Compare(this.lsvBanlist.Items[i].Name, String.Format("\r\n\r\n{0}", cbiBan.Guid)) == 0);
                            break;
                        case "pbguid":
                            // Ignore pb ban entries, handled in the pb event method.
                            blFoundBan = true;
                            break;
                        default:
                            break;
                    }

                    if (blFoundBan == true) {
                        break;
                    }
                }

                if (blFoundBan == false && String.Compare((string)this.lsvBanlist.Items[i].SubItems["type"].Tag, "pbguid") != 0) {
                    this.lsvBanlist.Items.Remove(this.lsvBanlist.Items[i]);
                    i--;
                }
            }
        }

        public void OnBanList(PRoConClient sender, List<CBanInfo> lstBans) {

            this.lsvBanlist.BeginUpdate();

            foreach (CBanInfo cbiBan in lstBans) {

                string strKey = String.Empty;

                if (String.Compare(cbiBan.IdType, "name") == 0) {
                    strKey = String.Format("{0}\r\n\r\n", cbiBan.SoldierName);
                }
                else if (String.Compare(cbiBan.IdType, "ip") == 0) {
                    strKey = String.Format("\r\n{0}\r\n", cbiBan.IpAddress);
                }
                else if (String.Compare(cbiBan.IdType, "guid") == 0) {
                    strKey = String.Format("\r\n\r\n{0}", cbiBan.Guid);
                }

                if (this.lsvBanlist.Items.ContainsKey(strKey) == false) {
                    this.lsvBanlist.Items.Add(this.CreateBanEntry(cbiBan));
                }
                else {
                    ListViewItem lviBanEntry = this.lsvBanlist.Items[strKey];
                    lviBanEntry.Text = cbiBan.SoldierName;
                    lviBanEntry.SubItems["type"].Tag = cbiBan.IdType;
                    lviBanEntry.SubItems["type"].Text = this.GetFriendlyTypeName(cbiBan.IdType);
                    lviBanEntry.SubItems["timeremaining"].Tag = cbiBan.BanLength;
                    lviBanEntry.SubItems["reason"].Text = cbiBan.Reason;
                }
            }

            this.RemoveDeletedBans(lstBans);

            for (int i = 0; i < this.lsvBanlist.Columns.Count; i++) {
                this.lsvBanlist.Columns[i].Width = -2;
            }

            this.tmrRefreshBanlist_Tick(null, null);

            this.lsvBanlist.EndUpdate();

        }

        private void m_prcClient_PunkbusterPlayerUnbanned(PRoConClient sender, CBanInfo cbiUnbannedPlayer) {
            this.OnUnban(sender.Game, cbiUnbannedPlayer);
        }

        public void OnUnban(FrostbiteClient sender, CBanInfo cbiBan) {

            string strKey = String.Empty;

            if (String.Compare(cbiBan.IdType, "name") == 0) {
                strKey = String.Format("{0}\r\n\r\n", cbiBan.SoldierName);
            }
            else if (String.Compare(cbiBan.IdType, "ip") == 0) {
                strKey = String.Format("\r\n{0}\r\n", cbiBan.IpAddress);
            }
            else if (String.Compare(cbiBan.IdType, "guid") == 0 || String.Compare(cbiBan.IdType, "pbguid") == 0) {
                strKey = String.Format("\r\n\r\n{0}", cbiBan.Guid);
            }

            if (this.lsvBanlist.Items.ContainsKey(strKey) == true) {
                this.lsvBanlist.Items[strKey].Remove();
                this.OnSettingResponse("local.banlist.unban", true);
            }
        }

        private void tmrRefreshBanlist_Tick(object sender, EventArgs e) {
            foreach (ListViewItem lviBanEntry in this.lsvBanlist.Items) {

                if (lviBanEntry.SubItems["timeremaining"].Tag != null) {

                    TimeoutSubset ctsTimeout = (TimeoutSubset)lviBanEntry.SubItems["timeremaining"].Tag;

                    if (ctsTimeout.Subset == TimeoutSubset.TimeoutSubsetType.Permanent) {
                        lviBanEntry.SubItems["timeremaining"].Text = this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colTimeRemaining.Permanent", null);
                    }
                    else if (ctsTimeout.Subset == TimeoutSubset.TimeoutSubsetType.Round) {
                        lviBanEntry.SubItems["timeremaining"].Text = this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colTimeRemaining.Round", null);
                    }
                    else if (ctsTimeout.Subset == TimeoutSubset.TimeoutSubsetType.Seconds) {

                        if (ctsTimeout.Seconds > 0) {
                            lviBanEntry.SubItems["timeremaining"].Text = this.SecondsToText((UInt32)ctsTimeout.Seconds, this.ma_strTimeDescriptionsShort);

                            ctsTimeout.Seconds -= (this.tmrRefreshBanlist.Interval / 1000);
                        }
                        else {
                            // I was going to remove it here but I want it to display unbanned until next banList update.
                            lviBanEntry.SubItems["timeremaining"].Text = this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.lsvBanlist.colTimeRemaining.Unbanned", null);
                        }
                    }
                }
            }
        }

        public void OnPbGuidUnban(CBanInfo cbiGuidBan) {
            if (this.lsvBanlist.Items.ContainsKey(String.Format("\r\n\r\n{0}", cbiGuidBan.Guid)) == true) {
                this.lsvBanlist.Items[String.Format("\r\n\r\n{0}", cbiGuidBan.Guid)].Remove();
                this.OnSettingResponse("local.banlist.unban", true);
            }
        }

        public void OnPbGuidBan(PRoConClient sender, CBanInfo cbiGuidBan) {
            this.lsvBanlist.BeginUpdate();

            if (this.lsvBanlist.Items.ContainsKey(String.Format("\r\n\r\n{0}", cbiGuidBan.Guid)) == false) {
                this.lsvBanlist.Items.Add(this.CreateBanEntry(cbiGuidBan));
            }
            else {
                ListViewItem lviBanEntry = this.lsvBanlist.Items[String.Format("\r\n\r\n{0}", cbiGuidBan.Guid)];
                lviBanEntry.Text = cbiGuidBan.SoldierName;
                lviBanEntry.SubItems["guid"].Text = cbiGuidBan.Guid;
                lviBanEntry.SubItems["ip"].Text = cbiGuidBan.IpAddress;

                lviBanEntry.SubItems["type"].Tag = cbiGuidBan.IdType;
                lviBanEntry.SubItems["type"].Text = this.GetFriendlyTypeName(cbiGuidBan.IdType);

                lviBanEntry.SubItems["timeremaining"].Tag = cbiGuidBan.BanLength;

                lviBanEntry.SubItems["reason"].Tag = cbiGuidBan.Reason.TrimEnd('"');
            }

            for (int i = 0; i < this.lsvBanlist.Columns.Count; i++) {
                this.lsvBanlist.Columns[i].Width = -2;
            }

            this.lsvBanlist.EndUpdate();
        }

        private void unbanToolStripMenuItem_Click(object sender, EventArgs e) {
            this.btnBanlistUnban_Click(sender, e);
        }

        private void btnBanlistUnban_Click(object sender, EventArgs e) {

            if (this.lsvBanlist.SelectedItems.Count > 0) {
                this.WaitForSettingResponse("local.banlist.unban");

                if (String.Compare((string)this.lsvBanlist.SelectedItems[0].SubItems["type"].Tag, "name") == 0) {
                    this.SendCommand("banList.remove", "name", this.lsvBanlist.SelectedItems[0].Text);
                }
                else if (String.Compare((string)this.lsvBanlist.SelectedItems[0].SubItems["type"].Tag, "ip") == 0) {
                    this.SendCommand("banList.remove", "ip", this.lsvBanlist.SelectedItems[0].SubItems["ip"].Text);
                }
                else if (String.Compare((string)this.lsvBanlist.SelectedItems[0].SubItems["type"].Tag, "guid") == 0) {
                    this.SendCommand("banList.remove", "guid", this.lsvBanlist.SelectedItems[0].SubItems["guid"].Text);
                }
                else if (String.Compare((string)this.lsvBanlist.SelectedItems[0].SubItems["type"].Tag, "pbguid") == 0) {
                    this.SendCommand("punkBuster.pb_sv_command", String.Format("pb_sv_unbanguid {0}", this.lsvBanlist.SelectedItems[0].SubItems["guid"].Text));
                    this.SendCommand("punkBuster.pb_sv_command", "pb_sv_updbanfile");
                    this.SendCommand("punkBuster.pb_sv_command", "pb_sv_banload");
                }

                this.m_prcClient.Game.SendBanListSavePacket();
                this.m_prcClient.Game.SendBanListListPacket();
            }
        }

        private void lsvBanlist_SelectedIndexChanged(object sender, EventArgs e) {

            if (this.lsvBanlist.SelectedItems.Count > 0) {
                this.btnBanlistUnban.Enabled = true && this.m_spPrivileges.CanEditBanList;
            }
            else if (this.lsvBanlist.FocusedItem != null) {
                this.btnBanlistUnban.Enabled = false;
            }

        }

        private void lsvBanlist_ColumnClick(object sender, ColumnClickEventArgs e) {
            // Determine if clicked column is already the column that is being sorted.
            if (e.Column == this.m_lvwBanlistColumnSorter.SortColumn) {
                // Reverse the current sort direction for this column.
                if (this.m_lvwBanlistColumnSorter.Order == SortOrder.Ascending) {
                    this.m_lvwBanlistColumnSorter.Order = SortOrder.Descending;
                }
                else {
                    this.m_lvwBanlistColumnSorter.Order = SortOrder.Ascending;
                }
            }
            else {
                // Set the column number that is to be sorted; default to ascending.
                this.m_lvwBanlistColumnSorter.SortColumn = e.Column;
                this.m_lvwBanlistColumnSorter.Order = SortOrder.Ascending;
            }

            // Perform the sort with these new sort options.
            this.lsvBanlist.Sort();
        }

        public void OnClearBanList(FrostbiteClient sender) {
            this.OnSettingResponse("local.banlist.clearlist", true);
        }

        private void btnBanlistClearNameList_Click(object sender, EventArgs e) {

            DialogResult dlgClearList = MessageBox.Show(this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistClearBanlist.Question", null), this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.btnBanlistClearBanlist.Title", null), MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (dlgClearList == DialogResult.Yes) {
                this.WaitForSettingResponse("local.banlist.clearlist");

                this.m_prcClient.Game.SendBanListClearPacket();
                this.m_prcClient.Game.SendBanListSavePacket();
                this.m_prcClient.Game.SendBanListListPacket();
            }
        }

        private void rdoBanlistName_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoBanlistName.Checked == true) {
                //this.ValidateManualBan();
                this.txtBanlistManualBanName.Focus();
                this.txtBanlistManualBanName.Enabled = true;
                this.txtBanlistManualBanIP.Enabled = false;
                this.txtBanlistManualBanGUID.Enabled = false;
                //this.cboBanlistReason.Enabled = false;
                //this.lblBanlistReason.Enabled = false;

                this.rdoBanlistTemporary.Enabled = true;

                this.UpdateConfirmationLabel();
            }
        }

        private void rdoBanlistIP_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoBanlistIP.Checked == true) {
                this.txtBanlistManualBanIP.Focus();
                this.txtBanlistManualBanName.Enabled = false;
                this.txtBanlistManualBanIP.Enabled = true;
                this.txtBanlistManualBanGUID.Enabled = false;
                //this.cboBanlistReason.Enabled = false;
                //this.lblBanlistReason.Enabled = false;

                this.rdoBanlistTemporary.Enabled = true;

                this.UpdateConfirmationLabel();
            }
        }

        private void rdoBanlistBc2GUID_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoBanlistBc2GUID.Checked == true) {
                this.txtBanlistManualBanGUID.Focus();
                this.txtBanlistManualBanName.Enabled = false;
                this.txtBanlistManualBanIP.Enabled = false;
                this.txtBanlistManualBanGUID.Enabled = true;
                //this.cboBanlistReason.Enabled = true;
                //this.lblBanlistReason.Enabled = true;
                this.txtBanlistManualBanIP.ForeColor = SystemColors.WindowText;

                this.rdoBanlistTemporary.Enabled = true;
                //this.rdoBanlistPermanent.Checked = true;

                this.UpdateConfirmationLabel();
            }
        }

        private void rdoBanlistGUID_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoBanlistPbGUID.Checked == true) {
                this.txtBanlistManualBanGUID.Focus();
                this.txtBanlistManualBanName.Enabled = true;
                this.txtBanlistManualBanIP.Enabled = true;
                this.txtBanlistManualBanGUID.Enabled = true;
                //this.cboBanlistReason.Enabled = true;
                //this.lblBanlistReason.Enabled = true;
                this.txtBanlistManualBanIP.ForeColor = SystemColors.WindowText;

                this.rdoBanlistTemporary.Enabled = false;
                this.rdoBanlistPermanent.Checked = true;

                this.UpdateConfirmationLabel();
            }
        }

        private void ListSettings_ManualBansVisibleChange(bool isVisible) {
            if (isVisible == true) {
                this.lnkCloseOpenManualBans.Text = this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.lnkCloseOpenManualBans.Close", null);
                this.picCloseOpenManualBans.Image = this.m_frmMain.iglIcons.Images["arrow_down.png"];

                this.spltBanlistManualBans.Panel2Collapsed = false;
            }
            else {
                this.lnkCloseOpenManualBans.Text = this.m_prcClient.Language.GetLocalized("uscListControlPanel.tabBanlist.lnkCloseOpenManualBans.Open", null);
                this.picCloseOpenManualBans.Image = this.m_frmMain.iglIcons.Images["arrow_up.png"];

                this.spltBanlistManualBans.Panel2Collapsed = true;
            }
        }

        private void lnkAddBan_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.ListSettings.ManualBansVisible = !this.m_prcClient.ListSettings.ManualBansVisible;
            }
        }

        private void picCloseOpenManualBans_Click(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.ListSettings.ManualBansVisible = !this.m_prcClient.ListSettings.ManualBansVisible;
            }
        }

        private void btnBanlistAddBan_Click(object sender, EventArgs e) {

            if (this.rdoBanlistName.Checked == true) {
                if (this.rdoBanlistPermanent.Checked == true) {
                    this.SendCommand("banList.add", "name", this.txtBanlistManualBanName.Text, "perm", this.cboBanlistReason.Text);
                }
                else {
                    this.SendCommand("banList.add", "name", this.txtBanlistManualBanName.Text, "seconds", (uscPlayerPunishPanel.GetBanLength(this.txtBanlistTime, this.cboBanlistTimeMultiplier) * 60).ToString(), this.cboBanlistReason.Text);
                }
            }
            else if (this.rdoBanlistIP.Checked == true) {
                if (this.rdoBanlistPermanent.Checked == true) {
                    this.SendCommand("banList.add", "ip", this.txtBanlistManualBanIP.Text, "perm", this.cboBanlistReason.Text);
                }
                else {
                    this.SendCommand("banList.add", "ip", this.txtBanlistManualBanIP.Text, "seconds", (uscPlayerPunishPanel.GetBanLength(this.txtBanlistTime, this.cboBanlistTimeMultiplier) * 60).ToString(), this.cboBanlistReason.Text);
                }
            }
            else if (this.rdoBanlistBc2GUID.Checked == true) {
                if (this.rdoBanlistPermanent.Checked == true) {
                    this.SendCommand("banList.add", "guid", this.txtBanlistManualBanGUID.Text, "perm", this.cboBanlistReason.Text);
                }
                else {
                    this.SendCommand("banList.add", "guid", this.txtBanlistManualBanGUID.Text, "seconds", (uscPlayerPunishPanel.GetBanLength(this.txtBanlistTime, this.cboBanlistTimeMultiplier) * 60).ToString(), this.cboBanlistReason.Text);
                }
            }
            else if (this.rdoBanlistPbGUID.Checked == true) {
                this.SendCommand("punkBuster.pb_sv_command", String.Format("pb_sv_banguid {0} \"{1}\" \"{2}\" \"BC2! {3}\"", this.txtBanlistManualBanGUID.Text, this.txtBanlistManualBanName.Text.Length > 0 ? this.txtBanlistManualBanName.Text : "???", this.txtBanlistManualBanIP.Text.Length > 0 ? this.txtBanlistManualBanIP.Text : "???", this.cboBanlistReason.Text));
                this.SendCommand("punkBuster.pb_sv_command", this.m_prcClient.Variables.GetVariable<string>("PUNKBUSTER_BANLIST_REFRESH", "pb_sv_banlist BC2! "));
            }

            this.txtBanlistManualBanName.Focus();
            this.txtBanlistManualBanName.Clear();
            this.txtBanlistManualBanGUID.Clear();
            this.txtBanlistManualBanIP.Clear();

            this.m_prcClient.Game.SendBanListSavePacket();
            this.m_prcClient.Game.SendBanListListPacket();
        }

        private void txtBanlistManualBanName_TextChanged(object sender, EventArgs e) {
            this.UpdateConfirmationLabel();
        }

        private void txtBanlistManualBanIP_TextChanged(object sender, EventArgs e) {
            this.UpdateConfirmationLabel();
        }

        private void txtBanlistManualBanGUID_TextChanged(object sender, EventArgs e) {
            this.UpdateConfirmationLabel();
        }

        private void lsvBanlist_MouseUp(object sender, MouseEventArgs e) {
            if (e.Button == MouseButtons.Right) {

                if (this.lsvBanlist.SelectedItems.Count > 0) {
                    Point pntMouseLocation = new Point(e.X, e.Y);
                    this.ctxBanlistMenuStrip.Show(this.lsvBanlist, pntMouseLocation);
                }
            }
        }

        private void copyToolStripMenuItem_Click(object sender, EventArgs e) {

            if (this.lsvBanlist.SelectedItems.Count > 0) {

                string strClipboard = this.lsvBanlist.SelectedItems[0].Text;

                foreach (ListViewItem.ListViewSubItem lvsiItem in this.lsvBanlist.SelectedItems[0].SubItems) {
                    strClipboard += " " + lvsiItem.Text;
                }

                try {
                    Clipboard.SetDataObject(strClipboard, true, 5, 10);
                }
                catch (Exception) {
                    // Nope, another thread is accessing the clipboard..
                }
            }
        }

        private void rdoBanlistTemporary_CheckedChanged(object sender, EventArgs e) {
            this.pnlBanlistTime.Enabled = this.rdoBanlistTemporary.Checked;
            this.UpdateConfirmationLabel();
        }

        private void rdoBanlistPermanent_CheckedChanged(object sender, EventArgs e) {
            this.pnlBanlistTime.Enabled = this.rdoBanlistTemporary.Checked;
            this.UpdateConfirmationLabel();
        }

        private void UpdateConfirmationLabel() {

            string strBanDescription = String.Empty;

            if (this.rdoBanlistPbGUID.Checked == true || this.rdoBanlistBc2GUID.Checked == true) {
                strBanDescription = this.txtBanlistManualBanGUID.Text;
            }
            else if (this.rdoBanlistIP.Checked == true) {
                strBanDescription = this.txtBanlistManualBanIP.Text;
            }
            else if (this.rdoBanlistName.Checked == true) {
                strBanDescription = this.txtBanlistManualBanName.Text;
            }

            bool blAbleToPunish = false;

            if (this.m_uscConnectionPanel != null) {
                this.lblBanlistConfirmation.Text = uscPlayerPunishPanel.GetConfirmationLabel(out blAbleToPunish, strBanDescription, this.m_spPrivileges,
                                                                                             this.m_prcClient.Language, false, false, this.rdoBanlistPermanent.Checked,
                                                                                             this.rdoBanlistTemporary.Checked, this.txtBanlistTime, this.cboBanlistTimeMultiplier,
                                                                                             this.ma_strTimeDescriptionsLong, this.m_prcClient.SV_Variables.GetVariable<int>("TEMP_BAN_CEILING", 3600));
            }

            if (this.rdoBanlistIP.Checked == true) {
                this.btnBanlistAddBan.Enabled = (this.txtBanlistManualBanIP.Text.Length > 0 && this.m_regIP.Match(this.txtBanlistManualBanIP.Text).Success) && blAbleToPunish == true;
                this.picBanlistIPError.Visible = !this.btnBanlistAddBan.Enabled && blAbleToPunish == true;

                if (this.btnBanlistAddBan.Enabled == false && blAbleToPunish == true) {
                    this.txtBanlistManualBanIP.ForeColor = Color.Maroon;
                }
                else {
                    this.txtBanlistManualBanIP.ForeColor = SystemColors.WindowText;
                }
            }
            else {
                this.picBanlistIPError.Visible = false;
            }

            if (this.rdoBanlistPbGUID.Checked == true) {
                this.btnBanlistAddBan.Enabled = (this.txtBanlistManualBanGUID.Text.Length > 0 && this.m_regPbGUID.Match(this.txtBanlistManualBanGUID.Text).Success) && blAbleToPunish == true;
                this.picBanlistGUIDError.Visible = !this.btnBanlistAddBan.Enabled && blAbleToPunish == true;

                if (this.btnBanlistAddBan.Enabled == false && blAbleToPunish == true) {
                    this.txtBanlistManualBanGUID.ForeColor = Color.Maroon;
                }
                else {
                    this.txtBanlistManualBanGUID.ForeColor = SystemColors.WindowText;
                }
            }
            else if (this.rdoBanlistBc2GUID.Checked == true) {
                this.btnBanlistAddBan.Enabled = (this.txtBanlistManualBanGUID.Text.Length > 0 && this.m_regBc2GUID.Match(this.txtBanlistManualBanGUID.Text).Success) && blAbleToPunish == true;
                this.picBanlistGUIDError.Visible = !this.btnBanlistAddBan.Enabled && blAbleToPunish == true;

                if (this.btnBanlistAddBan.Enabled == false && blAbleToPunish == true) {
                    this.txtBanlistManualBanGUID.ForeColor = Color.Maroon;
                }
                else {
                    this.txtBanlistManualBanGUID.ForeColor = SystemColors.WindowText;
                }
            }
            else {
                this.picBanlistGUIDError.Visible = false;
            }

            if (this.rdoBanlistName.Checked == true) {
                this.btnBanlistAddBan.Enabled = (this.txtBanlistManualBanName.Text.Length > 0) && blAbleToPunish == true;
            }

        }

        private void txtBanlistTime_TextChanged(object sender, EventArgs e) {
            this.UpdateConfirmationLabel();
        }

        private void cboBanlistTimeMultiplier_SelectedIndexChanged(object sender, EventArgs e) {
            this.UpdateConfirmationLabel();
        }

        private void txtBanlistTime_KeyPress(object sender, KeyPressEventArgs e) {
            e.Handled = (!char.IsDigit(e.KeyChar) && e.KeyChar != '\b');
        }

        private void btnBanlistRefresh_Click(object sender, EventArgs e) {

            this.m_prcClient.Game.SendBanListListPacket();
            // .SendPunkbusterThing
            this.SendCommand("punkBuster.pb_sv_command", this.m_prcClient.Variables.GetVariable<string>("PUNKBUSTER_BANLIST_REFRESH", "pb_sv_banlist BC2! "));
        }

        #endregion

    }*/

    /*partial class uscListControlPanel {
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
            System.Windows.Forms.ListViewGroup listViewGroup1 = new System.Windows.Forms.ListViewGroup("Name", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup2 = new System.Windows.Forms.ListViewGroup("IpAddress", System.Windows.Forms.HorizontalAlignment.Left);
            System.Windows.Forms.ListViewGroup listViewGroup3 = new System.Windows.Forms.ListViewGroup("Guid", System.Windows.Forms.HorizontalAlignment.Left);
            this.tbcLists = new System.Windows.Forms.TabControl();
            this.tabBanlist = new System.Windows.Forms.TabPage();
            this.spltBanlistManualBans = new System.Windows.Forms.SplitContainer();
            this.btnBanlistRefresh = new System.Windows.Forms.Button();
            this.picCloseOpenManualBans = new System.Windows.Forms.PictureBox();
            this.lnkCloseOpenManualBans = new System.Windows.Forms.LinkLabel();
            this.picClearLists = new System.Windows.Forms.PictureBox();
            this.picUnbanPlayer = new System.Windows.Forms.PictureBox();
            this.btnBanlistClearBanlist = new System.Windows.Forms.Button();
            this.btnBanlistUnban = new System.Windows.Forms.Button();
            this.lsvBanlist = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colName = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colIP = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colGUID = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colTimeRemaining = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colReason = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.rdoBanlistBc2GUID = new System.Windows.Forms.RadioButton();
            this.lblBanlistConfirmation = new System.Windows.Forms.Label();
            this.panel7 = new System.Windows.Forms.Panel();
            this.rdoBanlistTemporary = new System.Windows.Forms.RadioButton();
            this.rdoBanlistPermanent = new System.Windows.Forms.RadioButton();
            this.pnlBanlistTime = new System.Windows.Forms.Panel();
            this.lblBanlistTime = new System.Windows.Forms.Label();
            this.txtBanlistTime = new System.Windows.Forms.TextBox();
            this.cboBanlistTimeMultiplier = new System.Windows.Forms.ComboBox();
            this.picBanlistManualBanOkay = new System.Windows.Forms.PictureBox();
            this.picBanlistIPError = new System.Windows.Forms.PictureBox();
            this.picBanlistGUIDError = new System.Windows.Forms.PictureBox();
            this.txtBanlistManualBanIP = new System.Windows.Forms.TextBox();
            this.txtBanlistManualBanGUID = new System.Windows.Forms.TextBox();
            this.lblBanlistReason = new System.Windows.Forms.Label();
            this.cboBanlistReason = new System.Windows.Forms.ComboBox();
            this.btnBanlistAddBan = new System.Windows.Forms.Button();
            this.rdoBanlistPbGUID = new System.Windows.Forms.RadioButton();
            this.rdoBanlistIP = new System.Windows.Forms.RadioButton();
            this.rdoBanlistName = new System.Windows.Forms.RadioButton();
            this.txtBanlistManualBanName = new System.Windows.Forms.TextBox();
            this.tabMaplist = new System.Windows.Forms.TabPage();
            this.uscMaplist1 = new PRoCon.Controls.Maplist.uscMaplist();
            this.tabReservedSlots = new System.Windows.Forms.TabPage();
            this.lblMohNotice = new System.Windows.Forms.Label();
            this.pnlReservedPanel = new System.Windows.Forms.Panel();
            this.lblReservedCurrent = new System.Windows.Forms.Label();
            this.pnlReservedAddSoldierName = new System.Windows.Forms.Panel();
            this.txtReservedAddSoldierName = new System.Windows.Forms.TextBox();
            this.picReservedAddSoldierName = new System.Windows.Forms.PictureBox();
            this.lblReservedAddSoldierName = new System.Windows.Forms.Label();
            this.lnkReservedAddSoldierName = new System.Windows.Forms.LinkLabel();
            this.panel8 = new System.Windows.Forms.Panel();
            this.btnReservedRemoveSoldier = new System.Windows.Forms.Button();
            this.panel9 = new System.Windows.Forms.Panel();
            this.picReservedList = new System.Windows.Forms.PictureBox();
            this.panel10 = new System.Windows.Forms.Panel();
            this.lsvReservedList = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colSoldierNames = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.tabTextChatModeration = new System.Windows.Forms.TabPage();
            this.uscTextChatModerationListcs1 = new PRoCon.Controls.TextChatModeration.uscTextChatModerationListcs();
            this.tmrTimeoutCheck = new System.Windows.Forms.Timer(this.components);
            this.tmrRefreshBanlist = new System.Windows.Forms.Timer(this.components);
            this.ctxBanlistMenuStrip = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.copyToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.unbanToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.btnReservedSlotsListRefresh = new System.Windows.Forms.Button();
            this.tbcLists.SuspendLayout();
            this.tabBanlist.SuspendLayout();
            this.spltBanlistManualBans.Panel1.SuspendLayout();
            this.spltBanlistManualBans.Panel2.SuspendLayout();
            this.spltBanlistManualBans.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picCloseOpenManualBans)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picClearLists)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picUnbanPlayer)).BeginInit();
            this.panel7.SuspendLayout();
            this.pnlBanlistTime.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picBanlistManualBanOkay)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picBanlistIPError)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picBanlistGUIDError)).BeginInit();
            this.tabMaplist.SuspendLayout();
            this.tabReservedSlots.SuspendLayout();
            this.pnlReservedPanel.SuspendLayout();
            this.pnlReservedAddSoldierName.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picReservedAddSoldierName)).BeginInit();
            this.panel8.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picReservedList)).BeginInit();
            this.tabTextChatModeration.SuspendLayout();
            this.ctxBanlistMenuStrip.SuspendLayout();
            this.SuspendLayout();
            //
            // tbcLists
            //
            this.tbcLists.Controls.Add(this.tabBanlist);
            this.tbcLists.Controls.Add(this.tabMaplist);
            this.tbcLists.Controls.Add(this.tabReservedSlots);
            this.tbcLists.Controls.Add(this.tabTextChatModeration);
            this.tbcLists.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbcLists.Location = new System.Drawing.Point(0, 0);
            this.tbcLists.Name = "tbcLists";
            this.tbcLists.SelectedIndex = 0;
            this.tbcLists.Size = new System.Drawing.Size(1046, 856);
            this.tbcLists.TabIndex = 0;
            this.tbcLists.SelectedIndexChanged += new System.EventHandler(this.tbcLists_SelectedIndexChanged);
            //
            // tabBanlist
            //
            this.tabBanlist.Controls.Add(this.spltBanlistManualBans);
            this.tabBanlist.Location = new System.Drawing.Point(4, 24);
            this.tabBanlist.Name = "tabBanlist";
            this.tabBanlist.Padding = new System.Windows.Forms.Padding(8);
            this.tabBanlist.Size = new System.Drawing.Size(1038, 828);
            this.tabBanlist.TabIndex = 2;
            this.tabBanlist.Text = "Banlist";
            this.tabBanlist.UseVisualStyleBackColor = true;
            //
            // spltBanlistManualBans
            //
            this.spltBanlistManualBans.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltBanlistManualBans.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.spltBanlistManualBans.IsSplitterFixed = true;
            this.spltBanlistManualBans.Location = new System.Drawing.Point(8, 8);
            this.spltBanlistManualBans.Name = "spltBanlistManualBans";
            this.spltBanlistManualBans.Orientation = System.Windows.Forms.Orientation.Horizontal;
            //
            // spltBanlistManualBans.Panel1
            //
            this.spltBanlistManualBans.Panel1.Controls.Add(this.btnBanlistRefresh);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.picCloseOpenManualBans);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.lnkCloseOpenManualBans);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.picClearLists);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.picUnbanPlayer);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.btnBanlistClearBanlist);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.btnBanlistUnban);
            this.spltBanlistManualBans.Panel1.Controls.Add(this.lsvBanlist);
            //
            // spltBanlistManualBans.Panel2
            //
            this.spltBanlistManualBans.Panel2.Controls.Add(this.rdoBanlistBc2GUID);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.lblBanlistConfirmation);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.panel7);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.pnlBanlistTime);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.picBanlistManualBanOkay);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.picBanlistIPError);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.picBanlistGUIDError);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.txtBanlistManualBanIP);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.txtBanlistManualBanGUID);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.lblBanlistReason);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.cboBanlistReason);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.btnBanlistAddBan);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.rdoBanlistPbGUID);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.rdoBanlistIP);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.rdoBanlistName);
            this.spltBanlistManualBans.Panel2.Controls.Add(this.txtBanlistManualBanName);
            this.spltBanlistManualBans.Size = new System.Drawing.Size(1022, 812);
            this.spltBanlistManualBans.SplitterDistance = 690;
            this.spltBanlistManualBans.TabIndex = 94;
            //
            // btnBanlistRefresh
            //
            this.btnBanlistRefresh.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.btnBanlistRefresh.FlatAppearance.BorderSize = 0;
            this.btnBanlistRefresh.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnBanlistRefresh.Location = new System.Drawing.Point(3, 630);
            this.btnBanlistRefresh.Name = "btnBanlistRefresh";
            this.btnBanlistRefresh.Size = new System.Drawing.Size(35, 23);
            this.btnBanlistRefresh.TabIndex = 107;
            this.btnBanlistRefresh.UseVisualStyleBackColor = true;
            this.btnBanlistRefresh.Click += new System.EventHandler(this.btnBanlistRefresh_Click);
            //
            // picCloseOpenManualBans
            //
            this.picCloseOpenManualBans.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.picCloseOpenManualBans.Cursor = System.Windows.Forms.Cursors.Hand;
            this.picCloseOpenManualBans.Location = new System.Drawing.Point(8, 674);
            this.picCloseOpenManualBans.Name = "picCloseOpenManualBans";
            this.picCloseOpenManualBans.Size = new System.Drawing.Size(16, 16);
            this.picCloseOpenManualBans.TabIndex = 106;
            this.picCloseOpenManualBans.TabStop = false;
            this.picCloseOpenManualBans.Click += new System.EventHandler(this.picCloseOpenManualBans_Click);
            //
            // lnkCloseOpenManualBans
            //
            this.lnkCloseOpenManualBans.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkCloseOpenManualBans.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lnkCloseOpenManualBans.AutoSize = true;
            this.lnkCloseOpenManualBans.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkCloseOpenManualBans.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkCloseOpenManualBans.Location = new System.Drawing.Point(24, 675);
            this.lnkCloseOpenManualBans.Name = "lnkCloseOpenManualBans";
            this.lnkCloseOpenManualBans.Size = new System.Drawing.Size(107, 15);
            this.lnkCloseOpenManualBans.TabIndex = 105;
            this.lnkCloseOpenManualBans.TabStop = true;
            this.lnkCloseOpenManualBans.Text = "Close manual bans";
            this.lnkCloseOpenManualBans.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkAddBan_LinkClicked);
            //
            // picClearLists
            //
            this.picClearLists.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.picClearLists.Location = new System.Drawing.Point(600, 634);
            this.picClearLists.Name = "picClearLists";
            this.picClearLists.Size = new System.Drawing.Size(16, 16);
            this.picClearLists.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picClearLists.TabIndex = 104;
            this.picClearLists.TabStop = false;
            //
            // picUnbanPlayer
            //
            this.picUnbanPlayer.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.picUnbanPlayer.Location = new System.Drawing.Point(816, 634);
            this.picUnbanPlayer.Name = "picUnbanPlayer";
            this.picUnbanPlayer.Size = new System.Drawing.Size(16, 16);
            this.picUnbanPlayer.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picUnbanPlayer.TabIndex = 103;
            this.picUnbanPlayer.TabStop = false;
            //
            // btnBanlistClearBanlist
            //
            this.btnBanlistClearBanlist.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnBanlistClearBanlist.Location = new System.Drawing.Point(622, 630);
            this.btnBanlistClearBanlist.Name = "btnBanlistClearBanlist";
            this.btnBanlistClearBanlist.Size = new System.Drawing.Size(172, 23);
            this.btnBanlistClearBanlist.TabIndex = 100;
            this.btnBanlistClearBanlist.Text = "Clear Banlist";
            this.btnBanlistClearBanlist.UseVisualStyleBackColor = true;
            this.btnBanlistClearBanlist.Click += new System.EventHandler(this.btnBanlistClearNameList_Click);
            //
            // btnBanlistUnban
            //
            this.btnBanlistUnban.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnBanlistUnban.Enabled = false;
            this.btnBanlistUnban.Location = new System.Drawing.Point(838, 630);
            this.btnBanlistUnban.Name = "btnBanlistUnban";
            this.btnBanlistUnban.Size = new System.Drawing.Size(179, 23);
            this.btnBanlistUnban.TabIndex = 102;
            this.btnBanlistUnban.Text = "Unban Player";
            this.btnBanlistUnban.UseVisualStyleBackColor = true;
            this.btnBanlistUnban.Click += new System.EventHandler(this.btnBanlistUnban_Click);
            //
            // lsvBanlist
            //
            this.lsvBanlist.AllowColumnReorder = true;
            this.lsvBanlist.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lsvBanlist.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colName,
            this.colIP,
            this.colGUID,
            this.colType,
            this.colTimeRemaining,
            this.colReason});
            this.lsvBanlist.FullRowSelect = true;
            this.lsvBanlist.GridLines = true;
            listViewGroup1.Header = "Name";
            listViewGroup1.Name = "lvgName";
            listViewGroup2.Header = "IpAddress";
            listViewGroup2.Name = "lvgIP";
            listViewGroup3.Header = "Guid";
            listViewGroup3.Name = "lvgGUID";
            this.lsvBanlist.Groups.AddRange(new System.Windows.Forms.ListViewGroup[] {
            listViewGroup1,
            listViewGroup2,
            listViewGroup3});
            this.lsvBanlist.HideSelection = false;
            this.lsvBanlist.Location = new System.Drawing.Point(0, 0);
            this.lsvBanlist.MultiSelect = false;
            this.lsvBanlist.Name = "lsvBanlist";
            this.lsvBanlist.ShowGroups = false;
            this.lsvBanlist.ShowItemToolTips = true;
            this.lsvBanlist.Size = new System.Drawing.Size(1022, 624);
            this.lsvBanlist.TabIndex = 99;
            this.lsvBanlist.UseCompatibleStateImageBehavior = false;
            this.lsvBanlist.View = System.Windows.Forms.View.Details;
            this.lsvBanlist.SelectedIndexChanged += new System.EventHandler(this.lsvBanlist_SelectedIndexChanged);
            this.lsvBanlist.MouseUp += new System.Windows.Forms.MouseEventHandler(this.lsvBanlist_MouseUp);
            //
            // colName
            //
            this.colName.Text = "Name";
            //
            // colIP
            //
            this.colIP.Text = "IpAddress";
            //
            // colGUID
            //
            this.colGUID.Text = "Guid";
            //
            // colType
            //
            this.colType.Text = "Type";
            //
            // colTimeRemaining
            //
            this.colTimeRemaining.Text = "Remaining";
            this.colTimeRemaining.Width = 106;
            //
            // colReason
            //
            this.colReason.Text = "Reason";
            //
            // rdoBanlistBc2GUID
            //
            this.rdoBanlistBc2GUID.AutoSize = true;
            this.rdoBanlistBc2GUID.Location = new System.Drawing.Point(278, 6);
            this.rdoBanlistBc2GUID.Name = "rdoBanlistBc2GUID";
            this.rdoBanlistBc2GUID.Size = new System.Drawing.Size(74, 19);
            this.rdoBanlistBc2GUID.TabIndex = 116;
            this.rdoBanlistBc2GUID.Text = "BC2 Guid";
            this.rdoBanlistBc2GUID.UseVisualStyleBackColor = true;
            this.rdoBanlistBc2GUID.CheckedChanged += new System.EventHandler(this.rdoBanlistBc2GUID_CheckedChanged);
            //
            // lblBanlistConfirmation
            //
            this.lblBanlistConfirmation.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblBanlistConfirmation.Location = new System.Drawing.Point(313, 57);
            this.lblBanlistConfirmation.Name = "lblBanlistConfirmation";
            this.lblBanlistConfirmation.Size = new System.Drawing.Size(497, 41);
            this.lblBanlistConfirmation.TabIndex = 115;
            this.lblBanlistConfirmation.TextAlign = System.Drawing.ContentAlignment.BottomRight;
            //
            // panel7
            //
            this.panel7.Controls.Add(this.rdoBanlistTemporary);
            this.panel7.Controls.Add(this.rdoBanlistPermanent);
            this.panel7.Location = new System.Drawing.Point(3, 53);
            this.panel7.Name = "panel7";
            this.panel7.Size = new System.Drawing.Size(136, 65);
            this.panel7.TabIndex = 114;
            //
            // rdoBanlistTemporary
            //
            this.rdoBanlistTemporary.AutoSize = true;
            this.rdoBanlistTemporary.Location = new System.Drawing.Point(5, 28);
            this.rdoBanlistTemporary.Name = "rdoBanlistTemporary";
            this.rdoBanlistTemporary.Size = new System.Drawing.Size(83, 19);
            this.rdoBanlistTemporary.TabIndex = 113;
            this.rdoBanlistTemporary.Text = "Temporary";
            this.rdoBanlistTemporary.UseVisualStyleBackColor = true;
            this.rdoBanlistTemporary.CheckedChanged += new System.EventHandler(this.rdoBanlistTemporary_CheckedChanged);
            //
            // rdoBanlistPermanent
            //
            this.rdoBanlistPermanent.AutoSize = true;
            this.rdoBanlistPermanent.Checked = true;
            this.rdoBanlistPermanent.Location = new System.Drawing.Point(5, 6);
            this.rdoBanlistPermanent.Name = "rdoBanlistPermanent";
            this.rdoBanlistPermanent.Size = new System.Drawing.Size(83, 19);
            this.rdoBanlistPermanent.TabIndex = 112;
            this.rdoBanlistPermanent.TabStop = true;
            this.rdoBanlistPermanent.Text = "Permanent";
            this.rdoBanlistPermanent.UseVisualStyleBackColor = true;
            this.rdoBanlistPermanent.CheckedChanged += new System.EventHandler(this.rdoBanlistPermanent_CheckedChanged);
            //
            // pnlBanlistTime
            //
            this.pnlBanlistTime.Controls.Add(this.lblBanlistTime);
            this.pnlBanlistTime.Controls.Add(this.txtBanlistTime);
            this.pnlBanlistTime.Controls.Add(this.cboBanlistTimeMultiplier);
            this.pnlBanlistTime.Enabled = false;
            this.pnlBanlistTime.Location = new System.Drawing.Point(142, 57);
            this.pnlBanlistTime.Name = "pnlBanlistTime";
            this.pnlBanlistTime.Size = new System.Drawing.Size(165, 58);
            this.pnlBanlistTime.TabIndex = 113;
            //
            // lblBanlistTime
            //
            this.lblBanlistTime.AutoSize = true;
            this.lblBanlistTime.Location = new System.Drawing.Point(3, 2);
            this.lblBanlistTime.Name = "lblBanlistTime";
            this.lblBanlistTime.Size = new System.Drawing.Size(37, 15);
            this.lblBanlistTime.TabIndex = 0;
            this.lblBanlistTime.Text = "Time:";
            //
            // txtBanlistTime
            //
            this.txtBanlistTime.Location = new System.Drawing.Point(6, 23);
            this.txtBanlistTime.MaxLength = 3;
            this.txtBanlistTime.Name = "txtBanlistTime";
            this.txtBanlistTime.Size = new System.Drawing.Size(39, 23);
            this.txtBanlistTime.TabIndex = 1;
            this.txtBanlistTime.Text = "1";
            this.txtBanlistTime.TextChanged += new System.EventHandler(this.txtBanlistTime_TextChanged);
            this.txtBanlistTime.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtBanlistTime_KeyPress);
            //
            // cboBanlistTimeMultiplier
            //
            this.cboBanlistTimeMultiplier.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboBanlistTimeMultiplier.FormattingEnabled = true;
            this.cboBanlistTimeMultiplier.Items.AddRange(new object[] {
            "Minutes",
            "Hours",
            "Days",
            "Weeks",
            "Months"});
            this.cboBanlistTimeMultiplier.Location = new System.Drawing.Point(53, 23);
            this.cboBanlistTimeMultiplier.Name = "cboBanlistTimeMultiplier";
            this.cboBanlistTimeMultiplier.Size = new System.Drawing.Size(102, 23);
            this.cboBanlistTimeMultiplier.TabIndex = 2;
            this.cboBanlistTimeMultiplier.SelectedIndexChanged += new System.EventHandler(this.cboBanlistTimeMultiplier_SelectedIndexChanged);
            //
            // picBanlistManualBanOkay
            //
            this.picBanlistManualBanOkay.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.picBanlistManualBanOkay.Cursor = System.Windows.Forms.Cursors.Hand;
            this.picBanlistManualBanOkay.Location = new System.Drawing.Point(816, 82);
            this.picBanlistManualBanOkay.Name = "picBanlistManualBanOkay";
            this.picBanlistManualBanOkay.Size = new System.Drawing.Size(16, 16);
            this.picBanlistManualBanOkay.TabIndex = 109;
            this.picBanlistManualBanOkay.TabStop = false;
            this.picBanlistManualBanOkay.Visible = false;
            //
            // picBanlistIPError
            //
            this.picBanlistIPError.Cursor = System.Windows.Forms.Cursors.Hand;
            this.picBanlistIPError.Location = new System.Drawing.Point(121, 8);
            this.picBanlistIPError.Name = "picBanlistIPError";
            this.picBanlistIPError.Size = new System.Drawing.Size(16, 16);
            this.picBanlistIPError.TabIndex = 108;
            this.picBanlistIPError.TabStop = false;
            this.picBanlistIPError.Visible = false;
            //
            // picBanlistGUIDError
            //
            this.picBanlistGUIDError.Cursor = System.Windows.Forms.Cursors.Hand;
            this.picBanlistGUIDError.Location = new System.Drawing.Point(256, 9);
            this.picBanlistGUIDError.Name = "picBanlistGUIDError";
            this.picBanlistGUIDError.Size = new System.Drawing.Size(16, 16);
            this.picBanlistGUIDError.TabIndex = 107;
            this.picBanlistGUIDError.TabStop = false;
            this.picBanlistGUIDError.Visible = false;
            //
            // txtBanlistManualBanIP
            //
            this.txtBanlistManualBanIP.Enabled = false;
            this.txtBanlistManualBanIP.Location = new System.Drawing.Point(142, 28);
            this.txtBanlistManualBanIP.Name = "txtBanlistManualBanIP";
            this.txtBanlistManualBanIP.Size = new System.Drawing.Size(128, 23);
            this.txtBanlistManualBanIP.TabIndex = 67;
            this.txtBanlistManualBanIP.TextChanged += new System.EventHandler(this.txtBanlistManualBanIP_TextChanged);
            //
            // txtBanlistManualBanGUID
            //
            this.txtBanlistManualBanGUID.Enabled = false;
            this.txtBanlistManualBanGUID.Location = new System.Drawing.Point(276, 28);
            this.txtBanlistManualBanGUID.Name = "txtBanlistManualBanGUID";
            this.txtBanlistManualBanGUID.Size = new System.Drawing.Size(243, 23);
            this.txtBanlistManualBanGUID.TabIndex = 66;
            this.txtBanlistManualBanGUID.TextChanged += new System.EventHandler(this.txtBanlistManualBanGUID_TextChanged);
            //
            // lblBanlistReason
            //
            this.lblBanlistReason.AutoSize = true;
            this.lblBanlistReason.Location = new System.Drawing.Point(522, 10);
            this.lblBanlistReason.Name = "lblBanlistReason";
            this.lblBanlistReason.Size = new System.Drawing.Size(48, 15);
            this.lblBanlistReason.TabIndex = 65;
            this.lblBanlistReason.Text = "Reason:";
            //
            // cboBanlistReason
            //
            this.cboBanlistReason.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.cboBanlistReason.FormattingEnabled = true;
            this.cboBanlistReason.Items.AddRange(new object[] {
            "Team Killing",
            "Hacking/Cheating",
            "Admin abuse"});
            this.cboBanlistReason.Location = new System.Drawing.Point(525, 28);
            this.cboBanlistReason.Name = "cboBanlistReason";
            this.cboBanlistReason.Size = new System.Drawing.Size(492, 23);
            this.cboBanlistReason.TabIndex = 64;
            //
            // btnBanlistAddBan
            //
            this.btnBanlistAddBan.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnBanlistAddBan.Enabled = false;
            this.btnBanlistAddBan.ForeColor = System.Drawing.Color.Black;
            this.btnBanlistAddBan.Location = new System.Drawing.Point(838, 79);
            this.btnBanlistAddBan.Name = "btnBanlistAddBan";
            this.btnBanlistAddBan.Size = new System.Drawing.Size(179, 23);
            this.btnBanlistAddBan.TabIndex = 6;
            this.btnBanlistAddBan.Text = "Ban";
            this.btnBanlistAddBan.UseVisualStyleBackColor = true;
            this.btnBanlistAddBan.Click += new System.EventHandler(this.btnBanlistAddBan_Click);
            //
            // rdoBanlistPbGUID
            //
            this.rdoBanlistPbGUID.AutoSize = true;
            this.rdoBanlistPbGUID.Location = new System.Drawing.Point(386, 6);
            this.rdoBanlistPbGUID.Name = "rdoBanlistPbGUID";
            this.rdoBanlistPbGUID.Size = new System.Drawing.Size(67, 19);
            this.rdoBanlistPbGUID.TabIndex = 5;
            this.rdoBanlistPbGUID.Text = "PB Guid";
            this.rdoBanlistPbGUID.UseVisualStyleBackColor = true;
            this.rdoBanlistPbGUID.CheckedChanged += new System.EventHandler(this.rdoBanlistGUID_CheckedChanged);
            //
            // rdoBanlistIP
            //
            this.rdoBanlistIP.AutoSize = true;
            this.rdoBanlistIP.Location = new System.Drawing.Point(142, 6);
            this.rdoBanlistIP.Name = "rdoBanlistIP";
            this.rdoBanlistIP.Size = new System.Drawing.Size(77, 19);
            this.rdoBanlistIP.TabIndex = 4;
            this.rdoBanlistIP.Text = "IpAddress";
            this.rdoBanlistIP.UseVisualStyleBackColor = true;
            this.rdoBanlistIP.CheckedChanged += new System.EventHandler(this.rdoBanlistIP_CheckedChanged);
            //
            // rdoBanlistName
            //
            this.rdoBanlistName.AutoSize = true;
            this.rdoBanlistName.Checked = true;
            this.rdoBanlistName.Location = new System.Drawing.Point(8, 6);
            this.rdoBanlistName.Name = "rdoBanlistName";
            this.rdoBanlistName.Size = new System.Drawing.Size(57, 19);
            this.rdoBanlistName.TabIndex = 3;
            this.rdoBanlistName.TabStop = true;
            this.rdoBanlistName.Text = "Name";
            this.rdoBanlistName.UseVisualStyleBackColor = true;
            this.rdoBanlistName.CheckedChanged += new System.EventHandler(this.rdoBanlistName_CheckedChanged);
            //
            // txtBanlistManualBanName
            //
            this.txtBanlistManualBanName.Location = new System.Drawing.Point(8, 28);
            this.txtBanlistManualBanName.Name = "txtBanlistManualBanName";
            this.txtBanlistManualBanName.Size = new System.Drawing.Size(128, 23);
            this.txtBanlistManualBanName.TabIndex = 1;
            this.txtBanlistManualBanName.TextChanged += new System.EventHandler(this.txtBanlistManualBanName_TextChanged);
            //
            // tabMaplist
            //
            this.tabMaplist.Controls.Add(this.uscMaplist1);
            this.tabMaplist.Location = new System.Drawing.Point(4, 24);
            this.tabMaplist.Name = "tabMaplist";
            this.tabMaplist.Padding = new System.Windows.Forms.Padding(8);
            this.tabMaplist.Size = new System.Drawing.Size(1038, 828);
            this.tabMaplist.TabIndex = 0;
            this.tabMaplist.Text = "Maplist";
            this.tabMaplist.UseVisualStyleBackColor = true;
            //
            // uscMaplist1
            //
            this.uscMaplist1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscMaplist1.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscMaplist1.Location = new System.Drawing.Point(8, 8);
            this.uscMaplist1.Name = "uscMaplist1";
            this.uscMaplist1.SettingFail = null;
            this.uscMaplist1.SettingLoading = null;
            this.uscMaplist1.SettingSuccess = null;
            this.uscMaplist1.Size = new System.Drawing.Size(1022, 812);
            this.uscMaplist1.TabIndex = 1;
            //
            // tabReservedSlots
            //
            this.tabReservedSlots.Controls.Add(this.lblMohNotice);
            this.tabReservedSlots.Controls.Add(this.pnlReservedPanel);
            this.tabReservedSlots.Location = new System.Drawing.Point(4, 24);
            this.tabReservedSlots.Name = "tabReservedSlots";
            this.tabReservedSlots.Padding = new System.Windows.Forms.Padding(8);
            this.tabReservedSlots.Size = new System.Drawing.Size(1038, 828);
            this.tabReservedSlots.TabIndex = 1;
            this.tabReservedSlots.Text = "Reserved Slots";
            this.tabReservedSlots.UseVisualStyleBackColor = true;
            //
            // lblMohNotice
            //
            this.lblMohNotice.AutoSize = true;
            this.lblMohNotice.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMohNotice.ForeColor = System.Drawing.Color.Maroon;
            this.lblMohNotice.Location = new System.Drawing.Point(11, 11);
            this.lblMohNotice.Name = "lblMohNotice";
            this.lblMohNotice.Size = new System.Drawing.Size(320, 17);
            this.lblMohNotice.TabIndex = 28;
            this.lblMohNotice.Text = "This panel is disabled for MoH until further notice";
            this.lblMohNotice.Visible = false;
            //
            // pnlReservedPanel
            //
            this.pnlReservedPanel.Controls.Add(this.lblReservedCurrent);
            this.pnlReservedPanel.Controls.Add(this.pnlReservedAddSoldierName);
            this.pnlReservedPanel.Controls.Add(this.panel8);
            this.pnlReservedPanel.Controls.Add(this.panel9);
            this.pnlReservedPanel.Controls.Add(this.picReservedList);
            this.pnlReservedPanel.Controls.Add(this.panel10);
            this.pnlReservedPanel.Controls.Add(this.lsvReservedList);
            this.pnlReservedPanel.Location = new System.Drawing.Point(229, 31);
            this.pnlReservedPanel.Name = "pnlReservedPanel";
            this.pnlReservedPanel.Size = new System.Drawing.Size(486, 445);
            this.pnlReservedPanel.TabIndex = 27;
            //
            // lblReservedCurrent
            //
            this.lblReservedCurrent.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblReservedCurrent.AutoSize = true;
            this.lblReservedCurrent.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblReservedCurrent.Location = new System.Drawing.Point(32, 17);
            this.lblReservedCurrent.Name = "lblReservedCurrent";
            this.lblReservedCurrent.Size = new System.Drawing.Size(131, 15);
            this.lblReservedCurrent.TabIndex = 14;
            this.lblReservedCurrent.Text = "Current reserved slots";
            //
            // pnlReservedAddSoldierName
            //
            this.pnlReservedAddSoldierName.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.pnlReservedAddSoldierName.Controls.Add(this.txtReservedAddSoldierName);
            this.pnlReservedAddSoldierName.Controls.Add(this.picReservedAddSoldierName);
            this.pnlReservedAddSoldierName.Controls.Add(this.lblReservedAddSoldierName);
            this.pnlReservedAddSoldierName.Controls.Add(this.lnkReservedAddSoldierName);
            this.pnlReservedAddSoldierName.Location = new System.Drawing.Point(35, 305);
            this.pnlReservedAddSoldierName.Name = "pnlReservedAddSoldierName";
            this.pnlReservedAddSoldierName.Size = new System.Drawing.Size(430, 60);
            this.pnlReservedAddSoldierName.TabIndex = 2;
            //
            // txtReservedAddSoldierName
            //
            this.txtReservedAddSoldierName.Location = new System.Drawing.Point(34, 27);
            this.txtReservedAddSoldierName.Name = "txtReservedAddSoldierName";
            this.txtReservedAddSoldierName.Size = new System.Drawing.Size(280, 23);
            this.txtReservedAddSoldierName.TabIndex = 1;
            this.txtReservedAddSoldierName.TextChanged += new System.EventHandler(this.txtReservedAddSoldierName_TextChanged);
            this.txtReservedAddSoldierName.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtReservedAddSoldierName_KeyDown);
            //
            // picReservedAddSoldierName
            //
            this.picReservedAddSoldierName.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picReservedAddSoldierName.Location = new System.Drawing.Point(12, 8);
            this.picReservedAddSoldierName.Name = "picReservedAddSoldierName";
            this.picReservedAddSoldierName.Size = new System.Drawing.Size(16, 16);
            this.picReservedAddSoldierName.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picReservedAddSoldierName.TabIndex = 102;
            this.picReservedAddSoldierName.TabStop = false;
            //
            // lblReservedAddSoldierName
            //
            this.lblReservedAddSoldierName.AutoSize = true;
            this.lblReservedAddSoldierName.Location = new System.Drawing.Point(31, 9);
            this.lblReservedAddSoldierName.Name = "lblReservedAddSoldierName";
            this.lblReservedAddSoldierName.Size = new System.Drawing.Size(161, 15);
            this.lblReservedAddSoldierName.TabIndex = 93;
            this.lblReservedAddSoldierName.Text = "Add a soldier name to the list";
            //
            // lnkReservedAddSoldierName
            //
            this.lnkReservedAddSoldierName.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkReservedAddSoldierName.AutoSize = true;
            this.lnkReservedAddSoldierName.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkReservedAddSoldierName.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkReservedAddSoldierName.Location = new System.Drawing.Point(320, 30);
            this.lnkReservedAddSoldierName.Name = "lnkReservedAddSoldierName";
            this.lnkReservedAddSoldierName.Size = new System.Drawing.Size(67, 15);
            this.lnkReservedAddSoldierName.TabIndex = 2;
            this.lnkReservedAddSoldierName.TabStop = true;
            this.lnkReservedAddSoldierName.Text = "Add soldier";
            this.lnkReservedAddSoldierName.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkReservedAddSoldierName_LinkClicked);
            //
            // panel8
            //
            this.panel8.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panel8.Controls.Add(this.btnReservedSlotsListRefresh);
            this.panel8.Controls.Add(this.btnReservedRemoveSoldier);
            this.panel8.Location = new System.Drawing.Point(424, 120);
            this.panel8.Name = "panel8";
            this.panel8.Size = new System.Drawing.Size(41, 100);
            this.panel8.TabIndex = 1;
            //
            // btnReservedRemoveSoldier
            //
            this.btnReservedRemoveSoldier.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnReservedRemoveSoldier.Enabled = false;
            this.btnReservedRemoveSoldier.FlatAppearance.BorderSize = 0;
            this.btnReservedRemoveSoldier.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnReservedRemoveSoldier.Location = new System.Drawing.Point(3, 32);
            this.btnReservedRemoveSoldier.Name = "btnReservedRemoveSoldier";
            this.btnReservedRemoveSoldier.Size = new System.Drawing.Size(35, 23);
            this.btnReservedRemoveSoldier.TabIndex = 1;
            this.btnReservedRemoveSoldier.UseVisualStyleBackColor = true;
            this.btnReservedRemoveSoldier.Click += new System.EventHandler(this.btnReservedRemoveSoldier_Click);
            //
            // panel9
            //
            this.panel9.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.panel9.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel9.Location = new System.Drawing.Point(35, 289);
            this.panel9.Name = "panel9";
            this.panel9.Size = new System.Drawing.Size(424, 1);
            this.panel9.TabIndex = 95;
            //
            // picReservedList
            //
            this.picReservedList.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picReservedList.Location = new System.Drawing.Point(47, 41);
            this.picReservedList.Name = "picReservedList";
            this.picReservedList.Size = new System.Drawing.Size(16, 16);
            this.picReservedList.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picReservedList.TabIndex = 91;
            this.picReservedList.TabStop = false;
            //
            // panel10
            //
            this.panel10.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.panel10.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel10.Location = new System.Drawing.Point(35, 25);
            this.panel10.Name = "panel10";
            this.panel10.Size = new System.Drawing.Size(424, 1);
            this.panel10.TabIndex = 16;
            //
            // lsvReservedList
            //
            this.lsvReservedList.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)));
            this.lsvReservedList.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colSoldierNames});
            this.lsvReservedList.FullRowSelect = true;
            this.lsvReservedList.HideSelection = false;
            this.lsvReservedList.Location = new System.Drawing.Point(69, 41);
            this.lsvReservedList.MultiSelect = false;
            this.lsvReservedList.Name = "lsvReservedList";
            this.lsvReservedList.Size = new System.Drawing.Size(349, 232);
            this.lsvReservedList.TabIndex = 0;
            this.lsvReservedList.UseCompatibleStateImageBehavior = false;
            this.lsvReservedList.View = System.Windows.Forms.View.Details;
            this.lsvReservedList.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lsvReservedList_ColumnClick);
            this.lsvReservedList.SelectedIndexChanged += new System.EventHandler(this.lsvReservedList_SelectedIndexChanged);
            //
            // colSoldierNames
            //
            this.colSoldierNames.Text = "Soldier Names";
            this.colSoldierNames.Width = 317;
            //
            // tabTextChatModeration
            //
            this.tabTextChatModeration.Controls.Add(this.uscTextChatModerationListcs1);
            this.tabTextChatModeration.Location = new System.Drawing.Point(4, 24);
            this.tabTextChatModeration.Name = "tabTextChatModeration";
            this.tabTextChatModeration.Padding = new System.Windows.Forms.Padding(3);
            this.tabTextChatModeration.Size = new System.Drawing.Size(1038, 828);
            this.tabTextChatModeration.TabIndex = 3;
            this.tabTextChatModeration.Text = "Text chat moderation";
            this.tabTextChatModeration.UseVisualStyleBackColor = true;
            //
            // uscTextChatModerationListcs1
            //
            this.uscTextChatModerationListcs1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscTextChatModerationListcs1.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscTextChatModerationListcs1.Location = new System.Drawing.Point(3, 3);
            this.uscTextChatModerationListcs1.Name = "uscTextChatModerationListcs1";
            this.uscTextChatModerationListcs1.SettingFail = null;
            this.uscTextChatModerationListcs1.SettingLoading = null;
            this.uscTextChatModerationListcs1.SettingSuccess = null;
            this.uscTextChatModerationListcs1.Size = new System.Drawing.Size(1032, 822);
            this.uscTextChatModerationListcs1.TabIndex = 0;
            //
            // tmrTimeoutCheck
            //
            this.tmrTimeoutCheck.Tick += new System.EventHandler(this.tmrSettingsAnimator_Tick);
            //
            // tmrRefreshBanlist
            //
            this.tmrRefreshBanlist.Enabled = true;
            this.tmrRefreshBanlist.Interval = 15000;
            this.tmrRefreshBanlist.Tick += new System.EventHandler(this.tmrRefreshBanlist_Tick);
            //
            // ctxBanlistMenuStrip
            //
            this.ctxBanlistMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.copyToolStripMenuItem,
            this.toolStripMenuItem1,
            this.unbanToolStripMenuItem});
            this.ctxBanlistMenuStrip.Name = "ctxBanlistMenuStrip";
            this.ctxBanlistMenuStrip.Size = new System.Drawing.Size(172, 54);
            //
            // copyToolStripMenuItem
            //
            this.copyToolStripMenuItem.Name = "copyToolStripMenuItem";
            this.copyToolStripMenuItem.Size = new System.Drawing.Size(171, 22);
            this.copyToolStripMenuItem.Text = "Copy to Clipboard";
            this.copyToolStripMenuItem.Click += new System.EventHandler(this.copyToolStripMenuItem_Click);
            //
            // toolStripMenuItem1
            //
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(168, 6);
            //
            // unbanToolStripMenuItem
            //
            this.unbanToolStripMenuItem.Name = "unbanToolStripMenuItem";
            this.unbanToolStripMenuItem.Size = new System.Drawing.Size(171, 22);
            this.unbanToolStripMenuItem.Text = "Unban";
            this.unbanToolStripMenuItem.Click += new System.EventHandler(this.unbanToolStripMenuItem_Click);
            //
            // btnReservedSlotsListRefresh
            //
            this.btnReservedSlotsListRefresh.FlatAppearance.BorderSize = 0;
            this.btnReservedSlotsListRefresh.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnReservedSlotsListRefresh.Location = new System.Drawing.Point(3, 74);
            this.btnReservedSlotsListRefresh.Name = "btnReservedSlotsListRefresh";
            this.btnReservedSlotsListRefresh.Size = new System.Drawing.Size(35, 23);
            this.btnReservedSlotsListRefresh.TabIndex = 109;
            this.btnReservedSlotsListRefresh.UseVisualStyleBackColor = true;
            this.btnReservedSlotsListRefresh.Click += new System.EventHandler(this.btnReservedSlotsListRefresh_Click);
            //
            // uscListControlPanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tbcLists);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscListControlPanel";
            this.Size = new System.Drawing.Size(1046, 856);
            this.Load += new System.EventHandler(this.uscListControlPanel_Load);
            this.tbcLists.ResumeLayout(false);
            this.tabBanlist.ResumeLayout(false);
            this.spltBanlistManualBans.Panel1.ResumeLayout(false);
            this.spltBanlistManualBans.Panel1.PerformLayout();
            this.spltBanlistManualBans.Panel2.ResumeLayout(false);
            this.spltBanlistManualBans.Panel2.PerformLayout();
            this.spltBanlistManualBans.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picCloseOpenManualBans)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picClearLists)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picUnbanPlayer)).EndInit();
            this.panel7.ResumeLayout(false);
            this.panel7.PerformLayout();
            this.pnlBanlistTime.ResumeLayout(false);
            this.pnlBanlistTime.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picBanlistManualBanOkay)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picBanlistIPError)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picBanlistGUIDError)).EndInit();
            this.tabMaplist.ResumeLayout(false);
            this.tabReservedSlots.ResumeLayout(false);
            this.tabReservedSlots.PerformLayout();
            this.pnlReservedPanel.ResumeLayout(false);
            this.pnlReservedPanel.PerformLayout();
            this.pnlReservedAddSoldierName.ResumeLayout(false);
            this.pnlReservedAddSoldierName.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picReservedAddSoldierName)).EndInit();
            this.panel8.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picReservedList)).EndInit();
            this.tabTextChatModeration.ResumeLayout(false);
            this.ctxBanlistMenuStrip.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tbcLists;
        private System.Windows.Forms.TabPage tabMaplist;
        private System.Windows.Forms.TabPage tabReservedSlots;
        private System.Windows.Forms.TabPage tabBanlist;
        private System.Windows.Forms.Timer tmrTimeoutCheck;
        private System.Windows.Forms.Panel pnlReservedPanel;
        private System.Windows.Forms.Panel pnlReservedAddSoldierName;
        private System.Windows.Forms.PictureBox picReservedAddSoldierName;
        private System.Windows.Forms.Label lblReservedAddSoldierName;
        private System.Windows.Forms.LinkLabel lnkReservedAddSoldierName;
        private System.Windows.Forms.Panel panel8;
        private System.Windows.Forms.Button btnReservedRemoveSoldier;
        private System.Windows.Forms.Panel panel9;
        private System.Windows.Forms.PictureBox picReservedList;
        private System.Windows.Forms.Panel panel10;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvReservedList;
        private System.Windows.Forms.ColumnHeader colSoldierNames;
        private System.Windows.Forms.Label lblReservedCurrent;
        private System.Windows.Forms.TextBox txtReservedAddSoldierName;
        private System.Windows.Forms.Timer tmrRefreshBanlist;
        private System.Windows.Forms.SplitContainer spltBanlistManualBans;
        private System.Windows.Forms.LinkLabel lnkCloseOpenManualBans;
        private System.Windows.Forms.PictureBox picClearLists;
        private System.Windows.Forms.PictureBox picUnbanPlayer;
        private System.Windows.Forms.Button btnBanlistClearBanlist;
        private System.Windows.Forms.Button btnBanlistUnban;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvBanlist;
        private System.Windows.Forms.ColumnHeader colName;
        private System.Windows.Forms.ColumnHeader colIP;
        private System.Windows.Forms.ColumnHeader colGUID;
        private System.Windows.Forms.ColumnHeader colType;
        private System.Windows.Forms.ColumnHeader colTimeRemaining;
        private System.Windows.Forms.ColumnHeader colReason;
        private System.Windows.Forms.TextBox txtBanlistManualBanName;
        private System.Windows.Forms.RadioButton rdoBanlistPbGUID;
        private System.Windows.Forms.RadioButton rdoBanlistIP;
        private System.Windows.Forms.RadioButton rdoBanlistName;
        private System.Windows.Forms.Button btnBanlistAddBan;
        private System.Windows.Forms.Label lblBanlistReason;
        private System.Windows.Forms.ComboBox cboBanlistReason;
        private System.Windows.Forms.PictureBox picCloseOpenManualBans;
        private System.Windows.Forms.TextBox txtBanlistManualBanIP;
        private System.Windows.Forms.TextBox txtBanlistManualBanGUID;
        private System.Windows.Forms.PictureBox picBanlistGUIDError;
        private System.Windows.Forms.PictureBox picBanlistIPError;
        private System.Windows.Forms.PictureBox picBanlistManualBanOkay;
        private System.Windows.Forms.ContextMenuStrip ctxBanlistMenuStrip;
        private System.Windows.Forms.ToolStripMenuItem copyToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem unbanToolStripMenuItem;
        private System.Windows.Forms.Panel pnlBanlistTime;
        private System.Windows.Forms.Label lblBanlistTime;
        private System.Windows.Forms.TextBox txtBanlistTime;
        private System.Windows.Forms.ComboBox cboBanlistTimeMultiplier;
        private System.Windows.Forms.Panel panel7;
        private System.Windows.Forms.RadioButton rdoBanlistTemporary;
        private System.Windows.Forms.RadioButton rdoBanlistPermanent;
        private System.Windows.Forms.Label lblBanlistConfirmation;
        private System.Windows.Forms.RadioButton rdoBanlistBc2GUID;
        private System.Windows.Forms.Button btnBanlistRefresh;
        private Controls.Maplist.uscMaplist uscMaplist1;
        private System.Windows.Forms.TabPage tabTextChatModeration;
        private Controls.TextChatModeration.uscTextChatModerationListcs uscTextChatModerationListcs1;
        private System.Windows.Forms.Label lblMohNotice;
        private System.Windows.Forms.Button btnReservedSlotsListRefresh;
    }*/
}
