package Mino;

import java.awt.*;

public class Z1_Mino extends Mino{
    public Z1_Mino(){
        create(Color.red);
    }

    public void setXY(int x, int y) {
        //    0
        //  0 0
        //  0

        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x;
        block[1].y = block[0].y - Block.size;
        block[2].x = block[0].x - Block.size;
        block[2].y = block[0].y;
        block[3].x = block[0].x - Block.size;
        block[3].y = block[0].y + Block.size;
    }

    public void direction1() {
        //    0
        //  0 0
        //  0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x;
        tempB[1].y = block[0].y - Block.size;
        tempB[2].x = block[0].x - Block.size;
        tempB[2].y = block[0].y;
        tempB[3].x = block[0].x - Block.size;
        tempB[3].y = block[0].y + Block.size;

        updateXY(1);
    }

    public void direction2() {
        // 0 0
        //   0 0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x + Block.size;
        tempB[1].y = block[0].y;
        tempB[2].x = block[0].x;
        tempB[2].y = block[0].y - Block.size;
        tempB[3].x = block[0].x - Block.size;
        tempB[3].y = block[0].y - Block.size;

        updateXY(2);
    }

    public void direction3() {
        direction1();
    }

    public void direction4() {
        direction2();
    }
}
