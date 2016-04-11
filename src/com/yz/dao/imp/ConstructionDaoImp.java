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

import com.yz.dao.IConstructionDao;
import com.yz.model.Construction;
@Component("constructionDao")
public class ConstructionDaoImp implements IConstructionDao {
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
	public void save(Construction construction) {
		this.hibernateTemplate.save(construction);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#savereturn(com.yz.model.construction)
	 */
	public Integer savereturn(Construction construction) {
		return (Integer) this.hibernateTemplate.save(construction);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#delete(com.yz.model.construction)
	 */
	public void delete(Construction construction) {
		this.hibernateTemplate.delete(construction);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#update(com.yz.model.construction)
	 */
	public void update(Construction construction) {
		this.hibernateTemplate.update(construction);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
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
	 * @see com.yz.dao.imp.IconstructionDao#getconstructions()
	 */
	public List<Construction> getConstructions() {
		return this.hibernateTemplate.loadAll(Construction.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#queryList(java.lang.String)
	 */
	public List<Construction> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Construction> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Construction> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IconstructionDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Construction> getObjectsByIdList(final String hql,final List<Integer> idList) {
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
	 * @see com.yz.dao.imp.IconstructionDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Construction> pageList(final String queryString,final Object[] p,final Integer page,
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
	 * @see com.yz.dao.imp.IconstructionDao#getUniqueResult(java.lang.String, java.lang.Object[])
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
	 * @see com.yz.dao.imp.IConstructionDao#loadById(int)
	 */
	public Construction loadById(int id) {
		return (Construction) this.hibernateTemplate.load(Construction.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IConstructionDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Construction queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Construction)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IConstructionDao#checkConstructionExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkConstructionExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IConstructionDao#updateConstructionByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateConstructionByhql(String queryString, Object[] p) {
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
	public Construction getConstructionById(Integer upConstructionid) {
		// TODO Auto-generated method stub
		return (Construction) this.hibernateTemplate.get(Construction.class, upConstructionid);
	}


}
