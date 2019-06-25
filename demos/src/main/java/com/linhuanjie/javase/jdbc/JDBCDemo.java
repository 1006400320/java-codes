package com.linhuanjie.javase.jdbc;

import java.sql.*;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-12-02 10:45
 * @email: lhuanjie@qq.com
 */
public class JDBCDemo {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // mysql 8.x 版本需要使用新的驱动
    static final String JDBC_DRIVER_MYSQL8XX = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
//            Class.forName(JDBC_DRIVER);
            Class.forName(JDBC_DRIVER_MYSQL8XX);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM actor";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("actor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date lastUpdate = rs.getDate("last_update");
                String lastUpdateStr = rs.getString("last_update");

                // 输出数据
                System.out.print("actor_id: " + id);
                System.out.print(", first_name: " + firstName);
                System.out.print(", last_name: " + lastName);
                System.out.print(", lastUpdate: " + lastUpdate);
                System.out.print(", lastUpdateStr: " + lastUpdateStr);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }


}
