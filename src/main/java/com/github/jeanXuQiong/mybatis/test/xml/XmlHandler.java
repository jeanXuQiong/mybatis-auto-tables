package com.github.jeanXuQiong.mybatis.test.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class XmlHandler extends DefaultHandler {

    private Map<String,String> map;
    //开始解析每个元素时都会调用该方法
     @Override
     public void startElement(String uri, String localName, String qName,
             Attributes attributes) throws SAXException {
                  // TODO Auto-generated method stub
                 //判断正在解析的元素是不是开始解析的元素
                 System.out.println("--startElement()--"+qName);
                 if(qName.equals("resultMap")){
                         map=new HashMap<String, String>();
                     }

                 //判断正在解析的元素是否有属性值,如果有则将其全部取出并保存到map对象中，如:<person id="00001"></person>
            }


}
