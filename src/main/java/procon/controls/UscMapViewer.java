package procon.controls;

public class UscMapViewer {

    /*public partial class uscMapViewer : UserControl {

        private PRoConClient m_prcClient;
        private CLocalization m_clocLanguage;

        private frmMain m_frmMain;

        //private string m_strCurrentMap;
        //private string m_strMapPath;

        public string MapPath {
            get;
            private set;
        }

        public MapImagePackDictionary MapImagePacks {
            get;
            private set;
        }

        public MapImagePack LoadedMapImagePack {
            get;
            private set;
        }

        private bool m_isMapSelected;
        public bool IsMapSelected {
            get {
                return this.m_isMapSelected;
            }
            set {
                this.m_isMapSelected = value;

                if (this.LoadedMapImagePack != null) {
                    if (this.FullyLoadMap == true) {
                        this.LoadedMapImagePack.LoadMap(this.LoadedMapImagePack.LoadedMapFileName, true);
                    }
                    else {
                        this.LoadedMapImagePack.UnloadMapImage();
                    }
                }

                this.uscBattlemap.FullyLoadMap = this.FullyLoadMap;
            }
        }

        private bool m_isMapLoaded;
        public bool IsMapLoaded {
            get {
                return this.m_isMapLoaded;
            }
            set {
                this.m_isMapLoaded = value;

                if (this.LoadedMapImagePack != null) {
                    if (this.FullyLoadMap == true) {
                        this.LoadedMapImagePack.LoadMap(this.LoadedMapImagePack.LoadedMapFileName, true);
                    }
                    else {
                        this.LoadedMapImagePack.UnloadMapImage();
                    }
                }

                this.uscBattlemap.FullyLoadMap = this.FullyLoadMap;
            }
        }

        public bool FullyLoadMap {
            get {
                return this.IsMapSelected && this.IsMapLoaded;
            }
        }

        public uscMapViewer() {
            InitializeComponent();

            this.uscBattlemap.CreateMapZone += new BattlemapView.CreateMapZoneHandler(uscBattlemap_CreateMapZone);
            this.uscBattlemap.DeleteMapZone += new BattlemapView.DeleteMapZoneHandler(uscBattlemap_DeleteMapZone);
            this.uscBattlemap.ModifyMapZone += new BattlemapView.ModifyMapZoneHandler(uscBattlemap_ModifyMapZone);

            this.uscBattlemap.MapZoneSelected += new BattlemapView.MapZoneSelectedHandler(uscBattlemap_MapZoneSelected);
            //this.m_strCurrentMap = String.Empty;
            //this.m_strMapPath = Path.Combine(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "Media"), "Maps");

            //this.LoadMapImagePacks();
        }

        private void uscMapViewer_Load(object sender, EventArgs e) {
            //this.LoadMapImagePacks();

            this.cboImagePacks.Items.Clear();
            if (this.m_prcClient != null) {

                foreach (MapImagePack mipPack in this.MapImagePacks) {
                    this.cboImagePacks.Items.Add(mipPack);
                }

                this.cboImagePacks.SelectedItem = this.LoadedMapImagePack;

                foreach (MapZoneDrawing zone in this.m_prcClient.MapGeometry.MapZones) {
                    this.MapZones_MapZoneAdded(zone);
                }

                if (this.m_prcClient.SV_Variables.Contains("ZONE_TAG_LIST") == true) {
                    this.CheckTagsVariable(this.m_prcClient.SV_Variables["ZONE_TAG_LIST"]);
                }
                else if (this.m_prcClient.Variables.Contains("ZONE_TAG_LIST") == true) {
                    this.CheckTagsVariable(this.m_prcClient.Variables["ZONE_TAG_LIST"]);
                }

                this.IsMapLoaded = true;
            }
        }

        private void LoadMapImagePacks() {

            this.MapImagePacks.Clear();

            if (Directory.Exists(this.MapPath) == true) {
                string[] a_strMapImagePacks = Directory.GetDirectories(this.MapPath);

                foreach (string strMapImagePack in a_strMapImagePacks) {
                    if (File.Exists(Path.Combine(Path.Combine(this.MapPath, strMapImagePack), "data.map")) == true) {
                        this.MapImagePacks.Add(new MapImagePack(Path.Combine(this.MapPath, strMapImagePack), new CLocalization(Path.Combine(Path.Combine(this.MapPath, strMapImagePack), "data.map"), "data.map")));
                    }
                }
            }
        }

        public void Initalize(frmMain frmMain) {
            this.m_frmMain = frmMain;

            this.tsbPointer.Image = this.m_frmMain.iglIcons.Images["cursor.png"];
            this.tsbCalibration.Image = this.m_frmMain.iglIcons.Images["shape_square_edit.png"];
            this.btnCounterClockwise.Image = this.m_frmMain.iglIcons.Images["shape_rotate_anticlockwise.png"];
            this.btnClockwise.Image = this.m_frmMain.iglIcons.Images["shape_rotate_clockwise.png"];

            this.tsbMeasuringTool.Image = this.m_frmMain.iglIcons.Images["layer-shape-line.png"];

            this.tsbTeamColours.Image = this.m_frmMain.iglIcons.Images["block.png"];

            this.tsbMapZonesTools.Image = this.m_frmMain.iglIcons.Images["layers-ungroup.png"];

            this.btnAddTag.Image = this.m_frmMain.iglIcons.Images["add.png"];
        }

        public void SetLocalization(CLocalization clocLanguage) {
            if ((this.m_clocLanguage = clocLanguage) != null) {
                this.uscBattlemap.SetLocalization(clocLanguage);

                this.tsbPointer.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.tsbPointer");
                // Set the mouse over text for the kill colour
                this.uscBattlemap.KillColours = this.uscBattlemap.KillColours;

                this.tsbMeasuringTool.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.tsbMeasuringTool");
                this.tsbMapZonesTools.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.tsbMapZonesTools");
                this.lblImagePack.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.lblImagePack");
                this.tsbCalibration.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.tsbCalibration");

                this.grpZoneTags.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpZoneTags.Title");
                this.lblTagsHelp.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpZoneTags.lblTagsHelp");

                this.grpMarkerCollection.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpMarkerCollection.Title");
                this.lblTrackPlayersList.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpMarkerCollection.lblTrackPlayersList");
                this.btnClearMarkers.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpMarkerCollection.btnClearMarkers");

                this.grpOffset.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpOffset.Title");
                this.chkLockAxis.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpOffset.chkLockAxis");

                this.grpZoom.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpZoom.Title");
                this.grpRotation.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.grpRotation.Title");

                this.btnSaveCalibration.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.btnSaveCalibration");

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
            }
        }

        private void m_prcClient_GameTypeDiscovered(PRoConClient sender) {

            this.MapImagePacks = new MapImagePackDictionary();
            this.MapPath = Path.Combine(Path.Combine(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "Media"), sender.GameType), "Maps");
            this.LoadMapImagePacks();

            if (this.MapImagePacks.Count > 0) {
                this.LoadedMapImagePack = this.MapImagePacks[0];
            }

            this.uscBattlemap.SetLocalization(this.m_prcClient.Language);

            //this.m_prcClient.ServerInfo += new FrostbiteConnection.ServerInfoHandler(m_prcClient_ServerInfo);
            this.m_prcClient.PlayerKilled += new PRoConClient.PlayerKilledHandler(m_prcClient_PlayerKilled);
            this.m_prcClient.Game.PlayerLeft += new FrostbiteClient.PlayerLeaveHandler(m_prcClient_PlayerLeft);
            this.m_prcClient.Game.PlayerJoin += new FrostbiteClient.PlayerEventHandler(m_prcClient_PlayerJoin);
            this.m_prcClient.Game.ListPlayers += new FrostbiteClient.ListPlayersHandler(m_prcClient_ListPlayers);
            this.m_prcClient.Game.LoadingLevel += new FrostbiteClient.LoadingLevelHandler(m_prcClient_LoadingLevel);
            //this.m_prcClient.MapGeometry.MapLoaded += new MapGeometry.MapLoadedHandler(MapGeometry_MapLoaded);

            this.uscBattlemap.MapDetails = new List<CMap>(this.m_prcClient.MapListPool);

            this.cboPlayers.Items.Clear();
            foreach (CPlayerInfo cpiPlayer in this.m_prcClient.PlayerList) {
                if (this.cboPlayers.Items.Contains(cpiPlayer.SoldierName) == false) {
                    this.cboPlayers.Items.Add(cpiPlayer.SoldierName);
                }
            }

            this.m_prcClient.Game.ServerInfo += new FrostbiteClient.ServerInfoHandler(m_prcClient_ServerInfo);

            this.m_prcClient.Variables.VariableAdded += new PRoCon.Core.Variables.VariableDictionary.PlayerAlteredHandler(Variables_VariableAdded);
            this.m_prcClient.Variables.VariableRemoved += new PRoCon.Core.Variables.VariableDictionary.PlayerAlteredHandler(Variables_VariableRemoved);
            this.m_prcClient.Variables.VariableUpdated += new PRoCon.Core.Variables.VariableDictionary.PlayerAlteredHandler(Variables_VariableUpdated);
            this.m_prcClient.SV_Variables.VariableRemoved += new PRoCon.Core.Variables.VariableDictionary.PlayerAlteredHandler(SV_Variables_VariableRemoved);
            this.m_prcClient.SV_Variables.VariableUpdated += new PRoCon.Core.Variables.VariableDictionary.PlayerAlteredHandler(SV_Variables_VariableUpdated);
            this.m_prcClient.SV_Variables.VariableAdded += new PRoCon.Core.Variables.VariableDictionary.PlayerAlteredHandler(SV_Variables_VariableAdded);

            this.m_prcClient.MapGeometry.MapZones.MapZoneAdded += new MapZoneDictionary.MapZoneAlteredHandler(MapZones_MapZoneAdded);
            this.m_prcClient.MapGeometry.MapZones.MapZoneRemoved += new MapZoneDictionary.MapZoneAlteredHandler(MapZones_MapZoneRemoved);
            this.m_prcClient.MapGeometry.MapZones.MapZoneChanged += new MapZoneDictionary.MapZoneAlteredHandler(MapZones_MapZoneChanged);

            this.m_prcClient.MapZoneDeleted += new PRoConClient.MapZoneEditedHandler(m_prcClient_MapZoneDeleted);
            this.m_prcClient.MapZoneCreated += new PRoConClient.MapZoneEditedHandler(m_prcClient_MapZoneCreated);
            this.m_prcClient.MapZoneModified += new PRoConClient.MapZoneEditedHandler(m_prcClient_MapZoneModified);

            this.m_prcClient.ListMapZones += new PRoConClient.ListMapZonesHandler(m_prcClient_ListMapZones);

            this.m_prcClient.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(m_prcClient_ProconPrivileges);

        }

        private void m_prcClient_ServerInfo(FrostbiteClient sender, CServerInfo csiServerInfo) {

            if (this.LoadedMapImagePack != null) {

                if (this.LoadedMapImagePack.LoadedMapFileName.Length == 0 && String.Compare(this.LoadedMapImagePack.LoadedMapFileName, csiServerInfo.Map, true) != 0) {
                    // Load new map.
                    //this.CurrentMapFilename = csiServerInfo.Map.ToLower();

                    if (this.LoadedMapImagePack != null) {
                        this.LoadedMapImagePack.LoadMap(csiServerInfo.Map.ToLower(), this.FullyLoadMap);

                        this.MapLoaded(csiServerInfo.Map.ToLower());
                    }

                }
            }

            //this.CurrentMapFilename = csiServerInfo.Map.ToLower();
        }

        private void m_prcClient_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {
            this.m_prcClient.SendProconBattlemapListZonesPacket();
            this.m_prcClient.SendGetProconVarsPacket("ZONE_TAG_LIST");

            if ((this.tsbMapZonesTools.Enabled = spPrivs.CanEditMapZones) == false) {
                this.tsbPointer.Checked = true;
            }
        }

        private void m_prcClient_ListPlayers(FrostbiteClient sender, List<CPlayerInfo> lstPlayers, CPlayerSubset cpsSubset) {
            if (cpsSubset.Subset == CPlayerSubset.PlayerSubsetType.All) {

                string strSelectedName = (string)this.cboPlayers.SelectedItem;

                this.cboPlayers.Items.Clear();
                foreach (CPlayerInfo cpiPlayer in this.m_prcClient.PlayerList) {
                    if (this.cboPlayers.Items.Contains(cpiPlayer.SoldierName) == false) {
                        this.cboPlayers.Items.Add(cpiPlayer.SoldierName);
                    }
                }

                if (strSelectedName != null && this.cboPlayers.Items.Contains(strSelectedName) == true) {
                    this.cboPlayers.SelectedItem = strSelectedName;
                }

            }
        }

        private void m_prcClient_PlayerJoin(FrostbiteClient sender, string playerName) {
            if (this.cboPlayers.Items.Contains(playerName) == false) {
                this.cboPlayers.Items.Add(playerName);
            }
        }

        private void m_prcClient_PlayerLeft(FrostbiteClient sender, string playerName, CPlayerInfo cpiPlayer) {
            if (this.cboPlayers.Items.Contains(playerName) == true) {

                if (String.Compare((string)this.cboPlayers.SelectedItem, playerName) == 0) {
                    this.uscBattlemap.CalibrationMarkers.Clear();
                }

                this.cboPlayers.Items.Remove(playerName);
            }
        }

        private void m_prcClient_PlayerKilled(PRoConClient sender, Kill kKillerVictimDetails) {
            this.uscBattlemap.AddKill(kKillerVictimDetails);

            // If suicide and monitoring for suicides for this player.
            if (this.spltCalibration.Panel2Collapsed == false && String.Compare(kKillerVictimDetails.Killer.SoldierName, kKillerVictimDetails.Victim.SoldierName) == 0 && String.Compare((string)this.cboPlayers.SelectedItem, kKillerVictimDetails.Killer.SoldierName) == 0) {
                this.uscBattlemap.CalibrationMarkers.Add(kKillerVictimDetails);
            }
        }

        private CMap GetMap(string strFileName) {
            CMap returnMap = new CMap(String.Empty, strFileName, String.Empty, strFileName, 0);

            foreach (CMap map in this.m_prcClient.MapListPool) {
                if (String.Compare(map.FileName, strFileName, true) == 0) {
                    returnMap = map;
                    break;
                }
            }

            return returnMap;
        }

        private void m_prcClient_LoadingLevel(FrostbiteClient sender, string mapFileName, int roundsPlayed, int roundsTotal) {
            this.uscBattlemap.AddRoundChange(this.GetMap(mapFileName));
        }

        private void SetMapDetails() {
            if (this.LoadedMapImagePack != null) {

                this.btnSaveCalibration.Enabled = this.grpMarkerCollection.Enabled = this.grpOffset.Enabled = this.grpZoom.Enabled = this.grpRotation.Enabled = !this.LoadedMapImagePack.Readonly;

                this.chkLockAxis.Checked = (this.LoadedMapImagePack.MapOrigin.X == this.LoadedMapImagePack.MapOrigin.Y);

                if (this.LoadedMapImagePack.MapOrigin.X < this.trkOriginX.Minimum) {
                    this.trkOriginX.Value = this.trkOriginX.Minimum;
                }
                else if (this.LoadedMapImagePack.MapOrigin.X > this.trkOriginX.Maximum) {
                    this.trkOriginX.Value = this.trkOriginX.Maximum;
                }
                else {
                    this.trkOriginX.Value = this.LoadedMapImagePack.MapOrigin.X;
                }

                if (this.LoadedMapImagePack.MapOrigin.Y < this.trkOriginY.Minimum) {
                    this.trkOriginY.Value = this.trkOriginY.Minimum;
                }
                else if (this.LoadedMapImagePack.MapOrigin.Y > this.trkOriginY.Maximum) {
                    this.trkOriginY.Value = this.trkOriginY.Maximum;
                }
                else {
                    this.trkOriginY.Value = this.LoadedMapImagePack.MapOrigin.Y;
                }

                // 0.1 = 1, 0.2 = 2
                int iTrackbarScale = (int)(this.LoadedMapImagePack.MapScale.X * 100.0F);
                if (iTrackbarScale < this.trkZoomX.Minimum) {
                    this.trkZoomX.Value = this.trkZoomX.Minimum;
                }
                else if (iTrackbarScale > this.trkZoomX.Maximum) {
                    this.trkZoomX.Value = this.trkZoomX.Maximum;
                }
                else {
                    this.trkZoomX.Value = iTrackbarScale;
                }

                iTrackbarScale = (int)(this.LoadedMapImagePack.MapScale.Y * 100.0F);
                if (iTrackbarScale < this.trkZoomY.Minimum) {
                    this.trkZoomY.Value = this.trkZoomY.Minimum;
                }
                else if (iTrackbarScale > this.trkZoomY.Maximum) {
                    this.trkZoomY.Value = this.trkZoomY.Maximum;
                }
                else {
                    this.trkZoomY.Value = iTrackbarScale;
                }

                if (this.LoadedMapImagePack.MapRotation < 0.0F) {
                    this.numRotation.Value = 0;
                }
                else {
                    this.numRotation.Value = (decimal)(this.LoadedMapImagePack.MapRotation % 360.0F);
                }

                //this.lblRotation.Text = String.Format("{0:0}°", this.LoadedMapImagePack.MapRotation);

                if (this.LoadedMapImagePack.MapImage != null) {
                    //this.ovMapView.Map = new Bitmap(this.LoadedMapImagePack.MapImage);
                    this.uscBattlemap.Visible = true;
                    this.uscBattlemap.FitOnScreen();
                }
                else {
                    this.uscBattlemap.Visible = false;
                }

                if (this.LoadedMapImagePack.Readonly == true) {
                    this.lblMapPackFilePath.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.lblMapPackFilePath.ReadOnly");
                }
                else {
                    this.lblMapPackFilePath.Text = this.LoadedMapImagePack.MapImagePackDataFile.FilePath;
                }

                this.uscBattlemap.CalibrationMarkers.Clear();
            }
        }

        private void MapLoaded(string strMapName) {

            this.uscBattlemap.LoadedMapImagePack = this.LoadedMapImagePack;

            if (this.LoadedMapImagePack != null) {
                this.LoadedMapImagePack.MapLoaded += new MapImagePack.MapLoadedHandler(LoadedMapImagePack_MapLoaded);
            }

            this.SetMapDetails();
        }

        private void LoadedMapImagePack_MapLoaded() {
            this.SetMapDetails();
        }

        private void cboImagePacks_SelectedIndexChanged(object sender, EventArgs e) {

            if (cboImagePacks.SelectedItem != null) {
                this.SetMapPack((MapImagePack)cboImagePacks.SelectedItem);
            }
        }

        private void uscMapViewer_Resize(object sender, EventArgs e) {
            this.Invalidate();
        }

        public void SetMapPack(MapImagePack mpiPack) {

            string strCurrentMapFileName = String.Empty;

            if (this.LoadedMapImagePack != null) {
                strCurrentMapFileName = this.LoadedMapImagePack.LoadedMapFileName;
            }

            this.LoadedMapImagePack = mpiPack;

            if (strCurrentMapFileName.Length > 0) {
                this.LoadedMapImagePack.LoadMap(strCurrentMapFileName, this.FullyLoadMap);

                this.MapLoaded(strCurrentMapFileName);

                //if (this.MapLoaded != null) {
                //    FrostbiteConnection.RaiseEvent(this.MapLoaded.GetInvocationList(), strCurrentMapFileName);
                //}
            }
        }

        private void btnCounterClockwise_Click(object sender, EventArgs e) {

            this.numRotation.Value = (decimal)((this.LoadedMapImagePack.MapRotation + 90.0F) % 360.0F);

            //if (this.LoadedMapImagePack != null) {
            //    this.LoadedMapImagePack.MapRotation = (this.LoadedMapImagePack.MapRotation + 90.0F) % 360.0F;
            //    this.lblRotation.Text = String.Format("{0:0}°", this.LoadedMapImagePack.MapRotation);
            //}
        }

        private void btnClockwise_Click(object sender, EventArgs e) {

            this.numRotation.Value = (decimal)((this.LoadedMapImagePack.MapRotation - 90.0F + 360.0F) % 360.0F);

            //if (this.LoadedMapImagePack != null) {
            //    this.LoadedMapImagePack.MapRotation = (this.LoadedMapImagePack.MapRotation - 9.0F + 360.0F) % 360.0F;
            //    this.lblRotation.Text = String.Format("{0:0}°", this.LoadedMapImagePack.MapRotation);
            //}
        }

        private void numRotation_ValueChanged(object sender, EventArgs e) {

            if (this.numRotation.Value < 0) {
                this.numRotation.Value = 359;
            }
            else if (this.numRotation.Value == 360) {
                this.numRotation.Value = 0;
            }
            else if (this.LoadedMapImagePack != null) {
                this.LoadedMapImagePack.MapRotation = (float)this.numRotation.Value;
            }
        }

        private void chkLockAxis_CheckedChanged(object sender, EventArgs e) {
            this.trkOriginY.Enabled = !this.chkLockAxis.Checked;
            this.trkOriginY.Value = this.trkOriginX.Value;
        }

        private void trkOriginX_ValueChanged(object sender, EventArgs e) {
            if (this.LoadedMapImagePack != null) {

                if (this.chkLockAxis.Checked == true) {
                    this.trkOriginY.Value = this.trkOriginX.Value;
                }

                this.lblOffsetXValue.Text = String.Format("{0}", this.trkOriginX.Value);

                this.LoadedMapImagePack.MapOrigin = new Point(this.trkOriginX.Value, this.LoadedMapImagePack.MapOrigin.Y);
            }
        }

        private void trkOriginY_ValueChanged(object sender, EventArgs e) {
            if (this.LoadedMapImagePack != null) {
                this.lblOffsetYValue.Text = String.Format("{0}", this.trkOriginY.Value);

                this.LoadedMapImagePack.MapOrigin = new Point(this.LoadedMapImagePack.MapOrigin.X, this.trkOriginY.Value);
            }
        }

        private void trkZoom_ValueChanged(object sender, EventArgs e) {
            if (this.LoadedMapImagePack != null) {
                this.lblZoomXValue.Text = String.Format("{0:0.00}x", (float)this.trkZoomX.Value / 100.0F);

                this.LoadedMapImagePack.MapScale = new PointF(this.trkZoomX.Value / 100.0F, this.LoadedMapImagePack.MapScale.Y);
                // this.LoadedMapImagePack.MapScale = (float)this.trkZoomX.Value / 10.0F;
            }
        }


        private void trkZoomY_ValueChanged(object sender, EventArgs e) {
            if (this.LoadedMapImagePack != null) {
                this.lblZoomYValue.Text = String.Format("{0:0.00}x", (float)this.trkZoomY.Value / 100.0F);

                this.LoadedMapImagePack.MapScale = new PointF(this.LoadedMapImagePack.MapScale.X, this.trkZoomY.Value / 100.0F);
                // this.LoadedMapImagePack.MapScale = (float)this.trkZoomX.Value / 10.0F;
            }
        }

        private void btnClearMarkers_Click(object sender, EventArgs e) {
            this.uscBattlemap.CalibrationMarkers.Clear();
        }

        private void btnSaveCalibration_Click(object sender, EventArgs e) {
            if (this.LoadedMapImagePack != null) {

                string strMapFileName = this.LoadedMapImagePack.LoadedMapFileName;

                this.LoadedMapImagePack.MapImagePackDataFile.SetLocalized(String.Format("{0}.Translate.X", strMapFileName), this.LoadedMapImagePack.MapOrigin.X.ToString(CultureInfo.InvariantCulture.NumberFormat));
                this.LoadedMapImagePack.MapImagePackDataFile.SetLocalized(String.Format("{0}.Translate.Y", strMapFileName), this.LoadedMapImagePack.MapOrigin.Y.ToString(CultureInfo.InvariantCulture.NumberFormat));

                this.LoadedMapImagePack.MapImagePackDataFile.SetLocalized(String.Format("{0}.ScaleX", strMapFileName), this.LoadedMapImagePack.MapScale.X.ToString(CultureInfo.InvariantCulture.NumberFormat));
                this.LoadedMapImagePack.MapImagePackDataFile.SetLocalized(String.Format("{0}.ScaleY", strMapFileName), this.LoadedMapImagePack.MapScale.Y.ToString(CultureInfo.InvariantCulture.NumberFormat));
                this.LoadedMapImagePack.MapImagePackDataFile.SetLocalized(String.Format("{0}.Rotation", strMapFileName), this.LoadedMapImagePack.MapRotation.ToString(CultureInfo.InvariantCulture.NumberFormat));

                // REMOVE AFTER LEGACY UPDATE
                // this.LoadedMapImagePack.MapImagePackDataFile.SetLocalized(String.Format("{0}.Legacy", strMapFileName), bool.FalseString);
                // END REMOVE AFTER LEGACY UPDATE
            }
        }

        private void tsbCalibration_CheckedChanged(object sender, EventArgs e) {
            this.spltCalibration.Panel2Collapsed = !this.tsbCalibration.Checked;
            this.uscBattlemap.DisplayCalibrationGrid = this.tsbCalibration.Checked;
        }

        private void DeselectTools(ToolStripButton tlsButton) {
            if (this.tsbPointer != tlsButton) this.tsbPointer.Checked = false;
            if (this.tsbMeasuringTool != tlsButton) this.tsbMeasuringTool.Checked = false;
            if (this.tsbMapZonesTools != tlsButton) this.tsbMapZonesTools.Checked = false;

            this.spltZoneTags.Panel2Collapsed = true;
        }

        private void tsbPointer_CheckedChanged(object sender, EventArgs e) {
            if (this.tsbPointer.Checked == true) {
                this.DeselectTools(this.tsbPointer);
                this.uscBattlemap.SelectedTool = BattlemapViewTools.Pointer;
            }
        }

        private void tsbDistanceTool_CheckedChanged(object sender, EventArgs e) {
            if (this.tsbMeasuringTool.Checked == true) {
                this.DeselectTools(this.tsbMeasuringTool);
                this.uscBattlemap.SelectedTool = BattlemapViewTools.Measuring;
            }
        }

        private void tsbMapZonesTools_CheckedChanged(object sender, EventArgs e) {
            if (this.tsbMapZonesTools.Checked == true) {
                this.DeselectTools(this.tsbMapZonesTools);
                this.uscBattlemap.SelectedTool = BattlemapViewTools.Zones;
                this.spltZoneTags.Panel2Collapsed = false;
            }
        }

        private void tsbTeamColours_Click(object sender, EventArgs e) {

            if (this.uscBattlemap.KillColours == KillDisplayColours.EnemyColours) {
                this.uscBattlemap.KillColours = KillDisplayColours.TeamColours;
            }
            else {
                this.uscBattlemap.KillColours = KillDisplayColours.EnemyColours;
            }
        }

        private void uscBattlemap_KillColoursChanged(KillDisplayColours newKillColour) {
            if (newKillColour == KillDisplayColours.EnemyColours) {
                this.tsbTeamColours.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.tsbTeamColours.TeamColours");
                // Change to Team Colours image
            }
            else if (newKillColour == KillDisplayColours.TeamColours) {
                this.tsbTeamColours.Text = this.m_clocLanguage.GetLocalized("uscMapViewer.tsbTeamColours.EnemyColours");
                // Change to Enemy Colours image
            }
        }

        #region Map Zones

        private void CheckTagsVariable(Variable item) {
            if (String.Compare(item.Name, "ZONE_TAG_LIST", true) == 0) {
                ZoneTagList tags = new ZoneTagList(item.Value);

                this.cboTagList.Items.Clear();

                foreach (string zoneTag in tags) {
                    this.cboTagList.Items.Add(zoneTag);
                }
            }
        }

        private void Variables_VariableAdded(Variable item) {
            if (this.m_prcClient.IsPRoConConnection == false) {
                this.CheckTagsVariable(item);
            }
        }

        private void Variables_VariableUpdated(Variable item) {
            if (this.m_prcClient.IsPRoConConnection == false) {
                this.CheckTagsVariable(item);
            }
        }

        private void Variables_VariableRemoved(Variable item) {
            if (this.m_prcClient.IsPRoConConnection == false) {
                this.CheckTagsVariable(item);
            }
        }

        private void SV_Variables_VariableAdded(Variable item) {
            this.CheckTagsVariable(item);
        }

        private void SV_Variables_VariableUpdated(Variable item) {
            this.CheckTagsVariable(item);
        }

        private void SV_Variables_VariableRemoved(Variable item) {
            this.CheckTagsVariable(item);
        }

        private void btnAddTag_Click(object sender, EventArgs e) {

            ZoneTagList tags = new ZoneTagList(this.txtTagList.Text);

            if (this.cboTagList.SelectedItem != null && tags.Contains((string)this.cboTagList.SelectedItem) == false) {
                tags.Add((string)this.cboTagList.SelectedItem);
                this.txtTagList.Text = tags.ToString();
            }
        }

        private bool m_isModifyingTags;
        private void txtTagList_TextChanged(object sender, EventArgs e) {

            if (this.m_isModifyingTags == false && this.m_prcClient != null && this.txtTagList.Tag != null && this.txtTagList.Tag is MapZoneDrawing) {

                this.m_isModifyingTags = true;

                MapZoneDrawing selectedZone = (MapZoneDrawing)this.txtTagList.Tag;

                if (this.m_prcClient.IsPRoConConnection == true) {
                    // Send to layer

                    this.m_prcClient.SendProconBattlemapModifyZoneTagsPacket(selectedZone.UID, this.txtTagList.Text);
                }
                else {

                    if (this.m_prcClient.MapGeometry.MapZones.Contains(selectedZone.UID) == true) {

                        this.m_prcClient.MapGeometry.MapZones[selectedZone.UID].Tags.FromString(this.txtTagList.Text);
                    }
                }

                this.m_isModifyingTags = false;
            }
        }

        private void uscBattlemap_MapZoneSelected(MapZoneDrawing zone) {
            if (zone != null) {
                this.m_isModifyingTags = true;

                this.txtTagList.Tag = zone;
                this.txtTagList.Text = zone.Tags.ToString();
                this.spltZoneTags.Panel2.Enabled = true;

                this.m_isModifyingTags = false;
            }
            else {
                this.spltZoneTags.Panel2.Enabled = false;
            }
        }

        private void uscBattlemap_ModifyMapZone(string strUid, Point3D[] zonePoints) {
            if (this.m_prcClient != null) {

                if (this.m_prcClient.IsPRoConConnection == true) {
                    //List<string> list = new List<string>() { "procon.battlemap.modifyZonePoints", strUid };
                    //list.Add(zonePoints.Length.ToString());
                    //list.AddRange(Point3D.ToStringList(zonePoints));
                    //this.m_prcClient.SendRequest(list);

                    this.m_prcClient.SendProconBattlemapModifyZonePointsPacket(strUid, zonePoints);
                }
                else {
                    this.m_prcClient.MapGeometry.MapZones.ModifyMapZonePoints(strUid, zonePoints);
                }
            }
        }

        private void uscBattlemap_DeleteMapZone(string strUid) {
            if (this.m_prcClient != null) {

                if (this.m_prcClient.IsPRoConConnection == true) {
                    this.m_prcClient.SendProconBattlemapDeleteZonePacket(strUid);
                }
                else {
                    if (this.m_prcClient.MapGeometry.MapZones.Contains(strUid) == true) {
                        this.m_prcClient.MapGeometry.MapZones.Remove(strUid);
                    }
                }

            }
        }

        private void uscBattlemap_CreateMapZone(string mapFileName, Point3D[] zonePoints) {
            if (this.m_prcClient != null) {

                if (this.m_prcClient.IsPRoConConnection == true) {
                    // Create it on the layer..
                    //List<string> list = new List<string>() { "procon.battlemap.createZone", mapFileName };
                    //list.Add(zonePoints.Length.ToString());
                    //list.AddRange(Point3D.ToStringList(zonePoints));
                    this.m_prcClient.SendProconBattlemapCreateZonePacket(mapFileName, zonePoints);
                }
                else {
                    // Create it locally.
                    this.m_prcClient.MapGeometry.MapZones.CreateMapZone(mapFileName, zonePoints);
                }
            }
        }

        private void MapZones_MapZoneChanged(MapZoneDrawing item) {
            if (this.m_isModifyingTags == false) {
                this.m_isModifyingTags = true;

                this.uscBattlemap.SetMapZonePoints(item);
                this.uscBattlemap.SetMapZoneTags(item);

                if (this.txtTagList.Tag != null && String.Compare(item.UID, ((MapZoneDrawing)this.txtTagList.Tag).UID) == 0) {
                    this.txtTagList.Text = item.Tags.ToString();
                    this.txtTagList.Select(this.txtTagList.Text.Length, 0);
                }

                this.m_isModifyingTags = false;
            }
        }

        private void MapZones_MapZoneAdded(MapZoneDrawing item) {
            this.uscBattlemap.AddMapZone(item);
        }

        private void MapZones_MapZoneRemoved(MapZoneDrawing item) {
            this.uscBattlemap.RemoveMapZone(item);
        }

        private void m_prcClient_ListMapZones(PRoConClient sender, List<MapZoneDrawing> zones) {
            foreach (MapZoneDrawing zone in zones) {
                this.uscBattlemap.AddMapZone(zone);
            }
        }

        void m_prcClient_MapZoneModified(PRoConClient sender, MapZoneDrawing zone) {
            this.MapZones_MapZoneChanged(zone);
        }

        void m_prcClient_MapZoneCreated(PRoConClient sender, MapZoneDrawing zone) {
            this.MapZones_MapZoneAdded(zone);
        }

        void m_prcClient_MapZoneDeleted(PRoConClient sender, MapZoneDrawing zone) {
            this.MapZones_MapZoneRemoved(zone);
        }

        #endregion

    }*/

    /*partial class uscMapViewer {
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(uscMapViewer));
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.tsbPointer = new System.Windows.Forms.ToolStripButton();
            this.tsbTeamColours = new System.Windows.Forms.ToolStripButton();
            this.tsbMeasuringTool = new System.Windows.Forms.ToolStripButton();
            this.tsbMapZonesTools = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.lblImagePack = new System.Windows.Forms.ToolStripLabel();
            this.cboImagePacks = new System.Windows.Forms.ToolStripComboBox();
            this.tsbCalibration = new System.Windows.Forms.ToolStripButton();
            this.spltCalibration = new System.Windows.Forms.SplitContainer();
            this.spltZoneTags = new System.Windows.Forms.SplitContainer();
            this.grpZoneTags = new System.Windows.Forms.GroupBox();
            this.lblTagsHelp = new System.Windows.Forms.Label();
            this.btnAddTag = new System.Windows.Forms.Button();
            this.cboTagList = new System.Windows.Forms.ComboBox();
            this.txtTagList = new System.Windows.Forms.TextBox();
            this.lblMapPackFilePath = new System.Windows.Forms.Label();
            this.grpMarkerCollection = new System.Windows.Forms.GroupBox();
            this.btnClearMarkers = new System.Windows.Forms.Button();
            this.lblTrackPlayersList = new System.Windows.Forms.Label();
            this.cboPlayers = new System.Windows.Forms.ComboBox();
            this.btnSaveCalibration = new System.Windows.Forms.Button();
            this.grpRotation = new System.Windows.Forms.GroupBox();
            this.numRotation = new System.Windows.Forms.NumericUpDown();
            this.lblRotation = new System.Windows.Forms.Label();
            this.btnClockwise = new System.Windows.Forms.Button();
            this.btnCounterClockwise = new System.Windows.Forms.Button();
            this.grpZoom = new System.Windows.Forms.GroupBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.lblZoomYValue = new System.Windows.Forms.Label();
            this.trkZoomY = new System.Windows.Forms.TrackBar();
            this.lblZoomXValue = new System.Windows.Forms.Label();
            this.trkZoomX = new System.Windows.Forms.TrackBar();
            this.grpOffset = new System.Windows.Forms.GroupBox();
            this.lblOffsetYValue = new System.Windows.Forms.Label();
            this.lblOffsetXValue = new System.Windows.Forms.Label();
            this.trkOriginY = new System.Windows.Forms.TrackBar();
            this.chkLockAxis = new System.Windows.Forms.CheckBox();
            this.lblOriginY = new System.Windows.Forms.Label();
            this.trkOriginX = new System.Windows.Forms.TrackBar();
            this.lblOriginX = new System.Windows.Forms.Label();
            this.uscBattlemap = new PRoCon.Controls.BattlemapView();
            this.toolStrip1.SuspendLayout();
            this.spltCalibration.Panel1.SuspendLayout();
            this.spltCalibration.Panel2.SuspendLayout();
            this.spltCalibration.SuspendLayout();
            this.spltZoneTags.Panel1.SuspendLayout();
            this.spltZoneTags.Panel2.SuspendLayout();
            this.spltZoneTags.SuspendLayout();
            this.grpZoneTags.SuspendLayout();
            this.grpMarkerCollection.SuspendLayout();
            this.grpRotation.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numRotation)).BeginInit();
            this.grpZoom.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.trkZoomY)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.trkZoomX)).BeginInit();
            this.grpOffset.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.trkOriginY)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.trkOriginX)).BeginInit();
            this.SuspendLayout();
            //
            // toolStrip1
            //
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbPointer,
            this.tsbTeamColours,
            this.tsbMeasuringTool,
            this.tsbMapZonesTools,
            this.toolStripSeparator1,
            this.lblImagePack,
            this.cboImagePacks,
            this.tsbCalibration});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(918, 25);
            this.toolStrip1.TabIndex = 0;
            this.toolStrip1.Text = "toolStrip1";
            //
            // tsbPointer
            //
            this.tsbPointer.Checked = true;
            this.tsbPointer.CheckOnClick = true;
            this.tsbPointer.CheckState = System.Windows.Forms.CheckState.Checked;
            this.tsbPointer.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsbPointer.Image = ((System.Drawing.Image)(resources.GetObject("tsbPointer.Image")));
            this.tsbPointer.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbPointer.Name = "tsbPointer";
            this.tsbPointer.Size = new System.Drawing.Size(23, 22);
            this.tsbPointer.Text = "Pointer";
            this.tsbPointer.CheckedChanged += new System.EventHandler(this.tsbPointer_CheckedChanged);
            //
            // tsbTeamColours
            //
            this.tsbTeamColours.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsbTeamColours.Image = ((System.Drawing.Image)(resources.GetObject("tsbTeamColours.Image")));
            this.tsbTeamColours.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbTeamColours.Name = "tsbTeamColours";
            this.tsbTeamColours.Size = new System.Drawing.Size(23, 22);
            this.tsbTeamColours.Text = "Enemy Colours";
            this.tsbTeamColours.Click += new System.EventHandler(this.tsbTeamColours_Click);
            //
            // tsbMeasuringTool
            //
            this.tsbMeasuringTool.CheckOnClick = true;
            this.tsbMeasuringTool.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsbMeasuringTool.Image = ((System.Drawing.Image)(resources.GetObject("tsbMeasuringTool.Image")));
            this.tsbMeasuringTool.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbMeasuringTool.Name = "tsbMeasuringTool";
            this.tsbMeasuringTool.Size = new System.Drawing.Size(23, 22);
            this.tsbMeasuringTool.Text = "Measuring Tool";
            this.tsbMeasuringTool.CheckedChanged += new System.EventHandler(this.tsbDistanceTool_CheckedChanged);
            //
            // tsbMapZonesTools
            //
            this.tsbMapZonesTools.CheckOnClick = true;
            this.tsbMapZonesTools.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsbMapZonesTools.Image = ((System.Drawing.Image)(resources.GetObject("tsbMapZonesTools.Image")));
            this.tsbMapZonesTools.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbMapZonesTools.Name = "tsbMapZonesTools";
            this.tsbMapZonesTools.Size = new System.Drawing.Size(23, 22);
            this.tsbMapZonesTools.Text = "Map Zones";
            this.tsbMapZonesTools.CheckedChanged += new System.EventHandler(this.tsbMapZonesTools_CheckedChanged);
            //
            // toolStripSeparator1
            //
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            //
            // lblImagePack
            //
            this.lblImagePack.Name = "lblImagePack";
            this.lblImagePack.Size = new System.Drawing.Size(71, 22);
            this.lblImagePack.Text = "Image pack:";
            //
            // cboImagePacks
            //
            this.cboImagePacks.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboImagePacks.Name = "cboImagePacks";
            this.cboImagePacks.Size = new System.Drawing.Size(233, 25);
            this.cboImagePacks.SelectedIndexChanged += new System.EventHandler(this.cboImagePacks_SelectedIndexChanged);
            //
            // tsbCalibration
            //
            this.tsbCalibration.CheckOnClick = true;
            this.tsbCalibration.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsbCalibration.Image = ((System.Drawing.Image)(resources.GetObject("tsbCalibration.Image")));
            this.tsbCalibration.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbCalibration.Name = "tsbCalibration";
            this.tsbCalibration.Size = new System.Drawing.Size(23, 22);
            this.tsbCalibration.Text = "Calibration";
            this.tsbCalibration.CheckedChanged += new System.EventHandler(this.tsbCalibration_CheckedChanged);
            //
            // spltCalibration
            //
            this.spltCalibration.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltCalibration.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.spltCalibration.IsSplitterFixed = true;
            this.spltCalibration.Location = new System.Drawing.Point(0, 25);
            this.spltCalibration.Name = "spltCalibration";
            //
            // spltCalibration.Panel1
            //
            this.spltCalibration.Panel1.Controls.Add(this.spltZoneTags);
            //
            // spltCalibration.Panel2
            //
            this.spltCalibration.Panel2.Controls.Add(this.lblMapPackFilePath);
            this.spltCalibration.Panel2.Controls.Add(this.grpMarkerCollection);
            this.spltCalibration.Panel2.Controls.Add(this.btnSaveCalibration);
            this.spltCalibration.Panel2.Controls.Add(this.grpRotation);
            this.spltCalibration.Panel2.Controls.Add(this.grpZoom);
            this.spltCalibration.Panel2.Controls.Add(this.grpOffset);
            this.spltCalibration.Panel2.Padding = new System.Windows.Forms.Padding(3);
            this.spltCalibration.Panel2Collapsed = true;
            this.spltCalibration.Size = new System.Drawing.Size(918, 644);
            this.spltCalibration.SplitterDistance = 628;
            this.spltCalibration.SplitterWidth = 5;
            this.spltCalibration.TabIndex = 2;
            //
            // spltZoneTags
            //
            this.spltZoneTags.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltZoneTags.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.spltZoneTags.IsSplitterFixed = true;
            this.spltZoneTags.Location = new System.Drawing.Point(0, 0);
            this.spltZoneTags.Name = "spltZoneTags";
            //
            // spltZoneTags.Panel1
            //
            this.spltZoneTags.Panel1.Controls.Add(this.uscBattlemap);
            //
            // spltZoneTags.Panel2
            //
            this.spltZoneTags.Panel2.Controls.Add(this.grpZoneTags);
            this.spltZoneTags.Panel2.Enabled = false;
            this.spltZoneTags.Panel2.Padding = new System.Windows.Forms.Padding(3);
            this.spltZoneTags.Panel2Collapsed = true;
            this.spltZoneTags.Size = new System.Drawing.Size(918, 644);
            this.spltZoneTags.SplitterDistance = 609;
            this.spltZoneTags.TabIndex = 3;
            //
            // grpZoneTags
            //
            this.grpZoneTags.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.grpZoneTags.Controls.Add(this.lblTagsHelp);
            this.grpZoneTags.Controls.Add(this.btnAddTag);
            this.grpZoneTags.Controls.Add(this.cboTagList);
            this.grpZoneTags.Controls.Add(this.txtTagList);
            this.grpZoneTags.Location = new System.Drawing.Point(6, 6);
            this.grpZoneTags.Name = "grpZoneTags";
            this.grpZoneTags.Padding = new System.Windows.Forms.Padding(6);
            this.grpZoneTags.Size = new System.Drawing.Size(256, 257);
            this.grpZoneTags.TabIndex = 0;
            this.grpZoneTags.TabStop = false;
            this.grpZoneTags.Text = "Zone Tags";
            //
            // lblTagsHelp
            //
            this.lblTagsHelp.Location = new System.Drawing.Point(12, 22);
            this.lblTagsHelp.Name = "lblTagsHelp";
            this.lblTagsHelp.Size = new System.Drawing.Size(231, 49);
            this.lblTagsHelp.TabIndex = 3;
            this.lblTagsHelp.Text = "Tags and map functionality are provided by plugins.";
            //
            // btnAddTag
            //
            this.btnAddTag.FlatAppearance.BorderSize = 0;
            this.btnAddTag.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAddTag.Location = new System.Drawing.Point(209, 73);
            this.btnAddTag.Name = "btnAddTag";
            this.btnAddTag.Size = new System.Drawing.Size(34, 23);
            this.btnAddTag.TabIndex = 2;
            this.btnAddTag.UseVisualStyleBackColor = true;
            this.btnAddTag.Click += new System.EventHandler(this.btnAddTag_Click);
            //
            // cboTagList
            //
            this.cboTagList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboTagList.FormattingEnabled = true;
            this.cboTagList.Location = new System.Drawing.Point(9, 74);
            this.cboTagList.Name = "cboTagList";
            this.cboTagList.Size = new System.Drawing.Size(194, 23);
            this.cboTagList.TabIndex = 1;
            //
            // txtTagList
            //
            this.txtTagList.Location = new System.Drawing.Point(9, 102);
            this.txtTagList.Multiline = true;
            this.txtTagList.Name = "txtTagList";
            this.txtTagList.Size = new System.Drawing.Size(234, 146);
            this.txtTagList.TabIndex = 0;
            this.txtTagList.TextChanged += new System.EventHandler(this.txtTagList_TextChanged);
            //
            // lblMapPackFilePath
            //
            this.lblMapPackFilePath.Location = new System.Drawing.Point(13, 471);
            this.lblMapPackFilePath.Name = "lblMapPackFilePath";
            this.lblMapPackFilePath.Size = new System.Drawing.Size(245, 112);
            this.lblMapPackFilePath.TabIndex = 11;
            //
            // grpMarkerCollection
            //
            this.grpMarkerCollection.Controls.Add(this.btnClearMarkers);
            this.grpMarkerCollection.Controls.Add(this.lblTrackPlayersList);
            this.grpMarkerCollection.Controls.Add(this.cboPlayers);
            this.grpMarkerCollection.Location = new System.Drawing.Point(6, 6);
            this.grpMarkerCollection.Name = "grpMarkerCollection";
            this.grpMarkerCollection.Size = new System.Drawing.Size(252, 100);
            this.grpMarkerCollection.TabIndex = 10;
            this.grpMarkerCollection.TabStop = false;
            this.grpMarkerCollection.Text = "Marker Collection";
            //
            // btnClearMarkers
            //
            this.btnClearMarkers.Location = new System.Drawing.Point(74, 66);
            this.btnClearMarkers.Name = "btnClearMarkers";
            this.btnClearMarkers.Size = new System.Drawing.Size(167, 23);
            this.btnClearMarkers.TabIndex = 2;
            this.btnClearMarkers.Text = "Clear Markers";
            this.btnClearMarkers.UseVisualStyleBackColor = true;
            this.btnClearMarkers.Click += new System.EventHandler(this.btnClearMarkers_Click);
            //
            // lblTrackPlayersList
            //
            this.lblTrackPlayersList.AutoSize = true;
            this.lblTrackPlayersList.Location = new System.Drawing.Point(7, 19);
            this.lblTrackPlayersList.Name = "lblTrackPlayersList";
            this.lblTrackPlayersList.Size = new System.Drawing.Size(98, 15);
            this.lblTrackPlayersList.TabIndex = 1;
            this.lblTrackPlayersList.Text = "Mark suicides by:";
            //
            // cboPlayers
            //
            this.cboPlayers.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboPlayers.FormattingEnabled = true;
            this.cboPlayers.Location = new System.Drawing.Point(10, 37);
            this.cboPlayers.Name = "cboPlayers";
            this.cboPlayers.Size = new System.Drawing.Size(231, 23);
            this.cboPlayers.TabIndex = 0;
            //
            // btnSaveCalibration
            //
            this.btnSaveCalibration.Location = new System.Drawing.Point(123, 586);
            this.btnSaveCalibration.Name = "btnSaveCalibration";
            this.btnSaveCalibration.Size = new System.Drawing.Size(135, 23);
            this.btnSaveCalibration.TabIndex = 9;
            this.btnSaveCalibration.Text = "Save";
            this.btnSaveCalibration.UseVisualStyleBackColor = true;
            this.btnSaveCalibration.Click += new System.EventHandler(this.btnSaveCalibration_Click);
            //
            // grpRotation
            //
            this.grpRotation.Controls.Add(this.numRotation);
            this.grpRotation.Controls.Add(this.lblRotation);
            this.grpRotation.Controls.Add(this.btnClockwise);
            this.grpRotation.Controls.Add(this.btnCounterClockwise);
            this.grpRotation.Location = new System.Drawing.Point(6, 402);
            this.grpRotation.Name = "grpRotation";
            this.grpRotation.Size = new System.Drawing.Size(252, 66);
            this.grpRotation.TabIndex = 8;
            this.grpRotation.TabStop = false;
            this.grpRotation.Text = "Rotation";
            //
            // numRotation
            //
            this.numRotation.Location = new System.Drawing.Point(101, 24);
            this.numRotation.Maximum = new decimal(new int[] {
            360,
            0,
            0,
            0});
            this.numRotation.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            -2147483648});
            this.numRotation.Name = "numRotation";
            this.numRotation.Size = new System.Drawing.Size(46, 23);
            this.numRotation.TabIndex = 3;
            this.numRotation.ValueChanged += new System.EventHandler(this.numRotation_ValueChanged);
            //
            // lblRotation
            //
            this.lblRotation.AutoSize = true;
            this.lblRotation.Location = new System.Drawing.Point(148, 27);
            this.lblRotation.Name = "lblRotation";
            this.lblRotation.Size = new System.Drawing.Size(12, 15);
            this.lblRotation.TabIndex = 2;
            this.lblRotation.Text = "°";
            //
            // btnClockwise
            //
            this.btnClockwise.FlatAppearance.BorderSize = 0;
            this.btnClockwise.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnClockwise.Location = new System.Drawing.Point(206, 22);
            this.btnClockwise.Name = "btnClockwise";
            this.btnClockwise.Size = new System.Drawing.Size(35, 23);
            this.btnClockwise.TabIndex = 1;
            this.btnClockwise.UseVisualStyleBackColor = true;
            this.btnClockwise.Click += new System.EventHandler(this.btnClockwise_Click);
            //
            // btnCounterClockwise
            //
            this.btnCounterClockwise.FlatAppearance.BorderSize = 0;
            this.btnCounterClockwise.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCounterClockwise.Location = new System.Drawing.Point(30, 22);
            this.btnCounterClockwise.Name = "btnCounterClockwise";
            this.btnCounterClockwise.Size = new System.Drawing.Size(35, 23);
            this.btnCounterClockwise.TabIndex = 0;
            this.btnCounterClockwise.UseVisualStyleBackColor = true;
            this.btnCounterClockwise.Click += new System.EventHandler(this.btnCounterClockwise_Click);
            //
            // grpZoom
            //
            this.grpZoom.Controls.Add(this.label2);
            this.grpZoom.Controls.Add(this.label3);
            this.grpZoom.Controls.Add(this.lblZoomYValue);
            this.grpZoom.Controls.Add(this.trkZoomY);
            this.grpZoom.Controls.Add(this.lblZoomXValue);
            this.grpZoom.Controls.Add(this.trkZoomX);
            this.grpZoom.Location = new System.Drawing.Point(6, 272);
            this.grpZoom.Name = "grpZoom";
            this.grpZoom.Size = new System.Drawing.Size(252, 124);
            this.grpZoom.TabIndex = 7;
            this.grpZoom.TabStop = false;
            this.grpZoom.Text = "Zoom";
            //
            // label2
            //
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(10, 71);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(14, 15);
            this.label2.TabIndex = 11;
            this.label2.Text = "Y";
            //
            // label3
            //
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(10, 25);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(14, 15);
            this.label3.TabIndex = 10;
            this.label3.Text = "X";
            //
            // lblZoomYValue
            //
            this.lblZoomYValue.AutoSize = true;
            this.lblZoomYValue.Location = new System.Drawing.Point(217, 76);
            this.lblZoomYValue.Name = "lblZoomYValue";
            this.lblZoomYValue.Size = new System.Drawing.Size(14, 15);
            this.lblZoomYValue.TabIndex = 9;
            this.lblZoomYValue.Text = "Y";
            //
            // trkZoomY
            //
            this.trkZoomY.BackColor = System.Drawing.SystemColors.Window;
            this.trkZoomY.LargeChange = 10;
            this.trkZoomY.Location = new System.Drawing.Point(30, 66);
            this.trkZoomY.Maximum = 2000;
            this.trkZoomY.Minimum = 1;
            this.trkZoomY.Name = "trkZoomY";
            this.trkZoomY.Size = new System.Drawing.Size(181, 45);
            this.trkZoomY.TabIndex = 8;
            this.trkZoomY.TickFrequency = 5;
            this.trkZoomY.Value = 1;
            this.trkZoomY.ValueChanged += new System.EventHandler(this.trkZoomY_ValueChanged);
            //
            // lblZoomXValue
            //
            this.lblZoomXValue.AutoSize = true;
            this.lblZoomXValue.Location = new System.Drawing.Point(217, 25);
            this.lblZoomXValue.Name = "lblZoomXValue";
            this.lblZoomXValue.Size = new System.Drawing.Size(14, 15);
            this.lblZoomXValue.TabIndex = 7;
            this.lblZoomXValue.Text = "Y";
            //
            // trkZoomX
            //
            this.trkZoomX.BackColor = System.Drawing.SystemColors.Window;
            this.trkZoomX.LargeChange = 10;
            this.trkZoomX.Location = new System.Drawing.Point(30, 22);
            this.trkZoomX.Maximum = 2000;
            this.trkZoomX.Minimum = 1;
            this.trkZoomX.Name = "trkZoomX";
            this.trkZoomX.Size = new System.Drawing.Size(181, 45);
            this.trkZoomX.TabIndex = 0;
            this.trkZoomX.TickFrequency = 5;
            this.trkZoomX.Value = 1;
            this.trkZoomX.ValueChanged += new System.EventHandler(this.trkZoom_ValueChanged);
            //
            // grpOffset
            //
            this.grpOffset.Controls.Add(this.lblOffsetYValue);
            this.grpOffset.Controls.Add(this.lblOffsetXValue);
            this.grpOffset.Controls.Add(this.trkOriginY);
            this.grpOffset.Controls.Add(this.chkLockAxis);
            this.grpOffset.Controls.Add(this.lblOriginY);
            this.grpOffset.Controls.Add(this.trkOriginX);
            this.grpOffset.Controls.Add(this.lblOriginX);
            this.grpOffset.Location = new System.Drawing.Point(6, 112);
            this.grpOffset.Name = "grpOffset";
            this.grpOffset.Size = new System.Drawing.Size(252, 153);
            this.grpOffset.TabIndex = 6;
            this.grpOffset.TabStop = false;
            this.grpOffset.Text = "Offset";
            //
            // lblOffsetYValue
            //
            this.lblOffsetYValue.AutoSize = true;
            this.lblOffsetYValue.Location = new System.Drawing.Point(216, 96);
            this.lblOffsetYValue.Name = "lblOffsetYValue";
            this.lblOffsetYValue.Size = new System.Drawing.Size(14, 15);
            this.lblOffsetYValue.TabIndex = 6;
            this.lblOffsetYValue.Text = "Y";
            //
            // lblOffsetXValue
            //
            this.lblOffsetXValue.AutoSize = true;
            this.lblOffsetXValue.Location = new System.Drawing.Point(216, 50);
            this.lblOffsetXValue.Name = "lblOffsetXValue";
            this.lblOffsetXValue.Size = new System.Drawing.Size(14, 15);
            this.lblOffsetXValue.TabIndex = 5;
            this.lblOffsetXValue.Text = "X";
            //
            // trkOriginY
            //
            this.trkOriginY.BackColor = System.Drawing.SystemColors.Window;
            this.trkOriginY.Enabled = false;
            this.trkOriginY.LargeChange = 32;
            this.trkOriginY.Location = new System.Drawing.Point(30, 93);
            this.trkOriginY.Maximum = 8192;
            this.trkOriginY.Minimum = -8192;
            this.trkOriginY.Name = "trkOriginY";
            this.trkOriginY.Size = new System.Drawing.Size(181, 45);
            this.trkOriginY.TabIndex = 2;
            this.trkOriginY.TickStyle = System.Windows.Forms.TickStyle.None;
            this.trkOriginY.ValueChanged += new System.EventHandler(this.trkOriginY_ValueChanged);
            //
            // chkLockAxis
            //
            this.chkLockAxis.AutoSize = true;
            this.chkLockAxis.Checked = true;
            this.chkLockAxis.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkLockAxis.Location = new System.Drawing.Point(30, 22);
            this.chkLockAxis.Name = "chkLockAxis";
            this.chkLockAxis.Size = new System.Drawing.Size(75, 19);
            this.chkLockAxis.TabIndex = 0;
            this.chkLockAxis.Text = "Lock Axis";
            this.chkLockAxis.UseVisualStyleBackColor = true;
            this.chkLockAxis.CheckedChanged += new System.EventHandler(this.chkLockAxis_CheckedChanged);
            //
            // lblOriginY
            //
            this.lblOriginY.AutoSize = true;
            this.lblOriginY.Location = new System.Drawing.Point(7, 96);
            this.lblOriginY.Name = "lblOriginY";
            this.lblOriginY.Size = new System.Drawing.Size(14, 15);
            this.lblOriginY.TabIndex = 4;
            this.lblOriginY.Text = "Y";
            //
            // trkOriginX
            //
            this.trkOriginX.BackColor = System.Drawing.SystemColors.Window;
            this.trkOriginX.LargeChange = 32;
            this.trkOriginX.Location = new System.Drawing.Point(30, 48);
            this.trkOriginX.Maximum = 8192;
            this.trkOriginX.Minimum = -8192;
            this.trkOriginX.Name = "trkOriginX";
            this.trkOriginX.Size = new System.Drawing.Size(181, 45);
            this.trkOriginX.TabIndex = 1;
            this.trkOriginX.TickStyle = System.Windows.Forms.TickStyle.None;
            this.trkOriginX.ValueChanged += new System.EventHandler(this.trkOriginX_ValueChanged);
            //
            // lblOriginX
            //
            this.lblOriginX.AutoSize = true;
            this.lblOriginX.Location = new System.Drawing.Point(7, 50);
            this.lblOriginX.Name = "lblOriginX";
            this.lblOriginX.Size = new System.Drawing.Size(14, 15);
            this.lblOriginX.TabIndex = 3;
            this.lblOriginX.Text = "X";
            //
            // uscBattlemap
            //
            this.uscBattlemap.DisplayCalibrationGrid = false;
            this.uscBattlemap.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscBattlemap.ErrorRadius = 14;
            this.uscBattlemap.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscBattlemap.FullyLoadMap = false;
            this.uscBattlemap.KillColours = PRoCon.Controls.Battlemap.KillDisplay.KillDisplayColours.TeamColours;
            this.uscBattlemap.KillEventFadeout = 5000;
            this.uscBattlemap.LoadedMapImagePack = null;
            this.uscBattlemap.Location = new System.Drawing.Point(0, 0);
            this.uscBattlemap.Name = "uscBattlemap";
            this.uscBattlemap.SelectedTool = PRoCon.Controls.Battlemap.BattlemapViewTools.Pointer;
            this.uscBattlemap.Size = new System.Drawing.Size(918, 644);
            this.uscBattlemap.TabIndex = 2;
            this.uscBattlemap.ZoomFactor = 1F;
            this.uscBattlemap.KillColoursChanged += new PRoCon.Controls.BattlemapView.KillColoursChangedHandler(this.uscBattlemap_KillColoursChanged);
            //
            // uscMapViewer
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.spltCalibration);
            this.Controls.Add(this.toolStrip1);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscMapViewer";
            this.Size = new System.Drawing.Size(918, 669);
            this.Load += new System.EventHandler(this.uscMapViewer_Load);
            this.Resize += new System.EventHandler(this.uscMapViewer_Resize);
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.spltCalibration.Panel1.ResumeLayout(false);
            this.spltCalibration.Panel2.ResumeLayout(false);
            this.spltCalibration.ResumeLayout(false);
            this.spltZoneTags.Panel1.ResumeLayout(false);
            this.spltZoneTags.Panel2.ResumeLayout(false);
            this.spltZoneTags.ResumeLayout(false);
            this.grpZoneTags.ResumeLayout(false);
            this.grpZoneTags.PerformLayout();
            this.grpMarkerCollection.ResumeLayout(false);
            this.grpMarkerCollection.PerformLayout();
            this.grpRotation.ResumeLayout(false);
            this.grpRotation.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numRotation)).EndInit();
            this.grpZoom.ResumeLayout(false);
            this.grpZoom.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.trkZoomY)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.trkZoomX)).EndInit();
            this.grpOffset.ResumeLayout(false);
            this.grpOffset.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.trkOriginY)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.trkOriginX)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripButton tsbMapZonesTools;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripComboBox cboImagePacks;
        private System.Windows.Forms.ToolStripLabel lblImagePack;
        private System.Windows.Forms.ToolStripButton tsbPointer;
        private System.Windows.Forms.SplitContainer spltCalibration;
        private BattlemapView uscBattlemap;
        private System.Windows.Forms.ToolStripButton tsbCalibration;
        private System.Windows.Forms.GroupBox grpOffset;
        private System.Windows.Forms.TrackBar trkOriginY;
        private System.Windows.Forms.CheckBox chkLockAxis;
        private System.Windows.Forms.Label lblOriginY;
        private System.Windows.Forms.TrackBar trkOriginX;
        private System.Windows.Forms.Label lblOriginX;
        private System.Windows.Forms.Label lblOffsetYValue;
        private System.Windows.Forms.Label lblOffsetXValue;
        private System.Windows.Forms.GroupBox grpZoom;
        private System.Windows.Forms.TrackBar trkZoomX;
        private System.Windows.Forms.Label lblZoomXValue;
        private System.Windows.Forms.GroupBox grpRotation;
        private System.Windows.Forms.Button btnClockwise;
        private System.Windows.Forms.Button btnCounterClockwise;
        private System.Windows.Forms.Label lblRotation;
        private System.Windows.Forms.Button btnSaveCalibration;
        private System.Windows.Forms.GroupBox grpMarkerCollection;
        private System.Windows.Forms.Label lblTrackPlayersList;
        private System.Windows.Forms.ComboBox cboPlayers;
        private System.Windows.Forms.Button btnClearMarkers;
        private System.Windows.Forms.Label lblMapPackFilePath;
        private System.Windows.Forms.ToolStripButton tsbMeasuringTool;
        private System.Windows.Forms.ToolStripButton tsbTeamColours;
        private System.Windows.Forms.SplitContainer spltZoneTags;
        private System.Windows.Forms.GroupBox grpZoneTags;
        private System.Windows.Forms.TextBox txtTagList;
        private System.Windows.Forms.ComboBox cboTagList;
        private System.Windows.Forms.Button btnAddTag;
        private System.Windows.Forms.Label lblTagsHelp;
        private System.Windows.Forms.NumericUpDown numRotation;
        private System.Windows.Forms.Label lblZoomYValue;
        private System.Windows.Forms.TrackBar trkZoomY;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
    }*/
}
