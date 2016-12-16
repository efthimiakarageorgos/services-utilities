/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package  io.qio.qa.lib.SftpHelpers;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MAbstractSftpHelper {
    public String hostname;
    public String username;
    public String password;
    public String remotedirectory;
    public String file_to_transmit;
    public int port;
    public static Session session;
    public static Channel channel;
    public static ChannelSftp channel_sftp;
    
    final static Logger logger = Logger.getRootLogger();
    
    public void sftp(String hostname, String username, String password, String remotedirectory, String file_to_transmit, int port) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.remotedirectory = remotedirectory;
        this.file_to_transmit = file_to_transmit;
        session = null;
        channel = null;
        channel_sftp = null;
    }

    public void sftp(String hostname, String username, String password, int port) {
        this.setHostname(hostname);
        this.setPassword(password);
        this.setUsername(username);
        this.setPort(port);
    }

    public String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public String getRemotedirectory() {
        return remotedirectory;
    }

    public void setRemoteDirectory(String remotedirectory) {
        this.remotedirectory = remotedirectory;
    }

    public String getHostname() {
        return hostname;
    }

    public final void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public String getFile_to_transmit() {
        return file_to_transmit;
    }

    public void setFile_to_transmit(String file_to_transmit) {
        this.file_to_transmit = file_to_transmit;
    }

    public int getPort() {
        return port;
    }

    public final void setPort(int port) {
        this.port = port;
    }

    public static void connectToServer(String hostname, String username, String password, int port) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, hostname, port);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            logger.info("Session created.");
        } catch (Exception e) {
        	logger.info("Unable to establish SFTP Session");
        }
    }
    
    public static void DISCONNECT() {

        try {
            channel_sftp.quit();
            logger.info("Disconnected from SFTP Server");
        } catch (Exception e) {
        	logger.info("Unable to disconnect from FTP Server." + e.getMessage());
        }
    }


    public static void LS(String file_path) {
        try {
            @SuppressWarnings("rawtypes")
            Vector list;
            list = channel_sftp.ls(file_path);
            for (int i = 0; i < list.size(); i++) {
            	logger.info(list.get(i).toString());
            }
        } catch (Exception e) {
        	logger.info("Unable to List file in path -" + file_path);
        }
    }
    public void openChannel() {
        try {
            channel = session.openChannel("sftp");
            channel.connect();
            channel_sftp = (ChannelSftp) channel;
        } catch (Exception e) {
        	logger.info("Unable to open SFTP channel");
        }
    }
    public void uploadFile(String file_path, boolean isOerwrite) {
        try {
            File file = new File(file_path);
            logger.info("Uploading file " + file_path + ".(" + file.getName() + ")");
            if (isOerwrite) {
                channel_sftp.put(new FileInputStream(file), file.getName(), ChannelSftp.OVERWRITE);
            } else {
                channel_sftp.put(new FileInputStream(file), file.getName());
            }

        } catch (FileNotFoundException e) {
        	logger.info("Error while uploading file to SFTP server." + e.getMessage());
        } catch (SftpException e) {
        	logger.info("Error while uploading file to SFTP server." + e.getMessage());
        }
    }

    public void chageDirectory(String path) {

    	logger.info("Changing to FTP remote dir: " + path);
        try {
            channel_sftp.cd(path);
        } catch (SftpException e) {
        	logger.info("Unable to change the directory." + path);
        }
    }

    
	
}