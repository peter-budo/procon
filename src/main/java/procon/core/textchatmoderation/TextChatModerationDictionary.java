package procon.core.textchatmoderation;

public class TextChatModerationDictionary {
    /*[Serializable]
    public class TextChatModerationDictionary : KeyedCollection<string, TextChatModerationEntry> {

        protected override string GetKeyForItem(TextChatModerationEntry item) {
            return item.SoldierName;
        }

        public void AddEntry(TextChatModerationEntry item) {
            if (this.Contains(item.SoldierName) == true) {
                this.SetItem(this.IndexOf(this[item.SoldierName]), item);
            }
            else {
                this.Add(item);
            }
        }

        public void RemoveEntry(TextChatModerationEntry item) {
            if (this.Contains(item.SoldierName) == true) {
                this.Remove(item.SoldierName);
            }
        }

        public void AddRange(IEnumerable<TextChatModerationEntry> list) {
            foreach (TextChatModerationEntry item in list) {
                this.AddEntry(item);
            }
        }

    }*/
}
