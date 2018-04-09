package snaketest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;


public class Snake {

	GamePanel gameP;
	private Point[] body;//���������飬���������С������
	public static final int MAXLENTH=50;//������󳤶�
	private int head;//ָʾ��ͷλ��
	private int tail;//ָʾ��βλ��
	public int length;//������
	private int speed;//�����ٶ�
	public int x,y;//��ͷС��ĺ�������
	public int r;//����С��İ뾶
	
	static final int GameLocX = 10 , GameLocY = 10 , GameWidth = 690 , GameHeight = 490;
	
	public Snake(GamePanel gp){
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		body=new Point[MAXLENTH];
		head=-1;
		tail=-1;
		length=1;
		speed=10;
		x=50;
		y=50;
		r=10;
	}
	
	public void draw(Graphics g){//����̰���ߵ�ͼ��
		
	    //super.paint(g);   //û�лὫbuttonˢ��
	    g.setColor(Color.white);
	    g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //ˢ�½���
	    g.setColor(Color.black);
	    g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //���Ʊ߽�
		
//		System.out.println("head="+head+"  "+"tail="+tail);
		g.setColor(Color.BLUE);//��������Ϊ��ɫ
		
		if(length>1){
			int i=tail;
			while(i!=head){//ѭ�������������С��
				g.fillOval(body[i].x, body[i].y, r, r);
				i=(i+1)%body.length;
				
			}
		}
		g.setColor(Color.RED);//������ͷΪ��ɫ
		g.fillOval(body[head].x, body[head].y, r, r);
		if(length==1){
			length++;
		}
/*		if(tail>=1)
		{
		g.setColor(Color.WHITE);
		g.fillOval(body[tail-1].x, body[tail-1].y, r, r);
		}*/
	}
	
	public void update(){//������Ϸ�߼���̰���ߵ����꣩
		int direction=gameP.getDirection();
		switch(direction){
			case GamePanel.SOUTH:
				y+=speed;
				break;
			case GamePanel.NORTH:
				y-=speed;
				break;
			case GamePanel.EAST:
				x+=speed;
				break;
			case GamePanel.WEST:
				x-=speed;
				break;
		}
		head=(head+1)%body.length;//������ͷָ��λ��
		tail=(head+body.length-length+1)%body.length;//������βָ������
		body[head]=new Point(x,y);//������ͷС������ֵ
		
		if(length>2){ //�Լ�ײ�Լ��ļ��
			int i=tail;
			System.out.println("i = "+i);
			while(i!=head && i!=head-1){//ѭ�������������С��
				if((body[i].x-body[head].x)*(body[i].x-body[head].x)+(body[i].y-body[head].y)*(body[i].y-body[head].y)<(10*10))
				{
					int res=JOptionPane.showConfirmDialog(null, "����ô�������� �����ˣ�", "��Ϸ������", JOptionPane.YES_NO_OPTION);
		              if(res==JOptionPane.YES_OPTION){ 
		            	  System.exit(0);
		            	  //������ǡ���ִ����������
		              }else{
		            	  System.exit(0);   //������񡱺�ִ����������
		             
		             } 
				}
				i=(i+1)%body.length;
			}
		}

	}
	
}