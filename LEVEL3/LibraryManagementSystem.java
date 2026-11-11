
package LEVEL3;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class LibraryManagementSystem {
    static java.util.Scanner sc = new java.util.Scanner(System.in);

    static final String URL = "jdbc:mysql://localhost:3306/library_management";

    static final String USER = "root";

    static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Database Connection Method
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Register User");
            System.out.println("4. View All Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Transactions");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    registerUser();
                    break;

                case 4:
                    viewUsers();
                    break;

                case 5:
                    borrowBook();
                    break;

                case 6:
                    returnBook();
                    break;

                case 7:
                    viewTransactions();
                    break;

                case 8:
                    System.out.println("Thank you for using Library Management System!");

                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static void addBook() {

        try {

            Connection con = getConnection();

            String query = "INSERT INTO books (title, author, category) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);

            ps.executeUpdate();

            System.out.println("Book added successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Error adding book!");
            e.printStackTrace();
        }
    }

    static void viewBooks() {

        try {

            Connection con = getConnection();

            String query = "SELECT * FROM books";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== AVAILABLE BOOKS =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Book ID: " + rs.getInt("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("Available: " + rs.getBoolean("available"));

                System.out.println("----------------------");
            }

            if (!found) {
                System.out.println("No books found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Error viewing books!");
            e.printStackTrace();
        }
    }

    static void registerUser() {
        try {
            Connection con = getConnection();

            String query = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            System.out.print("Enter User Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            ps.executeUpdate();

            System.out.println("User registered successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error registering user!");
            e.printStackTrace();
        }
    }

    static void viewUsers() {
        try {
            Connection con = getConnection();

            String query = "SELECT * FROM users";

            PreparedStatement ps = con.prepareStatement(query);

            java.sql.ResultSet rs = ps.executeQuery();

            System.out.println("\n===== REGISTERED USERS =====");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));

                System.out.println("----------------------");
            }

            if (!found) {
                System.out.println("No users found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error viewing users!");
            e.printStackTrace();
        }
    }

    static void borrowBook() {

        try (Connection con = getConnection()) {

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();

            con.setAutoCommit(false);

            try {
                String checkQuery = "SELECT available FROM books " +
                        "WHERE book_id = ? FOR UPDATE";

                PreparedStatement checkPs = con.prepareStatement(checkQuery);

                checkPs.setInt(1, bookId);

                java.sql.ResultSet rs = checkPs.executeQuery();

                if (!rs.next()) {
                    System.out.println("Book not found!");
                    con.rollback();
                    return;
                }

                if (!rs.getBoolean("available")) {
                    System.out.println("Book is already borrowed!");
                    con.rollback();
                    return;
                }

                rs.close();
                checkPs.close();

                String userQuery = "SELECT user_id FROM users WHERE user_id = ?";

                PreparedStatement userPs = con.prepareStatement(userQuery);

                userPs.setInt(1, userId);

                java.sql.ResultSet userRs = userPs.executeQuery();

                if (!userRs.next()) {
                    System.out.println("User not found!");
                    userRs.close();
                    userPs.close();
                    con.rollback();
                    return;
                }

                userRs.close();
                userPs.close();

                String insertQuery = "INSERT INTO transactions " +
                        "(book_id, user_id, borrow_date, status) " +
                        "VALUES (?, ?, CURDATE(), 'BORROWED')";

                try (PreparedStatement insertPs = con.prepareStatement(insertQuery)) {

                    insertPs.setInt(1, bookId);
                    insertPs.setInt(2, userId);
                    insertPs.executeUpdate();
                }

                String updateQuery = "UPDATE books SET available = false " +
                        "WHERE book_id = ?";

                try (PreparedStatement updatePs = con.prepareStatement(updateQuery)) {

                    updatePs.setInt(1, bookId);
                    updatePs.executeUpdate();
                }

                con.commit();

                System.out.println("Book borrowed successfully!");

            } catch (SQLException e) {

                con.rollback();

                System.out.println("Transaction failed! Changes rolled back.");
                e.printStackTrace();
            }

        } catch (SQLException e) {

            System.out.println("Database error!");
            e.printStackTrace();
        }
    }

    static void returnBook() {

        try (Connection con = getConnection()) {

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            con.setAutoCommit(false);

            try {
                String checkQuery = "SELECT transaction_id FROM transactions " +
                        "WHERE book_id = ? AND status = 'BORROWED' " +
                        "FOR UPDATE";

                PreparedStatement checkPs = con.prepareStatement(checkQuery);

                checkPs.setInt(1, bookId);

                java.sql.ResultSet rs = checkPs.executeQuery();

                if (!rs.next()) {
                    System.out.println("No active borrowing record found!");
                    rs.close();
                    checkPs.close();
                    con.rollback();
                    return;
                }

                int transactionId = rs.getInt("transaction_id");

                rs.close();
                checkPs.close();

                String updateTransaction = "UPDATE transactions " +
                        "SET return_date = CURDATE(), status = 'RETURNED' " +
                        "WHERE transaction_id = ?";

                try (PreparedStatement ps = con.prepareStatement(updateTransaction)) {

                    ps.setInt(1, transactionId);
                    ps.executeUpdate();
                }

                String updateBook = "UPDATE books SET available = true WHERE book_id = ?";

                try (PreparedStatement ps = con.prepareStatement(updateBook)) {

                    ps.setInt(1, bookId);
                    ps.executeUpdate();
                }

                con.commit();

                System.out.println("Book returned successfully!");

            } catch (SQLException e) {

                con.rollback();

                System.out.println("Return failed! Changes rolled back.");
                e.printStackTrace();
            }

        } catch (SQLException e) {

            System.out.println("Database error!");
            e.printStackTrace();
        }
    }

    static void viewTransactions() {

        try {
            Connection con = getConnection();

            String query = "SELECT * FROM transactions";

            PreparedStatement ps = con.prepareStatement(query);

            java.sql.ResultSet rs = ps.executeQuery();

            System.out.println("\n===== LIBRARY TRANSACTIONS =====");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Transaction ID: "
                        + rs.getInt("transaction_id"));

                System.out.println("Book ID: "
                        + rs.getInt("book_id"));

                System.out.println("User ID: "
                        + rs.getInt("user_id"));

                System.out.println("Borrow Date: "
                        + rs.getDate("borrow_date"));

                System.out.println("Return Date: "
                        + rs.getDate("return_date"));

                System.out.println("Status: "
                        + rs.getString("status"));

                System.out.println("----------------------");
            }

            if (!found) {
                System.out.println("No transactions found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error viewing transactions!");
            e.printStackTrace();
        }
    }

}
