import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    static {
        try {
            // Load H2 driver explicitly
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ H2 Driver not found: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                initializeDatabase();
                System.out.println("✅ Database connected successfully!");
            } catch (SQLException e) {
                System.err.println("❌ Database connection failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    private static void initializeDatabase() {
        try {
            // Create a simple schema if file doesn't exist
            String schemaSql = 
                "CREATE TABLE IF NOT EXISTS customers (" +
                "    id VARCHAR(20) PRIMARY KEY," +
                "    name VARCHAR(100) NOT NULL," +
                "    address VARCHAR(200)," +
                "    contact VARCHAR(50)," +
                "    customer_type VARCHAR(20) NOT NULL" +
                ");" +
                "CREATE TABLE IF NOT EXISTS accounts (" +
                "    account_number VARCHAR(20) PRIMARY KEY," +
                "    customer_id VARCHAR(20) NOT NULL," +
                "    account_type VARCHAR(20) NOT NULL," +
                "    balance DECIMAL(15,2) DEFAULT 0.00," +
                "    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "    FOREIGN KEY (customer_id) REFERENCES customers(id)" +
                ");" +
                "CREATE TABLE IF NOT EXISTS transactions (" +
                "    transaction_id VARCHAR(30) PRIMARY KEY," +
                "    account_number VARCHAR(20) NOT NULL," +
                "    transaction_type VARCHAR(20) NOT NULL," +
                "    amount DECIMAL(15,2) NOT NULL," +
                "    balance_after DECIMAL(15,2) NOT NULL," +
                "    description VARCHAR(200)," +
                "    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "    FOREIGN KEY (account_number) REFERENCES accounts(account_number)" +
                ");" +
                "INSERT INTO customers (id, name, address, contact, customer_type) VALUES " +
                "('CUST001', 'John Smith', 'Gaborone', '+2671234567', 'INDIVIDUAL')," +
                "('CUST002', 'ABC Enterprises', 'Francistown', '+2677654321', 'COMPANY');" +
                "INSERT INTO accounts (account_number, customer_id, account_type, balance) VALUES " +
                "('SAV001', 'CUST001', 'SAVINGS', 1500.75)," +
                "('INV001', 'CUST001', 'INVESTMENT', 5000.00)," +
                "('CHQ001', 'CUST002', 'CHEQUE', 25000.50);" +
                "INSERT INTO transactions (transaction_id, account_number, transaction_type, amount, balance_after, description) VALUES " +
                "('TXN001', 'SAV001', 'DEPOSIT', 1000.00, 1000.00, 'Initial deposit')," +
                "('TXN002', 'SAV001', 'DEPOSIT', 500.75, 1500.75, 'Additional deposit');";
            
            Statement stmt = connection.createStatement();
            String[] statements = schemaSql.split(";");
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    stmt.execute(statement.trim());
                }
            }
            
            System.out.println("✅ Database schema initialized!");
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("✅ Database connection closed.");
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }
}