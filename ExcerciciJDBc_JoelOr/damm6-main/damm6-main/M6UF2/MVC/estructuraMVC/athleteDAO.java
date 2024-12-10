import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class athleteDAO implements DAO<athlete> {
    private Connection conn;

    public athleteDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void add(athlete item) {
        if (conn == null) return;

        String query = "INSERT INTO athlete (name, sport_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            // Establecer los parámetros en el PreparedStatement
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getSportId());

            // Ejecutar la consulta
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Manejar la excepción
            e.printStackTrace();
        }
    }

        public List<athlete> getAll () {
        List<athlete> athletes = new ArrayList<>();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM athlete");
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " - " + rs.getString("id"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return athletes;
        }


    public void searchAthleteByName(String name) {
        // SQL con JOIN entre 'athlete' y 'sport' para obtener el nombre del deporte
        String query = "SELECT a.name, a.id AS athlete_id, s.name AS sport_name " +
                "FROM athlete a " +
                "JOIN sport s ON a.sport_id = s.id " +
                "WHERE LOWER(a.name) LIKE LOWER(?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                String athleteName = rs.getString("name");
                int athleteId = rs.getInt("athlete_id");
                String sportName = rs.getString("sport_name");


                System.out.println("Athlete: " + athleteName + " - Id: " + athleteId + " - Sport: " + sportName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void listAthletesBySport(int sportId) {
        String query = "SELECT id, name, sport_name FROM list_athletes_by_sport(?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, sportId);
            ResultSet rs = stmt.executeQuery();



            System.out.println("Athletes in the selected sport:");
            while (rs.next()) {
                int athleteId = rs.getInt("id");
                String athleteName = rs.getString("name");
                String sportName = rs.getString("sport_name");

                // Mostrar los atletas del deporte seleccionado
                System.out.println("Athlete ID: " + athleteId + " - Name: " + athleteName + " - Sport: " + sportName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
