package htttpapicall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData {
 public static void main(String[] args)throws IOException {
	
	 URL url;
	 HttpURLConnection con;
     String readLine=null;
     
	try {
		url = new URL("http://dummy.restapiexample.com/api/v1/employees");
		con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod("GET");
	    int resCode=con.getResponseCode();
	    System.out.println(" Response Code : "+resCode);
	    	
	    		if(resCode==HttpURLConnection.HTTP_OK)
	    		{
	    	          BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));    
	    	          StringBuffer response=new StringBuffer();
	    	          while ((readLine=br.readLine())!=null) {
	    	        	  response.append(readLine);
					}
	    	          br.close();
	    	          System.out.println(" Data In JSON : "+response);
	    	          
	    		}
	    		else
	    		{
	    			System.out.println("Get Api Resources Not Found...!");
	    		}
	    		
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  
 }
}
