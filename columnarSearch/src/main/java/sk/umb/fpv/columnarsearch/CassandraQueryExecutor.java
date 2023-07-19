/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

/**
 *
 * @author tomraffaj
 */

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class CassandraQueryExecutor {
    private CqlSession session;
    
    public CassandraQueryExecutor(String contactPoint, int port, String username, String password, String keyspace) {
        InetSocketAddress addr = new InetSocketAddress(contactPoint, port);
        session = CqlSession.builder()
                .addContactPoint(addr)
                .withAuthCredentials(username, password)
                .withKeyspace(keyspace)
                .build();
    }
        public void closeConnection() {
        session.close();
    }
        
    public ResultSet executeQuery(String query) {
        return session.execute(query);
    }

}
