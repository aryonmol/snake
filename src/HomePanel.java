import javafx.scene.layout.Border;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import static javafx.application.Platform.exit;

public class HomePanel extends JPanel implements MouseListener{
    protected JButton play = new JButton("PLAY");
    //JButton music
    HomePanel() {
        this.setPreferredSize(new Dimension(0,250));
        this.setBackground(Color.RED);
        this.setFocusable(true);

        play.setBackground(Color.BLACK);
        play.setOpaque(true);
        play.addMouseListener(this);
        play.setVisible(true);
        play.setPreferredSize(new Dimension(300,100));
        this.add(play);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        new GameFrame();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
