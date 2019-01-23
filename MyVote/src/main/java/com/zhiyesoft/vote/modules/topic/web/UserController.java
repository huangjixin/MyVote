package com.zhiyesoft.vote.modules.topic.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.zhiyesoft.vote.basic.core.vo.Response;
import com.zhiyesoft.vote.modules.topic.domain.User;
import com.zhiyesoft.vote.modules.topic.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户后台控制器Api", description = "用户级别后台控制器api")
@Controller
@RequestMapping("vote/user")
public class UserController {
	private static final String IMG_REDIS_KEY = "img:";

	@Autowired
	private IUserService userService;
	@Autowired
	private Producer producer;

	@Autowired
	CacheManager cacheManager;

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
		int result = userService.deleteByPrimaryKey(id);
		response.setData(result);
		return response;
	}

	@ApiOperation(value = "查看对象", notes = "")
	@GetMapping(value = "view/{id}")
	@ResponseBody
	public Response getById(@PathVariable(name = "id") Integer id) {
		Response response = new Response();
		User record = userService.selectByPrimaryKey(id);
		response.setData(record);
		return response;
	}

	@ApiOperation(value = "查看全部对象", notes = "")
	@GetMapping(value = "selectAll")
	@ResponseBody
	public Response selectAll() {
		Response response = new Response();
		List<User> list = userService.selectAll();
		response.setDataList(list);
		return response;
	}

	@ApiOperation(value = "登陆接口，如果登陆不成功则返回null，反则返回数据库第一个人的信息", notes = "登陆接口，如果登陆不成功则返回null，反则返回数据库第一个人的信息")
	@PostMapping(value = "login")
	@ResponseBody
	public Response login(@RequestParam(value = "mobil") String mobil, @RequestParam(value = "password") String password) {
		Response response = new Response();
		User user = userService.login(mobil, password);
		if (user == null) {
			user = new User();
		}
		response.setData(user);
		return response;
	}

	@ApiOperation(value = "createImg", notes = "")
	@RequestMapping(value = "createImg", method = RequestMethod.GET)
	public void captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		session.setAttribute("verifyCode", text);
		// 将验证码保存至redis key为sessionId
//		if (redisUtil != null) {
//			String key = IMG_REDIS_KEY + request.getSession().getId();
//			redisUtil.set(key, text, 120);
//		}
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

}
