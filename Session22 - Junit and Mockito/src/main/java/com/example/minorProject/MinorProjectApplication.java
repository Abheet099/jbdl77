package com.example.minorProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.minorProject.enums.AccountStatus;
import com.example.minorProject.models.Admin;
import com.example.minorProject.models.MyUser;
import com.example.minorProject.models.Student;
import com.example.minorProject.repository.AdminRepositoryInterf;
import com.example.minorProject.repository.MyUserRepositoryInterf;
import com.example.minorProject.repository.StudentRepositoryInterf;

@SpringBootApplication
public class MinorProjectApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${users.authority.admin}")
	String adminAuthority;

	@Value("${users.authority.student}")
	String studentAuthority;

	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;

	@Autowired
	AdminRepositoryInterf adminRepositoryInterf;

	@Autowired
	StudentRepositoryInterf studentRepositoryInterf;

	public static void main(String[] args) {
		SpringApplication.run(MinorProjectApplication.class, args);
	}

	// Runs after application startup
	@Override
	public void run(String... args) {
		// Add default users
//		MyUser user1 = MyUser.builder()
//				.username("ankit")
//				.password(passwordEncoder.encode("ankit123"))
//				.authority(adminAuthority)
//				.build();
//		user1 = myUserRepositoryInterf.save(user1);
//
//		Admin admin = Admin.builder()
//				.age(45).name("Ankit Goyal").myUser(user1).build();
//
//		adminRepositoryInterf.save(admin);
//
//		MyUser user2 = MyUser.builder()
//				.username("kishan")
//				.password(passwordEncoder.encode("kishan123"))
//				.authority(studentAuthority)
//				.build();
//		user2 = myUserRepositoryInterf.save(user2);
//
//		Student student = Student.builder()
//				.name("Kishan Shah").email("kishan@gmail.com")
//				.accountStatus(AccountStatus.ACTIVE)
//				.phone("9876543557").myUser(user2).build();
//
//		studentRepositoryInterf.save(student);
	}

}
