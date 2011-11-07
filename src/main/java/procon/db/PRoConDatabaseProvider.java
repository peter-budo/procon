package procon.db;

public class PRoConDatabaseProvider {
    /*/// <summary>
    /// this class is the entry class for the basic procon database connection
    /// </summary>
    public class PRoConDatabaseProvider
    {
        private ISessionFactory activeSessionFactory;
        private Configuration databaseConfiguration;

        #region Properties

        /// <summary>
        /// gets the ADO.NET connection
        /// </summary>
        public IDbConnection Connection
        {
            get { return this.activeSessionFactory.OpenSession().Connection; }
        }

        #endregion

        public PRoConDatabaseProvider (DatabaseSettings settings)
        {
            this.databaseConfiguration = new Configuration();
            this.databaseConfiguration.Properties.Add("connection.provider_class",
                                                      typeof (DriverConnectionProvider).FullName);
            this.databaseConfiguration.Properties.Add("connection.driver_class", settings.DriverClass);
            this.databaseConfiguration.Properties.Add("connection.connection_string", settings.ConnectionString);
            this.databaseConfiguration.Properties.Add("dialect", settings.Dialect);
            this.databaseConfiguration.Properties.Add("proxyfactory.factory_class",
                                                      typeof (ProxyFactoryFactory).AssemblyQualifiedName);

            this.databaseConfiguration.AddInputStream(
                HbmSerializer.Default.Serialize(typeof (Player).Assembly));

            this.activeSessionFactory = this.databaseConfiguration.BuildSessionFactory();
        }

        public Configuration Configuration
        {
            get { return this.databaseConfiguration; }
        }

        public ISession Session
        {
            get { return this.activeSessionFactory.OpenSession(); }
        }

        public void EnableDebug ()
        {
            this.databaseConfiguration.Properties.Add("show_sql", "true");
            this.activeSessionFactory = this.databaseConfiguration.BuildSessionFactory();
        }

        public IPRoConDatabaseAccess BuildDataAccess()
        {
            return new PRoConDatabaseAccess(this.activeSessionFactory.OpenSession());
        }
    }*/
}
