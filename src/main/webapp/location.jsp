<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>위치 기반 WiFi 정보</title>
</head>
<body>
    <h1>위치 기반 WiFi 정보</h1>
    <h2>가까운 WiFi 정보</h2>
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

    <h2>검색 히스토리</h2>
    <table border="1">
        <tr>
            <th>위도</th>
            <th>경도</th>
            <th>검색 시간</th>
        </tr>
        <c:forEach var="history" items="${historyData}">
            <tr>
                <td>${history.latitude}</td>
                <td>${history.longitude}</td>
                <td>${history.searchTime}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
