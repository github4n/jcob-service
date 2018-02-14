package com.aicai.jcob.tjplan.common.domain.constant;

import java.util.ArrayList;
import java.util.List;

import com.aicai.jcob.tjrace.common.domain.constant.TjRaceLeagueType;

/**
 * 方案联赛标记
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月25日
 */
public class TjPlanLeagueTypeBit {
    /**TjPlanLeagueTypeBit 联赛类别:bit二进制未按位标识
第一位：欧冠，第二位英超，第三位：西甲，第四位：意甲，第五位：德甲，第六位：法甲，第7位：荷甲，第八位：葡超 */
	public final static long defualt = 0;
	public final static long og = 1;
	public final static long yc = 1 << 1;
	public final static long xj = 1 << 2;
	public final static long yj = 1 << 3;
	public final static long dj = 1 << 4;
	public final static long fj = 1 << 5;
	public final static long hj = 1 << 6;
	public final static long pc = 1 << 7;

	
	/**
	 * 验证  optionFlagbit操作标识是否已经存在userFlagbit 中
	 * @param optionFlagbit
	 * @param userFlagbit
	 * @return
	 */
	public static boolean isExistFlagBit(long optionFlagbit,long userFlagbit) {
		return 	(optionFlagbit & userFlagbit )> 0;
	}
	
	public static List<Long>  leagueTypeToLeagueTypeBit(List<TjRaceLeagueType> leagueTypeList){
		List<Long> leagueTypeBitList = new ArrayList<Long>();
		if (leagueTypeList == null || leagueTypeList.size() <= 0) {
			return leagueTypeBitList;
		}
		for (TjRaceLeagueType leagueType : leagueTypeList) {
			if (leagueType == null) {
				continue;
			}
			if (leagueType.getIndex() == TjRaceLeagueType.og.getIndex()) {
				leagueTypeBitList.add(og);
			}else if (leagueType.getIndex() == TjRaceLeagueType.yc.getIndex()) {
				leagueTypeBitList.add(yc);
			}else if (leagueType.getIndex() == TjRaceLeagueType.xj.getIndex()) {
				leagueTypeBitList.add(xj);
			}else if (leagueType.getIndex() == TjRaceLeagueType.yj.getIndex()) {
				leagueTypeBitList.add(yj);
			}else if (leagueType.getIndex() == TjRaceLeagueType.dj.getIndex()) {
				leagueTypeBitList.add(dj);
			}else if (leagueType.getIndex() == TjRaceLeagueType.fj.getIndex()) {
				leagueTypeBitList.add(fj);
			}else if (leagueType.getIndex() == TjRaceLeagueType.hj.getIndex()) {
				leagueTypeBitList.add(hj);
			}else if (leagueType.getIndex() == TjRaceLeagueType.pc.getIndex()) {
			leagueTypeBitList.add(pc);
			}
		}
		
		return leagueTypeBitList;
	}
}
