package com.zhiyesoft.vote.modules.topic.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhiyesoft.vote.basic.core.vo.Response;
import com.zhiyesoft.vote.modules.topic.domain.Question;
import com.zhiyesoft.vote.modules.topic.domain.Topic;
import com.zhiyesoft.vote.modules.topic.domain.Vote;
import com.zhiyesoft.vote.modules.topic.service.IQuestionService;
import com.zhiyesoft.vote.modules.topic.vo.QuestionVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "问题后台控制器Api", description = "问题级别后台控制器api")
@Controller
@RequestMapping("vote/quest")
public class QuestionController {

	@Autowired
	private IQuestionService questionService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		// 此方法中的参数格式化 针对@RequestBody修饰的对象时无效
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ApiOperation(value = "刪除对象", notes = "")
	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public Response delete(@PathVariable(name = "id") String id) {
		Response response = new Response();
		int result = questionService.deleteByPrimaryKey(id);
		response.setData(result);
//		Vote vote = Vote.builder().;
		return response;
	}

	@ApiOperation(value = "查看对象", notes = "")
	@GetMapping(value = "view/{id}")
	@ResponseBody
	public Response getById(@PathVariable(name = "id") String id) {
		Response response = new Response();
		Question record = questionService.selectByPrimaryKey(id);
		response.setData(record);
		return response;
	}

	@ApiOperation(value = "查看全部对象", notes = "查看全部对象")
	@GetMapping(value = "selectAll")
	@ResponseBody
	public Response selectAll() {
		Response response = new Response();
		List<Question> list = questionService.selectAll();
		response.setData(list);
		return response;
	}

	@ApiOperation(value = "分页查看对象", notes = "分页查看对象")
	@GetMapping(value = "selectByPage")
	@ResponseBody
	public Response selectByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		Response response = new Response();
		PageInfo<Question> pageInfo = questionService.selectByExample(null, page, size);
		response.setData(pageInfo);
		return response;
	}
	
	@ApiOperation(value = "根据话题查看全部问题对象", notes = "")
	@GetMapping(value = "selectAllByTopicId")
	@ResponseBody
	public Response selectAllByTopicId(@RequestParam(value = "topicId") Integer topicId) {
		Response response = new Response();
		List<QuestionVO> list = questionService.selectAllByTopicId(topicId);
		response.setDataList(list);
		return response;
	}
}
