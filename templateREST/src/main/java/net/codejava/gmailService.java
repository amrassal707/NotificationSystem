package net.codejava;
 
import java.util.List;
 
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
@Transactional
public class gmailService {
 
    @Autowired
    private gmailRepository repo;
     
    public List<gmail> listAll() {
        return repo.findAll();
    }
     
    public void save(gmail gmail) {
        repo.save(gmail);
    }
     
    public gmail get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}