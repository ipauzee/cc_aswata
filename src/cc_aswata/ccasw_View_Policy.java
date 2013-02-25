/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ccasw_View_Policy.java
 *
 * Created on Jan 4, 2013, 11:41:23 AM
 */

package cc_aswata;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Event.*;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Alienware
 */
public class ccasw_View_Policy extends javax.swing.JFrame {
    private static String cus[]=new String[21];
    private static String cus1[]=new String[21];
    private static String xhtml,delhtml;
    public static String client_id,nopolis;
    public static int wsid=1,wsid1=1,wsstt=0,X=0,X1=0,Form=0;
    public static boolean ws=false,ws1=false,transaction=false;
    Timer wsws;
    static Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
//    this.setPreferredSize(new Dimension(400, 300));
    Dimension windowSize = new Dimension(getPreferredSize());
    int wdwLeft = screenSize.width / 2 - windowSize.width / 2;
    int wdwTop = screenSize.height / 2 - windowSize.height / 2;
    
    /** Creates new form ccasw_View_Policy */
    public ccasw_View_Policy() {
        initComponents();
        setSize(1024,768);
//        setLocation(wdwLeft,wdwTop);
        wsws=new Timer(1000, wsinbound);
        btnEmbed.setVisible(false);
    }

    public static ContactCenterASWATA CCanj;
    public ccasw_View_Policy(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
    }
    private ccasw_ProgressBar Prg;
    public ccasw_View_Policy(ccasw_ProgressBar prg){
        this();
        this.Prg=prg;
    }
    public static ccasw_ticket Tic;
    public ccasw_View_Policy(ccasw_ticket tic){
        this();
        this.Tic=tic;
    }

    public static void html(){
        int awal=0,akhir=0;
        try{
            sql="select * from ws_detail_policy where request_id="+wsid+" ";
            rs1=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
//            System.out.println(sql);

            while(rs1.next()){
                html.setText(rs1.getString("html_format"));
                client_id=(rs1.getString(3));
            }
            xhtml = html.getText();
//            delhtml=xhtml.sub
            if (html.getContentType() == "text/html")
                html.setContentType("text/plain"); else
                    html.setContentType("text/html");

            xhtml=xhtml.replaceFirst("// ", "");
            xhtml=xhtml.replaceAll("\\{", "");
            xhtml=xhtml.replaceAll("\\}", "");
            xhtml=xhtml.replaceAll("<link rel=\"stylesheet\" href=\"theme/aswata.css\" type=\"text/css\">", "");
            xhtml=xhtml.replaceAll("<!--PRINT B2C BARU -->", "");
            xhtml=xhtml.replaceAll("<!-- tab POLICY -->", "");
            awal=xhtml.indexOf("<script");akhir=xhtml.indexOf("</script>")+9;

            while(awal!=-1){
//                System.out.println("awal : "+awal+" akhir : "+akhir);
                delhtml=xhtml.substring(awal, akhir);
//                System.out.println("script : "+delhtml);
                xhtml=xhtml.replace(delhtml, "");
                awal=xhtml.indexOf("<script");akhir=xhtml.indexOf("</script>")+9;
    //            Thread.sleep(10000);
    //            System.out.println("html : "+html);
            }
            html.setText(xhtml);
            sql="delete from ws_detail_policy where request_id="+wsid+" ";
            CCanj.jconn.SQLExecute(sql, CCanj.conn);
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
                Tic.txtcusnam.setText(rs1.getString("client_name"));
                Tic.txtcustadd.setText(rs1.getString("client_address")+"\n"+rs1.getString("city")+" "+rs1.getString("zip_code"));
                Tic.txtEmail.setText(rs1.getString("client_email_address"));
                Tic.txtCellPho.setText(rs1.getString("mobile_phone"));
                Tic.txtWorkPho.setText(rs1.getString("office_phone"));
                Tic.txtcusfax.setText(rs1.getString("office_fax"));
                cus[0]=(rs1.getString("client_name"));
                cus[1]=(rs1.getString("client_address")+"\n"+rs1.getString("city")+" "+rs1.getString("zip_code"));
                cus[2]=(rs1.getString("client_email_address"));
                cus[3]=(rs1.getString("mobile_phone"));
                cus[4]=(rs1.getString("office_phone"));
                cus[5]=(rs1.getString("office_fax"));
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

        btnEmbed = new javax.swing.JButton();
        btnOpenTic = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        html = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("VIEW POLICY");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 204, 255));
        setBounds(new java.awt.Rectangle(0, 0, 1024, 768));
        setFont(new java.awt.Font("Tahoma", 0, 11));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(null);

        btnEmbed.setFont(btnEmbed.getFont().deriveFont(btnEmbed.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnEmbed.setText("Embed");
        btnEmbed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmbedActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmbed);
        btnEmbed.setBounds(850, 40, 100, 20);

        btnOpenTic.setFont(btnOpenTic.getFont().deriveFont(btnOpenTic.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnOpenTic.setText("Open Ticket");
        btnOpenTic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenTicActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpenTic);
        btnOpenTic.setBounds(850, 20, 100, 20);

        jPanel1.setMaximumSize(new java.awt.Dimension(1024, 768));
        jPanel1.setLayout(null);

        html.setEditable(false);
        html.setFont(html.getFont().deriveFont((float)11));
        jScrollPane3.setViewportView(html);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(15, 0, 990, 740);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1030, 740);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        wsws.stop();
    }//GEN-LAST:event_formWindowClosed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentResized

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
            tic.txtNoPolis.setText(nopolis);
            request();
            tic.txtcusnam.setText(cus[0]);
            tic.txtcustadd.setText(cus[1]);
            tic.txtEmail.setText(cus[2]);
            tic.txtCellPho.setText(cus[3]);
            tic.txtWorkPho.setText(cus[4]);
            tic.txtcusfax.setText(cus[5]);
        }catch (SQLException ex) {
            Logger.getLogger(ccasw_Search_customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOpenTicActionPerformed

    private void btnEmbedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmbedActionPerformed
        // TODO add your handling code here:
        ws1=true;
        try{
            Tic.txtNoPolis.setText(nopolis);

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

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_View_Policy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnEmbed;
    public static javax.swing.JButton btnOpenTic;
    public static javax.swing.JEditorPane html;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    private static String sql,sql1,sqlid;
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
            System.out.println("\n status ws : "+wsstt+" dari ws id : "+wsid);
            process();
            if(wsstt==2){
                if(ws1==false){ html(); }else{ detilcus(); dispose();}
                wsws.stop();Prg.dialog.hide();
            }else if(wsstt==3){
                wsws.stop();
                Prg.dialog.hide();
                JOptionPane.showMessageDialog(null, "Data not found","Error!!!",JOptionPane.WARNING_MESSAGE);
                dispose();
            }
            ws1=false;
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
            btnOpenTic.setVisible(false);
        }
    }
}
