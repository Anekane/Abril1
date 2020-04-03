import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main(String[] argv) throws Exception {

        System.out.println("Indica que base de detos desea utilizar escribiendo sunombre: mysql / sqlite");
        Scanner sc = new Scanner(System.in);
        String db = sc.nextLine();
        DAOPerson obj=null;

        if (db.equals("mysql")){
            obj = new DAOPersonImplMySQL();
        } else if (db.equals ("sqlite")){
            obj = new DAOPersonImpl(); 
        }

            List<Person> l =  obj.findAll();
        for (Person i : l) {

            System.out.println(i.getId() + " --- " + i.getName()  + " --- " + i.getCountry());

        }
    }
}