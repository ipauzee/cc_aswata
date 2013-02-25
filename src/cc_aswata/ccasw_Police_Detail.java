/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ccasw_Police_Detail.java
 *
 * Created on Dec 17, 2012, 6:43:16 PM
 */

package cc_aswata;

import java.awt.event.*;
import java.awt.Event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Alienware
 */
public class ccasw_Police_Detail extends javax.swing.JFrame {
    private static String cus[]=new String[21];
    private static String cus1[]=new String[21];
    private static String cus2[]=new String[21];
    public static String client_id;
    public static int wsid=1,wsid1=1,wsstt=0,X=0,X1=0,Form=0;
    public static boolean ws=false,ws1=false,transaction=false;
    Timer wsws;

    /** Creates new form ccasw_Police_Detail */
    public ccasw_Police_Detail() {
        initComponents();
        tblcus.setModel(tabcus);
        tblcus1.setModel(tabcus1);
        tbcus(tblcus,new int []{40,40,240,60,140,40,40,140,140,110,110});
        tbcus(tblcus1,new int []{40,260,120,120,40,180,220});
        setSize(1010,630);
        wsws=new Timer(1000, wsinbound);
        if(Form==1){btnOpenTic.setEnabled(false);}
        btnEmbed.setVisible(false);
    }

    public static ContactCenterASWATA CCanj;
    public ccasw_Police_Detail(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
    }
    private ccasw_ProgressBar Prg;
    public ccasw_Police_Detail(ccasw_ProgressBar prg){
        this();
        this.Prg=prg;
    }
    public static ccasw_ticket Tic;
    public ccasw_Police_Detail(ccasw_ticket tic){
        this();
        this.Tic=tic;
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelcus(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"No.","ED","DOCUMENT NUMBER","TYPE","TRN. DATE","STS","CUR","AMOUNT","PAID AMOUNT","PYMNT. DATE","PAYMENT DOC. NUMBER"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel tabcus=getDefaultTabelcus();
    public static void tabelcus(){
        tabcus.setRowCount(0);
        try{
            sql="select * from ws_notes_info_collection where request_id="+wsid+" ";
            rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            System.out.println(sql);

            while(rs.next()){
                cus[0]=rs.getString("no");
                cus[1]=rs.getString(4);
                cus[2]=rs.getString(5);
                cus[3]=rs.getString(6);
                cus[4]=rs.getString(7);
                cus[5]=rs.getString(8);
                cus[6]=rs.getString(9);
                cus[7]=rs.getString(10);
                cus[8]=rs.getString(11);
                cus[9]=rs.getString(12);
                cus[10]=rs.getString(13);
                tabcus.addRow(cus);
            }
            sql1="delete from ws_notes_info_collection where request_id="+wsid+" ";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    private void tbcus(javax.swing.JTable tb, int lebar[]){
        tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
        int kolom=tb.getColumnCount();
        for (int i=0;i<kolom;i++){
            javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(18);
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelcus1(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"No.","REG. NO.","REG. DATE","DATE OF LOSS","CUR","LOST EST./ CLAIM PAID","STATUS"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel tabcus1=getDefaultTabelcus1();
    public static void tabelcus1(){
        tabcus1.setRowCount(0);
        try{
            sql1="select * from ws_claim_history_collection where request_id="+wsid+" ";
            rs2=CCanj.jconn.SQLExecuteRS(sql1, CCanj.conn);
            System.out.println(sql);

            while(rs2.next()){
                cus1[0]=rs2.getString("no");
                cus1[1]=rs2.getString(4);
                cus1[2]=rs2.getString(5);
                cus1[3]=rs2.getString(6);
                cus1[4]=rs2.getString(7);
                cus1[5]=rs2.getString(8);
                cus1[6]=rs2.getString(9);
                tabcus1.addRow(cus1);
            }
            sql="delete from ws_claim_history_collection where request_id="+wsid+" ";
            CCanj.jconn.SQLExecute(sql, CCanj.conn);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static void detilcus(){
        try{
            sql2="select * from ws_detail_policy_payment where request_id="+wsid+" ";
            rs1=CCanj.jconn.SQLExecuteRS(sql2, CCanj.conn);
            System.out.println(sql);

            while(rs1.next()){
                txtBranchName.setText(rs1.getString("branch_name"));
                lblPolicyNo.setText(rs1.getString("policy_number"));
                lblPI1.setText(rs1.getString("net_premi"));
                lblPI2.setText(rs1.getString(7));
                lblPI3.setText(rs1.getString(8));
                txtReqNm.setText(rs1.getString(9));
                txtInsured.setText(rs1.getString(10));
                txtCob.setText(rs1.getString(11));
                txtPFrom.setText(rs1.getString(12));
                txtPTo.setText(rs1.getString(13));
            }
            sql="delete from ws_detail_policy_payment where request_id="+wsid+" ";
            CCanj.jconn.SQLExecute(sql, CCanj.conn);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    
    public static void detilcus2(){
        try{
            sql="select * from ws_detail_customer where request_id="+wsid+" ";
            rs1=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
//            System.out.println(sql);

            while(rs1.next()){
                Tic.txtcusnam.setText(rs1.getString("client_name"));
                Tic.txtcustadd.setText(rs1.getString("client_address")+"\n"+rs1.getString("city")+" "+rs1.getString("zip_code"));
                Tic.txtEmail.setText(rs1.getString("client_email_address"));
                Tic.txtCellPho.setText(rs1.getString("mobile_phone"));
                Tic.txtWorkPho.setText(rs1.getString("office_phone"));
                Tic.txtcusfax.setText(rs1.getString("office_fax"));
                cus2[0]=(rs1.getString("client_name"));
                cus2[1]=(rs1.getString("client_address")+"\n"+rs1.getString("city")+" "+rs1.getString("zip_code"));
                cus2[2]=(rs1.getString("client_email_address"));
                cus2[3]=(rs1.getString("mobile_phone"));
                cus2[4]=(rs1.getString("office_phone"));
                cus2[5]=(rs1.getString("office_fax"));
            }
            sql1="delete from ws_detail_customer where request_id="+wsid+" ";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblPolicyNo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBranchName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblPI1 = new javax.swing.JLabel();
        lblPI2 = new javax.swing.JLabel();
        lblPI3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtInsured = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtReqNm = new javax.swing.JTextField();
        txtCob = new javax.swing.JTextField();
        txtPTo = new javax.swing.JTextField();
        txtPFrom = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcus = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcus1 = new javax.swing.JTable();
        btnClaim = new javax.swing.JButton();
        btnOpenTic = new javax.swing.JButton();
        btnEmbed = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("POLICY - DETAIL");
        setFont(new java.awt.Font("Tahoma", 1, 11));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setLayout(null);

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel1.setText("POLICY INFORMATION");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 160, 13);

        lblPolicyNo.setFont(lblPolicyNo.getFont().deriveFont(lblPolicyNo.getFont().getStyle() | java.awt.Font.BOLD, 11));
        lblPolicyNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPolicyNoMouseClicked(evt);
            }
        });
        jPanel2.add(lblPolicyNo);
        lblPolicyNo.setBounds(190, 40, 260, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(" : ");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(180, 40, 10, 20);

        jLabel4.setFont(jLabel4.getFont().deriveFont((float)11));
        jLabel4.setText("BRANCH NAME");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(0, 20, 170, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(" : ");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(180, 20, 10, 20);

        txtBranchName.setFont(txtBranchName.getFont().deriveFont((float)11));
        txtBranchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBranchNameActionPerformed(evt);
            }
        });
        jPanel2.add(txtBranchName);
        txtBranchName.setBounds(190, 20, 260, 24);

        jLabel6.setFont(jLabel6.getFont().deriveFont((float)11));
        jLabel6.setText("POLICY NUMBER");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(0, 40, 170, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText(" : ");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(680, 40, 10, 20);

        jLabel8.setFont(jLabel8.getFont().deriveFont((float)11));
        jLabel8.setText("NET PREMI ( in IDR)");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(500, 20, 180, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(" : ");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(680, 20, 10, 20);

        jLabel10.setFont(jLabel10.getFont().deriveFont((float)11));
        jLabel10.setText("TOTAL CLAIM ( in IDR)");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(500, 40, 180, 20);

        jLabel11.setFont(jLabel11.getFont().deriveFont((float)11));
        jLabel11.setText("LOSS RATIO");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(500, 60, 180, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel12.setText(" : ");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(680, 60, 10, 20);

        lblPI1.setFont(lblPI1.getFont().deriveFont((float)11));
        lblPI1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(lblPI1);
        lblPI1.setBounds(690, 20, 270, 20);

        lblPI2.setFont(lblPI2.getFont().deriveFont((float)11));
        lblPI2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(lblPI2);
        lblPI2.setBounds(690, 40, 270, 20);

        lblPI3.setFont(lblPI3.getFont().deriveFont(lblPI3.getFont().getStyle() | java.awt.Font.BOLD, 11));
        lblPI3.setForeground(new java.awt.Color(255, 0, 51));
        lblPI3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(lblPI3);
        lblPI3.setBounds(690, 60, 270, 20);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 11, 980, 80);

        jPanel3.setLayout(null);

        jLabel16.setFont(jLabel16.getFont().deriveFont(jLabel16.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel16.setText(" 01. GENERAL INFORMATION");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(0, 0, 160, 13);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel18.setText(" : ");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(180, 40, 10, 20);

        jLabel19.setFont(jLabel19.getFont().deriveFont((float)11));
        jLabel19.setText("REQUESTOR NAME");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(0, 20, 170, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel20.setText(" : ");
        jPanel3.add(jLabel20);
        jLabel20.setBounds(180, 20, 10, 20);

        txtInsured.setFont(txtInsured.getFont().deriveFont((float)11));
        jPanel3.add(txtInsured);
        txtInsured.setBounds(190, 40, 260, 24);

        jLabel21.setFont(jLabel21.getFont().deriveFont((float)11));
        jLabel21.setText("THE INSURED");
        jPanel3.add(jLabel21);
        jLabel21.setBounds(0, 40, 170, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel22.setText(" : ");
        jPanel3.add(jLabel22);
        jLabel22.setBounds(680, 40, 10, 20);

        jLabel23.setFont(jLabel23.getFont().deriveFont((float)11));
        jLabel23.setText("C.O.B");
        jPanel3.add(jLabel23);
        jLabel23.setBounds(500, 20, 180, 20);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel24.setText(" : ");
        jPanel3.add(jLabel24);
        jLabel24.setBounds(680, 20, 10, 20);

        jLabel25.setFont(jLabel25.getFont().deriveFont((float)11));
        jLabel25.setText("POLICY PERIOD");
        jPanel3.add(jLabel25);
        jLabel25.setBounds(500, 40, 180, 20);

        txtReqNm.setFont(txtReqNm.getFont().deriveFont((float)11));
        jPanel3.add(txtReqNm);
        txtReqNm.setBounds(190, 20, 260, 24);

        txtCob.setFont(txtCob.getFont().deriveFont((float)11));
        jPanel3.add(txtCob);
        txtCob.setBounds(690, 20, 270, 24);

        txtPTo.setFont(txtPTo.getFont().deriveFont((float)11));
        txtPTo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtPTo);
        txtPTo.setBounds(850, 40, 110, 24);

        txtPFrom.setFont(txtPFrom.getFont().deriveFont((float)11));
        txtPFrom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtPFrom);
        txtPFrom.setBounds(690, 40, 110, 24);

        jLabel26.setFont(jLabel26.getFont().deriveFont((float)11));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("TO");
        jPanel3.add(jLabel26);
        jLabel26.setBounds(810, 40, 20, 20);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel27.setText(" : ");
        jPanel3.add(jLabel27);
        jLabel27.setBounds(830, 40, 10, 20);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 100, 980, 80);

        jPanel4.setLayout(null);

        jLabel17.setFont(jLabel17.getFont().deriveFont(jLabel17.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel17.setText(" 02. NOTES INFO");
        jPanel4.add(jLabel17);
        jLabel17.setBounds(0, 0, 160, 13);

        tblcus.setFont(tblcus.getFont().deriveFont((float)11));
        tblcus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "ED.", "DOCUMENT NUMBER", "TYPE", "TRN. DATE", "STS", "CUR", "AMOUNT", "PAID AMOUNT", "PYMT. DATE", "PAYMENT DOC. NUMBER"
            }
        ));
        jScrollPane2.setViewportView(tblcus);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(0, 20, 980, 180);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 190, 980, 200);

        jPanel5.setLayout(null);

        jLabel28.setFont(jLabel28.getFont().deriveFont(jLabel28.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel28.setText(" 04. CLAIM HISTORY");
        jPanel5.add(jLabel28);
        jLabel28.setBounds(0, 0, 160, 13);

        tblcus1.setFont(tblcus1.getFont().deriveFont((float)11));
        tblcus1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "REG. NO.", "REG. DATE", "DATE OF LOSS", "CUR", "LOSS EST./ CLAIM PAID", "STATUS"
            }
        ));
        jScrollPane1.setViewportView(tblcus1);

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(0, 20, 980, 140);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 400, 980, 160);

        btnClaim.setFont(btnClaim.getFont().deriveFont(btnClaim.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnClaim.setText("CLAIM");
        btnClaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClaimActionPerformed(evt);
            }
        });
        jPanel1.add(btnClaim);
        btnClaim.setBounds(10, 560, 80, 24);

        btnOpenTic.setFont(btnOpenTic.getFont().deriveFont(btnOpenTic.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnOpenTic.setText("Open Ticket");
        btnOpenTic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenTicActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpenTic);
        btnOpenTic.setBounds(890, 560, 100, 24);

        btnEmbed.setFont(btnEmbed.getFont().deriveFont(btnEmbed.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnEmbed.setText("Embed");
        btnEmbed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmbedActionPerformed(evt);
            }
        });
        jPanel1.add(btnEmbed);
        btnEmbed.setBounds(790, 560, 100, 24);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblPolicyNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPolicyNoMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            try{
                sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                        + ",username='" + CCanj.lbluser.getText() + "'"
                        + ",function_id=8"
                        + ",ws_params='"+lblPolicyNo.getText()+".'"
                        + "";
                CCanj.jconn.SQLExecute(sql1, CCanj.conn);
                sqlid = "select distinct last_insert_id() from ws_request";
                rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
                while (rs.next()) {
                    wsid1 = Integer.parseInt(rs.getString(1));
                    ws1 = true;
                }
                System.out.println("\n wsid1 : "+wsid1);

                ccasw_View_Policy VIPO=new ccasw_View_Policy();
                VIPO.setVisible(true);
                if(ws1=true){
                    VIPO.ws=ws1;
                    VIPO.wsid=wsid1;
                    VIPO.Form=Form;
                    VIPO.nopolis=lblPolicyNo.getText();
                    VIPO.client_id=client_id;
                    VIPO.cek();
                    VIPO.request();
                }
            }catch (SQLException ex) {
                Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblPolicyNoMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        wsws.stop();tabcus.setRowCount(0);tabcus1.setRowCount(0);
    }//GEN-LAST:event_formWindowClosed

    private void txtBranchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBranchNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBranchNameActionPerformed

    private void btnOpenTicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenTicActionPerformed
        // TODO add your handling code here:
        ws1=true;
        try{
            sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                    + ",username='" + CCanj.lbluser.getText() + "'"
                    + ",function_id=2"
                    + ",ws_params='"+client_id+".'"
                    + "";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
            sqlid = "select distinct last_insert_id() from ws_request";
            rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
            while (rs.next()) {
                wsid1 = Integer.parseInt(rs.getString(1));
                ws = true;
            }
            System.out.println("\n wsid1 : "+wsid1);
            wsid=wsid1;
            ccasw_ticket tic=new ccasw_ticket();
            tic.setVisible(true);
            tic.txtNoPolis.setText(lblPolicyNo.getText());
            request();
            tic.txtcusnam.setText(cus2[0]);
            tic.txtcustadd.setText(cus2[1]);
            tic.txtEmail.setText(cus2[2]);
            tic.txtCellPho.setText(cus2[3]);
            tic.txtWorkPho.setText(cus2[4]);
            tic.txtcusfax.setText(cus2[5]);
        }catch (SQLException ex) {
            Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnOpenTicActionPerformed

    private void btnEmbedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmbedActionPerformed
        // TODO add your handling code here:
        ws1=true;
        try{
            Tic.txtNoPolis.setText(lblPolicyNo.getText());

            sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                    + ",username='" + CCanj.lbluser.getText() + "'"
                    + ",function_id=2"
                    + ",ws_params='"+client_id+".'"
                    + "";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
            sqlid = "select distinct last_insert_id() from ws_request";
            rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
            while (rs.next()) {
                wsid1 = Integer.parseInt(rs.getString(1));
                ws = true;
            }
            System.out.println("\n wsid1 : "+wsid1);
            wsid=wsid1;
            request();
        }catch (SQLException ex) {
            Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnEmbedActionPerformed

    private void btnClaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClaimActionPerformed
        // TODO add your handling code here:
        btnClaim.setEnabled(false);
        ccasw_claim_registration srclaim = new ccasw_claim_registration();
        srclaim.setVisible(true);
    }//GEN-LAST:event_btnClaimActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_Police_Detail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnClaim;
    public static javax.swing.JButton btnEmbed;
    public static javax.swing.JButton btnOpenTic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblPI1;
    public static javax.swing.JLabel lblPI2;
    public static javax.swing.JLabel lblPI3;
    public static javax.swing.JLabel lblPolicyNo;
    private javax.swing.JTable tblcus;
    private javax.swing.JTable tblcus1;
    public static javax.swing.JTextField txtBranchName;
    public static javax.swing.JTextField txtCob;
    public static javax.swing.JTextField txtInsured;
    public static javax.swing.JTextField txtPFrom;
    public static javax.swing.JTextField txtPTo;
    public static javax.swing.JTextField txtReqNm;
    // End of variables declaration//GEN-END:variables

    private static String sql,sql1,sql2,sqlid;
    private static ResultSet rs,rs1,rs2,rs3;

    Action wsinbound = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            webservice();
        }
    };

    private void webservice(){
        try{
            sql="select ws_status from ws_request where request_id="+wsid+" ";
            rs1=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            while(rs1.next()){
                wsstt=Integer.parseInt(rs1.getString(1));
            }
            System.out.println("\n status ws : "+wsstt);
            process();
            if(wsstt==2){
                if(ws1==false){
                    detilcus();System.out.println(" load detil : "+wsid);
                    tabelcus();System.out.println(" load notes : "+wsid);
                    tabelcus1();System.out.println(" load claim history : "+wsid);
                }else{
                     detilcus2();
                     dispose();
                }
                wsws.stop();
                Prg.dialog.hide();
                ws1=false;
            }else if(wsstt==3){
                wsws.stop();
                Prg.dialog.hide();
                JOptionPane.showMessageDialog(null, "Data not found","Error!!!",JOptionPane.WARNING_MESSAGE);
                dispose();
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public void request(){
        if(ws=true&&wsid!=-1){
            Prg.value=0;
            wsws.start();
//            dialog.show();
            Prg.dialog.show();
            Prg.pasw.setValue(0);
        }
    }
    public void process(){
        Prg.value=Prg.pasw.getValue() + 10;
        if(Prg.value ==100){
            Prg.value=0;
        }
        Prg.pasw.setValue(Prg.value);
        if(Prg.dialog.isShowing()==false){
            wsws.stop();
        }
    }
    public static void cek(){
        if(Form==1){
            btnEmbed.setVisible(true);
            btnOpenTic.setEnabled(false);
        }
    }
}
