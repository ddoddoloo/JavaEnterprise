package com.mywifiproject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/wifi")
public class WiFiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataService dataService = new DataService();
        List<WiFiInfo> wifiData = dataService.getNearestWiFi(37.5665, 126.9780); // 예시로 서울의 위도와 경도를 사용

        request.setAttribute("wifiData", wifiData);
        request.getRequestDispatcher("/wifi.jsp").forward(request, response);
    }
}
