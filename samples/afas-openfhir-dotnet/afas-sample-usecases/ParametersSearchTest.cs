using NUnit.Framework;
using Hl7.Fhir.Model;
using System;
using Hl7.Fhir.Rest;

namespace sample_usecases_tests
{
    class ParametersSearchTest
    {


        /**
         * This sample demonstrates how to fetch with no modifier parameter
         */
        [Test]
        public void SearchNoModifierParameter()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("name=Renard")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: name={organization.Name}");
            }
        }

        /**
         * This sample demonstrates how to fetch with contains parameter
         */
        [Test]
        public void SearchContainsParameter()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("name:contains=EURL")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: name={organization.Name}");
            }
        }

        /**
         * This sample demonstrates how to fetch with exact parameter
         */
        [Test]
        public void SearchExactParameter()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("name:exact=Gautier EURL")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} name={organization.Name}");
            }
        }

        /**
         * This sample demonstrates how to fetch with token parameter
         */
        [Test]
        public void SearchTokenCode()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("identifier=org-org-148")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} name={organization.Name}");
            }
        }
        
        /**
         * This sample demonstrates how to fetch with date parameter
         */
        [Test]
        public void SearchByDate()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("_lastUpdated=ge2022-08-05T14:51:04")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} lastUpdate={organization.Meta.LastUpdated.Value}");
            }
        }

        /**
         * This sample demonstrates how to fetch with "and" operand parameter
         */
        [Test]
        public void SearchAndParameter()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("name:contains=Renard").Add("name:contains", "et")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} | name={organization.Name}");
            }
        }

        /**
         * This sample demonstrates how to fetch with "or" operand parameter
         */
        [Test]
        public void SearchOrParameter()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            // create the name search parameter :
            var q = new SearchParams()
              .Where("name:contains=Renard,et")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} | name={organization.Name}");
            }
        }

    }
}
