package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.Registrar;


@Repository
@Transactional
public class BranchDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public void createRegistrar(Branch branch) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(branch);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void delete(Branch branch) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(branch);
		session.getTransaction().commit();
		session.close();
	}
	
	public void Update(Branch branch) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.update(branch);
		session.getTransaction().commit();
		session.close();
	}
	
	public Branch findByid(int id){
		
		Session session=sessionfactory.openSession();
		Branch d= (Branch) session.get(Branch.class, id);
		session.close();
		return d;
		
	}
	
	
	public Branch findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Branch branch=null;
		try{

			branch =(Branch) session.createQuery("from Branch c where c.uuid= :u")
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

	
	public List<Branch> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Branch> list = t.list();
		session.close();
		return list;
	}
}
