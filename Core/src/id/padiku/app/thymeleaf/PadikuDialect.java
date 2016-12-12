package id.padiku.app.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class PadikuDialect extends AbstractDialect {
	
	public PadikuDialect() {
		super();
	}

	@Override
	public String getPrefix() {
		return "ifs";
	}

	@Override
	  public Set<IProcessor> getProcessors() {
	    final Set<IProcessor> processors = new HashSet<IProcessor>();
	    processors.add(new RoleCheckerProcessor());
	    processors.add(new RoleInCheckerProcessor());
	    processors.add(new RoleNotInCheckerProcessor());
	    return processors;
	  }
}
