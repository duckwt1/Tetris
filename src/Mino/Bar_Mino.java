package Mino;

import java.awt.*;

public class Bar_Mino extends Mino{
    public Bar_Mino(){
        create(Color.blue);
    }

    public void setXY(int x, int y) {
        // 0 0 0 0

        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x - Block.size;
        block[1].y = block[0].y;
        block[2].x = block[0].x + Block.size;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.size*2;
        block[3].y = block[0].y;
    }

    public void direction1() {
        // 0 0 0 0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x - Block.size;
        tempB[1].y = block[0].y;
        tempB[2].x = block[0].x + Block.size;
        tempB[2].y = block[0].y;
        tempB[3].x = block[0].x + Block.size*2;
        tempB[3].y = block[0].y;

        updateXY(1);
    }

    public void direction2() {
        // 0
        // 0
        // 0
        // 0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x;
        tempB[1].y = block[0].y - Block.size;
        tempB[2].x = block[0].x;
        tempB[2].y = block[0].y + Block.size;
        tempB[3].x = block[0].x;
        tempB[3].y = block[0].y + Block.size*2;

        updateXY(2);
    }
    public void direction3() {
        direction1();
    }

    public void direction4() {
        direction2();
    }
}
