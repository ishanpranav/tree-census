using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    internal enum Health
    {
        [Name("")]
        None,

        Good,
        Fair,
        Poor
    }
}
