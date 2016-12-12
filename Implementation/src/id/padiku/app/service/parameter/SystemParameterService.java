package id.padiku.app.service.parameter;

import id.padiku.app.dao.parameter.IAppParameterDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemParameterService extends ParameterService {
	
	public SystemParameterService(){
		super();
	}
	
	public SystemParameterService(IAppParameterDAO parameterDAO){
    	super(parameterDAO);
    }
	
}
