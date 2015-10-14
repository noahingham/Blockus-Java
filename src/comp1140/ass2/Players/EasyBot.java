package comp1140.ass2.Players;

import comp1140.ass2.Game.*;
import comp1140.ass2.Scenes.Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author ***REMOVED*** ***REMOVED***, ***REMOVED*** on 25/09/15, with segments taken from Holly's code
 * Commented by Holly, 14/10/15
 */
public class EasyBot implements Player {

    final Game parent;

    /**
     * Creates new EasyBot
     * @param parent the Game class
     */
    public EasyBot(Game parent) {
        this.parent = parent;
    }

    /**
     * If a user clicks on the board, nothing should happen, since it's not their turn
     * @param x the clicked cell's x value in the grid
     * @param y the clicked cell's y value in the grid
     */
    @Override
    public void handleClick(int x, int y) {
        //#TODO: Alert user it's not their turn?
        // Or do nothing
    }

    /**
     * Easybot produces a next move
     * @param string representing the current game of played pieces
     * @return a string which represents the next move
     */
    @Override
    public String think(String string) {
        Board board = new Board(string);
        Panel myPanel = parent.panels[parent.currentColourId];
        Colour colour = parent.playerColours[parent.currentColourId];
        Shape[] shuffled = shuffle(myPanel.activeShapes);
        for(Shape shape : shuffled) {
            for(char orientation : new char[] {'A','B','C','D','E','F','G','H'}) {
                for(int x = 0; x<20; x++) {
                    for(int y = 0; y<20; y++) {
                        //Piece testPiece = new Piece(piece.shape, piece.colour);
                        Piece testPiece = new Piece(shape, colour);
                        testPiece.initialisePiece(new Coordinate(x,y), orientation);
                        if(board.legitimateMove(testPiece)) {
                            //parent.piecePreparer.addShape(shape, colour, orientation);
                            //parent.makeMove(this, testPiece);
                            return testPiece.toString();
                        }
                    }
                }
            }
        }
        return ".";
        //parent.makeMove(".");
    }

    /**
     * Easybot is not human
     * @return false
     */
    @Override
    public boolean isHuman() {
        return false;
    }

    /**
     * Passes when no moves can be made
     * @param parent the Game class
     */
    @Override
    public void pass(Game parent) {
        parent.makeMove(".");
    }

    /**
     * Randomly shuffles array of Pieces
     * @param pieces game pieces
     * @return a shuffled array of Pieces, where the same Pieces are in a different order
     */
    private static Shape[] shuffle(ArrayList<Shape> pieces)
    {
        Shape[] ar = new Shape[pieces.size()];
        pieces.toArray(ar);
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Shape a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

}
