package org.example.mino;

import java.awt.*;

public class Mino_T extends Mino{
    public Mino_T(){
        create(Color.magenta);
    }

    public void setXY(int x, int y){
        //Default
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y - Block.SIZE;
        b[2].x = b[0].x - Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y;
    }

    public void getDirection1() {
        //default
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x - Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE;
        tempB[3].y = b[0].y;
        updateXY(1);
    }

    public void getDirection2() {
        //right rotation * 1
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + Block.SIZE;
        updateXY(2);
    }

    public void getDirection3() {
        //right rotation * 2
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + Block.SIZE;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y;
        updateXY(3);
    }

    public void getDirection4() {
        //right rotation * 3
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - Block.SIZE;
        updateXY(4);
    }
}
