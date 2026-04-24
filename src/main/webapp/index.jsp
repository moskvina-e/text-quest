<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Космический квест</title>
</head>
<body>

     <!-- Блок для отображения сообщения об ошибке -->
        <h1>Добро пожаловать на борт!</h1>
        <p>Вы стоите в космическом порту. Принять вызов НЛО?</p>
         <% if (request.getAttribute("message") != null) { %>
         <p style="color: red;"><%= request.getAttribute("message") %></p>
         <% } %>

      <!-- Форма для отправки ответа -->
         <form action="game" method="post">
             <!-- Радио-кнопки для выбора ответа -->
             <input type="radio" name="answer" value="accept"> Принять вызов<br>
             <input type="radio" name="answer" value="decline"> Отклонить вызов<br>
             <!-- Кнопка отправки формы -->
             <input type="submit" value="Ответить">
         </form>

</body>
</html>