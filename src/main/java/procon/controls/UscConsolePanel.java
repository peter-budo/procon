package procon.controls;

public class UscConsolePanel {

    /*public partial class uscConsolePanel : UserControl {

        private frmMain m_frmMainWindow;
        private uscServerConnection m_uscParent;

        private CLocalization m_clocLanguage;

        private PRoConClient m_prcClient;

        //private Dictionary<string, Color> m_dicConsoleTextColours;

        private LinkedList<string> m_llCommandsHistory;
        private LinkedListNode<string> m_llCommandsHistoryCurrentNode;

        private LinkedList<string> m_llPunkbusterCommandsHistory;
        private LinkedListNode<string> m_llPunkbusterCommandsHistoryCurrentNode;

        public delegate void SendCommandDelegate(string strCommand);
        public event SendCommandDelegate SendCommand;

        public delegate void SendListCommandDelegate(List<string> lstCommand);
        public event SendListCommandDelegate SendListCommand;

        //public delegate void DebugDelegate(bool blDebugEnabled);
        //public event DebugDelegate EnableConsoleDebug;

        private Regex m_regRemoveCaretCodes;

        *//*
        public bool ShowConsoleDebug {
            get {
                return this.chkDebug.Checked;
            }
        }

        public bool ShowConsoleEvents {
            get {
                return this.chkEvents.Checked;
            }
        }
        *//*

        *//*
        public List<string> SetConsoleSettings {
            set {
                bool blChecked = true;

                if (value.Count >= 1 && bool.TryParse(value[0], out blChecked) == true) {
                    this.chkEnableOutput.Checked = blChecked;
                }

                if (value.Count >= 2 && bool.TryParse(value[1], out blChecked) == true) {
                    this.chkEvents.Checked = blChecked;
                }

                if (value.Count >= 3 && bool.TryParse(value[2], out blChecked) == true) {
                    this.chkDebug.Checked = blChecked;
                }

                if (value.Count >= 4 && bool.TryParse(value[3], out blChecked) == true) {
                    this.chkEnablePunkbusterOutput.Checked = blChecked;
                }
            }
        }

        public string ConsoleSettings {
            get {
                return String.Format("{0} {1} {2} {3}", this.chkEnableOutput.Checked, this.chkEvents.Checked, this.chkDebug.Checked, this.chkEnablePunkbusterOutput.Checked);
            }
        }
        *//*
        public uscConsolePanel() {
            InitializeComponent();

            this.m_clocLanguage = null;

            this.m_llCommandsHistory = new LinkedList<string>();
            this.m_llPunkbusterCommandsHistory = new LinkedList<string>();

            this.m_regRemoveCaretCodes = new Regex(@"\^[0-9]|\^b|\^i|\^n", RegexOptions.Compiled);
        }

        private void uscConsolePanel_Load(object sender, EventArgs e) {
            if (this.m_prcClient != null && this.m_prcClient.Console != null) {
                this.m_prcClient.Console.DisplayConnection = this.m_prcClient.Console.DisplayConnection;
                this.m_prcClient.Console.DisplayPunkbuster = this.m_prcClient.Console.DisplayPunkbuster;
                this.m_prcClient.Console.LogDebugDetails = this.m_prcClient.Console.LogDebugDetails;
                this.m_prcClient.Console.LogEventsConnection = this.m_prcClient.Console.LogEventsConnection;
                this.m_prcClient.Console.ConScrolling = this.m_prcClient.Console.ConScrolling;
                this.m_prcClient.Console.PBScrolling = this.m_prcClient.Console.PBScrolling;
            }
        }

        public void Initialize(frmMain frmMainWindow, uscServerConnection uscParent) {
            this.m_uscParent = uscParent;
            this.m_frmMainWindow = frmMainWindow;

            this.tbcConsoles.ImageList = this.m_frmMainWindow.iglIcons;
            this.tabConsole.ImageKey = "application_xp_terminal.png";
            this.tabPunkbuster.ImageKey = "punkbuster.png";

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
            this.m_prcClient.Console.WriteConsole += new PRoCon.Core.Logging.Loggable.WriteConsoleHandler(Console_WriteConsole);
            this.m_prcClient.Console.LogEventsConnectionChanged += new PRoCon.Core.Consoles.ConnectionConsole.IsEnabledHandler(Console_LogEventsConnectionChanged);
            this.m_prcClient.Console.DisplayConnectionChanged += new PRoCon.Core.Consoles.ConnectionConsole.IsEnabledHandler(Console_DisplayConnectionChanged);
            this.m_prcClient.Console.DisplayPunkbusterChanged += new PRoCon.Core.Consoles.ConnectionConsole.IsEnabledHandler(Console_DisplayPunkbusterChanged);

            this.m_prcClient.Console.LogDebugDetailsChanged += new PRoCon.Core.Consoles.ConnectionConsole.IsEnabledHandler(Console_LogDebugDetailsChanged);

            this.m_prcClient.PunkbusterConsole.WriteConsole += new PRoCon.Core.Logging.Loggable.WriteConsoleHandler(PunkbusterConsole_WriteConsole);

            this.m_prcClient.Console.ConScrollingChanged += new PRoCon.Core.Consoles.ConnectionConsole.IsEnabledHandler(Console_ConEnableScrollingChanged);
            this.m_prcClient.Console.PBScrollingChanged += new PRoCon.Core.Consoles.ConnectionConsole.IsEnabledHandler(Console_PBEnableScrollingChanged);

            this.m_prcClient.Game.Help += new FrostbiteClient.HelpHandler(Game_Help);
        }

        void Game_Help(FrostbiteClient sender, List<string> lstCommands) {
            this.txtConsoleCommand.AutoCompleteCustomSource.Clear();
            this.txtConsoleCommand.AutoCompleteCustomSource.AddRange(lstCommands.ToArray());
        }

        private void RefreshSentRecvCounters() {
            this.lblOutputKiBs.Text = String.Format("D: {0:0.00} KiB's, U: {1:0.00} KiB's", (float)this.m_prcClient.Console.BytesRecieved / 1024F, (float)this.m_prcClient.Console.BytesSent / 1024F);
        }

        public void SetColour(string strVariable, string strValue) {
            this.rtbConsoleBox.SetColour(strVariable, strValue);
        }

        public void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            this.tabConsole.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tabConsole", null);
            this.btnConsoleSend.Text = this.m_clocLanguage.GetLocalized("uscConsolePanel.btnConsoleSend", null);
            this.chkEnableOutput.Text = this.m_clocLanguage.GetLocalized("uscConsolePanel.chkEnableOutput", null);
            this.chkEvents.Text = this.m_clocLanguage.GetLocalized("uscConsolePanel.chkEvents", null);
            this.chkDebug.Text = this.m_clocLanguage.GetLocalized("uscConsolePanel.chkDebug", null);

            this.btnPunkbusterSend.Text = this.m_clocLanguage.GetLocalized("uscConsolePanel.btnConsoleSend", null);
            this.chkEnablePunkbusterOutput.Text = this.m_clocLanguage.GetLocalized("uscConsolePanel.chkEnableOutput", null);
            this.chkConEnableScrolling.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayScrolling", null);
            this.chkPBEnableScrolling.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayScrolling", null);
        }

        public event uscServerConnection.OnTabChangeDelegate OnTabChange;

        private void tbcConsoles_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.OnTabChange != null) {
                Stack<string> stkTabIndexes = new Stack<string>();
                stkTabIndexes.Push(tbcConsoles.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
            }
        }

        public void SetTabIndexes(Stack<string> stkTabIndexes) {
            if (tbcConsoles.TabPages.ContainsKey(stkTabIndexes.Peek()) == true) {
                this.tbcConsoles.SelectedTab = tbcConsoles.TabPages[stkTabIndexes.Pop()];
            }
        }

        private void Console_WriteConsole(DateTime dtLoggedTime, string strLoggedText) {

            this.RefreshSentRecvCounters();

            if (this.chkEnableOutput.Checked == true) {

                string strFormattedConsoleOutput = String.Format("[{0}] {1}{2}", dtLoggedTime.ToString("HH:mm:ss"), strLoggedText, Environment.NewLine);

                this.rtbConsoleBox.AppendText(strFormattedConsoleOutput);
                //this.rtbConsoleBox.AppendText(String.Format("[{0}] {1}\n", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput));

                // We only pass the length of the original text to exclude the time from being formatted.
                //this.ColourizeConsoleOutput(strConsoleOutput.Length);

                if (this.rtbConsoleBox.Focused == false) {
                    if (this.m_prcClient.Console.ConScrolling == true)
                    {
                        this.rtbConsoleBox.ScrollToCaret();
                    }
                }

                this.rtbConsoleBox.ReadOnly = false;

                int iMaxConsoleLines = this.m_prcClient.Variables.GetVariable<int>("MAX_CONSOLE_LINES", 75);
                int iConsoleBoxLines = this.rtbConsoleBox.Lines.Length;

                if ((iConsoleBoxLines > iMaxConsoleLines && this.rtbConsoleBox.Focused == false) || iConsoleBoxLines > 3000) {

                    for (int i = 0; i < iConsoleBoxLines - iMaxConsoleLines; i++) {

                        //while (this.rtbPunkbusterBox.Lines.Length > iMaxPunkbusterLines) {

                        this.rtbConsoleBox.Select(0, this.rtbConsoleBox.Lines[0].Length + 1);
                        this.rtbConsoleBox.SelectedText = String.Empty;
                    }
                }
                this.rtbConsoleBox.ReadOnly = true;
            }
        }

        private void PunkbusterConsole_WriteConsole(DateTime dtLoggedTime, string strLoggedText) {

            if (this.chkEnablePunkbusterOutput.Checked == true) {

                string strFormattedConsoleOutput = String.Format("{0}{1}", strLoggedText, Environment.NewLine);

                this.rtbPunkbusterBox.AppendText(strFormattedConsoleOutput);
                //this.rtbConsoleBox.AppendText(String.Format("[{0}] {1}\n", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput));

                // We only pass the length of the original text to exclude the time from being formatted.
                //this.ColourizeConsoleOutput(strConsoleOutput.Length);

                if (this.rtbPunkbusterBox.Focused == false) {
                    if (this.m_prcClient.Console.PBScrolling == true)
                    {
                        this.rtbPunkbusterBox.ScrollToCaret();
                    }
                }
                this.rtbPunkbusterBox.ReadOnly = false;

                int iMaxPunkbusterLines = this.m_prcClient.Variables.GetVariable<int>("MAX_PUNKBUSTERCONSOLE_LINES", 200);
                int iPunkbusterBoxLines = this.rtbPunkbusterBox.Lines.Length;

                if ((iPunkbusterBoxLines > iMaxPunkbusterLines && this.rtbPunkbusterBox.Focused == false) || iPunkbusterBoxLines > 3000) {
                    for (int i = 0; i < iPunkbusterBoxLines - iMaxPunkbusterLines; i++) {

                        //while (this.rtbPunkbusterBox.Lines.Length > iMaxPunkbusterLines) {

                        this.rtbPunkbusterBox.Select(0, this.rtbPunkbusterBox.Lines[0].Length + 1);
                        this.rtbPunkbusterBox.SelectedText = String.Empty;
                    }
                }
                this.rtbPunkbusterBox.ReadOnly = true;
            }
        }

        *//*
        public void PunkbusterWrite(string strConsoleOutput) {

            //string strFormattedConsoleOutput = String.Format("[{0}] {1}\r\n", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput);

            string strFormattedConsoleOutput = String.Format("{0}{1}", strConsoleOutput, Environment.NewLine);

            if (this.chkEnablePunkbusterOutput.Checked == true) {

                this.rtbPunkbusterBox.AppendText(strFormattedConsoleOutput);
                //this.rtbConsoleBox.AppendText(String.Format("[{0}] {1}\n", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput));

                // We only pass the length of the original text to exclude the time from being formatted.
                //this.ColourizeConsoleOutput(strConsoleOutput.Length);

                this.rtbPunkbusterBox.ScrollToCaret();
                this.rtbPunkbusterBox.ReadOnly = false;

                int iMaxPunkbusterLines = this.m_uscParent.GetVariableInt("MAX_PUNKBUSTERCONSOLE_LINES", 200);
                int iPunkbusterBoxLines = this.rtbPunkbusterBox.Lines.Length;

                if (iPunkbusterBoxLines > iMaxPunkbusterLines) {
                    for (int i = 0; i < iPunkbusterBoxLines - iMaxPunkbusterLines; i++) {

                        //while (this.rtbPunkbusterBox.Lines.Length > iMaxPunkbusterLines) {

                        this.rtbPunkbusterBox.Select(0, this.rtbPunkbusterBox.Lines[0].Length + 1);
                        this.rtbPunkbusterBox.SelectedText = String.Empty;
                    }
                }
                this.rtbPunkbusterBox.ReadOnly = true;
            }
        }
        *//*

        private void btnPunkbusterSend_Click(object sender, EventArgs e) {
            if (this.SendListCommand != null) {
                this.m_llPunkbusterCommandsHistory.AddFirst(this.txtPunkbusterCommand.Text);
                if (this.m_llPunkbusterCommandsHistory.Count > 20) {
                    this.m_llPunkbusterCommandsHistory.RemoveLast();
                }
                this.m_llPunkbusterCommandsHistoryCurrentNode = null;

                this.SendListCommand(new List<string>() { "punkBuster.pb_sv_command", this.txtPunkbusterCommand.Text });

                this.txtPunkbusterCommand.Clear();
                this.txtPunkbusterCommand.Focus();
            }
        }

        private void txtPunkbusterCommand_KeyDown(object sender, KeyEventArgs e) {

            if (this.SendListCommand != null && e.KeyData == Keys.Enter) {

                this.SendListCommand(new List<string>() { "punkBuster.pb_sv_command", this.txtPunkbusterCommand.Text });

                this.m_llPunkbusterCommandsHistory.AddFirst(this.txtPunkbusterCommand.Text);
                if (this.m_llPunkbusterCommandsHistory.Count > 20) {
                    this.m_llPunkbusterCommandsHistory.RemoveLast();
                }
                this.m_llPunkbusterCommandsHistoryCurrentNode = null;

                this.txtPunkbusterCommand.Clear();
                this.txtPunkbusterCommand.Focus();
                e.SuppressKeyPress = true;
            }

            if (e.KeyData == Keys.Up) {
                e.SuppressKeyPress = true;

                if (this.m_llPunkbusterCommandsHistoryCurrentNode == null && this.m_llPunkbusterCommandsHistory.First != null) {
                    this.m_llPunkbusterCommandsHistoryCurrentNode = this.m_llPunkbusterCommandsHistory.First;
                    this.txtPunkbusterCommand.Text = this.m_llPunkbusterCommandsHistoryCurrentNode.Value;

                    this.txtPunkbusterCommand.Select(this.txtPunkbusterCommand.Text.Length, 0);
                }
                else if (this.m_llPunkbusterCommandsHistoryCurrentNode != null && this.m_llPunkbusterCommandsHistoryCurrentNode.Next != null) {
                    this.m_llPunkbusterCommandsHistoryCurrentNode = this.m_llPunkbusterCommandsHistoryCurrentNode.Next;
                    this.txtPunkbusterCommand.Text = this.m_llPunkbusterCommandsHistoryCurrentNode.Value;

                    this.txtPunkbusterCommand.Select(this.txtConsoleCommand.Text.Length, 0);
                }
            }
            else if (e.KeyData == Keys.Down) {

                if (this.m_llPunkbusterCommandsHistoryCurrentNode != null && this.m_llPunkbusterCommandsHistoryCurrentNode.Previous != null) {
                    this.m_llPunkbusterCommandsHistoryCurrentNode = this.m_llPunkbusterCommandsHistoryCurrentNode.Previous;
                    this.txtPunkbusterCommand.Text = this.m_llPunkbusterCommandsHistoryCurrentNode.Value;

                    this.txtPunkbusterCommand.Select(this.txtPunkbusterCommand.Text.Length, 0);
                }

                e.SuppressKeyPress = true;
            }

        }


        private void btnConsoleSend_Click(object sender, EventArgs e) {
            if (this.SendCommand != null) {
                this.m_llCommandsHistory.AddFirst(this.txtConsoleCommand.Text);
                if (this.m_llCommandsHistory.Count > 20) {
                    this.m_llCommandsHistory.RemoveLast();
                }
                this.m_llCommandsHistoryCurrentNode = null;

                this.SendCommand(this.txtConsoleCommand.Text);

                this.txtConsoleCommand.Clear();
                this.txtConsoleCommand.Focus();
            }
        }

        private void txtConsoleCommand_KeyDown(object sender, KeyEventArgs e) {

            if (this.SendCommand != null && e.KeyData == Keys.Enter) {

                this.SendCommand(this.txtConsoleCommand.Text);

                this.m_llCommandsHistory.AddFirst(this.txtConsoleCommand.Text);
                if (this.m_llCommandsHistory.Count > 20) {
                    this.m_llCommandsHistory.RemoveLast();
                }
                this.m_llCommandsHistoryCurrentNode = null;

                this.txtConsoleCommand.Clear();
                this.txtConsoleCommand.Focus();
                e.SuppressKeyPress = true;
            }

            if (e.KeyData == Keys.Up) {
                e.SuppressKeyPress = true;

                if (this.m_llCommandsHistoryCurrentNode == null && this.m_llCommandsHistory.First != null) {
                    this.m_llCommandsHistoryCurrentNode = this.m_llCommandsHistory.First;
                    this.txtConsoleCommand.Text = this.m_llCommandsHistoryCurrentNode.Value;

                    this.txtConsoleCommand.Select(this.txtConsoleCommand.Text.Length, 0);
                }
                else if (this.m_llCommandsHistoryCurrentNode != null && this.m_llCommandsHistoryCurrentNode.Next != null) {
                    this.m_llCommandsHistoryCurrentNode = this.m_llCommandsHistoryCurrentNode.Next;
                    this.txtConsoleCommand.Text = this.m_llCommandsHistoryCurrentNode.Value;

                    this.txtConsoleCommand.Select(this.txtConsoleCommand.Text.Length, 0);
                }
            }
            else if (e.KeyData == Keys.Down) {

                if (this.m_llCommandsHistoryCurrentNode != null && this.m_llCommandsHistoryCurrentNode.Previous != null) {
                    this.m_llCommandsHistoryCurrentNode = this.m_llCommandsHistoryCurrentNode.Previous;
                    this.txtConsoleCommand.Text = this.m_llCommandsHistoryCurrentNode.Value;

                    this.txtConsoleCommand.Select(this.txtConsoleCommand.Text.Length, 0);
                }

                e.SuppressKeyPress = true;
            }
        }

        private void chkEnableOutput_CheckedChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.Console.DisplayConnection = this.chkEnableOutput.Checked;
            }
        }

        private void Console_DisplayConnectionChanged(bool isEnabled) {
            this.chkEnableOutput.Checked = isEnabled;
        }


        private void chkEnablePunkbusterOutput_CheckedChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.Console.DisplayPunkbuster = this.chkEnablePunkbusterOutput.Checked;
            }
        }

        void Console_DisplayPunkbusterChanged(bool isEnabled) {
            this.chkEnablePunkbusterOutput.Checked = isEnabled;
        }

        private void chkConEnableScrolling_CheckedChanged(object sender, EventArgs e)
        {
            if (this.m_prcClient != null)
            {
                this.m_prcClient.Console.ConScrolling = this.chkConEnableScrolling.Checked;
            }
        }

        void Console_ConEnableScrollingChanged(bool isEnabled)
        {
            this.chkConEnableScrolling.Checked = isEnabled;
        }

        private void chkPBEnableScrolling_CheckedChanged(object sender, EventArgs e)
        {
            if (this.m_prcClient != null)
            {
                this.m_prcClient.Console.PBScrolling = this.chkPBEnableScrolling.Checked;
            }
        }

        void Console_PBEnableScrollingChanged(bool isEnabled)
        {
            this.chkPBEnableScrolling.Checked = isEnabled;
        }

        private void chkDebug_CheckedChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.Console.LogDebugDetails = this.chkDebug.Checked;
            }
        }

        private void Console_LogDebugDetailsChanged(bool isEnabled) {
            this.chkDebug.Checked = isEnabled;
        }

        private void chkEvents_CheckedChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.Console.LogEventsConnection = this.chkEvents.Checked;
            }
        }

        private void Console_LogEventsConnectionChanged(bool isEnabled) {
            this.chkEvents.Checked = isEnabled;
        }
    }*/

    /*partial class uscConsolePanel {
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
            this.tbcConsoles = new System.Windows.Forms.TabControl();
            this.tabConsole = new System.Windows.Forms.TabPage();
            this.chkEnableOutput = new System.Windows.Forms.CheckBox();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            this.chkDebug = new System.Windows.Forms.CheckBox();
            this.chkEvents = new System.Windows.Forms.CheckBox();
            this.lblOutputKiBs = new System.Windows.Forms.Label();
            this.btnConsoleSend = new System.Windows.Forms.Button();
            this.txtConsoleCommand = new System.Windows.Forms.TextBox();
            this.pnlConsoleEnclosure = new System.Windows.Forms.Panel();
            this.tabPunkbuster = new System.Windows.Forms.TabPage();
            this.chkEnablePunkbusterOutput = new System.Windows.Forms.CheckBox();
            this.btnPunkbusterSend = new System.Windows.Forms.Button();
            this.txtPunkbusterCommand = new System.Windows.Forms.TextBox();
            this.panel1 = new System.Windows.Forms.Panel();
            this.chkConEnableScrolling = new System.Windows.Forms.CheckBox();
            this.rtbConsoleBox = new PRoCon.CodRichTextBox();
            this.rtbPunkbusterBox = new PRoCon.CodRichTextBox();
            this.chkPBEnableScrolling = new System.Windows.Forms.CheckBox();
            this.tbcConsoles.SuspendLayout();
            this.tabConsole.SuspendLayout();
            this.flowLayoutPanel1.SuspendLayout();
            this.pnlConsoleEnclosure.SuspendLayout();
            this.tabPunkbuster.SuspendLayout();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            //
            // tbcConsoles
            //
            this.tbcConsoles.Controls.Add(this.tabConsole);
            this.tbcConsoles.Controls.Add(this.tabPunkbuster);
            this.tbcConsoles.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbcConsoles.Location = new System.Drawing.Point(0, 0);
            this.tbcConsoles.Name = "tbcConsoles";
            this.tbcConsoles.SelectedIndex = 0;
            this.tbcConsoles.Size = new System.Drawing.Size(766, 567);
            this.tbcConsoles.TabIndex = 0;
            this.tbcConsoles.SelectedIndexChanged += new System.EventHandler(this.tbcConsoles_SelectedIndexChanged);
            //
            // tabConsole
            //
            this.tabConsole.Controls.Add(this.chkConEnableScrolling);
            this.tabConsole.Controls.Add(this.chkEnableOutput);
            this.tabConsole.Controls.Add(this.flowLayoutPanel1);
            this.tabConsole.Controls.Add(this.btnConsoleSend);
            this.tabConsole.Controls.Add(this.txtConsoleCommand);
            this.tabConsole.Controls.Add(this.pnlConsoleEnclosure);
            this.tabConsole.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.tabConsole.Location = new System.Drawing.Point(4, 24);
            this.tabConsole.Name = "tabConsole";
            this.tabConsole.Padding = new System.Windows.Forms.Padding(8);
            this.tabConsole.Size = new System.Drawing.Size(758, 539);
            this.tabConsole.TabIndex = 0;
            this.tabConsole.Text = "Console";
            this.tabConsole.UseVisualStyleBackColor = true;
            //
            // chkEnableOutput
            //
            this.chkEnableOutput.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkEnableOutput.AutoSize = true;
            this.chkEnableOutput.Checked = true;
            this.chkEnableOutput.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkEnableOutput.Location = new System.Drawing.Point(11, 480);
            this.chkEnableOutput.Name = "chkEnableOutput";
            this.chkEnableOutput.Size = new System.Drawing.Size(100, 19);
            this.chkEnableOutput.TabIndex = 27;
            this.chkEnableOutput.Text = "Enable output";
            this.chkEnableOutput.UseVisualStyleBackColor = true;
            this.chkEnableOutput.CheckedChanged += new System.EventHandler(this.chkEnableOutput_CheckedChanged);
            //
            // flowLayoutPanel1
            //
            this.flowLayoutPanel1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.flowLayoutPanel1.Controls.Add(this.chkDebug);
            this.flowLayoutPanel1.Controls.Add(this.chkEvents);
            this.flowLayoutPanel1.Controls.Add(this.lblOutputKiBs);
            this.flowLayoutPanel1.FlowDirection = System.Windows.Forms.FlowDirection.RightToLeft;
            this.flowLayoutPanel1.Location = new System.Drawing.Point(363, 476);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(384, 24);
            this.flowLayoutPanel1.TabIndex = 29;
            //
            // chkDebug
            //
            this.chkDebug.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.chkDebug.AutoSize = true;
            this.chkDebug.Location = new System.Drawing.Point(320, 3);
            this.chkDebug.Name = "chkDebug";
            this.chkDebug.Size = new System.Drawing.Size(61, 19);
            this.chkDebug.TabIndex = 25;
            this.chkDebug.Text = "Debug";
            this.chkDebug.UseVisualStyleBackColor = true;
            this.chkDebug.CheckedChanged += new System.EventHandler(this.chkDebug_CheckedChanged);
            //
            // chkEvents
            //
            this.chkEvents.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.chkEvents.AutoSize = true;
            this.chkEvents.Location = new System.Drawing.Point(254, 3);
            this.chkEvents.Name = "chkEvents";
            this.chkEvents.Size = new System.Drawing.Size(60, 19);
            this.chkEvents.TabIndex = 26;
            this.chkEvents.Text = "Events";
            this.chkEvents.UseVisualStyleBackColor = true;
            this.chkEvents.CheckedChanged += new System.EventHandler(this.chkEvents_CheckedChanged);
            //
            // lblOutputKiBs
            //
            this.lblOutputKiBs.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblOutputKiBs.AutoSize = true;
            this.lblOutputKiBs.Location = new System.Drawing.Point(205, 5);
            this.lblOutputKiBs.Name = "lblOutputKiBs";
            this.lblOutputKiBs.Size = new System.Drawing.Size(43, 15);
            this.lblOutputKiBs.TabIndex = 28;
            this.lblOutputKiBs.Text = "            ";
            this.lblOutputKiBs.TextAlign = System.Drawing.ContentAlignment.TopRight;
            //
            // btnConsoleSend
            //
            this.btnConsoleSend.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnConsoleSend.Location = new System.Drawing.Point(656, 505);
            this.btnConsoleSend.Name = "btnConsoleSend";
            this.btnConsoleSend.Size = new System.Drawing.Size(90, 23);
            this.btnConsoleSend.TabIndex = 24;
            this.btnConsoleSend.Text = "Send";
            this.btnConsoleSend.UseVisualStyleBackColor = true;
            this.btnConsoleSend.Click += new System.EventHandler(this.btnConsoleSend_Click);
            //
            // txtConsoleCommand
            //
            this.txtConsoleCommand.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtConsoleCommand.AutoCompleteCustomSource.AddRange(new string[] {
            "punkBuster.pb_sv_command ",
            "punkBuster.pb_sv_command pb_sv_plist",
            "punkBuster.pb_sv_command pb_sv_banlist",
            "punkBuster.pb_sv_command pb_sv_banguid",
            "admin.yell ",
            "login.plainText ",
            "login.hashed ",
            "logout",
            "quit",
            "version",
            "help",
            "admin.runScript ",
            "serverInfo",
            "admin.runNextLevel",
            "admin.currentLevel",
            "admin.nextLevel ",
            "admin.restartMap",
            "admin.supportedMaps ",
            "admin.setPlaylist ",
            "admin.getPlaylist ",
            "admin.getPlaylists",
            "admin.kickPlayer ",
            "admin.listPlayers ",
            "admin.listPlayers all",
            "admin.listPlayers team",
            "admin.listPlayers squad",
            "admin.listPlayers player",
            "admin.banPlayer ",
            "admin.banIP ",
            "admin.unbanPlayer ",
            "admin.unbanIP ",
            "admin.clearPlayerBanList",
            "admin.clearIPBanList",
            "admin.listPlayerBans",
            "admin.listIPBans",
            "reservedSlots.configFile ",
            "reservedSlots.load",
            "reservedSlots.save",
            "reservedSlots.addPlayer ",
            "reservedSlots.removePlayer ",
            "reservedSlots.clear",
            "reservedSlots.list",
            "reservedTagSlots.load",
            "reservedTagSlots.save",
            "reservedTagSlots.setTag ",
            "reservedTagSlots.addPlayer ",
            "reservedTagSlots.removePlayer ",
            "reservedTagSlots.clear",
            "reservedTagSlots.list",
            "mapList.configFile ",
            "mapList.load",
            "mapList.save",
            "mapList.list",
            "mapList.clear",
            "mapList.remove ",
            "mapList.append ",
            "mapList.nextLevelIndex ",
            "vars.adminPassword",
            "vars.gamePassword",
            "vars.punkBuster",
            "vars.hardCore",
            "vars.ranked",
            "vars.rankLimit",
            "vars.teamBalance",
            "vars.friendlyFire",
            "vars.currentPlayerLimit",
            "vars.maxPlayerLimit",
            "vars.playerLimit",
            "vars.bannerUrl",
            "vars.serverDescription",
            "vars.killCam",
            "vars.miniMap",
            "vars.crossHair",
            "vars.3dSpotting",
            "vars.miniMapSpotting",
            "vars.thirdPersonVehicleCameras"});
            this.txtConsoleCommand.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.Append;
            this.txtConsoleCommand.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.CustomSource;
            this.txtConsoleCommand.Location = new System.Drawing.Point(11, 506);
            this.txtConsoleCommand.Name = "txtConsoleCommand";
            this.txtConsoleCommand.Size = new System.Drawing.Size(639, 23);
            this.txtConsoleCommand.TabIndex = 23;
            this.txtConsoleCommand.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtConsoleCommand_KeyDown);
            //
            // pnlConsoleEnclosure
            //
            this.pnlConsoleEnclosure.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.pnlConsoleEnclosure.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pnlConsoleEnclosure.Controls.Add(this.rtbConsoleBox);
            this.pnlConsoleEnclosure.Location = new System.Drawing.Point(11, 14);
            this.pnlConsoleEnclosure.Name = "pnlConsoleEnclosure";
            this.pnlConsoleEnclosure.Size = new System.Drawing.Size(736, 460);
            this.pnlConsoleEnclosure.TabIndex = 22;
            //
            // tabPunkbuster
            //
            this.tabPunkbuster.Controls.Add(this.chkPBEnableScrolling);
            this.tabPunkbuster.Controls.Add(this.chkEnablePunkbusterOutput);
            this.tabPunkbuster.Controls.Add(this.btnPunkbusterSend);
            this.tabPunkbuster.Controls.Add(this.txtPunkbusterCommand);
            this.tabPunkbuster.Controls.Add(this.panel1);
            this.tabPunkbuster.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.tabPunkbuster.Location = new System.Drawing.Point(4, 24);
            this.tabPunkbuster.Name = "tabPunkbuster";
            this.tabPunkbuster.Padding = new System.Windows.Forms.Padding(3);
            this.tabPunkbuster.Size = new System.Drawing.Size(758, 539);
            this.tabPunkbuster.TabIndex = 1;
            this.tabPunkbuster.Text = "PunkBuster";
            this.tabPunkbuster.UseVisualStyleBackColor = true;
            //
            // chkEnablePunkbusterOutput
            //
            this.chkEnablePunkbusterOutput.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkEnablePunkbusterOutput.AutoSize = true;
            this.chkEnablePunkbusterOutput.Checked = true;
            this.chkEnablePunkbusterOutput.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkEnablePunkbusterOutput.Location = new System.Drawing.Point(11, 482);
            this.chkEnablePunkbusterOutput.Name = "chkEnablePunkbusterOutput";
            this.chkEnablePunkbusterOutput.Size = new System.Drawing.Size(100, 19);
            this.chkEnablePunkbusterOutput.TabIndex = 33;
            this.chkEnablePunkbusterOutput.Text = "Enable output";
            this.chkEnablePunkbusterOutput.UseVisualStyleBackColor = true;
            this.chkEnablePunkbusterOutput.CheckedChanged += new System.EventHandler(this.chkEnablePunkbusterOutput_CheckedChanged);
            //
            // btnPunkbusterSend
            //
            this.btnPunkbusterSend.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnPunkbusterSend.Location = new System.Drawing.Point(656, 507);
            this.btnPunkbusterSend.Name = "btnPunkbusterSend";
            this.btnPunkbusterSend.Size = new System.Drawing.Size(90, 23);
            this.btnPunkbusterSend.TabIndex = 30;
            this.btnPunkbusterSend.Text = "Send";
            this.btnPunkbusterSend.UseVisualStyleBackColor = true;
            this.btnPunkbusterSend.Click += new System.EventHandler(this.btnPunkbusterSend_Click);
            //
            // txtPunkbusterCommand
            //
            this.txtPunkbusterCommand.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtPunkbusterCommand.AutoCompleteCustomSource.AddRange(new string[] {
            "pb_sv_autoupdban ",
            "pb_sv_badname ",
            "pb_sv_badnamedel ",
            "pb_sv_badnamelist ",
            "pb_sv_ban ",
            "pb_sv_banempty ",
            "pb_sv_banguid ",
            "pb_sv_banlist ",
            "pb_sv_banload ",
            "pb_sv_banmask ",
            "pb_sv_bannameempty ",
            "pb_sv_bindsrch ",
            "pb_sv_cvar ",
            "pb_sv_cvarchanged ",
            "pb_sv_cvardel ",
            "pb_sv_cvarempty ",
            "pb_sv_cvarlist ",
            "pb_sv_cvarsrch ",
            "pb_sv_cvaruser ",
            "pb_sv_cvarval ",
            "pb_sv_disable ",
            "pb_sv_enable ",
            "pb_sv_file ",
            "pb_sv_filedel ",
            "pb_sv_fileempty ",
            "pb_sv_filelist ",
            "pb_sv_getss ",
            "pb_sv_homepath ",
            "pb_sv_ipguard ",
            "pb_sv_kick ",
            "pb_sv_load ",
            "pb_sv_md5tool ",
            "pb_sv_md5tooldel ",
            "pb_sv_md5toolempty ",
            "pb_sv_md5toollist ",
            "pb_sv_namelock ",
            "pb_sv_namelockempty ",
            "pb_sv_namelocklist ",
            "pb_sv_newlog ",
            "pb_sv_plist ",
            "pb_sv_powerguid ",
            "pb_sv_powerpoints ",
            "pb_sv_protectname ",
            "pb_sv_protecttag ",
            "pb_sv_reban ",
            "pb_sv_restart ",
            "pb_sv_tlist ",
            "pb_sv_task ",
            "pb_sv_taskdel ",
            "pb_sv_taskempty ",
            "pb_sv_unban ",
            "pb_sv_unbanguid ",
            "pb_sv_updbanfile ",
            "pb_sv_update ",
            "pb_sv_ver ",
            "pb_sv_writecfg "});
            this.txtPunkbusterCommand.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.Append;
            this.txtPunkbusterCommand.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.CustomSource;
            this.txtPunkbusterCommand.Location = new System.Drawing.Point(11, 508);
            this.txtPunkbusterCommand.Name = "txtPunkbusterCommand";
            this.txtPunkbusterCommand.Size = new System.Drawing.Size(638, 23);
            this.txtPunkbusterCommand.TabIndex = 29;
            this.txtPunkbusterCommand.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtPunkbusterCommand_KeyDown);
            //
            // panel1
            //
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.rtbPunkbusterBox);
            this.panel1.Location = new System.Drawing.Point(11, 14);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(736, 462);
            this.panel1.TabIndex = 28;
            //
            // chkConEnableScrolling
            //
            this.chkConEnableScrolling.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkConEnableScrolling.AutoSize = true;
            this.chkConEnableScrolling.Checked = true;
            this.chkConEnableScrolling.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkConEnableScrolling.Location = new System.Drawing.Point(188, 480);
            this.chkConEnableScrolling.Name = "chkConEnableScrolling";
            this.chkConEnableScrolling.Size = new System.Drawing.Size(109, 19);
            this.chkConEnableScrolling.TabIndex = 30;
            this.chkConEnableScrolling.Text = "Enable scrolling";
            this.chkConEnableScrolling.UseVisualStyleBackColor = true;
            this.chkConEnableScrolling.CheckedChanged += new System.EventHandler(this.chkConEnableScrolling_CheckedChanged);
            //
            // rtbConsoleBox
            //
            this.rtbConsoleBox.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rtbConsoleBox.DetectUrls = false;
            this.rtbConsoleBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbConsoleBox.Font = new System.Drawing.Font("Courier New", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rtbConsoleBox.Location = new System.Drawing.Point(0, 0);
            this.rtbConsoleBox.Name = "rtbConsoleBox";
            this.rtbConsoleBox.ReadOnly = true;
            this.rtbConsoleBox.Size = new System.Drawing.Size(734, 458);
            this.rtbConsoleBox.TabIndex = 0;
            this.rtbConsoleBox.Text = "";
            //
            // rtbPunkbusterBox
            //
            this.rtbPunkbusterBox.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rtbPunkbusterBox.DetectUrls = false;
            this.rtbPunkbusterBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbPunkbusterBox.Font = new System.Drawing.Font("Courier New", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rtbPunkbusterBox.ForeColor = System.Drawing.Color.RoyalBlue;
            this.rtbPunkbusterBox.Location = new System.Drawing.Point(0, 0);
            this.rtbPunkbusterBox.Name = "rtbPunkbusterBox";
            this.rtbPunkbusterBox.ReadOnly = true;
            this.rtbPunkbusterBox.Size = new System.Drawing.Size(734, 460);
            this.rtbPunkbusterBox.TabIndex = 0;
            this.rtbPunkbusterBox.Text = "";
            //
            // chkPBEnableScrolling
            //
            this.chkPBEnableScrolling.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkPBEnableScrolling.AutoSize = true;
            this.chkPBEnableScrolling.Checked = true;
            this.chkPBEnableScrolling.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkPBEnableScrolling.Location = new System.Drawing.Point(188, 482);
            this.chkPBEnableScrolling.Name = "chkPBEnableScrolling";
            this.chkPBEnableScrolling.Size = new System.Drawing.Size(109, 19);
            this.chkPBEnableScrolling.TabIndex = 34;
            this.chkPBEnableScrolling.Text = "Enable scrolling";
            this.chkPBEnableScrolling.UseVisualStyleBackColor = true;
            this.chkPBEnableScrolling.CheckedChanged += new System.EventHandler(this.chkPBEnableScrolling_CheckedChanged);
            //
            // uscConsolePanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tbcConsoles);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscConsolePanel";
            this.Size = new System.Drawing.Size(766, 567);
            this.Load += new System.EventHandler(this.uscConsolePanel_Load);
            this.tbcConsoles.ResumeLayout(false);
            this.tabConsole.ResumeLayout(false);
            this.tabConsole.PerformLayout();
            this.flowLayoutPanel1.ResumeLayout(false);
            this.flowLayoutPanel1.PerformLayout();
            this.pnlConsoleEnclosure.ResumeLayout(false);
            this.tabPunkbuster.ResumeLayout(false);
            this.tabPunkbuster.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tbcConsoles;
        private System.Windows.Forms.TabPage tabConsole;
        private System.Windows.Forms.CheckBox chkEnableOutput;
        private System.Windows.Forms.CheckBox chkEvents;
        private System.Windows.Forms.CheckBox chkDebug;
        private System.Windows.Forms.Button btnConsoleSend;
        private System.Windows.Forms.TextBox txtConsoleCommand;
        private System.Windows.Forms.Panel pnlConsoleEnclosure;
        private CodRichTextBox rtbConsoleBox;
        private System.Windows.Forms.TabPage tabPunkbuster;
        private System.Windows.Forms.CheckBox chkEnablePunkbusterOutput;
        private System.Windows.Forms.Button btnPunkbusterSend;
        private System.Windows.Forms.TextBox txtPunkbusterCommand;
        private System.Windows.Forms.Panel panel1;
        private CodRichTextBox rtbPunkbusterBox;
        private System.Windows.Forms.Label lblOutputKiBs;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
        private System.Windows.Forms.CheckBox chkConEnableScrolling;
        private System.Windows.Forms.CheckBox chkPBEnableScrolling;

    }*/
}
