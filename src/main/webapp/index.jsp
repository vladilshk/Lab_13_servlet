<%@ page import="Data.DataBase" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NOTEBOOK</title>
</head>
<body>
<h1 align="center">Notebook</h1>

<a href="addUser.jsp">
    <button>Add user or number</button>
</a>

<%
    /*DataBase dataBase = new DataBase();
    dataBase.getData();
    */
    String users = DataBase.getUsersForPrint();

%>
<p>
    <%= users %>
</p>
</body>
</html>