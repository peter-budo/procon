package procon.controls;

public class UscChatPanel {

    /*public partial class uscChatPanel : uscPage {

        private uscServerConnection m_uscParent;
        private CLocalization m_clocLanguage;

        private PRoConClient m_prcClient;

        //private FileStream m_stmChatFile;
        //private StreamWriter m_stwChatFile;

        //private Dictionary<string, Color> m_dicChatTextColours;

        private LinkedList<string> m_llChatHistory;
        private LinkedListNode<string> m_llChatHistoryCurrentNode;

        //public delegate void SendCommandDelegate(List<string> lstCommand);
        //public event SendCommandDelegate SendCommand;

        string m_strAllPlayers = "All Players";

        Regex m_regRemoveCaretCodes;

        *//*
        private bool m_blChatLogging;
        public bool ChatLogging {
            get {
                return m_blChatLogging;
            }
            set {

                if (value == true) {

                    try {

                        if (Directory.Exists(AppDomain.CurrentDomain.BaseDirectory + "Logs" + Path.DirectorySeparatorChar + this.m_uscParent.BFBC2Connection.FileHostNamePort + Path.DirectorySeparatorChar) == false) {
                            Directory.CreateDirectory(AppDomain.CurrentDomain.BaseDirectory + "Logs" + Path.DirectorySeparatorChar + this.m_uscParent.BFBC2Connection.FileHostNamePort + Path.DirectorySeparatorChar);
                        }

                        if (this.m_stmChatFile == null) {
                            if ((this.m_stmChatFile = new FileStream(String.Format(@"{0}{1}", AppDomain.CurrentDomain.BaseDirectory + "Logs" + Path.DirectorySeparatorChar + this.m_uscParent.BFBC2Connection.FileHostNamePort + Path.DirectorySeparatorChar, DateTime.Now.ToString("yyyyMMdd") + "_chat.log"), FileMode.Append)) != null) {
                                if ((this.m_stwChatFile = new StreamWriter(this.m_stmChatFile, Encoding.Unicode)) != null) {

                                    this.m_stwChatFile.WriteLine("Chat logging started: {0}", DateTime.Now.ToString("dddd, d MMMM yyyy HH:mm:ss"));
                                    this.m_stwChatFile.Flush();
                                }
                            }

                            this.m_blChatLogging = true;
                        }
                    }
                    catch (Exception) {
                        this.m_blChatLogging = false;
                    }
                }
                else {
                    if (this.m_stwChatFile != null) {

                        this.m_stwChatFile.WriteLine("Chat logging stopped: {0}", DateTime.Now.ToString("dddd, d MMMM yyyy HH:mm:ss"));

                        this.m_stwChatFile.Close();
                        this.m_stwChatFile.Dispose();
                        this.m_stwChatFile = null;
                    }

                    if (this.m_stmChatFile != null) {
                        this.m_stmChatFile.Close();
                        this.m_stmChatFile.Dispose();
                        this.m_stmChatFile = null;
                    }

                    this.m_blChatLogging = false;
                }
            }
        }
        *//*

        private void SendCommand(List<string> lstWords) {
            if (lstWords.Count > 0) {
                if (String.Compare(lstWords[0], "admin.yell", true) == 0) {
                    if (lstWords.Count >= 5) {
                        this.m_prcClient.SendProconAdminYell(lstWords[1], lstWords[2], lstWords[3], lstWords[4]);
                    }
                    else {
                        this.m_prcClient.SendProconAdminYell(lstWords[1], lstWords[2], lstWords[3], String.Empty);
                    }
                }
                else if (String.Compare(lstWords[0], "admin.say", true) == 0) {
                    if (lstWords.Count >= 4) {
                        this.m_prcClient.SendProconAdminSay(lstWords[1], lstWords[2], lstWords[3]);
                    }
                    else {
                        this.m_prcClient.SendProconAdminSay(lstWords[1], lstWords[2], String.Empty);
                    }
                }
            }
        }

        *//*
        public List<string> SetChatSettings {
            set {
                if (value.Count > 0) {
                    bool blChecked = true;
                    int iIndex = 0;

                    if (value.Count >= 1 && bool.TryParse(value[0], out blChecked) == true) {
                        this.chkDisplayOnJoinLeaveEvents.Checked = blChecked;
                    }

                    if (value.Count >= 2 && bool.TryParse(value[1], out blChecked) == true) {
                        this.chkDisplayOnKilledEvents.Checked = blChecked;
                    }

                    if (value.Count >= 3 && int.TryParse(value[2], out iIndex) == true && iIndex < this.cboDisplayList.Items.Count) {
                        this.cboDisplayList.SelectedIndex = iIndex;
                    }

                    if (value.Count >= 4 && int.TryParse(value[3], out iIndex) == true && iIndex < this.cboDisplayChatTime.Items.Count) {
                        this.cboDisplayChatTime.SelectedIndex = iIndex;
                    }
                }

            }
        }

        public string ChatSettings {
            get {
                return String.Format("{0} {1} {2} {3}", this.chkDisplayOnJoinLeaveEvents.Checked, this.chkDisplayOnKilledEvents.Checked, this.cboDisplayList.SelectedIndex, this.cboDisplayChatTime.SelectedIndex);
            }
        }
        *//*

        public uscChatPanel() {
            InitializeComponent();

            //this.m_stmChatFile = null;
            //this.m_stwChatFile = null;
            //this.m_blChatLogging = false;
            this.m_clocLanguage = null;

            this.m_llChatHistory = new LinkedList<string>();

            this.cboDisplayChatTime.Items.Clear();
            this.cboDisplayChatTime.Items.Add(2000);
            this.cboDisplayChatTime.Items.Add(4000);
            this.cboDisplayChatTime.Items.Add(6000);
            this.cboDisplayChatTime.Items.Add(8000);
            this.cboDisplayChatTime.Items.Add(10000);
            this.cboDisplayChatTime.Items.Add(15000);
            this.cboDisplayChatTime.Items.Add(20000);
            this.cboDisplayChatTime.Items.Add(25000);
            this.cboDisplayChatTime.Items.Add(30000);
            this.cboDisplayChatTime.Items.Add(35000);
            this.cboDisplayChatTime.Items.Add(40000);
            this.cboDisplayChatTime.Items.Add(45000);
            this.cboDisplayChatTime.Items.Add(50000);
            this.cboDisplayChatTime.Items.Add(55000);
            this.cboDisplayChatTime.Items.Add(59999);

            this.cboDisplayChatTime.SelectedIndex = 3;
            this.cboDisplayList.SelectedIndex = 0;

            this.m_regRemoveCaretCodes = new Regex(@"\^[0-9]|\^b|\^i|\^n", RegexOptions.Compiled);
        }

        private void uscChatPanel_Load(object sender, EventArgs e) {
            if (this.m_prcClient != null) {

                // Setting fires events which neatens the code a little elsewhere.
                this.m_prcClient.ChatConsole.LogJoinLeaving = this.m_prcClient.ChatConsole.LogJoinLeaving;
                this.m_prcClient.ChatConsole.LogKills = this.m_prcClient.ChatConsole.LogKills;
                this.m_prcClient.ChatConsole.Scrolling = this.m_prcClient.ChatConsole.Scrolling;
                this.m_prcClient.ChatConsole.DisplayTypeIndex = this.m_prcClient.ChatConsole.DisplayTypeIndex;
                this.m_prcClient.ChatConsole.DisplayTimeIndex = this.m_prcClient.ChatConsole.DisplayTimeIndex;
            }
        }

        public override void SetConnection(PRoConClient prcClient) {
            if ((this.m_prcClient = prcClient) != null) {
                if (this.m_prcClient.Game != null) {
                    this.m_prcClient_GameTypeDiscovered(prcClient);
                }
                else {
                    this.m_prcClient.GameTypeDiscovered += new PRoConClient.EmptyParamterHandler(m_prcClient_GameTypeDiscovered);
                }
                // update max length
                this.chatUpdTxtLength();
            }
        }

        private void m_prcClient_GameTypeDiscovered(PRoConClient sender) {
            this.m_prcClient.ChatConsole.WriteConsole += new PRoCon.Core.Logging.Loggable.WriteConsoleHandler(ChatConsole_WriteConsole);
            this.m_prcClient.ChatConsole.LogJoinLeavingChanged += new PRoCon.Core.Consoles.ChatConsole.IsEnabledHandler(ChatConsole_LogJoinLeavingChanged);
            this.m_prcClient.ChatConsole.LogKillsChanged += new PRoCon.Core.Consoles.ChatConsole.IsEnabledHandler(ChatConsole_LogKillsChanged);
            this.m_prcClient.ChatConsole.ScrollingChanged += new PRoCon.Core.Consoles.ChatConsole.IsEnabledHandler(ChatConsole_ScrollingChanged);

            this.m_prcClient.ChatConsole.DisplayTimeChanged += new PRoCon.Core.Consoles.ChatConsole.IndexChangedHandler(ChatConsole_DisplayTimeChanged);
            this.m_prcClient.ChatConsole.DisplayTypeChanged += new PRoCon.Core.Consoles.ChatConsole.IndexChangedHandler(ChatConsole_DisplayTypeChanged);

            this.m_prcClient.Game.ListPlayers += new FrostbiteClient.ListPlayersHandler(m_prcClient_ListPlayers);
            this.m_prcClient.Game.ServerInfo += new FrostbiteClient.ServerInfoHandler(m_prcClient_ServerInfo);

            if (sender.Game is MoHClient) {
                cboDisplayList.Items.RemoveAt(1);
            }
        }

        public void Initialize(uscServerConnection uscParent) {
            this.m_uscParent = uscParent;

            this.cboPlayerList.Items.Add(new CPlayerInfo("", String.Empty, -10, -10));
            this.cboPlayerList.SelectedIndex = 0;
        }

        public void SetColour(string strVariable, string strValue) {
            this.rtbChatBox.SetColour(strVariable, strValue);
        }

        public override void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            this.btnChatSend.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.btnChatSend", null);
            this.m_strAllPlayers = this.m_clocLanguage.GetLocalized("uscChatPanel.cboPlayerList.AllPlayers", null);
            this.lblAudience.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.lblAudience", null);
            this.lblDisplayFor.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.lblDisplayFor", null);

            this.lblDisplay.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.lblDisplay", null);

            this.cboDisplayList.Items[0] = this.m_clocLanguage.GetLocalized("uscChatPanel.cboDisplayList.Say", null);

            if (this.cboDisplayList.Items.Count > 1) {
                this.cboDisplayList.Items[1] = this.m_clocLanguage.GetLocalized("uscChatPanel.cboDisplayList.Yell", null);
            }

            this.chkDisplayOnJoinLeaveEvents.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayOnJoinLeaveEvents", null);
            this.chkDisplayOnKilledEvents.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayOnKilledEvents", null);
            this.chkDisplayScrollingEvents.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayScrolling", null);
            this.btnclearchat.Text = this.m_clocLanguage.GetLocalized("uscChatPanel.btnclearchat", null);
            this.cboDisplayChatTime.Refresh();
        }

        private void cboDisplayChatTime_DrawItem(object sender, DrawItemEventArgs e) {
            if (e.Index != -1) {
                int iDrawItemData = ((int)cboDisplayChatTime.Items[e.Index]);

                e.Graphics.FillRectangle(SystemBrushes.Window, e.Bounds);
                if (iDrawItemData == 8000) {
                    e.Graphics.DrawString(String.Format("{0} {1}", iDrawItemData / 1000, this.m_clocLanguage.GetLocalized("global.Seconds.Plural", null).ToLower()), new Font("Calibri", 10, FontStyle.Bold), SystemBrushes.WindowText, e.Bounds.Left, e.Bounds.Top, StringFormat.GenericDefault);
                }
                else {
                    e.Graphics.DrawString(String.Format("{0} {1}", Math.Ceiling((double)iDrawItemData / 1000), this.m_clocLanguage.GetLocalized("global.Seconds.Plural", null).ToLower()), this.Font, SystemBrushes.WindowText, e.Bounds.Left, e.Bounds.Top, StringFormat.GenericDefault);
                }
            }
        }

        private bool isInComboList(CPlayerInfo cpiPlayer) {

            bool blFound = false;

            foreach (CPlayerInfo cpiInfo in this.cboPlayerList.Items) {
                if (String.Compare(cpiInfo.SoldierName, cpiPlayer.SoldierName) == 0) {
                    blFound = true;
                    break;
                }
            }

            return blFound;
        }

        private void m_prcClient_ListPlayers(FrostbiteClient sender, List<CPlayerInfo> lstPlayers, CPlayerSubset cpsSubset) {
            if (cpsSubset.Subset == CPlayerSubset.PlayerSubsetType.All) {

                CPlayerInfo objSelected = (CPlayerInfo)this.cboPlayerList.SelectedItem;

                this.cboPlayerList.BeginUpdate();

                // So much easier with linq..
                foreach (CPlayerInfo cpiPlayer in lstPlayers) {
                    if (this.isInComboList(cpiPlayer) == false) {
                        this.cboPlayerList.Items.Add(cpiPlayer);
                    }
                }

                for (int i = 0; i < this.cboPlayerList.Items.Count; i++) {
                    bool blFound = false;

                    CPlayerInfo cpiInfo = (CPlayerInfo)this.cboPlayerList.Items[i];

                    foreach (CPlayerInfo cpiPlayer in lstPlayers) {
                        if (String.Compare(cpiInfo.SoldierName, cpiPlayer.SoldierName) == 0) {
                            blFound = true;
                            break;
                        }
                    }

                    if (blFound == false && cpiInfo.SquadID != -10 && cpiInfo.TeamID != -10) {
                        this.cboPlayerList.Items.RemoveAt(i);
                        i--;
                    }
                }

                this.cboPlayerList.EndUpdate();

            }
        }

        //public void OnPlayerList(List<CPlayerInfo> lstPlayers) {


        //}

        *//*
        public void OnPlayerKilled(string strKillerName, string strVictimName) {
            if (this.chkDisplayOnKilledEvents.Checked == true) {

                foreach (CPlayerInfo cpiInfo in this.cboPlayerList.Items) {
                    if (String.Compare(cpiInfo.SoldierName, strKillerName) == 0) {
                        strKillerName = String.Format("{0} {1}", cpiInfo.ClanTag, cpiInfo.SoldierName);
                    }

                    if (String.Compare(cpiInfo.SoldierName, strVictimName) == 0) {
                        strVictimName = String.Format("{0} {1}", cpiInfo.ClanTag, cpiInfo.SoldierName);
                    }
                }

                // Silly bug =\
                this.m_strPreviousAddition = String.Empty;
                this.Write(String.Format("{0} [^3{1}^0] {2}", strKillerName, this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayOnKilledEvents.Killed", null), strVictimName));
            }
        }


        public void OnPlayerJoin(string strSoldierName) {
            if (this.chkDisplayOnJoinLeaveEvents.Checked == true) {
                this.Write(String.Format("^4{0}", this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayOnJoinLeaveEvents.Joined", new string[] { strSoldierName })));
            }
        }

        public void OnPlayerLeave(string strSoldierName) {
            if (this.chkDisplayOnJoinLeaveEvents.Checked == true) {
                this.Write(String.Format("^1{0}", this.m_clocLanguage.GetLocalized("uscChatPanel.chkDisplayOnJoinLeaveEvents.Left", new string[] { strSoldierName })));
            }
        }


        public void OnChat(List<string> lstRawChat) {

            if (String.Compare(lstRawChat[0], "server", true) != 0) {
                if (lstRawChat.Count == 2) { // < R9 Support.
                    this.Write(String.Format("^b^4{0}^0 > ^4{1}", lstRawChat[0], lstRawChat[1]));
                }
                else if (lstRawChat.Count == 3 && String.Compare(lstRawChat[2], "all", true) == 0) {
                    this.Write(String.Format("^b^4{0}^0 > ^4{1}", lstRawChat[0], lstRawChat[1]));
                }
                else if (lstRawChat.Count >= 4 && String.Compare(lstRawChat[2], "team", true) == 0) {
                    this.Write(String.Format("^b^4{0}^0 - ^4{1}^0 >^4 {2}", lstRawChat[0], this.GetLocalizedTeamName(lstRawChat[3]), lstRawChat[1]));
                }
                else if (lstRawChat.Count >= 5 && String.Compare(lstRawChat[2], "squad", true) == 0) {
                    if (String.Compare(lstRawChat[4], "0") != 0) {
                        this.Write(String.Format("^b^4{0}^0 - ^4{1}^0 - ^4{2}^0 >^4 {3}", lstRawChat[0], this.GetLocalizedTeamName(lstRawChat[3]), this.m_clocLanguage.GetLocalized("global.Squad" + lstRawChat[4], null), lstRawChat[1]));
                    }
                    else {
                        // TO DO: Localize and change uscPlayerListPanel.lsvPlayers.colSquad
                        this.Write(String.Format("^b^4{0}^0 - ^4{1}^0 - ^4{2}^0 >^4 {3}", lstRawChat[0], this.GetLocalizedTeamName(lstRawChat[3]), this.m_clocLanguage.GetLocalized("uscPlayerListPanel.lsvPlayers.colSquad", null), lstRawChat[1]));
                    }
                }
            }
        }
        *//*

        // Quick R3 hack to stop the chat getting spammed out..
        //private string m_strPreviousAddition = String.Empty;

        private void ChatConsole_WriteConsole(DateTime dtLoggedTime, string strLoggedText) {
            string strFormattedConsoleOutput = String.Format("[{0}] {1}{2}", dtLoggedTime.ToString("HH:mm:ss"), strLoggedText, Environment.NewLine);

            this.rtbChatBox.AppendText(strFormattedConsoleOutput);
            //this.rtbConsoleBox.AppendText(String.Format("[{0}] {1}\n", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput));

            // We only pass the length of the original text to exclude the time from being formatted.
            //this.ColourizeConsoleOutput(strConsoleOutput.Length);

            //if (this.ChatLogging == true && this.m_stwChatFile != null) {
            //    this.m_stwChatFile.Write(this.m_regRemoveCaretCodes.Replace(strFormattedConsoleOutput, ""));
            //    this.m_stwChatFile.Flush();
            //}

            if (this.m_prcClient.ChatConsole.Scrolling == true)
            {
                this.rtbChatBox.ScrollToCaret();
            }
            this.rtbChatBox.ReadOnly = false;

            while (this.rtbChatBox.Lines.Length > this.m_prcClient.Variables.GetVariable<int>("MAX_CHAT_LINES", 75)) {
                this.rtbChatBox.Select(0, this.rtbChatBox.Lines[0].Length + 1);

                this.rtbChatBox.SelectedText = String.Empty;
            }
            this.rtbChatBox.ReadOnly = true;
        }
        *//*
        public void Write(string strConsoleOutput) {

            if (String.Compare(strConsoleOutput, m_strPreviousAddition) != 0) {

                m_strPreviousAddition = strConsoleOutput;

                string strFormattedConsoleOutput = String.Format("[{0}] {1}{2}", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput, Environment.NewLine);

                this.rtbChatBox.AppendText(strFormattedConsoleOutput);
                //this.rtbConsoleBox.AppendText(String.Format("[{0}] {1}\n", DateTime.Now.ToString("HH:mm:ss"), strConsoleOutput));

                // We only pass the length of the original text to exclude the time from being formatted.
                //this.ColourizeConsoleOutput(strConsoleOutput.Length);

                if (this.ChatLogging == true && this.m_stwChatFile != null) {
                    this.m_stwChatFile.Write(this.m_regRemoveCaretCodes.Replace(strFormattedConsoleOutput, ""));
                    this.m_stwChatFile.Flush();
                }

                this.rtbChatBox.ScrollToCaret();
                this.rtbChatBox.ReadOnly = false;

                while (this.rtbChatBox.Lines.Length > this.m_uscParent.GetVariableInt("MAX_CHAT_LINES", 75)) {
                    this.rtbChatBox.Select(0, this.rtbChatBox.Lines[0].Length + 1);

                    this.rtbChatBox.SelectedText = String.Empty;
                }
                this.rtbChatBox.ReadOnly = true;
            }
        }
        *//*
        private void txtChat_KeyDown(object sender, KeyEventArgs e) {

            if (e.KeyData == Keys.Enter) {

                this.btnChatSend_Click(this, null);

                this.m_llChatHistory.AddFirst(this.txtChat.Text);
                if (this.m_llChatHistory.Count > 20) {
                    this.m_llChatHistory.RemoveLast();
                }
                this.m_llChatHistoryCurrentNode = null;
                this.txtChat.Clear();
                this.txtChat.Focus();
                e.SuppressKeyPress = true;

                // update max length
                this.chatUpdTxtLength();
            }

            if (e.KeyData == Keys.Up) {
                e.SuppressKeyPress = true;

                if (this.m_llChatHistoryCurrentNode == null && this.m_llChatHistory.First != null) {
                    this.m_llChatHistoryCurrentNode = this.m_llChatHistory.First;
                    this.txtChat.Text = this.m_llChatHistoryCurrentNode.Value;

                    this.txtChat.Select(this.txtChat.Text.Length, 0);
                }
                else if (this.m_llChatHistoryCurrentNode != null && this.m_llChatHistoryCurrentNode.Next != null) {
                    this.m_llChatHistoryCurrentNode = this.m_llChatHistoryCurrentNode.Next;
                    this.txtChat.Text = this.m_llChatHistoryCurrentNode.Value;

                    this.txtChat.Select(this.txtChat.Text.Length, 0);
                }
            }
            else if (e.KeyData == Keys.Down) {

                if (this.m_llChatHistoryCurrentNode != null && this.m_llChatHistoryCurrentNode.Previous != null) {
                    this.m_llChatHistoryCurrentNode = this.m_llChatHistoryCurrentNode.Previous;
                    this.txtChat.Text = this.m_llChatHistoryCurrentNode.Value;

                    this.txtChat.Select(this.txtChat.Text.Length, 0);
                }

                e.SuppressKeyPress = true;
            }
        }
        *//*
        private string GetLocalizedTeamName(string strTeamID) {

            string strReturn = "Unknown";
            int iTeamID = 0;

            if (int.TryParse(strTeamID, out iTeamID) == true) {
                strReturn = this.m_uscParent.GetLocalizedTeamName(iTeamID, this.m_strCurrentMapFileName);
            }

            return strReturn;
        }
        *//*
        public void OnProconAdminSaying(string strAdminStack, string strMessage, CPlayerSubset spsAudience) {

            string strAdminName = this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Admin", null);

            if (strAdminStack.Length > 0) {
                strAdminName = String.Join(" via ", CPluginVariable.DecodeStringArray(strAdminStack));
            }

            if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.All) {
                this.Write(String.Format("^b^2{0}^0 > ^2{1}", strAdminName, strMessage));
            }
            else if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.Player) {
                this.Write(String.Format("^b^2{0}^0 -^2 {1}^0 > ^2{2}", strAdminName, spsAudience.SoldierName, strMessage));
            }
            else if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.Team) {
                this.Write(String.Format("^b^2{0}^0 -^2 {1}^0 >^2 {2}", strAdminName, this.m_uscParent.GetLocalizedTeamName(spsAudience.TeamID, this.m_strCurrentMapFileName), strMessage));
            }
            else if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.Squad) {
                this.Write(String.Format("^b^2{0}^0 -^2 {1}^0 - ^2{2}^0 >^2 {3}", strAdminName, this.m_uscParent.GetLocalizedTeamName(spsAudience.TeamID, this.m_strCurrentMapFileName), this.m_clocLanguage.GetLocalized("global.Squad" + spsAudience.SquadID.ToString(), null), strMessage));
            }
        }

        public void OnProconAdminYelling(string strAdminStack, string strMessage, CPlayerSubset spsAudience) {

            string strAdminName = this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Admin", null);

            if (strAdminStack.Length > 0) {
                strAdminName = String.Join(" via ", CPluginVariable.DecodeStringArray(strAdminStack));
            }

            if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.All) {
                this.Write(String.Format("^b^2{0}^0 > ^2{1}", strAdminName.ToUpper(), strMessage.ToUpper()));
            }
            else if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.Player) {
                this.Write(String.Format("^b^2{0}^0 -^2 {1}^0 > ^2{2}", strAdminName.ToUpper(), spsAudience.SoldierName.ToUpper(), strMessage.ToUpper()));
            }
            else if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.Team) {
                this.Write(String.Format("^b^2{0}^0 -^2 {1}^0 >^2 {2}", strAdminName.ToUpper(), this.m_uscParent.GetLocalizedTeamName(spsAudience.TeamID, this.m_strCurrentMapFileName).ToUpper(), strMessage.ToUpper()));
            }
            else if (spsAudience.Subset == CPlayerSubset.PlayerSubsetType.Squad) {
                this.Write(String.Format("^b^2{0}^0 -^2 {1}^0 - ^2{2}^0 >^2 {3}", strAdminName.ToUpper(), this.m_uscParent.GetLocalizedTeamName(spsAudience.TeamID, this.m_strCurrentMapFileName).ToUpper(), this.m_clocLanguage.GetLocalized("global.Squad" + spsAudience.SquadID.ToString(), null), strMessage).ToUpper());
            }

        }

        public void OnAdminYelling(string strMessage, string strPlayerSubset, string strTarget) {

            if (String.Compare(strPlayerSubset, "all", true) == 0) {
                this.Write(String.Format("^b^4{0}^0 > ^2{1}", this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Admin", null), strMessage));
            }
            else if (String.Compare(strPlayerSubset, "player", true) == 0) {
                this.Write(String.Format("^b^4{0}^0 >^4 {1}:^0 {2} > ^2{3}", this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Admin", null), this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Private", null), strTarget, strMessage));
            }
            else if (String.Compare(strPlayerSubset, "team", true) == 0) {
                this.Write(String.Format("^b^4{0}^0 >^4 Team:^2{1}^0 >^2 {2}", this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Admin", null), strTarget, strMessage));
            }
            else if (String.Compare(strPlayerSubset, "squad", true) == 0) {
                this.Write(String.Format("^b^4{0}^0 >^4 Squad:^2{1}^0 >^2 {2}", this.m_clocLanguage.GetLocalized("uscChatPanel.rtbChatBox.Admin", null), strTarget, strMessage));
            }
        }*//*

        private void btnChatSend_Click(object sender, EventArgs e) {

            this.m_llChatHistory.AddFirst(this.txtChat.Text);
            if (this.m_llChatHistory.Count > 20) {
                this.m_llChatHistory.RemoveLast();
            }
            this.m_llChatHistoryCurrentNode = null;

            CPlayerInfo objSelected = (CPlayerInfo)this.cboPlayerList.SelectedItem;

            if (objSelected != null) {

                if (this.cboDisplayList.SelectedIndex == 0) {

                    string sayOutput = String.Empty;
                    // PK
                    if (this.txtChat.Text.Length > 0 && this.txtChat.Text[0] == '/') {
                        sayOutput = this.txtChat.Text;
                    }
                    else {
                        if (Program.m_application.OptionsSettings.ChatDisplayAdminName)
                        {
                            sayOutput = String.Format("{0}: {1}", this.m_prcClient.Username.Length > 0 ? this.m_prcClient.Username : "Admin", this.txtChat.Text);
                        }
                        else
                        {
                            sayOutput = this.txtChat.Text;
                        }
                    }

                    if (objSelected.SquadID == -10 && objSelected.TeamID == -10) {

                        this.SendCommand(new List<string> { "admin.say", sayOutput, "all" });
                    }
                    else if (objSelected.SquadID == -10 && objSelected.TeamID > 0) {
                        this.SendCommand(new List<string> { "admin.say", sayOutput, "team", objSelected.TeamID.ToString() });
                    }
                    else {
                        this.SendCommand(new List<string> { "admin.say", sayOutput, "player", objSelected.SoldierName });
                    }
                }
                else if (this.cboDisplayList.SelectedIndex == 1) {
                    if (objSelected.SquadID == -10 && objSelected.TeamID == -10) {
                        this.SendCommand(new List<string> { "admin.yell", this.txtChat.Text, ((int)cboDisplayChatTime.SelectedItem).ToString(), "all" });
                    }
                    else if (objSelected.SquadID == -10 && objSelected.TeamID > 0) {
                        this.SendCommand(new List<string> { "admin.yell", this.txtChat.Text, ((int)cboDisplayChatTime.SelectedItem).ToString(), "team", objSelected.TeamID.ToString() });
                    }
                    else {
                        this.SendCommand(new List<string> { "admin.yell", this.txtChat.Text, ((int)cboDisplayChatTime.SelectedItem).ToString(), "player", objSelected.SoldierName });
                    }
                }
            }

            this.txtChat.Clear();
            this.txtChat.Focus();
            // update max length
            this.chatUpdTxtLength();
        }
        private void btnclearchat_Click(object sender, EventArgs e)
        {
            this.rtbChatBox.Clear();
            // update max length
            this.chatUpdTxtLength();
            this.txtChat.Clear();
        }

        private int ListContainsTeam(int iTeamID) {
            int iReturnTeamIndex = -1;

            for (int i = 0; i < this.cboPlayerList.Items.Count; i++) {
                if (((CPlayerInfo)cboPlayerList.Items[i]).SquadID == -10 && ((CPlayerInfo)cboPlayerList.Items[i]).TeamID == iTeamID) {
                    iReturnTeamIndex = i;
                    break;
                }
            }

            return iReturnTeamIndex;
        }

        List<string> m_lstTeamNames = new List<string>(17);
        string m_strCurrentMapFileName = String.Empty;

        private void m_prcClient_ServerInfo(FrostbiteClient sender, CServerInfo csiServerInfo) {
            this.cboPlayerList.BeginUpdate();

            int iTotalTeams = this.m_prcClient.GetLocalizedTeamNameCount(csiServerInfo.Map);
            this.m_strCurrentMapFileName = csiServerInfo.Map;

            // Add all the teams.
            for (int i = 1; i < iTotalTeams; i++) {

                int iTeamIndex = -1;

                if ((iTeamIndex = this.ListContainsTeam(i)) == -1) {
                    this.cboPlayerList.Items.Insert(1, new CPlayerInfo(this.m_prcClient.GetLocalizedTeamName(i, csiServerInfo.Map), String.Empty, i, -10));
                }
                else if (iTeamIndex >= 0 && iTeamIndex < this.cboPlayerList.Items.Count) {
                    this.cboPlayerList.Items[iTeamIndex] = new CPlayerInfo(this.m_prcClient.GetLocalizedTeamName(i, csiServerInfo.Map), String.Empty, i, -10);
                }
            }

            // Remove any excess teams (change gamemode)
            for (int i = 0; i < this.cboPlayerList.Items.Count; i++) {
                if (((CPlayerInfo)cboPlayerList.Items[i]).SquadID == -10 && ((CPlayerInfo)cboPlayerList.Items[i]).TeamID > iTotalTeams) {
                    cboPlayerList.Items.RemoveAt(i);
                    i--;
                }
            }

            this.cboPlayerList.EndUpdate();
        }
        *//*
        public void OnServerInfo(CServerInfo csiInfo) {

        }
        *//*
        private void cboPlayerList_DrawItem(object sender, DrawItemEventArgs e) {

            if (e.Index != -1) {
                //e.Graphics.TextRenderingHint = System.Drawing.Text.TextRenderingHint.AntiAlias;
                CPlayerInfo cpiDraw = ((CPlayerInfo)cboPlayerList.Items[e.Index]);

                e.Graphics.FillRectangle(SystemBrushes.Window, e.Bounds);

                if (cpiDraw.SquadID == -10 && cpiDraw.TeamID == -10) {
                    e.Graphics.DrawString(this.m_strAllPlayers, new Font("Calibri", 10, FontStyle.Bold), SystemBrushes.WindowText, e.Bounds.Left + 5, e.Bounds.Top, StringFormat.GenericDefault);
                }
                else if (cpiDraw.SquadID == -10 && cpiDraw.TeamID > 0) {
                    e.Graphics.DrawString(cpiDraw.SoldierName, new Font("Calibri", 10, FontStyle.Bold), SystemBrushes.WindowText, e.Bounds.Left + 5, e.Bounds.Top, StringFormat.GenericDefault);
                }
                else {
                    e.Graphics.DrawString(String.Format("{0} {1}", cpiDraw.ClanTag, cpiDraw.SoldierName), this.Font, SystemBrushes.WindowText, e.Bounds.Left + 5, e.Bounds.Top, StringFormat.GenericDefault);
                }
            }
        }

        public void PlayerSelectionChange(string strSoldierName) {
            foreach (CPlayerInfo cpiInfo in this.cboPlayerList.Items) {
                if (String.Compare(cpiInfo.SoldierName, strSoldierName) == 0) {
                    this.cboPlayerList.SelectedItem = cpiInfo;
                    break;
                }
            }
        }

        private void cboDisplayList_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.ChatConsole.DisplayTypeIndex = this.cboDisplayList.SelectedIndex;
            }
        }

        private void ChatConsole_DisplayTypeChanged(int index) {
            if (index >= 0 && index < this.cboDisplayList.Items.Count) {
                this.cboDisplayList.SelectedIndex = index;
            }

            if (this.cboDisplayList.SelectedIndex == 0) {
                this.lblDisplayFor.Enabled = false;
                this.cboDisplayChatTime.Enabled = false;
            }
            else if (this.cboDisplayList.SelectedIndex == 1) {
                this.lblDisplayFor.Enabled = true;
                this.cboDisplayChatTime.Enabled = true;
            }
        }

        private void chkDisplayOnJoinLeaveEvents_CheckedChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.ChatConsole.LogJoinLeaving = this.chkDisplayOnJoinLeaveEvents.Checked;
            }
        }

        private void chkDisplayOnKilledEvents_CheckedChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.ChatConsole.LogKills = this.chkDisplayOnKilledEvents.Checked;
            }
        }

        private void chkDisplayScrollingEvents_CheckedChanged(object sender, EventArgs e)
        {
            if (this.m_prcClient != null)
            {
                this.m_prcClient.ChatConsole.Scrolling = this.chkDisplayScrollingEvents.Checked;
            }
        }

        private void ChatConsole_ScrollingChanged(bool isEnabled) {
            this.chkDisplayScrollingEvents.Checked = isEnabled;
            this.chatUpdTxtLength();
            this.txtChat.Clear();
        }

        private void ChatConsole_LogKillsChanged(bool isEnabled) {
            this.chkDisplayOnKilledEvents.Checked = isEnabled;
        }

        private void ChatConsole_LogJoinLeavingChanged(bool isEnabled) {
            this.chkDisplayOnJoinLeaveEvents.Checked = isEnabled;
        }

        private void ChatConsole_DisplayTimeChanged(int index) {
            if (index >= 0 && index < this.cboDisplayChatTime.Items.Count) {
                this.cboDisplayChatTime.SelectedIndex = index;
            }
        }

        private void cboDisplayChatTime_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.m_prcClient != null) {
                this.m_prcClient.ChatConsole.DisplayTimeIndex = this.cboDisplayChatTime.SelectedIndex;
            }
        }

        private void chatUpdTxtLength()
        {
            // update max length
            if (Program.m_application.OptionsSettings.ChatDisplayAdminName)
            {
                if (this.m_prcClient.Username.Length > 0)
                {
                    this.txtChat.MaxLength = 100 - (this.m_prcClient.Username.Length + 2);
                }
                else
                {
                    this.txtChat.MaxLength = 100 - 7; // "Admin: "
                }
            }
            else
            {
                this.txtChat.MaxLength = 100;
            }
            //
        }
    }*/

    /*partial class uscChatPanel {
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
            this.pnlChatEnclosure = new System.Windows.Forms.Panel();
            this.rtbChatBox = new PRoCon.CodRichTextBox();
            this.chkDisplayScrollingEvents = new System.Windows.Forms.CheckBox();
            this.btnclearchat = new System.Windows.Forms.Button();
            this.txtChat = new System.Windows.Forms.TextBox();
            this.lblDisplay = new System.Windows.Forms.Label();
            this.btnChatSend = new System.Windows.Forms.Button();
            this.cboDisplayList = new System.Windows.Forms.ComboBox();
            this.cboPlayerList = new System.Windows.Forms.ComboBox();
            this.chkDisplayOnJoinLeaveEvents = new System.Windows.Forms.CheckBox();
            this.lblAudience = new System.Windows.Forms.Label();
            this.chkDisplayOnKilledEvents = new System.Windows.Forms.CheckBox();
            this.cboDisplayChatTime = new System.Windows.Forms.ComboBox();
            this.lblDisplayFor = new System.Windows.Forms.Label();
            this.pnlChatEnclosure.SuspendLayout();
            this.SuspendLayout();
            //
            // pnlChatEnclosure
            //
            this.pnlChatEnclosure.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.pnlChatEnclosure.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pnlChatEnclosure.Controls.Add(this.rtbChatBox);
            this.pnlChatEnclosure.Location = new System.Drawing.Point(4, 4);
            this.pnlChatEnclosure.Name = "pnlChatEnclosure";
            this.pnlChatEnclosure.Size = new System.Drawing.Size(874, 350);
            this.pnlChatEnclosure.TabIndex = 36;
            //
            // rtbChatBox
            //
            this.rtbChatBox.BackColor = System.Drawing.SystemColors.Window;
            this.rtbChatBox.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rtbChatBox.DetectUrls = false;
            this.rtbChatBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbChatBox.Location = new System.Drawing.Point(0, 0);
            this.rtbChatBox.Name = "rtbChatBox";
            this.rtbChatBox.ReadOnly = true;
            this.rtbChatBox.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.Vertical;
            this.rtbChatBox.Size = new System.Drawing.Size(872, 348);
            this.rtbChatBox.TabIndex = 1;
            this.rtbChatBox.Text = "";
            //
            // chkDisplayScrollingEvents
            //
            this.chkDisplayScrollingEvents.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkDisplayScrollingEvents.AutoSize = true;
            this.chkDisplayScrollingEvents.Location = new System.Drawing.Point(4, 404);
            this.chkDisplayScrollingEvents.Name = "chkDisplayScrollingEvents";
            this.chkDisplayScrollingEvents.Size = new System.Drawing.Size(109, 19);
            this.chkDisplayScrollingEvents.TabIndex = 35;
            this.chkDisplayScrollingEvents.Text = "Enable scrolling";
            this.chkDisplayScrollingEvents.UseVisualStyleBackColor = true;
            this.chkDisplayScrollingEvents.CheckedChanged += new System.EventHandler(this.chkDisplayScrollingEvents_CheckedChanged);
            //
            // btnclearchat
            //
            this.btnclearchat.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnclearchat.Location = new System.Drawing.Point(775, 404);
            this.btnclearchat.Name = "btnclearchat";
            this.btnclearchat.Size = new System.Drawing.Size(101, 23);
            this.btnclearchat.TabIndex = 34;
            this.btnclearchat.Text = "Clear Chat";
            this.btnclearchat.UseVisualStyleBackColor = true;
            this.btnclearchat.Click += new System.EventHandler(this.btnclearchat_Click);
            //
            // txtChat
            //
            this.txtChat.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.txtChat.Location = new System.Drawing.Point(4, 376);
            this.txtChat.MaxLength = 100;
            this.txtChat.Name = "txtChat";
            this.txtChat.Size = new System.Drawing.Size(506, 23);
            this.txtChat.TabIndex = 23;
            this.txtChat.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtChat_KeyDown);
            //
            // lblDisplay
            //
            this.lblDisplay.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.lblDisplay.AutoSize = true;
            this.lblDisplay.Location = new System.Drawing.Point(606, 358);
            this.lblDisplay.Name = "lblDisplay";
            this.lblDisplay.Size = new System.Drawing.Size(45, 15);
            this.lblDisplay.TabIndex = 33;
            this.lblDisplay.Text = "Display";
            //
            // btnChatSend
            //
            this.btnChatSend.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btnChatSend.Location = new System.Drawing.Point(775, 375);
            this.btnChatSend.Name = "btnChatSend";
            this.btnChatSend.Size = new System.Drawing.Size(101, 23);
            this.btnChatSend.TabIndex = 24;
            this.btnChatSend.Text = "Send";
            this.btnChatSend.UseVisualStyleBackColor = true;
            this.btnChatSend.Click += new System.EventHandler(this.btnChatSend_Click);
            //
            // cboDisplayList
            //
            this.cboDisplayList.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.cboDisplayList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboDisplayList.FormattingEnabled = true;
            this.cboDisplayList.Items.AddRange(new object[] {
            "Say",
            "Yell"});
            this.cboDisplayList.Location = new System.Drawing.Point(609, 375);
            this.cboDisplayList.Name = "cboDisplayList";
            this.cboDisplayList.Size = new System.Drawing.Size(56, 23);
            this.cboDisplayList.TabIndex = 32;
            this.cboDisplayList.SelectedIndexChanged += new System.EventHandler(this.cboDisplayList_SelectedIndexChanged);
            //
            // cboPlayerList
            //
            this.cboPlayerList.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.cboPlayerList.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawVariable;
            this.cboPlayerList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboPlayerList.DropDownWidth = 200;
            this.cboPlayerList.FormattingEnabled = true;
            this.cboPlayerList.Location = new System.Drawing.Point(516, 376);
            this.cboPlayerList.Name = "cboPlayerList";
            this.cboPlayerList.Size = new System.Drawing.Size(87, 24);
            this.cboPlayerList.TabIndex = 22;
            this.cboPlayerList.DrawItem += new System.Windows.Forms.DrawItemEventHandler(this.cboPlayerList_DrawItem);
            //
            // chkDisplayOnJoinLeaveEvents
            //
            this.chkDisplayOnJoinLeaveEvents.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkDisplayOnJoinLeaveEvents.AutoSize = true;
            this.chkDisplayOnJoinLeaveEvents.Location = new System.Drawing.Point(4, 431);
            this.chkDisplayOnJoinLeaveEvents.Name = "chkDisplayOnJoinLeaveEvents";
            this.chkDisplayOnJoinLeaveEvents.Size = new System.Drawing.Size(130, 19);
            this.chkDisplayOnJoinLeaveEvents.TabIndex = 30;
            this.chkDisplayOnJoinLeaveEvents.Text = "Display join/leaving";
            this.chkDisplayOnJoinLeaveEvents.UseVisualStyleBackColor = true;
            this.chkDisplayOnJoinLeaveEvents.CheckedChanged += new System.EventHandler(this.chkDisplayOnJoinLeaveEvents_CheckedChanged);
            //
            // lblAudience
            //
            this.lblAudience.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.lblAudience.AutoSize = true;
            this.lblAudience.Location = new System.Drawing.Point(513, 358);
            this.lblAudience.Name = "lblAudience";
            this.lblAudience.Size = new System.Drawing.Size(57, 15);
            this.lblAudience.TabIndex = 26;
            this.lblAudience.Text = "Audience";
            //
            // chkDisplayOnKilledEvents
            //
            this.chkDisplayOnKilledEvents.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.chkDisplayOnKilledEvents.AutoSize = true;
            this.chkDisplayOnKilledEvents.Location = new System.Drawing.Point(4, 456);
            this.chkDisplayOnKilledEvents.Name = "chkDisplayOnKilledEvents";
            this.chkDisplayOnKilledEvents.Size = new System.Drawing.Size(127, 19);
            this.chkDisplayOnKilledEvents.TabIndex = 29;
            this.chkDisplayOnKilledEvents.Text = "Display kills/deaths";
            this.chkDisplayOnKilledEvents.UseVisualStyleBackColor = true;
            this.chkDisplayOnKilledEvents.CheckedChanged += new System.EventHandler(this.chkDisplayOnKilledEvents_CheckedChanged);
            //
            // cboDisplayChatTime
            //
            this.cboDisplayChatTime.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.cboDisplayChatTime.DrawMode = System.Windows.Forms.DrawMode.OwnerDrawVariable;
            this.cboDisplayChatTime.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboDisplayChatTime.Enabled = false;
            this.cboDisplayChatTime.FormattingEnabled = true;
            this.cboDisplayChatTime.Items.AddRange(new object[] {
            "2 seconds",
            "4 seconds",
            "6 seconds",
            "8 seconds",
            "10 seconds",
            "15 seconds",
            "20 seconds",
            "25 seconds",
            "30 seconds",
            "35 seconds",
            "40 seconds",
            "45 seconds",
            "50 seconds",
            "55 seconds",
            "60 seconds"});
            this.cboDisplayChatTime.Location = new System.Drawing.Point(671, 376);
            this.cboDisplayChatTime.Name = "cboDisplayChatTime";
            this.cboDisplayChatTime.Size = new System.Drawing.Size(98, 24);
            this.cboDisplayChatTime.TabIndex = 27;
            this.cboDisplayChatTime.DrawItem += new System.Windows.Forms.DrawItemEventHandler(this.cboDisplayChatTime_DrawItem);
            this.cboDisplayChatTime.SelectedIndexChanged += new System.EventHandler(this.cboDisplayChatTime_SelectedIndexChanged);
            //
            // lblDisplayFor
            //
            this.lblDisplayFor.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.lblDisplayFor.AutoSize = true;
            this.lblDisplayFor.Enabled = false;
            this.lblDisplayFor.Location = new System.Drawing.Point(668, 358);
            this.lblDisplayFor.Name = "lblDisplayFor";
            this.lblDisplayFor.Size = new System.Drawing.Size(63, 15);
            this.lblDisplayFor.TabIndex = 28;
            this.lblDisplayFor.Text = "Display for";
            //
            // uscChatPanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.pnlChatEnclosure);
            this.Controls.Add(this.chkDisplayScrollingEvents);
            this.Controls.Add(this.btnclearchat);
            this.Controls.Add(this.txtChat);
            this.Controls.Add(this.lblDisplay);
            this.Controls.Add(this.btnChatSend);
            this.Controls.Add(this.cboDisplayList);
            this.Controls.Add(this.cboPlayerList);
            this.Controls.Add(this.chkDisplayOnJoinLeaveEvents);
            this.Controls.Add(this.lblAudience);
            this.Controls.Add(this.chkDisplayOnKilledEvents);
            this.Controls.Add(this.cboDisplayChatTime);
            this.Controls.Add(this.lblDisplayFor);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscChatPanel";
            this.Size = new System.Drawing.Size(880, 482);
            this.Load += new System.EventHandler(this.uscChatPanel_Load);
            this.pnlChatEnclosure.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.CheckBox chkDisplayOnJoinLeaveEvents;
        private System.Windows.Forms.CheckBox chkDisplayOnKilledEvents;
        private System.Windows.Forms.Label lblDisplayFor;
        private System.Windows.Forms.ComboBox cboDisplayChatTime;
        private System.Windows.Forms.Label lblAudience;
        private System.Windows.Forms.ComboBox cboPlayerList;
        private System.Windows.Forms.Button btnChatSend;
        private System.Windows.Forms.TextBox txtChat;
        private System.Windows.Forms.Label lblDisplay;
        private System.Windows.Forms.ComboBox cboDisplayList;
        private System.Windows.Forms.Button btnclearchat;
        private System.Windows.Forms.CheckBox chkDisplayScrollingEvents;
        private System.Windows.Forms.Panel pnlChatEnclosure;
        private CodRichTextBox rtbChatBox;

    }*/
}
