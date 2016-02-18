package com.sigai.convolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import java.util.*;

/**
 * Created by Richard on 2/17/2016.
 */
public class Player extends Actor{
    /*
        void getAvailableMoves()

            iterate through pieces with i
                if i.isSet is false (is empty either at start of game, or after a move)
                    LegalMoves lm = new LegalMoves(i)
                    i.isSet = true


     */
    public String playerName;
    public HashMap<Cell, LegalMoves> moves; //move information for all player's pieces
    public List<Cell> pieces;
    public int row, col, x, y, size;
    Sprite sprPiece;

    public static InputListener listener =  new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            System.out.print("f");
            //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
            return true;
        }
    };

    Player(int row, int col, int size, int x, int y,  Cell piece)
    {
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;
        this.size = size;
        sprPiece = new Sprite();
        getPieceTexture(col);
        sprPiece.setSize(size, size);
        getAvailableMoves();
        //setSize(size, size);
        //setPosition(x,y);
        //addListener(listener);
        System.out.println("PLAYER CREATED AT: " + getX() + ", " + getY());

    }

    public void render(SpriteBatch batch){
       batch.draw(sprPiece.getTexture(), x, y, size, size);

    }

    void getPieceTexture(int col)
    {
        if(col==7)
            sprPiece.setTexture(new Texture("bpiece.png"));
        if(col==0)
            sprPiece.setTexture(new Texture("wpiece.png"));
    }



    void getAvailableMoves()
    {

    }

}
