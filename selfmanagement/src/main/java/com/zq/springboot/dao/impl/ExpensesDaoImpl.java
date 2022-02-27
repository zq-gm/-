package com.zq.springboot.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.springboot.commontypes.ExpensesInfo;
import com.zq.springboot.dao.ExpensesDao;

@Mapper
@Service
public class ExpensesDaoImpl implements ExpensesDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ExpensesInfo> getExpensesList(String expensesDate) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("expensesDate",expensesDate);
		List<ExpensesInfo> sdf = sqlSession.selectList("getExpensesList", param);
		return sdf;
	}

	@Override
	public ExpensesInfo getExpensesInfoById(String id) {
		ExpensesInfo expensesInfo = sqlSession.selectOne("getExpensesInfoById", id);
		return expensesInfo;
	}

	@Override
	public int addExpenses(ExpensesInfo expensesInfo) {
		int result = sqlSession.insert("addExpenses", expensesInfo);
		return result;
	}

}
