package cb.lms.CB_Lms.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cb.lms.CB_Lms.dao.ICommonEntityDao;
import cb.lms.CB_Lms.modal.IDataEntity;



/**
 * 
 * @author 1595812
 *
 */

@Service
@Transactional
public class CommonService implements ICommonService{
	
   @Autowired
   private ICommonEntityDao dao;	

	@Override
	public Set<IDataEntity> findAll(Class entity, String[] aliases, String orderBy, Boolean isCacheable) {

		return dao.findAll(entity, aliases, orderBy, isCacheable);
	}

	@Override
	public IDataEntity findEntity(Class clazz, String propName, Object val) {

		return dao.findEntity(clazz, propName, val);
	}

	@Override
	public void saveEntity(IDataEntity entity) {
		
		dao.saveEntity(entity);		
	}

	@Override
	public void updateEntity(IDataEntity entity) {

		dao.updateEntity(entity);
	}

	@Override
	public void deleteEntity(IDataEntity entity) {
		
		dao.deleteEntity(entity);		
	}

}
