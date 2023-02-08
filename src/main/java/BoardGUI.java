import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private ImageIcon blackPawn;
    private ImageIcon blackRook;
    private ImageIcon blackKnight;
    private ImageIcon blackBishop;
    private ImageIcon blackQueen;
    private ImageIcon blackKing;
    private ImageIcon whitePawn;
    private ImageIcon whiteRook;
    private ImageIcon whiteKnight;
    private ImageIcon whiteBishop;
    private ImageIcon whiteQueen;
    private ImageIcon whiteKing;

    public BoardGUI() {
        blackPawn = new ImageIcon("blackPawn.png");
        blackRook = new ImageIcon("blackRook.png");
        blackKnight = new ImageIcon("blackKnight.png");
        blackBishop = new ImageIcon("blackKnight.png");
        blackQueen = new ImageIcon("blackQueen.png");
        blackKing = new ImageIcon("blackKing.png");
        whitePawn = new ImageIcon("whitePawn.png");
        whiteRook = new ImageIcon("whiteRook.png");
        whiteKnight = new ImageIcon("whiteKnight.png");
        whiteBishop = new ImageIcon("whiteBishop.png");
        whiteQueen = new ImageIcon("whiteQueen.png");
        whiteKing = new ImageIcon("whiteKing.png");

        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        buttons = new JButton[8][8];

        //Initialize board and colors
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                if (i % 2 == j % 2) {
                    button.setBackground(Color.white);
                }
                else {
                    button.setBackground(new Color(111, 143, 175));
                }
                panel.add(button);
            }
        }

        //Add all pawns
        for (int i = 0; i < 8; i++) {
            buttons[1][i].setIcon(blackPawn);
            buttons[6][i].setIcon(whitePawn);
        }
        //Add black pieces
        buttons[0][0].setIcon(blackRook);
        buttons[0][1].setIcon(blackKnight);
        buttons[0][2].setIcon(blackBishop);
        buttons[0][3].setIcon(blackQueen);
        buttons[0][4].setIcon(blackKing);
        buttons[0][5].setIcon(blackBishop);
        buttons[0][6].setIcon(blackKnight);
        buttons[0][7].setIcon(blackRook);
        //Add white pieces
        buttons[7][0].setIcon(whiteRook);
        buttons[7][1].setIcon(whiteKnight);
        buttons[7][2].setIcon(whiteBishop);
        buttons[7][3].setIcon(whiteQueen);
        buttons[7][4].setIcon(whiteKing);
        buttons[7][5].setIcon(whiteBishop);
        buttons[7][6].setIcon(whiteKnight);
        buttons[7][7].setIcon(whiteRook);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BoardGUI board = new BoardGUI();
    }
}
