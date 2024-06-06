package com.mywifiproject;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataService {
    public void saveWiFiData(String xmlData) {
        String sql = "INSERT INTO wifi_info (name, address, latitude, longitude) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));
            doc.getDocumentElement().normalize();

            NodeList rowList = doc.getElementsByTagName("row");

            for (int i = 0; i < rowList.getLength(); i++) {
                NodeList childNodes = rowList.item(i).getChildNodes();
                String name = "";
                String address = "";
                double latitude = 0.0;
                double longitude = 0.0;

                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeName().equals("X_SWIFI_MGR_NO")) {
                        name = childNodes.item(j).getTextContent();
                    } else if (childNodes.item(j).getNodeName().equals("X_SWIFI_WRDOFC")) {
                        address = childNodes.item(j).getTextContent();
                    } else if (childNodes.item(j).getNodeName().equals("LAT")) {
                        latitude = Double.parseDouble(childNodes.item(j).getTextContent());
                    } else if (childNodes.item(j).getNodeName().equals("LNT")) {
                        longitude = Double.parseDouble(childNodes.item(j).getTextContent());
                    }
                }
                pstmt.setString(1, name);
                pstmt.setString(2, address);
                pstmt.setDouble(3, latitude);
                pstmt.setDouble(4, longitude);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
