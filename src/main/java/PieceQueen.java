import javax.swing.*;
import java.awt.*;

public class PieceQueen extends Piece{
    public PieceQueen(int row, int col, Color color) {
        super(row, col, color);
    }

    //Queens can move any number of squares in any direction
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
        if ((rowTo - rowFrom == 0 && Math.abs(colTo - colFrom) > 0) || (colTo - colFrom == 0 && Math.abs(rowTo - rowFrom) > 0)) {
            int dirX = rowTo > rowFrom ? 1 : -1;
            int dirY = colTo > colFrom ? 1 : -1;
            for (int i = 1; i < Math.abs(rowTo - rowFrom) - 1; ++i) {
                if (board[rowFrom + i*dirX][colFrom + i*dirY].getIcon() != null) {
                    return false;
                }
            }
            return true;
        }
        else if (Math.abs(rowTo - rowFrom) == Math.abs(colTo - colFrom)) {
            int dirX = rowTo > rowFrom ? 1 : -1;
            int dirY = colTo > colFrom ? 1 : -1;
            for (int i = 1; i < Math.abs(rowTo - rowFrom); ++i) {
                if (board[rowFrom + i*dirX][colFrom + i*dirY].getIcon() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
