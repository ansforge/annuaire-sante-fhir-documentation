Extension: DevicePeriodImplantation
Id: Device-PeriodImplantation
* ^meta.lastUpdated = "2022-07-25T17:42:01.4253448+00:00"
* ^version = "0.3"
* ^status = #draft
* ^publisher = "ANS"
* ^contact.name = "monserviceclient.annuaire@esante.gouv.fr"
* ^context.type = #element
* ^context.expression = "Device"
* . ^short = "Dates de mise en oeuvre de l'EML"
* value[x] only Period
* value[x].start ^short = "Date de mise en œuvre"
* value[x].end ^short = "Date de fin de mise en œuvre"