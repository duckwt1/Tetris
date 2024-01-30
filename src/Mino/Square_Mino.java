package Mino;

import java.awt.*;

public class Square_Mino extends Mino{
    public Square_Mino(){
        create(Color.green);
    }

    public void setXY(int x, int y) {
        // 0 0
        // 0 0

        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x + Block.size;
        block[1].y = block[0].y;
        block[2].x = block[0].x;
        block[2].y = block[0].y + Block.size;
        block[3].x = block[0].x + Block.size;
        block[3].y = block[0].y + Block.size;
    }

    public void direction1() {

    }

    public void direction2() {

    }

    public void direction3() {

    }

    public void direction4() {
        super.direction4();
    }
}
