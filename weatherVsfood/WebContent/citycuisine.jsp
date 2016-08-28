<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>City and Cuisine</title>
</head>
<body>
<h3>Hello <%=session.getAttribute("name")%> !
</h3>

<p><font color=blue><h3> City: </font><%=session.getAttribute("city") %></p>
<p><font color=blue> Cuisine: </font><%=session.getAttribute("cuisine") %></p>
<p><font color=red> Current Weather: </font><%=session.getAttribute("weatherConditions") %></p>
</h3>
<h4>
<table  style="width:100%" border= 1><tr><th>Restuarant Name</th><th>Phone Number</th><th>Rating</th></tr>
<tr><td align=center><%=session.getAttribute("businessNamea") %></td><td align=center><%=session.getAttribute("businessPhonea") %></td><td align=center><%=session.getAttribute("businessRatinga") %></td></tr>
<tr><td align=center><%=session.getAttribute("businessNameb") %></td><td align=center><%=session.getAttribute("businessPhoneb") %></td><td align=center><%=session.getAttribute("businessRatingb") %></td></tr>
<tr><td align=center><%=session.getAttribute("businessNamec") %></td><td align=center><%=session.getAttribute("businessPhonec") %></td><td align=center><%=session.getAttribute("businessRatingc") %></td></tr>



</table>
</h4>






</body>
</html>