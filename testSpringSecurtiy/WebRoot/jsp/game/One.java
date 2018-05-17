public class One extends Root//第一个方块 长条
{
	private GameTable gTable;//创建游戏桌子
	private int x,y;//方块上角现在在桌子上的位置
	private int direct=1;//第一个角度
	private int[] store;//储存待判断方块是否可以移动的坐标标志

	public One()
	{
		store=new int[15];//15足够大了
	}
	
	public boolean begin()//初始化显示
	{
		//判断用于初始化的坐标是否被占用
		if((gTable.myTable[(gTable.x-1)/2][0]==0)&&(gTable.myTable[(gTable.x-1)/2][1]==0)&&
			(gTable.myTable[(gTable.x-1)/2][2]==0)&&(gTable.myTable[(gTable.x-1)/2][3]==0))
		{
			x=(gTable.x-1)/2;//记录左上角坐标
			y=0;
			gTable.myTable[x][y]=1;//设置占用标志
			gTable.myTable[x][y+1]=1;
			gTable.myTable[x][y+2]=1;
			gTable.myTable[x][y+3]=1;
			return true;
		}
		else
		{
			return false;//初始化失败
		}
	}
	
	public boolean down()//向下移动
	{
		switch(direct)//根据角度标志，其他方块有的有2个角度，有的有4个角度
		{
            case 1:if(y<=gTable.y-5)//是否在边缘
			       {
			       		store[0]=gTable.myTable[x][y+4];//储存待判断坐标
			       		if(isGo(1))//判断是否可移
			       		{
						gTable.myTable[x][y]=0;//将被移动坐标置0
						gTable.myTable[x][y+4]=1;//将目标坐标置1
						y+=1;//坐上角坐标移动
						return true;
			      		}
			      		 else
			      		 {
						return false;//移动失败
			     		 }
				}
				else
				{
					return false;//移动失败
				}	
			case 2:if(y<=gTable.y-2)//同case 1
				{
			       		store[0]=gTable.myTable[x][y+1];
			       		store[1]=gTable.myTable[x+1][y+1];
                        store[2]=gTable.myTable[x+2][y+1];
			       		store[3]=gTable.myTable[x+3][y+1];
			       		if(isGo(4))
			       		{
						gTable.myTable[x][y]=0;
				        gTable.myTable[x+1][y]=0;
						gTable.myTable[x+2][y]=0;
						gTable.myTable[x+3][y]=0;
						gTable.myTable[x][y+1]=1;
						gTable.myTable[x+1][y+1]=1;
						gTable.myTable[x+2][y+1]=1;
						gTable.myTable[x+3][y+1]=1;
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

	public boolean left()//同down()
	{
		switch(direct)
		{
			case 1:if(x>=1)
				{
			      	 	store[0]=gTable.myTable[x-1][y];
			       		store[1]=gTable.myTable[x-1][y+1];
			       		store[2]=gTable.myTable[x-1][y+2];
			       		store[3]=gTable.myTable[x-1][y+3];
			       		if(isGo(4))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x][y+2]=0;
						gTable.myTable[x][y+3]=0;
						gTable.myTable[x-1][y]=1;
						gTable.myTable[x-1][y+1]=1;
						gTable.myTable[x-1][y+2]=1;
						gTable.myTable[x-1][y+3]=1;
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
			       		if(isGo(1))
			      		 {
						gTable.myTable[x+3][y]=0;
						gTable.myTable[x-1][y]=1;
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

	public boolean right()//同down()
	{
		switch(direct)
		{
			case 1:if(x<=gTable.x-2)
				{
			      	 	store[0]=gTable.myTable[x+1][y];
			       		store[1]=gTable.myTable[x+1][y+1];
			       		store[2]=gTable.myTable[x+1][y+2];
			       		store[3]=gTable.myTable[x+1][y+3];
			       		if(isGo(4))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x][y+2]=0;
						gTable.myTable[x][y+3]=0;
						gTable.myTable[x+1][y]=1;
						gTable.myTable[x+1][y+1]=1;
						gTable.myTable[x+1][y+2]=1;
						gTable.myTable[x+1][y+3]=1;
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
			case 2:if(x<=gTable.x-5)
				{
			       		store[0]=gTable.myTable[x+4][y];
			       		if(isGo(1))
			      		 {
						gTable.myTable[x][y]=0;
						gTable.myTable[x+4][y]=1;
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

	public boolean change()//同down()
	{
		switch(direct)
		{
			case 1:if(x<=gTable.x-4)
				{
			      	store[0]=gTable.myTable[x+1][y];
			       	store[1]=gTable.myTable[x+1][y+1];
			       	store[2]=gTable.myTable[x+1][y+2];
			       	store[3]=gTable.myTable[x+1][y+3];
					store[4]=gTable.myTable[x+2][y];
					store[5]=gTable.myTable[x+2][y+1];
					store[6]=gTable.myTable[x+2][y+2];
					store[7]=gTable.myTable[x+2][y+3];
					store[8]=gTable.myTable[x+3][y];
					store[9]=gTable.myTable[x+3][y+1];
					store[10]=gTable.myTable[x+3][y+2];
					store[11]=gTable.myTable[x+3][y+3];
			       		if(isGo(12))
			       		{
						gTable.myTable[x][y]=0;
						gTable.myTable[x][y+1]=0;
						gTable.myTable[x][y+2]=0;
						gTable.myTable[x+1][y+3]=1;
						gTable.myTable[x+2][y+3]=1;
						gTable.myTable[x+3][y+3]=1;
						y+=3;
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
			case 2:if(true)
				{
			       	store[0]=gTable.myTable[x][y-1];
			       	store[1]=gTable.myTable[x][y-2];
			       	store[2]=gTable.myTable[x][y-3];
			       	store[3]=gTable.myTable[x+1][y-1];
					store[4]=gTable.myTable[x+1][y-2];
					store[5]=gTable.myTable[x+1][y-3];
					store[6]=gTable.myTable[x+2][y-1];
					store[7]=gTable.myTable[x+2][y-2];
					store[8]=gTable.myTable[x+2][y-3];
					store[9]=gTable.myTable[x+3][y-1];
					store[10]=gTable.myTable[x+3][y-2];
					store[11]=gTable.myTable[x+3][y-3];
			       		if(isGo(12))
			      		 {
						gTable.myTable[x+1][y]=0;
						gTable.myTable[x+2][y]=0;
						gTable.myTable[x+3][y]=0;
						gTable.myTable[x][y-3]=1;
						gTable.myTable[x][y-2]=1;
						gTable.myTable[x][y-1]=1;
						y-=3;
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

	public void downTo()//一下到底
	{
		boolean canDown=true;
		while(canDown)
		{
			canDown=down();//循环向下移动一格
		}
	}

	public boolean isGo(int n)
	{
		for(int i=0;i<=(n-1);i++)
		{
			if(store[i]!=0)//如果有占位则失败
				return false;
		}
		return true;
	}
}






























































