using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Threading.Tasks;
using CsvHelper;

namespace TreeCensus
{
    internal static partial class Program
    {
        private static readonly Borough[] s_boroughs = Enum.GetValues<Borough>();

        private static async Task Main(string[] args)
        {
            try
            {
                if (args.Length == 0)
                {
                    throw new ArgumentException(ArgumentException);
                }
                else
                {
                    string path = args[0];
                    TreeCollection trees = new TreeCollection();
                    TreeSpeciesCollection species = new TreeSpeciesCollection();

                    try
                    {
                        using (StreamReader streamReader = new StreamReader(path))
                        using (CsvReader csvReader = new CsvReader(streamReader, CultureInfo.InvariantCulture))
                        {
                            await foreach (Tree tree in csvReader.GetRecordsAsync<Tree>())
                            {
                                trees.Add(tree);

                                TreeSpecies item = new TreeSpecies(tree.CommonName, tree.LatinName);

                                if (!species.Contains(item))
                                {
                                    species.Add(item);
                                }
                            }
                        }
                    }
                    catch (IOException)
                    {
                        throw new IOException(GetIOException(path));
                    }

                    Dictionary<Borough, int> totals = new Dictionary<Borough, int>();

                    foreach (Borough borough in s_boroughs)
                    {
                        if (borough == Borough.None)
                        {
                            totals.Add(Borough.None, trees.Count);
                        }
                        else
                        {
                            totals.Add(borough, trees.GetCountByBorough(borough));
                        }
                    }

                    string? line;

                    do
                    {
                        await Console.Out.WriteLineAsync(Message);

                        line = await Console.In.ReadLineAsync();

                        if (line != null)
                        {
                            await Console.Out.WriteLineAsync(MatchHeader);

                            Dictionary<Borough, int> frequencies = new Dictionary<Borough, int>();

                            foreach (Borough borough in s_boroughs)
                            {
                                frequencies.Add(borough, value: 0);
                            }

                            TreeSpeciesCollection byCommonName = species.GetByCommonName(line);
                            TreeSpeciesCollection byLatinName = species.GetByLatinName(line);
                            
                            if (byCommonName.Count == 0 && byLatinName.Count == 0)
                            {
                                await Console.Out.WriteLineAsync(GetNotFoundMessage(line));
                            }
                            else
                            {
                                HashSet<TreeSpecies> bySpecies = new HashSet<TreeSpecies>();
                                SortedSet<string> speciesNames = new SortedSet<string>();

                                foreach (TreeSpecies item in byCommonName)
                                {
                                    speciesNames.Add(item.CommonName);
                                    bySpecies.Add(item);
                                }

                                foreach (TreeSpecies item in byLatinName)
                                {
                                    speciesNames.Add(item.LatinName);
                                    bySpecies.Add(item);
                                }

                                foreach (TreeSpecies item in bySpecies)
                                {
                                    foreach (Borough borough in s_boroughs)
                                    {
                                        if (borough == Borough.None)
                                        {
                                            frequencies[borough] += trees.GetCountByLatinName(item.LatinName);
                                        }
                                        else
                                        {
                                            frequencies[borough] += trees.GetCountByLatinNameInBorough(item.LatinName, borough);
                                        }
                                    }
                                }

                                foreach (string speciesName in speciesNames)
                                {
                                    await Console.Out.WriteLineAsync(GetMatchString(speciesName));
                                }

                                await Console.Out.WriteLineAsync();
                                await Console.Out.WriteLineAsync(PopularityHeader);

                                foreach (Borough borough in s_boroughs)
                                {
                                    await Console.Out.WriteLineAsync(GetPopularityString(borough, frequencies[borough], totals[borough]));
                                }

                                await Console.Out.WriteLineAsync();
                            }
                        }
                    }
                    while (!string.Equals(line, TerminateString, StringComparison.OrdinalIgnoreCase));
                }
            }
            catch (Exception exception)
            {
                await Console.Error.WriteLineAsync(exception.Message);
            }
        }
    }
}
