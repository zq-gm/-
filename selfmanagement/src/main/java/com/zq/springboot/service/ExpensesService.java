package com.zq.springboot.service;

import java.util.Date;
import java.util.List;

//import java.util.List;

import com.zq.springboot.common.MessageResult;
import com.zq.springboot.commontypes.ExpensesInfo;


/**
 * @author fmq
 * 消费记录表service
 */
public interface ExpensesService {
	
	/**
	 * @param 查询日期，分页大小，当前页
	 * @return
	 */
	MessageResult<List<ExpensesInfo>> getExpensesList(String expensesDate, int pageSize, int curPage);
	
	/**
	 * @param id 消费信息id
	 * @return 当前消费详情
	 */
	MessageResult<ExpensesInfo> getExpensesInfoById(String id);
	
	/**
	 * @param expensesInfo 消费详情
	 * @return 
	 * @return 是否添加成功
	 */
	MessageResult<String> addExpenses(ExpensesInfo expensesInfo);

}
