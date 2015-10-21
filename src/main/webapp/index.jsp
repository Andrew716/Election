<%@ page import="packageDB.CreateDataSource" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrii
  Date: 10/21/2015
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Election</title>
</head>
<body>

<%
  CreateDataSource.doCreationDataSource();
%>
<center>
  <form action="ReadFromJSPServlet" method="get">
    <b>Welcome to Election program!</b>


    <p></p>
    <input type="text" name="Surname" value="">Прізвище<Br>
    <p></p>
    <input type="text" name="Name">Ім'я<Br>
    <p></p>
    <input type="text" name="Fathers_name">По батькові<Br>
    <p></p>
    <input type="checkbox" value="candidate" name="candidate_checkBox">Я кандидат<Br>
    <input type="checkbox" value="voter" name="voter_checkBox">Я виборець<Br>
    <p></p>
    <input type="submit" value="Підтвердити">


  </form>

</center>

</body>
</html>
