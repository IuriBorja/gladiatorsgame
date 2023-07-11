import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class DamageBox extends Rectangle {

    public boolean isDamageable;

    public DamageBox(int x, int y, int width, int height) {
        super(x,y,width,height);
        this.isDamageable = false;
    }

}
