using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    public enum Borough
    {
        None,
        
        Manhattan,
        Bronx,
        Brooklyn,
        Queens,

        [Name("Staten Island")]
        StatenIsland
    }
}
