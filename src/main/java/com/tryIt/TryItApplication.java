package com.tryIt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value= {"com.tryIt.mapper"})
public class TryItApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryItApplication.class, args);
	}

}
