public class Eight extends Root //µ¥Ò»·½¿é
{
	private GameTable gTable;
	private int x,y;
	private int direct=1;
	private int[] store;

	public Eight()
	{
		store=new int[15];
	}
	
	public boolean begin()
	{
		if(gTable.myTable[(gTable.x-1)/2][0]==0)
		{
			x=(gTable.x-1)/2;
			y=0;
			gTable.myTable[x][y]=8;
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean down()
	{
		if(y<=gTable.y-2)
		{
			store[0]=gTable.myTable[x][y+1];
			if(isGo(1))
			{
				gTable.myTable[x][y]=0;
				gTable.myTable[x][y+1]=8;
				y+=1;
				return true;
			 }
			 else
			 {
				return false;
			 }
		}
		else
		{
			return false;
		}
	}

	public boolean left()
	{
		if(x>=1)
		{
		 	store[0]=gTable.myTable[x-1][y];
			if(isGo(1))
			{
				gTable.myTable[x][y]=0;
				gTable.myTable[x-1][y]=8;
				x-=1;
				return true;
			 }
			 else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public boolean right()
	{
		if(x<=gTable.x-2)
		{
		 	store[0]=gTable.myTable[x+1][y];
			if(isGo(1))
			{
				gTable.myTable[x][y]=0;
				gTable.myTable[x+1][y]=8;
				x+=1;
				return true;
			 }
			 else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public boolean change()
	{
		return true;
	}

	public void downTo()
	{
		boolean canDown=true;
		while(canDown)
		{
			canDown=down();
		}
	}

	public boolean isGo(int n)
	{
		for(int i=0;i<=(n-1);i++)
		{
			if(store[i]!=0)
				return false;
		}
		return true;
	}
}