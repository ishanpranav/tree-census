using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;

namespace TreeCensus
{
    internal class TreeSpeciesCollection : Collection<TreeSpecies>
    {
        public TreeSpeciesCollection() { }
        public TreeSpeciesCollection(IList<TreeSpecies> list) : base(list) { }

        public TreeSpeciesCollection GetByCommonName(string keyword)
        {
            if (keyword == null)
            {
                throw new ArgumentNullException(nameof(keyword));
            }
            else
            {
                return new TreeSpeciesCollection(this
                    .Where(x => x.CommonName.Contains(keyword, StringComparison.OrdinalIgnoreCase))
                    .ToList());
            }
        }

        public TreeSpeciesCollection GetByLatinName(string keyword)
        {
            if (keyword == null)
            {
                throw new ArgumentNullException(nameof(keyword));
            }
            else
            {
                return new TreeSpeciesCollection(this
                    .Where(x => x.LatinName.Contains(keyword, StringComparison.OrdinalIgnoreCase))
                    .ToList());
            }
        }
    }
}
