package org.fzb.multDatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.fzb.multDatasource.mapper")
public class MultDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultDatasourceApplication.class, args);
	}

}
