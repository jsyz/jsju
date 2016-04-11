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

import com.yz.dao.IYxareaDao;
import com.yz.model.Yxarea;
@Component("yxareaDao")
public class YxareaDaoImp implements IYxareaDao {
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
	public void save(Yxarea yxarea) {
		this.hibernateTemplate.save(yxarea);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#savereturn(com.yz.model.yxarea)
	 */
	public Integer savereturn(Yxarea yxarea) {
		return (Integer) this.hibernateTemplate.save(yxarea);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#delete(com.yz.model.yxarea)
	 */
	public void delete(Yxarea yxarea) {
		this.hibernateTemplate.delete(yxarea);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#update(com.yz.model.yxarea)
	 */
	public void update(Yxarea yxarea) {
		this.hibernateTemplate.update(yxarea);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
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
	 * @see com.yz.dao.imp.IyxareaDao#getyxareas()
	 */
	public List<Yxarea> getYxareas() {
		return this.hibernateTemplate.loadAll(Yxarea.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#queryList(java.lang.String)
	 */
	public List<Yxarea> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Yxarea> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Yxarea> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IyxareaDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Yxarea> getObjectsByIdList(final String hql,final List<Integer> idList) {
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
	 * @see com.yz.dao.imp.IyxareaDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Yxarea> pageList(final String queryString,final Object[] p,final Integer page,
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
	 * @see com.yz.dao.imp.IyxareaDao#getUniqueResult(java.lang.String, java.lang.Object[])
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
	 * @see com.yz.dao.imp.IYxareaDao#loadById(int)
	 */
	public Yxarea loadById(int id) {
		return (Yxarea) this.hibernateTemplate.load(Yxarea.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IYxareaDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Yxarea queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Yxarea)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IYxareaDao#checkYxareaExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkYxareaExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IYxareaDao#updateYxareaByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateYxareaByhql(String queryString, Object[] p) {
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
	public Yxarea getYxareaById(Integer upYxareaid) {
		// TODO Auto-generated method stub
		return (Yxarea) this.hibernateTemplate.get(Yxarea.class, upYxareaid);
	}


}
