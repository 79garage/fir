package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.dto.IUserInfo;
import th.go.sso.newcore.cont.common.dto.Paginable;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterCompanyBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RequesterCompanyBatchDaoImpl;

@Service
public class ConTbRequesterCompanyServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ConTbRequesterCompanyServiceImpl.class);

    @Autowired
    private RequesterCompanyBatchDaoImpl requesterCompanyBatchDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RequesterCompanyBatchBean> searchRequestCompanyBatch(String createBy, Date createDate, Paginable paginable, IUserInfo user, List<Long> requesterIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRequestCompanyBatch by user: {} ", userName);

        List<RequesterCompanyBatchBean> requesterCompanyBatchList = requesterCompanyBatchDao.queryRequestCompanyBatch(createBy, createDate, paginable, requesterIds);

        log.debug("End searchRequestCompanyBatch found result : {} ", requesterCompanyBatchList != null ? requesterCompanyBatchList.size() : 0);
        return requesterCompanyBatchList;
    }

    private String getUserName(IUserInfo user) {
        return user != null ? user.getUsername() : "no user login";
    }
}
