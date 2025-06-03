<%@ page import="app.entities.Order" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <style><%@include file="styles/all_orders.css"%></style>
    <title>Админ - Все заказы</title>
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

<div class="page-container">
    <h2>Все заказы пользователей</h2>

    <c:choose>
        <c:when test="${not empty orders}">
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
                        <form method="post" action="all_orders">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <button type="submit" name="action" value="complete" class="admin-action-btn status-complete">Выполнить</button>
                            <button type="submit" name="action" value="reject" class="admin-action-btn status-reject">Отклонить</button>
                            <button type="submit" name="action" value="delete" class="admin-action-btn delete-order">Удалить</button>
                        </form>
                    </c:if>
                    <c:if test="${order.status == 'Отменён'}">
                        <form method="post" action="all_orders">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <button type="submit" name="action" value="delete" class="admin-action-btn delete-order">Удалить</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>Заказы не найдены</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
