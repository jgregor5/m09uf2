
package exam;

/**
 *
 * @author julian
 */
public class Prova1 {
    
    public static void main(String[] args) throws InterruptedException {

        // COMPLETAR
    }
    
    // NO MODIFICAR
    
    interface EmissorImpuls extends Runnable {

        void setRetard(long retard);
        void setReceptor(ReceptorImpuls receptor);
        void parar();
    }

    interface ReceptorImpuls extends Runnable {

        void impuls();
    }
}
