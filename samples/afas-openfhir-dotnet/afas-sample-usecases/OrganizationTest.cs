using NUnit.Framework;
using Hl7.Fhir.Model;
using System;
using Hl7.Fhir.Rest;


namespace sample_usecases_tests
{
    class OrganizationTest
    {

        /**
         * Search all organizations
         */
        [Test]
        public void SearchAll()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var bundle = client.Search<Organization>();
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} name={organization.Name}");
            }
        }



        /**
         * Search by lastÛpdated
         */
        [Test]
        public void SearchModifiedFrom()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("_lastUpdated=ge2022-08-05T14:51:04")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} lastUpdate={organization.Meta.LastUpdated}");
            }
        }

        /**
         * Search by identifiers
         */
        [Test]
        public void SearchByIdentifiers()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("identifier=001604103000,01603998400,001604252500")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value}");
            }
        }

        /**
         * Search by Finess number
         */
        [Test]
        public void SearchByFinessNumber()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("identifier=https://finess.esante.gouv.fr|010000602,https://finess.esante.gouv.fr|010000628")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: id={organization.IdElement.Value}");
            }
        }
        
        /**
         * Search by type
         */
        [Test]
        public void SearchByType()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("type=http://interopsante.org/fhir/CodeSystem/fr-v2-3307|GEOGRAPHICAL-ENTITY")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                var types = "";
                foreach(var type in organization.Type)
                {
                    types = types + " - " +type.Coding[0].Code;
                }
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} type={types}");
            }
        }


        /**
         * Search by type (INSEE)
         */
        [Test]
        public void SearchByINSEENomenclature()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("type=https://mos.esante.gouv.fr/NOS/TRE_R75-InseeNAFrev2Niveau5/FHIR/TRE-R75-InseeNAFrev2Niveau5|82.19Z")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                var types = "";
                foreach (var type in organization.Type)
                {
                    types = types + " - " + type.Coding[0].Code;
                }
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} type={types}");
            }
        }



        /**
         * Search by activity type
         */
        [Test]
        public void SearchByActivity()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("type=https://mos.esante.gouv.fr/NOS/TRE_R02-SecteurActivite/FHIR/TRE-R02-SecteurActivite|SA29")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                var types = "";
                foreach (var type in organization.Type)
                {
                    types = types + " - " + type.Coding[0].Code;
                }
                Console.WriteLine($"Organization found: id={organization.IdElement.Value} activity={types}");
            }
        }
        
        /**
         * Search by name
         */
        [Test]
        public void SearchByNameTwoTerms()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("name:contains=imagerie").Add("name:contains", "centre")
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
         * Search by zip code
         */
        [Test]
        public void SearchByZipCode()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("address-postalcode=13290,13290")
              .LimitTo(50);
            var bundle = client.Search<Organization>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var organization = be.Resource as Organization;
                Console.WriteLine($"Organization found: name={organization.Name} | zipCode={organization.Address[0].PostalCode}");
            }
        }

    }
}
