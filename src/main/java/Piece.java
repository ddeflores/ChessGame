import javax.swing.*;
import java.awt.*;

public class Piece extends JButton {
    private int row;
    private int col;
    private Color color;

    public Piece(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Color getColor() {
        return color;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        return false;
    }
}
