package com.soenergy.SOLAREENERGY.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soenergy.SOLAREENERGY.Domain.AssignedPermission;
import com.soenergy.SOLAREENERGY.Utility.Msg;



@Repository
public class AssignedPermissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public String create(AssignedPermission assignedPermission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			session.save(assignedPermission);
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

	public String update(AssignedPermission assignedPermission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			session.update(assignedPermission);
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

	public String delete(AssignedPermission assignedPermission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			assignedPermission.setDeletedStatus(true);
			session.update(assignedPermission);
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
	public List<AssignedPermission> all() {
		Session session = sessionFactory.openSession();
		List<AssignedPermission> list = new ArrayList<>();
		try {

			list = session.createQuery("from AssignedPermission where deletedStatus=0").list();
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

	public AssignedPermission findOne(long id) {

		Session session = sessionFactory.openSession();
		AssignedPermission group = new AssignedPermission();
		try {
			group = session.get(AssignedPermission.class, id);
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
	
	@SuppressWarnings("unchecked")
	public List<AssignedPermission>user_permissions(String applicationName, long objectId,String objectName){
		
		Session session = sessionFactory.openSession();
		List<AssignedPermission> list = new ArrayList<>();
		try {
			Query query = session.createQuery("from AssignedPermission where deletedStatus=0 AND isGroup=0 AND objectId=:objectId AND objectName=:objectName AND applicationName=:applicationName");
			query.setParameter("objectId",objectId);
			query.setParameter("objectName",objectName);
			query.setParameter("applicationName",applicationName);
			list=query.list();			
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
	
}
