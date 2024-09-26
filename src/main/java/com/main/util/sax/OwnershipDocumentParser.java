package com.main.util.sax;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.main.util.sax.OwnershipDocument;

public class OwnershipDocumentParser {

  public OwnershipDocument parseXml(String xmlUrl) {
    //Create an empty link of users initially
	  OwnershipDocument ownershipDocument =null;
    try {
      //Create default handler instance
    	OwnershipDocumentHandler ownershipDocumentHandler = new OwnershipDocumentHandler();

      //Create parser from factory
      XMLReader xmlReader = XMLReaderFactory.createXMLReader();

      //Register handler with parser
      xmlReader.setContentHandler(ownershipDocumentHandler);
      
      URL linkURL = new URL(xmlUrl);
      
      URLConnection hc = linkURL.openConnection();
      hc.setRequestProperty("User-Agent", "ravirao1224@gmail.com");

      

      //Create an input source from the XML input stream
      InputSource source = new InputSource(hc.getInputStream());

      //parse the document
      xmlReader.parse(source);

      //populate the parsed users list in above created empty list; You can return from here also.
      ownershipDocument = ownershipDocumentHandler.getOwnershipDocument();

    } catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {

    }
    return ownershipDocument;
  }
}