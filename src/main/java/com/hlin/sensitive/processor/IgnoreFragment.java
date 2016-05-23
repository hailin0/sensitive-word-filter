package com.hlin.sensitive.processor;

import com.hlin.sensitive.KeyWord;

/**
 * 
 * 替换内容的片段处理方式
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class IgnoreFragment extends AbstractFragment {

    private String ignore = "";

    public IgnoreFragment() {
    }

    public IgnoreFragment(String ignore) {
        this.ignore = ignore;
    }

    @Override
    public String format(KeyWord word) {
        return ignore;
    }

}
