using System;
using CsvHelper.Configuration.Attributes;
using CsvHelper.TypeConversion;

namespace TreeCensus
{
    internal partial class Tree : IComparable, IComparable<Tree>, IEquatable<Tree>, IFormattable
    {
        private const string LatinNameName = "spc_latin";
        private const string CommonNameName = "spc_common";

        private int _id;

        [Name("tree_id")]
        public int Id
        {
            get
            {
                return _id;
            }
            set
            {
                if (value < 0)
                {
                    throw new ArgumentOutOfRangeException(ArgumentOutOfRangeException);
                }
                else
                {
                    _id = value;
                }
            }
        }

        [Name("status")]
        public Status Status { get; set; }

        [Name("health")]
        public Health Health { get; set; }

        [Name("spc_latin")]
        public string LatinName { get; set; } = string.Empty;

        [Name("spc_common")]
        public string CommonName { get; set; } = string.Empty;

        private int _postcode;

        [Name("postcode")]
        public int Postcode
        {
            get
            {
                return _postcode;
            }
            set
            {
                if (value >= 0 && value <= 99999)
                {
                    _postcode = value;
                }
                else
                {
                    throw new ArgumentOutOfRangeException(nameof(value), "Postcode must be between 00000 and 99999, inclusive.");
                }
            }
        }

        [Name("borough")]
        public Borough Borough { get; set; }

        [Name("x_sp")]
        public double X { get; set; }

        [Name("y_sp")]
        public double Y { get; set; }

        public Tree() { }

        public Tree(int id, TreeSpecies species)
        {
            Id = id;
            LatinName = species.LatinName;
            CommonName = species.CommonName;
        }

        public int CompareTo(object? obj)
        {
            return CompareTo(obj as Tree);
        }

        public int CompareTo(Tree? other)
        {
            if (other == null)
            {
                return 1;
            }
            else
            {
                int result = StringComparer.OrdinalIgnoreCase.Compare(CommonName, other.CommonName);

                if (result == 0)
                {
                    result = _id.CompareTo(other._id);
                }

                return result;
            }
        }

        public override bool Equals(object? obj)
        {
            return Equals(obj as Tree);
        }

        public bool Equals(Tree? other)
        {
            return other != null && _id == other._id && CommonName.Equals(other.CommonName, StringComparison.OrdinalIgnoreCase) && LatinName.Equals(other.LatinName, StringComparison.OrdinalIgnoreCase);
        }

        public override int GetHashCode()
        {
            HashCode result = new HashCode();

            result.Add(_id);
            result.Add(CommonName);
            result.Add(LatinName);

            return result.ToHashCode();
        }

        public override string ToString()
        {
            return ToString(formatProvider: null);
        }

        public string ToString(IFormatProvider? formatProvider)
        {
            return ToString(format: null, formatProvider);
        }

        public string ToString(string? format, IFormatProvider? formatProvider)
        {
            return string.Format(formatProvider, "{0} ({1}) #{2}", CommonName, LatinName, _id);
        }
    }
}
