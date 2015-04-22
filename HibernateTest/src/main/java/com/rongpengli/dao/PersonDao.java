package com.rongpengli.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.rongpengli.model.Person;

public class PersonDao {
	private static SessionFactory mSessionFactory = null;

	public static void main(String[] args) {
		createSessionFactory();
		// savePerson();

		List<Person> lPersons = listPersons();
		System.out.println(lPersons.size() + ", " + lPersons.get(4).getName());

		System.exit(0);
	}

	private static SessionFactory createSessionFactory() {
		Configuration lCfg = new Configuration()
				.configure("config\\hibernate.cfg.xml");
		System.out.println(lCfg.getProperties());

		StandardServiceRegistry lBuildRegistry = new StandardServiceRegistryBuilder()
				.applySettings(lCfg.getProperties()).build();
		mSessionFactory = lCfg.buildSessionFactory(lBuildRegistry);
		return mSessionFactory;
	}

	private static void savePerson() {
		Session lSession = mSessionFactory.openSession();
		lSession.beginTransaction();

		Person lPersopn = new Person();
		lPersopn.setAge(20);
		lPersopn.setName("lzl");

		lSession.save(lPersopn);
		lSession.getTransaction().commit();

		lSession.close();
	}

	@SuppressWarnings("unchecked")
	private static List<Person> listPersons() {
		Session lSession = mSessionFactory.openSession();
		lSession.beginTransaction();
		List<Person> lResult = lSession.createQuery(" from Person").list();
		lSession.getTransaction().commit();
		lSession.close();
		return lResult;
	}
}
