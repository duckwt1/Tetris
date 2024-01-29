package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(){
        JFrame tetris=new JFrame("Tetris");
        tetris.setBackground(Color.BLACK);
        tetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tetris.setResizable(false);

        GamePanel gamePanel=new GamePanel();
        tetris.add(gamePanel);
        tetris.pack();

        tetris.setLocationRelativeTo(null);
        tetris.setVisible(true);

        gamePanel.launchGame();
    }
}
