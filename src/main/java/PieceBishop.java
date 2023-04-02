import javax.swing.*;
import java.awt.*;

public class PieceBishop extends Piece {

    public PieceBishop(int row, int col, Color color) {
        super(row, col, color);
    }

    //Bishops can move any number of squares diagonally
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
        if (Math.abs(rowTo - rowFrom) == Math.abs(colTo - colFrom)) {
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
