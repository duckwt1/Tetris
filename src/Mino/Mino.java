package Mino;

import Controller.KeyHandler;
import GUI.PlayManager;

import java.awt.*;

public class Mino {
    public Block block[] = new Block[4];
    public  Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1;
    boolean left, right, bottom;
    public boolean active = true;
    public boolean  deactivating;
    int deactivateCounter = 0;

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

        checkRotationCollision();

        if (!left && !right && !bottom){
            this.direction = direction;
            block[0].x = tempB[0].x;
            block[0].y = tempB[0].y;
            block[1].x = tempB[1].x;
            block[1].y = tempB[1].y;
            block[2].x = tempB[2].x;
            block[2].y = tempB[2].y;
            block[3].x = tempB[3].x;
            block[3].y = tempB[3].y;
        }
    }
    public void direction1(){}
    public void direction2(){}
    public void direction3(){}
    public void direction4(){}
    public void checkMovementCollision(){
        left = false;
        right = false;
        bottom = false;

        checkStaticBlocksCollision();

        // Check frame collision
        // Left wall
        for (int i = 0; i < block.length; i++){
            if (block[i].x == PlayManager.left_x){
                left = true;
            }
        }
        // Right wall
        for (int i = 0; i < block.length; i++){
            if (block[i].x + Block.size == PlayManager.right_x){
                right = true;
            }
        }
        // Bottom floor
        for (int i = 0; i < block.length; i++){
            if (block[i].y + Block.size == PlayManager.bottom_y){
                bottom = true;
            }
        }
    }
    public void checkRotationCollision(){
        left = false;
        right = false;
        bottom = false;

        checkStaticBlocksCollision();

        // Check frame collision
        // Left wall
        for (int i = 0; i < block.length; i++){
            if (tempB[i].x < PlayManager.left_x){
                left = true;
            }
        }
        // Right wall
        for (int i = 0; i < block.length; i++){
            if (tempB[i].x + Block.size > PlayManager.right_x){
                right = true;
            }
        }
        // Bottom floor
        for (int i = 0; i < block.length; i++){
            if (tempB[i].y + Block.size > PlayManager.bottom_y){

            }
        }
    }
    private void checkStaticBlocksCollision(){
        for (int i = 0; i < PlayManager.staticBlocks.size(); i++){
            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            // Check down
            for (int j = 0; j < block.length; j++){
                if (block[j].y + Block.size == targetY && block[j].x == targetX ){
                    bottom = true;
                }
            }
            // Check left
            for (int j = 0; j < block.length; j++){
                if (block[j].x - Block.size == targetX && block[j].y == targetY){
                    left = true;
                }
            }
            // Check right
            for (int j = 0; j < block.length; j++){
                if (block[j].x + Block.size == targetX && block[j].y == targetY){
                    right = true;
                }
            }

        }
    }
    public void update(){

        if (deactivating){
            deactivating();
        }

        if (KeyHandler.up){
            switch (direction){
                case 1: direction2(); break;
                case 2: direction3(); break;
                case 3: direction4(); break;
                case 4: direction1(); break;
            }
            KeyHandler.up = false;
        }

        checkMovementCollision();

        if (KeyHandler.down){
            // If the mino's bottom is not hitting , it can go down
            if (!bottom){
                block[0].y += Block.size;
                block[1].y += Block.size;
                block[2].y += Block.size;
                block[3].y += Block.size;

                autoDropCounter = 0;

            }
            KeyHandler.down = false;
        }
        if (KeyHandler.left){

            if (!left){
                block[0].x -= Block.size;
                block[1].x -= Block.size;
                block[2].x -= Block.size;
                block[3].x -= Block.size;

            }
            KeyHandler.left = false;
        }
        if(KeyHandler.right){
            if (!right){
                block[0].x += Block.size;
                block[1].x += Block.size;
                block[2].x += Block.size;
                block[3].x += Block.size;


            }
            KeyHandler.right = false;
        }

        if (bottom){
            deactivating = true;
        }
        else  {
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
    }

    private void deactivating(){
        deactivateCounter++;

        // Wait 45 frames until deactivte
        if (deactivateCounter == 45){
            deactivateCounter = 0;
            checkMovementCollision(); // Check if the bottom is still hitting

            // If the bottom is still hitting after 45 frames, deactivate the mino
            if (bottom){
                active = false;
            }
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

