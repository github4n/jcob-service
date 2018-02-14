package com.aicai.jcob.tjexpert.common.domain.constant;  

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月28日 下午2:19:11 
 * 专家申请进度
 */
public class ExpertApplyProgress extends BaseType{

	private static final long serialVersionUID = -1657567877108920661L;

	protected ExpertApplyProgress(Integer index, String description) {
		super(index, description);
	}
	
    /**
     * 未申请 （终止）0
     * 申请提交 1 
     * 申请提交   -> 未通过（终止）2
     * 申请提交   —> 通过 3
     * 申请提交   —> 通过－>正式专家失败（终止）4
     * 申请提交   —> 通过－>发布了一条方案 5
     * 申请提交   —> 通过－>发布了一条方案－》正式专家失败（终止）6
     * 申请提交   —> 通过－>发布了一条方案－》正式专家（终止）7
     */
	public static final ExpertApplyProgress NOT_APPLY = new ExpertApplyProgress(0,"未申请");
	public static final ExpertApplyProgress IN_CHECK = new ExpertApplyProgress(1,"申请提交成功,审核中");
	public static final ExpertApplyProgress NOT_PASS_FIRST_CHECK = new ExpertApplyProgress(2,"初审未通过");
	public static final ExpertApplyProgress BE_TEST_EXPERT = new ExpertApplyProgress(3,"初审通过,成为实验室专家");
	public static final ExpertApplyProgress NOt_EXPERT_after_test_expert = new ExpertApplyProgress(4,"成为实验室专家后,申请专家失败");
	public static final ExpertApplyProgress test_expert_PUBLISH_TJPLAN = new ExpertApplyProgress(5,"发布了一条实验方案");
	public static final ExpertApplyProgress NOt_EXPERT_after_publish_plan = new ExpertApplyProgress(6,"发布了实验方案后，申请专家失败");
	public static final ExpertApplyProgress BE_FORMAL_EXPERT = new ExpertApplyProgress(7,"成为正式专家");
	
	
	
	public static List<ExpertApplyProgress> getAllList(){
		return getAll(ExpertApplyProgress.class);
	}
	
	public static ExpertApplyProgress valueOf(Integer index){
		return valueOf(ExpertApplyProgress.class,index);
	}
}
 