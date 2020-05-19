package cb.lms.CB_Lms.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cb.lms.CB_Lms.modal.IDataEntity;


/**
 * 
 * @author 1595812
 *
 */

@Repository
public class CommonEntityDao extends GenericDao implements ICommonEntityDao{

	
	/**
	 * 
	 */
	@Override
	public Set<IDataEntity> findAll(Class entity, String[] aliases, String orderBy, Boolean isCacheable) {
		
		return super.findAll(entity,aliases,orderBy,isCacheable);
	}

	/**
	 * 
	 */
	@Override
	public IDataEntity findEntity(Class clazz, String propName, Object val) {

		return findById(clazz, propName, val);
	}

	/**
	 * 
	 */
	@Override
	public void saveEntity(IDataEntity entity) {		
		save(entity);		
	}

	
	/**
	 * 
	 */
	@Override
	public void updateEntity(IDataEntity entity) {

		merge(entity);	
		save(entity);
	}

	/**
	 * 
	 */
	@Override
	public void deleteEntity(IDataEntity entity) {
		
		delete(entity);		
	}

}
