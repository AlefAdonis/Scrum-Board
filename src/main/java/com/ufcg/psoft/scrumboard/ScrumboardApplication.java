package com.ufcg.psoft.scrumboard;

import com.ufcg.psoft.scrumboard.models.entities.users.User;
import com.ufcg.psoft.scrumboard.repository.UserRepository;
import com.ufcg.psoft.scrumboard.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ufcg.psoft.scrumboard.*")
public class ScrumboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrumboardApplication.class, args);
	}

	private void carregaUsers(){
		User usuario1 = new User("alef adonis","a", "a@gmail.com" );
		UserRepository ur = new UserRepository();
		ur.addUser(usuario1);
	}

}
