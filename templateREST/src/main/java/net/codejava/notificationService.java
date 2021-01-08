package net.codejava;
 
import java.util.List;
 
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
@Transactional
public class notificationService {
 
    @Autowired
    private notificationRepository repo;
     
    public List<notification> listAll() {
        return repo.findAll();
    }
     
    public void save(notification notification) {
        repo.save(notification);
    }
     
    public notification get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}