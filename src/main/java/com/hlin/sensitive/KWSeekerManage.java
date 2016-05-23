package com.hlin.sensitive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 敏感词管理器
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class KWSeekerManage {

    /**
     * 敏感词模块. key为模块名，value为对应的敏感词搜索器
     */
    private Map<String, KWSeeker> seekers = new ConcurrentHashMap<String, KWSeeker>();

    /**
     * 初始化
     */
    public KWSeekerManage() {
    }

    /**
     * 
     * @param seekers
     */
    public KWSeekerManage(Map<String, KWSeeker> seekers) {
        this.seekers.putAll(seekers);
    }

    /**
     * 获取一个敏感词搜索器
     * 
     * @param wordType
     * @return
     */
    public KWSeeker getKWSeeker(String wordType) {
        return seekers.get(wordType);
    }

    /**
     * 加入一个敏感词搜索器
     * 
     * @param wordType
     * @param kwSeeker
     * @return
     */
    public void putKWSeeker(String wordType, KWSeeker kwSeeker) {
        seekers.put(wordType, kwSeeker);
    }

    /**
     * 移除一个敏感词搜索器
     * 
     * @param wordType
     * @return
     */
    public void removeKWSeeker(String wordType) {
        seekers.remove(wordType);
    }

    /**
     * 清除空搜索器
     */
    public void clear() {
        seekers.clear();
    }

}
