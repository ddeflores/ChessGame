import javax.swing.*;
import java.awt.*;

public class Piece {
    private int row;
    private int col;
    private Color color;

    // Default constructor
    public Piece(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    // Getters
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Color getColor() {
        return color;
    }

    // Each piece has its own valid move method
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board) {
        return false;
    }
}
