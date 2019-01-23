package com.zhiyesoft.vote.modules.topic.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhiyesoft.vote.basic.core.utils.FileUtil;
import com.zhiyesoft.vote.basic.core.vo.Response;
import com.zhiyesoft.vote.modules.topic.domain.Customer;
import com.zhiyesoft.vote.modules.topic.domain.Vote;
import com.zhiyesoft.vote.modules.topic.service.ICustomerService;
import com.zhiyesoft.vote.modules.topic.service.IUserService;
import com.zhiyesoft.vote.modules.topic.service.IVoteService;
import com.zhiyesoft.vote.modules.topic.vo.Person;
import com.zhiyesoft.vote.modules.topic.vo.TopicVO;
import com.zhiyesoft.vote.modules.topic.vo.VoteVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "投票后台控制器Api", description = "投票级别后台控制器api")
@Controller
@RequestMapping("vote/vote")
public class VoteController {

	@Autowired
	private IVoteService voteService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICustomerService customerService;

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
		int result = voteService.deleteByPrimaryKey(id);
		response.setData(result);
		return response;
	}

	@ApiOperation(value = "查看对象", notes = "")
	@GetMapping(value = "view/{id}")
	@ResponseBody
	public Response getById(@PathVariable(name = "id") String id) {
		Response response = new Response();
		Vote record = voteService.selectByPrimaryKey(id);
		response.setData(record);
		return response;
	}

	@ApiOperation(value = "投票", notes = "")
	@PostMapping(value = "insertVotes")
	@ResponseBody
	public Response insertVotes(@RequestBody List<Vote> votes, @RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "topicId") Integer topicId, @RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "custname") String custname) {
		Response response = new Response();
		Customer customer = new Customer();
		String customerId = UUID.randomUUID().toString().replaceAll("-", "");
		customer.setId(customerId);
		customer.setMobile(mobile);
		customer.setName(custname);
		customerService.insertSelective(customer);
		for (Vote vote : votes) {
			vote.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			vote.setClientId(customerId);
		}
		int result = this.voteService.insert(votes);
		if (result > 0) {
			response.setMessage("插入成功");
		} else {
			response.setMessage("插入失败");
			response.setCode("500");
		}
		return response;
	}

	@ApiOperation(value = "查看全部对象", notes = "查看全部对象")
	@GetMapping(value = "selectAll")
	@ResponseBody
	public Response selectAll() {
		Response response = new Response();
		List<Vote> list = voteService.selectAll();
		response.setData(list);
		return response;
	}

	@ApiOperation(value = "分页查看对象", notes = "分页查看对象")
	@GetMapping(value = "selectByPage")
	@ResponseBody
	public Response selectByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		Response response = new Response();
		PageInfo<Vote> pageInfo = voteService.selectByExample(null, page, size);
		response.setData(pageInfo);
		return response;
	}

	@ApiOperation(value = "查询topic的所有投票结果", notes = "")
	@PostMapping(value = "selectResultByTopicId")
	@ResponseBody
	public Response selectResultByTopicId(@RequestParam(value = "topicId") Integer topicId) {
		Response resp = new Response();
		List<TopicVO> topics = this.voteService.selectResultByTopicId(topicId);
		resp.setDataList(topics);
		return resp;
	}

	@ApiOperation(value = "查询用户的对topic的投票结果", notes = "")
	@PostMapping(value = "selectTopicResultByUserId")
	@ResponseBody
	public Response selectTopicResultByUserId(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "topicId") Integer topicId) {
		Response resp = new Response();
		List<TopicVO> topics = this.voteService.selectTopicResultByUserId(userId, topicId);
		resp.setDataList(topics);
		return resp;
	}

	@ApiOperation(value = "查询投票人和日期", notes = "")
	@PostMapping(value = "selectVoteByDate")
	@ResponseBody
	public Response selectVoteByDate(@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		Response resp = new Response();
		List<VoteVO> votes = this.voteService.selectVoteByDate(startDate, endDate, userId, page, size);
		resp.setDataList(votes);
		return resp;
	}

	@GetMapping("export")
	public void export(HttpServletResponse response) {

		// 模拟从数据库获取需要导出的数据
		List<Person> personList = new ArrayList<>();
		Person person1 = new Person("路飞", "1", new Date());
		Person person2 = new Person("娜美", "2", new Date());
		Person person3 = new Person("索隆", "1", new Date());
		Person person4 = new Person("小狸猫", "1", new Date());
		personList.add(person1);
		personList.add(person2);
		personList.add(person3);
		personList.add(person4);

		// 导出操作
		try {
			FileUtil.exportExcel(personList, "花名册", "草帽一伙", Person.class, "海贼王.xls", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("importExcel")
	public void importExcel() {
		String filePath = "F:\\海贼王.xls";
		// 解析excel，
		List<Person> personList;
		try {
			personList = FileUtil.importExcel(filePath, 1, 1, Person.class);
			// 也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer
			// titleRows, Integer headerRows, Class<T> pojoClass)导入
			System.out.println("导入数据一共【" + personList.size() + "】行");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO 保存数据库
	}

	@ApiOperation(value = "导出topic的所有投票结果", notes = "")
	@GetMapping(value = "exportResultByTopicId")
	@ResponseBody
	public Response exportResultByTopicId(@RequestParam(value = "topicId") Integer topicId,
			HttpServletResponse response) {
		Response resp = new Response();
		List<TopicVO> topics = this.voteService.selectResultByTopicId(topicId);
		try {
			if (!topics.isEmpty()) {
				FileUtil.exportExcel(topics, topics.get(0).getTopicName() + "-投票结果", "结果", TopicVO.class,
						topics.get(0).getTopicName()+"-投票结果.xls", response);
				resp.setMessage("导出成功");
			}else {
				resp.setMessage("导出内容为空");
			}
		} catch (Exception e) {
			resp.setCode("400");
			resp.setMessage("导出失败");
		}
		return resp;
	}
	
	@ApiOperation(value = "导出用户对topic的投票结果", notes = "")
	@GetMapping(value = "exportTopicResultByUserId")
	@ResponseBody
	public Response exportTopicResultByUserId(@RequestParam(value = "userId") String userId,@RequestParam(value = "topicId") Integer topicId,
			HttpServletResponse response) {
		Response resp = new Response();
		List<TopicVO> topics = this.voteService.selectTopicResultByUserId(userId, topicId);
		try {
			if (!topics.isEmpty()) {
				FileUtil.exportExcel(topics, topics.get(0).getTopicName() + "-投票结果", "结果", TopicVO.class,
						topics.get(0).getTopicName()+"-投票结果.xls", response);
					resp.setMessage("导出成功");
			}else {
				resp.setMessage("导出内容为空");
			}
		} catch (Exception e) {
			resp.setCode("400");
			resp.setMessage("导出失败");
			e.printStackTrace();
		}
		return resp;
	}
	
	@ApiOperation(value = "导出投票人和日期", notes = "")
	@PostMapping(value = "exportVoteByDate")
	@ResponseBody
	public Response exportVoteByDate(@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			HttpServletResponse response) {
		Response resp = new Response();
		List<VoteVO> votes = this.voteService.selectVoteByDate(startDate, endDate, userId, page, size);
		try {
			if (!votes.isEmpty()) {
				FileUtil.exportExcel(votes, "参与人和参与时间", "结果", VoteVO.class,"参与人和参与时间.xls", response);
				resp.setMessage("导出成功");
			}else {
				resp.setMessage("导出内容为空");
			}
		} catch (Exception e) {
			resp.setCode("400");
			resp.setMessage("导出失败");
			e.printStackTrace();
		}
		return resp;
	}
}
