package org.gwtcom.server;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * {@link RemoteServiceServlet} that automatically injects IoC dependency.
 * "org.springframework.beans.factory.annotation.Autowired" annotation is used
 * for marking which fields to inject into. </p> Note that the current
 * implementation will only inject "declared" fields, and not inherited fields.
 * Fields can be private, protected, package or public.
 * 
 * @author See Wah Cheng
 * @created 27 Jun 2008
 */
@SuppressWarnings("serial")
public class DependencyInjectionRemoteServiceServlet extends
		RemoteServiceServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		doDependencyInjection();
	}

	/**
	 * Carries out dependency injection. This implementation uses Spring IoC
	 * container.
	 * 
	 * @exception NoSuchBeanDefinitionException
	 *                if a suitable bean cannot be found in the Spring
	 *                application context. The current implementation looks up
	 *                beans by name
	 */
	protected void doDependencyInjection() {
		for (Field field : getFieldsToDependencyInject()) {
			try {
				boolean isFieldAccessible = field.isAccessible();
				if (!isFieldAccessible) {
					field.setAccessible(true);
				}
				field.set(this, WebApplicationContextUtils
						.getWebApplicationContext(getServletContext()).getBean(
								field.getName()));
				if (!isFieldAccessible) {
					field.setAccessible(false);
				}
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Find annotated fields to inject.
	 * 
	 * @return a list of all the annotated fields
	 */
	private Set<Field> getFieldsToDependencyInject() {
		Set<Field> fieldsToInject = new HashSet<Field>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Autowired.class) != null) {
				fieldsToInject.add(field);
			}
		}
		return fieldsToInject;
	}
}