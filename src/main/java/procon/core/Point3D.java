package procon.core;

public class Point3D {
    /*[Serializable]
    public class Point3D {
        public int X {
            get;
            set;
        }

        public int Y {
            get;
            set;
        }

        public int Z {
            get;
            set;
        }

        public Point3D() {
            this.X = 0;
            this.Y = 0;
            this.Z = 0;
        }

        public Point3D(int iX, int iY, int iZ) {
            this.X = iX;
            this.Y = iY;
            this.Z = iZ;
        }

        public Point3D(string strX, string strY, string strZ) {
            int iX = 0, iY = 0, iZ = 0;

            int.TryParse(strX, out iX);
            int.TryParse(strY, out iY);
            int.TryParse(strZ, out iZ);

            this.X = iX;
            this.Y = iY;
            this.Z = iZ;
        }

        public static List<string> ToStringList(Point3D[] points) {

            List<string> list = new List<string>();

            foreach (Point3D point in points) {
                list.Add(point.X.ToString());
                list.Add(point.Y.ToString());
                list.Add(point.Z.ToString());
            }

            return list;
        }

    }*/
}
