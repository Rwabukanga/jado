package com.Solar.SolarEnergy.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.Registrar;

@Repository
@Transactional
public class RegistrarDao {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	public void createRegistrar(Registrar reg) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(reg);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void delete(Registrar reg) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(reg);
		session.getTransaction().commit();
		session.close();
	}
	
	public Registrar findByid(int id){
		
		Session session=sessionfactory.openSession();
		Registrar d= (Registrar) session.get(Registrar.class, id);
		session.close();
		return d;
		
	}
	
	public List<Registrar> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Registrar> list = t.list();
		session.close();
		return list;
	}
	
	public Registrar findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Registrar registrar=null;
		try{

			registrar =(Registrar) session.createQuery("from Registrar c where c.uuid= :u")
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
		return registrar;
	}


}
