using System.Resources;

namespace TreeCensus
{
    partial class Tree
    {
        private static readonly ResourceManager s_resourceManager = new ResourceManager(typeof(Tree));

        private static string? ArgumentOutOfRangeException
        {
            get
            {
                return s_resourceManager.GetString("ArgumentOutOfRangeException_id");
            }
        }
    }
}
