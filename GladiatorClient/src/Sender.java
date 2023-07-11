import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender implements Runnable {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public Sender(Socket socket) {
        try {

            this.socket = socket;
            in = new BufferedReader((new InputStreamReader(System.in)));
            out = new PrintWriter(socket.getOutputStream(), true);

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
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
