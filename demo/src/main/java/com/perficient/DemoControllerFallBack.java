package com.perficient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.perficient.domain.Fortune;
import com.perficient.domain.FortuneProperties;


@RestController
@ComponentScan
@Component
public class DemoControllerFallBack {

		
		
	@Autowired
	private RestTemplate rest;
	@Autowired
	private FortuneProperties prop;

	/**
	 * Hystrix
	 * 
	 * 
	 * 
	 */

	/*@RequestMapping("/fallbackRandomFortuneSrv")
	public Fortune randomFortune() {
		return demoContrl.randomFortune();
	}*/
	
	/**
	 * Hystrix
	 * 
	 * 
	 * 
	 */
	
	
	@RequestMapping(value = "/testfallback", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "fallbackFortuneOP")
	public Fortune randomFortune() {
		return rest.getForObject("http://fortunes/random", Fortune.class);
	}

	
	private Fortune fallbackFortuneOP() {
		//fortune =  Fortune(42L, prop.getFallbackFortune());
		return new Fortune(42L, prop.getFallbackFortune());
	}
	
	@RequestMapping(value = "/testspring", method = RequestMethod.GET)
	public String test(){
		return "test String";
	}
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public Object test1(){
		return new Object();
	}

}
