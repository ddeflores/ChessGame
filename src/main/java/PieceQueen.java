import javax.swing.*;
import java.awt.*;

public class PieceQueen extends Piece{
    public PieceQueen(int row, int col, Color color) {
        super(row, col, color);
    }

    @Override
    //Queens can move any number of squares in any direction
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
        //Horizontal and vertical movement
        if ((rowTo - rowFrom == 0 && Math.abs(colTo - colFrom) > 0) || (colTo - colFrom == 0 && Math.abs(rowTo - rowFrom) > 0)) {

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
        //Diagonal movement
        else if (Math.abs(rowTo - rowFrom) == Math.abs(colTo - colFrom)) {

            // Make sure the movement path is clear of any other pieces
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
