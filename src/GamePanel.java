import javafx.scene.image.ImageView;
import sun.java2d.pipe.DrawImage;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int applesX;
    int applesY;
    char direction = 'R';
    boolean running = false;

    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();


    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {

            try {
                String path="/Users/amolavi23/IdeaProjects/snake/src/Apples.jpg";
                BufferedImage imageOrignal=ImageIO.read(new File(path));
                Image image=imageOrignal.getScaledInstance(UNIT_SIZE,UNIT_SIZE,Image.SCALE_DEFAULT);
                g.drawImage(image,applesX,applesY,null);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    try {
                        String path="../images/SnakeHead"+direction+".jpg";
                        BufferedImage imageOrignal=ImageIO.read(new File(path));
                        Image image=imageOrignal.getScaledInstance(UNIT_SIZE,UNIT_SIZE,Image.SCALE_DEFAULT);
                        g.drawImage(image,x[i],y[i],null);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    g.setColor(Color.WHITE);
                    g.fillRect(0,0,UNIT_SIZE,UNIT_SIZE);

                }
            }
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        applesX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        applesY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple() {
        if ((x[0] == applesX) && (y[0] == applesY)) {
            bodyParts+=3;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) { //head collides with body
            if ((x[0] == x[i]) && y[0] == y[i]) {
                running = false;
            }
        }
        if (x[0] < 0) { //left border
            running = false;
        }
        if (x[0] > SCREEN_WIDTH) { //right border
            running = false;
        }
        if (y[0] < 0) { //top border
            running = false;
        }
        if (y[0] > SCREEN_HEIGHT) { //bottom border
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        //Score
        try {
            String path="/Users/amolavi23/IdeaProjects/snake/src/GameOver.jpeg";
            BufferedImage imageOrignal=ImageIO.read(new File(path));
            Image image=imageOrignal.getScaledInstance(SCREEN_WIDTH,SCREEN_HEIGHT,Image.SCALE_DEFAULT);
            g.drawImage(image,0,0,null);
            g.setColor(Color.RED);
            Font f=new Font("Serif", Font.BOLD,50);
            g.setFont(f);
            g.drawString("Score: "+applesEaten, 225,500);
            g.drawString("Score: "+applesEaten, 225,500);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
