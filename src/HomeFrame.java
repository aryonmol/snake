import javax.swing.*;
import java.awt.event.*;

public class HomeFrame extends JFrame implements MouseListener{
    JButton play;
    boolean mouseClick;
    boolean mousePressed;

    // JLabel music;
    // boolean musicPlaying;

    HomeFrame(){
        this.add(new HomePanel());
        this.setTitle("SNAKE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

      //  play = new JButton();
      //  play.setText("PLAY");
      //  play.setBounds(150,350,300,100);
      //  play.setBackground(Color.BLUE);
      //  play.setOpaque(true);
      //  play.addMouseListener(this);
      //  this.add(play);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //if (play.)
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
