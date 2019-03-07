/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

/**
 *
 * @author julian
 */
public class SimpleWaitNotifyTest {

    public static void main(String[] args) throws InterruptedException {
        Fill fill = new Fill();
        fill.start();
        synchronized (fill) {
            System.out.println("Waiting for fill to complete...");
            while (!fill.done) {
                fill.wait();
            }
            System.out.println("Total is: " + fill.total);
        }
    }
}

class Fill extends Thread {

    int total;
    boolean done = false;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                total += i;
            }
            done = true;
            notify();
        }
    }
}
