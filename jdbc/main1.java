import java.sql.*;
import java.util.Scanner;
public class main1{
static final String URL="jdbc:mysql://localhost:3306/companydb";
static final String USER="root";
static final String PASSWORD="2004";
public static void main(String[] args){
try(Connection con=DriverManager.getConnection(URL,USER,PASSWORD);Scanner sc=new Scanner(System.in)){
Class.forName("com.mysql.cj.jdbc.Driver");
con.setAutoCommit(false);
while(true){
System.out.println("\n=== PRODUCT MANAGEMENT ===");
System.out.println("1. Add Product");
System.out.println("2. View Products");
System.out.println("3. Update Product");
System.out.println("4. Delete Product");
System.out.println("5. Exit");
System.out.print("Enter choice: ");
int choice=sc.nextInt();
switch(choice){
case 1:
addProduct(con,sc);
break;
case 2:
viewProducts(con);
break;
case 3:
updateProduct(con,sc);
break;
case 4:
deleteProduct(con,sc);
break;
case 5:
System.out.println("Exiting...");
con.close();
return;
default:
System.out.println("Invalid choice!");
}
}
}catch(Exception e){
e.printStackTrace();
}
}
private static void addProduct(Connection con,Scanner sc)throws SQLException{
System.out.print("Enter Product Name: ");
String name=sc.next();
System.out.print("Enter Price: ");
double price=sc.nextDouble();
System.out.print("Enter Quantity: ");
int qty=sc.nextInt();
PreparedStatement ps=con.prepareStatement("INSERT INTO Product(ProductName, Price, Quantity) VALUES(?, ?, ?)");
ps.setString(1,name);
ps.setDouble(2,price);
ps.setInt(3,qty);
ps.executeUpdate();
con.commit();
System.out.println("Product added successfully!");
}
private static void viewProducts(Connection con)throws SQLException{
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("SELECT * FROM Product");
System.out.println("ProductID\tName\t\tPrice\tQuantity");
while(rs.next()){
System.out.printf("%d\t%s\t%.2f\t%d%n",rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
}
}
private static void updateProduct(Connection con,Scanner sc)throws SQLException{
System.out.print("Enter ProductID to update: ");
int id=sc.nextInt();
System.out.print("Enter new Price: ");
double price=sc.nextDouble();
PreparedStatement ps=con.prepareStatement("UPDATE Product SET Price=? WHERE ProductID=?");
ps.setDouble(1,price);
ps.setInt(2,id);
int rows=ps.executeUpdate();
if(rows>0){
con.commit();
System.out.println("Product updated successfully!");
}else{
con.rollback();
System.out.println("Product not found.");
}
}
private static void deleteProduct(Connection con,Scanner sc)throws SQLException{
System.out.print("Enter ProductID to delete: ");
int id=sc.nextInt();
PreparedStatement ps=con.prepareStatement("DELETE FROM Product WHERE ProductID=?");
ps.setInt(1,id);
int rows=ps.executeUpdate();
if(rows>0){
con.commit();
System.out.println("Product deleted successfully!");
}else{
con.rollback();
System.out.println("Product not found.");
}
}
}
