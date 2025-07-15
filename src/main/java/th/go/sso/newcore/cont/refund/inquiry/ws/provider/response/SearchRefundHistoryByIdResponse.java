package th.go.sso.newcore.cont.refund.inquiry.ws.provider.response;

import th.go.sso.newcore.cont.refund.inquiry.bean.*;

public class SearchRefundHistoryByIdResponse {
    private RefundRequestBean refundRequestDetail;
    private RefundPeriodActiveAndInactiveBean caseCancelAndDelete;
    private ConTbRefundRequestChangeBean caseEdit;
    private String status;

    //new response separate by refundChangeType นายจ้างยื่นขอ
    private RefundChangeEmployerBean editingEmployerChangeType01;
    private RefundChangeEmployerBranchBean editingEmployerBranchChangeType02;
    private RefundChangeEmployerPeriodBean editingEmployerPeriodChangeType03;
    private RefundChangeEmployerIdentifierBean editingEmployerIdentifierChangeType09; //new sso doesn't have this action

    //new response separate by refundChangeType m39, m33  นายจ้างหลายราย
    private RefundChangeEmployeeBean editingEmployeeChangeType04;
    private RefundChangeEmployeeReplyBean editingEmployeeReplyChangeType05;
    private RefundChangeEmployeeRequestBean editingEmployeeRequestChangeType06;
    private RefundChangeEmployeeBranchPeriodBean editingEmployeeBranchPeriodChangeType0708;
    private RefundChangeEmployeeIdentifierBean editingEmployeeIdentifierChangeTypeA;
    private RefundChangeEligibleBean editingEligibleChangeTypeB;

    public RefundRequestBean getRefundRequestDetail() {
        return refundRequestDetail;
    }

    public void setRefundRequestDetail(RefundRequestBean refundRequestDetail) {
        this.refundRequestDetail = refundRequestDetail;
    }

    public RefundPeriodActiveAndInactiveBean getCaseCancelAndDelete() {
        return caseCancelAndDelete;
    }

    public void setCaseCancelAndDelete(RefundPeriodActiveAndInactiveBean caseCancelAndDelete) {
        this.caseCancelAndDelete = caseCancelAndDelete;
    }

    public ConTbRefundRequestChangeBean getCaseEdit() {
        return caseEdit;
    }

    public void setCaseEdit(ConTbRefundRequestChangeBean caseEdit) {
        this.caseEdit = caseEdit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RefundChangeEmployerBean getEditingEmployerChangeType01() {
        return editingEmployerChangeType01;
    }

    public void setEditingEmployerChangeType01(RefundChangeEmployerBean editingEmployerChangeType01) {
        this.editingEmployerChangeType01 = editingEmployerChangeType01;
    }

    public RefundChangeEmployerBranchBean getEditingEmployerBranchChangeType02() {
        return editingEmployerBranchChangeType02;
    }

    public void setEditingEmployerBranchChangeType02(RefundChangeEmployerBranchBean editingEmployerBranchChangeType02) {
        this.editingEmployerBranchChangeType02 = editingEmployerBranchChangeType02;
    }

    public RefundChangeEmployerPeriodBean getEditingEmployerPeriodChangeType03() {
        return editingEmployerPeriodChangeType03;
    }

    public void setEditingEmployerPeriodChangeType03(RefundChangeEmployerPeriodBean editingEmployerPeriodChangeType03) {
        this.editingEmployerPeriodChangeType03 = editingEmployerPeriodChangeType03;
    }

    public RefundChangeEmployerIdentifierBean getEditingEmployerIdentifierChangeType09() {
        return editingEmployerIdentifierChangeType09;
    }

    public void setEditingEmployerIdentifierChangeType09(RefundChangeEmployerIdentifierBean editingEmployerIdentifierChangeType09) {
        this.editingEmployerIdentifierChangeType09 = editingEmployerIdentifierChangeType09;
    }

    public RefundChangeEmployeeBean getEditingEmployeeChangeType04() {
        return editingEmployeeChangeType04;
    }

    public void setEditingEmployeeChangeType04(RefundChangeEmployeeBean editingEmployeeChangeType04) {
        this.editingEmployeeChangeType04 = editingEmployeeChangeType04;
    }

    public RefundChangeEmployeeReplyBean getEditingEmployeeReplyChangeType05() {
        return editingEmployeeReplyChangeType05;
    }

    public void setEditingEmployeeReplyChangeType05(RefundChangeEmployeeReplyBean editingEmployeeReplyChangeType05) {
        this.editingEmployeeReplyChangeType05 = editingEmployeeReplyChangeType05;
    }

    public RefundChangeEmployeeRequestBean getEditingEmployeeRequestChangeType06() {
        return editingEmployeeRequestChangeType06;
    }

    public void setEditingEmployeeRequestChangeType06(RefundChangeEmployeeRequestBean editingEmployeeRequestChangeType06) {
        this.editingEmployeeRequestChangeType06 = editingEmployeeRequestChangeType06;
    }

    public RefundChangeEmployeeBranchPeriodBean getEditingEmployeeBranchPeriodChangeType0708() {
        return editingEmployeeBranchPeriodChangeType0708;
    }

    public void setEditingEmployeeBranchPeriodChangeType0708(RefundChangeEmployeeBranchPeriodBean editingEmployeeBranchPeriodChangeType0708) {
        this.editingEmployeeBranchPeriodChangeType0708 = editingEmployeeBranchPeriodChangeType0708;
    }

    public RefundChangeEmployeeIdentifierBean getEditingEmployeeIdentifierChangeTypeA() {
        return editingEmployeeIdentifierChangeTypeA;
    }

    public void setEditingEmployeeIdentifierChangeTypeA(RefundChangeEmployeeIdentifierBean editingEmployeeIdentifierChangeTypeA) {
        this.editingEmployeeIdentifierChangeTypeA = editingEmployeeIdentifierChangeTypeA;
    }

    public RefundChangeEligibleBean getEditingEligibleChangeTypeB() {
        return editingEligibleChangeTypeB;
    }

    public void setEditingEligibleChangeTypeB(RefundChangeEligibleBean editingEligibleChangeTypeB) {
        this.editingEligibleChangeTypeB = editingEligibleChangeTypeB;
    }
}
