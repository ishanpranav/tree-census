using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Resources;

namespace TreeCensus
{
    partial class Tree
    {
        private static ResourceManager s_resourceManager = new ResourceManager(typeof(Tree));

        private static string? ArgumentOutOfRangeException
        {
            get
            {
                return s_resourceManager.GetString("ArgumentOutOfRangeException_id");
            }
        }
    }
}
