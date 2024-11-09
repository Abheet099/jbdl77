import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableWithoutMaven {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl77",
                "root", "root1234");
        Statement statement = connection.createStatement();
        statement.execute("create table maven_dumm(id int, name varchar(30))");

        int add = MathOperations.add(10, 20);
        System.out.println(add);
    }
}
