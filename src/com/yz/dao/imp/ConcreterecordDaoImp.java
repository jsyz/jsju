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

import com.yz.dao.IConcreterecordDao;
import com.yz.model.Concreterecord;
import com.yz.model.Proman;

@Component("ConcreterecordDao")
public class ConcreterecordDaoImp implements IConcreterecordDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public boolean checkConstructionExistsWithName(String queryString,
			Object[] p) {
		// TODO Auto-generated method stub
		return false;
	}

	public void delete(Concreterecord concreterecord) {
		// TODO Auto-generated method stub
			this.hibernateTemplate.delete(concreterecord);
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	public List<Concreterecord> getConstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Concreterecord> getObjectsByCondition(String queryString,
			Object[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Concreterecord> getObjectsByIdList(String hql,
			List<Integer> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getUniqueResult(String queryString, Object[] p) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Concreterecord loadById(int id) {
		// TODO Auto-generated method stub
		return (Concreterecord) this.hibernateTemplate.load(Concreterecord.class, id);
	}

	public List<Concreterecord> pageList(final String queryString, final Object[] p,
			final Integer page, final Integer size) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(queryString);
				if(p!=null&&p.length>0){
					for (int i = 0; i < p.length; i++) {
						query.setParameter(i, p[i]);
					}
				}
				if(page!=null&&page>0&&size!=null&&size>0){
					query.setFirstResult((page-1)*size).setMaxResults(size);
				}
				return query.list();
			}
			
		});
	}

	public Concreterecord queryByNamedParam(String queryString,
			String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Concreterecord> queryList(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Concreterecord> queryList(String queryString,
			String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Concreterecord concreterecord) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(concreterecord);
	}

	public Integer savereturn(Concreterecord concreterecord) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Concreterecord concreterecord) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(concreterecord);
	}

	public void updateByHql(String hql, String[] paramNames, Object[] values) {
		// TODO Auto-generated method stub

	}

	public int updateConstructionByhql(String queryString, Object[] p) {
		// TODO Auto-generated method stub
		return 0;
	}

}
