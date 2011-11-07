package procon.core;

public class LevelVariable {

    /*[Serializable]
    public class LevelVariable {

        public LevelVariableContext Context {
            get;
            private set;
        }

        public string VariableName {
            get;
            private set;
        }

        public string RawValue {
            get;
            set;
        }

        public T GetValue<T>(T tDefault) {
            T tReturn = tDefault;

            TypeConverter tycPossible = TypeDescriptor.GetConverter(typeof(T));
            if (tycPossible.CanConvertFrom(typeof(string)) == true) {
                tReturn = (T)tycPossible.ConvertFrom(this.RawValue);
            }
            else {
                tReturn = tDefault;
            }

            return tReturn;
        }

        public LevelVariable(LevelVariableContext lvcContext, string strVariableName, string strRawValue) {
            this.Context = lvcContext;
            this.VariableName = strVariableName;
            this.RawValue = strRawValue;
        }

        public static LevelVariable ExtractContextVariable(bool skipAllContext, List<string> contextList) {

            string contextType = String.Empty, contextTarget = String.Empty, variableName = String.Empty, variableValue = String.Empty;

            if (contextList.Count >= 1) {

                int offset = 0;

                contextType = contextList[offset++];

                if (String.Compare(contextType, "all") != 0 && offset < contextList.Count) {
                    contextTarget = contextList[offset++];
                }
                else if (skipAllContext == true) {
                    offset++;
                }

                if (offset < contextList.Count) {
                    variableName = contextList[offset++];
                }

                if (offset < contextList.Count) {
                    variableValue = contextList[offset++];
                }
            }

            return new LevelVariable(new LevelVariableContext(contextType, contextTarget), variableName, variableValue);
        }
    }*/
}
