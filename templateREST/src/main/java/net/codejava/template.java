package net.codejava;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class template {
	

	public Integer id;
	public String blueprint;
	public String subject;
	
	public template() {
    }
 
    public template(Integer id, String blueprint) {
        this.id = id;
        this.blueprint = blueprint;
       
    }
    
 
    public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
    

	public String getblueprint() {
		return blueprint;
	}

	public void setblueprint(String blueprint) {
		this.blueprint = blueprint;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
 
    // other getters and setters...
}