import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HomePanel extends JPanel{
    JButton play;
    HomePanel() {
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.RED);
        this.setFocusable(true);

        play = new JButton("PLAY");
        play.setBounds(150,350,300,100);
        play.setBackground(Color.BLUE);
        play.setOpaque(true);
        play.addMouseListener((MouseListener) this);
        this.add(play);
    }


}
