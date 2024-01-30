package Controller;

import GUI.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public static boolean up, down, left, right, pause;
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){
            up = true;
        }
        if (code == KeyEvent.VK_DOWN){
            down = true;
        }
        if (code == KeyEvent.VK_RIGHT){
            right = true;
        }
        if (code == KeyEvent.VK_LEFT){
            left = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            if (pause){
                pause = false;
                GamePanel.music.play(0, true);
                GamePanel.music.loop();
            } else {
                pause = true;
                GamePanel.music.stop();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
