using System;

namespace TreeCensus
{
    /// <summary>
    /// Represents a tree species.
    /// </summary>
    public class TreeSpecies : IEquatable<TreeSpecies>, ISpecies
    {
        /// <inheritdoc/>
        public string LatinName { get; }

        /// <inheritdoc/>
        public string CommonName { get; }

        /// <summary>
        /// Initializes a new instance of the <see cref="TreeSpecies"/> class.
        /// </summary>
        /// <param name="commonName">The common name.</param>
        /// <param name="latinName">The Latin name.</param>
        /// <exception cref="ArgumentNullException"><paramref name="commonName"/> is <see langword="null"/>.</exception>
        /// <exception cref="ArgumentNullException"><paramref name="latinName"/> is <see langword="null"/>.</exception>
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

        /// <inheritdoc/>
        public override bool Equals(object? obj)
        {
            return Equals(obj as TreeSpecies);
        }

        /// <inheritdoc/>
        public bool Equals(TreeSpecies? other)
        {
            return other != null && CommonName.Equals(other.CommonName, StringComparison.OrdinalIgnoreCase) && LatinName.Equals(other.LatinName, StringComparison.OrdinalIgnoreCase);
        }

        /// <inheritdoc/>
        public override int GetHashCode()
        {
            HashCode result = new HashCode();

            result.Add(CommonName);
            result.Add(LatinName);

            return result.ToHashCode();
        }
    }
}
