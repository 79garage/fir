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
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbRequesterBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterInsurerBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RequesterInsurerBatchDaoImpl;

@Service
public class ConTbRequesterInsurerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ConTbRequesterInsurerServiceImpl.class);

    @Autowired
    private RequesterInsurerBatchDaoImpl requesterInsurerBatchDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RequesterInsurerBatchBean> searchRequestInsurerBatch(String createBy, Date createDate, Paginable paginable, IUserInfo user, List<Long> requesterIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRequestInsurerBatch by user: {} ", userName);

        List<RequesterInsurerBatchBean> requesterInsurerBatchList = requesterInsurerBatchDao.queryRequestInsurerBatch(createBy, createDate, paginable, requesterIds);

        log.debug("End searchRequestInsurerBatch found result : {} ", requesterInsurerBatchList != null ? requesterInsurerBatchList.size() : 0);
        return requesterInsurerBatchList;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbRequesterBean> searchRequester39Batch(Paginable paginable, IUserInfo user, List<Long> refundRequestIds, String requesterType) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRequester39Batch by user: {} ", userName);

        List<ConTbRequesterBean> requester39BatchList = requesterInsurerBatchDao.queryConTbRequester39Batch(paginable, refundRequestIds, requesterType);

        log.debug("End searchRequester39Batch found result : {} ", requester39BatchList != null ? requester39BatchList.size() : 0);
        return requester39BatchList;
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public ConTbRequesterBean getConTbRequester39ById(final Long requesterId) throws Exception { 
        return requesterInsurerBatchDao.getConTbRequester39ById(requesterId);
    }

    private String getUserName(IUserInfo user) {
        return user != null ? user.getUsername() : "no user login";
    }
}
