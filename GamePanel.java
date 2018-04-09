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

public class GamePanel extends JPanel implements Runnable, KeyListener {// �̳м����¼������ӿ�
	private int x, y;
	private int dx, dy;
	private int direction;// ���淽��ֵ
	public static final int SOUTH = 0, NORTH = 1, EAST = 2, WEST = 3;// ���ϡ������������˶�
	private Snake sk;// ����̰���߶���
	private Food bk;// ����ʳ�����
	Image im;
	Graphics g;
	ImageIcon ima=new ImageIcon("C:\\Users\\qq\\Desktop\\others\\timg.jpg");

	public GamePanel() {
		
		this.setBounds(10, 10, 690, 490);
		
		JFrame f = new JFrame("̰����");
		f.setLocation(200, 80);//��������Ļ����ʾ��λ��
		f.setSize(790, 550);
		f.setResizable(false);//�̶���С
		f.add(this);
		f.setDefaultCloseOperation(3);
		//f.setLayout(null);
		
/*		JButton single= new JButton("��");
		JButton normal= new JButton("һ��");
		JButton excellent= new JButton("����");
		this.add(single);
		this.add(normal);
		this.add(excellent); ��������Ӹ���ť*/

		x = 50;//�趨̰���ߵ��������
		y = 50;
		dx = 10;//�趨ÿ�α仯�ĳ̶�
		dy = 10;
		addKeyListener(this);// ע������¼�������
		f.setVisible(true);	
		// ʵ����̰���ߵĶ��󣬲�����һ��GamePanel��������ã����ص�
		sk = new Snake(this);
		// ʵ����ʳ����󲢴���һ��GamePanel�����Snake���������
		bk = new Food(this, sk);
		this.requestFocus();
	}

	public void gameUpdate() {
		sk.update();// ����̰��������λ��
		bk.update(this);// ����ʳ������λ��

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
		sk.draw(bg);// �ں󱸻���������̰����ͼ��
		bk.draw(bg);// �ں󱸻���������ʳ��ͼ��
	}

	public void gamePaint(Image im) {
		g = this.getGraphics();
		g.drawImage(im, 0, 0, null);// ���󱸻���������������Ļ����ʾ����
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
					
					int res=JOptionPane.showConfirmDialog(null, "���ĵ÷�Ϊ  "+bk.score+"  ��ѡ���Ƿ����¿�ʼ", "��Ϸ������", JOptionPane.YES_NO_OPTION);
					              if(res==JOptionPane.YES_OPTION){ 
					          	    GamePanel g = new GamePanel();
					        		Thread thread1 = new Thread(g);
					        		thread1.start();

					            	  //������ǡ���ִ����������
					              }else{
					            	  System.exit(0);   //������񡱺�ִ����������
					             
					             } 
				}
			}
			gameRender(im);
			gamePaint(im);
		}
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();// ��ȡ������Ϣ
		System.out.println("keycode=" + keycode);
		if (keycode == KeyEvent.VK_SPACE)// �����µ��ǡ��ո񡱼������л�
			isPaused = !isPaused;

		switch (keycode) {// ���ݲ�ͬ�İ���Ϊdirection��ֵ
		case KeyEvent.VK_DOWN:// ��������̡��¡������
			direction = SOUTH;
			System.out.println(direction);
			break;
		case KeyEvent.VK_UP:// ��������̡��ϡ������
			direction = NORTH;
			System.out.println(direction);
			break;
		case KeyEvent.VK_RIGHT:// ��������̡��ҡ������
			direction = EAST;
			System.out.println(direction);
			break;
		case KeyEvent.VK_LEFT:// ��������̡��󡱷����
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