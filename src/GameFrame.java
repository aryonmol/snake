import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.io.IOException;

public class GameFrame extends JFrame{
    GameFrame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); //adds jframe components to game frame
        this.setVisible(true);
        this.setLocationRelativeTo(null); //puts the window in the middle of the screen
    }
}
