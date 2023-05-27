
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.sql.*;
/**
 *
 * @author Asus
 */
public class Frame6 extends javax.swing.JFrame {

    /**
     * Creates new form Frame6
     */
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    
    public Frame6(String eid,String month,int year) {
        initComponents();
        try{
                Class.forName("org.gjt.mm.mysql.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/salary_slip","root","");
                
        setSize(1200,800);
        this.jEditorPane1.setContentType("text/html");
        showData1(eid,month,year);
        }catch(Exception eee){}
        
    }
    void showData1(String eid,String month,int year) throws Exception
    {
        String Month="";
        if(Integer.parseInt(month)==1) Month="January";
        if(Integer.parseInt(month)==2) Month="February";
        if(Integer.parseInt(month)==3) Month="March";
        if(Integer.parseInt(month)==4) Month="April";
        if(Integer.parseInt(month)==5) Month="May";
        if(Integer.parseInt(month)==6) Month="June";
        if(Integer.parseInt(month)==7) Month="July";
        if(Integer.parseInt(month)==8) Month="August";
        if(Integer.parseInt(month)==9) Month="September";
        if(Integer.parseInt(month)==10) Month="October";
        if(Integer.parseInt(month)==11) Month="November";
        if(Integer.parseInt(month)==12) Month="December";
        
        double bs=0,hra=0,ca=0,apa=0,cea=0,sa=0,ia=0,ma=0,lta=0,pt=0,it=0;
        stmt=con.prepareStatement("Select sum(BasicSalary),sum(HRA),sum(CA),sum(APA),sum(CEA),sum(SA),sum(IA),sum(MA),sum(LTA),sum(PT),sum(IT) from monthlydetails where eid=? and year=? and month<=?");
        stmt.setString(1,eid);
        stmt.setInt(2,year);
        stmt.setString(3,month);
        rs=stmt.executeQuery();
        if(rs.next())
        {
            bs=rs.getDouble(1);
            hra=rs.getDouble(2);
             ca=rs.getDouble(3);
            apa=rs.getDouble(4);
            cea=rs.getDouble(5);
            sa=rs.getDouble(6);
            ia=rs.getDouble(7);
            ma=rs.getDouble(8);
            lta=rs.getDouble(9);
            pt=rs.getDouble(8);
            it=rs.getDouble(9);
             
           
         }
        stmt=con.prepareStatement("Select * from employees where eid=?");
        stmt.setString(1,eid);
        rs=stmt.executeQuery();
        String nm="",post="",bank="",acno="",pan="",grossSalary="";
        if(rs.next())
        {
            nm=rs.getString(2);
            post=rs.getString(3);
            bank=rs.getString(8);
            acno=rs.getString(7);
            pan=rs.getString(6);
            grossSalary=rs.getString(5);
        }
        String[] mydata=new String[20];
        stmt=con.prepareStatement("Select * from monthlydetails where eid=? and month=? and year=?");
        stmt.setString(1,eid);
        stmt.setString(2,month);
        stmt.setInt(3,year);
        rs=stmt.executeQuery();
        if(rs.next())
        {
            for(int i=0;i<20;i++)
            {
                mydata[i]=rs.getString(i+4);
            }
        } 
        double sum=0;
        for(int i=4;i<13;i++)
        { 
            sum +=Double.parseDouble(mydata[i]);
            
        }
         double sum1=0;
        for(int i=15;i<17;i++)
        { 
            sum1+=Double.parseDouble(mydata[i]);
            
        }
        double net= sum-sum1;
        double sum2= bs+hra+ca+apa+cea+sa+ia+ma+lta+pt+it;
        double sum3= pt+it;
        String data="<html><body>";
        data+= "<div style='float:left'>" +
                "<center><img src='file:/d:/mastery.jfif'>" +
        " <div style='float:right;color: rgb(34, 24, 209);'>" +
          "<p>" +
            "Mastery Software Service LLP <br>" +
            "53, Shivaji Housing Society<br>" +  
            "S. B. Road <br>" +
            "Pune 411016 <br>"+
        "</p></div> ";   
                       
        data+="<table align='center' border='1'>";
        data+="<th style='text-align:center ;width: 500px;color: rgb(34, 24, 209);' colspan='6';><b>PAY SLIP for "+Month+" "+year+"</b></th>";
        data+="<tr>";
        data+="  <td>\n" +
        "   <b> Name </b><br>\n" +
        "    Designation <br>\n" +
        "    Bank Name <br>\n" +
        "    Bank A/C No. <br>\n" +
        "  </td>\n" +
        "  <td  style='width: 100px;' colspan='2'>\n" +
        "    " +
        "           <b>"+nm+"</b><br>\n" +
        "           "+post+"  <br>\n" +
        "           "+bank+"  <br>\n" +
        "            "+acno+" <br>\n" +
        "    \n" +
        "</td>\n" +
        "  <td style='width: 100px;'>\n" +
        "    Payable Days <br>\n" +
        "    Monthly Gross <br>\n" +
        "    PAN <br>\n" +
        "  </td>\n" +
        "  <td colspan='2'>\n" +
        "    \n" +
        "              "+mydata[0]+"<br>\n" +
        "              "+grossSalary+"<br>\n" +
        "              "+pan+"<br>\n" +
        "    \n" +
        "  </td>";
                data+="</tr>";
                data+="<tr>";
                data+="<td style=\"width: 50px;\">Earning Head</td>\n" +
        " <td style=\"width: 50px;\">Current Month Earnings</td>\n" +
        "    <td style=\"width: 30px;\">"+Month+"-To-Date Earning</td>\n" +
        "    <td style=\"width: 50px;\">Deduction Head</td>\n" +
        "    <td style=\"width: 50px;\">Current Month Deduction</td>\n" +
        "    <td style=\"width: 50px;\">"+Month+"-To-Date Deduction</td>";
                data+="</tr>";
                data+="<tr>";
                data+="<td>" +
            "<br>"+
            "<br>"+
            "<br>"+
            "<br>"+     
            "<br>"+
            "<br>"+ 
            "<br>"+
            "<br>"+     
                        "BASIC <br>" +
        "HOUSE RENT ALLOWANCE <br>"+
        "CONVEYANCE  ALLOWANCE <br>" +
        "ACADEMIC PURSUIT ALLOWANCE <br>" +
        "CHILDREN EDUCATION ALLOWANCE  <br>" +
        "SPEICAL ALLOWANCE  <br>" +
        "INTERNET ALLOWANCE <br>" +
        "MEAL ALLOWANCE <br>" +
        "LEAVE TRAVEL ASSISTANCE <br>" +
        "                <br>" +
        "                <br>" +
        "                <br>" +
        "                <br>" +
        "                <br>" +
        "                <br>" +
        "                <br>" +
         
        "      </td><td>" +
        "        "+mydata[4]+" <br>\n" +
        "        "+mydata[5]+" <br>\n" +
        "       "+mydata[6]+" <br>\n" +
        "        "+mydata[7]+"<br>\n" +
        "        "+mydata[8]+"<br>\n" +
                 mydata[9]+"<br>\n" +
                 mydata[10]+"<br>\n" + 
                  mydata[11]+"<br>\n" +
                   mydata[12]+"<br>\n" +
                " </td>"
                        + "<td>" +
        "           "+bs+"  <br>\n" +
        "           "+hra+"  <br>\n" +
                        "           "+ca+"  <br>\n" +
        "           "+apa+"  <br>\n" +
        "           "+cea+"  <br>\n" +
        "           "+sa+"  <br>\n" +
        "           "+ia+"  <br>\n" +
        "           "+ma+"  <br>\n" +
        "           "+lta+"  <br>\n" +
       
       
        "       </td><td> INCOME TAX <br>" +
        "        PROFESSION TAX <br></td><td>"+mydata[16]+"<br>" +
        "         "+mydata[15]+"</td>"
                        + "<td>"+
                         "           "+pt+"  <br>\n" +
                        "           "+it+"  <br>\n" +
                        "</td>";
                data+="</tr>";
                data+="<tr>";
                data+=" <td style='text-align:right;'>TOTAL</td>\n" +
        "    <td style='text-align:right;'>"+sum+"</td>" +
        "    <td style='text-align:right;'>"+sum2+"</td>\n" +
        "    <td><pre>          </pre></td>\n" +
        "    <td style='text-align:right;'>"+sum1+"</td>\n" +
        "    <td style='text-align:right;'>"+sum3+"</td>";
                data+="</tr>";
                data+="<tr>";
                data+="<th style='text-align:center ;width: 900px;color: rgb(34, 24, 209);' colspan='6';>\n" +
        "      NET PAY (Rs.)"+net+":"+NewClass.convertNumberToWord((long)Math.round(net))+
        "    </th>";
                data+="</tr>";
                data+="<tr>";
                data+=" <th style='text-align:center ;width: 900px;color: rgb(34, 24, 209);' colspan='6';>\n" +
        "      Message:  Professional Tax will be deducted later.\n" +
        "    </th>";
                data+="</tr>";
                data+="<tr>";
                data+=" <th style='text-align:center ;width: 900px; font-size: small;' colspan='6';>\n" +
        "      <pre>                                             </pre>\n" +
        "    </th>";
        data+="</tr>";
       
        data+="</table>";
        data+="</body></html>";
        this.jEditorPane1.setText(data);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jEditorPane1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame6("","",0).setVisible(true);
            }
        });
        


        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
