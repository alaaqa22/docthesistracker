package dtt.dataAccess.utilities;

import java.sql.Connection;
import java.util.List;

import dtt.dataAccess.exceptions.DBConnectionFailedException;

import java.sql.Connection;
import java.util.List;

/**
 * Postgres Connection pool implementation as Singleton
 * 
 * @author Stefan Witka
 *
 */
public class ConnectionPool {
	private static final ConnectionPool connectionPool = new ConnectionPool();
	private List<Connection> available; //List of available connections
	private List<Connection> busy; //List of in use connections
	
	/**
	 * Private Constructor per singleton pattern
	 */
	private ConnectionPool() {
		
	}
	
	/**
	 * Get a connection from the connection pool.
	 * 
	 * <p> Returns a Connection from the list of available connections. 
	 * If available connections fall below TODO then additional temporary connections are created, up to a maximum of TODO connections.
	 * 
	 * @return The database connection
	 * @throws DBConnectionFailedException if there is an error while getting a connection from the pool
	 */
	public synchronized Connection getConnection() throws DBConnectionFailedException {
		return null;
	}

	/**
	 * Release a connection back to the connection pool.
	 * 
	 * <p> Releases a connection and moves it back to the list of available connections, or removes them if they were temporary.
	 * 
	 * @param connection The connection to be released
	 * @throws DBConnectionFailedException if there is an error while releasing the connection
	 */
	public void releaseConnection(Connection connection) throws DBConnectionFailedException {
		
	}

	/**
	 * Create a new connection and add it to the connection pool.
	 * 
	 * @throws DBConnectionFailedException if there is an error while creating a connection
	 */
	public void createConnection() throws DBConnectionFailedException {
		
	}

	/**
	 * Get the singleton instance of the connection pool.
	 * 
	 * @return The connection pool instance
	 */
	public static ConnectionPool getInstance() {
		return connectionPool;	
	}
}
