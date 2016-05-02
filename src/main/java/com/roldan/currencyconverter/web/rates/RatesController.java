package com.roldan.currencyconverter.web.rates;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roldan.currencyconverter.domain.model.ExchangeRateService;
import com.roldan.currencyconverter.domain.model.Query;
import com.roldan.currencyconverter.domain.model.QueryRepository;

@Controller
@RequestMapping("/")
public class RatesController {

	private ExchangeRateService exchangeRateService;
	private QueryRepository queryRepository;
	private QueryTraslator queryTraslator;
	
	@Autowired
	public RatesController(ExchangeRateService exchangeRateService, 
			QueryRepository queryRepository, QueryTraslator queryTraslator) {
		this.exchangeRateService = exchangeRateService;
		this.queryRepository = queryRepository;
		this.queryTraslator = queryTraslator;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("queryForm", new QueryForm());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();		
		
		List<Query> queries = queryRepository.findByUserName(username);
		List<QueryForm> queryFormList = queryTraslator.traslate(queries);
		model.addAttribute("queryFormList", queryFormList);
		
		return "ratesForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processRegister(@Valid QueryForm queryForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "ratesForm";
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		queryForm.setUsername(username);
		Query query = queryTraslator.traslate(queryForm);
		float exchangeRate = exchangeRateService.getExchangeRate(query);
		query.setRate(exchangeRate);		
		
		queryRepository.save(query);
		
		List<Query> queries = queryRepository.findByUserName(username);
		List<QueryForm> queryFormList = queryTraslator.traslate(queries);
		model.addAttribute("queryFormList", queryFormList);
		
		return "ratesForm";
	}
}