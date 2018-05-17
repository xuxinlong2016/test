public class Root//8个块的根类,具有一般性
{
	public boolean begin()//初始化显示
	{
		return true;
	}

	public boolean down()//向下移动
	{
		return true;
	}

	public boolean left()//向左移动
	{
		return true;
	}

	public boolean right()//向右移动
	{
		return true;
	}

	public boolean change()//变换角度
	{
		return true;
	}

	public void downTo()//一下到底
	{
	}
	
	public boolean isGo(int n)//以上函数调用的函数,用于判断是否可以移动
	{	
		return true;
	}

	public static void main(String args[])
	{
		new Root();
	}
}