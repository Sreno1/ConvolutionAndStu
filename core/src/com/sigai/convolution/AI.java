package com.sigai.convolution;

import java.util.ArrayList;

/**
 * Created by Richard on 2/18/2016.
 */
public class AI {

    /*
    getDepth
    score
    getAdjacency
    getStats
    howAdvanced
    checkThreat
    checkAreaControlled
    canWin
     */
    Cell[][] referenceBoard;
    Player currentPlayer;
    final int MAX_VAL = 100000000, MIN_VAL = -100000000;

    AI(Cell[][] b, Player p)
    {
        currentPlayer = p;
        referenceBoard = b;
        int depth = getDepth();
        alphaBeta(MAX_VAL, MIN_VAL, depth);
    }

    int alphaBeta(int alpha, int beta, int depth)
    {
        /*TODO: Needs a base case AKA if any player wins, one player has no pieces left, or depth = 0
                    If fufilled, call method score()

          check if current player is threatened  (checkThreat)

          check if opposing player is threatened (checkThreat)
            Will be for loop checking every player's piece possible moves
          change current player
          recursively call alphaBeta(-beta, -alpha, depth-1, c)
          change current player

            best score replacing alpha
            setting best score
         */
        return 0;
    }

    int getDepth()
    {
        /*
            Level of computer player 1-10
            Exponentially increases depth
         */
        return 0;
    }

    int score()
    {
        //Has a scoring int for 'us' and 'them'
        //Calculates score based off of the scoring methods that we have :)
        //return us - them;
        return 0;
    }

    ArrayList<Cell> getAdjacency(Cell cell)
    {
        //Checks the adjacent tiles around specified player (first 3x3 then 5x5)
        //If on warp, call getAdjacency(referenceBoard[7-cell.row][7-cell.col])
        //returns a list of enemy pieces
        return new ArrayList<Cell>();
    }

    void getStats()
    {
        //gets population ratio
        //Calls howAdvanced and checkAreaControlled
        //General stats
    }

    int howAdvanced()
    {
        //how far up the piece is to the opposing side
        //Create a variable for the average advancedness of each piece4
        return 0;
    }

    boolean checkThreats()
    {
        //Check if about to be jumped
        return false;
    }

    ArrayList<Cell> checkAreaControlled()
    {
        //
        return new ArrayList<Cell>();
    }

    boolean canWin()
    {
        //Checks if this is the last move needed to win
        return false;
    }

    void referenceBoardMove()
    {
        //simulates Board.move() except doesn't change the static Board.board
    }
}
