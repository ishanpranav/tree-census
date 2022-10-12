using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    /// <summary>
    /// Specifies the status of a tree.
    /// </summary>
    public enum Status
    {
        /// <summary>
        /// Unspecified.
        /// </summary>
        [Name("")]
        None,

        /// <summary>
        /// Alive.
        /// </summary>
        Alive,

        /// <summary>
        /// Dead.
        /// </summary>
        Dead,

        /// <summary>
        /// A tree stump.
        /// </summary>
        Stump
    }
}
