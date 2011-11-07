package procon.core.textchatmoderation;

public class TextChatModerationEntry {
    /*[Serializable]
    public class TextChatModerationEntry {

        public PlayerModerationLevelType PlayerModerationLevel {
            get;
            private set;
        }

        public string SoldierName {
            get;
            private set;
        }

        public TextChatModerationEntry(PlayerModerationLevelType playerModerationLevel, string soldierName) {
            this.PlayerModerationLevel = playerModerationLevel;
            this.SoldierName = soldierName;
        }

        public TextChatModerationEntry(string playerModerationLevel, string soldierName) {

            this.SoldierName = soldierName;
            this.PlayerModerationLevel = TextChatModerationEntry.GetPlayerModerationLevelType(playerModerationLevel);
        }

        public static PlayerModerationLevelType GetPlayerModerationLevelType(string playerModerationLevel) {

            PlayerModerationLevelType returnPlayerModerationLevel = PlayerModerationLevelType.None;

            switch (playerModerationLevel.ToLower()) {
                case "muted":
                    returnPlayerModerationLevel = PlayerModerationLevelType.Muted;
                    break;
                case "normal":
                    returnPlayerModerationLevel = PlayerModerationLevelType.Normal;
                    break;
                case "voice":
                    returnPlayerModerationLevel = PlayerModerationLevelType.Voice;
                    break;
                case "admin":
                    returnPlayerModerationLevel = PlayerModerationLevelType.Admin;
                    break;
                default:
                    break;
            }

            return returnPlayerModerationLevel;
        }

        public static ServerModerationModeType GetServerModerationLevelType(string serverModerationLevel) {

            ServerModerationModeType returnServerModerationLevel = ServerModerationModeType.None;

            switch (serverModerationLevel.ToLower()) {
                case "muted":
                    returnServerModerationLevel = ServerModerationModeType.Muted;
                    break;
                case "moderated":
                    returnServerModerationLevel = ServerModerationModeType.Moderated;
                    break;
                case "free":
                    returnServerModerationLevel = ServerModerationModeType.Free;
                    break;
                default:
                    break;
            }

            return returnServerModerationLevel;
        }
    }*/
}
