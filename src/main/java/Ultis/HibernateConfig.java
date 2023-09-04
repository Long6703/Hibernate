/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultis;

import entities.Department;
import entities.Mark;
import entities.Student;
import entities.Subject;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 *
 * @author ubuntu
 */
public class HibernateConfig {
    private static SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/homework");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "6703");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.FORMAT_SQL, "true");
            configuration.setProperties(properties);
            configuration = configuration.addAnnotatedClass(Mark.class);
            configuration = configuration.addAnnotatedClass(Department.class);
            configuration = configuration.addAnnotatedClass(Student.class);
            configuration = configuration.addAnnotatedClass(Subject.class);
            
            sessionFactory = (SessionFactory) configuration.buildSessionFactory();
        } catch (HibernateException e) {
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown(){
        getSessionFactory().close();
    }
}
