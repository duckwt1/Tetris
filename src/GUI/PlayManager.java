package GUI;

import Controller.KeyHandler;
import Mino.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayManager {
    final int width=360;
    final  int height=600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    //Mino
    Mino currentMino;
    final int mino_startX;
    final int mino_startY;
    Mino nextMino;
    final int nextMino_X;
    final int nextMino_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    //Others
    public static int dropInterval = 60; //Minos drop in every 60 frames

    public PlayManager(){
        //Main play area frame
        left_x = (GamePanel.width / 2) - (width / 2); // =460
        right_x = left_x + width;
        top_y = 50;
        bottom_y = top_y + height;

        mino_startX = left_x + (width / 2) - Block.size;
        mino_startY = top_y + Block.size;

        nextMino_X = right_x + 190;
        nextMino_Y = top_y + 500;

        //Set the starting mino
        currentMino = randomMino();
        currentMino.setXY(mino_startX,mino_startY);

        nextMino = randomMino();
        nextMino.setXY(nextMino_X, nextMino_Y);
    }
    private Mino randomMino(){
        // Random a mino
        Mino mino = null;
        int ran = new Random().nextInt(7);
        switch (ran){
            case 0: mino =  new L1_Mino(); break;
            case 1: mino =  new L2_Mino(); break;
            case 2: mino =  new Square_Mino(); break;
            case 3: mino =  new T_Mino(); break;
            case 4: mino =  new Bar_Mino(); break;
            case 5: mino =  new Z1_Mino(); break;
            case 6: mino =  new Z2_Mino(); break;
        }
        return mino;
    }
    public void update(){
        // Check if the current mino is active
        if (!currentMino.active) {
            // If the mino is not active, put it into the staticBlocks
            staticBlocks.add(currentMino.block[0]);
            staticBlocks.add(currentMino.block[1]);
            staticBlocks.add(currentMino.block[2]);
            staticBlocks.add(currentMino.block[3]);

            currentMino.deactivating = false;

            // Replace the current mino with the next mino
            currentMino = nextMino;
            currentMino.setXY(mino_startX, mino_startY);
            nextMino = randomMino();
            nextMino.setXY(nextMino_X, nextMino_Y);
        } else currentMino.update();


    }
    public void draw(Graphics2D g2){
        //Draw play area
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x-4, top_y-4, width+8, height+8);

        //Draw next mino
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Arial",Font.PLAIN,20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT",x+70, y+30);

        //Draw current mino
        if (currentMino != null){
            currentMino.draw(g2);
        }

        // Draw next mino
        nextMino.draw(g2);

        // Draw staticBlocks
        for (int i = 0; i < staticBlocks.size(); i++){
            staticBlocks.get(i).draw(g2);
        }

        // Draw pause
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (KeyHandler.pause){
            x = left_x + 95;
            y = top_y + 320;
            g2.drawString("Paused", x, y);
        }
    }
}
