package com.mywifiproject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));

        DataService dataService = new DataService();
        dataService.saveLocationHistory(latitude, longitude);
        List<WiFiInfo> wifiData = dataService.getNearestWiFi(latitude, longitude);
        List<LocationHistory> historyData = dataService.getLocationHistory();

        request.setAttribute("wifiData", wifiData);
        request.setAttribute("historyData", historyData);
        request.getRequestDispatcher("/location.jsp").forward(request, response);
    }
}
