package procon.forms;

public class FrmManageAccounts {

    /*public partial class frmManageAccounts : Form {

        private frmMain m_frmMainWindow = null;
        private CLocalization m_clocLanguage = null;

        private const int INT_DELETING_ACCOUNT = 1;
        private const int INT_REMOVING_PRIVILEGES = 2;
        private const int INT_CHANGING_PASSWORD = 3;
        private const int INT_CHANGING_PRIVILEGES = 4;

        private int m_iConfirmationAction = 0;

        //public delegate void AccountDelegate(object sender, AccountEventArgs e);
        //public event AccountDelegate AccountCreated;
        //public event AccountDelegate AccountDeleted;
        //public event AccountDelegate AccountPrivilegesChanged;
        //public event AccountDelegate AccountPasswordChanged;
        //public event AccountDelegate AccountPrivilegesCleared;

        *//*
        public List<string[]> UserList {
            get {

                List<string[]> lstReturnList = new List<string[]>();

                foreach (ListViewItem lviUser in this.lstAccounts.Items) {
                    lstReturnList.Add(new string[] { lviUser.Text, lviUser.Tag.ToString() });
                }

                return lstReturnList;
            }
        }
        *//*
        private PRoConApplication m_paProcon;

        public frmManageAccounts(PRoConApplication paProcon, frmMain frmMainWindow) {
            InitializeComponent();

            this.m_paProcon = paProcon;
            this.m_paProcon.AccountsList.AccountAdded += new AccountDictionary.AccountAlteredHandler(AccountsList_AccountAdded);
            this.m_paProcon.AccountsList.AccountRemoved += new AccountDictionary.AccountAlteredHandler(AccountsList_AccountRemoved);

            this.m_frmMainWindow = frmMainWindow;

            Rectangle recWindow = new Rectangle();
            recWindow.Location = new Point(0, 0);
            recWindow.Height = 540;
            recWindow.Width = 600;
            this.Bounds = recWindow;

            Rectangle recPanels = new Rectangle();
            recPanels.Location = new Point(0, 0);
            recPanels.Height = 500;
            recPanels.Width = 600;
            this.pnlChooseAccount.Bounds = this.pnlCreateAccount.Bounds = this.pnlAlterPrivileges.Bounds = this.pnlConfirmation.Bounds = this.pnlEditingUser.Bounds = recPanels;

            this.uscSetPrivileges.OnCancelPrivileges += new uscPrivilegesSelection.OnCancelPrivilegesDelegate(uscSetPrivileges_OnCancelPrivileges);
            this.uscSetPrivileges.OnUpdatePrivileges += new uscPrivilegesSelection.OnUpdatePrivilegesDelegate(uscSetPrivileges_OnUpdatePrivileges);

            this.picDeleteAccount.Image = this.m_frmMainWindow.iglIcons.Images["cross.png"];
            this.picCreateNewAccount.Image = this.m_frmMainWindow.iglIcons.Images["new.png"];
            this.picRemovePrivileges.Image = this.m_frmMainWindow.iglIcons.Images["key_delete.png"];
            this.picEditGlobalPrivileges.Image = this.m_frmMainWindow.iglIcons.Images["key.png"];
        }

        private void frmManageAccounts_Load(object sender, EventArgs e) {
            this.lstAccounts.Items.Clear();

            foreach (Account accAccount in this.m_paProcon.AccountsList) {
                this.AccountsList_AccountAdded(accAccount);
            }
        }

        void uscSetPrivileges_OnUpdatePrivileges(string strAccountName, CPrivileges spUpdatedPrivs) {
            this.ShowPanel(this.pnlEditingUser);

            if (this.m_paProcon.AccountsList.Contains(strAccountName) == true) {

                foreach (PRoConClient prcClient in this.m_paProcon.Connections) {
                    if (prcClient.Layer.AccountPrivileges.Contains(strAccountName) == true) {
                        prcClient.Layer.AccountPrivileges[strAccountName].SetPrivileges(spUpdatedPrivs);
                    }
                }
            }

            //if (this.AccountPrivilegesChanged != null) {
            //    this.AccountPrivilegesChanged(this, new AccountEventArgs(strAccountName, spUpdatedPrivs, ""));
            //}
        }

        void uscSetPrivileges_OnCancelPrivileges() {
            this.ShowPanel(this.pnlEditingUser);
        }

        public void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            this.uscSetPrivileges.SetLocalization(this.m_clocLanguage);

            this.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.Title", null);

            // Choose account windows
            this.lblSelectAccountTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblSelectAccountTitle", null);
            this.lnkCreateNewAccount.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkCreateNewAccount", null);
            //this.lnkCreateNewAccount.LinkArea = new LinkArea(0,this.lnkCreateNewAccount.Text.Length);

            // Create new account window
            this.lblCreateNewAccountTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblCreateNewAccountTitle", null);
            this.lblUsername.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblUsername", null);
            this.lblPassword.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblPassword", null);
            this.lblUserNameExistsError.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblUserNameExistsError", null);
            this.btnCreateAccount.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.btnCreateAccount", null);
            this.btnCancelNewAccount.Text = this.m_clocLanguage.GetLocalized("global.cancel", null);

            // Editing account window
            this.lblChangePassword.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblChangePassword", null);
            this.lnkUpdatePassword.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkUpdatePassword", null);
            //this.lnkUpdatePassword.LinkArea = new LinkArea(0, this.lnkUpdatePassword.Text.Length);

            this.lnkSetGlobalPrivileges.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkSetGlobalPrivileges", null);
            this.tltipExtraInfo.SetToolTip(this.lnkSetGlobalPrivileges, this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkSetGlobalPrivileges.ToolTip", null));
            this.lnkRemovePrivileges.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkRemovePrivileges", null);
            //this.lnkRemovePrivileges.LinkArea = new LinkArea(0, this.lnkRemovePrivileges.Text.Length);
            this.tltipExtraInfo.SetToolTip(this.lnkRemovePrivileges, this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkRemovePrivileges.ToolTip", null));
            this.lnkDeleteUser.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkDeleteUser", null);
            //this.lnkDeleteUser.LinkArea = new LinkArea(0, this.lnkDeleteUser.Text.Length);
            this.tltipExtraInfo.SetToolTip(this.lnkDeleteUser, this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkDeleteUser.ToolTip", null));
            this.lnkEditAnotherAccount.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lnkEditAnotherAccount", null);
            //this.lnkEditAnotherAccount.LinkArea = new LinkArea(0, this.lnkEditAnotherAccount.Text.Length);

            // Confirmation window
            this.btnCancel.Text = this.m_clocLanguage.GetLocalized("global.cancel", null);

            this.btnClose.Text = this.m_clocLanguage.GetLocalized("global.close", null);
        }

        // TO DO: implement this so it changes the icon.. when i finally get icons from people =)
        public void OnAccountConnected(string strUsername) {

        }

        public void OnAccountDisconnected(string strUsername) {

        }

        private void AccountsList_AccountAdded(Account item) {
            if (this.lstAccounts.Items.ContainsKey(item.Name) == false) {
                ListViewItem lviNewAccount = new ListViewItem(item.Name);
                lviNewAccount.Tag = item.Password;
                lviNewAccount.Name = item.Name;
                lviNewAccount.ImageIndex = 0;

                this.lstAccounts.Items.Add(lviNewAccount);
            }
        }

        public void CreateNewAccount(string strUsername, string strPassword) {
            this.m_paProcon.AccountsList.CreateAccount(strUsername, strPassword);
            *//*
            if (this.lstAccounts.Items.ContainsKey(strUsername) == false) {
                ListViewItem lviNewAccount = new ListViewItem(strUsername);
                lviNewAccount.Tag = strPassword;
                lviNewAccount.Name = strUsername;
                lviNewAccount.ImageIndex = 0;

                this.lstAccounts.Items.Add(lviNewAccount);

                // Throw event
                this.AccountCreated(this, new AccountEventArgs(strUsername, default(CPrivileges), strPassword));
            }
            *//*
        }

        private void AccountsList_AccountRemoved(Account item) {
            if (this.lstAccounts.Items.ContainsKey(item.Name) == true) {
                this.lstAccounts.Items.Remove(this.lstAccounts.Items[item.Name]);
            }
        }

        public void DeleteAccount(string strUsername) {

            if (this.m_paProcon.AccountsList.Contains(strUsername) == true) {
                this.m_paProcon.AccountsList.Remove(strUsername);
            }
            *//*
            if (this.lstAccounts.Items.ContainsKey(strUsername) == true) {
                this.lstAccounts.Items.Remove(this.lstAccounts.Items[strUsername]);

                // Throw event
                this.AccountDeleted(this, new AccountEventArgs(strUsername, default(CPrivileges), ""));
            }
            *//*
        }

        public void ChangePassword(string strUsername, string strPassword) {

            if (this.m_paProcon.AccountsList.Contains(strUsername) == true) {
                this.m_paProcon.AccountsList[strUsername].Password = strPassword;
            }

            *//*
            if (this.lstAccounts.Items.ContainsKey(strUsername) == true) {
                this.lstAccounts.Items[strUsername].Tag = strPassword;

                // Throw event
                this.AccountPasswordChanged(this, new AccountEventArgs(strUsername, default(CPrivileges), strPassword));
            }
            *//*
        }

        public void RemoveAllPrivileges(string strUsername) {

            foreach (PRoConClient prcClient in this.m_paProcon.Connections) {
                if (prcClient.Layer.AccountPrivileges.Contains(strUsername) == true) {
                    prcClient.Layer.AccountPrivileges[strUsername].SetPrivileges(default(CPrivileges));
                }
            }

            //if (this.m_paProcon.AccountsList.Contains(strUsername) == true) {
            //    foreach (AccountPrivilege apPrivileges in this.m_paProcon.AccountsList[strUsername].AccountPrivileges) {
            //        apPrivileges.SetPrivileges(default(CPrivileges));
            //    }
            //}

            *//*
            if (this.lstAccounts.Items.ContainsKey(strUsername) == true) {

                // Throw event
                this.AccountPrivilegesCleared(this, new AccountEventArgs(strUsername, default(CPrivileges), ""));
            }
            *//*
        }

        private void ShowPanel(Panel pnlToShow) {
            this.pnlChooseAccount.Visible = false;
            this.pnlCreateAccount.Visible = false;
            this.pnlEditingUser.Visible = false;
            this.pnlConfirmation.Visible = false;
            this.pnlAlterPrivileges.Visible = false;

            if (pnlToShow == this.pnlChooseAccount) {
                this.lstAccounts.SelectedItems.Clear();
            }

            pnlToShow.Visible = true;
        }

        #region Create New Account

        private void lnkAddNewAccount_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            this.txtUsername.Text = String.Empty;
            this.txtPassword.Text = String.Empty;

            this.ShowPanel(this.pnlCreateAccount);
        }

        private void ValidateCreateNewUser() {

            bool blUsernameExists = false;

            foreach (ListViewItem lviAccount in this.lstAccounts.Items) {
                if (lviAccount.Text.CompareTo(this.txtUsername.Text) == 0) {
                    blUsernameExists = true;
                    break;
                }
            }

            this.lblUserNameExistsError.Visible = true & blUsernameExists;

            // If the username is not blank AND the username is valid AND they have input a password
            if (this.txtUsername.Text.CompareTo(String.Empty) != 0 && blUsernameExists == false && this.txtPassword.Text.CompareTo(String.Empty) != 0) {
                this.btnCreateAccount.Enabled = true;
            }
            else {
                this.btnCreateAccount.Enabled = false;
            }
        }

        private void txtUsername_TextChanged(object sender, EventArgs e) {
            this.ValidateCreateNewUser();
        }

        private void txtPassword_TextChanged(object sender, EventArgs e) {
            this.ValidateCreateNewUser();
        }

        private void btnCreateAccount_Click(object sender, EventArgs e) {
            this.CreateNewAccount(this.txtUsername.Text, this.txtPassword.Text);

            this.ShowPanel(this.pnlChooseAccount);
        }

        private void btnCancelNewAccount_Click(object sender, EventArgs e) {
            this.ShowPanel(this.pnlChooseAccount);
        }

        #endregion

        #region Edit account

        private void lstAccounts_SelectedIndexChanged(object sender, EventArgs e) {

            if (this.lstAccounts.SelectedItems.Count > 0) {

                this.lblEditingAccountTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblEditingAccountTitle", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.txtChangePassword.Text = String.Empty;

                this.ShowPanel(this.pnlEditingUser);
            }
        }

        private void lnkEditAnotherAccount_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            this.ShowPanel(this.pnlChooseAccount);
        }

        private void txtChangePassword_TextChanged(object sender, EventArgs e) {

            if (this.txtChangePassword.Text.CompareTo(String.Empty) != 0) {
                this.lnkUpdatePassword.Enabled = true;
            }
            else {
                this.lnkUpdatePassword.Enabled = false;
            }
        }

        private void lnkUpdatePassword_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            if (this.lstAccounts.SelectedItems.Count > 0) {

                this.lblConfirmationTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationTitle.UpdatePassword", null);

                this.lblExtraInformation.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblExtraInformation.UpdatePassword", new string[] { this.lstAccounts.SelectedItems[0].Text });
                this.lblConfirmationQuestion.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationQuestion.UpdatePassword", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.btnConfirm.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.btnConfirm.UpdatePassword", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.m_iConfirmationAction = frmManageAccounts.INT_CHANGING_PASSWORD;
                this.ShowPanel(this.pnlConfirmation);
            }
        }

        private void lnkDeleteUser_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            if (this.lstAccounts.SelectedItems.Count > 0) {
                this.lblConfirmationTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationTitle.DeleteAccount", null);

                this.lblExtraInformation.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblExtraInformation.DeleteAccount", new string[] { this.lstAccounts.SelectedItems[0].Text });
                this.lblConfirmationQuestion.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationQuestion.DeleteAccount", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.btnConfirm.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.btnConfirm.DeleteAccount", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.m_iConfirmationAction = frmManageAccounts.INT_DELETING_ACCOUNT;
                this.ShowPanel(this.pnlConfirmation);
            }
        }

        private void lnkRemovePrivileges_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            if (this.lstAccounts.SelectedItems.Count > 0) {

                this.lblConfirmationTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationTitle.RemovePrivileges", null);

                this.lblExtraInformation.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblExtraInformation.RemovePrivileges", new string[] { this.lstAccounts.SelectedItems[0].Text });
                this.lblConfirmationQuestion.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationQuestion.RemovePrivileges", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.btnConfirm.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.btnConfirm.RemovePrivileges", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.m_iConfirmationAction = frmManageAccounts.INT_REMOVING_PRIVILEGES;
                this.ShowPanel(this.pnlConfirmation);
            }

        }

        private void btnConfirm_Click(object sender, EventArgs e) {

            if (this.lstAccounts.SelectedItems.Count > 0) {

                if (this.m_iConfirmationAction == frmManageAccounts.INT_DELETING_ACCOUNT) {
                    this.DeleteAccount(this.lstAccounts.SelectedItems[0].Text);

                    this.ShowPanel(this.pnlChooseAccount);
                }
                else if (this.m_iConfirmationAction == frmManageAccounts.INT_CHANGING_PASSWORD) {
                    this.ChangePassword(this.lstAccounts.SelectedItems[0].Text, this.txtChangePassword.Text);

                    this.ShowPanel(this.pnlEditingUser);
                }
                else if (this.m_iConfirmationAction == frmManageAccounts.INT_REMOVING_PRIVILEGES) {

                    this.RemoveAllPrivileges(this.lstAccounts.SelectedItems[0].Text);

                    this.ShowPanel(this.pnlEditingUser);
                }
                else if (this.m_iConfirmationAction == frmManageAccounts.INT_CHANGING_PRIVILEGES) {
                    this.uscSetPrivileges.AccountName = this.lstAccounts.SelectedItems[0].Text;
                    this.uscSetPrivileges.Privileges = this.CollectLowestPrivileges(this.lstAccounts.SelectedItems[0].Text);

                    this.ShowPanel(this.pnlAlterPrivileges);
                }
            }
        }

        public CPrivileges CollectLowestPrivileges(string strAccountName) {

            CPrivileges spLowestPrivileges = new CPrivileges();

            if (this.m_paProcon.AccountsList.Contains(strAccountName) == true) {
                spLowestPrivileges.PrivilegesFlags = CPrivileges.FullPrivilegesFlags;

                foreach (PRoConClient prcClient in this.m_paProcon.Connections) {
                    if (prcClient.Layer != null && prcClient.Layer.AccountPrivileges.Contains(strAccountName) == true) {
                        spLowestPrivileges.SetLowestPrivileges(prcClient.Layer.AccountPrivileges[strAccountName].Privileges);
                    }
                }


                //if (this.m_paProcon.AccountsList.Contains(strAccountName) == true) {
                //    foreach (AccountPrivilege apPrivilege in this.m_paProcon.AccountsList[strAccountName].AccountPrivileges) {

                //    }
                //}
            }

            return spLowestPrivileges;
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            this.ShowPanel(this.pnlEditingUser);
        }

        #endregion

        private void btnClose_Click(object sender, EventArgs e) {
            this.Close();
        }

        private void frmManageAccounts_Shown(object sender, EventArgs e) {
            this.ShowPanel(this.pnlChooseAccount);
        }

        private void lnkAlterGlobalPrivileges_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            if (this.lstAccounts.SelectedItems.Count > 0) {

                this.lblConfirmationTitle.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationTitle.AssignPrivileges", null);

                this.lblExtraInformation.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblExtraInformation.AssignPrivileges", null);
                this.lblConfirmationQuestion.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.lblConfirmationQuestion.AssignPrivileges", new string[] { this.lstAccounts.SelectedItems[0].Text });

                this.btnConfirm.Text = this.m_clocLanguage.GetLocalized("frmManageAccounts.btnConfirm.AssignPrivileges", null);

                this.m_iConfirmationAction = frmManageAccounts.INT_CHANGING_PRIVILEGES;
                this.ShowPanel(this.pnlConfirmation);
            }
        }

    }*/

    /*partial class frmManageAccounts {
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmManageAccounts));
            this.lstAccounts = new System.Windows.Forms.ListView();
            this.imlUsers = new System.Windows.Forms.ImageList(this.components);
            this.pnlChooseAccount = new System.Windows.Forms.Panel();
            this.picCreateNewAccount = new System.Windows.Forms.PictureBox();
            this.lblSelectAccountTitle = new System.Windows.Forms.Label();
            this.lnkCreateNewAccount = new System.Windows.Forms.LinkLabel();
            this.pnlEditingUser = new System.Windows.Forms.Panel();
            this.picDeleteAccount = new System.Windows.Forms.PictureBox();
            this.picRemovePrivileges = new System.Windows.Forms.PictureBox();
            this.picEditGlobalPrivileges = new System.Windows.Forms.PictureBox();
            this.lnkSetGlobalPrivileges = new System.Windows.Forms.LinkLabel();
            this.panel2 = new System.Windows.Forms.Panel();
            this.lnkEditAnotherAccount = new System.Windows.Forms.LinkLabel();
            this.lnkRemovePrivileges = new System.Windows.Forms.LinkLabel();
            this.lnkDeleteUser = new System.Windows.Forms.LinkLabel();
            this.txtChangePassword = new System.Windows.Forms.TextBox();
            this.lblChangePassword = new System.Windows.Forms.Label();
            this.lblEditingAccountTitle = new System.Windows.Forms.Label();
            this.lnkUpdatePassword = new System.Windows.Forms.LinkLabel();
            this.tltipExtraInfo = new System.Windows.Forms.ToolTip(this.components);
            this.pnlConfirmation = new System.Windows.Forms.Panel();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnConfirm = new System.Windows.Forms.Button();
            this.lblExtraInformation = new System.Windows.Forms.Label();
            this.lblConfirmationQuestion = new System.Windows.Forms.Label();
            this.lblConfirmationTitle = new System.Windows.Forms.Label();
            this.pnlCreateAccount = new System.Windows.Forms.Panel();
            this.lblUserNameExistsError = new System.Windows.Forms.Label();
            this.txtPassword = new System.Windows.Forms.TextBox();
            this.lblPassword = new System.Windows.Forms.Label();
            this.txtUsername = new System.Windows.Forms.TextBox();
            this.lblUsername = new System.Windows.Forms.Label();
            this.btnCancelNewAccount = new System.Windows.Forms.Button();
            this.btnCreateAccount = new System.Windows.Forms.Button();
            this.lblCreateNewAccountTitle = new System.Windows.Forms.Label();
            this.btnClose = new System.Windows.Forms.Button();
            this.pnlAlterPrivileges = new System.Windows.Forms.Panel();
            this.uscSetPrivileges = new PRoCon.uscPrivilegesSelection();
            this.pnlChooseAccount.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picCreateNewAccount)).BeginInit();
            this.pnlEditingUser.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picDeleteAccount)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picRemovePrivileges)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picEditGlobalPrivileges)).BeginInit();
            this.pnlConfirmation.SuspendLayout();
            this.pnlCreateAccount.SuspendLayout();
            this.pnlAlterPrivileges.SuspendLayout();
            this.SuspendLayout();
            //
            // lstAccounts
            //
            this.lstAccounts.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lstAccounts.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.None;
            this.lstAccounts.LargeImageList = this.imlUsers;
            this.lstAccounts.Location = new System.Drawing.Point(30, 48);
            this.lstAccounts.MultiSelect = false;
            this.lstAccounts.Name = "lstAccounts";
            this.lstAccounts.Size = new System.Drawing.Size(391, 362);
            this.lstAccounts.TabIndex = 0;
            this.lstAccounts.UseCompatibleStateImageBehavior = false;
            this.lstAccounts.View = System.Windows.Forms.View.Tile;
            this.lstAccounts.SelectedIndexChanged += new System.EventHandler(this.lstAccounts_SelectedIndexChanged);
            //
            // imlUsers
            //
            this.imlUsers.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlUsers.ImageStream")));
            this.imlUsers.TransparentColor = System.Drawing.Color.Transparent;
            this.imlUsers.Images.SetKeyName(0, "user-offline.ico");
            this.imlUsers.Images.SetKeyName(1, "user-online.ico");
            //
            // pnlChooseAccount
            //
            this.pnlChooseAccount.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.pnlChooseAccount.BackColor = System.Drawing.SystemColors.Window;
            this.pnlChooseAccount.Controls.Add(this.picCreateNewAccount);
            this.pnlChooseAccount.Controls.Add(this.lblSelectAccountTitle);
            this.pnlChooseAccount.Controls.Add(this.lnkCreateNewAccount);
            this.pnlChooseAccount.Controls.Add(this.lstAccounts);
            this.pnlChooseAccount.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.pnlChooseAccount.Location = new System.Drawing.Point(14, 14);
            this.pnlChooseAccount.Name = "pnlChooseAccount";
            this.pnlChooseAccount.Size = new System.Drawing.Size(456, 532);
            this.pnlChooseAccount.TabIndex = 29;
            //
            // picCreateNewAccount
            //
            this.picCreateNewAccount.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.picCreateNewAccount.Location = new System.Drawing.Point(30, 413);
            this.picCreateNewAccount.Name = "picCreateNewAccount";
            this.picCreateNewAccount.Size = new System.Drawing.Size(19, 18);
            this.picCreateNewAccount.TabIndex = 14;
            this.picCreateNewAccount.TabStop = false;
            //
            // lblSelectAccountTitle
            //
            this.lblSelectAccountTitle.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.lblSelectAccountTitle.Font = new System.Drawing.Font("Segoe UI", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSelectAccountTitle.Location = new System.Drawing.Point(26, 24);
            this.lblSelectAccountTitle.Name = "lblSelectAccountTitle";
            this.lblSelectAccountTitle.Size = new System.Drawing.Size(397, 21);
            this.lblSelectAccountTitle.TabIndex = 3;
            this.lblSelectAccountTitle.Text = "Choose the account you would like to change";
            //
            // lnkCreateNewAccount
            //
            this.lnkCreateNewAccount.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkCreateNewAccount.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lnkCreateNewAccount.AutoSize = true;
            this.lnkCreateNewAccount.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkCreateNewAccount.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkCreateNewAccount.Location = new System.Drawing.Point(56, 414);
            this.lnkCreateNewAccount.Name = "lnkCreateNewAccount";
            this.lnkCreateNewAccount.Size = new System.Drawing.Size(121, 15);
            this.lnkCreateNewAccount.TabIndex = 1;
            this.lnkCreateNewAccount.TabStop = true;
            this.lnkCreateNewAccount.Text = "Create a new account";
            this.lnkCreateNewAccount.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkAddNewAccount_LinkClicked);
            //
            // pnlEditingUser
            //
            this.pnlEditingUser.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.pnlEditingUser.BackColor = System.Drawing.SystemColors.Window;
            this.pnlEditingUser.Controls.Add(this.picDeleteAccount);
            this.pnlEditingUser.Controls.Add(this.picRemovePrivileges);
            this.pnlEditingUser.Controls.Add(this.picEditGlobalPrivileges);
            this.pnlEditingUser.Controls.Add(this.lnkSetGlobalPrivileges);
            this.pnlEditingUser.Controls.Add(this.panel2);
            this.pnlEditingUser.Controls.Add(this.lnkEditAnotherAccount);
            this.pnlEditingUser.Controls.Add(this.lnkRemovePrivileges);
            this.pnlEditingUser.Controls.Add(this.lnkDeleteUser);
            this.pnlEditingUser.Controls.Add(this.txtChangePassword);
            this.pnlEditingUser.Controls.Add(this.lblChangePassword);
            this.pnlEditingUser.Controls.Add(this.lblEditingAccountTitle);
            this.pnlEditingUser.Controls.Add(this.lnkUpdatePassword);
            this.pnlEditingUser.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.pnlEditingUser.Location = new System.Drawing.Point(489, 569);
            this.pnlEditingUser.Name = "pnlEditingUser";
            this.pnlEditingUser.Size = new System.Drawing.Size(456, 532);
            this.pnlEditingUser.TabIndex = 30;
            this.pnlEditingUser.Visible = false;
            //
            // picDeleteAccount
            //
            this.picDeleteAccount.Location = new System.Drawing.Point(42, 228);
            this.picDeleteAccount.Name = "picDeleteAccount";
            this.picDeleteAccount.Size = new System.Drawing.Size(19, 18);
            this.picDeleteAccount.TabIndex = 15;
            this.picDeleteAccount.TabStop = false;
            //
            // picRemovePrivileges
            //
            this.picRemovePrivileges.Location = new System.Drawing.Point(42, 189);
            this.picRemovePrivileges.Name = "picRemovePrivileges";
            this.picRemovePrivileges.Size = new System.Drawing.Size(19, 18);
            this.picRemovePrivileges.TabIndex = 14;
            this.picRemovePrivileges.TabStop = false;
            //
            // picEditGlobalPrivileges
            //
            this.picEditGlobalPrivileges.Location = new System.Drawing.Point(42, 144);
            this.picEditGlobalPrivileges.Name = "picEditGlobalPrivileges";
            this.picEditGlobalPrivileges.Size = new System.Drawing.Size(19, 18);
            this.picEditGlobalPrivileges.TabIndex = 13;
            this.picEditGlobalPrivileges.TabStop = false;
            //
            // lnkSetGlobalPrivileges
            //
            this.lnkSetGlobalPrivileges.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSetGlobalPrivileges.AutoSize = true;
            this.lnkSetGlobalPrivileges.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkSetGlobalPrivileges.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkSetGlobalPrivileges.Location = new System.Drawing.Point(68, 144);
            this.lnkSetGlobalPrivileges.Name = "lnkSetGlobalPrivileges";
            this.lnkSetGlobalPrivileges.Size = new System.Drawing.Size(169, 15);
            this.lnkSetGlobalPrivileges.TabIndex = 10;
            this.lnkSetGlobalPrivileges.TabStop = true;
            this.lnkSetGlobalPrivileges.Text = "Set privilges on all server layers";
            this.tltipExtraInfo.SetToolTip(this.lnkSetGlobalPrivileges, "Overwrites each layers individual privileges with a new set of rules");
            this.lnkSetGlobalPrivileges.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkAlterGlobalPrivileges_LinkClicked);
            //
            // panel2
            //
            this.panel2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel2.BackColor = System.Drawing.SystemColors.ControlLight;
            this.panel2.Location = new System.Drawing.Point(0, 428);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(456, 1);
            this.panel2.TabIndex = 9;
            //
            // lnkEditAnotherAccount
            //
            this.lnkEditAnotherAccount.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkEditAnotherAccount.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lnkEditAnotherAccount.AutoSize = true;
            this.lnkEditAnotherAccount.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkEditAnotherAccount.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkEditAnotherAccount.Location = new System.Drawing.Point(45, 432);
            this.lnkEditAnotherAccount.Name = "lnkEditAnotherAccount";
            this.lnkEditAnotherAccount.Size = new System.Drawing.Size(117, 15);
            this.lnkEditAnotherAccount.TabIndex = 8;
            this.lnkEditAnotherAccount.TabStop = true;
            this.lnkEditAnotherAccount.Text = "Edit another account";
            this.lnkEditAnotherAccount.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkEditAnotherAccount_LinkClicked);
            //
            // lnkRemovePrivileges
            //
            this.lnkRemovePrivileges.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkRemovePrivileges.AutoSize = true;
            this.lnkRemovePrivileges.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkRemovePrivileges.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkRemovePrivileges.Location = new System.Drawing.Point(68, 189);
            this.lnkRemovePrivileges.Name = "lnkRemovePrivileges";
            this.lnkRemovePrivileges.Size = new System.Drawing.Size(207, 15);
            this.lnkRemovePrivileges.TabIndex = 7;
            this.lnkRemovePrivileges.TabStop = true;
            this.lnkRemovePrivileges.Text = "Remove all individual server privileges";
            this.tltipExtraInfo.SetToolTip(this.lnkRemovePrivileges, "Removes this users access to all of the servers, essentially resetting the accoun" +
                    "t.");
            this.lnkRemovePrivileges.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkRemovePrivileges_LinkClicked);
            //
            // lnkDeleteUser
            //
            this.lnkDeleteUser.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkDeleteUser.AutoSize = true;
            this.lnkDeleteUser.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkDeleteUser.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkDeleteUser.Location = new System.Drawing.Point(68, 230);
            this.lnkDeleteUser.Name = "lnkDeleteUser";
            this.lnkDeleteUser.Size = new System.Drawing.Size(86, 15);
            this.lnkDeleteUser.TabIndex = 6;
            this.lnkDeleteUser.TabStop = true;
            this.lnkDeleteUser.Text = "Delete account";
            this.tltipExtraInfo.SetToolTip(this.lnkDeleteUser, "Deletes the user and removes their access from all servers.");
            this.lnkDeleteUser.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkDeleteUser_LinkClicked);
            //
            // txtChangePassword
            //
            this.txtChangePassword.Location = new System.Drawing.Point(54, 93);
            this.txtChangePassword.Name = "txtChangePassword";
            this.txtChangePassword.Size = new System.Drawing.Size(195, 23);
            this.txtChangePassword.TabIndex = 5;
            this.txtChangePassword.TextChanged += new System.EventHandler(this.txtChangePassword_TextChanged);
            //
            // lblChangePassword
            //
            this.lblChangePassword.AutoSize = true;
            this.lblChangePassword.Location = new System.Drawing.Point(50, 73);
            this.lblChangePassword.Name = "lblChangePassword";
            this.lblChangePassword.Size = new System.Drawing.Size(101, 15);
            this.lblChangePassword.TabIndex = 4;
            this.lblChangePassword.Text = "Change password";
            //
            // lblEditingAccountTitle
            //
            this.lblEditingAccountTitle.Font = new System.Drawing.Font("Segoe UI", 11.25F, System.Drawing.FontStyle.Bold);
            this.lblEditingAccountTitle.Location = new System.Drawing.Point(26, 24);
            this.lblEditingAccountTitle.Name = "lblEditingAccountTitle";
            this.lblEditingAccountTitle.Size = new System.Drawing.Size(414, 21);
            this.lblEditingAccountTitle.TabIndex = 3;
            this.lblEditingAccountTitle.Text = "Editing Phogue";
            //
            // lnkUpdatePassword
            //
            this.lnkUpdatePassword.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkUpdatePassword.AutoSize = true;
            this.lnkUpdatePassword.Enabled = false;
            this.lnkUpdatePassword.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkUpdatePassword.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkUpdatePassword.Location = new System.Drawing.Point(257, 97);
            this.lnkUpdatePassword.Name = "lnkUpdatePassword";
            this.lnkUpdatePassword.Size = new System.Drawing.Size(98, 15);
            this.lnkUpdatePassword.TabIndex = 1;
            this.lnkUpdatePassword.TabStop = true;
            this.lnkUpdatePassword.Text = "Update password";
            this.lnkUpdatePassword.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkUpdatePassword_LinkClicked);
            //
            // pnlConfirmation
            //
            this.pnlConfirmation.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.pnlConfirmation.BackColor = System.Drawing.SystemColors.Window;
            this.pnlConfirmation.Controls.Add(this.btnCancel);
            this.pnlConfirmation.Controls.Add(this.btnConfirm);
            this.pnlConfirmation.Controls.Add(this.lblExtraInformation);
            this.pnlConfirmation.Controls.Add(this.lblConfirmationQuestion);
            this.pnlConfirmation.Controls.Add(this.lblConfirmationTitle);
            this.pnlConfirmation.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.pnlConfirmation.Location = new System.Drawing.Point(14, 569);
            this.pnlConfirmation.Name = "pnlConfirmation";
            this.pnlConfirmation.Size = new System.Drawing.Size(456, 532);
            this.pnlConfirmation.TabIndex = 31;
            this.pnlConfirmation.Visible = false;
            //
            // btnCancel
            //
            this.btnCancel.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btnCancel.Location = new System.Drawing.Point(332, 423);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(87, 27);
            this.btnCancel.TabIndex = 7;
            this.btnCancel.Text = "Cancel";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            //
            // btnConfirm
            //
            this.btnConfirm.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btnConfirm.Location = new System.Drawing.Point(153, 423);
            this.btnConfirm.Name = "btnConfirm";
            this.btnConfirm.Size = new System.Drawing.Size(173, 27);
            this.btnConfirm.TabIndex = 6;
            this.btnConfirm.Text = "Delete Account";
            this.btnConfirm.UseVisualStyleBackColor = true;
            this.btnConfirm.Click += new System.EventHandler(this.btnConfirm_Click);
            //
            // lblExtraInformation
            //
            this.lblExtraInformation.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblExtraInformation.Location = new System.Drawing.Point(42, 61);
            this.lblExtraInformation.Name = "lblExtraInformation";
            this.lblExtraInformation.Size = new System.Drawing.Size(378, 125);
            this.lblExtraInformation.TabIndex = 5;
            this.lblExtraInformation.Text = "Deleting Phogue\'s account will immediately disconnect them if they are logged in";
            //
            // lblConfirmationQuestion
            //
            this.lblConfirmationQuestion.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.lblConfirmationQuestion.Location = new System.Drawing.Point(42, 365);
            this.lblConfirmationQuestion.Name = "lblConfirmationQuestion";
            this.lblConfirmationQuestion.Size = new System.Drawing.Size(380, 44);
            this.lblConfirmationQuestion.TabIndex = 4;
            this.lblConfirmationQuestion.Text = "Are you sure you want to delete Phogue\'s account?";
            this.lblConfirmationQuestion.TextAlign = System.Drawing.ContentAlignment.BottomLeft;
            //
            // lblConfirmationTitle
            //
            this.lblConfirmationTitle.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lblConfirmationTitle.Font = new System.Drawing.Font("Segoe UI", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblConfirmationTitle.Location = new System.Drawing.Point(21, 24);
            this.lblConfirmationTitle.Name = "lblConfirmationTitle";
            this.lblConfirmationTitle.Size = new System.Drawing.Size(414, 21);
            this.lblConfirmationTitle.TabIndex = 3;
            this.lblConfirmationTitle.Text = "Confirmation";
            //
            // pnlCreateAccount
            //
            this.pnlCreateAccount.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.pnlCreateAccount.BackColor = System.Drawing.SystemColors.Window;
            this.pnlCreateAccount.Controls.Add(this.lblUserNameExistsError);
            this.pnlCreateAccount.Controls.Add(this.txtPassword);
            this.pnlCreateAccount.Controls.Add(this.lblPassword);
            this.pnlCreateAccount.Controls.Add(this.txtUsername);
            this.pnlCreateAccount.Controls.Add(this.lblUsername);
            this.pnlCreateAccount.Controls.Add(this.btnCancelNewAccount);
            this.pnlCreateAccount.Controls.Add(this.btnCreateAccount);
            this.pnlCreateAccount.Controls.Add(this.lblCreateNewAccountTitle);
            this.pnlCreateAccount.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.pnlCreateAccount.Location = new System.Drawing.Point(477, 14);
            this.pnlCreateAccount.Name = "pnlCreateAccount";
            this.pnlCreateAccount.Size = new System.Drawing.Size(456, 532);
            this.pnlCreateAccount.TabIndex = 32;
            this.pnlCreateAccount.Visible = false;
            //
            // lblUserNameExistsError
            //
            this.lblUserNameExistsError.AutoSize = true;
            this.lblUserNameExistsError.ForeColor = System.Drawing.Color.Maroon;
            this.lblUserNameExistsError.Location = new System.Drawing.Point(257, 97);
            this.lblUserNameExistsError.Name = "lblUserNameExistsError";
            this.lblUserNameExistsError.Size = new System.Drawing.Size(132, 15);
            this.lblUserNameExistsError.TabIndex = 12;
            this.lblUserNameExistsError.Text = "Username already exists";
            this.lblUserNameExistsError.Visible = false;
            //
            // txtPassword
            //
            this.txtPassword.Location = new System.Drawing.Point(54, 159);
            this.txtPassword.Name = "txtPassword";
            this.txtPassword.Size = new System.Drawing.Size(195, 23);
            this.txtPassword.TabIndex = 11;
            this.txtPassword.TextChanged += new System.EventHandler(this.txtPassword_TextChanged);
            //
            // lblPassword
            //
            this.lblPassword.AutoSize = true;
            this.lblPassword.Location = new System.Drawing.Point(50, 138);
            this.lblPassword.Name = "lblPassword";
            this.lblPassword.Size = new System.Drawing.Size(57, 15);
            this.lblPassword.TabIndex = 10;
            this.lblPassword.Text = "Password";
            //
            // txtUsername
            //
            this.txtUsername.Location = new System.Drawing.Point(54, 93);
            this.txtUsername.Name = "txtUsername";
            this.txtUsername.Size = new System.Drawing.Size(195, 23);
            this.txtUsername.TabIndex = 9;
            this.txtUsername.TextChanged += new System.EventHandler(this.txtUsername_TextChanged);
            //
            // lblUsername
            //
            this.lblUsername.AutoSize = true;
            this.lblUsername.Location = new System.Drawing.Point(50, 73);
            this.lblUsername.Name = "lblUsername";
            this.lblUsername.Size = new System.Drawing.Size(60, 15);
            this.lblUsername.TabIndex = 8;
            this.lblUsername.Text = "Username";
            //
            // btnCancelNewAccount
            //
            this.btnCancelNewAccount.Location = new System.Drawing.Point(337, 233);
            this.btnCancelNewAccount.Name = "btnCancelNewAccount";
            this.btnCancelNewAccount.Size = new System.Drawing.Size(87, 27);
            this.btnCancelNewAccount.TabIndex = 7;
            this.btnCancelNewAccount.Text = "Cancel";
            this.btnCancelNewAccount.UseVisualStyleBackColor = true;
            this.btnCancelNewAccount.Click += new System.EventHandler(this.btnCancelNewAccount_Click);
            //
            // btnCreateAccount
            //
            this.btnCreateAccount.Enabled = false;
            this.btnCreateAccount.Location = new System.Drawing.Point(157, 233);
            this.btnCreateAccount.Name = "btnCreateAccount";
            this.btnCreateAccount.Size = new System.Drawing.Size(173, 27);
            this.btnCreateAccount.TabIndex = 6;
            this.btnCreateAccount.Text = "Create Account";
            this.btnCreateAccount.UseVisualStyleBackColor = true;
            this.btnCreateAccount.Click += new System.EventHandler(this.btnCreateAccount_Click);
            //
            // lblCreateNewAccountTitle
            //
            this.lblCreateNewAccountTitle.Font = new System.Drawing.Font("Segoe UI", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCreateNewAccountTitle.Location = new System.Drawing.Point(26, 24);
            this.lblCreateNewAccountTitle.Name = "lblCreateNewAccountTitle";
            this.lblCreateNewAccountTitle.Size = new System.Drawing.Size(414, 21);
            this.lblCreateNewAccountTitle.TabIndex = 3;
            this.lblCreateNewAccountTitle.Text = "Create new account";
            //
            // btnClose
            //
            this.btnClose.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnClose.Location = new System.Drawing.Point(1274, 1187);
            this.btnClose.Name = "btnClose";
            this.btnClose.Size = new System.Drawing.Size(87, 27);
            this.btnClose.TabIndex = 33;
            this.btnClose.Text = "Close";
            this.btnClose.UseVisualStyleBackColor = true;
            this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
            //
            // pnlAlterPrivileges
            //
            this.pnlAlterPrivileges.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.pnlAlterPrivileges.Controls.Add(this.uscSetPrivileges);
            this.pnlAlterPrivileges.Location = new System.Drawing.Point(952, 14);
            this.pnlAlterPrivileges.Name = "pnlAlterPrivileges";
            this.pnlAlterPrivileges.Size = new System.Drawing.Size(409, 532);
            this.pnlAlterPrivileges.TabIndex = 34;
            this.pnlAlterPrivileges.Visible = false;
            //
            // uscSetPrivileges
            //
            this.uscSetPrivileges.Dock = System.Windows.Forms.DockStyle.Fill;
            this.uscSetPrivileges.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.uscSetPrivileges.Location = new System.Drawing.Point(0, 0);
            this.uscSetPrivileges.Name = "uscSetPrivileges";
            this.uscSetPrivileges.Size = new System.Drawing.Size(409, 532);
            this.uscSetPrivileges.TabIndex = 0;
            //
            // frmManageAccounts
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Window;
            this.ClientSize = new System.Drawing.Size(1375, 1054);
            this.Controls.Add(this.pnlAlterPrivileges);
            this.Controls.Add(this.btnClose);
            this.Controls.Add(this.pnlEditingUser);
            this.Controls.Add(this.pnlChooseAccount);
            this.Controls.Add(this.pnlCreateAccount);
            this.Controls.Add(this.pnlConfirmation);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "frmManageAccounts";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "frmManageUsers";
            this.Load += new System.EventHandler(this.frmManageAccounts_Load);
            this.Shown += new System.EventHandler(this.frmManageAccounts_Shown);
            this.pnlChooseAccount.ResumeLayout(false);
            this.pnlChooseAccount.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picCreateNewAccount)).EndInit();
            this.pnlEditingUser.ResumeLayout(false);
            this.pnlEditingUser.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picDeleteAccount)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picRemovePrivileges)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picEditGlobalPrivileges)).EndInit();
            this.pnlConfirmation.ResumeLayout(false);
            this.pnlCreateAccount.ResumeLayout(false);
            this.pnlCreateAccount.PerformLayout();
            this.pnlAlterPrivileges.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView lstAccounts;
        private System.Windows.Forms.Panel pnlChooseAccount;
        private System.Windows.Forms.ImageList imlUsers;
        private System.Windows.Forms.LinkLabel lnkCreateNewAccount;
        private System.Windows.Forms.Label lblSelectAccountTitle;
        private System.Windows.Forms.Panel pnlEditingUser;
        private System.Windows.Forms.Label lblEditingAccountTitle;
        private System.Windows.Forms.LinkLabel lnkUpdatePassword;
        private System.Windows.Forms.TextBox txtChangePassword;
        private System.Windows.Forms.Label lblChangePassword;
        private System.Windows.Forms.LinkLabel lnkDeleteUser;
        private System.Windows.Forms.LinkLabel lnkRemovePrivileges;
        private System.Windows.Forms.ToolTip tltipExtraInfo;
        private System.Windows.Forms.Panel pnlConfirmation;
        private System.Windows.Forms.Label lblExtraInformation;
        private System.Windows.Forms.Label lblConfirmationQuestion;
        private System.Windows.Forms.Label lblConfirmationTitle;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnConfirm;
        private System.Windows.Forms.Panel pnlCreateAccount;
        private System.Windows.Forms.Button btnCancelNewAccount;
        private System.Windows.Forms.Button btnCreateAccount;
        private System.Windows.Forms.Label lblCreateNewAccountTitle;
        private System.Windows.Forms.TextBox txtPassword;
        private System.Windows.Forms.Label lblPassword;
        private System.Windows.Forms.TextBox txtUsername;
        private System.Windows.Forms.Label lblUsername;
        private System.Windows.Forms.Label lblUserNameExistsError;
        private System.Windows.Forms.LinkLabel lnkEditAnotherAccount;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnClose;
        private System.Windows.Forms.Panel pnlAlterPrivileges;
        private uscPrivilegesSelection uscSetPrivileges;
        private System.Windows.Forms.LinkLabel lnkSetGlobalPrivileges;
        private System.Windows.Forms.PictureBox picDeleteAccount;
        private System.Windows.Forms.PictureBox picRemovePrivileges;
        private System.Windows.Forms.PictureBox picEditGlobalPrivileges;
        private System.Windows.Forms.PictureBox picCreateNewAccount;
    }*/
}
