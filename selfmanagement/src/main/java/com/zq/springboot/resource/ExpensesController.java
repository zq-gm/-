package com.zq.springboot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zq.springboot.common.MessageResult;
import com.zq.springboot.commontypes.ExpensesInfo;
import com.zq.springboot.commontypes.UserInfo;
import com.zq.springboot.service.ExpensesService;


@RestController
public class ExpensesController {

	@Autowired
	private ExpensesService expensesService;
	
	@RequestMapping(value="/expenses/expensesList/{pageSize}/{curPage}",method= RequestMethod.GET)
	public MessageResult<List<ExpensesInfo>> getExpensesList(@PathVariable Integer pageSize, @PathVariable Integer curPage, @RequestParam(value="expensesDate",required = false,defaultValue = "") String expensesDate) {
		MessageResult<List<ExpensesInfo>> result = new MessageResult<List<ExpensesInfo>>();
		result = expensesService.getExpensesList(expensesDate, pageSize, curPage);
		return result;
	}
	@PostMapping(value="/expenses/addexpenses")
	public MessageResult<String> userLogin(@RequestBody ExpensesInfo expensesInfo) {
		MessageResult<String> result = new MessageResult<String>();
		if (expensesInfo == null) {
			result.setMessage("添加消费明细失败");
			result.setSuccessed(false);
		} else {
			result = expensesService.addExpenses(expensesInfo);
		}
		return result;
	}
	@RequestMapping(value="/expenses/expensesinfoById/{id}",method= RequestMethod.GET)
	public MessageResult<ExpensesInfo> getExpensesInfoById(@PathVariable String id) {
		MessageResult<ExpensesInfo> result = new MessageResult<ExpensesInfo>();
		result = expensesService.getExpensesInfoById(id);
		return result;
	}

}
