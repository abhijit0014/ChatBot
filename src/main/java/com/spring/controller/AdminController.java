package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dbSearch.DbService;
import com.spring.dbSearch.QueryEntity;
import com.spring.dbSearch.ResponseEntity;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final DbService dbService;

	public AdminController(DbService dbService) {
		super();
		this.dbService = dbService;
	}
	// query
	@GetMapping("/")
	public String addQueryGet(Model model)
	{
		model.addAttribute("query",new QueryEntity());
		model.addAttribute("queryList", dbService.getTop20Query());
		return "addQuery";
	}
	
	@PostMapping("/addQuery")
	public String addQueryPost(@ModelAttribute QueryEntity queryEntity, Model model)
	{
		dbService.saveQuery(queryEntity);
		model.addAttribute("query",new QueryEntity());
		model.addAttribute("queryList", dbService.getTop20Query());
		return "addQuery";
	}
	
	@GetMapping("/deleteQuery")
	public String deleteQuery(@RequestParam("id") int id,Model model)
	{
		dbService.deleteQuery(id);
		model.addAttribute("query",new QueryEntity());
		model.addAttribute("queryList", dbService.getTop20Query());
		return "addQuery";		
	}
	
	//response
	@GetMapping("/addResponse")
	public String addResponseGet(Model model,@RequestParam(value="queryid",required=false) Long queryid)
	{
		ResponseEntity responseEntity = new ResponseEntity();
		
		if(queryid!=null) {
			responseEntity.setQueryid(queryid);
			model.addAttribute("responseList",dbService.getALlResponsesByQueryId(queryid));	
			model.addAttribute("currentQuery", dbService.getByQueryId(queryid));
		}else {
			model.addAttribute("currentQuery", new QueryEntity());
		}
		model.addAttribute("response",responseEntity);		
		model.addAttribute("queryList", dbService.getTop20Query());
		return "addResponse";
	}
	
	@PostMapping("/addResponse")
	public String addResponsePost(@ModelAttribute ResponseEntity responseEntity, Model model)
	{
		dbService.saveResponse(responseEntity);
		model.addAttribute("response",new ResponseEntity());
		model.addAttribute("queryList", dbService.getTop20Query());
		model.addAttribute("currentQuery", new QueryEntity());
		return "addResponse";
	}
	@GetMapping("/deleteResponse")
	public String deleteResponse(@RequestParam("responseid") int response,Model model)
	{
		dbService.deleteResponse(response);
		model.addAttribute("currentQuery", new QueryEntity());
		model.addAttribute("response",new ResponseEntity());
		model.addAttribute("queryList", dbService.getTop20Query());
		return "addResponse";		
	}	
}
