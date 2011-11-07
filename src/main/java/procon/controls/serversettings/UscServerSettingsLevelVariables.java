package procon.controls.serversettings;

public class UscServerSettingsLevelVariables {

    /*public partial class uscServerSettingsLevelVariables : uscServerSettings {

        public uscServerSettingsLevelVariables() {
            InitializeComponent();

            this.AsyncSettingControls.Add("levelvars.clear", new AsyncStyleSetting(this.picClearLevelSettings, this.btnClearLevelSettings, new Control[] { this.btnClearLevelSettings }, true));

            this.AsyncSettingControls.Add("levelvars.set tickets", new AsyncStyleSetting(this.picLevelTickets, this.numLevelTickets, new Control[] { this.numLevelTickets, this.lnkLevelTickets, this.lblLevelTickets }, true));
            this.AsyncSettingControls.Add("levelvars.set ticketbleedspeed", new AsyncStyleSetting(this.picLevelTicketBleedSpeed, this.numLevelTicketBleedSpeed, new Control[] { this.numLevelTicketBleedSpeed, this.lnkLevelTicketBleedSpeed, this.lblLevelTicketBleedSpeed }, true));
            this.AsyncSettingControls.Add("levelvars.set vehiclespawnrate", new AsyncStyleSetting(this.picLevelVehicleSpawnRate, this.numLevelVehicleSpawnRate, new Control[] { this.numLevelVehicleSpawnRate, this.lnkLevelVehicleSpawnRate, this.lblLevelVehicleSpawnRate }, true));
            this.AsyncSettingControls.Add("levelvars.set vehiclesdisabled", new AsyncStyleSetting(this.picLevelVehiclesDisabled, this.chkLevelVehiclesDisabled, new Control[] { this.chkLevelVehiclesDisabled }, true));
            this.AsyncSettingControls.Add("levelvars.set startdelay", new AsyncStyleSetting(this.picLevelStartDelay, this.numLevelStartDelay, new Control[] { this.numLevelStartDelay, this.lnkLevelStartDelay, this.lblLevelStartDelay }, true));
            this.AsyncSettingControls.Add("levelvars.set respawndelay", new AsyncStyleSetting(this.picLevelRespawnDelay, this.numLevelRespawnDelay, new Control[] { this.numLevelRespawnDelay, this.lnkLevelRespawnDelay, this.lblLevelRespawnDelay }, true));

            this.rdoSettingsLevelContextAll.Checked = true;
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            base.SetLocalization(clocLanguage);

            // Level variables
            this.lblSettingsLevelContext.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsLevelContext");
            this.rdoSettingsLevelContextAll.Text = this.Language.GetLocalized("uscServerSettingsPanel.rdoSettingsLevelContextAll");
            this.rdoSettingsLevelContextGamemode.Text = this.Language.GetLocalized("uscServerSettingsPanel.rdoSettingsLevelContextGamemode");
            this.rdoSettingsLevelContextLevel.Text = this.Language.GetLocalized("uscServerSettingsPanel.rdoSettingsLevelContextLevel");

            this.btnClearLevelSettings.Text = this.Language.GetLocalized("uscServerSettingsPanel.btnClearLevelSettings");

            this.lblLevelTickets.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblLevelTickets");
            this.lnkLevelTickets.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkLevelTickets");

            this.lblLevelTicketBleedSpeed.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblLevelTicketBleedSpeed");
            this.lnkLevelTicketBleedSpeed.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkLevelTicketBleedSpeed");

            this.lblLevelVehicleSpawnRate.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblLevelVehicleSpawnRate");
            this.lnkLevelVehicleSpawnRate.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkLevelVehicleSpawnRate");

            this.lblLevelStartDelay.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblLevelStartDelay");
            this.lnkLevelStartDelay.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkLevelStartDelay");

            this.lblLevelRespawnDelay.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblLevelRespawnDelay");
            this.lnkLevelRespawnDelay.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkLevelRespawnDelay");

            this.chkLevelVehiclesDisabled.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkLevelVehiclesDisabled");

            this.lblLevelEvaluatedEffects.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblLevelEvaluatedEffects");

            this.context.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.context");
            this.tickets.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.tickets");
            this.ticketBleedSpeed.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.ticketBleedSpeed");
            this.vehicleSpawnRate.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.vehicleSpawnRate");
            this.vehiclesDisabled.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.vehiclesDisabled");
            this.startDelay.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.startDelay");
            this.respawnDelay.Text = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.respawnDelay");

            this.DisplayName = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsLevelVariables");
        }

        public override void SetConnection(Core.Remote.PRoConClient prcClient) {
            base.SetConnection(prcClient);

            if (this.Client != null) {
                if (this.Client.Game != null) {
                    this.m_prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    this.Client.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcClient_GameTypeDiscovered);
                }
            }
        }


        private void m_prcClient_GameTypeDiscovered(PRoConClient sender) {

            this.Client.Login += new PRoConClient.EmptyParamterHandler(m_prcClient_CommandLogin);

            this.Client.Game.LevelVariablesList += new FrostbiteClient.LevelVariableListHandler(m_prcClient_LevelVariablesList);
            this.Client.Game.LevelVariablesClear += new FrostbiteClient.LevelVariableHandler(m_prcClient_LevelVariablesClear);
            this.Client.Game.LevelVariablesSet += new FrostbiteClient.LevelVariableHandler(m_prcClient_LevelVariablesSet);

            foreach (CMap gamemodeMap in this.Client.GetGamemodeList()) {
                this.cboSettingsGamemodes.Items.Add(gamemodeMap);
            }

            foreach (CMap map in this.Client.MapListPool) {
                this.cboSettingsLevels.Items.Add(map);
            }
        }

        private void m_prcClient_CommandLogin(PRoConClient sender) {

            this.GetSelectedLevelVariables();

            if (this.cboSettingsGamemodes.Items.Count > 0) {
                this.cboSettingsGamemodes.SelectedIndex = 0;
            }

            if (this.cboSettingsLevels.Items.Count > 0) {
                this.cboSettingsLevels.SelectedIndex = 0;
            }
        }

        #region Level Variable Settings

        #region Helper Level Settings Methods

        private LevelVariableContext GetSelectedContext() {

            LevelVariableContextType contextType = LevelVariableContextType.None;
            string contextTarget = String.Empty;

            if (this.rdoSettingsLevelContextAll.Checked == true) {
                contextType = LevelVariableContextType.All;
            }
            else if (this.rdoSettingsLevelContextGamemode.Checked == true) {
                contextType = LevelVariableContextType.GameMode;
                if (this.cboSettingsGamemodes.SelectedItem != null) {
                    contextTarget = ((CMap)this.cboSettingsGamemodes.SelectedItem).PlayList;
                }
            }
            else if (this.rdoSettingsLevelContextLevel.Checked == true) {
                contextType = LevelVariableContextType.Level;

                if (this.cboSettingsGamemodes.SelectedItem != null) {
                    contextTarget = ((CMap)this.cboSettingsLevels.SelectedItem).FileName;
                }
            }

            return new LevelVariableContext(contextType, contextTarget);
        }

        private void GetLevelVariablesByContext(LevelVariableContext context) {
            if (this.Client != null && this.Client.Game != null) {
                this.Client.Game.SendLevelVarsListPacket(context);
            }

            //if (context.ContextTarget.Length > 0) {
            //    this.SendCommand("levelVars.list", context.ContextType.ToString().ToLower(), context.ContextTarget);
            //}
            //else {
            //    this.SendCommand("levelVars.list", context.ContextType.ToString().ToLower());
            //}
        }

        private void SetLevelVariablesByContext(LevelVariableContext context, string variable, string value) {
            if (this.Client != null && this.Client.Game != null) {
                this.Client.Game.SendLevelVarsSetPacket(context, variable, value);
            }

            //if (context.ContextTarget.Length > 0) {
            //    this.SendCommand("levelVars.set", context.ContextType.ToString().ToLower(), context.ContextTarget, variable, value);
            //}
            //else {
            //    this.SendCommand("levelVars.set", context.ContextType.ToString().ToLower(), variable, value);
            //}
        }

        private void GetSelectedLevelVariables() {

            LevelVariableContext selectedContext = this.GetSelectedContext();

            this.lsvEvaluatedEffect.Items.Clear();

            // If we just got a gamemode or level
            if (selectedContext.ContextType == LevelVariableContextType.GameMode || selectedContext.ContextType == LevelVariableContextType.Level) {
                // Then we need the context of "all" to evaluate the effect.
                this.GetLevelVariablesByContext(new LevelVariableContext(LevelVariableContextType.All, String.Empty));

                // If we just got a level
                if (selectedContext.ContextType == LevelVariableContextType.Level && this.cboSettingsLevels.SelectedItem != null) {
                    // Then we need the context of the levels gamemode to evaluate the effect.
                    this.GetLevelVariablesByContext(new LevelVariableContext(LevelVariableContextType.GameMode, ((CMap)this.cboSettingsLevels.SelectedItem).PlayList));
                }
            }

            this.GetLevelVariablesByContext(selectedContext);
        }

        #endregion

        private void rdoSettingsLevelContextAll_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoSettingsLevelContextAll.Checked == true) {
                this.rdoSettingsLevelContextAll.Font = new Font(this.Font, FontStyle.Bold);

                this.GetSelectedLevelVariables();
            }
            else {
                this.rdoSettingsLevelContextAll.Font = new Font(this.Font, FontStyle.Regular);
            }
        }

        private void rdoSettingsLevelContextGamemode_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoSettingsLevelContextGamemode.Checked == true) {
                this.rdoSettingsLevelContextGamemode.Font = new Font(this.Font, FontStyle.Bold);

                this.GetSelectedLevelVariables();
            }
            else {
                this.rdoSettingsLevelContextGamemode.Font = new Font(this.Font, FontStyle.Regular);
            }

            this.cboSettingsGamemodes.Enabled = this.rdoSettingsLevelContextGamemode.Checked;
        }

        private void rdoSettingsLevelContextLevel_CheckedChanged(object sender, EventArgs e) {
            if (this.rdoSettingsLevelContextLevel.Checked == true) {
                this.rdoSettingsLevelContextLevel.Font = new Font(this.Font, FontStyle.Bold);

                this.GetSelectedLevelVariables();
            }
            else {
                this.rdoSettingsLevelContextLevel.Font = new Font(this.Font, FontStyle.Regular);
            }

            this.cboSettingsLevels.Enabled = this.rdoSettingsLevelContextLevel.Checked;
        }

        private void cboSettingsGamemodes_SelectedIndexChanged(object sender, EventArgs e) {
            this.GetSelectedLevelVariables();
        }

        private void cboSettingsLevels_SelectedIndexChanged(object sender, EventArgs e) {
            this.GetSelectedLevelVariables();
        }

        private void cboSettingsGamemodes_DrawItem(object sender, DrawItemEventArgs e) {
            if (e.Index != -1) {

                CMap mapDraw = ((CMap)cboSettingsGamemodes.Items[e.Index]);

                e.Graphics.FillRectangle(SystemBrushes.Window, e.Bounds);
                e.DrawBackground();
                e.DrawFocusRectangle();

                e.Graphics.DrawString(String.Format("{0}", mapDraw.GameMode), this.Font, SystemBrushes.WindowText, e.Bounds.Left + 5, e.Bounds.Top);
            }
        }

        private void cboSettingsLevels_DrawItem(object sender, DrawItemEventArgs e) {
            if (e.Index != -1) {

                CMap mapDraw = ((CMap)cboSettingsLevels.Items[e.Index]);

                e.Graphics.FillRectangle(SystemBrushes.Window, e.Bounds);
                e.DrawBackground();
                e.DrawFocusRectangle();

                e.Graphics.DrawString(String.Format("{0} - {1}", mapDraw.GameMode, mapDraw.PublicLevelName), this.Font, SystemBrushes.WindowText, e.Bounds.Left + 5, e.Bounds.Top);
            }
        }

        private void SetLevelVariable(LevelVariable levelVar) {

            object value = null;

            if (String.Compare(levelVar.VariableName, "vehiclesdisabled", true) == 0) {
                value = levelVar.GetValue<bool>(false);
            }
            else {
                value = levelVar.GetValue<decimal>(100);
            }

            string responseKey = String.Format("levelvars.set {0}", levelVar.VariableName.ToLower());

            this.OnSettingResponse(responseKey, value, true);

            if (this.AsyncSettingControls.ContainsKey(responseKey) == true) {
                foreach (Control enableControl in this.AsyncSettingControls[responseKey].ma_ctrlEnabledInputs) {
                    if ((enableControl is Label && !(enableControl is LinkLabel)) || enableControl is CheckBox) {
                        enableControl.ForeColor = Color.LightSeaGreen;
                        enableControl.Font = new Font(this.Font, FontStyle.Bold);
                    }
                }
            }
        }

        #region Evaluated effects

        private void SetCurrentSelectedLevelVariables(List<LevelVariable> lstReturnedValues) {

            this.lblLevelTicketBleedSpeed.Font =
                this.lblLevelTickets.Font =
                this.lblLevelVehicleSpawnRate.Font =
                this.lblLevelStartDelay.Font =
                this.lblLevelRespawnDelay.Font =
                this.chkLevelVehiclesDisabled.Font = new Font(this.Font, FontStyle.Regular);

            this.lblLevelTicketBleedSpeed.ForeColor =
                 this.lblLevelTickets.ForeColor =
                 this.lblLevelVehicleSpawnRate.ForeColor =
                 this.lblLevelStartDelay.ForeColor =
                 this.lblLevelRespawnDelay.ForeColor =
                 this.chkLevelVehiclesDisabled.ForeColor = SystemColors.WindowText;

            // Set all to default values.  This might be expanded on in the future to be context sensitive (e.g more spawn delay for SQDM)
            this.IgnoreEvents = true;
            this.numLevelTickets.Value = 100;
            this.numLevelTicketBleedSpeed.Value = 100;
            this.numLevelVehicleSpawnRate.Value = 100;
            this.numLevelStartDelay.Value = 10;
            this.numLevelRespawnDelay.Value = 20;
            this.chkLevelVehiclesDisabled.Checked = false;
            this.IgnoreEvents = false;

            foreach (LevelVariable levelVar in lstReturnedValues) {
                this.SetLevelVariable(levelVar);
            }
        }

        private void UpdateTotalEffects() {

            if (this.lsvEvaluatedEffect.Items.ContainsKey("totalEvaluatedEffects") == true) {
                ListViewItem effectsItem = this.lsvEvaluatedEffect.Items["totalEvaluatedEffects"];

                for (int iSubItem = 1; iSubItem < effectsItem.SubItems.Count; iSubItem++) {
                    for (int iContext = effectsItem.Index - 1; iContext >= 0; iContext--) {

                        if (iContext < this.lsvEvaluatedEffect.Items[iContext].SubItems.Count) {
                            if (String.Compare(this.lsvEvaluatedEffect.Items[iContext].SubItems[iSubItem].Text, "-") != 0) {
                                effectsItem.SubItems[iSubItem].Text = this.lsvEvaluatedEffect.Items[iContext].SubItems[iSubItem].Text;
                                break;
                            }
                            else {
                                effectsItem.SubItems[iSubItem].Text = "Default";
                            }
                        }
                    }
                }
            }
        }

        private void SetLevelVariablesToEffects(LevelVariable lvRequestedContext, List<LevelVariable> lstReturnedValues) {

            if (this.lsvEvaluatedEffect.Items.ContainsKey(lvRequestedContext.Context.ToString()) == true) {
                ListViewItem item = this.lsvEvaluatedEffect.Items[lvRequestedContext.Context.ToString()];
                foreach (LevelVariable variable in lstReturnedValues) {
                    if (item.SubItems.ContainsKey(variable.VariableName) == true) {
                        item.SubItems[variable.VariableName].Text = variable.RawValue;
                    }
                }

                foreach (ColumnHeader col in this.lsvEvaluatedEffect.Columns) {
                    col.Width = -2;
                }

                this.UpdateTotalEffects();
            }
        }

        private ListViewItem CreateVariableEffectItem(string name, string text, Font font) {

            ListViewItem newItem;

            if (this.lsvEvaluatedEffect.Items.ContainsKey(name) == false) {

                newItem = new ListViewItem();
                newItem.Name = name;
                newItem.Text = text;
                newItem.UseItemStyleForSubItems = true;
                newItem.Font = font;

                ListViewItem.ListViewSubItem newSubItem = new ListViewItem.ListViewSubItem(newItem, "-");
                newSubItem.Name = "tickets";
                newItem.SubItems.Add(newSubItem);

                newSubItem = new ListViewItem.ListViewSubItem(newItem, "-");
                newSubItem.Name = "ticketBleedSpeed";
                newItem.SubItems.Add(newSubItem);

                newSubItem = new ListViewItem.ListViewSubItem(newItem, "-");
                newSubItem.Name = "vehicleSpawnRate";
                newItem.SubItems.Add(newSubItem);

                newSubItem = new ListViewItem.ListViewSubItem(newItem, "-");
                newSubItem.Name = "vehiclesDisabled";
                newItem.SubItems.Add(newSubItem);

                newSubItem = new ListViewItem.ListViewSubItem(newItem, "-");
                newSubItem.Name = "startDelay";
                newItem.SubItems.Add(newSubItem);

                newSubItem = new ListViewItem.ListViewSubItem(newItem, "-");
                newSubItem.Name = "respawnDelay";
                newItem.SubItems.Add(newSubItem);
            }
            else {
                newItem = this.lsvEvaluatedEffect.Items[name];
            }

            return newItem;
        }

        private void AddLevelVariablesToEffects(LevelVariable lvRequestedContext, List<LevelVariable> lstReturnedValues) {

            string strFriendlyContextName = lvRequestedContext.Context.ToString();

            if (lvRequestedContext.Context.ContextType == LevelVariableContextType.All) {
                strFriendlyContextName = this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.items.all");
            }
            else if (lvRequestedContext.Context.ContextType == LevelVariableContextType.GameMode) {
                strFriendlyContextName = this.Client.GetFriendlyGamemode(lvRequestedContext.Context.ContextTarget);
            }
            else if (lvRequestedContext.Context.ContextType == LevelVariableContextType.Level) {
                strFriendlyContextName = this.Client.GetFriendlyMapname(lvRequestedContext.Context.ContextTarget);
            }

            ListViewItem newItem = this.CreateVariableEffectItem(lvRequestedContext.Context.ToString(), strFriendlyContextName, this.Font);

            if (this.lsvEvaluatedEffect.Items.ContainsKey(newItem.Name) == false) {
                this.lsvEvaluatedEffect.Items.Add(newItem);
            }

            this.SetLevelVariablesToEffects(lvRequestedContext, lstReturnedValues);
        }

        private bool isApplicableContext(LevelVariable lvRequestedContext) {
            LevelVariableContext selectedContext = this.GetSelectedContext();

            bool returnIsApplicableContext = false;

            if (lvRequestedContext.Context.CompareTo(selectedContext) == 0) {
                returnIsApplicableContext = true;
            }
            else if (lvRequestedContext.Context.ContextType == LevelVariableContextType.All) {
                returnIsApplicableContext = true;
            }
            else if (lvRequestedContext.Context.ContextType == LevelVariableContextType.GameMode &&
                selectedContext.ContextType == LevelVariableContextType.Level) {

                string targetPlaylist = this.Client.GetPlaylistByMapname(selectedContext.ContextTarget);

                if (String.Compare(lvRequestedContext.Context.ContextTarget, targetPlaylist, true) == 0) {
                    returnIsApplicableContext = true;
                }
            }

            return returnIsApplicableContext;
        }

        private void ValidateAddLevelVariablesToEffects(LevelVariable lvRequestedContext, List<LevelVariable> lstReturnedValues) {
            LevelVariableContext selectedContext = this.GetSelectedContext();

            if (this.isApplicableContext(lvRequestedContext) == true) {
                this.AddLevelVariablesToEffects(lvRequestedContext, lstReturnedValues);

                if (lvRequestedContext.Context.CompareTo(selectedContext) == 0) {

                    if (this.lsvEvaluatedEffect.Items.ContainsKey("totalEvaluatedEffects") == false) {
                        this.lsvEvaluatedEffect.Items.Add(this.CreateVariableEffectItem("totalEvaluatedEffects", this.Language.GetLocalized("uscServerSettingsPanel.lsvEvaluatedEffect.items.totalEvaluatedEffects"), new Font(this.Font, FontStyle.Bold)));
                    }

                    this.UpdateTotalEffects();
                }
            }

            foreach (ColumnHeader col in this.lsvEvaluatedEffect.Columns) {
                col.Width = -2;
            }
        }

        #endregion

        private void m_prcClient_LevelVariablesList(FrostbiteClient sender, LevelVariable lvRequestedContext, List<LevelVariable> lstReturnedValues) {
            LevelVariableContext selectedContext = this.GetSelectedContext();

            if (selectedContext.CompareTo(lvRequestedContext.Context) == 0) {

                this.SetCurrentSelectedLevelVariables(lstReturnedValues);
            }

            this.ValidateAddLevelVariablesToEffects(lvRequestedContext, lstReturnedValues);
        }

        #region Clear Level Settings

        private void m_prcClient_LevelVariablesClear(FrostbiteClient sender, LevelVariable lvRequestedContext) {

            LevelVariableContext selectedContext = this.GetSelectedContext();

            if (selectedContext.CompareTo(lvRequestedContext.Context) == 0) {
                this.GetSelectedLevelVariables();

                this.OnSettingResponse("levelvars.clear", true, true);
            }
        }

        private void btnClearLevelSettings_Click(object sender, EventArgs e) {
            this.WaitForSettingResponse("levelvars.clear", null);

            LevelVariableContext selectedContext = this.GetSelectedContext();

            if (this.Client != null && this.Client.Game != null) {
                this.Client.Game.SendLevelVarsClearPacket(selectedContext);

                //if (selectedContext.ContextTarget.Length > 0) {
                //    this.SendCommand("levelVars.clear", selectedContext.ContextType.ToString().ToLower(), selectedContext.ContextTarget);
                //}
                //else {
                //    this.SendCommand("levelVars.clear", selectedContext.ContextType.ToString().ToLower());
                //}
            }
        }

        #endregion

        #region Set level variables

        private void m_prcClient_LevelVariablesSet(FrostbiteClient sender, LevelVariable lvRequestedContext) {
            LevelVariableContext selectedContext = this.GetSelectedContext();

            // If they are an exact match (we or another account is editing what we are editing)
            if (selectedContext.CompareTo(lvRequestedContext.Context) == 0) {
                this.SetLevelVariable(lvRequestedContext);
            }

            // If the variable applies to our currently editing context.
            if (this.isApplicableContext(lvRequestedContext) == true) {
                this.SetLevelVariablesToEffects(lvRequestedContext, new List<LevelVariable>() { lvRequestedContext });
            }
        }

        private void lnkLevelTickets_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.numLevelTickets.Focus();
            this.WaitForSettingResponse("levelvars.set tickets", (decimal)100);

            this.SetLevelVariablesByContext(this.GetSelectedContext(), "tickets", this.numLevelTickets.Value.ToString());
        }

        private void lnkLevelTicketBleedSpeed_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.numLevelTicketBleedSpeed.Focus();
            this.WaitForSettingResponse("levelvars.set ticketbleedspeed", (decimal)100);

            this.SetLevelVariablesByContext(this.GetSelectedContext(), "ticketBleedSpeed", this.numLevelTicketBleedSpeed.Value.ToString());
        }

        private void lnkLevelVehicleSpawnRate_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.numLevelVehicleSpawnRate.Focus();
            this.WaitForSettingResponse("levelvars.set vehiclespawnrate", (decimal)100);

            this.SetLevelVariablesByContext(this.GetSelectedContext(), "vehicleSpawnRate", this.numLevelVehicleSpawnRate.Value.ToString());
        }

        private void lnkLevelStartDelay_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.numLevelStartDelay.Focus();
            this.WaitForSettingResponse("levelvars.set startdelay", (decimal)100);

            this.SetLevelVariablesByContext(this.GetSelectedContext(), "startDelay", this.numLevelStartDelay.Value.ToString());
        }

        private void lnkLevelRespawnDelay_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.numLevelRespawnDelay.Focus();
            this.WaitForSettingResponse("levelvars.set respawndelay", (decimal)100);

            this.SetLevelVariablesByContext(this.GetSelectedContext(), "respawnDelay", this.numLevelRespawnDelay.Value.ToString());
        }

        private void chkLevelVehiclesDisabled_CheckedChanged(object sender, EventArgs e) {
            if (this.IgnoreEvents == false && this.AsyncSettingControls["levelvars.set vehiclesdisabled"].IgnoreEvent == false) {
                this.WaitForSettingResponse("levelvars.set vehiclesdisabled", !this.chkLevelVehiclesDisabled.Checked);

                this.SetLevelVariablesByContext(this.GetSelectedContext(), "vehiclesDisabled", Packet.bltos(this.chkLevelVehiclesDisabled.Checked));

            }
        }

        #endregion

        #endregion
    }*/

    /*partial class uscServerSettingsLevelVariables {
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
            this.lsvEvaluatedEffect = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.context = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.tickets = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.ticketBleedSpeed = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.vehicleSpawnRate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.vehiclesDisabled = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.startDelay = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.respawnDelay = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.picClearLevelSettings = new System.Windows.Forms.PictureBox();
            this.lblLevelEvaluatedEffects = new System.Windows.Forms.Label();
            this.pictureBox7 = new System.Windows.Forms.PictureBox();
            this.chkLevelVehiclesDisabled = new System.Windows.Forms.CheckBox();
            this.picLevelVehiclesDisabled = new System.Windows.Forms.PictureBox();
            this.lnkLevelRespawnDelay = new System.Windows.Forms.LinkLabel();
            this.picLevelRespawnDelay = new System.Windows.Forms.PictureBox();
            this.numLevelRespawnDelay = new System.Windows.Forms.NumericUpDown();
            this.lblLevelRespawnDelay = new System.Windows.Forms.Label();
            this.lnkLevelStartDelay = new System.Windows.Forms.LinkLabel();
            this.picLevelStartDelay = new System.Windows.Forms.PictureBox();
            this.numLevelStartDelay = new System.Windows.Forms.NumericUpDown();
            this.lblLevelStartDelay = new System.Windows.Forms.Label();
            this.lnkLevelVehicleSpawnRate = new System.Windows.Forms.LinkLabel();
            this.picLevelVehicleSpawnRate = new System.Windows.Forms.PictureBox();
            this.numLevelVehicleSpawnRate = new System.Windows.Forms.NumericUpDown();
            this.lblLevelVehicleSpawnRate = new System.Windows.Forms.Label();
            this.lnkLevelTicketBleedSpeed = new System.Windows.Forms.LinkLabel();
            this.picLevelTicketBleedSpeed = new System.Windows.Forms.PictureBox();
            this.numLevelTicketBleedSpeed = new System.Windows.Forms.NumericUpDown();
            this.lblLevelTicketBleedSpeed = new System.Windows.Forms.Label();
            this.lnkLevelTickets = new System.Windows.Forms.LinkLabel();
            this.picLevelTickets = new System.Windows.Forms.PictureBox();
            this.numLevelTickets = new System.Windows.Forms.NumericUpDown();
            this.lblLevelTickets = new System.Windows.Forms.Label();
            this.btnClearLevelSettings = new System.Windows.Forms.Button();
            this.cboSettingsGamemodes = new System.Windows.Forms.ComboBox();
            this.cboSettingsLevels = new System.Windows.Forms.ComboBox();
            this.rdoSettingsLevelContextLevel = new System.Windows.Forms.RadioButton();
            this.rdoSettingsLevelContextGamemode = new System.Windows.Forms.RadioButton();
            this.rdoSettingsLevelContextAll = new System.Windows.Forms.RadioButton();
            this.lblSettingsLevelContext = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.picClearLevelSettings)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox7)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelVehiclesDisabled)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelRespawnDelay)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelRespawnDelay)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelStartDelay)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelStartDelay)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelVehicleSpawnRate)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelVehicleSpawnRate)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelTicketBleedSpeed)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelTicketBleedSpeed)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelTickets)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelTickets)).BeginInit();
            this.SuspendLayout();
            //
            // lsvEvaluatedEffect
            //
            this.lsvEvaluatedEffect.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lsvEvaluatedEffect.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.context,
            this.tickets,
            this.ticketBleedSpeed,
            this.vehicleSpawnRate,
            this.vehiclesDisabled,
            this.startDelay,
            this.respawnDelay});
            this.lsvEvaluatedEffect.GridLines = true;
            this.lsvEvaluatedEffect.Location = new System.Drawing.Point(47, 278);
            this.lsvEvaluatedEffect.Name = "lsvEvaluatedEffect";
            this.lsvEvaluatedEffect.Size = new System.Drawing.Size(901, 258);
            this.lsvEvaluatedEffect.TabIndex = 289;
            this.lsvEvaluatedEffect.UseCompatibleStateImageBehavior = false;
            this.lsvEvaluatedEffect.View = System.Windows.Forms.View.Details;
            //
            // context
            //
            this.context.Text = "Context";
            //
            // tickets
            //
            this.tickets.Text = "Tickets";
            //
            // ticketBleedSpeed
            //
            this.ticketBleedSpeed.Text = "Ticket Bleed Speed";
            this.ticketBleedSpeed.Width = 120;
            //
            // vehicleSpawnRate
            //
            this.vehicleSpawnRate.Text = "Vehicle Spawn Rate";
            this.vehicleSpawnRate.Width = 119;
            //
            // vehiclesDisabled
            //
            this.vehiclesDisabled.Text = "Vehicles Disabled";
            this.vehiclesDisabled.Width = 108;
            //
            // startDelay
            //
            this.startDelay.Text = "Start Delay";
            this.startDelay.Width = 78;
            //
            // respawnDelay
            //
            this.respawnDelay.Text = "Respawn Delay";
            this.respawnDelay.Width = 104;
            //
            // picClearLevelSettings
            //
            this.picClearLevelSettings.Location = new System.Drawing.Point(600, 35);
            this.picClearLevelSettings.Name = "picClearLevelSettings";
            this.picClearLevelSettings.Size = new System.Drawing.Size(16, 16);
            this.picClearLevelSettings.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picClearLevelSettings.TabIndex = 288;
            this.picClearLevelSettings.TabStop = false;
            //
            // lblLevelEvaluatedEffects
            //
            this.lblLevelEvaluatedEffects.AutoSize = true;
            this.lblLevelEvaluatedEffects.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLevelEvaluatedEffects.Location = new System.Drawing.Point(43, 257);
            this.lblLevelEvaluatedEffects.Name = "lblLevelEvaluatedEffects";
            this.lblLevelEvaluatedEffects.Size = new System.Drawing.Size(221, 15);
            this.lblLevelEvaluatedEffects.TabIndex = 287;
            this.lblLevelEvaluatedEffects.Text = "Evaluated effects for selected context";
            //
            // pictureBox7
            //
            this.pictureBox7.Location = new System.Drawing.Point(18, 258);
            this.pictureBox7.Name = "pictureBox7";
            this.pictureBox7.Size = new System.Drawing.Size(16, 16);
            this.pictureBox7.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox7.TabIndex = 286;
            this.pictureBox7.TabStop = false;
            //
            // chkLevelVehiclesDisabled
            //
            this.chkLevelVehiclesDisabled.AutoSize = true;
            this.chkLevelVehiclesDisabled.Location = new System.Drawing.Point(538, 180);
            this.chkLevelVehiclesDisabled.Name = "chkLevelVehiclesDisabled";
            this.chkLevelVehiclesDisabled.Size = new System.Drawing.Size(116, 19);
            this.chkLevelVehiclesDisabled.TabIndex = 285;
            this.chkLevelVehiclesDisabled.Text = "Vehicles disabled";
            this.chkLevelVehiclesDisabled.UseVisualStyleBackColor = true;
            this.chkLevelVehiclesDisabled.Click += new System.EventHandler(this.chkLevelVehiclesDisabled_CheckedChanged);
            //
            // picLevelVehiclesDisabled
            //
            this.picLevelVehiclesDisabled.Location = new System.Drawing.Point(511, 182);
            this.picLevelVehiclesDisabled.Name = "picLevelVehiclesDisabled";
            this.picLevelVehiclesDisabled.Size = new System.Drawing.Size(16, 16);
            this.picLevelVehiclesDisabled.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLevelVehiclesDisabled.TabIndex = 284;
            this.picLevelVehiclesDisabled.TabStop = false;
            //
            // lnkLevelRespawnDelay
            //
            this.lnkLevelRespawnDelay.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelRespawnDelay.AutoSize = true;
            this.lnkLevelRespawnDelay.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkLevelRespawnDelay.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelRespawnDelay.Location = new System.Drawing.Point(322, 205);
            this.lnkLevelRespawnDelay.Name = "lnkLevelRespawnDelay";
            this.lnkLevelRespawnDelay.Size = new System.Drawing.Size(38, 15);
            this.lnkLevelRespawnDelay.TabIndex = 283;
            this.lnkLevelRespawnDelay.TabStop = true;
            this.lnkLevelRespawnDelay.Text = "Apply";
            this.lnkLevelRespawnDelay.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkLevelRespawnDelay_LinkClicked);
            //
            // picLevelRespawnDelay
            //
            this.picLevelRespawnDelay.Location = new System.Drawing.Point(230, 182);
            this.picLevelRespawnDelay.Name = "picLevelRespawnDelay";
            this.picLevelRespawnDelay.Size = new System.Drawing.Size(16, 16);
            this.picLevelRespawnDelay.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLevelRespawnDelay.TabIndex = 282;
            this.picLevelRespawnDelay.TabStop = false;
            //
            // numLevelRespawnDelay
            //
            this.numLevelRespawnDelay.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numLevelRespawnDelay.Location = new System.Drawing.Point(259, 203);
            this.numLevelRespawnDelay.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numLevelRespawnDelay.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numLevelRespawnDelay.Name = "numLevelRespawnDelay";
            this.numLevelRespawnDelay.Size = new System.Drawing.Size(56, 23);
            this.numLevelRespawnDelay.TabIndex = 281;
            this.numLevelRespawnDelay.Value = new decimal(new int[] {
            20,
            0,
            0,
            0});
            //
            // lblLevelRespawnDelay
            //
            this.lblLevelRespawnDelay.AutoSize = true;
            this.lblLevelRespawnDelay.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLevelRespawnDelay.Location = new System.Drawing.Point(255, 182);
            this.lblLevelRespawnDelay.Name = "lblLevelRespawnDelay";
            this.lblLevelRespawnDelay.Size = new System.Drawing.Size(85, 15);
            this.lblLevelRespawnDelay.TabIndex = 280;
            this.lblLevelRespawnDelay.Text = "Respawn delay";
            //
            // lnkLevelStartDelay
            //
            this.lnkLevelStartDelay.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelStartDelay.AutoSize = true;
            this.lnkLevelStartDelay.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkLevelStartDelay.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelStartDelay.Location = new System.Drawing.Point(110, 205);
            this.lnkLevelStartDelay.Name = "lnkLevelStartDelay";
            this.lnkLevelStartDelay.Size = new System.Drawing.Size(38, 15);
            this.lnkLevelStartDelay.TabIndex = 279;
            this.lnkLevelStartDelay.TabStop = true;
            this.lnkLevelStartDelay.Text = "Apply";
            this.lnkLevelStartDelay.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkLevelStartDelay_LinkClicked);
            //
            // picLevelStartDelay
            //
            this.picLevelStartDelay.Location = new System.Drawing.Point(18, 182);
            this.picLevelStartDelay.Name = "picLevelStartDelay";
            this.picLevelStartDelay.Size = new System.Drawing.Size(16, 16);
            this.picLevelStartDelay.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLevelStartDelay.TabIndex = 278;
            this.picLevelStartDelay.TabStop = false;
            //
            // numLevelStartDelay
            //
            this.numLevelStartDelay.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numLevelStartDelay.Location = new System.Drawing.Point(47, 203);
            this.numLevelStartDelay.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numLevelStartDelay.Name = "numLevelStartDelay";
            this.numLevelStartDelay.Size = new System.Drawing.Size(56, 23);
            this.numLevelStartDelay.TabIndex = 277;
            this.numLevelStartDelay.Value = new decimal(new int[] {
            10,
            0,
            0,
            0});
            //
            // lblLevelStartDelay
            //
            this.lblLevelStartDelay.AutoSize = true;
            this.lblLevelStartDelay.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLevelStartDelay.Location = new System.Drawing.Point(43, 182);
            this.lblLevelStartDelay.Name = "lblLevelStartDelay";
            this.lblLevelStartDelay.Size = new System.Drawing.Size(62, 15);
            this.lblLevelStartDelay.TabIndex = 276;
            this.lblLevelStartDelay.Text = "Start delay";
            //
            // lnkLevelVehicleSpawnRate
            //
            this.lnkLevelVehicleSpawnRate.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelVehicleSpawnRate.AutoSize = true;
            this.lnkLevelVehicleSpawnRate.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkLevelVehicleSpawnRate.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelVehicleSpawnRate.Location = new System.Drawing.Point(603, 135);
            this.lnkLevelVehicleSpawnRate.Name = "lnkLevelVehicleSpawnRate";
            this.lnkLevelVehicleSpawnRate.Size = new System.Drawing.Size(38, 15);
            this.lnkLevelVehicleSpawnRate.TabIndex = 275;
            this.lnkLevelVehicleSpawnRate.TabStop = true;
            this.lnkLevelVehicleSpawnRate.Text = "Apply";
            this.lnkLevelVehicleSpawnRate.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkLevelVehicleSpawnRate_LinkClicked);
            //
            // picLevelVehicleSpawnRate
            //
            this.picLevelVehicleSpawnRate.Location = new System.Drawing.Point(511, 112);
            this.picLevelVehicleSpawnRate.Name = "picLevelVehicleSpawnRate";
            this.picLevelVehicleSpawnRate.Size = new System.Drawing.Size(16, 16);
            this.picLevelVehicleSpawnRate.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLevelVehicleSpawnRate.TabIndex = 274;
            this.picLevelVehicleSpawnRate.TabStop = false;
            //
            // numLevelVehicleSpawnRate
            //
            this.numLevelVehicleSpawnRate.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numLevelVehicleSpawnRate.Increment = new decimal(new int[] {
            5,
            0,
            0,
            0});
            this.numLevelVehicleSpawnRate.Location = new System.Drawing.Point(540, 133);
            this.numLevelVehicleSpawnRate.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numLevelVehicleSpawnRate.Name = "numLevelVehicleSpawnRate";
            this.numLevelVehicleSpawnRate.Size = new System.Drawing.Size(56, 23);
            this.numLevelVehicleSpawnRate.TabIndex = 273;
            this.numLevelVehicleSpawnRate.Value = new decimal(new int[] {
            100,
            0,
            0,
            0});
            //
            // lblLevelVehicleSpawnRate
            //
            this.lblLevelVehicleSpawnRate.AutoSize = true;
            this.lblLevelVehicleSpawnRate.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLevelVehicleSpawnRate.Location = new System.Drawing.Point(537, 112);
            this.lblLevelVehicleSpawnRate.Name = "lblLevelVehicleSpawnRate";
            this.lblLevelVehicleSpawnRate.Size = new System.Drawing.Size(105, 15);
            this.lblLevelVehicleSpawnRate.TabIndex = 272;
            this.lblLevelVehicleSpawnRate.Text = "Vehicle spawn rate";
            //
            // lnkLevelTicketBleedSpeed
            //
            this.lnkLevelTicketBleedSpeed.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelTicketBleedSpeed.AutoSize = true;
            this.lnkLevelTicketBleedSpeed.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkLevelTicketBleedSpeed.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelTicketBleedSpeed.Location = new System.Drawing.Point(322, 135);
            this.lnkLevelTicketBleedSpeed.Name = "lnkLevelTicketBleedSpeed";
            this.lnkLevelTicketBleedSpeed.Size = new System.Drawing.Size(38, 15);
            this.lnkLevelTicketBleedSpeed.TabIndex = 271;
            this.lnkLevelTicketBleedSpeed.TabStop = true;
            this.lnkLevelTicketBleedSpeed.Text = "Apply";
            this.lnkLevelTicketBleedSpeed.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkLevelTicketBleedSpeed_LinkClicked);
            //
            // picLevelTicketBleedSpeed
            //
            this.picLevelTicketBleedSpeed.Location = new System.Drawing.Point(230, 112);
            this.picLevelTicketBleedSpeed.Name = "picLevelTicketBleedSpeed";
            this.picLevelTicketBleedSpeed.Size = new System.Drawing.Size(16, 16);
            this.picLevelTicketBleedSpeed.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLevelTicketBleedSpeed.TabIndex = 270;
            this.picLevelTicketBleedSpeed.TabStop = false;
            //
            // numLevelTicketBleedSpeed
            //
            this.numLevelTicketBleedSpeed.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numLevelTicketBleedSpeed.Increment = new decimal(new int[] {
            5,
            0,
            0,
            0});
            this.numLevelTicketBleedSpeed.Location = new System.Drawing.Point(259, 133);
            this.numLevelTicketBleedSpeed.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numLevelTicketBleedSpeed.Name = "numLevelTicketBleedSpeed";
            this.numLevelTicketBleedSpeed.Size = new System.Drawing.Size(56, 23);
            this.numLevelTicketBleedSpeed.TabIndex = 269;
            this.numLevelTicketBleedSpeed.Value = new decimal(new int[] {
            100,
            0,
            0,
            0});
            //
            // lblLevelTicketBleedSpeed
            //
            this.lblLevelTicketBleedSpeed.AutoSize = true;
            this.lblLevelTicketBleedSpeed.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLevelTicketBleedSpeed.Location = new System.Drawing.Point(255, 112);
            this.lblLevelTicketBleedSpeed.Name = "lblLevelTicketBleedSpeed";
            this.lblLevelTicketBleedSpeed.Size = new System.Drawing.Size(105, 15);
            this.lblLevelTicketBleedSpeed.TabIndex = 268;
            this.lblLevelTicketBleedSpeed.Text = "Ticket bleed speed";
            //
            // lnkLevelTickets
            //
            this.lnkLevelTickets.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelTickets.AutoSize = true;
            this.lnkLevelTickets.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkLevelTickets.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkLevelTickets.Location = new System.Drawing.Point(110, 135);
            this.lnkLevelTickets.Name = "lnkLevelTickets";
            this.lnkLevelTickets.Size = new System.Drawing.Size(38, 15);
            this.lnkLevelTickets.TabIndex = 267;
            this.lnkLevelTickets.TabStop = true;
            this.lnkLevelTickets.Text = "Apply";
            this.lnkLevelTickets.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkLevelTickets_LinkClicked);
            //
            // picLevelTickets
            //
            this.picLevelTickets.Location = new System.Drawing.Point(18, 112);
            this.picLevelTickets.Name = "picLevelTickets";
            this.picLevelTickets.Size = new System.Drawing.Size(16, 16);
            this.picLevelTickets.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picLevelTickets.TabIndex = 266;
            this.picLevelTickets.TabStop = false;
            //
            // numLevelTickets
            //
            this.numLevelTickets.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numLevelTickets.Increment = new decimal(new int[] {
            5,
            0,
            0,
            0});
            this.numLevelTickets.Location = new System.Drawing.Point(47, 133);
            this.numLevelTickets.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numLevelTickets.Name = "numLevelTickets";
            this.numLevelTickets.Size = new System.Drawing.Size(56, 23);
            this.numLevelTickets.TabIndex = 265;
            this.numLevelTickets.Value = new decimal(new int[] {
            100,
            0,
            0,
            0});
            //
            // lblLevelTickets
            //
            this.lblLevelTickets.AutoSize = true;
            this.lblLevelTickets.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLevelTickets.Location = new System.Drawing.Point(43, 112);
            this.lblLevelTickets.Name = "lblLevelTickets";
            this.lblLevelTickets.Size = new System.Drawing.Size(44, 15);
            this.lblLevelTickets.TabIndex = 264;
            this.lblLevelTickets.Text = "Tickets";
            //
            // btnClearLevelSettings
            //
            this.btnClearLevelSettings.Location = new System.Drawing.Point(622, 30);
            this.btnClearLevelSettings.Name = "btnClearLevelSettings";
            this.btnClearLevelSettings.Size = new System.Drawing.Size(114, 27);
            this.btnClearLevelSettings.TabIndex = 263;
            this.btnClearLevelSettings.Text = "Default";
            this.btnClearLevelSettings.UseVisualStyleBackColor = true;
            this.btnClearLevelSettings.Click += new System.EventHandler(this.btnClearLevelSettings_Click);
            //
            // cboSettingsGamemodes
            //
            this.cboSettingsGamemodes.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawFixed;
            this.cboSettingsGamemodes.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboSettingsGamemodes.Enabled = false;
            this.cboSettingsGamemodes.FormattingEnabled = true;
            this.cboSettingsGamemodes.Location = new System.Drawing.Point(131, 61);
            this.cboSettingsGamemodes.Name = "cboSettingsGamemodes";
            this.cboSettingsGamemodes.Size = new System.Drawing.Size(166, 24);
            this.cboSettingsGamemodes.TabIndex = 262;
            this.cboSettingsGamemodes.DrawItem += new System.Windows.Forms.DrawItemEventHandler(this.cboSettingsGamemodes_DrawItem);
            this.cboSettingsGamemodes.SelectedIndexChanged += new System.EventHandler(this.cboSettingsGamemodes_SelectedIndexChanged);
            //
            // cboSettingsLevels
            //
            this.cboSettingsLevels.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawFixed;
            this.cboSettingsLevels.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboSettingsLevels.Enabled = false;
            this.cboSettingsLevels.FormattingEnabled = true;
            this.cboSettingsLevels.Location = new System.Drawing.Point(315, 61);
            this.cboSettingsLevels.Name = "cboSettingsLevels";
            this.cboSettingsLevels.Size = new System.Drawing.Size(262, 24);
            this.cboSettingsLevels.TabIndex = 261;
            this.cboSettingsLevels.DrawItem += new System.Windows.Forms.DrawItemEventHandler(this.cboSettingsLevels_DrawItem);
            this.cboSettingsLevels.SelectedIndexChanged += new System.EventHandler(this.cboSettingsLevels_SelectedIndexChanged);
            //
            // rdoSettingsLevelContextLevel
            //
            this.rdoSettingsLevelContextLevel.AutoSize = true;
            this.rdoSettingsLevelContextLevel.Location = new System.Drawing.Point(315, 32);
            this.rdoSettingsLevelContextLevel.Name = "rdoSettingsLevelContextLevel";
            this.rdoSettingsLevelContextLevel.Size = new System.Drawing.Size(52, 19);
            this.rdoSettingsLevelContextLevel.TabIndex = 260;
            this.rdoSettingsLevelContextLevel.TabStop = true;
            this.rdoSettingsLevelContextLevel.Text = "Level";
            this.rdoSettingsLevelContextLevel.UseVisualStyleBackColor = true;
            this.rdoSettingsLevelContextLevel.CheckedChanged += new System.EventHandler(this.rdoSettingsLevelContextLevel_CheckedChanged);
            //
            // rdoSettingsLevelContextGamemode
            //
            this.rdoSettingsLevelContextGamemode.AutoSize = true;
            this.rdoSettingsLevelContextGamemode.Location = new System.Drawing.Point(131, 32);
            this.rdoSettingsLevelContextGamemode.Name = "rdoSettingsLevelContextGamemode";
            this.rdoSettingsLevelContextGamemode.Size = new System.Drawing.Size(87, 19);
            this.rdoSettingsLevelContextGamemode.TabIndex = 259;
            this.rdoSettingsLevelContextGamemode.TabStop = true;
            this.rdoSettingsLevelContextGamemode.Text = "Gamemode";
            this.rdoSettingsLevelContextGamemode.UseVisualStyleBackColor = true;
            this.rdoSettingsLevelContextGamemode.CheckedChanged += new System.EventHandler(this.rdoSettingsLevelContextGamemode_CheckedChanged);
            //
            // rdoSettingsLevelContextAll
            //
            this.rdoSettingsLevelContextAll.AutoSize = true;
            this.rdoSettingsLevelContextAll.Location = new System.Drawing.Point(17, 32);
            this.rdoSettingsLevelContextAll.Name = "rdoSettingsLevelContextAll";
            this.rdoSettingsLevelContextAll.Size = new System.Drawing.Size(59, 19);
            this.rdoSettingsLevelContextAll.TabIndex = 258;
            this.rdoSettingsLevelContextAll.TabStop = true;
            this.rdoSettingsLevelContextAll.Text = "Global";
            this.rdoSettingsLevelContextAll.UseVisualStyleBackColor = true;
            this.rdoSettingsLevelContextAll.CheckedChanged += new System.EventHandler(this.rdoSettingsLevelContextAll_CheckedChanged);
            //
            // lblSettingsLevelContext
            //
            this.lblSettingsLevelContext.AutoSize = true;
            this.lblSettingsLevelContext.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSettingsLevelContext.Location = new System.Drawing.Point(14, 12);
            this.lblSettingsLevelContext.Name = "lblSettingsLevelContext";
            this.lblSettingsLevelContext.Size = new System.Drawing.Size(52, 15);
            this.lblSettingsLevelContext.TabIndex = 257;
            this.lblSettingsLevelContext.Text = "Context";
            //
            // uscServerSettingsLevelVariables
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lsvEvaluatedEffect);
            this.Controls.Add(this.picClearLevelSettings);
            this.Controls.Add(this.lblLevelEvaluatedEffects);
            this.Controls.Add(this.pictureBox7);
            this.Controls.Add(this.chkLevelVehiclesDisabled);
            this.Controls.Add(this.picLevelVehiclesDisabled);
            this.Controls.Add(this.lnkLevelRespawnDelay);
            this.Controls.Add(this.picLevelRespawnDelay);
            this.Controls.Add(this.numLevelRespawnDelay);
            this.Controls.Add(this.lblLevelRespawnDelay);
            this.Controls.Add(this.lnkLevelStartDelay);
            this.Controls.Add(this.picLevelStartDelay);
            this.Controls.Add(this.numLevelStartDelay);
            this.Controls.Add(this.lblLevelStartDelay);
            this.Controls.Add(this.lnkLevelVehicleSpawnRate);
            this.Controls.Add(this.picLevelVehicleSpawnRate);
            this.Controls.Add(this.numLevelVehicleSpawnRate);
            this.Controls.Add(this.lblLevelVehicleSpawnRate);
            this.Controls.Add(this.lnkLevelTicketBleedSpeed);
            this.Controls.Add(this.picLevelTicketBleedSpeed);
            this.Controls.Add(this.numLevelTicketBleedSpeed);
            this.Controls.Add(this.lblLevelTicketBleedSpeed);
            this.Controls.Add(this.lnkLevelTickets);
            this.Controls.Add(this.picLevelTickets);
            this.Controls.Add(this.numLevelTickets);
            this.Controls.Add(this.lblLevelTickets);
            this.Controls.Add(this.btnClearLevelSettings);
            this.Controls.Add(this.cboSettingsGamemodes);
            this.Controls.Add(this.cboSettingsLevels);
            this.Controls.Add(this.rdoSettingsLevelContextLevel);
            this.Controls.Add(this.rdoSettingsLevelContextGamemode);
            this.Controls.Add(this.rdoSettingsLevelContextAll);
            this.Controls.Add(this.lblSettingsLevelContext);
            this.Name = "uscServerSettingsLevelVariables";
            this.Size = new System.Drawing.Size(952, 540);
            ((System.ComponentModel.ISupportInitialize)(this.picClearLevelSettings)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox7)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelVehiclesDisabled)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelRespawnDelay)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelRespawnDelay)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelStartDelay)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelStartDelay)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelVehicleSpawnRate)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelVehicleSpawnRate)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelTicketBleedSpeed)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelTicketBleedSpeed)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picLevelTickets)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numLevelTickets)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private PRoCon.Controls.ControlsEx.ListViewNF lsvEvaluatedEffect;
        private System.Windows.Forms.ColumnHeader context;
        private System.Windows.Forms.ColumnHeader tickets;
        private System.Windows.Forms.ColumnHeader ticketBleedSpeed;
        private System.Windows.Forms.ColumnHeader vehicleSpawnRate;
        private System.Windows.Forms.ColumnHeader vehiclesDisabled;
        private System.Windows.Forms.ColumnHeader startDelay;
        private System.Windows.Forms.ColumnHeader respawnDelay;
        private System.Windows.Forms.PictureBox picClearLevelSettings;
        private System.Windows.Forms.Label lblLevelEvaluatedEffects;
        private System.Windows.Forms.PictureBox pictureBox7;
        private System.Windows.Forms.CheckBox chkLevelVehiclesDisabled;
        private System.Windows.Forms.PictureBox picLevelVehiclesDisabled;
        private System.Windows.Forms.LinkLabel lnkLevelRespawnDelay;
        private System.Windows.Forms.PictureBox picLevelRespawnDelay;
        private System.Windows.Forms.NumericUpDown numLevelRespawnDelay;
        private System.Windows.Forms.Label lblLevelRespawnDelay;
        private System.Windows.Forms.LinkLabel lnkLevelStartDelay;
        private System.Windows.Forms.PictureBox picLevelStartDelay;
        private System.Windows.Forms.NumericUpDown numLevelStartDelay;
        private System.Windows.Forms.Label lblLevelStartDelay;
        private System.Windows.Forms.LinkLabel lnkLevelVehicleSpawnRate;
        private System.Windows.Forms.PictureBox picLevelVehicleSpawnRate;
        private System.Windows.Forms.NumericUpDown numLevelVehicleSpawnRate;
        private System.Windows.Forms.Label lblLevelVehicleSpawnRate;
        private System.Windows.Forms.LinkLabel lnkLevelTicketBleedSpeed;
        private System.Windows.Forms.PictureBox picLevelTicketBleedSpeed;
        private System.Windows.Forms.NumericUpDown numLevelTicketBleedSpeed;
        private System.Windows.Forms.Label lblLevelTicketBleedSpeed;
        private System.Windows.Forms.LinkLabel lnkLevelTickets;
        private System.Windows.Forms.PictureBox picLevelTickets;
        private System.Windows.Forms.NumericUpDown numLevelTickets;
        private System.Windows.Forms.Label lblLevelTickets;
        private System.Windows.Forms.Button btnClearLevelSettings;
        private System.Windows.Forms.ComboBox cboSettingsGamemodes;
        private System.Windows.Forms.ComboBox cboSettingsLevels;
        private System.Windows.Forms.RadioButton rdoSettingsLevelContextLevel;
        private System.Windows.Forms.RadioButton rdoSettingsLevelContextGamemode;
        private System.Windows.Forms.RadioButton rdoSettingsLevelContextAll;
        private System.Windows.Forms.Label lblSettingsLevelContext;
    }*/
}
