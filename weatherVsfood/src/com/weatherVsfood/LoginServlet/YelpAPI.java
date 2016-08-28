package com.weatherVsfood.LoginServlet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Code sample for accessing the Yelp API V2.
 * 
 * This program demonstrates the capability of the Yelp API version 2.0 by using the Search API to
 * query for businesses by a search term and location, and the Business API to query additional
 * information about the top result from the search query.
 * 
 * <p>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp Documentation</a> for more info.
 * 
 */
public class YelpAPI {

  private static final String API_HOST = "api.yelp.com";
  private static final String DEFAULT_TERM = "indian";
  private static final String DEFAULT_LOCATION = "Indiana";
  private static final int SEARCH_LIMIT = 3;
  private static final String SEARCH_PATH = "/v2/search";
  private static final String BUSINESS_PATH = "/v2/business";

  /*
   * Update OAuth credentials below from the Yelp Developers API site:
   * http://www.yelp.com/developers/getting_started/api_access
   */
  private static final String CONSUMER_KEY = "";
  private static final String CONSUMER_SECRET = "";
  private static final String TOKEN = "";
  private static final String TOKEN_SECRET = "";

  public static String businessNamea=null;
  public static String businessName1a=null;
  public static String businessRatinga=null;
  public static String businessPhonea=null;
	
  public static String businessNameb=null;
  public static String businessName1b=null;
  public static String businessRatingb=null;
  public static String businessPhoneb=null;
	
  public static String businessNamec=null;
  public static String businessName1c=null;
  public static String businessRatingc=null;
  public static String businessPhonec=null;
  
  OAuthService service;
  Token accessToken;

  /**
   * Setup the Yelp API OAuth credentials.
   * 
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
    this.service =
        new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
            .apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
  }

  /**
   * Creates and sends a request to the Search API by term and location.
   * <p>
   * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
   * for more info.
   * 
   * @param term <tt>String</tt> of the search term to be queried
   * @param location <tt>String</tt> of the location
   * @return <tt>String</tt> JSON Response
   */
  public String searchForBusinessesByLocation(String term, String location) {
    OAuthRequest request = createOAuthRequest(SEARCH_PATH);
    request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("location", location);
    request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
    return sendRequestAndGetResponse(request);
  }

  /**
   * Creates and sends a request to the Business API by business ID.
   * <p>
   * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
   * for more info.
   * 
   * @param businessID <tt>String</tt> business ID of the requested business
   * @return <tt>String</tt> JSON Response
   */
  public String searchByBusinessId(String businessID) {
    OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
    return sendRequestAndGetResponse(request);
  }

  /**
   * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
   * 
   * @param path API endpoint to be queried
   * @return <tt>OAuthRequest</tt>
   */
  private OAuthRequest createOAuthRequest(String path) {
    OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
    return request;
  }

  /**
   * Sends an {@link OAuthRequest} and returns the {@link Response} body.
   * 
   * @param request {@link OAuthRequest} corresponding to the API request
   * @return <tt>String</tt> body of API response
   */
  private String sendRequestAndGetResponse(OAuthRequest request) {
    this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    return response.getBody();
  }

  /**
   * Queries the Search API based on the command line arguments and takes the first result to query
   * the Business API.
   * 
   * @param yelpApi <tt>YelpAPI</tt> service instance
   * @param yelpApiCli <tt>YelpAPICLI</tt> command line arguments
   */
  private static void queryAPI(YelpAPI yelpApi, YelpAPICLI yelpApiCli) {
    String searchResponseJSON =
        yelpApi.searchForBusinessesByLocation(yelpApiCli.term, yelpApiCli.location);

    JSONParser parser = new JSONParser();
    JSONObject response = null;
    try {
      response = (JSONObject) parser.parse(searchResponseJSON);
    } catch (ParseException pe) {
      System.out.println("Error: could not parse JSON response:");
      System.out.println(searchResponseJSON);
      System.exit(1);
    }

    JSONArray businesses = (JSONArray) response.get("businesses");
    for(int k=0; k<3; k++){
		JSONObject businessValue= (JSONObject) businesses.get(k);
	    	
	    if(k==0){
	    	businessName1a= businessValue.get("name").toString().replace("-", " ");
			businessPhonea= businessValue.get("phone").toString().replace("-", " ");
			businessRatinga= businessValue.get("rating").toString().replace("-", " ");		        
			businessNamea= businessName1a.substring(0, 1).toUpperCase() + businessName1a.substring(1, businessName1a.length()).toLowerCase();
				    
	    }
	    if(k==1){
	    	businessName1b= businessValue.get("name").toString().replace("-", " ");
			businessPhoneb= businessValue.get("phone").toString().replace("-", " ");
			businessRatingb= businessValue.get("rating").toString().replace("-", " ");		        
			businessNameb= businessName1b.substring(0, 1).toUpperCase() + businessName1b.substring(1, businessName1b.length()).toLowerCase();
	    }
	    if(k==2){
	    	businessName1c= businessValue.get("name").toString().replace("-", " ");
			businessPhonec= businessValue.get("phone").toString().replace("-", " ");
			businessRatingc= businessValue.get("rating").toString().replace("-", " ");		        
			businessNamec= businessName1c.substring(0, 1).toUpperCase() + businessName1c.substring(1, businessName1c.length()).toLowerCase();
			    	
	    }
	}

  }

  public static String bna(){
	  return businessNamea;
  }
  
  public static String bnb(){
	  return businessNameb;
  }
  
  public static String bnc(){
	  return businessNamec;
  }
  
  public static String pna(){
	  return businessPhonea;
  }
  public static String pnb(){
	  return businessPhoneb;
  }
  public static String pnc(){
	  return businessPhonec;
  }
  
  public static String rna(){
	  return businessRatinga;
  }
  
  public static String rnb(){
	  return businessRatingb;
  }
  
  public static String rnc(){
	  return businessRatingc;
  }
  /**
   * Command-line interface for the sample Yelp API runner.
   */
  public static class YelpAPICLI {
    @Parameter(names = {"-q", "--term"}, description = "Search Query Term")
    public String term = DEFAULT_TERM;

    @Parameter(names = {"-l", "--location"}, description = "Location to be Queried")
    public String location = DEFAULT_LOCATION;
    
    YelpAPICLI(String w, String v){
    	if(w!="" && v!=""){
    		term= v;
    		location= w;
    	}
    }
  }

  /**
   * Main entry for sample Yelp API requests.
   * <p>
   * After entering your OAuth credentials, execute <tt><b>run.sh</b></tt> to run this example.
   */
  public static void mainYelp(String string, String w, String v) {
    YelpAPICLI yelpApiCli = new YelpAPICLI(w,v);
    new JCommander(yelpApiCli, string);

    YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
    queryAPI(yelpApi, yelpApiCli);
  }
}
