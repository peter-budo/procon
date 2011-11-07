package procon.db.model;

public class Server {
    /*[Class(NameType =typeof(Server), Table = "server")]
    public class Server
    {
        [Id(Name = "Id", Column = "id")]
        [Generator(1, Class = "native")]
        public virtual long Id { get; set; }

        /// <summary>
        /// the server address with ip-address and name
        /// </summary>
        [Property(Column = "address", UniqueKey = "uq_server_01")]
        public virtual string Address { get; set; }

        /// <summary>
        /// the current visible name
        /// </summary>
        [Property(Column = "name")]
        public virtual string Name { get; set; }

        [Property(Column = "version")]
        public virtual string Version { get; set; }

        public override bool Equals(object obj)
        {
            var entity = obj as Server;
            if (entity != null)
            {
                return new EqualsBuilder()
                    .Append(this.Name, entity.Name)
                    .Append(this.Address, entity.Address)
                    .IsEqual;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return new HashCodeBuilder()
                .Append(this.Address)
                .Append(this.Name)
                .ToHashCode();
        }
    }*/
}
