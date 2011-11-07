package procon.core.players.items;

public class SpecializationDictionary {
    /*[Serializable]
    public class SpecializationDictionary : KeyedCollection<string, Specialization>, ICloneable {

        protected override string GetKeyForItem(Specialization item) {
            return item.Name;
        }

        public object Clone() {
            return this.MemberwiseClone();
        }

        public new Specialization this[string key] {
            get {
                Specialization keyedSpecialization = null;

                foreach (Specialization spec in this) {
                    if (String.Compare(spec.Name, key, true) == 0) {
                        keyedSpecialization = spec;
                        break;
                    }
                }

                return keyedSpecialization;
            }
        }

        public new bool Contains(string key) {

            bool isKeyed = false;

            foreach (Specialization spec in this) {
                if (String.Compare(spec.Name, key, true) == 0) {
                    isKeyed = true;
                    break;
                }
            }

            return isKeyed;
        }
    }*/
}
