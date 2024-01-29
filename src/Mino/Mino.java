package Mino;

import GUI.PlayManager;

import java.awt.*;

public class Mino {
    public Block block[] = new Block[4];
    public  Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    public void create(Color color){
        block[0] = new Block(color);
        block[1] = new Block(color);
        block[2] = new Block(color);
        block[3] = new Block(color);
        tempB[0] = new Block(color);
        tempB[1] = new Block(color);
        tempB[2] = new Block(color);
        tempB[3] = new Block(color);
    }
    public void setXY(int x, int y){

    }
    public void updateXY(int direction){

    }
    public void update(){
        autoDropCounter++; // The counter increases in every frame
        if (autoDropCounter == PlayManager.dropInterval){
            //The mino goes down
            block[0].y += Block.size;
            block[1].y += Block.size;
            block[2].y += Block.size;
            block[3].y += Block.size;
            autoDropCounter = 0;
        }
    }
    public void draw(Graphics2D g2){

        int margin = 1;
        g2.setColor(block[0].color);
        g2.fillRect(block[0].x + margin, block[0].y + margin, Block.size - (margin*2), Block.size - (margin*2));
        g2.fillRect(block[1].x + margin, block[1].y + margin, Block.size - (margin*2), Block.size - (margin*2));
        g2.fillRect(block[2].x + margin, block[2].y + margin, Block.size - (margin*2), Block.size - (margin*2));
        g2.fillRect(block[3].x + margin, block[3].y + margin, Block.size - (margin*2), Block.size - (margin*2));
    }
}

