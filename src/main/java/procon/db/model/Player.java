package procon.db.model;

public class Player {
    /*/// <summary>
    /// the domain object represents a player
    /// </summary>
    [Class(NameType = typeof(Player), Table = "player")]
    public class Player
    {
        [Id(Name="Id", Column = "id", TypeType = typeof(long), UnsavedValueObject = 0)]
        [Generator(1, Class = "native")]
        public virtual long Id { get; set; }

        [Property(Column = "name")]
        public virtual string Name { get; set; }

        [Property(Column = "guid")]
        public virtual string Guid { get; set; }

        [Property(Column = "clan_tag")]
        public virtual string ClanTag { get; set; }

        [ManyToOne(ClassType = typeof(Country), Column = "country_id")]
        public virtual Country Country { get; set; }

        public override bool Equals(object obj)
        {
            var entity = obj as Player;
            if (entity != null)
            {
                return new EqualsBuilder()
                    .Append(this.Name, entity.Name)
                    .Append(this.ClanTag, entity.ClanTag)
                    .Append(this.Country, entity.Country)
                    .Append(this.Guid, entity.Guid)
                    .IsEqual;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return new HashCodeBuilder()
                .Append(this.Name)
                .Append(this.ClanTag)
                .Append(this.Country)
                .Append(this.Guid)
                .ToHashCode();
        }
    }*/
}
