using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    /// <summary>
    /// Specifies the health of a tree.
    /// </summary>
    public enum Health
    {
        /// <summary>
        /// Unspecified.
        /// </summary>
        [Name("")]
        None,

        /// <summary>
        /// Good health.
        /// </summary>
        Good,

        /// <summary>
        /// Fair health.
        /// </summary>
        Fair,

        /// <summary>
        /// Poor health.
        /// </summary>
        Poor
    }
}
