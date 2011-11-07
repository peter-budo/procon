package procon.core.consoles;

public class PunkbusterConsole {

    /*
    public class PunkbusterConsole : Loggable {

        public event WriteConsoleHandler WriteConsole;

        private PRoConClient m_prcClient;

        public PunkbusterConsole(PRoConClient prcClient)
            : base() {

            this.m_prcClient = prcClient;

            this.FileHostNamePort = this.m_prcClient.FileHostNamePort;
            this.LoggingStartedPrefix = "Punkbuster logging started";
            this.LoggingStoppedPrefix = "Punkbuster logging stopped";
            this.FileNameSuffix = "punkbuster";

            this.m_prcClient.Game.PunkbusterMessage += new FrostbiteClient.PunkbusterMessageHandler(m_prcClient_PunkbusterMessage);
            this.m_prcClient.Game.SendPunkbusterMessage += new FrostbiteClient.SendPunkBusterMessageHandler(m_prcClient_SendPunkbusterMessage);
        }

        private void m_prcClient_SendPunkbusterMessage(FrostbiteClient sender, string punkbusterMessage) {
            this.Write("^2" + punkbusterMessage.TrimEnd('\r', '\n').Replace("{", "{{").Replace("}", "}}"));
        }

        private void m_prcClient_PunkbusterMessage(FrostbiteClient sender, string punkbusterMessage) {
            this.Write(punkbusterMessage.TrimEnd('\r', '\n').Replace("{", "{{").Replace("}", "}}"));
        }

        public void Write(string strFormat, params string[] a_objArguments) {

            DateTime dtLoggedTime = DateTime.UtcNow.ToUniversalTime().AddHours(m_prcClient.Game.UTCoffset).ToLocalTime();
            string strText = String.Format(strFormat, a_objArguments);

            this.WriteLogLine(String.Format("[{0}] {1}", dtLoggedTime.ToString("HH:mm:ss"), strText));

            if (this.WriteConsole != null) {
                FrostbiteConnection.RaiseEvent(this.WriteConsole.GetInvocationList(), dtLoggedTime, strText);
            }
        }
    }
     */
}
