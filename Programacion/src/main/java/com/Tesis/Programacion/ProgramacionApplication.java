package com.Tesis.Programacion;

import com.Tesis.Programacion.Service.JwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProgramacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramacionApplication.class, args);
	}

}
