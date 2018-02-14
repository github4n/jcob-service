package com.aicai.jcob.test.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyLogQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogResult;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyWayResult;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyLogManager;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyWayManager;
import com.aicai.jcob.test.TestBase;

public class MemberDrawMoneyTest extends TestBase {

	@Autowired
	@Qualifier("memberDrawMoneyWayManagerImpl")
	private MemberDrawMoneyWayManager memberDrawMoneyWayManager;

	@Autowired
	@Qualifier("memberDrawMoneyLogManagerImpl")
	private MemberDrawMoneyLogManager memberDrawMoneyLogManager;
	
	@Test
	public void queryMemberDrawMoneyWayByPage() {
		DataPage<MemberDrawMoneyWayResult> page = new DataPage<MemberDrawMoneyWayResult>();
		MemberDrawMoneyWayQueryOption option = new MemberDrawMoneyWayQueryOption();
		page = memberDrawMoneyWayManager.queryMemberDrawMoneyWayByPage(option, page);
		for (MemberDrawMoneyWayResult item : page.getDataList()) {
			System.out.println(item.getId());
		}
	}
	
	@Test
	public void queryMemberDrawMoneyLogByPage() {
		DataPage<MemberDrawMoneyLogResult> page = new DataPage<MemberDrawMoneyLogResult>();
		MemberDrawMoneyLogQueryOption option = new MemberDrawMoneyLogQueryOption();
		page = memberDrawMoneyLogManager.queryMemberDrawMoneyLogByPage(option, page);
		for (MemberDrawMoneyLogResult item : page.getDataList()) {
			System.out.println(item.getId());
		}
	}
}
