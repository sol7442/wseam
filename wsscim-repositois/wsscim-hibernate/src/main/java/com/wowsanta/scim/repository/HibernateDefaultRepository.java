package com.wowsanta.scim.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimSearchRequest;
import com.wowsanta.scim.hibernate.HibernateEntity;
import com.wowsanta.scim.hibernate.HibernateRepository;
import com.wowsanta.scim.repository.ScimRepositoryException;

@SuppressWarnings({"unchecked","rawtypes"})
public class HibernateDefaultRepository extends HibernateRepository{
	
	private Class resoucreClass;
	public void setResourceClass(Class clazz) {
		this.resoucreClass = clazz;
	}
	@Override
	public void create(ScimResource resource) throws ScimRepositoryException {
		this.session.save(resource);
	}

	@Override
	public void update(ScimResource resource) throws ScimRepositoryException {
		HibernateEntity old_resource =  (HibernateEntity) this.session.get(resoucreClass,resource.getId());
		if(old_resource == null) {
			throw new ScimRepositoryException("NotFound resource : " + resource.getId());
		}
		
		old_resource.copy(resource);
		this.session.update(old_resource);
	}

	@Override
	public void delete(ScimResource resource) throws ScimRepositoryException {
		this.session.delete(resource);
	}

	@Override
	public ScimResource get(String id) throws ScimRepositoryException {
		ScimResource resource = (ScimResource) this.session.get(resoucreClass, id);
		return resource;
	}

	
	@Override
	public ScimListResponse search(ScimSearchRequest request) throws ScimRepositoryException {
		
		ScimListResponse response =  null;// new WsScimListResponse();
		
		CriteriaBuilder cb = this.session.getCriteriaBuilder();
		CriteriaQuery<ScimResource> cq = cb.createQuery(resoucreClass);
		Root<ScimResource> root = cq.from(ScimResource.class);
		
		cq.select(root);
		if(request.getFilter() != null) {
			
		}
		Query<ScimResource> query = this.session.createQuery(cq);
		
		List<ScimResource> result_list = query.getResultList();
		response.setTotalResults(result_list.size());
		response.setStartIndex(request.getStartIndex());
		response.setItemsPerPage(request.getCount());
		
		query.setFirstResult(request.getCount()*request.getStartIndex());
		query.setMaxResults(request.getCount());
		
		for (ScimResource ScimResource : result_list) {
			response.addResource(ScimResource);
		}
	     
	    return response;
	}



}
