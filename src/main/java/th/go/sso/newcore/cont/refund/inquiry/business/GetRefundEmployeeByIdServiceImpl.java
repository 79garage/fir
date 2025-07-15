package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.go.sso.newcore.cont.common.exception.BusinessException;
import th.go.sso.newcore.cont.common.utils.CalculateUtils;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.ObjectUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.SplitEmployeeRefundCompanyPeriodBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.SplitEmployeeRefundDaoImpl;

@Service
public class GetRefundEmployeeByIdServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(GetRefundEmployeeByIdServiceImpl.class);


    @Autowired
    private SplitEmployeeRefundDaoImpl splitEmployeeRefundDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public SplitEmployeeRefundCompanyPeriodBean employeeSplitDetails(Long refundRequestId) throws Exception {
        log.info("Start employeeSplitDetails");

        SplitEmployeeRefundCompanyPeriodBean bean = splitEmployeeRefundDao.findRefundEmployeePeriod(refundRequestId);
        if (ObjectUtils.isObjectNull(bean)) {
            throw new BusinessException("ไม่พบข้อมูลงวดเดือนของผู้ประกันตน");
        }

        bean.setEmpeSumRate(CalculateUtils.sum(List.of(NumberUtils.convertZeroIfNull(bean.getRefundSicknessRate())
                , NumberUtils.convertZeroIfNull(bean.getRefundRetirementRate())
                , NumberUtils.convertZeroIfNull(bean.getRefundUnemploymentRate()))));

        log.info("End employeeSplitDetails");
        return bean;
    }
}
