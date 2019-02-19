package org.testwork.assignment.output;

import org.testwork.assignment.Athlet;
import org.testwork.assignment.event.EventType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintStream;
import java.util.List;

public class XMLFormatter {
    public void createXML(List<Athlet> athlets, PrintStream outputStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("result");
            doc.appendChild(root);

            for (Athlet athlet: athlets) {
                root.appendChild(createAthlet(doc, athlet));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = transformerFactory.newTransformer();

            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);

            StreamResult console = new StreamResult(outputStream);

            transf.transform(source, console);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Node createAthlet(Document doc, Athlet athlet) {
        Element athletElement = doc.createElement("athlet");
        athletElement.setAttribute("name", athlet.getName());
        athletElement.setAttribute("place", athlet.getPlace());
        athletElement.setAttribute("score", Integer.toString(athlet.getScore()));
        for (int i = 0; i < athlet.getDecathlon().length; i ++) {
            Element node = doc.createElement(EventType.getByOrder(i).name());
            node.appendChild(doc.createTextNode(Double.toString(athlet.getDecathlon()[i])));
            athletElement.appendChild(node);
        }
        return athletElement;
    }
}
