package com.sigai.convolution;

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
    public Player[] players = new Player[2];

    //Gets the player names
    Board(String p1, String p2)
    {
        ///players[0].playerName = p1;
        //players[1].playerName = p2;
        init();
    }

    void init()
    {

    }

    public void move(Cell from, Cell to)
    {

    }

    public Player playerSwitch(String player)
    {
        if(players[0].playerName.equals(player))
            return players[1];
        else
            return players[0];
    }

    public void gameStatus()
    {

    }
}
