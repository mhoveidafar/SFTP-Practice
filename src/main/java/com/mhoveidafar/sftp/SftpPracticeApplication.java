package com.mhoveidafar.sftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@SpringBootApplication
public class SftpPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SftpPracticeApplication.class, args);
		
		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession("AnanticsFTP", "192.168.1.45", 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword("Superior@2019");
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.get("/home/AnanticsFTP/ftptest.txt", "C:/Users/Mohamad/Desktop/uploads/ftptest.txt");
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}

	}

}
