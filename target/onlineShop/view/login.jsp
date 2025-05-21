<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Вход</title>
  <style><%@include file="styles/login.css"%></style>
  <% if (session.getAttribute("errorMessage") != null) { %>
  <script>
    alert('<%= session.getAttribute("errorMessage") %>');
  </script>
  <% session.removeAttribute("errorMessage"); %>
  <% } %>
</head>
<body>
<div class="form-container">
  <h2>Вход</h2>
  <form method="post">
    <input type="text" name="username" placeholder="Логин" required />
    <input type="password" name="password" placeholder="Пароль" required />
    <input type="hidden" name="action" value="login"/>
    <button type="submit">Войти</button>
  </form>
  <a class="switch-link" href="register">Нет аккаунта? Зарегистрироваться</a>
</div>
</body>
</html>
