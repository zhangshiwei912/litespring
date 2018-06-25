package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.util.Assert;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String,Object> singletonObjects=new ConcurrentHashMap<String, Object>(64);
	
	public void registrySingletonBean(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "beanName 不能为空");
		Object oldObject = this.singletonObjects.get(beanName);
		if(oldObject!=null) {
			throw new IllegalStateException(beanName+"已经存在");
		}
		this.singletonObjects.put(beanName, singletonObject);
	}

	public Object getSingleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}

}
