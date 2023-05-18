import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeFrame extends JFrame{
    JPanel snake;
    HomeFrame() {
        JLabel SNAKE = new JLabel("SNAKE");
        SNAKE.setFont(new Font("Times New Roman", 1, 100));
        SNAKE.setForeground(Color.GREEN);
        snake = new JPanel();
        snake.setPreferredSize(new Dimension(0,322));
        snake.setBackground(Color.RED);
        snake.setFocusable(true);
        snake.add(SNAKE, BorderLayout.SOUTH);

        JPanel background = new JPanel();
        background.setPreferredSize(new Dimension(0,100));
        background.setBackground(Color.RED);
        background.setFocusable(true);

        this.setTitle("Snake");
        HomePanel h = new HomePanel();
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(600,600));
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        this.add(h, BorderLayout.SOUTH);
        this.add(snake,BorderLayout.CENTER);
        this.add(background, BorderLayout.NORTH);

        this.setLocationRelativeTo(null);


    }
}
