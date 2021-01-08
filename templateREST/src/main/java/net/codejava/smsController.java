package net.codejava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class smsController implements Controllers<sms> {
	@Autowired 
	private notificationService service;
	@Autowired
	private smsService sservice;
	
	@Override
	@GetMapping("/sms") // localhost/notifications
	public List<sms> list() {
		return sservice.listAll();
	}
	@Override
	@GetMapping("/sms/{id}")
	public ResponseEntity<sms> get(@PathVariable Integer id) {
	    try {
	    	System.out.println("checking the function");
	    	sms sms=sservice.get(id);
	   
	    	 return new ResponseEntity<sms>(sms, HttpStatus.OK);
	    }
	    catch (Exception e) {
	    	return new ResponseEntity<sms>(HttpStatus.NOT_FOUND);
		}
	       
	       
	}
	
	
	@PostMapping("/addsmsqueue")
	public  void addSms() {
		System.out.println("checking the function");
		List<notification> list_Of_Notification=service.listAll();
		
		for(int i =0 ; i<list_Of_Notification.size();i++) {
			if(list_Of_Notification.get(i).channel.equals("sms")) {
				  sms s= new sms();
				  notification n= service.get(list_Of_Notification.get(i).id);
				  if(n.mark== true) {
					  continue;
				  } 
				  else if(n.mark==false) {
				  n.mark=true;
				  service.save(n);
				s.content=n.content;
	    		s.sendTO=n.sendTO;
	    		sservice.save(s);
	    		
				  }
				  System.out.println(s.content);
			}
		}
	  
		
	}
	@PostMapping("/addsmsqueue/{id}")
	public  void addgmailbyID(@PathVariable Integer id) {
		System.out.println("checking the function");
		notification n=service.get(id);
	if(n.channel.equals("sms")) {
				  sms s= new sms();
				  if(n.mark== true) {			 
				  } 
				  else if(n.mark==false) {
				  n.mark=true;
				  service.save(n);
				s.content=n.content;
	    		s.sendTO=n.sendTO;
	    		sservice.save(s);
	    		 System.out.println(s.content);
				  }	
			}
	}
	

	//update
	@Override
	@PutMapping("/sms/{id}")
	public ResponseEntity<?> update(@RequestBody sms sms, @PathVariable Integer id) {
	    try {
	    	System.out.println("checking the function");
	    	sms existsms = sservice.get(id);
	        sservice.save(sms);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	//delete
	@Override
	@DeleteMapping("/sms/{id}")
	public void delete(@PathVariable Integer id) {
	    sservice.delete(id);
	    //deleted
	}
	@Override
	public void addSign(sms obj) {
		// TODO Auto-generated method stub
		
	}
	

}
