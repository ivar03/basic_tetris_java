package org.example.mino;

import java.awt.*;

public class Block extends Rectangle {
    public int x, y;
    public static final int SIZE = 30; //30 px block
    public Color c;

    public Block(Color c){
        this.c = c;
    }

    public void draw(Graphics2D g2){
        int m =2;
        g2.setColor(c);
        g2.fillRect(x + m,y + m,SIZE - (2*m),SIZE - (2*m));
    }
}
