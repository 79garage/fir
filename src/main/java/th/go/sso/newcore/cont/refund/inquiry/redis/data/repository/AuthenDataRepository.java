package th.go.sso.newcore.cont.refund.inquiry.redis.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.redis.entity.AuthenEntity;

@Repository
public interface AuthenDataRepository extends CrudRepository<AuthenEntity, String> {
	public AuthenEntity findByPreferredUsername(String username);
}
