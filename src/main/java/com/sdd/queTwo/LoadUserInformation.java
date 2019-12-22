package com.sdd.queTwo;


import com.sdd.queTwo.baseAnno.Column;
import com.sdd.queTwo.baseAnno.Table;
import com.sdd.queTwo.domain.User;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoadUserInformation {


    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/saddad", "root", "root");
//here saddad is database name, root is username and password
            Statement stmt = con.createStatement();

            String query = "";
            String tableName = "";

            Annotation[] annotations = User.class.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Table.class)) {
                    tableName = ((Table) annotation).name();
                } else if (annotation.annotationType().equals(Column.class)) {
                    if (query.isEmpty()) {
                        query += ", ";
                    }
                    query += ((Column) annotation).name();
                }
            }

            query = "select " + query  + " from " +tableName;

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
