import java.awt.*;

public class PieceBishop extends Piece {

    public PieceBishop(int row, int col, Color color) {
        super(row, col, color);
    }

    //Bishops can move any number of squares diagonally
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (Math.abs(rowTo - rowFrom) == Math.abs(colTo - colFrom)) {
            return true;
        }
        else {
            return false;
        }
    }
}
