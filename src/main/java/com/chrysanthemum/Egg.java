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

    public Egg() {
        col = (int)(Math.random() * Yard.COLUMNS);
        row = (int)(Math.random() * Yard.ROWS);
    }


    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        // *w *h = real position
        g.fillOval(col * w ,row * h,w,h);
        g.setColor(c);
    }
}
