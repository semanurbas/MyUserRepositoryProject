package springBoot.myUserServiceProject.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import springBoot.myUserServiceProject.model.User;


@Service
public interface UserService {

	User findByEmail(String email);
	
	User findByUsername(String username);
	
	User findById(long id);
	
	List<User> getAll();
	
	List<User> getAll(int pageNo,int pageSize);
	
	List<User> getAllSorted();
	




}
