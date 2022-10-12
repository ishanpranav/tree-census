using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    /// <summary>
    /// Specifies a borough.
    /// </summary>
    public enum Borough
    {
        /// <summary>
        /// Unspecified.
        /// </summary>
        None,

        /// <summary>
        /// Manhattan Island.
        /// </summary>
        Manhattan,

        /// <summary>
        /// The Bronx.
        /// </summary>
        Bronx,

        /// <summary>
        /// Brooklyn.
        /// </summary>
        Brooklyn,

        /// <summary>
        /// Queens.
        /// </summary>
        Queens,

        /// <summary>
        /// Staten Island.
        /// </summary>
        [Name("Staten Island")]
        StatenIsland
    }
}
