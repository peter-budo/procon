package procon.core.httpserver;

public class HttpWebServerRequest {

    /*public class HttpWebServerRequest {

        public delegate void ResponseSentHandler(HttpWebServerRequest request, HttpWebServerResponseData response);
        public event ResponseSentHandler ResponseSent;

        public delegate void ClientShutdownHandler(HttpWebServerRequest sender);
        public event ClientShutdownHandler ClientShutdown;

        public event HttpWebServer.ProcessResponseHandler ProcessRequest;

        private byte[] ma_completedPacket;
        private byte[] ma_recievedPacket;

        public NetworkStream Stream {
            get;
            private set;
        }

        public HttpWebServerRequestData Data {
            get;
            private set;
        }

        public HttpWebServerRequest(NetworkStream stream) {
            this.Stream = stream;
            this.ma_recievedPacket = new byte[4096];

            this.Stream.BeginRead(this.ma_recievedPacket, 0, this.ma_recievedPacket.Length, this.ReadWebRequests, null);
        }

        public void ProcessPacket() {

            if (this.ma_completedPacket != null) {

                string packet = Encoding.ASCII.GetString(this.ma_completedPacket);

                this.Data = new HttpWebServerRequestData(packet);

                if (this.ProcessRequest != null) {
                    FrostbiteConnection.RaiseEvent(this.ProcessRequest.GetInvocationList(), this);
                }
            }
        }

        #region Reading packet

        private void CompilePacket(int recievedData) {
            if (this.ma_completedPacket == null) {
                this.ma_completedPacket = new byte[recievedData];
            }
            else {
                Array.Resize(ref this.ma_completedPacket, this.ma_completedPacket.Length + recievedData);
            }

            Array.Copy(this.ma_recievedPacket, 0, this.ma_completedPacket, this.ma_completedPacket.Length - recievedData, recievedData);
        }

        private void ReadWebRequests(IAsyncResult ar) {

            try {
                //HttpWebServerRequest client = (HttpWebServerRequest)ar.AsyncState;

                int iBytesRead = this.Stream.EndRead(ar);

                if (iBytesRead > 0) {
                    this.CompilePacket(iBytesRead);

                    if (this.Stream.DataAvailable == true) {
                        this.Stream.BeginRead(this.ma_recievedPacket, 0, this.ma_recievedPacket.Length, this.ReadWebRequests, null);
                    }
                    else {
                        this.ProcessPacket();
                    }
                }
            }
            catch (Exception) {

            }

            this.Shutdown();
        }

        public void Shutdown() {
            if (this.Stream != null) {
                this.Stream.Close();
                this.Stream = null;

                if (this.ClientShutdown != null) {
                    FrostbiteConnection.RaiseEvent(this.ClientShutdown.GetInvocationList(), this);
                }
            }
        }

        #endregion

        #region Sending Packet

        public void Respond(HttpWebServerResponseData response) {

            try {
                if (this.Stream != null) {

                    byte[] bData = Encoding.UTF8.GetBytes(response.ToString());

                    this.Stream.Write(bData, 0, bData.Length);

                    if (this.ResponseSent != null) {
                        FrostbiteConnection.RaiseEvent(this.ResponseSent.GetInvocationList(), this, response);
                    }

                    this.Shutdown();
                }
            }
            catch (Exception e) {

            }
        }

        #endregion

        public override string ToString() {
            return this.Data.Request;
        }
    }*/
}
