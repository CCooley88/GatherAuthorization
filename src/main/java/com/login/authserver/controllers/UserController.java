package com.login.authserver.controllers;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.login.authserver.model.AuthUser;
import com.login.authserver.repos.UserRepository;
import com.login.authserver.services.JwtUtil;

@CrossOrigin
@RestController
public class UserController {
	
	JSONObject payload;
	
	// Make the repository available to the class via a bean/autowire
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user/{id}")
	Optional<AuthUser> one(@PathVariable Integer id) {
//		void one(@PathVariable Integer id) {
//		System.out.println("In get user");
		return userRepository.findById(id);
	}
	
	@GetMapping("/test")
	void test() {
		System.out.println("In test");
	}
		
	@GetMapping("/validate")
	void validate(@CookieValue(value = "UserToken", defaultValue = "Canada") String token) {
//	void validate(@RequestHeader("Cookie") String token) {
		System.out.println("In token validation, cookie:" + "\n" + token);
//	    String token = new JwtUtil(payload).toString();
//        token = token.replaceAll("\\.", "X");
        //verify and use
        JwtUtil incomingToken;
        try {
            incomingToken = new JwtUtil(token);
            if (incomingToken.isValid()) {
               System.out.println("Token is Valid");
            }
        } catch (NoSuchAlgorithmException ex) {
           System.out.println("No Such Algo Exception Thrown");
        }		
	}	
	
	@GetMapping("/name")
	void name(@RequestHeader("username") String name) {
		System.out.println("Name cookie:" + name);	
	}
	
	@GetMapping("/login/{userName}/{password}")
	String one(HttpServletResponse response, @PathVariable String userName, @PathVariable String password) {
//		Attempt to get the user from the database
		List<AuthUser> userList = userRepository.findByUserName(userName);
		if(userList.size() > 0 && userList.get(0).getPassword().equalsIgnoreCase(password)) {
			AuthUser user = userList.get(0);
//			Add to the response cookie
			LocalDateTime ldt = LocalDateTime.now().plusDays(90);;
//			payload = new JSONObject("{\"sub\":\"1234\",\"aud\":[\"admin\"],"
			payload = new JSONObject("{\"sub\":" + user.getEmail() + ",\"aud\":[\"admin\"],"
		            + "\"exp\":" + ldt.toEpochSecond(ZoneOffset.UTC) + "}");
			String token = new JwtUtil(payload).toString();
			Cookie authCookie = new Cookie("UserToken",token);
			authCookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
			authCookie.setSecure(true);
			authCookie.setPath("/");
			response.addCookie(authCookie);		
			
			return "Creds are correct, cookie added";
		}
		else {
			return "Creds are invalid";
			}
	}
}