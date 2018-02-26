package com.gc.controller;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gc.model.Product;

/*
 * author: Andrew Calabro-Cavin
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/welcome")
	// this is listing all the data from teh product class
	public ModelAndView helloWorld() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml"); // can also put the hibernate xml file in a folder, just have to change this to "/folder_name/hibernate.cfg.xml 

		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Session session = sessionFactory.openSession(); // have to chain openSession because Session is an interface 
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(Product.class);
		ArrayList<Product> prodList = (ArrayList<Product>)crit.list(); 
		tx.commit();
		session.close();
		
		return new ModelAndView("welcome", "plist", prodList);
	}
}
