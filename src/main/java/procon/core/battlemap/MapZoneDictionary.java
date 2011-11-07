package procon.core.battlemap;

public class MapZoneDictionary {
    /*
    [Serializable]
    public class MapZoneDictionary : KeyedCollection<string, MapZoneDrawing> {

        public delegate void MapZoneAlteredHandler(MapZoneDrawing item);
        public event MapZoneAlteredHandler MapZoneAdded;
        public event MapZoneAlteredHandler MapZoneChanged;
        public event MapZoneAlteredHandler MapZoneRemoved;

        protected override string GetKeyForItem(MapZoneDrawing item) {
            return item.UID;
        }

        protected override void InsertItem(int index, MapZoneDrawing item) {
            base.InsertItem(index, item);
            item.TagsEdited += new MapZoneDrawing.TagsEditedHandler(item_TagsEdited);

            if (this.MapZoneAdded != null) {
                FrostbiteConnection.RaiseEvent(this.MapZoneAdded.GetInvocationList(), item);
            }
        }

        private void item_TagsEdited(MapZoneDrawing sender) {
            if (this.MapZoneChanged != null) {
                FrostbiteConnection.RaiseEvent(this.MapZoneChanged.GetInvocationList(), sender);
            }
        }

        protected override void RemoveItem(int index) {

            MapZoneDrawing apRemoved = this[index];
            apRemoved.TagsEdited -= new MapZoneDrawing.TagsEditedHandler(item_TagsEdited);

            base.RemoveItem(index);

            if (this.MapZoneRemoved != null) {
                FrostbiteConnection.RaiseEvent(this.MapZoneRemoved.GetInvocationList(), apRemoved);
            }
        }

        protected override void SetItem(int index, MapZoneDrawing item) {
            if (this.MapZoneChanged != null) {
                FrostbiteConnection.RaiseEvent(this.MapZoneChanged.GetInvocationList(), item);
            }

            base.SetItem(index, item);
            item.TagsEdited += new MapZoneDrawing.TagsEditedHandler(item_TagsEdited);
        }

        public void CreateMapZone(string mapFileName, Point3D[] points) {

            Random random = new Random();
            string strUid = String.Empty;

            do {
                strUid = String.Format("{0}{1}", mapFileName, random.Next());
                strUid = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(strUid));
            } while (this.Contains(strUid) == true);

            this.Add(new MapZoneDrawing(strUid, mapFileName, "", points, true));
        }

        public void ModifyMapZonePoints(string strUid, Point3D[] points) {

            if (this.Contains(strUid) == true) {
                // this[strUid].LevelFileName = mapFileName;
                this[strUid].ZonePolygon = points;

                if (this.MapZoneChanged != null) {
                    FrostbiteConnection.RaiseEvent(this.MapZoneChanged.GetInvocationList(), this[strUid]);
                }
            }

        }
    }
     */
}
