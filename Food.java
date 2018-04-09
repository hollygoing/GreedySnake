package snaketest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public class Food {
	
	private GamePanel gameP;
	private Snake snk;
	public Point location;//食物的坐标
	public Point size;//食物方块的尺寸
	private Random rand;//随机类对象
	public int score = 0 ;
	
	public int x,y;//食物的坐标点
	

	public Food(GamePanel gp,Snake sk){
		gameP=gp;//通过构造方法的参数来获取对GamePanel对象的引用
		snk=sk;//通过构造方法的参数来获取对Snake对象的引用
		rand=new Random();
		//食物随机的出现在屏幕上某个位置
		
		x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 650 + 20;//用于设定食物的投放范围
		y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 450 + 20;
		
		System.out.println("x = "+x+" y = "+y);
		
		location=new Point(x,y);
		size=new Point(sk.r,sk.r);//食物尺寸与贪吃蛇小球大小相同
	}
	
	public void draw(Graphics g){//绘制食物图形
		g.setColor(Color.PINK);//设置食物颜色
		g.fillRect(location.x, location.y, size.x, size.y);//绘制食物
	}
	
	public void update(GamePanel gp){//更新游戏逻辑（食物坐标）
		//碰撞检测（中心检测法），判断贪吃蛇是否吃到了食物
		if((snk.x-location.x)*(snk.x-location.x)+(snk.y-location.y)*(snk.y-location.y)<(snk.r*snk.r)){
			//若贪吃蛇的蛇头与食物发生碰撞，则随机生成新的食物位置
			
			x = (Math.abs(rand.nextInt(gp.getWidth())%gameP.getWidth())) % 650 + 20;
			y = (Math.abs(rand.nextInt(gp.getHeight())%gameP.getHeight())) % 450 + 20;
			
			location=new Point(x,y);
			if(snk.length<Snake.MAXLENTH){
				snk.length++;//若蛇身长度未达到最大值，则蛇身伸长一个单位
				score ++;
			}	
		}
	}
}