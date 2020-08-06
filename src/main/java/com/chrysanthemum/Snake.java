package com.chrysanthemum;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

enum Dir {
    L, R, U, D
}

/**
 * @author: omnifocus
 * @Date: 2020/8/3 9:01 PM
 * @Description: com.chrysanthemum
 * @version: 1.0
 */
///不用继承Frame
public class Snake /*extends Frame*/ {


    // 由多个Node组成
    private Node head;
    private Node tail;
    private Dir headDir = Dir.U;


    public Snake() {
        Node node = new Node(100,100,Dir.U);
        head = node;
        tail = node;
    }

    //画出每一节
    public void draw(Graphics g) {
        Node node = head;
        while (node != null) {
            node.draw(g);
            node = node.next;
        }
        move();

    }

    public void addToTail(/*Node node*/) {

        Node node = null;
        switch (tail.dir) {
            case U:
                node = new Node(tail.row +  head.w, tail.col, tail.dir);
                break;
            case D:
                node = new Node(tail.row -  head.w, tail.col, tail.dir);
                break;
            case L:
                node = new Node(tail.row, tail.col +  head.w, tail.dir);
                break;
            case R:
                node = new Node(tail.row, tail.col -  head.w, tail.dir);
                break;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    //控制蛇头的走向
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_LEFT:
                headDir = Dir.L;
                break;
            case VK_RIGHT:
                headDir = Dir.R;
                break;
            case VK_UP:
                headDir = Dir.U;
                break;
            case VK_DOWN:
                headDir = Dir.D;
                break;
        }

    }

    private void move() {
        addToHead();
        removeTail();
    }

    private void removeTail() {
        //怎么找到tail的前一个呢？
        tail = tail.prev;
        tail.next = null;
    }

    private void addToHead() {
        Node node = null;
        switch (headDir) {
            case U:
                node = new Node(head.row - head.w, head.col, headDir);
                break;
            case D:
                node = new Node(head.row +  head.w, head.col, headDir);
                break;
            case L:
                node = new Node(head.row, head.col -  head.w, headDir);
                break;
            case R:
                node = new Node(head.row, head.col +  head.w, headDir);
                break;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void eat(Egg egg) {
        if (getRectangle().intersects(egg.getRectangle())) {
            egg.reappear();
            addToHead();
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(head.col  , head.row , head.w, head.h );
    }

    /*至于怎么画是每一节做决定*/
    private class Node {
        public int row;
        public int col;
       int w = Yard.BLOCK_SIZE, h = w;
        //指向前一个
        Node prev;
        private int base_rand = 10;
        //指向下一个
        Node next;
        private int base_rand_complement = 10;
        Dir dir;

        {
            // 刚好占满一个格子
           row = (int) (Math.random() * base_rand) + base_rand_complement;
            col = (int) (Math.random() * base_rand) + base_rand_complement;
        }

        Node(int row, int col, Dir dir) {
            this(dir);
            this.row = row ;
            this.col = col ;


        }

        Node(Dir dir) {
            this.dir = dir;
        }

        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(col , row , w,h);
            g.setColor(c);
        }

    }
}
