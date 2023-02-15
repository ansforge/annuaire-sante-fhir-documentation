using Hl7.Fhir.Rest;
using System.Net.Http;
using System.Threading;

namespace sample_usecases_tests
{
    class FhirTestUtils
    {

        public static FhirClient CreateClient()
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
