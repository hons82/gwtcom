package org.gwtcom.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class EMF implements FactoryBean<EntityManager>, ApplicationContextAware, BeanNameAware {

	private AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor;
	private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
	private String _name;

	public EMF() {
	}

	@Override
	public EntityManager getObject() throws Exception {
		EntityManager manager = getEntityManager();
		annotationBeanPostProcessor.postProcessAfterInstantiation(manager, _name);
		return manager;
	}

	@Override
	public Class<EntityManager> getObjectType() {
		return EntityManager.class;
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
		annotationBeanPostProcessor = applicationContext
				.getBeansOfType(AutowiredAnnotationBeanPostProcessor.class).values().iterator().next();
	}

	public EntityManager getEntityManager() {
		return emfInstance.createEntityManager();
	}
}