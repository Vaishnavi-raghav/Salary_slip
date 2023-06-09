/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.Color;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class Frame5 extends javax.swing.JFrame {

    /**
     * Creates new form Frame5
     */
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    String month;
    int year;
    public Frame5(String month,int year) {
        this.month=month;
        this.year=year;
                
        initComponents();
        this.jTable1.setComponentPopupMenu(this.jPopupMenu1);
         getContentPane().setBackground(Color.white);

         
         
      try{
           setSize(1500,700);
                Class.forName("org.gjt.mm.mysql.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/salary_slip","root","");
                stmt=con.prepareStatement("Select * from monthlydetails where month=? and year=?");
                stmt.setString(1,month);
                stmt.setInt(2,year);
                rs=stmt.executeQuery();
                DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
                while(rs.next())
                {
                    String[] row=new String[23];
                    for(int i=1;i<=23;i++)
                        row[i-1]=rs.getString(i);
                    dtm.addRow(row);
                }
        }catch(Exception eee){}
    }
     public Frame5() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenuItem1.setText("Print");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EID", "Month", "Year", "PayableDay", "Leaves", "LWP", "PaidDays", "BasicSalary", "HRA", "CA", "APA", "CEA", "SA", "IA", "MA", "LTA", "PI", "PF", "PT", "IT", "Adjustment", "Total", "NetPaid"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int r=this.jTable1.getSelectedRow();
        if(r>=0)
        {
            Object eid=jTable1.getModel().getValueAt(r,0);
            Frame6 f=new Frame6(eid.toString(),month,year);
            f.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Frame5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
