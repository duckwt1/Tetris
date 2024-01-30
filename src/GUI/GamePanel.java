package GUI;

import Controller.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int width=1280;
    public static final int height=720;
    final int fps=60;
    Thread gameThread;
    PlayManager pm;
    public static Sound music = new Sound();
    public  static Sound se = new Sound();

    public GamePanel(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setLayout(null);

        pm=new PlayManager();

        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
    }
    public void launchGame(){
        gameThread=new Thread(this);
        gameThread.start();

        music.play(0, true);
        music.loop();
    }
    @Override
    public void run() {
        //Game Loop
        double drawInterval = 1000000000 / fps; // 0.01666667s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }


    }
    public void update(){
        if (!KeyHandler.pause){
            if (!KeyHandler.pause && !pm.gameOver){
                pm.update();
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2= (Graphics2D) g;
        pm.draw(g2);
    }

}
