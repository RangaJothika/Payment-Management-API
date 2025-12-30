package com.cn.cnpayment.dal;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cn.cnpayment.entity.Orders;

import jakarta.persistence.EntityManager;

/**
 * Complete the OrderDalImpl implementation class as mentioned below:
 * 
 * a. Autowire EntityManager.
 * 
 * b. Override the following methods:
 * 
 * 1. getById(int id): This method fetches an Order from the database for a
 * specific Id.
 * 
 * 2. save(Orders order): This method saves an Order into the database.
 * 
 * 3. delete(int id): This method deletes an Order from the database for a
 * specific Id.
 * 
 * 4. getAllOrders(): This method fetches the list of Orders from the database.
 **/

@Repository
public class OrderDalImpl implements OrderDal {
	@Autowired
	EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public Orders getById(int id) {
		// TODO Auto-generated method stub
		return getSession().get(Orders.class, id);
	}

	@Override
	public void save(Orders orders) {
		// TODO Auto-generated method stub
		getSession().save(orders);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Orders order = getById(id);
		getSession().delete(order);
	}

	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return getSession().createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
	}
	// Autowire the EntityManager object.
}
