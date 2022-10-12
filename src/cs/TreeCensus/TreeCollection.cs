using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace TreeCensus
{
    /// <summary>
    /// Represents a collection of trees.
    /// </summary>
    public partial class TreeCollection : IEnumerable<Tree>
    {
        private readonly HashSet<Tree> _items = new HashSet<Tree>();

        /// <summary>
        /// Gets the total number of trees in the collection.
        /// </summary>
        public int Count
        {
            get
            {
                return _items.Count;
            }
        }

        /// <summary>
        /// Adds a tree to the collection.
        /// </summary>
        /// <param name="item">The tree to add.</param>
        public void Add(Tree item)
        {
            _items.Add(item);
        }

        /// <summary>
        /// Gets the number of trees whose species' common names are the same as the given species name using a case-insensitive comparison.
        /// </summary>
        /// <param name="value">The common name.</param>
        /// <returns>The number of trees with the given common name.</returns>
        public int GetCountByCommonName(string value)
        {
            return this.Count(x => x.CommonName.Equals(value, StringComparison.OrdinalIgnoreCase));
        }

        /// <summary>
        /// Gets the number of trees whose species' Latin names are the same as the given species name using a case-insensitive comparison.
        /// </summary>
        /// <param name="value">The Latin name.</param>
        /// <returns>The number of trees with the given Latin name.</returns>
        public int GetCountByLatinName(string value)
        {
            return this.Count(x => x.LatinName.Equals(value, StringComparison.OrdinalIgnoreCase));
        }

        /// <summary>
        /// Gets the number of trees located in the given borough using a case-insensitive comparison.
        /// </summary>
        /// <param name="value">The borough name.</param>
        /// <returns>The number of trees in the given borough.</returns>
        public int GetCountByBorough(Borough value)
        {
            return this.Count(x => x.Borough == value);
        }

        /// <summary>
        /// Gets the number of trees whose species' common names are the same as the given species name and which are located in the given borough. All comparisons are case-insensitive.
        /// </summary>
        /// <param name="commonName">The common name.</param>
        /// <param name="borough">The borough name.</param>
        /// <returns>The number of trees with the given common name in the given borough.</returns>
        public int GetCountByCommonNameInBorough(string commonName, Borough borough)
        {
            return this.Count(x => x.Borough == borough && x.CommonName.Equals(commonName, StringComparison.OrdinalIgnoreCase));
        }

        /// <summary>
        /// Gets the number of trees whose species' Latin names are the same as the given species name and which are located in the given borough. All comparisons are case-insensitive.
        /// </summary>
        /// <param name="latinName">The Latin name.</param>
        /// <param name="borough">The borough name.</param>
        /// <returns>The number of trees with the given Latin name in the given borough.</returns>
        public int GetCountByLatinNameInBorough(string latinName, Borough borough)
        {
            return this.Count(x => x.Borough == borough && x.LatinName.Equals(latinName, StringComparison.OrdinalIgnoreCase));
        }

        /// <inheritdoc/>
        public IEnumerator<Tree> GetEnumerator()
        {
            return _items.GetEnumerator();
        }

        /// <inheritdoc/>
        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }
}
