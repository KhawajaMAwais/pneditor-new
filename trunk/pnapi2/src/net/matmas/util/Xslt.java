package net.matmas.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Contains XSLT related functions.
 * @author matmas
 */
public class Xslt {
	
	/**
	 * Transforms one XML file to another XML using a XSLT transformation
	 * @param fileToTransform XML source file to transform from
	 * @param xslt XSLT file to transform with
	 * @param output XML destination file to store the result
	 * @return output file
	 * @throws java.io.IOException
	 * @throws javax.xml.transform.TransformerException
	 */
	public static File transformXml(File fileToTransform, InputStream xslt, File output) throws IOException, TransformerException {
		Source xmlSource = new StreamSource(fileToTransform);
		Source xsltSource = new StreamSource(xslt); //or DOMSource or SAXSource
//		Source xmlSource = new SAXSource(new InputSource(fileToTransform.toString()));
//		Source xsltSource = new SAXSource(new InputSource(xslt)); //or DOMSource or SAXSource
		Result result = new StreamResult(output);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer(xsltSource);
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.transform(xmlSource, result);
		return output;
	}
	
}
