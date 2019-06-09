
package exam;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author julian
 */
public class Prova2 {
    
    static final int MAXRESERVA = 3;
    static final int COUNT = 100;
    
    public static void main(String[] args) {
        
        // objecte compartit pels compradors
        Espectacle e = new MeuEspectacle(3, 15);   
        
        // crear COUNT compradors que reserven alhora        
        Thread[] threads = new Thread[COUNT];        
        for (int i=0; i<COUNT; i++) {
            threads[i] = new Thread(new Comprador(e));
        }
        
        // executar els compradors
        for (int i=0; i<COUNT; i++) {
            threads[i].start();
        }
        
        // esperar
        for (int i=0; i<COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        // mostrar l'objecte
        System.out.println("FINAL: " + e);
    }
    
    /**
     * Simulador de compra
     */
    static class Comprador implements Runnable {
        
        private Espectacle e;
        
        public Comprador(Espectacle e) {
            this.e = e;
        }
        
        private void dormir(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }            

        @Override
        public void run() {
            
            Random r = new Random();
            
            // el comprador es pren un temps per decidir-se
            dormir(r.nextInt(2500) + 500);
            
            // una sessio aleatoria
            int sessio = r.nextInt(e.getSessions());
            int free = e.getLliures(sessio);
            if (free == 0) {
                // completa: finalizar
                System.out.println("sessio completa: " + sessio);
                return;
            }
            
            // reserva màxima de MAXRESERVA
            int seients = r.nextInt(free) + 1;
            if (seients > MAXRESERVA) {
                seients = MAXRESERVA;
            }
            
            // el comprador es pren un temps per decidir-se
            dormir(r.nextInt(2500) + 500);
            
            // intenta reservar
            String[] reserves = e.reserva(sessio, seients);
            if (reserves == null) {
                System.out.println("reserva impossible: " + sessio + "(" + seients + ")");
            }
            else {
                System.out.println("reservats: " + sessio + "(" + seients + ")" + Arrays.toString(reserves));
            }
        }
    }
        
    /**
     * Espectacle
     */
    static class MeuEspectacle implements Espectacle {
        
        public MeuEspectacle(int sessions, int seients) {
            // completar
        }

        @Override
        public int getSessions() {
            throw new RuntimeException("no implementat!");
        }

        @Override
        public int getSeients() {
            throw new RuntimeException("no implementat!");
        }

        @Override
        public int getLliures(int sessio) {
            throw new RuntimeException("no implementat!");
        }

        @Override
        public String[] reserva(int sessio, int seients) {
            throw new RuntimeException("no implementat!");
        }

        @Override
        public boolean entrada(int sessio, String codi) {
            throw new RuntimeException("no implementat!");
        }
        
        @Override
        public String toString() {
            // pots canviar-ho, si vols
            return super.toString();                    
        }
    }

    interface Espectacle {

        int getSessions();
        int getSeients();
        int getLliures(int sessio);
        String[] reserva(int sessio, int seients);
        boolean entrada(int sessio, String codi);
    }    
}
