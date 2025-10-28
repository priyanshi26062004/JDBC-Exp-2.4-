import java.sql.*;

public class Main{
public static void main(String[] args) {
String url = "jdbc:mysql://root@localhost:3306/companydb";
String user = "root";
String pass = "2004";
try {
Connection con = DriverManager.getConnection(url, user, pass);
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM Employee");
System.out.println("EmpID | Name | Salary");
while (rs.next()) {
System.out.println(rs.getInt("EmpID") + " | " + rs.getString("Name") + " | " + rs.getDouble("Salary"));
}
rs.close();
st.close();
con.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}
