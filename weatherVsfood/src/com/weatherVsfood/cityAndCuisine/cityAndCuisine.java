package com.weatherVsfood.cityAndCuisine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.weatherVsfood.LoginServlet.TwoStepOAuth;
import com.weatherVsfood.LoginServlet.YelpAPI;

/**
 * Servlet implementation class cityAndCuisine
 */
@SuppressWarnings("serial")
public class cityAndCuisine extends HttpServlet {
	
	private final String USER_AGENT = "Mozilla/5.0";

	public String weatherConditions=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
			response.setContentType("text/html");  
	        PrintWriter out = response.getWriter();  
	     
	        String w= request.getParameter("city");
			w= w.substring(0, 1).toUpperCase() + w.substring(1, w.length()).toLowerCase();
			
	        String v= request.getParameter("cuisine");
			v= v.substring(0, 1).toUpperCase() + v.substring(1, v.length()).toLowerCase();
	        
			cityAndCuisine http = new cityAndCuisine();
	        try {
				weatherConditions=http.sendGet(w);
				weatherConditions= weatherConditions.substring(0, 1).toUpperCase() + weatherConditions.substring(1, weatherConditions.length()).toLowerCase();   
				request.getSession().setAttribute("weatherConditions", weatherConditions);
	        } catch (Exception e) {
				e.printStackTrace();
			}
	        
	       
	        YelpAPI.mainYelp("",w,v);
	        request.getSession().setAttribute("businessNamea", YelpAPI.bna());
	        request.getSession().setAttribute("businessPhonea", YelpAPI.pna());
	        request.getSession().setAttribute("businessRatinga", YelpAPI.rna());		    
	    	
	        request.getSession().setAttribute("businessNameb", YelpAPI.bnb());
	        request.getSession().setAttribute("businessPhoneb", YelpAPI.pnb());
	        request.getSession().setAttribute("businessRatingb", YelpAPI.rnb());

	        request.getSession().setAttribute("businessNamec", YelpAPI.bnc());
	        request.getSession().setAttribute("businessPhonec", YelpAPI.pnc());
	        request.getSession().setAttribute("businessRatingc", YelpAPI.rnc());  
	        request.getSession().setAttribute("city", w);
	        request.getSession().setAttribute("cuisine", v);
	        request.getSession().setAttribute("rowdy", "hello");
	        
	        response.sendRedirect("citycuisine.jsp");
	        out.close();    			

	}
	
	public String sendGet(String s) throws Exception {

		String url = "http://api.openweathermap.org/data/2.5/weather?q="+s+"";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
	
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		String t= response.toString();
		JSONObject j= (JSONObject) new JSONParser().parse(t);
		ArrayList v= (ArrayList) j.get("weather");
		Iterator i= v.iterator();
		String m= "";
		while(i.hasNext()){
			JSONObject mn= (JSONObject) i.next();
			m= mn.get("description").toString();
		}
		   
	    return m;
	}
}
