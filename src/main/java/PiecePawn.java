import java.awt.*;

public class PiecePawn extends Piece{

    public PiecePawn(int row, int col, Color color) {
        super(row, col, color);
    }

    //Pawn can move one square forward
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (colFrom - colTo == 0 && rowTo - rowFrom == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
