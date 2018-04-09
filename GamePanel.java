package snaketest;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class GamePanel extends JPanel implements Runnable, KeyListener {// 继承键盘事件监听接口
	private int x, y;
	private int dx, dy;
	private int direction;// 保存方向值
	public static final int SOUTH = 0, NORTH = 1, EAST = 2, WEST = 3;// 向南、北、东、西运动
	private Snake sk;// 建立贪吃蛇对象
	private Food bk;// 建立食物对象
	Image im;
	Graphics g;
	ImageIcon ima=new ImageIcon("C:\\Users\\qq\\Desktop\\others\\timg.jpg");

	public GamePanel() {
		
		this.setBounds(10, 10, 690, 490);
		
		JFrame f = new JFrame("贪吃蛇");
		f.setLocation(200, 80);//设置在屏幕上显示的位置
		f.setSize(790, 550);
		f.setResizable(false);//固定大小
		f.add(this);
		f.setDefaultCloseOperation(3);
		//f.setLayout(null);
		
/*		JButton single= new JButton("简单");
		JButton normal= new JButton("一般");
		JButton excellent= new JButton("困难");
		this.add(single);
		this.add(normal);
		this.add(excellent); 本来打算加个按钮*/

		x = 50;//设定贪吃蛇的起点坐标
		y = 50;
		dx = 10;//设定每次变化的程度
		dy = 10;
		addKeyListener(this);// 注册键盘事件监听器
		f.setVisible(true);	
		// 实例化贪吃蛇的对象，并传递一个GamePanel对象的引用，划重点
		sk = new Snake(this);
		// 实例化食物对象并传递一个GamePanel对象和Snake对象的引用
		bk = new Food(this, sk);
		this.requestFocus();
	}

	public void gameUpdate() {
		sk.update();// 更新贪吃蛇坐标位置
		bk.update(this);// 更新食物坐标位置

		switch (direction) {
		case SOUTH:
			y = y + dy;
			break;
		case NORTH:
			y = y - dy;
			break;
		case EAST:
			x = x + dx;
			break;
		case WEST:
			x = x - dx;
			break;
		}
		
		
	}

	public void gameRender(Image im) {
		Graphics bg = im.getGraphics();
		bg.drawImage(ima.getImage(),0,0,this.getWidth(),this.getHeight(),null);
		sk.draw(bg);// 在后备缓冲区绘制贪吃蛇图形
		bk.draw(bg);// 在后备缓冲区绘制食物图形
	}

	public void gamePaint(Image im) {
		g = this.getGraphics();
		g.drawImage(im, 0, 0, null);// 将后备缓冲区的内容在屏幕上显示出来
	}

	boolean isPaused = false;
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			im = new BufferedImage(this.getWidth(), this.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			
			if (isPaused == false) {
				gameUpdate();
				
				if(x >= 700 || x <= 0 || y >=500 || y <= 0 ) 
				{
					isPaused = !isPaused;
					
					if(x<=0) x = x + dx;
					if(y<=0) y = y + dy;
					if(x>=700) x = x - dx;
					if(y>=500) y = y - dy;
					
					int res=JOptionPane.showConfirmDialog(null, "您的得分为  "+bk.score+"  请选择是否重新开始", "游戏结束！", JOptionPane.YES_NO_OPTION);
					              if(res==JOptionPane.YES_OPTION){ 
					          	    GamePanel g = new GamePanel();
					        		Thread thread1 = new Thread(g);
					        		thread1.start();

					            	  //点击“是”后执行这个代码块
					              }else{
					            	  System.exit(0);   //点击“否”后执行这个代码块
					             
					             } 
				}
			}
			gameRender(im);
			gamePaint(im);
		}
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();// 获取按键信息
		System.out.println("keycode=" + keycode);
		if (keycode == KeyEvent.VK_SPACE)// 若按下的是“空格”键。则切换
			isPaused = !isPaused;

		switch (keycode) {// 根据不同的按键为direction赋值
		case KeyEvent.VK_DOWN:// 如果按键盘“下”方向键
			direction = SOUTH;
			System.out.println(direction);
			break;
		case KeyEvent.VK_UP:// 如果按键盘“上”方向键
			direction = NORTH;
			System.out.println(direction);
			break;
		case KeyEvent.VK_RIGHT:// 如果按键盘“右”方向键
			direction = EAST;
			System.out.println(direction);
			break;
		case KeyEvent.VK_LEFT:// 如果按键盘“左”方向键
			direction = WEST;
			System.out.println(direction);
			break;

		}

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public int getDirection() {
		return direction;
	}

	public static void main(String[] args) {
	    GamePanel g = new GamePanel();
		Thread thread = new Thread(g);
		thread.start();

	}

}