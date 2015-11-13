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

    <b>Welcome to Election program!</b>

    <div class="virovnyat">
      <form action="AddPersonServlet" method="post">
      <p></p>
      <input type="text" name="Surname" >Surname<Br>
      <p></p>
      <input type="text" name="Name">Name<Br>
      <p></p>
      <input type="text" name="Fathers_name">Father's name<Br>
      <p></p>
      <input type="text" name="Password">Password<Br>
      <p></p>
      <input type="text" name="PasswordConfirm">Confirm password<Br>
      <p></p>
      <input type="checkbox"  name="candidate_checkBox">I am a candidate<Br>
      <input type="checkbox"  name="voter_checkBox">I am a voter<Br>
      <p></p>
      <input type="submit"  name="buttonSubmit">
  </form>

  <form action="matchPage.jsp" method="post">
    <input type="submit" name="vote" value="vote">
  </form>
    </div>





</center>

</body>
</html>
