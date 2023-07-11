import org.academiadecodigo.simplegraphics.graphics.Color;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CollisionGrid {

    public CollsionTile[][] grid;





    public CollisionGrid (){
        grid = new CollsionTile[Field.maxRows][Field.maxCols];
    }

    public void loadGrid(String path){

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] euclides;
            int[][] numbers = new int[Field.maxRows][Field.maxCols];
            int row = 0;

            while ((line = bufferedReader.readLine()) != null){
                euclides = line.split( " ");
                for (int col = 0; col < numbers[row].length ; col++) {
                    numbers[row][col] = Integer.parseInt(euclides[col]);
                    if (numbers[row][col] == 1) {
                        grid[row][col] = new CollsionTile(Field.PADDING + col * Field.CELL_SIZE,Field.PADDING + row * Field.CELL_SIZE, Field.CELL_SIZE, Field.CELL_SIZE, false);
                        //grid[row][col].fill();

                    } else if (numbers[row][col] == 2) {
                        grid[row][col] = new CollsionTile(Field.PADDING + col * Field.CELL_SIZE,Field.PADDING + row * Field.CELL_SIZE, Field.CELL_SIZE, Field.CELL_SIZE, true);
                        grid[row][col].setColor(Color.RED);
                        //grid[row][col].fill();
                        grid[row][col].damage = true;
                    }

                }
                row++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void collisionChecker(){

        for (int row = 0; row < Field.maxRows; row++) {
            for (int col = 0; col < Field.maxCols; col++) {



            }
        }

    }






    public void update() {

    }

}
