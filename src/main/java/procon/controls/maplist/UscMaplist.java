package procon.controls.maplist;

public class UscMaplist {

    /*public partial class uscMaplist : uscPage {

        private frmMain m_frmMain;
        //private uscServerConnection m_uscConnectionPanel;
        private PRoConClient m_client;

        //private List<Core.Maps.MaplistEntry> m_previousMapList;

        private bool m_blSettingNewPlaylist;
        private bool m_blSettingAppendingSingleMap;

        private CPrivileges m_privileges;

        private int m_iReselectShufflingMapIndex = 0;

        public uscMaplist() {
            InitializeComponent();

            this.SetStyle(ControlStyles.UserPaint, true);
            this.SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.DoubleBuffer, true);

            this.AsyncSettingControls.Add("local.playlist.change", new AsyncStyleSetting(this.picMaplistChangePlaylist, this.lsvMaplist, new Control[] { this.pnlMaplistAddMap, this.lsvMaplist }, true));
            this.AsyncSettingControls.Add("local.maplist.change", new AsyncStyleSetting(this.picMaplistAlterMaplist, this.lsvMaplist, new Control[] { this.pnlMaplistAddMap, this.lsvMaplist }, true));

            this.m_blSettingAppendingSingleMap = false;
            this.AsyncSettingControls.Add("local.maplist.append", new AsyncStyleSetting(this.picMaplistAppendMap, this.lsvMaplist, new Control[] { this.lblMaplistPool }, true));
            //this.AsyncSettingControls.Add("local.maplist.setnextlevel", new AsyncStyleSetting(this.picMaplistAppendMap, this.lsvMaplist, new Control[] { this.lblMaplistPool, this.lnkMaplistAddMapToList, this.lnkMaplistSetNextMap }, true));

            this.m_privileges = new CPrivileges(CPrivileges.FullPrivilegesFlags);
        }

        public override void SetLocalization(CLocalization clocLanguage) {

            this.lblMaplistCurrentPlayList.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lblMaplistCurrentPlayList");
            this.lnkMaplistChangePlaylist.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lnkMaplistChangePlaylist");
            this.lblMaplistCurrentMaplist.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lblMaplistCurrentMaplist");

            this.colPoolGameType.Text = this.colGameType.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lsvMaplist.colGametype");
            this.colPoolMapname.Text = this.colMapname.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lsvMaplist.colMapname");
            this.colPoolMapFilename.Text = this.colMapFilename.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lsvMaplist.colMapFilename");
            this.lblMaplistMustChangeWarning.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lblMaplistMustChangeWarning");
            this.lblMaplistPool.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lblMaplistPool");

            this.colMapRounds.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lsvMaplist.colMapRounds");
            this.lblMaplistRounds.Text = clocLanguage.GetLocalized("uscListControlPanel.tabMaplist.lblMaplistRounds");
        }


        public void Initialize(frmMain frmMainWindow, uscServerConnection uscConnectionPanel) {
            this.m_frmMain = frmMainWindow;

            this.SettingFail = frmMainWindow.picAjaxStyleFail.Image;
            this.SettingSuccess = frmMainWindow.picAjaxStyleSuccess.Image;
            this.SettingLoading = frmMainWindow.picAjaxStyleLoading.Image;

            this.btnAddMap.Image = frmMainWindow.iglIcons.Images["arrow-curve-000-left.png"];
            this.btnRemoveMap.Image = frmMainWindow.iglIcons.Images["arrow-curve-180-left.png"];
        }

        public override void SetConnection(PRoConClient prcClient) {
            if ((this.m_client = prcClient) != null) {
                if (this.m_client.Game != null) {
                    this.m_prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    this.m_client.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcClient_GameTypeDiscovered);
                }
            }
        }

        private void m_prcClient_GameTypeDiscovered(PRoConClient sender) {

            this.m_client.Game.PlaylistSet += new FrostbiteClient.PlaylistSetHandler(Game_PlaylistSet);

            this.m_client.Game.MapListSave += new FrostbiteClient.EmptyParamterHandler(Game_MapListSave);
            this.m_client.Game.MapListMapAppended += new FrostbiteClient.MapListAppendedHandler(Game_MapListMapAppended);
            this.m_client.Game.MapListCleared += new FrostbiteClient.EmptyParamterHandler(Game_MapListCleared);
            this.m_client.Game.MapListListed += new FrostbiteClient.MapListListedHandler(Game_MapListListed);
            this.m_client.Game.MapListMapInserted += new FrostbiteClient.MapListMapInsertedHandler(Game_MapListMapInserted);
            this.m_client.Game.MapListMapRemoved += new FrostbiteClient.MapListLevelIndexHandler(Game_MapListMapRemoved);

            this.m_client.MapListPool.ItemAdded += new NotificationList<CMap>.ItemModifiedHandler(MapListPool_ItemAdded);

            this.m_client.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcClient_ProconPrivileges);

            this.splitContainer1.Panel1Collapsed = this.m_client.Game.HasOpenMaplist;

            this.RefreshLocalMaplist();
        }

        private void RefreshLocalMaplist() {

            if (this.m_client != null && this.m_client.Game != null) {

                if (this.m_client.Game.HasOpenMaplist == false) {

                    CMap[] a_objItems = new CMap[this.cboMaplistPlaylists.Items.Count];
                    this.cboMaplistPlaylists.Items.CopyTo(a_objItems, 0);
                    List<CMap> lstCurrentList = new List<CMap>(a_objItems);

                    foreach (CMap mapPool in this.m_client.MapListPool) {
                        if (lstCurrentList.Find(map => String.Compare(map.PlayList, mapPool.PlayList, true) == 0) == null) {
                            this.cboMaplistPlaylists.Items.Add(mapPool);

                            if (String.Compare(mapPool.PlayList, this.m_client.ListSettings.CurrentPlaylist, true) == 0) {
                                this.cboMaplistPlaylists.SelectedItem = mapPool;
                            }

                            a_objItems = new CMap[this.cboMaplistPlaylists.Items.Count];
                            this.cboMaplistPlaylists.Items.CopyTo(a_objItems, 0);
                            lstCurrentList = new List<CMap>(a_objItems);
                        }
                    }
                }

                if (this.cboMaplistPlaylists.SelectedItem != null || this.m_client.Game.HasOpenMaplist == true) {

                    this.lsvMaplistPool.BeginUpdate();

                    // Update the available maplist pool.
                    this.lsvMaplistPool.Items.Clear();
                    List<CMap> lstMapPool;
                    if (this.m_client.Game.HasOpenMaplist == true) {
                        lstMapPool = new List<CMap>(this.m_client.MapListPool);
                    }
                    else {
                        lstMapPool = this.m_client.MapListPool.FindAll(map => String.Compare(map.PlayList, ((CMap)cboMaplistPlaylists.SelectedItem).PlayList, true) == 0);
                    }

                    foreach (CMap map in lstMapPool) {

                        if (this.lsvMaplistPool.Items.ContainsKey(map.FileName.ToLower()) == false) {
                            ListViewItem mapPoolItem = new ListViewItem();
                            mapPoolItem.Tag = new MaplistEntry(map.FileName);
                            mapPoolItem.Text = map.GameMode;
                            mapPoolItem.SubItems.Add(new ListViewItem.ListViewSubItem(mapPoolItem, map.FileName));
                            mapPoolItem.SubItems.Add(new ListViewItem.ListViewSubItem(mapPoolItem, map.PublicLevelName));

                            this.lsvMaplistPool.Items.Add(mapPoolItem);
                        }
                    }

                    this.lsvMaplistPool.EndUpdate();
                }

                for (int i = 0; i < this.lsvMaplistPool.Columns.Count; i++) {
                    this.lsvMaplistPool.Columns[i].Width = -2;
                }
            }
        }

        private void m_prcClient_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {
            this.m_privileges = spPrivs;

            this.Enabled = true && this.m_privileges.CanEditMapList;
        }

        private void MapListPool_ItemAdded(int iIndex, CMap item) {
            this.RefreshLocalMaplist();
        }

        private void Game_MapListMapInserted(FrostbiteClient sender, int mapIndex, string mapFileName, int rounds) {
            this.InsertMapInMapList(mapIndex, new Core.Maps.MaplistEntry(mapFileName, rounds));
        }

        private void Game_MapListMapRemoved(FrostbiteClient sender, int mapIndex) {
            if (this.lsvMaplist.Items.Count > mapIndex) {
                this.lsvMaplist.Items.RemoveAt(mapIndex);

                if (this.m_iReselectShufflingMapIndex < this.lsvMaplist.Items.Count) {
                    this.lsvMaplist.Items[this.m_iReselectShufflingMapIndex].Selected = true;
                }

                this.UpdateMaplistIndexes();
            }
        }

        private void Game_MapListListed(FrostbiteClient sender, List<Core.Maps.MaplistEntry> lstMapList) {
            this.lsvMaplist.BeginUpdate();

            this.lsvMaplist.Items.Clear();

            for (int i = 0; i < lstMapList.Count; i++) {
                ListViewItem lviMap = new ListViewItem();
                lviMap.Tag = lstMapList[i];
                lviMap.Name = lstMapList[i].MapFileName;
                lviMap.Text = Convert.ToString(i + 1);

                lviMap.SubItems.Add(this.m_client.GetFriendlyGamemodeByMap(lstMapList[i].MapFileName));
                lviMap.SubItems.Add(this.m_client.GetFriendlyMapname(lstMapList[i].MapFileName));
                lviMap.SubItems.Add(lstMapList[i].MapFileName);

                ListViewItem.ListViewSubItem lviRounds = new ListViewItem.ListViewSubItem();
                lviRounds.Name = "rounds";

                if (lstMapList[i].Rounds == 0) {
                    lviRounds.Text = "2";
                    lviRounds.Tag = 0;
                }
                else {
                    lviRounds.Text = lstMapList[i].Rounds.ToString();
                    lviRounds.Tag = lstMapList[i].Rounds;
                }

                lviMap.SubItems.Add(lviRounds);

                this.lsvMaplist.Items.Add(lviMap);
            }

            for (int i = 0; i < this.lsvMaplist.Columns.Count; i++) {
                this.lsvMaplist.Columns[i].Width = -2;
            }

            this.lsvMaplist.EndUpdate();
        }

        private void Game_MapListCleared(FrostbiteClient sender) {
            this.lsvMaplist.BeginUpdate();

            this.lsvMaplist.Items.Clear();

            this.lsvMaplist.EndUpdate();
        }

        private void UpdateMaplistIndexes() {
            for (int i = 0; i < this.lsvMaplist.Items.Count; i++) {
                this.lsvMaplist.Items[i].Text = (i + 1).ToString();
            }
        }

        private void InsertMapInMapList(int insertIndex, Core.Maps.MaplistEntry mapEntry) {
            this.lsvMaplist.BeginUpdate();

            ListViewItem lviMap = new ListViewItem();

            lviMap.Tag = mapEntry;
            lviMap.Name = mapEntry.MapFileName;
            lviMap.Text = Convert.ToString(this.lsvMaplist.Items.Count + 1);

            lviMap.SubItems.Add(this.m_client.GetFriendlyGamemodeByMap(mapEntry.MapFileName));
            lviMap.SubItems.Add(this.m_client.GetFriendlyMapname(mapEntry.MapFileName));
            lviMap.SubItems.Add(mapEntry.MapFileName);

            ListViewItem.ListViewSubItem lviRounds = new ListViewItem.ListViewSubItem();
            lviRounds.Name = "rounds";

            if (mapEntry.Rounds == 0) {
                lviRounds.Text = "2";
                lviRounds.Tag = 0;
            }
            else {
                lviRounds.Text = mapEntry.Rounds.ToString();
                lviRounds.Tag = mapEntry.Rounds;
            }

            lviMap.SubItems.Add(lviRounds);

            if (insertIndex >= 0) {
                this.lsvMaplist.Items.Insert(insertIndex, lviMap);
            }
            else {
                this.lsvMaplist.Items.Add(lviMap);
            }

            this.UpdateMaplistIndexes();

            for (int i = 0; i < this.lsvMaplist.Columns.Count; i++) {
                this.lsvMaplist.Columns[i].Width = -2;
            }

            if (this.m_blSettingAppendingSingleMap == true) {
                this.OnSettingResponse("local.maplist.append", true);
            }

            this.lsvMaplist.EndUpdate();
        }

        private void Game_MapListMapAppended(FrostbiteClient sender, Core.Maps.MaplistEntry mapEntry) {
            this.InsertMapInMapList(-1, mapEntry);
        }

        private void Game_MapListSave(FrostbiteClient sender) {
            if (this.m_iReselectShufflingMapIndex < this.lsvMaplist.Items.Count) {
                this.lsvMaplist.Items[this.m_iReselectShufflingMapIndex].Selected = true;
            }

            if (this.m_blSettingNewPlaylist == true) {
                this.OnSettingResponse("local.playlist.change", true);
            }

            this.OnSettingResponse("local.maplist.change", true);

            this.m_blSettingNewPlaylist = false;

            this.lsvMaplist.EndUpdate();
        }

        private void Game_PlaylistSet(FrostbiteClient sender, string playlist) {

            // If we've just set the playlist..
            if (this.m_blSettingNewPlaylist == true && this.m_client.MapListPool.Find(map => String.Compare(map.PlayList, playlist, true) == 0) != null) {
                // Add all the supported maps

                this.WaitForSettingResponse("local.playlist.change");
                this.WaitForSettingResponse("local.maplist.change");

                this.lsvMaplist.BeginUpdate();

                sender.SendMapListClearPacket();

                foreach (CMap map in this.m_client.MapListPool.FindAll(map => String.Compare(map.PlayList, playlist, true) == 0)) {
                    sender.SendMapListAppendPacket(map.FileName, 0);
                }

                sender.SendMapListSavePacket();
            }

            foreach (CMap cmPlayList in this.cboMaplistPlaylists.Items) {
                if (String.Compare(playlist, cmPlayList.PlayList, true) == 0) {
                    this.cboMaplistPlaylists.SelectedItem = cmPlayList;

                    this.lnkMaplistChangePlaylist.Enabled = false;
                    this.pnlMaplistAddMap.Enabled = true && this.m_privileges.CanEditMapList;
                    //this.pnlMaplistAddMap.Enabled = true && this.m_privileges.CanEditMapList && this.lsvMaplist.SelectedItems.Count > 0;

                    this.lblMaplistMustChangeWarning.Visible = false;

                    this.cboMaplistPlaylists.Refresh();

                    this.RefreshLocalMaplist();
                }
            }

            this.cboMaplistPlaylists_SelectedIndexChanged(null, null);
        }

        private void cboMaplistPlaylists_SelectedIndexChanged(object sender, EventArgs e) {

            if (cboMaplistPlaylists.SelectedIndex >= 0) {
                if (String.Compare(((CMap)cboMaplistPlaylists.Items[cboMaplistPlaylists.SelectedIndex]).PlayList, this.m_client.ListSettings.CurrentPlaylist, true) == 0) {

                    this.lnkMaplistChangePlaylist.Enabled = false;
                    this.pnlMaplistAddMap.Enabled = true && this.m_privileges.CanEditMapList;
                    //this.pnlMaplistPositionControls.Enabled = true && this.m_privileges.CanEditMapList && this.lsvMaplist.SelectedItems.Count > 0;

                    this.lblMaplistMustChangeWarning.Visible = false;
                    this.splitContainer1.Panel2.Enabled = true;
                }
                else {
                    this.lnkMaplistChangePlaylist.Enabled = true && this.m_privileges.CanEditMapList;
                    this.pnlMaplistAddMap.Enabled = false;
                    //this.pnlMaplistPositionControls.Enabled = false;

                    this.lblMaplistMustChangeWarning.Visible = true;
                    this.splitContainer1.Panel2.Enabled = false;
                }

                this.RefreshLocalMaplist();
            }
        }

        private void cboMaplistPlaylists_DrawItem(object sender, DrawItemEventArgs e) {
            if (e.Index != -1) {

                Font ftnSelectFont = this.Font;
                Brush clrBrushColour = SystemBrushes.WindowText;
                CMap mpGamemode = ((CMap)cboMaplistPlaylists.Items[e.Index]);

                e.Graphics.FillRectangle(SystemBrushes.Window, e.Bounds);

                if (String.Compare(mpGamemode.PlayList, this.m_client.ListSettings.CurrentPlaylist, true) == 0) {
                    ftnSelectFont = new Font(this.Font, FontStyle.Bold);
                    clrBrushColour = Brushes.MediumSeaGreen;
                }

                if (this.m_client.MapListPool.Find(map => String.Compare(map.PlayList, mpGamemode.PlayList, true) == 0) != null) {
                    e.Graphics.DrawString(mpGamemode.GameMode, ftnSelectFont, clrBrushColour, e.Bounds.Left + 5, e.Bounds.Top);

                    string strSupportedMaps = this.m_client.Language.GetLocalized("uscListControlPanel.tabMaplist.cboMaplistPlaylists.SupportedMaps", this.m_client.MapListPool.FindAll(map => String.Compare(map.PlayList, mpGamemode.PlayList, true) == 0).Count.ToString());
                    e.Graphics.DrawString(strSupportedMaps, ftnSelectFont, clrBrushColour, e.Bounds.Right - TextRenderer.MeasureText(strSupportedMaps, ftnSelectFont).Width - 10, e.Bounds.Top);
                }
                else {
                    e.Graphics.DrawString(mpGamemode.GameMode, ftnSelectFont, clrBrushColour, e.Bounds.Left + 5, e.Bounds.Top);
                }
            }
        }

        #region Maplist Control Events

        private void lsvMaplist_BeforeLabelEdit(object sender, LabelEditEventArgs e) {
            e.CancelEdit = true;
        }

        private void lsvMaplist_DragEnter(object sender, DragEventArgs e) {
            e.Effect = DragDropEffects.Move;
        }

        private void lsvMaplist_DragOver(object sender, DragEventArgs e) {

            if (e.Data is MaplistEntry) {
                e.Effect = DragDropEffects.Move;
            }

            //this.lsvMaplist.BeginUpdate();
            this.lsvMaplist.Refresh();
            //this.lsvMaplist.EndUpdate();

            Point cp = this.lsvMaplist.PointToClient(new Point(e.X, e.Y));
            ListViewItem dragToItem = this.lsvMaplist.GetItemAt(cp.X, cp.Y);
            Rectangle itemBounds = new Rectangle();
            if (dragToItem != null) {
                itemBounds = dragToItem.GetBounds(ItemBoundsPortion.Entire);
            }
            else {
                if (this.lsvMaplist.Items.Count > 0) {
                    dragToItem = this.lsvMaplist.Items[this.lsvMaplist.Items.Count - 1];
                    itemBounds = dragToItem.GetBounds(ItemBoundsPortion.Entire);
                    itemBounds.Y += itemBounds.Height;
                }
            }

            if (dragToItem != null) {
                Graphics g = this.lsvMaplist.CreateGraphics();

                GraphicsPath gp = new GraphicsPath();
                gp.AddLines(
                    new Point[]
                    {
                        new Point(0, itemBounds.Y - 4),
                        new Point(5, itemBounds.Y),
                        new Point(0, itemBounds.Y + 4)
                    }
                    );

                g.FillPath(Brushes.Black, gp);
                gp.Dispose();

                g.DrawLine(new Pen(Brushes.Black, 2.0F), 0, itemBounds.Y, itemBounds.Width, itemBounds.Y);
                g.Dispose();
            }

        }

        private void lsvMaplist_DragDrop(object sender, DragEventArgs e) {
            this.lsvMaplist.Refresh();

            MaplistEntry insertingMap = ((MaplistEntry)e.Data.GetData(typeof(MaplistEntry)));
            if (this.m_client != null && this.m_client.Game != null) {

                Point cp = this.lsvMaplist.PointToClient(new Point(e.X, e.Y));
                ListViewItem dragToItem = this.lsvMaplist.GetItemAt(cp.X, cp.Y);

                this.m_blSettingAppendingSingleMap = true;

                if (insertingMap.Index >= 0) {
                    this.m_client.Game.SendMapListRemovePacket(insertingMap.Index);
                }

                if (dragToItem != null) {
                    this.WaitForSettingResponse("local.maplist.append");
                    this.m_client.Game.SendMapListInsertPacket(dragToItem.Index, insertingMap.MapFileName, insertingMap.Rounds);
                }
                else {
                    this.WaitForSettingResponse("local.maplist.append");
                    this.m_client.Game.SendMapListAppendPacket(insertingMap.MapFileName, insertingMap.Rounds);
                }

                this.m_client.Game.SendMapListSavePacket();
            }
        }

        private void lsvMaplist_ItemDrag(object sender, ItemDragEventArgs e) {
            ListViewItem lviSelected = (ListViewItem)e.Item;

            if (e.Button == MouseButtons.Left) {

                int rounds = 0;

                if (lviSelected != null && lviSelected.Tag != null && lviSelected.Tag != null && int.TryParse(lviSelected.SubItems[4].Text, out rounds) == true) {
                    ((PRoCon.Controls.ControlsEx.ListViewNF)sender).DoDragDrop(new MaplistEntry(lviSelected.Index, ((MaplistEntry)lviSelected.Tag).MapFileName, rounds), DragDropEffects.None | DragDropEffects.Scroll | DragDropEffects.Move);
                }
            }
        }

        private void lsvMaplist_SelectedIndexChanged(object sender, EventArgs e) {
            this.btnRemoveMap.Enabled = (this.lsvMaplist.SelectedItems.Count > 0);
        }

        private void lsvMaplist_DragLeave(object sender, EventArgs e) {
            this.lsvMaplist.Refresh();
        }

        private void lsvMaplist_MouseDoubleClick(object sender, MouseEventArgs e) {
            if (this.m_client != null && this.m_client.Game != null) {
                if (lsvMaplist.SelectedItems.Count > 0) {
                    this.m_client.Game.SendMapListNextLevelIndexPacket(lsvMaplist.SelectedItems[0].Index);
                    this.m_client.Game.SendAdminRunNextRoundPacket();
                }
            }
        }

        #endregion

        #region Maplist Pool Control Events

        private void lsvMaplistPool_ItemDrag(object sender, ItemDragEventArgs e) {
            ListViewItem lviSelected = (ListViewItem)e.Item;

            if (e.Button == MouseButtons.Left) {

                if (lviSelected != null && lviSelected.Tag != null && lviSelected.Tag != null) {

                    ((PRoCon.Controls.ControlsEx.ListViewNF)sender).DoDragDrop(new MaplistEntry(((MaplistEntry)lviSelected.Tag).MapFileName, (int)this.numRoundsSelect.Value), DragDropEffects.None | DragDropEffects.Scroll | DragDropEffects.Move);
                }
            }
        }

        private void lsvMaplistPool_SelectedIndexChanged(object sender, EventArgs e) {
            this.btnAddMap.Enabled = (this.lsvMaplistPool.SelectedItems.Count > 0);
        }

        #endregion

        #region Map Add/Remove Controls

        private void btnAddMap_Click(object sender, EventArgs e) {
            if (this.m_client != null && this.m_client.Game != null && this.lsvMaplistPool.SelectedItems.Count > 0) {

                if (this.lsvMaplistPool.SelectedItems[0].Tag != null) {
                    this.WaitForSettingResponse("local.maplist.append");
                    this.m_blSettingAppendingSingleMap = true;
                    this.m_client.Game.SendMapListAppendPacket(((MaplistEntry)this.lsvMaplistPool.SelectedItems[0].Tag).MapFileName, (int)this.numRoundsSelect.Value);

                    this.m_client.Game.SendMapListSavePacket();
                }
            }
        }

        private void btnRemoveMap_Click(object sender, EventArgs e) {
            if (this.m_client != null && this.m_client.Game != null && this.lsvMaplist.SelectedItems.Count > 0) {
                this.m_iReselectShufflingMapIndex = this.lsvMaplist.SelectedItems[0].Index;

                this.m_client.Game.SendMapListRemovePacket(this.lsvMaplist.SelectedItems[0].Index);

                this.m_client.Game.SendMapListSavePacket();
            }
        }

        #endregion

        private void lnkMaplistChangePlaylist_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.cboMaplistPlaylists.SelectedIndex >= 0) {

                CMap cmPlaylist = (CMap)this.cboMaplistPlaylists.SelectedItem;

                // Tell this.CurrentPlaylist the next playlist command back is a response to us setting it.
                this.m_blSettingNewPlaylist = true;
                this.m_client.Game.SendAdminSetPlaylistPacket(cmPlaylist.PlayList);
            }
        }

    }*/

    /*partial class uscMaplist {
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
            this.picMaplistAlterMaplist = new System.Windows.Forms.PictureBox();
            this.picMaplistChangePlaylist = new System.Windows.Forms.PictureBox();
            this.cboMaplistPlaylists = new System.Windows.Forms.ComboBox();
            this.lblMaplistCurrentPlayList = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.lnkMaplistChangePlaylist = new System.Windows.Forms.LinkLabel();
            this.lblMaplistCurrentMaplist = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.lblMaplistRounds = new System.Windows.Forms.Label();
            this.numRoundsSelect = new System.Windows.Forms.NumericUpDown();
            this.lblMaplistPool = new System.Windows.Forms.Label();
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.lblMaplistMustChangeWarning = new System.Windows.Forms.Label();
            this.panel3 = new System.Windows.Forms.Panel();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.pnlMaplistAddMap = new System.Windows.Forms.Panel();
            this.btnRemoveMap = new System.Windows.Forms.Button();
            this.btnAddMap = new System.Windows.Forms.Button();
            this.picMaplistAppendMap = new System.Windows.Forms.PictureBox();
            this.lsvMaplistPool = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colPoolGameType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPoolMapname = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colPoolMapFilename = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.lsvMaplist = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.colMapPosition = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colGameType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colMapname = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colMapFilename = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colMapRounds = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            ((System.ComponentModel.ISupportInitialize)(this.picMaplistAlterMaplist)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picMaplistChangePlaylist)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numRoundsSelect)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.panel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.pnlMaplistAddMap.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picMaplistAppendMap)).BeginInit();
            this.SuspendLayout();
            //
            // picMaplistAlterMaplist
            //
            this.picMaplistAlterMaplist.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picMaplistAlterMaplist.Location = new System.Drawing.Point(17, -8);
            this.picMaplistAlterMaplist.Name = "picMaplistAlterMaplist";
            this.picMaplistAlterMaplist.Size = new System.Drawing.Size(19, 18);
            this.picMaplistAlterMaplist.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picMaplistAlterMaplist.TabIndex = 91;
            this.picMaplistAlterMaplist.TabStop = false;
            //
            // picMaplistChangePlaylist
            //
            this.picMaplistChangePlaylist.Location = new System.Drawing.Point(9, 46);
            this.picMaplistChangePlaylist.Name = "picMaplistChangePlaylist";
            this.picMaplistChangePlaylist.Size = new System.Drawing.Size(16, 16);
            this.picMaplistChangePlaylist.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picMaplistChangePlaylist.TabIndex = 90;
            this.picMaplistChangePlaylist.TabStop = false;
            //
            // cboMaplistPlaylists
            //
            this.cboMaplistPlaylists.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawVariable;
            this.cboMaplistPlaylists.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboMaplistPlaylists.FormattingEnabled = true;
            this.cboMaplistPlaylists.Location = new System.Drawing.Point(31, 42);
            this.cboMaplistPlaylists.Name = "cboMaplistPlaylists";
            this.cboMaplistPlaylists.Size = new System.Drawing.Size(366, 24);
            this.cboMaplistPlaylists.TabIndex = 0;
            this.cboMaplistPlaylists.DrawItem += new System.Windows.Forms.DrawItemEventHandler(this.cboMaplistPlaylists_DrawItem);
            this.cboMaplistPlaylists.SelectedIndexChanged += new System.EventHandler(this.cboMaplistPlaylists_SelectedIndexChanged);
            //
            // lblMaplistCurrentPlayList
            //
            this.lblMaplistCurrentPlayList.AutoSize = true;
            this.lblMaplistCurrentPlayList.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblMaplistCurrentPlayList.Location = new System.Drawing.Point(30, 24);
            this.lblMaplistCurrentPlayList.Name = "lblMaplistCurrentPlayList";
            this.lblMaplistCurrentPlayList.Size = new System.Drawing.Size(94, 15);
            this.lblMaplistCurrentPlayList.TabIndex = 1;
            this.lblMaplistCurrentPlayList.Text = "Current play list";
            //
            // panel1
            //
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel1.Location = new System.Drawing.Point(39, 32);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(915, 1);
            this.panel1.TabIndex = 16;
            //
            // lnkMaplistChangePlaylist
            //
            this.lnkMaplistChangePlaylist.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkMaplistChangePlaylist.AutoSize = true;
            this.lnkMaplistChangePlaylist.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkMaplistChangePlaylist.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkMaplistChangePlaylist.Location = new System.Drawing.Point(404, 45);
            this.lnkMaplistChangePlaylist.Name = "lnkMaplistChangePlaylist";
            this.lnkMaplistChangePlaylist.Size = new System.Drawing.Size(88, 15);
            this.lnkMaplistChangePlaylist.TabIndex = 1;
            this.lnkMaplistChangePlaylist.TabStop = true;
            this.lnkMaplistChangePlaylist.Text = "Change playlist";
            this.lnkMaplistChangePlaylist.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkMaplistChangePlaylist_LinkClicked);
            //
            // lblMaplistCurrentMaplist
            //
            this.lblMaplistCurrentMaplist.AutoSize = true;
            this.lblMaplistCurrentMaplist.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblMaplistCurrentMaplist.Location = new System.Drawing.Point(495, 22);
            this.lblMaplistCurrentMaplist.Name = "lblMaplistCurrentMaplist";
            this.lblMaplistCurrentMaplist.Size = new System.Drawing.Size(93, 15);
            this.lblMaplistCurrentMaplist.TabIndex = 14;
            this.lblMaplistCurrentMaplist.Text = "Current maplist";
            //
            // panel2
            //
            this.panel2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel2.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel2.Controls.Add(this.picMaplistAlterMaplist);
            this.panel2.Location = new System.Drawing.Point(499, 30);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(455, 1);
            this.panel2.TabIndex = 17;
            //
            // lblMaplistRounds
            //
            this.lblMaplistRounds.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblMaplistRounds.AutoSize = true;
            this.lblMaplistRounds.Location = new System.Drawing.Point(21, 285);
            this.lblMaplistRounds.Name = "lblMaplistRounds";
            this.lblMaplistRounds.Size = new System.Drawing.Size(47, 15);
            this.lblMaplistRounds.TabIndex = 111;
            this.lblMaplistRounds.Text = "Rounds";
            //
            // numRoundsSelect
            //
            this.numRoundsSelect.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.numRoundsSelect.Location = new System.Drawing.Point(21, 303);
            this.numRoundsSelect.Name = "numRoundsSelect";
            this.numRoundsSelect.Size = new System.Drawing.Size(51, 23);
            this.numRoundsSelect.TabIndex = 110;
            //
            // lblMaplistPool
            //
            this.lblMaplistPool.AutoSize = true;
            this.lblMaplistPool.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblMaplistPool.Location = new System.Drawing.Point(28, 22);
            this.lblMaplistPool.Name = "lblMaplistPool";
            this.lblMaplistPool.Size = new System.Drawing.Size(89, 15);
            this.lblMaplistPool.TabIndex = 107;
            this.lblMaplistPool.Text = "Available maps";
            //
            // splitContainer1
            //
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.FixedPanel = System.Windows.Forms.FixedPanel.Panel1;
            this.splitContainer1.IsSplitterFixed = true;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            this.splitContainer1.Orientation = System.Windows.Forms.Orientation.Horizontal;
            //
            // splitContainer1.Panel1
            //
            this.splitContainer1.Panel1.Controls.Add(this.lblMaplistMustChangeWarning);
            this.splitContainer1.Panel1.Controls.Add(this.lblMaplistCurrentPlayList);
            this.splitContainer1.Panel1.Controls.Add(this.lnkMaplistChangePlaylist);
            this.splitContainer1.Panel1.Controls.Add(this.panel1);
            this.splitContainer1.Panel1.Controls.Add(this.cboMaplistPlaylists);
            this.splitContainer1.Panel1.Controls.Add(this.picMaplistChangePlaylist);
            //
            // splitContainer1.Panel2
            //
            this.splitContainer1.Panel2.Controls.Add(this.lblMaplistPool);
            this.splitContainer1.Panel2.Controls.Add(this.panel3);
            this.splitContainer1.Panel2.Controls.Add(this.lblMaplistCurrentMaplist);
            this.splitContainer1.Panel2.Controls.Add(this.pnlMaplistAddMap);
            this.splitContainer1.Panel2.Controls.Add(this.panel2);
            this.splitContainer1.Panel2.Controls.Add(this.lsvMaplistPool);
            this.splitContainer1.Panel2.Controls.Add(this.lsvMaplist);
            this.splitContainer1.Size = new System.Drawing.Size(1001, 852);
            this.splitContainer1.SplitterDistance = 90;
            this.splitContainer1.SplitterWidth = 5;
            this.splitContainer1.TabIndex = 28;
            //
            // lblMaplistMustChangeWarning
            //
            this.lblMaplistMustChangeWarning.AutoSize = true;
            this.lblMaplistMustChangeWarning.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold);
            this.lblMaplistMustChangeWarning.ForeColor = System.Drawing.Color.DarkOrange;
            this.lblMaplistMustChangeWarning.Location = new System.Drawing.Point(30, 69);
            this.lblMaplistMustChangeWarning.Name = "lblMaplistMustChangeWarning";
            this.lblMaplistMustChangeWarning.Size = new System.Drawing.Size(272, 15);
            this.lblMaplistMustChangeWarning.TabIndex = 113;
            this.lblMaplistMustChangeWarning.Text = "You must change the play list to add these maps";
            this.lblMaplistMustChangeWarning.Visible = false;
            //
            // panel3
            //
            this.panel3.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel3.Controls.Add(this.pictureBox1);
            this.panel3.Location = new System.Drawing.Point(31, 30);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(366, 1);
            this.panel3.TabIndex = 113;
            //
            // pictureBox1
            //
            this.pictureBox1.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.pictureBox1.Location = new System.Drawing.Point(-27, -8);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(19, 18);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 91;
            this.pictureBox1.TabStop = false;
            //
            // pnlMaplistAddMap
            //
            this.pnlMaplistAddMap.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)));
            this.pnlMaplistAddMap.Controls.Add(this.btnRemoveMap);
            this.pnlMaplistAddMap.Controls.Add(this.btnAddMap);
            this.pnlMaplistAddMap.Controls.Add(this.picMaplistAppendMap);
            this.pnlMaplistAddMap.Controls.Add(this.lblMaplistRounds);
            this.pnlMaplistAddMap.Controls.Add(this.numRoundsSelect);
            this.pnlMaplistAddMap.Location = new System.Drawing.Point(403, 46);
            this.pnlMaplistAddMap.Name = "pnlMaplistAddMap";
            this.pnlMaplistAddMap.Size = new System.Drawing.Size(89, 684);
            this.pnlMaplistAddMap.TabIndex = 112;
            //
            // btnRemoveMap
            //
            this.btnRemoveMap.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnRemoveMap.Enabled = false;
            this.btnRemoveMap.FlatAppearance.BorderSize = 0;
            this.btnRemoveMap.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRemoveMap.Location = new System.Drawing.Point(24, 332);
            this.btnRemoveMap.Name = "btnRemoveMap";
            this.btnRemoveMap.Size = new System.Drawing.Size(41, 27);
            this.btnRemoveMap.TabIndex = 114;
            this.btnRemoveMap.UseVisualStyleBackColor = true;
            this.btnRemoveMap.Click += new System.EventHandler(this.btnRemoveMap_Click);
            //
            // btnAddMap
            //
            this.btnAddMap.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnAddMap.Enabled = false;
            this.btnAddMap.FlatAppearance.BorderSize = 0;
            this.btnAddMap.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAddMap.Location = new System.Drawing.Point(24, 255);
            this.btnAddMap.Name = "btnAddMap";
            this.btnAddMap.Size = new System.Drawing.Size(41, 27);
            this.btnAddMap.TabIndex = 113;
            this.btnAddMap.UseVisualStyleBackColor = true;
            this.btnAddMap.Click += new System.EventHandler(this.btnAddMap_Click);
            //
            // picMaplistAppendMap
            //
            this.picMaplistAppendMap.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.picMaplistAppendMap.Location = new System.Drawing.Point(35, 233);
            this.picMaplistAppendMap.Name = "picMaplistAppendMap";
            this.picMaplistAppendMap.Size = new System.Drawing.Size(16, 16);
            this.picMaplistAppendMap.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picMaplistAppendMap.TabIndex = 112;
            this.picMaplistAppendMap.TabStop = false;
            //
            // lsvMaplistPool
            //
            this.lsvMaplistPool.AllowDrop = true;
            this.lsvMaplistPool.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)));
            this.lsvMaplistPool.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colPoolGameType,
            this.colPoolMapname,
            this.colPoolMapFilename});
            this.lsvMaplistPool.FullRowSelect = true;
            this.lsvMaplistPool.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.lsvMaplistPool.HideSelection = false;
            this.lsvMaplistPool.Location = new System.Drawing.Point(33, 46);
            this.lsvMaplistPool.MultiSelect = false;
            this.lsvMaplistPool.Name = "lsvMaplistPool";
            this.lsvMaplistPool.Size = new System.Drawing.Size(364, 684);
            this.lsvMaplistPool.TabIndex = 109;
            this.lsvMaplistPool.UseCompatibleStateImageBehavior = false;
            this.lsvMaplistPool.View = System.Windows.Forms.View.Details;
            this.lsvMaplistPool.ItemDrag += new System.Windows.Forms.ItemDragEventHandler(this.lsvMaplistPool_ItemDrag);
            this.lsvMaplistPool.SelectedIndexChanged += new System.EventHandler(this.lsvMaplistPool_SelectedIndexChanged);
            //
            // colPoolGameType
            //
            this.colPoolGameType.Text = "Gametype";
            //
            // colPoolMapname
            //
            this.colPoolMapname.Tag = "Name";
            this.colPoolMapname.Text = "Mapname";
            this.colPoolMapname.Width = 73;
            //
            // colPoolMapFilename
            //
            this.colPoolMapFilename.Text = "Filename";
            //
            // lsvMaplist
            //
            this.lsvMaplist.AllowDrop = true;
            this.lsvMaplist.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lsvMaplist.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colMapPosition,
            this.colGameType,
            this.colMapname,
            this.colMapFilename,
            this.colMapRounds});
            this.lsvMaplist.FullRowSelect = true;
            this.lsvMaplist.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.lsvMaplist.HideSelection = false;
            this.lsvMaplist.LabelEdit = true;
            this.lsvMaplist.Location = new System.Drawing.Point(498, 46);
            this.lsvMaplist.MultiSelect = false;
            this.lsvMaplist.Name = "lsvMaplist";
            this.lsvMaplist.Size = new System.Drawing.Size(456, 684);
            this.lsvMaplist.TabIndex = 2;
            this.lsvMaplist.UseCompatibleStateImageBehavior = false;
            this.lsvMaplist.View = System.Windows.Forms.View.Details;
            this.lsvMaplist.BeforeLabelEdit += new System.Windows.Forms.LabelEditEventHandler(this.lsvMaplist_BeforeLabelEdit);
            this.lsvMaplist.ItemDrag += new System.Windows.Forms.ItemDragEventHandler(this.lsvMaplist_ItemDrag);
            this.lsvMaplist.SelectedIndexChanged += new System.EventHandler(this.lsvMaplist_SelectedIndexChanged);
            this.lsvMaplist.DragDrop += new System.Windows.Forms.DragEventHandler(this.lsvMaplist_DragDrop);
            this.lsvMaplist.DragEnter += new System.Windows.Forms.DragEventHandler(this.lsvMaplist_DragEnter);
            this.lsvMaplist.DragOver += new System.Windows.Forms.DragEventHandler(this.lsvMaplist_DragOver);
            this.lsvMaplist.DragLeave += new System.EventHandler(this.lsvMaplist_DragLeave);
            this.lsvMaplist.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.lsvMaplist_MouseDoubleClick);
            //
            // colMapPosition
            //
            this.colMapPosition.Text = "#";
            //
            // colGameType
            //
            this.colGameType.Text = "Gametype";
            //
            // colMapname
            //
            this.colMapname.Tag = "Name";
            this.colMapname.Text = "Mapname";
            this.colMapname.Width = 73;
            //
            // colMapFilename
            //
            this.colMapFilename.Text = "Filename";
            //
            // colMapRounds
            //
            this.colMapRounds.Text = "Rounds";
            //
            // uscMaplist
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.splitContainer1);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscMaplist";
            this.Size = new System.Drawing.Size(1001, 852);
            ((System.ComponentModel.ISupportInitialize)(this.picMaplistAlterMaplist)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picMaplistChangePlaylist)).EndInit();
            this.panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.numRoundsSelect)).EndInit();
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel1.PerformLayout();
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.Panel2.PerformLayout();
            this.splitContainer1.ResumeLayout(false);
            this.panel3.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.pnlMaplistAddMap.ResumeLayout(false);
            this.pnlMaplistAddMap.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picMaplistAppendMap)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox picMaplistAlterMaplist;
        private System.Windows.Forms.PictureBox picMaplistChangePlaylist;
        private System.Windows.Forms.ComboBox cboMaplistPlaylists;
        private System.Windows.Forms.Label lblMaplistCurrentPlayList;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.LinkLabel lnkMaplistChangePlaylist;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvMaplist;
        private System.Windows.Forms.ColumnHeader colMapPosition;
        private System.Windows.Forms.ColumnHeader colMapname;
        private System.Windows.Forms.ColumnHeader colMapFilename;
        private System.Windows.Forms.ColumnHeader colMapRounds;
        private System.Windows.Forms.Label lblMaplistCurrentMaplist;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.ColumnHeader colGameType;
        private System.Windows.Forms.Label lblMaplistRounds;
        private System.Windows.Forms.NumericUpDown numRoundsSelect;
        private PRoCon.Controls.ControlsEx.ListViewNF lsvMaplistPool;
        private System.Windows.Forms.ColumnHeader colPoolGameType;
        private System.Windows.Forms.ColumnHeader colPoolMapname;
        private System.Windows.Forms.ColumnHeader colPoolMapFilename;
        private System.Windows.Forms.Label lblMaplistPool;
        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.Panel pnlMaplistAddMap;
        private System.Windows.Forms.Label lblMaplistMustChangeWarning;
        private System.Windows.Forms.PictureBox picMaplistAppendMap;
        private System.Windows.Forms.Button btnRemoveMap;
        private System.Windows.Forms.Button btnAddMap;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.PictureBox pictureBox1;
    }*/
}
