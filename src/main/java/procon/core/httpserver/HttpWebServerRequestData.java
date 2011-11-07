package procon.core.httpserver;

public class HttpWebServerRequestData {

    /*[Serializable]
    public class HttpWebServerRequestData {

        public string Method {
            get;
            private set;
        }

        public string Request {
            get;
            private set;
        }

        public string RequestPath {
            get;
            private set;
        }

        public string RequestFile {
            get;
            private set;
        }

        public NameValueCollection Query {
            get;
            private set;
        }

        public string HttpVersion {
            get;
            private set;
        }

        public string Post {
            get;
            private set;
        }

        public WebHeaderCollection Headers {
            get;
            private set;
        }

        public HttpWebServerRequestData(string document) {
            this.Method = String.Empty;
            this.Request = String.Empty;
            this.RequestPath = String.Empty;
            this.RequestFile = String.Empty;
            this.HttpVersion = String.Empty;
            this.Post = String.Empty;
            this.Query = new NameValueCollection();
            this.Headers = new WebHeaderCollection();

            Match methodFileMatch = Regex.Match(document, @"^(?<method>GET|POST) (?<request>.*?) HTTP/(?<http_version>[0-9\.]*)[\r\n]+?", RegexOptions.IgnoreCase);
            if (methodFileMatch.Success == true) {

                this.Method = methodFileMatch.Groups["method"].Value;
                this.Request = methodFileMatch.Groups["request"].Value;
                this.HttpVersion = methodFileMatch.Groups["http_version"].Value;

                string[] a_requestQueryString = this.Request.Split(new char[] { '?' }, 2, StringSplitOptions.RemoveEmptyEntries);

                if (a_requestQueryString.Length >= 1) {
                    int lastIndexForwardSlash = a_requestQueryString[0].LastIndexOf('/') + 1;

                    this.RequestPath = a_requestQueryString[0].Substring(0, lastIndexForwardSlash);
                    this.RequestFile = a_requestQueryString[0].Substring(lastIndexForwardSlash, a_requestQueryString[0].Length - lastIndexForwardSlash);

                    if (a_requestQueryString.Length >= 2) {
                        this.Query = HttpUtility.ParseQueryString(a_requestQueryString[1]);
                    }
                }

                document = document.Remove(0, methodFileMatch.Value.Length);

                this.Headers = new WebHeaderCollection();
                Match headersMatch = Regex.Match(document, @"(?<header>.*?)[ ]*?:[ ]*?(?<value>.*?)[\r\n]+?");

                while (headersMatch.Success == true) {

                    this.Headers.Set(headersMatch.Groups["header"].Value, headersMatch.Groups["value"].Value);

                    document = document.Remove(0, headersMatch.Value.Length);

                    headersMatch = headersMatch.NextMatch();
                }

                if (String.Compare(this.Method, "POST", true) == 0) {
                    // Read additional post data.
                }
            }
        }
    }*/
}
