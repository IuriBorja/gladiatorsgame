import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private int port;
    private GameEngine gameEngine;
    private List<String> clientsData;
    private ExecutorService cachedPool = Executors.newCachedThreadPool();

    public Server() {
        try {
            port = 6001;
            serverSocket = new ServerSocket(port);
            clientsData = Collections.synchronizedList(new ArrayList<>());
            gameEngine = new GameEngine(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        awaitClients();
    }

    public void awaitClients() {
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket, this);
                Player player = new Player(client, gameEngine);
                gameEngine.players.add(player);
                player.setPosition();
                System.out.println(player.xPos);
                System.out.println(player.yPos);
                String clientData = "" + player.name + " " + gameEngine.playersDefaultHealth +
                                    " " + gameEngine.playersDefaultSpeed +
                                    " " + player.xPos + " " + player.yPos +
                                    " " + (player.isDead ? 1 : 0);

                clientsData.add(clientData);
                cachedPool.submit(client);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
