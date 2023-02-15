using System;
using System.Net.Http;

using System.Threading;
using Hl7.Fhir.Model;
using Hl7.Fhir.Rest;

namespace afas_openfhir_dotnet
{
    class Program
    {
        static void Main(string[] args)
        {
            var client = createClient();

            var q = new SearchParams()
             // .Where("name:exact=ewout")
             // .OrderBy("birthdate", SortOrder.Descending)
             // .SummaryOnly().Include("Patient:organization")
              .LimitTo(50);
            var bundle = client.Search<Practitioner>();
            foreach(var be in bundle.Entry)
            {
                var pr = be.Resource as Practitioner;
                Console.Write($"{pr.Gender.Value} ");
            }

        }



        static FhirClient createClient()
        {
            var settings = new FhirClientSettings
            {
                Timeout = 40000,
                PreferredFormat = ResourceFormat.Json,
                VerifyFhirVersion = false,
            };
            var handler = new AuthorizationMessageHandler();
            return new FhirClient("https://gateway.api.esante.gouv.fr/fhir/", settings, handler);
        }

    }


    public class AuthorizationMessageHandler : HttpClientHandler
    {
        protected async override System.Threading.Tasks.Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
        {
            request.Headers.Add("ESANTE-API-KEY", "eb2e94fa-ffe6-491f-aa9d-073f6a5a2415");
            return await base.SendAsync(request, cancellationToken);
        }
    }
}
