package com.aicai.jcob.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BankType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -733442715161486369L;

	public static Map<Integer, BankType> bankMap = new HashMap<Integer, BankType>();

	/**
	 * 序号
	 */
	protected Integer index;
	/**
	 * 内部银行代码
	 */
	protected String bankCode;

	/**
	 * 银行名称
	 */
	protected String bankName;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

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

	public BankType(Integer index, String bankCode, String bankName) {
		this.index = index;
		this.bankCode = bankCode;
		this.bankName = bankName;
		bankMap.put(index, this);
	}

	public static BankType findBankTypeByIndex(Integer index) {
		return bankMap.get(index);
	}

	public static BankType findBankTypeByBankCode(String bankCode) {
		for (Entry<Integer, BankType> entry : bankMap.entrySet()) {
			if (entry.getValue().getBankCode().equals(bankCode)) {
				return bankMap.get(entry.getKey());
			}
		}
		return null;
	}

	public static List<BankType> getBankTypeList() {
		List<BankType> list = new ArrayList<BankType>();
		for (Entry<Integer, BankType> entry : bankMap.entrySet()) {
			list.add(bankMap.get(entry.getKey()));
		}
		return list;
	}

	public static BankType BANK_OTHER = new BankType(1, "OTHER", "其它银行");
	public static BankType BANK_GSYH = new BankType(2, "GSYH", "中国工商银行");
	public static BankType BANK_ZSYH = new BankType(3, "ZSYH", "招商银行");
	public static BankType BANK_JSYH = new BankType(4, "JSYH", "中国建设银行");
	public static BankType BANK_JTYH = new BankType(5, "JTYH", "交通银行");
	public static BankType BANK_NYYH = new BankType(6, "NYYH", "中国农业银行");
	public static BankType BANK_ZGYH = new BankType(7, "ZGYH", "中国银行");
	public static BankType BANK_YZYH = new BankType(8, "YZYH", "中国邮政");
	public static BankType BANK_GDYH = new BankType(9, "GDYH", "中国光大银行");
	public static BankType BANK_MSYH = new BankType(10, "MSYH", "中国民生银行");
	public static BankType BANK_PFYH = new BankType(11, "PFYH", "上海浦东发展银行");
	public static BankType BANK_SZFZYH = new BankType(12, "SZFZYH", "深圳发展银行");
	public static BankType BANK_XYYH = new BankType(13, "XYYH", "兴业银行");
	public static BankType BANK_ZXYH = new BankType(14, "BANK_ZXYH", "中信银行");
	public static BankType BANK_BJYH = new BankType(15, "BJYH", "北京银行");
	public static BankType BANK_HXYH = new BankType(16, "HXYH", "华夏银行");
	public static BankType BANK_GDFZYH = new BankType(17, "GDFZYH", "广东发展银行");
	public static BankType BANK_GZSNCXYS = new BankType(18, "GZSNCXYS","广州市农村信用社");
	public static BankType BANK_GZSSYYH = new BankType(19, "GZSSYYH", "广州市商业银行");
	public static BankType BANK_SHNCSYYH = new BankType(20, "SHNCSYYH","上海农村商业银行");
	public static BankType BANK_PAYH = new BankType(21, "PAYH", "平安银行");
	public static BankType BANK_SZNCSYYH = new BankType(22, "SZNCSYYH","深圳农村商业银行");
	public static BankType BANK_SDXYS = new BankType(23, "SDXYS", "顺德信用社");
	public static BankType BANK_WJNCSYYH = new BankType(24, "WJNCSYYH","吴江农村商业银行");
	public static BankType BANK_SHYH = new BankType(25, "SHYH", "上海银行");
	public static BankType BANK_DYYH = new BankType(26, "DYYH", "东亚银行");
	public static BankType BANK_NBYH = new BankType(27, "NBYH", "宁波银行");
	public static BankType BANK_BSYH = new BankType(28, "BSYH", "包商银行");
	public static BankType BANK_CSYH = new BankType(29, "CSYH", "长沙银行");
	public static BankType BANK_CDYH = new BankType(30, "CDYH", "承德银行");
	public static BankType BANK_CDNSYH = new BankType(31, "CDNSYH", "成都农商银行");
	public static BankType BANK_CQNCSYYH = new BankType(32, "CQNCSYYH","重庆农村商业银行");
	public static BankType BANK_CQYH = new BankType(33, "CQYH", "重庆银行");
	public static BankType BANK_DLYH = new BankType(34, "DLYH", "大连银行");
	public static BankType BANK_EEDSYH = new BankType(35, "EEDSYH", "鄂尔多斯银行");
	public static BankType BANK_FJHXYH = new BankType(36, "FJHXYH", "福建海峡银行");
	public static BankType BANK_FJNCXYS = new BankType(37, "FJNCXYS", "福建农村信用社");
	public static BankType BANK_GYYH = new BankType(38, "GYYH", "贵阳银行");
	public static BankType BANK_GZYH = new BankType(39, "GZYH", "广州银行");
	public static BankType BANK_GZNCSYYH = new BankType(40, "GZNCSYYH","广州农村商业银行");
	public static BankType BANK_HEBYH = new BankType(41, "HEBYH", "哈尔滨银行");
	public static BankType BANK_HNNCXYS = new BankType(42, "HNNCXYS","湖南省农村信用社");
	public static BankType BANK_WSYH = new BankType(43, "WSYH", "徽商银行");
	public static BankType BANK_HBYH = new BankType(44, "HBYH", "河北银行");
	public static BankType BANK_HZYH = new BankType(45, "HZYH", "杭州银行");
	public static BankType BANK_JZYH = new BankType(46, "JZYH", "锦州银行");
	public static BankType BANK_JSCSNCSYYH = new BankType(47, "JSCSNCSYYH","江苏常熟农村商业银行");
	public static BankType BANK_JSUYH = new BankType(48, "JSUYH", "江苏银行");
	public static BankType BANK_JYNCSYYH = new BankType(49, "JYNCSYYH","江阴农村商业银行");
	public static BankType BANK_JJYH = new BankType(50, "JJYH", "九江银行");
	public static BankType BANK_LZYH = new BankType(51, "LZYH", "兰州银行");
	public static BankType BANK_LJYH = new BankType(52, "LJYH", "龙江银行");
	public static BankType BANK_QHYH = new BankType(53, "QHYH", "青海银行");
	public static BankType BANK_SHNSYH = new BankType(54, "SHNSYH", "上海农商银行");
	public static BankType BANK_SRYH = new BankType(55, "SRYH", "上饶银行");
	public static BankType BANK_SDNSYH = new BankType(56, "SDNSYH", "顺德农村商业银行");
	public static BankType BANK_TZYH = new BankType(57, "TZYH", "台州银行");
	public static BankType BANK_WHSSYYH = new BankType(58, "WHSSYYH", "威海市商业银行");
	public static BankType BANK_WFYH = new BankType(59, "WFYH", "潍坊银行");
	public static BankType BANK_WZYH = new BankType(60, "WZYH", "温州银行");
	public static BankType BANK_WLMQYH = new BankType(61, "WLMQYH", "乌鲁木齐商业银行");
	public static BankType BANK_WXNCYH = new BankType(62, "WXNCYH", "无锡农村商业银行");
	public static BankType BANK_YCSSYYH = new BankType(63, "YCSSYYH", "宜昌市商业银行");
	public static BankType BANK_JZYHYH = new BankType(64, "JZYHYH", "鄞州银行");
	public static BankType BANK_ZJCZSYYH = new BankType(65, "ZJCZSYYH","浙江稠州商业银行");
	public static BankType BANK_ZJTLSYYH = new BankType(66, "ZJTLSYYH","浙江泰隆商业银行");
	public static BankType BANK_ZJMTSYYH = new BankType(67, "ZJMTSYYH","浙江民泰商业银行");
	public static BankType BANK_NJYH = new BankType(68, "NJYH", "南京银行");
	public static BankType BANK_NCYH = new BankType(69, "NCYH", "南昌银行");
	public static BankType BANK_JLYH = new BankType(70, "JLYH", "齐鲁银行");
	public static BankType BANK_YDYH = new BankType(71, "YDYH", "尧都农村商业银行");
	public static BankType BANK_BHYH = new BankType(72, "BHYH", "渤海银行");
	public static BankType BANK_JHYH = new BankType(73, "JHYH", "金华银行");
	public static BankType BANK_BJNSYH = new BankType(74, "BJNSYH", "北京农商银行");
	public static BankType BANK_CDUYH = new BankType(75, "CDUYH", "成都银行");
	public static BankType BANK_ZZHOUYH = new BankType(76, "ZZHOUYH", "郑州银行");
	public static BankType BANK_ASSYYH = new BankType(77, "ASSYYH", "安顺市商业银行");
	public static BankType BANK_AHSNCXYS = new BankType(78, "AHSNCXYS","安徽省农村信用社");
	public static BankType BANK_AYYH = new BankType(79, "AYYH", "安阳银行");
	public static BankType BANK_BDYH = new BankType(80, "BDYH", "保定银行");
	public static BankType BANK_CDNCSYYH = new BankType(81, "CDNCSYYH","成都农村商业银行");
	public static BankType BANK_CSNCSYYH = new BankType(82, "CSNCSYYH","常熟农村商业银行");
	public static BankType BANK_CZYH = new BankType(83, "CZYH", "沧州银行");
	public static BankType BANK_CZSSYYH = new BankType(84, "CZSSYYH", "长治市商业银行");
	public static BankType BANK_DEYANGYH = new BankType(85, "DEYANGYH", "德阳银行");
	public static BankType BANK_DGNCSYYH = new BankType(86, "DGNCSYYH","东莞农村商业银行");
	public static BankType BANK_DONGYYH = new BankType(87, "DONGYYH", "东营银行");
	public static BankType BANK_FUSHUNYH = new BankType(88, "FUSHUNYH", "抚顺银行");
	public static BankType BANK_FUDIANYH = new BankType(89, "FUDIANYH", "富滇银行");
	public static BankType BANK_GXBBWYH = new BankType(90, "GXBBWYH", "广西北部湾银行");
	public static BankType BANK_GDNCXYS = new BankType(91, "GDNCXYS", "广东农村信用社");
	public static BankType BANK_GANZHOUYH = new BankType(92, "GANZHOUYH","赣州银行");
	public static BankType BANK_GUILINYH = new BankType(93, "GUILINYH", "桂林银行");
	public static BankType BANK_GSSNCXYS = new BankType(94, "GSSNCXYS","甘肃省农村信用社联合社");
	public static BankType BANK_HBSNCXYS = new BankType(95, "HBSNCXYS","河北省农村信用社");
	public static BankType BANK_HEBIYH = new BankType(96, "HEBIYH", "鹤壁银行");
	public static BankType BANK_HENGSSSYYH = new BankType(97, "HENGSSSYYH","衡水市商业银行");
	public static BankType BANK_HAINNCXYS = new BankType(98, "HAINNCXYS","海南省农村信用社联合社");
	public static BankType BANK_HBNCXYS = new BankType(99, "HBNCXYS","湖北省农村信用社联合社");
	public static BankType BANK_HUBEIYH = new BankType(100, "HUBEIYH", "湖北银行");
	public static BankType BANK_HULUDAOYH = new BankType(101, "HULUDAOYH","葫芦岛银行");
	public static BankType BANK_HANDANYH = new BankType(102, "HANDANYH", "邯郸银行");
	public static BankType BANK_JICHENGYH = new BankType(103, "JICHENGYH ","晋城银行");
	public static BankType BANK_JXNCXYS = new BankType(104, "JXNCXYS","江西农村信用联合社");
	public static BankType BANK_JSNCXYS = new BankType(105, "JSNCXYS","江苏省农村信用社联合社");
	public static BankType BANK_JNNCSYYH = new BankType(106, "JNNCSYYH","江南农村商业银行");
	public static BankType BANK_JLNCXYS = new BankType(107, "JLNCXYS","吉林省农村信用社");
	public static BankType BANK_JILINYH = new BankType(108, "JILINYH", "吉林银行");
	public static BankType BANK_JZSSYYH = new BankType(109, "JZSSYYH","晋中市商业银行");
	public static BankType BANK_KSNCSYYH = new BankType(110, "KSNCSYYH","昆山农村商业银行");
	public static BankType BANK_KELSSYYH = new BankType(111, "KELSSYYH","库尔勒市商业银行");
	public static BankType BANK_KLYH = new BankType(112, "KLYH", "昆仑银行");
	public static BankType BANK_LSZSYYH = new BankType(113, "LSZSYYH","凉山州商业银行");
	public static BankType BANK_LSSSYYH = new BankType(114, "LSSSYYH","乐山市商业银行");
	public static BankType BANK_LINSYH = new BankType(115, "LINSYH", "临商银行");
	public static BankType BANK_LIUZHOUYH = new BankType(116, "LIUZHOUYH","柳州银行");
	public static BankType BANK_LUOHEYH = new BankType(117, "LUOHEYH", "漯河银行");
	public static BankType BANK_NMGYH = new BankType(118, "NMGYH", "内蒙古银行");
	public static BankType BANK_NMGNCXYS = new BankType(119, "NMGNCXYS","内蒙古农村信用社");
	public static BankType BANK_NBDHYH = new BankType(120, "NBDHYH", "宁波东海银行");
	public static BankType BANK_NXHHNSYH = new BankType(121, "NXHHNSYH","宁夏黄河农商银行");
	public static BankType BANK_NCSSYYH = new BankType(122, "NCSSYYH","南充市商业银行");
	public static BankType BANK_PDSYH = new BankType(123, "PDSYH", "平顶山银行");
	public static BankType BANK_PZHSSYYH = new BankType(124, "PZHSSYYH","攀枝花市商业银行");
	public static BankType BANK_QHDSSYYH = new BankType(125, "QHDSSYYH","秦皇岛市商业银行");
	public static BankType BANK_QZYH = new BankType(126, "QZYH", "泉州银行");
	public static BankType BANK_QJSSYYH = new BankType(127, "QJSSYYH","曲靖市商业银行");
	public static BankType BANK_QHSNCXYS = new BankType(128, "QHSNCXYS","青海省农村信用社");
	public static BankType BANK_QDYH = new BankType(129, "QDYH", "青岛银行");
	public static BankType BANK_SMXYH = new BankType(130, "SMXYH", "三门峡银行");
	public static BankType BANK_SZFTYZCZYH = new BankType(131, "SZFTYZCZYH","深圳福田银座村镇银行");
	public static BankType BANK_SQYH = new BankType(132, "SQYH", "商丘银行");
	public static BankType BANK_SZNSYH = new BankType(133, "SZNSYH", "深圳农商银行");
	public static BankType BANK_SZYH = new BankType(134, "SZYH", "苏州银行");
	public static BankType BANK_SNSSYYH = new BankType(135, "SNSSYYH","遂宁市商业银行");
	public static BankType BANK_SCSNCXYS = new BankType(136, "SCSNCXYS","四川省农村信用合作社");
	public static BankType BANK_SDSNCXYS = new BankType(137, "SDSNCXYS","山东省农村信用社");
	public static BankType BANK_SXRDNCSYYH = new BankType(138, "SXRDNCSYYH","山西尧都农村商业银行");
	public static BankType BANK_SXSNCXYS = new BankType(139, "SXSNCXYS","山西省农村信用社联合社");
	public static BankType BANK_TASSYYH = new BankType(140, "TASSYYH","泰安市商业银行");
	public static BankType BANK_TCNCSYYH = new BankType(141, "TCNCSYYH","太仓农村商业银行");
	public static BankType BANK_TSSSYYH = new BankType(142, "TSSSYYH","唐山市商业银行");
	public static BankType BANK_TJBHNCSYYH = new BankType(143, "TJBHNCSYYH","天津滨海农村商业银行");
	public static BankType BANK_WUHAIYH = new BankType(144, "WUHAIYH", "乌海银行");
	public static BankType BANK_WJNSYH = new BankType(145, "WJNSYH", "吴江农商银行");
	public static BankType BANK_XMYH = new BankType(146, "XMYH", "厦门银行");
	public static BankType BANK_XCYH = new BankType(147, "XCYH", "许昌银行");
	public static BankType BANK_YTYH = new BankType(148, "YTYH", "邢台银行");
	public static BankType BANK_YBSSYYH = new BankType(149, "YBSSYYH","宜宾市商业银行");
	public static BankType BANK_YASSYYH = new BankType(150, "YASSYYH","雅安市商业银行");
	public static BankType BANK_YQSSYYH = new BankType(151, "YQSSYYH","阳泉市商业银行");
	public static BankType BANK_YXSSYYH = new BankType(152, "YXSSYYH","玉溪市商业银行");
	public static BankType BANK_YINCSSYYH = new BankType(153, "YINCSSYYH","银川市商业银行");
	public static BankType BANK_YKYH = new BankType(154, "YKYH", "营口银行");
	public static BankType BANK_CYYH = new BankType(155, "CYYH", "朝阳银行");
	public static BankType BANK_ZJGNCSYYH = new BankType(156, "ZJGNCSYYH","张家港农村商业银行");
	public static BankType BANK_ZJKSSYYH = new BankType(157, "ZJKSSYYH","张家口市商业银行");
	public static BankType BANK_ZGSSYYH = new BankType(158, "ZGSSYYH","自贡市商业银行");
	public static BankType BANK_ZKYH = new BankType(159, "ZKYH", "周口银行");
	public static BankType BANK_ZYSSYYH = new BankType(160, "ZYSSYYH","遵义市商业银行");
	public static BankType BANK_HGZXQYYH = new BankType(161, "HGZXQYYH","韩国中小企业银行");
	public static BankType BANK_HQYH = new BankType(162, "HQYH", "花旗银行");
	public static BankType BANK_ZDYH = new BankType(163, "ZDYH", "渣打银行");
	public static BankType BANK_FXYH = new BankType(164, "FXYH", "法兴银行");
	public static BankType BANK_DHYH = new BankType(165, "DHYH", "大华银行");
	public static BankType BANK_XHYH = new BankType(166, "XHYH", "新韩银行");
	public static BankType BANK_JIAXINGYH = new BankType(167, "JIAXINGYH","嘉兴银行");
	public static BankType BANK_YNSNCXYS = new BankType(168, "YNSNCXYS","云南省农村信用社");
	public static BankType BANK_GXNCXYS = new BankType(169, "GXNCXYS","广西农村信用社");
	
	/**
	 * 通过连连code获取内部code
	 * @param lianlianCode
	 * @return
	 */
	public static String getInnerCodeAndBankNameByLianLianCode(String lianlianCode){
	   Map<String, String> bankExchangeMap = getLianLianBankExchangeMap();
		for (Entry<String, String> codeSet : bankExchangeMap.entrySet()) {
			if (codeSet.getValue().equals(lianlianCode)) {
				return codeSet.getKey();
			}
		}
	   return "";
	}
	
	/**
	 * 连连银行代码
	 * @return
	 */
	public  static Map<String, String> getLianLianBankExchangeMap() {
		return lianLianBankExchangeMap;
	}
	
	private static  Map<String, String> lianLianBankExchangeMap = new HashMap<String, String>();
	
	 static{
//			1	工商银行	全国	借记卡	信用卡	01020000
			lianLianBankExchangeMap.put(BankType.BANK_GSYH.getBankCode() + "-" + BankType.BANK_GSYH.getBankName(), "01020000");
//			2	农业银行	全国	借记卡	信用卡	01030000
			lianLianBankExchangeMap.put(BankType.BANK_NYYH.getBankCode() + "-" + BankType.BANK_NYYH.getBankName(), "01030000");
//			3	中国银行	全国	借记卡	信用卡	01040000
			lianLianBankExchangeMap.put(BankType.BANK_ZGYH.getBankCode() + "-" + BankType.BANK_ZGYH.getBankName(), "01040000");
//			4	建设银行	全国	借记卡	信用卡	01050000
			lianLianBankExchangeMap.put(BankType.BANK_JSYH.getBankCode() + "-" + BankType.BANK_JSYH.getBankName(), "01050000");
//			5	交通银行	全国	借记卡	信用卡	03010000
			lianLianBankExchangeMap.put(BankType.BANK_JTYH.getBankCode() + "-" + BankType.BANK_JTYH.getBankName(), "03010000");
//			6	邮政储蓄银行	全国	借记卡	信用卡	01000000
			lianLianBankExchangeMap.put(BankType.BANK_YZYH.getBankCode() + "-" + BankType.BANK_YZYH.getBankName(), "01000000");
//			7	中信银行	全国	借记卡	信用卡	03020000
			lianLianBankExchangeMap.put(BankType.BANK_ZXYH.getBankCode() + "-" + BankType.BANK_ZXYH.getBankName(), "03020000");
//			8	光大银行	全国	借记卡	信用卡	03030000
			lianLianBankExchangeMap.put(BankType.BANK_GDYH.getBankCode() + "-" + BankType.BANK_GDYH.getBankName(), "03030000");
//			9	华夏银行	全国	借记卡	信用卡	03040000
			lianLianBankExchangeMap.put(BankType.BANK_HXYH.getBankCode() + "-" + BankType.BANK_HXYH.getBankName(), "03040000");
//			10	民生银行	全国	借记卡	信用卡	03050000
			lianLianBankExchangeMap.put(BankType.BANK_MSYH.getBankCode() + "-" + BankType.BANK_MSYH.getBankName(), "03050000");
//			11	广发银行	全国	　	信用卡	03060000
			lianLianBankExchangeMap.put(BankType.BANK_GDFZYH.getBankCode() + "-" + BankType.BANK_GDFZYH.getBankName(), "03060000");
//			12	平安银行	全国	借记卡	信用卡	03070000
			lianLianBankExchangeMap.put(BankType.BANK_PAYH.getBankCode() + "-" + BankType.BANK_PAYH.getBankName(), "03070000");
//			13	招商银行	全国	借记卡	信用卡	03080000
			lianLianBankExchangeMap.put(BankType.BANK_ZSYH.getBankCode() + "-" + BankType.BANK_ZSYH.getBankName(), "03080000");
//			14	兴业银行	全国	借记卡	信用卡	03090000
			lianLianBankExchangeMap.put(BankType.BANK_XYYH.getBankCode() + "-" + BankType.BANK_XYYH.getBankName(), "03090000");
//			15	浦发银行	全国	借记卡	信用卡	03100000
			lianLianBankExchangeMap.put(BankType.BANK_PFYH.getBankCode() + "-" + BankType.BANK_PFYH.getBankName(), "03100000");
//			16	渤海银行	全国	借记卡	　	03170000
			lianLianBankExchangeMap.put(BankType.BANK_BHYH.getBankCode() + "-" + BankType.BANK_BHYH.getBankName(), "03170000");
//			17	北京银行	全国	借记卡	信用卡	04031000
			lianLianBankExchangeMap.put(BankType.BANK_BJYH.getBankCode() + "-" + BankType.BANK_BJYH.getBankName(), "04031000");
//			18	上海银行	全国	借记卡	信用卡	04012900
			lianLianBankExchangeMap.put(BankType.BANK_SHYH.getBankCode() + "-" + BankType.BANK_SHYH.getBankName(), "04012900");
//			19	杭州银行	区域	借记卡	信用卡	04233310
			lianLianBankExchangeMap.put(BankType.BANK_HZYH.getBankCode() + "-" + BankType.BANK_HZYH.getBankName(), "04233310");
//			20	徽商银行	区域	借记卡	信用卡	04403600
			lianLianBankExchangeMap.put(BankType.BANK_WSYH.getBankCode() + "-" + BankType.BANK_WSYH.getBankName(), "04403600");
//			21	江苏银行	区域	借记卡	信用卡	05083000
			lianLianBankExchangeMap.put(BankType.BANK_JSUYH.getBankCode() + "-" + BankType.BANK_JSUYH.getBankName(), "05083000");
//			22	金华银行	区域	借记卡	信用卡	04263380
			lianLianBankExchangeMap.put(BankType.BANK_JHYH.getBankCode() + "-" + BankType.BANK_JHYH.getBankName(), "04263380");
//			23	南京银行	区域	借记卡	信用卡	04243010
			lianLianBankExchangeMap.put(BankType.BANK_NJYH.getBankCode() + "-" + BankType.BANK_NJYH.getBankName(), "04243010");
//			24	温州银行	区域	借记卡	信用卡	04123330
			lianLianBankExchangeMap.put(BankType.BANK_WZYH.getBankCode() + "-" + BankType.BANK_WZYH.getBankName(), "04123330");
//			25	河北银行	区域	借记卡	信用卡	04221210
			lianLianBankExchangeMap.put(BankType.BANK_HBYH.getBankCode() + "-" + BankType.BANK_HBYH.getBankName(), "04221210");
//			26	北京农商银行	区域	　	信用卡	14181000
			lianLianBankExchangeMap.put(BankType.BANK_BJNSYH.getBankCode() + "-" + BankType.BANK_BJNSYH.getBankName(), "14181000");
//			27	包商银行	区域	借记卡	信用卡	04791920
			lianLianBankExchangeMap.put(BankType.BANK_BSYH.getBankCode() + "-" + BankType.BANK_BSYH.getBankName(), "04791920");
//			28	成都银行	区域	借记卡	　	64296510
			lianLianBankExchangeMap.put(BankType.BANK_CDUYH.getBankCode() + "-" + BankType.BANK_CDUYH.getBankName(), "64296510");
//			29	重庆农村商业银行	区域	借记卡	信用卡	14136900
			lianLianBankExchangeMap.put(BankType.BANK_CQNCSYYH.getBankCode() + "-" + BankType.BANK_CQNCSYYH.getBankName(), "14136900");
//			30	郑州银行	区域	借记卡（不支持96828 940056开头的卡）	　	04354910
			lianLianBankExchangeMap.put(BankType.BANK_ZZHOUYH.getBankCode() + "-" + BankType.BANK_ZZHOUYH.getBankName(), "04354910");
//			31	福建海峡银行	区域	借记卡	　	04053910
			lianLianBankExchangeMap.put(BankType.BANK_FJHXYH.getBankCode() + "-" + BankType.BANK_FJHXYH.getBankName(), "04053910");
//			32	安顺市商业银行	区域	借记卡	　	05197110
			lianLianBankExchangeMap.put(BankType.BANK_ASSYYH.getBankCode() + "-" + BankType.BANK_ASSYYH.getBankName(), "05197110");
//			33	安徽省农村信用社	区域	借记卡	　	14473600
			lianLianBankExchangeMap.put(BankType.BANK_AHSNCXYS.getBankCode() + "-" + BankType.BANK_AHSNCXYS.getBankName(), "14473600");
//			34	安阳银行	区域	借记卡	　	17219924
			lianLianBankExchangeMap.put(BankType.BANK_AYYH.getBankCode() + "-" + BankType.BANK_AYYH.getBankName(), "17219924");
//			35	保定银行	区域	借记卡	　	05521340
			lianLianBankExchangeMap.put(BankType.BANK_BDYH.getBankCode() + "-" + BankType.BANK_BDYH.getBankName(), "05521340");
//			36	成都农村商业银行	区域	借记卡	信用卡	65226510
			lianLianBankExchangeMap.put(BankType.BANK_CDNCSYYH.getBankCode() + "-" + BankType.BANK_CDNCSYYH.getBankName(), "65226510");
//			37	常熟农村商业银行	区域	借记卡	信用卡	14033055
			lianLianBankExchangeMap.put(BankType.BANK_CSNCSYYH.getBankCode() + "-" + BankType.BANK_CSNCSYYH.getBankName(), "14033055");
//			38	长沙银行	区域	　	信用卡	04615510
			lianLianBankExchangeMap.put(BankType.BANK_CSYH.getBankCode() + "-" + BankType.BANK_CSYH.getBankName(), "04615510");
//			39	承德银行	区域	借记卡	信用卡	65131410
			lianLianBankExchangeMap.put(BankType.BANK_CDYH.getBankCode() + "-" + BankType.BANK_CDYH.getBankName(), "65131410");
//			40	沧州银行	区域	借记卡	　	04761430
			lianLianBankExchangeMap.put(BankType.BANK_CZYH.getBankCode() + "-" + BankType.BANK_CZYH.getBankName(), "04761430");
//			41	重庆银行	区域	　	信用卡	04416900
			lianLianBankExchangeMap.put(BankType.BANK_CQYH.getBankCode() + "-" + BankType.BANK_CQYH.getBankName(), "04416900");
//			42	长治市商业银行	区域	借记卡	　	05121660
			lianLianBankExchangeMap.put(BankType.BANK_CZSSYYH.getBankCode() + "-" + BankType.BANK_CZSSYYH.getBankName(), "05121660");
//			43	大连银行	区域	　	信用卡	04202220
			lianLianBankExchangeMap.put(BankType.BANK_DLYH.getBankCode() + "-" + BankType.BANK_DLYH.getBankName(), "04202220");
//			44	德阳银行	区域	借记卡	　	04986580
			lianLianBankExchangeMap.put(BankType.BANK_DEYANGYH.getBankCode() + "-" + BankType.BANK_DEYANGYH.getBankName(), "04986580");
//			45	东莞农村商业银行	区域	借记卡	　	14156020
			lianLianBankExchangeMap.put(BankType.BANK_DGNCSYYH.getBankCode() + "-" + BankType.BANK_DGNCSYYH.getBankName(), "14156020");
//			46	东营银行	区域	　	信用卡	65274550
			lianLianBankExchangeMap.put(BankType.BANK_DONGYYH.getBankCode() + "-" + BankType.BANK_DONGYYH.getBankName(), "65274550");
//			47	鄂尔多斯银行	区域	借记卡	信用卡	05342050
			lianLianBankExchangeMap.put(BankType.BANK_EEDSYH.getBankCode() + "-" + BankType.BANK_EEDSYH.getBankName(), "05342050");
//			48	福建省农村信用社	区域	　	信用卡	14173900
			lianLianBankExchangeMap.put(BankType.BANK_FJNCXYS.getBankCode() + "-" + BankType.BANK_FJNCXYS.getBankName(), "14173900");
//			49	抚顺银行	区域	借记卡	　	04302240
			lianLianBankExchangeMap.put(BankType.BANK_FUSHUNYH.getBankCode() + "-" + BankType.BANK_FUSHUNYH.getBankName(), "04302240");
//			50	富滇银行	区域	借记卡	信用卡	64667310
			lianLianBankExchangeMap.put(BankType.BANK_FUDIANYH.getBankCode() + "-" + BankType.BANK_FUDIANYH.getBankName(), "64667310");
//			51	广西北部湾银行	区域	借记卡	　	64786110
			lianLianBankExchangeMap.put(BankType.BANK_GXBBWYH.getBankCode() + "-" + BankType.BANK_GXBBWYH.getBankName(), "64786110");
//			52	广西农村信用社	区域	借记卡	　	14436100
			lianLianBankExchangeMap.put(BankType.BANK_GXNCXYS.getBankCode() + "-" + BankType.BANK_GXNCXYS.getBankName(), "14436100");
//			53	广州银行	区域	借记卡	信用卡	64135810
			lianLianBankExchangeMap.put(BankType.BANK_GZYH.getBankCode() + "-" + BankType.BANK_GZYH.getBankName(), "64135810");
//			54	广东农村信用社	区域	借记卡	　	14505800
			lianLianBankExchangeMap.put(BankType.BANK_GDNCXYS.getBankCode() + "-" + BankType.BANK_GDNCXYS.getBankName(), "14505800");
//			55	广州农村商业银行	区域	借记卡	信用卡	14055810
			lianLianBankExchangeMap.put(BankType.BANK_GZNCSYYH.getBankCode() + "-" + BankType.BANK_GZNCSYYH.getBankName(), "14055810");
//			56	赣州银行	区域	借记卡	信用卡	64634280
			lianLianBankExchangeMap.put(BankType.BANK_GANZHOUYH.getBankCode() + "-" + BankType.BANK_GANZHOUYH.getBankName(), "64634280");
//			57	桂林银行	区域	借记卡	　	64916170
			lianLianBankExchangeMap.put(BankType.BANK_GUILINYH.getBankCode() + "-" + BankType.BANK_GUILINYH.getBankName(), "64916170");
//			58	贵阳银行	区域	借记卡	信用卡	64437010
			lianLianBankExchangeMap.put(BankType.BANK_GYYH.getBankCode() + "-" + BankType.BANK_GYYH.getBankName(), "64437010");
//			59	甘肃省农村信用社联合社	区域	借记卡	　	14538200
			lianLianBankExchangeMap.put(BankType.BANK_GSSNCXYS.getBankCode() + "-" + BankType.BANK_GSSNCXYS.getBankName(), "14538200");
//			60	河北省农村信用社	区域	借记卡	　	14411200
			lianLianBankExchangeMap.put(BankType.BANK_HBNCXYS.getBankCode() + "-" + BankType.BANK_HBNCXYS.getBankName(), "14411200");
			
//			61	鹤壁银行	区域	借记卡	　	05354970
			lianLianBankExchangeMap.put(BankType.BANK_HEBIYH.getBankCode() + "-" + BankType.BANK_HEBIYH.getBankName(), "05354970");
//			62	衡水市商业银行	区域	借记卡	　	05611480
			lianLianBankExchangeMap.put(BankType.BANK_HENGSSSYYH.getBankCode() + "-" + BankType.BANK_HENGSSSYYH.getBankName(), "05611480");
//			63	哈尔滨银行	区域	借记卡	信用卡	04422610
			lianLianBankExchangeMap.put(BankType.BANK_HEBYH.getBankCode() + "-" + BankType.BANK_HEBYH.getBankName(), "04422610");
//			64	海南省农村信用社联合社	区域	借记卡	　	14486400
			lianLianBankExchangeMap.put(BankType.BANK_HAINNCXYS.getBankCode() + "-" + BankType.BANK_HAINNCXYS.getBankName(), "14486400");
//			65	湖南省农村信用社联合社	区域	借记卡	信用卡	14385500
			lianLianBankExchangeMap.put(BankType.BANK_HNNCXYS.getBankCode() + "-" + BankType.BANK_HNNCXYS.getBankName(), "14385500");
//			66	湖北省农村信用社联合社	区域	借记卡	　	14105200
			lianLianBankExchangeMap.put(BankType.BANK_HBNCXYS.getBankCode() + "-" + BankType.BANK_HBNCXYS.getBankName(), "14105200");
//			67	湖北银行	区域	借记卡	　	05755200
			lianLianBankExchangeMap.put(BankType.BANK_HUBEIYH.getBankCode() + "-" + BankType.BANK_HUBEIYH.getBankName(), "05755200");
//			68	葫芦岛银行	区域	借记卡	　	04332350
			lianLianBankExchangeMap.put(BankType.BANK_HULUDAOYH.getBankCode() + "-" + BankType.BANK_HULUDAOYH.getBankName(), "04332350");
//			69	邯郸银行	区域	借记卡	　	05171270
			lianLianBankExchangeMap.put(BankType.BANK_HANDANYH.getBankCode() + "-" + BankType.BANK_HANDANYH.getBankName(), "05171270");
//			70	嘉兴银行	区域	借记卡	　	04703350
			lianLianBankExchangeMap.put(BankType.BANK_JIAXINGYH.getBankCode() + "-" + BankType.BANK_JIAXINGYH.getBankName(), "04703350");
//			71	晋城银行	区域	借记卡	　	05031680
			lianLianBankExchangeMap.put(BankType.BANK_JICHENGYH.getBankCode() + "-" + BankType.BANK_JICHENGYH.getBankName(), "05031680");
//			72	江西农村信用联合社	区域	借记卡	　	14394200
			lianLianBankExchangeMap.put(BankType.BANK_JXNCXYS.getBankCode() + "-" + BankType.BANK_JXNCXYS.getBankName(), "14394200");
//			73	江苏省农村信用社联合社	区域	借记卡	信用卡	14243000
			lianLianBankExchangeMap.put(BankType.BANK_JSNCXYS.getBankCode() + "-" + BankType.BANK_JSNCXYS.getBankName(), "14243000");
//			74	江阴农村商业银行	区域	借记卡	信用卡	14123020
			lianLianBankExchangeMap.put(BankType.BANK_JYNCSYYH.getBankCode() + "-" + BankType.BANK_JYNCSYYH.getBankName(), "14123020");
//			75	江南农村商业银行	区域	借记卡	　	14603040
			lianLianBankExchangeMap.put(BankType.BANK_JNNCSYYH.getBankCode() + "-" + BankType.BANK_JNNCSYYH.getBankName(), "14603040");
//			76	吉林省农村信用社	区域	借记卡	　	14452400
			lianLianBankExchangeMap.put(BankType.BANK_JLNCXYS.getBankCode() + "-" + BankType.BANK_JLNCXYS.getBankName(), "14452400");
//			77	吉林银行	区域	借记卡	　	04512420
			lianLianBankExchangeMap.put(BankType.BANK_JILINYH.getBankCode() + "-" + BankType.BANK_JILINYH.getBankName(), "04512420");
//			78	晋中市商业银行	区域	借记卡	　	05591750
			lianLianBankExchangeMap.put(BankType.BANK_JZSSYYH.getBankCode() + "-" + BankType.BANK_JZSSYYH.getBankName(), "05591750");
//			79	锦州银行	区域	　	信用卡	64392270
			lianLianBankExchangeMap.put(BankType.BANK_JZYH.getBankCode() + "-" + BankType.BANK_JZYH.getBankName(), "64392270");
//			80	九江银行	区域	借记卡	信用卡	64544240
			lianLianBankExchangeMap.put(BankType.BANK_JJYH.getBankCode() + "-" + BankType.BANK_JJYH.getBankName(), "64544240");
//			81	昆山农村商业银行	区域	借记卡	　	14023052
			lianLianBankExchangeMap.put(BankType.BANK_KSNCSYYH.getBankCode() + "-" + BankType.BANK_KSNCSYYH.getBankName(), "14023052");
//			82	库尔勒市商业银行	区域	借记卡	　	05658880
			lianLianBankExchangeMap.put(BankType.BANK_KELSSYYH.getBankCode() + "-" + BankType.BANK_KELSSYYH.getBankName(), "05658880");
//			83	昆仑银行	区域	借记卡	　	05478820
			lianLianBankExchangeMap.put(BankType.BANK_KLYH.getBankCode() + "-" + BankType.BANK_KLYH.getBankName(), "05478820");
//			84	龙江银行	区域	借记卡	信用卡	04922600
			lianLianBankExchangeMap.put(BankType.BANK_LJYH.getBankCode() + "-" + BankType.BANK_LJYH.getBankName(), "04922600");
//			85	凉山州商业银行	区域	借记卡	　	05556840
			lianLianBankExchangeMap.put(BankType.BANK_LSZSYYH.getBankCode() + "-" + BankType.BANK_LSZSYYH.getBankName(), "05556840");
//			86	乐山市商业银行	区域	借记卡	　	05406650
			lianLianBankExchangeMap.put(BankType.BANK_LSSSYYH.getBankCode() + "-" + BankType.BANK_LSSSYYH.getBankName(), "05406650");
//			87	临商银行	区域	借记卡	　	64314730
			lianLianBankExchangeMap.put(BankType.BANK_LINSYH.getBankCode() + "-" + BankType.BANK_LINSYH.getBankName(), "64314730");
//			88	柳州银行	区域	借记卡	　	04956140
			lianLianBankExchangeMap.put(BankType.BANK_LIUZHOUYH.getBankCode() + "-" + BankType.BANK_LIUZHOUYH.getBankName(), "04956140");
//			89	漯河银行	区域	借记卡	　	05565040
			lianLianBankExchangeMap.put(BankType.BANK_LUOHEYH.getBankCode() + "-" + BankType.BANK_LUOHEYH.getBankName(), "05565040");
//			90	兰州银行	区域	借记卡	信用卡	04478210
			lianLianBankExchangeMap.put(BankType.BANK_LZYH.getBankCode() + "-" + BankType.BANK_LZYH.getBankName(), "04478210");
			
//			91	内蒙古银行	区域	借记卡	　	04741910
			lianLianBankExchangeMap.put(BankType.BANK_NMGYH.getBankCode() + "-" + BankType.BANK_NMGYH.getBankName(), "04741910");
//			92	内蒙古农村信用社	区域	借记卡	　	04741910
			lianLianBankExchangeMap.put(BankType.BANK_NMGNCXYS.getBankCode() + "-" + BankType.BANK_NMGNCXYS.getBankName(), "04741910");
//			93	宁波银行	区域	　	信用卡	04083320
			lianLianBankExchangeMap.put(BankType.BANK_NBYH.getBankCode() + "-" + BankType.BANK_NBYH.getBankName(), "04083320");
//			94	宁波东海银行	区域	借记卡	　	05503320
			lianLianBankExchangeMap.put(BankType.BANK_NBDHYH.getBankCode() + "-" + BankType.BANK_NBDHYH.getBankName(), "05503320");
//			95	宁夏黄河农商银行	区域	借记卡	　	14468700
			lianLianBankExchangeMap.put(BankType.BANK_NXHHNSYH.getBankCode() + "-" + BankType.BANK_NXHHNSYH.getBankName(), "14468700");
//			96	南昌银行	区域	借记卡	信用卡	64484210
			lianLianBankExchangeMap.put(BankType.BANK_NCYH.getBankCode() + "-" + BankType.BANK_NCYH.getBankName(), "64484210");
//			97	南充市商业银行	区域	借记卡	　	04966730
			lianLianBankExchangeMap.put(BankType.BANK_NCSSYYH.getBankCode() + "-" + BankType.BANK_NCSSYYH.getBankName(), "04966730");
//			98	平顶山银行	区域	借记卡	　	05484950
			lianLianBankExchangeMap.put(BankType.BANK_PDSYH.getBankCode() + "-" + BankType.BANK_PDSYH.getBankName(), "05484950");
//			99	攀枝花市商业银行	区域	借记卡	　	04836560
			lianLianBankExchangeMap.put(BankType.BANK_PZHSSYYH.getBankCode() + "-" + BankType.BANK_PZHSSYYH.getBankName(), "04836560");
//			100	青海银行	区域	　	信用卡	64588510
			lianLianBankExchangeMap.put(BankType.BANK_QHYH.getBankCode() + "-" + BankType.BANK_QHYH.getBankName(), "64588510");
//			101	齐鲁银行	区域	借记卡	信用卡	64094510
			lianLianBankExchangeMap.put(BankType.BANK_JLYH.getBankCode() + "-" + BankType.BANK_JLYH.getBankName(), "64094510");
//			102	秦皇岛市商业银行	区域	借记卡	　	04571260
			lianLianBankExchangeMap.put(BankType.BANK_QHDSSYYH.getBankCode() + "-" + BankType.BANK_QHDSSYYH.getBankName(), "04571260");
//			103	泉州银行	区域	借记卡	　	04643970
			lianLianBankExchangeMap.put(BankType.BANK_QZYH.getBankCode() + "-" + BankType.BANK_QZYH.getBankName(), "04643970");
//			104	曲靖市商业银行	区域	借记卡	　	05027360
			lianLianBankExchangeMap.put(BankType.BANK_QJSSYYH.getBankCode() + "-" + BankType.BANK_QJSSYYH.getBankName(), "05027360");
//			105	青海省农村信用社	区域	借记卡	　	14498500
			lianLianBankExchangeMap.put(BankType.BANK_QHSNCXYS.getBankCode() + "-" + BankType.BANK_QHSNCXYS.getBankName(), "14498500");
//			106	青岛银行	区域	借记卡	信用卡	04504520
			lianLianBankExchangeMap.put(BankType.BANK_QDYH.getBankCode() + "-" + BankType.BANK_QDYH.getBankName(), "04504520");
//			107	上海农商银行	区域	借记卡	信用卡	65012900
			lianLianBankExchangeMap.put(BankType.BANK_SHNSYH.getBankCode() + "-" + BankType.BANK_SHNSYH.getBankName(), "65012900");
//			108	三门峡银行	区域	借记卡	　	04885050
			lianLianBankExchangeMap.put(BankType.BANK_SMXYH.getBankCode() + "-" + BankType.BANK_SMXYH.getBankName(), "04885050");
//			109	上饶银行	区域	借记卡	信用卡	65264330
			lianLianBankExchangeMap.put(BankType.BANK_SRYH.getBankCode() + "-" + BankType.BANK_SRYH.getBankName(), "65264330");
//			110	深圳福田银座村镇银行	区域	借记卡	　	15205840
			lianLianBankExchangeMap.put(BankType.BANK_SZFTYZCZYH.getBankCode() + "-" + BankType.BANK_SZFTYZCZYH.getBankName(), "15205840");
//			111	商丘银行	区域	借记卡	　	65675060
			lianLianBankExchangeMap.put(BankType.BANK_SQYH.getBankCode() + "-" + BankType.BANK_SQYH.getBankName(), "65675060");
//			112	深圳农商银行	区域	借记卡	　	14045840
			lianLianBankExchangeMap.put(BankType.BANK_SZNSYH.getBankCode() + "-" + BankType.BANK_SZNSYH.getBankName(), "14045840");
//			113	苏州银行	区域	借记卡	　	04213050
			lianLianBankExchangeMap.put(BankType.BANK_SZYH.getBankCode() + "-" + BankType.BANK_SZYH.getBankName(), "04213050");
//			114	遂宁市商业银行	区域	借记卡	　	05516620
			lianLianBankExchangeMap.put(BankType.BANK_SNSSYYH.getBankCode() + "-" + BankType.BANK_SNSSYYH.getBankName(), "05516620");
//			115	顺德农村商业银行	区域	借记卡	信用卡	65085883
			lianLianBankExchangeMap.put(BankType.BANK_SDNSYH.getBankCode() + "-" + BankType.BANK_SDNSYH.getBankName(), "65085883");
//			116	四川省农村信用合作社	区域	借记卡	　	14526500
			lianLianBankExchangeMap.put(BankType.BANK_SCSNCXYS.getBankCode() + "-" + BankType.BANK_SCSNCXYS.getBankName(), "14526500");
//			117	山东省农村信用社	区域	借记卡	　	14144500
			lianLianBankExchangeMap.put(BankType.BANK_SDSNCXYS.getBankCode() + "-" + BankType.BANK_SDSNCXYS.getBankName(), "14144500");
//			118	山西尧都农村商业银行	区域	　	信用卡	14341770
			lianLianBankExchangeMap.put(BankType.BANK_SXRDNCSYYH.getBankCode() + "-" + BankType.BANK_SXRDNCSYYH.getBankName(), "14341770");
//			119	山西省农村信用社联合社	区域	借记卡	　	14551600
			lianLianBankExchangeMap.put(BankType.BANK_SXSNCXYS.getBankCode() + "-" + BankType.BANK_SXSNCXYS.getBankName(), "14551600");
//			120	泰安市商业银行	区域	借记卡	　	05284630
			lianLianBankExchangeMap.put(BankType.BANK_TASSYYH.getBankCode() + "-" + BankType.BANK_TASSYYH.getBankName(), "05284630");
//			121	太仓农村商业银行	区域	借记卡	　	14333051
			lianLianBankExchangeMap.put(BankType.BANK_TCNCSYYH.getBankCode() + "-" + BankType.BANK_TCNCSYYH.getBankName(), "14333051");
//			122	唐山市商业银行	区域	借记卡	　	04991240
			lianLianBankExchangeMap.put(BankType.BANK_TSSSYYH.getBankCode() + "-" + BankType.BANK_TSSSYYH.getBankName(), "04991240");
//			123	天津滨海农村商业银行	区域	借记卡	　	14561100
			lianLianBankExchangeMap.put(BankType.BANK_TJBHNCSYYH.getBankCode() + "-" + BankType.BANK_TJBHNCSYYH.getBankName(), "14561100");
//			124	台州银行	区域	借记卡	信用卡	04593450
			lianLianBankExchangeMap.put(BankType.BANK_TZYH.getBankCode() + "-" + BankType.BANK_TZYH.getBankName(), "04593450");
//			125	潍坊银行	区域	借记卡	信用卡	64624580
			lianLianBankExchangeMap.put(BankType.BANK_WFYH.getBankCode() + "-" + BankType.BANK_WFYH.getBankName(), "64624580");
//			126	威海市商业银行	区域	借记卡	信用卡	03134650
			lianLianBankExchangeMap.put(BankType.BANK_WHSSYYH.getBankCode() + "-" + BankType.BANK_WHSSYYH.getBankName(), "03134650");
//			127	乌海银行	区域	借记卡	　	05311930
			lianLianBankExchangeMap.put(BankType.BANK_WUHAIYH.getBankCode() + "-" + BankType.BANK_WUHAIYH.getBankName(), "05311930");
//			128	吴江农商银行	区域	借记卡	信用卡	14283054
			lianLianBankExchangeMap.put(BankType.BANK_WJNSYH.getBankCode() + "-" + BankType.BANK_WJNSYH.getBankName(), "14283054");
//			129	无锡农村商业银行	区域	借记卡	信用卡	04453020
			lianLianBankExchangeMap.put(BankType.BANK_WXNCYH.getBankCode() + "-" + BankType.BANK_WXNCYH.getBankName(), "04453020");
//			130	乌鲁木齐市商业银行	区域	借记卡	信用卡	04270001
			lianLianBankExchangeMap.put(BankType.BANK_WLMQYH.getBankCode() + "-" + BankType.BANK_WLMQYH.getBankName(), "04270001");
			
//			131	厦门银行	区域	借记卡	　	04023930
			lianLianBankExchangeMap.put(BankType.BANK_XMYH.getBankCode() + "-" + BankType.BANK_XMYH.getBankName(), "04023930");
//			132	许昌银行	区域	借记卡	　	05365030
			lianLianBankExchangeMap.put(BankType.BANK_XCYH.getBankCode() + "-" + BankType.BANK_XCYH.getBankName(), "05365030");
//			133	邢台银行	区域	借记卡	　	05541310
			lianLianBankExchangeMap.put(BankType.BANK_YTYH.getBankCode() + "-" + BankType.BANK_YTYH.getBankName(), "05541310");
//			134	宜昌市商业银行	区域	　	信用卡	04325260
			lianLianBankExchangeMap.put(BankType.BANK_YCSSYYH.getBankCode() + "-" + BankType.BANK_YCSSYYH.getBankName(), "04325260");
//			135	宜宾市商业银行	区域	借记卡	　	05646710
			lianLianBankExchangeMap.put(BankType.BANK_YBSSYYH.getBankCode() + "-" + BankType.BANK_YBSSYYH.getBankName(), "05646710");
//			136	雅安市商业银行	区域	借记卡	　	05666770
			lianLianBankExchangeMap.put(BankType.BANK_YASSYYH.getBankCode() + "-" + BankType.BANK_YASSYYH.getBankName(), "05666770");
//			137	阳泉市商业银行	区域	借记卡	　	05631650
			lianLianBankExchangeMap.put(BankType.BANK_YQSSYYH.getBankCode() + "-" + BankType.BANK_YQSSYYH.getBankName(), "05631650");
//			138	玉溪市商业银行	区域	借记卡	　	05247410
			lianLianBankExchangeMap.put(BankType.BANK_YXSSYYH.getBankCode() + "-" + BankType.BANK_YXSSYYH.getBankName(), "05247410");
//			139	银川市商业银行	区域	　	信用卡	15438710
			lianLianBankExchangeMap.put(BankType.BANK_YCSSYYH.getBankCode() + "-" + BankType.BANK_YCSSYYH.getBankName(), "15438710");
//			140	云南省农村信用社	区域	借记卡	　	65097300
			lianLianBankExchangeMap.put(BankType.BANK_YNSNCXYS.getBankCode() + "-" + BankType.BANK_YNSNCXYS.getBankName(), "65097300");
//			141	鄞州银行	区域	　	信用卡	14203320
			lianLianBankExchangeMap.put(BankType.BANK_JZYHYH.getBankCode() + "-" + BankType.BANK_JZYHYH.getBankName(), "14203320");
//			142	营口银行	区域	借记卡	　	04652280
			lianLianBankExchangeMap.put(BankType.BANK_YKYH.getBankCode() + "-" + BankType.BANK_YKYH.getBankName(), "04652280");
//			143	浙江稠州商业银行	区域	借记卡	信用卡	05303380
			lianLianBankExchangeMap.put(BankType.BANK_ZJCZSYYH.getBankCode() + "-" + BankType.BANK_ZJCZSYYH.getBankName(), "05303380");
//			144	浙江民泰商业银行	区域	借记卡	信用卡	05253450
			lianLianBankExchangeMap.put(BankType.BANK_ZJMTSYYH.getBankCode() + "-" + BankType.BANK_ZJMTSYYH.getBankName(), "05253450");
//			145	浙江泰隆商业银行	区域	　	信用卡	64733450
			lianLianBankExchangeMap.put(BankType.BANK_ZJTLSYYH.getBankCode() + "-" + BankType.BANK_ZJTLSYYH.getBankName(), "64733450");
//			146	朝阳银行	区域	借记卡	　	05492340
			lianLianBankExchangeMap.put(BankType.BANK_CYYH.getBankCode() + "-" + BankType.BANK_CYYH.getBankName(), "05492340");
//			147	张家港农村商业银行	区域	借记卡	　	14163056
			lianLianBankExchangeMap.put(BankType.BANK_ZJGNCSYYH.getBankCode() + "-" + BankType.BANK_ZJGNCSYYH.getBankName(), "14163056");
//			148	张家口市商业银行	区域	借记卡	　	04901380
			lianLianBankExchangeMap.put(BankType.BANK_ZJKSSYYH.getBankCode() + "-" + BankType.BANK_ZJKSSYYH.getBankName(), "04901380");
//			149	自贡市商业银行	区域	借记卡	　	05326560
			lianLianBankExchangeMap.put(BankType.BANK_ZGSSYYH.getBankCode() + "-" + BankType.BANK_ZGSSYYH.getBankName(), "05326560");
//			150	周口银行	区域	借记卡	　	05625080
			lianLianBankExchangeMap.put(BankType.BANK_ZKYH.getBankCode() + "-" + BankType.BANK_ZKYH.getBankName(), "05625080");
//			151	遵义市商业银行	区域	借记卡	　	05167030
			lianLianBankExchangeMap.put(BankType.BANK_ZYSSYYH.getBankCode() + "-" + BankType.BANK_ZYSSYYH.getBankName(), "05167030");
//			152	韩国中小企业银行	外资	借记卡	　	28590410
			lianLianBankExchangeMap.put(BankType.BANK_HGZXQYYH.getBankCode() + "-" + BankType.BANK_HGZXQYYH.getBankName(), "28590410");
//			153	花旗银行	外资	借记卡	　	03190001
			lianLianBankExchangeMap.put(BankType.BANK_HQYH.getBankCode() + "-" + BankType.BANK_HQYH.getBankName(), "03190001");
//			154	东亚银行	外资	借记卡	　	03200000
			lianLianBankExchangeMap.put(BankType.BANK_DYYH.getBankCode() + "-" + BankType.BANK_DYYH.getBankName(), "03200000");
//			155	渣打银行	外资	借记卡	　	03220000
			lianLianBankExchangeMap.put(BankType.BANK_ZDYH.getBankCode() + "-" + BankType.BANK_ZDYH.getBankName(), "03220000");
//			156	法兴银行	外资	借记卡	　	03330001
			lianLianBankExchangeMap.put(BankType.BANK_FXYH.getBankCode() + "-" + BankType.BANK_FXYH.getBankName(), "03330001");
//			157	大华银行	外资	借记卡	　	03340000
			lianLianBankExchangeMap.put(BankType.BANK_DHYH.getBankCode() + "-" + BankType.BANK_DHYH.getBankName(), "03340000");
//			158	新韩银行	外资	借记卡	　	03280000
			lianLianBankExchangeMap.put(BankType.BANK_XHYH.getBankCode() + "-" + BankType.BANK_XHYH.getBankName(), "03280000");
	   }
	 
}
