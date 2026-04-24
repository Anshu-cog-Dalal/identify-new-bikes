package org.example.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLDataUtil {

    private static Document document;

    private static void loadXML() {
        if (document == null) {
            try {
                File xmlFile = new File("src/test/resources/testdata/TestData.xml");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();
            } catch (Exception e) {
                throw new RuntimeException("Failed to load XML file", e);
            }
        }
    }

    public static String getData(String parentTag, String childTag) {
        loadXML();
        NodeList nodeList = document.getElementsByTagName(parentTag);

        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getElementsByTagName(childTag)
                        .item(0)
                        .getTextContent();
            }
        }
        return "";
    }

    public static String getDataByAttribute(
            String parentTag,
            String attributeName,
            String attributeValue,
            String childTag) {

        loadXML();
        NodeList nodes = document.getElementsByTagName(parentTag);

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            if (element.getAttribute(attributeName).equals(attributeValue)) {
                return element.getElementsByTagName(childTag)
                        .item(0)
                        .getTextContent();
            }
        }
        return "";
    }
}