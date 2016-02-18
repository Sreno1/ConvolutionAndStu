package com.sigai.convolution;

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
}
