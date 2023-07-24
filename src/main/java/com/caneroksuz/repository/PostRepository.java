package com.caneroksuz.repository;

import com.caneroksuz.repository.entity.Post;
import com.caneroksuz.repository.entity.User;
import com.caneroksuz.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PostRepository implements ICrud<Post> {

    Session session;
    Transaction transaction;

    @Override
    public Post save(Post post) {
        try {
            session= HibernateUtility.getSessionFactory().openSession();
            System.out.println("Oturum açıldı..");
            transaction=session.beginTransaction();
            session.save(post);
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
        return post;
    }


    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public Post deleteById(Long id) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }


/*    -- optional hql sorgusu post repostiroyde
1 nuramalı postu atan kullanıcıyı getiren sorgu ==> join  */
    public Optional<User> findByUserPostId(Long postId){
        String hql = "SELECT u FROM User u JOIN Post p ON u.id = p.userId where p.id="+postId;
        session = HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class);

        User user = null;

        try {
            user = typedQuery.getSingleResult();
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return Optional.ofNullable(user);
    }

}
