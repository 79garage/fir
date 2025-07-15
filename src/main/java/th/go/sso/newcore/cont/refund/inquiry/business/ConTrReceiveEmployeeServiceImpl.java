package th.go.sso.newcore.cont.refund.inquiry.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.sso.newcore.cont.common.helper.TimerHelper;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeDetailBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.impl.RefundEmployeeDaoImpl;

@Service
public class ConTrReceiveEmployeeServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ConTrReceiveEmployeeServiceImpl.class);

    @Autowired
    private TimerHelper timer;

    @Autowired
    private RefundEmployeeDaoImpl refundEmployeeDao;

    public List<EmployeeDetailBean> searchReceiveEmployee(Long companyId, Long receiveCompanyId) throws Exception {
        timer.start("searchReceiveEmployee");
        List<EmployeeDetailBean> receiveEmployeeList = refundEmployeeDao.findConTrReceiveEmployee(companyId, receiveCompanyId);
        log.info("receiveEmployeeList : {}", receiveEmployeeList.size());
        receiveEmployeeList.forEach(list -> {
            if (!StringUtils.isEmpty(list.getRefundReasonCode())) {
                if (List.of("01", "02", "07", "09", "10", "11").contains(list.getRefundReasonCode())) {
                    list.setEmpeOverp(list.getCntrAmont());
                }
            }
        });
        timer.endToString("searchReceiveEmployee");
        return receiveEmployeeList;
    }
}
