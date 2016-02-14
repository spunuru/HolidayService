package com.mycompany.hr.util;

import java.io.IOException;
import java.io.StringWriter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLUtil {

    /**
     * converts an XML element to string format
     * @param element
     * @return
     * @throws IOException
     */
    public static String xmlElementToString(Element element) throws IOException {
        Document doc = element.getDocument();
        if(doc == null){
            doc = new Document();
            doc.setRootElement(element);
        }
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        StringWriter out = new StringWriter();
        outputter.output(doc, out);
        return out.toString();
    }
    
    private XMLUtil() {}
}
