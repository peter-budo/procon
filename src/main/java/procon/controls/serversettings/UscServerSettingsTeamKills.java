package procon.controls.serversettings;

public class UscServerSettingsTeamKills {

    /*public partial class uscServerSettingsTeamKills : uscServerSettings {
        public uscServerSettingsTeamKills() {
            InitializeComponent();

            this.AsyncSettingControls.Add("vars.teamkillcountforkick 0", new AsyncStyleSetting(this.picSettingsTeamkillCountLimit, this.chkSettingsTeamkillCountLimit, new Control[] { this.chkSettingsTeamkillCountLimit }, true));
            this.AsyncSettingControls.Add("vars.teamkillcountforkick", new AsyncStyleSetting(this.picSettingsTeamkillCountLimit, this.numSettingsTeamkillCountLimit, new Control[] { this.numSettingsTeamkillCountLimit, this.lnkSettingsTeamkillCountLimit }, true));

            this.AsyncSettingControls.Add("vars.teamkillvalueforkick 0", new AsyncStyleSetting(this.picSettingsTeamkillValueLimit, this.chkSettingsTeamkillValueLimit, new Control[] { this.chkSettingsTeamkillValueLimit }, true));
            this.AsyncSettingControls.Add("vars.teamkillvalueforkick", new AsyncStyleSetting(this.picSettingsTeamkillValueLimit, this.numSettingsTeamkillValueLimit, new Control[] { this.numSettingsTeamkillValueLimit, this.lnkSettingsTeamkillValueLimit }, true));

            this.AsyncSettingControls.Add("vars.teamkillvalueincrease", new AsyncStyleSetting(this.picSettingsTeamKillValueIncrease, this.numSettingsTeamKillValueIncrease, new Control[] { this.numSettingsTeamKillValueIncrease, this.lnkSettingsTeamKillValueIncrease }, true));
            this.AsyncSettingControls.Add("vars.teamkillvaluedecreasepersecond", new AsyncStyleSetting(this.picTeamKillValueDecreasePerSecond, this.numTeamKillValueDecreasePerSecond, new Control[] { this.numTeamKillValueDecreasePerSecond, this.lnkTeamKillValueDecreasePerSecond }, true));
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            base.SetLocalization(clocLanguage);

            this.chkSettingsTeamkillCountLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkSettingsTeamkillCountLimit");
            this.lnkSettingsTeamkillCountLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsTeamkillCountLimit");

            this.chkSettingsTeamkillValueLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkSettingsTeamkillValueLimit");
            this.lnkSettingsTeamkillValueLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsTeamkillValueLimit");

            this.lblTeamKillValueDecreasePerSecond.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblTeamKillValueDecreasePerSecond");
            this.lnkTeamKillValueDecreasePerSecond.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkTeamKillValueDecreasePerSecond");

            this.lblSettingsTeamKillValueIncrease.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsTeamKillValueIncrease");
            this.lnkSettingsTeamKillValueIncrease.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsTeamKillValueIncrease");

            this.DisplayName = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsTeamKill");

            this.UpdateTeamkillExplanations();
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

            this.Client.Game.TeamKillCountForKick += new FrostbiteClient.LimitHandler(m_prcClient_TeamKillCountForKick);
            this.Client.Game.TeamKillValueForKick += new FrostbiteClient.LimitHandler(m_prcClient_TeamKillValueForKick);
            this.Client.Game.TeamKillValueIncrease += new FrostbiteClient.LimitHandler(m_prcClient_TeamKillValueIncrease);
            this.Client.Game.TeamKillValueDecreasePerSecond += new FrostbiteClient.LimitHandler(m_prcClient_TeamKillValueDecreasePerSecond);
        }

        #region Team Kill Count For Kick

        private int m_iPreviousSuccessTeamKillCountForKick;

        private void m_prcClient_TeamKillCountForKick(FrostbiteClient sender, int limit) {
            this.m_iPreviousSuccessTeamKillCountForKick = limit;

            if (this.m_iPreviousSuccessTeamKillCountForKick == 0) {
                this.OnSettingResponse("vars.teamkillcountforkick 0", true, true);
            }
            else {
                this.OnSettingResponse("vars.teamkillcountforkick", (decimal)this.m_iPreviousSuccessTeamKillCountForKick, true);
            }
        }

        private void chkSettingsTeamkillCountLimit_CheckedChanged(object sender, EventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.pnlSettingsTeamkillCountLimit.Enabled = !this.chkSettingsTeamkillCountLimit.Checked;
                this.pnlSettingsTeamkillCountLimit.Visible = !this.chkSettingsTeamkillCountLimit.Checked;

                if (this.IgnoreEvents == false && this.AsyncSettingControls["vars.teamkillcountforkick 0"].IgnoreEvent == false) {
                    if (this.chkSettingsTeamkillCountLimit.Checked == true) {
                        this.WaitForSettingResponse("vars.teamkillcountforkick 0", !this.chkSettingsTeamkillCountLimit.Checked);

                        this.Client.Game.SendSetVarsTeamKillCountForKickPacket(0);
                    }
                }
            }
        }

        private void lnkSettingsTeamkillCountLimit_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.numSettingsTeamkillCountLimit.Focus();
                this.WaitForSettingResponse("vars.teamkillcountforkick", (decimal)this.m_iPreviousSuccessTeamKillCountForKick);

                this.Client.Game.SendSetVarsTeamKillCountForKickPacket((int)this.numSettingsTeamkillCountLimit.Value);
                //this.SendCommand("vars.teamKillCountForKick", this.numSettingsTeamkillCountLimit.Value.ToString());
            }
        }

        #endregion

        #region Team Killing

        private int m_iPreviousSuccessTeamKillValueForKick;

        private void m_prcClient_TeamKillValueForKick(FrostbiteClient sender, int limit) {
            this.m_iPreviousSuccessTeamKillValueForKick = limit;

            if (this.m_iPreviousSuccessTeamKillValueForKick == 0) {
                this.OnSettingResponse("vars.teamkillvalueforkick 0", true, true);
            }
            else {
                this.OnSettingResponse("vars.teamkillvalueforkick", (decimal)this.m_iPreviousSuccessTeamKillValueForKick, true);
            }
        }

        private void chkSettingsTeamkillValueLimit_CheckedChanged(object sender, EventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.pnlSettingsTeamkillValueLimit.Enabled = !this.chkSettingsTeamkillValueLimit.Checked;
                this.pnlSettingsTeamkillValueLimit.Visible = !this.chkSettingsTeamkillValueLimit.Checked;

                if (this.IgnoreEvents == false && this.AsyncSettingControls["vars.teamkillvalueforkick 0"].IgnoreEvent == false) {
                    if (this.chkSettingsTeamkillValueLimit.Checked == true) {
                        this.WaitForSettingResponse("vars.teamkillvalueforkick 0", !this.chkSettingsTeamkillValueLimit.Checked);

                        this.Client.Game.SendSetVarsTeamKillValueForKickPacket(0);

                        //this.SendCommand("vars.teamKillValueForKick", "0");
                    }
                }
            }
        }

        private void lnkSettingsTeamkillValueLimit_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.numSettingsTeamkillValueLimit.Focus();
                this.WaitForSettingResponse("vars.teamkillvalueforkick", (decimal)this.m_iPreviousSuccessTeamKillValueForKick);

                this.Client.Game.SendSetVarsTeamKillValueForKickPacket((int)this.numSettingsTeamkillValueLimit.Value);
            }
        }


        private int m_iPreviousSuccessTeamKillValueIncrease;

        void m_prcClient_TeamKillValueIncrease(FrostbiteClient sender, int limit) {
            this.m_iPreviousSuccessTeamKillValueIncrease = limit;

            this.OnSettingResponse("vars.teamkillvalueincrease", (decimal)limit, true);
        }

        private void lnkSettingsTeamKillValueIncrease_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.numSettingsTeamKillValueIncrease.Focus();
                this.WaitForSettingResponse("vars.teamkillvalueincrease", (decimal)this.m_iPreviousSuccessTeamKillValueIncrease);

                this.Client.Game.SendSetVarsTeamKillValueIncreasePacket((int)this.numSettingsTeamKillValueIncrease.Value);
            }
        }

        private int m_iPreviousSuccessTeamKillValueDecreasePerSecond;

        void m_prcClient_TeamKillValueDecreasePerSecond(FrostbiteClient sender, int limit) {
            this.m_iPreviousSuccessTeamKillValueDecreasePerSecond = limit;

            this.OnSettingResponse("vars.teamkillvaluedecreasepersecond", (decimal)limit, true);
        }

        private void lnkTeamKillValueDecreasePerSecond_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.numTeamKillValueDecreasePerSecond.Focus();
                this.WaitForSettingResponse("vars.teamkillvaluedecreasepersecond", (decimal)this.m_iPreviousSuccessTeamKillValueDecreasePerSecond);

                this.Client.Game.SendSetVarsTeamKillValueDecreasePerSecondPacket((int)this.numTeamKillValueDecreasePerSecond.Value);
            }
        }

        private void UpdateTeamkillExplanations() {

            float burstSecond = (float)this.numSettingsTeamkillValueLimit.Value / (float)this.numSettingsTeamKillValueIncrease.Value;
            float minimumRate = 60.0F / ((float)this.numSettingsTeamKillValueIncrease.Value / ((float)this.numTeamKillValueDecreasePerSecond.Value - 1.0F)) + burstSecond;
            float forgivenSeconds = (float)this.numSettingsTeamKillValueIncrease.Value / (float)this.numTeamKillValueDecreasePerSecond.Value;

            this.lblSettingsTkCountExplanation.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsTkCountExplanation", this.numSettingsTeamkillCountLimit.Value.ToString());

            StringBuilder valueExplanation = new StringBuilder();
            valueExplanation.AppendLine(this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsTkValueExplanation.Minimums", burstSecond.ToString("0.0"), minimumRate.ToString("0.00")));
            //valueExplanation.AppendLine(this.m_clocLanguage.GetLocalized("uscServerSettingsPanel.lblSettingsTkValueExplanation.MinimumRateMinute", minimumRate.ToString("0.00")));
            valueExplanation.AppendLine(this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsTkValueExplanation.ForgivenSingle", forgivenSeconds.ToString("0.0")));

            this.lblSettingsTkValueExplanation.Text = valueExplanation.ToString();

        }

        private void numSettingsTeamkillValueLimit_ValueChanged(object sender, EventArgs e) {
            this.UpdateTeamkillExplanations();
        }

        private void numSettingsTeamKillValueIncrease_ValueChanged(object sender, EventArgs e) {
            this.UpdateTeamkillExplanations();
        }

        private void numTeamKillValueDecreasePerSecond_ValueChanged(object sender, EventArgs e) {
            this.UpdateTeamkillExplanations();
        }

        private void numSettingsTeamkillCountLimit_ValueChanged(object sender, EventArgs e) {
            this.UpdateTeamkillExplanations();
        }

        #endregion
    }*/

    /*partial class uscServerSettingsTeamKills {
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
            this.pnlSettingsTeamkillValueLimit = new System.Windows.Forms.Panel();
            this.lblSettingsTkValueExplanation = new System.Windows.Forms.Label();
            this.lnkTeamKillValueDecreasePerSecond = new System.Windows.Forms.LinkLabel();
            this.picTeamKillValueDecreasePerSecond = new System.Windows.Forms.PictureBox();
            this.numTeamKillValueDecreasePerSecond = new System.Windows.Forms.NumericUpDown();
            this.lblTeamKillValueDecreasePerSecond = new System.Windows.Forms.Label();
            this.lnkSettingsTeamKillValueIncrease = new System.Windows.Forms.LinkLabel();
            this.picSettingsTeamKillValueIncrease = new System.Windows.Forms.PictureBox();
            this.numSettingsTeamKillValueIncrease = new System.Windows.Forms.NumericUpDown();
            this.lblSettingsTeamKillValueIncrease = new System.Windows.Forms.Label();
            this.lnkSettingsTeamkillValueLimit = new System.Windows.Forms.LinkLabel();
            this.numSettingsTeamkillValueLimit = new System.Windows.Forms.NumericUpDown();
            this.panel6 = new System.Windows.Forms.Panel();
            this.chkSettingsTeamkillValueLimit = new System.Windows.Forms.CheckBox();
            this.picSettingsTeamkillValueLimit = new System.Windows.Forms.PictureBox();
            this.pnlSettingsTeamkillCountLimit = new System.Windows.Forms.Panel();
            this.lblSettingsTkCountExplanation = new System.Windows.Forms.Label();
            this.lnkSettingsTeamkillCountLimit = new System.Windows.Forms.LinkLabel();
            this.numSettingsTeamkillCountLimit = new System.Windows.Forms.NumericUpDown();
            this.panel4 = new System.Windows.Forms.Panel();
            this.chkSettingsTeamkillCountLimit = new System.Windows.Forms.CheckBox();
            this.picSettingsTeamkillCountLimit = new System.Windows.Forms.PictureBox();
            this.pnlSettingsTeamkillValueLimit.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picTeamKillValueDecreasePerSecond)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTeamKillValueDecreasePerSecond)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsTeamKillValueIncrease)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsTeamKillValueIncrease)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsTeamkillValueLimit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsTeamkillValueLimit)).BeginInit();
            this.pnlSettingsTeamkillCountLimit.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsTeamkillCountLimit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsTeamkillCountLimit)).BeginInit();
            this.SuspendLayout();
            //
            // pnlSettingsTeamkillValueLimit
            //
            this.pnlSettingsTeamkillValueLimit.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.lblSettingsTkValueExplanation);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.lnkTeamKillValueDecreasePerSecond);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.picTeamKillValueDecreasePerSecond);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.numTeamKillValueDecreasePerSecond);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.lblTeamKillValueDecreasePerSecond);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.lnkSettingsTeamKillValueIncrease);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.picSettingsTeamKillValueIncrease);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.numSettingsTeamKillValueIncrease);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.lblSettingsTeamKillValueIncrease);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.lnkSettingsTeamkillValueLimit);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.numSettingsTeamkillValueLimit);
            this.pnlSettingsTeamkillValueLimit.Controls.Add(this.panel6);
            this.pnlSettingsTeamkillValueLimit.Location = new System.Drawing.Point(293, 81);
            this.pnlSettingsTeamkillValueLimit.Name = "pnlSettingsTeamkillValueLimit";
            this.pnlSettingsTeamkillValueLimit.Size = new System.Drawing.Size(656, 249);
            this.pnlSettingsTeamkillValueLimit.TabIndex = 219;
            //
            // lblSettingsTkValueExplanation
            //
            this.lblSettingsTkValueExplanation.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblSettingsTkValueExplanation.Location = new System.Drawing.Point(14, 170);
            this.lblSettingsTkValueExplanation.Name = "lblSettingsTkValueExplanation";
            this.lblSettingsTkValueExplanation.Size = new System.Drawing.Size(638, 73);
            this.lblSettingsTkValueExplanation.TabIndex = 230;
            //
            // lnkTeamKillValueDecreasePerSecond
            //
            this.lnkTeamKillValueDecreasePerSecond.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkTeamKillValueDecreasePerSecond.AutoSize = true;
            this.lnkTeamKillValueDecreasePerSecond.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkTeamKillValueDecreasePerSecond.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkTeamKillValueDecreasePerSecond.Location = new System.Drawing.Point(120, 135);
            this.lnkTeamKillValueDecreasePerSecond.Name = "lnkTeamKillValueDecreasePerSecond";
            this.lnkTeamKillValueDecreasePerSecond.Size = new System.Drawing.Size(38, 15);
            this.lnkTeamKillValueDecreasePerSecond.TabIndex = 229;
            this.lnkTeamKillValueDecreasePerSecond.TabStop = true;
            this.lnkTeamKillValueDecreasePerSecond.Text = "Apply";
            this.lnkTeamKillValueDecreasePerSecond.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkTeamKillValueDecreasePerSecond_LinkClicked);
            //
            // picTeamKillValueDecreasePerSecond
            //
            this.picTeamKillValueDecreasePerSecond.Location = new System.Drawing.Point(28, 113);
            this.picTeamKillValueDecreasePerSecond.Name = "picTeamKillValueDecreasePerSecond";
            this.picTeamKillValueDecreasePerSecond.Size = new System.Drawing.Size(16, 16);
            this.picTeamKillValueDecreasePerSecond.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picTeamKillValueDecreasePerSecond.TabIndex = 228;
            this.picTeamKillValueDecreasePerSecond.TabStop = false;
            //
            // numTeamKillValueDecreasePerSecond
            //
            this.numTeamKillValueDecreasePerSecond.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numTeamKillValueDecreasePerSecond.Increment = new decimal(new int[] {
            15,
            0,
            0,
            0});
            this.numTeamKillValueDecreasePerSecond.Location = new System.Drawing.Point(57, 133);
            this.numTeamKillValueDecreasePerSecond.Maximum = new decimal(new int[] {
            10000,
            0,
            0,
            0});
            this.numTeamKillValueDecreasePerSecond.Minimum = new decimal(new int[] {
            2,
            0,
            0,
            0});
            this.numTeamKillValueDecreasePerSecond.Name = "numTeamKillValueDecreasePerSecond";
            this.numTeamKillValueDecreasePerSecond.Size = new System.Drawing.Size(56, 23);
            this.numTeamKillValueDecreasePerSecond.TabIndex = 227;
            this.numTeamKillValueDecreasePerSecond.Value = new decimal(new int[] {
            50,
            0,
            0,
            0});
            this.numTeamKillValueDecreasePerSecond.ValueChanged += new System.EventHandler(this.numTeamKillValueDecreasePerSecond_ValueChanged);
            //
            // lblTeamKillValueDecreasePerSecond
            //
            this.lblTeamKillValueDecreasePerSecond.AutoSize = true;
            this.lblTeamKillValueDecreasePerSecond.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTeamKillValueDecreasePerSecond.Location = new System.Drawing.Point(54, 112);
            this.lblTeamKillValueDecreasePerSecond.Name = "lblTeamKillValueDecreasePerSecond";
            this.lblTeamKillValueDecreasePerSecond.Size = new System.Drawing.Size(208, 15);
            this.lblTeamKillValueDecreasePerSecond.TabIndex = 226;
            this.lblTeamKillValueDecreasePerSecond.Text = "Teamkill value cooldown (per second)";
            //
            // lnkSettingsTeamKillValueIncrease
            //
            this.lnkSettingsTeamKillValueIncrease.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsTeamKillValueIncrease.AutoSize = true;
            this.lnkSettingsTeamKillValueIncrease.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsTeamKillValueIncrease.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsTeamKillValueIncrease.Location = new System.Drawing.Point(120, 73);
            this.lnkSettingsTeamKillValueIncrease.Name = "lnkSettingsTeamKillValueIncrease";
            this.lnkSettingsTeamKillValueIncrease.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsTeamKillValueIncrease.TabIndex = 225;
            this.lnkSettingsTeamKillValueIncrease.TabStop = true;
            this.lnkSettingsTeamKillValueIncrease.Text = "Apply";
            this.lnkSettingsTeamKillValueIncrease.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsTeamKillValueIncrease_LinkClicked);
            //
            // picSettingsTeamKillValueIncrease
            //
            this.picSettingsTeamKillValueIncrease.Location = new System.Drawing.Point(28, 50);
            this.picSettingsTeamKillValueIncrease.Name = "picSettingsTeamKillValueIncrease";
            this.picSettingsTeamKillValueIncrease.Size = new System.Drawing.Size(16, 16);
            this.picSettingsTeamKillValueIncrease.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsTeamKillValueIncrease.TabIndex = 224;
            this.picSettingsTeamKillValueIncrease.TabStop = false;
            //
            // numSettingsTeamKillValueIncrease
            //
            this.numSettingsTeamKillValueIncrease.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numSettingsTeamKillValueIncrease.Increment = new decimal(new int[] {
            50,
            0,
            0,
            0});
            this.numSettingsTeamKillValueIncrease.Location = new System.Drawing.Point(57, 70);
            this.numSettingsTeamKillValueIncrease.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numSettingsTeamKillValueIncrease.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numSettingsTeamKillValueIncrease.Name = "numSettingsTeamKillValueIncrease";
            this.numSettingsTeamKillValueIncrease.Size = new System.Drawing.Size(56, 23);
            this.numSettingsTeamKillValueIncrease.TabIndex = 223;
            this.numSettingsTeamKillValueIncrease.Value = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            this.numSettingsTeamKillValueIncrease.ValueChanged += new System.EventHandler(this.numSettingsTeamKillValueIncrease_ValueChanged);
            //
            // lblSettingsTeamKillValueIncrease
            //
            this.lblSettingsTeamKillValueIncrease.AutoSize = true;
            this.lblSettingsTeamKillValueIncrease.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSettingsTeamKillValueIncrease.Location = new System.Drawing.Point(54, 50);
            this.lblSettingsTeamKillValueIncrease.Name = "lblSettingsTeamKillValueIncrease";
            this.lblSettingsTeamKillValueIncrease.Size = new System.Drawing.Size(104, 15);
            this.lblSettingsTeamKillValueIncrease.TabIndex = 222;
            this.lblSettingsTeamKillValueIncrease.Text = "Value per team kill";
            //
            // lnkSettingsTeamkillValueLimit
            //
            this.lnkSettingsTeamkillValueLimit.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsTeamkillValueLimit.AutoSize = true;
            this.lnkSettingsTeamkillValueLimit.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsTeamkillValueLimit.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsTeamkillValueLimit.Location = new System.Drawing.Point(72, 10);
            this.lnkSettingsTeamkillValueLimit.Name = "lnkSettingsTeamkillValueLimit";
            this.lnkSettingsTeamkillValueLimit.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsTeamkillValueLimit.TabIndex = 140;
            this.lnkSettingsTeamkillValueLimit.TabStop = true;
            this.lnkSettingsTeamkillValueLimit.Text = "Apply";
            this.lnkSettingsTeamkillValueLimit.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsTeamkillValueLimit_LinkClicked);
            //
            // numSettingsTeamkillValueLimit
            //
            this.numSettingsTeamkillValueLimit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numSettingsTeamkillValueLimit.Increment = new decimal(new int[] {
            50,
            0,
            0,
            0});
            this.numSettingsTeamkillValueLimit.Location = new System.Drawing.Point(9, 5);
            this.numSettingsTeamkillValueLimit.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numSettingsTeamkillValueLimit.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numSettingsTeamkillValueLimit.Name = "numSettingsTeamkillValueLimit";
            this.numSettingsTeamkillValueLimit.Size = new System.Drawing.Size(56, 23);
            this.numSettingsTeamkillValueLimit.TabIndex = 96;
            this.numSettingsTeamkillValueLimit.Value = new decimal(new int[] {
            4100,
            0,
            0,
            0});
            this.numSettingsTeamkillValueLimit.ValueChanged += new System.EventHandler(this.numSettingsTeamkillValueLimit_ValueChanged);
            //
            // panel6
            //
            this.panel6.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel6.Location = new System.Drawing.Point(6, 5);
            this.panel6.Name = "panel6";
            this.panel6.Size = new System.Drawing.Size(1, 162);
            this.panel6.TabIndex = 157;
            //
            // chkSettingsTeamkillValueLimit
            //
            this.chkSettingsTeamkillValueLimit.AutoSize = true;
            this.chkSettingsTeamkillValueLimit.Location = new System.Drawing.Point(36, 89);
            this.chkSettingsTeamkillValueLimit.Name = "chkSettingsTeamkillValueLimit";
            this.chkSettingsTeamkillValueLimit.Size = new System.Drawing.Size(145, 19);
            this.chkSettingsTeamkillValueLimit.TabIndex = 218;
            this.chkSettingsTeamkillValueLimit.Text = "No teamkill value limit";
            this.chkSettingsTeamkillValueLimit.UseVisualStyleBackColor = true;
            this.chkSettingsTeamkillValueLimit.CheckedChanged += new System.EventHandler(this.chkSettingsTeamkillValueLimit_CheckedChanged);
            //
            // picSettingsTeamkillValueLimit
            //
            this.picSettingsTeamkillValueLimit.Location = new System.Drawing.Point(10, 91);
            this.picSettingsTeamkillValueLimit.Name = "picSettingsTeamkillValueLimit";
            this.picSettingsTeamkillValueLimit.Size = new System.Drawing.Size(16, 16);
            this.picSettingsTeamkillValueLimit.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsTeamkillValueLimit.TabIndex = 217;
            this.picSettingsTeamkillValueLimit.TabStop = false;
            //
            // pnlSettingsTeamkillCountLimit
            //
            this.pnlSettingsTeamkillCountLimit.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.pnlSettingsTeamkillCountLimit.Controls.Add(this.lblSettingsTkCountExplanation);
            this.pnlSettingsTeamkillCountLimit.Controls.Add(this.lnkSettingsTeamkillCountLimit);
            this.pnlSettingsTeamkillCountLimit.Controls.Add(this.numSettingsTeamkillCountLimit);
            this.pnlSettingsTeamkillCountLimit.Controls.Add(this.panel4);
            this.pnlSettingsTeamkillCountLimit.Location = new System.Drawing.Point(293, 7);
            this.pnlSettingsTeamkillCountLimit.Name = "pnlSettingsTeamkillCountLimit";
            this.pnlSettingsTeamkillCountLimit.Size = new System.Drawing.Size(656, 67);
            this.pnlSettingsTeamkillCountLimit.TabIndex = 216;
            //
            // lblSettingsTkCountExplanation
            //
            this.lblSettingsTkCountExplanation.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblSettingsTkCountExplanation.Location = new System.Drawing.Point(14, 36);
            this.lblSettingsTkCountExplanation.Name = "lblSettingsTkCountExplanation";
            this.lblSettingsTkCountExplanation.Size = new System.Drawing.Size(638, 31);
            this.lblSettingsTkCountExplanation.TabIndex = 158;
            //
            // lnkSettingsTeamkillCountLimit
            //
            this.lnkSettingsTeamkillCountLimit.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsTeamkillCountLimit.AutoSize = true;
            this.lnkSettingsTeamkillCountLimit.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsTeamkillCountLimit.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsTeamkillCountLimit.Location = new System.Drawing.Point(72, 10);
            this.lnkSettingsTeamkillCountLimit.Name = "lnkSettingsTeamkillCountLimit";
            this.lnkSettingsTeamkillCountLimit.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsTeamkillCountLimit.TabIndex = 140;
            this.lnkSettingsTeamkillCountLimit.TabStop = true;
            this.lnkSettingsTeamkillCountLimit.Text = "Apply";
            this.lnkSettingsTeamkillCountLimit.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsTeamkillCountLimit_LinkClicked);
            //
            // numSettingsTeamkillCountLimit
            //
            this.numSettingsTeamkillCountLimit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numSettingsTeamkillCountLimit.Location = new System.Drawing.Point(9, 5);
            this.numSettingsTeamkillCountLimit.Name = "numSettingsTeamkillCountLimit";
            this.numSettingsTeamkillCountLimit.Size = new System.Drawing.Size(56, 23);
            this.numSettingsTeamkillCountLimit.TabIndex = 96;
            this.numSettingsTeamkillCountLimit.Value = new decimal(new int[] {
            6,
            0,
            0,
            0});
            this.numSettingsTeamkillCountLimit.ValueChanged += new System.EventHandler(this.numSettingsTeamkillCountLimit_ValueChanged);
            //
            // panel4
            //
            this.panel4.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel4.Location = new System.Drawing.Point(6, 5);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(1, 48);
            this.panel4.TabIndex = 157;
            //
            // chkSettingsTeamkillCountLimit
            //
            this.chkSettingsTeamkillCountLimit.AutoSize = true;
            this.chkSettingsTeamkillCountLimit.Location = new System.Drawing.Point(36, 15);
            this.chkSettingsTeamkillCountLimit.Name = "chkSettingsTeamkillCountLimit";
            this.chkSettingsTeamkillCountLimit.Size = new System.Drawing.Size(158, 19);
            this.chkSettingsTeamkillCountLimit.TabIndex = 215;
            this.chkSettingsTeamkillCountLimit.Text = "No teamkill counter limit";
            this.chkSettingsTeamkillCountLimit.UseVisualStyleBackColor = true;
            this.chkSettingsTeamkillCountLimit.CheckedChanged += new System.EventHandler(this.chkSettingsTeamkillCountLimit_CheckedChanged);
            //
            // picSettingsTeamkillCountLimit
            //
            this.picSettingsTeamkillCountLimit.Location = new System.Drawing.Point(10, 17);
            this.picSettingsTeamkillCountLimit.Name = "picSettingsTeamkillCountLimit";
            this.picSettingsTeamkillCountLimit.Size = new System.Drawing.Size(16, 16);
            this.picSettingsTeamkillCountLimit.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsTeamkillCountLimit.TabIndex = 214;
            this.picSettingsTeamkillCountLimit.TabStop = false;
            //
            // uscServerSettingsTeamKills
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.pnlSettingsTeamkillValueLimit);
            this.Controls.Add(this.chkSettingsTeamkillValueLimit);
            this.Controls.Add(this.picSettingsTeamkillValueLimit);
            this.Controls.Add(this.pnlSettingsTeamkillCountLimit);
            this.Controls.Add(this.chkSettingsTeamkillCountLimit);
            this.Controls.Add(this.picSettingsTeamkillCountLimit);
            this.Name = "uscServerSettingsTeamKills";
            this.Size = new System.Drawing.Size(952, 340);
            this.pnlSettingsTeamkillValueLimit.ResumeLayout(false);
            this.pnlSettingsTeamkillValueLimit.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picTeamKillValueDecreasePerSecond)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTeamKillValueDecreasePerSecond)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsTeamKillValueIncrease)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsTeamKillValueIncrease)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsTeamkillValueLimit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsTeamkillValueLimit)).EndInit();
            this.pnlSettingsTeamkillCountLimit.ResumeLayout(false);
            this.pnlSettingsTeamkillCountLimit.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsTeamkillCountLimit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsTeamkillCountLimit)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel pnlSettingsTeamkillValueLimit;
        private System.Windows.Forms.Label lblSettingsTkValueExplanation;
        private System.Windows.Forms.LinkLabel lnkTeamKillValueDecreasePerSecond;
        private System.Windows.Forms.PictureBox picTeamKillValueDecreasePerSecond;
        private System.Windows.Forms.NumericUpDown numTeamKillValueDecreasePerSecond;
        private System.Windows.Forms.Label lblTeamKillValueDecreasePerSecond;
        private System.Windows.Forms.LinkLabel lnkSettingsTeamKillValueIncrease;
        private System.Windows.Forms.PictureBox picSettingsTeamKillValueIncrease;
        private System.Windows.Forms.NumericUpDown numSettingsTeamKillValueIncrease;
        private System.Windows.Forms.Label lblSettingsTeamKillValueIncrease;
        private System.Windows.Forms.LinkLabel lnkSettingsTeamkillValueLimit;
        private System.Windows.Forms.NumericUpDown numSettingsTeamkillValueLimit;
        private System.Windows.Forms.Panel panel6;
        private System.Windows.Forms.CheckBox chkSettingsTeamkillValueLimit;
        private System.Windows.Forms.PictureBox picSettingsTeamkillValueLimit;
        private System.Windows.Forms.Panel pnlSettingsTeamkillCountLimit;
        private System.Windows.Forms.Label lblSettingsTkCountExplanation;
        private System.Windows.Forms.LinkLabel lnkSettingsTeamkillCountLimit;
        private System.Windows.Forms.NumericUpDown numSettingsTeamkillCountLimit;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.CheckBox chkSettingsTeamkillCountLimit;
        private System.Windows.Forms.PictureBox picSettingsTeamkillCountLimit;
    }*/
}
