import javax.swing.*;
import java.awt.*;

public class PieceKing extends Piece{

    public PieceKing(int row, int col, Color color) {
        super(row, col, color);
    }

    //Kings can move one square in any direction
    @Override
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
         return (Math.abs(rowTo - rowFrom) <= 1 && Math.abs(colTo - colFrom) <= 1);
    }
}
