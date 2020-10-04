package htttpapicall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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
	    	          Gson gson = new Gson(); 
	    	          String jsonStr=response.toString();
	    	          
	    	          //Parse Json Object to Java Object
	    	          JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
	    	          	
	    	          //get data from Object
	    	          JsonElement jsonData  = jsonObject.get("data");
	    	          System.out.println("Data :"+jsonData);
	    	          
	    	          //deserialize json array as root 
	    	          Employee[] employeeArray = gson.fromJson(jsonData, Employee[].class);  
	    	         
	    	          
	    	          //Count All Employee and Display Employee Data
	    	          int count=0;
	    	          for(Employee employee : employeeArray) {
	    	              System.out.println(employee);
	    	              count++;
	    	          }
	    	         
	    	         System.out.println("Total Number of Employee : "+count);
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
