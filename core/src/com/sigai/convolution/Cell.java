package com.sigai.convolution;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Richard on 2/17/2016.
 */
public class Cell {
    /*

        String name -- returns what kind of piece it contains. "X" if none
        boolean isWarp -- determines if warp
        int row, col -- Based off of class Board's grid
        boolean isSet = false;
     */
    Sprite tile;
    public String name;
    public boolean isWarp = false;
    public int row, col, size, x, y;
    public boolean isSet = false;

    //row and col are for Board.board coordinates. x and y are for GUI coordinates
    Cell(int row, int col, int size, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.size = size-2;
        this.row = row;
        this.col = col;
        isSet = true;

        tile = new Sprite();
        getTileTexture(row,col);
        tile.setSize(size, size);
    }
/*
    public void loadAtlas(String path, String key){
        atlases.put(key, new TextureAtlas(Gdx.files.internal(path)));
    }

    public TextureAtlas getAtlas(String key){
        return atlases.get(key);
    }
*/
    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(tile.getTexture(), x, y, size, size);
    }

    void getTileTexture(int row, int col)
    {

        if((row == 1 && col==2) || (7-row == 1 && 7-col == 2))
            tile.setTexture(new Texture("ppink.png"));

        else if((row == 3 && col==2) || (7-row == 3 && 7-col == 2))
            tile.setTexture(new Texture("pred.png"));

        else if((row == 4 && col==2) || (7-row == 4 && 7-col == 2))
            tile.setTexture(new Texture("porange.png"));

        else if((row == 5 && col==3) || (7-row == 5 && 7-col == 3))
            tile.setTexture(new Texture("pyellow.png"));

        else if((row == 5 && col==4) || (7-row == 5 && 7-col == 4))
            tile.setTexture(new Texture("pgreen.png"));

        else if((row == 6 && col==2) || (7-row == 6 && 7-col == 2))
            tile.setTexture(new Texture("pblue.png"));
        else
            tile.setTexture(new Texture("tile.png"));
        isWarp = true;
    }
}
