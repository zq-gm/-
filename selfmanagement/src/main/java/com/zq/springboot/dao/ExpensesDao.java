package com.zq.springboot.dao;

import java.util.List;

import com.zq.springboot.commontypes.ExpensesInfo;

public interface ExpensesDao {

	/**
	 * @param 查询日期，分页大小，当前页
	 * @return
	 */
	List<ExpensesInfo> getExpensesList(String expensesDate);
	
	/**
	 * @param id 消费信息id
	 * @return 当前消费详情
	 */
	ExpensesInfo getExpensesInfoById(String id);
	
	/**
	 * @param expensesInfo 消费详情
	 * @return 是否添加成功
	 */
	int addExpenses(ExpensesInfo expensesInfo);
	
}
