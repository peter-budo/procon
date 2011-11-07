package procon.core.plugin;

public class Plugin {
    /*public class Plugin {

        public string ClassName { get; private set; }

        public bool IsLoaded { get; set; }
        public bool IsEnabled { get; set; }
        public IPRoConPluginInterface Type { get; private set; }

        public List<string> RegisteredEvents { get; set; }

        public Dictionary<string, string> CacheFailCompiledPluginVariables { get; private set; }

        // Failed plugin
        public Plugin(string className) {
            this.ClassName = className;
            this.CacheFailCompiledPluginVariables = new Dictionary<string, string>();
            this.IsLoaded = false;
            this.IsEnabled = false;
        }

        // Loaded plugin
        public Plugin(string className, IPRoConPluginInterface type) {
            this.ClassName = className;
            this.Type = type;
            this.IsLoaded = true;
            this.IsEnabled = false;

            this.RegisteredEvents = new List<string>();
        }

        public void ConditionalInvoke(string methodName, params object[] parameters) {
            // if no events have been registered (all events fired) or the event has been registered.
            if (this.RegisteredEvents != null && (this.RegisteredEvents.Count == 0 || this.RegisteredEvents.Contains(methodName) == true)) {
                this.Type.Invoke(methodName, parameters);
            }
        }
    }*/
}
