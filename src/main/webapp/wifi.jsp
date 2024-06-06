<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>WiFi 정보</title>
</head>
<body>
    <h1>WiFi 정보</h1>
    <table border="1">
        <tr>
            <th>이름</th>
            <th>주소</th>
            <th>위도</th>
            <th>경도</th>
        </tr>
        <c:forEach var="wifi" items="${wifiData}">
            <tr>
                <td>${wifi.name}</td>
                <td>${wifi.address}</td>
                <td>${wifi.latitude}</td>
                <td>${wifi.longitude}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
