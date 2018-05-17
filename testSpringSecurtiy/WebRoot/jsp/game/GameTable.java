public class GameTable//游戏桌
{
	public static int[][] myTable;//储存标志
	public static int x;//横坐标
	public static int y;//纵坐标

	public GameTable()
	{
	}

	public GameTable(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public static void main(String args[])
	{
		new GameTable();
	}
}