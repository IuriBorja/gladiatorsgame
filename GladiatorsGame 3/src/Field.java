import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Field {

    public static final int CELL_SIZE = 8;

    public static final int PADDING = 10;

    public static int screenWidth;

    public static int screenHeight;

    public static int maxCols;

    public static int maxRows;

    public static Picture picture;


    public static void generateValues(String path){
            picture = new Picture(PADDING, PADDING, path);
            maxCols = picture.getWidth() / CELL_SIZE;
            maxRows = picture.getHeight() / CELL_SIZE;
            screenWidth = picture.getWidth();
            screenHeight = picture.getHeight();
            picture.draw();

    }


}
