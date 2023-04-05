import javax.swing.*;
import java.awt.*;

public class PiecePawn extends Piece{
    public PiecePawn(int row, int col, Color color) {
        super(row, col, color);
    }

    //Pawn can move one square forward
    public boolean validMove(int rowFrom, int colFrom, int rowTo, int colTo, JButton[][] board, Color color) {
        if (color == Color.white) {
            if (rowFrom == 6) { //If the pawn is on its starting square, it can move up either one or two squares
                if (colFrom - colTo == 0 && (rowFrom - rowTo <= 2 && rowFrom - rowTo > 0) && (board[5][colFrom].getIcon() == null)) {
                    return true;
                }
            } else { // Otherwise, it can only move up one square
                if (colFrom - colTo == 0 && rowFrom - rowTo == 1) {
                    return true;
                }
            }
            // If the pawn is attempting to move diagonally, check that there is a piece there to take
            return rowFrom - rowTo == 1 && Math.abs(colFrom - colTo) == 1 && board[rowTo][colTo].getIcon() != null;

        } else {
            if (rowFrom == 1) { //If the pawn is on its starting square, it can move up either one or two squares
                if (Math.abs(colFrom - colTo) == 0 && (rowTo - rowFrom <= 2 && rowTo - rowFrom > 0) && (board[2][colFrom].getIcon() == null)) {
                    return true;
                }
            } else {
                // Otherwise, it can only move up one square
                if (colFrom - colTo == 0 && rowTo - rowFrom == 1) {
                    return true;
                }
            }
            // If the pawn is attempting to move diagonally, check that there is a piece there to take
            return (rowTo - rowFrom == 1 && Math.abs(colTo - colFrom) == 1) && board[rowTo][colTo].getIcon() != null;
            }
        }
    }
