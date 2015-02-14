package com.newgame;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.jar.Attributes;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class BannerParser extends DefaultHandler {
	
	ArrayList<Banner> bannerList;
	Banner banner;
	String data,tempVal;
	
	public BannerParser(String Data) {
		// TODO Auto-generated constructor stub
		bannerList = new ArrayList<Banner>();
		this.data = data;
		
	}
	
    public	byte parse() {
    	
    	SAXParserFactory spf = null;
		SAXParser sp = null;
		InputStream inputStream = null;
		
		try {
			inputStream = new ByteArrayInputStream(data.getBytes());
			spf = SAXParserFactory.newInstance();
			if (spf != null) {
				sp = spf.newSAXParser();
				sp.parse(inputStream, this);
			}
		}
		
		/*
		 * Exceptions need to be handled MalformedURLException
		 * ParserConfigurationException IOException SAXException
		 */

		catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {
			}
		}
		if (bannerList != null && bannerList.size() > 0) {
			//	//Log.d("Array List Size",""+tipsList.get(4).getTitle());
				
			
				return 1;
			} else {
				return 0;
			}
		
    }
    public ArrayList<Banner> getBannerList(){
		 return bannerList;
	 }
    
    
    
    
    public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		 

		 if(localName.equalsIgnoreCase("addbanner")){
			 banner = new Banner();
			 Log.d("Working", "+++++++++++++++++++++++");
		 }
	  
		 
		 
	 }
	 
	 public void characters(char[] ch, int start, int length)
				throws SAXException {
			tempVal = new String(ch, start, length);
		}
	 
	 public void endElement(String uri, String localName, String qName)
				throws SAXException {
		 
		 if(localName.equalsIgnoreCase("addbanner")){
			bannerList.add(banner);
			 Log.d("Working", "+++++++++++++++++++++++");
		 }
		 
		 if(localName.equalsIgnoreCase("bannerpath")){
				banner.setBannerPath(tempVal);
			 }
		 
		 if(localName.equalsIgnoreCase("addlink")){
				banner.setBannerLink(tempVal);
			 }
		 
		 
	 }

}
