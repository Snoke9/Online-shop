<%@ page import="app.entities.Product" %>
<%@ page import="app.model.Store" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style><%@include file="styles/products.css"%></style>
</head>
<body>

<header class="header">
  <div class="header-container">
    <div class="nav-links">
      <a href="<%= request.getContextPath() %>/products" class="nav-link active">Добавление товаров</a>
      <a href="<%= request.getContextPath() %>/all_orders" class="nav-link active">Заказы</a>
    </div>
    <form method="post" class="logout-container">
      <input type="hidden" name="action" value="exit"/>
      <button class="logout-btn active" onclick="logout()">Выйти</button>
    </form>
  </div>
</header>

<h2>Товары</h2>
<form method="post">
  <input type="text" name="productName" placeholder="Название товара" required />
  <input type="hidden" name="action" value="addProduct"/>
  <input type="text" name="description" placeholder="Описание товара" required />
  <input type="number" name="prodPrice" placeholder="Цена товара" required />
  <button type="submit">Добавить товар</button>
</form>

<table border="1">
  <thead>
  <tr>
    <th>Товар</th>
    <th>Описание</th>
    <th>Цена</th>
    <th>Действие</th>
  </tr>
  </thead>
  <tbody>
  <% for (Product p : Store.getProducts()) { %>
  <tr>
    <td><%= p.getName() %></td>
    <td><%= p.getDescription() %></td>
    <td><%= p.getProdPrice() %></td>
    <td>
      <form method="post" style="display:inline">
        <input type="hidden" name="id" value="<%= p.getId() %>"/>
        <input type="hidden" name="action" value="delete"/>
        <button type="submit">Удалить</button>
      </form>
    </td>
  </tr>
  <% } %>
  </tbody>
</table>

<a style="text-decoration:none">
  <button type="button" onclick="history.back()">Назад</button>
</a>


</body>
</html>
