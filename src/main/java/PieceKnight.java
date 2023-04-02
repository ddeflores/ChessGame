import javax.swing.*;
import java.awt.*;

public class PieceKnight extends Piece{

    public PieceKnight(int row, int col, Color color) {
        super(row, col, color);
    }


    //Knights can move in any direction in an L-Shape
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
        if (Math.abs(rowTo - rowFrom) == 1 && Math.abs(colTo - colFrom) == 2) {
            return true;
        }
        else if (Math.abs(colTo - colFrom) == 1 && Math.abs(rowTo - rowFrom) == 2) {
            return true;
        }
        else {
            return false;
        }
    }
}
