package procon.core.plugin;

public class CPluginVariable {

    /*[Serializable]
    public class CPluginVariable {
        private string m_strVariableName;
        private string m_strVariableType;
        private string m_strVariableValue;

        public CPluginVariable(string strVariableName, string strVariable, string strValue) {
            this.m_strVariableName = strVariableName;
            this.m_strVariableType = strVariable;
            this.m_strVariableValue = strValue;
        }

        public CPluginVariable(string strVariableName, Type tyVariable, object objValue) {
            this.m_strVariableName = strVariableName;

            if (tyVariable == typeof(bool)) {
                this.m_strVariableType = "bool";
                this.m_strVariableValue = objValue.ToString();
            }
            else if (tyVariable == typeof(enumBoolOnOff)) {
                this.m_strVariableType = "onoff";
                this.m_strVariableValue = Enum.GetName(tyVariable, (enumBoolOnOff)objValue);
            }
            else if (tyVariable == typeof(enumBoolYesNo)) {
                this.m_strVariableType = "yesno";
                this.m_strVariableValue = Enum.GetName(tyVariable, (enumBoolYesNo)objValue);
            }
            else if (tyVariable == typeof(int)) {
                this.m_strVariableType = "int";
                this.m_strVariableValue = objValue.ToString();
            }
            else if (tyVariable == typeof(double)) {
                this.m_strVariableType = "double";
                this.m_strVariableValue = objValue.ToString();
            }
            else if (tyVariable == typeof(string)) {
                if (objValue != null) {
                    this.m_strVariableType = "multiline";
                    this.m_strVariableValue = objValue.ToString();
                }
                else {
                    this.m_strVariableType = "multiline";
                    this.m_strVariableValue = String.Empty;
                }
            }
            else if (tyVariable == typeof(string[])) {
                if (objValue != null) {
                    this.m_strVariableType = "stringarray";
                    this.m_strVariableValue = EncodeStringArray((string[])objValue);
                }
                else {
                    this.m_strVariableType = "stringarray";
                    this.m_strVariableValue = String.Empty;
                }
            }
        }

        public string Name {
            get { return this.m_strVariableName; }
        }

        public string Type {
            get { return this.m_strVariableType; }
        }

        public string Value {
            get { return this.m_strVariableValue; }
        }

        // Yes I know.  I got lazy..
        public static string Encode(string strData) {
            string strReturn = HttpUtility.UrlEncode(strData);
            if (strReturn != null) {
                strReturn = strReturn.Replace("(", "%28");
                strReturn = strReturn.Replace(")", "%29");
                strReturn = strReturn.Replace(",", "%2C");
            }
            return strReturn;
        }

        public static string Decode(string strData) {
            return HttpUtility.UrlDecode(strData);
        }

        public static string HtmlDecode(string strData) {
            return HttpUtility.HtmlDecode(strData);
        }

        public static string[] DecodeStringArray(string strValue) {
            var a_strReturn = new string[] { };

            a_strReturn = strValue.Split(new char[] { '|' });

            for (int i = 0; i < a_strReturn.Length; i++) {
                a_strReturn[i] = Decode(a_strReturn[i]);
            }

            return a_strReturn;
        }

        public static string EncodeStringArray(string[] a_strValue) {

            StringBuilder encodedString = new StringBuilder();

            for (int i = 0; i < a_strValue.Length; i++) {
                if (i > 0) {
                    encodedString.Append("|");
                    //strReturn += "|";
                }
                encodedString.Append(Encode(a_strValue[i]));
                //strReturn += Encode(a_strValue[i]);
            }

            return encodedString.ToString();
        }
    }*/
}
