package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;
import org.litespring.service.v1.PetStoreService;

public interface BeanFactory {

	Object getBean(String beanId);

}
