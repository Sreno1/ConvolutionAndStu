package com.sigai.convolution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Richard on 2/17/2016.
 */
public class LegalMoves {
    /*
    We want to make it only update recently moved piece's available moves to save mucho memory
        LegalAction action
        public HashMap<LegalAction, ArrayList<Cell>> legalMoves

        LegalMoves(Cell piece)
        {
            legalMoves = getLegalMoves(piece)
        }





        public HashMap<LegalAction, ArrayList<Cell>> getLegalMoves(Cell selectedPiece)
        new HashMap<LegalAction, ArrayList<Cell>> availableMoves
        checks selectedPiece with:
           canMove
           canWarp
           canJump          <- ALL OF THESE ARE TYPE HashMap<LegalAction, ArrayList<Cell>>
           canSplit             -with PARAMETERS (new Queue<String>, new ArrayList<Cell>, selectedPiece, availableMoves)
           canJumpWarp
           canWarpJump

            if true for any:
               tempAL.add(thecell)
                tempQueue.add(whatevermoveitis)
                availableMoves.put(new LegalAction(tempQueue), tempAL)
     */

    public static HashMap<LegalAction, ArrayList<Cell>> legalMoves; //categorized individual piece moves
    public static ArrayList<Cell> allMoves = new ArrayList<Cell>(); //all moves for that player
    public ArrayList<Cell> pieceMoves = new ArrayList<Cell>(); //Individual piece moves

    public LegalMoves (Cell piece)
    {
        legalMoves = getLegalMoves(piece);

    }

    public void deletePieceMoves()
    {
        allMoves.remove(pieceMoves);
        System.out.println(allMoves.isEmpty());
        pieceMoves = null;
    }

    public HashMap<LegalAction,ArrayList<Cell>> getLegalMoves(Cell selectedPiece)
    {
        //These are the moves that only require one LegalAction
        HashMap availableMoves = new HashMap<LegalAction,ArrayList<Cell>>();
        canMove(new LegalAction(new ArrayDeque<String>()), new ArrayList<Cell>(), selectedPiece, availableMoves);
        canWarp(new LegalAction(new ArrayDeque<String>()), new ArrayList<Cell>(), selectedPiece, availableMoves);
        canJump(new LegalAction(new ArrayDeque<String>()), new ArrayList<Cell>(), selectedPiece, availableMoves);
        canJumpWarp(new LegalAction(new ArrayDeque<String>()), new ArrayList<Cell>(), selectedPiece, availableMoves);
        canWarpJump(new LegalAction(new ArrayDeque<String>()), new ArrayList<Cell>(), selectedPiece, availableMoves);

        //We need to make a function that switches between all of those functions to check if that move can continue
        //i.e. Jumping and landing on a warp. Continue move by warping
        //availableMoves.put(continuedLegalActions, continuedMoves);

        return availableMoves;
    }

    void canMove(LegalAction protocol, ArrayList<Cell> list, Cell piece, HashMap<LegalAction,ArrayList<Cell>> moves)
    {
        list = adjacentSpaces(piece);
        allMoves.addAll(list);
        pieceMoves.addAll(list);
        if(!list.isEmpty())
            protocol.add("move");
        moves.put(protocol, list);
    }
    void canWarp(LegalAction protocol, ArrayList<Cell> list, Cell piece, HashMap<LegalAction,ArrayList<Cell>> moves)
    {
        list = warpSpaces(piece);
        if(Board.board[piece.row][piece.col].isWarp && Board.board[7-piece.row][7-piece.col].contains == null)
            list.add(Board.board[7-piece.row][7-piece.col]);
        allMoves.addAll(list);
        pieceMoves.addAll(list);
        if(!list.isEmpty())
            protocol.add("warp");
        moves.put(protocol, list);
    }
    void canSplit(LegalAction protocol, ArrayList<Cell> list, Cell piece, HashMap<LegalAction,ArrayList<Cell>> moves)
    {

    }
    void canJump(LegalAction protocol, ArrayList<Cell> list, Cell piece, HashMap<LegalAction,ArrayList<Cell>> moves)
    {
        list = adjacentJumps(piece);
        allMoves.addAll(list);
        pieceMoves.addAll(list);
        if(!list.isEmpty())
            protocol.add("jump");
        moves.put(protocol, list);
    }
    void canJumpWarp(LegalAction protocol, ArrayList<Cell> list, Cell piece, HashMap<LegalAction,ArrayList<Cell>> moves)
    {
        list = enemyOnAdjacentWarp(piece);
        allMoves.addAll(list);
        pieceMoves.addAll(list);
        if(!list.isEmpty())
            protocol.add("jumpwarp");
        moves.put(protocol, list);
    }
    void canWarpJump(LegalAction protocol, ArrayList<Cell> list, Cell piece, HashMap<LegalAction,ArrayList<Cell>> moves)
    {
        list = enemyOnOpposingWarp(piece);
        allMoves.addAll(list);
        pieceMoves.addAll(list);
        if(!list.isEmpty())
            protocol.add("warpjump");
        moves.put(protocol, list);
    }

    ArrayList<Cell> adjacentSpaces(Cell c)
    {
        ArrayList spaces = new ArrayList<Cell>();
        for(int i = c.row-1; i <= c.row+1;i++)
            for(int j = c.col-1; j <= c.col+1;j++)
                if(isInBounds(i,j) && Board.board[i][j].contains == null)
                    spaces.add(Board.board[i][j]);

        return spaces;
    }

    ArrayList<Cell> adjacentJumps(Cell c)
    {
        ArrayList enemies = new ArrayList<Cell>();
        for(int i = c.row-1; i <= c.row+1;i++)
            for(int j = c.col-1; j <= c.col+1;j++)
                if(isInBounds(i,j) && Board.board[i][j].contains != null
                && Board.board[i][j].contains != c.contains && isInBounds(2*i - c.row, 2*j - c.col) && Board.board[2*i - c.row][2*j - c.col].contains == null)
                    enemies.add(Board.board[2*i - c.row][2*j - c.col]);

        return enemies;
    }

    ArrayList<Cell> warpSpaces(Cell c)
    {
        ArrayList spaces = new ArrayList<Cell>();
        for(int i = c.row-1; i <= c.row+1;i++)
            for(int j = c.col-1; j <= c.col+1;j++)
                if(isInBounds(i,j) && Board.board[i][j].contains == null && Board.board[i][j].isWarp && Board.board[7-i][7-j].contains == null)
                    spaces.add(Board.board[7-i][7-j]);

        return spaces;
    }
    //Rubber duck method
    ArrayList<Cell> enemyOnAdjacentWarp(Cell c)
    {
        ArrayList enemies = new ArrayList<Cell>();
        for(int i = c.row-1; i <= c.row+1;i++)
            for(int j = c.col-1; j <= c.col+1;j++)
            {
                if (isInBounds(i, j) && Board.board[i][j].contains != null
                        && Board.board[i][j].contains != c.contains && Board.board[i][j].isWarp
                        && Board.board[7 - i][7 - j].contains == null
                        && Board.board[7 - c.row][7 - c.col].contains == null)
                {//3 2  (4)(7-(2*2 - 6)
                    enemies.add(Board.board[7 - c.row][7 - c.col]); //The opposing adjacent landing tile

                }

                //if(isInBounds(7 - (2*i - c.row), 7 - (2*j - c.col)))
                    //continuedMoves()
            }
        return enemies;
    }

    ArrayList<Cell> enemyOnOpposingWarp(Cell c)
    {
        ArrayList enemies = new ArrayList<Cell>();
        for (int i = c.row - 1; i <= c.row + 1; i++)
            for (int j = c.col - 1; j <= c.col + 1; j++)
                if (isInBounds(i, j) && Board.board[i][j].contains == null
                        && Board.board[7 - i][7 - j].contains != null && Board.board[i][j].isWarp
                        && Board.board[7 - i][7 - j].contains != c.contains
                        && Board.board[7 - c.row][7 - c.col].contains == null)
                    enemies.add(Board.board[7 - c.row][7 - c.col]); //The opposing adjacent landing tile

        return enemies;
    }

    ArrayList<Cell> continuedMoves(Cell c, ArrayList<Cell> leftOff, boolean isJump)
    {
        ArrayList continued = new ArrayList<Cell>();

        return continued;
    }




    boolean isInBounds(int i, int j)
    {
        if(i < 0 || i > 7 || j < 0 || j > 7)
            return false;
        return true;
    }
}
