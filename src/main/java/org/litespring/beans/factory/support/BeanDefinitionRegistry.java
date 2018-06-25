package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	BeanDefinition getBeanDefinition(String beanId);
	void registryBeanDefinition(String beanId,BeanDefinition bd);
}
