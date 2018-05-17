public class Seven extends Root  //ма
{
	private GameTable gTable;
	private int x,y;
	private int direct=1;
	private int[] store;




	public Seven()
	{
		store=new int[15];
	}
	
	public boolean begin()
	{
		if((gTable.myTable[(gTable.x-1)/2][1]==0)&&(gTable.myTable[(gTable.x-1)/2+1][0]==0)&&
			(gTable.myTable[(gTable.x-1)/2+1][1]==0)&&(gTable.myTable[(gTable.x-1)/2+2][1]==0))
		{
			x=(gTable.x-1)/2;
			y=0;
			gTable.myTable[x][y+1]=7;
			gTable.myTable[x+1][y]=7;
			gTable.myTable[x+1][y+1]=7;
			gTable.myTable[x+2][y+1]=7;
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
					store[2]=gTable.myTable[x+2][y+2];
			       		if(isGo(3))
			       		{
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x+2][y+1]=0;
						gTable.myTable[x][y+2]=7;
						gTable.myTable[x+1][y+2]=7;
						gTable.myTable[x+2][y+2]=7;
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
			case 2:if(y<=gTable.y-4)
				{
			       		store[0]=gTable.myTable[x][y+3];
			       		store[1]=gTable.myTable[x+1][y+2];
			       		if(isGo(2))
			       		{
						gTable.myTable[x][y]=0;
				        	gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x][y+3]=7;
						gTable.myTable[x+1][y+2]=7;
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
			case 3:if(y<=gTable.y-3)
			       {
			       		store[0]=gTable.myTable[x][y+1];
					store[1]=gTable.myTable[x+1][y+2];
					store[2]=gTable.myTable[x+2][y+1];
			       		if(isGo(3))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x+2][y]=0;
						gTable.myTable[x][y+1]=7;
						gTable.myTable[x+1][y+2]=7;
						gTable.myTable[x+2][y+1]=7;
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
			case 4:if(y<=gTable.y-4)
				{
			       		store[0]=gTable.myTable[x][y+2];
			       		store[1]=gTable.myTable[x+1][y+3];
			       		if(isGo(2))
			       		{
						gTable.myTable[x+1][y]=0;
				        	gTable.myTable[x][y+1]=0;
						gTable.myTable[x][y+2]=7;
						gTable.myTable[x+1][y+3]=7;
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
			default:return false;
		}
	}

	public boolean left()
	{
		switch(direct)
		{
			case 1:if(x>=1)
				{
			      	 	store[0]=gTable.myTable[x][y];
			       		store[1]=gTable.myTable[x-1][y+1];
			       		if(isGo(2))
			       		{
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x+2][y+1]=0;
						gTable.myTable[x][y]=7;
						gTable.myTable[x-1][y+1]=7;
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
			case 2:if(x>=1)
				{
			       		store[0]=gTable.myTable[x-1][y];
					store[1]=gTable.myTable[x-1][y+1];
					store[2]=gTable.myTable[x-1][y+2];
			       		if(isGo(3))
			      		 {
						gTable.myTable[x][y]=0;
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x][y+2]=0;
						gTable.myTable[x-1][y]=7;
						gTable.myTable[x-1][y+1]=7;
						gTable.myTable[x-1][y+2]=7;
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
			case 3:if(x>=1)
				{
			      	 	store[0]=gTable.myTable[x-1][y];
			       		store[1]=gTable.myTable[x][y+1];
			       		if(isGo(2))
			       		{
						gTable.myTable[x+2][y]=0;
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x-1][y]=7;
						gTable.myTable[x][y+1]=7;
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
			case 4:if(x>=1)
				{
			       		store[0]=gTable.myTable[x][y];
					store[1]=gTable.myTable[x-1][y+1];
					store[2]=gTable.myTable[x][y+2];
			       		if(isGo(3))
			      		 {
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x+1][y+2]=0;
						gTable.myTable[x][y]=7;
						gTable.myTable[x-1][y+1]=7;
						gTable.myTable[x][y+2]=7;
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
			default:return false;
		}
	}

	public boolean right()
	{
		switch(direct)
		{
			case 1:if(x<=gTable.x-4)
				{
			      	 	store[0]=gTable.myTable[x+2][y];
			       		store[1]=gTable.myTable[x+3][y+1];
			       		if(isGo(2))
			       		{
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x+2][y]=7;
						gTable.myTable[x+3][y+1]=7;
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
			case 2:if(x<=gTable.x-3)
				{
			       		store[0]=gTable.myTable[x+1][y];
					store[1]=gTable.myTable[x+2][y+1];
					store[2]=gTable.myTable[x+1][y+2];
			       		if(isGo(3))
			      		 {
						gTable.myTable[x][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x][y+2]=0;
						gTable.myTable[x+1][y]=7;
						gTable.myTable[x+2][y+1]=7;
						gTable.myTable[x+1][y+2]=7;
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
			case 3:if(x<=gTable.x-4)
				{
			      	 	store[0]=gTable.myTable[x+3][y];
			       		store[1]=gTable.myTable[x+2][y+1];
			       		if(isGo(2))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x+3][y]=7;
						gTable.myTable[x+2][y+1]=7;
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
			case 4:if(x<=gTable.x-3)
				{
			       		store[0]=gTable.myTable[x+2][y];
					store[1]=gTable.myTable[x+2][y+1];
					store[2]=gTable.myTable[x+2][y+2];
			       		if(isGo(3))
			      		 {
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x+1][y+2]=0;
						gTable.myTable[x+2][y]=7;
						gTable.myTable[x+2][y+1]=7;
						gTable.myTable[x+2][y+2]=7;
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
			default:return false;
		}
	}

	public boolean change()
	{
		switch(direct)
		{
			case 1:if(true)
				{
			      	 	store[0]=gTable.myTable[x][y];
			       		store[1]=gTable.myTable[x][y-1];
			       		store[2]=gTable.myTable[x+1][y-1];
			       		if(isGo(3))
			       		{
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x+2][y+1]=0;
						gTable.myTable[x][y]=7;
						gTable.myTable[x][y-1]=7;
						y-=1;
						direct=2;
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
			case 2:if(x<=gTable.x-3)
				{
			       		store[0]=gTable.myTable[x+1][y];
			       		store[1]=gTable.myTable[x+2][y];
			       		store[2]=gTable.myTable[x+2][y+1];
			       		store[3]=gTable.myTable[x+1][y+2];
			       		if(isGo(4))
			      		 {
						gTable.myTable[x][y]=0;
						gTable.myTable[x][y+2]=0;
						gTable.myTable[x+2][y+1]=7;
						gTable.myTable[x+1][y+2]=7;
						y+=1;
						direct=3;
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
			case 3:if(true)
				{
			      	 	store[0]=gTable.myTable[x][y-1];
			       		store[1]=gTable.myTable[x+1][y-1];
			       		store[2]=gTable.myTable[x+2][y-1];
					store[3]=gTable.myTable[x+2][y+1];
			       		if(isGo(4))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x+1][y+1]=0;
						gTable.myTable[x+2][y-1]=7;
						gTable.myTable[x+2][y+1]=7;
						x+=1;
						y-=1;
						direct=4;
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
			case 4:if(x<=gTable.x-3)
				{
			       		store[0]=gTable.myTable[x+2][y];
			       		store[1]=gTable.myTable[x+2][y+1];
			       		store[2]=gTable.myTable[x+2][y+2];
			       		store[3]=gTable.myTable[x][y+2];
			       		if(isGo(4))
			      		 {
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x][y+2]=7;
						gTable.myTable[x+2][y+2]=7;
						y+=1;
						direct=1;
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
			default:return false;
		}
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