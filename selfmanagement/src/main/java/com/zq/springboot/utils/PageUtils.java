package com.zq.springboot.utils;

import com.github.pagehelper.PageInfo;
import com.zq.springboot.common.PageResult;

public class PageUtils {

	/**
	 * 根据分页插件获取的结果集设置到分页对象
	 * @param pageInfo
	 * @return 分页结果
	 */
	public static PageResult getPageResult(PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        return pageResult;
    }
}
