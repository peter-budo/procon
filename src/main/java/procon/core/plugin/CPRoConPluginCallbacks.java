package procon.core.plugin;

public class CPRoConPluginCallbacks {

    /*public class CPRoConPluginCallbacks : MarshalByRefObject {

        private CPRoConMarshalByRefObject.ExecuteCommandHandler m_delExecuteCommand;
        private CPRoConMarshalByRefObject.GetAccountPrivilegesHandler m_delGetAccountPrivileges;
        private CPRoConMarshalByRefObject.GetVariableHandler m_delGetVariable;
        private CPRoConMarshalByRefObject.GetVariableHandler m_delGetSvVariable;
        private CPRoConMarshalByRefObject.GetMapDefinesHandler m_delGetMapDefines;
        private CPRoConMarshalByRefObject.TryGetLocalizedHandler m_delTryGetLocalized;

        private CPRoConMarshalByRefObject.RegisterCommandHandler m_delRegisterCommand;
        private CPRoConMarshalByRefObject.UnregisterCommandHandler m_delUnregisterCommand;
        private CPRoConMarshalByRefObject.GetRegisteredCommandsHandler m_delGetRegisteredCommands;

        private CPRoConMarshalByRefObject.GetWeaponDefinesHandler m_delGetWeaponDefines;
        private CPRoConMarshalByRefObject.GetSpecializationDefinesHandler m_delGetSpecializationDefines;

        private CPRoConMarshalByRefObject.GetLoggedInAccountUsernamesHandler m_delGetLoggedInAccountUsernames;
        private CPRoConMarshalByRefObject.RegisterEventsHandler m_delRegisterEvents;

        public override object InitializeLifetimeService() {
            return null;
        }

        public CPRoConPluginCallbacks(CPRoConMarshalByRefObject.ExecuteCommandHandler delExecuteCommandCallback,
                                      CPRoConMarshalByRefObject.GetAccountPrivilegesHandler delGetAccountPrivileges,
                                      CPRoConMarshalByRefObject.GetVariableHandler delGetVariable,
                                      CPRoConMarshalByRefObject.GetVariableHandler delGetSvVariable,
                                      CPRoConMarshalByRefObject.GetMapDefinesHandler delGetMapDefines,
                                      CPRoConMarshalByRefObject.TryGetLocalizedHandler delTryGetLocalized,
                                      CPRoConMarshalByRefObject.RegisterCommandHandler delRegisterCommand,
                                      CPRoConMarshalByRefObject.UnregisterCommandHandler delUnregisterCommand,
                                      CPRoConMarshalByRefObject.GetRegisteredCommandsHandler delGetRegisteredCommands,
                                      CPRoConMarshalByRefObject.GetWeaponDefinesHandler delGetWeaponDefines,
                                      CPRoConMarshalByRefObject.GetSpecializationDefinesHandler delGetSpecializationDefines,
                                      CPRoConMarshalByRefObject.GetLoggedInAccountUsernamesHandler delGetLoggedInAccountUsernames,
                                      CPRoConMarshalByRefObject.RegisterEventsHandler delRegisterEvents ) {
            this.m_delExecuteCommand = delExecuteCommandCallback;
            this.m_delGetAccountPrivileges = delGetAccountPrivileges;
            this.m_delGetVariable = delGetVariable;
            this.m_delGetSvVariable = delGetSvVariable;
            this.m_delGetMapDefines = delGetMapDefines;
            this.m_delTryGetLocalized = delTryGetLocalized;

            this.m_delRegisterCommand = delRegisterCommand;
            this.m_delUnregisterCommand = delUnregisterCommand;
            this.m_delGetRegisteredCommands = delGetRegisteredCommands;

            this.m_delGetWeaponDefines = delGetWeaponDefines;
            this.m_delGetSpecializationDefines = delGetSpecializationDefines;

            this.m_delGetLoggedInAccountUsernames = delGetLoggedInAccountUsernames;
            this.m_delRegisterEvents = delRegisterEvents;
        }

        public WeaponDictionary GetWeaponDefines_Callback() {
            return this.m_delGetWeaponDefines();
        }

        public SpecializationDictionary GetSpecializationDefines_Callback() {
            return this.m_delGetSpecializationDefines();
        }

        public void ExecuteCommand_Callback(List<string> lstCommand) {
            this.m_delExecuteCommand(lstCommand);
        }

        public string GetVariable_Callback(string strVariable) {
            return this.m_delGetVariable(strVariable);
        }

        public string GetSvVariable_Callback(string strSvVariable) {
            return this.m_delGetSvVariable(strSvVariable);
        }

        public CPrivileges GetAccountPrivileges_Callback(string strAccountName) {
            return this.m_delGetAccountPrivileges(strAccountName);
        }

        public List<CMap> GetMapDefines_Callback() {
            return this.m_delGetMapDefines();
        }

        public bool TryGetLocalized_Callback(string strLanguageCode, out string strLocalizedText, string strVariable, string[] a_strArguements) {
            return this.m_delTryGetLocalized(strLanguageCode, out strLocalizedText, strVariable, a_strArguements);
        }

        public void RegisterCommand_Callback(MatchCommand mtcCommand) {
            this.m_delRegisterCommand(mtcCommand);
        }

        public void UnregisterCommand_Callback(MatchCommand mtcCommand) {
            this.m_delUnregisterCommand(mtcCommand);
        }

        public List<MatchCommand> GetRegisteredCommands_Callback() {
            return this.m_delGetRegisteredCommands();
        }

        public List<string> GetLoggedInAccountUsernames_Callback() {
            return this.m_delGetLoggedInAccountUsernames();
        }

        public void RegisterEvents_Callback(string className, List<string> events) {
            this.m_delRegisterEvents(className, events);
        }
    }*/
}
