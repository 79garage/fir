package th.go.sso.newcore.cont.refund.inquiry.constant;

public class ConstantsCode {
//	Progress Status
	public static final String PENDING_REVIEW_NULL = null;    // รอพิจารณาคำขอ
	public static final String PENDING_REVIEW = "N";          // รอพิจารณาคำขอ
	public static final String PENDING_APPROVAL = "W";        // รออนุมัติคำขอ
	public static final String APPROVED = "1";                // อนุมัติ
	public static final String NOT_APPROVED = "0";            // ไม่อนุมัติ
	public static final String WAITING_PAYMENT = "S";         // รอสั่งจ่าย
	public static final String PAYMENT_SUCCESS = "P";         // สั่งจ่ายสำเร็จ
	public static final String PAYMENT_FAILED = "F";          // สั่งจ่ายไม่สำเร็จ
	public static final String CANCELED = "C";                // ยกเลิก (Canceled)
	public static final String DELETED = "D";                 // ลบแล้ว (Deleted)

}
