public class Two extends Root //·½¿é
{
	private GameTable gTable;
	private int x,y;
	private int direct=1;
	private int[] store;




	public Two()
	{
		store=new int[15];
	}

	public boolean begin()
	{
		if((gTable.myTable[(gTable.x-1)/2][0]==0)&&(gTable.myTable[(gTable.x-1)/2+1][0]==0)&&
			(gTable.myTable[(gTable.x-1)/2][1]==0)&&(gTable.myTable[(gTable.x-1)/2+1][1]==0))
		{
			x=(gTable.x-1)/2;
			y=0;
			gTable.myTable[x][y]=2;
			gTable.myTable[x][y+1]=2;
			gTable.myTable[x+1][y]=2;
			gTable.myTable[x+1][y+1]=2;
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean down()
	{
		switch(direct)
		{
                	case 1:if(y<=gTable.y-3)
			       {
			       		store[0]=gTable.myTable[x][y+2];
					store[1]=gTable.myTable[x+1][y+2];
			       		if(isGo(2))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x][y+2]=2;
						gTable.myTable[x+1][y+2]=2;
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
			case 2:
			default:return false;
		}
	}

	public boolean left()
	{
		switch(direct)
		{
			case 1:if(x>=1)
				{
			      	 	store[0]=gTable.myTable[x-1][y];
			       		store[1]=gTable.myTable[x-1][y+1];
			       		if(isGo(2))
			       		{
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x-1][y]=2;
						gTable.myTable[x-1][y+1]=2;
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
			case 2:
			default:return false;
		}
	}

	public boolean right()
	{
		switch(direct)
		{
			case 1:if(x<=gTable.x-3)
				{
			      	 	store[0]=gTable.myTable[x+2][y];
			       		store[1]=gTable.myTable[x+2][y+1];
			       		if(isGo(2))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x+2][y]=2;
						gTable.myTable[x+2][y+1]=2;
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
			case 2:
			default:return false;
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