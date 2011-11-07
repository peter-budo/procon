package procon.core;

public class ConnectionDictionary {
    /*
    public class ConnectionDictionary : KeyedCollection<string, PRoConClient> {

        public delegate void ConnectionAlteredHandler(PRoConClient item);
        public event ConnectionAlteredHandler ConnectionAdded;
        public event ConnectionAlteredHandler ConnectionChanged;
        public event ConnectionAlteredHandler ConnectionRemoved;

        protected override string GetKeyForItem(PRoConClient item) {
            return item.HostNamePort;
        }

        protected override void InsertItem(int index, PRoConClient item) {
            if (this.ConnectionAdded != null) {
                FrostbiteConnection.RaiseEvent(this.ConnectionAdded.GetInvocationList(), item);
            }

            base.InsertItem(index, item);
        }

        protected override void RemoveItem(int index) {
            PRoConClient prcClient = this[index];

            base.RemoveItem(index);

            if (this.ConnectionRemoved != null) {
                FrostbiteConnection.RaiseEvent(this.ConnectionRemoved.GetInvocationList(), prcClient);
            }
        }

        protected override void SetItem(int index, PRoConClient item) {
            if (this.ConnectionChanged != null) {
                FrostbiteConnection.RaiseEvent(this.ConnectionChanged.GetInvocationList(), item);
            }

            base.SetItem(index, item);
        }

        public string ToJsonString() {

            Hashtable connections = new Hashtable();

            ArrayList connectionList = new ArrayList();
            foreach (PRoConClient client in this) {

                Hashtable connection = new Hashtable();

                connection.Add("host_name_port", client.HostNamePort);
                connection.Add("connected", (bool)(client.State == ConnectionState.Connected));
                connection.Add("logged_in", client.IsLoggedIn);
                connection.Add("procon_connection", client.IsPRoConConnection);
                connection.Add("gametype", client.GameType);
                connection.Add("version", client.VersionNumber);

                connectionList.Add(connection);
            }

            connections.Add("connections", connectionList);

            return JSON.JsonEncode(connections);
        }
    }
     */
}
