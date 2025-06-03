<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Мои заказы</title>
    <style><%@include file="styles/my_orders.css"%></style>
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

<div class="page-container">
    <h2>Ваши заказы</h2>
    <c:forEach var="order" items="${orders}">
        <div class="order-block">
            <p><strong>Заказ №:</strong> ${order.id}</p>
            <p><strong>Имя:</strong> ${order.firstName} ${order.lastName}</p>
            <p><strong>Телефон:</strong> ${order.phoneNumber}</p>
            <p><strong>Адрес:</strong> ${order.address}</p>
            <p><strong>Статус:</strong> ${order.status}</p>
            <p><strong>Итого:</strong> ${order.totalPrice} ₽</p>
            <p><strong>Товары:</strong></p>
            <ul>
                <c:forEach var="entry" items="${order.products.entrySet()}">
                    <li>${entry.key.name} — ${entry.value} шт.</li>
                </c:forEach>
            </ul>
            <c:if test="${order.status != 'Отменён'}">
                <form method="post" action="/my_orders">
                    <input type="hidden" name="orderId" value="${order.id}" />
                    <button type="submit" class="cancel-btn">Отменить заказ</button>
                </form>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>
</html>