package th.go.sso.newcore.cont.refund.inquiry.ws.provider;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.go.sso.newcore.cont.common.dto.GetByIdRequest;
import th.go.sso.newcore.cont.common.dto.IResponseBean;
import th.go.sso.newcore.cont.common.dto.RequestBean;
import th.go.sso.newcore.cont.common.dto.UserPrincipal;
import th.go.sso.newcore.cont.common.utils.WebServiceUtils;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.ConTrReceiveEmployeeWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetHoldReceiveByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetPaybackRefundRequestByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetPaybackRefundRequestByReceiveNoWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetRefundEmployeeByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetRefundInsurerHoldReceiveByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetRefundInsurerMultipleCompanyInfoByPeriodWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.GetRefundInsurerMultipleCompanyInfoByPeriodsWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.InitiateWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.RefundDeleteCancelHistByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchConTbPaybackRequestInfoWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchContributionCompanyByEmprWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchContributionInsurerWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchOverHoldReceiveWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchOverRefundCompanyByEmprWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchOverRefundCompanyWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchOverRefundEmployeeNoticeWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchOverRefundEmployeeWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchOverRefundInsurerWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackCompanyBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackEmployeeBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackInsurerBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackRefundRequestByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackRequestCompanyBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackRequestEmployeeBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchPaybackRequestInsurerBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundCompanyByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundCompanyWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundDeleteCancelHistWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundHistoryByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundHistoryWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundInquiryInsurerWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundInsurerByIdWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundInsurerRequestImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundRequestCompanyBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundRequestEmployeeBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRefundRequestInsurerBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRequester33BatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRequester39BatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRequesterCompanyBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRequesterEmployeeBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRequesterInsurerBatchWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchRequesterM39WsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchSplitEmployeeRefundWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.SearchStatisticRefundReportWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback.SearchM33CompanyPaybackListWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback.SearchM33CompanyRefundSuccessListWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback.SearchM33EmployeePaybackListWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback.SearchM33EmployeeRefundSuccessListWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback.SearchM39PaybackListWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.impl.payback.SearchM39RefundSuccessListWsProviderImpl;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetHoldReceiveByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundEmployeeByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerHoldReceiveByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerMultipleCompanyInfoByPeriodRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundInsurerMultipleCompanyInfoByPeriodsRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.GetRefundRequestByReceiveNoRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.RefundDeleteCancelHistoryByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchConTbPaybackRequestRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchContributionCompanyByEmprRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchContributionInsurerRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchEmployeeDetailRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM33PaybackListRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchM39PaybackListRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverHoldReceiveRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundCompanyByEmprRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeNoticeRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchOverRefundEmployeeRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackCompanyBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackEmployeeBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackInsurerBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackRequestByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackRequestCompanyBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackRequestEmployeeBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchPaybackRequestInsurerBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundCompanyByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundCompanyRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundDeleteCancelHistoryRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundHistoryRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInquiryInsurerRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerDetailByIdRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundInsurerRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRefundRequestBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequester33Request;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequester39Request;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterCompanyBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterEmployeeBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterInsurerBatchRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchRequesterM39Request;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchSplitEmployeeRefundRequest;
import th.go.sso.newcore.cont.refund.inquiry.ws.provider.request.SearchStatisticRefundReportRequest;

//test only
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/transaction")
public class RefundInquiryWsProvider {

	private static final Logger log = LoggerFactory.getLogger(RefundInquiryWsProvider.class);

	@Autowired
	private SearchOverRefundCompanyByEmprWsProviderImpl overRefundCompanyByEmprWsProvider;
	@Autowired
	private SearchContributionCompanyByEmprWsProviderImpl contributionCompanyByEmprWsProvider;
	@Autowired
	private SearchOverRefundInsurerWsProviderImpl overRefundInsurerWsProvider;
	@Autowired
	private SearchContributionInsurerWsProviderImpl contributionInsurerWsProvider;
	@Autowired
	private SearchRefundCompanyWsProviderImpl refundCompanyWsProvider;
	@Autowired
	private SearchRefundCompanyByIdWsProviderImpl refundCompanyChangeWsProvider;
	@Autowired
	private SearchOverRefundEmployeeWsProviderImpl overRefundEmployeeWsProvider;
	@Autowired
	private SearchRefundInsurerByIdWsProviderImpl refundInsurerByIdWsProvider;
	@Autowired
	private SearchRefundInsurerRequestImpl refundInsurerRequestProvider;
	@Autowired
	private SearchOverRefundCompanyWsProviderImpl overRefundCompanyWsProvider;
	@Autowired
	private SearchSplitEmployeeRefundWsProviderImpl searchSplitEmployeeRefundWsProvider;
	@Autowired
	private SearchRefundInquiryInsurerWsProviderImpl searchRefundInquiryInsurerWsProvider;
	@Autowired
	private SearchConTbPaybackRequestInfoWsProviderImpl searchConTbPaybackRequestInfoWsProvider;
	@Autowired
	private GetPaybackRefundRequestByReceiveNoWsProviderImpl getPaybackRefundRequestByReceiveNoWsProvider;
	@Autowired
	private GetPaybackRefundRequestByIdWsProviderImpl getPaybackRefundRequestByIdWsProvider;
	@Autowired
	private GetRefundInsurerMultipleCompanyInfoByPeriodsWsProviderImpl getRefundInsurerMultipleCompanyInfoByPeriodsWsProvider;
	@Autowired
	private GetRefundInsurerMultipleCompanyInfoByPeriodWsProviderImpl getRefundInsurerMultipleCompanyInfoByPeriodWsProvider;
	@Autowired
	private SearchPaybackRefundRequestByIdWsProviderImpl searchPaybackRefundRequestByIdWsProvider;
	@Autowired
	private SearchRefundRequestCompanyBatchWsProviderImpl searchRefundRequestCompanyBatchWsProvider;
	@Autowired
	private SearchRefundRequestEmployeeBatchWsProviderImpl searchRefundRequestEmployeeBatchWsProvider;
	@Autowired
	private SearchRequesterCompanyBatchWsProviderImpl searchRequesterCompanyBatchWsProvider;
	@Autowired
	private SearchPaybackRequestCompanyBatchWsProviderImpl searchPaybackRequestCompanyBatchWsProvider;
	@Autowired
	private SearchPaybackRequestInsurerBatchWsProviderImpl searchPaybackRequestInsurerBatchWsProvider;
	@Autowired
	private SearchPaybackRequestEmployeeBatchWsProviderImpl searchPaybackRequestEmployeeBatchWsProvider;
	@Autowired
	private SearchRefundRequestInsurerBatchWsProviderImpl searchRefundRequestInsurerBatchWsProvider;
	@Autowired
	private SearchRequesterInsurerBatchWsProviderImpl searchRequesterInsurerBatchWsProvider;
	@Autowired
	private SearchRequesterEmployeeBatchWsProviderImpl searchRequesterEmployeeBatchWsProvider;
	@Autowired
	private ConTrReceiveEmployeeWsProviderImpl conTrReceiveEmployeeWsProvider;
	@Autowired
	private SearchOverRefundEmployeeNoticeWsProviderImpl searchOverRefundEmployeeNoticeWsProvider;
	@Autowired
	private SearchRequester39BatchWsProviderImpl searchRequester39BatchWsProvider;
	@Autowired
	private SearchRequester33BatchWsProviderImpl searchRequester33BatchWsProvider;
	@Autowired
	private SearchPaybackCompanyBatchWsProviderImpl searchPaybackCompanyBatchWsProvider;
	@Autowired
	private SearchPaybackInsurerBatchWsProviderImpl searchPaybackInsurerBatchWsProvider;
	@Autowired
	private SearchPaybackEmployeeBatchWsProviderImpl searchPaybackEmployeeBatchWsProvider;
	@Autowired
	private SearchRefundDeleteCancelHistWsProviderImpl searchRefundDeleteCancelHistWsProvider;
	@Autowired
	private RefundDeleteCancelHistByIdWsProviderImpl refundDeleteCancelHistByIdWsProvider;
	@Autowired
	private SearchRefundHistoryWsProviderImpl searchRefundHistoryWsProvider;
	@Autowired
	private SearchRefundHistoryByIdWsProviderImpl searchRefundHistoryByIdWsProvider;
	@Autowired
	private SearchStatisticRefundReportWsProviderImpl searchStatisticRefundReportWsProvider;
	@Autowired
	private GetRefundEmployeeByIdWsProviderImpl getRefundEmployeeByIdWsProvider;
	@Autowired
	private SearchOverHoldReceiveWsProviderImpl searchOverHoldReceiveWsProvider;
	@Autowired
	private GetHoldReceiveByIdWsProviderImpl getHoldReceiveByIdWsProvider;
	@Autowired
	private GetRefundInsurerHoldReceiveByIdWsProviderImpl getRefundInsurerHoldReceiveByIdWsProvider;
	@Autowired
	private SearchM33CompanyPaybackListWsProviderImpl searchM33CompanyPaybackListWsProvider;
	@Autowired
	private SearchM33CompanyRefundSuccessListWsProviderImpl searchM33CompanyRefundSuccessListWsProvider;
	@Autowired
	private SearchM33EmployeePaybackListWsProviderImpl searchM33EmployeePaybackListWsProvider;
	@Autowired
	private SearchM33EmployeeRefundSuccessListWsProviderImpl searchM33EmployeeRefundSuccessListWsProvider;
	@Autowired
	private SearchM39PaybackListWsProviderImpl searchM39PaybackListWsProvider;
	@Autowired
	private SearchM39RefundSuccessListWsProviderImpl searchM39RefundSuccessListWsProvider;
	@Autowired
	private SearchRequesterM39WsProviderImpl searchRequesterM39WsProvider;
	@Autowired
	private InitiateWsProviderImpl initiateWsProvider;

	@GetMapping(value = "/start")
	public String Started() {
		return "Started !!!!";
	}

	@GetMapping(value = "/initiate")
	public IResponseBean reloadSystemConfig(RequestBean request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return initiateWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" initiate ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-over-refund-company-by-employer")
	public IResponseBean searchOverRefund(@RequestBody SearchOverRefundCompanyByEmprRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return overRefundCompanyByEmprWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchOverRefund ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-contribution-company-by-employer")
	public IResponseBean searchConCompany(@RequestBody SearchContributionCompanyByEmprRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return contributionCompanyByEmprWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchConCompany ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-over-refund-insurer")
	public IResponseBean searchOverRefundInsurer(@RequestBody SearchRefundInsurerRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return overRefundInsurerWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchOverRefundInsurer ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-contribution-insurer")
	public IResponseBean searchContributionInsurer(@RequestBody SearchContributionInsurerRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return contributionInsurerWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchContributionInsurer ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-company-info-by-ids")
	public IResponseBean searchRefundCompany(@RequestBody SearchRefundCompanyRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return refundCompanyWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchRefundCompany ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-company-request-info-by-id")
	public IResponseBean searchRefundCompanyChange(@RequestBody SearchRefundCompanyByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return refundCompanyChangeWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchRefundCompany ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-over-refund-employee")
	public IResponseBean searchOverRefundEmployee(@RequestBody SearchOverRefundEmployeeRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return overRefundEmployeeWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchOverRefundEmployee ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-insurer-m39-request-info-by-id")
	public IResponseBean searchRefundInsurerById(@RequestBody SearchRefundInsurerByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return refundInsurerByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" searchRefundInsurerById ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-insurer-m39-info-by-ids")
	public IResponseBean searchRefundInsurerByIds(@RequestBody SearchRefundInsurerDetailByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return refundInsurerRequestProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRefundInsurerByIds", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-company")
	public IResponseBean searchOverRefundCompany(@RequestBody SearchOverRefundCompanyByEmprRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return overRefundCompanyWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchOverRefundCompanyPeriod", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-split-employee-refund")
	public IResponseBean getSplitEmployeeRefundWsProvider(@RequestBody SearchSplitEmployeeRefundRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchSplitEmployeeRefundWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getSplitEmployeeRefundWsProvider", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-insurer")
	public IResponseBean searchRefundInsurer(@RequestBody SearchRefundInquiryInsurerRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundInquiryInsurerWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRefundInsurer", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@Deprecated
	@PostMapping(value = "/get-payback-request-info")
	public IResponseBean searchPaybackRequestInfo(@RequestBody SearchConTbPaybackRequestRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchConTbPaybackRequestInfoWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackRequestInfo", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-payback-request-by-receive-no")
	public IResponseBean getPaybackRefundRequestByReceiveNo(@RequestBody GetRefundRequestByReceiveNoRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getPaybackRefundRequestByReceiveNoWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getPaybackRefundRequestByReceiveNo ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-payback-request-by-id")
	public IResponseBean getPaybackRefundRequestById(@RequestBody GetByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getPaybackRefundRequestByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getPaybackRefundRequestById ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-insurer-multiple-company-info-by-periods")
	public IResponseBean getRefundInsurerMultipleInfoByPeriods(@RequestBody GetRefundInsurerMultipleCompanyInfoByPeriodsRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getRefundInsurerMultipleCompanyInfoByPeriodsWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getRefundInsurerMultipleInfoByPeriods ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-insurer-multiple-company-info-by-period")
	public IResponseBean getRefundInsurerMultipleInfoByPeriod(@RequestBody GetRefundInsurerMultipleCompanyInfoByPeriodRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getRefundInsurerMultipleCompanyInfoByPeriodWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getRefundInsurerMultipleInfoByPeriod ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-payback-request-by-payback-request-id")
	public IResponseBean getPaybackRefundByPaybackRequestId(@RequestBody SearchPaybackRequestByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackRefundRequestByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getPaybackRefundByPaybackRequestId ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-request-company-batch")
	public IResponseBean searchRefundRequestCompanyBatch(@RequestBody SearchRefundRequestBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundRequestCompanyBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRefundRequestCompanyBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-request-employee-batch")
	public IResponseBean searchRefundRequestEmployeeBatch(@RequestBody SearchRefundRequestBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundRequestEmployeeBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRefundRequestEmployeeBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-requester-company-batch")
	public IResponseBean searchRequesterCompanyBatch(@RequestBody SearchRequesterCompanyBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRequesterCompanyBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRequesterCompanyBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-payback-request-company-batch")
	public IResponseBean searchPaybackRequestCompanyBatch(@RequestBody SearchPaybackRequestCompanyBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackRequestCompanyBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackRequestCompanyBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-payback-request-insurer-batch")
	public IResponseBean searchPaybackRequestInsurerBatch(@RequestBody SearchPaybackRequestInsurerBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackRequestInsurerBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackRequestInsurerBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-payback-request-employee-batch")
	public IResponseBean searchPaybackRequestEmployeeBatch(@RequestBody SearchPaybackRequestEmployeeBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackRequestEmployeeBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackRequestEmployeeBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-request-insurer-batch")
	public IResponseBean searchRefundRequestInsurerBatch(@RequestBody SearchRefundRequestBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundRequestInsurerBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRefundRequestInsurerBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-requester-insurer-batch")
	public IResponseBean searchRequesterInsurerBatch(@RequestBody SearchRequesterInsurerBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRequesterInsurerBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRequesterInsurerBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-requester-employee-batch")
	public IResponseBean searchRequesterEmployeeBatch(@RequestBody SearchRequesterEmployeeBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRequesterEmployeeBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRequesterEmployeeBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-employee-detail")
	public IResponseBean getEmployeeDetail(@RequestBody SearchEmployeeDetailRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return conTrReceiveEmployeeWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getEmployeeDetail", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-over-refund-employee-notice")
	public IResponseBean searchOverRefundEmployeeNotice(@RequestBody SearchOverRefundEmployeeNoticeRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchOverRefundEmployeeNoticeWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchOverRefundEmployeeNotice", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-requester39-batch")
	public IResponseBean searchRequester39Batch(@RequestBody SearchRequester39Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRequester39BatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRequester39Batch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-requester33-batch")
	public IResponseBean searchRequester33Batch(@RequestBody SearchRequester33Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRequester33BatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRequester33Batch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-payback-company-batch")
	public IResponseBean searchPaybackCompanyBatch(@RequestBody SearchPaybackCompanyBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackCompanyBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackCompanyBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-payback-insurer-batch")
	public IResponseBean searchPaybackInsurerBatch(@RequestBody SearchPaybackInsurerBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackInsurerBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackInsurerBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-payback-employee-batch")
	public IResponseBean searchPaybackEmployeeBatch(@RequestBody SearchPaybackEmployeeBatchRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchPaybackEmployeeBatchWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchPaybackEmployeeBatch", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-delete-cancel-history")
	public IResponseBean searchRefundDeleteCancelHistory(@RequestBody SearchRefundDeleteCancelHistoryRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundDeleteCancelHistWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-refund-delete-cancel-history", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-delete-cancel-history-by-id")
	public IResponseBean searchRefundDeleteCancelHistoryById(@RequestBody RefundDeleteCancelHistoryByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return refundDeleteCancelHistByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-refund-delete-cancel-history-by-id", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-history")
	public IResponseBean searchRefundHistory(@RequestBody SearchRefundHistoryRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundHistoryWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("search-refund-history", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-history-by-id")
	public IResponseBean searchRefundHistoryById(@RequestBody SearchRefundHistoryByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRefundHistoryByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("search-refund-history-by-id", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-static-refund-report-batch")
	public IResponseBean searchStaticRefundReport(@RequestBody SearchStatisticRefundReportRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchStatisticRefundReportWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchStaticRefundReport", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-employee-by-id")
	public IResponseBean getRefundEmployeeById(@RequestBody GetRefundEmployeeByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getRefundEmployeeByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getRefundEmployeeById", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-refund-hold-receive")
	public IResponseBean searchRefundHoldReceive(@RequestBody SearchOverHoldReceiveRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchOverHoldReceiveWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("searchRefundHoldReceive", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-hold-receive-by-id")
	public IResponseBean getHoldReceiveById(@RequestBody GetHoldReceiveByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getHoldReceiveByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getHoldReceiveById", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/get-refund-insurer-hold-receive-by-id")
	public IResponseBean getRefundInsurerHoldReceiveById(@RequestBody GetRefundInsurerHoldReceiveByIdRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return getRefundInsurerHoldReceiveByIdWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("getRefundInsurerHoldReceiveById", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-m33-company-refund-success-list")
	public IResponseBean searchM33CompanyRefundSuccessList(@RequestBody SearchM33PaybackListRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchM33CompanyRefundSuccessListWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-m33-company-refund-success-list", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-m33-employee-refund-success-list")
	public IResponseBean searchM33EmployeeRefundSuccessList(@RequestBody SearchM33PaybackListRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchM33EmployeeRefundSuccessListWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-m33-employee-refund-success-list", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-m39-refund-success-list")
	public IResponseBean searchM39RefundSuccessList(@RequestBody SearchM39PaybackListRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchM39RefundSuccessListWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-m39-refund-success-list", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-m33-company-payback-list")
	public IResponseBean searchM33CompanyPaybackList(@RequestBody SearchM33PaybackListRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchM33CompanyPaybackListWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-m33-company-payback-list", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-m33-employee-payback-list")
	public IResponseBean searchM33EmployeePaybackList(@RequestBody SearchM33PaybackListRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchM33EmployeePaybackListWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-m33-employee-payback-list", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/search-m39-payback-list")
	public IResponseBean searchM39PaybackList(@RequestBody SearchM39PaybackListRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchM39PaybackListWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-m39-payback-list", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}
	
	@PostMapping(value = "/search-requester-m39")
	public IResponseBean searchRequesterM39(@RequestBody SearchRequesterM39Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return searchRequesterM39WsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error("/search-requester-m39", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

}
