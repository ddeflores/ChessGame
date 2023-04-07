import javax.swing.*;
import java.awt.*;

public class PieceKing extends Piece{

    public PieceKing(int row, int col, Color color) {
        super(row, col, color);
    }

    public boolean checkCastlePath(int rowFrom, int colTo, JButton[][] board) {
        if (colTo == 6) {
            for (int i = 5; i < 7; i++) {
                if (board[rowFrom][i] .getIcon() != null) {
                    return false;
                }
            }
        } else {
            for (int i = 3; i > 0; i--) {
                if (board[rowFrom][i] .getIcon() != null) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
         return (Math.abs(rowTo - rowFrom) <= 1 && Math.abs(colTo - colFrom) <= 1);
    }
}
