package procon.core.plugin;

public class PluginDictionary {

    /*public class PluginDictionary : KeyedCollection<string, Plugin> {

        public List<string> EnabledClassNames {
            get {
                List<string> enabledClasses = new List<string>();

                foreach (Plugin plugin in this) {
                    if (plugin.IsEnabled == true) {
                        enabledClasses.Add(plugin.ClassName);
                    }
                }

                return enabledClasses;
            }
        }

        public List<string> LoadedClassNames {
            get {
                List<string> loadedClasses = new List<string>();

                foreach (Plugin plugin in this) {
                    if (plugin.IsLoaded == true) {
                        loadedClasses.Add(plugin.ClassName);
                    }
                }

                return loadedClasses;
            }
        }

        public bool IsEnabled(string className) {

            bool isEnabled = false;

            if (this.Contains(className) == true) {
                isEnabled = this[className].IsEnabled;
            }

            return isEnabled;
        }

        public bool IsLoaded(string className) {

            bool isLoaded = false;

            if (this.Contains(className) == true) {
                isLoaded = this[className].IsLoaded;
            }

            return isLoaded;
        }

        protected override string GetKeyForItem(Plugin item) {
            return item.ClassName;
        }

        public void AddLoadedPlugin(string className, IPRoConPluginInterface type) {
            if (this.Contains(className) == false) {
                this.Add(new Plugin(className, type));
            }
            else {
                this.SetItem(this.IndexOf(this[className]), new Plugin(className, type));
            }
        }

        public void SetCachedPluginVariable(string className, string variable, string value) {

            if (this.Contains(className) == false) {
                this.Add(new Plugin(className));
            }

            if (this[className].CacheFailCompiledPluginVariables.ContainsKey(variable) == true) {
                this[className].CacheFailCompiledPluginVariables[variable] = value;
            }
            else {
                this[className].CacheFailCompiledPluginVariables.Add(variable, value);
            }
        }

    }*/
}
