package com.caneroksuz.repository;

import com.caneroksuz.repository.entity.User;
import com.caneroksuz.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserRepository implements ICrud <User> {

    Session session;
    Transaction transaction;


    @Override
    public User save(User user) {

        try {
            session=HibernateUtility.getSessionFactory().openSession();
            System.out.println("Oturum açıldı..");
            transaction=session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Kayıt başarılı..");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();;
            System.out.println("Kayıt başarısız..");
        }finally {
            System.out.println("Oturum kapatılıyor");
            session.close();
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }
}
