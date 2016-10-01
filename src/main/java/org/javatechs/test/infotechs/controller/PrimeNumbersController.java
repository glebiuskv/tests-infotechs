package org.javatechs.test.infotechs.controller;

import org.javatechs.test.infotechs.service.PrimeNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gleb
 *         30.09.2016.
 */
@Controller
@RequestMapping("/prime")
public class PrimeNumbersController {
	private static Logger log = LoggerFactory.getLogger(PrimeNumbersController.class);

	@Autowired
	PrimeNumberService numberService;

	@RequestMapping(method = RequestMethod.GET)
	public String getForm() {
		return "form.html";
	}

	@RequestMapping(value = "/calculate")
	@ResponseBody
	@Cacheable("prime_number_mult_cache")
	public Map<String, Object> calculate(@RequestParam Long input) {
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("result", numberService.process(input));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			result.put("error", e.getMessage());
		}
		return result;
	}

}
