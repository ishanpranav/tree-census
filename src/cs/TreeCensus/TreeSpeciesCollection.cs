using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;

namespace TreeCensus
{
    /// <summary>
    /// Represents a collection of tree species.
    /// </summary>
    public class TreeSpeciesCollection : Collection<TreeSpecies>
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="TreeSpeciesCollection"/> class.
        /// </summary>
        public TreeSpeciesCollection() { }

        /// <summary>
        /// Initializes a new instance of the <see cref="TreeSpeciesCollection"/> class.
        /// </summary>
        /// <param name="list">The list that is wrapped by the new collection.</param>
        public TreeSpeciesCollection(IList<TreeSpecies> list) : base(list) { }

        /// <summary>
        /// Constructs a new collection of tree species containing the species whose common names contain a given keyword as a substring.
        /// </summary>
        /// <param name="keyword">The substring present in all the common names of the species in the output.</param>
        /// <returns>The collection of matching tree species.</returns>
        /// <exception cref="ArgumentNullException"><paramref name="keyword"/> is <see langword="null"/>.</exception>
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

        /// <summary>
        /// Constructs a new collection of tree species containing the species whose Latin names contain a given keyword as a substring.
        /// </summary>
        /// <param name="keyword">The substring present in all the Latin names of the species in the output.</param>
        /// <returns>The collection of matching tree species.</returns>
        /// <exception cref="ArgumentNullException"><paramref name="keyword"/> is null.</exception>
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
