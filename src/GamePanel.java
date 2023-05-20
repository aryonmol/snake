import com.sun.corba.se.impl.activation.ServerMain;
import sun.print.PathGraphics;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    File f = new File("snake/Sounds/BackgroundMusic.wav");
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
    Clip clip = AudioSystem.getClip();
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

    GamePanel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();



    }

    public void startGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        clip.open(audioIn);
        clip.start();
        clip.loop(4);



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.RED);
            Font f = new Font("Serif", Font.BOLD, 25);
            g.setFont(f);
            g.drawString(""+applesEaten, SCREEN_WIDTH-25,  25);
            try {

                String path = "snake/images/Apples.jpg";
                BufferedImage imageOrignal = ImageIO.read(new File(path));
                Image image = imageOrignal.getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_DEFAULT);
                g.drawImage(image, applesX, applesY, null);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    try {
                        String path = "snake/images/SnakeHead" + direction + ".jpg";
                        BufferedImage imageOrignal = ImageIO.read(new File(path));
                        Image image = imageOrignal.getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_DEFAULT);
                        g.drawImage(image, x[i], y[i], null);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else if (i == bodyParts - 1) {

                    try {

                        String path = "snake/images/SnakeTail" + getTailDirection() + ".jpg";
                        BufferedImage imageOrignal = ImageIO.read(new File(path));
                        Image image = imageOrignal.getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_DEFAULT);
                        g.drawImage(image, x[i], y[i], null);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                }
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, UNIT_SIZE, UNIT_SIZE);
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
                y[0] = y[0]  + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if ((x[0] == applesX) && (y[0] == applesY)) {

            bodyParts += 3;
            applesEaten++;
            newApple();
            playSound("AppleSound.wav");

        }
    }
    public void playSound(String fileName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File f = new File("snake/Sounds/"+fileName);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
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
            clip.stop();
            playSound("GameOver.wav");
            String path = "snake/images/GameOver.jpeg";
            BufferedImage imageOrignal = ImageIO.read(new File(path));
            Image image = imageOrignal.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_DEFAULT);
            g.drawImage(image, 0, 0, null);
            g.setColor(Color.RED);
            Font f = new Font("Serif", Font.BOLD, 50);
            g.setFont(f);
            g.drawString("Score: " + applesEaten, 225, 500);
            g.drawString("Score: " + applesEaten, 225, 500);
            JLabel SNAKE = new JLabel("SNAKE");
            SNAKE.setFont(new Font("Times New Roman", 1, 100));
            SNAKE.setForeground(Color.GREEN);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

    }

    public char getTailDirection() {
        if (x[bodyParts - 2] == x[bodyParts - 1] + UNIT_SIZE) {
            return 'R';
        } else if (x[bodyParts - 2] == x[bodyParts - 1] - UNIT_SIZE) {
            return 'L';
        } else if (y[bodyParts - 2] == y[bodyParts - 1] - UNIT_SIZE) {
            return 'U';
        } else {
            return 'D';
        }
    }
        public void actionPerformed (ActionEvent e){
            if (running) {
                move();
                try {
                    checkApple();
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

