package common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DataActions {

	public static String readXmlData(String fileLocation, String dataNode) {
		String testinput;
		String projectPath = System.getProperty("user.dir");
		File fXmlFile = new File(projectPath + File.separator + fileLocation);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = String.format(dataNode);
		Node node = null;
		try {
			node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		testinput = node != null ? (node.getTextContent()) : "cannot read the test data xml file";
		return testinput;
	}

	public static JSONObject readJsonData(String filePath, String className) {
		JSONObject testObject = null;

		try {
			FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			testObject = (JSONObject) jsonObject.get(className);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return testObject;
	}
}
