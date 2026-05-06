<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Космический квест</title>
</head>
<body>

  <h1>Космический квест</h1>

  <div>
    <p>Игрок: ${sessionScope.playerName}</p>
    <p>Сыграно игр: ${sessionScope.gamesPlayed != null ? sessionScope.gamesPlayed : 0}</p>
  </div>

  <hr>

 <h2>${step.text}</h2>
  <c:choose>
    <c:when test="${not empty step.option1}">

    </c:when>
</body>
</html>