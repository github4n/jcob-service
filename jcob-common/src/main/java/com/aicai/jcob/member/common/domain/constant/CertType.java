package com.aicai.jcob.member.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 身份证件类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class CertType extends BaseType {
	private static final long serialVersionUID = 7585644138649957702L;
	public CertType(Integer status, String description) {
		super(status, description);
	}
	public static CertType identity_card = new CertType(0, "身份证");
	public static CertType officer_card = new CertType(1, "军官证");
	public static CertType passport_card = new CertType(2, "护照");
	public static List<CertType> getAllList() {
		return getAll(CertType.class);
	}
	public static  CertType valueOf(Integer index){
		return valueOf(CertType.class, index);
	}
}
