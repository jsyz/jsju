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

import com.yz.dao.IUseroDao;
import com.yz.model.Usero;
@Component("useroDao")
public class UseroDaoImp implements IUseroDao {
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
	public void save(Usero usero) {
		this.hibernateTemplate.save(usero);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#savereturn(com.yz.model.usero)
	 */
	public Integer savereturn(Usero usero) {
		return (Integer) this.hibernateTemplate.save(usero);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#delete(com.yz.model.usero)
	 */
	public void delete(Usero usero) {
		this.hibernateTemplate.delete(usero);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#update(com.yz.model.usero)
	 */
	public void update(Usero usero) {
		this.hibernateTemplate.update(usero);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
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
	 * @see com.yz.dao.imp.IuseroDao#getuseros()
	 */
	public List<Usero> getUseros() {
		return this.hibernateTemplate.loadAll(Usero.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#queryList(java.lang.String)
	 */
	public List<Usero> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Usero> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Usero> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IuseroDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Usero> getObjectsByIdList(final String hql,final List<Integer> idList) {
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
	 * @see com.yz.dao.imp.IuseroDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Usero> pageList(final String queryString,final Object[] p,final Integer page,
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
	 * @see com.yz.dao.imp.IuseroDao#getUniqueResult(java.lang.String, java.lang.Object[])
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
	 * @see com.yz.dao.imp.IUseroDao#loadById(int)
	 */
	public Usero loadById(int id) {
		return (Usero) this.hibernateTemplate.load(Usero.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IUseroDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Usero queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Usero)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IUseroDao#checkUseroExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkUseroExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IUseroDao#updateUseroByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateUseroByhql(String queryString, Object[] p) {
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
	public Usero getUseroById(Integer upUseroid) {
		// TODO Auto-generated method stub
		return (Usero) this.hibernateTemplate.get(Usero.class, upUseroid);
	}


}
