import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;


public class Player {

    public String name;
    public int speed;
    public Picture playerPic;
    public Rectangle hitbox;
    public DamageBox damageBoxLeft;
    public DamageBox damageBoxRight;
    public Game game;
    public CollisionGrid collisionGrid;
    public KeyHandler keyHandler;
    private boolean isWalking;
    private boolean isAttacking;
    private boolean isGetingHit;
    private boolean isDead;
    private int health;
    private ArrayList<Player> players;
    private int hashCode;



    //leftSprites
    private String[] leftAttackSprites = {"left_atk01.png", "left_atk02.png", "left_atk03.png", "left_atk04.png", "left_atk05.png", "left_atk06.png"};
    private String[] leftDamageSprites = {"left_getDamage01.png", "left_getDamage02.png", "left_getDamage03.png"};
    private String[] leftDeathSprites = {"left_getDeath01.png", "left_getDeath02.png", "left_getDeath03.png", "left_getDeath04.png", "left_getDeath05.png", "left_getDeath06.png", "left_getDeath07.png"};
    private String[] leftStaySprites = {"left_getStay01.png", "left_getStay02.png", "left_getStay03.png", "left_getStay04.png", "left_getStay05.png", "left_getStay06.png"};
    private String[] leftWalkSprites = {"left_getWalk01.png", "left_getWalk02.png", "left_getWalk03.png", "left_getWalk04.png", "left_getWalk05.png", "left_getWalk06.png", "left_getWalk07.png", "left_getWalk08.png"};


    //rightSprites
    private String[] rightAttackSprites = {"right_atk01.png", "right_atk02.png", "right_atk03.png", "right_atk04.png", "right_atk05.png", "right_atk06.png"};
    private String[] rightDamageSprites = {"right_getDamage01.png", "right_getDamage02.png", "right_getDamage03.png"};
    private String[] rightDeathSprites = {"right_getDeath01.png", "right_getDeath02.png", "right_getDeath03.png", "right_getDeath04.png", "right_getDeath05.png", "right_getDeath06.png", "right_getDeath07.png"};
    private String[] rightStaySprites = {"right_getStay01.png", "right_getStay02.png", "right_getStay03.png", "right_getStay04.png", "right_getStay05.png", "right_getStay06.png"};
    private String[] rightWalkSprites = {"right_getWalk01.png", "right_getWalk02.png", "right_getWalk03.png", "right_getWalk04.png", "right_getWalk05.png", "right_getWalk06.png", "right_getWalk07.png", "right_getWalk08.png"};



    private Direction damageDirection;
    private Direction currentDirection;
    private int spriteCounter;
    private int spriteCounterAttacking;
    private int spriteCounterGetHit;
    private int spriteCounterDie;


    public Player (String name, CollisionGrid collisionGrid, int x, int y, Direction direction, Game game){
        this.game = game;
        players = game.players;
        this.collisionGrid = collisionGrid;
        this.name = name;
        keyHandler = new KeyHandler(this);
        playerPic = new Picture(x, y, "images/tile001.png");
        currentDirection = direction;
        switch (currentDirection){
            case LEFT:
                playerPic.load("/left_sprites/" + leftStaySprites[0]);
                break;
            case RIGHT:
                playerPic.load("/right_sprites/" + rightStaySprites[0]);
                break;
        }

        playerPic.draw();
        hitbox = new Rectangle(playerPic.getX() + playerPic.getWidth() / 2 - 8, playerPic.getY() + playerPic.getHeight() / 2 + 3,Field.CELL_SIZE * 2,Field.CELL_SIZE * 2.5);
        damageBoxLeft = new DamageBox(playerPic.getX(), playerPic.getY() + 15, hitbox.getX() - playerPic.getX(), playerPic.getHeight() - 15);
        damageBoxRight = new DamageBox(hitbox.getX() + hitbox.getWidth(), playerPic.getY() + 15, hitbox.getX() - playerPic.getX(), playerPic.getHeight() - 15);
        speed = 5;
        spriteCounter = 0;
        isWalking = false;
        hashCode = this.hashCode();
        isAttacking = false;
        health = 100;


    }


    public void update() {


        if(keyHandler.up) {
            isWalking = true;
            playerPic.translate(0, -speed);
            hitbox.translate(0, -speed);
            damageBoxRight.translate(0, -speed);
            damageBoxLeft.translate(0, -speed);
            if(checkTileCollision(Direction.UP) || hitbox.getY() < Field.PADDING + 18 || checkPlayerCollision(Direction.UP)){
                playerPic.translate(0, speed);
                hitbox.translate(0, speed);
                damageBoxRight.translate(0, speed);
                damageBoxLeft.translate(0, speed);
            }
        }
        if(keyHandler.down){
            isWalking = true;
            playerPic.translate(0, speed);
            hitbox.translate(0, speed);
            damageBoxRight.translate(0, speed);
            damageBoxLeft.translate(0, speed);
            if(checkTileCollision(Direction.DOWN) || hitbox.getY() + hitbox.getHeight() > Field.screenHeight + Field.PADDING || checkPlayerCollision(Direction.DOWN)){
                playerPic.translate(0, -speed);
                hitbox.translate(0, -speed);
                damageBoxRight.translate(0, -speed);
                damageBoxLeft.translate(0, -speed);
            }
        }
        if(keyHandler.left) {
            isWalking = true;
            currentDirection = Direction.LEFT;
            playerPic.translate(-speed, 0);
            hitbox.translate(-speed, 0);
            damageBoxRight.translate(-speed, 0);
            damageBoxLeft.translate(-speed, 0);
            if(checkTileCollision(Direction.LEFT) || hitbox.getX() < Field.PADDING + 20 || checkPlayerCollision(Direction.LEFT)){
                playerPic.translate(speed,0);
                hitbox.translate(speed,0);
                damageBoxRight.translate(speed, 0);
                damageBoxLeft.translate(speed, 0);
            }

        }
        if(keyHandler.right){
            isWalking = true;
            currentDirection = Direction.RIGHT;
            playerPic.translate(speed, 0);
            hitbox.translate(speed, 0);
            damageBoxRight.translate(speed, 0);
            damageBoxLeft.translate(speed, 0);
            if(checkTileCollision(Direction.RIGHT) || hitbox.getX() + hitbox.getWidth() > Field.screenWidth + Field.PADDING - 20 || checkPlayerCollision(Direction.RIGHT)){
                playerPic.translate(-speed,0);
                hitbox.translate(-speed,0);
                damageBoxRight.translate(-speed, 0);
                damageBoxLeft.translate(-speed, 0);
            }
        }
        if(keyHandler.attack){
            isAttacking = true;

        }

        if (!keyHandler.up && !keyHandler.down && !keyHandler.left && !keyHandler.right) {
            isWalking = false;
        }

        if(isAttacking) {
            checkAttacking();
        } else if (isGetingHit) {

            if (health > 0) {
                getHit(10);
            } else {
                die();
            }
        } else {
            checkSprites();
        }


    }
    public boolean checkTileCollision(Direction direction){

        switch(direction) {
            case UP:
                for (int row = 0; row < Field.maxRows;row++){
                    for (int col = 0;col < Field.maxCols;col++){
                        if(collisionGrid.grid[row][col] != null){
                            if((hitbox.getY() <= collisionGrid.grid[row][col].getY() + collisionGrid.grid[row][col].getHeight()) && (hitbox.getY() >= collisionGrid.grid[row][col].getY())){
                                if((hitbox.getX() >= collisionGrid.grid[row][col].getX()) && (hitbox.getX() <= collisionGrid.grid[row][col].getX() + collisionGrid.grid[row][col].getWidth())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.DOWN;
                                    }
                                    return true;
                                }
                                if((hitbox.getX() + hitbox.getWidth() >= collisionGrid.grid[row][col].getX()) && (hitbox.getX() + hitbox.getWidth() <= collisionGrid.grid[row][col].getX() + collisionGrid.grid[row][col].getWidth())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.DOWN;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case DOWN:
                for (int row = 0; row < Field.maxRows;row++){
                    for (int col = 0;col < Field.maxCols;col++){
                        if(collisionGrid.grid[row][col] != null){
                            if((hitbox.getY() + hitbox.getHeight() <= collisionGrid.grid[row][col].getY() + collisionGrid.grid[row][col].getHeight()) && (hitbox.getY() + hitbox.getHeight() >= collisionGrid.grid[row][col].getY())){
                                if((hitbox.getX() >= collisionGrid.grid[row][col].getX()) && (hitbox.getX() <= collisionGrid.grid[row][col].getX() + collisionGrid.grid[row][col].getWidth())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.UP;
                                    }
                                    return true;
                                }
                                if((hitbox.getX() + hitbox.getWidth() >= collisionGrid.grid[row][col].getX()) && (hitbox.getX() + hitbox.getWidth() <= collisionGrid.grid[row][col].getX() + collisionGrid.grid[row][col].getWidth())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.UP;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }

                break;
            case LEFT:
                for (int row = 0; row < Field.maxRows;row++){
                    for (int col = 0;col < Field.maxCols;col++){
                        if(collisionGrid.grid[row][col] != null){
                            if((hitbox.getX() >= collisionGrid.grid[row][col].getX()) && (hitbox.getX() <= collisionGrid.grid[row][col].getX() + collisionGrid.grid[row][col].getWidth())){
                                if((hitbox.getY() + hitbox.getHeight() <= collisionGrid.grid[row][col].getY() + collisionGrid.grid[row][col].getHeight()) && (hitbox.getY() + hitbox.getHeight() >= collisionGrid.grid[row][col].getY())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.RIGHT;
                                    }
                                    return true;
                                }
                                if((hitbox.getY() >= collisionGrid.grid[row][col].getY()) && (hitbox.getY() <= collisionGrid.grid[row][col].getY() + collisionGrid.grid[row][col].getHeight())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.RIGHT;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }


                break;
            case RIGHT:
                for (int row = 0; row < Field.maxRows;row++){
                    for (int col = 0;col < Field.maxCols;col++){
                        if(collisionGrid.grid[row][col] != null){
                            if((hitbox.getX() + hitbox.getWidth() >= collisionGrid.grid[row][col].getX()) && (hitbox.getX() + hitbox.getWidth() <= collisionGrid.grid[row][col].getX() + collisionGrid.grid[row][col].getWidth())){
                                if((hitbox.getY() + hitbox.getHeight() <= collisionGrid.grid[row][col].getY() + collisionGrid.grid[row][col].getHeight()) && (hitbox.getY() + hitbox.getHeight() >= collisionGrid.grid[row][col].getY())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.LEFT;
                                    }
                                    return true;
                                }
                                if((hitbox.getY() >= collisionGrid.grid[row][col].getY()) && (hitbox.getY() <= collisionGrid.grid[row][col].getY() + collisionGrid.grid[row][col].getHeight())){
                                    if (collisionGrid.grid[row][col].damage) {
                                        isGetingHit = true;
                                        damageDirection = Direction.LEFT;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }

                break;
        }
    return false;
    }
    public boolean checkPlayerCollision(Direction direction){

        for (int i = 0; i < players.size(); i++) {

            if (players.get(i).hashCode != hashCode) {
                Rectangle playerHitBox = players.get(i).hitbox;
                switch (direction) {
                    case UP:
                        if (hitbox.getY() >= playerHitBox.getY() && hitbox.getY() <= playerHitBox.getY() + playerHitBox.getHeight()) {
                            if (hitbox.getX() >= playerHitBox.getX() && hitbox.getX() <= playerHitBox.getX() + playerHitBox.getWidth()) {
                                return true;
                            }
                            if (hitbox.getX() + hitbox.getWidth() >= playerHitBox.getX() && hitbox.getX() + hitbox.getWidth() <= playerHitBox.getX() + playerHitBox.getWidth()) {
                                return true;
                            }
                        }
                        break;
                    case DOWN:
                        if (hitbox.getY() + hitbox.getHeight() >= playerHitBox.getY() && hitbox.getY() + hitbox.getHeight() <= playerHitBox.getY() + playerHitBox.getHeight()) {
                            if (hitbox.getX() >= playerHitBox.getX() && hitbox.getX() <= playerHitBox.getX() + playerHitBox.getWidth()) {
                                return true;
                            }
                            if (hitbox.getX() + hitbox.getWidth() >= playerHitBox.getX() && hitbox.getX() + hitbox.getWidth() <= playerHitBox.getX() + playerHitBox.getWidth()) {
                                return true;
                            }
                        }
                        break;
                    case LEFT:
                        if (hitbox.getX() >= playerHitBox.getX() && hitbox.getX() <= playerHitBox.getX() + playerHitBox.getWidth()) {
                            if (hitbox.getY() + hitbox.getHeight() <= playerHitBox.getY() + playerHitBox.getHeight() && hitbox.getY() + hitbox.getHeight() >= playerHitBox.getY()) {
                                return true;
                            }
                            if (hitbox.getY() <= playerHitBox.getY() + playerHitBox.getHeight() && hitbox.getY() >= playerHitBox.getY()) {
                                return true;
                            }
                        }
                        break;
                    case RIGHT:
                        if (hitbox.getX() + hitbox.getWidth() >= playerHitBox.getX() && hitbox.getX() + hitbox.getWidth() <= playerHitBox.getX() + playerHitBox.getWidth()) {
                            if (hitbox.getY() >= playerHitBox.getY() && hitbox.getY() <= playerHitBox.getY() + playerHitBox.getHeight()) {
                                return true;
                            }
                            if (hitbox.getY() + hitbox.getHeight() >= playerHitBox.getY() && hitbox.getY() + hitbox.getHeight() <= playerHitBox.getY() + playerHitBox.getHeight()) {
                                return true;
                            }
                        }
                }
            }
        }

        return false;




    }
    public void checkSprites(){


        if(spriteCounter == 0){
            spriteCounter = 24;
        }
        if (isWalking) {

            switch (currentDirection) {
                case LEFT:
                    if(spriteCounter > 21) {
                        playerPic.load("left_sprites/" + leftWalkSprites[0]);
                    } else if (spriteCounter > 18) {
                        playerPic.load("left_sprites/" + leftWalkSprites[1]);
                    } else if (spriteCounter > 15) {
                        playerPic.load("left_sprites/" + leftWalkSprites[2]);
                    } else if (spriteCounter > 12) {
                        playerPic.load("left_sprites/" + leftWalkSprites[3]);
                    } else if (spriteCounter > 9) {
                        playerPic.load("left_sprites/" + leftWalkSprites[4]);
                    } else if (spriteCounter > 6) {
                        playerPic.load("left_sprites/" + leftWalkSprites[5]);
                    } else if (spriteCounter > 3) {
                        playerPic.load("left_sprites/" + leftWalkSprites[6]);
                    } else if (spriteCounter > 0) {
                        playerPic.load("left_sprites/" + leftWalkSprites[7]);
                    }
                    break;
                case RIGHT:
                    if(spriteCounter > 21) {
                        playerPic.load("right_sprites/" + rightWalkSprites[0]);
                    } else if (spriteCounter > 18) {
                        playerPic.load("right_sprites/" + rightWalkSprites[1]);
                    } else if (spriteCounter > 15) {
                        playerPic.load("right_sprites/" + rightWalkSprites[2]);
                    } else if (spriteCounter > 12) {
                        playerPic.load("right_sprites/" + rightWalkSprites[3]);
                    } else if (spriteCounter > 9) {
                        playerPic.load("right_sprites/" + rightWalkSprites[4]);
                    } else if (spriteCounter > 6) {
                        playerPic.load("right_sprites/" + rightWalkSprites[5]);
                    } else if (spriteCounter > 3) {
                        playerPic.load("right_sprites/" + rightWalkSprites[6]);
                    } else if (spriteCounter > 0) {
                        playerPic.load("right_sprites/" + rightWalkSprites[7]);
                    }
                    break;
            }

        }

        if (!isWalking) {

            switch (currentDirection) {
                case LEFT:
                    if (spriteCounter > 20) {
                        playerPic.load("left_sprites/" + leftStaySprites[0]);
                    } else if (spriteCounter > 16) {
                        playerPic.load("left_sprites/" + leftStaySprites[1]);
                    } else if (spriteCounter > 12) {
                        playerPic.load("left_sprites/" + leftStaySprites[2]);
                    } else if (spriteCounter > 8) {
                        playerPic.load("left_sprites/" + leftStaySprites[3]);
                    } else if (spriteCounter > 4) {
                        playerPic.load("left_sprites/" + leftStaySprites[4]);
                    } else if (spriteCounter > 0) {
                        playerPic.load("left_sprites/" + leftStaySprites[5]);
                    }
                    break;
                case RIGHT:
                    if (spriteCounter > 20) {
                        playerPic.load("right_sprites/" + rightStaySprites[0]);
                    } else if (spriteCounter > 16) {
                        playerPic.load("right_sprites/" + rightStaySprites[1]);
                    } else if (spriteCounter > 12) {
                        playerPic.load("right_sprites/" + rightStaySprites[2]);
                    } else if (spriteCounter > 8) {
                        playerPic.load("right_sprites/" + rightStaySprites[3]);
                    } else if (spriteCounter > 4) {
                        playerPic.load("right_sprites/" + rightStaySprites[4]);
                    } else if (spriteCounter > 0) {
                        playerPic.load("right_sprites/" + rightStaySprites[5]);
                    }
                    break;
            }
        }

        
        spriteCounter--;

    }
    public void checkAttacking() {

        if(spriteCounterAttacking == 0){
            spriteCounterAttacking = 18;
            System.out.println(playerPic.getX() + ", " + playerPic.getY());
        }


            switch (currentDirection) {
                case LEFT:
                    damageBoxLeft.isDamageable = true;
                    if(spriteCounterAttacking > 15) {
                        playerPic.load("left_sprites/" + leftAttackSprites[0]);
                    } else if (spriteCounterAttacking > 12) {
                        playerPic.load("left_sprites/" + leftAttackSprites[1]);
                    } else if (spriteCounterAttacking > 9) {
                        playerPic.load("left_sprites/" + leftAttackSprites[2]);
                    } else if (spriteCounterAttacking > 6) {
                        playerPic.load("left_sprites/" + leftAttackSprites[3]);
                    } else if (spriteCounterAttacking > 3) {
                        playerPic.load("left_sprites/" + leftAttackSprites[4]);
                    } else if (spriteCounterAttacking > 0) {
                        playerPic.load("left_sprites/" + leftAttackSprites[5]);
                    }
                    break;
                case RIGHT:
                    damageBoxRight.isDamageable = true;
                    if(spriteCounterAttacking > 15) {
                        playerPic.load("right_sprites/" + rightAttackSprites[0]);
                    } else if (spriteCounterAttacking > 12) {
                        playerPic.load("right_sprites/" + rightAttackSprites[1]);
                    } else if (spriteCounterAttacking > 9) {
                        playerPic.load("right_sprites/" + rightAttackSprites[2]);
                    } else if (spriteCounterAttacking > 6) {
                        playerPic.load("right_sprites/" + rightAttackSprites[3]);
                    } else if (spriteCounterAttacking > 3) {
                        playerPic.load("right_sprites/" + rightAttackSprites[4]);
                    } else if (spriteCounterAttacking > 0) {
                        playerPic.load("right_sprites/" + rightAttackSprites[5]);
                    }
                    break;
            }




        spriteCounterAttacking--;

        if(spriteCounterAttacking == 0){
            isAttacking = false;
            damageBoxLeft.isDamageable = false;
            damageBoxRight.isDamageable = false;
        }


    }
    public void getHit(int damage) {

        if (spriteCounterGetHit == 0) {
            spriteCounterGetHit = 9;
            health -= damage;
        }

        switch (damageDirection) {
            case DOWN:
                playerPic.translate(0,2);
                hitbox.translate(0,2);
                damageBoxLeft.translate(0,2);
                damageBoxRight.translate(0,2);
                break;
            case UP:
                playerPic.translate(0, -2);
                hitbox.translate(0,-2);
                damageBoxLeft.translate(0,-2);
                damageBoxRight.translate(0,-2);
                break;
            case RIGHT:
                playerPic.translate(2,0);
                hitbox.translate(2,0);
                damageBoxLeft.translate(2,0);
                damageBoxRight.translate(2,0);
                break;
            case LEFT:
                playerPic.translate(-2,0);
                hitbox.translate(-2,0);
                damageBoxLeft.translate(-2,0);
                damageBoxRight.translate(-2,0);
                break;
        }

        switch (currentDirection) {

            case LEFT:
                if (spriteCounterGetHit > 6){
                    playerPic.load("left_sprites/" + leftDamageSprites[0]);
                } else if (spriteCounterGetHit > 3){
                    playerPic.load("left_sprites/" + leftDamageSprites[1]);
                } else if (spriteCounterGetHit > 0) {
                    playerPic.load("left_sprites/" + leftDamageSprites[2]);
                }
                break;

            case RIGHT:
                if (spriteCounterGetHit > 6) {
                    playerPic.load("right_sprites/" + rightDamageSprites[0]);
                } else if (spriteCounterGetHit > 3) {
                    playerPic.load("right_sprites/" + rightDamageSprites[1]);
                } else if (spriteCounterGetHit > 0) {
                    playerPic.load("right_sprites/" + rightDamageSprites[2]);
                }
                break;
        }

        spriteCounterGetHit--;

        if (spriteCounterGetHit == 0) {
            isGetingHit = false;
        }


    }
    public void die() {

        if (spriteCounterDie == 0) {
            spriteCounterDie = 28;
        }

        switch (currentDirection) {
            case LEFT:
                if (spriteCounterDie > 25) {
                    playerPic.load("left_sprites/" + leftDeathSprites[0]);
                } else if (spriteCounterDie > 22) {
                    playerPic.load("left_sprites/" + leftDeathSprites[1]);
                } else if (spriteCounterDie > 19) {
                    playerPic.load("left_sprites/" + leftDeathSprites[2]);
                } else if (spriteCounterDie > 16) {
                    playerPic.load("left_sprites/" + leftDeathSprites[3]);
                } else if (spriteCounterDie > 13) {
                    playerPic.load("left_sprites/" + leftDeathSprites[4]);
                } else if (spriteCounterDie > 10) {
                    playerPic.load("left_sprites/" + leftDeathSprites[5]);
                } else if (spriteCounterDie > 7) {
                    playerPic.load("left_sprites/" + leftDeathSprites[6]);
                }
                break;
            case RIGHT:
                if (spriteCounterDie > 25) {
                    playerPic.load("right_sprites/" + rightDeathSprites[0]);
                } else if (spriteCounterDie > 22) {
                    playerPic.load("right_sprites/" + rightDeathSprites[1]);
                } else if (spriteCounterDie > 19) {
                    playerPic.load("right_sprites/" + rightDeathSprites[2]);
                } else if (spriteCounterDie > 16) {
                    playerPic.load("right_sprites/" + rightDeathSprites[3]);
                } else if (spriteCounterDie > 13) {
                    playerPic.load("right_sprites/" + rightDeathSprites[4]);
                } else if (spriteCounterDie > 10) {
                    playerPic.load("right_sprites/" + rightDeathSprites[5]);
                } else if (spriteCounterDie > 7) {
                    playerPic.load("right_sprites/" + rightDeathSprites[6]);
                }
                break;

        }

        spriteCounterDie--;

        if (spriteCounterDie == 0) {
            isDead = true;
        }







    }





    public boolean isDead() {
        return isDead;
    }

    public String getName() {
        return name;
    }



}
