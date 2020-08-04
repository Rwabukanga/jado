package com.Solar.SolarEnergy.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.Solar.SolarEnergy.Domain.Payment;



@Repository
@Transactional
public class Paymentdao {

	@Autowired
	private SessionFactory sessionfactory;
	
	
    public void createpayment(Payment payment) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(payment);
		session.getTransaction().commit();
		session.close();
		
	}
    
 public void updatepayment(Payment payment) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.update(payment);
		session.getTransaction().commit();
		session.close();
		
	}
	
 public void deletepayment(Payment payment) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(payment);
		session.getTransaction().commit();
		session.close();
		
	}
    
 public Payment findByid(int id){
		
		Session session=sessionfactory.openSession();
		Payment d=  session.get(Payment.class, id);
		session.close();
		return d;
		
	}
 
 public Payment findByUuid(String uuid){
		Session session = sessionfactory.openSession();
		Payment payment=null;
		try{

			payment =(Payment) session.createQuery("from Payment c where c.uuid= :u")
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
		return payment;
	}
 public List<Payment> findAll(Class c){
		Session session=sessionfactory.openSession();
		Query t=session.createQuery("from "+c.getName()+" c");
		List<Payment> list = t.list();
		session.close();
		return list;
	}
 
 public Payment findAllbyPayment(long id){
		Session session=sessionfactory.openSession();
		Payment pay=null;
		pay =(Payment) session.createQuery("from Payment c where contract_id=:id ").setParameter("id", id).uniqueResult();
		
		session.close();
		return pay;
	}
}
 

