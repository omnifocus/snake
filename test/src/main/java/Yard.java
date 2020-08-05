import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ��������̰���ߵĻ����
 * @author bjsxt
 * @version 1.0
 */
public class Yard extends Frame {

	/**
	 * ����
	 */
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	PaintThread paintThread = new PaintThread();
	Snake s = new Snake(this);
	Egg e = new Egg();
	Image offScreenImage = null;
	private boolean gameOver = false; //��Ϸ�Ƿ����
	private Font fontGameOver = new Font("����", Font.BOLD, 50);
	private int score = 0;
	
	public static void main(String[] args) {
		new Yard().launch();
	}
	
	public void launch() {
		this.setLocation(200, 200);
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());

		new Thread(paintThread).start();
	}
	
	public void stop() {
		gameOver = true;
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		//��������
		for(int i=1; i<ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		for(int i=1; i<COLS; i++) {
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		
		g.setColor(Color.YELLOW);
		g.drawString("score:" + score, 10, 60);
		
		if(gameOver) {
			g.setFont(fontGameOver);
			g.drawString("��Ϸ����", 120, 180);
			
			paintThread.pause();
		}
		
		g.setColor(c);
		
		s.eat(e);
		e.draw(g);
		s.draw(g);
		
		
	}
	
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	
	/**
	 * �õ����õķ���
	 * @return ����
	 */

	public int getScore() {
		return score;
	}
	
	/**
	 * �������õķ���
	 * @param score ����
	 */

	public void setScore(int score) {
		this.score = score;
	}
	
	private class PaintThread implements Runnable {
		private boolean running = true;
		private boolean pause = false;
		public void run() {
			while(running) {
				if(pause) continue;
				else repaint();

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void pause() {
			this.pause = true;
		}

		public void reStart() {
			this.pause = false;
			s = new Snake(Yard.this);
			gameOver = false;
		}

		public void gameOver() {
			running = false;
		}

	}
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_F2) {
				paintThread.reStart();
			}
			s.keyPressed(e);
		}

	}

}
