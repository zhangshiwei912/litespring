package org.litespring.test.v4;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.ConstructorArgument.ValueHolder;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;

public class PackageResourceLoaderTest {
	
	@Test
	public void testPackageResourceLoader() throws Exception {
		PackageResourceLoader loader=new PackageResourceLoader();
		Resource[] resources=loader.getResources("org.litespring.dao.v4");
		Assert.assertEquals(2, resources.length);
		
		
	}

}
