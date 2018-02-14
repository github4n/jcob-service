package com.aicai.jcob.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 快捷支付限额表
 * @author hailong.zhang
 *
 */
public class BankLimitType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8129160505316158043L;

	/**
	 * 银行行号
	 */
	private String bankCode;
	
	/**
	 * 银行名称
	 */
	private String bankName;
	
	/**
	 * 系统内问编号
	 */
	private String innerCode;
	
	/**
	 * 单笔交易限额
	 */
	private BigDecimal singleLimit;
	
	/**
	 * 单日交易限额
	 */
	private BigDecimal dayLimit;
	
	/**
	 * 单月交易限额
	 */
	private BigDecimal monthLimit;
	
	/**
	 * 卡类型:2借记卡，3贷记卡
	 */
	private String payType;
	
	/**
	 * 备注
	 */
	private String remark;
	
	private static  Map<String, BankLimitType> lianLianBankLimitMap = new HashMap<String, BankLimitType>();

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(BigDecimal singleLimit) {
		this.singleLimit = singleLimit;
	}

	public BigDecimal getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(BigDecimal dayLimit) {
		this.dayLimit = dayLimit;
	}

	public BigDecimal getMonthLimit() {
		return monthLimit;
	}

	public void setMonthLimit(BigDecimal monthLimit) {
		this.monthLimit = monthLimit;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public BankLimitType(){}
	
	public BankLimitType(String bankCode,String bankName,BigDecimal singleLimit,BigDecimal dayLimit,BigDecimal monthLimit,String payType,String innerCode,String remark) {
		this.setBankCode(bankCode);
		this.setBankName(bankName);
		this.setSingleLimit(singleLimit);
		this.setDayLimit(dayLimit);
		this.setMonthLimit(monthLimit);
		this.setPayType(payType);
		this.setInnerCode(innerCode);
		this.setRemark(remark);
	}
	
	//借记卡
	public static BankLimitType BANK_GSYH = new BankLimitType("01020000","中国工商银行",new BigDecimal(5000),new BigDecimal(50000),new BigDecimal(50000),"2","GSYH",null);
	public static BankLimitType BANK_NYYH = new BankLimitType("01030000","中国农业银行",new BigDecimal(5000),new BigDecimal(5000),null,"2","NYYH",null);
	public static BankLimitType BANK_ZGYH = new BankLimitType("01040000","中国银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZGYH",null);
	public static BankLimitType BANK_ZSYH = new BankLimitType("03080000","招商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZSYH",null);
	public static BankLimitType BANK_YZYH = new BankLimitType("01000000","邮政储蓄银行",new BigDecimal(5000),null,null,"2","YZYH",null);
	
	public static BankLimitType BANK_PFYH = new BankLimitType("03100000","浦发银行",new BigDecimal(20000),new BigDecimal(20000),null,"2","PFYH",null);
	public static BankLimitType BANK_HXYH = new BankLimitType("03040000","华夏银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HXYH",null);
	public static BankLimitType BANK_PAYH = new BankLimitType("03070000","平安银行",new BigDecimal(50000),new BigDecimal(50000),null,"2","PAYH",null);
	public static BankLimitType BANK_GDYH = new BankLimitType("03030000","光大银行",new BigDecimal(30000),new BigDecimal(30000),null,"2","GDYH",null);
	public static BankLimitType BANK_HZYH = new BankLimitType("04233310","杭州银行",new BigDecimal(50000),new BigDecimal(50000),null,"2","HZYH",null);
	
	public static BankLimitType BANK_JSYH = new BankLimitType("01050000","建设银行",new BigDecimal(10000),new BigDecimal(10000),new BigDecimal(50000),"2","JSYH",null);
	public static BankLimitType BANK_MSYH = new BankLimitType("03050000","民生银行",new BigDecimal(5000),new BigDecimal(5000),null,"2","MSYH",null);
	public static BankLimitType BANK_GDFZYH = new BankLimitType("03060000","广发银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GDFZYH",null);
	public static BankLimitType BANK_BJYH = new BankLimitType("04031000","北京银行",new BigDecimal(20000),new BigDecimal(20000),null,"2","BJYH",null);
	public static BankLimitType BANK_JTYH = new BankLimitType("03010000","交通银行",new BigDecimal(10000),new BigDecimal(10000),new BigDecimal(50000),"2","JTYH",null);
	
	public static BankLimitType BANK_XYYH = new BankLimitType("03090000","兴业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","XYYH",null);
	public static BankLimitType BANK_ZXYH = new BankLimitType("03020000","中信银行",new BigDecimal(20000),new BigDecimal(50000),new BigDecimal(150000),"2","ZXYH",null);
	public static BankLimitType BANK_BHYH = new BankLimitType("03170000","渤海银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","BHYH",null);
	public static BankLimitType BANK_SHYH = new BankLimitType("04012900","上海银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SHYH",null);
	public static BankLimitType BANK_JSUYH = new BankLimitType("05083000","江苏银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JSUYH",null);
	
	public static BankLimitType BANK_JHYH = new BankLimitType("04263380","金华银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JHYH",null);
	public static BankLimitType BANK_NJYH = new BankLimitType("04243010","南京银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","NJYH",null);
	public static BankLimitType BANK_WZYH = new BankLimitType("04123330","温州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","WZYH",null);
	public static BankLimitType BANK_HBYH = new BankLimitType("04221210","河北银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HBYH",null);
	public static BankLimitType BANK_BSYH = new BankLimitType("04791920","包商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","BSYH",null);
	
	public static BankLimitType BANK_CDUYH = new BankLimitType("64296510","成都银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CDUYH",null);
	public static BankLimitType BANK_CQNCSYYH = new BankLimitType("14136900","重庆农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CQNCSYYH",null);
	public static BankLimitType BANK_ZZHOUYH = new BankLimitType("04354910","郑州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZZHOUYH",null);
	public static BankLimitType BANK_FJHXYH = new BankLimitType("04053910","福建海峡银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","FJHXYH",null);
	public static BankLimitType BANK_ASSYYH = new BankLimitType("05197110","安顺市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ASSYYH",null);
	
	public static BankLimitType BANK_AYYH = new BankLimitType("17219924","安阳银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","AYYH",null);
	public static BankLimitType BANK_BDYH = new BankLimitType("05521340","保定银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","BDYH",null);
	public static BankLimitType BANK_CDNCSYYH = new BankLimitType("65226510","成都农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CDNCSYYH",null);
	public static BankLimitType BANK_CSNCSYYH = new BankLimitType("14033055","常熟农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CSNCSYYH",null);
	public static BankLimitType BANK_CDYH = new BankLimitType("65131410","承德银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CDYH",null);
	
	public static BankLimitType BANK_CZYH = new BankLimitType("04761430","沧州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CZYH",null);
	public static BankLimitType BANK_CZSSYYH = new BankLimitType("05121660","长治市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CZSSYYH",null);
	public static BankLimitType BANK_DEYANGYH = new BankLimitType("04986580","德阳银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","DEYANGYH",null);
	public static BankLimitType BANK_DGNCSYYH = new BankLimitType("14156020","东莞农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","DGNCSYYH",null);
	public static BankLimitType BANK_EEDSYH = new BankLimitType("05342050","鄂尔多斯银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","EEDSYH",null);
	
	public static BankLimitType BANK_FUSHUNYH = new BankLimitType("04302240","抚顺银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","FUSHUNYH",null);
	public static BankLimitType BANK_FUDIANYH = new BankLimitType("64667310","富滇银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","FUDIANYH",null);
	public static BankLimitType BANK_GXBBWYH = new BankLimitType("64786110","广西北部湾银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GXBBWYH",null);
	public static BankLimitType BANK_GXNCXYS = new BankLimitType("14436100","广西农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","GXNCXYS",null);
	public static BankLimitType BANK_GZYH = new BankLimitType("64135810","广州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GZYH",null);
	
	public static BankLimitType BANK_GDNCXYS = new BankLimitType("14505800","广东农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","GDNCXYS",null);
	public static BankLimitType BANK_GZNCSYYH = new BankLimitType("14055810","广州农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GZNCSYYH",null);
	public static BankLimitType BANK_GANZHOUYH = new BankLimitType("64634280","赣州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GANZHOUYH",null);
	public static BankLimitType BANK_GUILINYH = new BankLimitType("64916170","桂林银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GUILINYH",null);
	public static BankLimitType BANK_GYYH = new BankLimitType("64437010","贵阳银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","GYYH",null);
	
	public static BankLimitType BANK_HBNCXYS = new BankLimitType("14411200","河北省农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","HBNCXYS",null);
	public static BankLimitType BANK_HEBIYH = new BankLimitType("05354970","鹤壁银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HEBIYH",null);
	public static BankLimitType BANK_HENGSSSYYH = new BankLimitType("05611480","衡水市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HENGSSSYYH",null);
	public static BankLimitType BANK_HEBYH = new BankLimitType("04422610","哈尔滨银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HEBYH",null);
	public static BankLimitType BANK_HAINNCXYS = new BankLimitType("14486400","海南省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"2","HAINNCXYS",null);
	
	public static BankLimitType BANK_HNNCXYS = new BankLimitType("14385500","湖南省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"2","HNNCXYS",null);
	public static BankLimitType BANK_HUBEINCXYS = new BankLimitType("14105200","湖北省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"2","HUBEINCXYS",null);
	public static BankLimitType BANK_JZYH = new BankLimitType("64392270","锦州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JZYH",null);
	public static BankLimitType BANK_HULUDAOYH = new BankLimitType("04332350","葫芦岛银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HULUDAOYH",null);
	public static BankLimitType BANK_HANDANYH = new BankLimitType("05171270","邯郸银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","HANDANYH",null);
	
	public static BankLimitType BANK_JIAXINGYH = new BankLimitType("04703350","嘉兴银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JIAXINGYH",null);
	public static BankLimitType BANK_JICHENGYH = new BankLimitType("05031680","晋城银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JICHENGYH",null);
	public static BankLimitType BANK_JXNCXYS = new BankLimitType("14394200","江西农村信用联合社",new BigDecimal(10000),new BigDecimal(10000),null,"2","JXNCXYS",null);
	public static BankLimitType BANK_JSNCXYS = new BankLimitType("14243000","江苏省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"2","JSNCXYS",null);
	public static BankLimitType BANK_JYNCSYYH = new BankLimitType("14123020","江阴农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JYNCSYYH",null);
	
	public static BankLimitType BANK_JNNCSYYH = new BankLimitType("14603040","江南农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JNNCSYYH",null);
	public static BankLimitType BANK_JLNCXYS = new BankLimitType("14452400","吉林省农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","JLNCXYS",null);
	public static BankLimitType BANK_JILINYH = new BankLimitType("04512420","吉林银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JILINYH",null);
	public static BankLimitType BANK_JZSSYYH = new BankLimitType("05591750","晋中市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JZSSYYH",null);
	public static BankLimitType BANK_JJYH = new BankLimitType("64544240","九江银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JJYH",null);
	
	public static BankLimitType BANK_KSNCSYYH = new BankLimitType("14023052","昆山农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","KSNCSYYH",null);
	public static BankLimitType BANK_KELSSYYH = new BankLimitType("05658880","库尔勒市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","KELSSYYH",null);
	public static BankLimitType BANK_KLYH = new BankLimitType("05478820","昆仑银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","KLYH",null);
	public static BankLimitType BANK_LJYH = new BankLimitType("04922600","龙江银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LJYH",null);
	public static BankLimitType BANK_LSZSYYH = new BankLimitType("05556840","凉山州商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LSZSYYH",null);
	
	public static BankLimitType BANK_LSSSYYH = new BankLimitType("05406650","乐山市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LSSSYYH",null);
	public static BankLimitType BANK_LINSYH = new BankLimitType("64314730","临商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LINSYH",null);
	public static BankLimitType BANK_LIUZHOUYH = new BankLimitType("04956140","柳州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LIUZHOUYH",null);
	public static BankLimitType BANK_LUOHEYH = new BankLimitType("05565040","漯河银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LUOHEYH",null);
	public static BankLimitType BANK_LZYH = new BankLimitType("04478210","兰州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","LZYH",null);
	
	public static BankLimitType BANK_NMGYH = new BankLimitType("04741910","内蒙古银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","NMGYH",null);
	public static BankLimitType BANK_NMGNCXYS = new BankLimitType("04741910","内蒙古农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","NMGNCXYS",null);
	public static BankLimitType BANK_NBDHYH = new BankLimitType("05503320","宁波东海银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","NBDHYH",null);
	public static BankLimitType BANK_NXHHNSYH = new BankLimitType("14468700","宁夏黄河农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","NXHHNSYH",null);
	public static BankLimitType BANK_NCYH = new BankLimitType("64484210","南昌银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","NCYH",null);
	
	public static BankLimitType BANK_NCSSYYH = new BankLimitType("04966730","南充市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","NCSSYYH",null);
	public static BankLimitType BANK_PDSYH = new BankLimitType("05484950","平顶山银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","PDSYH",null);
	public static BankLimitType BANK_PZHSSYYH = new BankLimitType("04836560","攀枝花市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","PZHSSYYH",null);
	public static BankLimitType BANK_JLYH = new BankLimitType("64094510","齐鲁银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","JLYH",null);
	public static BankLimitType BANK_QHDSSYYH = new BankLimitType("04571260","秦皇岛市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","QHDSSYYH",null);
	
	public static BankLimitType BANK_QZYH = new BankLimitType("04643970","泉州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","QZYH",null);
	public static BankLimitType BANK_QJSSYYH = new BankLimitType("05027360","曲靖市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","QJSSYYH",null);
	public static BankLimitType BANK_QHSNCXYS = new BankLimitType("14498500","青海省农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","QHSNCXYS",null);
	public static BankLimitType BANK_QDYH = new BankLimitType("04504520","青岛银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","QDYH",null);
	public static BankLimitType BANK_SHNSYH = new BankLimitType("65012900","上海农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SHNSYH",null);
	
	public static BankLimitType BANK_SMXYH = new BankLimitType("04885050","三门峡银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SMXYH",null);
	public static BankLimitType BANK_SRYH = new BankLimitType("65264330","上饶银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SRYH",null);
	public static BankLimitType BANK_SZFTYZCZYH = new BankLimitType("15205840","深圳福田银座村镇银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SZFTYZCZYH",null);
	public static BankLimitType BANK_SQYH = new BankLimitType("65675060","商丘银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SQYH",null);
	public static BankLimitType BANK_SZNSYH = new BankLimitType("14045840","深圳农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SZNSYH",null);
	
	public static BankLimitType BANK_SZYH = new BankLimitType("04213050","苏州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SZYH",null);
	public static BankLimitType BANK_SNSSYYH = new BankLimitType("05516620","遂宁市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SNSSYYH",null);
	public static BankLimitType BANK_SDNSYH = new BankLimitType("65085883","顺德农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","SDNSYH",null);
	public static BankLimitType BANK_SCSNCXYS = new BankLimitType("14526500","四川省农村信用合作社",new BigDecimal(10000),new BigDecimal(10000),null,"2","SCSNCXYS",null);
	public static BankLimitType BANK_SDSNCXYS = new BankLimitType("14144500","山东省农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","SDSNCXYS",null);
	
	public static BankLimitType BANK_SXSNCXYS = new BankLimitType("14551600","山西省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"2","SXSNCXYS",null);
	public static BankLimitType BANK_TASSYYH = new BankLimitType("05284630","泰安市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","TASSYYH",null);
	public static BankLimitType BANK_TCNCSYYH = new BankLimitType("14333051","太仓农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","TCNCSYYH",null);
	public static BankLimitType BANK_TSSSYYH = new BankLimitType("04991240","唐山市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","TSSSYYH",null);
	public static BankLimitType BANK_TJBHNCSYYH = new BankLimitType("14561100","天津滨海农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","TJBHNCSYYH",null);
	
	public static BankLimitType BANK_TZYH = new BankLimitType("04593450","台州银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","TZYH",null);
	public static BankLimitType BANK_WFYH = new BankLimitType("64624580","潍坊银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","WFYH",null);
	public static BankLimitType BANK_WHSSYYH = new BankLimitType("03134650","威海市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","WHSSYYH",null);
	public static BankLimitType BANK_WUHAIYH = new BankLimitType("05311930","乌海银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","WUHAIYH",null);
	public static BankLimitType BANK_WJNSYH = new BankLimitType("14283054","吴江农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","WJNSYH",null);
	
	public static BankLimitType BANK_YCSSYYH = new BankLimitType("04325260","宜昌市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YCSSYYH",null);
	public static BankLimitType BANK_WLMQYH = new BankLimitType("04270001","乌鲁木齐市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","WLMQYH",null);
	public static BankLimitType BANK_XMYH = new BankLimitType("04023930","厦门银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","XMYH",null);
	public static BankLimitType BANK_XCYH = new BankLimitType("05365030","许昌银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","XCYH",null);
	public static BankLimitType BANK_YTYH = new BankLimitType("05541310","邢台银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YTYH",null);
	
	public static BankLimitType BANK_YBSSYYH = new BankLimitType("05646710","宜宾市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YBSSYYH",null);
	public static BankLimitType BANK_YASSYYH = new BankLimitType("05666770","雅安市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YASSYYH",null);
	public static BankLimitType BANK_YQSSYYH = new BankLimitType("05631650","阳泉市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YQSSYYH",null);
	public static BankLimitType BANK_YXSSYYH = new BankLimitType("05247410","玉溪市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YXSSYYH",null);
	public static BankLimitType BANK_YNSNCXYS = new BankLimitType("65097300","云南省农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"2","YNSNCXYS",null);
	
	public static BankLimitType BANK_YKYH = new BankLimitType("04652280","营口银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","YKYH",null);
	public static BankLimitType BANK_ZJCZSYYH = new BankLimitType("05303380","浙江稠州商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZJCZSYYH",null);
	public static BankLimitType BANK_ZJMTSYYH = new BankLimitType("05253450","浙江民泰商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZJMTSYYH",null);
	public static BankLimitType BANK_CYYH = new BankLimitType("05492340","朝阳银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CYYH",null);
	public static BankLimitType BANK_ZJGNCSYYH = new BankLimitType("14163056","张家港农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZJGNCSYYH",null);
	
	public static BankLimitType BANK_ZJKSSYYH = new BankLimitType("04901380","张家口市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZJKSSYYH",null);
	public static BankLimitType BANK_ZGSSYYH = new BankLimitType("05326560","自贡市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZGSSYYH",null);
	public static BankLimitType BANK_ZKYH = new BankLimitType("05625080","周口银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZKYH",null);
	public static BankLimitType BANK_ZYSSYYH = new BankLimitType("05167030","遵义市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZYSSYYH",null);
	public static BankLimitType BANK_ZDYH = new BankLimitType("03220000","渣打银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","ZDYH",null);
	
	public static BankLimitType BANK_CQYH = new BankLimitType("04416900","重庆银行",new BigDecimal(10000),new BigDecimal(10000),null,"2","CQYH",null);
	
	//信用卡
	public static BankLimitType BANK_GSYH_C = new BankLimitType("01020000","中国工商银行",new BigDecimal(5000),new BigDecimal(50000),new BigDecimal(50000),"3","GSYH",null);
	public static BankLimitType BANK_PFYH_C = new BankLimitType("03100000","浦发银行",new BigDecimal(20000),new BigDecimal(20000),null,"3","PFYH",null);
	public static BankLimitType BANK_HXYH_C = new BankLimitType("03040000","华夏银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","HXYH",null);
	public static BankLimitType BANK_GDYH_C = new BankLimitType("03030000","光大银行",new BigDecimal(30000),new BigDecimal(30000),null,"3","GDYH",null);
	public static BankLimitType BANK_MSYH_C = new BankLimitType("03050000","民生银行",new BigDecimal(20000),new BigDecimal(20000),null,"3","MSYH",null);
	
	public static BankLimitType BANK_HZYH_C = new BankLimitType("04233310","杭州银行",new BigDecimal(50000),new BigDecimal(50000),null,"3","HZYH",null);
	public static BankLimitType BANK_JSYH_C = new BankLimitType("01050000","建设银行",new BigDecimal(10000),new BigDecimal(10000),new BigDecimal(50000),"3","JSYH",null);
	public static BankLimitType BANK_GDFZYH_C = new BankLimitType("03060000","广发银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","GDFZYH",null);
	public static BankLimitType BANK_ZXYH_C = new BankLimitType("03020000","中信银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","ZXYH",null);
	public static BankLimitType BANK_NYYH_C = new BankLimitType("01030000","农业银行",new BigDecimal(5000),new BigDecimal(5000),null,"3","NYYH",null);
	
	public static BankLimitType BANK_ZGYH_C = new BankLimitType("01040000","中国银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","ZGYH",null);
	public static BankLimitType BANK_XYYH_C = new BankLimitType("03090000","兴业银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","XYYH",null);
	public static BankLimitType BANK_PAYH_C = new BankLimitType("03070000","平安银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","PAYH",null);
	public static BankLimitType BANK_YZYH_C = new BankLimitType("01000000","邮政储蓄银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","YZYH",null);
	public static BankLimitType BANK_BJYH_C = new BankLimitType("04031000","北京银行",new BigDecimal(30000),new BigDecimal(50000),null,"3","BJYH",null);
	
	public static BankLimitType BANK_ZSYH_C = new BankLimitType("03080000","招商银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","ZSYH",null);
	public static BankLimitType BANK_JTYH_C = new BankLimitType("03010000","交通银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JTYH",null);
	public static BankLimitType BANK_SHYH_C = new BankLimitType("04012900","上海银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","SHYH",null);
	public static BankLimitType BANK_WSYH_C = new BankLimitType("04403600","徽商银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WSYH",null);
	public static BankLimitType BANK_JSUYH_C = new BankLimitType("05083000","江苏银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JSUYH",null);
	
	public static BankLimitType BANK_JHYH_C = new BankLimitType("04263380","金华银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JHYH",null);
	public static BankLimitType BANK_NJYH_C = new BankLimitType("04243010","南京银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","NJYH",null);
	public static BankLimitType BANK_WZYH_C = new BankLimitType("04123330","温州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WZYH",null);
	public static BankLimitType BANK_HBYH_C = new BankLimitType("04221210","河北银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","HBYH",null);
	public static BankLimitType BANK_BJNSYH_C = new BankLimitType("14181000","北京农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","BJNSYH",null);
	
	public static BankLimitType BANK_BSYH_C = new BankLimitType("04791920","包商银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","BSYH",null);
	public static BankLimitType BANK_CQNCSYYH_C = new BankLimitType("14136900","重庆农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","CQNCSYYH",null);
	public static BankLimitType BANK_CDNCSYYH_C = new BankLimitType("65226510","成都农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","CDNCSYYH",null);
	public static BankLimitType BANK_CSNCSYYH_C = new BankLimitType("14033055","常熟农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","CSNCSYYH",null);
	public static BankLimitType BANK_CSYH_C = new BankLimitType("04615510","长沙银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","CSYH",null);
	
	public static BankLimitType BANK_CDYH_C = new BankLimitType("65131410","承德银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","CDYH",null);
	public static BankLimitType BANK_CQYH_C = new BankLimitType("04416900","重庆银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","CQYH",null);
	public static BankLimitType BANK_DLYH_C = new BankLimitType("04202220","大连银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","DLYH",null);
	public static BankLimitType BANK_DONGYYH_C = new BankLimitType("65274550","东营银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","DONGYYH",null);
	public static BankLimitType BANK_EEDSYH_C = new BankLimitType("05342050","鄂尔多斯银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","EEDSYH",null);
	
	public static BankLimitType BANK_FJNCXYS_C = new BankLimitType("14173900","福建省农村信用社",new BigDecimal(10000),new BigDecimal(10000),null,"3","FJNCXYS",null);
	public static BankLimitType BANK_FUDIANYH_C = new BankLimitType("64667310","富滇银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","FUDIANYH",null);
	public static BankLimitType BANK_GZYH_C = new BankLimitType("64135810","广州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","GZYH",null);
	public static BankLimitType BANK_GZNCSYYH_C = new BankLimitType("14055810","广州农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","GZNCSYYH",null);
	public static BankLimitType BANK_GANZHOUYH_C = new BankLimitType("64634280","赣州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","GANZHOUYH",null);
	
	public static BankLimitType BANK_GYYH_C = new BankLimitType("64437010","贵阳银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","GYYH",null);
	public static BankLimitType BANK_HEBYH_C = new BankLimitType("04422610","哈尔滨银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","HEBYH",null);
	public static BankLimitType BANK_HNNCXYS_C = new BankLimitType("14385500","湖南省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"3","HNNCXYS",null);
	public static BankLimitType BANK_JSNCXYS_C = new BankLimitType("14243000","江苏省农村信用社联合社",new BigDecimal(10000),new BigDecimal(10000),null,"3","JSNCXYS",null);
	public static BankLimitType BANK_JNNCSYYH_C = new BankLimitType("14123020","江阴农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JNNCSYYH",null);
	
	public static BankLimitType BANK_JZYH_C = new BankLimitType("64392270","锦州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JZYH",null);
	public static BankLimitType BANK_JJYH_C = new BankLimitType("64544240","九江银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JJYH",null);
	public static BankLimitType BANK_LJYH_C = new BankLimitType("04922600","龙江银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","LJYH",null);
	public static BankLimitType BANK_LZYH_C = new BankLimitType("04478210","兰州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","LZYH",null);
	public static BankLimitType BANK_NBYH_C = new BankLimitType("04083320","宁波银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","NBYH",null);
	
	public static BankLimitType BANK_NCYH_C = new BankLimitType("64484210","南昌银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","NCYH",null);
	public static BankLimitType BANK_QHYH_C = new BankLimitType("64588510","青海银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","QHYH",null);
	public static BankLimitType BANK_JLYH_C = new BankLimitType("64094510","齐鲁银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JLYH",null);
	public static BankLimitType BANK_QDYH_C = new BankLimitType("04504520","青岛银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","QDYH",null);
	public static BankLimitType BANK_SHNSYH_C = new BankLimitType("65012900","上海农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","SHNSYH",null);
	
	public static BankLimitType BANK_SRYH_C = new BankLimitType("65264330","上饶银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","SRYH",null);
	public static BankLimitType BANK_SDNSYH_C = new BankLimitType("65085883","顺德农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","SDNSYH",null);
	public static BankLimitType BANK_SXRDNCSYYH_C = new BankLimitType("14341770","山西尧都农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","SXRDNCSYYH",null);
	public static BankLimitType BANK_TZYH_C = new BankLimitType("04593450","台州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","TZYH",null);
	public static BankLimitType BANK_WFYH_C = new BankLimitType("64624580","潍坊银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WFYH",null);
	
	public static BankLimitType BANK_WHSSYYH_C = new BankLimitType("03134650","威海市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WHSSYYH",null);
	public static BankLimitType BANK_WJNSYH_C = new BankLimitType("14283054","吴江农商银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WJNSYH",null);
	public static BankLimitType BANK_WXNCYH_C = new BankLimitType("04453020","无锡农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WXNCYH",null);
	public static BankLimitType BANK_WLMQYH_C = new BankLimitType("04270001","乌鲁木齐市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","WLMQYH",null);
	public static BankLimitType BANK_YCSSYYH_C = new BankLimitType("04325260","宜昌市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","YCSSYYH",null);
	
	public static BankLimitType BANK_YINCSSYYH_C = new BankLimitType("15438710","银川市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","YINCSSYYH",null);
	public static BankLimitType BANK_JZYHYH_C = new BankLimitType("14203320","鄞州银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","JZYHYH",null);
	public static BankLimitType BANK_ZJCZSYYH_C = new BankLimitType("05303380","浙江稠州商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","ZJCZSYYH",null);
	public static BankLimitType BANK_ZJMTSYYH_C = new BankLimitType("05253450","浙江民泰商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","ZJMTSYYH",null);
	public static BankLimitType BANK_ZJTLSYYH_C = new BankLimitType("64733450","浙江泰隆商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","ZJTLSYYH",null);
	
	public static BankLimitType BANK_SZFTYZCZYH_C = new BankLimitType("15205840","深圳福田银座村镇银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","SZFTYZCZYH",null);
	public static BankLimitType BANK_TJBHNCSYYH_C = new BankLimitType("14561100","天津滨海农村商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","TJBHNCSYYH",null);
	public static BankLimitType BANK_YASSYYH_C = new BankLimitType("05666770","雅安市商业银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","YASSYYH",null);
	public static BankLimitType BANK_ZKYH_C = new BankLimitType("05625080","周口银行",new BigDecimal(10000),new BigDecimal(10000),null,"3","ZKYH",null);
	
	
	
	
	static {
		//借记卡类
		lianLianBankLimitMap.put(BANK_GSYH.getBankCode() + "-" + BANK_GSYH.getPayType(), BANK_GSYH);
		lianLianBankLimitMap.put(BANK_NYYH.getBankCode() + "-" + BANK_NYYH.getPayType(), BANK_NYYH);
		lianLianBankLimitMap.put(BANK_ZGYH.getBankCode() + "-" + BANK_ZGYH.getPayType(), BANK_ZGYH);
		lianLianBankLimitMap.put(BANK_ZSYH.getBankCode() + "-" + BANK_ZSYH.getPayType(), BANK_ZSYH);
		lianLianBankLimitMap.put(BANK_YZYH.getBankCode() + "-" + BANK_YZYH.getPayType(), BANK_YZYH);
		
		lianLianBankLimitMap.put(BANK_PFYH.getBankCode() + "-" + BANK_PFYH.getPayType(), BANK_PFYH);
		lianLianBankLimitMap.put(BANK_HXYH.getBankCode() + "-" + BANK_HXYH.getPayType(), BANK_HXYH);
		lianLianBankLimitMap.put(BANK_PAYH.getBankCode() + "-" + BANK_PAYH.getPayType(), BANK_PAYH);
		lianLianBankLimitMap.put(BANK_GDYH.getBankCode() + "-" + BANK_GDYH.getPayType(), BANK_GDYH);
		lianLianBankLimitMap.put(BANK_HZYH.getBankCode() + "-" + BANK_HZYH.getPayType(), BANK_HZYH);
		
		lianLianBankLimitMap.put(BANK_JSYH.getBankCode() + "-" + BANK_JSYH.getPayType(), BANK_JSYH);
		lianLianBankLimitMap.put(BANK_MSYH.getBankCode() + "-" + BANK_MSYH.getPayType(), BANK_MSYH);
		lianLianBankLimitMap.put(BANK_GDFZYH.getBankCode() + "-" + BANK_GDFZYH.getPayType(), BANK_GDFZYH);
		lianLianBankLimitMap.put(BANK_BJYH.getBankCode() + "-" + BANK_BJYH.getPayType(), BANK_BJYH);
		lianLianBankLimitMap.put(BANK_JTYH.getBankCode() + "-" + BANK_JTYH.getPayType(), BANK_JTYH);
		
		lianLianBankLimitMap.put(BANK_XYYH.getBankCode() + "-" + BANK_XYYH.getPayType(), BANK_XYYH);
		lianLianBankLimitMap.put(BANK_ZXYH.getBankCode() + "-" + BANK_ZXYH.getPayType(), BANK_ZXYH);
		lianLianBankLimitMap.put(BANK_BHYH.getBankCode() + "-" + BANK_BHYH.getPayType(), BANK_BHYH);
		lianLianBankLimitMap.put(BANK_SHYH.getBankCode() + "-" + BANK_SHYH.getPayType(), BANK_SHYH);
		lianLianBankLimitMap.put(BANK_JSUYH.getBankCode() + "-" + BANK_JSUYH.getPayType(), BANK_JSUYH);
		
		lianLianBankLimitMap.put(BANK_JHYH.getBankCode() + "-" + BANK_JHYH.getPayType(), BANK_JHYH);
		lianLianBankLimitMap.put(BANK_NJYH.getBankCode() + "-" + BANK_NJYH.getPayType(), BANK_NJYH);
		lianLianBankLimitMap.put(BANK_WZYH.getBankCode() + "-" + BANK_WZYH.getPayType(), BANK_WZYH);
		lianLianBankLimitMap.put(BANK_HBYH.getBankCode() + "-" + BANK_HBYH.getPayType(), BANK_HBYH);
		lianLianBankLimitMap.put(BANK_BSYH.getBankCode() + "-" + BANK_BSYH.getPayType(), BANK_BSYH);
		
		lianLianBankLimitMap.put(BANK_CDUYH.getBankCode() + "-" + BANK_CDUYH.getPayType(), BANK_CDUYH);
		lianLianBankLimitMap.put(BANK_CQNCSYYH.getBankCode() + "-" + BANK_CQNCSYYH.getPayType(), BANK_CQNCSYYH);
		lianLianBankLimitMap.put(BANK_ZZHOUYH.getBankCode() + "-" + BANK_ZZHOUYH.getPayType(), BANK_ZZHOUYH);
		lianLianBankLimitMap.put(BANK_FJHXYH.getBankCode() + "-" + BANK_FJHXYH.getPayType(), BANK_FJHXYH);
		lianLianBankLimitMap.put(BANK_ASSYYH.getBankCode() + "-" + BANK_ASSYYH.getPayType(), BANK_ASSYYH);
		
		lianLianBankLimitMap.put(BANK_AYYH.getBankCode() + "-" + BANK_AYYH.getPayType(), BANK_AYYH);
		lianLianBankLimitMap.put(BANK_BDYH.getBankCode() + "-" + BANK_BDYH.getPayType(), BANK_BDYH);
		lianLianBankLimitMap.put(BANK_CDNCSYYH.getBankCode() + "-" + BANK_CDNCSYYH.getPayType(), BANK_CDNCSYYH);
		lianLianBankLimitMap.put(BANK_CSNCSYYH.getBankCode() + "-" + BANK_CSNCSYYH.getPayType(), BANK_CSNCSYYH);
		lianLianBankLimitMap.put(BANK_CDYH.getBankCode() + "-" + BANK_CDYH.getPayType(), BANK_CDYH);
		
		lianLianBankLimitMap.put(BANK_CZYH.getBankCode() + "-" + BANK_CZYH.getPayType(), BANK_CZYH);
		lianLianBankLimitMap.put(BANK_CZSSYYH.getBankCode() + "-" + BANK_CZSSYYH.getPayType(), BANK_CZSSYYH);
		lianLianBankLimitMap.put(BANK_DEYANGYH.getBankCode() + "-" + BANK_DEYANGYH.getPayType(), BANK_DEYANGYH);
		lianLianBankLimitMap.put(BANK_DGNCSYYH.getBankCode() + "-" + BANK_DGNCSYYH.getPayType(), BANK_DGNCSYYH);
		lianLianBankLimitMap.put(BANK_EEDSYH.getBankCode() + "-" + BANK_EEDSYH.getPayType(), BANK_EEDSYH);
		
		lianLianBankLimitMap.put(BANK_FUSHUNYH.getBankCode() + "-" + BANK_FUSHUNYH.getPayType(), BANK_FUSHUNYH);
		lianLianBankLimitMap.put(BANK_FUDIANYH.getBankCode() + "-" + BANK_FUDIANYH.getPayType(), BANK_FUDIANYH);
		lianLianBankLimitMap.put(BANK_GXBBWYH.getBankCode() + "-" + BANK_GXBBWYH.getPayType(), BANK_GXBBWYH);
		lianLianBankLimitMap.put(BANK_GXNCXYS.getBankCode() + "-" + BANK_GXNCXYS.getPayType(), BANK_GXNCXYS);
		lianLianBankLimitMap.put(BANK_GZYH.getBankCode() + "-" + BANK_GZYH.getPayType(), BANK_GZYH);
		
		lianLianBankLimitMap.put(BANK_GDNCXYS.getBankCode() + "-" + BANK_GDNCXYS.getPayType(), BANK_GDNCXYS);
		lianLianBankLimitMap.put(BANK_GZNCSYYH.getBankCode() + "-" + BANK_GZNCSYYH.getPayType(), BANK_GZNCSYYH);
		lianLianBankLimitMap.put(BANK_GANZHOUYH.getBankCode() + "-" + BANK_GANZHOUYH.getPayType(), BANK_GANZHOUYH);
		lianLianBankLimitMap.put(BANK_GUILINYH.getBankCode() + "-" + BANK_GUILINYH.getPayType(), BANK_GUILINYH);
		lianLianBankLimitMap.put(BANK_GYYH.getBankCode() + "-" + BANK_GYYH.getPayType(), BANK_GYYH);
		
		lianLianBankLimitMap.put(BANK_HBNCXYS.getBankCode() + "-" + BANK_HBNCXYS.getPayType(), BANK_HBNCXYS);
		lianLianBankLimitMap.put(BANK_HEBIYH.getBankCode() + "-" + BANK_HEBIYH.getPayType(), BANK_HEBIYH);
		lianLianBankLimitMap.put(BANK_HENGSSSYYH.getBankCode() + "-" + BANK_HENGSSSYYH.getPayType(), BANK_HENGSSSYYH);
		lianLianBankLimitMap.put(BANK_HEBYH.getBankCode() + "-" + BANK_HEBYH.getPayType(), BANK_HEBYH);
		lianLianBankLimitMap.put(BANK_HAINNCXYS.getBankCode() + "-" + BANK_HAINNCXYS.getPayType(), BANK_HAINNCXYS);
		
		lianLianBankLimitMap.put(BANK_HNNCXYS.getBankCode() + "-" + BANK_HNNCXYS.getPayType(), BANK_HNNCXYS);
		lianLianBankLimitMap.put(BANK_HUBEINCXYS.getBankCode() + "-" + BANK_HUBEINCXYS.getPayType(), BANK_HUBEINCXYS);
		lianLianBankLimitMap.put(BANK_JZYH.getBankCode() + "-" + BANK_JZYH.getPayType(), BANK_JZYH);
		lianLianBankLimitMap.put(BANK_HULUDAOYH.getBankCode() + "-" + BANK_HULUDAOYH.getPayType(), BANK_HULUDAOYH);
		lianLianBankLimitMap.put(BANK_HANDANYH.getBankCode() + "-" + BANK_HANDANYH.getPayType(), BANK_HANDANYH);
		
		lianLianBankLimitMap.put(BANK_JIAXINGYH.getBankCode() + "-" + BANK_JIAXINGYH.getPayType(), BANK_JIAXINGYH);
		lianLianBankLimitMap.put(BANK_JICHENGYH.getBankCode() + "-" + BANK_JICHENGYH.getPayType(), BANK_JICHENGYH);
		lianLianBankLimitMap.put(BANK_JXNCXYS.getBankCode() + "-" + BANK_JXNCXYS.getPayType(), BANK_JXNCXYS);
		lianLianBankLimitMap.put(BANK_JSNCXYS.getBankCode() + "-" + BANK_JSNCXYS.getPayType(), BANK_JSNCXYS);
		lianLianBankLimitMap.put(BANK_JYNCSYYH.getBankCode() + "-" + BANK_JYNCSYYH.getPayType(), BANK_JYNCSYYH);
		
		lianLianBankLimitMap.put(BANK_JNNCSYYH.getBankCode() + "-" + BANK_JNNCSYYH.getPayType(), BANK_JNNCSYYH);
		lianLianBankLimitMap.put(BANK_JLNCXYS.getBankCode() + "-" + BANK_JLNCXYS.getPayType(), BANK_JLNCXYS);
		lianLianBankLimitMap.put(BANK_JILINYH.getBankCode() + "-" + BANK_JILINYH.getPayType(), BANK_JILINYH);
		lianLianBankLimitMap.put(BANK_JZSSYYH.getBankCode() + "-" + BANK_JZSSYYH.getPayType(), BANK_JZSSYYH);
		lianLianBankLimitMap.put(BANK_JJYH.getBankCode() + "-" + BANK_JJYH.getPayType(), BANK_JJYH);
		
		lianLianBankLimitMap.put(BANK_KSNCSYYH.getBankCode() + "-" + BANK_KSNCSYYH.getPayType(), BANK_KSNCSYYH);
		lianLianBankLimitMap.put(BANK_KELSSYYH.getBankCode() + "-" + BANK_KELSSYYH.getPayType(), BANK_KELSSYYH);
		lianLianBankLimitMap.put(BANK_KLYH.getBankCode() + "-" + BANK_KLYH.getPayType(), BANK_KLYH);
		lianLianBankLimitMap.put(BANK_LJYH.getBankCode() + "-" + BANK_LJYH.getPayType(), BANK_LJYH);
		lianLianBankLimitMap.put(BANK_LSZSYYH.getBankCode() + "-" + BANK_LSZSYYH.getPayType(), BANK_LSZSYYH);
		
		lianLianBankLimitMap.put(BANK_LSSSYYH.getBankCode() + "-" + BANK_LSSSYYH.getPayType(), BANK_LSSSYYH);
		lianLianBankLimitMap.put(BANK_LINSYH.getBankCode() + "-" + BANK_LINSYH.getPayType(), BANK_LINSYH);
		lianLianBankLimitMap.put(BANK_LIUZHOUYH.getBankCode() + "-" + BANK_LIUZHOUYH.getPayType(), BANK_LIUZHOUYH);
		lianLianBankLimitMap.put(BANK_LUOHEYH.getBankCode() + "-" + BANK_LUOHEYH.getPayType(), BANK_LUOHEYH);
		lianLianBankLimitMap.put(BANK_LZYH.getBankCode() + "-" + BANK_LZYH.getPayType(), BANK_LZYH);
		
		lianLianBankLimitMap.put(BANK_NMGYH.getBankCode() + "-" + BANK_NMGYH.getPayType(), BANK_NMGYH);
		lianLianBankLimitMap.put(BANK_NMGNCXYS.getBankCode() + "-" + BANK_NMGNCXYS.getPayType(), BANK_NMGNCXYS);
		lianLianBankLimitMap.put(BANK_NBDHYH.getBankCode() + "-" + BANK_NBDHYH.getPayType(), BANK_NBDHYH);
		lianLianBankLimitMap.put(BANK_NXHHNSYH.getBankCode() + "-" + BANK_NXHHNSYH.getPayType(), BANK_NXHHNSYH);
		lianLianBankLimitMap.put(BANK_NCYH.getBankCode() + "-" + BANK_NCYH.getPayType(), BANK_NCYH);
		
		lianLianBankLimitMap.put(BANK_NCSSYYH.getBankCode() + "-" + BANK_NCSSYYH.getPayType(), BANK_NCSSYYH);
		lianLianBankLimitMap.put(BANK_PDSYH.getBankCode() + "-" + BANK_PDSYH.getPayType(), BANK_PDSYH);
		lianLianBankLimitMap.put(BANK_PZHSSYYH.getBankCode() + "-" + BANK_PZHSSYYH.getPayType(), BANK_PZHSSYYH);
		lianLianBankLimitMap.put(BANK_JLYH.getBankCode() + "-" + BANK_JLYH.getPayType(), BANK_JLYH);
		lianLianBankLimitMap.put(BANK_QHDSSYYH.getBankCode() + "-" + BANK_QHDSSYYH.getPayType(), BANK_QHDSSYYH);
		
		lianLianBankLimitMap.put(BANK_QZYH.getBankCode() + "-" + BANK_QZYH.getPayType(), BANK_QZYH);
		lianLianBankLimitMap.put(BANK_QJSSYYH.getBankCode() + "-" + BANK_QJSSYYH.getPayType(), BANK_QJSSYYH);
		lianLianBankLimitMap.put(BANK_QHSNCXYS.getBankCode() + "-" + BANK_QHSNCXYS.getPayType(), BANK_QHSNCXYS);
		lianLianBankLimitMap.put(BANK_QDYH.getBankCode() + "-" + BANK_QDYH.getPayType(), BANK_QDYH);
		lianLianBankLimitMap.put(BANK_SHNSYH.getBankCode() + "-" + BANK_SHNSYH.getPayType(), BANK_SHNSYH);
		
		lianLianBankLimitMap.put(BANK_SMXYH.getBankCode() + "-" + BANK_SMXYH.getPayType(), BANK_SMXYH);
		lianLianBankLimitMap.put(BANK_SRYH.getBankCode() + "-" + BANK_SRYH.getPayType(), BANK_SRYH);
		lianLianBankLimitMap.put(BANK_SZFTYZCZYH.getBankCode() + "-" + BANK_SZFTYZCZYH.getPayType(), BANK_SZFTYZCZYH);
		lianLianBankLimitMap.put(BANK_SQYH.getBankCode() + "-" + BANK_SQYH.getPayType(), BANK_SQYH);
		lianLianBankLimitMap.put(BANK_SZNSYH.getBankCode() + "-" + BANK_SZNSYH.getPayType(), BANK_SZNSYH);
		
		lianLianBankLimitMap.put(BANK_SZYH.getBankCode() + "-" + BANK_SZYH.getPayType(), BANK_SZYH);
		lianLianBankLimitMap.put(BANK_SNSSYYH.getBankCode() + "-" + BANK_SNSSYYH.getPayType(), BANK_SNSSYYH);
		lianLianBankLimitMap.put(BANK_SDNSYH.getBankCode() + "-" + BANK_SDNSYH.getPayType(), BANK_SDNSYH);
		lianLianBankLimitMap.put(BANK_SCSNCXYS.getBankCode() + "-" + BANK_SCSNCXYS.getPayType(), BANK_SCSNCXYS);
		lianLianBankLimitMap.put(BANK_SDSNCXYS.getBankCode() + "-" + BANK_SDSNCXYS.getPayType(), BANK_SDSNCXYS);
		
		lianLianBankLimitMap.put(BANK_SXSNCXYS.getBankCode() + "-" + BANK_SXSNCXYS.getPayType(), BANK_SXSNCXYS);
		lianLianBankLimitMap.put(BANK_TASSYYH.getBankCode() + "-" + BANK_TASSYYH.getPayType(), BANK_TASSYYH);
		lianLianBankLimitMap.put(BANK_TCNCSYYH.getBankCode() + "-" + BANK_TCNCSYYH.getPayType(), BANK_TCNCSYYH);
		lianLianBankLimitMap.put(BANK_TSSSYYH.getBankCode() + "-" + BANK_TSSSYYH.getPayType(), BANK_TSSSYYH);
		lianLianBankLimitMap.put(BANK_TJBHNCSYYH.getBankCode() + "-" + BANK_TJBHNCSYYH.getPayType(), BANK_TJBHNCSYYH);
		
		lianLianBankLimitMap.put(BANK_TZYH.getBankCode() + "-" + BANK_TZYH.getPayType(), BANK_TZYH);
		lianLianBankLimitMap.put(BANK_WFYH.getBankCode() + "-" + BANK_WFYH.getPayType(), BANK_WFYH);
		lianLianBankLimitMap.put(BANK_WHSSYYH.getBankCode() + "-" + BANK_WHSSYYH.getPayType(), BANK_WHSSYYH);
		lianLianBankLimitMap.put(BANK_WUHAIYH.getBankCode() + "-" + BANK_WUHAIYH.getPayType(), BANK_WUHAIYH);
		lianLianBankLimitMap.put(BANK_WJNSYH.getBankCode() + "-" + BANK_WJNSYH.getPayType(), BANK_WJNSYH);
		
		lianLianBankLimitMap.put(BANK_YCSSYYH.getBankCode() + "-" + BANK_YCSSYYH.getPayType(), BANK_YCSSYYH);
		lianLianBankLimitMap.put(BANK_WLMQYH.getBankCode() + "-" + BANK_WLMQYH.getPayType(), BANK_WLMQYH);
		lianLianBankLimitMap.put(BANK_XMYH.getBankCode() + "-" + BANK_XMYH.getPayType(), BANK_XMYH);
		lianLianBankLimitMap.put(BANK_XCYH.getBankCode() + "-" + BANK_XCYH.getPayType(), BANK_XCYH);
		lianLianBankLimitMap.put(BANK_YTYH.getBankCode() + "-" + BANK_YTYH.getPayType(), BANK_YTYH);
		
		lianLianBankLimitMap.put(BANK_YBSSYYH.getBankCode() + "-" + BANK_YBSSYYH.getPayType(), BANK_YBSSYYH);
		lianLianBankLimitMap.put(BANK_YASSYYH.getBankCode() + "-" + BANK_YASSYYH.getPayType(), BANK_YASSYYH);
		lianLianBankLimitMap.put(BANK_YQSSYYH.getBankCode() + "-" + BANK_YQSSYYH.getPayType(), BANK_YQSSYYH);
		lianLianBankLimitMap.put(BANK_YXSSYYH.getBankCode() + "-" + BANK_YXSSYYH.getPayType(), BANK_YXSSYYH);
		lianLianBankLimitMap.put(BANK_YNSNCXYS.getBankCode() + "-" + BANK_YNSNCXYS.getPayType(), BANK_YNSNCXYS);
		
		lianLianBankLimitMap.put(BANK_YKYH.getBankCode() + "-" + BANK_YKYH.getPayType(), BANK_YKYH);
		lianLianBankLimitMap.put(BANK_ZJCZSYYH.getBankCode() + "-" + BANK_ZJCZSYYH.getPayType(), BANK_ZJCZSYYH);
		lianLianBankLimitMap.put(BANK_ZJMTSYYH.getBankCode() + "-" + BANK_ZJMTSYYH.getPayType(), BANK_ZJMTSYYH);
		lianLianBankLimitMap.put(BANK_CYYH.getBankCode() + "-" + BANK_CYYH.getPayType(), BANK_CYYH);
		lianLianBankLimitMap.put(BANK_ZJGNCSYYH.getBankCode() + "-" + BANK_ZJGNCSYYH.getPayType(), BANK_ZJGNCSYYH);
		
		lianLianBankLimitMap.put(BANK_ZJKSSYYH.getBankCode() + "-" + BANK_ZJKSSYYH.getPayType(), BANK_ZJKSSYYH);
		lianLianBankLimitMap.put(BANK_ZGSSYYH.getBankCode() + "-" + BANK_ZGSSYYH.getPayType(), BANK_ZGSSYYH);
		lianLianBankLimitMap.put(BANK_ZKYH.getBankCode() + "-" + BANK_ZKYH.getPayType(), BANK_ZKYH);
		lianLianBankLimitMap.put(BANK_ZYSSYYH.getBankCode() + "-" + BANK_ZYSSYYH.getPayType(), BANK_ZYSSYYH);
		lianLianBankLimitMap.put(BANK_ZDYH.getBankCode() + "-" + BANK_ZDYH.getPayType(), BANK_ZDYH);
		
		lianLianBankLimitMap.put(BANK_CQYH.getBankCode() + "-" + BANK_CQYH.getPayType(), BANK_CQYH);
		
		//信用卡类
		lianLianBankLimitMap.put(BANK_GSYH_C.getBankCode() + "-" + BANK_GSYH_C.getPayType(), BANK_GSYH_C);
		lianLianBankLimitMap.put(BANK_PFYH_C.getBankCode() + "-" + BANK_PFYH_C.getPayType(), BANK_PFYH_C);
		lianLianBankLimitMap.put(BANK_HXYH_C.getBankCode() + "-" + BANK_HXYH_C.getPayType(), BANK_HXYH_C);
		lianLianBankLimitMap.put(BANK_GDYH_C.getBankCode() + "-" + BANK_GDYH_C.getPayType(), BANK_GDYH_C);
		lianLianBankLimitMap.put(BANK_MSYH_C.getBankCode() + "-" + BANK_MSYH_C.getPayType(), BANK_MSYH_C);
		
		lianLianBankLimitMap.put(BANK_HZYH_C.getBankCode() + "-" + BANK_HZYH_C.getPayType(), BANK_HZYH_C);
		lianLianBankLimitMap.put(BANK_JSYH_C.getBankCode() + "-" + BANK_JSYH_C.getPayType(), BANK_JSYH_C);
		lianLianBankLimitMap.put(BANK_GDFZYH_C.getBankCode() + "-" + BANK_GDFZYH_C.getPayType(), BANK_GDFZYH_C);
		lianLianBankLimitMap.put(BANK_ZXYH_C.getBankCode() + "-" + BANK_ZXYH_C.getPayType(), BANK_ZXYH_C);
		lianLianBankLimitMap.put(BANK_NYYH_C.getBankCode() + "-" + BANK_NYYH_C.getPayType(), BANK_NYYH_C);
		
		lianLianBankLimitMap.put(BANK_ZGYH_C.getBankCode() + "-" + BANK_ZGYH_C.getPayType(), BANK_ZGYH_C);
		lianLianBankLimitMap.put(BANK_XYYH_C.getBankCode() + "-" + BANK_XYYH_C.getPayType(), BANK_XYYH_C);
		lianLianBankLimitMap.put(BANK_PAYH_C.getBankCode() + "-" + BANK_PAYH_C.getPayType(), BANK_PAYH_C);
		lianLianBankLimitMap.put(BANK_YZYH_C.getBankCode() + "-" + BANK_YZYH_C.getPayType(), BANK_YZYH_C);
		lianLianBankLimitMap.put(BANK_BJYH_C.getBankCode() + "-" + BANK_BJYH_C.getPayType(), BANK_BJYH_C);
		
		lianLianBankLimitMap.put(BANK_ZSYH_C.getBankCode() + "-" + BANK_ZSYH_C.getPayType(), BANK_ZSYH_C);
		lianLianBankLimitMap.put(BANK_JTYH_C.getBankCode() + "-" + BANK_JTYH_C.getPayType(), BANK_JTYH_C);
		lianLianBankLimitMap.put(BANK_SHYH_C.getBankCode() + "-" + BANK_SHYH_C.getPayType(), BANK_SHYH_C);
		lianLianBankLimitMap.put(BANK_WSYH_C.getBankCode() + "-" + BANK_WSYH_C.getPayType(), BANK_WSYH_C);
		lianLianBankLimitMap.put(BANK_JSUYH_C.getBankCode() + "-" + BANK_JSUYH_C.getPayType(), BANK_JSUYH_C);
		
		lianLianBankLimitMap.put(BANK_JHYH_C.getBankCode() + "-" + BANK_JHYH_C.getPayType(), BANK_JHYH_C);
		lianLianBankLimitMap.put(BANK_NJYH_C.getBankCode() + "-" + BANK_NJYH_C.getPayType(), BANK_NJYH_C);
		lianLianBankLimitMap.put(BANK_WZYH_C.getBankCode() + "-" + BANK_WZYH_C.getPayType(), BANK_WZYH_C);
		lianLianBankLimitMap.put(BANK_HBYH_C.getBankCode() + "-" + BANK_HBYH_C.getPayType(), BANK_HBYH_C);
		lianLianBankLimitMap.put(BANK_BJNSYH_C.getBankCode() + "-" + BANK_BJNSYH_C.getPayType(), BANK_BJNSYH_C);
		
		lianLianBankLimitMap.put(BANK_BSYH_C.getBankCode() + "-" + BANK_BSYH_C.getPayType(), BANK_BSYH_C);
		lianLianBankLimitMap.put(BANK_CQNCSYYH_C.getBankCode() + "-" + BANK_CQNCSYYH_C.getPayType(), BANK_CQNCSYYH_C);
		lianLianBankLimitMap.put(BANK_CDNCSYYH_C.getBankCode() + "-" + BANK_CDNCSYYH_C.getPayType(), BANK_CDNCSYYH_C);
		lianLianBankLimitMap.put(BANK_CSNCSYYH_C.getBankCode() + "-" + BANK_CSNCSYYH_C.getPayType(), BANK_CSNCSYYH_C);
		lianLianBankLimitMap.put(BANK_CSYH_C.getBankCode() + "-" + BANK_CSYH_C.getPayType(), BANK_CSYH_C);
		
		lianLianBankLimitMap.put(BANK_CDYH_C.getBankCode() + "-" + BANK_CDYH_C.getPayType(), BANK_CDYH_C);
		lianLianBankLimitMap.put(BANK_CQYH_C.getBankCode() + "-" + BANK_CQYH_C.getPayType(), BANK_CQYH_C);
		lianLianBankLimitMap.put(BANK_DLYH_C.getBankCode() + "-" + BANK_DLYH_C.getPayType(), BANK_DLYH_C);
		lianLianBankLimitMap.put(BANK_DONGYYH_C.getBankCode() + "-" + BANK_DONGYYH_C.getPayType(), BANK_DONGYYH_C);
		lianLianBankLimitMap.put(BANK_EEDSYH_C.getBankCode() + "-" + BANK_EEDSYH_C.getPayType(), BANK_EEDSYH_C);
		
		lianLianBankLimitMap.put(BANK_FJNCXYS_C.getBankCode() + "-" + BANK_FJNCXYS_C.getPayType(), BANK_FJNCXYS_C);
		lianLianBankLimitMap.put(BANK_FUDIANYH_C.getBankCode() + "-" + BANK_FUDIANYH_C.getPayType(), BANK_FUDIANYH_C);
		lianLianBankLimitMap.put(BANK_GZYH_C.getBankCode() + "-" + BANK_GZYH_C.getPayType(), BANK_GZYH_C);
		lianLianBankLimitMap.put(BANK_GZNCSYYH_C.getBankCode() + "-" + BANK_GZNCSYYH_C.getPayType(), BANK_GZNCSYYH_C);
		lianLianBankLimitMap.put(BANK_GANZHOUYH_C.getBankCode() + "-" + BANK_GANZHOUYH_C.getPayType(), BANK_GANZHOUYH_C);
		
		lianLianBankLimitMap.put(BANK_GYYH_C.getBankCode() + "-" + BANK_GYYH_C.getPayType(), BANK_GYYH_C);
		lianLianBankLimitMap.put(BANK_HEBYH_C.getBankCode() + "-" + BANK_HEBYH_C.getPayType(), BANK_HEBYH_C);
		lianLianBankLimitMap.put(BANK_HNNCXYS_C.getBankCode() + "-" + BANK_HNNCXYS_C.getPayType(), BANK_HNNCXYS_C);
		lianLianBankLimitMap.put(BANK_JSNCXYS_C.getBankCode() + "-" + BANK_JSNCXYS_C.getPayType(), BANK_JSNCXYS_C);
		lianLianBankLimitMap.put(BANK_JNNCSYYH_C.getBankCode() + "-" + BANK_JNNCSYYH_C.getPayType(), BANK_JNNCSYYH_C);
		
		lianLianBankLimitMap.put(BANK_JZYH_C.getBankCode() + "-" + BANK_JZYH_C.getPayType(), BANK_JZYH_C);
		lianLianBankLimitMap.put(BANK_JJYH_C.getBankCode() + "-" + BANK_JJYH_C.getPayType(), BANK_JJYH_C);
		lianLianBankLimitMap.put(BANK_LJYH_C.getBankCode() + "-" + BANK_LJYH_C.getPayType(), BANK_LJYH_C);
		lianLianBankLimitMap.put(BANK_LZYH_C.getBankCode() + "-" + BANK_LZYH_C.getPayType(), BANK_LZYH_C);
		lianLianBankLimitMap.put(BANK_NBYH_C.getBankCode() + "-" + BANK_NBYH_C.getPayType(), BANK_NBYH_C);
		
		lianLianBankLimitMap.put(BANK_NCYH_C.getBankCode() + "-" + BANK_NCYH_C.getPayType(), BANK_NCYH_C);
		lianLianBankLimitMap.put(BANK_QHYH_C.getBankCode() + "-" + BANK_QHYH_C.getPayType(), BANK_QHYH_C);
		lianLianBankLimitMap.put(BANK_JLYH_C.getBankCode() + "-" + BANK_JLYH_C.getPayType(), BANK_JLYH_C);
		lianLianBankLimitMap.put(BANK_QDYH_C.getBankCode() + "-" + BANK_QDYH_C.getPayType(), BANK_QDYH_C);
		lianLianBankLimitMap.put(BANK_SHNSYH_C.getBankCode() + "-" + BANK_SHNSYH_C.getPayType(), BANK_SHNSYH_C);
		
		lianLianBankLimitMap.put(BANK_SRYH_C.getBankCode() + "-" + BANK_SRYH_C.getPayType(), BANK_SRYH_C);
		lianLianBankLimitMap.put(BANK_SDNSYH_C.getBankCode() + "-" + BANK_SDNSYH_C.getPayType(), BANK_SDNSYH_C);
		lianLianBankLimitMap.put(BANK_SXRDNCSYYH_C.getBankCode() + "-" + BANK_SXRDNCSYYH_C.getPayType(), BANK_SXRDNCSYYH_C);
		lianLianBankLimitMap.put(BANK_TZYH_C.getBankCode() + "-" + BANK_TZYH_C.getPayType(), BANK_TZYH_C);
		lianLianBankLimitMap.put(BANK_WFYH_C.getBankCode() + "-" + BANK_WFYH_C.getPayType(), BANK_WFYH_C);
		
		lianLianBankLimitMap.put(BANK_WHSSYYH_C.getBankCode() + "-" + BANK_WHSSYYH_C.getPayType(), BANK_WHSSYYH_C);
		lianLianBankLimitMap.put(BANK_WJNSYH_C.getBankCode() + "-" + BANK_WJNSYH_C.getPayType(), BANK_WJNSYH_C);
		lianLianBankLimitMap.put(BANK_WXNCYH_C.getBankCode() + "-" + BANK_WXNCYH_C.getPayType(), BANK_WXNCYH_C);
		lianLianBankLimitMap.put(BANK_WLMQYH_C.getBankCode() + "-" + BANK_WLMQYH_C.getPayType(), BANK_WLMQYH_C);
		lianLianBankLimitMap.put(BANK_YCSSYYH_C.getBankCode() + "-" + BANK_YCSSYYH_C.getPayType(), BANK_YCSSYYH_C);
		
		lianLianBankLimitMap.put(BANK_YINCSSYYH_C.getBankCode() + "-" + BANK_YINCSSYYH_C.getPayType(), BANK_YINCSSYYH_C);
		lianLianBankLimitMap.put(BANK_JZYHYH_C.getBankCode() + "-" + BANK_JZYHYH_C.getPayType(), BANK_JZYHYH_C);
		lianLianBankLimitMap.put(BANK_ZJCZSYYH_C.getBankCode() + "-" + BANK_ZJCZSYYH_C.getPayType(), BANK_ZJCZSYYH_C);
		lianLianBankLimitMap.put(BANK_ZJMTSYYH_C.getBankCode() + "-" + BANK_ZJMTSYYH_C.getPayType(), BANK_ZJMTSYYH_C);
		lianLianBankLimitMap.put(BANK_ZJTLSYYH_C.getBankCode() + "-" + BANK_ZJTLSYYH_C.getPayType(), BANK_ZJTLSYYH_C);
		
		lianLianBankLimitMap.put(BANK_SZFTYZCZYH_C.getBankCode() + "-" + BANK_SZFTYZCZYH_C.getPayType(), BANK_SZFTYZCZYH_C);
		lianLianBankLimitMap.put(BANK_TJBHNCSYYH_C.getBankCode() + "-" + BANK_TJBHNCSYYH_C.getPayType(), BANK_TJBHNCSYYH_C);
		lianLianBankLimitMap.put(BANK_YASSYYH_C.getBankCode() + "-" + BANK_YASSYYH_C.getPayType(), BANK_YASSYYH_C);
		lianLianBankLimitMap.put(BANK_ZKYH_C.getBankCode() + "-" + BANK_ZKYH_C.getPayType(), BANK_ZKYH_C);
		
		
	}
	
	public static BankLimitType getBankLimitType(String bankCode,String payType) {
		return lianLianBankLimitMap.get(bankCode + "-" + payType);
	}
	
}
