package procon.db.model;

public class PlayerPlaytime {
    /*[Class(NameType = typeof(PlayerPlaytime), Table = "player_playtime")]
    public class PlayerPlaytime
    {
        [Id(Column = "id", TypeType = typeof(long), Name = "Id")]
        [Generator(1, Class = "native")]
        public virtual long Id { get; set; }

        [ManyToOne(ClassType = typeof(Player), Column = "player_id")]
        public virtual Player Player { get; set; }

        [ManyToOne(ClassType = typeof(Server), Column = "server_id")]
        public virtual Server Server { get; set; }

        [Property(Column = "starttime")]
        public virtual DateTime Start { get; set; }

        [Property(Column = "endtime")]
        public virtual DateTime Quit { get; set; }
    }*/
}
