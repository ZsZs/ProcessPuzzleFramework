package com.processpuzzle.util.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author avincze
 * @uml.annotations
 * derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_VUlm4D5fEdq3NJ6sg2oavA"
 */

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration()
			// .setProperty("hibernate.connection.datasource","adi_db")
				.setProperty("hibernate.connection.driver_class","net.sourceforge.jtds.jdbc.Driver")
				.setProperty("hibernate.connection.url","jdbc:jtds:sqlserver://localhost/ADI")
				.setProperty("hibernate.connection.username", "adiuser")
				.setProperty("hibernate.connection.password", "adiuser")
				.setProperty("hibernate.dialect","org.hibernate.dialect.SQLServerDialect")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.transaction.factory_class","org.hibernate.transaction.JDBCTransactionFactory")
//				.setProperty("hibernate.hbm2ddl.auto", "create")  // recreate the database!!!
//				.addClass(com.itcodex.adi.order_management.domain.ModuleOrderState.class)
//				.addClass(com.itcodex.adi.order_management.domain.SupportedSystem.class)
//				.addClass(com.itcodex.adi.contact_management.domain.Company.class)								
//				.addClass(com.itcodex.adi.order_management.domain.OTPSystem.class)
//				.addClass(com.itcodex.adi.contact_management.domain.Person.class)
//                .addClass(com.itcodex.adi.order_management.domain.ModuleOrderPriority.class)
//				.addClass(com.itcodex.adi.order_management.domain.ModuleOrder.class)
//				.addClass(com.itcodex.adi.document_management.domain.DocFile.class)
//				.addClass(com.itcodex.adi.order_management.domain.PCR.class)
//				.addClass(com.itcodex.objectpuzzle.project_planning.domain.resourcetype.Role.class)
//				.addClass(com.itcodex.adi.system_administration.domain.Employee.class)
//				.addClass(com.itcodex.adi.project_administration.domain.PcrNumber.class)
//				.addResource("com/itcodex/adi/order_management/domain/ModuleOrder.hbm.xml")
//				.addResource("com/itcodex/adi/project_administration/domain/PcrNumber.hbm.xml")
			.buildSessionFactory();
		} catch (Throwable ex) {
			//System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static final ThreadLocal session = new ThreadLocal();

	public static Session currentSession() {
		Session s = (Session) session.get();
		// Open a new Session, if this Thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
			// Make sure you log the exception, as it might be swallowed
		}
		return s;
	}

	public static void closeSession() {
		Session s = (Session) session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}