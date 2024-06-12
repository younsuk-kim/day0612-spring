package com.green.ssh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Profile("ssh")
@Component
@ConfigurationProperties("spring.ssh.tunnel")
public class SSHTunnellingProperties {
	
	public SSHTunnellingProperties() {
		System.out.println(">>>>>>>>>>>>SSHTunnellingProperties Bean 생성");
	}
			private String username;
			private String sshHost;
			private int	sshPort;
			private String privateKey;
			private String localPort;
			private String rdsHost;
			private int rdsPort;
			
			
}
