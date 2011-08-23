package com.processpuzzle.application.configuration.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HibernateFilter implements Filter {

//	private SessionFactory sf;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//		try {
//			System.out.println("---Starting Transaction---");
//			sf.getCurrentSession().beginTransaction();
//
//			chain.doFilter(request, response);
//
//			// Commit and cleanup
//			System.out.println("---Committing Transaction");
//			sf.getCurrentSession().getTransaction().commit();
//
//		} catch (StaleObjectStateException staleEx) {
//			throw staleEx;
//		} catch (Throwable ex) {
//			ex.printStackTrace();
//			try {
//				if (sf.getCurrentSession().getTransaction().isActive()) {
//					sf.getCurrentSession().getTransaction().rollback();
//				}
//			} catch (Throwable rbEx) {
//			}
//		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
//		sf = ((HibernatePersistenceProvider) ProcessPuzzleContext.getStrategy(HibernatePersistenceProvider.class.getName())).getSessionFactory();
	}

	public void destroy() {
	}

}
