import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class main {

    static Statement stmt;
    static ResultSet rs;
    static String sqlString;
    static Connection con;

    public static void main(String[] args) {
    connectDB();
        System.out.println();
        System.out.println();
        System.out.println();
        boolean stop = false;
        while(stop!=true){
            System.out.println("Kære bruger hvad vil du?");
            System.out.println(" 1. Liste indhold i motorcycle tabellen");
            System.out.println(" 2. Tilføj et objekt til tabellen");
            System.out.println(" 3. Execute stop");
            Scanner brugerInput = new Scanner(System.in);
            int valg = brugerInput.nextInt();


            if (valg == 1 ) runDB();
            if (valg == 2 ) insertData();
            if (valg == 3 ) stop = true;

        }
    }




    public static void connectDB(){
        System.out.println("Connecting to DB SQL");

        try
        {

            String url = "jdbc:mysql://localhost:3306/test_db";

            //Get a connection to the database for a user named root with password admin
            con = DriverManager.getConnection(url,"root","Tim10ses");

            //Display the URL and connection information
            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);

        }catch (Exception e){
            System.out.println("ERROR");
        }
    }

    public static void runDB(){
        System.out.println("Connecting to DB SQL");

        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlString = "SELECT * FROM motorcycles";
            rs = stmt.executeQuery(sqlString);

            System.out.println("Displaying in terminal:");
            while(rs.next()){
                String col1 = rs.getString("motorcycleName");
                String col2 = rs.getString("engineSize");
                String col3 = rs.getString("motorcycleRegNumb");
                System.out.println("\tMotorcycleName = " + col1 + "\tengineSize = " + col2 + "\tmotorcycleRegNumb = " + col3);

            }
            con.close();
        }
        catch (Exception e){
            System.out.println("Fuck something went wrong");
        }

    }

    public static void insertData(){

        Scanner brugerInput = new Scanner(System.in);
        System.out.println("Angiv Motorcycle navn: ");
        String navn = brugerInput.nextLine();

        System.out.println("Angiv engine size: ");
        String engineSize = brugerInput.nextLine();

        System.out.println("Angiv Reg... Nummer: ");
        String regNumb = brugerInput.nextLine();

        System.out.println("Angiv dit brug (Optional): ");
        String usage = brugerInput.nextLine();

        String insertSQL = "INSERT INTO motorcycles" +
                "(`motorcycleName`,`motorcycleRegNumb`,`engineSize`,`usage`) " +
                "VALUES('"+navn+"','"+engineSize+"','"+regNumb+"','"+usage+"');";

        try {
            stmt = con.createStatement();

            stmt.executeUpdate(insertSQL);
        }
        catch (Exception e){
            System.out.println("Fucki wucki something went wrong");
        }
    }
}
