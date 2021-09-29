package springBoot.myUserServiceProject.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import springBoot.myUserServiceProject.business.abstracts.UserService;
import springBoot.myUserServiceProject.model.User;
import springBoot.myUserServiceProject.repository.UserRepository;

@Service
public class UserManager implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	public UserManager(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findByEmail(String email) {
		
		return this.userRepository.findByEmail(email);
	}
	

	@Override
	public User findById(long id) {
		
		return this.userRepository.findById(id).get();
	}


	@Override
	public User findByUsername(String username) {
		
		return this.userRepository.findByUsername(username).get();
	}

	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	@Override
	public List<User> getAll(int pageNo, int pageSize) {
		Pageable pageable= PageRequest.of(pageNo-1, pageSize);
		return this.userRepository.findAll(pageable).getContent();
	}

	@Override
	public List<User> getAllSorted() {
		
		Sort sort= Sort.by(Sort.Direction.DESC,"productName");
		return this.userRepository.findAll(sort);
	}




	


}

