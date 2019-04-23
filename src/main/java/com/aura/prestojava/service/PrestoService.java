package com.aura.prestojava.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class PrestoService {

    @Value("${presto.url}")
    private String prestoUrl;

    private static String BRAND_PRICE_QUERY="select brand,sum(price) as totalPrice from record join brand_dimension on record.bid=brand_dimension.bid group by brand_dimension.brand order by totalPrice desc limit 10";

    private static String AGE_PRICE_QUERY="select cast((year(CURRENT_DATE)-year(birth)) as integer) as age,sum(price) as totalPrice from record join user_dimension on record.uid=user_dimension.uid group by cast((year(CURRENT_DATE)-year(birth)) as integer) order by totalPrice desc";


    public String selectBrandPrice(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(prestoUrl, "liu", null);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(BRAND_PRICE_QUERY);

            while (resultSet.next()){
                System.out.println(resultSet);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (!statement.isClosed()){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
