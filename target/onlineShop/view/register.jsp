<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Регистрация</title>
  <style><%@include file="styles/register.css"%></style>
</head>
<body>
<div class="form-container">
  <h2>Регистрация</h2>
  <form method="post">
    <input type="text" name="username" placeholder="Логин" required />
    <input type="email" name="email" placeholder="Email" required />
    <input type="password" name="password" placeholder="Пароль" required />
    <input type="hidden" name="action" value="register"/>
    <button type="submit">Зарегистрироваться</button>
  </form>
  <a class="switch-link" href="login">Уже есть аккаунт? Войти</a>
</div>
</body>
</html>
