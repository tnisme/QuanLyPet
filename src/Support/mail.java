/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import java.sql.*;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class mail {
    String user = "sa";
    String pass1 = "123";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyPet";
    public String getpass(String username){
        String pass="";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass1);
            String sql = "SELECT id,pass FROM dbo.nhanvien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                if(username.equals(rs.getString(1))==true){
                    pass=rs.getString(2);
                }
            }
        } catch (Exception e) {
        }
        return pass;
    }
    
    public static void sendmail(String recepient) throws Exception{
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        
        String myAccount = "nghiabui1809@gmail.com";
        String password = "Pass0967647428";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccount,password);
            }
        });
        
        Message message = prepareMessage(session, myAccount,recepient );
    }

    private static Message prepareMessage(Session session, String myAccount, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Recover password");
            message.setText("123456");
        } catch (Exception e) {
        }
        return null;
    }
}
