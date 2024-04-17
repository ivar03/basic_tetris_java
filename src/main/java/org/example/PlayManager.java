package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import org.example.mino.*;

public class PlayManager {
    //main play area
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    //mino
    Mino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;
    Mino nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    //drop mino
    public static int dropInterval = 60; // mino drop every 60 frames
    boolean gameOver;

    //score
    int level = 1;
    int lines;
    int score;

    public PlayManager(){
        //play area frame
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2); // 1280/2 - 360/2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;
        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;

        //starting mino
        currentMino = pickMino();
        currentMino.setXY(MINO_START_X,MINO_START_Y);
        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_X,NEXTMINO_Y);
    }

    public Mino pickMino(){
        //randomly picking
        Mino mino = null;
        int i = new Random().nextInt(7);
        switch (i){
            case 0: mino = new Mino_L1();break;
            case 1: mino = new Mino_L2();break;
            case 2: mino = new Mino_Square();break;
            case 3: mino = new Mino_Bar();break;
            case 4: mino = new Mino_T();break;
            case 5: mino = new Mino_Z1();break;
            case 6: mino = new Mino_Z2();break;
        }
        return mino;
    }

    public void update(){
        if (!currentMino.active){
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);
            //game over check
            if (currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y){
                gameOver = true;
            }
            currentMino.deactivating = false;
            //replace current mino
            currentMino = nextMino;
            currentMino.setXY(MINO_START_X,MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_X,NEXTMINO_Y);
            checkDelete();
        }else currentMino.update();
    }

    public void checkDelete(){
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;
        while (x < right_x && y < bottom_y){
            for (int i = 0; i < staticBlocks.size(); i++){
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y){
                    blockCount++;
                }
            }
            x += Block.SIZE;
            if (x == right_x){
                if (blockCount == 12){
                    for (int i = staticBlocks.size()-1; i > -1; i--){
                        if (staticBlocks.get(i).y == y){
                            staticBlocks.remove(i);
                        }
                    }
                    lineCount++;
                    lines++;
                    if (lines % 10 == 0 && dropInterval > 1){
                        level++;
                        if (dropInterval > 10){
                            dropInterval -= 10;
                        }else {
                            dropInterval -= 1;
                        }
                    }
                    for (int i = 0; i < staticBlocks.size(); i++){
                        if (staticBlocks.get(i).y < y){
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }
                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }
        if (lineCount > 0){
            int singleLineCount = 10 * level;
            score += singleLineCount * lineCount;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);

        //next mino frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x+60,y+60);

        //score
        g2.drawRect(x,top_y,250,300);
        x += 40;
        y = top_y + 90;
        g2.drawString("LEVEL: " + level,x,y); y+= 70;
        g2.drawString("LINES: " + lines,x,y); y+= 70;
        g2.drawString("SCORES: " + score,x,y); y+= 70;

        //drawing mino
        if (currentMino != null){
            currentMino.draw(g2);
        }

        //draw next mino
        nextMino.draw(g2);

        //draw static block
        for (int i = 0; i < staticBlocks.size(); i++){
            staticBlocks.get(i).draw(g2);
        }

        //drawing pause
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (gameOver){
            x = left_x + 25;
            y = top_y + 320;
            g2.drawString("GAME OVER",x,y);
        }
        else if (KeyHandler.pausePressed) {
            x = left_x + (WIDTH / 2) - 100;
            y = top_y + (HEIGHT / 2) - 25;
            g2.drawString("PAUSED", x, y);
        }
        x = 30;
        y = top_y + 320;
        g2.setColor(Color.white);
        g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
        g2.drawString("Simple Tetris", x, y);
    }
}
