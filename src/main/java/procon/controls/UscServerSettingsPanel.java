package procon.controls;

public class UscServerSettingsPanel {

    /*public partial class uscServerSettingsPanel : UserControl {

        private frmMain m_frmMain;
        private CLocalization m_clocLanguage;

        public uscServerSettingsPanel() {
            InitializeComponent();

            this.SetStyle(ControlStyles.UserPaint, true);
            this.SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.DoubleBuffer, true);

            this.cboSelectedSettingsPanel.DisplayMember = "DisplayName";
        }

        private void uscServerSettingsPanel_Load(object sender, EventArgs e) {

        }

        public void Initialize(frmMain frmMainWindow) {
            this.m_frmMain = frmMainWindow;
        }

        public void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            *//*
            this.cmiExportSettings.Text = this.m_clocLanguage.GetLocalized("uscServerSettingsPanel.cmiExportSettings");
            this.cmiImportSettings.Text = this.m_clocLanguage.GetLocalized("uscServerSettingsPanel.cmiImportSettings");
            this.cmiConfigGenerator.Text = this.m_clocLanguage.GetLocalized("uscServerSettingsPanel.cmiConfigGenerator");
            *//*

            foreach (uscServerSettings page in this.cboSelectedSettingsPanel.Items) {
                page.SetLocalization(clocLanguage);
            }
        }

        public void SetConnection(PRoConClient prcClient) {
            if (prcClient != null) {
                if (prcClient.Game != null) {
                    this.prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    prcClient.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(prcClient_GameTypeDiscovered);
                }
            }
        }

        private void prcClient_GameTypeDiscovered(PRoConClient sender) {
            sender.ProconPrivileges += new PRoConClient.ProconPrivilegesHandler(sender_ProconPrivileges);

            this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsDetails());
            this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsConfiguration());

            if (sender.Game is BFBC2Client) {
                this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsGameplayBFBC2());
            }
            else if (sender.Game is MoHClient) {
                this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsGameplayMoH());
            }

            this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsTextChatModeration());
            this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsTeamKills());
            this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsLevelVariables());

            if (sender.Game is BFBC2Client) {
                this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsConfigGeneratorBFBC2());
            }
            else if (sender.Game is MoHClient) {
                this.cboSelectedSettingsPanel.Items.Add(new uscServerSettingsConfigGeneratorMoH());
            }

            foreach (uscServerSettings page in this.cboSelectedSettingsPanel.Items) {
                if (this.pnlSettingsPanels.Controls.Contains(page) == false) {
                    this.pnlSettingsPanels.Controls.Add(page);
                    page.Dock = DockStyle.Fill;
                }

                if (this.cboSelectedSettingsPanel.SelectedItem == null) {
                    this.cboSelectedSettingsPanel.SelectedItem = page;
                }

                if (this.m_frmMain != null) {
                    page.SettingLoading = this.m_frmMain.picAjaxStyleLoading.Image;
                    page.SettingFail = this.m_frmMain.picAjaxStyleFail.Image;
                    page.SettingSuccess = this.m_frmMain.picAjaxStyleSuccess.Image;
                }

                page.SetConnection(sender);

                if (this.m_clocLanguage != null) {
                    page.SetLocalization(this.m_clocLanguage);
                }
            }
        }

        public void sender_ProconPrivileges(PRoConClient sender, CPrivileges spPrivs) {
            this.pnlSettingsPanels.Enabled = spPrivs.CanAlterServerSettings;
        }

        private void cboSelectedSettingsPanel_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.cboSelectedSettingsPanel.SelectedItem != null) {

                foreach (uscServerSettings page in this.cboSelectedSettingsPanel.Items) {
                    if (page == this.cboSelectedSettingsPanel.SelectedItem) {
                        page.Show();
                    }
                    else {
                        page.Hide();
                    }
                }
            }
        }

    }*/


    /*partial class uscServerSettingsPanel {
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
            this.cboSelectedSettingsPanel = new System.Windows.Forms.ComboBox();
            this.pnlSettingsPanels = new System.Windows.Forms.Panel();
            this.SuspendLayout();
            //
            // cboSelectedSettingsPanel
            //
            this.cboSelectedSettingsPanel.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboSelectedSettingsPanel.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cboSelectedSettingsPanel.FormattingEnabled = true;
            this.cboSelectedSettingsPanel.Location = new System.Drawing.Point(13, 13);
            this.cboSelectedSettingsPanel.Name = "cboSelectedSettingsPanel";
            this.cboSelectedSettingsPanel.Size = new System.Drawing.Size(365, 23);
            this.cboSelectedSettingsPanel.TabIndex = 0;
            this.cboSelectedSettingsPanel.SelectedIndexChanged += new System.EventHandler(this.cboSelectedSettingsPanel_SelectedIndexChanged);
            //
            // pnlSettingsPanels
            //
            this.pnlSettingsPanels.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.pnlSettingsPanels.Location = new System.Drawing.Point(13, 42);
            this.pnlSettingsPanels.Name = "pnlSettingsPanels";
            this.pnlSettingsPanels.Size = new System.Drawing.Size(987, 449);
            this.pnlSettingsPanels.TabIndex = 1;
            //
            // uscServerSettingsPanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.pnlSettingsPanels);
            this.Controls.Add(this.cboSelectedSettingsPanel);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscServerSettingsPanel";
            this.Padding = new System.Windows.Forms.Padding(10);
            this.Size = new System.Drawing.Size(1013, 504);
            this.Load += new System.EventHandler(this.uscServerSettingsPanel_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ComboBox cboSelectedSettingsPanel;
        private System.Windows.Forms.Panel pnlSettingsPanels;

    }*/
}
