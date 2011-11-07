package procon.controls.battlemap.mapzonedisplay;

public class MapZonePointControl {

    /*public class MapZonePointControl : MapObject, IMapZonePoints {

        public delegate void MapZonePointControlDragHandler(MapZonePointControl sender);
        public event MapZonePointControlDragHandler MapZonePointDropped;

        public bool IsPointSelected {
            get {
                return this.m_isMouseDown;
            }
        }

        private PointF m_zonePoint;
        public PointF ZonePoint {
            get {
                return this.m_zonePoint;
            }
            set {
                this.m_zonePoint = value;
                this.ObjectPath = new GraphicsPath();

                RectangleF recPoint = new RectangleF(value.X - 3.0F, value.Y - 3.0F, 6.0F, 6.0F);
                this.ObjectPath.AddRectangle(recPoint);
                this.HotSpot = this.ObjectPath.GetBounds();

                this.HotSpot = new RectangleF(this.HotSpot.X - 10.0F, this.HotSpot.Y - 10.0F, this.HotSpot.Width + 20.0F, this.HotSpot.Height + 20.0F);
            }
        }

        public Point3D ToPoint3D() {
            return new Point3D((int)this.ZonePoint.X, (int)this.ZonePoint.Y, 0);
        }

        public MapZonePointControl(PointF point) {
            this.ZonePoint = point;

        }

        protected override void MouseOver(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.Blue);
        }

        protected override void MouseLeave(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.WhiteSmoke);
        }

        protected override void MouseDown(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.WhiteSmoke);
        }

        protected override void MouseUp(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.WhiteSmoke);
        }

        protected override void MouseClicked(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.WhiteSmoke);
        }

        protected override void NormalPaint(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.WhiteSmoke);
        }

        protected override void EndDrag() {
            if (this.MapZonePointDropped != null) {
                this.MapZonePointDropped(this);
            }
        }

        #region IMapZonePoints Members

        public void translate(float x, float y) {
            this.ZonePoint = new PointF(this.ZonePoint.X + x, this.ZonePoint.Y + y);
        }

        #endregion
    }*/
}
