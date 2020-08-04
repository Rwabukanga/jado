package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.Solar.SolarEnergy.Domain.Solarenergy;


@Repository
@Transactional
public class solarEnergyDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public void createsolarenergy(Solarenergy en) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(en);
		session.getTransaction().commit();
		session.close();
		
	}
	public void updatesolarenergy(Solarenergy en) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.update(en);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(Solarenergy en) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(en);
		session.getTransaction().commit();
		session.close();
	}
	
	public Solarenergy findByid(int id){
		
		Session session=sessionfactory.openSession();
		Solarenergy d= (Solarenergy) session.get(Solarenergy.class, id);
		session.close();
		return d;
		
	}
	
	public List<Solarenergy> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Solarenergy> list = t.list();
		session.close();
		return list;
	}
	
	public Solarenergy findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Solarenergy energy=null;
		try{

			energy =(Solarenergy) session.createQuery("from Solarenergy c where c.uuid= :u")
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
		return energy;
	}

	
}
