<%@ page import="app.model.*" %>
<%@ page import="app.entities.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Order Products</title>
  <style><%@include file="styles/orders.css"%></style>
  <script>
    function updateTotal() {
      let total = 0;
      const prices = document.querySelectorAll('[data-price]');
      prices.forEach(p => {
        const price = parseFloat(p.dataset.price);
        const qty = parseInt(document.getElementById('qty_' + p.dataset.id).value);
        total += price * qty;
      });
      document.getElementById("totalSum").innerText = total.toFixed(2);
      document.getElementById("totalPriceInput").value = total.toFixed(2);
    }
  </script>

  <% if (session.getAttribute("errorMessage") != null) { %>
  <script>
    alert('<%= session.getAttribute("errorMessage") %>');
  </script>
  <% session.removeAttribute("errorMessage"); %>
  <% } %>
</head>
<body>

<header class="header">
  <div class="header-container">
    <div class="nav-links">
      <a href="<%= request.getContextPath() %>/orders" class="nav-link active">Магазин</a>
      <a href="<%= request.getContextPath() %>/my_orders" class="nav-link active">Мои заказы</a>
    </div>
    <form method="post" class="logout-container">
      <input type="hidden" name="action" value="exit"/>
      <button class="logout-btn active" onclick="logout()">Выйти</button>
    </form>
  </div>
</header>

<h2>Оформление заказа</h2>
<form method="post">
  <table>
    <thead>
    <tr>
      <th>Название</th>
      <th>Описание</th>
      <th>Цена</th>
      <th>Количество</th>
    </tr>
    </thead>
    <tbody>
    <% for (Product p : Store.getProducts()) { %>
    <tr data-price="<%= p.getProdPrice() %>" data-id="<%= p.getId() %>">
      <td><%= p.getName() %></td>
      <td><%= p.getDescription() %></td>
      <td><%= p.getProdPrice() %> ₽</td>
      <td>
        <input type="number" name="quantity_<%= p.getId() %>" id="qty_<%= p.getId() %>" value="0" min="0" max="52" onchange="updateTotal()"/>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <div class="form-group">
    <label>Имя: <input type="text" name="firstName" required /></label>
  </div>
  <div class="form-group">
    <label>Фамилия: <input type="text" name="lastName" required /></label>
  </div>
  <div class="form-group">
    <label>Телефон: <input type="text" name="phone" required /></label>
  </div>
  <div class="form-group">
    <label>Адрес доставки: <input type="text" name="address" required /></label>
  </div>

  <div class="total">
    <input type="hidden" name="totalPrice" id="totalPriceInput" value="0" />
    Итого: <span id="totalSum">0.00</span> ₽
  </div>

  <input type="hidden" name="action" value="create"/>
  <button class="payButton" type="submit">Оформить заказ</button>
</form>

</body>
</html>