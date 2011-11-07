package procon.core.lists;

public class ListsSettings {

    /*public class ListsSettings {

        PRoConClient m_prcClient;

        public delegate void ManualBansVisibleChangeHandler(bool isVisible);
        public event ManualBansVisibleChangeHandler ManualBansVisibleChange;

        private bool m_isManualBansVisible;
        public bool ManualBansVisible {
            get {
                return this.m_isManualBansVisible;
            }
            set {
                this.m_isManualBansVisible = value;

                if (this.ManualBansVisibleChange != null) {
                    FrostbiteConnection.RaiseEvent(this.ManualBansVisibleChange.GetInvocationList(), this.m_isManualBansVisible);
                }
            }
        }

        public string CurrentPlaylist {
            get;
            private set;
        }

        public List<string> Settings {
            get {
                return new List<string>() { this.m_isManualBansVisible.ToString() };
            }
            set {
                bool isVisible = true;

                if (value.Count >= 1 && bool.TryParse(value[0], out isVisible) == true) {
                    this.m_isManualBansVisible = isVisible;
                }
            }
        }

        public ListsSettings(PRoConClient prcClient) {
            this.m_isManualBansVisible = false;

            this.m_prcClient = prcClient;
            this.m_prcClient.Game.PlaylistSet += new FrostbiteClient.PlaylistSetHandler(m_prcClient_PlaylistSet);
        }

        private void m_prcClient_PlaylistSet(FrostbiteClient sender, string playlist) {
            this.CurrentPlaylist = playlist;
        }
    }*/
}
