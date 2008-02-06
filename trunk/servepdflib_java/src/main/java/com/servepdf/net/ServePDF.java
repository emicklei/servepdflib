package com.servepdf.net;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.servepdf.dto.PDFData;

public class ServePDF {
	
	public String base_url = "http://pws.servepdf.com";
	
	public void process(PDFData data, OutputStream out){
		HttpClient http = new HttpClient();
		PostMethod post = new PostMethod(base_url + "/process");		
		try {
			post.setRequestEntity(new StringRequestEntity(data.toXML(),"text/xml" , "utf8"));
			int code = http.executeMethod(post);
			if (code != HttpStatus.SC_OK) 
				throw new RuntimeException("post failed returning http code:" + code);
			InputStream pdfStream =  post.getResponseBodyAsStream();
			byte[] buffer = new byte[1024];
			int howMany = 0;
			do {
				howMany = pdfStream.read(buffer);
				if (howMany > 0) out.write(buffer, 0, howMany);
			} while (howMany != -1);
		} catch (Exception ex) {
			throw new RuntimeException("Unable to process PDFData",ex);
		} finally {
			post.releaseConnection();
		}		
	}
}