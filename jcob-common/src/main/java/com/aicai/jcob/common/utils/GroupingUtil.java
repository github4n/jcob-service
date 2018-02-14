package com.aicai.jcob.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分组工具
 * @author fx
 *
 */
public class GroupingUtil {
	
	/**
	 * 根据下标取模分组
	 * @return
	 */
	public static <T> Map<Integer,List<T>> toGroupsByIndex(List<T> objs,int maxGroupCount,int countPerGroup){
    	Map<Integer,List<T>> objectMap=new HashMap<Integer,List<T>>();
    	if(objs.isEmpty()){
    		return objectMap;
    	}
    	int mapSize=objs.size()/countPerGroup+1; //组数
        mapSize=mapSize>maxGroupCount?maxGroupCount:mapSize;
  
        for (int listIndex=0;listIndex<objs.size();listIndex++) {
        	Integer mapIndex=0;
        	if(mapSize-1!=0){
        		mapIndex=listIndex%mapSize;
        	}
        	List<T> subList=objectMap.get(mapIndex);
        	if(subList==null){
        		subList=new ArrayList<T>();
        		objectMap.put(mapIndex, subList);
        	}
        	subList.add(objs.get(listIndex));
        }
        return objectMap;
	}
	
	/**
	 * 根据下标顺序分组
	 * @return
	 */
	public static <T> Map<Integer,List<T>> toGroupsByIndexOrder(List<T> objs,int maxGroupCount,int countPerGroup){
    	Map<Integer,List<T>> objectMap=new HashMap<Integer,List<T>>();
    	if(objs.isEmpty()){
    		return objectMap;
    	}
    	int mapSize=objs.size()/countPerGroup+1; //组数
        mapSize=mapSize>maxGroupCount?maxGroupCount:mapSize;
        countPerGroup=objs.size()/mapSize+1;
    	int listFrom=0;
    	int listTo=listFrom+countPerGroup;
    	Integer mapIndex=0;
    	listTo=listTo>objs.size()?objs.size():listTo;
        do{
        	List<T> subList=objs.subList(listFrom, listTo);
        	objectMap.put(mapIndex, subList);
        	listFrom=listTo;
        	listTo+=countPerGroup;
        	mapIndex++;
        	listTo=listTo>objs.size()?objs.size():listTo;
        }while(listTo<=objs.size() && listFrom<listTo);
        
        return objectMap;
	}
	
	public static void main(String[] args){
		List<Long> ids=new ArrayList<Long>();
		for(long i=0;i<420;i++){
			ids.add(i);
		}
		Map<Integer,List<Long>> map=toGroupsByIndexOrder(ids,3,200);
		int i=0;
		int j=0;
		for(Object v:map.values()){
			i++;
			System.out.println(i+"++++++++++");
			for(Object t:(List<Object>)v){
				System.out.println(((Long)t).intValue());
				System.out.println("------"+j);
				j++;
			}
		}
		
	}
}
