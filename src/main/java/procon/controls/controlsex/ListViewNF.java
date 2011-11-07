package procon.controls.controlsex;

public class ListViewNF {

    /*// No flicker ListView.
    public class ListViewNF : System.Windows.Forms.ListView {
        public ListViewNF() {
            this.SetStyle(ControlStyles.OptimizedDoubleBuffer | ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.EnableNotifyMessage, true);
        }

        protected override void OnNotifyMessage(Message m) {
            if (m.Msg != 0x14) {
                base.OnNotifyMessage(m);
            }
        }
    }*/
}
