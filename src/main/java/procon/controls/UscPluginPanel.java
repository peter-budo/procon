package procon.controls;

public class UscPluginPanel {

    /*public partial class uscPluginPanel : UserControl {

        private uscServerConnection m_uscParent;
        private frmMain m_frmMain;
        private CLocalization m_clocLanguage;

        private PRoConClient m_prcClient;

        private CustomClass m_cscPluginVariables;

        public delegate void EventDelegate();
        public event EventDelegate ReloadPlugins;

        public delegate void PluginEventDelegate(PluginDetails spdPlugin);
        public event PluginEventDelegate PluginLoaded;
        public event PluginEventDelegate PluginVariablesAltered;

        public delegate PluginDetails GetPluginDetailsDelegate(string strClassName);
        public event GetPluginDetailsDelegate GetPluginDetails;

        public delegate void SetPluginVariableDelegate(string strClassName, string strVariable, string strValue);
        public event SetPluginVariableDelegate SetPluginVariable;

        public delegate void PluginEnabledDelegate(string strClassName, bool blEnabled);
        public event PluginEnabledDelegate PluginEnabled;

        public ListViewNF.ListViewItemCollection LoadedPlugins {
            get {
                return this.lsvLoadedPlugins.Items;
            }
        }


        #region css string
        private readonly string m_cssDescription = @"

*//* RESET *//*

html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,font,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,dl,dt,dd,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td{border:0;outline:0;vertical-align:baseline;background:transparent;margin:0;padding:0;}



*//* BASIC *//*

*:focus{outline:none;}

.clear{clear:both;}

body{font-family:Tahoma;font-size:11px;color:#2C2C29;}

p{font-size:1.2em;padding:2px;margin:1px 0 15px;}

a{color:#807D7A;}

h1{background-color:#FFF;border-bottom:1px solid #DCDCDB;letter-spacing:-1px;font-size:24px;padding-bottom:3px;font-weight:400;margin:10px 0 3px 0;font-family:Vera, Helvetica, Georgia;}

h2{background-color:#FFF;border-bottom:1px solid #DCDCDB;letter-spacing:-1px;font-size:20px;padding-bottom:3px;font-weight:400;margin:10px 0 3px 0;font-family:Vera, Helvetica, Georgia;color:#3366ff;}

h2 a{font-weight:700;border:0;text-decoration:none;color:#3366ff;display:block;}

h3{font-size:1.3em;color:#3366ff;}

h4{font-size:1.1em}

h5{font-size:0.9em}

h6{font-size:0.7em}

hr{color:#DCDCDB;background-color:#DCDCDB;height:1px;border:0px;}

pre{width:100%; white-space:pre-wrap;}

ul li{list-style:circle;margin-bottom:4px;}

blockquote{margin:20px 10px 10px 5px;border-left:4px solid #DDD;padding:0 5px 0 5px;font-size:11px;text-align:justify;}

table{margin:.5em 0 1em;font-size:11px;}

table td,table th{text-align:left;border-right:1px solid #fff;padding:.4em .8em;}

table th{background-color:#5e5e5e;color:#fff;text-transform:uppercase;font-weight:bold;border-bottom:1px solid #e8e1c8;}

table td{background-color:#eee;}

table th a{color:#d6f325;}

table th a:hover{color:#fff;}

table tr.even td{background-color:#ddd;}

table tr:hover td{background-color:#fff;}

table.nostyle td,table.nostyle th,table.nostyle tr.even td,table.nostyle tr:hover td{border:0;background:none;background-color:transparent;}
";
        #endregion

        private bool m_blLocalPlugins;
        [CategoryAttribute("PRoCon Settings"), DescriptionAttribute("The control is used for local plugins or remote")]
        public bool LocalPlugins {
            set {
                //this.spltPlugins.Panel2Collapsed = value;
                this.lnkReloadPlugins.Visible = !value;

                this.m_blLocalPlugins = value;
            }
            get {
                return this.m_blLocalPlugins;
            }
        }

        private AssemblyBuilder m_asmBuilder;
        private ModuleBuilder m_modBuilder;
        private Dictionary<string, Enum> m_dicGeneratedEnums;

        public uscPluginPanel() {
            InitializeComponent();

            this.m_frmMain = null;
            this.m_uscParent = null;

            if (this.webDescription.Document == null) {
                this.webDescription.Navigate("about:blank");
                this.webDescription.Document.Window.Name = "hi";
            }
            this.webDescription.Navigating += new WebBrowserNavigatingEventHandler(webDescription_Navigating);

            this.m_asmBuilder = Thread.GetDomain().DefineDynamicAssembly(new AssemblyName("PRoConPluginEnumAssembly"), AssemblyBuilderAccess.Run);
            this.m_modBuilder = this.m_asmBuilder.DefineDynamicModule("PRoConPluginEnumModule");
            this.m_dicGeneratedEnums = new Dictionary<string, Enum>();

            this.m_cscPluginVariables = new CustomClass();

            this.m_blLocalPlugins = true;

            this.lsvLoadedPlugins.CreateGraphics();
        }

        void webDescription_Navigating(object sender, WebBrowserNavigatingEventArgs e) {
            e.Cancel = true;
        }

        public void Initialize(frmMain frmMainWindow, uscServerConnection uscParent) {

            this.m_frmMain = frmMainWindow;
            this.m_uscParent = uscParent;

            this.tbcPluginDetails.ImageList = this.m_frmMain.iglIcons;
            this.lsvLoadedPlugins.SmallImageList = this.m_frmMain.iglIcons;

            this.tabPluginDetails.ImageKey = "information.png";
            this.tabPluginSettings.ImageKey = "plugin_edit.png";
        }

        public void SetConnection(PRoConClient prcClient) {
            if ((this.m_prcClient = prcClient) != null) {

            }
        }

        public void SetColour(string strVariable, string strValue) {
            this.rtbScriptConsole.SetColour(strVariable, strValue);
        }

        public void SetLocalization(CLocalization clocLanguage) {
            this.m_clocLanguage = clocLanguage;

            //this.tbpPlugins.Text = this.m_clocLanguage.GetLocalized("uscServerConnection.tbpPlugins.Title", null);

            this.lblLoadedPlugins.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.lblLoadedPlugins", null);
            this.lnkReloadPlugins.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.lnkReloadPlugins", null);
            //this.lnkReloadPlugins.LinkArea = new LinkArea(0, this.lnkReloadPlugins.Text.Length);
            this.lnkMorePlugins.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.lnkMorePlugins", null);

            this.tabPluginDetails.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails", null);
            //this.lblPluginName.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails.lblPluginName", null);
            //this.lblPluginAuthor.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails.lblPluginAuthor", null);
            //this.lblPluginDescription.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails.lblPluginDescription", null);
            //this.lblPluginVersion.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails.lblPluginVersion", null);
            //this.lblPluginWebsite.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails.lblPluginWebsite", null);

            this.tabPluginSettings.Text = this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginSettings", null);
        }

        public event uscServerConnection.OnTabChangeDelegate OnTabChange;

        private bool m_blSettingTab = false;
        private void tbcPluginDetails_SelectedIndexChanged(object sender, EventArgs e) {
            if (this.m_blSettingTab == false && this.OnTabChange != null) {
                Stack<string> stkTabIndexes = new Stack<string>();

                if (lsvLoadedPlugins.SelectedItems.Count > 0) {
                    stkTabIndexes.Push(lsvLoadedPlugins.SelectedItems[0].Name);
                }
                else {
                    stkTabIndexes.Push("");
                }

                stkTabIndexes.Push(tbcPluginDetails.SelectedTab.Name);

                this.OnTabChange(this, stkTabIndexes);
                this.m_blSettingTab = false;
            }
        }

        public void SetTabIndexes(Stack<string> stkTabIndexes) {

            this.m_blSettingTab = true;

            if (stkTabIndexes.Count > 0 && tbcPluginDetails.TabPages.ContainsKey(stkTabIndexes.Peek()) == true) {
                this.tbcPluginDetails.SelectedTab = tbcPluginDetails.TabPages[stkTabIndexes.Pop()];
            }

            if (stkTabIndexes.Count > 0 && lsvLoadedPlugins.Items.ContainsKey(stkTabIndexes.Peek()) == true) {
                lsvLoadedPlugins.Items[stkTabIndexes.Pop()].Selected = true;
            }

        }

        public void Write(DateTime dtLoggedTime, string strPluginConsoleOutput) {

            this.rtbScriptConsole.AppendText(String.Format("[{0}] {1}{2}", dtLoggedTime.ToString("HH:mm:ss ff"), strPluginConsoleOutput, Environment.NewLine));

            this.rtbScriptConsole.ScrollToCaret();

            while (this.rtbScriptConsole.Lines.Length > this.m_prcClient.Variables.GetVariable<int>("MAX_PLUGINCONSOLE_LINES", 75)) {
                this.rtbScriptConsole.Select(0, this.rtbScriptConsole.Lines[0].Length + 1);
                this.rtbScriptConsole.ReadOnly = false;
                this.rtbScriptConsole.SelectedText = String.Empty;
                this.rtbScriptConsole.ReadOnly = true;
            }
        }

        public ListViewItem IsLoadedPlugin(string strClassName) {
            ListViewItem lviLoadedPlugin = null;

            foreach (ListViewItem lviPlugin in this.lsvLoadedPlugins.Items) {
                if (lviPlugin.Tag != null && ((PluginDetails)lviPlugin.Tag).ClassName.CompareTo(strClassName) == 0) {
                    lviLoadedPlugin = lviPlugin;
                }
            }

            return lviLoadedPlugin;
        }

        private bool m_blSupressDisabledEvent = false;

        public void SetEnabledPlugins(List<string> lstClassNames) {

            ListViewItem lviPlugin = null;

            foreach (string strClassName in lstClassNames) {
                if ((lviPlugin = IsLoadedPlugin(strClassName)) != null) {
                    lviPlugin.Checked = true;
                }
            }

        }

        public void SetLoadedPlugins(List<string> lstClassNames) {

            ListViewItem lviPlugin = null;

            foreach (string strClassName in new List<string>(lstClassNames)) {

                PluginDetails spdDetails = this.GetPluginDetails(strClassName);

                if ((lviPlugin = IsLoadedPlugin(strClassName)) == null) {

                    ListViewItem lviNewItem = new ListViewItem(spdDetails.Name);
                    lviNewItem.Tag = spdDetails;
                    lviNewItem.Name = strClassName;
                    lviNewItem.ImageKey = "plugin_disabled.png";

                    //this.m_uscParent.ThrowEvent(this, uscEventsPanel.CapturableEvents.PluginLoaded, new string[] { strClassName });
                    this.m_blSupressDisabledEvent = true;

                    lsvLoadedPlugins.Items.Add(lviNewItem);


                }
                else {
                    lviPlugin.Text = spdDetails.Name;
                    lviPlugin.Tag = spdDetails;
                }

                if (this.PluginLoaded != null) {
                    this.PluginLoaded(spdDetails);
                }

                this.lsvLoadedPlugins_SelectedIndexChanged(this, null);

                //foreach (KeyValuePair<string, CPRoConLayerClient> kvpConnection in this.m_dicLayerClients) {
                //    kvpConnection.Value.OnPluginLoaded(spdDetails);
                //}

            }

            foreach (ColumnHeader column in this.lsvLoadedPlugins.Columns) {
                column.Width = -2;
            }

        }

        private Enum GenerateEnum(string enumName, string[] literals) {

            Enum returnEnum = null;

            try {

                if (this.m_dicGeneratedEnums.ContainsKey(enumName) == false) {

                    EnumBuilder enumBuilder = m_modBuilder.DefineEnum(enumName, TypeAttributes.Public, typeof(System.Int32));
                    //string[] al = { "en-US", "en-UK", "ar-SA", "da-DK", "French", "Cantonese" };
                    for (int i = 0; i < literals.Length; i++) {
                        // here al is an array list with a list of string values
                        if (literals[i].ToString().Length > 0) {
                            enumBuilder.DefineLiteral(literals[i].ToString(), i);
                        }
                    }

                    Type enumType = enumBuilder.CreateType();
                    returnEnum = (Enum)Activator.CreateInstance(enumType);

                    this.m_dicGeneratedEnums.Add(enumName, returnEnum);
                }
                else {
                    returnEnum = this.m_dicGeneratedEnums[enumName];
                }
            }
            catch (Exception e) {
                FrostbiteConnection.LogError("uscPluginPanel.GenerateEnum", enumName + " " + String.Join("|", literals), e);
            }

            return returnEnum;
        }

        private void SetPluginsVariables(string strClassName, string strPluginName, List<CPluginVariable> lstVariables) {

            if (lstVariables != null) {
                foreach (CPluginVariable cpvVariable in lstVariables) {

                    string strCategoryName = strPluginName;
                    string strVariableName = cpvVariable.Name;

                    string[] a_strVariable = cpvVariable.Name.Split(new char[] { '|' }, 2);
                    if (a_strVariable.Length == 2) {
                        strCategoryName = a_strVariable[0];
                        strVariableName = a_strVariable[1];
                    }

                    Enum generatedEnum = null;
                    Match isGeneratedEnum;
                    if ((isGeneratedEnum = Regex.Match(cpvVariable.Type, @"enum.(?<enumname>.*?)\((?<literals>.*)\)")).Success == true) {
                        if ((generatedEnum = this.GenerateEnum(isGeneratedEnum.Groups["enumname"].Value, isGeneratedEnum.Groups["literals"].Value.Split('|'))) != null) {
                            string variableValue = cpvVariable.Value;

                            if (Enum.IsDefined(generatedEnum.GetType(), variableValue) == false) {
                                string[] a_Names = Enum.GetNames(generatedEnum.GetType());

                                if (a_Names.Length > 0) {
                                    variableValue = a_Names[0];
                                }
                            }

                            if (Enum.IsDefined(generatedEnum.GetType(), variableValue) == true) {
                                if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                    this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, Enum.Parse(generatedEnum.GetType(), variableValue), generatedEnum.GetType(), false, true));
                                }
                                else {
                                    this.m_cscPluginVariables[cpvVariable.Name].Value = Enum.Parse(generatedEnum.GetType(), variableValue);
                                }
                            }

                        }

                    }
                    else {

                        switch (cpvVariable.Type) {
                            case "bool":
                                bool blTryBool;
                                if (bool.TryParse(cpvVariable.Value, out blTryBool) == true) {
                                    if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                        this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, blTryBool, typeof(bool), false, true));
                                    }
                                    else {
                                        this.m_cscPluginVariables[cpvVariable.Name].Value = blTryBool;
                                    }
                                }
                                break;
                            case "onoff":
                                if (Enum.IsDefined(typeof(enumBoolOnOff), cpvVariable.Value) == true) {

                                    if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                        this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, Enum.Parse(typeof(enumBoolOnOff), cpvVariable.Value), typeof(enumBoolOnOff), false, true));
                                    }
                                    else {
                                        this.m_cscPluginVariables[cpvVariable.Name].Value = Enum.Parse(typeof(enumBoolOnOff), cpvVariable.Value);
                                    }
                                }
                                break;
                            case "yesno":
                                if (Enum.IsDefined(typeof(enumBoolYesNo), cpvVariable.Value) == true) {
                                    if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                        this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, Enum.Parse(typeof(enumBoolYesNo), cpvVariable.Value), typeof(enumBoolYesNo), false, true));
                                    }
                                    else {
                                        this.m_cscPluginVariables[cpvVariable.Name].Value = Enum.Parse(typeof(enumBoolYesNo), cpvVariable.Value);
                                    }
                                }
                                break;
                            case "int":
                                int iTryInt;
                                if (int.TryParse(cpvVariable.Value, out iTryInt) == true) {
                                    if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                        this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, iTryInt, typeof(int), false, true));
                                    }
                                    else {
                                        this.m_cscPluginVariables[cpvVariable.Name].Value = iTryInt;
                                    }
                                }
                                break;
                            case "double":
                                double dblTryDouble;
                                if (double.TryParse(cpvVariable.Value, out dblTryDouble) == true) {
                                    if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                        this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, dblTryDouble, typeof(double), false, true));
                                    }
                                    else {
                                        this.m_cscPluginVariables[cpvVariable.Name].Value = dblTryDouble;
                                    }
                                }
                                break;
                            case "string":
                                if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                    this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, CPluginVariable.Decode(cpvVariable.Value), typeof(String), false, true));
                                }
                                else {

                                    this.m_cscPluginVariables[cpvVariable.Name].Value = cpvVariable.Value;
                                }
                                break;
                            case "multiline":
                                if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                    CustomProperty cptNewProperty = new CustomProperty(strVariableName, strCategoryName, strClassName, CPluginVariable.Decode(cpvVariable.Value), typeof(String), false, true);

                                    cptNewProperty.Attributes = new AttributeCollection( new EditorAttribute(typeof(System.ComponentModel.Design.MultilineStringEditor), typeof(System.Drawing.Design.UITypeEditor)), new TypeConverterAttribute(typeof(System.ComponentModel.Design.MultilineStringEditor)) );
                                    this.m_cscPluginVariables.Add(cptNewProperty);
                                }
                                else {
                                    this.m_cscPluginVariables[cpvVariable.Name].Value = cpvVariable.Value;
                                }
                                break;
                            case "stringarray":
                                if (this.m_cscPluginVariables.ContainsKey(cpvVariable.Name) == false) {
                                    this.m_cscPluginVariables.Add(new CustomProperty(strVariableName, strCategoryName, strClassName, CPluginVariable.DecodeStringArray(cpvVariable.Value), typeof(string[]), false, true));

                                    //this.m_cscPluginVariables.Add(new CustomProperty(cpvVariable.Name, strPluginName, strClassName, "Alaska", typeof(StatesList), false, true));
                                }
                                else {
                                    this.m_cscPluginVariables[cpvVariable.Name].Value = CPluginVariable.DecodeStringArray(cpvVariable.Value);
                                }

                                break;
                        }
                        //                }
                    }
                }
            }

            this.ppgScriptSettings.Refresh();
        }


        private void ppgScriptSettings_PropertyValueChanged(object s, PropertyValueChangedEventArgs e) {

            if (this.m_cscPluginVariables.ContainsKey(e.ChangedItem.Label) == true) {

                string strValue = e.ChangedItem.Value.ToString();

                if (this.m_cscPluginVariables[e.ChangedItem.Label].Type == typeof(bool)) {
                    strValue = strValue.ToLower();
                }
                //               else if (this.m_cscPluginVariables[e.ChangedItem.Label].Type == typeof(string)) {
                //                   strValue = strValue;
                //               }
                else if (this.m_cscPluginVariables[e.ChangedItem.Label].Type == typeof(string[])) {
                    strValue = CPluginVariable.EncodeStringArray((string[])e.ChangedItem.Value);

                }

                // TO DO: Set the script variable.
                //this.m_frmMainParent.SetSingleSv(this.m_cscPluginVariables[e.ChangedItem.Label].ClassName, e.ChangedItem.Label, strValue);

                this.SetPluginVariable(this.m_cscPluginVariables[e.ChangedItem.Label].ClassName, e.ChangedItem.Label, strValue);

                //this.lsvLoadedPlugins_SelectedIndexChanged(this, null);
                this.RefreshSelectedPlugin();

                if (this.m_cscPluginVariables.ContainsKey(e.ChangedItem.Label) == true) {

                    PluginDetails spdUpdatedDetails = this.GetPluginDetails(this.m_cscPluginVariables[e.ChangedItem.Label].ClassName);
                    if (this.PluginVariablesAltered != null) {
                        this.PluginVariablesAltered(spdUpdatedDetails);
                    }

                }

                //foreach (KeyValuePair<string, CPRoConLayerClient> kvpConnection in this.m_dicLayerClients) {
                //    kvpConnection.Value.OnPluginVariablesAltered(spdUpdatedDetails);
                //}

            }
        }

        public void RefreshPlugin() {
            //this.lsvLoadedPlugins_SelectedIndexChanged(this, null);
            this.RefreshSelectedPlugin();
        }

        private void RefreshSelectedPlugin() {
            if (this.lsvLoadedPlugins.SelectedItems.Count > 0) {
                //this.tbcPluginDetails.Enabled = true;

                PluginDetails spdDetails = this.GetPluginDetails(((PluginDetails)lsvLoadedPlugins.SelectedItems[0].Tag).ClassName);

                this.lsvLoadedPlugins.SelectedItems[0].Tag = spdDetails;

                //this.txtPluginName.Text = spdDetails.m_strName;
                //this.txtPluginAuthor.Text = spdDetails.m_strAuthor;
                //this.txtPluginVersion.Text = spdDetails.m_strVersion;
                //this.txtPluginDescription.Text = spdDetails.m_strDescription;

                HtmlDocument document = this.webDescription.Document.OpenNew(true);

                string html = String.Format(@"<html><head><style>{0}</style></head><body><h1>{1} - {2}</h1>{3}: <a href=""http://{4}"" target=""_blank"">{5}</a><br><br>{6}</body></html>", this.m_cssDescription, spdDetails.Name, spdDetails.Version, this.m_clocLanguage.GetLocalized("uscPluginPanel.tabPluginDetails.lblPluginAuthor"), spdDetails.Website, spdDetails.Author, spdDetails.Description != null ? spdDetails.Description : String.Empty); // .Replace(Environment.NewLine, @"<br>")
                document.Write(html);

                //this.lklPluginWebsite.Text = spdDetails.m_strWebsite;
                //this.lklPluginWebsite.Tag = spdDetails.m_strWebsite;
                //this.lklPluginWebsite.LinkArea = new LinkArea(0, spdDetails.m_strWebsite.Length);

                this.m_cscPluginVariables.Clear();
                this.SetPluginsVariables(spdDetails.ClassName, spdDetails.Name, spdDetails.DisplayPluginVariables);
            }
            else if (this.lsvLoadedPlugins.FocusedItem != null) {
                //this.tbcPluginDetails.Enabled = false;

                //this.txtPluginName.Text = String.Empty;
                //this.txtPluginAuthor.Text = String.Empty;
                //this.txtPluginVersion.Text = String.Empty;
                //this.txtPluginDescription.Text = String.Empty;

                //this.lklPluginWebsite.Text = String.Empty;

                this.m_cscPluginVariables.Clear();
                this.ppgScriptSettings.Refresh();
            }
        }

        private void lsvLoadedPlugins_SelectedIndexChanged(object sender, EventArgs e) {

            // Start up optimization, takes 100ms at startup to assign this is ctor so now
            // it's set here when the user first selects a plugin to display.  They won't notice it at all there.
            if (this.ppgScriptSettings.SelectedObject == null ) {
                this.ppgScriptSettings.SelectedObject = this.m_cscPluginVariables;
            }

            //if (this.m_prcClient != null && this.m_prcClient.Plugins != null) {
                this.RefreshSelectedPlugin();
            //}
            *//*
            if (this.lsvLoadedPlugins.SelectedItems.Count > 0) {
                //this.tbcPluginDetails.Enabled = true;

                SPluginDetails spdDetails = this.GetPluginDetails(((SPluginDetails)lsvLoadedPlugins.SelectedItems[0].Tag).m_strClassName);

                this.lsvLoadedPlugins.SelectedItems[0].Tag = spdDetails;

                this.txtPluginName.Text = spdDetails.m_strName;
                this.txtPluginAuthor.Text = spdDetails.m_strAuthor;
                this.txtPluginVersion.Text = spdDetails.m_strVersion;
                this.txtPluginDescription.Text = spdDetails.m_strDescription;

                this.lklPluginWebsite.Text = spdDetails.m_strWebsite;
                this.lklPluginWebsite.Tag = spdDetails.m_strWebsite;
                this.lklPluginWebsite.LinkArea = new LinkArea(0, spdDetails.m_strWebsite.Length);

                this.m_cscPluginVariables.Clear();
                this.SetPluginsVariables(spdDetails.m_strClassName, spdDetails.m_strName, spdDetails.m_lstDisplayPluginVariables);
            }
            else if (this.lsvLoadedPlugins.FocusedItem != null) {
                //this.tbcPluginDetails.Enabled = false;

                this.txtPluginName.Text = String.Empty;
                this.txtPluginAuthor.Text = String.Empty;
                this.txtPluginVersion.Text = String.Empty;
                this.txtPluginDescription.Text = String.Empty;

                this.lklPluginWebsite.Text = String.Empty;

                this.m_cscPluginVariables.Clear();
                this.ppgScriptSettings.Refresh();
            }
            *//*
            if (this.lsvLoadedPlugins.FocusedItem != null) {
                this.m_blSettingTab = false;
                this.tbcPluginDetails_SelectedIndexChanged(null, null);
            }
        }

        //private void lklPluginWebsite_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

        //    if (this.lklPluginWebsite.Tag != null) {

        //        string strLink = this.lklPluginWebsite.Tag.ToString();

        //        if (Regex.Match(strLink, "^http://.*?$").Success == false) {
        //            strLink = "http://" + strLink;
        //        }

        //        System.Diagnostics.Process.Start(strLink);
        //    }
        //}

        private void lsvLoadedPlugins_ItemChecked(object sender, ItemCheckedEventArgs e) {
            if (e.Item.Tag != null && this.m_blSupressDisabledEvent == false) {
                if (e.Item.Checked == true) {

                    if (this.PluginEnabled != null) {
                        this.PluginEnabled(((PluginDetails)e.Item.Tag).ClassName, true);
                        e.Item.ImageKey = "plugin.png";
                    }

                    *//*
                    this.m_prcConnection.EnablePlugin(((SPluginDetails)e.Item.Tag).m_strClassName);

                    foreach (KeyValuePair<string, CPRoConLayerClient> kvpConnection in this.m_dicLayerClients) {
                        kvpConnection.Value.OnPluginEnabled(((SPluginDetails)e.Item.Tag).m_strClassName, true);
                    }
                    *//*
                }
                else {

                    if (this.PluginEnabled != null) {
                        this.PluginEnabled(((PluginDetails)e.Item.Tag).ClassName, false);

                        e.Item.ImageKey = "plugin_disabled.png";
                    }

                    *//*
                    this.m_prcConnection.DisablePlugin(((SPluginDetails)e.Item.Tag).m_strClassName);

                    foreach (KeyValuePair<string, CPRoConLayerClient> kvpConnection in this.m_dicLayerClients) {
                        kvpConnection.Value.OnPluginEnabled(((SPluginDetails)e.Item.Tag).m_strClassName, false);
                    }
                    *//*
                }
            }

            this.m_blSupressDisabledEvent = false;
        }

        private void lnkReloadPlugins_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {

            if (this.ReloadPlugins != null) {
                this.rtbScriptConsole.Text = String.Empty;

                this.ReloadPlugins();
            }

            //this.m_prcConnection.CompilePlugins();
        }

        private void lnkMorePlugins_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e) {
            System.Diagnostics.Process.Start("http://phogue.net/procon/moreplugins.php");
        }

        private void uscPluginPanel_Resize(object sender, EventArgs e) {
            Rectangle tabBounds = this.tbcPluginDetails.Bounds;
            tabBounds.Width = this.Bounds.Width - tabBounds.X - 5;
            this.tbcPluginDetails.SetBounds(tabBounds.X, tabBounds.Y, tabBounds.Width, tabBounds.Height);

            this.lsvLoadedPlugins.Height = this.spltPlugins.Panel1.Height - 50;

            try {
                this.spltPlugins.SplitterDistance = (int)(this.spltPlugins.Bounds.Height * 0.8);
            }
            catch (Exception) { }
        }

    }*/

    /*partial class uscPluginPanel {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Component Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.webDescription = new System.Windows.Forms.WebBrowser();
            this.ppgScriptSettings = new System.Windows.Forms.PropertyGrid();
            this.lblLoadedPlugins = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.rtbScriptConsole = new PRoCon.CodRichTextBox();
            this.tabPluginDetails = new System.Windows.Forms.TabPage();
            this.spltPlugins = new System.Windows.Forms.SplitContainer();
            this.lnkReloadPlugins = new System.Windows.Forms.LinkLabel();
            this.lnkMorePlugins = new System.Windows.Forms.LinkLabel();
            this.lsvLoadedPlugins = new PRoCon.Controls.ControlsEx.ListViewNF();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.tbcPluginDetails = new System.Windows.Forms.TabControl();
            this.tabPluginSettings = new System.Windows.Forms.TabPage();
            this.panel1.SuspendLayout();
            this.tabPluginDetails.SuspendLayout();
            this.spltPlugins.Panel1.SuspendLayout();
            this.spltPlugins.Panel2.SuspendLayout();
            this.spltPlugins.SuspendLayout();
            this.tbcPluginDetails.SuspendLayout();
            this.tabPluginSettings.SuspendLayout();
            this.SuspendLayout();
            //
            // webDescription
            //
            this.webDescription.Dock = System.Windows.Forms.DockStyle.Fill;
            this.webDescription.Location = new System.Drawing.Point(3, 3);
            this.webDescription.MinimumSize = new System.Drawing.Size(20, 20);
            this.webDescription.Name = "webDescription";
            this.webDescription.Size = new System.Drawing.Size(393, 232);
            this.webDescription.TabIndex = 0;
            //
            // ppgScriptSettings
            //
            this.ppgScriptSettings.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ppgScriptSettings.HelpVisible = false;
            this.ppgScriptSettings.Location = new System.Drawing.Point(3, 3);
            this.ppgScriptSettings.Name = "ppgScriptSettings";
            this.ppgScriptSettings.PropertySort = System.Windows.Forms.PropertySort.Categorized;
            this.ppgScriptSettings.Size = new System.Drawing.Size(393, 235);
            this.ppgScriptSettings.TabIndex = 3;
            this.ppgScriptSettings.ToolbarVisible = false;
            this.ppgScriptSettings.PropertyValueChanged += new System.Windows.Forms.PropertyValueChangedEventHandler(this.ppgScriptSettings_PropertyValueChanged);
            //
            // lblLoadedPlugins
            //
            this.lblLoadedPlugins.AutoSize = true;
            this.lblLoadedPlugins.Location = new System.Drawing.Point(0, 8);
            this.lblLoadedPlugins.Name = "lblLoadedPlugins";
            this.lblLoadedPlugins.Size = new System.Drawing.Size(88, 15);
            this.lblLoadedPlugins.TabIndex = 17;
            this.lblLoadedPlugins.Text = "Loaded plugins";
            //
            // panel1
            //
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.rtbScriptConsole);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(659, 238);
            this.panel1.TabIndex = 14;
            //
            // rtbScriptConsole
            //
            this.rtbScriptConsole.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rtbScriptConsole.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbScriptConsole.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.rtbScriptConsole.Location = new System.Drawing.Point(0, 0);
            this.rtbScriptConsole.Name = "rtbScriptConsole";
            this.rtbScriptConsole.ReadOnly = true;
            this.rtbScriptConsole.Size = new System.Drawing.Size(657, 236);
            this.rtbScriptConsole.TabIndex = 0;
            this.rtbScriptConsole.Text = "";
            //
            // tabPluginDetails
            //
            this.tabPluginDetails.Controls.Add(this.webDescription);
            this.tabPluginDetails.Location = new System.Drawing.Point(4, 24);
            this.tabPluginDetails.Name = "tabPluginDetails";
            this.tabPluginDetails.Padding = new System.Windows.Forms.Padding(3);
            this.tabPluginDetails.Size = new System.Drawing.Size(399, 238);
            this.tabPluginDetails.TabIndex = 2;
            this.tabPluginDetails.Text = "Details";
            this.tabPluginDetails.UseVisualStyleBackColor = true;
            //
            // spltPlugins
            //
            this.spltPlugins.Dock = System.Windows.Forms.DockStyle.Fill;
            this.spltPlugins.FixedPanel = System.Windows.Forms.FixedPanel.Panel2;
            this.spltPlugins.Location = new System.Drawing.Point(0, 0);
            this.spltPlugins.Name = "spltPlugins";
            this.spltPlugins.Orientation = System.Windows.Forms.Orientation.Horizontal;
            //
            // spltPlugins.Panel1
            //
            this.spltPlugins.Panel1.Controls.Add(this.lnkReloadPlugins);
            this.spltPlugins.Panel1.Controls.Add(this.lnkMorePlugins);
            this.spltPlugins.Panel1.Controls.Add(this.lsvLoadedPlugins);
            this.spltPlugins.Panel1.Controls.Add(this.tbcPluginDetails);
            this.spltPlugins.Panel1.Controls.Add(this.lblLoadedPlugins);
            this.spltPlugins.Panel1MinSize = 250;
            //
            // spltPlugins.Panel2
            //
            this.spltPlugins.Panel2.Controls.Add(this.panel1);
            this.spltPlugins.Panel2.Padding = new System.Windows.Forms.Padding(0, 0, 5, 0);
            this.spltPlugins.Size = new System.Drawing.Size(664, 520);
            this.spltPlugins.SplitterDistance = 277;
            this.spltPlugins.SplitterWidth = 5;
            this.spltPlugins.TabIndex = 18;
            //
            // lnkReloadPlugins
            //
            this.lnkReloadPlugins.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkReloadPlugins.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.lnkReloadPlugins.AutoSize = true;
            this.lnkReloadPlugins.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkReloadPlugins.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkReloadPlugins.Location = new System.Drawing.Point(3, 257);
            this.lnkReloadPlugins.Name = "lnkReloadPlugins";
            this.lnkReloadPlugins.Size = new System.Drawing.Size(85, 15);
            this.lnkReloadPlugins.TabIndex = 19;
            this.lnkReloadPlugins.TabStop = true;
            this.lnkReloadPlugins.Text = "Reload plugins";
            this.lnkReloadPlugins.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkReloadPlugins_LinkClicked);
            //
            // lnkMorePlugins
            //
            this.lnkMorePlugins.ActiveLinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkMorePlugins.AutoSize = true;
            this.lnkMorePlugins.LinkBehavior = System.Windows.Forms.LinkBehavior.HoverUnderline;
            this.lnkMorePlugins.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(100)))), ((int)(((byte)(220)))));
            this.lnkMorePlugins.Location = new System.Drawing.Point(163, 8);
            this.lnkMorePlugins.Name = "lnkMorePlugins";
            this.lnkMorePlugins.Size = new System.Drawing.Size(85, 15);
            this.lnkMorePlugins.TabIndex = 21;
            this.lnkMorePlugins.TabStop = true;
            this.lnkMorePlugins.Text = "Reload plugins";
            this.lnkMorePlugins.TextAlign = System.Drawing.ContentAlignment.TopRight;
            this.lnkMorePlugins.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.lnkMorePlugins_LinkClicked);
            //
            // lsvLoadedPlugins
            //
            this.lsvLoadedPlugins.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)));
            this.lsvLoadedPlugins.CheckBoxes = true;
            this.lsvLoadedPlugins.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1});
            this.lsvLoadedPlugins.FullRowSelect = true;
            this.lsvLoadedPlugins.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.None;
            this.lsvLoadedPlugins.HideSelection = false;
            this.lsvLoadedPlugins.Location = new System.Drawing.Point(3, 27);
            this.lsvLoadedPlugins.MultiSelect = false;
            this.lsvLoadedPlugins.Name = "lsvLoadedPlugins";
            this.lsvLoadedPlugins.Size = new System.Drawing.Size(245, 226);
            this.lsvLoadedPlugins.TabIndex = 20;
            this.lsvLoadedPlugins.UseCompatibleStateImageBehavior = false;
            this.lsvLoadedPlugins.View = System.Windows.Forms.View.Details;
            this.lsvLoadedPlugins.ItemChecked += new System.Windows.Forms.ItemCheckedEventHandler(this.lsvLoadedPlugins_ItemChecked);
            this.lsvLoadedPlugins.SelectedIndexChanged += new System.EventHandler(this.lsvLoadedPlugins_SelectedIndexChanged);
            //
            // columnHeader1
            //
            this.columnHeader1.Width = 186;
            //
            // tbcPluginDetails
            //
            this.tbcPluginDetails.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tbcPluginDetails.Controls.Add(this.tabPluginDetails);
            this.tbcPluginDetails.Controls.Add(this.tabPluginSettings);
            this.tbcPluginDetails.Location = new System.Drawing.Point(254, 8);
            this.tbcPluginDetails.Name = "tbcPluginDetails";
            this.tbcPluginDetails.SelectedIndex = 0;
            this.tbcPluginDetails.Size = new System.Drawing.Size(407, 266);
            this.tbcPluginDetails.TabIndex = 18;
            this.tbcPluginDetails.SelectedIndexChanged += new System.EventHandler(this.tbcPluginDetails_SelectedIndexChanged);
            //
            // tabPluginSettings
            //
            this.tabPluginSettings.Controls.Add(this.ppgScriptSettings);
            this.tabPluginSettings.Location = new System.Drawing.Point(4, 22);
            this.tabPluginSettings.Name = "tabPluginSettings";
            this.tabPluginSettings.Padding = new System.Windows.Forms.Padding(3);
            this.tabPluginSettings.Size = new System.Drawing.Size(399, 241);
            this.tabPluginSettings.TabIndex = 1;
            this.tabPluginSettings.Text = "Plugin Settings";
            this.tabPluginSettings.UseVisualStyleBackColor = true;
            //
            // uscPluginPanel
            //
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.spltPlugins);
            this.Font = new System.Drawing.Font("Segoe UI", 9F);
            this.Name = "uscPluginPanel";
            this.Size = new System.Drawing.Size(664, 520);
            this.Resize += new System.EventHandler(this.uscPluginPanel_Resize);
            this.panel1.ResumeLayout(false);
            this.tabPluginDetails.ResumeLayout(false);
            this.spltPlugins.Panel1.ResumeLayout(false);
            this.spltPlugins.Panel1.PerformLayout();
            this.spltPlugins.Panel2.ResumeLayout(false);
            this.spltPlugins.ResumeLayout(false);
            this.tbcPluginDetails.ResumeLayout(false);
            this.tabPluginSettings.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.WebBrowser webDescription;
        private CodRichTextBox rtbScriptConsole;
        private System.Windows.Forms.PropertyGrid ppgScriptSettings;
        private Controls.ControlsEx.ListViewNF lsvLoadedPlugins;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.Label lblLoadedPlugins;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TabPage tabPluginDetails;
        private System.Windows.Forms.SplitContainer spltPlugins;
        private System.Windows.Forms.LinkLabel lnkMorePlugins;
        private System.Windows.Forms.LinkLabel lnkReloadPlugins;
        private System.Windows.Forms.TabControl tbcPluginDetails;
        private System.Windows.Forms.TabPage tabPluginSettings;

    }*/
}
