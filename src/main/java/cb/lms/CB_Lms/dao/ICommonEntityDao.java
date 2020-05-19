package cb.lms.CB_Lms.dao;

import java.util.List;
import java.util.Set;

import cb.lms.CB_Lms.modal.IDataEntity;




/**
 * 
 * @author 1595812
 *
 */
public interface ICommonEntityDao {
	
	/**
	 * 
	 * @param entity
	 * @param aliases
	 * @param orderBy
	 * @param isCacheable
	 * @return
	 */
	public Set<IDataEntity> findAll(Class entity,String[] aliases,String orderBy,Boolean isCacheable);
	
	/**
	 * 
	 * @param clazz
	 * @param propName
	 * @param val
	 * @return
	 */
	public IDataEntity findEntity(Class clazz,String propName,Object val);
	
	/**
	 * 
	 * @param entity
	 */
	public void saveEntity(IDataEntity entity);
	
	/**
	 * 
	 * @param entity
	 */
	public void updateEntity(IDataEntity entity);
	
	/**
	 * 
	 * @param entity
	 */
	public void deleteEntity(IDataEntity entity);

}
