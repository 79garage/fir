package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.common.utils.NumberUtils;
import th.go.sso.newcore.cont.common.utils.StringUtils;
import th.go.sso.newcore.cont.refund.inquiry.bean.CompanyRateBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.ConTbInformationBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.EmployeeRateBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.InformationConditionBean;
import th.go.sso.newcore.cont.refund.inquiry.bean.InsurerRateBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbInformationMapper;

@Repository
public class ConTbInformationDaoImpl extends DaoBase<Long> {

    public ConTbInformationDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_INFORMATION";
    }

    @Override
    protected String getPkColumnName() {
        return "INFORMATION_ID";
    }

    public List<ConTbInformationBean> searchConTbInformation(InformationConditionBean bean) throws Exception {

        final StringBuilder sql = new StringBuilder();

        sql.append(" select * ");
        sql.append(" from con_tb_information ");
        sql.append(" where 1 = 1 ");

        final Map<String, Object> param = new HashMap<>();

        if (!StringUtils.isEmpty(bean.getSubject())) {
            sql.append(" and subject = :subject ");
            param.put("subject", bean.getSubject());
        }

        if (!StringUtils.isEmpty(bean.getGroupCode())) {
            sql.append(" and group_code = :groupCode ");
            param.put("groupCode", bean.getGroupCode());
        }

        if (!StringUtils.isEmpty(bean.getSysCode())) {
            sql.append(" and sys_code = :sysCode ");
            param.put("sysCode", bean.getSysCode());
        }

        if (!StringUtils.isEmpty(bean.getSysDate())) {
            sql.append(" and ( start_date <= :sysDate and end_date >= :sysDate ) ");
            param.put("sysDate",bean.getSysDate());
        }

        if (!NumberUtils.isZeroOrNull(bean.getInformationId())) {
            sql.append(" and information_id = :informationId ");
            param.put("informationId", bean.getInformationId());
        }

        return queryForList(sql.toString(), param, new ConTbInformationMapper());
    }


    public CompanyRateBean searchCompanyRate(InformationConditionBean bean) throws Exception {

        CompanyRateBean rateBean = new CompanyRateBean();

        List<ConTbInformationBean> infoList = searchConTbInformation(bean);

        if (infoList != null) {

            for (ConTbInformationBean infoBean : infoList) {
                BigDecimal value = new BigDecimal(infoBean.getValue1());
                switch (infoBean.getSubject()) {
                    case "SICKNESS":
                        rateBean.setSickness(value);
                        break;
                    case "MATERNITY":
                        rateBean.setMaternity(value);
                        break;
                    case "INVALIDITY":
                        rateBean.setInvalidity(value);
                        break;
                    case "DEATH":
                        rateBean.setDeath(value);
                        break;
                    case "RETIREMENT":
                        rateBean.setRetirement(value);
                        break;
                    case "CHILD_ALLOWANCE":
                        rateBean.setChildAllowance(value);
                        break;
                    case "UNEMPLOYMENT":
                        rateBean.setUnemployment(value);
                        break;
                    default:
                        break;
                }
            }
        }

        return rateBean;
    }

    public EmployeeRateBean searchEmployeeRate(InformationConditionBean bean) throws Exception {

        EmployeeRateBean rateBean = new EmployeeRateBean();

        List<ConTbInformationBean> infoList = searchConTbInformation(bean);

        if (infoList != null) {

            for (ConTbInformationBean infoBean : infoList) {
                BigDecimal value = new BigDecimal(infoBean.getValue1());
                switch (infoBean.getSubject()) {
                    case "SICKNESS":
                        rateBean.setSickness(value);
                        break;
                    case "MATERNITY":
                        rateBean.setMaternity(value);
                        break;
                    case "INVALIDITY":
                        rateBean.setInvalidity(value);
                        break;
                    case "DEATH":
                        rateBean.setDeath(value);
                        break;
                    case "RETIREMENT":
                        rateBean.setRetirement(value);
                        break;
                    case "CHILD_ALLOWANCE":
                        rateBean.setChildAllowance(value);
                        break;
                    case "UNEMPLOYMENT":
                        rateBean.setUnemployment(value);
                        break;
                    default:
                        break;
                }
            }
        }

        return rateBean;
    }

    public InsurerRateBean searchInsurerRate(InformationConditionBean bean) throws Exception {

        InsurerRateBean rateBean = new InsurerRateBean();

        List<ConTbInformationBean> infoList = searchConTbInformation(bean);

        if (infoList != null) {

            for (ConTbInformationBean infoBean : infoList) {
                BigDecimal value = new BigDecimal(infoBean.getValue1());
                switch (infoBean.getSubject()) {
                    case "SICKNESS":
                        rateBean.setSickness(value);
                        break;
                    case "MATERNITY":
                        rateBean.setMaternity(value);
                        break;
                    case "INVALIDITY":
                        rateBean.setInvalidity(value);
                        break;
                    case "DEATH":
                        rateBean.setDeath(value);
                        break;
                    case "RETIREMENT":
                        rateBean.setRetirement(value);
                        break;
                    case "CHILD_ALLOWANCE":
                        rateBean.setChildAllowance(value);
                        break;
                    case "UNEMPLOYMENT":
                        rateBean.setUnemployment(value);
                        break;
                    default:
                        break;
                }
            }
        }

        return rateBean;
    }
}
