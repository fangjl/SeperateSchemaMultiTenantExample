package com.wavemaker.mt.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.wavemaker.mt.domain.User;


/**
 * @author gauravs
 *
 */
@Service
public class WMMultitenantService {

    private SessionFactory sessionFactory;


    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(Long id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @SuppressWarnings("unchecked")
	public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
