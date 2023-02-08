import java.awt.*;

public class PieceQueen extends Piece{
    public PieceQueen(int row, int col, Color color) {
        super(row, col, color);
    }

    //Queens can move any number of squares in any direction
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (rowTo - rowFrom == 0 && Math.abs(colTo - colFrom) > 0) {
            return true;
        }
        else if (colTo - colFrom == 0 && Math.abs(rowTo - rowFrom) > 0) {
            return true;
        }
        else if (Math.abs(rowTo - rowFrom) == Math.abs(colTo - colFrom)) {
            return true;
        }
        else {
            return false;
        }
    }
}
