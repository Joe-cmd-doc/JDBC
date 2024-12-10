import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReadXML {
	private String host;
	private String port;
	private String user;
	private String password;
	private String database;

	// Constructor que llegeix les dades del fitxer XML
	public ReadXML(String config_file) {
		File file = new File(config_file);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			// Obtenim l'element arrel (cb-config)
			Element root = doc.getDocumentElement();

			// Llegeix els valors dins de l'element cb-config
			host = root.getElementsByTagName("host").item(0).getTextContent();
			port = root.getElementsByTagName("port").item(0).getTextContent();
			user = root.getElementsByTagName("user").item(0).getTextContent();
			password = root.getElementsByTagName("password").item(0).getTextContent();
			database = root.getElementsByTagName("database").item(0).getTextContent();

			// Mostra les dades llegides
			System.out.println("Host: " + host);
			System.out.println("Port: " + port);
			System.out.println("User: " + user);
			System.out.println("Password: " + password);
			System.out.println("Database: " + database);

		} catch (Exception e) {
			System.out.println("Error en llegir el fitxer XML: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Mètode per connectar a la base de dades
	public Connection dbConnect() {
		Connection connection = null;

		// URL de connexió
		String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;

		try {
			// Estableix la connexió
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connexió exitosa a la base de dades!");
		} catch (SQLException e) {
			System.out.println("Error al connectar amb la base de dades:");
			e.printStackTrace();
		}

		return connection;
	}
}
