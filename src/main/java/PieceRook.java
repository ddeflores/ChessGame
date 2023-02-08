import java.awt.*;

public class PieceRook extends Piece{
    public PieceRook(int row, int col, Color color) {
        super(row, col, color);
    }

    //Rooks can move any number of squares horizontally or vertically
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (rowTo - rowFrom == 0 && Math.abs(colTo - colFrom) > 0) {
            return true;
        }
        else if (colTo - colFrom == 0 && Math.abs(rowTo - rowFrom) > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
