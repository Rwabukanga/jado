package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.Branchuser;

@Repository
@Transactional
public class BranchUserDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public void createBranchUSERR(Branchuser branch) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(branch);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void delete(Branchuser branch) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(branch);
		session.getTransaction().commit();
		session.close();
	}
	
	public Branchuser findByid(int id){
		
		Session session=sessionfactory.openSession();
		Branchuser d=(Branchuser) session.get(Branchuser.class, id);
		session.close();
		return d;
		
	}
	
	
	public Branchuser findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Branchuser branch=null;
		try{

			branch =(Branchuser) session.createQuery("from Branchuser c where c.uuid= :u")
					.setParameter("u",uuid)
					.uniqueResult();

		} catch (Exception e) {


		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}
		return branch;
	}

	
	
	public List<Branchuser> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Branchuser> list = t.list();
		session.close();
		return list;
	}
	
	public Branchuser findByusername(String username){
		Session session = sessionfactory.openSession();
		Branchuser branch=null;
		try{

			branch =(Branchuser) session.createQuery("from Branchuser c where c.username= :u")
					.setParameter("u",username)
					.uniqueResult();

		} catch (Exception e) {


		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.clear();
				session.close();
			}
		}
		return branch;
	}
}
