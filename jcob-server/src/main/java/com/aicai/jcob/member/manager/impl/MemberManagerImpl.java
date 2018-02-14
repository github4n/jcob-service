package com.aicai.jcob.member.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.common.utils.JSONUtils;
import com.aicai.jcob.common.utils.MD5;
import com.aicai.jcob.common.utils.ThrowableUtil;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.MemberInfoLog;
import com.aicai.jcob.member.common.domain.MemberLogin;
import com.aicai.jcob.member.common.domain.MemberLoginToken;
import com.aicai.jcob.member.common.domain.constant.MemberFlagBit;
import com.aicai.jcob.member.common.domain.constant.MemberInfoLogStatus;
import com.aicai.jcob.member.common.domain.constant.MemberInfoType;
import com.aicai.jcob.member.common.domain.constant.MemberLoginStatus;
import com.aicai.jcob.member.common.domain.constant.MemberLoginTokenStatus;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.aicai.jcob.member.common.domain.constant.OperType;
import com.aicai.jcob.member.common.domain.constant.PasswordStrength;
import com.aicai.jcob.member.common.domain.constant.RegisterType;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.common.domain.option.MemberInfoLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.common.domain.option.MemberOption;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.common.domain.result.MemberResult;
import com.aicai.jcob.member.handler.RegisterHandler;
import com.aicai.jcob.member.handler.impl.RegisterHandlerFactory;
import com.aicai.jcob.member.manager.MemberInfoLogManager;
import com.aicai.jcob.member.manager.MemberLoginManager;
import com.aicai.jcob.member.manager.MemberLoginTokenManager;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.member.manager.impl.util.MemberValid;
import com.aicai.jcob.member.manager.impl.util.WordCensor;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertInfoQueryOption;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;

public class MemberManagerImpl implements MemberManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberDbDao;
	
	@Autowired
	@Qualifier("memberLoginManagerImpl")
	private MemberLoginManager memberLoginManager;
	
	@Autowired
	@Qualifier("memberLoginTokenManagerImpl")
	private MemberLoginTokenManager memberLoginTokenManager;
	
	@Autowired
	@Qualifier("memberOperLogManagerImpl")
	private MemberOperLogManager memberOperLogManager;
	
	@Autowired
	@Qualifier("tjExpertInfoManagerImpl")
	private TjExpertInfoManager tjExpertInfoManager;
	
	@Autowired
	@Qualifier("memberInfoLogManagerImpl")
	private MemberInfoLogManager memberInfoLogManager;
	
	@Autowired
	@Qualifier("transactionTemplateMember")
	protected TransactionTemplate transactionTemplate;
	@Autowired
	private MemberHuoyanWalletManager memberHuoyanWalletManager;
	@Autowired
	private MemberWalletManager memberWalletManager;
	
	@Override
	public Member insertMember(Member member) {
		memberDbDao.insertAndSetupId("MemberMapper.insertSelective", member);
		return member;
	}

	/*@Override
	public Member updateMember(Member member) {
		memberDbDao.updateByObj("MemberMapper.updateByPrimaryKeySelective", member);
		return member;
	}*/

	@Override
	public int updateMemberStatusById(long id,int status) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("status", status);
		return memberDbDao.update("MemberMapper.updateMemberStatusById", param);
	}

	@Override
	public ModelResult<Member> queryMemberById(long id) {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member member = null;
		try {
			member = memberDbDao.queryUnique("MemberMapper.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(member);
		return result;
	}

	@Override
	public ModelResult<Member> register(Member member, RegisterOption registerOption,
			MemberOperOption memberOperOption) throws JcobServerException {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member memberRetrun = null;
		try {
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_INFO_NOTNULL);
			if(null == registerOption) throw new JcobServerException(ExceptionCode.MEMBER_REGISTERINFO_NOTNULL);
			if(null == memberOperOption) throw new JcobServerException(ExceptionCode.MEMBER_OPERATORINFO_NOTNULL);
			RegisterHandler registerHandler = RegisterHandlerFactory.getRegisterHandler(registerOption.getRegisterType());
			if(null == registerHandler) throw new JcobServerException(ExceptionCode.MEMBER_REGISTERTYPE_NOTNULL);
			if(StringUtils.isBlank(registerOption.getRegisterId())) throw new JcobServerException(ExceptionCode.MEMBER_REGISTERID_NOTNULL);
			
			registerHandler.verify(member,registerOption);
			if(StringUtils.isNotEmpty(member.getNickName())) checkNickNameAvailable(member.getNickName(),member.getId());	//检查昵称是还已经使用
			
			//判断 registerId 是否已经存在
			MemberLogin registerLog = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(registerHandler.getLoginType(), registerOption.getRegisterId());
			
			if(null != registerLog) {
				logger.info("registerList is not null,[{}] !",registerOption.getRegisterId());
				throw new JcobServerException(ExceptionCode.MEMBER_REGISTER_ALREADY_EXISTS);
			}
			
			if(RegisterType.phone_register.getIndex() == registerOption.getRegisterType()){
				//判断手机号之前是否有过第三方注册,如果第三方注册过,但是手机号并未验证,仍然允许注册
				logger.info("queryMemberByPhone,phone : [{}]",member.getPhone());
				List<Member> memList = this.queryMemberByPhone(member.getPhone());
				for(Member mem : memList){
					if(MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, mem.getFlagBit())){
						throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_ALREADY_VALID);
					}
				}
			}
			
			//第三方注册时,暂不会添加属性验证
			if(RegisterType.phone_register.getIndex() == registerOption.getRegisterType()) member.addFlagBit(MemberFlagBit.phone_valid);
			if(RegisterType.email_register.getIndex() == registerOption.getRegisterType()) member.addFlagBit(MemberFlagBit.email_valid);
			
			memberRetrun = saveMemberAndLog(member,registerOption,memberOperOption);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		result.setModel(memberRetrun);
		return result;
	}

	@Override
	public ModelResult<String> queryTokenByPassword(Integer loginType, String loginId,
			String password, String machineId, MemberOperOption memberOperOption) {
		ModelResult<String> result = new ModelResult<String>();
		Map<String,String> errMap = new HashMap<String,String>();
		String newToken = null;
		try {
			MemberLogin memberLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(loginType, loginId);
			
			if(null == memberLogin) {
				logger.info("memberLogin is null,loginType : [{}],loginId : [{}]",loginType,loginId);
				throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_ISNULL);
			}
			
			if(memberLogin.getStatus() == MemberLoginStatus.invalid) throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_STATUS_INVALID);
			
			//记录用户操作流水
			saveMemberOperLog(memberLogin.getMemberId().longValue(), OperType.login.getIndex(), memberOperOption);
			
			Member member = this.queryMemberById(memberLogin.getMemberId()).getModel();
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			
			if(!checkPasswordCorrect(member.getPassword(),password,memberLogin.getMemberId())) throw new JcobServerException(ExceptionCode.MEMBER_PASSWORD_NOTCORRECT);	//验证密码
			
			MemberLoginToken tokenLog = memberLoginTokenManager.queryMemberLoginTokenByMemberIdAndMachineId(memberLogin.getMemberId(), machineId, loginType);
			
			//如果存在其它设备，更新其它设备状态为可用
			/*List<MemberLoginToken> tokenList = memberLoginTokenManager.queryMemberLoginTokenByMemberId(memberLogin.getMemberId());
			if(CollectionUtils.isNotEmpty(tokenList)){
				for(MemberLoginToken tempToken : tokenList) {
					memberLoginTokenManager.updateMemberLoginTokenStatus(tempToken.getId(), MemberLoginTokenStatus.valid);	//更新其它设备的token状态为可用
				}
			}*/
			
			newToken = generateSoken(memberLogin.getMemberId(),machineId);
			if(null == tokenLog){
				//初次登录
				MemberLoginToken newLoginToken = new MemberLoginToken();
				newLoginToken.setMemberId(memberLogin.getMemberId().longValue());
				newLoginToken.setMachineId(machineId);
				newLoginToken.setLoginType(loginType);
				newLoginToken.setToken(newToken);
				newLoginToken.setStatus(MemberLoginTokenStatus.valid);
				memberLoginTokenManager.insertMemberLoginToken(newLoginToken);
			} else {
				if(tokenLog.getStatus() == MemberLoginTokenStatus.invalid) 
					memberLoginTokenManager.updateMemberLoginTokenToken(tokenLog.getId(), newToken);	//更新旧的token
				else
					newToken = tokenLog.getToken();
			}
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			return result;
		}
		
		result.setModel(newToken);
		return result;
	}

	@Override
	public ModelResult<String> queryTokenByLoginId(Integer loginType, String loginId,
			String machineId, MemberOperOption memberOperOption) {
		ModelResult<String> result = new ModelResult<String>();
		Map<String,String> errMap = new HashMap<String,String>();
		String newToken = null;
		try {
			MemberLogin memberLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(loginType, loginId);
			
			if(null == memberLogin) {
				logger.info("memberLogin is null,loginType : [{}],loginId : [{}]",loginType,loginId);
				throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_ISNULL);
			}
			
			if(memberLogin.getStatus() == MemberLoginStatus.invalid) throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_STATUS_INVALID);
			
			//记录用户操作流水
			saveMemberOperLog(memberLogin.getMemberId().longValue(), OperType.login.getIndex(), memberOperOption);
			
			Member member = this.queryMemberById(memberLogin.getMemberId()).getModel();
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			
			//if(!member.getPassword().equals(password)) throw new JcobServerException(ExceptionCode.PASSWORD_NOT_CORRECT);	//验证密码
			
			MemberLoginToken tokenLog = memberLoginTokenManager.queryMemberLoginTokenByMemberIdAndMachineId(memberLogin.getMemberId(), machineId, loginType);
			
			//如果存在其它设备，更新其它设备状态为可用
			/*List<MemberLoginToken> tokenList = memberLoginTokenManager.queryMemberLoginTokenByMemberId(memberLogin.getMemberId());
			if(CollectionUtils.isNotEmpty(tokenList)){
				for(MemberLoginToken tempToken : tokenList) {
					memberLoginTokenManager.updateMemberLoginTokenStatus(tempToken.getId(), MemberLoginTokenStatus.valid);	//更新其它设备的token状态为可用
				}
			}*/
			
			newToken = generateSoken(memberLogin.getMemberId(),machineId);
			if(null == tokenLog){
				//初次登录
				MemberLoginToken newLoginToken = new MemberLoginToken();
				newLoginToken.setMemberId(memberLogin.getMemberId().longValue());
				newLoginToken.setMachineId(machineId);
				newLoginToken.setLoginType(loginType);
				newLoginToken.setToken(newToken);
				newLoginToken.setStatus(MemberLoginTokenStatus.valid);
				memberLoginTokenManager.insertMemberLoginToken(newLoginToken);
			} else {
				if(tokenLog.getStatus() == MemberLoginTokenStatus.invalid) 
					memberLoginTokenManager.updateMemberLoginTokenToken(tokenLog.getId(), newToken);	//更新旧的token
				else
					newToken = tokenLog.getToken();
			}
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(newToken);
		return result;
	}
	
	
	@Override
	public ModelResult<Member> queryMemberByTokenAndMachineId(String machineId,String token,MemberOperOption memberOperOption) {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member member = null;
		try {
			MemberLoginToken logToken = memberLoginTokenManager.queryMemberLoginTokenByTokenAndMachineId(token, machineId);
			if(null == logToken) {
				logger.info("logToken is null!,machineId : [{}],token : [{}]",machineId,token);
				throw new JcobServerException(ExceptionCode.MEMBER_TOKEN_ISNULL); 
			}
			
			if(logToken.getStatus() == MemberLoginTokenStatus.invalid) {
				logger.info("MemberLoginTokenStatus is invalud,machineId : [{}],token : [{}]",machineId,token);
				throw new JcobServerException(ExceptionCode.MEMBER_TOKEN_UNEFFECTIVE);
			}
			
			//记录member操作流水
			saveMemberOperLog(logToken.getMemberId(), OperType.login.getIndex(), memberOperOption);
			
			member = this.queryMemberById(logToken.getMemberId()).getModel();
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(member);
		return result;
	}
	
	private String generateSoken(long memberId,String machineId) {
		return MD5.md5Encode(memberId + machineId + System.currentTimeMillis());
	}

	@Override
	public ModelResult<Integer> logoutByToken(String machineId, String token,MemberOperOption memberOperOption) {
		ModelResult<Integer> result = new ModelResult<Integer>();
		Map<String,String> errMap = new HashMap<String,String>();
		Integer flag = 0;
		try {
			MemberLoginToken loginToken = memberLoginTokenManager.queryMemberLoginTokenByTokenAndMachineId(token, machineId);
			if(null == loginToken) {
				logger.info("loginToken is null,machineId : [{}],token : [{}]",machineId,token);
				throw new JcobServerException(ExceptionCode.MEMBER_TOKEN_ISNULL);
			}
			//记录操作流水
			saveMemberOperLog(loginToken.getMemberId(), OperType.logout.getIndex(), memberOperOption);
			
			flag = memberLoginTokenManager.updateMemberLoginTokenStatus(loginToken.getId(), MemberLoginTokenStatus.invalid);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(flag);
		return result;
	}

	@Override
	public ModelResult<Boolean> updateMemberPasswordByMobile(String mobile, String newPassword,MemberOperOption memberOperOption) {
		/*MemberLogin memLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(MemberLoginType.login_phone.getIndex(), mobile);
		
		if(null == memLogin) {
			logger.error("memLogin is null,mobile : [{}]",mobile);
			throw new JcobServerException(ExceptionCode.MOBILE_ISNULL);
		}
		
		boolean pass = checkMobileIsValid(memLogin.getMemberId());
		if(!pass) {
			logger.error("mobile : [{}] is not valid,reset password fail!",mobile);	//手机号未通过认证不能修改密码
			throw new JcobServerException(ExceptionCode.MOBILE_NOT_VALID); 
		}
		
		if(memLogin.getStatus() == MemberLoginStatus.invalid) throw new JcobServerException(ExceptionCode.MOBILE_UNEFFECTIVE);*/
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			Member member = queryValidMemberByPhone(mobile).getModel();//查询手机号的有效用户
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_UNEFFECTIVE); 
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					updateMemberPasswordById(member.getId(), newPassword);
					
					/*List<MemberLogin> loginList = memberLoginManager.queryMemberLoginTokenByMemberId(member.getId());
					if(CollectionUtils.isNotEmpty(loginList)){
						for(MemberLogin login : loginList){
							memberLoginManager.updateMemberLoginStatusById(login.getId(), MemberLoginStatus.invalid);
						}
					}*/
					
					List<MemberLoginToken> tokenList = memberLoginTokenManager.queryMemberLoginTokenByMemberId(member.getId());
					if(CollectionUtils.isNotEmpty(tokenList)){
						for(MemberLoginToken token : tokenList){
							memberLoginTokenManager.updateMemberLoginTokenStatus(token.getId(), MemberLoginTokenStatus.invalid);
						}
					}
					//记录操作流水
					saveMemberOperLog(member.getId(), OperType.reset_password.getIndex(), memberOperOption);
				}
			});
		} catch (JcobServerException e) {
			result.setModel(Boolean.FALSE);
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			result.setModel(Boolean.FALSE);
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(Boolean.TRUE);
		return result;
		
	}

	@Override
	public int updateMemberPasswordById(Long memberId, String password) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", memberId);
		param.put("password", encodePasswordBySalt(password, String.valueOf(memberId)));
		param.put("passwordStrength", PasswordStrength.CheckPasswordSecurityLevel(password));
		
		return memberDbDao.update("MemberMapper.updateMemberPasswordById", param);
	}

	@Override
	public ModelResult<Boolean> updateMemberBaseInfoByMember(MemberOption memberOption,MemberOperOption memberOperOption) throws JcobServerException {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Map<String,String> errMap = new HashMap<String,String>();
		try {
			Member oldMember = this.queryMemberById(memberOption.getMemberId()).getModel();
			if(null == oldMember) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST); 
			checkNickNameAvailable(memberOption.getNickName(),oldMember.getId());
			
			//检查新手机号码是否可用
//			checkMobileIsValid();
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					if(!oldMember.getPhone().equals(memberOption.getMobile())){
						//手机号码更改，添加[用户信息变更流水表]
						saveMemberInfoLog(oldMember.getId(),MemberInfoType.phone.getIndex(),oldMember.getPhone(),memberOption.getMobile());
						
						//更新 member_login 表的login_id
						MemberLogin memLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(MemberLoginType.login_phone.getIndex(), oldMember.getPhone());
						memberLoginManager.updateMemberLoginLoginIdById(memLogin.getId(), memberOption.getMobile());
						
						//更新[member_login_token]的token失效
						List<MemberLoginToken> tokenList = memberLoginTokenManager.queryMemberLoginTokenByMemberId(oldMember.getId().intValue());
						if(CollectionUtils.isNotEmpty(tokenList)){
							for(MemberLoginToken loginToken : tokenList) {
								memberLoginTokenManager.updateMemberLoginTokenStatus(loginToken.getId(), MemberLoginTokenStatus.invalid);
							}
						}
					}
					
					if(!oldMember.getNickName().equals(memberOption.getNickName())){
						//昵称更改，添加[用户信息变更流水表]
						saveMemberInfoLog(oldMember.getId(),MemberInfoType.nick_name.getIndex(),oldMember.getNickName(),memberOption.getNickName());
					}
					
					if(!oldMember.getCertNo().equals(memberOption.getCertNo())){
						//身份证更改，添加[用户信息变更流水表]
						saveMemberInfoLog(oldMember.getId(),MemberInfoType.identity_card.getIndex(),oldMember.getCertNo(),memberOption.getCertNo());
					}
					
					if(!oldMember.getRealName().equals(memberOption.getRealName())){
						//真实姓名更改，添加[用户信息变更流水表]
						saveMemberInfoLog(oldMember.getId(),MemberInfoType.real_name.getIndex(),oldMember.getRealName(),memberOption.getRealName());
					}
					
					saveMemberOperLog(oldMember.getId(), OperType.modify_member_info.getIndex(), memberOperOption);
					
					updateMemberMobileCertNoRealNameNickNameById(oldMember.getId(), memberOption.getCertNo(), memberOption.getMobile(), memberOption.getNickName(), memberOption.getRealName());
				}
			});
			
		} catch (JcobServerException e) {
			result.setModel(Boolean.FALSE);
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			result.setModel(Boolean.FALSE);
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(Boolean.TRUE);
		return result;
	}
	
	/**
	 * 记录member的操作流水
	 * @param memberId
	 * @param operType
	 * @param memberOperOption
	 */
	private void saveMemberOperLog(long memberId,int operType,MemberOperOption memberOperOption) {
		/*MemberOperLog operLog = new MemberOperLog();
		operLog.setMemberId(memberId);
		operLog.setOperType(operType);
		operLog.setClientType(memberOperOption.getClientType());
		operLog.setChannel(memberOperOption.getChannel());
		operLog.setUserIp(memberOperOption.getUserIp());
		operLog.setFrontServerIp(memberOperOption.getFrontServerIp());
		operLog.setBackServerIp(memberOperOption.getBackServerIp());
		operLog.setRemark(memberOperOption.getRemark());
		operLog.setCreateTime(Calendar.getInstance());*/
		memberOperOption.setOperType(operType);
//		memberOperLogManager.insertMemberOperLog(operLog);
		memberOperLogManager.asyncSaveMemberOperLog(memberId, memberOperOption, memberOperOption.getRemark());
	}
	
	private void saveMemberInfoLog(long memberId,int infoType,String oldValue,String newValue) {
		MemberInfoLog log = new MemberInfoLog();
		log.setMemberId(memberId);
		log.setStatus(MemberInfoLogStatus.valid);
		log.setInfoType(infoType);
		log.setOldInfo(oldValue);
		log.setNewInfo(newValue);
		memberInfoLogManager.insertMemberInfoLog(log);
	}
	
	/**
	 * 保存注册操作相关记录，事务管理
	 * @param member
	 * @param registerOption
	 * @param memberOperOption
	 * @return
	 */
	private Member saveMemberAndLog(Member member,RegisterOption registerOption
			,MemberOperOption memberOperOption) throws Exception{
		//新增操作添加事务管理
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					logger.info("开始执行注册事务处理");
					member.setPasswordStrength(PasswordStrength.CheckPasswordSecurityLevel(member.getPassword()));
					insertMember(member);
					updateMemberNoPwdById(member.getId(),member.getPassword());
					MemberLogin memlogin = new MemberLogin();
					memlogin.setMemberId(member.getId());
					memlogin.setLoginType(registerOption.getRegisterType());
					memlogin.setLoginId(registerOption.getRegisterId());
					memberLoginManager.insertMemberLogin(memlogin);
					//添加到专家推荐表信息
					logger.info("添加专家推荐");
					tjExpertInfoManager.addTjexpert(member.getId(), null, TjExpertLevel.normal);
					memberHuoyanWalletManager.initMemberHuoyanWallet(member);
					memberWalletManager.initMemberWallet(member);
					
				}
			});
			
			//添加用户操作流水
			saveMemberOperLog(member.getId(), OperType.register.getIndex(), memberOperOption);
			
			//添加用户信息变更流水
			if(null != member.getPhone()){
				saveMemberInfoLog(member.getId(),MemberInfoType.phone.getIndex(),null,member.getPhone());
			}
			
			if(null != member.getCertNo()){
				saveMemberInfoLog(member.getId(),MemberInfoType.identity_card.getIndex(),null,member.getCertNo());
			}
			
			if(null != member.getNickName()){
				saveMemberInfoLog(member.getId(),MemberInfoType.nick_name.getIndex(),null,member.getNickName());
			}
			
			if(null != member.getRealName()){
				saveMemberInfoLog(member.getId(),MemberInfoType.real_name.getIndex(),null,member.getRealName());
			}
			
		} catch (Exception e) {
			logger.error("注册操作失败,[{}-{}]",e,ThrowableUtil.getFullExceptionInfo(e));
			throw e;
		}
		return member;
	}

	@Override
	public int updateMemberMobileCertNoRealNameNickNameById(long id,
			String certNo, String mobile, String nickName, String realName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("certNo", certNo);
		param.put("nickName", nickName);
		param.put("realName", realName);
		param.put("mobile", mobile);
		return memberDbDao.update("MemberMapper.updateMemberMobileCertNoRealNameNickNameById", param);
	}

	@Override
	public int updateMemberNoPwdById(long id,String password) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("no", "jcob_" + id);
		param.put("password", encodePasswordBySalt(password, String.valueOf(id)));
		return memberDbDao.update("MemberMapper.updateMemberNoPwdById", param);
	}

	@Override
	public ModelResult<Member> queryMemberByPassword(Integer loginType, String loginId,
			String password, MemberOperOption memberOperOption) {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		ModelResult<Member> memberResult = null ;
		try {
			memberResult = queryMemberByLoginId(loginType, loginId, memberOperOption);
			if(null == memberResult || null == memberResult.getModel()) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			boolean con = checkPasswordCorrect(memberResult.getModel().getPassword(),password,memberResult.getModel().getId());
			if(!con) throw new JcobServerException(ExceptionCode.MEMBER_PASSWORD_NOTCORRECT);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(memberResult.getModel());
		return result;
	}

	@Override
	public ModelResult<Member> queryMemberByLoginId(Integer loginType, String loginId,
			MemberOperOption memberOperOption) throws JcobServerException {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member member = null;
		try {
			MemberLogin memLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(loginType, loginId);
			
			if(null == memLogin) throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_ISNULL);
			if(MemberLoginStatus.invalid == memLogin.getStatus()) throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_STATUS_INVALID);
			
			member = queryMemberById(memLogin.getMemberId()).getModel();
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_INFO_NOTNULL);
			
			//记录用户操作流水
			saveMemberOperLog(member.getId(), OperType.login.getIndex(), memberOperOption);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(member);
		return result;
	}
	
	@Override
	public ModelResult<MemberLogin> binding2Member(Long memberId, Integer newLoginType,
			String newLoginId, MemberOperOption memberOperOption) {
		ModelResult<MemberLogin> result = new ModelResult<MemberLogin>();
		Map<String,String> errMap = new HashMap<String,String>();
		MemberLogin login = null;
		try {
			MemberLogin memLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(newLoginType, newLoginId);
			if(null != memLogin) throw new JcobServerException(ExceptionCode.MEMBER_LOGIN_EXISTS); 
			
			login = new MemberLogin();
			login.setMemberId(memberId);
			login.setLoginType(newLoginType);
			login.setLoginId(newLoginId);
			login.setStatus(MemberLoginStatus.valid);
			memberLoginManager.insertMemberLogin(login);
			//记录用户操作流水
			saveMemberOperLog(memberId, OperType.third_bind.getIndex(), memberOperOption);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(login);
		return result;
	}

	@Override
	public int updateMemberCertNoRealNameById(long id, String certNo,
			String realName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("certNo", certNo);
		param.put("realName", realName);
		return memberDbDao.update("MemberMapper.updateMemberCertNoRealNameById", param);
	}

	@Override
	public List<Member> queryMemberByPhoneRegisterType(String mobile,int registerType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("phone", mobile);
		param.put("registerType", registerType);
		return memberDbDao.queryList("MemberMapper.queryMemberByPhoneRegisterType", param);
	}

	@Override
	public void checkNickNameAvailable(String nickName,Long memberId)
			throws JcobServerException {
		checkNickNameAvailable(nickName,14,memberId);
	}

	@Override
	public void checkNickNameAvailable(String nickName, int lengthMax,Long memberId)
			throws JcobServerException {
		if (StringUtils.isBlank(nickName)) {
			throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_NOTNULL);
		}
		nickName = nickName.trim();
		MemberValid.validNickName(nickName,lengthMax);
		WordCensor.validwordcensor(nickName);
		boolean exists = this.checkNickNameExists(nickName,memberId);	// true 表示存在
		if (exists) throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_EXISTS);
	}

	@Override
	public ModelResult<List<Member>> queryMemberByNickName(String nickName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("nickName", nickName);
		return new ModelResult<List<Member>>(memberDbDao.queryList("MemberMapper.queryMemberByNickName", param));
	}

	@Override
	public int updateMemberNickNameById(long memberId, String nickName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", memberId);
		param.put("nickName", nickName);
		return memberDbDao.update("MemberMapper.updateMemberNickNameById", param);
	}
	
	/**
	 * 检查手机号码是否已经通过认证,true表示通过
	 * @param mobile
	 * @return
	 */
	@Override
	public ModelResult<Boolean> checkMobileIsValid(long memberId) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			Member member = queryMemberById(memberId).getModel();
			if(null == member) 
				throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			
			if(!MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, member.getFlagBit()))
				throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_NOTPASSVALID);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		}
		
		result.setModel(Boolean.TRUE);
		return result;
	}
	
	@Override
	public ModelResult<Boolean> modifyMemberNickName(Long memberId, String nickName) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Map<String,String> errMap = new HashMap<String,String>();
		int flag = 0;
		
		try {
			checkNickNameAvailable(nickName,memberId);
			
			MemberInfoLog infoLog = new MemberInfoLog();
			Member member = this.queryMemberById(memberId).getModel();
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			infoLog.setMemberId(memberId);
			infoLog.setOldInfo(member.getNickName());
			infoLog.setNewInfo(nickName);
			infoLog.setInfoType(MemberInfoType.nick_name.getIndex());
			
			flag = this.updateMemberNickNameById(memberId, nickName);
			memberInfoLogManager.insertMemberInfoLog(infoLog);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		}
		
		result.setModel(flag > 0);
		return result;
	}

	@Override
	public String encodePasswordBySalt(String password, String salt) {
		return MD5.md5Encode(password + salt);
	}

	@Override
	public DataPage<MemberResult> pageMemberVoByOption(
			MemberExpertOption option, DataPage<Member> page) {
		logger.info("pageMemberVoByOption begin ==");
		DataPage<MemberResult> dataPage = new DataPage<MemberResult>();
		dataPage.setPageNo(page.getPageNo());
		dataPage.setPageSize(page.getPageSize());
		
		logger.info("option : [{}]",JSONArray.toJSON(option));
		logger.info("option level : [{}]",option.getLevel());
		if(null == option.getLevel()){
			//主表是Member表
			DataPage<Member> membPageData = pageMemberByOption(option, page);
			List<Member> memlist = membPageData.getDataList();
			
			if(CollectionUtils.isEmpty(memlist)) return dataPage;
			
			Long[] idArray = joinMemberIdStr(memlist);
			
			List<TjExpertInfo> expertList = tjExpertInfoManager.queryTjExpertInfoByMemberIdArray(idArray);
			List<MemberResult> voList = new ArrayList<MemberResult>();
			
			for(Member member : memlist){
				for(TjExpertInfo tjExpert : expertList){
					if(tjExpert.getMemberId().equals(member.getId())) {
						MemberResult vo = new MemberResult(member);
						vo.setLevel(tjExpert.getLevel());
						voList.add(vo);
						break;
					}
				}
			}
			dataPage.setDataList(voList);
			dataPage.setTotalCount(membPageData.getTotalCount());
		}else{
			DataPage<TjExpertInfo> pageParam = new DataPage<TjExpertInfo>();
			pageParam.setPageNo(dataPage.getPageNo());
			pageParam.setPageSize(dataPage.getPageSize());
			
			TjExpertInfoQueryOption tjOption = new TjExpertInfoQueryOption();
			if(null != option.getMemberId()) tjOption.setMemberId(option.getMemberId());
			if(null != option.getLevel()) tjOption.setLevel(option.getLevel());
			if(null != option.getStartTime()) tjOption.setStartTime(option.getStartTime());
			if(null != option.getEndTime()) tjOption.setEndTime(option.getEndTime());
			
			DataPage<TjExpertInfo> tjDataPage = tjExpertInfoManager.pageExpertInfo(pageParam, null, option.getLevel(), option.getStartTime(), option.getEndTime());
			List<TjExpertInfo> expertList = tjDataPage.getDataList();
			if(CollectionUtils.isEmpty(expertList)) return dataPage;
			
			Long[] idArray = joinTjIdArray(expertList);
			
			List<Member> memlist = queryMemberByIdArray(idArray);
			List<MemberResult> voList = new ArrayList<MemberResult>();
			
			for(TjExpertInfo tjExpert : expertList){
				for(Member member : memlist){
					if(tjExpert.getMemberId().equals(member.getId())) {
						MemberResult vo = new MemberResult(member);
						vo.setLevel(tjExpert.getLevel());
						voList.add(vo);
						break;
					}
				}
			}
			dataPage.setDataList(voList);
			dataPage.setTotalCount(tjDataPage.getTotalCount());
		}
		
		
		return dataPage;
	}
	
	/**
	 * 昵称是否已经使用,如果已经使用,返回true,否则返回false
	 * @param nickName
	 * @return boolean
	 */
	@Override
	public boolean checkNickNameExists(String nickName,Long memberId) {
		ModelResult<List<Member>> memberResult = queryMemberByNickName(nickName);
		
		/*MemberInfoLogOption option = new MemberInfoLogOption();
		option.setNewInfo(nickName);*/
		List<MemberInfoLog> logList = memberInfoLogManager.queryNotCurrentMemberInfoLog(memberId,nickName);
		
		if(CollectionUtils.isNotEmpty(memberResult.getModel())){
			logger.info("Nick name : [{}],already exists in member",nickName);
			return true;
		}
		
		if(CollectionUtils.isNotEmpty(logList)){
			logger.info("Nick name : [{}],already exists in memberloginfo",nickName);
			return true;
		}
		
		return false;
	}
	
	private boolean checkPasswordCorrect(String password,String inputPwd,Long memberId) throws JcobServerException{
		if(StringUtils.isBlank(password) || StringUtils.isBlank(inputPwd)) 
			throw new JcobServerException(ExceptionCode.MEMBER_PASSWORD_NOTNULL);
		return password.equals(encodePasswordBySalt(inputPwd, String.valueOf(memberId)));
	}
	
	private DataPage<Member> pageMemberByOption(MemberExpertOption option, DataPage<Member> page) {
		Map<String,Object> param = JSONUtils.object2MapSpecail(option);
		return (DataPage<Member>)memberDbDao.queryPage("MemberMapper.countMemberVoByOption", "MemberMapper.pageMemberVoByOption", param, page);
	}
	
	/**
	 * 组合ID的组合字符串
	 * @param list
	 * @return
	 */
	private Long[] joinMemberIdStr(List<Member> list) {
		List<Long> idlist = new ArrayList<Long>();
		for(Member member : list) idlist.add(member.getId());
		return idlist.toArray(new Long[]{});
	}

	/**
	 * 组合ID的组合
	 * @param list
	 * @return
	 */
	private Long[] joinTjIdArray(List<TjExpertInfo> list) {
		List<Long> idList = new ArrayList<Long>();
		for(TjExpertInfo info : list) idList.add(info.getMemberId());
		return idList.toArray(new Long[]{});
	}
	
	@Override
	public ModelResult<MemberResult> queryMemberVoByMemberId(Long memberId) {
		ModelResult<MemberResult> result = new ModelResult<MemberResult>();
		Map<String,String> errMap = new HashMap<String,String>();
		MemberResult vo = null;
		try {
			Member member = queryMemberById(memberId).getModel();
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST); 
			
			TjExpertInfo expert = tjExpertInfoManager.queryExpertInfoByMemberId(memberId);
			if(null == expert) throw new JcobServerException(ExceptionCode.EXPERT_NOT_EXIST);
			
			vo = new MemberResult(member);
			vo.setLevel(expert.getLevel());
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(vo);
		return result;
	}

	@Override
	public List<Member> queryMemberByIdArray(Long[] idArray) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("idArray", idArray);
		return memberDbDao.queryList("MemberMapper.queryMemberByIdArray", param);
	}

	@Override
	public ModelResult<Member> queryValidMemberByPhone(String phone) {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member validMember = null;
		
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("phone", phone);
			List<Member> memList = memberDbDao.queryList("MemberMapper.queryValidMemberByPhone",param);
			if(CollectionUtils.isEmpty(memList)) {
				logger.info("memList is null,phone : [{}]",phone);
				throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_ISNULL);
			}
			
			for(Member member : memList){
				if(MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, member.getFlagBit())){
					validMember = member;
					break;
				}
			}
			
			if(null == validMember) {
				logger.info("no validMember,phone : [{}]",phone);
				throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_NOTPASSVALID);
			}
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(validMember);
		return result;
	}

	@Override
	public ModelResult<Member> thirdRegister(Member member,
			RegisterOption registerOption, MemberOperOption memberOperOption) {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member memberRetrun = null;
		try {
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_INFO_NOTNULL);
			if(null == registerOption) throw new JcobServerException(ExceptionCode.MEMBER_REGISTERINFO_NOTNULL);
			if(null == memberOperOption) throw new JcobServerException(ExceptionCode.MEMBER_OPERATORINFO_NOTNULL);
			RegisterHandler registerHandler = RegisterHandlerFactory.getRegisterHandler(registerOption.getRegisterType());
			if(null == registerHandler) throw new JcobServerException(ExceptionCode.MEMBER_REGISTERTYPE_NOTNULL);
			if(StringUtils.isBlank(registerOption.getRegisterId())) throw new JcobServerException(ExceptionCode.MEMBER_REGISTERID_NOTNULL);
			
			registerHandler.verify(member,registerOption);
			if(StringUtils.isNotEmpty(member.getNickName())) checkNickNameAvailable(member.getNickName(),member.getId());	//检查昵称是还已经使用
			
			//判断 registerId 是否已经存在
			MemberLogin registerLog = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(registerOption.getRegisterType(), registerOption.getRegisterId());
			
			if(null != registerLog) {
				logger.info("registerList is not null,[{}] !",registerOption.getRegisterId());
				throw new JcobServerException(ExceptionCode.MEMBER_REGISTER_ALREADY_EXISTS);
			}
			
			memberRetrun = saveMemberAndLog(member,registerOption,memberOperOption);
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		result.setModel(memberRetrun);
		return result;
	}

	@Override
	public List<Member> queryMemberByPhone(String mobile) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("phone", mobile);
		return memberDbDao.queryList("MemberMapper.queryMemberByPhone", param);
	}
	
	@Override
	public ModelResult<Boolean> modifyMemberPhoneNo(Long memberId,String oldPhone, String newPhone) {
		logger.info("modifyMemberPhoneNo begin ...");
		ModelResult<Boolean> result = new ModelResult<Boolean>(Boolean.FALSE);
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			//新的号码是否已经有人用过，是否通过了手机验证
			MemberLogin newPhoneLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(MemberLoginType.login_phone.getIndex(), newPhone);
			if(null != newPhoneLogin) throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_ALREADY_USED);
			
			List<Member> memList = this.queryMemberByPhone(newPhone);
			for(Member mem : memList){
				if(MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, mem.getFlagBit())){
					throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_ALREADY_VALID);
				}
			}
			
			//如果存在旧的memberlogin(手机登录类型)且状态为有效
			MemberLogin oldPhoneLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(MemberLoginType.login_phone.getIndex(), oldPhone);
			if(null != oldPhoneLogin){
				if(memberId != oldPhoneLogin.getMemberId()) throw new JcobServerException(ExceptionCode.MEMBER_ID_NOTEQUALS); 
				
				memberLoginManager.updateMemberLoginStatusById(oldPhoneLogin.getId(), MemberLoginStatus.invalid);
				
				MemberLogin newMemberLogin = new MemberLogin();
				newMemberLogin.setMemberId(oldPhoneLogin.getMemberId());
				newMemberLogin.setLoginType(oldPhoneLogin.getLoginType());
				newMemberLogin.setStatus(MemberLoginStatus.valid);
				newMemberLogin.setLoginId(newPhone);
				memberLoginManager.insertMemberLogin(newMemberLogin);
				
				//更新memberLoginToken失效
				memberLoginTokenManager.updateMemberLoginTokenByMemberIdLoginTypeInValid(oldPhoneLogin.getMemberId(),MemberLoginType.login_phone.getIndex());
			}
			
			//更新member手机号，同时通过手机验证
			updateMemberMobileAndPassValid(memberId,newPhone);
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统操作异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(Boolean.TRUE);
		return result;
	}

	@Override
	public int updateMemberMobileAndPassValid(long memberId, String mobile) {
		Member oldMember = this.queryMemberById(memberId).getModel();
		
		if(null == oldMember) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
		
		if(!MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, oldMember.getFlagBit()))
			oldMember.addFlagBit(MemberFlagBit.phone_valid);
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", memberId);
		param.put("mobile", mobile);
		param.put("flagBit", oldMember.getFlagBit());
		
		//
		MemberLogin oldMemLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(MemberLoginType.login_phone.getIndex(), mobile);
		if(null != oldMemLogin) memberLoginManager.updateMemberLoginStatusById(oldMemLogin.getId(), MemberLoginStatus.invalid);
		
		MemberLogin mLogin = new MemberLogin();
		mLogin.setMemberId(memberId);
		mLogin.setLoginType(MemberLoginType.login_phone.getIndex());
		mLogin.setLoginId(mobile);
		mLogin.setStatus(MemberLoginStatus.valid);
		memberLoginManager.insertMemberLogin(mLogin);
		
		return memberDbDao.update("MemberMapper.updateMemberMobileAndPassValid", param);
	}

	@Override
	public ModelResult<Boolean> updateMemberIcon(Long memberId, String iconPath) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", memberId);
		param.put("icon", iconPath);
		
		return new ModelResult<Boolean>(memberDbDao.update("MemberMapper.updateMemberIcon", param) > 0);
	}

	@Override
	public ModelResult<Boolean> addMemberPhoneValid(Long memberId, String phone) {
		Member member = this.queryMemberById(memberId).getModel();
		
		if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
		if(MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, member.getFlagBit())) return new ModelResult<Boolean>(Boolean.TRUE);
		
		member.addFlagBit(MemberFlagBit.phone_valid);
		
		MemberLogin oldMemLogin = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(MemberLoginType.login_phone.getIndex(), phone);
		if(null != oldMemLogin) memberLoginManager.updateMemberLoginStatusById(oldMemLogin.getId(), MemberLoginStatus.invalid);
		//add memberlogin
		MemberLogin mLogin = new MemberLogin();
		mLogin.setMemberId(member.getId());
		mLogin.setLoginType(MemberLoginType.login_phone.getIndex());
		mLogin.setLoginId(phone);
		mLogin.setStatus(MemberLoginStatus.valid);
		memberLoginManager.insertMemberLogin(mLogin);
		
		
		return new ModelResult<Boolean>(updateMemberFlagBit(memberId,member.getFlagBit()) > 0);
	}

	@Override
	public int updateMemberFlagBit(long memberId, long flagBit) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", memberId);
		param.put("flagBit", flagBit);
		return memberDbDao.update("MemberMapper.updateMemberFlagBit", param);
	}

	@Override
	public List<Member> queryMemberByIdArrayAndOption(Long[] idArray,
			MemberExpertOption option) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("phone", option.getPhone());
		param.put("nickName", option.getNickName());
		param.put("status", option.getStatus());
		param.put("clientType", option.getClientType());
		param.put("registerType", option.getRegisterType());
		param.put("idArray", idArray);
		return memberDbDao.queryList("MemberMapper.queryMemberByIdArrayAndOption", param);
	}

	@Override
	public ModelResult<Member> queryMemberByTokenAndMachineId(String machineId,
			String token) {
		ModelResult<Member> result = new ModelResult<Member>();
		Map<String,String> errMap = new HashMap<String,String>();
		Member member = null;
		try {
			MemberLoginToken logToken = memberLoginTokenManager.queryMemberLoginTokenByTokenAndMachineId(token, machineId);
			if(null == logToken) {
				logger.error("logToken is null!,machineId : [{}],token : [{}]",machineId,token);
				throw new JcobServerException(ExceptionCode.MEMBER_TOKEN_ISNULL); 
			}
			
			if(logToken.getStatus() == MemberLoginTokenStatus.invalid) {
				logger.error("MemberLoginTokenStatus is invalud,machineId : [{}],token : [{}]",machineId,token);
				throw new JcobServerException(ExceptionCode.MEMBER_TOKEN_UNEFFECTIVE);
			}
			
			member = this.queryMemberById(logToken.getMemberId()).getModel();
			memberOperLogManager.saveSystemAutoLoginLog(member.getId());
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(member);
		return result;
	}

	@Override
	public boolean checkNickNameExists(String nickName) {
		ModelResult<List<Member>> memberResult = queryMemberByNickName(nickName);
		
		MemberInfoLogOption option = new MemberInfoLogOption();
		option.setNewInfo(nickName);
		List<MemberInfoLog> logList = memberInfoLogManager.queryMemberInfoLogWithOption(option);
		
		if(CollectionUtils.isNotEmpty(memberResult.getModel())){
			logger.info("Nick name : [{}],already exists in member",nickName);
			return true;
		}
		
		if(CollectionUtils.isNotEmpty(logList)){
			logger.info("Nick name : [{}],already exists in memberloginfo",nickName);
			return true;
		}
		
		return false;
	}

	@Override
	public ModelResult<Boolean> updateMemberRealNamePhoneCertification(
			long memberId, String realName, String certNo) {
		ModelResult<Boolean> result = new ModelResult<Boolean>(true);
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			Member member = this.queryMemberById(memberId).getModel();
			if (null == member)
				throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			this.updateMemberCertNoRealNameById(memberId, certNo, realName);
			member.addFlagBit(MemberFlagBit.certification);
			this.updateMemberFlagBit(memberId, member.getFlagBit());
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			result.setModel(false);
			return result;
		}
		return result;
	}

	@Override
	public ModelResult<Member> queryMemberByThirdLoginId(int loginType,
			String loginId) {
		ModelResult<Member> result = new ModelResult<Member>(null);
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			List<MemberLogin> loginList = memberLoginManager.queryMemberLoginByThirdLoginTypeAndLoginId(loginType,
							loginId);
			if (org.apache.commons.collections.CollectionUtils.isEmpty(loginList))
				throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			MemberLogin memLogin = loginList.get(0);
			
			Member member = this.queryMemberById(memLogin.getMemberId()).getModel();
			if(null == member) throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			result.setModel(member);
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			result.setModel(null);
			return result;
		}
		return result;
	}

	@Override
	public ModelResult<Boolean> updateMemberRealNamePhoneCertificationAndPhone(
			long memberId, String realName, String certNo, String phone) {
		ModelResult<Boolean> result = new ModelResult<Boolean>(true);
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			Member member = this.queryMemberById(memberId).getModel();
			if (null == member)
				throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					if(MemberFlagBit.isExistFlagBit(MemberFlagBit.certification, member.getFlagBit())) {
						throw new JcobServerException(ExceptionCode.MEMBER_CERTIFICATION_ALREADY_VALID);
					}
					
					if(MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, member.getFlagBit())) {
						throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_ALREADY_VALID);
					}
					
					updateMemberCertNoRealNameById(memberId, certNo, realName);
					member.addFlagBit(MemberFlagBit.certification);
					member.addFlagBit(MemberFlagBit.phone_valid);
					updateMemberFlagBit(memberId, member.getFlagBit());
					
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("id", memberId);
					param.put("mobile", phone);
					param.put("flagBit", member.getFlagBit());
					memberDbDao.update("MemberMapper.updateMemberMobileAndPassValid", param);
				}
			});
		} catch (Exception e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统处理异常");
			result.setErrorList(errMap);
			result.setModel(false);
			return result;
		}
		return result;
	}

	@Override
	public List<Member> queryMemberListByIdList(List<Long> memberIdList) {
		if (CollectionUtils.isEmpty(memberIdList)) {
			throw new JcobServerException(ExceptionCode.paramError);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("memberIdList", memberIdList);
		return memberDbDao.queryList("MemberMapper.queryMemberListByIdList", param);
	}

}
