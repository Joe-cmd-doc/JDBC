import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class sportDAO implements DAO<sport> {
    private Connection conn;

    public sportDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void add(sport item) {

        if (conn == null) return;

        try (Statement statement = conn.createStatement()) {
            String query = "INSERT INTO sport (name) VALUES ('" + item.getName() + "')";
            statement.executeUpdate(query);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public List<sport> getAll() {
        List <sport> sports = new ArrayList<>();
        // TODO Auto-generated method stub
        if (conn == null) return null;
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sport");
            while (resultSet.next()) {
                sports.add(new sport(resultSet.getString("name"), resultSet.getInt("id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   return sports; }

    public List<sport> getSportsList() {
        List<sport> sports = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM list_sports()")) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                sports.add(new sport(name, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sports;
    }



}







