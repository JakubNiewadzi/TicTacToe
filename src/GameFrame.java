import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    JButton button;
    JRadioButton onePlayer;
    JRadioButton twoPlayers;
    int playersAmount=0;
    private
    JRadioButton cross;
    JRadioButton circle;
    GamePanel gp;
    public GameFrame(){
        button = new JButton();
        button.setBounds(250, 275 ,100 ,50);
        button.addActionListener(this);
        button.setText("Start!");
        button.setFocusable(true);
        onePlayer=new JRadioButton("1 Gracz");
        onePlayer.setBounds(100, 100, 150 ,20);
        onePlayer.addActionListener(this);
        twoPlayers=new JRadioButton("2 Graczy");
        twoPlayers.setBounds(300, 100, 150 ,20);
        twoPlayers.addActionListener(this);
        cross = new JRadioButton();
        circle = new JRadioButton();

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(onePlayer);
        bg1.add(twoPlayers);



        this.setSize(600, 600);
        this.setLayout(null);
        this.add(button);
        this.add(onePlayer);
        this.add(twoPlayers);

        //this.pack();
        //this.add(gp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(this.getLayout());

        this.setTitle("Kółko i krzyżyk");
        this.setResizable(false);
        this.setVisible(true);



        this.setLocationRelativeTo(null);

        //gp.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button && playersAmount!=0){
            this.setLayout(new BorderLayout());
            this.remove(button);
            this.remove(onePlayer);
            this.remove(twoPlayers);
            gp = new GamePanel(playersAmount);
            this.add(gp);
            this.pack();
        }else if(e.getSource()==onePlayer){
            playersAmount=1;
        }else if(e.getSource()==twoPlayers){
            playersAmount=2;
        }
    }
}
