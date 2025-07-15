package th.go.sso.newcore.cont.refund.inquiry.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import th.go.sso.newcore.cont.common.dao.DaoBase;
import th.go.sso.newcore.cont.refund.inquiry.bean.RefundReportBean;
import th.go.sso.newcore.cont.refund.inquiry.dao.mapper.ConTbStatisticRefundReportRowMapper;

@Repository
public class ConTbStatisticRefundReportDaoImpl extends DaoBase<Long> {
    public ConTbStatisticRefundReportDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "CON_TB_STATISTIC_REFUND_REPORT";
    }

    @Override
    protected String getPkColumnName() {
        return "STATISTIC_REFUND_REPORT_ID";
    }

    public List<RefundReportBean> findRefundRequestBeforeOneMonth() throws Exception {
        String sql = """
                SELECT /*+ PARALLEL(t0, 32) */
                    t0.*
                FROM
                    (
                        SELECT
                            req.receive_no,
                            req.receive_date,
                            comp.company_id,
                            comp.company_no,
                            comp.company_name,
                            comp.company_branch_code,
                            comp.company_branch_name,
                            ins.insurer_id,
                            ins.id_card_no,
                            ins.full_name,
                            ins.first_name,
                            ins.last_name,
                            repe.refund_period,
                            repe.refund_period_month,
                            repe.refund_period_year,
                            repe.request_empr_amount,
                            repe.request_empe_amount,
                            repe.request_interest_amount,
                            repe.request_total_amount,
                            repe.refund_empr_amount,
                            repe.refund_empe_amount,
                            repe.refund_interest_amount,
                            repe.refund_total_amount,
                            repe.refund_sickness_amount,
                            repe.refund_retirement_amount,
                            repe.refund_unemployment_amount,
                            req.refund_type_place,
                            req.request_reason_code,
                            req.request_reason_other,
                            CASE WHEN req.section = '0' THEN const33.value_th
                            WHEN req.section = '1' THEN const39.value_th
                            WHEN req.section = '3' THEN constmulti.value_th ELSE '' END AS request_reason_desc,
                            dept_rec.dep_code AS department_receive_code,
                            dept_res.dep_code AS department_responsible_code,
                            dept_rec.dep_name AS department_receive_name,
                            dept_res.dep_name AS department_responsible_name,
                            req.refund_prompt_pay,
                            req.refund_po_order,
                            req.refund_bank_account,
                            bak.bank_code     AS refund_bank_code,
                            bak.bank_name     AS bank_name,
                            req.refund_po_order_name,
                            req.period_start,
                            req.period_end,
                            req.request_amount,
                            req.period_start_month,
                            req.period_start_year,
                            req.period_end_month,
                            req.period_end_year,
                            req.section,
                            req.approve_date,
                            CASE WHEN req.approve_status = 'W' THEN 'B' ELSE req.approve_status END AS approve_status,
                            req.split_refund,
                            'A'               AS status,
                            sysdate           AS process_date,
                            'SYSTEM'          AS create_by,
                            last_day(sysdate) AS create_date
                        FROM
                            con_tb_refund_request req
                            JOIN con_tb_refund_period repe ON ( repe.refund_request_id = req.refund_request_id )
                            LEFT JOIN con_ms_company       comp ON ( comp.company_id = req.company_id )
                            LEFT JOIN con_ms_insurer       ins ON ( ins.insurer_id = req.insurer_id )
                            LEFT JOIN con_tb_constant      const33 ON ( req.request_reason_code = const33.code
                                                                   AND const33.group_code = 'REFUND_CAUSE' AND const33.sys_code = 'M33' )
                            LEFT JOIN con_tb_constant      const39 ON ( req.request_reason_code = const39.code
                                                                   AND const39.group_code = 'REFUND_CAUSE' AND const39.sys_code = 'M39' )
                            LEFT JOIN con_tb_constant      constmulti ON ( req.request_reason_code = constmulti.code
                                                                      AND constmulti.group_code = 'REFUND_CAUSE_MULTI' AND constmulti.sys_code = 'M33' )
                            LEFT JOIN con_ms_department    dept_res ON ( req.department_responsible_code = dept_res.dep_code )
                            LEFT JOIN con_ms_department    dept_rec ON ( req.department_receive_code = dept_rec.dep_code )
                            LEFT JOIN con_ms_bank          bak ON ( bak.bank_id = req.refund_bank_id )
                        WHERE
                            1 = 1
                            AND req.approve_status = 'W'
                            AND ( to_char( req.receive_date, 'YYYYMM' ) < to_char( sysdate, 'YYYYMM' ) )
                    ) t0
                """;
        return queryForList(sql, null, new ConTbStatisticRefundReportRowMapper());
    }

    public List<RefundReportBean> findRefundRequestCurrentMonth() throws Exception {
        String sql = """
                SELECT /*+ PARALLEL(t0, 32) */
                            t0.*
                        FROM
                            (
                                SELECT
                                    req.receive_no,
                                    req.receive_date,
                                    comp.company_id,
                                    comp.company_no,
                                    comp.company_name,
                                    comp.company_branch_code,
                                    comp.company_branch_name,
                                    ins.insurer_id,
                                    ins.id_card_no,
                                    ins.full_name,
                                    ins.first_name,
                                    ins.last_name,
                                    repe.refund_period,
                                    repe.refund_period_month,
                                    repe.refund_period_year,
                                    repe.request_empr_amount,
                                    repe.request_empe_amount,
                                    repe.request_interest_amount,
                                    repe.request_total_amount,
                                    repe.refund_empr_amount,
                                    repe.refund_empe_amount,
                                    repe.refund_interest_amount,
                                    repe.refund_total_amount,
                                    repe.refund_sickness_amount,
                                    repe.refund_retirement_amount,
                                    repe.refund_unemployment_amount,
                                    req.refund_type_place,
                                    req.request_reason_code,
                                    req.request_reason_other,
                                    CASE WHEN req.section = '0' THEN const33.value_th
                                    WHEN req.section = '1' THEN const39.value_th
                                    WHEN req.section = '3' THEN constmulti.value_th ELSE '' END AS request_reason_desc,
                                    dept_rec.dep_code      AS department_receive_code,
                                    dept_res.dep_code      AS department_responsible_code,
                                    dept_rec.dep_name      AS department_receive_name,
                                    dept_res.dep_name      AS department_responsible_name,
                                    req.refund_prompt_pay,
                                    req.refund_po_order,
                                    req.refund_bank_account,
                                    bak.bank_code          AS refund_bank_code,
                                    bak.bank_name          AS bank_name,
                                    req.refund_po_order_name,
                                    req.period_start,
                                    req.period_end,
                                    req.request_amount,
                                    req.period_start_month,
                                    req.period_start_year,
                                    req.period_end_month,
                                    req.period_end_year,
                                    req.section,
                                    req.approve_date,
                                    repe.approve_status,
                                    req.split_refund,
                                    'A'                    AS status,
                                    sysdate                AS process_date,
                                    'SYSTEM'               AS create_by,
                                    last_day(receive_date) AS create_date
                                FROM
                                    con_tb_refund_request req
                                    JOIN con_tb_refund_period repe ON ( repe.refund_request_id = req.refund_request_id )
                                    LEFT JOIN con_ms_company       comp ON ( comp.company_id = req.company_id )
                                    LEFT JOIN con_ms_insurer       ins ON ( ins.insurer_id = req.insurer_id )
                                    LEFT JOIN con_tb_constant      const33 ON ( req.request_reason_code = const33.code
                                                                           AND const33.group_code = 'REFUND_CAUSE' AND const33.sys_code = 'M33' )
                                    LEFT JOIN con_tb_constant      const39 ON ( req.request_reason_code = const39.code
                                                                           AND const39.group_code = 'REFUND_CAUSE' AND const39.sys_code = 'M39' )
                                    LEFT JOIN con_tb_constant      constmulti ON ( req.request_reason_code = constmulti.code
                                                                              AND constmulti.group_code = 'REFUND_CAUSE_MULTI' AND constmulti.sys_code = 'M33' )
                                    LEFT JOIN con_ms_department    dept_res ON ( req.department_responsible_code = dept_res.dep_code )
                                    LEFT JOIN con_ms_department    dept_rec ON ( req.department_receive_code = dept_rec.dep_code )
                                    LEFT JOIN con_ms_bank          bak ON ( bak.bank_id = req.refund_bank_id )
                                WHERE
                                    1 = 1
                                    AND req.receive_no IS NOT NULL
                                    AND ( to_char( req.receive_date, 'YYYYMM' ) = to_char( sysdate, 'YYYYMM' ) )
                            ) t0
                """;
        return queryForList(sql, null, new ConTbStatisticRefundReportRowMapper());
    }
}
