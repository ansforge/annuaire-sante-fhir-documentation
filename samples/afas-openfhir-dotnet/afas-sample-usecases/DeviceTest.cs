using NUnit.Framework;
using Hl7.Fhir.Model;
using System;
using Hl7.Fhir.Rest;

namespace sample_usecases_tests
{
    public class DeviceTest
    {


        /**
         * Search all devices
         */
        [Test]
        public void SearchAll()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var bundle = client.Search<Device>();
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var device = be.Resource as Device;
                Console.WriteLine($"Device found: id={device.IdElement.Value} AuthorizationARHGOS={device.Extension.FindLast(e => e.Url.Equals("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")).Value}");
            }
        }

        /**
         * Search all devices modified from a given date
         */
        [Test]
        public void SearchFromModifiedDate()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("_lastUpdated=ge2022-08-07T14:51:04")
              .LimitTo(50);
            var bundle = client.Search<Device>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var device = be.Resource as Device;
                Console.WriteLine($"Device found: id={device.IdElement.Value} AuthorizationARHGOS={device.Extension.FindLast(e => e.Url.Equals("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")).Value}");
            }
        }


        /**
         * Search all devices containing arhgos number
         */
        [Test]
        public void SearchForArhgosNumber()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("number-authorization-arhgos=93-93-67204")
              .LimitTo(50);
            var bundle = client.Search<Device>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var device = be.Resource as Device;
                Console.WriteLine($"Device found: id={device.IdElement.Value} AuthorizationARHGOS={device.Extension.FindLast(e => e.Url.Equals("https://apifhir.annuaire.sante.fr/ws-sync/exposed/structuredefinition/Device-numberAuthorizationARHGOS")).Value}");
            }
        }


        /**
         * Search devices by identifier
         */
        [Test]
        public void SearchByIdentifier()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("identifier=32-31-1156,93-93-4364")
              .LimitTo(50);
            var bundle = client.Search<Device>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var device = be.Resource as Device;
                Console.WriteLine($"Device found: id={device.IdElement.Value}");
            }
        }

        /**
         * Search devices by status
         */
        [Test]
        public void SearchByStatus()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("status=active")
              .LimitTo(50);
            var bundle = client.Search<Device>(q);
            foreach (var be in bundle.Entry)
            {
                // print Device data
                var device = be.Resource as Device;
                Console.WriteLine($"Device found: id={device.IdElement.Value}  | status={device.Status.Value}");
            }
        }


        /**
         * Search devices by type
         */
        [Test]
        public void SearchByType()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
               .Where("type=https://mos.esante.gouv.fr/NOS/TRE_R272-EquipementMaterielLourd/FHIR/TRE-R272-EquipementMaterielLourd|05602")
              .LimitTo(50);
            var bundle = client.Search<Device>(q);
            foreach (var be in bundle.Entry)
            {
                // print Device data
                var device = be.Resource as Device;
                Console.WriteLine($"Device found: id={device.IdElement.Value}  | type={device.Type.Coding[0].Code}");
            }
        }

    }
}