using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace TreeCensus
{
    internal partial class TreeCollection : IEnumerable<Tree>
    {
        private readonly HashSet<Tree> _items = new HashSet<Tree>();

        public int Count
        {
            get
            {
                return _items.Count;
            }
        }

        public void Add(Tree item)
        {
            _items.Add(item);
        }

        public int GetCountByCommonName(string value)
        {
            return this.Count(x => x.CommonName.Equals(value, StringComparison.OrdinalIgnoreCase));
        }

        public int GetCountByLatinName(string value)
        {
            return this.Count(x => x.LatinName.Equals(value, StringComparison.OrdinalIgnoreCase));
        }

        public int GetCountByBorough(Borough value)
        {
            return this.Count(x => x.Borough == value);
        }

        public int GetCountByCommonNameInBorough(string commonName, Borough borough)
        {
            return this.Count(x => x.Borough == borough && x.CommonName.Equals(commonName, StringComparison.OrdinalIgnoreCase));
        }

        public int GetCountByLatinNameInBorough(string latinName, Borough borough)
        {
            return this.Count(x => x.Borough == borough && x.LatinName.Equals(latinName, StringComparison.OrdinalIgnoreCase));
        }

        public IEnumerator<Tree> GetEnumerator()
        {
            return _items.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }
}
