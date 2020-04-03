import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DAOPersonImplMySQL implements DAOPerson {
    private int i = 0;
    private int len = 0;
    private Connection conn = null;
    private List<Person> lista = new ArrayList<Person>();
    private String table;
    private String url = "jdbc:mysql://localhost/db1";

    public DAOPersonImplMySQL() {
            table="person";
            connect(url);
    }

    private void connect(String url){
      try {
        Class.forName("com.mysql.jdbc.Driver");
      } catch (Exception e) {
      e.printStackTrace();
      }

      try {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame tu usuario de la base de datos: ");
        String usuario = sc.nextLine();
        System.out.println("Dame la clave de la base de datos: ");
        String clave = sc.nextLine();
        conn = DriverManager.getConnection(url,usuario, clave);
        i = 0;
        this.lista = todos();
        this.len = lista.size();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    private void closeConnect(){
      if(conn == null)
        return;
      try {
        conn.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

    public List<Person> findAll() throws Exception {
      this.lista = todos();
      this.len = lista.size();
      this.i = 0;
      return this.lista;
    }

    public Person get() throws Exception {
      if(i < len) {
        Person au = new Person();
        au = lista.get(i);
        i++;
        return au;
      }
      i=0;
      return null;
    }

    public List<Person> todos() {
      List<Person> result = new ArrayList<Person>();
      String sql = "select * from person";
      try {
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
          Person unAu = new Person();
          unAu.setName(resultSet.getString("name"));
          unAu.setId(resultSet.getInt("id"));
          unAu.setCountry(resultSet.getString("country"));

        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
       return result;
    }
}

