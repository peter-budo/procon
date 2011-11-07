package procon.core.httpserver;

public class HttpWebServer {

    /*public class HttpWebServer {

        public delegate void ProcessResponseHandler(HttpWebServerRequest sender);
        public event ProcessResponseHandler ProcessRequest;

        public delegate void StateChangeHandler(HttpWebServer sender);
        public event StateChangeHandler HttpServerOnline;
        public event StateChangeHandler HttpServerOffline;

        private TcpListener m_tcpListener;
        private List<HttpWebServerRequest> m_httpClients;

        private Dictionary<string, HttpWebServerResponseData> m_cachedResponses;

        public string BindingAddress {
            get;
            set;
        }

        public UInt16 ListeningPort {
            get;
            set;
        }

        public bool IsOnline {
            get;
            private set;
        }

        public HttpWebServer(string bindingAddress, UInt16 port) {
            this.m_httpClients = new List<HttpWebServerRequest>();
            this.m_cachedResponses = new Dictionary<string, HttpWebServerResponseData>();

            this.BindingAddress = bindingAddress;
            this.ListeningPort = port;
        }

        private IPAddress ResolveHostName(string strHostName) {
            IPAddress ipReturn = IPAddress.None;

            if (IPAddress.TryParse(strHostName, out ipReturn) == false) {

                ipReturn = IPAddress.None;

                try {
                    IPHostEntry iphHost = Dns.GetHostEntry(strHostName);

                    if (iphHost.AddressList.Length > 0) {
                        ipReturn = iphHost.AddressList[0];
                    }
                    // ELSE return IPAddress.None..
                }
                catch (Exception) { } // Returns IPAddress.None..
            }

            return ipReturn;
        }

        public void Start() {

            try {
                IPAddress ipBinding = this.ResolveHostName(this.BindingAddress);

                this.m_tcpListener = new TcpListener(ipBinding, this.ListeningPort);

                this.m_tcpListener.Start();

                this.m_tcpListener.BeginAcceptTcpClient(this.ListenIncommingWebRequests, null);
                this.IsOnline = true;

                if (this.HttpServerOnline != null) {
                    FrostbiteConnection.RaiseEvent(this.HttpServerOnline.GetInvocationList(), this);
                }
            }
            catch (SocketException) {
                this.Shutdown();
            }
        }

        // private AsyncCallback m_asyncAcceptCallback = new AsyncCallback(PRoConLayer.ListenIncommingLayerConnections);
        private void ListenIncommingWebRequests(IAsyncResult ar) {
            TcpClient tcpNewConnection = null;
            try {
                tcpNewConnection = this.m_tcpListener.EndAcceptTcpClient(ar);

                HttpWebServerRequest newClient = new HttpWebServerRequest(tcpNewConnection.GetStream());
                newClient.ProcessRequest += new HttpWebServer.ProcessResponseHandler(newClient_ProcessRequest);
                newClient.ResponseSent += new HttpWebServerRequest.ResponseSentHandler(newClient_ResponseSent);
                newClient.ClientShutdown += new HttpWebServerRequest.ClientShutdownHandler(newClient_ClientShutdown);

                this.m_httpClients.Add(newClient);

                //if (this.m_tcpListener != null) {
                //    this.m_tcpListener.BeginAcceptTcpClient(this.ListenIncommingWebRequests, null);
                //}
            }
            catch (Exception) {
                if (tcpNewConnection != null) {
                    tcpNewConnection.Close();
                }
            }

            try {
                if (this.m_tcpListener != null) {
                    this.m_tcpListener.BeginAcceptTcpClient(this.ListenIncommingWebRequests, null);
                }
            }
            catch (Exception) {
                this.Shutdown();
            }

        }

        private void newClient_ResponseSent(HttpWebServerRequest request, HttpWebServerResponseData response) {
            if (response.Cache.CacheType == HttpWebServerCacheType.Cache && this.m_cachedResponses.ContainsKey(request.ToString()) == false) {
                this.m_cachedResponses.Add(request.ToString(), response);
            }
        }

        private void newClient_ProcessRequest(HttpWebServerRequest sender) {

            // Scrub the cache for old responses
            foreach (string key in new List<string>(this.m_cachedResponses.Keys)) {
                if (this.m_cachedResponses[key].Cache.TrashTime >= DateTime.Now) {
                    this.m_cachedResponses.Remove(key);
                }
            }

            if (this.m_cachedResponses.ContainsKey(sender.ToString()) == true) {
                sender.Respond(this.m_cachedResponses[sender.ToString()]);
            }
            else if (this.ProcessRequest != null) {
                FrostbiteConnection.RaiseEvent(this.ProcessRequest.GetInvocationList(), sender);
            }
        }

        private void newClient_ClientShutdown(HttpWebServerRequest sender) {
            if (this.m_httpClients.Contains(sender) == true) {
                this.m_httpClients.Remove(sender);
            }
        }

        public void Shutdown() {

            try {
                this.IsOnline = false;

                foreach (HttpWebServerRequest client in new List<HttpWebServerRequest>(this.m_httpClients)) {
                    client.Shutdown();
                }

                if (this.m_tcpListener != null) {
                    this.m_tcpListener.Stop();
                    this.m_tcpListener = null;
                }

                if (this.HttpServerOffline != null) {
                    FrostbiteConnection.RaiseEvent(this.HttpServerOffline.GetInvocationList(), this);
                }
            }
            catch (Exception) { }
        }

    }*/
}
