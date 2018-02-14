package com.aicai.jcob.member.manager;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.MemberLogin;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.common.domain.option.MemberOption;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.common.domain.result.MemberResult;

public interface MemberManager {
	
	Member insertMember(Member member);
	
//	Member updateMember(Member member);
	
	int updateMemberStatusById(long memberId,int status);
	
	int updateMemberNoPwdById(long memberId,String password);
	
	/**
	 * 修改用户基本信息
	 * @param id
	 * @param certNo 身份证号码
	 * @param mobile 手机号码
	 * @param nickName 昵称
	 * @param realName 真实姓名
	 * @return
	 */
	int updateMemberMobileCertNoRealNameNickNameById(long memberId,String certNo,String mobile,String nickName,String realName);
	
	int updateMemberCertNoRealNameById(long memberId,String certNo,String realName);
	
	int updateMemberNickNameById(long memberId,String nickName);
	
	/**
	 * 更新手机号同时通过手机验证
	 * @param memberId
	 * @param mobile
	 * @return
	 */
	int updateMemberMobileAndPassValid(long memberId,String mobile);
	
	int updateMemberFlagBit(long memberId,long flagBit);
	
	ModelResult<Member> queryMemberById(long memberId);
	
	List<Member> queryMemberByPhoneRegisterType(String mobile,int registerType);
	
	List<Member> queryMemberByPhone(String mobile);
	
	ModelResult<List<Member>> queryMemberByNickName(String nickName);
	
	ModelResult<Member> queryMemberByPassword(Integer loginType,String loginId,String password,MemberOperOption memberOperOption);
	
	ModelResult<Member> queryMemberByLoginId(Integer loginType,String loginId,MemberOperOption memberOperOption) throws JcobServerException;
	
	ModelResult<MemberLogin> binding2Member(Long memberId,Integer newLoginType,String newLoginId,MemberOperOption memberOperOption);
	//
	
	ModelResult<Member> queryMemberByTokenAndMachineId(String machineId,String token,MemberOperOption memberOperOption);
	
	ModelResult<Member> queryMemberByTokenAndMachineId(String machineId,String token);
	
	ModelResult<Member> register(Member member,RegisterOption registerOption,MemberOperOption memberOperOption) throws JcobServerException;
	
	ModelResult<String> queryTokenByPassword(Integer loginType,String loginId,String password,String machineId, MemberOperOption memberOperOption);
	
	ModelResult<String> queryTokenByLoginId(Integer loginType,String loginId,String machineId, MemberOperOption memberOperOption);
	
	ModelResult<Integer> logoutByToken(String machineId, String token, MemberOperOption memberOperOption);
	
	ModelResult<Boolean> updateMemberPasswordByMobile(String mobile, String newPassword,MemberOperOption memberOperOption);
	
	int updateMemberPasswordById(Long memberId,String password);
	
	ModelResult<Boolean> updateMemberBaseInfoByMember(MemberOption memberOption,MemberOperOption memberOperOption) throws JcobServerException;
	
	public void checkNickNameAvailable(String nickName,Long memberId) throws JcobServerException;
	
	public void checkNickNameAvailable(String nickName,int lengthMax,Long memberId) throws JcobServerException;
	
	ModelResult<Boolean> checkMobileIsValid(long memberId);
	
	public ModelResult<Boolean> modifyMemberNickName(Long memberId,String nickName);
	
	/**
	 * 对密码加盐后使用MD5加密
	 * @param password 密码原文
	 * @param salt 加密使用的盐
	 * @return
	 */
	public String encodePasswordBySalt(String password,String salt);
	
	public DataPage<MemberResult> pageMemberVoByOption(MemberExpertOption option, DataPage<Member> page);
	
	public ModelResult<MemberResult> queryMemberVoByMemberId(Long memberId);
	
	/**
	 * 根据已知的ID数组与OPTION的选项为条件查询member 信息
	 * @param idArray
	 * @param option
	 * @return
	 */
	List<Member> queryMemberByIdArray(Long[] idArray);
	
	List<Member> queryMemberByIdArrayAndOption(Long[] idArray,MemberExpertOption option);
	
	ModelResult<Member> thirdRegister(Member member, RegisterOption registerOption, MemberOperOption memberOperOption);
	
	ModelResult<Member> queryValidMemberByPhone(String phone);
	
	boolean checkNickNameExists(String nickName,Long memberId);
	
	boolean checkNickNameExists(String nickName);
	
	public ModelResult<Boolean> modifyMemberPhoneNo(Long memberId,String oldPhone,String newPhone);
	
	public ModelResult<Boolean> updateMemberIcon(Long memberId,String iconPath);
	
	public ModelResult<Boolean> addMemberPhoneValid(Long memberId,String phone);
	
	public ModelResult<Boolean> updateMemberRealNamePhoneCertification(long memberId,String realName,String certNo);
	
	public ModelResult<Member> queryMemberByThirdLoginId(int loginType,String loginId);
	
	public ModelResult<Boolean> updateMemberRealNamePhoneCertificationAndPhone(long memberId,String realName,String certNo,String phone);
	
	public List<Member> queryMemberListByIdList(List<Long> memberIdList);
}
