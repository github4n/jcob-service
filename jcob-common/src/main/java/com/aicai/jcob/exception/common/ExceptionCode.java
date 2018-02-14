package com.aicai.jcob.exception.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author fx
 *
 */
public class ExceptionCode implements Serializable {

    private static final long serialVersionUID = -2892956957010575101L;
    
    ///////////////////////////////////////////////////////ExceptionCode的定义
    /**参数错误*/
    public static final ExceptionCode paramError = new ExceptionCode("server.paramError", "参数错误");
    /**没有实现服务错误 */
    public static final ExceptionCode notImplement = new ExceptionCode("server.notImplement","没有实现接口");
    
    public static final ExceptionCode optimisticFailure = new ExceptionCode("server.optimisticFailure","乐观更新失败");
    
    //add by zhanghl 2016-01-25 添加支付异常常量 begin
    
    /**
     * 用户中心异常
     */
    public static final ExceptionCode MEMBER_NOT_EXIST = new ExceptionCode("member.not.exist", "用户不存在");
    public static final ExceptionCode MEMBER_ID_NOTEQUALS = new ExceptionCode("member.id.notequals", "用户ID不匹配");
    public static final ExceptionCode MEMBER_ID_NOTNULL = new ExceptionCode("member.id.notnull", "充值用户账号不能空");
    public static final ExceptionCode MEMBER_NICKNAME_NOTNULL = new ExceptionCode("member.nickname.notnull", "昵称不能为空");
    public static final ExceptionCode MEMBER_NICKNAME_LENGTH_ERROR = new ExceptionCode("member.nickname.length.error", "昵称长度错误");
    public static final ExceptionCode MEMBER_NICKNAME_COMPONENT_ERROR = new ExceptionCode("member.nickname.componet.error", "昵称格式错误");
    public static final ExceptionCode MEMBER_NICKNAME_EXISTS = new ExceptionCode("member.nickname.exists", "昵称已经存在");
    public static final ExceptionCode MOBILE_NOT_NULL = new ExceptionCode("mobile.not.null", "手机码号不能为空");
    public static final ExceptionCode MEMBER_ICON_NOTNULL = new ExceptionCode("member.icon.notnull", "用户头像不能为空");
    public static final ExceptionCode MEMBER_EMAIL_NOTNULL = new ExceptionCode("member.email.notnull", "邮箱不能为空");
    public static final ExceptionCode MEMBER_MOBILE_NOTPASSVALID = new ExceptionCode("member.mobile.notpassvalid", "手机号码未通过验证");
    public static final ExceptionCode MEMBER_CERTNO_NOTNULL = new ExceptionCode("member.certno.notnull", "身份证不能为空");
    public static final ExceptionCode MEMBER_REALNAME_NOTNULL = new ExceptionCode("member.realname.notnull", "真实姓名不能为空");
    public static final ExceptionCode MEMBER_CERTTYPE_NOTNULL = new ExceptionCode("member.certtype.notnull", "证件类型不能为空");
    public static final ExceptionCode MEMBER_CLIENTVERSION_NOTNULL = new ExceptionCode("member.clientversion.notnull", "客户端版本不能为空");
    public static final ExceptionCode MEMBER_REGISTERTYPE_NOTNULL = new ExceptionCode("member.registertype.notnull", "注册类型不能为空");
    public static final ExceptionCode MEMBER_PASSWORD_NOTNULL = new ExceptionCode("member.password.notnull", "密码不能为空");
    public static final ExceptionCode MEMBER_PASSWORD_NOTCORRECT = new ExceptionCode("member.password.notcorrect", "密码不正确");
    public static final ExceptionCode MEMBER_CHARGELOG_ISNULL = new ExceptionCode("member.chargelog.isnull","充值操作记录为空");
    public static final ExceptionCode MEMBER_TOKEN_ISNULL = new ExceptionCode("member.token.isnull","token不存在");
    public static final ExceptionCode MEMBER_TOKEN_UNEFFECTIVE = new ExceptionCode("member.token.uneffective","token已经失效");
    public static final ExceptionCode MEMBER_INFO_NOTNULL = new ExceptionCode("member.info.notnull","用户信息不能为空");
    public static final ExceptionCode MEMBER_LOGIN_ISNULL = new ExceptionCode("member.login.isnull","用户登录信息为空");
    public static final ExceptionCode MEMBER_LOGIN_STATUS_INVALID = new ExceptionCode("member.login.status.error","用户登录信息状态无效");
    public static final ExceptionCode MEMBER_LOGIN_EXISTS = new ExceptionCode("member.login.exists","用户登录信息已经存在");
    public static final ExceptionCode MEMBER_REGISTERINFO_NOTNULL = new ExceptionCode("member.registerInfo.notnull","注册信息不能为空");
    public static final ExceptionCode MEMBER_REGISTERID_NOTNULL = new ExceptionCode("member.registerId.notnull","用户注册号不能为空");
    public static final ExceptionCode MEMBER_REGISTER_ALREADY_EXISTS = new ExceptionCode("member.registerId.alreadyExists.error","该注册号已经存在");
    public static final ExceptionCode MEMBER_OPERATORINFO_NOTNULL = new ExceptionCode("member.operOption.isnull.error","用户操作信息不能为空");
    public static final ExceptionCode MEMBER_MOBILE_ISNULL = new ExceptionCode("member.mobile.isnull","手机号不存在");
    public static final ExceptionCode MEMBER_MOBILE_ALREADY_USED = new ExceptionCode("member.mobile.alreadyused.error","手机号已经在使用中");
    public static final ExceptionCode MEMBER_MOBILE_UNEFFECTIVE = new ExceptionCode("member.mobile.uneffective","手机号码无效");
    public static final ExceptionCode MEMBER_MOBILE_ALREADY_VALID = new ExceptionCode("meber.mobile.alreadyvalid.error","手机号码已经被验证过");
    public static final ExceptionCode MEMBER_NICKNAME_ILLEGALITY = new ExceptionCode("member.nickname.illegality", "昵称包含非法字符");
    public static final ExceptionCode MEMBER_CERTIFICATION_ALREADY_VALID = new ExceptionCode("meber.certification.alreadyvalid.error","身份信息已经被验证过");
    /**
     * 验证码
     */
    public static final ExceptionCode MEMBER_PHONE_BLACK_LIST = new ExceptionCode("member.phone.black.list", "手机号属于黑名单");
    public static final ExceptionCode MEMBER_AUTHCODE_PARAM_ISNULL = new ExceptionCode("member.parm.isnull", "手机号和验证码必须输入");
    public static final ExceptionCode MEMBER_AUTHCODE_ERROR = new ExceptionCode("member.authcode.error", "验证码错误");
    public static final ExceptionCode MEMBER_AUTHCODE_EXPIRED = new ExceptionCode("member.authcode.expired", "验证码已失效");
    public static final ExceptionCode MEMBER_AUTHCODE_OVER_GET = new ExceptionCode("member.authcode.over.get", "获取次数过多，请24小时后再获取");
    public static final ExceptionCode MEMBER_AUTHCODE_OVER_VERIFY = new ExceptionCode("member.authcode.over.verify", "验证次数过多，请24小时后再验证");
    public static final ExceptionCode MEMBER_AUTHCODE_OVER_FREQUENCY = new ExceptionCode("member.authcode.over.frequency", "不能再次发送,间隔时间太短");
    public static final ExceptionCode MEMBER_AUTHCODE_SYSTEM_ERROR = new ExceptionCode("member.authcode.system.error", "验证码系统出现错误");
    
    /**
     * 支付网关异常
     */
    public static final ExceptionCode PAYMENT_CHANNEL_NOTNULL = new ExceptionCode("payment.channel.notnull", "渠道不能空");
    public static final ExceptionCode PAYMENT_CHARGEWAY_NOTNULL = new ExceptionCode("payment.chargeWay.notnull", "充值方式不能空");
    public static final ExceptionCode PAYMENT_CHARGEWAY_NOTSUPPORT = new ExceptionCode("payment.chargeWay.notsupport", "充值方式暂不支持");
    public static final ExceptionCode PAYMENT_SELLCLIENT_NOTNULL = new ExceptionCode("payment.sellclient.notnull", "充值客户端不能空");
    public static final ExceptionCode PAYMENT_SIGN_NOTNULL = new ExceptionCode("payment.sign.notnull", "签名不能空");
    public static final ExceptionCode PAYMENT_CLIENTTYPE_NOTNULL = new ExceptionCode("payment.clienttype.not.null", "客户端类型不能为空");
    public static final ExceptionCode PAYMENT_SIGN_FAIL = new ExceptionCode("payment.sign.fail", "签名验证失败");
    public static final ExceptionCode PAYMENT_PARTNER_NOTEXIST = new ExceptionCode("payment.partner.notexist.", "充值合作商不存在");
    public static final ExceptionCode PAYMENT_CHARGENO_NOTNULL = new ExceptionCode("payment.chargeNo.notnull","充值流水不能为空");
    public static final ExceptionCode PAYMENT_CHARGELOG_NOTFOUND = new ExceptionCode("payment.chargelog.notfound","未找到充值订单");
    public static final ExceptionCode PAYMENT_CHARGENO_FORMAT_INCORRECT = new ExceptionCode("payment.chargeNo.format.error","充值流水格式错误");
    public static final ExceptionCode PAYMENT_PAYDATA_ERROR = new ExceptionCode("payment.paydata.error","网关数据被篡改");
    public static final ExceptionCode PAYMENT_REFFER_ERROR = new ExceptionCode("payment.reffer.error","请重新从网站发起充值请求");	/**来源不明*/
    public static final ExceptionCode PAYMENT_CHANNEL_FORBIDDEN = new ExceptionCode("payment.channel.forbidden","渠道已被禁用");
    public static final ExceptionCode PAYMENT_CHARGEDECIMAL_ERROR = new ExceptionCode("payment.chargedecimal.error","充值金额精度错误");
    public static final ExceptionCode PAYMENT_RETURNURL_NOTNULL = new ExceptionCode("payment.returnurl.notnull","同步返回地址不能为空");
    public static final ExceptionCode PAYMENT_AMOUNT_NOT_LESS_THEN_ZERO = new ExceptionCode("payment.amount.not.lessthen.zero", "充值金额须大于0");
    public static final ExceptionCode PAYMENT_WEIXIN_PREORDEREXCEPTION = new ExceptionCode("payment.weixin.preorderexception", "微信支付统一下单异常");
    public static final ExceptionCode PAYMENT_CREDITCARD_NOTSUPPORT = new ExceptionCode("payment.creditcard.notsupport", "不支持信用卡充值");
    public static final ExceptionCode PAYMENT_AMOUNT_MORETHAN_SINGLELIMIT = new ExceptionCode("payment.amount.morethan.singlelimit", "该银行卡单笔最高支付{}元");
    public static final ExceptionCode PAYMENT_AMOUNT_MORETHAN_DAYLIMIT = new ExceptionCode("payment.amount.morethan.daylimit", "该银行卡单日最高支付{}元");
    public static final ExceptionCode PAYMENT_AMOUNT_MORETHAN_MONTHLIMIT = new ExceptionCode("payment.amount.morethan.monthlimit", "该银行卡单月最高支付{}元");
    public static final ExceptionCode PAYMENT_BANKCARD_NOTNULL = new ExceptionCode("payment.bankcard.notnull", "银行卡号不能为空");
    
    /**
     * jing.ming 后台人工添加专家(要求必须已注册) 2016-1-27
     */
    public static final ExceptionCode EXPERT_NOT_REGISTER = new ExceptionCode("expert.register.must.required","用户未注册,不能设置为专家");
    public static final ExceptionCode EXPERT_APPLY_MEMBERID_ERROR = new ExceptionCode("expert.apply.memberId.error","专家申请memberId错误");
    public static final ExceptionCode EXPERT_APPLY_REASON_NOT_NULL = new ExceptionCode("expert.apply.reason.not.null","专家申请理由不能为空");
    public static final ExceptionCode EXPERT_NOT_EXIST = new ExceptionCode("expert.not.exist", "专家不存在");
    public static final ExceptionCode EXPERT_APPLY_UPDATE_ERROR = new ExceptionCode("expert.apply.update.error", "更新专家审核错误");
    public static final ExceptionCode EXPERT_WINRATIO_UPDATE_ERROR = new ExceptionCode("update.WinRatio.and.openedCount.error", "更新命中率和发布方案数出错");
    public static final ExceptionCode EXPERT_MEMBER_NOT_EXIST = new ExceptionCode("tjExpert.member.not.exist", "专家memberId对应的用户不存在");
    public static final ExceptionCode EXPERT_RECOMMENDRESULT_IS_NULL = new ExceptionCode("tjExpert.recommend.member.not.exist", "查询推荐专家为空");
    public static final ExceptionCode EXPERT_LEVEL_AUTO_UPDATE_ERROR = new ExceptionCode("expert.auto.update.level.error", "自动更新专家等级出错");
    public static final ExceptionCode EXPERT_LEVEL_LOG_INSERT_ERROR = new ExceptionCode("expert.level.Log.insert.error", "插入专家升级日志出错");
    public static final ExceptionCode EXPERT_UPDATE_ATTENTIONME_TO_ZERO_ERROR = new ExceptionCode("expert.attentionMe.to.zero.error", "专家降为普通用户后置零专家的attentionMe出错");
    public static final ExceptionCode EXPERT_ATTENTIONSTATUS_UPDATE_ERROR = new ExceptionCode("expert.attentionStatus.update.error", "专家关注状态修改出错");
    public static final ExceptionCode EXPERT_ATTENTION_OTHER_UPDATE_ERROR = new ExceptionCode("expert.attentionOther.sub.error", "粉丝关注的人数修改出错");
    public static final ExceptionCode EXPERT_ADMIN_SET_REQUIRE_MEMBER_NOT_EXPERT = new ExceptionCode("expert.admin.set.require.member.not.expert", "后台人工设置专家，要求用户当前不是专家身份");
    
    private static Map<String,ExceptionCode> strCode2ExceptionCode = new HashMap<>();
    
    /**错误代码**/
    public String code;

    /**错误代码对应消息**/
    public String msg;
     
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    
	public ExceptionCode(String code, String msg) {
        this.msg = msg;
        this.code = code;
        if(null == strCode2ExceptionCode) strCode2ExceptionCode = new HashMap<String, ExceptionCode>();
        strCode2ExceptionCode.put(code, this);
    }
	
	public static ExceptionCode getExceptionCode(String code){
		return strCode2ExceptionCode.get(code);
	}
	
	public static String getExceptionMsg(String code){
		ExceptionCode exCode = strCode2ExceptionCode.get(code);
		if(exCode == null){
			return "";
		}
		return exCode.getMsg();
	}

}
