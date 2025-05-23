<%@ page import="app.entities.Product" %>
<%@ page import="app.model.Store" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style><%@include file="styles/products.css"%></style>
</head>
<body>

<h2>Products</h2>
<form method="post">
  <input type="text" name="productName" placeholder="Product name" required />
  <input type="hidden" name="action" value="addProduct"/>
  <input type="text" name="description" placeholder="Product description" required />
  <input type="number" name="prodPrice" placeholder="Product price" required />
  <button type="submit">Add Product</button>
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
