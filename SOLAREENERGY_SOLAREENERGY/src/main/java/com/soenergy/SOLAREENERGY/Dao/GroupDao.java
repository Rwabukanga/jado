package com.soenergy.SOLAREENERGY.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soenergy.SOLAREENERGY.Domain.TheGroup;
import com.soenergy.SOLAREENERGY.Utility.Msg;



@Repository
public class GroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	public String create(TheGroup group) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			session.save(group);
			tx.commit();
			session.flush();
			return Msg.save;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return Msg.error;
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
	}

	public String update(TheGroup group) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			session.update(group);
			tx.commit();
			session.flush();
			return Msg.update;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return Msg.error;
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
	}

	public String delete(TheGroup group) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			group.setDeletedStatus(true);
			session.update(group);
			tx.commit();
			session.flush();
			return Msg.delete;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return Msg.error;
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<TheGroup> all() {
		Session session = sessionFactory.openSession();
		List<TheGroup> list = new ArrayList<>();
		try {

			list = session.createQuery("from TheGroup where deletedStatus=0").list();
		} catch (Exception e) {

			
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}
		return list;
	}

	public TheGroup findOne(long id) {

		Session session = sessionFactory.openSession();
		TheGroup group = new TheGroup();
		try {
			group = session.get(TheGroup.class, id);
		} catch (Exception e) {

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}

		return group;
	}
}
