package procon.forms;

public class BasicConsole {

    /*public partial class BasicConsole : Form {

        public delegate PRoConApplication WindowLoadedHandler(bool execute);
        public event WindowLoadedHandler WindowLoaded;

        private PRoConApplication m_praApplication;

        public BasicConsole() {
            InitializeComponent();
        }

        private void BasicConsole_Load(object sender, EventArgs e) {

            this.m_praApplication = this.WindowLoaded(false);
            this.m_praApplication.Connections.ConnectionAdded += new ConnectionDictionary.ConnectionAlteredHandler(Connections_ConnectionAdded);
            this.m_praApplication.execute();

            if (this.m_praApplication.CustomTitle.Length > 0) {
                this.Text = this.m_praApplication.CustomTitle;
            }
        }

        private void Connections_ConnectionAdded(PRoConClient item) {
            item.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(item_GameTypeDiscovered);

        }

        private void item_GameTypeDiscovered(PRoConClient sender) {
            sender.ConnectionClosed += new PRoConClient.EmptyParamterHandler(sender_ConnectionClosed);
            sender.ConnectionFailure += new PRoConClient.FailureHandler(sender_ConnectionFailure);
            sender.ConnectSuccess += new PRoConClient.EmptyParamterHandler(sender_ConnectSuccess);
            sender.Login += new PRoConClient.EmptyParamterHandler(sender_Login);
            sender.LoginAttempt += new PRoConClient.EmptyParamterHandler(sender_LoginAttempt);
            sender.LoginFailure += new PRoConClient.AuthenticationFailureHandler(sender_LoginFailure);
            sender.Logout += new PRoConClient.EmptyParamterHandler(sender_Logout);
        }

        private void UpdateConnectionsLabel() {

            StringBuilder builder = new StringBuilder();

            foreach (PRoConClient client in this.m_praApplication.Connections) {

                if (client.State == ConnectionState.Connected && client.IsLoggedIn == true) {
                    builder.AppendFormat("{0,15}: {1}\r\n", "LoggedIn", client.HostNamePort);
                }
                else if (client.State == ConnectionState.Connected) {
                    builder.AppendFormat("{0,15}: {1}\r\n", "Connected", client.HostNamePort);
                }
                else if (client.State == ConnectionState.Connecting) {
                    builder.AppendFormat("{0,15}: {1}\r\n", "Connecting", client.HostNamePort);
                }
                else if (client.State == ConnectionState.Error) {
                    builder.AppendFormat("{0,15}: {1}\r\n", "Connection Error", client.HostNamePort);
                }
                else {
                    builder.AppendFormat("{0,15}: {1}\r\n", "Disconnected", client.HostNamePort);
                }
            }

            this.label1.Text = builder.ToString();
        }

        void sender_Logout(PRoConClient sender) {
            this.UpdateConnectionsLabel();
        }

        void sender_LoginAttempt(PRoConClient sender) {
            this.UpdateConnectionsLabel();
        }

        void sender_Login(PRoConClient sender) {
            this.UpdateConnectionsLabel();
        }

        void sender_ConnectSuccess(PRoConClient sender) {
            this.UpdateConnectionsLabel();
        }

        void sender_ConnectionFailure(PRoConClient sender, Exception exception) {
            this.UpdateConnectionsLabel();
        }

        void sender_ConnectionClosed(PRoConClient sender) {
            this.UpdateConnectionsLabel();
        }

        void sender_LoginFailure(PRoConClient sender, string strError) {
            this.UpdateConnectionsLabel();
        }

        private void BasicConsole_FormClosing(object sender, FormClosingEventArgs e) {
            this.m_praApplication.Shutdown();
        }
    }*/

    /*partial class BasicConsole {
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

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(BasicConsole));
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            //
            // label1
            //
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(243, 165);
            this.label1.TabIndex = 0;
            //
            // BasicConsole
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(267, 183);
            this.Controls.Add(this.label1);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "BasicConsole";
            this.Text = "PRoCon Frostbite - Console";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.BasicConsole_FormClosing);
            this.Load += new System.EventHandler(this.BasicConsole_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label1;


    }*/
}
