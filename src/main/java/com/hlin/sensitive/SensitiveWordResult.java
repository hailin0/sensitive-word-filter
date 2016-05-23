package com.hlin.sensitive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 敏感词匹配结果
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class SensitiveWordResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6908376892848907410L;

    /**
     * 敏感词
     */
    private String word;

    /**
     * 敏感词匹配位置集合
     */
    private List<PositionNode> positions = new ArrayList<PositionNode>();

    /**
     * 构造器
     * 
     * @param startPosition
     * @param word
     */
    public SensitiveWordResult(int startPosition, String word) {
        this.word = word;
        addPosition(startPosition, word);
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the positions
     */
    public List<PositionNode> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<PositionNode> positions) {
        this.positions = positions;
    }

    /**
     * 记录敏感词位置
     * 
     * @param startPosition
     * @param word
     */
    public void addPosition(int startPosition, String word) {
        positions.add(new PositionNode(startPosition, startPosition + word.length()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SensitiveWordResult other = (SensitiveWordResult) obj;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SensitiveWordResult [word=" + word + ", positions=" + positions + "]";
    }

    /**
     * 位置节点
     * 
     * @author hailin0@yeah.net
     * @createDate 2016年5月22日
     *
     */
    public class PositionNode {
        /**
         * 开始位置
         */
        private int startPosition;

        /**
         * 结束位置
         */
        private int endPosition;

        /**
         * @param startPosition
         * @param endPosition
         */
        public PositionNode(int startPosition, int endPosition) {
            super();
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

        /**
         * @return the startPosition
         */
        public int getStartPosition() {
            return startPosition;
        }

        /**
         * @param startPosition the startPosition to set
         */
        public void setStartPosition(int startPosition) {
            this.startPosition = startPosition;
        }

        /**
         * @return the endPosition
         */
        public int getEndPosition() {
            return endPosition;
        }

        /**
         * @param endPosition the endPosition to set
         */
        public void setEndPosition(int endPosition) {
            this.endPosition = endPosition;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "PositionNode [startPosition=" + startPosition + ", endPosition=" + endPosition
                    + "]";
        }

    }
}
