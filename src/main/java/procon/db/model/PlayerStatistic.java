package procon.db.model;

public class PlayerStatistic {
    /*[Class(NameType = typeof(PlayerStatistic), Table = "player_statistic")]
    public class PlayerStatistic
    {
        [Id(Name = "Id", Column = "id")]
        [Generator(1, Class = "native")]
        public virtual long Id { get; set; }

        [ManyToOne(ClassType = typeof(Player), Fetch = FetchMode.Join, Column = "player_id")]
        public virtual Player Player { get; set; }

        [ManyToOne(ClassType = typeof(Server), Column = "server_id")]
        public virtual Server Server { get; set; }

        [Property(Column = "score")]
        public virtual int Score { get; set; }

        [Property(Column = "kills")]
        public virtual int KillCount { get; set; }

        [Property(Column = "deaths")]
        public virtual int DeathCount { get; set; }

        public virtual double KillDeathRatio
        {
            get
            {
                if (this.DeathCount != 0)
                {
                    return (double) this.KillCount / this.DeathCount;
                }
                return this.KillCount;
            }
        }
    }*/
}
