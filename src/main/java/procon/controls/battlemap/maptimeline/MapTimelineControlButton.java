package procon.controls.battlemap.maptimeline;

public class MapTimelineControlButton {

    /*public class MapTimelineControlButton : MapObject {

        public delegate void TimelineControlButtonClickedHandler(MapTimelineControlButton sender, MapTimelineControlButtonType ButtonType);
        public event TimelineControlButtonClickedHandler TimelineControlButtonClicked;

        public MapTimelineControlButtonType ButtonType {
            get;
            private set;
        }

        public float ButtonOpacity {
            get;
            set;
        }

        public Color ForegroundColour {
            get;
            set;
        }

        public MapTimelineControlButton(GraphicsPath gpButtonPath, MapTimelineControlButtonType mtbtButtonType)
            : base(gpButtonPath) {
            this.ButtonOpacity = 0.0F;
            this.ButtonType = mtbtButtonType;
            this.ForegroundColour = Color.White;
        }

        public MapTimelineControlButton()
            : base() {
            this.ButtonOpacity = 0.0F;
            this.ButtonType = MapTimelineControlButtonType.None;
        }

        protected override void MouseOver(Graphics g) {
            this.DrawBwShape(g, this.ButtonOpacity, 4.0F, Color.Black, ControlPaint.Light(Color.RoyalBlue));
        }

        protected override void MouseLeave(Graphics g) {
            this.DrawBwShape(g, this.ButtonOpacity, 4.0F, Color.Black, this.ForegroundColour);
        }

        protected override void MouseDown(Graphics g) {
            this.DrawBwShape(g, this.ButtonOpacity, 8.0F, Color.Black, ControlPaint.Light(Color.RoyalBlue));
        }

        protected override void MouseUp(Graphics g) {
            this.DrawBwShape(g, this.ButtonOpacity, 4.0F, Color.Black, this.ForegroundColour);
        }

        protected override void MouseClicked(Graphics g) {
            this.DrawBwShape(g, this.ButtonOpacity, 8.0F, Color.Black, ControlPaint.Light(Color.RoyalBlue));

            if (this.TimelineControlButtonClicked != null) {
                this.TimelineControlButtonClicked(this, this.ButtonType);
                //FrostbiteConnection.RaiseEvent(this.TimelineControlButtonClicked.GetInvocationList(), this.ButtonType);
            }
        }

        protected override void NormalPaint(Graphics g) {
            this.DrawBwShape(g, this.ButtonOpacity, 4.0F, Color.Black, this.ForegroundColour);
        }
    }*/
}
