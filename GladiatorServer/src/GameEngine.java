import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameEngine {
    Server server;
    ArrayList<Player> players;
    ExecutorService cachedPool = Executors.newCachedThreadPool();
    int playersDefaultSpeed = 5;
    int playersDefaultHealth = 100;
    Map<Integer, Integer> startPositions;
    ArrayList<Integer> xPositions, yPositions;

    public GameEngine(Server server) {
        this.server = server;
        players = new ArrayList<>();
        xPositions = new ArrayList<>(10);
        yPositions = new ArrayList<>(10);
        generateStartPositions();
    }

    public void generateStartPositions() {
        addPosition(100, 375);
        addPosition(153, 230);
        addPosition(245, 145);
        addPosition(385, 85);
        addPosition(535, 155);
        addPosition(620, 240);
        addPosition(665, 380);
        addPosition(610, 540);
        addPosition(390, 645);
        addPosition(240, 515);
    }

    public void addPosition(int x, int y) {
        xPositions.add(x);
        yPositions.add(y);
    }

    public void getPlayers() {

    }


}
