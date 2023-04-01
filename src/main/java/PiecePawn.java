import java.awt.*;

public class PiecePawn extends Piece{
    public PiecePawn(int row, int col, Color color) {
        super(row, col, color);
    }

    //Pawn can move one square forward
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, Color color) {
        if ((color == Color.white && rowFrom == 6) || (color == Color.black && rowFrom == 1)) {
            return Math.abs(colFrom - colTo) == 0 && Math.abs(rowTo - rowFrom) <= 2;
        }
        else {
            return (Math.abs(colFrom - colTo) == 0 && Math.abs(rowTo - rowFrom) == 1);
        }
    }
}
