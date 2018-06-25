package org.litespring.context.support;

import org.litespring.context.AbstractApplicationContext;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext {
	
	public FileSystemApplicationContext(String configFile) {
		super(configFile);
	}

	@Override
	protected Resource getResourceByPath(String configFile) {
		return new FileSystemResource(configFile);
	}
	


}
