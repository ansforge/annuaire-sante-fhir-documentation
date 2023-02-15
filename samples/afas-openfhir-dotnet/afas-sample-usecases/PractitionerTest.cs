using Hl7.Fhir.Model;
using Hl7.Fhir.Rest;
using NUnit.Framework;
using System;

namespace sample_usecases_tests
{
    class PractitionerTest
    {

        /**
         * Search all Practitioner
         */
        [Test]
        public void SearchAll()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var bundle = client.Search<Practitioner>();
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitioner = be.Resource as Practitioner;
                var name = "";
                foreach (var n in practitioner.Name[0].Prefix)
                {
                    name = name + " " + n;
                }
                Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
            }
        }


        /**
         * Search practitioner by identifier
         */
        [Test]
        public void SearchByIdentifier()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("identifier=0012807590,810000005479")
              .LimitTo(50);
            var bundle = client.Search<Practitioner>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitioner = be.Resource as Practitioner;
                var name = "";
                foreach (var n in practitioner.Name[0].Prefix)
                {
                    name = name + " " + n;
                }
                Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
            }
        }



        /**
         * Search practitioner by name's prefix
         */
        [Test]
        public void SearchByNamePrefix()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("name=MME")
              .LimitTo(50);
            var bundle = client.Search<Practitioner>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitioner = be.Resource as Practitioner;
                var name = "";
                foreach (var n in practitioner.Name[0].Prefix)
                {
                    name = name + " " + n;
                }
                Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} name={name}");
            }
        }



        /**
         * Search practitioner by active status
         */
        [Test]
        public void SearchActive()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("active=true")
              .LimitTo(50);
            var bundle = client.Search<Practitioner>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitioner = be.Resource as Practitioner;
                Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | active={practitioner.Active.Value}");
            }
        }



        /**
         * Search practitioner by modification date
         */
        [Test]
        public void SearchModifiedFrom()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("_lastUpdated=ge2022-08-08T06:47:02")
              .LimitTo(50);
            var bundle = client.Search<Practitioner>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitioner = be.Resource as Practitioner;
                Console.WriteLine($"Practitioner found: id={practitioner.IdElement.Value} | lastUpdate={practitioner.Meta.LastUpdated}");
            }
        }

    }
}
