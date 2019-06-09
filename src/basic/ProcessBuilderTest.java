package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author julian
 */
public class ProcessBuilderTest {

    public static void main(String[] args) throws InterruptedException,
            IOException {
        
        //ProcessBuilder pb = new ProcessBuilder("echo", "hola, mon!");
        //ProcessBuilder pb = new ProcessBuilder("/bin/pwd");
        ProcessBuilder pb = new ProcessBuilder("/sbin/ping", "-n", "-c", "3", "google.com");
        System.out.println("executant " + pb.command());
        Process process = pb.start();
        int errCode = process.waitFor();
        System.out.println("ha funcionat? " + (errCode == 0 ? "Sí" : "No"));
        System.out.println("sortida:\n" + output(process.getInputStream()));
        System.out.println("error:\n" + output(process.getErrorStream()));
    }

    private static String output(InputStream inputStream) throws IOException {
        
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }
}
