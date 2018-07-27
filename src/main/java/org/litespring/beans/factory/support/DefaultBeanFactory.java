package org.litespring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.exception.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
		implements ConfigurableBeanFactory ,BeanDefinitionRegistry {
	
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
	private ClassLoader beanClassLoader;
	
	
	public DefaultBeanFactory() {
	}
	public DefaultBeanFactory(String configFile) {
	}
	
	public void registerBeanDefinition(String beanId, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanId, bd);
	}

	public BeanDefinition getBeanDefinition(String beanId) {
		return this.beanDefinitionMap.get(beanId);
	}

	public Object getBean(String beanId) {
		BeanDefinition bd=this.getBeanDefinition(beanId);
		if(bd==null)
			return null;
		if(bd.isSingleton()) {
			Object bean=this.getSingleton(beanId);
			if(bean==null) {
				bean=createBean(bd);
				this.registrySingletonBean(beanId, bean);
			}
			return bean;
		}
		return createBean(bd);
	}
	private Object createBean(BeanDefinition bd) {
		// ����ʵ��
		Object bean = instantiateBean(bd);
		// ��������
		populateBean(bd,bean);
		
		return bean;
	}
	
	private Object instantiateBean(BeanDefinition bd) {
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName=bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("�����bean");
		}
	}
	
	private void populateBean(BeanDefinition bd, Object bean) {
		List<PropertyValue> pvs=bd.getPropertyValues();
		if(pvs==null||pvs.isEmpty()) {
			return ;
		}
		BeanDefinitionValueResolver valueResolver=new BeanDefinitionValueResolver(this);
		SimpleTypeConverter converter = new SimpleTypeConverter(); 
		try {
			for (PropertyValue pv : pvs){
				String propertyName = pv.getName();
				Object originalValue = pv.getValue();
				Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
				
				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if(pd.getName().equals(propertyName)){
						Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
						pd.getWriteMethod().invoke(bean, convertedValue);
						break;
					}
				}
 
				
			}
		}catch(Exception ex) {
			throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getBeanClassName() + "]", ex);
		}
	}
	
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader=beanClassLoader;
	}
	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader!=null?beanClassLoader:ClassUtils.getDefaultClassLoader();
	}
	 public Object resolveDependency(DependencyDescriptor descriptor) {
			
			Class<?> typeToMatch = descriptor.getDependencyType();
			for(BeanDefinition bd: this.beanDefinitionMap.values()){		
				//ȷ��BeanDefinition ��Class����
				resolveBeanClass(bd);
				Class<?> beanClass = bd.getBeanClass();			
				if(typeToMatch.isAssignableFrom(beanClass)){
					return this.getBean(bd.getID());
				}
			}
			return null;
		}
	    public void resolveBeanClass(BeanDefinition bd) {
			if(bd.hasBeanClass()){
				return;
			} else{
				try {
					bd.resolveBeanClass(this.getBeanClassLoader());
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("can't load class:"+bd.getBeanClassName());
				}
			}
		}

	

}
