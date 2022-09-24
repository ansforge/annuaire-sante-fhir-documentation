using NUnit.Framework;
using Hl7.Fhir.Model;
using System;
using Hl7.Fhir.Rest;

namespace sample_usecases_tests
{
    class PractitionerRoleTest
    {


        /**
         * Search all PractitionerRole
         */
        [Test]
        public void SearchAll()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var bundle = client.Search<PractitionerRole>();
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;
                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={practitionerRole.Code[0].Coding[0].Code}");
            }
        }

        /**
         * Get by Id
         */
        [Test]
        public void GetById()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var practitionerRole = client.Read<PractitionerRole>("PractitionerRole/005-5087586-6923328");
            Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value}");
        }


        /**
         * Search practitioner role by role
         */
        [Test]
        public void SearchByRoles()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("role=40").Add("role","E")
              .LimitTo(50);

            var bundle = client.Search<PractitionerRole>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;

                var roleCodes = "";
                foreach(var c in practitionerRole.Code)
                {
                    var codings = "";
                    foreach(var code in c.Coding)
                    {
                        codings = codings + code.System + ":" + code.Code + "|";
                    }
                    roleCodes = roleCodes + " - " + codings;
                }

                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={roleCodes}");
            }
        }


        /**
         * Search practitioner role by speciality
         */
        [Test]
        public void SearchBySpeciality()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();

            var q = new SearchParams()
              .Where("role=40").Add("specialty", "SCD01")
              .LimitTo(50);

            var bundle = client.Search<PractitionerRole>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;

                var roleCodes = "";
                // concat roles
                foreach(var c in practitionerRole.Code)
                {
                    var codings = "";
                    foreach(var code in c.Coding)
                    {
                        codings = codings + code.System + ":" + code.Code + "|";
                    }
                    roleCodes = roleCodes + " - " + codings;
                }

                // concat specialty
                foreach (var c in practitionerRole.Specialty)
                {
                    var codings = "";
                    foreach (var code in c.Coding)
                    {
                        codings = codings + code.System + ":" + code.Code + "|";
                    }
                    roleCodes = roleCodes + " - " + codings;
                }

                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={roleCodes}");
            }
        }




        /**
         * Search practitioner role by smart card type
         */
        [Test]
        public void SearchBySmartCardType()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            var q = new SearchParams()
              .Where("type-smartcard=CPS")
              .LimitTo(50);
            var bundle = client.Search<PractitionerRole>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;
                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value}");
            }
        }

        /**
         * Search active practitioner roles
         */
        [Test]
        public void SearchActive()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            var q = new SearchParams()
              .Where("active=true")
              .LimitTo(50);
            var bundle = client.Search<PractitionerRole>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;
                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} active={practitionerRole.Active.Value}");
            }
        }


        /**
         * Search practitioner role by practitioner
         */
        [Test]
        public void SearchByPractitioner()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            var q = new SearchParams()
              .Where("practitioner=003-138020")
              .LimitTo(50);
            var bundle = client.Search<PractitionerRole>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;
                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} practitioner={practitionerRole.Practitioner.Reference}");
            }
        }


        /**
         * Search practitioner roles by role
         */
        [Test]
        public void SearchByRole()
        {
            // create the client:
            var client = FhirTestUtils.CreateClient();
            var q = new SearchParams()
              .Where("role=10")
              .LimitTo(50);
            var bundle = client.Search<PractitionerRole>(q);
            foreach (var be in bundle.Entry)
            {
                // print ids:
                var practitionerRole = be.Resource as PractitionerRole;
                Console.WriteLine($"PractitionerRole found: id={practitionerRole.IdElement.Value} code={practitionerRole.Code[0].Coding[0].Code}");
            }
        }
    }
}
