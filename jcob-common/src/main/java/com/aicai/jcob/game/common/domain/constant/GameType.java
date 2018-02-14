package com.aicai.jcob.game.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 钱包变动业务类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class GameType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public GameType(Integer index, String description) {
		super(index, description);
	}
	public GameType(Integer index,String description,String outDescription) {
		super( index,  description, outDescription);
	}
	public static GameType JJ_ZC_SPF = new GameType(4071, "竞彩足球让球胜平负","胜平负");
    public static GameType JJ_ZC_RQ_SPF = new GameType(4076, "竞彩足球胜平负","让球胜平负");
    public static GameType JJ_ZC_YP = new GameType(4078, "竞彩足球亚盘","亚盘");
    public static GameType JJ_ZC_WW_DXQ = new GameType(4079, "竞彩足球外围大小球","大小球");
	public static List<GameType> getAllList() {
		return getAll(GameType.class);
	}
	public static  GameType valueOf(Integer index){
		return valueOf(GameType.class, index);
	}
}
