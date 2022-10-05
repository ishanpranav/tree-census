using System.Resources;

namespace TreeCensus
{
    partial class Program
    {
        private static readonly ResourceManager s_resourceManager = new ResourceManager(typeof(Program));

        private static string? ArgumentException
        {
            get
            {
                return s_resourceManager.GetString(name: "ArgumentException_args");
            }
        }

        private static string? TerminateString
        {
            get
            {
                return s_resourceManager.GetString(nameof(TerminateString));
            }
        }

        private static string? Message
        {
            get
            {
                return s_resourceManager.GetString(nameof(Message));
            }
        }

        private static string MatchHeader
        {
            get
            {
                return s_resourceManager.GetString(nameof(MatchHeader)) ?? string.Empty;
            }
        }

        private static string PopularityHeader
        {
            get
            {
                return s_resourceManager.GetString(nameof(PopularityHeader)) ?? string.Empty;
            }
        }

        private static string GetIOException(string path)
        {
            return string.Format(s_resourceManager.GetString(name: "IOException[path]") ?? "{0}", path);
        }

        private static string GetMatchString(string speciesName)
        {
            return string.Format(s_resourceManager.GetString(name: "MatchString[speciesName]") ?? "{0}", speciesName);
        }

        private static string GetPopularityString(Borough borough, int frequency, int total)
        {
            return string.Format(s_resourceManager.GetString(name: "PopularityString[borough;fraction;proportion]") ?? "{0} {1} {2}", s_resourceManager.GetString($"Borough.{borough}") ?? borough.ToString(),
           string.Format(s_resourceManager.GetString(name: "FractionString[frequency;total]") ?? "{0} {1}", frequency, total), (double)frequency / total);
        }

        private static string GetNotFoundMessage(string keyword)
        {
            return string.Format(s_resourceManager.GetString(name: "NotFoundMessage[keyword]") ?? "{0}", keyword);
        }
    }
}
