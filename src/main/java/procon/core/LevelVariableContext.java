package procon.core;

public class LevelVariableContext {

    /*[Serializable]
    public class LevelVariableContext : IComparable {
        public LevelVariableContextType ContextType {
            get;
            private set;
        }

        public string ContextTarget {
            get;
            private set;
        }

        public LevelVariableContext(LevelVariableContextType lvctContextType) {
            this.ContextType = lvctContextType;
            this.ContextTarget = String.Empty;
        }

        public LevelVariableContext(LevelVariableContextType lvctContextType, string strContextTarget) {
            this.ContextType = lvctContextType;
            this.ContextTarget = strContextTarget;
        }

        public LevelVariableContext(string strContextType, string strContextTarget) {

            if (String.Compare(strContextType, "all", true) == 0) {
                this.ContextType = LevelVariableContextType.All;
            }
            else if (String.Compare(strContextType, "gamemode", true) == 0) {
                this.ContextType = LevelVariableContextType.GameMode;
            }
            else if (String.Compare(strContextType, "level", true) == 0) {
                this.ContextType = LevelVariableContextType.Level;
            }
            else {
                this.ContextType = LevelVariableContextType.None;
            }

            this.ContextTarget = strContextTarget;
        }

        #region IComparable Members

        public int CompareTo(object obj) {
            LevelVariableContext compareObject = (LevelVariableContext)obj;

            //int returnCompare = String.Compare(this.ContextType.ToString(), compareObject.ContextType.ToString(), true);
            //returnCompare += String.Compare(this.ContextTarget, compareObject.ContextTarget, true);

            return String.Compare(this.ToString(), compareObject.ToString(), true);
        }

        #endregion

        public override string ToString() {
            if (this.ContextTarget.Length > 0) {
                return String.Format("{0} - {1}", this.ContextType.ToString(), this.ContextTarget);
            }
            else {
                return String.Format("{0}", this.ContextType.ToString());
            }

        }
    }*/
}
