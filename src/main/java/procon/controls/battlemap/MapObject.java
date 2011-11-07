package procon.controls.battlemap;

public class MapObject {

    /*public class MapObject : IDisposable {

        protected PointF m_pntDrawOffset;
        protected Point m_pntMousePosition;
        protected MouseButtons m_MouseButtons;

        protected bool m_isMouseOverLeaveCheck;
        protected bool m_isMouseDown;

        public bool IsMouseOver {
            get;
            protected set;
        }

        public bool IsDragging {
            get;
            protected set;
        }

        public GraphicsPath ObjectPath {
            get;
            protected set;
        }

        public RectangleF HotSpot {
            get;
            protected set;
        }

        protected void DrawBwShape(Graphics g, float flOpacity, float flOutlineWidth, Color clBackground, Color clForecolour) {
            this.DrawBwShape(g, this.ObjectPath, flOpacity, flOutlineWidth, clBackground, clForecolour);
        }

        protected void DrawBwShape(Graphics g, float flOpacity, float flOutlineWidth, Color clBackground, Brush foreColourBrush) {
            this.DrawBwShape(g, this.ObjectPath, flOpacity, flOutlineWidth, clBackground, foreColourBrush);
        }

        protected readonly Pen m_pOneWidth = new Pen(Brushes.Black, 1.0F);

        protected void DrawBwShape(Graphics g, GraphicsPath gpPass, float flOpacity, float flOutlineWidth, Color clBackground, Color clForecolour) {
            if (flOpacity > 0.0F) {
                GraphicsPath gp = (GraphicsPath)gpPass.Clone();

                Matrix m = new Matrix();
                m.Translate(this.m_pntDrawOffset.X, this.m_pntDrawOffset.Y);
                gp.Transform(m);

                Pen pen = new Pen(Color.FromArgb((int)(255.0F * flOpacity), clBackground), flOutlineWidth);
                pen.LineJoin = LineJoin.Round;
                g.DrawPath(pen, gp);
                SolidBrush brush = new SolidBrush(Color.FromArgb((int)(255.0F * flOpacity), clForecolour));
                g.FillPath(brush, gp);

                brush.Dispose();
                pen.Dispose();
                m.Dispose();
                gp.Dispose();
            }
        }

        protected void DrawBwShape(Graphics g, GraphicsPath gpPass, float flOpacity, float flOutlineWidth, Color clBackground, Brush foreColourBrush) {
            if (flOpacity > 0.0F) {
                GraphicsPath gp = (GraphicsPath)gpPass.Clone();

                Matrix m = new Matrix();
                m.Translate(this.m_pntDrawOffset.X, this.m_pntDrawOffset.Y);
                gp.Transform(m);

                Pen pen = new Pen(Color.FromArgb((int)(255.0F * flOpacity), clBackground), flOutlineWidth);
                pen.LineJoin = LineJoin.Round;
                g.DrawPath(pen, gp);
                //SolidBrush brush = new SolidBrush(Color.FromArgb((int)(255.0F * flOpacity), clForecolour));
                g.FillPath(foreColourBrush, gp);

                //brush.Dispose();
                pen.Dispose();
                m.Dispose();
                gp.Dispose();
            }
        }

        public MapObject() {
            this.m_pntDrawOffset = new PointF();
        }

        public MapObject(GraphicsPath gpButtonPath) {
            this.ObjectPath = gpButtonPath;
            this.HotSpot = this.ObjectPath.GetBounds();

            this.m_pntDrawOffset = new PointF();
        }

        protected virtual void MouseOver(Graphics g) {

        }

        protected virtual void MouseLeave(Graphics g) {

        }

        protected virtual void MouseDown(Graphics g) {

        }

        protected virtual void MouseUp(Graphics g) {

        }

        protected virtual void MouseClicked(Graphics g) {

        }

        protected virtual void NormalPaint(Graphics g) {
            this.DrawBwShape(g, 1.0F, 4.0F, Color.Black, Color.White);
        }

        protected virtual void BeginDrag() {

        }

        protected virtual void EndDrag() {

        }

        protected virtual void CheckMouseOver(PointF pntDrawOffset, Point pntMouseLocation) {
            this.IsMouseOver = (new RectangleF(pntDrawOffset.X + this.HotSpot.X, pntDrawOffset.Y + this.HotSpot.Y, this.HotSpot.Width, this.HotSpot.Height)).Contains(pntMouseLocation.X, pntMouseLocation.Y);
        }

        public void Draw(Graphics g, PointF pntDrawOffset, Point pntMouseLocation, MouseButtons mbButtons) {

            //this.IsMouseOver = (new RectangleF(pntDrawOffset.X + this.HotSpot.X, pntDrawOffset.Y + this.HotSpot.Y, this.HotSpot.Width, this.HotSpot.Height)).Contains(pntMouseLocation.X, pntMouseLocation.Y);

            //Region bah = new Region(this.ObjectPath);
            //this.IsMouseOver = bah.IsVisible(new PointF(pntMouseLocation.X - pntDrawOffset.X, pntMouseLocation.Y - pntDrawOffset.Y));
            //bah.Dispose();

            this.CheckMouseOver(pntDrawOffset, pntMouseLocation);

            if (this.m_pntMousePosition != null) {
                if (this.IsDragging == false && this.IsMouseOver == true && this.m_isMouseDown == true && (this.m_pntMousePosition.X != pntMouseLocation.X || this.m_pntMousePosition.Y != pntMouseLocation.Y)) {
                    this.IsDragging = true;
                    this.BeginDrag();
                }
                else if (this.IsDragging == true && (this.IsMouseOver == false || this.m_isMouseDown == false)) {
                    this.IsDragging = false;
                    this.EndDrag();
                }
            }

            this.m_pntMousePosition = pntMouseLocation;
            this.m_pntDrawOffset = pntDrawOffset;
            this.m_MouseButtons = mbButtons;

            if (this.IsMouseOver == true && (mbButtons == MouseButtons.Left || mbButtons == MouseButtons.Right)) {
                this.m_isMouseDown = true;
                this.MouseDown(g);
            }
            else if (this.m_isMouseDown == true && (mbButtons != MouseButtons.Left && mbButtons != MouseButtons.Right)) {
                this.m_isMouseDown = false;

                if (this.IsMouseOver == true) {
                    this.MouseClicked(g);
                }
                else {
                    this.MouseUp(g);
                }
            }
            //else if ( && this.m_isMouseDown == true && (mbButtons != MouseButtons.Left && mbButtons != MouseButtons.Right)) {
            //    this.m_isMouseDown = false;

            //}
            else if (this.IsMouseOver == true && this.m_isMouseDown == false) {
                this.m_isMouseOverLeaveCheck = true;
                this.MouseOver(g);
            }
            else if (this.IsMouseOver == false && this.m_isMouseOverLeaveCheck == true && this.m_isMouseDown == false) {
                this.m_isMouseOverLeaveCheck = false;
                this.MouseLeave(g);
            }
            else {
                this.m_isMouseOverLeaveCheck = false;
                //this.m_isMouseDown = false;

                this.NormalPaint(g);
            }

        }

        #region IDisposable Members

        public void Dispose() {
            this.ObjectPath.Dispose();
        }

        #endregion
    }*/
}
