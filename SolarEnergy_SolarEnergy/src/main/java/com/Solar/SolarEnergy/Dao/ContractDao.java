package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Solar.SolarEnergy.Domain.Contract;

@Repository
@Transactional
public class ContractDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public void createcontract(Contract cont) {
		Session ss=sessionfactory.openSession();
		ss.beginTransaction();
		ss.save(cont);
		ss.getTransaction().commit();
		ss.close();
	}
	
	public void deletecontract(int id) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(id);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updatecontract(Contract contract) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.update(contract);
		session.getTransaction().commit();
		session.close();
	}
	
	public Contract findByid(int id){
		
		Session session=sessionfactory.openSession();
		Contract d= (Contract) session.get(Contract.class, id);
		session.close();
		return d;
		
	}
	
	
	public Contract findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Contract contract=null;
		try{

			contract =(Contract) session.createQuery("from Contract c where c.uuid= :u")
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
		return contract;
	}

	
	public List<Contract> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Contract> list = t.list();
		session.close();
		return list;
	}
	public Contract findAllbyContract(long id){
		Session session=sessionfactory.openSession();
		Contract order=null;
		order =(Contract) session.createQuery("from Contract c where order_orderid=:id ").setParameter("id", id).uniqueResult();
		
		session.close();
		return order;
	}
}
