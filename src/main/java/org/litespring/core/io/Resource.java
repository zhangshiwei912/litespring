package org.litespring.core.io;

import java.io.InputStream;

public interface Resource {
	public InputStream getInputStream() throws Exception;
	public String getDescription();
}
