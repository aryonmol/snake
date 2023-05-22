import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeFrame extends JFrame implements MouseListener{
    JPanel snake;
    JPanel home;
    protected JButton play = new JButton("PLAY");

    HomeFrame() {
        home = new JPanel();
        home.setPreferredSize(new Dimension(600,250));
        home.setBackground(Color.RED);
        home.setFocusable(true);

        play.setBackground(Color.BLACK);
        play.setOpaque(true);
        play.addMouseListener(this);
        play.setVisible(true);
        play.setPreferredSize(new Dimension(300,100));
        home.add(play);

        JLabel SNAKE = new JLabel("Snake");
        SNAKE.setFont(new Font("Verdana", 1, 100));
        SNAKE.setForeground(Color.GREEN);
        snake = new JPanel();
        snake.setPreferredSize(new Dimension(600,322));
        snake.setBackground(Color.RED);
        snake.setFocusable(true);
        snake.add(SNAKE, BorderLayout.SOUTH);

        JPanel background = new JPanel();
        background.setPreferredSize(new Dimension(600,100));
        background.setBackground(Color.RED);
        background.setFocusable(true);

        this.setTitle("Snake");
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(600,600));
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        this.add(home, BorderLayout.SOUTH);
        this.add(snake,BorderLayout.CENTER);
        this.add(background, BorderLayout.NORTH);

        this.setLocationRelativeTo(null);
        }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dispose();
        new GameFrame();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

