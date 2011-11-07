package procon.core.httpserver;

public class HttpWebServerResponseData {

    /*[Serializable]
    public class HttpWebServerResponseData {

        public HttpWebServerCacheSettings Cache {
            get;
            private set;
        }

        public string HttpVersion {
            get;
            set;
        }

        /// <summary>
        /// You shouldn't ever need to reply with anything but 200 OK
        /// instead you should include an error response in your JSON output
        /// </summary>
        public string StatusCode {
            get;
            set;
        }

        public WebHeaderCollection Headers {
            get;
            private set;
        }

        public string Document {
            get;
            set;
        }

        public HttpWebServerResponseData(string document) {

            this.Headers = new WebHeaderCollection();
            this.HttpVersion = "1.1";
            this.StatusCode = "200 OK";

            this.Cache = new HttpWebServerCacheSettings();

            // To-Do, most should just accept UTF-8 but we should take
            // the requested content-type into consideration
            this.Headers.Set("Content-Type", "text/html; charset=UTF-8");
            this.Headers.Set("Accept-Ranges", "bytes");
            this.Headers.Set("Connection", "close");

            this.Document = document;
        }

        public override string ToString() {

            StringBuilder builder = new StringBuilder();

            builder.AppendFormat("HTTP/{0} {1}\r\n", this.HttpVersion, this.StatusCode);

            foreach (string header in this.Headers.AllKeys) {
                builder.AppendFormat("{0}: {1}\r\n", header, this.Headers[header]);
            }

            // TODO: Test if this is needed?
            //builder.AppendFormat("Content-Length: {0}\r\n\r\n\r\n", this.Document.Length);
            builder.Append("\r\n\r\n");
            builder.Append(this.Document);

            return builder.ToString();
        }
    }*/
}