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

    // Effect
    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();

    boolean gameOver;

    // Score
    int level = 1;
    int score;
    int line;

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

            // Check if the game is over
            if (currentMino.block[0].x == mino_startX && currentMino.block[0].y == mino_startY){
                // This means the current mino immediately collided a block. So it's xy are the same with the next mino
                gameOver = true;
                GamePanel.se.play(2,false);
                GamePanel.music.stop();
            }

            currentMino.deactivating = false;

            // Replace the current mino with the next mino
            currentMino = nextMino;
            currentMino.setXY(mino_startX, mino_startY);
            nextMino = randomMino();
            nextMino.setXY(nextMino_X, nextMino_Y);

            // When a mino becomes inactive, check if line(s) can be deleted
            checkDelete();

        } else currentMino.update();
    }
    private void checkDelete(){
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;

        while (x < right_x && y < bottom_y){
            for (int i = 0; i < staticBlocks.size(); i++){
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y){
                    // Increase the count if there is a static block
                    blockCount++;
                }
            }

            x += Block.size;

            if (x == right_x){

                // If the blockCount reaches 12 , that means the current y line is all filled with blocks. So we can delete them
                if (blockCount == 12){

                    effectCounterOn = true;
                    effectY.add(y);

                    for (int i = staticBlocks.size() - 1; i > -1; i--){ // Don't use loop from 0 to size of staticBlock because we remove the index of the arraylist
                        // Remove all the blocks in current y line
                        if (staticBlocks.get(i).y == y){
                            staticBlocks.remove(i);
                        }
                    }
                    lineCount++;
                    line++;

                    // Increase drop speed (1 is the fastest)
                    if (line % 10 == 0 && dropInterval > 1){
                        level++;
                        if (dropInterval > 10){
                            dropInterval -= 10;
                        } else {
                            dropInterval -= 1;
                        }
                    }

                    // A line has been deleted, so need to slide down all blocks above it
                    for (int i = 0; i < staticBlocks.size(); i++){
                        if (staticBlocks.get(i).y < y){
                            staticBlocks.get(i).y += Block.size;
                        }
                    }
                }

                blockCount = 0;
                x = left_x;
                y += Block.size;
            }
            // Add score
            if (lineCount > 0){
                GamePanel.se.play(1,false);
                int singleLineScore = 10 * level;
                score += singleLineScore * lineCount;
            }
        }
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
        g2.setFont(new Font("Arial",Font.PLAIN,30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next",x+70, y+30);

        // Draw score
        g2.drawRect(x, top_y, 250 , 230);
        x += 40;
        y = top_y + 50;
        g2.drawString("Level: "+ level, x, y); y+=70;
        g2.drawString("Lines: "+ line, x, y); y+=70;
        g2.drawString("Score: "+ score, x, y);


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

        // Draw effect
        if (effectCounterOn){
            effectCounter++;

            g2.setColor(Color.white);
            for (int i = 0; i < effectY.size(); i++){
                g2.fillRect(left_x, effectY.get(i), width, Block.size );
            }

            if (effectCounter == 7){
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
            }
        }

        // Draw pause and game over
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (gameOver){
            x = left_x + 50;
            y = top_y + 320;
            g2.drawString("Game Over",x, y);
        }
        if (KeyHandler.pause){
            x = left_x + 95;
            y = top_y + 320;
            g2.drawString("Paused", x, y);
        }
    }
}
