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
import th.go.sso.newcore.cont.refund.inquiry.bean.RequesterEmployeeBatchBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RequesterEmployeeBatchDaoImpl;

@Service
public class ConTbRequesterEmployeeServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ConTbRequesterEmployeeServiceImpl.class);

    @Autowired
    private RequesterEmployeeBatchDaoImpl requesterEmployeeBatchDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RequesterEmployeeBatchBean> searchRequestEmployeeBatch(String createBy, Date createDate, Paginable paginable, IUserInfo user, List<Long> requesterIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRequestEmployeeBatch by user: {} ", userName);

        List<RequesterEmployeeBatchBean> requesterEmployeeBatchList = requesterEmployeeBatchDao.queryRequestEmployeeBatch(createBy, createDate, paginable, requesterIds);

        log.debug("End searchRequestEmployeeBatch found result : {} ", requesterEmployeeBatchList != null ? requesterEmployeeBatchList.size() : 0);
        return requesterEmployeeBatchList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ConTbRequesterBean> searchRequester33Batch(Paginable paginable, IUserInfo user, List<Long> refundRequestIds) throws Exception {
        String userName = getUserName(user);
        log.debug("Start searchRequester33Batch by user: {} ", userName);

        List<ConTbRequesterBean> requester33BatchList = requesterEmployeeBatchDao.queryConTbRequester33Batch(paginable, refundRequestIds);

        log.debug("End searchRequester33Batch found result : {} ", requester33BatchList != null ? requester33BatchList.size() : 0);
        return requester33BatchList;
    }

    private String getUserName(IUserInfo user) {
        return user != null ? user.getUsername() : "no user login";
    }
}
