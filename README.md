# weather-Vs-food

index.jsp is used to get the input for username and password, it passes the data onto LoginServlet class

The username and password are verified in the MySQL table loginIDdetails via class LoginDao
  If the username and password are present, the class LoginServlet redirects to welcome.jsp
  If not, the user is redirected to the index.jsp

The user is asked for the City and Cuisine in the welcome.jsp page

Based on the city and cuisine, the weather and the top 3 restuarants are output on citycuisine.jsp using JSON data from openweatherdata.org and Yelp respectively.

