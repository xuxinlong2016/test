// 功能: 把数据从xml中 提取出来,存放到指定位置

package avatar.base.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;   
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class InitApplication {
	
	Logger logger = Logger.getLogger( this.getClass() );


	public InitApplication() {
	}

	public synchronized void reloadALL() {
		
		AppinitWebBean.removeAllInitKeyValues();
		reloadAppInit("initParamates.xml");
		
	}


	private void reloadAppInit( String fileName ) {
		try {
			
			String strFileName = InitApplication.class.getClassLoader().getResource("/") + fileName;
			
			strFileName = strFileName.substring(6);
	
			Document doc = parseDocument(strFileName);

			NodeList selectTags = doc.getElementsByTagName("select");
			for (int i = 0; i < selectTags.getLength(); i++) {				
				String ids[] = new String[0];
				String values[] = new String[0];
				ArrayList id = new ArrayList();
				ArrayList value = new ArrayList();
				String name = ((Element) selectTags.item(i))
						.getAttribute("name");
				NodeList children = selectTags.item(i).getChildNodes();
				for (int j = 0; j < children.getLength(); j++) {
					if (children.item(j).getNodeType() != 1) {
						continue;
					}
					Element child = (Element) children.item(j);
					String childName = child.getTagName();
					if (childName.equals("option")) {
						id.add(child.getAttribute("id"));
						value.add(child.getAttribute("value"));
					}
					AppinitWebBean.removeOption(name);
					AppinitWebBean.addOption(name, (String[]) id.toArray(ids),
							(String[]) value.toArray(values));
				}

			}

			NodeList nlInitKeyValueTags = doc
					.getElementsByTagName("init-key-value");
			for (int i = 0; i < nlInitKeyValueTags.getLength(); i++) {
				Element child = (Element) nlInitKeyValueTags.item(i);
				AppinitWebBean.addInitKeyValue(getInitKeyName(child),
						getInitKeyValue(child));

			}

		} catch (Exception e) {
			if (logger != null) {
				logger.info("***********************************************reloadAppInitWebBean()", e);
			}
			e.printStackTrace();
		}

	}

	private Document parseDocument(String file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(file));
		return document;
	}

	private String getInitKeyName(Element e) {
		if (e == null || !e.hasChildNodes()) {
			return "";
		}
		NodeList nl = e.getElementsByTagName("name");
		if (nl == null || nl.getLength() <= 0) {
			return "";
		}
		if (!nl.item(0).hasChildNodes()) {
			return "";
		} else {
			return nl.item(0).getFirstChild().getNodeValue();
		}
	}

	private String getInitKeyValue(Element e) {
		if (e == null || !e.hasChildNodes()) {
			return "";
		}
		NodeList nl = e.getElementsByTagName("value");
		if (nl == null || nl.getLength() <= 0) {
			return "";
		}
		if (!nl.item(0).hasChildNodes()) {
			return "";
		} else {
			return nl.item(0).getFirstChild().getNodeValue();
		}
	}
	

	
}
