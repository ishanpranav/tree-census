namespace TreeCensus
{
    /// <summary>
    /// Defines methods for accessing the scientific (Latin) and common (English) names of a species.
    /// </summary>
    public interface ISpecies
    {
        /// <summary>
        /// Gets the scientific (Latin) name of the tree.
        /// </summary>
        /// <value>The scientific name.</value>
        string LatinName { get; }

        /// <summary>
        /// Gets the common (English) name of the tree.
        /// </summary>
        /// <value>The common name.</value>
        string CommonName { get; }
    }
}
