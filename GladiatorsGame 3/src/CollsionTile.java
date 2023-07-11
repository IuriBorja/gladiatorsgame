import com.sun.jndi.ldap.LdapPoolManager;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class CollsionTile extends Rectangle {

    public boolean damage;

    public CollsionTile(int x, int y, int width, int height, boolean damage) {
        super(x, y, width, height);
        this.damage = damage;
    }





}
