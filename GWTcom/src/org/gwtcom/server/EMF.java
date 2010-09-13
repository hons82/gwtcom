package org.gwtcom.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class EMF implements FactoryBean<PersistenceManager>, ApplicationContextAware, BeanNameAware {

	private AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor;
	private static final PersistenceManagerFactory emfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	private String _name;

	public EMF() {
	}

	@Override
	public PersistenceManager getObject() throws Exception {
		PersistenceManager manager = getPersistenceManager();
		annotationBeanPostProcessor.postProcessAfterInstantiation(manager, _name);
		return manager;
	}

	@Override
	public Class<PersistenceManager> getObjectType() {
		return PersistenceManager.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void setBeanName(String name) {
		_name = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		annotationBeanPostProcessor = (AutowiredAnnotationBeanPostProcessor) applicationContext
				.getBeansOfType(AutowiredAnnotationBeanPostProcessor.class).values().iterator().next();
	}

	public PersistenceManager getPersistenceManager() {
		return emfInstance.getPersistenceManager();
	}
}