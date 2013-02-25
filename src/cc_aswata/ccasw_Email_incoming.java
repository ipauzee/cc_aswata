/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ccasw_Email_incoming.java
 *
 * Created on Feb 23, 2010, 3:56:48 PM
 */

package cc_aswata;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.*;
import javax.sun.database.JavaConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author jsm
 */
public class ccasw_Email_incoming extends javax.swing.JFrame {
    public static int z;
    public static int zz;
    public static int mid=0;
    private boolean selected;
    String nmfile, fullnmfile, s;

    /** Creates new form ccasw_Email_incoming */
    public ccasw_Email_incoming() {
        initComponents();
        zz=-1;
        showCaltype();
        showCust();
        selected=false;
    }

    public ccasw_Search_ticket Sct;
    public ccasw_Email_incoming(ccasw_Search_ticket sct){
        this();
        this.Sct=sct;
    }
    public static ccasw_login Log;
    public ccasw_Email_incoming(ccasw_login log){
        this();
        this.Log=log;
    }
    public static ContactCenterASWATA CCanj;
    public ccasw_Email_incoming(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
    }
    public static ccasw_EMail Mail;
    public ccasw_Email_incoming(ccasw_EMail mail){
        this();
        this.Mail=mail;
    }
    private ccasw_ticket Tic;
    public ccasw_Email_incoming(ccasw_ticket tic){
        this();
        this.Tic=tic;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtmsg = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtsubject = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtfrom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbcaltype = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbcust = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtnotic = new javax.swing.JTextField();
        scpCcList1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        btnAttachment = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("EMAIL iNCOMING");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jScrollPane3.setFont(jScrollPane3.getFont().deriveFont((float)11));

        txtmsg.setColumns(20);
        txtmsg.setFont(txtmsg.getFont().deriveFont((float)12));
        txtmsg.setRows(5);
        jScrollPane3.setViewportView(txtmsg);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(110, 150, 660, 320);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel2.setText("messages :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 150, 100, 20);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel1.setText("From             :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 100, 20);

        txtsubject.setFont(txtsubject.getFont().deriveFont((float)11));
        jPanel1.add(txtsubject);
        txtsubject.setBounds(110, 70, 500, 25);

        jButton1.setFont(new java.awt.Font("Calibri", 0, 12));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        jButton1.setText("Search Ticket");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 470, 131, 24);

        jButton2.setFont(new java.awt.Font("Calibri", 0, 12));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tic.jpg"))); // NOI18N
        jButton2.setText("Open Ticket");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(640, 470, 133, 24);

        jButton3.setFont(new java.awt.Font("Calibri", 0, 12));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reply m.jpg"))); // NOI18N
        jButton3.setText("Reply");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(610, 10, 99, 41);

        txtfrom.setFont(txtfrom.getFont().deriveFont((float)11));
        jPanel1.add(txtfrom);
        txtfrom.setBounds(110, 10, 500, 25);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel3.setText("Subject       :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 70, 100, 20);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel4.setText("Sender Type");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 90, 100, 20);

        cbcaltype.setFont(cbcaltype.getFont().deriveFont((float)11));
        cbcaltype.setMaximumRowCount(9);
        cbcaltype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcaltype.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbcaltypeMouseClicked(evt);
            }
        });
        jPanel1.add(cbcaltype);
        cbcaltype.setBounds(110, 90, 200, 24);

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel16.setText("Attachment");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(10, 130, 100, 20);

        cbcust.setFont(cbcust.getFont().deriveFont((float)11));
        cbcust.setMaximumRowCount(9);
        cbcust.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        jPanel1.add(cbcust);
        cbcust.setBounds(110, 110, 200, 24);

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel5.setText("To                 :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 30, 100, 20);

        txtto.setFont(txtto.getFont().deriveFont((float)11));
        jPanel1.add(txtto);
        txtto.setBounds(110, 30, 500, 25);

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel6.setText("Cc                 :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 50, 100, 20);

        txtcc.setFont(txtcc.getFont().deriveFont((float)11));
        jPanel1.add(txtcc);
        txtcc.setBounds(110, 50, 500, 25);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Attachment"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(610, 50, 160, 0);

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ticket No");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(310, 110, 120, 20);

        txtnotic.setFont(txtnotic.getFont().deriveFont((float)11));
        jPanel1.add(txtnotic);
        txtnotic.setBounds(430, 110, 180, 25);

        jList2.setFont(jList2.getFont().deriveFont((float)11));
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList2MouseReleased(evt);
            }
        });
        scpCcList1.setViewportView(jList2);

        jPanel1.add(scpCcList1);
        scpCcList1.setBounds(110, 130, 500, 24);

        btnAttachment.setFont(btnAttachment.getFont().deriveFont(btnAttachment.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnAttachment.setText("Download");
        btnAttachment.setEnabled(false);
        btnAttachment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachmentActionPerformed(evt);
            }
        });
        jPanel1.add(btnAttachment);
        btnAttachment.setBounds(610, 130, 100, 24);

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel17.setText("Cust. Company");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(10, 110, 100, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ccasw_ticket tic=new ccasw_ticket();
        tic.setVisible(true);

        Tic.newtic=true;
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ccasw_EMail com=new ccasw_EMail();
        com.setVisible(true);

        Mail.txtcto.setText(txtfrom.getText());
        Mail.txtccc.setText(txtcc.getText());
        Mail.txtcsu.setText(txtsubject.getText());
        Mail.txtcmsg.setText("From : "+txtfrom.getText()
                            +"\nCc : "+txtcc.getText()
                            +"\nSubject : "+txtsubject.getText()
                            +"\n\n"+txtmsg.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        ccasw_Search_ticket st=new ccasw_Search_ticket();
        st.setVisible(true);
        Sct.mail=true;
    }//GEN-LAST:event_jButton1MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        zz=(Tic.id);
            if(mid!=0){
//                System.out.print(zz);
                if(zz==-1){
                    sql = "update log_mail set"
                            + " status=2 ,direction_type='"+cbcaltype.getSelectedItem()+"', cust_name='"+cbcust.getSelectedItem()+"' "
                            + " ticket_no='"+txtnotic.getText()+"'"
                            + " where mail_id='"+mid+"'";
                    CCanj.jconn.SQLExecute(sql, CCanj.conn);
                }else{
                    sql = "update log_mail set"
                            + " status=2 , ticket_id='"+zz+"' ,direction_type='"+cbcaltype.getSelectedItem()+"', cust_name='"+cbcust.getSelectedItem()+"' " +
                        "where mail_id='"+mid+"'";
                    CCanj.jconn.SQLExecute(sql, CCanj.conn);
//                    System.out.print(sql);
                }
            }
        CCanj.mail();
//        try {
//            conn.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(selected==true){
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void cbcaltypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcaltypeMouseClicked
        // TODO add your handling code here:
        selected=true;
    }//GEN-LAST:event_cbcaltypeMouseClicked
String att;
Object sel1;int baris;int conter;
    private void jList2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseReleased
        // TODO add your handling code here:
        att="";
        int selectedIx = jList2.getSelectedIndex();
        //        for (int i=0; i<selectedIx.length; i++) {
        //            //            countagent++;
        //            sel = jList1.getModel().getElementAt(selectedIx[i]);
        //
        //            if(agent.equals("")){
        //                agent=agent+(String.valueOf(sel));
        //            }else{
        //                agent=agent+","+(String.valueOf(sel));
        //            }
        //        }
        //        if(agent.equals("")){
        //            agent=agent+"<None>";
        //        }
        sel1 = jList2.getModel().getElementAt(selectedIx);
        att=String.valueOf(sel1);
        btnAttachment.setEnabled(true);
}//GEN-LAST:event_jList2MouseReleased

    private void btnAttachmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachmentActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser(att);
        chooser.setSelectedFile(new File(att));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            nmfile=(chooser.getSelectedFile().getName().toString());
            System.out.print("\nnamafile: " + nmfile + "\n");
            fullnmfile=(chooser.getSelectedFile().getAbsolutePath());
            //            s = "DOWNLOAD|EMAIL|"+nmfile+"|"+fullnmfile+"|localhost|cc_takaful\r\n";
            CCanj.s = "DOWNLOAD|EMAIL|"+mid+"|0|"+att+"|"+fullnmfile+"|"+Log.ftpserver+"|"+Log.ftpuser+"\r\n";
            CCanj.kirimUplo();

        }
}//GEN-LAST:event_btnAttachmentActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_Email_incoming().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAttachment;
    public static javax.swing.JComboBox cbcaltype;
    public static javax.swing.JComboBox cbcust;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane scpCcList1;
    public static javax.swing.JTextField txtcc;
    public static javax.swing.JTextField txtfrom;
    public static javax.swing.JTextArea txtmsg;
    public static javax.swing.JTextField txtnotic;
    public static javax.swing.JTextField txtsubject;
    public static javax.swing.JTextField txtto;
    // End of variables declaration//GEN-END:variables

    public static String sql;
    public static String sql1;
    public static ResultSet rs,rs1;
//    public static JavaConnector jconn=new JavaConnector();
//    public static Connection conn;

    public static void open(){
        sql = "select * from log_mail " +
                    "where status=1 and direction=0 " +
                    "and username='"+ CCanj.lbluser.getText()+"'";
            rs = CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            System.out.print("\n isi mail "+sql);
        try {
            while (rs.next()) {
                z++;
                mid=Integer.parseInt(rs.getString("mail_id"));
                txtfrom.setText(rs.getString("mail_from").toString());                
                txtcc.setText(rs.getString("mail_cc").toString());
                txtsubject.setText(rs.getString("mail_subject").toString());
                txtmsg.setText(rs.getString("mail_text").toString());
                txtto.setText(rs.getString("mail_to"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ccasw_Sms_income.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void showCaltype(){
        try{
            cbcaltype.removeAllItems();

            sql="select data from _callertype";
            rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
            while(rs.next()){
                cbcaltype.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);

        }
    }
    private void showCust(){
        try{
            cbcust.removeAllItems();
            cbcust.addItem("Others");

            sql="select distinct(cust_name) from customer_order order by cust_name";
            rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
            while(rs.next()){
                cbcust.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);

        }
    }
    public static void getAttch(){
        DefaultListModel listModel = new DefaultListModel();
        jList2.setModel(listModel);
        try{
            jList2.setModel(listModel);
            sql1="select filename from mail_attachment where mail_id='"+mid+"'";
            rs1=CCanj.jconn.SQLExecuteRS(sql1,CCanj.conn);
            while(rs1.next()){
                listModel.addElement(rs1.getString("filename").toString());
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
