package com.sigai.convolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Richard on 2/17/2016.
 */
public class Board {
    /*
        TODO:
        -NEED A 8X8 GRID
        IDEA 1: HAVE GRID BE RELATIVE TO THE SIZE OF APPLICATION. POSITION DEPENDS ON XML?

        HAVE INDIVIDUAL SPRITES FOR EACH CELL OR ONE LARGE IMAGE FOR THE BOARD?
        ----REGULAR TILES = ONE LARGE IMAGE
        ----WARPS = INDIVIDUAL (FOR ANIMATION)

        8x8 grid of class CELL
        Cell[][] board -- collection of all the cells and their info
        Player[2] player -- both players
        String currentPlayer -- player[0].playerName
        int movesTilStalemate = 30

        Cell's possible names:
        player[0], player[0] + "_split", player[1], player[1] + "_split", "none"

        FUNCTIONS:

        void init()
            instantiates the board

        void move(Cell from, Cell to)
            Moves piece from Cell.from to Cell.to
            Calls class LegalAction (LegalAction tells move what the protocol is, should it remove another piece/split/nothing)
            Cell.from.moves = null
            currentPlayer = playerSwitch(currentPlayer)
            LegalMoves
            gameStatus()

        Player playerSwitch(String player)
            returns the contrasting player

        void gameStatus()
            checks if currentPlayer has no pieces left (if so: stop game, last player wins
            checks if currentPlayer has 1 piece left
                if so: if movesTilStalemate > 0, movesTilStalemate--
                        else, stop game, is stalemate
            checks if currentPlayer has no moves left---- is stalemate
     */

    public static Cell[][] board;
    public static String playerOne, playerTwo;
    public static ArrayList<Player> pieces;
    public static String currentPlayer = null;


    //Gets the player names
    public Board(String p1, String p2)
    {
        playerOne = p1;
        playerTwo = p2;
        init();
    }

    void init()
    {
        board = new Cell[8][8];
        int cellSize = GameBoard.WIDTH/8;
        pieces = new ArrayList<Player>();
        int i = 0;
        for(int x=0;x<8;x++)
            for(int y=0;y<8;y++)
            {
                board[x][y] = new Cell(x, y, cellSize, cellSize * (x + 1 / 2) + 4, cellSize * (y + 1 / 2) + 4);
                GameScreen.stage.addActor(board[x][y]);
                board[x][y].contains = null;
            }

        for(int x=0;x<8;x++)
            for(int y=0;y<8;y+=7)
                if(y == 0 || y == 7)
                {
                    pieces.add(new Player(x, y, cellSize, cellSize * (x + 1 / 2) + 4, cellSize * (y + 1 / 2) + 4, board[x][y]));
                    if(y == 7) pieces.get(i).setName("black_piece");
                    else       pieces.get(i).setName("white_piece");
                    GameScreen.stage.addActor(pieces.get(i));
                    board[x][y].contains = pieces.get(i).getName();
                    i++;

                }
    }

    public static Hashtable<String, Integer> getPositions(Cell c)
    {
        Hashtable pos = new Hashtable<String, Integer> ();
        pos.put("x",c.x);
        pos.put("y",c.y);
        pos.put("row",c.row);
        pos.put("col",c.col);
        return pos;
    }

    public static void move()
    {

        if(!Player.firstMoveMade)
        {
            //piece.playerName = players[0].playerName;
            for(Player p : pieces)
            {
                if (p.col == Player.pieceSelected.col)
                {
                    p.playerName = playerOne;
                    currentPlayer = playerOne;
                }
                else
                    p.playerName = playerTwo;

            }
            Player.firstMoveMade = true;
        }
        Player piece = Player.pieceSelected;
        Cell tile = Cell.tileSelected;



        System.out.println(getPositions(board[piece.row][piece.col]) + " " + piece.playerName);
        //move piece's name to destination cell
        Board.board[piece.row][piece.col].contains = null;
        Board.board[tile.row][tile.col].contains = piece.getName();


        piece.update(getPositions(tile));

        System.out.println(getPositions(board[piece.row][piece.col]));
        currentPlayer = playerSwitch(piece.playerName);
    }

    public static String playerSwitch(String player)
    {
        if(playerOne.equals(player))
            return playerTwo;
        else
            return playerOne;
    }

    public void gameStatus()
    {

    }
}
