package GUI;

import Mino.*;
import java.awt.*;

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

        //Set the starting mino
        currentMino = new L1_Mino();
        currentMino.setXY(mino_startX,mino_startY);
    }
    public void update(){
        currentMino.update();
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

    }
}
