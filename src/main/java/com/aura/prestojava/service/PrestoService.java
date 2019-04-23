package com.aura.prestojava.service;

import com.aura.prestojava.bean.AgeQueryVO;
import com.aura.prestojava.bean.BrandQueryVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestoService {

    @Value("${presto.url}")
    private String prestoUrl;

    private static String BRAND_PRICE_QUERY="select brand,sum(price) as totalPrice from record join brand_dimension on record.bid=brand_dimension.bid group by brand_dimension.brand order by totalPrice desc limit 10";

    private static String AGE_PRICE_QUERY="select cast((year(CURRENT_DATE)-year(birth)) as integer) as age,sum(price) as totalPrice from record join user_dimension on record.uid=user_dimension.uid group by cast((year(CURRENT_DATE)-year(birth)) as integer) order by totalPrice desc";


    public List<BrandQueryVO> selectBrandPrice(){
        Connection connection = null;
        Statement statement = null;
        try {
            // 获得连接
            connection = DriverManager.getConnection(prestoUrl, "liu", null);
            statement = connection.createStatement();

            //执行语句返回结果
            ResultSet resultSet = statement.executeQuery(BRAND_PRICE_QUERY);

            List<BrandQueryVO> list = new ArrayList<>();
            // 遍历结果集
            while (resultSet.next()){
                BrandQueryVO vo = new BrandQueryVO();
                vo.setBrand(resultSet.getString("brand"));
                vo.setTotalPrice(resultSet.getLong("totalPrice"));
                list.add(vo);
                System.out.println(vo.toString());
            }

            resultSet.close();
            return list;
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


    public List<AgeQueryVO> selectAgePrice(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(prestoUrl, "liu", null);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(AGE_PRICE_QUERY);

            List<AgeQueryVO> list = new ArrayList<>();
            while (resultSet.next()){
                AgeQueryVO vo = new AgeQueryVO();
                vo.setAge(resultSet.getInt("age"));
                vo.setTotalPrice(resultSet.getLong("totalPrice"));
                list.add(vo);
                System.out.println(vo.toString());
            }

            resultSet.close();
            return list;
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
