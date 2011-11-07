package procon.db.util;

public class SQLiteDatabaseSettings {
    /*public class SQLiteDatabaseSettings : DatabaseSettings
    {
        private string fileName;

        public new string FileName
        {
            get
            {
                if (string.IsNullOrEmpty(this.fileName))
                {
                    return ":memory:";
                }
                return this.fileName;
            }
            set { this.fileName = value; }
        }

        public override string DriverClass
        {
            get { return typeof(SQLite20Driver).FullName; }
        }

        public override string Dialect
        {
            get { return typeof(SQLiteDialect).FullName; }
        }

        public override string ConnectionString
        {
            get
            {
                var connectionStringBuilder = new SQLiteConnectionStringBuilder();
                connectionStringBuilder.DataSource = this.FileName;
                connectionStringBuilder.Password = this.Password;
                return connectionStringBuilder.ToString();
            }
        }
    }*/
}
