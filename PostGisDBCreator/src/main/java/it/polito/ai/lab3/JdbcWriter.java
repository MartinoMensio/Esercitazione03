package it.polito.ai.lab3;

import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class JdbcWriter {

	private Connection connection;
	private String createBusStopGeographic = "CREATE TABLE BusStopGeographic ( id varchar(20) not null, name varchar(255) not null, location geography(POINT, 4326), primary key (id))";
	private String dropBusStopGeographic = "DROP TABLE BusStopGeographic";
	private String readBusStop = "SELECT * from BusStop";
	// TODO insert into..

	public JdbcWriter() {
		try {
			Class.forName("org.postgresql.Driver");
			
			String server = null;
			try {
				List<String> lines = Files.readAllLines(Paths.get(JdbcWriter.class.getClassLoader().getResource("db_ip.txt").toURI().toString().substring(6)));
				server = lines.get(0);
			} catch (Exception e) {
				server = "localhost";
			}

			connection = DriverManager.getConnection("jdbc:postgresql://" + server + ":5432/trasporti", "postgres",
					"ai-user-password");
			// set auto commit to false so that transactions are used
			connection.setAutoCommit(false);
			// clear the three tables
			// TODO prepare statements
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// TODO some methods
}
