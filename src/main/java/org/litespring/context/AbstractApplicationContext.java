package org.litespring.context;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
	
	DefaultBeanFactory beanFactory=null;
	private ClassLoader beanClassLoader;
	
	public AbstractApplicationContext(String configFile) {
		beanFactory=new DefaultBeanFactory();
		XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
		Resource resource=this.getResourceByPath(configFile);
		reader.loadBeanDefinitions(resource);
		beanFactory.setBeanClassLoader(this.getBeanClassLoader());
	}
	protected abstract Resource getResourceByPath(String configFile);
	
	public Object getBean(String beanId) {
		return beanFactory.getBean(beanId);
	}
	
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader=beanClassLoader;
	}
	
	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader!=null?beanClassLoader:ClassUtils.getDefaultClassLoader();
	}
}
