package procon.core.consoles.chat;

public class ChatMessage {
    /*public class ChatMessage {

        public DateTime LoggedTime {
            get;
            private set;
        }

        public string Speaker {
            get;
            private set;
        }

        public string Message {
            get;
            private set;
        }

        public bool IsFromServer {
            get;
            private set;
        }

        public bool IsYelling {
            get;
            private set;
        }

        public CPlayerSubset Subset {
            get;
            private set;
        }

        public ChatMessage(DateTime loggedTime, string speaker, string message, bool isFromServer, bool isYelling, CPlayerSubset subset) {
            this.LoggedTime = loggedTime;
            this.Speaker = speaker;
            this.Message = message;
            this.IsFromServer = isFromServer;
            this.IsYelling = isYelling;
            this.Subset = subset;
        }

        public Hashtable ToHashtable() {

            Hashtable message = new Hashtable();

            message.Add("date_time", JSON.DateTimeToISO8601(this.LoggedTime.ToUniversalTime()));
            message.Add("speaker", this.Speaker);
            message.Add("message", this.Message);
            message.Add("is_from_server", this.IsFromServer);
            message.Add("is_yelling", this.IsYelling);
            message.Add("subset", this.Subset.ToHashtable());

            return message;
        }
    }*/
}
