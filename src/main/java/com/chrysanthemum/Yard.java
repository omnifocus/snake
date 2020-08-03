package com.chrysanthemum;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author: omnifocus
 * @Date: 2020/8/3 8:19 PM
 * @Description: com.chrysanthemum
 * @version: 1.0
 */
public class Yard extends Frame {

    private final int ROWS = 100;
    private final int COLUMNS = 100;
    private final int BLOCK_SIZE = 5;

    public static void main(String[] args) {
        new Yard().launch();
    }

    public void launch() {
        this.setSize(COLUMNS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        this.setLocation(100,100);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
