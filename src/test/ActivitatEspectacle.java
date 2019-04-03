package test;

/**
 * Dissenya una classe thread-safe que permeti reservar entrades.
 * Un espectacle té un aforament i diverses sessions.
 * Protegeix el sistema de reserves per evitar race conditions sobre cada 
 * sessió.
 * Simula els compradors accedint al sistema de reserves.
 */
public class ActivitatEspectacle {
    
    interface Espectacle {
        
        int lliures(int sessio);
        String[] reservar(int sessio, int numseients);
    }
}
