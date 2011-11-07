package procon.core;

public class Kill {

    /*[Serializable]
    public class Kill {

        public CPlayerInfo Killer {
            get;
            private set;
        }

        public CPlayerInfo Victim {
            get;
            private set;
        }

        *//*
        public string Killer {
            get;
            private set;
        }
        *//*

        public Point3D KillerLocation {
            get;
            private set;
        }

        *//*
        public string Victim {
            get;
            private set;
        }
        *//*

        public Point3D VictimLocation {
            get;
            private set;
        }

        public string DamageType {
            get;
            private set;
        }

        public bool Headshot {
            get;
            private set;
        }

        public bool IsSuicide {
            get;
            private set;
        }

        // TO DO: Change set back to private only.
        public DateTime TimeOfDeath {
            get;
            set;
        }

        public double Distance {
            get;
            private set;
        }

        public Kill(CPlayerInfo cpKiller, CPlayerInfo cpVictim, string strDamageType, bool blHeadshot, Point3D pntKiller, Point3D pntVictim) {

            if ((this.Killer = cpKiller) == null) {
                this.Killer = new CPlayerInfo();
            }

            if ((this.Victim = cpVictim) == null) {
                this.Victim = new CPlayerInfo();
            }

            this.IsSuicide = (String.Compare(this.Killer.SoldierName, this.Victim.SoldierName) == 0);

            this.DamageType = strDamageType;
            this.Headshot = blHeadshot;
            this.KillerLocation = pntKiller;
            this.VictimLocation = pntVictim;

            this.TimeOfDeath = DateTime.Now;

            double dx = pntKiller.X - pntVictim.X;
            double dy = pntKiller.Y - pntVictim.Y;
            double dz = pntKiller.Z - pntVictim.Z;

            this.Distance = Math.Sqrt(dx * dx + dy * dy + dz * dz);
        }

        *//*
        public Kill(string strKiller, string strVictim, string strDamageType, bool blHeadshot, Point3D pntKiller, Point3D pntVictim) {
            this.Killer = strKiller;
            this.Victim = strVictim;
            this.DamageType = strDamageType;
            this.Headshot = blHeadshot;
            this.KillerLocation = pntKiller;
            this.VictimLocation = pntVictim;

            this.TimeOfDeath = DateTime.Now;

            double dx = pntKiller.X - pntVictim.X;
            double dy = pntKiller.Y - pntVictim.Y;
            double dz = pntKiller.Z - pntVictim.Z;

            this.Distance = Math.Sqrt(dx*dx + dy*dy + dz*dz);
        }
        *//*
    }*/
}
