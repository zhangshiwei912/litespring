import java.io.File;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.core.io.support.PackageResourceLoader;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

public class testIODemo {
	private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

	private final ClassLoader classLoader;

	public testIODemo() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}

	public testIODemo(ClassLoader classLoader) {
		Assert.notNull(classLoader, "ResourceLoader must not be null");
		this.classLoader = classLoader;
	}



	public ClassLoader getClassLoader() {
		return this.classLoader;
	}
	public void getResources(String basePackage) {
		Assert.notNull(basePackage, "basePackage  must not be null");
		String location=ClassUtils.convertClassNameToResourcePath(basePackage);
		ClassLoader cl=getClassLoader();
		URL url = cl.getResource(location);	
		File rootDir=new File(url.getFile());
	}
	
	
	
	
	
}
