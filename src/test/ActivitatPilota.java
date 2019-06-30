package test;

import java.io.IOException;

/**
 * Has d'escriure dues classes que implementin Joc i Teclat.
 * El Joc reb pulsacions de tecles d'un Teclat per a canviar la direcció d'un 
 * objecte en 2D: amb una x i una y. Pot rebre quatre tecles: 
 * WSAD (pujar/baixar/esquerra/dreta). Si reb qualsevol altre tecla, no fa res.
 * Això és el que fa el fil de cada objecte:
 * - Joc: cada X millis, mou l'objecte una posició segons la direcció actual.
 * - Teclat: cada X millis, genera una pulsació cap el Joc segons el String "keys".
 * Quan s'acaba el String, s'acaba el Teclat i el Joc.
 * Pots enviar una tecla especial al Joc per indicar-li.
 */
public class ActivitatPilota {
    
    public static void main(String[] args) throws IOException {
        
        Joc joc = createJoc(100);
        Teclat teclat = createTeclat("W---S---A---D---", 1000);
        teclat.setListener(joc);
        
        new Thread(joc).start();
        new Thread(teclat).start();
    }
        
    interface Joc extends Runnable, KeyListener {        
    }
    
    interface Teclat extends Runnable, KeySource {        
    }
    
    interface KeySource {
        void setListener(KeyListener listener);
    }
    
    interface KeyListener {
        void push(char key);
    }
    
    // IMPLEMENTS
    
    private static Joc createJoc(long interval) {
        throw new RuntimeException("no implementat!");
    }

    private static Teclat createTeclat(String keys, long interval) {
        throw new RuntimeException("no implementat!");
    }
        
}
