package com.caneroksuz.repository;

import com.caneroksuz.repository.entity.Name;
import com.caneroksuz.repository.entity.User;
import com.caneroksuz.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
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
        //String sql="select * from tbl_user";
        String hql="select u from User as u"; // hql ile nesne üzerinden çalışıyoruz..
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery =session.createQuery(hql, User.class);
        List<User> userList = typedQuery.getResultList();

        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {

        String hql = "SELECT u FROM User as u WHERE u.id =:id";
        session =HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("id",id);
        User user =null;

        try {
            user=typedQuery.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return Optional.ofNullable(user);
    }


    public Optional<User> findById2(Long id) {


        session =HibernateUtility.getSessionFactory().openSession();

        User user =session.find(User.class,id);

        if (user==null){
            System.out.println("Kullanıcı bulunamadı");
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findByUsername(String username){
        String hql="select u from User as u where u.username=:myusername";
        session=HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery=session.createQuery(hql, User.class);
        typedQuery.setParameter("myusername",username);
//        Query query=session.createQuery(hql);
//        query.setParameter("myusername",username);
//        User user2= (User) query.getSingleResult();
//        System.out.println("query==>"+user2);
//        System.out.println("typed quert==>"+user1);

        // getSingleResult bulamadığı zaman exception fırlatır...
        User user=null;

        try {
            user=typedQuery.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return  Optional.ofNullable(user);
    }

    public List<Name> findAllName() {
        String hql="select u.name from User as u"; // hql ile nesne üzerinden çalışıyoruz..
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Name> typedQuery =session.createQuery(hql, Name.class);
        List<Name> nameList = typedQuery.getResultList();

        return nameList;
    }

    public List<String> findAllFirstname() {
        String hql="select u.name.firstName from User as u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<String> typedQuery =session.createQuery(hql, String.class);
        List<String> firstnameList = typedQuery.getResultList();

        return firstnameList;
    }

    public List<User> findAllFirstNameStartWith(String value){
        String hql = "SELECT u from User AS u WHERE u.name.firstName LIKE :x";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("x",value+"%");
        List<User> userList = typedQuery.getResultList();

        return userList;
    }

    public List<User> findAllFirstNameStartWithAndGtPostCount(String value, int postCount){
        String hql = "SELECT u from User AS u WHERE u.name.firstName LIKE :x AND u.postCount>:y";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setParameter("x",value+"%");
        typedQuery.setParameter("y", postCount);
        List<User> userList = typedQuery.getResultList();

        return userList;
    }

    public Long sumPostCount(){
        String hql = "SELECT sum(postCount) from User";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Long> typedQuery = session.createQuery(hql, Long.class);
        return  typedQuery.getSingleResult();
    }

    public Double avgPostCount(){
        String hql = "SELECT avg(postCount) from User";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Double> typedQuery = session.createQuery(hql, Double.class);
        return  typedQuery.getSingleResult();
    }

    public List<Object[]> groupByPostCount(){
        String hql ="SELECT postCount,count(u.postCount) FROM User u GROUP BY postCount";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Object[]> typedQuery = session.createQuery(hql,Object[].class);
        return typedQuery.getResultList();
    }

    // en cok post atan kullanıcıyı bulalım
    public Optional<User> mostPostingUser(){
        String hql ="SELECT u FROM User u where postCount = (select max(postCount) FROM User)";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        User user = null;
        List<User> list = typedQuery.getResultList();

        if (!list.isEmpty()){
            user =list.get(0);
        }

        return Optional.ofNullable(user);
    }

    // en cok post atan kullanıcıyı bulalım
    public Optional<User> mostPostingUser2(){
        String hql ="SELECT u FROM User u order by postCount desc";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
        typedQuery.setMaxResults(1);
        User user = null;

        try {
            user = typedQuery.getSingleResult();

        }catch (Exception e){
            System.out.println(e.toString());
        }

        return Optional.ofNullable(user);
    }

   // Butunkullanıcıların username gender ve postcount nu donen sorgu
    public List<Object[]> getUsernameGenderPostCount(){
        String hql="SELECT u.username, u.gender, u.postCount from User u";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Object[]> typedQuery = session.createQuery(hql,Object[].class);
        List<Object[]> list = typedQuery.getResultList();
        if(list.isEmpty()){
            System.out.println("Liste boş");
        }
        return list;
    }

    //her cınsıyette ki kullanıclar ve toplam attıkları post sayısı
    public List<Object[]> getUserGendersWithTotalPost(){
        String hql = "SELECT gender, count(u),sum(postCount) FROM User u group by gender";
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery typedQuery = session.createQuery(hql,Object[].class);
        session.close();
        return typedQuery.getResultList();
    }


    /*
// en cok post atan kullanıcıyı bulalım
Butunkullanıcıların username gender ve postcount nu donen sorgu
her cınsıyette ki kullanıclar ve toplam attıkları post sayısı
-- optional hql sorgusu post repostiroyde
1 nuramalı postu atan kullanıcıyı getiren sorgu ==> join

*/

}
