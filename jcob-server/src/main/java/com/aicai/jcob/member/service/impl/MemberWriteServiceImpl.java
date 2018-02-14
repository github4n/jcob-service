package com.aicai.jcob.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.utils.ThrowableUtil;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.MemberLogin;
import com.aicai.jcob.member.common.domain.MemberUseChargeWay;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.common.domain.result.MemberResult;
import com.aicai.jcob.member.common.service.MemberWriteService;
import com.aicai.jcob.member.manager.MemberLoginManager;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.manager.MemberChargeChannelManager;
import com.aicai.jcob.membercharge.manager.MemberChargeWayManager;
import com.aicai.jcob.membercharge.manager.MemberUseChargeWayManager;

public class MemberWriteServiceImpl implements MemberWriteService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager;
	
	@Autowired
	@Qualifier("memberLoginManagerImpl")
	private MemberLoginManager memberLoginManager;
	
	@Autowired
	@Qualifier("memberChargeWayManagerImpl")
	private MemberChargeWayManager memberChargeWayManager;
	
	@Autowired
	@Qualifier("memberChargeChannelManagerImpl")
	private MemberChargeChannelManager memberChargeChannelManager;
	
	@Autowired
	@Qualifier("memberUseChargeWayManagerImpl")
	private MemberUseChargeWayManager memberUseChargeWayManager;
	
	@Autowired
	@Qualifier("memberOperLogManagerImpl")
	private MemberOperLogManager memberOperLogManager;
	
	@Override
	public ModelResult<Member> register(Member member, RegisterOption registerOption,
			MemberOperOption memberOperOption) {
		return memberManager.register(member, registerOption, memberOperOption);
	}

	@Override
	public ModelResult<Member> loginByPassword(Integer loginType, String loginId, String password,
			MemberOperOption memberOperOption) {
		return memberManager.queryMemberByPassword(loginType, loginId, password, memberOperOption);
	}

	@Override
	public ModelResult<Member> thirdLogin(Integer loginType, String loginId, MemberOperOption memberOperOption) {
		return memberManager.queryMemberByLoginId(loginType, loginId, memberOperOption);
	}

	@Override
	public ModelResult<MemberLogin> binding2Member(Long memberId, Integer newLoginType, String newLoginId,
			MemberOperOption memberOperOption) {
		return memberManager.binding2Member(memberId, newLoginType, newLoginId, memberOperOption);
	}

	@Override
	public ModelResult<String> loginByPassword(Integer loginType, String loginId, String password,
			String machineId, MemberOperOption memberOperOption) {
		return memberManager.queryTokenByPassword(loginType, loginId, password, machineId, memberOperOption);
	}

	@Override
	public ModelResult<String> thirdLogin(Integer loginType, String loginId, String machineId,
			MemberOperOption memberOperOption) {
		return memberManager.queryTokenByLoginId(loginType, loginId, machineId, memberOperOption);
	}

	@Override
	public ModelResult<Member> queryMemberByToken(String token, String machineId, MemberOperOption memberOperOption) {
		return memberManager.queryMemberByTokenAndMachineId(machineId, token,memberOperOption);
	}

	@Override
	public ModelResult<Member> loginByToken(String machineId, String token,MemberOperOption memberOperOption) {
		return memberManager.queryMemberByTokenAndMachineId(machineId, token,memberOperOption); 
	}

	@Override
	public ModelResult<Integer> logoutByToken(String machineId, String token,MemberOperOption memberOperOption) {
		return memberManager.logoutByToken(machineId, token,memberOperOption);
	}

	@Override
	public ModelResult<Boolean> resetMemberPasswordByMobile(String mobile, String newPassword,MemberOperOption memberOperOption) {
		return memberManager.updateMemberPasswordByMobile(mobile, newPassword, memberOperOption);
	}

	/*@Override
	public ModelResult<Boolean> modifyMemberBasicInfo(Long memberId,String mobile,String certNo,String nickName,String realName,MemberOperOption memberOperOption) {
		return memberManager.updateMemberBaseInfoByMember(memberId, mobile, certNo, nickName, realName, memberOperOption);
	}*/

	@Override
	public ModelResult<Boolean> checkMebmerRegisterIsValid(long memberId) {
		return memberManager.checkMobileIsValid(memberId);
	}

	@Override
	public ModelResult<Boolean> modifyMemberNickName(Long memberId, String nickName) {
		return memberManager.modifyMemberNickName(memberId, nickName);
	}

	@Override
	public ModelResult<Member> queryMemberBymemberId(Long memberId) {
		return memberManager.queryMemberById(memberId);
	}

	@Override
	public ModelResult<Member> queryValidMemberByPhone(String phone) {
		return memberManager.queryValidMemberByPhone(phone);
	}

	@Override
	public PageResult<MemberResult> queryMemberExpertByPage(
			MemberExpertOption option, DataPage<Member> page) {
		PageResult<MemberResult> pageResult = new PageResult<MemberResult>();
		try {
			pageResult.setPage(memberManager.pageMemberVoByOption(option, page));
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
		}
		return pageResult;
	}

	@Override
	public ModelResult<MemberResult> queryMemberExpertByMemberId(Long memberId) {
		return memberManager.queryMemberVoByMemberId(memberId);
	}

	@Override
	public ModelResult<Member> thirdRegister(Member member,
			RegisterOption registerOption, MemberOperOption memberOperOption) {
		return memberManager.thirdRegister(member, registerOption, memberOperOption);
	}

	@Override
	public ModelResult<MemberLogin> thirdIsAlreadyRegister(String loginId) {
		return memberLoginManager.thirdIsAlreadyRegister(loginId);
	}

	@Override
	public ModelResult<MemberChargeWay> queryMemberChargeWay(int chargeWayIndex,int clientType) {
		return new ModelResult<MemberChargeWay>(
				memberChargeWayManager.queryMemberChargeWayByChargeWayIndexClientType(chargeWayIndex, clientType));
	}

	@Override
	public ModelResult<Boolean> checkNickNameExists(String nickName,Long memberId) {
		return new ModelResult<Boolean>(memberManager.checkNickNameExists(nickName,memberId));
	}

	@Override
	public ModelResult<MemberChargeChannel> queryMemberChargeChannel(long channelId) {
		return new ModelResult<MemberChargeChannel>(memberChargeChannelManager.queryMemberChargeChannelById(channelId));
	}

	@Override
	public ModelResult<MemberChargeWay> queryMemberChargeWayById(int chargeWayId) {
		return new ModelResult<MemberChargeWay>(memberChargeWayManager.queryMemberChargeWayById(chargeWayId));
	}

	@Override
	public ModelResult<MemberChargeChannel> queryMemberChargeChannelByChannelIndex(
			int channelIndex) {
		return new ModelResult<MemberChargeChannel>(memberChargeChannelManager.queryMemberChargeChannelByChannelIndex(channelIndex));
	}

	@Override
	public ModelResult<Boolean> modifyMemberPhoneNo(Long memberId,String oldPhone,
			String newPhone) {
		return memberManager.modifyMemberPhoneNo(memberId,oldPhone, newPhone);
	}

	@Override
	public ModelResult<Boolean> modifyMemberIcon(Long memberId, String iconPath) {
		return memberManager.updateMemberIcon(memberId, iconPath);
	}

	@Override
	public ModelResult<Boolean> passMemberPhoneValid(Long memberId, String phone) {
		return memberManager.addMemberPhoneValid(memberId, phone);
	}

	@Override
	public ModelResult<Member> queryMemberByToken(String token, String machineId) {
		return memberManager.queryMemberByTokenAndMachineId(machineId, token);
	}

	@Override
	public List<Member> queryMemberByIdArrayAndOption(Long[] idArray,
			MemberExpertOption option) {
		return memberManager.queryMemberByIdArrayAndOption(idArray, option) ;
	}

	@Override
	public ModelResult<Boolean> checkNickNameExists(String nickName) {
		return new ModelResult<Boolean>(memberManager.checkNickNameExists(nickName));
	}

	@Override
	public ModelResult<List<MemberUseChargeWay>> queryMemberRecentlyUseChargeWay(
			Long memberId) {
		return memberUseChargeWayManager.queryMemberUseChargeWayTop5(memberId);
	}

	@Override
	public ModelResult<Boolean> cancelMemberUseChargeWay(long id,long memberId) {
		return new ModelResult<Boolean>(memberUseChargeWayManager.updateMemberUseChargeWayStatusFailById(id,memberId) > 0);
	}

	@Override
	public ModelResult<String> getMemberRegisterUserIp(long memberId) {
		return memberOperLogManager.getMemberRegisterUserIp(memberId);
	}

	@Override
	public ModelResult<Boolean> authenticateMember(long memberId,
			String realName, String certNo) {
		return memberManager.updateMemberRealNamePhoneCertification(memberId, realName, certNo);
	}

	@Override
	public ModelResult<Member> queryMemberByThirdLoginId(int loginType,
			String loginId) {
		return memberManager.queryMemberByThirdLoginId(loginType, loginId);
	}

	@Override
	public ModelResult<Boolean> authenticateMemberAndPhone(long memberId,
			String realName, String certNo, String phone) {
		return memberManager.updateMemberRealNamePhoneCertificationAndPhone(memberId, realName, certNo, phone);
	}

	@Override
	public ModelResult<List<Member>> queryMemberListByIdList(List<Long> memberIdList) {
		return new ModelResult<List<Member>>(memberManager.queryMemberListByIdList(memberIdList));
	}

}
