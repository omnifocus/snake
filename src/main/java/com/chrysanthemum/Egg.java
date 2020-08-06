package com.chrysanthemum;

import java.awt.*;

/**
 * @author: omnifocus
 * @Date: 2020/8/5 9:55 PM
 * @Description: com.chrysanthemum
 * @version: 1.0
 */
public class Egg  {
    int col, row;
    int w = Yard.BLOCK_SIZE, h = w;
    Color prev = Color.RED;

    public Egg() {
        randomColRow();
    }


    public void draw(Graphics g) {
        Color c = g.getColor();
        //交替改变红绿
        prev = prev == Color.RED ? Color.GREEN : Color.RED;
        g.setColor(prev);
        // *w *h = real position
        g.fillOval(col  ,row ,w,h);
        g.setColor(c);
    }

    public Rectangle getRectangle() {
        return new Rectangle(col  , row , w, h );
    }

    public void reappear() {
        randomColRow();
    }

    private void randomColRow() {
        col = ((int)(Math.random() * Yard.COLUMNS )) * Yard.BLOCK_SIZE;
        //往下，加w
        row =( (int)(Math.random() * (Yard.ROWS - w) )  + w)* Yard.BLOCK_SIZE ;
    }


}
