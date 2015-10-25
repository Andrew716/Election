<%--
  Created by IntelliJ IDEA.
  User: Andrii
  Date: 10/22/2015
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <title>Election</title>
</head>
<body>

<center>
  <form action="AddPersonServlet" method="post">
    <b>Welcome to Election program!</b>


    <p></p>
    <input type="text" name="Surname" >Прізвище<Br>
    <p></p>
    <input type="text" name="Name">Ім'я<Br>
    <p></p>
    <input type="text" name="Fathers_name">По батькові<Br>
    <p></p>
    <input type="checkbox"  name="candidate_checkBox">Я кандидат<Br>
    <input type="checkbox"  name="voter_checkBox">Я виборець<Br>
    <p></p>
    <input type="submit"  name="buttonSubmit">


  </form>

</center>

</body>
</html>
