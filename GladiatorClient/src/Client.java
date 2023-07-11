import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    Socket socket;
    int port;
    String address;
    ExecutorService cachedPool = Executors.newCachedThreadPool();
    ArrayList<Player> players;


    public Client() {
        try {

            port = 6001;
            address = "localhost";
            socket = new Socket(address, port);
            players = new ArrayList<>();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        startConnection();
    }

    public void startConnection() {
        cachedPool.submit(new Sender(socket));
        cachedPool.submit(new Receiver(socket, this));
    }




}
