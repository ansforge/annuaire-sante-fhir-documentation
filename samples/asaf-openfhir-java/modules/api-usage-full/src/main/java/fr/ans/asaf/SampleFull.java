package fr.ans.asaf;

import fr.ans.asaf.service.SyncService;


/**
 * Sample application to play with some demonstration code
 *
 * @author Guillaume Poulériguen
 * @since 1.0.0
 */
public class SampleFull {

    /**
     * The entry point of the application.
     * Feel free to uncomment the feature you want to test
     */
    public static void main(String[] args){
        var syncService = new SyncService();

        // Feature 1: Synchronization of PractitionerRole only:
        //syncService.syncFullSimple();
        // Feature 2: Synchronization of PractionerRole and Practitioner and Organization:
        syncService.syncFullWithRelatedObjects();

    }

}
