package com.sigai.convolution;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


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
    public String playerName = null;
    public static HashMap<Cell, LegalMoves> moves = new HashMap<Cell, LegalMoves>(); //move information for all player's pieces
    public List<Cell> pieces;
    public Cell piece;
    public int row, col, x, y, size;
    Sprite sprPiece;
    //boolean pieceSelected = false;
    public static Player pieceSelected;
    public static boolean firstMoveMade = false;

    public InputListener listener =  new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //First touch: selecting piece
            if(pieceSelected != null)
                for(Cell c : Player.moves.get(Player.pieceSelected.piece).pieceMoves) {
                    c.highlighted = false;
                    c.highlightTile();
                }
            addPieceMoves(piece);
            pieceSelected = (Player)event.getTarget();
            if(pieceSelected.playerName != Board.currentPlayer)
                return false;
            System.out.println("piece highlighted at " + pieceSelected.row + " " + pieceSelected.col);
            System.out.println("pieces moves: " + moves.get(Player.onTile(pieceSelected)).pieceMoves);

            return true;
        }

        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            //If piece has been highlighted, highlight the possible moves
            for(Cell c : moves.get(Player.onTile(pieceSelected)).pieceMoves) {
                c.highlighted = true;
                c.highlightTile();
            }
        }

    };

    @Override
    public void act(float delta)
    {
        for(Iterator<Action> iter = this.getActions().iterator(); iter.hasNext();){
            iter.next().act(delta);
        }
    }

    Player(int row, int col, int size, int x, int y,  Cell piece)
    {
        this.row = row;
        this.col = col;
        this.x = x;
        this.y = y;
        this.size = size;
        this.piece = piece;
        //this.piece.contains =
        sprPiece = new Sprite();
        getPieceTexture(col);
        sprPiece.setSize(size, size);
        setBounds(x, y, size, size);
        setPosition(x, y);
        setTouchable(Touchable.enabled);
        addListener(listener);
        System.out.println("PLAYER CREATED AT: " + getX() + ", " + getY());

    }

    public void draw(Batch batch, float alpha){
       batch.draw(sprPiece.getTexture(), x, y, size, size);

    }

    void getPieceTexture(int col)
    {
        if(col==7)
            sprPiece.setTexture(new Texture("bpiece.png"));
        if(col==0)
            sprPiece.setTexture(new Texture("wpiece.png"));
    }



    void addPieceMoves(Cell piece)
    {
        moves.put(piece, new LegalMoves(piece));
    }


    public static LegalMoves getLegalMove (Cell piece)
    {
        LegalMoves lm = moves.get(piece);
        return lm;
    }

    public boolean isValidMove (Cell tile)
    {
        return moves.get(piece).pieceMoves.contains(tile);
    }

    public static Cell onTile (Player piece)
    {
        return Board.board[piece.row][piece.col];
    }

    public void update(Hashtable<String, Integer> pos)
    {
        //Delete the piece's previous moves from the overall moves
        System.out.println("previous tile:" + moves.get(piece).pieceMoves.isEmpty());
        moves.get(piece).deletePieceMoves();
        moves.remove(piece);
        removeListener(listener);
        x = pos.get("x");
        y = pos.get("y");
        row = pos.get("row");
        col = pos.get("col");
        Board.pieces.remove(this);
        piece = Board.board[row][col];
        setBounds(x,y,size,size);

        //Add the new moves!
        moves.put(piece, new LegalMoves(piece));
        addPieceMoves(piece);
        Board.pieces.add(this);
        addListener(listener);
    }
}
