/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ccasw_Asdept.java
 *
 * Created on Mar 31, 2010, 2:19:47 PM
 */

package cc_aswata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sun.database.JavaConnector;
import javax.swing.JOptionPane;

/**
 *
 * @author jsm
 */
public class ccasw_Asdept extends javax.swing.JFrame {
    private String deptid;
    public static int id;
    public static int notic;
    private String usrlvl;
    private String opdt;
    private String optm;
    public static int statuss;
    private String s;
    public static StringBuffer toSend = new StringBuffer("");

    /** Creates new form ccasw_Asdept */
    public ccasw_Asdept() {
        initComponents();
//        conn=(Connection) jconn.ConnectToMySQL("localhost","cc_anj","root","");
//        conn=(Connection) jconn.ConnectToMySQL("192.168.0.32","cc_anj","cc_anj","123jaring456");
//        conn=(Connection) jconn.ConnectToMySQL("192.168.0.234","cc_anj","cc_anj","123jaring456");
        showDept();
    }

    public static ContactCenterASWATA CCanj;
    public ccasw_Asdept(ContactCenterASWATA ccanj){
        this();
        this.CCanj=ccanj;
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
        jLabel1 = new javax.swing.JLabel();
        cbdept = new javax.swing.JComboBox();
        btnsend = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ticket Assignment");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel1.setText("Department");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 100, 20);

        cbdept.setFont(new java.awt.Font("Calibri", 0, 12));
        cbdept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbdept);
        cbdept.setBounds(10, 30, 250, 24);

        btnsend.setFont(new java.awt.Font("Calibri", 0, 12));
        btnsend.setText("Send");
        btnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendActionPerformed(evt);
            }
        });
        jPanel1.add(btnsend);
        btnsend.setBounds(93, 70, 80, 25);

        Cancel.setFont(new java.awt.Font("Calibri", 0, 12));
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        jPanel1.add(Cancel);
        Cancel.setBounds(177, 70, 80, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        CCanj.asshow=false;
        dispose();
    }//GEN-LAST:event_CancelActionPerformed

    private void btnsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsendActionPerformed
        // TODO add your handling code here:
        getdept();
        opdt();
        optm();
        stt();
        usrlvl();
        sql="update tickets set assign_dept='"+deptid+"' where ticket_id='"+id+"' and ticket_no='"+notic+"' limit 1";
        CCanj.jconn.SQLExecute(sql,CCanj.conn);

        sql1="insert into log_tickets (log_date,log_time,username,user_level,ticket_id,_status,info,ticket_no) values ('"+opdt+"','"+optm+"','"+CCanj.lbluser.getText()+"','"+usrlvl+"','"+id+"','"+statuss+"','send ticket to "+cbdept.getSelectedItem()+"','"+notic+"')";
        CCanj.jconn.SQLExecute(sql1, CCanj.conn);
        s = "TICKET|ASSIGN|"+deptid+"|"+notic+"\r\n";
        sendString(s);
        CCanj.outbroad.print(toSend); CCanj.outbroad.flush();
        toSend.setLength(0);
        CCanj.btnsenddept.setEnabled(false);
        CCanj.asshow=false;
//        JOptionPane.showMessageDialog(null, "Ticket SENT", "Send to Dept.",JOptionPane.WARNING_MESSAGE);
        dispose();
    }//GEN-LAST:event_btnsendActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
//        try {
//            conn.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }//GEN-LAST:event_formWindowClosed

       private static void sendString(String s) {
         synchronized (toSend) {
             toSend.append(s + "\r\n");
      }
   }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ccasw_Asdept().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton btnsend;
    private javax.swing.JComboBox cbdept;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public static String sql;
    public static String sql1;
    public static ResultSet rs;
//    public static JavaConnector jconn=new JavaConnector();
//    public static Connection conn;


     private void showDept()
    {
         cbdept.removeAllItems();
        try{
            cbdept.removeAllItems();
            cbdept.addItem("--");

            sql="select dept_name from _department";
            rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
            while(rs.next()){
                cbdept.addItem(rs.getString(1));
//                cbasdept.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);

        }
      }
     
    private void getdept(){
           try{
               sql="select dept_id from _department where dept_name='"+cbdept.getSelectedItem()+"'";
               rs=CCanj.jconn.SQLExecuteRS(sql,CCanj.conn);
               while(rs.next()){
                   deptid=rs.getString(1);
               }
           }catch(Exception e){
               System.out.print(e);
           }
           }
    private void usrlvl(){
                try{
                    sql="select _level from user_account where username='"+CCanj.lbluser.getText()+"'";
                    rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
                    while(rs.next()){
                        usrlvl=rs.getString(1);
                    }
                }catch(Exception e){
                    System.out.print(e);
                }
            }
    private void opdt(){
          try{
              sql="select CURRENT_DATE";
              rs = CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
              while(rs.next()){
                  opdt=rs.getString(1);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
    }
        private void optm(){
            try{
              sql="select CURRENT_TIME";
              rs = CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
              while(rs.next()){
                  optm=rs.getString(1);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
        }
        private void stt(){
                try{
                    sql="select _status from tickets where ticket_id='"+id+"' and ticket_no='"+notic+"'";
                    rs=CCanj.jconn.SQLExecuteRS(sql, CCanj.conn);
                    while(rs.next()){
                        statuss=Integer.parseInt(rs.getString(1));
                    }
                }catch(Exception e){
                    System.out.print(e);
                }
            }
}
