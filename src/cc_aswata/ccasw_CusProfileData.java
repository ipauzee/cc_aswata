/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ccasw_CusProfileData.java
 *
 * Created on Dec 17, 2012, 3:13:28 PM
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
public class ccasw_CusProfileData extends javax.swing.JFrame {
    private static String cus[]=new String[21];
    private static String client_id;
    public static int wsid=1,wsid1=1,wsstt=0,Form=0;
    public static boolean ws=false,ws1=false,transaction=false,update=false;
    Timer wsws;

    /** Creates new form ccasw_CusProfileData */
    public ccasw_CusProfileData() {
        initComponents();
        tblcus.setModel(tabcus);
        tbcus(tblcus,new int []{40,200,120,40,160,160});
        group.add(rd1);        group.add(rd2);        group.add(rd3);        group.add(rd4);
        setSize(810,580);
        pnlTran.setVisible(false);
        transaction=false;
        wsws=new Timer(1000, wsinbound);
    }

    public static ContactCenterASWATA CCanj;
    public ccasw_CusProfileData(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
    }
    private ccasw_ProgressBar Prg;
    public ccasw_CusProfileData(ccasw_ProgressBar prg){
        this();
        this.Prg=prg;
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelcus(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"No.","Policy","Expire Date","Cur","Premium Amount","O/S Premium"}){
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
            sql="select * from ws_transaction where request_id="+wsid1+" ";
            rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
//            System.out.println(sql);

            while(rs.next()){
                cus[0]=rs.getString("no");
                cus[1]=rs.getString("policy_no");
                cus[2]=rs.getString("expiry_date");
                cus[3]=rs.getString("currency");
                cus[4]=rs.getString("premium_amount");
                cus[5]=rs.getString("os_premium");
                tabcus.addRow(cus);
            }
            sql1="delete from ws_transaction where request_id="+wsid1+" ";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static void detilcus(){
        try{
            sql="select * from ws_detail_customer where request_id="+wsid+" ";
            rs1=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
//            System.out.println(sql);

            while(rs1.next()){
                lblDC1.setText(rs1.getString("client_id"));
                lblDC2.setText(rs1.getString("client_name"));
                lblDC3.setText(rs1.getString("date_of_birth"));
                lblDC4.setText(rs1.getString("contact_person"));
                lblDC5.setText(rs1.getString("client_address"));
                txtDC5.setText(rs1.getString("client_address"));
                lblDC6.setText(rs1.getString("city"));
                lblDC7.setText(rs1.getString("zip_code"));
                lblDC8.setText(rs1.getString("client_mail_address"));
                lblDC9.setText(rs1.getString("client_email_address"));
                txtDC9.setText(rs1.getString("client_email_address"));
                lblDC10.setText(rs1.getString("house_phone")+" / "+rs1.getString("mobile_phone"));
                txtDC6.setText(rs1.getString("mobile_phone"));txtDC7.setText(rs1.getString("house_phone"));txtDC8.setText(rs1.getString("office_phone"));
                lblDC11.setText(rs1.getString("office_phone")+" / "+rs1.getString("office_fax"));
                lblDC12.setText(rs1.getString("client_type"));
                lblDC13.setText(rs1.getString("segment"));
                lblDC14.setText(rs1.getString("account_manager"));
                lblDC15.setText(rs1.getString("client_territory"));
                if(rs1.getString("client_profile").equals("Potential")){
                    rd4.setSelected(true);
                }else if(rs1.getString("client_profile").equals("Black List")){
                    rd3.setSelected(true);
                }else if(rs1.getString("client_profile").equals("Non Active")){
                    rd2.setSelected(true);
                }else if(rs1.getString("client_profile").equals("Active")){
                    rd1.setSelected(true);
                }else{
                    rd1.setSelected(false);rd2.setSelected(false);rd3.setSelected(false);rd4.setSelected(false);
                }
                txtComment.setText(rs1.getString("comment"));
            }
            sql1="delete from ws_detail_customer where request_id="+wsid+" ";
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

        group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnTog2 = new javax.swing.JToggleButton();
        btnTog1 = new javax.swing.JToggleButton();
        pnlData = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblDC1 = new javax.swing.JLabel();
        lblDC2 = new javax.swing.JLabel();
        lblDC3 = new javax.swing.JLabel();
        lblDC4 = new javax.swing.JLabel();
        lblDC5 = new javax.swing.JLabel();
        lblDC6 = new javax.swing.JLabel();
        lblDC7 = new javax.swing.JLabel();
        lblDC8 = new javax.swing.JLabel();
        lblDC9 = new javax.swing.JLabel();
        lblDC10 = new javax.swing.JLabel();
        lblDC11 = new javax.swing.JLabel();
        lblDC12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblDC13 = new javax.swing.JLabel();
        lblDC14 = new javax.swing.JLabel();
        lblDC15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        rd1 = new javax.swing.JRadioButton();
        rd2 = new javax.swing.JRadioButton();
        rd3 = new javax.swing.JRadioButton();
        rd4 = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pnlUpdate = new javax.swing.JPanel();
        txtDC5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtDC9 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtDC6 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtDC7 = new javax.swing.JTextField();
        txtDC8 = new javax.swing.JTextField();
        pnlTran = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcus = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CUSTOMER PROFILE");
        setFont(new java.awt.Font("Tahoma", 1, 11));
        setMinimumSize(new java.awt.Dimension(815, 480));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        btnTog2.setText("TRANSACTION DATA");
        btnTog2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTog2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnTog2);
        btnTog2.setBounds(540, 0, 150, 24);

        btnTog1.setSelected(true);
        btnTog1.setText("CUSTOMER DATA");
        btnTog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTog1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnTog1);
        btnTog1.setBounds(120, 0, 140, 24);

        pnlData.setBackground(new java.awt.Color(255, 255, 255));
        pnlData.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CUSTOMER DATA ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        pnlData.setLayout(null);

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel1.setText("CLIENT NAME");
        pnlData.add(jLabel1);
        jLabel1.setBounds(20, 40, 150, 20);

        jLabel2.setFont(jLabel2.getFont().deriveFont((float)11));
        jLabel2.setText(" : ");
        pnlData.add(jLabel2);
        jLabel2.setBounds(170, 40, 10, 20);

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel3.setText("CONTACT PERSON");
        pnlData.add(jLabel3);
        jLabel3.setBounds(20, 80, 150, 20);

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel4.setText("DATE OF BIRTH");
        pnlData.add(jLabel4);
        jLabel4.setBounds(20, 60, 150, 20);

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel5.setText("ZIP CODE");
        pnlData.add(jLabel5);
        jLabel5.setBounds(20, 140, 150, 20);

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel7.setText("CLIENT EMAIL ADDRESS");
        pnlData.add(jLabel7);
        jLabel7.setBounds(20, 180, 150, 20);

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel8.setText("CLIENT MAIL ADDRESS");
        pnlData.add(jLabel8);
        jLabel8.setBounds(20, 160, 150, 20);

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel9.setText("OFFICE PHONE / FAX");
        pnlData.add(jLabel9);
        jLabel9.setBounds(20, 220, 150, 20);

        jLabel10.setFont(jLabel10.getFont().deriveFont(jLabel10.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel10.setText("HOUSE PHONE / CELL NO");
        pnlData.add(jLabel10);
        jLabel10.setBounds(20, 200, 150, 20);

        jLabel11.setFont(jLabel11.getFont().deriveFont(jLabel11.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel11.setText("SEGMENT");
        pnlData.add(jLabel11);
        jLabel11.setBounds(20, 260, 150, 20);

        jLabel12.setFont(jLabel12.getFont().deriveFont(jLabel12.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel12.setText("CLIENT TYPE");
        pnlData.add(jLabel12);
        jLabel12.setBounds(20, 240, 150, 20);

        jLabel13.setFont(jLabel13.getFont().deriveFont(jLabel13.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel13.setText("CLIENT TERRITORY");
        pnlData.add(jLabel13);
        jLabel13.setBounds(20, 300, 150, 20);

        jLabel14.setFont(jLabel14.getFont().deriveFont(jLabel14.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel14.setText("ACCOUNT MANAGER");
        pnlData.add(jLabel14);
        jLabel14.setBounds(20, 280, 150, 20);

        jLabel15.setFont(jLabel15.getFont().deriveFont(jLabel15.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel15.setText("COMMENT");
        pnlData.add(jLabel15);
        jLabel15.setBounds(20, 340, 150, 20);

        jLabel16.setFont(jLabel16.getFont().deriveFont(jLabel16.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel16.setText("CLIENT PROFILE");
        pnlData.add(jLabel16);
        jLabel16.setBounds(20, 320, 150, 20);

        jLabel18.setFont(jLabel18.getFont().deriveFont((float)11));
        jLabel18.setText(" : ");
        pnlData.add(jLabel18);
        jLabel18.setBounds(170, 20, 10, 20);

        jLabel19.setFont(jLabel19.getFont().deriveFont((float)11));
        jLabel19.setText(" : ");
        pnlData.add(jLabel19);
        jLabel19.setBounds(170, 80, 10, 20);

        jLabel20.setFont(jLabel20.getFont().deriveFont((float)11));
        jLabel20.setText(" : ");
        pnlData.add(jLabel20);
        jLabel20.setBounds(170, 60, 10, 20);

        jLabel21.setFont(jLabel21.getFont().deriveFont((float)11));
        jLabel21.setText(" : ");
        pnlData.add(jLabel21);
        jLabel21.setBounds(170, 140, 10, 20);

        jLabel23.setFont(jLabel23.getFont().deriveFont((float)11));
        jLabel23.setText(" : ");
        pnlData.add(jLabel23);
        jLabel23.setBounds(170, 180, 10, 20);

        jLabel24.setFont(jLabel24.getFont().deriveFont((float)11));
        jLabel24.setText(" : ");
        pnlData.add(jLabel24);
        jLabel24.setBounds(170, 160, 10, 20);

        jLabel25.setFont(jLabel25.getFont().deriveFont((float)11));
        jLabel25.setText(" : ");
        pnlData.add(jLabel25);
        jLabel25.setBounds(170, 220, 10, 20);

        jLabel26.setFont(jLabel26.getFont().deriveFont((float)11));
        jLabel26.setText(" : ");
        pnlData.add(jLabel26);
        jLabel26.setBounds(170, 200, 10, 20);

        jLabel27.setFont(jLabel27.getFont().deriveFont((float)11));
        jLabel27.setText(" : ");
        pnlData.add(jLabel27);
        jLabel27.setBounds(170, 260, 10, 20);

        jLabel28.setFont(jLabel28.getFont().deriveFont((float)11));
        jLabel28.setText(" : ");
        pnlData.add(jLabel28);
        jLabel28.setBounds(170, 240, 10, 20);

        jLabel29.setFont(jLabel29.getFont().deriveFont((float)11));
        jLabel29.setText(" : ");
        pnlData.add(jLabel29);
        jLabel29.setBounds(170, 300, 10, 20);

        jLabel30.setFont(jLabel30.getFont().deriveFont((float)11));
        jLabel30.setText(" : ");
        pnlData.add(jLabel30);
        jLabel30.setBounds(170, 280, 10, 20);

        jLabel31.setFont(jLabel31.getFont().deriveFont((float)11));
        jLabel31.setText(" : ");
        pnlData.add(jLabel31);
        jLabel31.setBounds(170, 340, 10, 20);

        jLabel32.setFont(jLabel32.getFont().deriveFont((float)11));
        jLabel32.setText(" : ");
        pnlData.add(jLabel32);
        jLabel32.setBounds(170, 320, 10, 20);

        jLabel33.setFont(jLabel33.getFont().deriveFont(jLabel33.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel33.setText("CLIENT ID");
        pnlData.add(jLabel33);
        jLabel33.setBounds(20, 20, 150, 20);

        lblDC1.setFont(lblDC1.getFont().deriveFont((float)11));
        pnlData.add(lblDC1);
        lblDC1.setBounds(180, 20, 550, 20);

        lblDC2.setFont(lblDC2.getFont().deriveFont((float)11));
        pnlData.add(lblDC2);
        lblDC2.setBounds(180, 40, 550, 20);

        lblDC3.setFont(lblDC3.getFont().deriveFont((float)11));
        pnlData.add(lblDC3);
        lblDC3.setBounds(180, 60, 550, 20);

        lblDC4.setFont(lblDC4.getFont().deriveFont((float)11));
        pnlData.add(lblDC4);
        lblDC4.setBounds(180, 80, 550, 20);

        lblDC5.setFont(lblDC5.getFont().deriveFont((float)11));
        pnlData.add(lblDC5);
        lblDC5.setBounds(180, 100, 550, 20);

        lblDC6.setFont(lblDC6.getFont().deriveFont((float)11));
        pnlData.add(lblDC6);
        lblDC6.setBounds(180, 120, 550, 20);

        lblDC7.setFont(lblDC7.getFont().deriveFont((float)11));
        pnlData.add(lblDC7);
        lblDC7.setBounds(180, 140, 550, 20);

        lblDC8.setFont(lblDC8.getFont().deriveFont((float)11));
        pnlData.add(lblDC8);
        lblDC8.setBounds(180, 160, 550, 20);

        lblDC9.setFont(lblDC9.getFont().deriveFont((float)11));
        pnlData.add(lblDC9);
        lblDC9.setBounds(180, 180, 550, 20);

        lblDC10.setFont(lblDC10.getFont().deriveFont((float)11));
        lblDC10.setText("- / -");
        pnlData.add(lblDC10);
        lblDC10.setBounds(180, 200, 550, 20);

        lblDC11.setFont(lblDC11.getFont().deriveFont((float)11));
        lblDC11.setText("- / -");
        pnlData.add(lblDC11);
        lblDC11.setBounds(180, 220, 550, 20);

        lblDC12.setFont(lblDC12.getFont().deriveFont((float)11));
        pnlData.add(lblDC12);
        lblDC12.setBounds(180, 240, 550, 20);

        jLabel17.setFont(jLabel17.getFont().deriveFont(jLabel17.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel17.setText("CITY");
        pnlData.add(jLabel17);
        jLabel17.setBounds(20, 120, 150, 20);

        jLabel34.setFont(jLabel34.getFont().deriveFont((float)11));
        jLabel34.setText(" : ");
        pnlData.add(jLabel34);
        jLabel34.setBounds(170, 120, 10, 20);

        lblDC13.setFont(lblDC13.getFont().deriveFont((float)11));
        pnlData.add(lblDC13);
        lblDC13.setBounds(180, 260, 550, 20);

        lblDC14.setFont(lblDC14.getFont().deriveFont((float)11));
        pnlData.add(lblDC14);
        lblDC14.setBounds(180, 280, 550, 20);

        lblDC15.setFont(lblDC15.getFont().deriveFont((float)11));
        pnlData.add(lblDC15);
        lblDC15.setBounds(180, 300, 550, 20);

        txtComment.setColumns(20);
        txtComment.setEditable(false);
        txtComment.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtComment.setRows(5);
        jScrollPane1.setViewportView(txtComment);

        pnlData.add(jScrollPane1);
        jScrollPane1.setBounds(180, 340, 550, 50);

        rd1.setBackground(new java.awt.Color(255, 255, 255));
        rd1.setFont(rd1.getFont().deriveFont((float)11));
        rd1.setText("Active");
        pnlData.add(rd1);
        rd1.setBounds(180, 320, 93, 23);

        rd2.setBackground(new java.awt.Color(255, 255, 255));
        rd2.setFont(rd2.getFont().deriveFont((float)11));
        rd2.setText("Non Active");
        pnlData.add(rd2);
        rd2.setBounds(270, 320, 90, 23);

        rd3.setBackground(new java.awt.Color(255, 255, 255));
        rd3.setText("Black List");
        pnlData.add(rd3);
        rd3.setBounds(380, 320, 90, 21);

        rd4.setBackground(new java.awt.Color(255, 255, 255));
        rd4.setFont(rd4.getFont().deriveFont((float)11));
        rd4.setText("Potential");
        pnlData.add(rd4);
        rd4.setBounds(480, 320, 90, 23);

        jLabel36.setFont(jLabel36.getFont().deriveFont((float)11));
        jLabel36.setText(" : ");
        pnlData.add(jLabel36);
        jLabel36.setBounds(170, 100, 10, 20);

        jLabel35.setFont(jLabel35.getFont().deriveFont(jLabel35.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel35.setText("CLIENT ADDRESS");
        pnlData.add(jLabel35);
        jLabel35.setBounds(20, 100, 150, 20);

        jPanel1.add(pnlData);
        pnlData.setBounds(20, 20, 760, 410);

        pnlUpdate.setBackground(new java.awt.Color(255, 255, 255));
        pnlUpdate.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlUpdate.setLayout(null);

        txtDC5.setFont(txtDC5.getFont().deriveFont((float)11));
        pnlUpdate.add(txtDC5);
        txtDC5.setBounds(180, 10, 550, 25);

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel6.setText("CLIENT ADDRESS");
        pnlUpdate.add(jLabel6);
        jLabel6.setBounds(20, 10, 150, 20);

        jLabel22.setFont(jLabel22.getFont().deriveFont((float)11));
        jLabel22.setText(" : ");
        pnlUpdate.add(jLabel22);
        jLabel22.setBounds(170, 10, 10, 20);

        txtDC9.setFont(txtDC9.getFont().deriveFont((float)11));
        pnlUpdate.add(txtDC9);
        txtDC9.setBounds(180, 30, 550, 25);

        jLabel38.setFont(jLabel38.getFont().deriveFont((float)11));
        jLabel38.setText(" : ");
        pnlUpdate.add(jLabel38);
        jLabel38.setBounds(170, 30, 10, 20);

        jLabel37.setFont(jLabel37.getFont().deriveFont(jLabel37.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel37.setText("CLIENT EMAIL ADDRESS");
        pnlUpdate.add(jLabel37);
        jLabel37.setBounds(20, 30, 150, 20);

        txtDC6.setFont(txtDC6.getFont().deriveFont((float)11));
        pnlUpdate.add(txtDC6);
        txtDC6.setBounds(560, 50, 170, 25);

        jLabel40.setFont(jLabel40.getFont().deriveFont((float)11));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("/");
        pnlUpdate.add(jLabel40);
        jLabel40.setBounds(540, 50, 20, 20);

        jLabel39.setFont(jLabel39.getFont().deriveFont(jLabel39.getFont().getStyle() & ~java.awt.Font.BOLD, 10));
        jLabel39.setText("HOUSE / OFFICE /CELL PHONE");
        pnlUpdate.add(jLabel39);
        jLabel39.setBounds(20, 50, 150, 20);

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlUpdate.add(jButton1);
        jButton1.setBounds(623, 70, 110, 23);

        jLabel41.setFont(jLabel41.getFont().deriveFont((float)11));
        jLabel41.setText(" : ");
        pnlUpdate.add(jLabel41);
        jLabel41.setBounds(170, 50, 10, 20);

        jLabel42.setFont(jLabel42.getFont().deriveFont((float)11));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("/");
        pnlUpdate.add(jLabel42);
        jLabel42.setBounds(350, 50, 20, 20);

        txtDC7.setFont(txtDC7.getFont().deriveFont((float)11));
        pnlUpdate.add(txtDC7);
        txtDC7.setBounds(180, 50, 170, 25);

        txtDC8.setFont(txtDC8.getFont().deriveFont((float)11));
        pnlUpdate.add(txtDC8);
        txtDC8.setBounds(370, 50, 170, 25);

        jPanel1.add(pnlUpdate);
        pnlUpdate.setBounds(20, 440, 760, 100);

        pnlTran.setBackground(new java.awt.Color(255, 255, 255));
        pnlTran.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSACTION DATA ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        pnlTran.setLayout(null);

        jLabel66.setFont(jLabel66.getFont().deriveFont(jLabel66.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel66.setText("LIST OF POLICY");
        pnlTran.add(jLabel66);
        jLabel66.setBounds(20, 30, 150, 20);

        jScrollPane2.setFont(jScrollPane2.getFont().deriveFont(jScrollPane2.getFont().getStyle() & ~java.awt.Font.BOLD, 11));

        tblcus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "POLICY", "EXPIRED DATE", "CUR", "PREMIUM AMOUNT", "O/S PREMIUM"
            }
        ));
        tblcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcusMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblcus);

        pnlTran.add(jScrollPane2);
        jScrollPane2.setBounds(20, 50, 720, 450);

        jPanel1.add(pnlTran);
        pnlTran.setBounds(20, 20, 760, 520);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTog2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTog2ActionPerformed
        // TODO add your handling code here:
        btnTog1.setSelected(false);
        pnlTran.setVisible(true);
        btnTog2.setSelected(true);
        pnlData.setVisible(false);pnlUpdate.setVisible(false);
        if(transaction==false){
            transaction=true;
            sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                    + ",username='" + CCanj.lbluser.getText() + "'"
                    + ",function_id=3"
                    + ",ws_params='"+lblDC1.getText()+"'."
                    + "";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
            sqlid = "select distinct last_insert_id() from log_mail";
            rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
            try {
                while (rs.next()) {
                    wsid1 = Integer.parseInt(rs.getString(1));
                    ws = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ccasw_CusProfileData.class.getName()).log(Level.SEVERE, null, ex);
            }
            request();
        }
    }//GEN-LAST:event_btnTog2ActionPerformed

    private void btnTog1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTog1ActionPerformed
        // TODO add your handling code here:
        btnTog2.setSelected(false);
        pnlData.setVisible(true);pnlUpdate.setVisible(true);
        btnTog1.setSelected(true);
        pnlTran.setVisible(false);
    }//GEN-LAST:event_btnTog1ActionPerformed

    private void tblcusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcusMouseClicked
        // TODO add your handling code here:
        if(tabcus.getRowCount()!=0){
            if(evt.getClickCount()==2){
                try{
                    sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                            + ",username='" + CCanj.lbluser.getText() + "'"
                            + ",function_id=5"
                            + ",ws_params='"+(String)tblcus.getValueAt(tblcus.getSelectedRow(),tblcus.getTableHeader().getColumnModel().getColumnIndex("Policy"))+".'"
                            + "";
                    CCanj.jconn.SQLExecute(sql1, CCanj.conn);
                    sqlid = "select distinct last_insert_id() from ws_request";
                    rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
                    while (rs.next()) {
                        wsid1 = Integer.parseInt(rs.getString(1));
                        ws1 = true;
                    }
                    System.out.println("\n wsid1 : "+wsid1);
                                
                    ccasw_Police_Detail PODE=new ccasw_Police_Detail();
                    PODE.setVisible(true);
                    if(ws1=true){
                        PODE.ws=ws1;
                        PODE.wsid=wsid1;
                        PODE.Form=Form;
                        PODE.client_id=lblDC1.getText();
                        PODE.cek();
                        PODE.request();
                    }

                }catch (SQLException ex) {
                    Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
                }
//                dispose();
            }
        }
    }//GEN-LAST:event_tblcusMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        wsws.stop();tabcus.setRowCount(0);
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        update=true;jButton1.setEnabled(false);
        try{
            sql1 = "insert into ws_request set request_time=CURRENT_TIMESTAMP "
                    + ",username='" + CCanj.lbluser.getText() + "'"
                    + ",function_id=12"
                    + ",ws_params='"+txtDC7.getText()+"|"+txtDC8.getText()+"|"+txtDC6.getText()+"|"+txtDC5.getText()+"|"+txtDC9.getText()+"|"+CCanj.lbluser.getText()+"|"+lblDC1.getText()+".'"
                    + "";
            CCanj.jconn.SQLExecute(sql1, CCanj.conn);
            sqlid = "select distinct last_insert_id() from ws_request";
            rs = CCanj.jconn.SQLExecuteRS(sqlid, CCanj.conn);
            while (rs.next()) {
                wsid1 = Integer.parseInt(rs.getString(1));
                ws1 = true;
                request();
            }
            System.out.println("\n wsid1 update : "+wsid1);
        }catch (SQLException ex) {
            Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_CusProfileData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnTog1;
    private javax.swing.JToggleButton btnTog2;
    private javax.swing.ButtonGroup group;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblDC1;
    public static javax.swing.JLabel lblDC10;
    public static javax.swing.JLabel lblDC11;
    public static javax.swing.JLabel lblDC12;
    public static javax.swing.JLabel lblDC13;
    public static javax.swing.JLabel lblDC14;
    public static javax.swing.JLabel lblDC15;
    public static javax.swing.JLabel lblDC2;
    public static javax.swing.JLabel lblDC3;
    public static javax.swing.JLabel lblDC4;
    public static javax.swing.JLabel lblDC5;
    public static javax.swing.JLabel lblDC6;
    public static javax.swing.JLabel lblDC7;
    public static javax.swing.JLabel lblDC8;
    public static javax.swing.JLabel lblDC9;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlTran;
    private javax.swing.JPanel pnlUpdate;
    public static javax.swing.JRadioButton rd1;
    public static javax.swing.JRadioButton rd2;
    public static javax.swing.JRadioButton rd3;
    public static javax.swing.JRadioButton rd4;
    private javax.swing.JTable tblcus;
    public static javax.swing.JTextArea txtComment;
    public static javax.swing.JTextField txtDC5;
    public static javax.swing.JTextField txtDC6;
    public static javax.swing.JTextField txtDC7;
    public static javax.swing.JTextField txtDC8;
    public static javax.swing.JTextField txtDC9;
    // End of variables declaration//GEN-END:variables

    private static String sql,sql1,sqlid;
    private static ResultSet rs,rs1;

    Action wsinbound = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            webservice();
        }
    };

    private void webservice(){
        if(transaction==true){
            wsid=wsid1;
        }
        try{
            System.out.println("\n status wsid CD : "+wsid);
            sql="select ws_status from ws_request where request_id="+wsid+" ";
            rs1=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
            while(rs1.next()){
                wsstt=Integer.parseInt(rs1.getString(1));
            }
            System.out.println("\n status ws CD : "+wsstt);
            process();
            if(wsstt==2){
                if(transaction==true){                    
                    tabelcus();
                }else{
                    if(update==true){
                        JOptionPane.showMessageDialog(null, "Update Success..!!!","Updating via web services",JOptionPane.INFORMATION_MESSAGE);
                        update=false;jButton1.setEnabled(true);
                    } else{
                        detilcus();
                    }
                }
                Prg.dialog.hide();
                wsws.stop();
            }else if(wsstt==3){
                wsws.stop();
                JOptionPane.showMessageDialog(null, "Data not found","Error!!!",JOptionPane.WARNING_MESSAGE);
                update=false;jButton1.setEnabled(true);
                Prg.dialog.hide();
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
}
