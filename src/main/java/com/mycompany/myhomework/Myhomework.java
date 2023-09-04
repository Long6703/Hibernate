/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.myhomework;

import Ultis.HibernateConfig;
import entities.Department;
import entities.Mark;
import entities.Student;
import entities.Subject;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ubuntu
 */
public class Myhomework {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) HibernateConfig.getSessionFactory();
        Session session = (Session) sessionFactory.openSession();

        try {
            session = (Session) session.beginTransaction();
            for (int i = 0; i <= 4; i++) {
                Department department = new Department("D" + i + 1);
                session.save(department);

                for (int j = 0; j <= 2; j++) {
                    Student student = new Student();
                    student.setName("S" + i + 1);
                    student.setDepartment(department);
                    session.save(student);
                }

                for (int k = 0; k <= 2; k++) {
                    Subject subject = new Subject();
                    subject.setSubjectName("Subject " + (k + 1));
                    subject.setDepartment(department);
                    session.save(subject);
                }
            }

            List<Department> departments = session.createQuery("FROM Department").list();
            for (Department department : departments) {
                List<Student> students = department.getStudents();
                List<Subject> subjects = department.getSubjects();

                for (Student student : students) {
                    for (Subject subject : subjects) {
                        Mark gradeDetail = new Mark();
                        gradeDetail.setStudent(student);
                        gradeDetail.setSubject(subject);
                        gradeDetail.setScore(90);
                        session.save(gradeDetail);
                    }
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
            HibernateConfig.shutdown();
        }
        session.close();
        HibernateConfig.shutdown();
    }
}
