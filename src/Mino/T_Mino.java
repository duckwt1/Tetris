package Mino;

import java.awt.*;

public class T_Mino extends Mino{
    public T_Mino(){
        create(Color.magenta);
    }
    public void setXY(int x, int y) {
        //   0
        // 0 0 0

        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x;
        block[1].y = block[0].y - Block.size;
        block[2].x = block[0].x - Block.size;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.size;
        block[3].y = block[0].y;
    }
    public void direction1() {
        //   0
        // 0 0 0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x;
        tempB[1].y = block[0].y - Block.size;
        tempB[2].x = block[0].x - Block.size;
        tempB[2].y = block[0].y;
        tempB[3].x = block[0].x + Block.size;
        tempB[3].y = block[0].y;

        updateXY(1);
    }
    public void direction2() {
        // 0
        // 0 0
        // 0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x + Block.size;
        tempB[1].y = block[0].y;
        tempB[2].x = block[0].x;
        tempB[2].y = block[0].y - Block.size;
        tempB[3].x = block[0].x;
        tempB[3].y = block[0].y + Block.size;

        updateXY(2);

    }
    public void direction3() {
        // 0 0 0
        //   0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x;
        tempB[1].y = block[0].y + Block.size;
        tempB[2].x = block[0].x + Block.size;
        tempB[2].y = block[0].y;
        tempB[3].x = block[0].x - Block.size;
        tempB[3].y = block[0].y;

        updateXY(3);
    }

    public void direction4() {
        //   0
        // 0 0
        //   0

        tempB[0].x = block[0].x;
        tempB[0].y = block[0].y;
        tempB[1].x = block[0].x - Block.size;
        tempB[1].y = block[0].y;
        tempB[2].x = block[0].x;
        tempB[2].y = block[0].y + Block.size;
        tempB[3].x = block[0].x;
        tempB[3].y = block[0].y - Block.size;

        updateXY(4);
    }
}
