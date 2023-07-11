import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Server server;
    private final BufferedReader in;
    private BufferedReader fileReader;
    private  PrintWriter fileWriter;
    private final PrintWriter out;


    public ClientHandler(Socket socket, Server server) {
        try {

            this.socket = socket;
            this.server = server;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            fileReader = new BufferedReader(new FileReader("database/dataplayers.txt"));
            fileWriter = new PrintWriter(new FileWriter("database/dataplayers.txt"),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {


        //fileWriter.println(fileReader.readLine());
        try {
           while (true) {
               out.println(in.readLine());
             }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }
}
