package com.aicai.jcob.memberwallet.common.domain.result;

import java.math.BigDecimal;

import com.aicai.appmodel.domain.result.BaseResult;
import com.aicai.appmodel.page.DataPage;

public class PageWalletResult<T> extends BaseResult {
	private static final long serialVersionUID = -1091780329565150714L;
	private DataPage<T> page;
	
	private BigDecimal totalAmount;

	public DataPage<T> getPage(){
		return page;
	}
	public void setPage(DataPage<T> page){
		this.page = page;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <SubClass extends PageWalletResult> SubClass withPage(DataPage<T> page){
		this.page = page;
		return (SubClass)this;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
