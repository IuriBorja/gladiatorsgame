import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.ArrayList;

public class KeyHandler implements KeyboardHandler {

    private KeyboardEvent[] events;

    private Keyboard keyboard;
    private Player player;

    public boolean up, down, left, right, attack;

    public KeyHandler(Player player) {
        this.player = player;
        events = new KeyboardEvent[5];
        keyboard = new Keyboard(this);
        createEvents(player.getName());
    }

    public void createEvents(String playerName) {
        ArrayList<Integer> keyboardEvents = new ArrayList<>();

        for(int i = 0; i < events.length; i++) {
            events[i] = new KeyboardEvent();
        }
        if(playerName.equals("Euclides")) {
            keyboardEvents.add(KeyboardEvent.KEY_W);
            keyboardEvents.add(KeyboardEvent.KEY_S);
            keyboardEvents.add(KeyboardEvent.KEY_A);
            keyboardEvents.add(KeyboardEvent.KEY_D);
            keyboardEvents.add(KeyboardEvent.KEY_SPACE);
        } else {
            keyboardEvents.add(KeyboardEvent.KEY_UP);
            keyboardEvents.add(KeyboardEvent.KEY_DOWN);
            keyboardEvents.add(KeyboardEvent.KEY_LEFT);
            keyboardEvents.add(KeyboardEvent.KEY_RIGHT);
            keyboardEvents.add(KeyboardEvent.KEY_ENTER);
        }
        // UP
        events[0].setKey(keyboardEvents.get(0));
        //DOWN
        events[1].setKey(keyboardEvents.get(1));
        // LEFT
        events[2].setKey(keyboardEvents.get(2));
        //RIGHT
        events[3].setKey(keyboardEvents.get(3));
        //ATTACK
        events[4].setKey(keyboardEvents.get(4));


        for(int i = 0; i < events.length; i++){
            events[i].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            KeyboardEvent eventRelease = new KeyboardEvent();
            eventRelease.setKey(events[i].getKey());
            eventRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyboard.addEventListener(eventRelease);
            keyboard.addEventListener(events[i]);
        }

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == events[0].getKey()) { up = true;
        }
        if(keyboardEvent.getKey() == events[1].getKey()) { down = true;
        }
        if(keyboardEvent.getKey() == events[2].getKey()) { left = true;
        }
        if(keyboardEvent.getKey() == events[3].getKey()) { right = true;
        }
        if(keyboardEvent.getKey() == events[4].getKey()) { attack = true;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == events[0].getKey()) { up = false;
        }
        if(keyboardEvent.getKey() == events[1].getKey()) { down = false;
        }
        if(keyboardEvent.getKey() == events[2].getKey()) { left = false;
        }
        if(keyboardEvent.getKey() == events[3].getKey()) { right = false;
        }
        if(keyboardEvent.getKey() == events[4].getKey()) { attack = false;
        }

    }
}
