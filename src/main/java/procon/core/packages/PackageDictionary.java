package procon.core.packages;

public class PackageDictionary {

    /*public class PackageDictionary : KeyedCollection<string, Package> {

        public PackageDictionary() {

        }

        protected override string GetKeyForItem(Package item) {
            return item.Uid;
        }

        public void AddPackage(Package package) {
            if (this.Contains(package.Uid) == false) {
                this.Add(package);
            }
            else {
                // Update the item, it might be a new release.
                this.SetItem(this.IndexOf(this[package.Uid]), package);
            }
        }

        public bool IsNewer(Package package) {

            bool isNewer = false;

            if (this.Contains(package.Uid) == true) {
                // if 1.0.0.0 < 2.0.0.0
                isNewer = (this[package.Uid].Version < package.Version);
            }
            else {
                // Not found, yes it is newer.
                isNewer = true;
            }

            return isNewer;
        }

    }*/
}
