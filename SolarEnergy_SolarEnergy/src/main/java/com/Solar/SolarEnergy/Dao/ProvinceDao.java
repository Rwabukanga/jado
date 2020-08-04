package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.Province;


@Repository
@Transactional
public class ProvinceDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public void createprovince(Province pr) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(pr);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void delete(Province pr) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(pr);
		session.getTransaction().commit();
		session.close();
	}
	
	public Province findByid(int id){
		
		Session session=sessionfactory.openSession();
		Province d= (Province) session.get(Province.class, id);
		session.close();
		return d;
		
	}
	
	public List<Province> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Province> list = t.list();
		session.close();
		return list;
	}
}
