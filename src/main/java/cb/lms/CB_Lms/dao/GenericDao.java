package cb.lms.CB_Lms.dao;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 
 * @author 1595812
 *
 */
public class GenericDao {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public <T> void persist(T entity) {

		entityManager.persist(entity);
	}

	/**
	 * Only soft delete.
	 * 
	 * @param entity
	 */
	public <T> void delete(T entity) {

		entityManager.remove(entity);
	}

	/**
	 * Not Physically deleting the record.
	 * 
	 * @param entity
	 */
	public <T> void deleteEntity(T entity) {
		entityManager.remove(entity);
	}

	/**
	 * 
	 * @param entity
	 */
	public <T> void save(T entity) {
		entityManager.persist(entity);

	}

	/**
	 * 
	 * @param entity
	 */
	public <T> void merge(T entity) {
		entityManager.merge(entity);
	}

	/**
	 * 
	 * @param entityClass
	 * @param aliases
	 * @param orderByCol
	 * @param isCachable
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> Set<T> findAll(Class entityClass, String[] aliases, String orderByCol, boolean isCachable) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = builder.createQuery(entityClass);

		Root root = null;

		if (aliases != null) {
			root = criteriaQuery.multiselect(Arrays.asList(aliases)).from(entityClass);
		} else {

			root = criteriaQuery.from(entityClass);
		}

		if(orderByCol!=null) {
			criteriaQuery.orderBy(builder.asc(root.get(orderByCol)));
		}

		Set<T> results = new LinkedHashSet<T>();
		results.addAll(entityManager.createQuery(criteriaQuery).getResultList());

		return results;
	}
	
	/**
	 * 
	 * @param entityClass
	 * @param idName
	 * @param val
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public <T> T findById(Class entityClass, String idName, Object val) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = builder.createQuery(entityClass);

		Root root = criteriaQuery.from(entityClass);


		criteriaQuery.where(builder.equal(root.get(idName), val));

		// criteria.add(Restrictions.eq("isDelete", 0));
		return (T) entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
