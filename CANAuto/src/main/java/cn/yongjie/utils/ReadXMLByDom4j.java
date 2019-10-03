package cn.yongjie.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ReadXMLByDom4j {

    public static Document getDocument(String fileName) throws DocumentException{

        // 1.创建一个SaxReader
        SAXReader sr = new SAXReader();
        // 2. 读取一个xml文件，得到这个xml文件的Document对象
        Document document = sr.read(fileName);

        return document;
    }

    // 将xml document写入文件
    public static void documentToXML(Document document, String fileName) throws Exception, FileNotFoundException{

        OutputFormat format = OutputFormat.createCompactFormat();
        XMLWriter writer = new XMLWriter(new FileOutputStream(fileName), format);
        writer.write(document);
        writer.close();
    }


}
