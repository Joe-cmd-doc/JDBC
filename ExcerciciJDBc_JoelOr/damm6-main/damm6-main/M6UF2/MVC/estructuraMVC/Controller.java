import java.sql.Connection;

public class Controller {

	Connection conn;


		public static void main(String[] args) {
			Controller controller = new Controller(); 
			controller.init();
		}
	
		private void init() {
			ReadXML config = new ReadXML("database.xml");
			Connection conn = config.dbConnect();
			MainView mainView = new MainView();


			athleteDAO athleteDAO = new athleteDAO(conn);
			sportDAO sportDAO = new sportDAO(conn);


			while (true) {
				try {
					// Mostra el menú principal i obté l'opció de l'usuari
					int option = mainView.mainMenu();

					// Comprovem l'opció amb el switch
					switch (option) {
						case 1:
							// Mostrar sports
							mainView.SportsList(sportDAO.getSportsList());
							// Afegir Esport
							sportDAO.add(mainView.SportForm());
							break;

						case 2:
							// Mostrar atletes
							mainView.AthletesList(athleteDAO.getAll());
							// Afegir Atleta
							athleteDAO.add(mainView.AthleteForm());
							break;

						case 3:
							// Buscar Atleta
							athleteDAO.searchAthleteByName(mainView.searchAthleteForm());
							break;

						case 4:
							// Mostrar sports
							mainView.SportsList(sportDAO.getSportsList());
							// Llistar atletes per esport
							athleteDAO.listAthletesBySport(mainView.askSport());
							break;

						case 5:
							// Mostrar sports
							mainView.SportsList(sportDAO.getSportsList());
							break;

						case 6:
							// Sortir i tancar la connexió
							try {
								if (conn != null && !conn.isClosed()) {
									conn.close();
									System.out.println("Connexió tancada correctament.");
								}
							} catch (Exception e) {
								System.out.println("Error al tancar la connexió: " + e.getMessage());
							}
							System.out.println("Adéu!");
							return; // Finalitza el programa

						default:
							// Opció fora de rang
							System.out.println("Opció no vàlida. Si us plau, introdueix un número entre 1 i 6.");
							break;
					}
				} catch (NumberFormatException e) {
					// Si l'usuari introdueix una entrada no numèrica
					System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número.");
				} catch (Exception e) {
					// Altres errors no controlats
					System.out.println("S'ha produït un error: " + e.getMessage());
				}
			}


		}
}



