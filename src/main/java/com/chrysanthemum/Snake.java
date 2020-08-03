package com.chrysanthemum;

import java.awt.*;

/**
 * @author: omnifocus
 * @Date: 2020/8/3 9:01 PM
 * @Description: com.chrysanthemum
 * @version: 1.0
 */
///不用继承Frame
public class Snake /*extends Frame*/ {


    // 由多个Node组成
    private Node head ;
    private Node tail;


    public Snake(Node node) {
        head = node;
        tail = node;
    }

    public void addToTail(Node node) {
        tail.next = node;
    }

    /*至于怎么画是每一节做决定*/
    private class Node {
        public final int ROW;
        public  final int COL;
        public static final int SIZE = Yard.BLOCK_SIZE;
        private  int base_rand = 201;
        private  int base_rand_complement = 100;
        Node next;

       {
            // 刚好占满一个格子
            int rand_row = (int) (Math.random() * base_rand) + base_rand_complement;
            int rand_col = (int) (Math.random() * base_rand) + base_rand_complement;
            // 如果是202，那么就202-2，如果是200，结果就是200
            ROW = rand_row % 10 == 0 ? rand_row : rand_row - rand_row % 10;
            COL = rand_col % 10 == 0 ? rand_col : rand_col - rand_col % 10;
        }

        public void paint(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(ROW, COL, SIZE, SIZE);
            g.setColor(c);
        }

    }
}
