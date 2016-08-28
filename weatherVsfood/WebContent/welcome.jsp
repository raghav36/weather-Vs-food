<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome <%=session.getAttribute("name")%></title>
</head>
<body>
    <h4>
        Hello,
        <%=session.getAttribute("name")%></h4>
       <form action="/weatherVsfood/cityAndCuisine" method="get">
        <fieldset style="width: 300px">
            <legend> Weather Vs Food</legend>
            <table>
                <tr>
                    <td>City name</td>
                    <td><input type="text" name="city" required="required" /></td>
                </tr>
                <tr>
                    <td>Cuisine</td>
                    <td><input type="text" name="cuisine" required="required" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Search" /></td>
                </tr>
            </table>
        </fieldset>
    </form>
</body>
</html>
