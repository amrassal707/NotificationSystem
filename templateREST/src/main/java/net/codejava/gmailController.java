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
public class gmailController implements Controllers<gmail> {
	@Autowired 
	private notificationService service;
	@Autowired
	private gmailService gservice;
	
	
	@Override
	@GetMapping("/gmails") // localhost/notifications
	public List<gmail> list() {
		return gservice.listAll();
	}
	
	@Override
	@GetMapping("/gmail/{id}")
	public ResponseEntity<gmail> get(@PathVariable Integer id) {
	    try {
	    	System.out.println("checking the function");
	    	gmail gmail=gservice.get(id);
	   
	    	 return new ResponseEntity<gmail>(gmail, HttpStatus.OK);
	    }
	    catch (Exception e) {
	    	return new ResponseEntity<gmail>(HttpStatus.NOT_FOUND);
		}
	       
	       
	}
	

	@PostMapping("/addgmailqueue")
	public  void addgmail() {
		System.out.println("checking the function");
		List<notification> list_Of_Notification=service.listAll();
		
		for(int i =0 ; i<list_Of_Notification.size();i++) {
			if(list_Of_Notification.get(i).channel.equals("gmail")) {
				  gmail g= new gmail();
				  notification n= service.get(list_Of_Notification.get(i).id);
				  if(n.mark== true) {
					  continue;
				  } 
				  else if(n.mark==false) {
				  n.mark=true;
				  service.save(n);
				g.content=n.content;
	    		g.sendTO=n.sendTO;
	    		gservice.save(g);
	    		
				  }
				  System.out.println(g.content);
	    		
			}
		}
		
	  
		
	}
	
	
	

	@PostMapping("/addgmailqueue/{id}")
	public  void addgmailbyID(@PathVariable Integer id) {
		System.out.println("checking the function");
		notification n=service.get(id);
	if(n.channel.equals("gmail")) {
				  gmail g= new gmail();
				  if(n.mark== true) {			 
				  } 
				  else if(n.mark==false) {
				  n.mark=true;
				  service.save(n);
				g.content=n.content;
	    		g.sendTO=n.sendTO;
	    		gservice.save(g);
	    		 System.out.println(g.content);
				  }	
			}
	}
	
	
	
	//update
	@Override
	@PutMapping("/gmail/{id}")
	public ResponseEntity<?> update(@RequestBody gmail gmail, @PathVariable Integer id) {
	    try {
	    	System.out.println("checking the function");
	    	gmail existgmail = gservice.get(id);
	        gservice.save(gmail);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	//delete
	@Override
	@DeleteMapping("/gmail/{id}")
	public void delete(@PathVariable Integer id) {
	    gservice.delete(id);
	    //deleted
	}

	@Override
	public void addSign(gmail obj) {
		// TODO Auto-generated method stub
		
	}

	

}
