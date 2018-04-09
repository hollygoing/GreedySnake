package snaketest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public class Food {
	
	private GamePanel gameP;
	private Snake snk;
	public Point location;//ʳ�������
	public Point size;//ʳ�﷽��ĳߴ�
	private Random rand;//��������
	public int score = 0 ;
	
	public int x,y;//ʳ��������
	

	public Food(GamePanel gp,Snake sk){
		gameP=gp;//ͨ�����췽���Ĳ�������ȡ��GamePanel���������
		snk=sk;//ͨ�����췽���Ĳ�������ȡ��Snake���������
		rand=new Random();
		//ʳ������ĳ�������Ļ��ĳ��λ��
		
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 650 + 20;//�����趨ʳ���Ͷ�ŷ�Χ
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 450 + 20;
		
		System.out.println("x = "+x+" y = "+y);
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//ʳ��ߴ���̰����С���С��ͬ
	}
	
	public void draw(Graphics g){//����ʳ��ͼ��
		g.setColor(Color.PINK);//����ʳ����ɫ
		g.fillRect(location.x, location.y, size.x, size.y);//����ʳ��
	}
	
	public void update(GamePanel gp){//������Ϸ�߼���ʳ�����꣩
		//��ײ��⣨���ļ�ⷨ�����ж�̰�����Ƿ�Ե���ʳ��
		if((snk.x-location.x)*(snk.x-location.x)+(snk.y-location.y)*(snk.y-location.y)<(snk.r*snk.r)){
			//��̰���ߵ���ͷ��ʳ�﷢����ײ������������µ�ʳ��λ��
			
			x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 650 + 20;
			y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 450 + 20;
			
			location=new Point(x,y);
			if(snk.length<Snake.MAXLENTH){
				snk.length++;//��������δ�ﵽ���ֵ���������쳤һ����λ
				score ++;
			}	
		}
	}
}