package com.soenergy.SOLAREENERGY.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soenergy.SOLAREENERGY.Domain.SystemUser;
import com.soenergy.SOLAREENERGY.Domain.UserActiveTyoe;
import com.soenergy.SOLAREENERGY.Utility.Msg;







@Repository
public class SystemUserDao {

	@Autowired
	private SessionFactory sessionFactory;


	public String create(SystemUser user) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
			user.setActive(UserActiveTyoe.ACTIVE); 	
			session.save(user);
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

	
	public String update(SystemUser user) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
			tx=session.beginTransaction();
			session.update(user);
			tx.commit();
			session.flush();
			return Msg.update;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return Msg.error;
		}finally {
			if (session != null && session.isOpen()) {
			session.clear();
			session.close();
			}
		}
	}


	public String delete(SystemUser user) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try {
			
			tx=session.beginTransaction();
			user.setDeletedStatus(true);
			session.update(user);
			tx.commit();
			session.flush();
			return Msg.delete;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return Msg.error;
		}finally {
			if (session != null && session.isOpen()) {
			session.clear();
			session.close();
			}
		}
	}


	@SuppressWarnings("unchecked")
	public List<SystemUser> all() {
		Session session = sessionFactory.openSession();
		List<SystemUser>list=new ArrayList<>();
		try{
		
		list = session.createQuery("from SystemUser where deletedStatus=0").list();
		}catch (Exception e) {

		}finally {
			if (session != null && session.isOpen()) {
			session.flush();	
			session.clear();
			session.close();
			}
		}
		return list;
	}

	
	public SystemUser findOne(long id) {
		
		Session session = sessionFactory.openSession();
		SystemUser user=new SystemUser(); 
		try{
		user = session.get(SystemUser.class, id);
		}catch (Exception e) {
			
		}finally {
			if (session != null && session.isOpen()) {
			session.flush();	
			session.clear();
			session.close();
			}
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<SystemUser> system_user_by_referenceName(String referenceName) {
		
		Session session = sessionFactory.openSession();
		List<SystemUser>list=new ArrayList<>();
		try{
		
		list =  session.createCriteria(SystemUser.class)
				.add(Restrictions.eq("referenceName", referenceName)).list(); 
		}catch (Exception e) {
			

		}finally {
			if (session != null && session.isOpen()) {
			session.flush();	
			session.clear();
			session.close();
			}
		}
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SystemUser> login(String applicationName, String username,String password){
		
		Session session = sessionFactory.openSession();
		List<SystemUser> list = new ArrayList<>();
		try {
			Query query = session.createQuery("from SystemUser where deletedStatus=0  AND username=:username AND password=:password AND applicationName=:applicationName");
			query.setParameter("username",username);
			query.setParameter("password",password);
			query.setParameter("applicationName",applicationName);
			list = query.list();		
			System.out.println("Login Called");
		} catch (Exception e) {
			//System.out.println("Not Called"+e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}
		return list;
	}
	
	
	
	public SystemUser find_user_by_username( String username){
		SystemUser user=new SystemUser();
		Session session = sessionFactory.openSession();
		//List<SystemUser> list = new ArrayList<>();
		try {
			Query query = session.createQuery("from SystemUser where deletedStatus=0 AND username=:username");
			query.setParameter("username",username);		
			user=(SystemUser)query.uniqueResult();
			
		} catch (Exception e) {

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}
		return user;
	}
	
	/*
	 * Find user by UUID
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public SystemUser find_user_by_uuid( String uuid){
		Session session = sessionFactory.openSession();
		SystemUser user=new SystemUser(); 
		try{
//		user = session.get(SystemUser.class, uuid);
			user = (SystemUser) session.createQuery("from SystemUser s where s.deletedStatus=0 AND s.uuid=:uuid").setParameter("uuid", uuid).uniqueResult();
		}catch (Exception e) {
			
		}finally {
			if (session != null && session.isOpen()) {
			session.flush();	
			session.clear();
			session.close();
			}
		}
		
		return user;
	}
	
	
	/*
	 * Find super admin in any application
	 * */
	
	@SuppressWarnings("unchecked")
	public List<SystemUser> super_admins(String applicationName){
		
		Session session = sessionFactory.openSession();
		List<SystemUser> list = new ArrayList<>();
		try {
			Query query = session.createQuery("from SystemUser where deletedStatus=0  AND role=:role  AND applicationName=:applicationName");
			query.setParameter("role","SuperAdmin");			
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


	@SuppressWarnings("unchecked")
	public List<SystemUser> adminsAndEntityManagers(String applicationName, String objectName, long objectId) {
		Session session = sessionFactory.openSession();
		List<SystemUser> list = new ArrayList<>();
		try {
			Query query = session.createQuery("from SystemUser where deletedStatus=0  AND applicationName=:applicationName AND objectName=:objectName AND objectId=:objectId");
			query.setParameter("objectName",objectName);			
			query.setParameter("applicationName",applicationName);
			query.setParameter("objectId", objectId);
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
	

	/**
	 * Block  The User
	 */
	public String blockUser(long objectId,boolean status){
       	Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(
					"update SystemUser set user_locked= :st where object_id= :id");
			
			query.setParameter("st",status );
			query.setParameter("id", objectId);
			query.executeUpdate();
			return "Active Status Changed";
		} catch (Exception e) {
			 System.out.println("Handle Lock The  User "+e.getMessage());
			 return "There is an Error ";
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}
	}
	
}
