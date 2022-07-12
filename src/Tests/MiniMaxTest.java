package Tests;

import Application.GamePanel;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertNotEquals;

public class MiniMaxTest {

    private char board[][];
    private char aiPlayer;
    Random random;

    GamePanel gp;

    @Before
    public void init(){
        board= new char[3][3];
        random = new Random();
        int player=random.nextInt(2);
        if(player==0){
            aiPlayer='X';
        }else{
            aiPlayer='O';
        }
        gp=new GamePanel(1, 'X');
    }

    @Test
    public void miniMaxTest(){
        int bestI = -1;
        int bestJ = -1;
        int score;
        if (aiPlayer == 'O') {
            int bestScore = (int) Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        board[i][j] = 'O';
                        score = gp.miniMax(board, 9, false);
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
            assertNotEquals(bestScore, -1);
        }else if(aiPlayer == 'X'){
            int bestScore = (int) Double.POSITIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != 'X' && board[i][j] != 'O') {
                        board[i][j] = 'X';
                        score = gp.miniMax(board, 9, true);
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
            assertNotEquals(bestScore, 1);
        }
    }

}
