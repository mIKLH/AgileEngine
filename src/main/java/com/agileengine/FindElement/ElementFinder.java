package com.agileengine.FindElement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;

public class ElementFinder {

    public String findElement(File file, String element) throws Exception {
        String result = null;
        try (FileInputStream fileIS = new FileInputStream(file)) {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(fileIS);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.compile(element).evaluate(xmlDocument, XPathConstants.NODESET);

            if (nodeList.getLength() > 0) {
                Node nNode = nodeList.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    result = getXPath(nodeList.item(0));
                }
            } else {
                throw new Exception("Element not found!");
            }

        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private String getXPath(Node node) {
        Node parent = node.getParentNode();
        if (parent == null) {
            return node.getNodeName();
        }
        return getXPath(parent) + "/" + node.getNodeName();
    }
}
