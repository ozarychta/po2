package sample.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/oliwia/IdeaProjects/BankSystemGUI/accountsDb.db";

    public static final String TABLE_ACCOUNTS = "Accounts";
    public static final String COLUMN_USER_ID = "ClientID";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String COLUMN_LAST_NAME = "LastName";
    public static final String COLUMN_PESEL = "Pesel";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_BALANCE= "Balance";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final int INDEX_USER_ID = 1;
    public static final int INDEX_FIRST_NAME = 2;
    public static final int INDEX_LAST_NAME = 3;
    public static final int INDEX_PESEL = 4;
    public static final int INDEX_ADDRESS = 5;
    public static final int INDEX_BALANCE = 6;

    public static final String QUERY_BY_USER_ID =
            "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_USER_ID + " LIKE ?";

    public static final String QUERY_BY_FIRST_NAME =
            "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_FIRST_NAME + " LIKE ?";

    public static final String QUERY_BY_LAST_NAME =
            "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_LAST_NAME + " LIKE ?";

    public static final String QUERY_BY_PESEL =
            "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_PESEL + " LIKE ?";

    public static final String QUERY_BY_ADDRESS =
            "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_ADDRESS + " LIKE ?";

    public static final String QUERY_BY_BALANCE =
            "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_BALANCE + " LIKE ?";

    public static final String INSERT_ACCOUNT = "INSERT INTO " + TABLE_ACCOUNTS +
            '(' + COLUMN_FIRST_NAME + ", " +COLUMN_LAST_NAME + ", " +COLUMN_PESEL + ", " +COLUMN_ADDRESS + ", " +COLUMN_BALANCE
            + ") VALUES(?, ?, ?, ?, ?)";

    public static final String GET_BALANCE_BY_ID =
            "SELECT "+COLUMN_BALANCE+" FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_USER_ID + " = ?";

    public static final String UPDATE_BALANCE_BY_ID =
            "UPDATE "+TABLE_ACCOUNTS+" SET " + COLUMN_BALANCE+ " = ? WHERE " + COLUMN_USER_ID + " = ?";

    public static final String DELETE_BY_ID =
            "DELETE FROM "+TABLE_ACCOUNTS+" WHERE " + COLUMN_USER_ID + " = ?";


        private PreparedStatement queryByUserID;
        private PreparedStatement queryByFirstName;
        private PreparedStatement queryByLastName;
        private PreparedStatement queryByPesel;
        private PreparedStatement queryByAddress;
        private PreparedStatement queryByBalance;
        private PreparedStatement insertAccount;
        private PreparedStatement getBalanceByID;
        private PreparedStatement updateBalanceByID;
        private PreparedStatement deleteByID;

    private Connection conn;
    private static Datasource instance = new Datasource();

    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryByUserID = conn.prepareStatement(QUERY_BY_USER_ID);
            queryByFirstName = conn.prepareStatement(QUERY_BY_FIRST_NAME);
            queryByLastName = conn.prepareStatement(QUERY_BY_LAST_NAME);
            queryByAddress = conn.prepareStatement(QUERY_BY_ADDRESS);
            queryByPesel = conn.prepareStatement(QUERY_BY_PESEL);
            queryByBalance = conn.prepareStatement(QUERY_BY_BALANCE);
            insertAccount = conn.prepareStatement(INSERT_ACCOUNT);
            getBalanceByID = conn.prepareStatement(GET_BALANCE_BY_ID);
            updateBalanceByID = conn.prepareStatement(UPDATE_BALANCE_BY_ID);
            deleteByID = conn.prepareStatement(DELETE_BY_ID);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if(queryByUserID != null){
                queryByUserID.close();
            }
            if(queryByFirstName != null){
                queryByFirstName.close();
            }
            if(queryByLastName != null){
                queryByLastName.close();
            }
            if(queryByPesel != null){
                queryByPesel.close();
            }
            if(queryByAddress != null){
                queryByAddress.close();
            }
            if(queryByBalance != null){
                queryByBalance.close();
            }
            if(insertAccount != null){
                insertAccount.close();
            }
            if(getBalanceByID != null){
                getBalanceByID.close();
            }
            if(updateBalanceByID != null){
                updateBalanceByID.close();
            }
            if(deleteByID != null){
                deleteByID.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public void setAccount(Account a, ResultSet r) throws SQLException{
        a.setClientID(r.getInt(INDEX_USER_ID));
        a.setFirstName(r.getString(INDEX_FIRST_NAME));
        a.setLastName(r.getString(INDEX_LAST_NAME));
        a.setPesel(r.getString(INDEX_PESEL));
        a.setAddress(r.getString(INDEX_ADDRESS));
        a.setBalance(r.getDouble(INDEX_BALANCE));
    }

    public List<Account> queryAccounts(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ACCOUNTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_USER_ID);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Account> accounts = new ArrayList<>();
            while (results.next()) {

                Account account = new Account();
                setAccount(account, results);
                accounts.add(account);
            }

            return accounts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }



    private List<Account> findBy(PreparedStatement ps, String value) {

        try {
            ps.setString(1, value);

            ResultSet results = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (results.next()) {

                Account account = new Account();
                setAccount(account, results);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Account> findByClientID(String value) {
        return findBy(queryByUserID, value);
    }

    public List<Account> findByFirstName(String value) {

        return findBy(queryByFirstName, value);
    }

    public List<Account> findByLastName(String value) {

        return findBy(queryByLastName, value);
    }

    public List<Account> findByPesel(String value) {

        return findBy(queryByPesel, value);
    }

    public List<Account> findByAddress(String value) {

        return findBy(queryByAddress, value);
    }

    public List<Account> findByBalance(String value) {

        return findBy(queryByBalance, value);
    }

    public int insertAccount(String firstName, String lastName, String pesel, String address, double balance) {

        try{
            insertAccount.setString(1, firstName);
            insertAccount.setString(2, lastName);
            insertAccount.setString(3, pesel);
            insertAccount.setString(4, address);
            insertAccount.setDouble(5, balance);
            int affectedRows = insertAccount.executeUpdate();

            return affectedRows;
        }catch(Exception e){
            System.out.println("Insert failed");
            return -1;
        }

    }

    public int delete(int id){
        try{
            deleteByID.setInt(1,id);
            int affectedRows = deleteByID.executeUpdate();
            return affectedRows;
        }catch (Exception e) {
            System.out.println("Deleting query failed: " + e.getMessage());
            return -1;
        }
    }

    private double getBalanceByID(int id){
        try{
            getBalanceByID.setInt(1,id);
            ResultSet result = getBalanceByID.executeQuery();
            double balance = result.getDouble(1);
            return balance;
        }catch(Exception e){
            System.out.println("That ID is not in the database");
            return -1;
        }
    }

    private boolean updateBalanceByID(int id, double amount){
        try{
            updateBalanceByID.setDouble(1,amount);
            updateBalanceByID.setInt(2,id);
            ResultSet result = getBalanceByID.executeQuery();
            int affectedRecords = updateBalanceByID.executeUpdate();

            return affectedRecords == 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("That ID is not in the database");
            return false;
        }
    }

    public int withdraw(int id, double amount){
        double balance = getBalanceByID(id);
        if(balance<0) return -1;
        else{
            double newBalance = balance-amount;
            if(newBalance<0) return -2;
            else{
                if(updateBalanceByID(id, newBalance)){
                    return 1;
                } else return 0;
            }
        }
    }

    public int deposit(int id, double amount){
        double balance = getBalanceByID(id);
        if(balance<0) return -1;
        else{
            double newBalance = balance+amount;
            if(updateBalanceByID(id, newBalance)){
                return 1;
            } else return 0;
        }
    }

    public int transaction(int idFrom, int idTo, double amount) {

        int somethingWrong=0;
        try {
            conn.setAutoCommit(false);
            if(getBalanceByID(idTo)==-1){
                somethingWrong=-3;
                throw new SQLException("Id to deposit not in database");
            }

            int withdrawalResult = withdraw(idFrom, amount);
            int depositResult = deposit(idTo, amount);
            if(withdrawalResult==1) {
                if(depositResult ==1){
                    conn.commit();
                    somethingWrong = 1;
                } else if(depositResult==-1){
                    somethingWrong=-3;
                } else{
                    somethingWrong = depositResult;
                    throw new SQLException("The deposit failed");
                }
            } else {
                somethingWrong = withdrawalResult;
                throw new SQLException("The withdrawal failed");
            }

        } catch(Exception e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("Oh no! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                return somethingWrong;
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
                return somethingWrong;
            }

        }
    }

}
