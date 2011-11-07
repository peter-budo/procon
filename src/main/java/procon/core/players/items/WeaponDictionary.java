package procon.core.players.items;

public class WeaponDictionary {

    /*[Serializable]
    public class WeaponDictionary : KeyedCollection<string, Weapon>, ICloneable {

        protected override string GetKeyForItem(Weapon item) {
            return item.Name;
        }

        public object Clone() {
            // Only need a shallow copy since the Weapon objects are readonly themselves.
            return this.MemberwiseClone();
        }

        public new Weapon this[string key] {
            get {
                Weapon keyedWeapon = null;

                foreach (Weapon weapon in this) {
                    if (String.Compare(weapon.Name, key, true) == 0) {
                        keyedWeapon = weapon;
                        break;
                    }
                }

                return keyedWeapon;
            }
        }

        public new bool Contains(string key) {

            bool isKeyed = false;

            foreach (Weapon weapon in this) {
                if (String.Compare(weapon.Name, key, true) == 0) {
                    isKeyed = true;
                    break;
                }
            }

            return isKeyed;
        }
    }*/
}
