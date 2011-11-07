package procon.controls.serversettings;

public class UscServerSettings {

    /*public partial class uscServerSettings : uscPage {

        public string DisplayName {
            get;
            protected set;
        }

        protected CLocalization Language {
            get;
            private set;
        }

        protected PRoConClient Client {
            get;
            private set;
        }

        protected bool IgnoreEvents {
            get;
            set;
        }

        public uscServerSettings() {
            InitializeComponent();
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            this.Language = clocLanguage;
        }

        public override void SetConnection(Core.Remote.PRoConClient prcClient) {
            this.Client = prcClient;

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
            this.Client.Game.ResponseError += new FrostbiteClient.ResponseErrorHandler(m_prcClient_ResponseError);
        }

        private void m_prcClient_ResponseError(FrostbiteClient sender, Packet originalRequest, string errorMessage) {
            if (originalRequest.Words.Count >= 1) {
                this.OnSettingResponse(originalRequest.Words[0].ToLower(), null, false);
            }
        }
    }*/

    /*partial class uscServerSettings {
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
            this.SuspendLayout();
            //
            // uscServerSettings
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.DisplayName = "uscServerSettings";
            this.Size = new System.Drawing.Size(175, 173);
            this.ResumeLayout(false);

        }

        #endregion
    }*/
}
