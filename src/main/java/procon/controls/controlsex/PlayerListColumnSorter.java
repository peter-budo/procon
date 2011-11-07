package procon.controls.controlsex;

public class PlayerListColumnSorter {

    /*public class PlayerListColumnSorter : ListViewColumnSorter, IComparer {

        public Regex TotalsAveragesChecker {
            get;
            private set;
        }

        public PlayerListColumnSorter()
            : base() {
            this.TotalsAveragesChecker = new Regex("procon.playerlist.(totals|averages)[0-9]", RegexOptions.IgnoreCase | RegexOptions.Compiled);
        }

        public new int Compare(object x, object y) {
            ListViewItem listviewX = (ListViewItem)x, listviewY = (ListViewItem)y;

            if (this.TotalsAveragesChecker.IsMatch(listviewY.Name) == true) {
                return -1;
            }
            else if (this.TotalsAveragesChecker.IsMatch(listviewX.Name) == true) {
                return 1;
            }
            else {
                return base.Compare(x, y);
            }
        }

    }*/
}
