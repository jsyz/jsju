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

import com.yz.dao.IDaymanageDao;
import com.yz.model.Daymanage;
@Component("daymanageDao")
public class DaymanageDaoImp implements IDaymanageDao {
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IUserDao#save(com.yz.model.User)
	 */
	public void save(Daymanage daymanage) {
		this.hibernateTemplate.save(daymanage);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#savereturn(com.yz.model.daymanage)
	 */
	public Integer savereturn(Daymanage daymanage) {
		return (Integer) this.hibernateTemplate.save(daymanage);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#delete(com.yz.model.daymanage)
	 */
	public void delete(Daymanage daymanage) {
		this.hibernateTemplate.delete(daymanage);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#update(com.yz.model.daymanage)
	 */
	public void update(Daymanage daymanage) {
		this.hibernateTemplate.update(daymanage);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public void updateByHql(final String hql,final String[] paramNames,final Object[] values) {
		this.hibernateTemplate.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], values[i]);
				}
				query.executeUpdate();
				return null;
			}
			
		});
	}
	
	//获得所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#getdaymanages()
	 */
	public List<Daymanage> getDaymanages() {
		return this.hibernateTemplate.loadAll(Daymanage.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#queryList(java.lang.String)
	 */
	public List<Daymanage> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Daymanage> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Daymanage> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Daymanage> getObjectsByIdList(final String hql,final List<Integer> idList) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setParameterList("idList", idList);
				return query.list();
			}
			
		});
	}
	
	//根据hql语句、条件值、分页查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Daymanage> pageList(final String queryString,final Object[] p,final Integer page,
			final Integer size) {
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
	
	
	
	//根据hql、条件值获得一个唯一值
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IdaymanageDao#getUniqueResult(java.lang.String, java.lang.Object[])
	 */
	public int getUniqueResult(final String queryString,final Object[] p) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		if(p!=null&&p.length>0){
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		Object obj=query.uniqueResult();
		return ((Long)obj).intValue();
			
	}
	
	//根据id查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IDaymanageDao#loadById(int)
	 */
	public Daymanage loadById(int id) {
		return (Daymanage) this.hibernateTemplate.load(Daymanage.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IDaymanageDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Daymanage queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Daymanage)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IDaymanageDao#checkDaymanageExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkDaymanageExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IDaymanageDao#updateDaymanageByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateDaymanageByhql(String queryString, Object[] p) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		if(p!=null&&p.length>0){
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		//返回受影响的行数
		return query.executeUpdate();
	}
	public Daymanage getDaymanageById(Integer upDaymanageid) {
		// TODO Auto-generated method stub
		return (Daymanage) this.hibernateTemplate.get(Daymanage.class, upDaymanageid);
	}


}
