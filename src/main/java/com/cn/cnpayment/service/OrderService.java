package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.OrderDal;
import com.cn.cnpayment.dal.OrderDalImpl;
import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Complete the OrderService class as mentioned below:
 * 
 * a. Autowire OrderDal
 * 
 * b. Complete the following methods:
 * 
 * 1. getOrderById(int id): This method fetches an Order for a specific Id.
 * 
 * 2. getAllOrders(): This method fetches the list of Orders.
 * 
 * 3. saveOrder(Orders newOrder): This method saves an Order.
 * 
 * 4. delete(int id): This method deletes an Order for a specific Id.
 **/

@Service
public class OrderService {

	// Autowire the OrderDal object.

	/**
	 * 1. Complete the method body for getOrderById() by adding proper arguments. 2.
	 * add proper annotation for registering this method as a Transaction
	 **/
	@Autowired
	OrderDal orderDalImpl;
	@Autowired
	PaymentDAL paymentDAl;

	@Transactional
	public Orders getOrderById(int id) {
		Orders order=orderDalImpl.getById(id);
		if (order == null)
			throw new NotFoundException("Id Not Found");
		return order;
	}

	/**
	 * 1. Complete the method body for getAllOrders(). 2. add proper annotation for
	 * registering this method as a Transaction
	 **/
	@Transactional
	public List<Orders> getAllOrders() {
		return orderDalImpl.getAllOrders();
	}

	/**
	 * 1. Complete the method body for saveOrder() method by adding proper
	 * arguments. 2. add proper annotation for registering this method as a
	 * Transaction
	 **/
	@Transactional
	public void saveOrder(Orders order) {
//		if (orderDalImpl.getById(order.getId()) != null)
//			throw new ElementAlreadyExistException("The element already exists");
		List<Payment> payments=new ArrayList<>();
		for(Payment payment:order.getPayments()) {
			Payment existingPayment=paymentDAl.getById(payment.getId());
			payments.add(existingPayment);
		}
		order.setPayments(payments);//new order creation is recommended for data integration
		orderDalImpl.save(order);
	}

	/**
	 * 1. Complete the method body for delete() method by adding proper arguments.
	 * 2. add proper annotation for registering this method as a Transaction
	 **/
	@Transactional
	public void delete(int id) {
//		if (orderDalImpl.getById(id) == null)
//			throw new NotFoundException("Id Not Found");
		orderDalImpl.delete(id);
	}

}
