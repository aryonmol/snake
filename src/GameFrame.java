<<<<<<< HEAD
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame{

    GameFrame() {
=======
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.io.IOException;

public class GameFrame extends JFrame{
    GameFrame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
>>>>>>> 52f68e4ef024ecfa7f98fcca303d82759bcde055
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); //adds jframe components to game frame
        this.setVisible(true);
        this.setLocationRelativeTo(null); //puts the window in the middle of the screen

    }
}
