
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author MAYURA MANAWADU
 */
public class Elections {

    public static void main(String[] args) {

        System.out.println("Press 1 to INSERT Data");
        System.out.println("Press 2 to DELETE Data");
        System.out.println("Press 3 to UPDATE Data");
        System.out.println("Press 4 to Display the table");
        System.out.println("Press 5 to exit\n");

        System.out.println("Select the desired option:");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
        }

        switch (option) {
            case 1:
                INSERT();
                break;
            case 2:
                DELETE();
                break;
            case 3:
                UPDATE();
                break;
            case 4:
                DISPLAY();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid Input");

                break;

        }
    }

    static void INSERT() {

        System.out.println("\nINSERT DATA");

        String url = "jdbc:mysql://localhost:3380/elections?useSSL=true";
        String user = "root";
        String pass = "mayura";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter the Electorate");
        String Electorate = scanner.nextLine();
        System.out.println("Enter the Party");
        String Party = scanner.nextLine();
        System.out.println("Enter the number of Votes");
        int Votes = scanner.nextInt();

        String v1 = Integer.toString(Votes);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO results (first_name, last_name,electorate,party,votes) VALUES (?,?,?,?,?)");

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, Electorate);
            statement.setString(4, Party);
            statement.setString(5, v1);

            statement.executeUpdate();
            System.out.println("done");
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        DISPLAY();
        System.out.println("\n");
        String[] args = null;
        main(args);
    }

    static void DELETE() {

        String url = "jdbc:mysql://localhost:3380/elections?useSSL=true";
        String user = "root";
        String pass = "mayura";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the candidate you want to delete:");
        int candiID = scanner.nextInt();
        String candIDString = Integer.toString(candiID);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM results WHERE candidateID=?");
            statement.setString(1, candIDString);

            statement.executeUpdate();
            System.out.println("done\n");
            System.out.println("done");
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        DISPLAY();
        System.out.println("\n");
        String[] args = null;
        main(args);

    }

    static void UPDATE() {

        String url = "jdbc:mysql://localhost:3380/elections?useSSL=true";
        String user = "root";
        String pass = "mayura";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the candidate you want to Update:");
        String candiID = scanner.nextLine();

        System.out.println("Enter new value for First Name:");
        String fName = scanner.nextLine();
        System.out.println("Enter new Value for Last Name:");
        String lName = scanner.nextLine();
        System.out.println("Enter the new Electorate");
        String electorate = scanner.nextLine();
        System.out.println("Enter the new Party");
        String party = scanner.nextLine();
        System.out.println("Enter the new number of Votes");
        int votes = scanner.nextInt();

        String candIDString = candiID;
        String voteString = Integer.toString(votes);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = conn.prepareStatement("UPDATE results SET first_name=?, last_name=?, electorate=?, party=?, " +
                    "votes=?  WHERE candidateID=?");
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, electorate);
            statement.setString(4, party);
            statement.setString(5, voteString);
            statement.setString(6, candIDString);
            statement.executeUpdate();
            System.out.println("done");
            System.out.println("done");
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        DISPLAY();
        System.out.println("\n");
        String[] args = null;
        main(args);
    }

    static void DISPLAY() {

        String url = "jdbc:mysql://localhost:3380/elections?useSSL=true";
        String user = "root";
        String pass = "mayura";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM results");
            ResultSet results = statement.executeQuery();

            System.out.println("  Candidate ID \t First Name \t Last Name \t Electorate \t Party \t\t   Votes");

            while (results.next()) {
                int id = results.getInt(1);
                String candID = results.getString("candidateID");
                String firstname = results.getString("first_name");
                String lastname = results.getString("last_name");
                String electorate = results.getString("electorate");
                String party = results.getString("party");
                String votes = results.getString("votes");
                System.out.println("\t" + candID + "\t   " + firstname + "\t   " + lastname + "\t   " + electorate + "\t   " + party + "\t   " + votes);
            }
            System.out.println("done");
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }



    }
}
