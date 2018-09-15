package com.github.jeanXuQiong;

import com.github.jeanXuQiong.mybatis.test.dao.TestDao;
import com.github.jeanXuQiong.mybatis.test.xml.XmlHandler;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisInitAutoTableApplicationTests {
	@Autowired
	private TestDao testDao;

	@Test
	public void contextLoads() throws Exception {
		System.out.print(testDao.getAll());
		String field = "value";
		Map<String, String> map = loadVlaue(MapperScan.class,field,MybatisInitAutoTableApplication.class.getName());
		System.out.print(map.get(field));

	}

	public Map<String,String> getXmlMsg(){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			InputStream stream = this.getClass().getResourceAsStream("com/github/jeanXuQiong/mybatis/test/dao/TestDao.xml");
			saxParser.parse(stream,new XmlHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}
	public static void main(String[] args) throws  Exception{
		SAXReader saxReader = new SAXReader();
		InputStream stream = MybatisInitAutoTableApplicationTests.class.getResourceAsStream("com/github/jeanXuQiong/mybatis/test/dao/TestDao.xml");
		Document doc = saxReader.read(stream);
		Element rootElement = doc.getRootElement();

		Element resultMap = rootElement.element("resultMap");
		Element id = resultMap.element("id");


		Element result = resultMap.element("result");


	}


	public Map<String, String> loadVlaue(Class annotationClasss,
										 String annotationField, String className) throws Exception {

		System.out.println("处理Annotation类名称  === "+annotationClasss.getName());
		System.out.println("处理Annotation类属性名称  === "+annotationField);
		System.out.println("处理Annotation的调用类名称  === "+className);
		Map<String, String> map = new HashMap<String, String>();
		Class clazz = Class.forName(className);
		if (clazz.isAnnotationPresent(annotationClasss)) {
			Annotation p = clazz.getAnnotation(annotationClasss);
			Method m = p.getClass()
					.getDeclaredMethod(annotationField, null);
			String[] values = (String[]) m.invoke(p, null);
			for (String key : values) {
				System.out.println("注解值 === " + key);
				map.put(annotationField, key);
			}
		}
		System.out.println("map数量  === " + map.size());
		return map;
	}
}
