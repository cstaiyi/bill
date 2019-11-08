package com.zcit.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hifeng
 * @date 2018/8/8 10:37
 */
@Getter
@Setter
public class PageResult<E> extends BaseReturn {
    private long total;

    private long pages;

    private List<E> data;

    public PageResult() {
    }

    public PageResult(IPage<E> page) {
        this.total = page.getTotal();
        this.data = page.getRecords();
        this.pages = page.getPages();
    }
}
