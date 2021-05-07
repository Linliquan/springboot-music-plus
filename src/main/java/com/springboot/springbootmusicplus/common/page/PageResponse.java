package com.springboot.springbootmusicplus.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 15:33
 */
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = -5820727439829169724L;

    /**
     * 总记录数
     */
    private long totalCount = 0;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    /**
     * 当前页
     */
    private int currPage = 1;

    /**
     * 列表数据
     */
    private List<T> list = new ArrayList<>();

    public <T> PageResponse() {
    }

    public PageResponse(long totalCount, int pageSize, int currPage, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.list = list;
    }

    /**
     * 成功获取到数据
     *
     * @param list
     * @param currPage
     * @param pageSize
     * @param totalCount
     * @param <T>
     * @return
     */
    public static <T> PageResponse<T> success(List<T> list, int currPage, int pageSize, long totalCount) {
        if (list == null) {
            return empty(currPage, pageSize);
        }
        return new PageResponse<>(totalCount, pageSize, currPage, list);
    }

    /**
     * 空数据
     *
     * @param currPage
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> PageResponse<T> empty(int currPage, int pageSize) {
        return new PageResponse<>(0, pageSize, currPage, Collections.<T>emptyList());
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        int total = (int) totalCount;
        if (total < pageSize) {
            return 1;
        }
        return total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "{" +
                "totalCount=" + totalCount +
                ", totalPage=" + getTotalPage() +
                ", pageSize=" + pageSize +
                ", currPage=" + currPage +
                ", list=" + (null == list ? 0 : list.size()) +
                '}';
    }
}
