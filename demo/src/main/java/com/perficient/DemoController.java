package com.perficient;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.perficient.domain.Fortune;
import com.perficient.domain.FortuneProperties;

@Configuration
@EnableAutoConfiguration
@RestController
// @EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@Service
@EnableConfigurationProperties(FortuneProperties.class)
public class DemoController {

	private final AtomicLong counter = new AtomicLong();
	private static String template = "Hello , %s!";
	Fortune fortune = null;
	public Fortune getFortune() {
		return fortune;
	}

	public void setFortune(Fortune fortune) {
		this.fortune = fortune;
	}

	@Autowired
	private RestTemplate rest;
	@Autowired
	private FortuneProperties prop;

	@RequestMapping("/demo")
	public String getDemo() {
		return " HOLA!! COMO ESTAS? ";
	}

	@RequestMapping("/demoMessage")
	public DemoModel getDemoMessage(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new DemoModel(counter.getAndIncrement(), String.format(template, name));
	}

	/**
	 * Config Server - http://cloud.spring.io/spring-cloud-config/ The value of
	 * config.name in the sample (or any other values you bind to in the normal
	 * Spring Boot way) can come from local configuration or from the remote
	 * Config Server. The Config Server will take precedence by default. To see
	 * this look at the /env endpoint in the application and see the
	 * configServer property sources.
	 * 
	 * If you set spring.config.name=configserver the app will run on port 8888
	 * and serve data from a sample repository.
	 * 
	 * You need a spring.cloud.config.server.git.uri to locate the configuration
	 * data for your own needs (by default it is the location of a git
	 * repository, and can be a local file:.. URL).
	 */
	@Value("${greeting}")
	String greeting = "world";

	@Value("${app.names}")
	String[] names;

	/*
	 * @Value("${hello}") String hello = "";
	 */

	@RequestMapping("/")
	public String home() {
		return "Hello " + greeting;
	}

	/*
	 * @RequestMapping("/hello") public String homeHello() { return "Hello " +
	 * hello; }
	 */

	/**
	 * Eureka - Service Discovery As long as Spring Cloud Netflix and Eureka
	 * Core are on the classpath any Spring Boot application
	 * with @EnableEurekaClient will try to contact a Eureka server on
	 * http://localhost:8761 (the default value of
	 * eureka.client.serviceUrl.defaultZone):
	 * 
	 * http://cosmos-dev.cfapps.io/guides/gs/service-registry/
	 * 
	 * Native netflix Eureka client
	 * 
	 * @Autowired private EurekaClient discoveryClient;
	 * 
	 *            public String serviceUrl() { InstanceInfo instance =
	 *            discoveryClient.getNextServerFromEureka("STORES", false);
	 *            return instance.getHomePageUrl(); }
	 * 
	 *            Alternative
	 * 
	 * @Autowired private DiscoveryClient discoveryClient;
	 * 
	 *            public String serviceUrl() { List<ServiceInstance> list =
	 *            client.getInstances("STORES"); if (list != null && list.size()
	 *            > 0 ) { return list.get(0).getUri(); } return null; }
	 * 
	 */

	@RequestMapping(value = "/discoverFortuneSrv", method = RequestMethod.GET)
	public String discoverFortuneSrv() {

		Fortune fortune = rest.getForObject("http://fortunes/random", Fortune.class);
		return fortune.getText();
	}

	

}
