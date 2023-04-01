import java.awt.*;

public class PiecePawn extends Piece{

    public PiecePawn(int row, int col, Color color) {
        super(row, col, color);
    }

    //Pawn can move one square forward
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (Math.abs(colFrom - colTo) == 0 && Math.abs(rowTo - rowFrom) == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
