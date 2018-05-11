package jtidy.java_jtidy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class AppConvertTest {

	@Test
	public void test() {
		testConvertedTindy();
	}

	public void testConvertedTindy(){
		System.out.println( "Start process" );
        String file = "C:\\Users\\detectortroyano\\Downloads\\file-html.html";
        String outDirectory = "C:\\Users\\detectortroyano\\Downloads\\";
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);

			Tidy tidy = new Tidy();
			tidy.setShowWarnings(false);
			tidy.setXmlTags(false);
			tidy.setInputEncoding("UTF-8");
			tidy.setOutputEncoding("UTF-8");
			tidy.setXHTML(true);
			tidy.setMakeClean(true);
			Document xmlDoc = tidy.parseDOM(fis, null);
			
			tidy.pprint(xmlDoc,new FileOutputStream(outDirectory+"file-html.xhtml"));
			
			
			String inputFile = outDirectory+"file-html.xhtml";
			String url = new File(inputFile).toURI().toURL().toString();
			String outputFile = outDirectory+"file-html.pdf";
			OutputStream os = new FileOutputStream(outputFile);
			
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(url);
			renderer.layout();
			renderer.createPDF(os);
			
			os.close();
			System.out.println( "Completed process" );
		}catch (java.io.FileNotFoundException e){  
		    System.out.println("File not found: ");  
		}catch(Exception e){
			System.out.println( "Error =>" + e);
		}
	}

}
