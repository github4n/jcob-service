package com.aicai.jcob.tjexpert.manager;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertWinRatioQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertAdminWinRatioResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertRecommendResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertWinRatioResult;

/**
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午3:16:48 程序的简单说明
 */
public interface TjExpertInfoManager {

	DataPage<TjExpertInfoAdminResult> pageTjExpertInfo(DataPage<TjExpertInfo> page,
			TjExpertInfoQueryOption tjExpertInfoQueryOption);

	DataPage<TjExpertAdminWinRatioResult> pageExpertWinRatio(DataPage<TjExpertAdminWinRatioResult> page,
			TjExpertWinRatioQueryOption tjExpertInfoQueryOption);

	TjExpertInfo addTjexpert(Long memberId, String desc, TjExpertLevel expertLevel);

	int updateExpertInfo(TjExpertInfo tjExpertInfo);

	/**
	 * 查询专家信息
	 * 
	 * @param memberId
	 * @return
	 * @create_time 2016年2月4日 下午4:54:59
	 */
	public TjExpertInfo queryExpertInfoByMemberId(Long memberId);

	/**
	 * 
	 * @param levelArray
	 * @param page
	 * @return
	 */
	DataPage<TjExpertInfo> pageExpertByLevel(Integer[] levelArray, DataPage<TjExpertInfo> page);

	/**
	 * 根据相联的MEMBER ID组查询 TjExpertInfo 信息
	 * 
	 * @param id
	 * @return
	 */
	List<TjExpertInfo> queryTjExpertInfoByMemberIdArray(Long[] idArray);

	/**
	 * 更新专家record
	 * 
	 * @param memberId
	 * @param newNecord
	 * @param oldRecord
	 * @return
	 */
	int updateExpertRecord(Long memberId, String newRecord, String oldRecord);

	/**
	 * 更新专家level
	 * 
	 * @param memberId
	 * @param newLevel
	 * @param oldLevel
	 * @return
	 */
	int updateExpertLevel(Long memberId, Integer newLevel, Integer oldLevel);

	/**
	 * 关注:增加关注我的人数
	 * 
	 * @param memberId
	 * @return
	 */
	int attentionMeAdd(Long memberId);

	/**
	 * 取关:减少关注我的人数
	 * 
	 * @param memberId
	 * @return
	 */
	int attentionMeSub(Long memberId);

	/**
	 * 关注:增加我关注的人数
	 * 
	 * @param memberId
	 * @return
	 */
	int attentionOtherAdd(Long memberId);

	/**
	 * 取关:减少我关注的人数
	 * 
	 * @param memberId
	 * @return
	 */
	int attentionOtherSub(Long memberId);

	/**
	 * 更新专家胜率
	 * 
	 * @param memberId
	 */
	public void updateTjExpertInfoWinRatioByMemberId(Long memberId);

	ModelResult<Map<Long, TjExpertRecommendResult>> queryRecommendExpertByMemberId(List<Long> memberIdList);

	ModelResult<Boolean> updateExpertLevelAndInsertLog(Long memberId, Integer newLevel, Integer oldLevel, Integer handType);

	DataPage<TjExpertWinRatioResult> pageExpertWinRatioByRank(DataPage<TjExpertWinRatioResult> dataPage,
			Integer winRatioRankField, Integer leastCountPlan);

	DataPage<TjExpertInfo> pageExpertInfo(DataPage<TjExpertInfo> page, Long[] memberIdArr, Integer level, Calendar startTime,
			Calendar endTime);

	/**
	 * 将降为普通用户的专家的attention_me置零
	 * 
	 * @param memberId
	 * @return
	 */
	int zeroAttentionMe(Long memberId);
	
	DataPage<Long> queryExpertMemberId(DataPage<Long> dataPage);
}
