package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.District;

@Repository
@Transactional
public class DistrictDao {

	
	@Autowired
	private SessionFactory sessionfactory;
	
	public void createDistrict(District pr) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(pr);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void delete(District pr) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(pr);
		session.getTransaction().commit();
		session.close();
	}
	
	public District findByid(int id){
		
		Session session=sessionfactory.openSession();
		District d= (District) session.get(District.class, id);
		session.close();
		return d;
		
	}
	
	public List<District> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<District> list = t.list();
		session.close();
		return list;
	}
	
}
