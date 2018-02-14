package com.aicai.jcob.memberdrawmoney.common.domain.constant;

import java.math.BigDecimal;

/**
 * 充值提款常量配置
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年3月11日
 */
public class MembeDrawAndChargeConstant {
	/**最大提款金额**/
	public static BigDecimal drawMoneyMax = BigDecimal.valueOf(5000);
	/**最小提款金额**/
	public static BigDecimal drawMoneyMin = BigDecimal.valueOf(10);
	/**提款手续费**/
	public static BigDecimal getDrawMoneyFee(BigDecimal drawmoneyAmount){
		return BigDecimal.valueOf(1);
	}
	
}
