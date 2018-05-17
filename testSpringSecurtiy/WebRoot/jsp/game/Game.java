import java.applet.*;//Applet AudioClip setLayout add getCodeBase drawImage repaint
import java.awt.*;//Image Label Graphics Color
import java.awt.event.*;//ActionListener KeyEvent ActionEvent ItemListener 
import javax.swing.*;//JButton Timer JComboBox JOptionPane

public class Game extends Applet
{

	private GameTable gTable;//创建一个游戏桌  
	private Image myImage0,myImage1,myImage2,myImage3,myImage4,myImage5,myImage6,myImage7,myImage8,myImage9;//加载九个图片，用来覆盖背景和组成方块
	private AudioClip myAudio,myAudio1,myAudio2,myAudio3,myAudio4,myAudio5,myAudio6;//加载背景音乐 AudioClip类用来在Java Applet内播放声音，该类在java.Applet包中有定义
	//使用Applet播放声音时需首先定义AudioClip对象，GetAudioClip方法能把声音赋予AudioClip对象，如果仅想把声音播放一遍，应调用AudioClip类的play方法，如果想循环把声音剪辑，应选用AudioClip类的loop方法。 
	private JButton btStart,btRestart,Start_music,Stop_music;//创建四个按钮，分别是开始、重新开始、播放音乐、暂停音乐
	private StartListener startlistener;//开始按钮事件监听器
	private int number=8;//方块种类
	private int nTime=1000;//速度，创建记时器时使用
	private int nWhich;//标志现在要下落的方块
	private int tempnWhich;//标志下一个要下落的方块
	private boolean canMove=false;//标志是否响应键盘
	private Root root;//要落下的方块的引用，即根类
	private Timer timer;//记时器，用于控制下落时间间隔
	private Label scorelabel;//显示成绩的控件
	private int nScore=0;//每消一行加10分，用来记录总分数
	private JComboBox speedcombobox,illustrate;//选择速度的控件和游戏说明
	private String[] choices={"1","2","3","4","5","6","7","8","9"};//JComboBox的选项单
	private String[] shuoming={"   X 键 ：一下到底","方向键上：方块变形","方向键下：加速下降","方向键左：向左移动","方向键右：向右移动","组员：","李函远","舒来晴","涂纯","孙旭辉","刘呈锋","徐世文"};
	
	  public void init()
      {
		btStart=new JButton("开始");
		btRestart=new JButton("重来");
		Start_music=new JButton("播放音乐");
		//不用布局管理器，以便自己设定按钮位置
		setLayout(null);
		btStart.setBounds(520,335,90,30);
		btRestart.setBounds(520,400,90,30);
		Start_music.setBounds(515,40,100,30);
		//添加按钮
		add(btStart);
		add(btRestart);
		add(Start_music);
       //为按钮添加监听器
		startlistener=new StartListener();
		btStart.addActionListener(startlistener);
		btRestart.addActionListener(new RestartListener());
		btRestart.setEnabled(false);//初始重开始按钮，禁用
		Start_music.addActionListener(new MusicListener());
		//加载图片和声音
        myImage0=getImage(getCodeBase(),"b0.jpg");
		myImage1=getImage(getCodeBase(),"b1.jpg");
		myImage2=getImage(getCodeBase(),"b2.jpg");
		myImage3=getImage(getCodeBase(),"b3.jpg");
		myImage4=getImage(getCodeBase(),"b4.jpg");
		myImage5=getImage(getCodeBase(),"b5.jpg");
		myImage6=getImage(getCodeBase(),"b6.jpg");
		myImage7=getImage(getCodeBase(),"b7.jpg");
		myImage8=getImage(getCodeBase(),"b8.jpg");
		myImage9=getImage(getCodeBase(),"b9.jpg");	
		//创建AudioClip对象并用 getAudioClip方法将其初始化。
		myAudio=getAudioClip(getCodeBase(),"backmusic.wav");
		myAudio1=getAudioClip(getCodeBase(),"fail.wav");
		myAudio2=getAudioClip(getCodeBase(),"xiaoqu.wav");
		myAudio3=getAudioClip(getCodeBase(),"down.wav");
		myAudio4=getAudioClip(getCodeBase(),"changespeed.wav");	
		myAudio5=getAudioClip(getCodeBase(),"change.wav");						
		myAudio6=getAudioClip(getCodeBase(),"ReadyGo.wav");
		//初始速度控件
		speedcombobox=new JComboBox(choices);
		speedcombobox.addItemListener(new SpeedListener());
		speedcombobox.setEditable(false);
		speedcombobox.setBounds(520,150,90,30);
		add(speedcombobox);
		//初始游戏说明控件
		illustrate=new JComboBox(shuoming);
		illustrate.setEditable(false);
		illustrate.setBounds(495,470,140,30);
		add(illustrate);
        //初始成绩控件
		scorelabel=new Label("0");
		scorelabel.setBounds(520,90,90,30);
		add(scorelabel);
		//创建一个15*20的桌子，0代表无方块
        gTable=new GameTable(15,20);
		gTable.myTable=new int[gTable.x][gTable.y];
		for(int i=0;i<gTable.x;i++)
			for(int j=0;j<gTable.y;j++)
				gTable.myTable[i][j]=0;
        //产生随机方块
		nWhich=(((int)Math.round(Math.random()*100))%number)+1;
		switch(nWhich)//初始化产生的方块
		{
			case 1:root=new One();break;
			case 2:root=new Two();break;
			case 3:root=new Three();break;
			case 4:root=new Four();break;
			case 5:root=new Five();break;
			case 6:root=new Six();break;
			case 7:root=new Seven();break;
			case 8:root=new Eight();break;
			default:break;
		}
		
		root.begin();//初始化 占据桌子
		
		tempnWhich=(((int)Math.round(Math.random()*100))%number)+1;//产生下一个提前显示的方块
		nWhich=tempnWhich;
		
		timer=new Timer(nTime,new MyRun());//创建记时器，调用MyRun
		//Timer(int delay, ActionListener listener)  类 javax.swing.Timer 的构造方法 创建一个每 delay 毫秒将通知其侦听器的 Timer。
		
		//为按钮添加键盘监听器
	    Start_music.addKeyListener(new KeyAdapter()
					{
						public void keyPressed(KeyEvent e)
						{
							if(canMove)
								judgement(e);
						}
					});		
		btStart.addKeyListener(new KeyAdapter()
					{
						public void keyPressed(KeyEvent e)
						{
							if(canMove)
								judgement(e);
						}
					});
		btRestart.addKeyListener(new KeyAdapter()
					{
						public void keyPressed(KeyEvent e)
						{
							if(canMove)
								judgement(e);
						}
					});
	   }
	   
	public void paint(Graphics g)
	{
		//画背景框架，两个有色彩的大长方形 循环是为了增加厚度                              
		g.setColor(Color.red);
		for(int i=0;i<10;i++)
		g.drawRect(20+i,20+i,640-2*i,548-2*i);
		
		g.setColor(Color.orange);
		for(int i=0;i<5;i++)
		g.drawRect(85+i,68+i,340-2*i,452-2*i);
		
		g.drawImage(myImage9,663,23,this);
		//根据桌子上的标记决定画哪种方块	
		for(int i=0;i<gTable.x;i++)
			for(int j=0;j<gTable.y;j++)
			{
			   if(gTable.myTable[i][j]==0)//画白方块
				    {
						g.drawImage(myImage0,92+i*(20+2),75+j*(20+2),this);//当转换了更多图像时要通知的对象为当前对象
					}
			   else if(gTable.myTable[i][j]==1)//画蓝方块 
					{
						g.drawImage(myImage1,92+i*(20+2),75+j*(20+2),this);
					}
			   else if(gTable.myTable[i][j]==2)
					{
						g.drawImage(myImage2,92+i*(20+2),75+j*(20+2),this);
					}
			   else if(gTable.myTable[i][j]==3)
					{
						g.drawImage(myImage3,92+i*(20+2),75+j*(20+2),this);
					}
			   else if(gTable.myTable[i][j]==4)
					{
						g.drawImage(myImage4,92+i*(20+2),75+j*(20+2),this);
					}
			   else if(gTable.myTable[i][j]==5)
					{
						g.drawImage(myImage5,92+i*(20+2),75+j*(20+2),this);
					}
			   else if(gTable.myTable[i][j]==6)
					{
						g.drawImage(myImage6,92+i*(20+2),75+j*(20+2),this);
					}
			   else if(gTable.myTable[i][j]==7)
					{
						g.drawImage(myImage7,92+i*(20+2),75+j*(20+2),this);
					}
			   else
					{
						g.drawImage(myImage8,92+i*(20+2),75+j*(20+2),this);
					}
			}	
	    //刷新
		btStart.repaint();
		btRestart.repaint();
		Start_music.repaint();
		speedcombobox.repaint();
		illustrate.repaint();
        //更新成绩
		scorelabel.setText(Integer.toString(nScore));
		scorelabel.setBackground(Color.green);
		scorelabel.repaint();
		
		g.setColor(Color.magenta);
		g.drawString("目前得分:",450,110);
		g.drawString("速度1--9:",450,170);
		g.drawString("下一方块是:",450,230);
		nextTo(g);	
    }
    
	public void update(Graphics g)
	{
		paint(g);//防止闪烁
	}
	
	//画下一个提前显示的方块
	public void nextTo(Graphics g)
	{	
		for(int i=0;i<3;i++)//先全部用白方块覆盖
			for(int j=0;j<4;j++)
				g.drawImage(myImage0,533+i*(20+2),215+j*(20+2),this);
				
		switch(tempnWhich)//根据标志决定画哪个方块
		{	
			case 1:g.drawImage(myImage1,555,215,this);//长条
			       g.drawImage(myImage1,555,237,this);
			       g.drawImage(myImage1,555,259,this);
			       g.drawImage(myImage1,555,281,this);
			       break;
			case 2:g.drawImage(myImage2,533,237,this);//方块
			       g.drawImage(myImage2,533,259,this);
			       g.drawImage(myImage2,555,237,this);
			       g.drawImage(myImage2,555,259,this);
			       break;
			case 3:g.drawImage(myImage3,555,237,this);//右z
			       g.drawImage(myImage3,533,259,this);
			       g.drawImage(myImage3,555,215,this);
			       g.drawImage(myImage3,533,237,this);
			       break;
			case 4:g.drawImage(myImage4,533,215,this);//左z
			       g.drawImage(myImage4,533,237,this);
			       g.drawImage(myImage4,555,237,this);
			       g.drawImage(myImage4,555,259,this);
			       break;
			case 5:g.drawImage(myImage5,577,215,this);//右折
			       g.drawImage(myImage5,555,237,this);
			       g.drawImage(myImage5,555,259,this);
			       g.drawImage(myImage5,555,215,this);
			       break;
			case 6:g.drawImage(myImage6,533,215,this);//左折
			       g.drawImage(myImage6,555,215,this);
			       g.drawImage(myImage6,555,237,this);
			       g.drawImage(myImage6,555,259,this);
			       break;
			case 7:g.drawImage(myImage7,555,237,this);//土
			       g.drawImage(myImage7,533,259,this);
			       g.drawImage(myImage7,555,259,this);
			       g.drawImage(myImage7,577,259,this);
			       break;
			case 8:g.drawImage(myImage8,533,237,this);//单一方块
			       break;
			default:break;
		}
	}

	public void judgement(KeyEvent e)//判断输入的是哪个键，并做出响应
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_X://如果是X键，就一下到底 
			{
				root.downTo();
                myAudio3.play();//使用AudioClip类的play方法 播放音乐一次		
			}
				lineOver();//消去添满的一行，并加10分
				switch(nWhich)//产生新的方块
				{
					case 1:root=new One();break;
					case 2:root=new Two();break;
					case 3:root=new Three();break;
					case 4:root=new Four();break;
					case 5:root=new Five();break;
					case 6:root=new Six();break;
					case 7:root=new Seven();break;
					case 8:root=new Eight();break;
					default:break;
				}
				tempnWhich=(((int)Math.round(Math.random()*100))%number)+1;
				nWhich=tempnWhich;
				
				if(!root.begin())//如果新的方块产生失败，则游戏结束
				{	
					repaint();
					gameover();
				}
				else
				{	
					repaint();
				}
				break;
			case KeyEvent.VK_DOWN:root.down();repaint();break;
			case KeyEvent.VK_LEFT:root.left();repaint();break;
			case KeyEvent.VK_RIGHT:root.right();repaint();break;
			case KeyEvent.VK_UP:
			{
				root.change();
				myAudio5.play();
				repaint();break;
			}
			default:break;
		}
	}

	public void lineOver()//消去添满的一行，并加10分
	{
		boolean isCan;//标志是否循环消一行
		boolean isContinue=true;//标志是否还有待消的行
		int k=gTable.y-1;
		while(isContinue)
		{
			isCan=true;
			while(isCan)
			{
				for(int i=0;i<gTable.x;i++)
				{
					if(gTable.myTable[i][k]==0)
						isCan=false;
				}
				
				if(isCan)//满足条件，开始消行
				{
					for(int i=0;i<gTable.x;i++)
					gTable.myTable[i][k]=0;
					myAudio2.play();					
						
					for(int j=k-1;j>=0;j--)
						for(int i=0;i<gTable.x;i++)
						{
							if(gTable.myTable[i][j]!=0)
							    {
									gTable.myTable[i][j+1]=1;
								    gTable.myTable[i][j]=0;
								}
						}
					repaint();
					nScore+=10;//加成绩
				}
			}
			k--;
			if(k<1)
				isContinue=false;
		}
	}

	public void gameover()//游戏结束
	{
		myAudio1.play();
		Start_music.setLabel("播放音乐");
		timer.stop();//计时器暂停
		JOptionPane anOptionPane=new JOptionPane();//弹出对话框
		anOptionPane.showMessageDialog(this,"           很遗憾 游戏结束!","sorry",JOptionPane.WARNING_MESSAGE);
		over();//处理各个变量，以便重新开始
	}
	
	public void over()//游戏结束后，处理各个变量，以便重新开始
	{
		for(int i=0;i<gTable.x;i++)//重新给游戏桌置0标志
			for(int j=0;j<gTable.y;j++)
			{
				gTable.myTable[i][j]=0;
			}
		timer.stop();
		repaint();
		nScore=0;
		switch(nWhich)//产生新的方块
		{
			case 1:root=new One();break;
			case 2:root=new Two();break;
			case 3:root=new Three();break;
			case 4:root=new Four();break;
			case 5:root=new Five();break;
			case 6:root=new Six();break;
			case 7:root=new Seven();break;
			case 8:root=new Eight();break;
			default:break;
		}
		tempnWhich=(((int)Math.round(Math.random()*100))%number)+1;
		nWhich=tempnWhich;
		root.begin();
		repaint();
		btStart.setLabel("开始");
		btStart.setEnabled(true);
		speedcombobox.setEnabled(true);
		btRestart.setEnabled(false);
		startlistener.setisRun(true);	
		canMove=false;
	}
	
	public class MyRun implements ActionListener//记时器调用的监听器
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!root.down())//将方块下落一格，如果不能再下降了，就调用下面语句
			{
				lineOver();//消行
				switch(nWhich)//产生新的方块
				{
					case 1:root=new One();break;
					case 2:root=new Two();break;
					case 3:root=new Three();break;
					case 4:root=new Four();break;
					case 5:root=new Five();break;
					case 6:root=new Six();break;
					case 7:root=new Seven();break;
					case 8:root=new Eight();break;
					default:break;
				}
				tempnWhich=(((int)Math.round(Math.random()*100))%number)+1;
				nWhich=tempnWhich;
				if(!root.begin())//如果失败，游戏结束
				{	
					repaint();
					gameover();
				}
				else	
					repaint();
			}
			else
			{
				repaint();
			}
		}
	}

	public class StartListener implements ActionListener//开始按钮监听器
	{
		private boolean isRun=true;//标志是开始还是暂停，一钮两用

		public void setisRun(boolean run)
		{
			isRun=run;
		}	

		public void actionPerformed(ActionEvent e)
		{
			btRestart.setEnabled(true);
			if(isRun)//是开始
			{
				myAudio6.play();
				speedcombobox.setEnabled(false);
				btStart.setLabel("停止");
				timer.start();
				isRun=!isRun;
				canMove=true;//可以响应键盘
			}
			else//是暂停
			{
				speedcombobox.setEnabled(true);
				btStart.setLabel("开始");
				timer.stop();
				isRun=!isRun;
				canMove=false;//不可以响应键盘
			}
		}
	}

	public class MusicListener implements ActionListener//音乐按钮监听器
	{
		private boolean music=true;//标志是开始还是暂停，一钮两用
		public void actionPerformed(ActionEvent e)
		{
			
			if(music)//是开始
			{

		        myAudio.loop();//循环播放背景音乐      
				Start_music.setLabel("暂停音乐");
				music=!music;
			}
			else//是暂停
			{
				myAudio.stop();//停止播放
				Start_music.setLabel("播放音乐");
				music=!music;
			}
		}
	}

	public class RestartListener implements ActionListener//重新开始按钮监听器
	{
		public void actionPerformed(ActionEvent e)
		{
			over();//重新初始化各个变量
		}
	}

	public class SpeedListener implements ItemListener//速度控件监听器
	{
		public void itemStateChanged(ItemEvent e)
		{
				nTime=1000-(new Integer(speedcombobox.getSelectedItem().toString()).intValue())*110;
			    timer=new Timer(nTime,new MyRun());//根据选择的nTime间隔重新设置记时器，以变换速度	
			    myAudio4.play();
		}
	}
}//主体结束