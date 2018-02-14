package com.aicai.jcob.member.common.service;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.MemberLogin;
import com.aicai.jcob.member.common.domain.MemberUseChargeWay;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.common.domain.result.MemberResult;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;

public interface MemberWriteService {
	
	
	/**
	 * 
	 * @param member
	 * @param registerOption 指定注册类型与注册识别号，比如 手机号注册，则要写手机号
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<Member> register(Member member,RegisterOption registerOption,MemberOperOption memberOperOption);
	
	
	/**
	 * 
	 * @param loginType
	 * @param loginId
	 * @param password
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<Member> loginByPassword(Integer loginType,String loginId,String password,MemberOperOption memberOperOption);
	
	/**
	 * 
	 * @param loginType
	 * @param loginId
	 * @param password
	 * @param machineId
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<String> loginByPassword(Integer loginType,String loginId,String password,String machineId, MemberOperOption memberOperOption);
	
	/**
	 * 第三方授权登录，调用这个方法前说明经过了授权
	 * @param loginType
	 * @param loginId
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<Member> thirdLogin(Integer loginType,String loginId,MemberOperOption memberOperOption);
	/**
	 * 第三方授权登录，调用这个方法前说明经过了授权
	 * @param loginType
	 * @param loginId
	 * @param machineId
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<String> thirdLogin(Integer loginType,String loginId,String machineId,MemberOperOption memberOperOption);
	
	/**
	 * 新的LoginType，loginId 绑定到已存在的Member
	 * @param memberId
	 * @param newLoginType
	 * @param newLoginId
	 * @return
	 */
	public ModelResult<MemberLogin> binding2Member(Long memberId,Integer newLoginType,String newLoginId,MemberOperOption memberOperOption);
	
	public ModelResult<Member> loginByToken(String machineId,String token,MemberOperOption memberOperOption);
	
	public ModelResult<Integer> logoutByToken(String machineId,String token,MemberOperOption memberOperOption);
	
	/**
	 * 查询用户功能
	 * @param token
	 * @param machineId
	 * @param memberOperOption
	 * @return member信息
	 */
	public ModelResult<Member> queryMemberByToken(String token,String machineId,MemberOperOption memberOperOption);
	
	/**
	 * 查询用户功能
	 * @param token
	 * @param machineId
	 * @return member信息
	 */
	public ModelResult<Member> queryMemberByToken(String token,String machineId);
	
	/**
	 * 查询用户功能
	 * @param memberId
	 * @return
	 */
	public ModelResult<Member> queryMemberBymemberId(Long memberId);
	
	/**
	 * 查询用户列表
	 * @param memberId
	 * @return
	 */
	public ModelResult<List<Member>> queryMemberListByIdList(List<Long> memberIdList);
	
	/**
	 * 找回密码
	 * @param mobile 手机号
	 * @param newPassword 新密码 
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<Boolean> resetMemberPasswordByMobile(String mobile,String newPassword,MemberOperOption memberOperOption);
	
	/**
	 * 更改用户基本信息
	 * @param memberId 用户ID
	 * @param mobile 手机号码
	 * @param certNo 身份证号码
	 * @param nickName 昵称
	 * @param realName 真实姓名
	 * @param memberOperOption
	 * @return
	 */
	/*public ModelResult<Boolean> modifyMemberBasicInfo(Long memberId,String mobile,String certNo,String nickName,String realName,MemberOperOption memberOperOption);*/
	
	/**
	 * 验证用户手机号是否已经注册，且是否通过验证
	 * 如果手机号通过验证,返回true
	 * 如果手机号不存在或未难过验证,返回false
	 * @param mobile
	 * @return
	 */
	public ModelResult<Boolean> checkMebmerRegisterIsValid(long memberId);
	
	/**
	 * 
	 * @param memberId
	 * @param nickName
	 * @return
	 * @throws JcobServerException
	 */
	public ModelResult<Boolean> modifyMemberNickName(Long memberId,String nickName) throws JcobServerException;
	
	/**
	 * 如果返回结果为空，1.手机号不存在，2用户未通过验证
	 * @param phone
	 * @return
	 * @throws JcobServerException
	 */
	public ModelResult<Member> queryValidMemberByPhone(String phone);
	
	/**
	 * 分页查询用户信息
	 * @param option
	 * 用户手机号
	 * 用户昵称
	 * 用户id
	 * 用户等级
	 * 用户注册时间
	 * 用户的注册类型
	 * 用户的注册平台
	 * @param page
	 * @return
	 */
	public PageResult<MemberResult> queryMemberExpertByPage(MemberExpertOption option,DataPage<Member> page);
	
	/**
	 * 查询用户信息详情
	 * @param memberId
	 * @return
	 */
	public ModelResult<MemberResult> queryMemberExpertByMemberId(Long memberId);
	
	/**
	 * 第三方注册
	 * @param member
	 * @param registerOption
	 * @param memberOperOption
	 * @return
	 */
	public ModelResult<Member> thirdRegister(Member member,RegisterOption registerOption,MemberOperOption memberOperOption);
	
	/**
	 * 用户是否曾经第三方注册过(如果注册过,返回的MemberLogin对象非空,可直接获得memberId)
	 * @param loginId 第三方登录号
	 * @return
	 */
	public ModelResult<MemberLogin> thirdIsAlreadyRegister(String loginId);
	
	public ModelResult<MemberChargeWay> queryMemberChargeWay(int chargeWayIndex,int clientType);
	
	public ModelResult<MemberChargeWay> queryMemberChargeWayById(int chargeWayId);
	
	public ModelResult<MemberChargeChannel> queryMemberChargeChannel(long channelId);
	
	public ModelResult<MemberChargeChannel> queryMemberChargeChannelByChannelIndex(int channelIndex);
	
	/**
	 * 检查昵称是否已经使用
	 * @param nickName
	 * @param memberId
	 * @return
	 */
	public ModelResult<Boolean> checkNickNameExists(String nickName,Long memberId);
	
	/**
	 * 检查昵称是否已经使用
	 * @param nickName
	 * @return
	 */
	public ModelResult<Boolean> checkNickNameExists(String nickName);
	
	/**
	 * 修改用户手机号
	 * @param memberId
	 * @param oldPhone 旧手机号
	 * @param newPhone 新手机号
	 * @return
	 */
	public ModelResult<Boolean> modifyMemberPhoneNo(Long memberId,String oldPhone,String newPhone);
	
	/**
	 * 设置用户头像
	 * @param memberId 用户ID
	 * @param iconPath 头像路径
	 * @return
	 */
	public ModelResult<Boolean> modifyMemberIcon(Long memberId,String iconPath);
	
	/**
	 * 通过用户手机验证
	 * @param memberId
	 * @param phone 手机号
	 * @return
	 */
	public ModelResult<Boolean> passMemberPhoneValid(Long memberId,String phone);
	
	List<Member> queryMemberByIdArrayAndOption(Long[] idArray,MemberExpertOption option);
	
	/**
	 * 查询用户最近充值成功的5条充值方式
	 * @param memberId
	 * @return
	 */
	public ModelResult<List<MemberUseChargeWay>> queryMemberRecentlyUseChargeWay(Long memberId);
	
	/**
	 * 注销用户充值记录(修改为0，无效状态) 
	 * @param memberId	用户ID
	 * @param charge_index 充值形式(支付宝、微信、连连)
	 * @param bank_code 银行行号
	 * @param bank_card 银行卡号
	 * @param pay_type 支付方式(借记卡、信用卡)
	 * @return
	 */
	public ModelResult<Boolean> cancelMemberUseChargeWay(long id,long memberId);
	
	/**
	 * 获取用户注册时的IP地址
	 * @param memberId
	 * @return
	 */
	public ModelResult<String> getMemberRegisterUserIp(long memberId);
	
	/**
	 * 实名认证用户
	 * @param memberId
	 * @param realName
	 * @param certNo
	 * @return
	 */
	public ModelResult<Boolean> authenticateMember(long memberId,String realName,String certNo);
	
	/**
	 * 查询第三方用户在系统中注册信息
	 * @param loginType 第三方登录类型
	 * @param loginId 第三方登录号
	 * @return Member
	 */
	public ModelResult<Member> queryMemberByThirdLoginId(int loginType,String loginId);
	
	/**
	 * 实名认证用户身份与手机号码
	 * @param memberId
	 * @param realName
	 * @param certNo
	 * @param phone
	 * @return
	 */
	public ModelResult<Boolean> authenticateMemberAndPhone(long memberId,String realName,String certNo,String phone);
}
