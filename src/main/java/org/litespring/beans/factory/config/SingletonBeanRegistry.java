package org.litespring.beans.factory.config;

public interface SingletonBeanRegistry {
	void registrySingletonBean(String beanId,Object singletonObject);
	Object getSingleton(String beanId);
}
