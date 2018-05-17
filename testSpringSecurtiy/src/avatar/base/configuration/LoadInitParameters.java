/*
 * @(#) LoadInitParameters.java  2011-4-9 下午03:34:42
 *
 * Copyright 2011 by Sparta 
 */

package avatar.base.configuration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;



/**
 * 读取配置文件。 11/4/18 
 */
public class LoadInitParameters {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(this.getClass());

	public void initPara() {

		(new InitApplication()).reloadALL();
		
		Thread[] td = new Thread[Thread.activeCount()];
		int leng = Thread.enumerate(td);
		boolean isThread = true;
		for (int i = 0; i < leng; i++) {
			if (td[i].getName().startsWith("countReport")) {
				isThread = false;
			}
		}

	}
	
	public static void main(String[] args){
	
		new LoadInitParameters().initPara();
		
		System.out.println( AppinitWebBean.getOption("xtmk").size());
		
	}
	
	/**
	 * web服务器启动时加载配置文件。 sparta 11/4/9
	 */
	//public class LoadInitParameters extends HttpServlet {
	//
//		private static final long serialVersionUID = 1L;
	//
//		Logger logger = Logger.getLogger(this.getClass());
	//
//		public void init(ServletConfig config) throws ServletException {
//			super.init(config);
//			(new InitApplication()).reloadALL(getServletContext());
//			
//			Thread[] td = new Thread[Thread.activeCount()];
//			int leng = Thread.enumerate(td);
//			boolean isThread = true;
//			for (int i = 0; i < leng; i++) {
//				if (td[i].getName().startsWith("countReport")) {
//					isThread = false;
//				}
//			}
	//
//		}
	//
	//}

}
