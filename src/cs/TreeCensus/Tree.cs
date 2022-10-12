using System;
using CsvHelper.Configuration.Attributes;

namespace TreeCensus
{
    /// <summary>
    /// Represents a tree.
    /// </summary>
    public partial class Tree : IComparable, IComparable<Tree>, IEquatable<Tree>, IFormattable, ISpecies
    {
        private int _id;

        /// <summary>
        /// Gets or sets the tree identifier.
        /// </summary>
        /// <value>The tree identifier.</value>
        /// <exception cref="System.ArgumentOutOfRangeException">The value is less than 0.</exception>
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

        /// <summary>
        /// Gets or sets the tree status.
        /// </summary>
        /// <value>The tree status.</value>
        [Name("status")]
        public Status Status { get; set; }

        /// <summary>
        /// Gets or sets the tree health.
        /// </summary>
        /// <value>The tree health.</value>
        [Name("health")]
        public Health Health { get; set; }

        /// <summary>
        /// Gets or sets the scientific (Latin) name of the tree. The default is <see cref="string.Empty"/>.
        /// </summary>
        /// <value>The scientific name.</value>
        [Name("spc_latin")]
        public string LatinName { get; set; } = string.Empty;

        /// <summary>
        /// Gets or sets the common (English) name of the tree. The default is <see cref="string.Empty"/>.
        /// </summary>
        /// <value>The common name.</value>
        [Name("spc_common")]
        public string CommonName { get; set; } = string.Empty;

        private int _postcode;

        /// <summary>
        /// Gets or sets the postcode.
        /// </summary>
        /// <value>The postcode, a five-digit number between 00000 and 99999.</value>
        /// <exception cref="System.ArgumentOutOfRangeException">The postcode is less than 0 or greater than 99999.</exception>
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

        /// <summary>
        /// Gets or sets the borough in which the tree is located.
        /// </summary>
        /// <value>The borough.</value>
        [Name("borough")]
        public Borough Borough { get; set; }

        /// <summary>
        /// Gets or sets the x-coordinate of the tree.
        /// </summary>
        /// <value>The x-coordinate.</value>
        [Name("x_sp")]
        public double X { get; set; }

        /// <summary>
        /// Gets or sets the y-coordinate of the tree.
        /// </summary>
        /// <value>The y-coordinate.</value>
        [Name("y_sp")]
        public double Y { get; set; }

        /// <summary>
        /// Initializes a new instance of the <see cref="Tree"/> class.
        /// </summary>
        public Tree() { }

        /// <summary>
        /// Initializes a new instance of the <see cref="Tree"/> class.
        /// </summary>
        /// <param name="id">The tree identifier.</param>
        /// <param name="species">The tree species.</param>
        public Tree(int id, TreeSpecies species)
        {
            Id = id;
            LatinName = species.LatinName;
            CommonName = species.CommonName;
        }

        /// <inheritdoc/>
        public int CompareTo(object? obj)
        {
            return CompareTo(obj as Tree);
        }

        /// <inheritdoc/>
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

        /// <inheritdoc/>
        public override bool Equals(object? obj)
        {
            return Equals(obj as Tree);
        }

        /// <inheritdoc/>
        public bool Equals(Tree? other)
        {
            return other != null && _id == other._id && CommonName.Equals(other.CommonName, StringComparison.OrdinalIgnoreCase) && LatinName.Equals(other.LatinName, StringComparison.OrdinalIgnoreCase);
        }

        /// <inheritdoc/>
        public override int GetHashCode()
        {
            HashCode result = new HashCode();

            result.Add(_id);
            result.Add(CommonName);
            result.Add(LatinName);

            return result.ToHashCode();
        }

        /// <inheritdoc/>
        public override string ToString()
        {
            return ToString(formatProvider: null);
        }

        /// <inheritdoc/>
        public string ToString(IFormatProvider? formatProvider)
        {
            return ToString(format: null, formatProvider);
        }

        /// <inheritdoc/>
        public string ToString(string? format, IFormatProvider? formatProvider)
        {
            return string.Format(formatProvider, "{0} ({1}) #{2}", CommonName, LatinName, _id);
        }
    }
}
