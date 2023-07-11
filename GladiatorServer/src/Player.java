import java.io.IOException;

public class Player {

    ClientHandler clientHandler;
    GameEngine gameEngine;
    public String name;
    public int speed;
    public int xPos;
    public int yPos;
    private int health;
    public boolean isDead;

    public Player(ClientHandler clientHandler, GameEngine gameEngine) {
        try {

            this.clientHandler = clientHandler;
            this.gameEngine = gameEngine;
            clientHandler.getOut().println("What's your name?");
            name = clientHandler.getIn().readLine();
            speed = gameEngine.playersDefaultSpeed;
            health = gameEngine.playersDefaultHealth;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPosition() {
        xPos = gameEngine.xPositions.get(gameEngine.players.indexOf(this));
        yPos = gameEngine.yPositions.get(gameEngine.players.indexOf(this));
        clientHandler.getOut().println(xPos + " " + yPos);
    }

    public void createCharacter() {

    }

}
