package procon.controls.battlemap.mapimagepacks;

public class MapImagePackDictionary {

    /*public class MapImagePackDictionary : KeyedCollection<string, MapImagePack> {

        public delegate void ImagePackAlteredHandler(MapImagePack item);
        public event ImagePackAlteredHandler ImagePackAdded;
        public event ImagePackAlteredHandler ImagePackRemoved;

        protected override string GetKeyForItem(MapImagePack item) {
            return item.MapImagePackPath;
        }

        protected override void InsertItem(int index, MapImagePack item) {
            base.InsertItem(index, item);

            if (this.ImagePackAdded != null) {
                FrostbiteConnection.RaiseEvent(this.ImagePackAdded.GetInvocationList(), item);
            }
        }

        protected override void RemoveItem(int index) {
            MapImagePack clocRemoved = this[index];

            base.RemoveItem(index);

            if (this.ImagePackRemoved != null) {
                FrostbiteConnection.RaiseEvent(this.ImagePackRemoved.GetInvocationList(), clocRemoved);
            }
        }
    }*/
}
