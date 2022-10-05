using System;

namespace TreeCensus
{
    internal class TreeSpecies : IEquatable<TreeSpecies>
    {
        public string LatinName { get; }
        public string CommonName { get; }

        public TreeSpecies(string commonName, string latinName)
        {
            if (commonName == null)
            {
                throw new ArgumentNullException(nameof(commonName));
            }
            else if (latinName == null)
            {
                throw new ArgumentNullException(nameof(latinName));
            }
            else
            {
                CommonName = commonName;
                LatinName = latinName;
            }
        }

        public override bool Equals(object? obj)
        {
            return Equals(obj as TreeSpecies);
        }

        public bool Equals(TreeSpecies? other)
        {
            return other != null && CommonName.Equals(other.CommonName, StringComparison.OrdinalIgnoreCase) && LatinName.Equals(other.LatinName, StringComparison.OrdinalIgnoreCase);
        }

        public override int GetHashCode()
        {
            HashCode result = new HashCode();

            result.Add(CommonName);
            result.Add(LatinName);

            return result.ToHashCode();
        }
    }
}
