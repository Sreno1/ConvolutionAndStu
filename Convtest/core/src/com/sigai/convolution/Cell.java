package com.sigai.convolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Richard on 2/17/2016.
 */
public class Cell extends Actor{
    /*

        String name -- returns what kind of piece it contains. "X" if none
        boolean isWarp -- determines if warp
        int row, col -- Based off of class Board's grid
        boolean isSet = false;
     */
    Sprite tile;
    public String contains;
    public boolean isWarp = false;
    public int row, col, size, x, y;
    public boolean isSet = false;
    public static Cell tileSelected;
    public boolean highlighted = false;

    public InputListener listener =  new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //First touch: selecting piece

            if(Player.pieceSelected != null && Player.pieceSelected.playerName == Board.currentPlayer
            && ((Cell) event.getTarget()).contains == null && Player.pieceSelected.isValidMove((Cell)event.getTarget()))
            {

                tileSelected = (Cell) event.getTarget();
                System.out.println("highlighted tile:" + Board.getPositions(tileSelected) );
                return true;
            }
            return false;
        }

        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            //If piece has been highlighted, highlight the possible moves
            for(Cell c : Player.moves.get(Player.pieceSelected.piece).pieceMoves) {
                c.highlighted = false;
                c.highlightTile();
            }

            Board.move();
            Player.pieceSelected = null;
            tileSelected = null;

            //piece not highlighted yet. do a shake animation
        }

    };


    //row and col are for Board.board coordinates. x and y are for GUI coordinates
    Cell(int row, int col, int size, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.size = size-2;
        this.row = row;
        this.col = col;
        isSet = true;
        contains = null;
        tile = new Sprite();
        getTileTexture(row,col);
        tile.setSize(size, size);
        setBounds(x, y, size, size);
        setPosition(x,y);
        addListener(listener);
    }

    public void draw(Batch batch, float alpha){
        batch.draw(tile.getTexture(), x, y, size, size);
    }


    public void highlightTile()
    {
        getTileTexture(row, col);
    }

    void getTileTexture(int row, int col)
    {

        if(!highlighted) {
            if ((row == 1 && col == 2) || (7 - row == 1 && 7 - col == 2)) {
                tile.setTexture(new Texture("ppink.png"));
                isWarp = true;
            } else if ((row == 3 && col == 2) || (7 - row == 3 && 7 - col == 2)) {
                tile.setTexture(new Texture("pred.png"));
                isWarp = true;
            } else if ((row == 4 && col == 2) || (7 - row == 4 && 7 - col == 2)) {
                tile.setTexture(new Texture("porange.png"));
                isWarp = true;
            } else if ((row == 5 && col == 3) || (7 - row == 5 && 7 - col == 3)) {
                tile.setTexture(new Texture("pyellow.png"));
                isWarp = true;
            } else if ((row == 5 && col == 4) || (7 - row == 5 && 7 - col == 4)) {
                tile.setTexture(new Texture("pgreen.png"));
                isWarp = true;
            } else if ((row == 6 && col == 2) || (7 - row == 6 && 7 - col == 2)) {
                tile.setTexture(new Texture("pblue.png"));
                isWarp = true;
            } else
                tile.setTexture(new Texture("tile.png"));
        }
        else
            tile.setTexture(new Texture("highlighted.png"));
    }
}
