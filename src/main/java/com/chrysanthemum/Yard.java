package com.chrysanthemum;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author: omnifocus
 * @Date: 2020/8/3 8:19 PM
 * @Description: com.chrysanthemum
 * @version: 1.0
 */
public class Yard extends Frame {

    public static final int ROWS = 30;
    public static final int COLUMNS = 30;
    public static final int BLOCK_SIZE = 20;
    private Snake snake = new Snake();
    private Image offScreenImage;
    private Egg egg = new Egg();

    public static void main(String[] args) {
        Yard yard = new Yard();
        yard.launch();
        new Thread(() -> {
            while (true) {
                yard.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void launch() {
        this.setSize(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        this.setLocation(100, 100);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });
    }



    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        g.setColor(Color.DARK_GRAY);
        //画横线
        for (int i = 0; i < ROWS; i++) {
            g.drawLine(0, BLOCK_SIZE * i, BLOCK_SIZE * COLUMNS, BLOCK_SIZE * i);
        }
        //画竖线
        for (int j = 0; j < COLUMNS; j++) {
            g.drawLine(BLOCK_SIZE * j, 0, BLOCK_SIZE * j, BLOCK_SIZE * ROWS);
        }
        snake.draw(g);
        egg.draw(g);
        g.setColor(c);

    }


}
