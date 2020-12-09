package org.fzb.ssproxySharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.fzb.ssproxySharding.mapper")
public class SsproxyShardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsproxyShardingApplication.class, args);
	}

}
