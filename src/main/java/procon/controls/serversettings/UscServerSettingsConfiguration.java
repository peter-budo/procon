package procon.controls.serversettings;

public class UscServerSettingsConfiguration {

    /*public partial class uscServerSettingsConfiguration : uscServerSettings {

        private int m_iPreviousSuccessPlayerLimit;
        private int m_iPreviousSuccessIdleTimeoutLimit;

        private string m_strPreviousSuccessAdminPassword;
        private string m_strPreviousSuccessGamePassword;

        public uscServerSettingsConfiguration() {
            InitializeComponent();

            //this.AsyncSettingControls.Add("vars.punkbuster", new AsyncStyleSetting(this.picSettingsPunkbuster, this.chkSettingsPunkbuster, new Control[] { this.chkSettingsPunkbuster }, false));
            //this.AsyncSettingControls.Add("vars.ranked", new AsyncStyleSetting(this.picSettingsRanked, this.chkSettingsRanked, new Control[] { this.chkSettingsRanked }, false));

            this.AsyncSettingControls.Add("vars.playerlimit", new AsyncStyleSetting(this.picSettingsPlayerLimit, this.numSettingsPlayerLimit, new Control[] { this.numSettingsPlayerLimit, this.lnkSettingsSetPlayerLimit }, true));
            this.AsyncSettingControls.Add("vars.profanityfilter", new AsyncStyleSetting(this.picSettingsProfanityFilter, this.chkSettingsProfanityFilter, new Control[] { this.chkSettingsProfanityFilter }, true));
            this.AsyncSettingControls.Add("vars.idletimeout 0", new AsyncStyleSetting(this.picSettingsIdleKickLimit, this.chkSettingsNoIdleKickLimit, new Control[] { this.chkSettingsNoIdleKickLimit }, true));
            this.AsyncSettingControls.Add("vars.idletimeout", new AsyncStyleSetting(this.picSettingsIdleKickLimit, this.numSettingsIdleKickLimit, new Control[] { this.numSettingsIdleKickLimit, this.lnkSettingsSetidleKickLimit }, true));

            this.AsyncSettingControls.Add("vars.gamepassword", new AsyncStyleSetting(this.picSettingsGamePassword, this.txtSettingsGamePassword, new Control[] { this.lblSettingsGamePassword, this.txtSettingsGamePassword, this.lnkSettingsSetGamePassword }, true));
            this.AsyncSettingControls.Add("vars.adminpassword", new AsyncStyleSetting(this.picSettingsAdminPassword, this.txtSettingsAdminPassword, new Control[] { this.lblSettingsAdminPassword, this.txtSettingsAdminPassword, this.lnkSettingsSetAdminPassword }, true));

            this.m_iPreviousSuccessPlayerLimit = 50;
            this.m_iPreviousSuccessIdleTimeoutLimit = 0;
            this.m_strPreviousSuccessAdminPassword = String.Empty;
            this.m_strPreviousSuccessGamePassword = String.Empty;
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            base.SetLocalization(clocLanguage);

            this.chkSettingsPunkbuster.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkSettingsPunkbuster");
            this.chkSettingsRanked.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkSettingsRanked");

            this.lblSettingsPlayerLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsPlayerLimit");
            this.lnkSettingsSetPlayerLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetPlayerLimit");

            this.lblSettingsGamePassword.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsGamePassword");
            this.lnkSettingsSetGamePassword.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetGamePassword");
            this.lblSettingsAdminPassword.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsAdminPassword");
            this.lnkSettingsSetAdminPassword.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetAdminPassword");

            this.chkSettingsProfanityFilter.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkSettingsProfanityFilter");
            this.chkSettingsNoIdleKickLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.chkSettingsNoIdleKickLimit");
            this.lnkSettingsSetidleKickLimit.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetidleKickLimit");

            this.DisplayName = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsConfiguration");
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

            this.Client.Game.Punkbuster += new FrostbiteClient.IsEnabledHandler(m_prcClient_Punkbuster);
            this.Client.Game.Ranked += new FrostbiteClient.IsEnabledHandler(m_prcClient_Ranked);

            this.Client.Game.GamePassword += new FrostbiteClient.PasswordHandler(m_prcClient_GamePassword);
            this.Client.Game.AdminPassword += new FrostbiteClient.PasswordHandler(m_prcClient_AdminPassword);

            this.Client.Game.PlayerLimit += new FrostbiteClient.LimitHandler(m_prcClient_PlayerLimit);
            this.Client.Game.MaxPlayerLimit += new FrostbiteClient.LimitHandler(m_prcClient_MaxPlayerLimit);
            this.Client.Game.CurrentPlayerLimit += new FrostbiteClient.LimitHandler(m_prcClient_CurrentPlayerLimit);
            this.Client.Game.ProfanityFilter += new FrostbiteClient.IsEnabledHandler(m_prcClient_ProfanityFilter);
            this.Client.Game.IdleTimeout += new FrostbiteClient.LimitHandler(m_prcClient_IdleTimeout);

            this.Client.Game.ServerInfo += new FrostbiteClient.ServerInfoHandler(m_prcClient_ServerInfo);
        }

        private void m_prcClient_ServerInfo(FrostbiteClient sender, CServerInfo csiServerInfo) {
            if (csiServerInfo.MaxPlayerCount > 0 && csiServerInfo.MaxPlayerCount < this.numSettingsPlayerLimit.Maximum) {
                this.numSettingsPlayerLimit.Value = (decimal)csiServerInfo.MaxPlayerCount;
            }
        }

        #region Passwords

        private void m_prcClient_GamePassword(FrostbiteClient sender, string password) {
            this.OnSettingResponse("vars.gamepassword", password, true);
            this.m_strPreviousSuccessGamePassword = password;
        }

        private void lnkSettingsSetGamePassword_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.txtSettingsGamePassword.Focus();
                this.WaitForSettingResponse("vars.gamepassword", this.m_strPreviousSuccessGamePassword);

                this.Client.Game.SendSetVarsGamePasswordPacket(this.txtSettingsGamePassword.Text);
            }
        }

        private void m_prcClient_AdminPassword(FrostbiteClient sender, string password) {
            this.OnSettingResponse("vars.adminpassword", password, true);
            this.m_strPreviousSuccessAdminPassword = password;
        }

        private void lnkSettingsSetAdminPassword_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.txtSettingsAdminPassword.Focus();
                this.WaitForSettingResponse("vars.adminpassword", this.m_strPreviousSuccessAdminPassword);

                this.Client.Game.SendSetVarsAdminPasswordPacket(this.txtSettingsAdminPassword.Text);
            }
        }

        #endregion

        #region Punkbuster

        private void m_prcClient_Punkbuster(FrostbiteClient sender, bool isEnabled) {
            this.chkSettingsPunkbuster.Checked = isEnabled;
            //this.OnSettingResponse("vars.punkbuster", isEnabled, true);
        }

        *//*
        private void chkSettingsPunkbuster_CheckedChanged(object sender, EventArgs e) {

            if (this.Client != null && this.Client.Game != null) {

                if (this.IgnoreEvents == false && this.AsyncSettingControls["vars.punkbuster"].IgnoreEvent == false) {
                    this.WaitForSettingResponse("vars.punkbuster", !this.chkSettingsPunkbuster.Checked);

                    this.Client.Game.SendSetVarsPunkBusterPacket(this.chkSettingsPunkbuster.Checked);
                    //this.SendCommand("vars.punkBuster", Packet.bltos(this.chkSettingsPunkbuster.Checked));
                }
            }
        }
        *//*

        #endregion

        #region Ranked

        private void m_prcClient_Ranked(FrostbiteClient sender, bool isEnabled) {
            this.chkSettingsRanked.Checked = isEnabled;
            //this.OnSettingResponse("vars.ranked", isEnabled, true);
        }

        *//*
        private void chkSettingsRanked_CheckedChanged(object sender, EventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                if (this.IgnoreEvents == false && this.AsyncSettingControls["vars.ranked"].IgnoreEvent == false) {
                    this.WaitForSettingResponse("vars.ranked", !this.chkSettingsRanked.Checked);

                    this.Client.Game.SendSetVarsRankedPacket(this.chkSettingsPunkbuster.Checked);
                    //this.SendCommand("vars.ranked", Packet.bltos(this.chkSettingsRanked.Checked));
                }
            }
        }
        *//*

        #endregion

        #region Player Limit

        private void m_prcClient_CurrentPlayerLimit(FrostbiteClient sender, int limit) {
            if (limit > 0 && limit <= this.numSettingsPlayerLimit.Maximum) {
                this.numSettingsPlayerLimit.Value = (decimal)limit;
            }
        }

        private void m_prcClient_MaxPlayerLimit(FrostbiteClient sender, int limit) {
            this.numSettingsPlayerLimit.Maximum = (decimal)limit;
        }

        private void m_prcClient_PlayerLimit(FrostbiteClient sender, int limit) {
            this.OnSettingResponse("vars.playerlimit", (decimal)limit, true);
            this.m_iPreviousSuccessPlayerLimit = limit;
        }

        private void lnkSettingsSetPlayerLimt_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.numSettingsPlayerLimit.Focus();
                this.WaitForSettingResponse("vars.playerlimit", (decimal)this.m_iPreviousSuccessPlayerLimit);

                this.Client.Game.SendSetVarsPlayerLimitPacket((int)this.numSettingsPlayerLimit.Value);
                //this.SendCommand("vars.playerLimit", this.numSettingsPlayerLimit.Value.ToString());
            }
        }

        #endregion

        #region Idle Timeout

        private void m_prcClient_IdleTimeout(FrostbiteClient sender, int limit) {
            this.m_iPreviousSuccessIdleTimeoutLimit = limit;

            if (this.m_iPreviousSuccessIdleTimeoutLimit == 0) {
                this.OnSettingResponse("vars.idletimeout 0", true, true);
            }
            else {
                this.OnSettingResponse("vars.idletimeout", (decimal)this.m_iPreviousSuccessIdleTimeoutLimit, true);
            }
        }

        private void chkSettingsNoIdleKickLimit_CheckedChanged(object sender, EventArgs e) {
            this.pnlSettingsSetidleKickLimit.Enabled = !this.chkSettingsNoIdleKickLimit.Checked;
            this.pnlSettingsSetidleKickLimit.Visible = !this.chkSettingsNoIdleKickLimit.Checked;

            if (this.IgnoreEvents == false && this.AsyncSettingControls["vars.idletimeout 0"].IgnoreEvent == false) {
                if (this.chkSettingsNoIdleKickLimit.Checked == true) {
                    this.WaitForSettingResponse("vars.idletimeout 0", !this.chkSettingsNoIdleKickLimit.Checked);

                    this.Client.Game.SendSetVarsIdleTimeoutPacket(0);
                    //this.SendCommand("vars.idleTimeout", "0");
                }
            }
        }

        private void lnkSettingsSetidleKickLimit_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            this.numSettingsIdleKickLimit.Focus();
            this.WaitForSettingResponse("vars.idletimeout", (decimal)this.m_iPreviousSuccessIdleTimeoutLimit);

            this.Client.Game.SendSetVarsIdleTimeoutPacket((int)this.numSettingsIdleKickLimit.Value);
            //this.SendCommand("vars.idleTimeout", this.numSettingsIdleKickLimit.Value.ToString());
        }

        #endregion

        #region Profanity Filter

        private void m_prcClient_ProfanityFilter(FrostbiteClient sender, bool isEnabled) {
            this.OnSettingResponse("vars.profanityfilter", isEnabled, true);
        }

        private void chkSettingsProfanityFilter_CheckedChanged(object sender, EventArgs e) {
            if (this.IgnoreEvents == false && this.AsyncSettingControls["vars.profanityfilter"].IgnoreEvent == false) {
                this.WaitForSettingResponse("vars.profanityfilter", !this.chkSettingsProfanityFilter.Checked);

                this.Client.Game.SendSetVarsProfanityFilterPacket(this.chkSettingsProfanityFilter.Checked);
                //this.SendCommand("vars.profanityFilter", Packet.bltos(this.chkSettingsProfanityFilter.Checked));
            }
        }

        #endregion
    }*/

    /*partial class uscServerSettingsConfiguration {
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
            this.pnlSettingsSetidleKickLimit = new System.Windows.Forms.Panel();
            this.lnkSettingsSetidleKickLimit = new System.Windows.Forms.LinkLabel();
            this.numSettingsIdleKickLimit = new System.Windows.Forms.NumericUpDown();
            this.panel2 = new System.Windows.Forms.Panel();
            this.chkSettingsNoIdleKickLimit = new System.Windows.Forms.CheckBox();
            this.picSettingsIdleKickLimit = new System.Windows.Forms.PictureBox();
            this.chkSettingsProfanityFilter = new System.Windows.Forms.CheckBox();
            this.picSettingsProfanityFilter = new System.Windows.Forms.PictureBox();
            this.lnkSettingsSetPlayerLimit = new System.Windows.Forms.LinkLabel();
            this.picSettingsPlayerLimit = new System.Windows.Forms.PictureBox();
            this.numSettingsPlayerLimit = new System.Windows.Forms.NumericUpDown();
            this.chkSettingsRanked = new System.Windows.Forms.CheckBox();
            this.picSettingsRanked = new System.Windows.Forms.PictureBox();
            this.chkSettingsPunkbuster = new System.Windows.Forms.CheckBox();
            this.picSettingsPunkbuster = new System.Windows.Forms.PictureBox();
            this.lblSettingsPlayerLimit = new System.Windows.Forms.Label();
            this.picSettingsGamePassword = new System.Windows.Forms.PictureBox();
            this.lblSettingsGamePassword = new System.Windows.Forms.Label();
            this.lnkSettingsSetGamePassword = new System.Windows.Forms.LinkLabel();
            this.pnlSettingsAdminPassword = new System.Windows.Forms.Panel();
            this.lnkSettingsSetAdminPassword = new System.Windows.Forms.LinkLabel();
            this.picSettingsAdminPassword = new System.Windows.Forms.PictureBox();
            this.txtSettingsAdminPassword = new System.Windows.Forms.TextBox();
            this.lblSettingsAdminPassword = new System.Windows.Forms.Label();
            this.txtSettingsGamePassword = new System.Windows.Forms.TextBox();
            this.pnlSettingsSetidleKickLimit.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsIdleKickLimit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsIdleKickLimit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsProfanityFilter)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsPlayerLimit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsPlayerLimit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsRanked)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsPunkbuster)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsGamePassword)).BeginInit();
            this.pnlSettingsAdminPassword.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsAdminPassword)).BeginInit();
            this.SuspendLayout();
            //
            // pnlSettingsSetidleKickLimit
            //
            this.pnlSettingsSetidleKickLimit.Controls.Add(this.lnkSettingsSetidleKickLimit);
            this.pnlSettingsSetidleKickLimit.Controls.Add(this.numSettingsIdleKickLimit);
            this.pnlSettingsSetidleKickLimit.Controls.Add(this.panel2);
            this.pnlSettingsSetidleKickLimit.Location = new System.Drawing.Point(587, 51);
            this.pnlSettingsSetidleKickLimit.Name = "pnlSettingsSetidleKickLimit";
            this.pnlSettingsSetidleKickLimit.Size = new System.Drawing.Size(215, 37);
            this.pnlSettingsSetidleKickLimit.TabIndex = 225;
            //
            // lnkSettingsSetidleKickLimit
            //
            this.lnkSettingsSetidleKickLimit.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetidleKickLimit.AutoSize = true;
            this.lnkSettingsSetidleKickLimit.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetidleKickLimit.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetidleKickLimit.Location = new System.Drawing.Point(72, 10);
            this.lnkSettingsSetidleKickLimit.Name = "lnkSettingsSetidleKickLimit";
            this.lnkSettingsSetidleKickLimit.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsSetidleKickLimit.TabIndex = 140;
            this.lnkSettingsSetidleKickLimit.TabStop = true;
            this.lnkSettingsSetidleKickLimit.Text = "Apply";
            this.lnkSettingsSetidleKickLimit.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetidleKickLimit_LinkClicked);
            //
            // numSettingsIdleKickLimit
            //
            this.numSettingsIdleKickLimit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numSettingsIdleKickLimit.Increment = new decimal(new int[] {
            15,
            0,
            0,
            0});
            this.numSettingsIdleKickLimit.Location = new System.Drawing.Point(9, 5);
            this.numSettingsIdleKickLimit.Maximum = new decimal(new int[] {
            3600,
            0,
            0,
            0});
            this.numSettingsIdleKickLimit.Name = "numSettingsIdleKickLimit";
            this.numSettingsIdleKickLimit.Size = new System.Drawing.Size(56, 23);
            this.numSettingsIdleKickLimit.TabIndex = 96;
            this.numSettingsIdleKickLimit.Value = new decimal(new int[] {
            300,
            0,
            0,
            0});
            //
            // panel2
            //
            this.panel2.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel2.Location = new System.Drawing.Point(6, 5);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(1, 48);
            this.panel2.TabIndex = 157;
            //
            // chkSettingsNoIdleKickLimit
            //
            this.chkSettingsNoIdleKickLimit.AutoSize = true;
            this.chkSettingsNoIdleKickLimit.Location = new System.Drawing.Point(330, 59);
            this.chkSettingsNoIdleKickLimit.Name = "chkSettingsNoIdleKickLimit";
            this.chkSettingsNoIdleKickLimit.Size = new System.Drawing.Size(110, 19);
            this.chkSettingsNoIdleKickLimit.TabIndex = 224;
            this.chkSettingsNoIdleKickLimit.Text = "Disable idle kick";
            this.chkSettingsNoIdleKickLimit.UseVisualStyleBackColor = true;
            this.chkSettingsNoIdleKickLimit.CheckedChanged += new System.EventHandler(this.chkSettingsNoIdleKickLimit_CheckedChanged);
            //
            // picSettingsIdleKickLimit
            //
            this.picSettingsIdleKickLimit.Location = new System.Drawing.Point(304, 59);
            this.picSettingsIdleKickLimit.Name = "picSettingsIdleKickLimit";
            this.picSettingsIdleKickLimit.Size = new System.Drawing.Size(16, 16);
            this.picSettingsIdleKickLimit.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsIdleKickLimit.TabIndex = 223;
            this.picSettingsIdleKickLimit.TabStop = false;
            //
            // chkSettingsProfanityFilter
            //
            this.chkSettingsProfanityFilter.AutoSize = true;
            this.chkSettingsProfanityFilter.Location = new System.Drawing.Point(330, 23);
            this.chkSettingsProfanityFilter.Name = "chkSettingsProfanityFilter";
            this.chkSettingsProfanityFilter.Size = new System.Drawing.Size(101, 19);
            this.chkSettingsProfanityFilter.TabIndex = 222;
            this.chkSettingsProfanityFilter.Text = "Profanity filter";
            this.chkSettingsProfanityFilter.UseVisualStyleBackColor = true;
            this.chkSettingsProfanityFilter.CheckedChanged += new System.EventHandler(this.chkSettingsProfanityFilter_CheckedChanged);
            //
            // picSettingsProfanityFilter
            //
            this.picSettingsProfanityFilter.Location = new System.Drawing.Point(304, 24);
            this.picSettingsProfanityFilter.Name = "picSettingsProfanityFilter";
            this.picSettingsProfanityFilter.Size = new System.Drawing.Size(16, 16);
            this.picSettingsProfanityFilter.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsProfanityFilter.TabIndex = 221;
            this.picSettingsProfanityFilter.TabStop = false;
            //
            // lnkSettingsSetPlayerLimit
            //
            this.lnkSettingsSetPlayerLimit.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetPlayerLimit.AutoSize = true;
            this.lnkSettingsSetPlayerLimit.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetPlayerLimit.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetPlayerLimit.Location = new System.Drawing.Point(100, 119);
            this.lnkSettingsSetPlayerLimit.Name = "lnkSettingsSetPlayerLimit";
            this.lnkSettingsSetPlayerLimit.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsSetPlayerLimit.TabIndex = 220;
            this.lnkSettingsSetPlayerLimit.TabStop = true;
            this.lnkSettingsSetPlayerLimit.Text = "Apply";
            this.lnkSettingsSetPlayerLimit.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetPlayerLimt_LinkClicked);
            //
            // picSettingsPlayerLimit
            //
            this.picSettingsPlayerLimit.Location = new System.Drawing.Point(9, 94);
            this.picSettingsPlayerLimit.Name = "picSettingsPlayerLimit";
            this.picSettingsPlayerLimit.Size = new System.Drawing.Size(16, 16);
            this.picSettingsPlayerLimit.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsPlayerLimit.TabIndex = 218;
            this.picSettingsPlayerLimit.TabStop = false;
            //
            // numSettingsPlayerLimit
            //
            this.numSettingsPlayerLimit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.numSettingsPlayerLimit.Location = new System.Drawing.Point(37, 117);
            this.numSettingsPlayerLimit.Maximum = new decimal(new int[] {
            32,
            0,
            0,
            0});
            this.numSettingsPlayerLimit.Minimum = new decimal(new int[] {
            8,
            0,
            0,
            0});
            this.numSettingsPlayerLimit.Name = "numSettingsPlayerLimit";
            this.numSettingsPlayerLimit.Size = new System.Drawing.Size(56, 23);
            this.numSettingsPlayerLimit.TabIndex = 217;
            this.numSettingsPlayerLimit.Value = new decimal(new int[] {
            8,
            0,
            0,
            0});
            //
            // chkSettingsRanked
            //
            this.chkSettingsRanked.AutoSize = true;
            this.chkSettingsRanked.Enabled = false;
            this.chkSettingsRanked.Location = new System.Drawing.Point(35, 57);
            this.chkSettingsRanked.Name = "chkSettingsRanked";
            this.chkSettingsRanked.Size = new System.Drawing.Size(65, 19);
            this.chkSettingsRanked.TabIndex = 210;
            this.chkSettingsRanked.Text = "Ranked";
            this.chkSettingsRanked.UseVisualStyleBackColor = true;
            //
            // picSettingsRanked
            //
            this.picSettingsRanked.Location = new System.Drawing.Point(9, 59);
            this.picSettingsRanked.Name = "picSettingsRanked";
            this.picSettingsRanked.Size = new System.Drawing.Size(16, 16);
            this.picSettingsRanked.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsRanked.TabIndex = 209;
            this.picSettingsRanked.TabStop = false;
            //
            // chkSettingsPunkbuster
            //
            this.chkSettingsPunkbuster.AutoSize = true;
            this.chkSettingsPunkbuster.Enabled = false;
            this.chkSettingsPunkbuster.Location = new System.Drawing.Point(35, 23);
            this.chkSettingsPunkbuster.Name = "chkSettingsPunkbuster";
            this.chkSettingsPunkbuster.Size = new System.Drawing.Size(86, 19);
            this.chkSettingsPunkbuster.TabIndex = 208;
            this.chkSettingsPunkbuster.Text = "Punkbuster";
            this.chkSettingsPunkbuster.UseVisualStyleBackColor = true;
            //
            // picSettingsPunkbuster
            //
            this.picSettingsPunkbuster.Location = new System.Drawing.Point(9, 24);
            this.picSettingsPunkbuster.Name = "picSettingsPunkbuster";
            this.picSettingsPunkbuster.Size = new System.Drawing.Size(16, 16);
            this.picSettingsPunkbuster.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsPunkbuster.TabIndex = 207;
            this.picSettingsPunkbuster.TabStop = false;
            //
            // lblSettingsPlayerLimit
            //
            this.lblSettingsPlayerLimit.AutoSize = true;
            this.lblSettingsPlayerLimit.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSettingsPlayerLimit.Location = new System.Drawing.Point(34, 96);
            this.lblSettingsPlayerLimit.Name = "lblSettingsPlayerLimit";
            this.lblSettingsPlayerLimit.Size = new System.Drawing.Size(66, 15);
            this.lblSettingsPlayerLimit.TabIndex = 206;
            this.lblSettingsPlayerLimit.Text = "Player limit";
            //
            // picSettingsGamePassword
            //
            this.picSettingsGamePassword.Location = new System.Drawing.Point(9, 164);
            this.picSettingsGamePassword.Name = "picSettingsGamePassword";
            this.picSettingsGamePassword.Size = new System.Drawing.Size(16, 16);
            this.picSettingsGamePassword.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsGamePassword.TabIndex = 227;
            this.picSettingsGamePassword.TabStop = false;
            //
            // lblSettingsGamePassword
            //
            this.lblSettingsGamePassword.AutoSize = true;
            this.lblSettingsGamePassword.Location = new System.Drawing.Point(31, 163);
            this.lblSettingsGamePassword.Name = "lblSettingsGamePassword";
            this.lblSettingsGamePassword.Size = new System.Drawing.Size(91, 15);
            this.lblSettingsGamePassword.TabIndex = 226;
            this.lblSettingsGamePassword.Text = "Game password";
            //
            // lnkSettingsSetGamePassword
            //
            this.lnkSettingsSetGamePassword.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetGamePassword.AutoSize = true;
            this.lnkSettingsSetGamePassword.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetGamePassword.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetGamePassword.Location = new System.Drawing.Point(243, 190);
            this.lnkSettingsSetGamePassword.Name = "lnkSettingsSetGamePassword";
            this.lnkSettingsSetGamePassword.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsSetGamePassword.TabIndex = 230;
            this.lnkSettingsSetGamePassword.TabStop = true;
            this.lnkSettingsSetGamePassword.Text = "Apply";
            this.lnkSettingsSetGamePassword.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetGamePassword_LinkClicked);
            //
            // pnlSettingsAdminPassword
            //
            this.pnlSettingsAdminPassword.Controls.Add(this.lnkSettingsSetAdminPassword);
            this.pnlSettingsAdminPassword.Controls.Add(this.picSettingsAdminPassword);
            this.pnlSettingsAdminPassword.Controls.Add(this.txtSettingsAdminPassword);
            this.pnlSettingsAdminPassword.Controls.Add(this.lblSettingsAdminPassword);
            this.pnlSettingsAdminPassword.Location = new System.Drawing.Point(367, 150);
            this.pnlSettingsAdminPassword.Name = "pnlSettingsAdminPassword";
            this.pnlSettingsAdminPassword.Size = new System.Drawing.Size(432, 72);
            this.pnlSettingsAdminPassword.TabIndex = 229;
            //
            // lnkSettingsSetAdminPassword
            //
            this.lnkSettingsSetAdminPassword.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetAdminPassword.AutoSize = true;
            this.lnkSettingsSetAdminPassword.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetAdminPassword.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetAdminPassword.Location = new System.Drawing.Point(254, 40);
            this.lnkSettingsSetAdminPassword.Name = "lnkSettingsSetAdminPassword";
            this.lnkSettingsSetAdminPassword.Size = new System.Drawing.Size(38, 15);
            this.lnkSettingsSetAdminPassword.TabIndex = 139;
            this.lnkSettingsSetAdminPassword.TabStop = true;
            this.lnkSettingsSetAdminPassword.Text = "Apply";
            this.lnkSettingsSetAdminPassword.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetAdminPassword_LinkClicked);
            //
            // picSettingsAdminPassword
            //
            this.picSettingsAdminPassword.Location = new System.Drawing.Point(16, 13);
            this.picSettingsAdminPassword.Name = "picSettingsAdminPassword";
            this.picSettingsAdminPassword.Size = new System.Drawing.Size(16, 16);
            this.picSettingsAdminPassword.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsAdminPassword.TabIndex = 131;
            this.picSettingsAdminPassword.TabStop = false;
            //
            // txtSettingsAdminPassword
            //
            this.txtSettingsAdminPassword.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtSettingsAdminPassword.Location = new System.Drawing.Point(45, 36);
            this.txtSettingsAdminPassword.Name = "txtSettingsAdminPassword";
            this.txtSettingsAdminPassword.Size = new System.Drawing.Size(200, 23);
            this.txtSettingsAdminPassword.TabIndex = 130;
            //
            // lblSettingsAdminPassword
            //
            this.lblSettingsAdminPassword.AutoSize = true;
            this.lblSettingsAdminPassword.Location = new System.Drawing.Point(42, 12);
            this.lblSettingsAdminPassword.Name = "lblSettingsAdminPassword";
            this.lblSettingsAdminPassword.Size = new System.Drawing.Size(96, 15);
            this.lblSettingsAdminPassword.TabIndex = 129;
            this.lblSettingsAdminPassword.Text = "Admin password";
            //
            // txtSettingsGamePassword
            //
            this.txtSettingsGamePassword.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtSettingsGamePassword.Location = new System.Drawing.Point(35, 185);
            this.txtSettingsGamePassword.Name = "txtSettingsGamePassword";
            this.txtSettingsGamePassword.Size = new System.Drawing.Size(200, 23);
            this.txtSettingsGamePassword.TabIndex = 228;
            //
            // uscServerSettingsConfiguration
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.picSettingsGamePassword);
            this.Controls.Add(this.lblSettingsGamePassword);
            this.Controls.Add(this.lnkSettingsSetGamePassword);
            this.Controls.Add(this.pnlSettingsAdminPassword);
            this.Controls.Add(this.txtSettingsGamePassword);
            this.Controls.Add(this.pnlSettingsSetidleKickLimit);
            this.Controls.Add(this.chkSettingsNoIdleKickLimit);
            this.Controls.Add(this.picSettingsIdleKickLimit);
            this.Controls.Add(this.chkSettingsProfanityFilter);
            this.Controls.Add(this.picSettingsProfanityFilter);
            this.Controls.Add(this.lnkSettingsSetPlayerLimit);
            this.Controls.Add(this.picSettingsPlayerLimit);
            this.Controls.Add(this.numSettingsPlayerLimit);
            this.Controls.Add(this.chkSettingsRanked);
            this.Controls.Add(this.picSettingsRanked);
            this.Controls.Add(this.chkSettingsPunkbuster);
            this.Controls.Add(this.picSettingsPunkbuster);
            this.Controls.Add(this.lblSettingsPlayerLimit);
            this.Name = "uscServerSettingsConfiguration";
            this.Size = new System.Drawing.Size(817, 236);
            this.pnlSettingsSetidleKickLimit.ResumeLayout(false);
            this.pnlSettingsSetidleKickLimit.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsIdleKickLimit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsIdleKickLimit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsProfanityFilter)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsPlayerLimit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numSettingsPlayerLimit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsRanked)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsPunkbuster)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsGamePassword)).EndInit();
            this.pnlSettingsAdminPassword.ResumeLayout(false);
            this.pnlSettingsAdminPassword.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsAdminPassword)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel pnlSettingsSetidleKickLimit;
        private System.Windows.Forms.LinkLabel lnkSettingsSetidleKickLimit;
        private System.Windows.Forms.NumericUpDown numSettingsIdleKickLimit;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.CheckBox chkSettingsNoIdleKickLimit;
        private System.Windows.Forms.PictureBox picSettingsIdleKickLimit;
        private System.Windows.Forms.CheckBox chkSettingsProfanityFilter;
        private System.Windows.Forms.PictureBox picSettingsProfanityFilter;
        private System.Windows.Forms.LinkLabel lnkSettingsSetPlayerLimit;
        private System.Windows.Forms.PictureBox picSettingsPlayerLimit;
        private System.Windows.Forms.NumericUpDown numSettingsPlayerLimit;
        private System.Windows.Forms.CheckBox chkSettingsRanked;
        private System.Windows.Forms.PictureBox picSettingsRanked;
        private System.Windows.Forms.CheckBox chkSettingsPunkbuster;
        private System.Windows.Forms.PictureBox picSettingsPunkbuster;
        private System.Windows.Forms.Label lblSettingsPlayerLimit;
        private System.Windows.Forms.PictureBox picSettingsGamePassword;
        private System.Windows.Forms.Label lblSettingsGamePassword;
        private System.Windows.Forms.LinkLabel lnkSettingsSetGamePassword;
        private System.Windows.Forms.Panel pnlSettingsAdminPassword;
        private System.Windows.Forms.LinkLabel lnkSettingsSetAdminPassword;
        private System.Windows.Forms.PictureBox picSettingsAdminPassword;
        private System.Windows.Forms.TextBox txtSettingsAdminPassword;
        private System.Windows.Forms.Label lblSettingsAdminPassword;
        private System.Windows.Forms.TextBox txtSettingsGamePassword;
    }*/
}
