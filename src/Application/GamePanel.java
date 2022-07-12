package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GamePanel extends JPanel {
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 600;
    public static final int UNIT_SIZE = 200;
    private char[][] board = new char[3][3];
    public boolean running = true;
    private int playerAmount;
    private char symbol = 'X';

    private char humanSymbol;
    private char winner;

    public GamePanel(int playerAmount, char humanSymbol) {
        this.addMouseListener(new MyMouseListener());
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.setVisible(true);
        this.playerAmount = playerAmount;
        this.humanSymbol = humanSymbol;
        if (this.playerAmount == 2) {
            this.symbol = humanSymbol;
        } else if (this.playerAmount == 1 && this.humanSymbol == 'O') {
            makeMove();
            repaint();
            this.symbol = 'O';
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 1; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'O') {
                    g.drawOval(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                } else if (board[i][j] == 'X') {
                    g.drawLine(j * UNIT_SIZE, i * UNIT_SIZE, (j + 1) * UNIT_SIZE, (i + 1) * UNIT_SIZE);
                    g.drawLine((j + 1) * UNIT_SIZE, i * UNIT_SIZE, (j) * UNIT_SIZE, (i + 1) * UNIT_SIZE);
                }
            }
        }
        if (!running) {
            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            if(winner=='O' || winner=='X') {
                g.drawString("Wygrał gracz: " + winner, (SCREEN_WIDTH - metrics1.stringWidth("Wygrał gracz: " + winner)) / 2, g.getFont().getSize());
            }else{
                g.drawString("Remis!", (SCREEN_WIDTH - metrics1.stringWidth("Remis!")) / 2, g.getFont().getSize());
            }
        }

    }

    public void checkWinner(char winner) {
        if (winner == 'X') {
            this.winner = 'X';
            running = false;
            repaint();
        } else if (winner == 'O') {
            this.winner = 'O';
            running = false;
            repaint();
        } else if (winner == 'N') {
            this.winner = 'N';
            running = false;
            repaint();
        }
    }

    public char checkLines(char[][] board) {
        int linia = 0;
        for (int i = 0; i < 3; i++) {
            linia = 0;
            for (int j = 0; j < 3; j++) {
                if ((board[i][0] == board[i][j]) && (board[i][j] == 'X' || board[i][j] == 'O')) {
                    linia++;
                }
                if (linia == 3) {
                    return board[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            linia = 0;
            for (int j = 0; j < 3; j++) {
                if ((board[0][i] == board[j][i]) && (board[j][i] == 'X' || board[j][i] == 'O')) {
                    linia++;
                }
                if (linia == 3) {
                    return board[j][i];
                }
            }
        }
        linia = 0;
        for (int i = 0; i < 3; i++) {
            if ((board[i][i] == board[0][0]) && (board[0][0] == 'X' || board[0][0] == 'O')) {
                linia++;
            }
        }
        if (linia == 3) {
            return board[0][0];
        }
        linia = 0;
        for (int i = 0; i < 3; i++) {
            if ((board[i][2 - i] == board[0][2]) && (board[0][2] == 'X' || board[0][2] == 'O')) {
                linia++;
            }
        }
        if (linia == 3) {
            return board[0][2];
        }
        linia = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X' || board[i][j] == 'O') {
                    linia++;
                }
            }
        }
        if (linia == 9) {
            return 'N';

        }
        return 'G';
    }

    private void makeMove() {
        int bestI = -1;
        int bestJ = -1;
        int score;
        if (running && humanSymbol == 'X') {
            int bestScore = (int) Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        board[i][j] = 'O';
                        score = miniMax(board, 9, false);
                        board[i][j] = 'n';
                        if (score > bestScore) {
                            bestScore = score;
                            bestI = i;
                            bestJ = j;
                        }
                    }
                }
            }
            board[bestI][bestJ] = 'O';
        }else if(running && humanSymbol == 'O'){
            int bestScore = (int) Double.POSITIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        board[i][j] = 'X';
                        score = miniMax(board, 9, true);
                        board[i][j] = 'n';
                        if (score < bestScore) {
                            bestScore = score;
                            bestI = i;
                            bestJ = j;
                        }
                    }
                }
            }
            board[bestI][bestJ] = 'X';
        }
    }

    public int miniMax(char[][] board, int depth, boolean maximizingPlayer) {
        char winner = checkLines(board);
        if (winner == 'X') {
            return -1;
        } else if (winner == 'O') {
            return 1;
        } else if (winner == 'N') {
            return 0;
        }
        int score;
        if (maximizingPlayer == true) {
            int bestScore = (int) Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        board[i][j] = 'O';
                        score = miniMax(board, depth + 1, false);
                        board[i][j] = 'n';
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = (int) Double.POSITIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        board[i][j] = 'X';
                        score = miniMax(board, depth + 1, true);
                        board[i][j] = 'n';
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }


    class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (running) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (e.getX() > j * 200 && e.getX() < (j + 1) * 200 && e.getY() > i * 200 && e.getY() < (i + 1) * 200) {
                            if (board[i][j] != 'X' && board[i][j] != 'O') {
                                board[i][j] = symbol;
                                if (symbol == 'X') {
                                    symbol = 'O';
                                } else {
                                    symbol = 'X';
                                }
                                repaint();
                                checkWinner(checkLines(board));
                                if (playerAmount == 1) {
                                    if (humanSymbol == 'X') {
                                        makeMove();
                                        symbol = 'X';
                                    } else {
                                        makeMove();
                                        symbol = 'O';
                                    }
                                    repaint();
                                    checkWinner(checkLines(board));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
