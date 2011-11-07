package procon.db.model;

public class Kill {
    /*[Class (NameType = typeof (Kill), Table = "kill")]
    public class Kill
    {
        [Id (Name = "Id", TypeType = typeof (long), Column = "id")]
        [Generator (1, Class = "native")]
        public virtual long Id { get; set; }

        [ManyToOne(ClassType = typeof(Server), Column = "server_id")]
        public virtual Server Server { get; set; }

        [Property(Column = "kill_timestamp")]
        public virtual DateTime Timestamp { get; set; }

        [ManyToOne(ClassType = typeof(Player), Column = "killer_player_id")]
        public virtual Player Killer { get; set; }

        [ManyToOne(ClassType = typeof(Player), Column = "killed_player_id")]
        public virtual Player PlayerKilled { get; set; }

        [Property(Column = "weapon")]
        public virtual string Weapon { get; set; }
    }*/
}
