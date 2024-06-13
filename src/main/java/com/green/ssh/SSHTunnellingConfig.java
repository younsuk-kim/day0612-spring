package com.green.ssh;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Profile("ssh")
@RequiredArgsConstructor
@Configuration
public class SSHTunnellingConfig {
	
	private final ApplicationContext application;
	
	
		//HikariDataSource 빈 수동 생성
		@Primary
		@Bean
		DataSource dataSource(
				DataSourceProperties dataSourceProperties,
				SSHTunnellingProperties sshTunnellingProperties) throws JSchException {
			//System.out.println(">>>sshTunnellingProperties:"+sshTunnellingProperties);
			//ec2-tunnelling
			//Java로 작성된 SSH2 구현 라이브러리
			JSch jsch=new JSch();
			jsch.addIdentity(sshTunnellingProperties.getPrivateKey());
			
			Session session=jsch.getSession(
					sshTunnellingProperties.getUsername(),
					sshTunnellingProperties.getSshHost(),
					sshTunnellingProperties.getSshPort());
			//호스트 키 검사 비활성화
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			
			//localPort를 하나로 고정해서 사용하다보니 수정시 이미 바인딩된 포트라고 떠서
			//랜덤으로 적용했어요
			int lport=new Random().nextInt(999)+33001;
			
			int localPort=session.setPortForwardingL(
					lport, 
					sshTunnellingProperties.getRdsHost(), 
					sshTunnellingProperties.getRdsPort());
			
			System.out.println("localPort:"+localPort);
			//DataSource 정보
			HikariConfig config=hikariConfig();
			config.setJdbcUrl(dataSourceProperties.getUrl().replace("[LOCAL_PORT]", String.valueOf(localPort)));
			config.setDriverClassName(dataSourceProperties.getDriverClassName());
			config.setUsername(dataSourceProperties.getUsername());
			config.setPassword(dataSourceProperties.getPassword());
			return new HikariDataSource(config);
		}
		
		
		@Bean
		@ConfigurationProperties(prefix = "spring.datasource.hikari")
		public HikariConfig hikariConfig() {
			return new HikariConfig();
		}

		@Bean
		SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		
		System.out.println(">>>:"+dataSource);
		
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		//1.datasource
		factoryBean.setDataSource(dataSource);
		//2.Configuration
		factoryBean.setConfiguration(mybatisConfiguration());
		//3.mapper.xml-location patton
		String locationPattern="classpath*:sqlmap/**/*-mapper.xml";
		Resource[] resource=application.getResources(locationPattern); // ... 대신 여러개 집합인 배열로..
		factoryBean.setMapperLocations(resource);
		
		return factoryBean.getObject();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "ssh.mybatis.configuration")
	org.apache.ibatis.session.Configuration mybatisConfiguration() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean
	SqlSessionTemplate sqlSessionTemplate(DataSource dataSource) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory(dataSource));
	}

}
