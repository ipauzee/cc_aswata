/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Search_ticket.java
 *
 * Created on Feb 23, 2010, 4:31:41 PM
 */

package cc_aswata;


import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
/**
 *
 * @author jsm
 */
public class ccasw_Search_customer extends javax.swing.JFrame {
    private static String cus[]=new String[21];
    private static String condition,branchid;
    public static int Form=0,wsid=-1,wsid1=1,wsstt=0;
    public static boolean ws=false,action=false;
    Timer wsws;
    /** Creates new form Search_ticket */
    public ccasw_Search_customer() {
        initComponents();
        tblcus.setModel(tabcus);
        tbcus(tblcus,new int []{40,180,100,100,200,500,80});
        tabcus.setRowCount(0);
        setLocation(0,330);
        wsws=new Timer(1000, wsinbound);
        branch();
    }

    public static ContactCenterASWATA CCanj;
    public ccasw_Search_customer(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
    }
    private ccasw_ticket Tic;
    public ccasw_Search_customer(ccasw_ticket tic){
        this();
        this.Tic=tic;
    }
    private ccasw_InBoundCall Inc;
    public ccasw_Search_customer(ccasw_InBoundCall inc){
        this();
        this.Inc=inc;
    }
    public ccasw_Sms_income Sin;
    public ccasw_Search_customer(ccasw_Sms_income sin){
        this();
        this.Sin=sin;
    }
    public ccasw_Email_incoming Ein;
    public ccasw_Search_customer(ccasw_Email_incoming ein){
        this();
        this.Ein=ein;
    }
    private ccasw_ProgressBar Prg;
    public ccasw_Search_customer(ccasw_ProgressBar prg){
        this();
        this.Prg=prg;
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelcus(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"No.","Clinet Nama","Home Phone No.","Office Phone No.","Branch","Address","Client ID"}){
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
            sql="select *"
//                    + ", _ws_branch.name as brch"
                    + " from ws_search_customer"
//                    + " left join _ws_branch on ws_search_customer.branch=_ws_branch.branch_id"
                    + " where request_id="+wsid+" ";
            rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            System.out.println(sql);

            while(rs.next()){
                cus[0]=rs.getString("no");
                cus[1]=rs.getString("client_name");
                cus[2]=rs.getString("home_phone_no");
                cus[3]=rs.getString("office_phone_no");
                cus[4]=rs.getString("branch_name");
                cus[5]=rs.getString("address");
                cus[6]=rs.getString("client_id");
                tabcus.addRow(cus);
            }
            sql1="delete from ws_search_customer where request_id="+wsid+" ";
            btnsrch.setEnabled(true);
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcus = new javax.swing.JTable();
        btnsrch = new javax.swing.JButton();
        txtPhoneNo = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbBranch = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Customer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Customer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Calibri", 0, 11));
        jPanel1.setLayout(null);

        jScrollPane1.setAutoscrolls(true);

        tblcus.setAutoCreateRowSorter(true);
        tblcus.setFont(tblcus.getFont().deriveFont((float)11));
        tblcus.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcus.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblcus.setDoubleBuffered(true);
        tblcus.setFillsViewportHeight(true);
        tblcus.setMaximumSize(new java.awt.Dimension(2147483647, 72));
        tblcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcus);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(17, 68, 990, 300);

        btnsrch.setFont(btnsrch.getFont().deriveFont(btnsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnsrch.setText("Search Customer");
        btnsrch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsrchMouseClicked(evt);
            }
        });
        btnsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsrchActionPerformed(evt);
            }
        });
        jPanel1.add(btnsrch);
        btnsrch.setBounds(847, 38, 160, 24);

        txtPhoneNo.setFont(txtPhoneNo.getFont().deriveFont((float)11));
        jPanel1.add(txtPhoneNo);
        txtPhoneNo.setBounds(730, 40, 104, 24);

        txtNama.setFont(txtNama.getFont().deriveFont((float)11));
        jPanel1.add(txtNama);
        txtNama.setBounds(220, 40, 180, 24);

        txtAddress.setFont(txtAddress.getFont().deriveFont((float)11));
        jPanel1.add(txtAddress);
        txtAddress.setBounds(410, 40, 310, 24);

        jLabel5.setFont(jLabel5.getFont().deriveFont((float)11));
        jLabel5.setText("Nama");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(220, 30, 80, 10);

        jLabel6.setFont(jLabel6.getFont().deriveFont((float)11));
        jLabel6.setText("Branch");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 30, 80, 10);

        jLabel7.setFont(jLabel7.getFont().deriveFont((float)11));
        jLabel7.setText("Address");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(410, 30, 80, 10);

        jLabel8.setFont(jLabel8.getFont().deriveFont((float)11));
        jLabel8.setText("Phone No");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(730, 30, 80, 10);

        jPanel1.add(cbBranch);
        cbBranch.setBounds(20, 40, 170, 24);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(190, 40, 20, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsrchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsrchMouseClicked
        
    }//GEN-LAST:event_btnsrchMouseClicked

    @SuppressWarnings("static-access")
    private void tblcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcusMouseClicked
        // TODO add your handling code here:
//        ccasw_CusProfileData CEDE=new ccasw_CusProfileData();
//        CEDE.setVisible(true);
        if(tabcus.getRowCount()!=0){
            if(evt.getClickCount()==2){
                try{
//                    switch (Form){
//                        case 1:

    //                        Tic.txtNoPolis.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),0));
    //                        Tic.txtcusnam.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),4));
    //                        Tic.txtcustadd.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),5));
    //                        Tic.txtcusfax.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),6));
    //                        Tic.txtEmail.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),7));
    //                        Tic.txtTowName.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),14));
    //                        Tic.txtTowPho.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),15));
    //                        Tic.txtTowMail.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),16));
    //                        Tic.contid=Integer.parseInt((String)tblcus.getValueAt(tblcus.getSelectedRow(),20));
    //
    //                        Tic.showcus();
//                            Inc.IdCust=((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Client ID")));
//                            Inc.txtIdCust.setText((String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Client ID")));
//                            Inc.tabeltic();
//                            break;
//                        case 2:
                            sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                                    + ",username='" + CCanj.lbluser.getText() + "'"
                                    + ",function_id=2"
                                    + ",ws_params='"+(String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Client ID"))+".'"
                                    + "";
                            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
                            sqlid = "select distinct last_insert_id() from ws_request";
                            rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
                            while (rs.next()) {
                                wsid1 = Integer.parseInt(rs.getString(1));
                                ws = true;                                
                            }
                            System.out.println("\n wsid1 : "+wsid1);
//                            if(ws=true){
//                                wsws.start();
//                            }
                            ccasw_CusProfileData CEDE=new ccasw_CusProfileData();
                            CEDE.setVisible(true);
                            CEDE.wsid=wsid1;
                            CEDE.Form=Form;
                            CEDE.request();
//                            break;
//                        case 3:
//                            break;
//                        case 4:
//                            break;
//                    }
                }catch (SQLException ex) {
                    Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
                }
//                dispose();
            }
        }
    }//GEN-LAST:event_tblcusMouseClicked

    private void btnsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsrchActionPerformed
        // TODO add your handling code here:
        try {
            getbranch();
            btnsrch.setEnabled(false);
            sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                    + ",username='" + CCanj.lbluser.getText() + "'"
                    + ",function_id=1"
                    + ",ws_params='" + branchid + "|" + txtNama.getText() + "|" + txtAddress.getText() + "|" + txtPhoneNo.getText() + ".'"
                    + "";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
            sqlid = "select distinct last_insert_id() from ws_request";
            rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
            while (rs.next()) {
                wsid = Integer.parseInt(rs.getString(1));
                ws = true;
            }
            System.out.println("\n wsid : "+wsid);
            request();
        } catch (SQLException ex) {
            Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsrchActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        wsws.stop();tabcus.setRowCount(0);
        switch (Form) {
            case 1:

                break;

            case 2:
                Inc.wscus=false;
                break;
            case 3:

                break;
        }
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cbBranch.setSelectedIndex(-1);
    }//GEN-LAST:event_jButton1ActionPerformed

   
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_Search_customer().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnsrch;
    private javax.swing.JComboBox cbBranch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcus;
    public static javax.swing.JTextField txtAddress;
    public static javax.swing.JTextField txtNama;
    public static javax.swing.JTextField txtPhoneNo;
    // End of variables declaration//GEN-END:variables

    private static String sql,sql1,sqlid;
    private static ResultSet rs,rs1;

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
            System.out.println("\n status ws : "+wsstt+" dari ws id : "+wsid);
            process();
            if(wsstt==2){
                Prg.pasw.setValue(100);
                System.out.println("\n progress full closing");
                Prg.dialog.hide();btnsrch.setEnabled(true);
                tabelcus();
                wsws.stop();
            }else if(wsstt==3){
                wsws.stop();Prg.dialog.hide();btnsrch.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Data not found","Error!!!",JOptionPane.WARNING_MESSAGE);
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
            wsws.stop();btnsrch.setEnabled(true);
        }
    }

    private void branch(){
        action=false;
        cbBranch.removeAllItems();
        try{
            sql="select name from _ws_branch ";
            rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
            while(rs.next()){
                cbBranch.addItem(rs.getString(1));
            }
            cbBranch.setSelectedIndex(-1);
            action=true;
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void getbranch(){
        branchid="";
        try{
            sql="select branch_id from _ws_branch where name='"+cbBranch.getSelectedItem()+"'";
            rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
            while(rs.next()){
                branchid=(rs.getString(1).toString());
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
