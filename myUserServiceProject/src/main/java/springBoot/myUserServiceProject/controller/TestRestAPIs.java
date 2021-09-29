package springBoot.myUserServiceProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import springBoot.myUserServiceProject.business.abstracts.UserService;
import springBoot.myUserServiceProject.model.User;
import springBoot.myUserServiceProject.repository.RoleRepository;
import springBoot.myUserServiceProject.repository.UserRepository;
import springBoot.myUserServiceProject.security.jwt.JwtProvider;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestAPIs {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
	@Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
	
    @GetMapping("/api/test/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
    	log.debug("User contents is opening");
        return ">>> User Contents!";
    }

    @GetMapping("/api/test/pm")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String projectManagementAccess() {
    	log.debug("Project Manager contents is opening");
        return ">>> Project Manager Contents";
    }

    @GetMapping("/api/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
    	log.debug("admin contents is opening");
        return ">>> Admin Contents";
    }
    
    @GetMapping("/api/auth/getAll")
	public List<User> getAll(){
		log.debug("users are listed");
    	return this.userService.getAll();		
	}
    
    @GetMapping("/api/test/admin/getAllPageable")
    @PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllPageable(@RequestParam  int pageNo,@RequestParam int pageSize){
    	log.debug("users are listed");
    	return this.userService.getAll(pageNo,pageSize);		
	}
    
    @GetMapping("/api/test/admin/getAllSorted")
    @PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllSorted(){
    	log.debug("users are listed in order");
    	return this.userService.getAllSorted();		
	}
    
    @GetMapping("/api/test/admin/findByEmail")
    @PreAuthorize("hasRole('ADMIN')")
	public User findByEmail(@RequestParam String email){
    	log.debug("User found with mail");
    	return this.userService.findByEmail(email);		
	}
    
    @GetMapping("/api/test/admin/findByUsername")
    @PreAuthorize("hasRole('ADMIN')")
	public User findByUsername(@RequestParam String username){
    	log.debug("User found with username");
    	return this.userService.findByUsername(username);		
	}
    
    @GetMapping("/api/test/admin/findById")
    @PreAuthorize("hasRole('ADMIN')")
	public User findById(@RequestParam long id){
    	log.debug("User found with id");
    	return this.userService.findById(id);		
	}
    
  
	
}