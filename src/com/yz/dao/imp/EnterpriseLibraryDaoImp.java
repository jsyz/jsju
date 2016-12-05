package com.yz.dao.imp;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.yz.dao.IEnterpriseLibraryDao;
import com.yz.model.EnterpriseLibrary;

@Component("enterpriseLibraryDao")
public class EnterpriseLibraryDaoImp implements IEnterpriseLibraryDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public boolean checkEnterpriseLibraryExistsWithName(String queryString,
			Object[] p) {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete(EnterpriseLibrary enterpriseLibrary) {
		// TODO Auto-generated method stub

	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	public List<EnterpriseLibrary> getEnterpriseLibrarys() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EnterpriseLibrary> getObjectsByCondition(String queryString,
			Object[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EnterpriseLibrary> getObjectsByIdList(String hql,
			List<Integer> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getUniqueResult(String queryString, Object[] p) {
		// TODO Auto-generated method stub
		return 0;
	}

	public EnterpriseLibrary loadById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EnterpriseLibrary> pageList(final String queryString,final Object[] p,final Integer page,
	final Integer size) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);
				if (p != null && p.length > 0) {
					for (int i = 0; i < p.length; i++) {
						query.setParameter(i, p[i]);
					}
				}
				if (page != null && page > 0 && size != null && size > 0) {
					query.setFirstResult((page - 1) * size).setMaxResults(size);
				}
				return query.list();
			}

		});
	}

	public EnterpriseLibrary queryByNamedParam(String queryString,
			String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub

		return null;
	}

	public List<EnterpriseLibrary> queryList(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EnterpriseLibrary> queryList(String queryString,
			String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(EnterpriseLibrary EnterpriseLibrary) {
		// TODO Auto-generated method stub

	}

	public Integer savereturn(EnterpriseLibrary enterpriseLibrary) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(EnterpriseLibrary enterpriseLibrary) {
		// TODO Auto-generated method stub

	}

	public void updateByHql(String hql, String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub

	}

	public int updateEnterpriseLibraryByhql(String queryString, Object[] p) {
		// TODO Auto-generated method stub
		return 0;
	}

}
