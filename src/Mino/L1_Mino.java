package Mino;

import java.awt.*;

public class L1_Mino extends Mino{
    public L1_Mino(){
        create(Color.ORANGE);
    }

    @Override
    public void setXY(int x, int y) {
        // 0        b1
        // 0        b0
        // 0 0      b2 b3
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x;
        block[1].y = block[0].y - Block.size;
        block[2].x = block[0].x;
        block[2].y = block[0].y + Block.size;
        block[3].x = block[0].x + Block.size;
        block[3].y = block[0].y + Block.size;

    }
}
