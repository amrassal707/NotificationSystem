package net.codejava;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class gmail {
	public Integer id;
	public String sendTO;
	public String content;
	
	public gmail(Integer id, String sendTO, String content) {
		super();
		this.id = id;
		this.sendTO = sendTO;
		this.content = content;
	}
	public gmail() {
		
	}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getsendTO() {
		return sendTO;
	}
	public void setsendTO(String sendTO) {
		this.sendTO = sendTO;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}