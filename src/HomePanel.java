import javafx.scene.layout.Border;

import java.awt.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

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
        try {
            new GameFrame();
        } catch (UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
