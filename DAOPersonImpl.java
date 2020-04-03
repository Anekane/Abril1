import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class DAOPersonImpl implements DAOPerson {
  private int i = 0;
  private int len = 0;
  private Connection conex = null;
  private List<Person> lista = new ArrayList<Person>();
  private String tabla;
  private String url = "jdbc:sqlite:db1.db";

  public DAOPersonImpl() {
    tabla="person";
    connect(url);
  }

  private void connect(String url){
    try {
      conex = DriverManager.getConnection(url);
      i = 0;
      this.lista = todos();
      this.len = lista.size();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void closeConnect(){
    if(conex == null)
    return;
    try {
      conex.close();
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
      PreparedStatement statement = conex.prepareStatement(sql);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Person pers = new Person();
        pers.setName(resultSet.getString("name"));
        pers.setId(resultSet.getInt("id"));
        pers.setCountry(resultSet.getString("country"));
        result.add(pers);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

}