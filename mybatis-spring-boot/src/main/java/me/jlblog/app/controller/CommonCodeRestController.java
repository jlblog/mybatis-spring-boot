package me.jlblog.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.jlblog.app.common.model.JsonResponse;
import me.jlblog.app.domain.CommonCode;
import me.jlblog.app.service.CommonCodeService;
import me.jlblog.app.service.LoginUserDetails;

@RestController
@RequestMapping("api/commons")
public class CommonCodeRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeRestController.class);
	
	@Autowired
	CommonCodeService commonCodeService;
	
	@RequestMapping(value = "code", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse getCommonCodeList(){
		List<CommonCode> commonCodeList = commonCodeService.findAll();
		return new JsonResponse(true, commonCodeList);
	}
	
	@RequestMapping(value = "code/{code}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse getCommonCode(@PathVariable String code) {
		CommonCode commonCode = commonCodeService.findOne(code);
		return new JsonResponse(true, commonCode); 
	}

	@RequestMapping(value = "code", method = RequestMethod.POST)
	public JsonResponse postCommonCode (@RequestBody CommonCode commonCode, @AuthenticationPrincipal LoginUserDetails userDetails) {
		try {
			commonCode.setRegId(userDetails.getUsername());
			commonCodeService.create(commonCode);
			return new JsonResponse(true, commonCode);
		} catch (Exception e) {
			logger.error("postCommonCode", e);
			return new JsonResponse(false);
		}
	}
	
	@RequestMapping(value = "code", method = RequestMethod.PUT)
	public JsonResponse putCommonCode (@RequestBody CommonCode commonCode, @AuthenticationPrincipal LoginUserDetails userDetails) {
		try {
			commonCode.setRegId(userDetails.getUsername());
			commonCodeService.update(commonCode);
			return new JsonResponse(true, commonCode);
		} catch (Exception e) {
			logger.error("putCommonCode", e);
			return new JsonResponse(false);
		}
	}
	
	@RequestMapping(value = "code/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public JsonResponse deleteCommonCode (@PathVariable String code, @AuthenticationPrincipal LoginUserDetails userDetails, CommonCode commonCode) {
		try {
			commonCode.setRegId(userDetails.getUsername());
			commonCodeService.delete(commonCode);
			return new JsonResponse(true, commonCode);
		} catch (Exception e) {
			logger.error("deleteCommonCode", e);
			return new JsonResponse(false);
		}
	}
	
}
