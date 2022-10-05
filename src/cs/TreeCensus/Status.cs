using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    internal enum Status
    {
        [Name("")]
        None,

        Alive,
        Dead,
        Stump
    }
}
