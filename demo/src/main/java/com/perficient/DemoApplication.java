package com.perficient;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication


public class DemoApplication {//implements Banner {

    public static void main(String[] args) {
       SpringApplication.run(DemoApplication.class, args);
        //SpringApplication.setBanner
        
    	/*SpringApplication app = new SpringApplication(DemoApplication.class); 
        app.setShowBanner(true); 
        app.run(args);*/
        				
       
        System.out.println("DEMO APP STARTED***********");
    }

	/*@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
		// TODO Auto-generated method stub
		out.println("DILIP");
	}*/
    
    
   
    
}
