package com.zq.springboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.springboot.common.MessageResult;
import com.zq.springboot.common.PageResult;
import com.zq.springboot.commontypes.ExpensesInfo;
import com.zq.springboot.dao.ExpensesDao;
import com.zq.springboot.service.ExpensesService;
import com.zq.springboot.utils.PageUtils;

@Service
public class ExpensesServiceImpl implements ExpensesService {

	@Autowired
	private ExpensesDao expensesDao;

	@Override
	public MessageResult<List<ExpensesInfo>> getExpensesList(String expensesDate, int pageSize, int curPage) {
		MessageResult<List<ExpensesInfo>> result = new MessageResult<List<ExpensesInfo>>();
		PageHelper.startPage(curPage, pageSize);
		List<ExpensesInfo> expensesInfoList = expensesDao.getExpensesList(expensesDate);
		PageResult pageResult = PageUtils.getPageResult(new PageInfo<ExpensesInfo>(expensesInfoList));
		result.setPageResult(pageResult);
		if (expensesInfoList == null) {
			result.setMessage("查询消费列表失败");
			result.setSuccessed(false);
		} else {
			result.setData(expensesInfoList);
			result.setMessage("查询消费列表成功");
			result.setSuccessed(true);
		}
		return result;
	}

	@Override
	public MessageResult<ExpensesInfo> getExpensesInfoById(String id) {
		MessageResult<ExpensesInfo> result = new MessageResult<ExpensesInfo>();
		ExpensesInfo expensesInfo = expensesDao.getExpensesInfoById(id);
		if (expensesInfo == null) {
			result.setMessage("查询消费明细失败");
			result.setSuccessed(false);
		} else {
			result.setData(expensesInfo);
			result.setMessage("查询消费明细成功");
			result.setSuccessed(true);
		}
		return result;
	}

	@Override
	public MessageResult<String> addExpenses(ExpensesInfo expensesInfo) {
		MessageResult<String> result = new MessageResult<String>();
		expensesInfo.setId(new Date().getTime()+"");
		int addNum = expensesDao.addExpenses(expensesInfo);
		if (addNum == 0) {
			result.setMessage("添加消费明细失败");
			result.setSuccessed(false);
		} else {
			result.setData("添加成功");
			result.setMessage("添加消费明细成功");
			result.setSuccessed(true);
		}
		return result;
	}


}
