package net.codejava;
 
import java.util.List;
 
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
@Transactional
public class smsService {
 
    @Autowired
    private smsRepository repo;
     
    public List<sms> listAll() {
        return repo.findAll();
    }
     
    public void save(sms sms) {
        repo.save(sms);
    }
     
    public sms get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}