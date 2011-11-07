package procon.controls.serversettings;

public class UscServerSettingsDetails {

    /*public partial class uscServerSettingsDetails : uscServerSettings {

        private string m_strPreviousSuccessServerName;
        private string m_strPreviousSuccessServerDescription;
        private string m_strPreviousSuccessBannerURL;

        private CDownloadFile m_cdfBanner;

        public uscServerSettingsDetails() {
            InitializeComponent();

            this.AsyncSettingControls.Add("vars.servername", new AsyncStyleSetting(this.picSettingsServerName, this.txtSettingsServerName, new Control[] { this.lblSettingsServerName, this.txtSettingsServerName, this.lnkSettingsSetServerName }, true));
            this.AsyncSettingControls.Add("vars.serverdescription", new AsyncStyleSetting(this.picSettingsDescription, this.txtSettingsDescription, new Control[] { this.lblSettingsDescription, this.txtSettingsDescription, this.lnkSettingsSetDescription }, true));
            this.AsyncSettingControls.Add("vars.bannerurl", new AsyncStyleSetting(this.picSettingsBannerURL, this.txtSettingsBannerURL, new Control[] { this.lblSettingsBannerURL, this.txtSettingsBannerURL, this.lnkSettingsSetBannerURL }, true));

            this.m_strPreviousSuccessServerName = String.Empty;
            this.m_strPreviousSuccessServerDescription = String.Empty;
            this.m_strPreviousSuccessBannerURL = String.Empty;
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            base.SetLocalization(clocLanguage);

            this.lblSettingsDescription.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsDescription");
            this.lnkSettingsSetDescription.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetDescription");
            this.lblSettingsBannerURL.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsBannerURL");
            this.lnkSettingsSetBannerURL.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetBannerURL");

            this.lblSettingsDownloadedBannerURLError.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsDownloadedBannerURLError");

            this.lblSettingsServerName.Text = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsServerName");
            this.lnkSettingsSetServerName.Text = this.Language.GetLocalized("uscServerSettingsPanel.lnkSettingsSetServerName");

            this.DisplayName = this.Language.GetLocalized("uscServerSettingsPanel.lblSettingsDetails");
        }

        public override void SetConnection(PRoConClient prcClient) {
            base.SetConnection(prcClient);

            if (this.Client != null) {
                if (this.Client.Game != null) {
                    this.Client_GameTypeDiscovered(prcClient);
                }
                else {
                    this.Client.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(Client_GameTypeDiscovered);
                }
            }
        }


        private void Client_GameTypeDiscovered(PRoConClient sender) {

            this.Client.Game.ServerName += new FrostbiteClient.ServerNameHandler(m_prcClient_ServerName);
            this.Client.Game.BannerUrl += new FrostbiteClient.BannerUrlHandler(m_prcClient_BannerUrl);
            this.Client.Game.ServerDescription += new FrostbiteClient.ServerDescriptionHandler(m_prcClient_ServerDescription);

        }

        #region Banner URL

        private void m_prcClient_BannerUrl(FrostbiteClient sender, string url) {
            this.OnSettingResponse("vars.bannerurl", url, true);

            if (String.Compare(this.m_strPreviousSuccessBannerURL, url) != 0) {

                if (String.IsNullOrEmpty(url) == false) {
                    this.DownloadBannerURL(url);
                }
                else {
                    this.cdfBanner_DownloadComplete(null);
                }
            }

            this.m_strPreviousSuccessBannerURL = url;
        }

        public void OnSettingsBannerURLSuccess(FrostbiteClient sender, string strSuccessBannerURL) {
            if (String.Compare(this.m_strPreviousSuccessBannerURL, strSuccessBannerURL) != 0) {

                if (String.IsNullOrEmpty(strSuccessBannerURL) == false) {
                    this.DownloadBannerURL(strSuccessBannerURL);
                }
                else {
                    this.cdfBanner_DownloadComplete(null);
                }
            }

            this.m_strPreviousSuccessBannerURL = strSuccessBannerURL;
        }

        private void lnkSettingsSetBannerURL_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                // TO DO: More error reporting about the image.
                if (String.IsNullOrEmpty(this.txtSettingsBannerURL.Text) == false) {
                    this.DownloadBannerURL(this.txtSettingsBannerURL.Text);
                }
                else {
                    this.cdfBanner_DownloadComplete(null);
                }
                //this.picSettingsDownloadedBannerURL.ImageLocation = this.txtSettingsBannerURL.Text;

                this.txtSettingsBannerURL.Focus();
                this.WaitForSettingResponse("vars.bannerurl", this.m_strPreviousSuccessBannerURL);

                this.Client.Game.SendSetVarsBannerUrlPacket(this.txtSettingsBannerURL.Text);
            }
        }

        #endregion

        #region Server Description

        private void m_prcClient_ServerDescription(FrostbiteClient sender, string serverDescription) {
            this.m_strPreviousSuccessServerDescription = serverDescription.Replace("|", Environment.NewLine);
            this.OnSettingResponse("vars.serverdescription", this.m_strPreviousSuccessServerDescription, true);

        }

        private void lnkSettingsSetDescription_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.txtSettingsDescription.Focus();
                this.WaitForSettingResponse("vars.serverdescription", this.m_strPreviousSuccessServerDescription);

                this.Client.Game.SendSetVarsServerDescriptionPacket(this.txtSettingsDescription.Text.Replace(Environment.NewLine, "|"));
                //this.SendCommand("vars.serverDescription", );
            }
        }

        #endregion

        #region Server Name

        private void m_prcClient_ServerName(FrostbiteClient sender, string strServerName) {
            this.OnSettingResponse("vars.servername", strServerName, true);
            this.m_strPreviousSuccessServerName = strServerName;
        }

        private void lnkSettingsSetServerName_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.Client != null && this.Client.Game != null) {
                this.txtSettingsServerName.Focus();
                this.WaitForSettingResponse("vars.servername", this.m_strPreviousSuccessServerName);

                this.Client.Game.SendSetVarsServerNamePacket(this.txtSettingsServerName.Text);
                //this.SendCommand("vars.serverName", this.txtSettingsServerName.Text);
            }
        }

        #endregion

        #region Download Banner

        private void DownloadBannerURL(string strUrl) {
            if (strUrl != null) {
                this.m_cdfBanner = new CDownloadFile(strUrl);
                this.m_cdfBanner.DownloadComplete += new CDownloadFile.DownloadFileEventDelegate(cdfBanner_DownloadComplete);
                this.m_cdfBanner.DownloadError += new CDownloadFile.DownloadFileEventDelegate(cdfBanner_DownloadError);
                this.m_cdfBanner.BeginDownload();
            }
        }

        private void cdfBanner_DownloadError(CDownloadFile cdfSender) {
            this.lblSettingsDownloadedBannerURLError.Visible = true;
            this.picSettingsDownloadedBannerURL.Image = null;
        }

        private void cdfBanner_DownloadComplete(CDownloadFile cdfSender) {
            this.lblSettingsDownloadedBannerURLError.Visible = false;

            if (cdfSender != null) {
                MemoryStream msImage = new MemoryStream(cdfSender.CompleteFileData);
                Image imgCompleted = Image.FromStream(msImage);

                this.picSettingsDownloadedBannerURL.Image = imgCompleted;
            }
            else {
                this.picSettingsDownloadedBannerURL.Image = null;
            }
        }

        #endregion
    }*/

    /*partial class uscServerSettingsDetails {
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
            this.lnkSettingsSetServerName = new System.Windows.Forms.LinkLabel();
            this.picSettingsServerName = new System.Windows.Forms.PictureBox();
            this.txtSettingsServerName = new System.Windows.Forms.TextBox();
            this.lblSettingsServerName = new System.Windows.Forms.Label();
            this.lblSettingsDownloadedBannerURLError = new System.Windows.Forms.Label();
            this.lnkSettingsSetBannerURL = new System.Windows.Forms.LinkLabel();
            this.lnkSettingsSetDescription = new System.Windows.Forms.LinkLabel();
            this.picSettingsDownloadedBannerURL = new System.Windows.Forms.PictureBox();
            this.picSettingsBannerURL = new System.Windows.Forms.PictureBox();
            this.txtSettingsBannerURL = new System.Windows.Forms.TextBox();
            this.lblSettingsBannerURL = new System.Windows.Forms.Label();
            this.picSettingsDescription = new System.Windows.Forms.PictureBox();
            this.txtSettingsDescription = new System.Windows.Forms.TextBox();
            this.lblSettingsDescription = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsServerName)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsDownloadedBannerURL)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsBannerURL)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsDescription)).BeginInit();
            this.SuspendLayout();
            //
            // lnkSettingsSetServerName
            //
            this.lnkSettingsSetServerName.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetServerName.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.lnkSettingsSetServerName.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetServerName.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetServerName.Location = new System.Drawing.Point(730, 61);
            this.lnkSettingsSetServerName.Name = "lnkSettingsSetServerName";
            this.lnkSettingsSetServerName.Size = new System.Drawing.Size(254, 17);
            this.lnkSettingsSetServerName.TabIndex = 214;
            this.lnkSettingsSetServerName.TabStop = true;
            this.lnkSettingsSetServerName.Text = "Apply";
            this.lnkSettingsSetServerName.TextAlign = System.Drawing.ContentAlignment.TopRight;
            this.lnkSettingsSetServerName.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetServerName_LinkClicked);
            //
            // picSettingsServerName
            //
            this.picSettingsServerName.Location = new System.Drawing.Point(8, 7);
            this.picSettingsServerName.Name = "picSettingsServerName";
            this.picSettingsServerName.Size = new System.Drawing.Size(16, 16);
            this.picSettingsServerName.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsServerName.TabIndex = 213;
            this.picSettingsServerName.TabStop = false;
            //
            // txtSettingsServerName
            //
            this.txtSettingsServerName.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtSettingsServerName.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtSettingsServerName.Location = new System.Drawing.Point(36, 31);
            this.txtSettingsServerName.MaxLength = 64;
            this.txtSettingsServerName.Name = "txtSettingsServerName";
            this.txtSettingsServerName.Size = new System.Drawing.Size(948, 23);
            this.txtSettingsServerName.TabIndex = 212;
            //
            // lblSettingsServerName
            //
            this.lblSettingsServerName.AutoSize = true;
            this.lblSettingsServerName.Location = new System.Drawing.Point(33, 7);
            this.lblSettingsServerName.Name = "lblSettingsServerName";
            this.lblSettingsServerName.Size = new System.Drawing.Size(72, 15);
            this.lblSettingsServerName.TabIndex = 211;
            this.lblSettingsServerName.Text = "Server name";
            //
            // lblSettingsDownloadedBannerURLError
            //
            this.lblSettingsDownloadedBannerURLError.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblSettingsDownloadedBannerURLError.ForeColor = System.Drawing.Color.Maroon;
            this.lblSettingsDownloadedBannerURLError.Location = new System.Drawing.Point(309, 352);
            this.lblSettingsDownloadedBannerURLError.Name = "lblSettingsDownloadedBannerURLError";
            this.lblSettingsDownloadedBannerURLError.Size = new System.Drawing.Size(374, 24);
            this.lblSettingsDownloadedBannerURLError.TabIndex = 210;
            this.lblSettingsDownloadedBannerURLError.Text = "Error downloading image";
            this.lblSettingsDownloadedBannerURLError.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.lblSettingsDownloadedBannerURLError.Visible = false;
            //
            // lnkSettingsSetBannerURL
            //
            this.lnkSettingsSetBannerURL.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetBannerURL.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.lnkSettingsSetBannerURL.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetBannerURL.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetBannerURL.Location = new System.Drawing.Point(730, 307);
            this.lnkSettingsSetBannerURL.Name = "lnkSettingsSetBannerURL";
            this.lnkSettingsSetBannerURL.Size = new System.Drawing.Size(254, 17);
            this.lnkSettingsSetBannerURL.TabIndex = 209;
            this.lnkSettingsSetBannerURL.TabStop = true;
            this.lnkSettingsSetBannerURL.Text = "Apply";
            this.lnkSettingsSetBannerURL.TextAlign = System.Drawing.ContentAlignment.TopRight;
            this.lnkSettingsSetBannerURL.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetBannerURL_LinkClicked);
            //
            // lnkSettingsSetDescription
            //
            this.lnkSettingsSetDescription.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetDescription.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.lnkSettingsSetDescription.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSettingsSetDescription.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSettingsSetDescription.Location = new System.Drawing.Point(696, 211);
            this.lnkSettingsSetDescription.Name = "lnkSettingsSetDescription";
            this.lnkSettingsSetDescription.Size = new System.Drawing.Size(288, 17);
            this.lnkSettingsSetDescription.TabIndex = 208;
            this.lnkSettingsSetDescription.TabStop = true;
            this.lnkSettingsSetDescription.Text = "Apply";
            this.lnkSettingsSetDescription.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.lnkSettingsSetDescription.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkSettingsSetDescription_LinkClicked);
            //
            // picSettingsDownloadedBannerURL
            //
            this.picSettingsDownloadedBannerURL.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.picSettingsDownloadedBannerURL.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.picSettingsDownloadedBannerURL.ImageLocation = "";
            this.picSettingsDownloadedBannerURL.Location = new System.Drawing.Point(240, 332);
            this.picSettingsDownloadedBannerURL.Name = "picSettingsDownloadedBannerURL";
            this.picSettingsDownloadedBannerURL.Size = new System.Drawing.Size(512, 64);
            this.picSettingsDownloadedBannerURL.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage;
            this.picSettingsDownloadedBannerURL.TabIndex = 207;
            this.picSettingsDownloadedBannerURL.TabStop = false;
            //
            // picSettingsBannerURL
            //
            this.picSettingsBannerURL.Location = new System.Drawing.Point(8, 251);
            this.picSettingsBannerURL.Name = "picSettingsBannerURL";
            this.picSettingsBannerURL.Size = new System.Drawing.Size(16, 16);
            this.picSettingsBannerURL.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsBannerURL.TabIndex = 206;
            this.picSettingsBannerURL.TabStop = false;
            //
            // txtSettingsBannerURL
            //
            this.txtSettingsBannerURL.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtSettingsBannerURL.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtSettingsBannerURL.Location = new System.Drawing.Point(34, 275);
            this.txtSettingsBannerURL.MaxLength = 64;
            this.txtSettingsBannerURL.Name = "txtSettingsBannerURL";
            this.txtSettingsBannerURL.Size = new System.Drawing.Size(948, 23);
            this.txtSettingsBannerURL.TabIndex = 205;
            //
            // lblSettingsBannerURL
            //
            this.lblSettingsBannerURL.AutoSize = true;
            this.lblSettingsBannerURL.Location = new System.Drawing.Point(30, 250);
            this.lblSettingsBannerURL.Name = "lblSettingsBannerURL";
            this.lblSettingsBannerURL.Size = new System.Drawing.Size(68, 15);
            this.lblSettingsBannerURL.TabIndex = 204;
            this.lblSettingsBannerURL.Text = "Banner URL";
            //
            // picSettingsDescription
            //
            this.picSettingsDescription.Location = new System.Drawing.Point(8, 95);
            this.picSettingsDescription.Name = "picSettingsDescription";
            this.picSettingsDescription.Size = new System.Drawing.Size(16, 16);
            this.picSettingsDescription.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picSettingsDescription.TabIndex = 203;
            this.picSettingsDescription.TabStop = false;
            //
            // txtSettingsDescription
            //
            this.txtSettingsDescription.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtSettingsDescription.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtSettingsDescription.Location = new System.Drawing.Point(36, 120);
            this.txtSettingsDescription.MaxLength = 400;
            this.txtSettingsDescription.Multiline = true;
            this.txtSettingsDescription.Name = "txtSettingsDescription";
            this.txtSettingsDescription.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtSettingsDescription.Size = new System.Drawing.Size(948, 87);
            this.txtSettingsDescription.TabIndex = 202;
            //
            // lblSettingsDescription
            //
            this.lblSettingsDescription.AutoSize = true;
            this.lblSettingsDescription.Location = new System.Drawing.Point(30, 96);
            this.lblSettingsDescription.Name = "lblSettingsDescription";
            this.lblSettingsDescription.Size = new System.Drawing.Size(67, 15);
            this.lblSettingsDescription.TabIndex = 201;
            this.lblSettingsDescription.Text = "Description";
            //
            // uscServerSettingsDetails
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lnkSettingsSetServerName);
            this.Controls.Add(this.picSettingsServerName);
            this.Controls.Add(this.txtSettingsServerName);
            this.Controls.Add(this.lblSettingsServerName);
            this.Controls.Add(this.lblSettingsDownloadedBannerURLError);
            this.Controls.Add(this.lnkSettingsSetBannerURL);
            this.Controls.Add(this.lnkSettingsSetDescription);
            this.Controls.Add(this.picSettingsDownloadedBannerURL);
            this.Controls.Add(this.picSettingsBannerURL);
            this.Controls.Add(this.txtSettingsBannerURL);
            this.Controls.Add(this.lblSettingsBannerURL);
            this.Controls.Add(this.picSettingsDescription);
            this.Controls.Add(this.txtSettingsDescription);
            this.Controls.Add(this.lblSettingsDescription);
            this.Name = "uscServerSettingsDetails";
            this.Size = new System.Drawing.Size(992, 417);
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsServerName)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsDownloadedBannerURL)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsBannerURL)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picSettingsDescription)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.LinkLabel lnkSettingsSetServerName;
        private System.Windows.Forms.PictureBox picSettingsServerName;
        private System.Windows.Forms.TextBox txtSettingsServerName;
        private System.Windows.Forms.Label lblSettingsServerName;
        private System.Windows.Forms.Label lblSettingsDownloadedBannerURLError;
        private System.Windows.Forms.LinkLabel lnkSettingsSetBannerURL;
        private System.Windows.Forms.LinkLabel lnkSettingsSetDescription;
        private System.Windows.Forms.PictureBox picSettingsDownloadedBannerURL;
        private System.Windows.Forms.PictureBox picSettingsBannerURL;
        private System.Windows.Forms.TextBox txtSettingsBannerURL;
        private System.Windows.Forms.Label lblSettingsBannerURL;
        private System.Windows.Forms.PictureBox picSettingsDescription;
        private System.Windows.Forms.TextBox txtSettingsDescription;
        private System.Windows.Forms.Label lblSettingsDescription;
    }*/
}
