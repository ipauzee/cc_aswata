/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ccasw_Search_ticket.java
 *
 * Created on Mar 11, 2010, 1:52:18 AM
 */

package cc_aswata;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.sun.database.JavaConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.net.*;
/**
 *
 * @author jsm
 */
public class ccasw_Search_ticket extends javax.swing.JFrame {

    public static boolean sms;
    public static boolean mail;
    public static boolean out;

    public static int tiCid;
    public static int sMs;
    public static int Mail;
    public static int oUt;

    String s;
    String tic[]=new String[13];

    /** Creates new form ccasw_Search_ticket */
    public ccasw_Search_ticket() {
        initComponents();
        tbltic.setModel(tabtic);
    }

    private ContactCenterASWATA CCanj;
    public ccasw_Search_ticket(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
    }
    public ccasw_OutBound Otb;
    public ccasw_Search_ticket(ccasw_OutBound otb){
        this();
        this.Otb=otb;
    }
    public ccasw_Sms_income Sin;
    public ccasw_Search_ticket(ccasw_Sms_income sin){
        this();
        this.Sin=sin;
    }
    public ccasw_Email_incoming Ein;
    public ccasw_Search_ticket(ccasw_Email_incoming ein){
        this();
        this.Ein=ein;
    }
    public ccasw_SMS Sms;
    public ccasw_Search_ticket(ccasw_SMS sms){
        this();
        this.Sms=sms;
    }
    public ccasw_EMail EMail;
    public ccasw_Search_ticket(ccasw_EMail email){
        this();
        this.EMail=email;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltic = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        txtuser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcategory = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdriver = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcustomer = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtticno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SEARCH TICKET");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Ticket", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Calibri", 0, 11));
        jPanel4.setLayout(null);

        jScrollPane3.setFont(jScrollPane3.getFont().deriveFont((float)11));

        tbltic.setFont(new java.awt.Font("Calibri", 0, 11));
        tbltic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblticMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltic);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(20, 60, 818, 150);

        jButton5.setFont(jButton5.getFont().deriveFont(jButton5.getFont().getStyle() | java.awt.Font.BOLD, 11));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        jButton5.setText("Search Ticket");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);
        jButton5.setBounds(690, 40, 140, 24);

        txtuser.setFont(txtuser.getFont().deriveFont((float)11));
        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });
        jPanel4.add(txtuser);
        txtuser.setBounds(20, 40, 104, 24);

        jLabel5.setFont(jLabel5.getFont().deriveFont((float)11));
        jLabel5.setText("User");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(20, 30, 22, 10);

        txtcategory.setFont(txtcategory.getFont().deriveFont((float)11));
        jPanel4.add(txtcategory);
        txtcategory.setBounds(130, 40, 104, 24);

        jLabel6.setFont(jLabel6.getFont().deriveFont((float)11));
        jLabel6.setText("Category");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(130, 30, 51, 10);

        txtdriver.setFont(txtdriver.getFont().deriveFont((float)11));
        jPanel4.add(txtdriver);
        txtdriver.setBounds(240, 40, 104, 24);

        jLabel7.setFont(jLabel7.getFont().deriveFont((float)11));
        jLabel7.setText("Driver");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(240, 30, 51, 10);

        txtcustomer.setFont(txtcustomer.getFont().deriveFont((float)11));
        jPanel4.add(txtcustomer);
        txtcustomer.setBounds(350, 40, 104, 24);

        jLabel8.setFont(jLabel8.getFont().deriveFont((float)11));
        jLabel8.setText("Customer");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(350, 30, 51, 10);

        txtticno.setFont(txtticno.getFont().deriveFont((float)11));
        jPanel4.add(txtticno);
        txtticno.setBounds(460, 40, 104, 24);

        jLabel9.setFont(jLabel9.getFont().deriveFont((float)11));
        jLabel9.setText("No Ticket");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(460, 30, 51, 10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblticMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            if(sms==true){
                Tampils();
                dispose();
//                sql="update log_sms set ticket_id='"+tiCid+"' where ";
//                jconn.SQLExecute(sql, conn);
            }
            if(out==true){
                Tampil();
                dispose();
            }
            if(mail==true){
                Tampilm();
                dispose();
            }
        }    
}//GEN-LAST:event_tblticMouseClicked

    int row=0;
    public void Tampil(){
        row=tbltic.getSelectedRow();
        Otb.txtnotic.setText(tbltic.getValueAt(row,0).toString());
        Otb.txtnotic1.setText(tbltic.getValueAt(row,12).toString());
    }
 public void Tampils(){
        row=tbltic.getSelectedRow();
        Sms.txtnotic.setText((String)tbltic.getValueAt(row,0));
        Sms.id=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(),12));
 }
 public void Tampilm(){
        row=tbltic.getSelectedRow();
        EMail.txtnotic.setText((String)tbltic.getValueAt(row,0));
        EMail.id=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(),12));
 }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton5ActionPerformed

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtuserActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        tabtic.setRowCount(0);
        try{
            int row=0;
                sql="select a.ticket_no," +
                        " a._status," +
                        " b.category_name," +
                        " a.assign_dept," +
                        " a.assign_username," +
                        " a.cust_name," +
                        " a.cust_phone," +
                        " a.user," +
                        " a.vehicle_platno," +
                        " a.vehicle_type," +
                        " a.driver_name," +
                        " a.driver_phone," +
                        " a.ticket_id" +
                        " from tickets a, _ticketcategory b" +
                        " where a.category_id=b.category_id";
                condition="";
                if(!txtuser.getText().equals("")){
//                    if (condition.equals("")){
//                        condition=condition+" and ";
//                    }
                    condition=condition+" and a.user like '%"+txtuser.getText()+"%'";
                }
                if(!txtcategory.getText().equals("")){
                    condition=condition+" and b.category_name like '%"+txtcategory.getText()+"%'";
                }
                if(!txtdriver.getText().equals("")){
                    condition=condition+" and a.driver_name like '%"+txtdriver.getText()+"%'";
                }
                if(!txtcustomer.getText().equals("")){
                    condition=condition+" and a.cust_name like '%"+txtcustomer.getText()+"%'";
                }
                if(!txtticno.getText().equals("")){
                    condition=condition+" and a.ticket_no like '%"+txtticno.getText()+"%'";
                }

            sql=sql+condition;
            rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            System.out.println(sql);

            while(rs.next()){
                tic[0]=rs.getString(1);
                tic[1]=rs.getString(2);
                tic[2]=rs.getString(3);
                tic[3]=rs.getString(4);
                tic[4]=rs.getString(5);
                tic[5]=rs.getString(6);
                tic[6]=rs.getString(7);
                tic[7]=rs.getString(8);
                tic[8]=rs.getString(9);
                tic[9]=rs.getString(10);
                tic[10]=rs.getString(11);
                tic[11]=rs.getString(12);
                tic[12]=rs.getString(13);
                tabtic.addRow(tic);
                row+=1;
            }if(row==0){
                JOptionPane.showMessageDialog(null,"Ticket with number ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" dosen't exsist");
            }

        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }//GEN-LAST:event_jButton5MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
//        try {
//            conn.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }//GEN-LAST:event_formWindowClosed

     private javax.swing.table.DefaultTableModel getDefaultTabeltic(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Ticket No.","Status","Category","Assign Dept.","Assign User","Customer","Phone number","Username","No. Plat","Type","Driver","Phone"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_Search_ticket().setVisible(true);
            }
        });
    }

    private javax.swing.table.DefaultTableModel tabtic=getDefaultTabeltic();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbltic;
    private javax.swing.JTextField txtcategory;
    private javax.swing.JTextField txtcustomer;
    private javax.swing.JTextField txtdriver;
    private javax.swing.JTextField txtticno;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables

    private String sql;
    private String sql1;
    private String sqlid;
    private String condition;    
    private ResultSet rs;
//    private JavaConnector jconn=new JavaConnector();
//    private Connection conn;
}