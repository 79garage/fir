package th.go.sso.newcore.cont.refund.inquiry.redis.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.RedisCrudBase;
import th.go.sso.newcore.cont.common.redis.entity.AuthenEntity;
import th.go.sso.newcore.cont.refund.inquiry.redis.data.repository.AuthenDataRepository;

@Repository
public class AuthenDataImpl extends RedisCrudBase<AuthenEntity, String>{

	private AuthenDataRepository crudRepo;
	
	@Autowired
	public AuthenDataImpl(AuthenDataRepository crudRepo) {
		super(crudRepo);
		this.crudRepo = crudRepo;
	}

	public AuthenEntity findByPreferredUsername(String username) {
		return crudRepo.findByPreferredUsername(username);
	}
}
