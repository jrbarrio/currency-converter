package com.roldan.currencyconverter.web.rates;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roldan.currencyconverter.domain.model.ExchangeRateService;

@Controller
@RequestMapping("/")
public class RatesController {

	private ExchangeRateService exchangeRateService;
	
	@Autowired
	public RatesController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("ratesForm", new RatesForm()); 
		return "ratesForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processRegister(@Valid RatesForm ratesForm, Errors errors) {
		if (errors.hasErrors()) {
			return "ratesForm";
		}
		
		float rate = exchangeRateService.getExchangeRate(
				ratesForm.getFrom(), 
				ratesForm.getTo(), 
				ratesForm.getDate());
		
		ratesForm.setRate(rate);
		
		return "ratesForm";
	}
}