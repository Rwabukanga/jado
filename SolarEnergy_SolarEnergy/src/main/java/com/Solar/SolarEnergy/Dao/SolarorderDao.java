package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.Solarorder;



@Repository
@Transactional
public class SolarorderDao {

	
	@Autowired
	private SessionFactory sessionfactory;
	
	public void createorder(Solarorder order) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void updateorder(Solarorder order) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void deleteorder(int id) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(id);
		session.getTransaction().commit();
		session.close();
	}
	
	public Solarorder findByid(int id){
		
		Session session=sessionfactory.openSession();
		Solarorder d=(Solarorder) session.get(Solarorder.class, id);
		session.close();
		return d;
		
	}
	
	
	public Solarorder findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Solarorder order=null;
		try{

			order =(Solarorder) session.createQuery("from Solarorder c where c.uuid= :u")
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
		return order;
	}

	
	
	public List<Solarorder> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Solarorder> list = t.list();
		session.close();
		return list;
	}
	
	public List<Solarorder> findAllbyRegistrar(long id){
		Session session=sessionfactory.openSession();
		List<Solarorder>orders=null;
		orders=session.createQuery("from Solarorder c where registrar_id=:id ").setParameter("id", id).list();
		
		session.close();
		return orders;
	}
	
	public Solarorder findByusername(String username){
		Session session = sessionfactory.openSession();
		Solarorder order=null;
		try{

			order =(Solarorder) session.createQuery("from Solarorder c where c.username= :u")
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
		return order;
	}
}
