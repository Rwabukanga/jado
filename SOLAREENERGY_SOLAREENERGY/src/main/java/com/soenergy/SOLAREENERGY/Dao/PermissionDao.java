package com.soenergy.SOLAREENERGY.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soenergy.SOLAREENERGY.Domain.Permission;
import com.soenergy.SOLAREENERGY.Utility.Msg;


@Repository
public class PermissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public String create(Permission permission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			session.save(permission);
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

	public String update(Permission permission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			session.update(permission);
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

	public String delete(Permission permission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			permission.setDeletedStatus(true);
			session.update(permission);
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
	public List<Permission> all() {
		Session session = sessionFactory.openSession();
		List<Permission> list = new ArrayList<>();
		try {

			list = session.createQuery("from Permission where deletedStatus=0").list();
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

	public Permission findOne(long id) {

		Session session = sessionFactory.openSession();
		Permission permission = new Permission();
		try {
			permission = session.get(Permission.class, id);
		} catch (Exception e) {

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}

		return permission;
	}
	
	
	
}
