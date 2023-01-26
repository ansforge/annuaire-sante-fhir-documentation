package fr.ans.asaf;

import fr.ans.asaf.service.RequestService;

/**
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SampleFull {

    public static void main(String[] args) {
        var requestService = new RequestService();

        //requestService.getMedicoSociauxFiness();
        //requestService.getHospitals();
        requestService.getBiologyOrganization();
    }

}
