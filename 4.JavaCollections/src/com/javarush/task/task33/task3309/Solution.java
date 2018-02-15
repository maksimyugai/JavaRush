package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //создаем дерево, состоящее из тэгов
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(false);

            //делаем маршалинг в объект document
            marshaller.marshal(obj, document);

            //делаем CDATA, если нужно
            changeTextToCDATA(document, document);

            //добавляем комменты
            NodeList nodeList = document.getElementsByTagName(tagName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node element = nodeList.item(i);
                element.getParentNode().insertBefore(document.createComment(comment), element);
            }

            /*
             * source - источник xml - дерева
             * result - поток, который запишет xml-дерево во writer
             */
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(writer);

            //записываем из source в result -> writer с помощью transformer
            //создаем трансформер, чтобы преобразовать дерево в результирующее дерево
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            //разршаем добавление доп. пробелов, чтобы было красиво, в виде иерархии
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        }
        catch (Exception e){}
        finally {
            writer.close();
        }
        return writer.toString();
    }
    private static void changeTextToCDATA(Node mainNode, Document document){
        if (mainNode.hasChildNodes()){
            NodeList children = mainNode.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                if (node.getNodeType() == Node.TEXT_NODE){
                    if (node.getTextContent().matches(".*[<>&].*")) {
                        Node newNode = document.createCDATASection(node.getTextContent());
                        node.getParentNode().replaceChild(newNode, node);
                    }
                }
                else
                    changeTextToCDATA(node, document);
            }
        }
    }
    public static void main(String[] args) {
    }
}
