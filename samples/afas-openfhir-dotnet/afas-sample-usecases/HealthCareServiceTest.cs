using NUnit.Framework;
using Hl7.Fhir.Model;
using System;
using Hl7.Fhir.Rest;

namespace sample_usecases_tests
{
    class HealthCareServiceTest
    {

        /**
         * Search all healthcare services
         */
        [Test]
        public void SearchAll()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var bundle = client.Search<HealthcareService>();
            foreach (var be in bundle.Entry)
            {
                // print HealthcareService data:
                var healthcareService = be.Resource as HealthcareService;
                Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value}");
            }
        }


        /**
         * Search healthcare services by identifier
         */
        [Test]
        public void SearchByIdentifier()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("identifier=52-52-49883")
              .LimitTo(50);
            var bundle = client.Search<HealthcareService>(q);
            foreach (var be in bundle.Entry)
            {
                // print HealthcareService data:
                var healthcareService = be.Resource as HealthcareService;
                Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value}");
            }
        }


        /**
         * Search active healthcare services
         */
        [Test]
        public void SearchActive()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("active=true")
              .LimitTo(50);
            var bundle = client.Search<HealthcareService>(q);
            foreach (var be in bundle.Entry)
            {
                // print HealthcareService data:
                var healthcareService = be.Resource as HealthcareService;
                Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} | status={healthcareService.Active.Value}");
            }
        }

        /**
         * Search healthcare services of characteristic : "Chirurgie ambulatoire"
         */
        [Test]
        public void SearchByCharacteristic1()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("characteristic=https://mos.esante.gouv.fr/NOS/TRE_R276-FormeActivite/FHIR/TRE-R276-FormeActivite|07")
              .LimitTo(50);
            var bundle = client.Search<HealthcareService>(q);
            foreach (var be in bundle.Entry)
            {
                // print HealthcareService data:
                var healthcareService = be.Resource as HealthcareService;
                var healthcareServiceCoding = healthcareService.Characteristic[0].Coding[0];

                Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} | characteristic={healthcareServiceCoding.System}|{healthcareServiceCoding.Code}");
            }
        }

        /**
         * Search healthcare services "Hébergement complet internat"
         */
        [Test]
        public void SearchByCharacteristic2()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("characteristic=https://mos.esante.gouv.fr/NOS/TRE_R209-TypeActivite/FHIR/TRE-R209-TypeActivite|11")
              .LimitTo(50);
            var bundle = client.Search<HealthcareService>(q);
            foreach (var be in bundle.Entry)
            {
                // print HealthcareService data:
                var healthcareService = be.Resource as HealthcareService;
                var healthcareServiceCoding = healthcareService.Characteristic[0].Coding[0];

                Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} | characteristic={healthcareServiceCoding.System}|{healthcareServiceCoding.Code}");
            }
        }

        /**
         * Search by lastUpdate
         */
        [Test]
        public void SearchModifiedFrom()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("_lastUpdated=ge2022-08-18")
              .LimitTo(50);
            var bundle = client.Search<HealthcareService>(q);
            foreach (var be in bundle.Entry)
            {
                // print HealthcareService data:
                var healthcareService = be.Resource as HealthcareService;
                Console.WriteLine($"Healthcare Service found: id={healthcareService.IdElement.Value} lastUpdate={healthcareService.Meta.LastUpdated.Value}");
            }
        }

    }
}
