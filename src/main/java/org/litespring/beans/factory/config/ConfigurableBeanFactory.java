package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
	void setBeanClassLoader(ClassLoader beanClassLoader);
	ClassLoader getBeanClassLoader();

}
