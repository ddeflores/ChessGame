import javax.swing.*;
import java.awt.*;

public class PieceRook extends Piece{
    public PieceRook(int row, int col, Color color) {
        super(row, col, color);
    }

    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
        //Rooks can move any number of squares horizontally or vertically
        if ((Math.abs(rowTo - rowFrom) == 0 && Math.abs(colTo - colFrom) > 0) || (Math.abs(rowTo - rowFrom) > 0 && Math.abs(colTo - colFrom) == 0)) {

            // Make sure the movement path is clear of any other pieces
            int dirX = rowTo > rowFrom ? 1 : -1;
            int dirY = colTo > colFrom ? 1 : -1;
            for (int i = 1; i < Math.abs(rowTo - rowFrom) - 1; ++i) {
                if (board[rowFrom + i*dirX][colFrom + i*dirY].getIcon() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
