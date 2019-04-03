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
        return new MeuJoc(interval);
    }

    private static Teclat createTeclat(String keys, long interval) {
        return new MeuTeclat(keys, interval);
    }
    
    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }    
    
    static class MeuJoc implements Joc {  
        
        private long interval;
        private int x, y, vx, vy;
        private boolean stop;

        
        public MeuJoc(long interval) {        
            this.interval = interval;
            this.x = 0;
            this.y = 0;
            this.vx = 0;
            this.vy = 0;
            this.stop = false;            
        }

        @Override
        public void run() {
            while (!this.stop) {                
                this.x += this.vx;
                this.y += this.vy;
                sleep(this.interval);
                System.out.println(x + " | " + y);
            }            
        }

        @Override
        public void push(char key) {
            switch (key) {
                case 'W':
                    this.vy = 1;
                    this.vx = 0;
                    break;
                case 'S':
                    this.vy = -1;
                    this.vx = 0;
                    break;
                case 'A':
                    this.vx = -1;
                    this.vy = 0;
                    break;
                case 'D':
                    this.vx = 1;
                    this.vy = 0;
                    break;
                case 0: 
                    stop = true;
            }            
        }
    }
    
    static class MeuTeclat implements Teclat {
        
        private KeyListener listener;
        private long interval;
        private String keys;
        
        public MeuTeclat(String keys, long interval) { 
            this.keys = keys;
            this.interval = interval;
        }

        @Override
        public void run() {
            int index = 0;
            while (index < this.keys.length()) {
                char key = this.keys.charAt(index ++);
                System.out.println("> " + key);
                this.listener.push(key);
                sleep(this.interval);
            }
            
            this.listener.push((char) 0);            
        }

        @Override
        public void setListener(KeyListener listener) {
            this.listener = listener;
        }        
    }
    
}
