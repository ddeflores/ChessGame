import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private ImageIcon blackPawn = new ImageIcon("blackPawn.png");
    private ImageIcon blackRook = new ImageIcon("blackRook.png");
    private ImageIcon blackKnight = new ImageIcon("blackKnight.png");
    private ImageIcon blackBishop = new ImageIcon("blackBishop.png");
    private ImageIcon blackQueen = new ImageIcon("blackQueen.png");
    private ImageIcon blackKing = new ImageIcon("blackKing.png");
    private ImageIcon whitePawn = new ImageIcon("whitePawn.png");
    private ImageIcon whiteRook = new ImageIcon("whiteRook.png");
    private ImageIcon whiteKnight = new ImageIcon("whiteKnight.png");
    private ImageIcon whiteBishop = new ImageIcon("whiteBishop.png");
    private ImageIcon whiteQueen = new ImageIcon("whiteQueen.png");
    private ImageIcon whiteKing = new ImageIcon("whiteKing.png");

    private JButton[][] buttons;
    private JButton selectedButton;
    private Icon icon;
    private int count;
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private Color currentTurn = Color.white;
    private boolean whiteKingMoved, blackKingMoved = false;
    private boolean kingsideWhiteRookMoved, kingsideBlackRookMoved = false;
    private boolean queensideWhiteRookMoved, queensideBlackRookMoved= false;

    //Initialize the board and its GUI
    public BoardGUI() {
        // Initialize the frame, panel, and board of JButtons
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        buttons = new JButton[8][8];

        //Initialize board and alternating colors-- each square on the board contains a JButton with a MouseListener
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
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // If it is the first click, initialize from square and X and Y values
                        if (count == 0) {
                            selectedButton = (JButton) e.getSource();
                            fromX = getX(selectedButton);
                            fromY = getY(selectedButton);
                            icon = selectedButton.getIcon();
                            // If you clicked on a piece of the correct color, increment the click count
                            if (selectedButton.getIcon() != null && getPiece(selectedButton).getColor() == currentTurn) {
                                count++;
                            }
                        } else { // This is now the second click
                            // Initialize the square to move to, and the type of piece to move
                            JButton source = (JButton) e.getSource();
                            Piece pieceToMove = getPiece(selectedButton);
                            toX = getX(source);
                            toY = getY(source);
                            // If there is a piece on the square being moved to, check if the move is valid, and take it
                            if (source.getIcon() != null) {
                                Piece pieceToTake = getPiece(source);
                                if (pieceToMove.getColor() != pieceToTake.getColor()) { // Make sure the piece on the square is on the other team

                                    // A pawns validMove method takes different parameters
                                    if (pieceToMove instanceof PiecePawn) {
                                        if (((PiecePawn)pieceToMove).validMove(fromX,fromY,toX,toY,buttons,pieceToMove.getColor())) {
                                            source.setIcon(icon);
                                            selectedButton.setIcon(null);
                                            currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                        }
                                    } else {
                                        if (pieceToMove.validMove(fromX, fromY, toX, toY, buttons)) {
                                            source.setIcon(icon);
                                            selectedButton.setIcon(null);
                                            currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                        }
                                    }
                                    count--;
                                }
                            } else { // There is no piece on the square being moved to. If the move is valid, then move the piece

                                // A pawns validMove method takes different parameters
                                if (pieceToMove instanceof PiecePawn) {
                                    if (((PiecePawn) pieceToMove).validMove(fromX, fromY, toX, toY, buttons, ((PiecePawn) pieceToMove).getColor())) {
                                        source.setIcon(icon);
                                        selectedButton.setIcon(null);
                                        currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                    }
                                // Kings have the ability to castle left or right, if both the king and the target rook have not moved
                                } else if (pieceToMove instanceof PieceKing){
                                    // If the king is making a regular move, switch its moved status to true
                                    if (pieceToMove.validMove(fromX, fromY, toX, toY, buttons)) {
                                        source.setIcon(icon);
                                        selectedButton.setIcon(null);
                                        if (currentTurn == Color.white){
                                            whiteKingMoved = true;
                                        } else {
                                            blackKingMoved = true;
                                        }
                                        currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                    // If castling to the kingside, make sure the king and the kingside rook have not moved
                                    } else if (toY - fromY == 2 && toX == fromX && buttons[toX][toY].getIcon() == null && fromY == 4) {
                                        if (((PieceKing) pieceToMove).checkCastlePath(fromX, toY, buttons)) {
                                            if (pieceToMove.getColor() == Color.black && !blackKingMoved && !kingsideBlackRookMoved) {
                                                buttons[fromX][6].setIcon(blackKing);
                                                buttons[fromX][5].setIcon(blackRook);
                                                buttons[fromX][fromY].setIcon(null);
                                                buttons[toX][7].setIcon(null);
                                                currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                            } else {
                                                if (!whiteKingMoved && !kingsideWhiteRookMoved) {
                                                    buttons[fromX][6].setIcon(whiteKing);
                                                    buttons[fromX][5].setIcon(whiteRook);
                                                    buttons[fromX][fromY].setIcon(null);
                                                    buttons[toX][7].setIcon(null);
                                                    currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                                }
                                            }
                                        }
                                    // If castling to the queenside, make sure the king and the queenside rook have not moved
                                    } else if (fromY - toY == 2 && toX == fromX && buttons[toX][toY].getIcon() == null && fromY == 4) {
                                        if (((PieceKing) pieceToMove).checkCastlePath(fromX, toY, buttons)) {
                                            if (pieceToMove.getColor() == Color.black && !blackKingMoved && !queensideBlackRookMoved) {
                                                buttons[fromX][2].setIcon(blackKing);
                                                buttons[fromX][3].setIcon(blackRook);
                                                buttons[fromX][fromY].setIcon(null);
                                                buttons[toX][0].setIcon(null);
                                                currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                            } else {
                                                if (!whiteKingMoved && !queensideWhiteRookMoved) {
                                                    buttons[fromX][2].setIcon(whiteKing);
                                                    buttons[fromX][3].setIcon(whiteRook);
                                                    buttons[fromX][fromY].setIcon(null);
                                                    buttons[toX][0].setIcon(null);
                                                    currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                                }
                                            }
                                        }
                                    }
                                // If the piece is not a king or pawn
                                } else {
                                    if (pieceToMove.validMove(fromX, fromY, toX, toY, buttons)) {
                                        if (pieceToMove instanceof PieceRook) {
                                            if (pieceToMove.getColor() == Color.black) {
                                                if (pieceToMove.getCol() == 7) {
                                                    kingsideBlackRookMoved = true;
                                                } else {
                                                    queensideBlackRookMoved = true;
                                                }
                                            } else {
                                                if (pieceToMove.getCol() == 7) {
                                                    kingsideWhiteRookMoved = true;
                                                } else {
                                                    queensideWhiteRookMoved = true;
                                                }
                                            }
                                        }
                                        source.setIcon(icon);
                                        selectedButton.setIcon(null);
                                        currentTurn = (currentTurn == Color.white) ? Color.black : Color.white;
                                    }
                                }
                                count--;
                            }
                        }
                    }
                });
                panel.add(button);
            }
        }

        //Add all pawns to their starting squares
        for (int i = 0; i < 8; i++) {
            buttons[1][i].setIcon(blackPawn);
            buttons[6][i].setIcon(whitePawn);
        }
        //Add black pieces to their starting squares
        buttons[0][0].setIcon(blackRook);
        buttons[0][1].setIcon(blackKnight);
        buttons[0][2].setIcon(blackBishop);
        buttons[0][3].setIcon(blackQueen);
        buttons[0][4].setIcon(blackKing);
        buttons[0][5].setIcon(blackBishop);
        buttons[0][6].setIcon(blackKnight);
        buttons[0][7].setIcon(blackRook);
        //Add white pieces to their starting squares
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

    // Get the row of the button that was clicked
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

    //Get the column of the button that was clicked
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

    //Get the type of piece that is on the square that was clicked
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

    //Run this to play the game
    public static void main(String[] args) {
        BoardGUI board = new BoardGUI();
    }
}
