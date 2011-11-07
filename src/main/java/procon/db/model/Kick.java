package procon.db.model;

public class Kick {
    /*[Class(NameType = typeof(Kick), Table = "player_kick")]
    public class Kick
    {
        [Id(Name = "Id", TypeType = typeof(long), Column = "id")]
        [Generator(Class = "native")]
        public virtual long Id { get; set; }

        [ManyToOne(ClassType = typeof(Player), Column = "player_id")]
        public virtual Player Player { get; set; }

        [ManyToOne(ClassType = typeof(Server), Column = "server_id")]
        public virtual Server Server { get; set; }

        [Property(Column = "kicked")]
        public virtual DateTime KickedTimestamp { get; set; }

        [Property(Column = "reason")]
        public virtual string Reason { get; set; }
    }*/
}
