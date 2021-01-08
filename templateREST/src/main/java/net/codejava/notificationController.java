package net.codejava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.ldap.Control;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class notificationController implements Controllers<notification> {
@Autowired 
private notificationService service;
@Autowired
private templateService tService;
@Autowired
private smsService sservice;
@Autowired
private gmailService gservice;

//read
@Override
@GetMapping("/notifications") // localhost/notifications
public List<notification> list() {
	return service.listAll();
}

//sms  table     gmail table 

// notification
// dear username you have booked ??? 
// dear username your account ??? has been registered
@Override
@GetMapping("/notifications/{id}")
public ResponseEntity<notification> get(@PathVariable Integer id) {
    try {
    	System.out.println("checking the function");
    	notification notification=service.get(id);
    //String s=notification.content;
    	//notification exist= notification;
    //	int y=notification.notification.indexOf("username",5,13);
    	//notification.notification.replace(str, "rawan");
        //System.out.println(notification.notification);
   	//System.out.println(notification.notification);
    	//name1="hello";
    	///notification.notification;
    	//Mailer.send("amrassal707@gmail.com", "23635587", notification.getChannel(), "hello", notification.getnotification());
    	 return new ResponseEntity<notification>(notification, HttpStatus.OK);
    }
    catch (Exception e) {
    	return new ResponseEntity<notification>(HttpStatus.NOT_FOUND);
	}
       
       
}
//create

@PostMapping("/addnotification/{id}/{username}/{item}/{channel}/{sendTO}")
public  void addNotification(@RequestBody notification n,@PathVariable Integer id,@PathVariable String username,@PathVariable String item,@PathVariable String channel,@PathVariable String sendTO) {
	System.out.println("checking the function");
	template template=tService.get(id); // dear username your item is booked
	 String s=template.blueprint;
   String w=s.replace("username", username);
   String y=w.replace("item", item);
	n.content=y;
    n.channel=channel;
    n.sendTO=sendTO;
    n.subject=template.subject;
    service.save(n);
}




//update
@Override
@PutMapping("/notifications/{id}")
public ResponseEntity<?> update(@RequestBody notification notification, @PathVariable Integer id) {
    try {
    	System.out.println("checking the function");
        notification existnotification = service.get(id);
        service.save(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }      
}






@Override
//delete
@DeleteMapping("/notifications/{id}")
public void delete(@PathVariable Integer id) {
    service.delete(id);
    //deleted
}

@Override
public void addSign(notification obj) {
	// TODO Auto-generated method stub
	
}

}