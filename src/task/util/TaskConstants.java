package task.util;

/**
 * The Class TaskConstants.
 * 
 * @date 2013-7-2 10:50:39
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public final class TaskConstants {

	/** 订单. */
	public static final int WORKUNIT_TYPE_ORDER = 0;

	/** 自建联盟订单. */
	public static final int WORKUNIT_TYPE_ORDER_ACCOUNT = 1;

	/** 店铺订单. */
	public static final int WORKUNIT_TYPE_SHOP_ORDER = 2;

	/** 已完成店铺订单. */
	public static final int WORKUNIT_TYPE_SHOP_ORDER_COMPLETED = 3;

	/** 商品兑换券. */
	public static final int WORKUNIT_TYPE_PRODUCT_COUPON = 4;

	/** 已使用代金券. */
	public static final int WORKUNIT_TYPE_VOUCHER_USED = 5;

	/** 返还代金券. */
	public static final int WORKUNIT_TYPE_VOUCHER_USED_RETURN = 6;

	/** pop店铺订单. */
	public static final int WORKUNIT_TYPE_POP_SHOP_ORDER = 7;
	
	/** 预售订单. */
	public static final int WORKUNIT_TYPE_ORDER_PRE_SALE = 8;
	
	/** 异常数据. */
	public static final int WORKUNIT_TYPE_EXCEP_RECORD = 9;
	
	/** reids库存数据. */
	public static final int WORKUNIT_TYPE_CLOUD_INVENTORY = 10;
	

	/** 订单. */
	public static final String WORKUNIT_TYPE_NAME_ORDER = "订单";

	/** 自建联盟订单(自建联盟指的是业务，其本质仍然是普通订单). */
	public static final String WORKUNIT_TYPE_NAME_ORDER_ACCOUNT = "自建联盟订单";

	/** 店铺订单. */
	public static final String WORKUNIT_TYPE_NAME_SHOP_ORDER = "店铺订单";

	/** 已完成店铺订单. */
	public static final String WORKUNIT_TYPE_NAME_SHOP_ORDER_COMPLETED = "已完成店铺订单";

	/** 商品兑换券. */
	public static final String WORKUNIT_TYPE_NAME_PRODUCT_COUPON = "商品兑换券";

	/** 已使用代金券. */
	public static final String WORKUNIT_TYPE_NAME_VOUCHER_USED = "已使用代金券";

	/** 返还代金券. */
	public static final String WORKUNIT_TYPE_NAME_VOUCHER_USED_RETURN = "返还代金券";

	/** pop店铺订单. */
	public static final String WORKUNIT_TYPE_NAME_POP_SHOP_ORDER = "店铺订单POP";
	
	/** 预售订单. */
	public static final String WORKUNIT_TYPE_NAME_ORDER_PRE_SALE = "预售订单";
	
	/** 异常数据. */
	public static final String WORKUNIT_TYPE_NAME_EXCEP_RECORD = "异常数据";
	
	/** reids库存数据. */
	public static final String WORKUNIT_TYPE_NAME_CLOUD_INVENTORY = "云端库存";
	
	/** 待处理. */
	public static final int EXCEP_RECORD_STATUS_TO_PROCESS = 0;

	/** 处理成功. */
	public static final int EXCEP_RECORD_STATUS_SUCCESS = 1;

	/** 处理失败. */
	public static final int EXCEP_RECORD_STATUS_FAILED = 2;
	
	/** 警告. */
	public static final int EXCEP_RECORD_STATUS_WARN = 3;
	
	/** 挂起. */
	public static final int EXCEP_RECORD_STATUS_SUSPEND = 4;
	
	/** 默认类型:主键冲突,可处理. */
	public static final int EXCEP_RECORD_TYPE_DEFAUT_UQPK_E = 0;
	
	/** 业务异常，不处理，警告状态. */
	public static final int EXCEP_RECORD_TYPE_BUSINESS_E = 1;
	
	/** 人工干预，不处理，挂起(除非手动操作，否则不自动处理). */
	public static final int EXCEP_RECORD_TYPE_MANUAL_E = 2;
	
	/** 异常记录列表页面，每页显示的记录数. */
	public static final int EXCEP_RECORD_PAGE_RECORD_COUNT = 10;

}
