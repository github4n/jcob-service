package com.aicai.jcob.tjexpert.common.domain.constant;  

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午2:38:13 
 * 专家申请审核状态
 */
public class TjExpertApplyCheckStatus extends BaseType{

	private static final long serialVersionUID = -3478525615242232887L;

	protected TjExpertApplyCheckStatus(Integer index, String description) {
		super(index, description);
	}

	/**审核状态：0待审核，1审核通过，2审核未通过*/
	public static final TjExpertApplyCheckStatus wait_check = new TjExpertApplyCheckStatus(0,"待审核");
	public static final TjExpertApplyCheckStatus pass_check = new TjExpertApplyCheckStatus(1,"审核通过为实验室专家");
	public static final TjExpertApplyCheckStatus cancel_check = new TjExpertApplyCheckStatus(2,"审核未通过");//终止
	public static final TjExpertApplyCheckStatus check_format_EXPERT = new TjExpertApplyCheckStatus(3,"审核升为正式专家");//终止
	public static final TjExpertApplyCheckStatus check_normal = new TjExpertApplyCheckStatus(4,"正式降为普通用户");//终止
	public static List<TjExpertApplyCheckStatus> getAllList(){
		return getAll(TjExpertApplyCheckStatus.class);
	}
	
	public static TjExpertApplyCheckStatus valueOf(Integer index){
		return  valueOf(TjExpertApplyCheckStatus.class,index);
	}
}
 