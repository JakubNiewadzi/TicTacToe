package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    private JButton button;
    private JRadioButton onePlayer;
    private JRadioButton twoPlayers;
    private int playersAmount = 0;
    private JRadioButton cross;
    private JRadioButton circle;
    private GamePanel gp;
    private char start = 'n';

    public GameFrame() {

        button = new JButton();
        button.setBounds(250, 200, 100, 50);
        button.addActionListener(this);
        button.setText("Start!");
        button.setFocusable(true);
        onePlayer = new JRadioButton("1 Gracz");
        onePlayer.setBounds(100, 50, 150, 20);
        onePlayer.addActionListener(this);
        twoPlayers = new JRadioButton("2 Graczy");
        twoPlayers.setBounds(300, 50, 150, 20);
        twoPlayers.addActionListener(this);
        cross = new JRadioButton("Krzyżyk");
        cross.setBounds(100, 100, 150, 20);
        cross.addActionListener(this);
        circle = new JRadioButton("Kółko");
        circle.setBounds(300, 100, 150, 20);
        circle.addActionListener(this);

        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();

        bg1.add(onePlayer);
        bg1.add(twoPlayers);
        bg2.add(cross);
        bg2.add(circle);


        this.setSize(600, 600);
        this.setLayout(null);
        this.add(button);
        this.add(onePlayer);
        this.add(twoPlayers);
        this.add(cross);
        this.add(circle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kółko i krzyżyk");
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button && playersAmount != 0 && start!='n') {
            this.setLayout(new BorderLayout());
            this.remove(button);
            this.remove(onePlayer);
            this.remove(twoPlayers);
            this.remove(cross);
            this.remove(circle);
            gp = new GamePanel(playersAmount, start);
            this.add(gp);
            this.pack();
        } else if (e.getSource() == onePlayer) {
            playersAmount = 1;
        } else if (e.getSource() == twoPlayers) {
            playersAmount = 2;
        } else if(e.getSource()==cross){
            start='X';
        }else if(e.getSource()==circle){
            start='O';
        }
    }
}
