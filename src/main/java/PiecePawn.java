import javax.swing.*;
import java.awt.*;

public class PiecePawn extends Piece{
    public PiecePawn(int row, int col, Color color) {
        super(row, col, color);
    }

    //Pawn can move one square forward
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board, Color color) {
        if (color == Color.white && rowFrom == 6) {
            if (Math.abs(colFrom - colTo) == 0 && Math.abs(rowTo - rowFrom) <= 2 && (board[5][colFrom].getIcon() == null)) {
                return true;
            }
            return false;
        }
        else if (color == Color.black && rowFrom == 1) {
            if (Math.abs(colFrom - colTo) == 0 && Math.abs(rowTo - rowFrom) <= 2 && (board[2][colFrom].getIcon() == null)) {
                return true;
            }
            return false;
        }
        else {
            return (Math.abs(colFrom - colTo) == 0 && Math.abs(rowTo - rowFrom) == 1);
        }
    }
}
