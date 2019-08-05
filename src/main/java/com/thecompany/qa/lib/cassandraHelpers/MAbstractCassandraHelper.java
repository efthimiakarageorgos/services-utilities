/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */

package com.thecompany.qa.lib.cassandraHelpers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MAbstractCassandraHelper {
    public Cluster cluster;
    public Session session;
    public String serverAddress;
    public String keyspaceName;
    public String userName;
    public String password;
    public LinkedHashMap<String, String> FILTER;
    public ResultSet RESULTS;
    public int result_count;

    final static Logger logger = Logger.getRootLogger();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getResult_count() {
        return result_count;
    }

    public void setResult_count(int result_count) {
        this.result_count = result_count;
    }

    public Session getSession() {
        return session;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    public void setKeyspaceName(String keyspaceName) {
        this.keyspaceName = keyspaceName;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void CONNECT() {
        try {
            System.out.println("Connecting to cassandra server " + this.serverAddress);
            cluster = Cluster.builder().addContactPoint(this.serverAddress).withCredentials(this.userName, this.password).build();
            session = cluster.connect(this.keyspaceName);

            System.out.println("Connected to keyspace " + this.keyspaceName);
        } catch (Exception e) {
            System.out.println("Unable to connect to cassandra server." + this.serverAddress + " " + this.keyspaceName);
        }
    }

    public void CONNECT(String serverAddress, String keySpace, String userName, String password) {
        this.serverAddress = serverAddress;
        this.keyspaceName = keySpace;
        this.userName = userName;
        this.password = password;
        CONNECT();
    }

    public void DISCONNECT() {
        try {
            cluster.close();
            System.out.println("Disconnected from the cassandra server");
        } catch (Exception e) {
            System.out.println("Unable to disconnect the session" + e.getMessage());
        }
    }

    public ResultSet QUERY(String query) {
        try {
            //System.out.println("Getting results : " + query);
            logger.info("Getting results : " + query);
            ResultSet results = session.execute(query);
            this.RESULTS = results;
            return results;
        } catch (Exception e) {
            logger.info("Unable to fetch results. " + query);
            return null;
        }
    }

    public void EXECUTE(String command) {
        try {
            logger.info("Executing : " + command);
            session.execute(command);
        } catch (Exception e) {
            logger.info("Unable to execute " + command);
        }
    }

    public int getCount(String query) {
        try {
            ResultSet results = session.execute(query);
            this.RESULTS = results;
            return results.all().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String findOne(String query,String columnName) {
        try {

            ResultSet results = session.execute(query);
            return results.one().getString(0);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void APPLY() {
        // TODO Auto-generated method stub

    }

    public void RESULT_COUNT(int i) {
        // TODO Auto-generated method stub

    }

    public void LS() {
        int count = 0;
        for (Row row : this.RESULTS) {
            row.getColumnDefinitions();
            logger.info(row.getString("asset_id"));
            ++count;
        }
        logger.info("Retrieved " + count + " rows");
        this.setResult_count(count);
    }

    public void executeCQLInCassandra(ArrayList<String> commandList, String cassandraDbServerAddress, String cassandraDbKeySpace, String cassandraUsername, String cassandraPassword) {
        CONNECT (cassandraDbServerAddress, cassandraDbKeySpace, cassandraUsername, cassandraPassword);
        for (String command : commandList) {
            this.EXECUTE(command);
        }
        DISCONNECT();
    }
}
