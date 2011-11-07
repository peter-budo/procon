package procon.core.plugin;

public class CPRoConPluginLoaderFactory {
    /*// Factory class to create objects exposing IPRoConPluginInterface
    public class CPRoConPluginLoaderFactory : MarshalByRefObject {

        public override object InitializeLifetimeService() {
            return null;
        }

        public CPRoConPluginLoaderFactory() { }

        public IPRoConPluginInterface Create(string strAssemblyFile, string strTypeName, object[] a_objConstructArgs) {
            return (IPRoConPluginInterface)Activator.CreateInstanceFrom(strAssemblyFile, strTypeName, false, BindingFlags.Instance | BindingFlags.Public | BindingFlags.CreateInstance, null, a_objConstructArgs, null, null, null).Unwrap();
        }
    }*/
}
