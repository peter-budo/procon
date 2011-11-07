package procon.controls.controlsex;

public class ListViewColumnSorter {

    /*// MSDN
    public class ListViewColumnSorter : IComparer {
        private int ColumnToSort;
        private SortOrder OrderOfSort;
        private CaseInsensitiveComparer ObjectCompare;

        public ListViewColumnSorter() {
            ColumnToSort = 0;
            OrderOfSort = SortOrder.None;
            ObjectCompare = new CaseInsensitiveComparer();
        }

        public int Compare(object x, object y) {
            int compareResult;
            ListViewItem listviewX = (ListViewItem)x, listviewY = (ListViewItem)y;

            UInt32 uix = 0, uiy = 0;
            Double dblx = 0, dbly = 0;

            if (Double.TryParse(listviewX.SubItems[ColumnToSort].Text, out dblx) == true && Double.TryParse(listviewY.SubItems[ColumnToSort].Text, out dbly) == true) {
                // Comparing two ints
                compareResult = ObjectCompare.Compare(dblx, dbly);
            }
            else {
                if (listviewX.SubItems[ColumnToSort].Tag != null && listviewY.SubItems[ColumnToSort].Tag != null
                    && listviewX.SubItems[ColumnToSort].Tag is UInt32 && listviewY.SubItems[ColumnToSort].Tag is UInt32
                    && UInt32.TryParse(((UInt32)listviewX.SubItems[ColumnToSort].Tag).ToString(), out uix) == true && UInt32.TryParse(((UInt32)listviewY.SubItems[ColumnToSort].Tag).ToString(), out uiy) == true) {
                    // Comparing two times.
                    compareResult = ObjectCompare.Compare(uix, uiy);
                }
                else {
                    // Comparing two strings.
                    compareResult = ObjectCompare.Compare(listviewX.SubItems[ColumnToSort].Text, listviewY.SubItems[ColumnToSort].Text);
                }
            }

            //if (Regex.Match(listviewY.Name, "procon.playerlist.totals[0-9]", RegexOptions.IgnoreCase).Success == true) {
            //    return -1;
            //}
            //else if (Regex.Match(listviewY.Name, "procon.playerlist.totals[0-9]", RegexOptions.IgnoreCase).Success == false) {
            //    return 1;
            //}
            //else {
            if (OrderOfSort == SortOrder.Ascending) {
                return compareResult;
            }
            else if (OrderOfSort == SortOrder.Descending) {
                return (-compareResult);
            }
            else {
                return 0;
            }
            //}
        }

        public int SortColumn {
            set { ColumnToSort = value; }
            get { return ColumnToSort; }
        }

        public SortOrder Order {
            set { OrderOfSort = value; }
            get { return OrderOfSort; }
        }
    }*/
}
