package procon.core;

public class CustomClass {
    /*/// <summary>
    /// CustomClass (Which is binding to property grid)
    /// Minor edits commented, original from:
    /// http://www.codeproject.com/KB/tabs/Dynamic_Propertygrid.aspx?display=PrintAll&fid=143493&df=90&mpp=25&noise=3&sort=Position&view=Quick&select=2211237
    /// </summary>
    public class CustomClass : CollectionBase, ICustomTypeDescriptor
    {
        /// <summary>
        /// Add CustomProperty to Collectionbase List
        /// </summary>
        /// <param name="Value"></param>
        public void Add(CustomProperty Value)
        {
            base.List.Add(Value);

        }

        /// <summary>
        /// Remove item from List
        /// </summary>
        /// <param name="Name"></param>
        public void Remove(string Name)
        {
            foreach (CustomProperty prop in base.List)
            {
                if (prop.Name == Name)
                {
                    base.List.Remove(prop);
                    return;
                }
            }
        }

        // Added in by Phogue.
        public bool ContainsKey(string Name)
        {
            bool blContainsKey = false;
            foreach (CustomProperty prop in base.List)
            {
                if (prop.Name == Name)
                {
                    blContainsKey = true;
                    break;
                }
            }
            return blContainsKey;
        }

        /// <summary>
        /// Indexer
        /// </summary>
        public CustomProperty this[int index]
        {
            get
            {
                return (CustomProperty)base.List[index];
            }
            set
            {
                base.List[index] = (CustomProperty)value;
            }
        }

        // Added in by Phogue.
        public CustomProperty this[string Name]
        {
            get
            {
                foreach (CustomProperty prop in base.List)
                {
                    if (prop.Name == Name)
                    {
                        return prop;
                    }
                }
                return null;
            }
            set
            {

                for (int i = 0; i < this.Count; i++)
                {
                    if (((CustomProperty)this[i]).Name == Name)
                    {
                        this[i] = (CustomProperty)value;
                        break;
                    }
                }
            }
        }

        #region "TypeDescriptor Implementation"
        /// <summary>
        /// Get Class Name
        /// </summary>
        /// <returns>String</returns>
        public String GetClassName()
        {
            return TypeDescriptor.GetClassName(this, true);
        }

        /// <summary>
        /// GetAttributes
        /// </summary>
        /// <returns>AttributeCollection</returns>
        public AttributeCollection GetAttributes()
        {
            return TypeDescriptor.GetAttributes(this, true);
        }

        /// <summary>
        /// GetComponentName
        /// </summary>
        /// <returns>String</returns>
        public String GetComponentName()
        {
            return TypeDescriptor.GetComponentName(this, true);
        }

        /// <summary>
        /// GetConverter
        /// </summary>
        /// <returns>TypeConverter</returns>
        public TypeConverter GetConverter()
        {
            return TypeDescriptor.GetConverter(this, true);
        }

        /// <summary>
        /// GetDefaultEvent
        /// </summary>
        /// <returns>EventDescriptor</returns>
        public EventDescriptor GetDefaultEvent()
        {
            return TypeDescriptor.GetDefaultEvent(this, true);
        }

        /// <summary>
        /// GetDefaultProperty
        /// </summary>
        /// <returns>PropertyDescriptor</returns>
        public PropertyDescriptor GetDefaultProperty()
        {
            return TypeDescriptor.GetDefaultProperty(this, true);
        }

        /// <summary>
        /// GetEditor
        /// </summary>
        /// <param name="editorBaseType">editorBaseType</param>
        /// <returns>object</returns>
        public object GetEditor(Type editorBaseType)
        {
            return TypeDescriptor.GetEditor(this, editorBaseType, true);
        }

        public EventDescriptorCollection GetEvents(Attribute[] attributes)
        {
            return TypeDescriptor.GetEvents(this, attributes, true);
        }

        public EventDescriptorCollection GetEvents()
        {
            return TypeDescriptor.GetEvents(this, true);
        }

        public PropertyDescriptorCollection GetProperties(Attribute[] attributes)
        {
            PropertyDescriptor[] newProps = new PropertyDescriptor[this.Count];
            for (int i = 0; i < this.Count; i++)
            {
                CustomProperty prop = (CustomProperty)this[i];
                newProps[i] = new CustomPropertyDescriptor(ref prop, attributes);
            }

            return new PropertyDescriptorCollection(newProps);
        }

        public PropertyDescriptorCollection GetProperties()
        {
            return TypeDescriptor.GetProperties(this, true);
        }

        public object GetPropertyOwner(PropertyDescriptor pd)
        {
            return this;
        }
        #endregion

    }*/
}
