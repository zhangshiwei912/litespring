package org.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.litespring.util.Assert;

public class FileSystemResource implements Resource {
	
	private final String path;
	private final File file;
	
	public FileSystemResource(String path) {
		Assert.notNull(path,"path²»ÄÜÎª¿Õ");
		this.path=path;
		this.file=new File(path);
	}
	
	
	public InputStream getInputStream() throws Exception {
		return new FileInputStream(this.file);
	}

	public String getDescription() {
		return "fire ["+this.file.getAbsolutePath()+"]";
	}

}
