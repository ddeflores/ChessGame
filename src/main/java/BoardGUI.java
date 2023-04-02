import javax.swing.*;
import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
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
    private JButton selectedButton;
    private Icon icon;
    private int count;
    private int fromX, fromY, toX, toY;
    private Color currentTurn = Color.white;

    public BoardGUI() {
        blackPawn = new ImageIcon("blackPawn.png");
        blackRook = new ImageIcon("blackRook.png");
        blackKnight = new ImageIcon("blackKnight.png");
        blackBishop = new ImageIcon("blackBishop.png");
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
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (count % 2 == 0) {
                            selectedButton = (JButton) e.getSource();
                            fromX = getX(selectedButton);
                            fromY = getY(selectedButton);
                            icon = selectedButton.getIcon();
                            count++;
                        }
                        else {
                            JButton source = (JButton) e.getSource();
                            if (selectedButton.getIcon() != null && getPiece(selectedButton).getColor() == currentTurn) {
                                Piece pieceToMove = getPiece(selectedButton);
                                toX = getX(source);
                                toY = getY(source);
                                if (source.getIcon() != null) {
                                    Piece pieceToTake = getPiece(source);
                                    if (pieceToMove.getColor() != pieceToTake.getColor()) {
                                        if (pieceToMove instanceof PiecePawn) {
                                            if (pieceToMove.getColor() == Color.black && (toX - fromX == 1 && Math.abs(toY - fromY) == 1)) {
                                                source.setIcon(icon);
                                                selectedButton.setIcon(null);
                                                currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                                count++;
                                            } else if (pieceToMove.getColor() == Color.white && (fromX - toX == 1 && Math.abs(fromY - toY) == 1)) {
                                                source.setIcon(icon);
                                                selectedButton.setIcon(null);
                                                currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                                count++;
                                            }
                                        }
                                        else {
                                            if (pieceToMove.validMove(fromX, fromY, toX, toY)) {
                                                source.setIcon(icon);
                                                selectedButton.setIcon(null);
                                                currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                                count++;
                                            }
                                        }
                                    }
                                }
                                else {
                                    if (pieceToMove instanceof PiecePawn) {
                                        if (((PiecePawn) pieceToMove).validMove(fromX, fromY, toX, toY, ((PiecePawn) pieceToMove).getColor())) {
                                            source.setIcon(icon);
                                            selectedButton.setIcon(null);
                                            currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                            count++;
                                        }
                                    }
                                    else {
                                        if (pieceToMove.validMove(fromX, fromY, toX, toY)) {
                                            source.setIcon(icon);
                                            selectedButton.setIcon(null);
                                            currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                            count++;
                                        }
                                    }
                                }
                            }
                            else {
                                count--;
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
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
    public int getX(JButton selectedButton) {
        int x = -1;
        Container parent = selectedButton.getParent();
        if (parent.getLayout() instanceof GridLayout) {
            GridLayout layout = (GridLayout)  parent.getLayout();
            int cols = layout.getColumns();
            int rows = layout.getRows();
            int index = parent.getComponentZOrder(selectedButton);
            x = index / cols;
        }
        return x;
    }
    public int getY(JButton selectedButton) {
        int y = -1;
        Container parent = selectedButton.getParent();
        if (parent.getLayout() instanceof GridLayout) {
            GridLayout layout = (GridLayout)  parent.getLayout();
            int cols = layout.getColumns();
            int rows = layout.getRows();
            int index = parent.getComponentZOrder(selectedButton);
            y = index % cols;
        }
        return y;
    }
    public Piece getPiece(JButton selectedButton) {
        if (selectedButton.getIcon() == blackPawn) {
            return new PiecePawn(getX(selectedButton), getY(selectedButton), Color.black);
        }
        else if (selectedButton.getIcon() == blackRook) {
            return new PieceRook(getX(selectedButton), getY(selectedButton), Color.black);
        }
        else if (selectedButton.getIcon() == blackKnight) {
            return new PieceKnight(getX(selectedButton), getY(selectedButton), Color.black);
        }
        else if (selectedButton.getIcon() == blackBishop) {
            return new PieceBishop(getX(selectedButton), getY(selectedButton), Color.black);
        }
        else if (selectedButton.getIcon() == blackQueen) {
            return new PieceQueen(getX(selectedButton), getY(selectedButton), Color.black);
        }
        else if (selectedButton.getIcon() == blackKing) {
            return new PieceKing(getX(selectedButton), getY(selectedButton), Color.black);
        }
        else if (selectedButton.getIcon() == whitePawn) {
            return new PiecePawn(getX(selectedButton), getY(selectedButton), Color.white);
        }
        else if (selectedButton.getIcon() == whiteRook) {
            return new PieceRook(getX(selectedButton), getY(selectedButton), Color.white);
        }
        else if (selectedButton.getIcon() == whiteKnight) {
            return new PieceKnight(getX(selectedButton), getY(selectedButton), Color.white);
        }
        else if (selectedButton.getIcon() == whiteBishop) {
            return new PieceBishop(getX(selectedButton), getY(selectedButton), Color.white);
        }
        else if (selectedButton.getIcon() == whiteQueen) {
            return new PieceQueen(getX(selectedButton), getY(selectedButton), Color.white);
        }
        else if (selectedButton.getIcon() == whiteKing) {
            return new PieceKing(getX(selectedButton), getY(selectedButton), Color.white);
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        BoardGUI board = new BoardGUI();
    }
}
