import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver implements Runnable {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    Client client;

    public Receiver(Socket socket, Client client) {
        try {
            this.client = client;
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(System.out, true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {
            while(true){
                out.println(in.readLine());
            }
        } catch (IOException e ) {
          e.printStackTrace();
        }

    }
}
