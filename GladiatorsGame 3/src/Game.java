import java.util.ArrayList;

public class Game{

    public ArrayList<Player> players;
    private CollisionGrid collisionGrid;
    public ArrayList<Player> playersToBeRemoved;

    public Game() {
        playersToBeRemoved = new ArrayList<>();
        players = new ArrayList<>();
        Field.generateValues("images/gladiator_arena_map.png");
        collisionGrid = new CollisionGrid();
        collisionGrid.loadGrid("res/maps/saveFile2.txt");
        players.add(new Player("Euclides", collisionGrid, Field.screenWidth / 4, Field.screenHeight / 2, Direction.RIGHT, this));
        players.add(new Player("World", collisionGrid, Field.screenWidth/4 * 3, Field.screenHeight / 2, Direction.LEFT, this));
    }



    public void run() {
        while(true) {
            update();
            draw();
            try {
                Thread.sleep(41);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {

        for(Player player : players) {
            if (!player.isDead()) {
                player.update();
            } else {
                playersToBeRemoved.add(player);
            }

        }

        for (Player player: playersToBeRemoved) {
            player.playerPic.delete();
            player.hitbox.delete();
            player.damageBoxRight.delete();
            player.damageBoxLeft.delete();
        }

        players.removeAll(playersToBeRemoved);
        playersToBeRemoved.removeAll(playersToBeRemoved);

    }
    public void draw() {

    }
}
