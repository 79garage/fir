package th.go.sso.newcore.fir.receipt.transaction.ws.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import th.go.sso.newcore.fir.common.dto.IResponseBean;
import th.go.sso.newcore.fir.common.dto.RequestBean;
import th.go.sso.newcore.fir.common.dto.UserPrincipal;
import th.go.sso.newcore.fir.common.utils.WebServiceUtils;
import th.go.sso.newcore.fir.receipt.transaction.ws.provider.impl.*;
import th.go.sso.newcore.fir.receipt.transaction.ws.provider.request.*;

import javax.servlet.http.HttpServletRequest;

//test only
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/financial")
public class ReceiptTransactionWsProvider {

	private static final Logger log = LoggerFactory.getLogger(ReceiptTransactionWsProvider.class);

	@Autowired
	private SaveReceipt33WsProviderImpl saveReceipt33WsProvider;

	@Autowired
	private SaveReceipt39WsProviderImpl saveReceipt39WsProvider;

	@Autowired
	private SaveReceipt40WsProviderImpl saveReceipt40WsProvider;
	
	@Autowired
	private InitiateWsProviderImpl initiateWsProvider;

	@Autowired
	private SaveFailCheque33WsProviderImpl saveFailCheque33WsProvider;

	@Autowired
	private SaveFailCheque39WsProviderImpl saveFailCheque39WsProvider;

	@Autowired
	private SaveReceivePaybackWsProviderImpl saveReceivePaybackWsProvider;

	@Autowired
	private SaveTransferFundWsProviderImpl saveTransferFundWsProvider;

	@Autowired
	private SaveOtherWsProviderImpl saveOtherWsProvider;

	@Autowired
	private CancelReceiptBookWsProviderImpl cancelReceiptBookWsProvider;

	@Autowired
	private CancelReceiptMoneyOrderWsProviderImpl cancelReceiptMoneyOrderWsProvider;

	@Autowired
	private EditReceiptBookWsProviderImpl editReceiptBookWsProvider;

	@Autowired
	private EditReceiptMoneyOrderWsProviderImpl editReceiptMoneyOrderWsProvider;

	@GetMapping(value = "/start")
	public String startCalled() {
		return "Started !!!!";
	}

	@GetMapping(value = "/initiate")
	public IResponseBean reloadSystemConfig(RequestBean request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return initiateWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /initiate ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-receive-payment-m33")
	public IResponseBean saveReceipt33WsProvider(@RequestBody SaveReceipt33Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return saveReceipt33WsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /save-receive-payment-m33 ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-receive-payment-m39")
	public IResponseBean saveReceipt39WsProvider(@RequestBody Receipt39Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return saveReceipt39WsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" save-receive-payment-m39 ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-receive-payment-m40")
	public IResponseBean saveReceipt40WsProvider(@RequestBody SaveReceipt40Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return saveReceipt40WsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" save-receive-payment-m40 ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-fail-cheque-payment-m33")
	public IResponseBean saveFailCheque33WsProvider(@RequestBody SaveReceipt33Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return saveFailCheque33WsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /save-fail-cheque-payment-m33 ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-fail-cheque-payment-m39")
	public IResponseBean saveFailCheque39WsProvider(@RequestBody Receipt39Request request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			return saveFailCheque39WsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /save-fail-cheque-payment-m39 ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-receive-payback")
	public IResponseBean saveReceivePaybackWsProvider(@RequestBody PaybackMainRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/save-receive-payback : {}", WebServiceUtils.toJson(request));
			return saveReceivePaybackWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /save-receive-payback ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-receive-transfer-fund")
	public IResponseBean saveReceiveTransferFundWsProvider(@RequestBody TransferFundMainRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/save-receive-transfer-fund : {}", WebServiceUtils.toJson(request));
			return saveTransferFundWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /save-receive-transfer-fund ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/save-receive-other")
	public IResponseBean saveReceiveOtherWsProvider(@RequestBody SaveOtherRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/save-receive-other : {}", WebServiceUtils.toJson(request));
			return saveOtherWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /save-receive-other ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/cancel-receipt-book")
	public IResponseBean cancelReceiptBook(@RequestBody EditReceiptBookRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/cancel-receipt-book : {}", WebServiceUtils.toJson(request));
			return cancelReceiptBookWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /cancel-receipt-book ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/cancel-receipt-money-order")
	public IResponseBean cancelReceiptMoneyOrder(@RequestBody EditReceiptBookRequest request, HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/cancel-receipt-money-order : {}", WebServiceUtils.toJson(request));
			return cancelReceiptMoneyOrderWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" /cancel-receipt-money-order ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/edit-receipt-book")
	public IResponseBean editReceiptBook(@RequestBody EditReceiptRequest request,
											   HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/edit-receipt-book : {}", WebServiceUtils.toJson(request));
			return editReceiptBookWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" edit-receipt ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}

	@PostMapping(value = "/edit-receipt-money-order")
	public IResponseBean editReceiptMoneyOrder(@RequestBody EditReceiptRequest request,
											   HttpServletRequest servletRequest, @AuthenticationPrincipal UserPrincipal user) {
		try {
			log.debug("/edit-receipt-money-order : {}", WebServiceUtils.toJson(request));
			return editReceiptMoneyOrderWsProvider.startService(request, servletRequest, user);
		} catch (Exception e) {
			log.error(" edit-receipt ", e);
		}
		return WebServiceUtils.getDefaultErrorResponseBean();
	}
}
