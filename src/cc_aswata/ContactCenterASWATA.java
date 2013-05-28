/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContactCenterASWATA.java
 *
 * Created on Feb 15, 2010, 9:41:37 AM
 */

package cc_aswata;

import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Event.*;
import java.sql.*;
import javax.sun.database.JavaConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.lang.Boolean;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.net.*;
import javax.swing.JFileChooser;

import jxl.*;
import jxl.write.*;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.UIManager.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jsm
 */
public class ContactCenterASWATA extends javax.swing.JInternalFrame {

    public static int counter=0,c=0,m=0,sm=0,fx=0,tt=0,x=0;
    public static boolean inshow=false,outshow=false,ticshow=false,outbound=false,asshow=false;
    public static String s,loid,callid;
    public static boolean teleOn=false, uploOn=false, brcaOn=false, uploOn1=false;
    long elapsed;
    
    // TCP Components
   public static ServerSocket hostServer = null;
   public static ServerSocket hostServer1 = null;
   public static Socket sockettele = null;
   public static Socket socketbroad = null;
   public static Socket socketupload = null;
   public static Socket socketupload1 = null;
   public static BufferedReader intele = null;
   public static BufferedReader inbroad = null;
   public static BufferedReader inupload = null;
   public static BufferedReader inupload1 = null;
   public static PrintWriter outtele = null;
   public static PrintWriter outbroad = null;
   public static PrintWriter outupload = null;
   public static PrintWriter outupload1 = null;
   
   // Connection atate info
   public static String IPtele = "localhost";
   public static int porttele = 6017;
   public static String IPbroad = "192.168.0.20";
   public static int portbroad = 23;
  // public static int connectionStatus = DISCONNECTED;
   public static boolean isHost = true;
   //public static String statusString = statusMessages[connectionStatus];
   public static StringBuffer toAppend = new StringBuffer("");
   public static StringBuffer toSend = new StringBuffer("");

    /** Creates new form ContactCenterASWATA */
    public static String in[]=new String[30];
    public static String ou[]=new String[17];
    public static String in2[]=new String[15];
    public static String in3[]=new String[15];
    public static String ou1[]=new String[15];
    public static String ou3[]=new String[15];
    public static String tic[]=new String[30];
    public static String reptic[]=new String[100];
    public static String repcal[]=new String[40];
    public static String repsms[]=new String[20];
    public static String repmail[]=new String[20];
    public static String repfax[]=new String[20];
    public static String act[]=new String[7];
    public static String hoin[]=new String[20];
    public static String hoou[]=new String[20];
    public static String perfin[]=new String[20];
    public static String perfou[]=new String[20];
    public static String dayin[]=new String[20];
    public static String dayou[]=new String[20];
    String date;
    String oldtext;
    String scroltext;
    String newtext;
    String pass=null;
    String user=null;
    public static String pabx;
    public static String in_ext;
    public static String out_ext;
    public static String lt;
    public static String ld;
    public static int v;
    Timer broad;
    Timer inbo;
    Timer receiv;
    Timer Scrol;
    public static Timer msg;
    private int usrlvl;
    public static String msn[]=new String[6];
    public static String msu[]=new String[6];
    private String msgidin;
    private String msgidou;
    private String tu;

    public ContactCenterASWATA() {
        
        initComponents();
        conn=Log.conn;jconn=Log.jconn;
        setSize(1020,750);
        usrlvl();
//        followUp();
        currentdate();
        tblin.setModel(tabin);
        tblout.setModel(tabou);
        tbltic.setModel(tabtic);
        tblticconf.setModel(tabticconf);
        tblact.setModel(tabact);
        tblmin.setModel(tabmin);
        tblmou.setModel(tabmou);
        tblsin.setModel(tabsin);
        tblsou.setModel(tabsou);
        tblfin.setModel(tabfin);
        tblfou.setModel(tabfou);
        tblreptic.setModel(tabreptic);
        tblrepcal.setModel(tabrepcal);
        tblrepsms.setModel(tabrepsms);
        tblrepmail.setModel(tabrepmail);
        tblrepfax.setModel(tabrepfax);
        tblmsin.setModel(tabmsin);
        tblmsou.setModel(tabmsou);
        tblhourin.setModel(tabhoin);
        tblhourout.setModel(tabhoou);
        tbldailyin.setModel(tabdayin);
        tbldailyout.setModel(tabdayou);
        tblperformin.setModel(tabperfin);
        tblperformout.setModel(tabperfou);
        tbin(tblin,new int []{130,100,70,85,85      ,100,85,160,85,160
                                ,85,160,85,160      ,100,120        ,150,90,90,90,120      ,550,100,150,200});
        tbin(tblout,new int []{130,110,100,100,110,130,110,110});
        tbin(tblmin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblmou,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblsin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblsou,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblfin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblfou,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100});
        tbin(tblreptic,new int []{85,85,120,85,175,150,125                ,125,150,200,200,250
                ,320,100,255,100,100                ,120,120,255,500,500

                ,500,225,80,105,85                ,200,120,150,100,100
                ,150,100,125,120,110                ,100,100,150,100,500

                ,50,60,50,60,50                ,60,50,60,150,150
                ,150,150,150,150,300                ,120,120,120,120,100

                ,100,150,100,100,300                ,500,85,85,90,100
                ,150,85,85,90,100                ,85,85,90,85,100

                ,90,85,150,125,150
                ,85});
        tbin(tblrepcal,new int []{0,85,85,90,75,100,90,90,90,75,75,75,110,90,85,85,85,90,110,120,110,350,300,85,85,85,90,110,175,85,110,160,150});
        tbin(tblticconf,new int []{100,115,100,350,200,100,300,120,250,100,170,120,120,120});
        tbin(tbltic,new int []{100,100,100,120,100      ,150,200,200,150,300,100
                                ,150,250,120,500,100        ,100,100,120,100,500
                                ,50,50,55,55,-1,-1});
        tbin(tblrepsms,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblrepmail,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblrepfax,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblhourin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblhourout,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tbldailyin,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tbldailyout,new int []{100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblperformin,new int []{100,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});
        tbin(tblperformout,new int []{100,100,100,100,100,100
                ,100,100,100,100,100
                ,100,100,100,100,100});  
        opdt();
        optm();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dtdt;
        try {
            dtdt = sdf.parse(opdt.substring(8, 10) + "/" + opdt.substring(5, 7) + "/" + opdt.substring(0, 4));
            dctic1.setDate(dtdt);            dctic2.setDate(dtdt);            dctic3.setDate(dtdt);            dctic4.setDate(dtdt);
            dctic5.setDate(dtdt);            dctic6.setDate(dtdt);            dctic7.setDate(dtdt);            dctic8.setDate(dtdt);
            dccal1.setDate(dtdt);            dccal2.setDate(dtdt);            dcfax1.setDate(dtdt);            dcfax2.setDate(dtdt);
            dcmail1.setDate(dtdt);            dcmail2.setDate(dtdt);            dcsms1.setDate(dtdt);            dcsms2.setDate(dtdt);
            dtmi.setDate(dtdt);            dtmi1.setDate(dtdt);            dtmo.setDate(dtdt);            dtmo1.setDate(dtdt);
            dtsi.setDate(dtdt);            dtsi1.setDate(dtdt);            dtso.setDate(dtdt);            dtso1.setDate(dtdt);
            dtfi.setDate(dtdt);            dtfi1.setDate(dtdt);            dtfo.setDate(dtdt);            dtfo1.setDate(dtdt);
            dtmsi.setDate(dtdt);            dtmsi1.setDate(dtdt);            dtmso.setDate(dtdt);            dtmso1.setDate(dtdt);
            dthi.setDate(dtdt);            dtho.setDate(dtdt);            dtdi.setDate(dtdt);            dtdi1.setDate(dtdt);
            dtdo.setDate(dtdt);            dtdo1.setDate(dtdt);            dtpi.setDate(dtdt);            dtpi1.setDate(dtdt);
            dtpo.setDate(dtdt);            dtpo1.setDate(dtdt);
        } catch (ParseException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelin();
        tabelou();
        tabelmsin();
        tabelmsou();
        tabeltic();
        tabelticconf();
        tabelact();
        tabelmin();
        tabelmou();
        call();
        sms();
        mail();
        fax();
        new Timer(1000, dating).start();        
        inbo=new Timer(1000, activ);
        receiv=new Timer(10, testing);
        Scrol=new Timer(40, tiscrol);
        msg=new Timer(1000, inbound);

        if(Log.version!=Log.Loc){
            if(!Log.data[0].equals("herfan")){
//                connect();
                connecttele();
                connectuploder();
            }
        }else{

        }

        v=btncall.getDebugGraphicsOptions();
//        System.out.print("\nusrlvl = "+usrlvl);
//        if(usrlvl!=0){
        usr();showDept();showStatus();showDept();
        btnrelease.setEnabled(true);
//        }
    }

        private ccasw_ticket Tic;
    public ContactCenterASWATA(ccasw_ticket tic){
        this();
        this.Tic=tic;
    }

    public static ccasw_login Log;
    public ContactCenterASWATA(ccasw_login log){
        this();
        this.Log=log;
    }

    private ccasw_InBoundCall Inc;
    public ContactCenterASWATA(ccasw_InBoundCall inc){
        this();
        this.Inc=inc;
    }

    private ccasw_OutBound Obc;
    public ContactCenterASWATA(ccasw_OutBound obc){
        this();
        this.Obc=obc;
    }
    
    private ccasw_History Hic;
    public ContactCenterASWATA(ccasw_History hic){
        this();
        this.Hic=hic;
    }

    public ccasw_Sms_income Sin;
    public ContactCenterASWATA(ccasw_Sms_income sin){
        this();
        this.Sin=sin;
    }
    public ccasw_Email_incoming Ein;
    public ContactCenterASWATA(ccasw_Email_incoming ein){
        this();
        this.Ein=ein;
    }
    public ccasw_Fax_incoming Fin;
    public ContactCenterASWATA(ccasw_Fax_incoming fin){
        this();
        this.Fin=fin;
    }
    public ccasw_Asdept Asd;
    public ContactCenterASWATA(ccasw_Asdept asd){
        this();
        this.Asd=asd;
    }
    public ccasw_Mssg Misg;
    public ContactCenterASWATA(ccasw_Mssg misg){
        this();
        this.Misg=misg;
    }
    
    public static void tbin(javax.swing.JTable tb, int lebar[]){
        tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        int kolom=tb.getColumnCount();
        for (int i=0;i<kolom;i++){
            javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
//            tbc.setHeaderRenderer(tb.setAlignmentX(JTable.CENTER_ALIGNMENT));
            tb.setAlignmentY(tb.CENTER_ALIGNMENT);
            tb.setRowHeight(18);
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","User","Shift","Line number","Call status"
                        ,"Duration","Inquiry","Detail","Complaint","Detail"
                        ,"Request","Detail","Feedback","Detail"
                        ,"Blank Call","Caller number"
                        ,"Caller Name","Ticket No.","Status","Log ID","Call Type"
                        ,"Comment","Wrong Number","Cust Company","Produk"/*,"Callback date","Callback time"*/}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false//,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static void tabelin(){
        tabin.setRowCount(0);
        x=0;
             try{
                Date dt5 =dctic5.getDate();
                Date dt6 =dctic6.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cal3 = sdf.format(dt5);
                cal4 = sdf.format(dt6);
//              sql="select a.log_time, a.username, a.shift, a.line_number, a._callstatus, a.duration, a._complaint, a._blankcall, a.caller_number, a.caller_name, b.ticket_no, b._status from log_phone a, tickets b where a.log_date = '"+ld+"'";
              sql="select log_phone.*, " +
                      "_callstatus.data as cllstt, " +
                      "substring(callback_time from 1 for 10) as cb_date, " +
                      "substring(callback_time from 12 for 8) as cb_time, " +
                      "shift.data as dshift, " +
                      "if(_inquiry=1,'YES','NO') as inq, " +
                      "if(_complaint=1,'YES','NO') as comp, " +
                      "if(_request=1,'YES','NO') as req, " +
                      "if(_feedback=1,'YES','NO') as feed, " +
                      "if(_blankcall=1,'YES','NO') as blank, " +
                      "if(_wrongnumber=1,'YES','NO') as wrong, " +
                      "tickets.ticket_no as notic " +
                      "from log_phone " +
                      "join _callstatus on log_phone._callstatus=_callstatus.code " +
                      "join shift on log_phone.shift=shift.code " +
                      "left join tickets on log_phone.ticket_id=tickets.ticket_id " +
                      "where log_date between '"+cal3+"' and '"+cal4+"' and _direction=0 ";
                condition="";
            if(!cbagenin.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenin.getSelectedItem()+"%'";
            }
            if(!cbcalstatin.getSelectedItem().equals("--")){
                condition=condition+" and _callstatus like '%"+cbcalstatin.getSelectedIndex()+"%'";
            }

            sql=sql+condition+" order by log_id";
              rs=jconn.SQLExecuteRS(sql, conn);
              System.out.println(sql);

            while(rs.next()){
//                System.out.print("\nisi id ="+rs.getString(1));
                in[x]=rs.getString(2)+" "+rs.getString(3);x++;
                in[x]=rs.getString(4);x++;
                in[x]=rs.getString("dshift");x++;
                in[x]=rs.getString(7);x++;
                in[x]=rs.getString("cllstt");x++;

                in[x]=rs.getString("duration");x++;
                in[x]=rs.getString("inq");x++;
                in[x]=rs.getString("inquiry_detail");x++;
                in[x]=rs.getString("comp");x++;
                in[x]=rs.getString("complaint_detail");x++;
                
                in[x]=rs.getString("req");x++;
                in[x]=rs.getString("request_detail");x++;
                in[x]=rs.getString("feed");x++;
                in[x]=rs.getString("feedback_detail");x++;
                
                in[x]=rs.getString("blank");x++;
                in[x]=rs.getString("caller_number");x++;

                in[x]=rs.getString("caller_name");x++;
                in[x]=rs.getString("notic");x++;
                in[x]=null;x++;
                in[x]=rs.getString(1);x++;//logid
                in[x]=rs.getString("caller_type");x++;//call_type

                in[x]=rs.getString("comment");x++;//comment
                in[x]=rs.getString("wrong");x++;//wromg_num
                in[x]=rs.getString("cust_name");x++;//call_back
                in[x]=rs.getString("inbound_type");x++;//call_back
//                System.out.print("\nisi calback time ="+rs.getString(25));
                tabin.addRow(in);
                x=0;
            }
              lblcalincount.setText(String.valueOf(tabin.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }


    public static javax.swing.table.DefaultTableModel getDefaultTabelout(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","User","Shift","Line number","Duration","Phone number","Ticket No","Status"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelou(){
        x=0;
        tabou.setRowCount(0);
        try{
            Date dt7 =dctic7.getDate();
            Date dt8 =dctic8.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal5 = sdf.format(dt7);
            cal6 = sdf.format(dt8);
             int row=0;
              sql="select log_phone.*" +
                      ", tickets.ticket_no as notic" +
                      ", _ticketstatus.data as status" +
                      ", shift.data as dshift" +
                      " from log_phone" +
                      " left join tickets on log_phone.ticket_id=tickets.ticket_id"+
                      " left join _ticketstatus on tickets._status=_ticketstatus.code"+
                      " join shift on log_phone.shift=shift.code" +
                      " where log_date between '"+cal5+"' and '"+cal6+"' and _direction=1";
                  condition="";
            if(!cbagenou.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenou.getSelectedItem()+"%'";
            }
            sql=sql+condition+" order by log_id";
              rs=jconn.SQLExecuteRS(sql, conn);
//              System.out.println(sql);
            while(rs.next()){
                ou[x]=rs.getString("log_time");x++;
                ou[x]=rs.getString("username");x++;
                ou[x]=rs.getString("dshift");x++;
                ou[x]=rs.getString("line_number");x++;
                ou[x]=rs.getString("duration");x++;
                ou[x]=rs.getString("phone_number");x++;
                ou[x]=rs.getString("notic");x++;
                ou[x]=rs.getString("status");x++;
                tabou.addRow(ou);
                x=0;
            }
              lblcaloutcount.setText(String.valueOf(tabou.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabeltic(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
//                new String [] {"Ticket No.","Status","Category","Assign Dept.","Assign User","Customer","Phone number","Username","No. Plat","Type","Driver","Phone","Ticket Id","GS","GT","STORING","OTHER"}){
                new String [] {"Ticket No.","Priority","Type","Status","Open By"
                        ,"Department","Assign Dept.","Handle By","Category","Category Detail","Category Sub Detail"
                        ,"Product","Polis No","Cust. Name","Details","Solution","Ticket Id"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public void tabeltic(){
        x=0;
        tabtic.setRowCount(0);
        try{
            Date dt3 =dctic3.getDate();
            Date dt4 =dctic4.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tic3 = sdf.format(dt3);
            tic4 = sdf.format(dt4);
            sql="select * "
                    + ", _sendertype.data as source"
                    + ", _ticketmedia.data as media"
                    + ", _inboundtype.data as _type"
                    + ", _ticketcategory.category_name as category"
                    + ", _ticketdetailcategory.detail_category_name as category_detail"
                    + ", _ticketdetailsubcategory.detail_sub_category_name as category_subdetail"
                    + ", a.dept_name as dept_name"
                    + ", b.dept_name as ass_dept"
                    + ", _ticketpriority.data as prior"
                    + ", _ticketstatus.data as stt"
                    + ", _relatedproduct.data as prod"
                    + " from tickets "
                    + " left join _sendertype on tickets.source_id=_sendertype.code"
                    + " left join _ticketmedia on tickets.media_id=_ticketmedia.code"
                    + " left join _inboundtype on tickets.type_id=_inboundtype.code"
                    + " left join _ticketcategory on tickets.category_id =_ticketcategory.category_id"
                    + " left join _ticketdetailcategory on tickets.category_detail_id =_ticketdetailcategory.detail_category_id"
                    + " left join _ticketdetailsubcategory on tickets.category_subdetail_id =_ticketdetailsubcategory.detail_sub_category_id"
                    + " left join _department a on tickets.dept_id =a.dept_id"
                    + " left join _department b on tickets.assign_dept =b.dept_id"
                    + " left join _ticketpriority on tickets._priority =_ticketpriority.code"
                    + " left join _ticketstatus on tickets._status =_ticketstatus.code"
                    + " left join _relatedproduct on tickets.product_id =_relatedproduct.code"
                    + " where ticket_id is not null ";
              condition="";
            if(cktgl.isSelected()==true){
                if(!dctic4.getDate().equals("")){
                    condition=condition+" and open_date between '"+tic3+"' and '"+tic4+"'";
                }else{
                    condition=condition+" and open_date= '"+tic3+"'";
                    System.out.print(condition);
                }
            }
            if(!txtticno1.getText().equals("")){
                condition=condition+" and ticket_no like '%"+txtticno1.getText()+"%'";
            }
            if(!cbdept.getSelectedItem().equals("--")){
                condition=condition+" and tickets.dept_id = '"+cbdept.getSelectedIndex()+"'";
            }
            if(!cbticstatus.getSelectedItem().equals("--")){
                if(!cbticstatus.getSelectedItem().equals("CANCEL")){
                    condition=condition+" and _status = '"+cbticstatus.getSelectedIndex()+"'";
                }else{
                    condition=condition+" and _status = '-1'";
                }
            }
            if(!cbFollowUp.getSelectedItem().equals("--")){
                condition=condition+" and follow_up = '"+cbFollowUp.getSelectedItem()+"'";
            }
            if(ckassign.isSelected()==true){
                condition=condition+" and assign_dept=0";
            }
            if(ckstoring.isSelected()==true){
                condition=condition+" and _storing=1";
            }
            if(cksubmit.isSelected()==true){
                condition=condition+" and _submitted=0";
            }
            if(!txtcus.getText().equals("")){
                condition=condition+" and cust_name like '%"+txtcus.getText()+"%'";
            }
            if(!txtdriv.getText().equals("")){
                condition=condition+" and driver_name like '%"+txtdriv.getText()+"%'";
            }
            if(!cbcate.getSelectedItem().equals("--")){
                condition=condition+" and category = '"+cbcate.getSelectedItem()+"'";
            }
            if(!txtdrivcode.getText().equals("")){
                condition=condition+" and driver_code like '%"+txtdrivcode.getText()+"%'";
            }
            sql=sql+condition+" order by ticket_no";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.print("\nserach tic "+sql);

            while(rs.next()){
                tic[x]=rs.getString("ticket_no");x++;
                tic[x]=rs.getString("prior");x++;
                tic[x]=rs.getString("_type");x++;
                tic[x]=rs.getString("stt");x++;
                tic[x]=rs.getString("open_username");x++;
                tic[x]=rs.getString("dept_name");x++;
                tic[x]=rs.getString("ass_dept");x++;
                tic[x]=rs.getString("handle_by");x++;
                tic[x]=rs.getString("category");x++;
                tic[x]=rs.getString("category_detail");x++;
                tic[x]=rs.getString("category_subdetail");x++;
                tic[x]=rs.getString("prod");x++;
                tic[x]=rs.getString("cust_id");x++;
                tic[x]=rs.getString("cust_name");x++;    
                tic[x]=rs.getString("info");x++;
                tic[x]=rs.getString("solution");x++;
                tic[x]=rs.getString("ticket_id");x++;
                tabtic.addRow(tic);
                x=0;
            }
            lblticcount.setText(String.valueOf(tabtic.getRowCount()));
//              System.out.println(tic[0]);
//              System.out.println(tic[12]);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelsin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","From","Status","Messages","Process By","Id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }

    private void tabelsin() {
          try{
              tabsin.setRowCount(0);
              int x=0;
              Date dt1 =dtsi.getDate();
              Date dt2 =dtsi1.getDate();
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              sin = sdf.format(dt1);
              sin1= sdf.format(dt2);
              sql="select log_sms.*, rcvd_status.data as stt, " +
                      "tickets.ticket_no as notic " +
                      "from log_sms " +
                      "join rcvd_status on log_sms._status=rcvd_status.code " +
                      "left join tickets on log_sms.ticket_id=tickets.ticket_id " +
                      "where sms_date between '"+sin+"' and '"+sin1+"' and _direction=0 order by sms_id";
              rs=jconn.SQLExecuteRS(sql, conn);
              System.out.println(sql);

            while(rs.next()){
                in2[x]=rs.getString("sms_date")+" "+rs.getString("sms_time");x++;
                in2[x]=rs.getString("sms_from");x++;
                in2[x]=rs.getString("stt");x++;
                in2[x]=rs.getString("sms_text");x++;
                in2[x]=rs.getString("username");x++;
                in2[x]=rs.getString("sms_id");x++;
                in2[x]=rs.getString("cust_name");x++;
                tabsin.addRow(in2);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelsou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","To","Status","Messages","Sent By","No. Ticket"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelsou(){
        try{
           tabsou.setRowCount(0);
           int x=0;
           Date dt1 =dtso.getDate();
           Date dt2 =dtso1.getDate();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           sou = sdf.format(dt1);
           sou1= sdf.format(dt2);
           sql="select log_sms.*, send_status.data as stt, " +
                   "tickets.ticket_no as notic " +
                   "from log_sms " +
                   "join send_status on log_sms._status=send_status.code " +
                   "left join tickets on log_sms.ticket_id=tickets.ticket_id " +
                   "where sms_date between '"+sou+"' and '"+sou1+"' and _direction=1 order by sms_id";
           rs=jconn.SQLExecuteRS(sql, conn);
           System.out.println(sql);

           while(rs.next()){
               ou1[x]=rs.getString("sms_date")+" "+rs.getString("sms_time");x++;
               ou1[x]=rs.getString("sms_to");x++;
               ou1[x]=rs.getString("stt");x++;
               ou1[x]=rs.getString("sms_text");x++;
               ou1[x]=rs.getString("username");x++;
               ou1[x]=rs.getString("notic");x++;
               tabsou.addRow(ou1);x=0;
           }
       }catch(Exception exc){
           System.err.println(exc.getMessage());
       }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelmin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","From","Subject","Status","username","Text","id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    String min,min1,mou,mou1,sin,sin1,sou,sou1,fin,fin1,fou,fou1;
    public static String msin,msin1,msou,msou1;
    private void tabelmin() {
        try{
            tabmin.setRowCount(0);
            int x=0;
            Date dt1 =dtmi.getDate();
            Date dt2 =dtmi1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            min = sdf.format(dt1);
            min1= sdf.format(dt2);
            sql="select log_mail.*, " +
                    "rcvd_status.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_mail " +
                    "join rcvd_status on log_mail.status=rcvd_status.code " +
                    "left join tickets on log_mail.ticket_id=tickets.ticket_id " +
                    "where mail_date between '"+min+"' and '"+min1+"' and direction=0 order by mail_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                in2[x]=rs.getString("mail_date");x++;
                in2[x]=rs.getString("mail_time");x++;
                in2[x]=rs.getString("mail_from");x++;
                in2[x]=rs.getString("mail_subject");x++;
                in2[x]=rs.getString("stt");x++;
                in2[x]=rs.getString("username");x++;
                in2[x]=rs.getString("mail_text");x++;
                in2[x]=rs.getString("mail_id");x++;
                in2[x]=rs.getString("cust_name");x++;
                tabmin.addRow(in2);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelmout(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","To","Username","Subject","Ticket no","Status","Text","CC","id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelmou() {
        try{
            tabmou.setRowCount(0);
            int x=0;
            Date dt1 =dtmo.getDate();
            Date dt2 =dtmo1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            mou = sdf.format(dt1);
            mou1= sdf.format(dt2);
            sql="select log_mail.*, send_status.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_mail " +
                    "join send_status on log_mail.status=send_status.code " +
                    "left join tickets on log_mail.ticket_id=tickets.ticket_id " +
                    "where mail_date between '"+mou+"' and '"+mou1+"' and direction=1 order by mail_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                ou1[x]=rs.getString("mail_date");x++;
                ou1[x]=rs.getString("mail_time");x++;
                ou1[x]=rs.getString("mail_to");x++;
                ou1[x]=rs.getString("username");x++;
                ou1[x]=rs.getString("mail_subject");x++;
                ou1[x]=rs.getString("notic");x++;
                ou1[x]=rs.getString("stt");x++;
                ou1[x]=rs.getString("mail_text");x++;
                ou1[x]=rs.getString("mail_cc");x++;
                ou1[x]=rs.getString("mail_id");x++;
                ou1[x]=rs.getString("cust_name");x++;
                tabmou.addRow(ou1);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }

    public static javax.swing.table.DefaultTableModel getDefaultTabelfin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","Document name","Status","Process By","Id","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelfin() {
        try{
            tabfin.setRowCount(0);
            int x=0;
            Date dt1 =dtfi.getDate();
            Date dt2 =dtfi1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            fin = sdf.format(dt1);
            fin1= sdf1.format(dt2);
            sql="select log_fax.*, rcvd_status.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_fax " +
                    "join rcvd_status on log_fax._status=rcvd_status.code " +
                    "left join tickets on log_fax.ticket_id=tickets.ticket_id " +
                    "where rcvd_time between '"+fin+"' and '"+fin1+"' and _direction=0 order by fax_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                in3[x]=rs.getString("rcvd_time");x++;
                in3[x]=rs.getString("doc_name");x++;
                in3[x]=rs.getString("stt");x++;
                in3[x]=rs.getString("username");x++;
                in3[x]=rs.getString("fax_id");x++;
                in3[x]=rs.getString("cust_name");x++;
                tabfin.addRow(in3);
                x=0;
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelfou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Time","To","Document name","Status","Sent By","No. Ticket"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void tabelfou(){
        try{
            tabfou.setRowCount(0);
            int row=0;
            Date dt1 =dtfo.getDate();
            Date dt2 =dtfo1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            fou = sdf.format(dt1);
            fou1= sdf1.format(dt2);
            sql="select log_fax.*, _faxstatus.data as stt, " +
                    "tickets.ticket_no as notic " +
                    "from log_fax " +
                    "join _faxstatus on log_fax._status=_faxstatus.code " +
                    "left join tickets on log_fax.ticket_id=tickets.ticket_id " +
                    "where sent_time between '"+fou+"' and '"+fou1+"' and _direction=1 order by fax_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                ou3[0]=rs.getString("sent_time");
                ou3[1]=rs.getString("recipient");
                ou3[2]=rs.getString("doc_name");
                ou3[3]=rs.getString("stt");
                ou3[4]=rs.getString("username");
                ou3[5]=rs.getString("notic");
                tabfou.addRow(ou3);
            }
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

        jdp = new javax.swing.JDesktopPane();
        lbldate = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnlogout = new javax.swing.JButton();
        lbluser = new javax.swing.JLabel();
        lbllogo = new javax.swing.JLabel();
        lblpas = new javax.swing.JLabel();
        lblshift = new javax.swing.JLabel();
        lblshift1 = new javax.swing.JLabel();
        pnlscroll = new javax.swing.JPanel();
        lblscroll = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btncall = new javax.swing.JButton();
        btnsms = new javax.swing.JButton();
        btnmail = new javax.swing.JButton();
        btnoutbound = new javax.swing.JButton();
        lblactivity = new javax.swing.JLabel();
        btnready = new javax.swing.JButton();
        cbdirection = new javax.swing.JComboBox();
        txtcalnoti = new javax.swing.JTextField();
        txtfaxnoti = new javax.swing.JTextField();
        txtsmsnoti = new javax.swing.JTextField();
        txtmailnoti = new javax.swing.JTextField();
        btnfax = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtab = new javax.swing.JTabbedPane();
        pnlinbon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblin = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        btninsrch = new javax.swing.JButton();
        dctic5 = new com.toedter.calendar.JDateChooser();
        dctic6 = new com.toedter.calendar.JDateChooser();
        jLabel58 = new javax.swing.JLabel();
        cbcalstatin = new javax.swing.JComboBox();
        cbagenin = new javax.swing.JComboBox();
        lblcalincount = new javax.swing.JLabel();
        lblrepticcount12 = new javax.swing.JLabel();
        pnlou = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblout = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblticconf = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        dctic7 = new com.toedter.calendar.JDateChooser();
        dctic8 = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnoutsrch = new javax.swing.JButton();
        cbagenou = new javax.swing.JComboBox();
        lblcaloutcount = new javax.swing.JLabel();
        lblrepticcount11 = new javax.swing.JLabel();
        pnltic = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltic = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        dctic3 = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        btnticsrch = new javax.swing.JButton();
        dctic4 = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        btnsenddept = new javax.swing.JButton();
        cktgl = new javax.swing.JCheckBox();
        jLabel73 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtsolution = new javax.swing.JTextArea();
        jScrollPane38 = new javax.swing.JScrollPane();
        txtdetail = new javax.swing.JTextArea();
        lblticcount = new javax.swing.JLabel();
        lblrepticcount10 = new javax.swing.JLabel();
        jScrollPane47 = new javax.swing.JScrollPane();
        jPanel25 = new javax.swing.JPanel();
        txtticno1 = new javax.swing.JTextField();
        ckstoring = new javax.swing.JCheckBox();
        ckassign = new javax.swing.JCheckBox();
        jLabel68 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cbticstatus = new javax.swing.JComboBox();
        cbdept = new javax.swing.JComboBox();
        cksubmit = new javax.swing.JCheckBox();
        jLabel53 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txtcus = new javax.swing.JTextField();
        txtdriv = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        cbcate = new javax.swing.JComboBox();
        txtdrivcode = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        cbFollowUp = new javax.swing.JComboBox();
        pnlact = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblact = new javax.swing.JTable();
        btnrelease = new javax.swing.JButton();
        cbagenrelease = new javax.swing.JComboBox();
        panelsms = new javax.swing.JTabbedPane();
        pninbox = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblsin = new javax.swing.JTable();
        dtsi = new com.toedter.calendar.JDateChooser();
        dtsi1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtimsg2 = new javax.swing.JTextArea();
        txtfrom2 = new javax.swing.JTextField();
        btnsmsinsrch = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        cbcust = new javax.swing.JComboBox();
        btncussavesms = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        txtnoticsms = new javax.swing.JTextField();
        pnoutbox = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblsou = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        dtso = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dtso1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtimsg1 = new javax.swing.JTextArea();
        txtfrom1 = new javax.swing.JTextField();
        btnsmsoutsrch = new javax.swing.JButton();
        panelmail = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblmin = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtfrom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtisu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtimsg = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        dtmi = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        dtmi1 = new com.toedter.calendar.JDateChooser();
        btnmailinsrch = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        cbcust1 = new javax.swing.JComboBox();
        btncussaveEmail = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        txtnoticmail = new javax.swing.JTextField();
        btnAttachment = new javax.swing.JButton();
        scpCcList1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblmou = new javax.swing.JTable();
        txtoto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtocc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtosu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtomsg = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtidti = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        dtmo = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        dtmo1 = new com.toedter.calendar.JDateChooser();
        btnmailoutsrch = new javax.swing.JButton();
        scpCcList2 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        btnAttachment1 = new javax.swing.JButton();
        panelfax = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        tblfin = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        dtfi = new com.toedter.calendar.JDateChooser();
        jLabel69 = new javax.swing.JLabel();
        dtfi1 = new com.toedter.calendar.JDateChooser();
        btnfinsrch = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        lblview = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        cbcust2 = new javax.swing.JComboBox();
        btncussaveFax = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        txtnoticfax = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tblfou = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();
        dtfo = new com.toedter.calendar.JDateChooser();
        jLabel76 = new javax.swing.JLabel();
        dtfo1 = new com.toedter.calendar.JDateChooser();
        btnfoutsrch = new javax.swing.JButton();
        jScrollPane31 = new javax.swing.JScrollPane();
        lblview1 = new javax.swing.JLabel();
        tabbpanereport = new javax.swing.JTabbedPane();
        pnlrep = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblreptic = new javax.swing.JTable();
        btnreptic = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        dctic1 = new com.toedter.calendar.JDateChooser();
        dctic2 = new com.toedter.calendar.JDateChooser();
        btnexporttic = new javax.swing.JButton();
        lblrepticcount = new javax.swing.JLabel();
        lblrepticcount1 = new javax.swing.JLabel();
        jScrollPane87 = new javax.swing.JScrollPane();
        jPanel35 = new javax.swing.JPanel();
        txtticno2 = new javax.swing.JTextField();
        ckstoring1 = new javax.swing.JCheckBox();
        ckassign1 = new javax.swing.JCheckBox();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        cbticstatus1 = new javax.swing.JComboBox();
        cbdept1 = new javax.swing.JComboBox();
        cksubmit1 = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtcus1 = new javax.swing.JTextField();
        txtdriv1 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        cbcate1 = new javax.swing.JComboBox();
        txtdrivcode1 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        cbFollowUp1 = new javax.swing.JComboBox();
        pnlrep1 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblrepcal = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnrepcal = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        dccal1 = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        dccal2 = new com.toedter.calendar.JDateChooser();
        cbcaldir = new javax.swing.JComboBox();
        cbcalstat = new javax.swing.JComboBox();
        cbagenirepcal = new javax.swing.JComboBox();
        cbcaltyperepcal = new javax.swing.JComboBox();
        btnexportcall = new javax.swing.JButton();
        lblrepcalcount = new javax.swing.JLabel();
        lblrepticcount3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane39 = new javax.swing.JScrollPane();
        tblhourin = new javax.swing.JTable();
        jLabel78 = new javax.swing.JLabel();
        dthi = new com.toedter.calendar.JDateChooser();
        btnhi = new javax.swing.JButton();
        btnexportcall1 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane40 = new javax.swing.JScrollPane();
        tblhourout = new javax.swing.JTable();
        dtho = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        btnho = new javax.swing.JButton();
        btnexportcall2 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane43 = new javax.swing.JScrollPane();
        tbldailyin = new javax.swing.JTable();
        jLabel82 = new javax.swing.JLabel();
        dtdi = new com.toedter.calendar.JDateChooser();
        btndi = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        dtdi1 = new com.toedter.calendar.JDateChooser();
        btnexportcall3 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane44 = new javax.swing.JScrollPane();
        tbldailyout = new javax.swing.JTable();
        dtdo = new com.toedter.calendar.JDateChooser();
        jLabel83 = new javax.swing.JLabel();
        btndo = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        dtdo1 = new com.toedter.calendar.JDateChooser();
        btnexportcall4 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane41 = new javax.swing.JScrollPane();
        tblperformin = new javax.swing.JTable();
        dtpi = new com.toedter.calendar.JDateChooser();
        btnpi1 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        dtpi1 = new com.toedter.calendar.JDateChooser();
        jLabel88 = new javax.swing.JLabel();
        btnexportcall5 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane42 = new javax.swing.JScrollPane();
        tblperformout = new javax.swing.JTable();
        dtpo = new com.toedter.calendar.JDateChooser();
        btnpo1 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        dtpo1 = new com.toedter.calendar.JDateChooser();
        jLabel89 = new javax.swing.JLabel();
        btnexportcall6 = new javax.swing.JButton();
        pnlrep2 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblrepsms = new javax.swing.JTable();
        txtsmsstat = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnrepsms = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        dcsms1 = new com.toedter.calendar.JDateChooser();
        jLabel44 = new javax.swing.JLabel();
        dcsms2 = new com.toedter.calendar.JDateChooser();
        txtsmsticid = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cbdirrepsms = new javax.swing.JComboBox();
        cbagenirepcal1 = new javax.swing.JComboBox();
        btnexportsms = new javax.swing.JButton();
        lblrepsmscount = new javax.swing.JLabel();
        lblrepticcount5 = new javax.swing.JLabel();
        pnlrep3 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblrepmail = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtmailticid = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnrepmail = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtmailsub = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        dcmail1 = new com.toedter.calendar.JDateChooser();
        jLabel46 = new javax.swing.JLabel();
        dcmail2 = new com.toedter.calendar.JDateChooser();
        cbdirmail = new javax.swing.JComboBox();
        cbagenrepmail = new javax.swing.JComboBox();
        btnexportmail = new javax.swing.JButton();
        lblrepmailcount = new javax.swing.JLabel();
        lblrepticcount7 = new javax.swing.JLabel();
        pnlrep4 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblrepfax = new javax.swing.JTable();
        txtfaxfinm = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnrepfax = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        dcfax1 = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        dcfax2 = new com.toedter.calendar.JDateChooser();
        cbstatusrepfax = new javax.swing.JComboBox();
        btnexportmail1 = new javax.swing.JButton();
        cbagenirepfax = new javax.swing.JComboBox();
        jLabel64 = new javax.swing.JLabel();
        cbdirfax = new javax.swing.JComboBox();
        lblrepfaxcount = new javax.swing.JLabel();
        lblrepticcount9 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pninbox1 = new javax.swing.JPanel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tblmsin = new javax.swing.JTable();
        dtmsi = new com.toedter.calendar.JDateChooser();
        dtmsi1 = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane34 = new javax.swing.JScrollPane();
        txtimsg3 = new javax.swing.JTextArea();
        btnreplymsg = new javax.swing.JButton();
        btndelmsg = new javax.swing.JButton();
        pnoutbox1 = new javax.swing.JPanel();
        jScrollPane35 = new javax.swing.JScrollPane();
        tblmsou = new javax.swing.JTable();
        jLabel71 = new javax.swing.JLabel();
        dtmso = new com.toedter.calendar.JDateChooser();
        jLabel72 = new javax.swing.JLabel();
        dtmso1 = new com.toedter.calendar.JDateChooser();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane36 = new javax.swing.JScrollPane();
        txtimsg4 = new javax.swing.JTextArea();
        btncomposemsg = new javax.swing.JButton();
        btndelmsg1 = new javax.swing.JButton();
        pnlinf = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane46 = new javax.swing.JScrollPane();
        jTextArea12 = new javax.swing.JTextArea();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane48 = new javax.swing.JScrollPane();
        jTextArea13 = new javax.swing.JTextArea();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane49 = new javax.swing.JScrollPane();
        jTextArea14 = new javax.swing.JTextArea();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane50 = new javax.swing.JScrollPane();
        jTextArea15 = new javax.swing.JTextArea();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane45 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextArea();
        jScrollPane51 = new javax.swing.JScrollPane();
        jTextArea16 = new javax.swing.JTextArea();
        jScrollPane52 = new javax.swing.JScrollPane();
        jTextArea17 = new javax.swing.JTextArea();
        jScrollPane53 = new javax.swing.JScrollPane();
        jTextArea18 = new javax.swing.JTextArea();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane54 = new javax.swing.JScrollPane();
        jTextArea19 = new javax.swing.JTextArea();
        jScrollPane55 = new javax.swing.JScrollPane();
        jTextArea20 = new javax.swing.JTextArea();
        jScrollPane56 = new javax.swing.JScrollPane();
        jTextArea21 = new javax.swing.JTextArea();
        jScrollPane57 = new javax.swing.JScrollPane();
        jTextArea22 = new javax.swing.JTextArea();
        jScrollPane58 = new javax.swing.JScrollPane();
        jTextArea23 = new javax.swing.JTextArea();
        jScrollPane59 = new javax.swing.JScrollPane();
        jTextArea24 = new javax.swing.JTextArea();
        jScrollPane60 = new javax.swing.JScrollPane();
        jTextArea25 = new javax.swing.JTextArea();
        jScrollPane61 = new javax.swing.JScrollPane();
        jTextArea26 = new javax.swing.JTextArea();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jTabbedPane9 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane62 = new javax.swing.JScrollPane();
        jTextArea27 = new javax.swing.JTextArea();
        jScrollPane63 = new javax.swing.JScrollPane();
        jTextArea28 = new javax.swing.JTextArea();
        jScrollPane64 = new javax.swing.JScrollPane();
        jTextArea29 = new javax.swing.JTextArea();
        jScrollPane65 = new javax.swing.JScrollPane();
        jTextArea30 = new javax.swing.JTextArea();
        jScrollPane66 = new javax.swing.JScrollPane();
        jTextArea31 = new javax.swing.JTextArea();
        jScrollPane67 = new javax.swing.JScrollPane();
        jTextArea32 = new javax.swing.JTextArea();
        jScrollPane68 = new javax.swing.JScrollPane();
        jTextArea33 = new javax.swing.JTextArea();
        jScrollPane69 = new javax.swing.JScrollPane();
        jTextArea34 = new javax.swing.JTextArea();
        jScrollPane70 = new javax.swing.JScrollPane();
        jTextArea35 = new javax.swing.JTextArea();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane71 = new javax.swing.JScrollPane();
        jTextArea36 = new javax.swing.JTextArea();
        jScrollPane72 = new javax.swing.JScrollPane();
        jTextArea37 = new javax.swing.JTextArea();
        jScrollPane73 = new javax.swing.JScrollPane();
        jTextArea38 = new javax.swing.JTextArea();
        jScrollPane74 = new javax.swing.JScrollPane();
        jTextArea39 = new javax.swing.JTextArea();
        jScrollPane75 = new javax.swing.JScrollPane();
        jTextArea40 = new javax.swing.JTextArea();
        jScrollPane76 = new javax.swing.JScrollPane();
        jTextArea41 = new javax.swing.JTextArea();
        jScrollPane77 = new javax.swing.JScrollPane();
        jTextArea42 = new javax.swing.JTextArea();
        jTabbedPane11 = new javax.swing.JTabbedPane();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane78 = new javax.swing.JScrollPane();
        jTextArea43 = new javax.swing.JTextArea();
        jScrollPane79 = new javax.swing.JScrollPane();
        jTextArea44 = new javax.swing.JTextArea();
        jScrollPane80 = new javax.swing.JScrollPane();
        jTextArea45 = new javax.swing.JTextArea();
        jScrollPane81 = new javax.swing.JScrollPane();
        jTextArea46 = new javax.swing.JTextArea();
        jTabbedPane12 = new javax.swing.JTabbedPane();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane82 = new javax.swing.JScrollPane();
        jTextArea47 = new javax.swing.JTextArea();
        jScrollPane83 = new javax.swing.JScrollPane();
        jTextArea48 = new javax.swing.JTextArea();
        jScrollPane84 = new javax.swing.JScrollPane();
        jTextArea49 = new javax.swing.JTextArea();
        jScrollPane85 = new javax.swing.JScrollPane();
        jTextArea50 = new javax.swing.JTextArea();
        jScrollPane86 = new javax.swing.JScrollPane();
        jTextArea51 = new javax.swing.JTextArea();
        jTabbedPane13 = new javax.swing.JTabbedPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        jLabel99 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jLabel100 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jLabel101 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setTitle("CONTACT CENTER ASWATA");
        setFont(new java.awt.Font("Tahoma", 0, 11));

        jdp.setBackground(new java.awt.Color(255, 255, 255));

        lbldate.setFont(new java.awt.Font("Calibri", 1, 24));
        lbldate.setForeground(new java.awt.Color(255, 255, 255));
        lbldate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldate.setText("Date Time");
        lbldate.setBounds(40, 660, 230, 30);
        jdp.add(lbldate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(255, 102, 0));
        jPanel1.setFont(new java.awt.Font("Calibri", 0, 11));
        jPanel1.setLayout(null);

        btnlogout.setFont(new java.awt.Font("Calibri", 0, 14));
        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117830_public.png"))); // NOI18N
        btnlogout.setToolTipText("LOG OUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnlogout);
        btnlogout.setBounds(920, 20, 50, 40);

        lbluser.setFont(new java.awt.Font("Calibri", 1, 20));
        lbluser.setForeground(new java.awt.Color(255, 102, 51));
        lbluser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbluser.setText("Username");
        jPanel1.add(lbluser);
        lbluser.setBounds(810, 20, 100, 40);

        lbllogo.setFont(new java.awt.Font("Calibri", 0, 14));
        lbllogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Aswata Logo2.jpg"))); // NOI18N
        jPanel1.add(lbllogo);
        lbllogo.setBounds(10, 20, 220, 90);

        lblpas.setFont(new java.awt.Font("Calibri", 0, 14));
        lblpas.setText("jLabel1");
        lblpas.setEnabled(false);
        lblpas.setRequestFocusEnabled(false);
        jPanel1.add(lblpas);
        lblpas.setBounds(110, 70, 40, 0);

        lblshift.setFont(new java.awt.Font("Calibri", 0, 14));
        lblshift.setEnabled(false);
        lblshift.setRequestFocusEnabled(false);
        jPanel1.add(lblshift);
        lblshift.setBounds(110, 70, 0, 0);

        lblshift1.setFont(new java.awt.Font("Calibri", 0, 14));
        lblshift1.setEnabled(false);
        lblshift1.setRequestFocusEnabled(false);
        jPanel1.add(lblshift1);
        lblshift1.setBounds(110, 70, 0, 0);

        pnlscroll.setBackground(new java.awt.Color(255, 255, 255));
        pnlscroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlscrollMouseClicked(evt);
            }
        });
        pnlscroll.setLayout(null);

        lblscroll.setFont(lblscroll.getFont().deriveFont(lblscroll.getFont().getStyle() | java.awt.Font.BOLD, 11));
        lblscroll.setForeground(new java.awt.Color(255, 0, 0));
        pnlscroll.add(lblscroll);
        lblscroll.setBounds(710, 0, 0, 20);

        jPanel1.add(pnlscroll);
        pnlscroll.setBounds(20, 117, 950, 20);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(null);

        btncall.setFont(new java.awt.Font("Calibri", 0, 14));
        btncall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cal1.jpg"))); // NOI18N
        btncall.setBorder(null);
        btncall.setEnabled(false);
        btncall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncallActionPerformed(evt);
            }
        });
        jPanel26.add(btncall);
        btncall.setBounds(100, 0, 80, 80);

        btnsms.setFont(new java.awt.Font("Calibri", 0, 14));
        btnsms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sm.jpg"))); // NOI18N
        btnsms.setBorder(null);
        btnsms.setEnabled(false);
        btnsms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsActionPerformed(evt);
            }
        });
        jPanel26.add(btnsms);
        btnsms.setBounds(190, 0, 80, 80);

        btnmail.setFont(new java.awt.Font("Calibri", 0, 14));
        btnmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mail.jpg"))); // NOI18N
        btnmail.setBorder(null);
        btnmail.setEnabled(false);
        btnmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailActionPerformed(evt);
            }
        });
        jPanel26.add(btnmail);
        btnmail.setBounds(280, 0, 80, 80);

        btnoutbound.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnoutbound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/out.jpg"))); // NOI18N
        btnoutbound.setToolTipText("OutBound");
        btnoutbound.setEnabled(false);
        btnoutbound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoutboundActionPerformed(evt);
            }
        });
        jPanel26.add(btnoutbound);
        btnoutbound.setBounds(470, 0, 70, 0);

        lblactivity.setFont(new java.awt.Font("Calibri", 1, 14));
        lblactivity.setForeground(new java.awt.Color(255, 102, 51));
        lblactivity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblactivity.setText("Disconnected");
        jPanel26.add(lblactivity);
        lblactivity.setBounds(0, 0, 90, 20);

        btnready.setFont(btnready.getFont().deriveFont(btnready.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnready.setText("Ready");
        btnready.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreadyMouseClicked(evt);
            }
        });
        btnready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreadyActionPerformed(evt);
            }
        });
        jPanel26.add(btnready);
        btnready.setBounds(0, 40, 90, 23);

        cbdirection.setFont(cbdirection.getFont());
        cbdirection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND" }));
        jPanel26.add(cbdirection);
        cbdirection.setBounds(0, 20, 90, 24);

        txtcalnoti.setEditable(false);
        txtcalnoti.setFont(new java.awt.Font("Calibri", 1, 18));
        txtcalnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtcalnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcalnoti.setBorder(null);
        txtcalnoti.setOpaque(false);
        txtcalnoti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcalnotiMouseClicked(evt);
            }
        });
        jPanel26.add(txtcalnoti);
        txtcalnoti.setBounds(490, 50, 30, 20);

        txtfaxnoti.setEditable(false);
        txtfaxnoti.setFont(new java.awt.Font("Calibri", 1, 18));
        txtfaxnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtfaxnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfaxnoti.setBorder(null);
        txtfaxnoti.setOpaque(false);
        jPanel26.add(txtfaxnoti);
        txtfaxnoti.setBounds(380, 80, 60, 20);

        txtsmsnoti.setEditable(false);
        txtsmsnoti.setFont(new java.awt.Font("Calibri", 1, 18));
        txtsmsnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtsmsnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsmsnoti.setBorder(null);
        txtsmsnoti.setOpaque(false);
        jPanel26.add(txtsmsnoti);
        txtsmsnoti.setBounds(200, 80, 60, 20);

        txtmailnoti.setEditable(false);
        txtmailnoti.setFont(new java.awt.Font("Calibri", 1, 18));
        txtmailnoti.setForeground(new java.awt.Color(255, 0, 0));
        txtmailnoti.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmailnoti.setBorder(null);
        txtmailnoti.setOpaque(false);
        txtmailnoti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmailnotiActionPerformed(evt);
            }
        });
        jPanel26.add(txtmailnoti);
        txtmailnoti.setBounds(290, 80, 60, 20);

        btnfax.setFont(new java.awt.Font("Calibri", 0, 14));
        btnfax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fax.jpg"))); // NOI18N
        btnfax.setBorder(null);
        btnfax.setEnabled(false);
        btnfax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfaxActionPerformed(evt);
            }
        });
        jPanel26.add(btnfax);
        btnfax.setBounds(370, 0, 80, 80);

        jPanel1.add(jPanel26);
        jPanel26.setBounds(250, 20, 550, 100);

        jPanel1.setBounds(10, 20, 990, 140);
        jdp.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(null);

        jtab.setBackground(new java.awt.Color(255, 255, 255));
        jtab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jtab.setFont(jtab.getFont().deriveFont(jtab.getFont().getStyle() | java.awt.Font.BOLD, 11));

        pnlinbon.setBackground(new java.awt.Color(255, 255, 255));
        pnlinbon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlinbon.setLayout(null);

        tblin.setAutoCreateRowSorter(true);
        tblin.setFont(tblin.getFont().deriveFont((float)11));
        tblin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblin.setDoubleBuffered(true);
        tblin.setFillsViewportHeight(true);
        tblin.setMaximumSize(new java.awt.Dimension(2147483647, 72));
        tblin.setRowHeight(20);
        tblin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblinMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblin);

        pnlinbon.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 950, 390);

        jLabel55.setFont(jLabel55.getFont().deriveFont((float)11));
        jLabel55.setText("Open From");
        pnlinbon.add(jLabel55);
        jLabel55.setBounds(10, 10, 80, 10);

        jLabel56.setFont(jLabel56.getFont().deriveFont((float)11));
        jLabel56.setText("Until");
        pnlinbon.add(jLabel56);
        jLabel56.setBounds(130, 10, 50, 10);

        jLabel57.setFont(jLabel57.getFont().deriveFont((float)11));
        jLabel57.setText("Agen");
        pnlinbon.add(jLabel57);
        jLabel57.setBounds(260, 10, 50, 10);

        btninsrch.setFont(btninsrch.getFont().deriveFont(btninsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btninsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btninsrch.setText("Search By");
        btninsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsrchActionPerformed(evt);
            }
        });
        pnlinbon.add(btninsrch);
        btninsrch.setBounds(500, 20, 130, 24);

        dctic5.setDateFormatString("dd/MM/yyyy");
        dctic5.setFont(dctic5.getFont().deriveFont((float)11));
        pnlinbon.add(dctic5);
        dctic5.setBounds(10, 20, 120, 24);

        dctic6.setDateFormatString("dd/MM/yyyy");
        dctic6.setFont(dctic6.getFont().deriveFont((float)11));
        pnlinbon.add(dctic6);
        dctic6.setBounds(130, 20, 120, 24);

        jLabel58.setFont(jLabel58.getFont().deriveFont((float)11));
        jLabel58.setText("Call Status");
        pnlinbon.add(jLabel58);
        jLabel58.setBounds(370, 10, 100, 10);

        cbcalstatin.setFont(cbcalstatin.getFont().deriveFont((float)11));
        cbcalstatin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ABANDON", "ANSWERED", "--" }));
        cbcalstatin.setSelectedIndex(2);
        pnlinbon.add(cbcalstatin);
        cbcalstatin.setBounds(370, 20, 100, 24);

        cbagenin.setFont(cbagenin.getFont().deriveFont((float)11));
        cbagenin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlinbon.add(cbagenin);
        cbagenin.setBounds(260, 20, 100, 24);

        lblcalincount.setFont(lblcalincount.getFont().deriveFont((float)11));
        lblcalincount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlinbon.add(lblcalincount);
        lblcalincount.setBounds(880, 0, 40, 10);

        lblrepticcount12.setFont(lblrepticcount12.getFont().deriveFont((float)11));
        lblrepticcount12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount12.setText("list");
        pnlinbon.add(lblrepticcount12);
        lblrepticcount12.setBounds(920, 0, 40, 10);

        jtab.addTab("InBound", pnlinbon);

        pnlou.setBackground(new java.awt.Color(255, 255, 255));
        pnlou.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlou.setLayout(null);

        tblout.setAutoCreateRowSorter(true);
        tblout.setFont(tblout.getFont().deriveFont((float)11));
        tblout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblout.setRowHeight(20);
        tblout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbloutMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblout);

        pnlou.add(jScrollPane12);
        jScrollPane12.setBounds(10, 40, 950, 390);

        tblticconf.setAutoCreateRowSorter(true);
        tblticconf.setFont(tblticconf.getFont().deriveFont((float)11));
        tblticconf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Confirm Username", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblticconf.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblticconf.setRowHeight(20);
        tblticconf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblticconfMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tblticconf);

        pnlou.add(jScrollPane17);
        jScrollPane17.setBounds(10, 430, 950, 0);

        jLabel59.setFont(jLabel59.getFont().deriveFont((float)11));
        jLabel59.setText("Open From");
        pnlou.add(jLabel59);
        jLabel59.setBounds(10, 10, 100, 10);

        dctic7.setDateFormatString("dd/MM/yyyy");
        dctic7.setFont(dctic7.getFont().deriveFont((float)11));
        pnlou.add(dctic7);
        dctic7.setBounds(10, 20, 120, 24);

        dctic8.setDateFormatString("dd/MM/yyyy");
        dctic8.setFont(dctic8.getFont().deriveFont((float)11));
        pnlou.add(dctic8);
        dctic8.setBounds(130, 20, 120, 24);

        jLabel60.setFont(jLabel60.getFont().deriveFont((float)11));
        jLabel60.setText("Until");
        pnlou.add(jLabel60);
        jLabel60.setBounds(130, 10, 100, 10);

        jLabel61.setFont(jLabel61.getFont().deriveFont((float)11));
        jLabel61.setText("Agen");
        pnlou.add(jLabel61);
        jLabel61.setBounds(260, 10, 100, 10);

        btnoutsrch.setFont(btnoutsrch.getFont().deriveFont(btnoutsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnoutsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnoutsrch.setText("Search By");
        btnoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnoutsrchActionPerformed(evt);
            }
        });
        pnlou.add(btnoutsrch);
        btnoutsrch.setBounds(370, 20, 120, 24);

        cbagenou.setFont(cbagenou.getFont().deriveFont((float)11));
        cbagenou.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlou.add(cbagenou);
        cbagenou.setBounds(260, 20, 100, 24);

        lblcaloutcount.setFont(lblcaloutcount.getFont().deriveFont((float)11));
        lblcaloutcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlou.add(lblcaloutcount);
        lblcaloutcount.setBounds(880, 0, 40, 10);

        lblrepticcount11.setFont(lblrepticcount11.getFont().deriveFont((float)11));
        lblrepticcount11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount11.setText("list");
        pnlou.add(lblrepticcount11);
        lblrepticcount11.setBounds(920, 0, 40, 10);

        jtab.addTab("OutBound", pnlou);

        pnltic.setBackground(new java.awt.Color(255, 255, 255));
        pnltic.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnltic.setLayout(null);

        tbltic.setAutoCreateRowSorter(true);
        tbltic.setFont(tbltic.getFont().deriveFont((float)11));
        tbltic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ticket No.", "Status", "Category", "Assign Dept.", "Assign user", "Customer", "Phone Number", "User", "No.Plat", "Type", "Driver", "Phone", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltic.setAlignmentX(1.0F);
        tbltic.setAlignmentY(1.0F);
        tbltic.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbltic.setRowHeight(20);
        tbltic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblticMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltic);

        pnltic.add(jScrollPane3);
        jScrollPane3.setBounds(10, 60, 950, 260);

        jButton6.setFont(jButton6.getFont().deriveFont(jButton6.getFont().getStyle() | java.awt.Font.BOLD, 11));
        jButton6.setText("Open Ticket");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnltic.add(jButton6);
        jButton6.setBounds(850, 410, 110, 23);

        dctic3.setDateFormatString("dd/MM/yyyy");
        pnltic.add(dctic3);
        dctic3.setBounds(10, 20, 120, 24);

        jLabel52.setFont(jLabel52.getFont().deriveFont((float)11));
        jLabel52.setText("Open From");
        pnltic.add(jLabel52);
        jLabel52.setBounds(30, 10, 80, 10);

        btnticsrch.setFont(btnticsrch.getFont().deriveFont(btnticsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnticsrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnticsrch.setText("Search By");
        btnticsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnticsrchActionPerformed(evt);
            }
        });
        pnltic.add(btnticsrch);
        btnticsrch.setBounds(840, 20, 120, 24);

        dctic4.setDateFormatString("dd/MM/yyyy");
        pnltic.add(dctic4);
        dctic4.setBounds(130, 20, 120, 24);

        jLabel54.setFont(jLabel54.getFont().deriveFont((float)11));
        jLabel54.setText("Until");
        pnltic.add(jLabel54);
        jLabel54.setBounds(130, 10, 21, 10);

        btnsenddept.setFont(btnsenddept.getFont().deriveFont(btnsenddept.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnsenddept.setText("Send to Dept.");
        btnsenddept.setEnabled(false);
        btnsenddept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsenddeptActionPerformed(evt);
            }
        });
        pnltic.add(btnsenddept);
        btnsenddept.setBounds(10, 410, 130, 24);

        cktgl.setBackground(new java.awt.Color(255, 255, 204));
        cktgl.setFont(new java.awt.Font("Calibri", 0, 14));
        cktgl.setSelected(true);
        cktgl.setOpaque(false);
        cktgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cktglActionPerformed(evt);
            }
        });
        pnltic.add(cktgl);
        cktgl.setBounds(10, 0, 20, 20);

        jLabel73.setText("Solution :");
        pnltic.add(jLabel73);
        jLabel73.setBounds(490, 320, 60, 10);

        jLabel77.setText("Details :");
        pnltic.add(jLabel77);
        jLabel77.setBounds(10, 320, 60, 10);

        txtsolution.setColumns(20);
        txtsolution.setEditable(false);
        txtsolution.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtsolution.setLineWrap(true);
        txtsolution.setRows(5);
        jScrollPane37.setViewportView(txtsolution);

        pnltic.add(jScrollPane37);
        jScrollPane37.setBounds(490, 330, 470, 80);

        txtdetail.setColumns(20);
        txtdetail.setEditable(false);
        txtdetail.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtdetail.setLineWrap(true);
        txtdetail.setRows(5);
        jScrollPane38.setViewportView(txtdetail);

        pnltic.add(jScrollPane38);
        jScrollPane38.setBounds(10, 330, 470, 80);

        lblticcount.setFont(lblticcount.getFont().deriveFont((float)11));
        lblticcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnltic.add(lblticcount);
        lblticcount.setBounds(880, 0, 40, 10);

        lblrepticcount10.setFont(lblrepticcount10.getFont().deriveFont((float)11));
        lblrepticcount10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount10.setText("list");
        pnltic.add(lblrepticcount10);
        lblrepticcount10.setBounds(920, 0, 40, 10);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setPreferredSize(new java.awt.Dimension(1200, 60));
        jPanel25.setLayout(null);

        txtticno1.setFont(txtticno1.getFont().deriveFont((float)11));
        jPanel25.add(txtticno1);
        txtticno1.setBounds(10, 20, 80, 24);

        ckstoring.setBackground(new java.awt.Color(255, 255, 204));
        ckstoring.setFont(ckstoring.getFont().deriveFont((float)11));
        ckstoring.setText("Storing");
        ckstoring.setOpaque(false);
        ckstoring.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckstoringActionPerformed(evt);
            }
        });
        jPanel25.add(ckstoring);
        ckstoring.setBounds(420, 20, 80, 0);

        ckassign.setBackground(new java.awt.Color(255, 255, 204));
        ckassign.setFont(ckassign.getFont().deriveFont((float)11));
        ckassign.setText("Escalated");
        ckassign.setOpaque(false);
        jPanel25.add(ckassign);
        ckassign.setBounds(420, 20, 80, 0);

        jLabel68.setFont(jLabel68.getFont().deriveFont((float)11));
        jLabel68.setText("Status");
        jPanel25.add(jLabel68);
        jLabel68.setBounds(240, 10, 100, 10);

        jLabel63.setFont(jLabel63.getFont().deriveFont((float)11));
        jLabel63.setText("Dept.");
        jPanel25.add(jLabel63);
        jLabel63.setBounds(90, 10, 100, 10);

        cbticstatus.setFont(cbticstatus.getFont().deriveFont((float)11));
        cbticstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPEN", "PROCESS", "CLOSED", "CANCEL", "--" }));
        cbticstatus.setSelectedIndex(4);
        cbticstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbticstatusActionPerformed(evt);
            }
        });
        jPanel25.add(cbticstatus);
        cbticstatus.setBounds(240, 20, 100, 24);

        cbdept.setFont(cbdept.getFont().deriveFont((float)11));
        cbdept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPT. CALL CENTER", "DEPT. KENDARAAN KEBON JERUK", "DEPT. DRIVER", "DEPT. MARKETING", " ", "DEPT. KENDARAAN LUAR KOTA", "--" }));
        cbdept.setSelectedIndex(6);
        cbdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdeptActionPerformed(evt);
            }
        });
        jPanel25.add(cbdept);
        cbdept.setBounds(90, 20, 150, 24);

        cksubmit.setBackground(new java.awt.Color(255, 255, 204));
        cksubmit.setFont(cksubmit.getFont().deriveFont((float)11));
        cksubmit.setText("Not Submitted");
        cksubmit.setOpaque(false);
        cksubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cksubmitActionPerformed(evt);
            }
        });
        jPanel25.add(cksubmit);
        cksubmit.setBounds(340, 20, 120, 20);

        jLabel53.setFont(jLabel53.getFont().deriveFont((float)11));
        jLabel53.setText("No Ticket");
        jPanel25.add(jLabel53);
        jLabel53.setBounds(10, 10, 80, 10);

        jLabel94.setFont(jLabel94.getFont().deriveFont((float)11));
        jLabel94.setText("Customer");
        jPanel25.add(jLabel94);
        jLabel94.setBounds(440, 10, 80, 10);

        txtcus.setFont(txtcus.getFont().deriveFont((float)11));
        jPanel25.add(txtcus);
        txtcus.setBounds(440, 20, 110, 24);

        txtdriv.setFont(txtdriv.getFont().deriveFont((float)11));
        jPanel25.add(txtdriv);
        txtdriv.setBounds(770, 20, 110, 0);

        jLabel95.setFont(jLabel95.getFont().deriveFont((float)11));
        jLabel95.setText("Driver Name");
        jPanel25.add(jLabel95);
        jLabel95.setBounds(770, 10, 80, 0);

        jLabel96.setFont(jLabel96.getFont().deriveFont((float)11));
        jLabel96.setText("Category");
        jPanel25.add(jLabel96);
        jLabel96.setBounds(550, 10, 100, 10);

        cbcate.setFont(cbcate.getFont().deriveFont((float)11));
        cbcate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPT. CALL CENTER", "DEPT. KENDARAAN KEBON JERUK", "DEPT. DRIVER", "DEPT. MARKETING", " ", "DEPT. KENDARAAN LUAR KOTA", "--" }));
        cbcate.setSelectedIndex(6);
        cbcate.setEnabled(false);
        cbcate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcateActionPerformed(evt);
            }
        });
        jPanel25.add(cbcate);
        cbcate.setBounds(550, 20, 150, 24);

        txtdrivcode.setFont(txtdrivcode.getFont().deriveFont((float)11));
        jPanel25.add(txtdrivcode);
        txtdrivcode.setBounds(880, 20, 80, 0);

        jLabel97.setFont(jLabel97.getFont().deriveFont((float)11));
        jLabel97.setText("Driver Code");
        jPanel25.add(jLabel97);
        jLabel97.setBounds(880, 10, 80, 0);

        jLabel98.setFont(jLabel98.getFont().deriveFont((float)11));
        jLabel98.setText("Follow Up By");
        jPanel25.add(jLabel98);
        jLabel98.setBounds(420, 10, 100, 0);

        cbFollowUp.setFont(cbFollowUp.getFont().deriveFont((float)11));
        cbFollowUp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPEN", "PROCESS", "CLOSED", "CANCEL", "--" }));
        cbFollowUp.setSelectedIndex(4);
        cbFollowUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFollowUpActionPerformed(evt);
            }
        });
        jPanel25.add(cbFollowUp);
        cbFollowUp.setBounds(420, 20, 100, 0);

        jScrollPane47.setViewportView(jPanel25);

        pnltic.add(jScrollPane47);
        jScrollPane47.setBounds(250, 1, 590, 63);

        jtab.addTab("Ticket", pnltic);

        pnlact.setBackground(new java.awt.Color(255, 255, 255));
        pnlact.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlact.setLayout(null);

        tblact.setFont(tblact.getFont().deriveFont((float)11));
        tblact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Level", "Activity", "Info", "Loggin", "Host address", "Line number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblact.setRowHeight(20);
        tblact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblactMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblactMouseExited(evt);
            }
        });
        jScrollPane5.setViewportView(tblact);

        pnlact.add(jScrollPane5);
        jScrollPane5.setBounds(10, 30, 950, 400);

        btnrelease.setFont(btnrelease.getFont().deriveFont(btnrelease.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnrelease.setText("Release");
        btnrelease.setEnabled(false);
        btnrelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreleaseActionPerformed(evt);
            }
        });
        pnlact.add(btnrelease);
        btnrelease.setBounds(860, 10, 100, 24);

        cbagenrelease.setFont(cbagenrelease.getFont().deriveFont((float)11));
        cbagenrelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbagenreleaseActionPerformed(evt);
            }
        });
        pnlact.add(cbagenrelease);
        cbagenrelease.setBounds(690, 10, 170, 24);

        jtab.addTab("Activity Monitoring", pnlact);

        panelsms.setBackground(new java.awt.Color(255, 255, 255));
        panelsms.setFont(panelsms.getFont().deriveFont((float)10));
        panelsms.setOpaque(true);

        pninbox.setBackground(new java.awt.Color(255, 255, 255));
        pninbox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pninbox.setLayout(null);

        tblsin.setAutoCreateRowSorter(true);
        tblsin.setFont(tblsin.getFont().deriveFont((float)11));
        tblsin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sender", "Read", "Replied", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsin.setRowHeight(20);
        tblsin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsinMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblsin);

        pninbox.add(jScrollPane6);
        jScrollPane6.setBounds(10, 40, 930, 180);

        dtsi.setDateFormatString("dd/MM/yyyy");
        dtsi.setFont(dtsi.getFont().deriveFont((float)11));
        pninbox.add(dtsi);
        dtsi.setBounds(10, 20, 120, 24);

        dtsi1.setDateFormatString("dd/MM/yyyy");
        dtsi1.setFont(dtsi1.getFont().deriveFont((float)11));
        pninbox.add(dtsi1);
        dtsi1.setBounds(130, 20, 120, 24);

        jLabel1.setFont(jLabel1.getFont().deriveFont((float)11));
        jLabel1.setText("From :");
        pninbox.add(jLabel1);
        jLabel1.setBounds(10, 10, 100, 10);

        jLabel2.setFont(jLabel2.getFont().deriveFont((float)11));
        jLabel2.setText("Until :");
        pninbox.add(jLabel2);
        jLabel2.setBounds(130, 10, 100, 10);

        jLabel47.setFont(jLabel47.getFont().deriveFont((float)11));
        jLabel47.setText("Sender");
        pninbox.add(jLabel47);
        jLabel47.setBounds(10, 240, 100, 20);

        jLabel49.setFont(jLabel49.getFont().deriveFont((float)11));
        jLabel49.setText("messages :");
        pninbox.add(jLabel49);
        jLabel49.setBounds(10, 260, 100, 20);

        txtimsg2.setColumns(20);
        txtimsg2.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtimsg2.setLineWrap(true);
        txtimsg2.setRows(5);
        jScrollPane19.setViewportView(txtimsg2);

        pninbox.add(jScrollPane19);
        jScrollPane19.setBounds(110, 260, 550, 76);

        txtfrom2.setFont(txtfrom2.getFont().deriveFont((float)11));
        pninbox.add(txtfrom2);
        txtfrom2.setBounds(110, 240, 250, 24);

        btnsmsinsrch.setFont(btnsmsinsrch.getFont().deriveFont(btnsmsinsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnsmsinsrch.setText("Search");
        btnsmsinsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsinsrchActionPerformed(evt);
            }
        });
        pninbox.add(btnsmsinsrch);
        btnsmsinsrch.setBounds(260, 20, 90, 24);

        jLabel80.setFont(jLabel80.getFont().deriveFont((float)11));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Cust. Company");
        pninbox.add(jLabel80);
        jLabel80.setBounds(360, 240, 100, 0);

        cbcust.setFont(cbcust.getFont().deriveFont((float)11));
        cbcust.setMaximumRowCount(9);
        cbcust.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcustActionPerformed(evt);
            }
        });
        pninbox.add(cbcust);
        cbcust.setBounds(460, 240, 200, 0);

        btncussavesms.setFont(btncussavesms.getFont().deriveFont(btncussavesms.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btncussavesms.setText("Save");
        btncussavesms.setEnabled(false);
        btncussavesms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncussavesmsActionPerformed(evt);
            }
        });
        pninbox.add(btncussavesms);
        btncussavesms.setBounds(720, 240, 80, 24);

        jLabel91.setFont(jLabel91.getFont().deriveFont((float)11));
        jLabel91.setText("Ticket No");
        pninbox.add(jLabel91);
        jLabel91.setBounds(660, 260, 100, 20);

        txtnoticsms.setFont(txtnoticsms.getFont().deriveFont((float)11));
        pninbox.add(txtnoticsms);
        txtnoticsms.setBounds(660, 280, 140, 24);

        panelsms.addTab("InBox", pninbox);

        pnoutbox.setBackground(new java.awt.Color(255, 255, 255));
        pnoutbox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnoutbox.setLayout(null);

        tblsou.setAutoCreateRowSorter(true);
        tblsou.setFont(tblsou.getFont().deriveFont((float)11));
        tblsou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sent Time", "Send by", "Recipient", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsou.setRowHeight(20);
        tblsou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsouMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblsou);

        pnoutbox.add(jScrollPane7);
        jScrollPane7.setBounds(10, 40, 930, 180);

        jLabel3.setFont(jLabel3.getFont().deriveFont((float)11));
        jLabel3.setText("From :");
        pnoutbox.add(jLabel3);
        jLabel3.setBounds(10, 10, 100, 10);

        dtso.setDateFormatString("dd/MM/yyyy");
        pnoutbox.add(dtso);
        dtso.setBounds(10, 20, 120, 24);

        jLabel4.setFont(jLabel4.getFont().deriveFont((float)11));
        jLabel4.setText("Until :");
        pnoutbox.add(jLabel4);
        jLabel4.setBounds(130, 10, 100, 10);

        dtso1.setDateFormatString("dd/MM/yyyy");
        pnoutbox.add(dtso1);
        dtso1.setBounds(130, 20, 120, 24);

        jLabel8.setFont(jLabel8.getFont().deriveFont((float)11));
        jLabel8.setText("Recipient");
        pnoutbox.add(jLabel8);
        jLabel8.setBounds(10, 240, 100, 20);

        jLabel35.setFont(jLabel35.getFont().deriveFont((float)11));
        jLabel35.setText("messages :");
        pnoutbox.add(jLabel35);
        jLabel35.setBounds(10, 260, 100, 20);

        txtimsg1.setColumns(20);
        txtimsg1.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtimsg1.setLineWrap(true);
        txtimsg1.setRows(5);
        jScrollPane18.setViewportView(txtimsg1);

        pnoutbox.add(jScrollPane18);
        jScrollPane18.setBounds(110, 260, 510, 76);

        txtfrom1.setFont(txtfrom1.getFont().deriveFont((float)11));
        pnoutbox.add(txtfrom1);
        txtfrom1.setBounds(110, 240, 250, 24);

        btnsmsoutsrch.setFont(btnsmsoutsrch.getFont().deriveFont(btnsmsoutsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnsmsoutsrch.setText("Search");
        btnsmsoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmsoutsrchActionPerformed(evt);
            }
        });
        pnoutbox.add(btnsmsoutsrch);
        btnsmsoutsrch.setBounds(260, 20, 90, 24);

        panelsms.addTab("OutBox", pnoutbox);

        jtab.addTab("Sms", panelsms);

        panelmail.setBackground(new java.awt.Color(255, 255, 255));
        panelmail.setFont(panelmail.getFont().deriveFont((float)10));
        panelmail.setOpaque(true);
        panelmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelmailMouseClicked(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel9.setLayout(null);

        tblmin.setAutoCreateRowSorter(true);
        tblmin.setFont(tblmin.getFont().deriveFont((float)11));
        tblmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "From", "Subject", "Date", "Read", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmin.setRowHeight(20);
        tblmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblminMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblmin);

        jPanel9.add(jScrollPane8);
        jScrollPane8.setBounds(10, 40, 950, 180);

        jLabel5.setFont(jLabel5.getFont().deriveFont((float)11));
        jLabel5.setText("From :");
        jPanel9.add(jLabel5);
        jLabel5.setBounds(10, 10, 100, 10);

        txtfrom.setFont(txtfrom.getFont().deriveFont((float)11));
        jPanel9.add(txtfrom);
        txtfrom.setBounds(110, 240, 300, 24);

        jLabel11.setFont(jLabel11.getFont().deriveFont((float)11));
        jLabel11.setText("Subject :");
        jPanel9.add(jLabel11);
        jLabel11.setBounds(10, 260, 100, 20);

        txtisu.setFont(txtisu.getFont().deriveFont((float)11));
        txtisu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtisuActionPerformed(evt);
            }
        });
        jPanel9.add(txtisu);
        txtisu.setBounds(110, 260, 700, 24);

        jLabel12.setFont(jLabel12.getFont().deriveFont((float)11));
        jLabel12.setText("messages :");
        jPanel9.add(jLabel12);
        jLabel12.setBounds(10, 280, 100, 20);

        txtimsg.setColumns(20);
        txtimsg.setFont(txtimsg.getFont().deriveFont((float)11));
        txtimsg.setLineWrap(true);
        txtimsg.setRows(5);
        txtimsg.setAutoscrolls(false);
        jScrollPane9.setViewportView(txtimsg);

        jPanel9.add(jScrollPane9);
        jScrollPane9.setBounds(110, 280, 700, 110);

        jLabel18.setFont(jLabel18.getFont().deriveFont((float)11));
        jLabel18.setText("From :");
        jPanel9.add(jLabel18);
        jLabel18.setBounds(10, 240, 100, 20);

        dtmi.setDateFormatString("dd/MM/yyyy");
        dtmi.setFont(dtmi.getFont().deriveFont((float)11));
        jPanel9.add(dtmi);
        dtmi.setBounds(10, 20, 120, 24);

        jLabel48.setFont(jLabel48.getFont().deriveFont((float)11));
        jLabel48.setText("Until :");
        jPanel9.add(jLabel48);
        jLabel48.setBounds(130, 10, 100, 10);

        dtmi1.setDateFormatString("dd/MM/yyyy");
        dtmi1.setFont(dtmi1.getFont().deriveFont((float)11));
        jPanel9.add(dtmi1);
        dtmi1.setBounds(130, 20, 120, 24);

        btnmailinsrch.setFont(btnmailinsrch.getFont().deriveFont(btnmailinsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnmailinsrch.setText("Search");
        btnmailinsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailinsrchActionPerformed(evt);
            }
        });
        jPanel9.add(btnmailinsrch);
        btnmailinsrch.setBounds(260, 20, 90, 24);

        jLabel81.setFont(jLabel81.getFont().deriveFont((float)11));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Cust. Company");
        jPanel9.add(jLabel81);
        jLabel81.setBounds(410, 240, 100, 0);

        cbcust1.setFont(cbcust1.getFont().deriveFont((float)11));
        cbcust1.setMaximumRowCount(9);
        cbcust1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcust1ActionPerformed(evt);
            }
        });
        jPanel9.add(cbcust1);
        cbcust1.setBounds(510, 240, 200, 0);

        btncussaveEmail.setFont(btncussaveEmail.getFont().deriveFont(btncussaveEmail.getFont().getStyle() | java.awt.Font.BOLD));
        btncussaveEmail.setText("Save");
        btncussaveEmail.setEnabled(false);
        btncussaveEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncussaveEmailActionPerformed(evt);
            }
        });
        jPanel9.add(btncussaveEmail);
        btncussaveEmail.setBounds(730, 240, 80, 24);

        jLabel92.setFont(jLabel92.getFont().deriveFont((float)11));
        jLabel92.setText("Ticket No");
        jPanel9.add(jLabel92);
        jLabel92.setBounds(810, 220, 100, 20);

        txtnoticmail.setFont(txtnoticmail.getFont().deriveFont((float)11));
        jPanel9.add(txtnoticmail);
        txtnoticmail.setBounds(810, 240, 150, 24);

        btnAttachment.setFont(btnAttachment.getFont().deriveFont(btnAttachment.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnAttachment.setText("Download");
        btnAttachment.setEnabled(false);
        btnAttachment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachmentActionPerformed(evt);
            }
        });
        jPanel9.add(btnAttachment);
        btnAttachment.setBounds(860, 260, 100, 24);

        jList2.setFont(jList2.getFont().deriveFont((float)11));
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList2MouseReleased(evt);
            }
        });
        scpCcList1.setViewportView(jList2);

        jPanel9.add(scpCcList1);
        scpCcList1.setBounds(810, 280, 150, 110);

        panelmail.addTab("InBox", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel10.setLayout(null);

        tblmou.setAutoCreateRowSorter(true);
        tblmou.setFont(tblmou.getFont().deriveFont((float)11));
        tblmou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "To", "Subject", "Date", "Cc", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmou.setRowHeight(20);
        tblmou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmouMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblmou);

        jPanel10.add(jScrollPane10);
        jScrollPane10.setBounds(10, 40, 950, 180);

        txtoto.setFont(txtoto.getFont().deriveFont((float)11));
        jPanel10.add(txtoto);
        txtoto.setBounds(110, 240, 300, 24);

        jLabel13.setBackground(new java.awt.Color(255, 255, 204));
        jLabel13.setFont(jLabel13.getFont().deriveFont((float)11));
        jLabel13.setText("To :");
        jPanel10.add(jLabel13);
        jLabel13.setBounds(10, 240, 100, 20);

        txtocc.setFont(txtocc.getFont().deriveFont((float)11));
        jPanel10.add(txtocc);
        txtocc.setBounds(510, 240, 300, 24);

        jLabel14.setFont(jLabel14.getFont().deriveFont((float)11));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cc :");
        jPanel10.add(jLabel14);
        jLabel14.setBounds(410, 240, 100, 20);

        jLabel15.setFont(jLabel15.getFont().deriveFont((float)11));
        jLabel15.setText("Subject :");
        jPanel10.add(jLabel15);
        jLabel15.setBounds(10, 260, 100, 20);

        txtosu.setFont(txtosu.getFont().deriveFont((float)11));
        jPanel10.add(txtosu);
        txtosu.setBounds(110, 260, 700, 24);

        jLabel16.setFont(jLabel16.getFont().deriveFont((float)11));
        jLabel16.setText("messages :");
        jPanel10.add(jLabel16);
        jLabel16.setBounds(10, 280, 100, 20);

        txtomsg.setColumns(20);
        txtomsg.setEditable(false);
        txtomsg.setFont(txtomsg.getFont().deriveFont((float)11));
        txtomsg.setLineWrap(true);
        txtomsg.setRows(5);
        txtomsg.setAutoscrolls(false);
        jScrollPane11.setViewportView(txtomsg);

        jPanel10.add(jScrollPane11);
        jScrollPane11.setBounds(110, 280, 700, 110);

        jLabel17.setFont(jLabel17.getFont().deriveFont((float)11));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Ticket Id. :");
        jPanel10.add(jLabel17);
        jLabel17.setBounds(210, 220, 100, 20);

        txtidti.setFont(txtidti.getFont().deriveFont((float)11));
        jPanel10.add(txtidti);
        txtidti.setBounds(310, 220, 100, 24);

        jLabel50.setFont(jLabel50.getFont().deriveFont((float)11));
        jLabel50.setText("From :");
        jPanel10.add(jLabel50);
        jLabel50.setBounds(10, 10, 100, 10);

        dtmo.setDateFormatString("dd/MM/yyyy");
        dtmo.setFont(dtmo.getFont().deriveFont((float)11));
        jPanel10.add(dtmo);
        dtmo.setBounds(10, 20, 120, 24);

        jLabel51.setFont(jLabel51.getFont().deriveFont((float)11));
        jLabel51.setText("Until :");
        jPanel10.add(jLabel51);
        jLabel51.setBounds(130, 10, 100, 10);

        dtmo1.setDateFormatString("dd/MM/yyyy");
        dtmo1.setFont(dtmo1.getFont().deriveFont((float)11));
        jPanel10.add(dtmo1);
        dtmo1.setBounds(130, 20, 120, 24);

        btnmailoutsrch.setFont(btnmailoutsrch.getFont().deriveFont(btnmailoutsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnmailoutsrch.setText("Search");
        btnmailoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmailoutsrchActionPerformed(evt);
            }
        });
        jPanel10.add(btnmailoutsrch);
        btnmailoutsrch.setBounds(260, 20, 90, 24);

        jList3.setFont(jList3.getFont().deriveFont((float)11));
        jList3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList3MouseReleased(evt);
            }
        });
        scpCcList2.setViewportView(jList3);

        jPanel10.add(scpCcList2);
        scpCcList2.setBounds(810, 280, 150, 110);

        btnAttachment1.setFont(btnAttachment1.getFont().deriveFont(btnAttachment1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnAttachment1.setText("Download");
        btnAttachment1.setEnabled(false);
        btnAttachment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachment1ActionPerformed(evt);
            }
        });
        jPanel10.add(btnAttachment1);
        btnAttachment1.setBounds(860, 260, 100, 24);

        panelmail.addTab("OutBox", jPanel10);

        jtab.addTab("Email", panelmail);

        panelfax.setBackground(new java.awt.Color(255, 255, 255));
        panelfax.setFont(panelfax.getFont().deriveFont((float)10));
        panelfax.setOpaque(true);
        panelfax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelfaxMouseClicked(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel14.setLayout(null);

        tblfin.setAutoCreateRowSorter(true);
        tblfin.setFont(tblfin.getFont().deriveFont((float)11));
        tblfin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "From", "Subject", "Date", "Read", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblfin.setRowHeight(20);
        tblfin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblfinMouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(tblfin);

        jPanel14.add(jScrollPane28);
        jScrollPane28.setBounds(10, 40, 950, 140);

        jLabel65.setFont(jLabel65.getFont().deriveFont((float)11));
        jLabel65.setText("From :");
        jPanel14.add(jLabel65);
        jLabel65.setBounds(10, 10, 100, 10);

        dtfi.setDateFormatString("dd/MM/yyyy");
        dtfi.setFont(dtfi.getFont().deriveFont((float)11));
        jPanel14.add(dtfi);
        dtfi.setBounds(10, 20, 120, 24);

        jLabel69.setFont(jLabel69.getFont().deriveFont((float)11));
        jLabel69.setText("Until :");
        jPanel14.add(jLabel69);
        jLabel69.setBounds(130, 10, 100, 10);

        dtfi1.setDateFormatString("dd/MM/yyyy");
        dtfi1.setFont(dtfi1.getFont().deriveFont((float)11));
        jPanel14.add(dtfi1);
        dtfi1.setBounds(130, 20, 120, 24);

        btnfinsrch.setFont(btnfinsrch.getFont().deriveFont(btnfinsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnfinsrch.setText("Search");
        btnfinsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinsrchActionPerformed(evt);
            }
        });
        jPanel14.add(btnfinsrch);
        btnfinsrch.setBounds(260, 20, 100, 24);

        lblview.setBackground(new java.awt.Color(204, 204, 255));
        lblview.setOpaque(true);
        jScrollPane29.setViewportView(lblview);

        jPanel14.add(jScrollPane29);
        jScrollPane29.setBounds(10, 180, 950, 220);

        jLabel90.setFont(jLabel90.getFont().deriveFont((float)11));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Cust. Company");
        jPanel14.add(jLabel90);
        jLabel90.setBounds(670, 10, 90, 0);

        cbcust2.setFont(cbcust2.getFont().deriveFont((float)11));
        cbcust2.setMaximumRowCount(9);
        cbcust2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other" }));
        cbcust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcust2ActionPerformed(evt);
            }
        });
        jPanel14.add(cbcust2);
        cbcust2.setBounds(670, 20, 200, 0);

        btncussaveFax.setFont(btncussaveFax.getFont().deriveFont(btncussaveFax.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btncussaveFax.setText("Save");
        btncussaveFax.setEnabled(false);
        btncussaveFax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncussaveFaxActionPerformed(evt);
            }
        });
        jPanel14.add(btncussaveFax);
        btncussaveFax.setBounds(880, 20, 80, 24);

        jLabel93.setFont(jLabel93.getFont().deriveFont((float)11));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("Ticket No");
        jPanel14.add(jLabel93);
        jLabel93.setBounds(530, 10, 60, 10);

        txtnoticfax.setFont(txtnoticfax.getFont().deriveFont((float)11));
        jPanel14.add(txtnoticfax);
        txtnoticfax.setBounds(530, 20, 140, 24);

        panelfax.addTab("InBox", jPanel14);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel15.setLayout(null);

        tblfou.setAutoCreateRowSorter(true);
        tblfou.setFont(tblfou.getFont().deriveFont((float)11));
        tblfou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "To", "Subject", "Date", "Cc", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblfou.setRowHeight(20);
        tblfou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblfouMouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tblfou);

        jPanel15.add(jScrollPane30);
        jScrollPane30.setBounds(10, 40, 950, 140);

        jLabel75.setFont(jLabel75.getFont().deriveFont((float)11));
        jLabel75.setText("From :");
        jPanel15.add(jLabel75);
        jLabel75.setBounds(10, 10, 100, 10);

        dtfo.setDateFormatString("dd/MM/yyyy");
        dtfo.setFont(dtfo.getFont().deriveFont((float)11));
        jPanel15.add(dtfo);
        dtfo.setBounds(10, 20, 120, 24);

        jLabel76.setFont(jLabel76.getFont().deriveFont((float)11));
        jLabel76.setText("Until :");
        jPanel15.add(jLabel76);
        jLabel76.setBounds(130, 10, 100, 10);

        dtfo1.setDateFormatString("dd/MM/yyyy");
        dtfo1.setFont(dtfo1.getFont().deriveFont((float)11));
        jPanel15.add(dtfo1);
        dtfo1.setBounds(130, 20, 120, 24);

        btnfoutsrch.setFont(btnfoutsrch.getFont().deriveFont(btnfoutsrch.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnfoutsrch.setText("Search");
        btnfoutsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfoutsrchActionPerformed(evt);
            }
        });
        jPanel15.add(btnfoutsrch);
        btnfoutsrch.setBounds(260, 20, 90, 24);

        lblview1.setBackground(new java.awt.Color(204, 204, 255));
        lblview1.setOpaque(true);
        jScrollPane31.setViewportView(lblview1);

        jPanel15.add(jScrollPane31);
        jScrollPane31.setBounds(10, 180, 950, 220);

        panelfax.addTab("OutBox", jPanel15);

        jtab.addTab("Fax", panelfax);

        tabbpanereport.setBackground(new java.awt.Color(255, 255, 255));
        tabbpanereport.setFont(tabbpanereport.getFont().deriveFont(tabbpanereport.getFont().getStyle() | java.awt.Font.BOLD, 10));
        tabbpanereport.setOpaque(true);

        pnlrep.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep.setLayout(null);

        tblreptic.setFont(tblreptic.getFont().deriveFont((float)11));
        tblreptic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblreptic.setRowHeight(20);
        tblreptic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblrepticMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblreptic);

        pnlrep.add(jScrollPane4);
        jScrollPane4.setBounds(10, 60, 950, 320);

        btnreptic.setFont(btnreptic.getFont().deriveFont(btnreptic.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnreptic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnreptic.setText("Search");
        btnreptic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepticActionPerformed(evt);
            }
        });
        pnlrep.add(btnreptic);
        btnreptic.setBounds(850, 20, 100, 24);

        jLabel37.setFont(jLabel37.getFont().deriveFont((float)11));
        jLabel37.setText("Open From");
        pnlrep.add(jLabel37);
        jLabel37.setBounds(10, 10, 100, 10);

        jLabel38.setFont(jLabel38.getFont().deriveFont((float)11));
        jLabel38.setText("Until");
        pnlrep.add(jLabel38);
        jLabel38.setBounds(130, 10, 100, 10);

        dctic1.setDateFormatString("dd/MM/yyyy");
        dctic1.setFont(dctic1.getFont().deriveFont((float)11));
        pnlrep.add(dctic1);
        dctic1.setBounds(10, 20, 120, 24);

        dctic2.setDateFormatString("dd/MM/yyyy");
        dctic2.setFont(dctic2.getFont().deriveFont((float)11));
        pnlrep.add(dctic2);
        dctic2.setBounds(130, 20, 120, 24);

        btnexporttic.setFont(btnexporttic.getFont().deriveFont(btnexporttic.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexporttic.setText("Export");
        btnexporttic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportticActionPerformed(evt);
            }
        });
        pnlrep.add(btnexporttic);
        btnexporttic.setBounds(10, 380, 90, 20);

        lblrepticcount.setFont(lblrepticcount.getFont().deriveFont((float)11));
        lblrepticcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep.add(lblrepticcount);
        lblrepticcount.setBounds(880, 0, 40, 10);

        lblrepticcount1.setFont(lblrepticcount1.getFont().deriveFont((float)11));
        lblrepticcount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount1.setText("list");
        pnlrep.add(lblrepticcount1);
        lblrepticcount1.setBounds(920, 0, 40, 10);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setPreferredSize(new java.awt.Dimension(1200, 45));
        jPanel35.setLayout(null);

        txtticno2.setFont(txtticno2.getFont().deriveFont((float)11));
        jPanel35.add(txtticno2);
        txtticno2.setBounds(10, 20, 80, 24);

        ckstoring1.setBackground(new java.awt.Color(255, 255, 204));
        ckstoring1.setFont(ckstoring1.getFont().deriveFont((float)11));
        ckstoring1.setText("Storing");
        ckstoring1.setOpaque(false);
        ckstoring1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckstoring1ActionPerformed(evt);
            }
        });
        jPanel35.add(ckstoring1);
        ckstoring1.setBounds(420, 20, 80, 0);

        ckassign1.setBackground(new java.awt.Color(255, 255, 204));
        ckassign1.setFont(ckassign1.getFont().deriveFont((float)11));
        ckassign1.setText("Escalated");
        ckassign1.setOpaque(false);
        jPanel35.add(ckassign1);
        ckassign1.setBounds(420, 20, 80, 0);

        jLabel102.setFont(jLabel102.getFont().deriveFont((float)11));
        jLabel102.setText("Status");
        jPanel35.add(jLabel102);
        jLabel102.setBounds(240, 10, 100, 10);

        jLabel103.setFont(jLabel103.getFont().deriveFont((float)11));
        jLabel103.setText("Dept.");
        jPanel35.add(jLabel103);
        jLabel103.setBounds(90, 10, 100, 10);

        cbticstatus1.setFont(cbticstatus1.getFont().deriveFont((float)11));
        cbticstatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPEN", "PROCESS", "CLOSED", "CANCEL", "--" }));
        cbticstatus1.setSelectedIndex(4);
        cbticstatus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbticstatus1ActionPerformed(evt);
            }
        });
        jPanel35.add(cbticstatus1);
        cbticstatus1.setBounds(240, 20, 100, 24);

        cbdept1.setFont(cbdept1.getFont().deriveFont((float)11));
        cbdept1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPT. CALL CENTER", "DEPT. KENDARAAN KEBON JERUK", "DEPT. DRIVER", "DEPT. MARKETING", " ", "DEPT. KENDARAAN LUAR KOTA", "--" }));
        cbdept1.setSelectedIndex(6);
        cbdept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdept1ActionPerformed(evt);
            }
        });
        jPanel35.add(cbdept1);
        cbdept1.setBounds(90, 20, 150, 24);

        cksubmit1.setBackground(new java.awt.Color(255, 255, 204));
        cksubmit1.setFont(cksubmit1.getFont().deriveFont((float)11));
        cksubmit1.setText("Not Submitted");
        cksubmit1.setOpaque(false);
        cksubmit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cksubmit1ActionPerformed(evt);
            }
        });
        jPanel35.add(cksubmit1);
        cksubmit1.setBounds(340, 20, 120, 20);

        jLabel62.setFont(jLabel62.getFont().deriveFont((float)11));
        jLabel62.setText("No Ticket");
        jPanel35.add(jLabel62);
        jLabel62.setBounds(10, 10, 80, 10);

        jLabel104.setFont(jLabel104.getFont().deriveFont((float)11));
        jLabel104.setText("Customer");
        jPanel35.add(jLabel104);
        jLabel104.setBounds(440, 10, 80, 10);

        txtcus1.setFont(txtcus1.getFont().deriveFont((float)11));
        jPanel35.add(txtcus1);
        txtcus1.setBounds(440, 20, 110, 24);

        txtdriv1.setFont(txtdriv1.getFont().deriveFont((float)11));
        jPanel35.add(txtdriv1);
        txtdriv1.setBounds(770, 20, 110, 0);

        jLabel105.setFont(jLabel105.getFont().deriveFont((float)11));
        jLabel105.setText("Driver Name");
        jPanel35.add(jLabel105);
        jLabel105.setBounds(770, 10, 80, 0);

        jLabel106.setFont(jLabel106.getFont().deriveFont((float)11));
        jLabel106.setText("Category");
        jPanel35.add(jLabel106);
        jLabel106.setBounds(550, 10, 100, 10);

        cbcate1.setFont(cbcate1.getFont().deriveFont((float)11));
        cbcate1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPT. CALL CENTER", "DEPT. KENDARAAN KEBON JERUK", "DEPT. DRIVER", "DEPT. MARKETING", " ", "DEPT. KENDARAAN LUAR KOTA", "--" }));
        cbcate1.setSelectedIndex(6);
        cbcate1.setEnabled(false);
        cbcate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcate1ActionPerformed(evt);
            }
        });
        jPanel35.add(cbcate1);
        cbcate1.setBounds(550, 20, 150, 24);

        txtdrivcode1.setFont(txtdrivcode1.getFont().deriveFont((float)11));
        jPanel35.add(txtdrivcode1);
        txtdrivcode1.setBounds(880, 20, 80, 0);

        jLabel107.setFont(jLabel107.getFont().deriveFont((float)11));
        jLabel107.setText("Driver Code");
        jPanel35.add(jLabel107);
        jLabel107.setBounds(880, 10, 80, 0);

        jLabel108.setFont(jLabel108.getFont().deriveFont((float)11));
        jLabel108.setText("Follow Up By");
        jPanel35.add(jLabel108);
        jLabel108.setBounds(420, 10, 100, 0);

        cbFollowUp1.setFont(cbFollowUp1.getFont().deriveFont((float)11));
        cbFollowUp1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OPEN", "PROCESS", "CLOSED", "CANCEL", "--" }));
        cbFollowUp1.setSelectedIndex(4);
        cbFollowUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFollowUp1ActionPerformed(evt);
            }
        });
        jPanel35.add(cbFollowUp1);
        cbFollowUp1.setBounds(420, 20, 100, 0);

        jScrollPane87.setViewportView(jPanel35);

        pnlrep.add(jScrollPane87);
        jScrollPane87.setBounds(250, 1, 590, 60);

        tabbpanereport.addTab("Tickets", pnlrep);

        pnlrep1.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep1.setLayout(null);

        tblrepcal.setFont(tblrepcal.getFont().deriveFont((float)11));
        tblrepcal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblrepcal.setRowHeight(20);
        jScrollPane13.setViewportView(tblrepcal);

        pnlrep1.add(jScrollPane13);
        jScrollPane13.setBounds(10, 40, 950, 340);

        jLabel21.setFont(jLabel21.getFont().deriveFont((float)11));
        jLabel21.setText("Call Status");
        pnlrep1.add(jLabel21);
        jLabel21.setBounds(260, 10, 100, 10);

        jLabel22.setFont(jLabel22.getFont().deriveFont((float)11));
        jLabel22.setText("Direction");
        pnlrep1.add(jLabel22);
        jLabel22.setBounds(360, 10, 100, 10);

        jLabel23.setFont(jLabel23.getFont().deriveFont((float)11));
        jLabel23.setText("Caller Type");
        pnlrep1.add(jLabel23);
        jLabel23.setBounds(460, 10, 100, 0);

        btnrepcal.setFont(btnrepcal.getFont().deriveFont(btnrepcal.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnrepcal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepcal.setText("Search Call");
        btnrepcal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepcalActionPerformed(evt);
            }
        });
        pnlrep1.add(btnrepcal);
        btnrepcal.setBounds(670, 20, 130, 24);

        jLabel24.setFont(jLabel24.getFont().deriveFont((float)11));
        jLabel24.setText("Agen");
        pnlrep1.add(jLabel24);
        jLabel24.setBounds(460, 10, 100, 10);

        jLabel39.setFont(jLabel39.getFont().deriveFont((float)11));
        jLabel39.setText("Open From");
        pnlrep1.add(jLabel39);
        jLabel39.setBounds(10, 10, 100, 10);

        dccal1.setDateFormatString("dd/MM/yyyy");
        dccal1.setFont(dccal1.getFont().deriveFont((float)11));
        pnlrep1.add(dccal1);
        dccal1.setBounds(10, 20, 120, 24);

        jLabel40.setFont(jLabel40.getFont().deriveFont((float)11));
        jLabel40.setText("Until");
        pnlrep1.add(jLabel40);
        jLabel40.setBounds(130, 10, 100, 10);

        dccal2.setDateFormatString("dd/MM/yyyy");
        dccal2.setFont(dccal2.getFont().deriveFont((float)11));
        pnlrep1.add(dccal2);
        dccal2.setBounds(130, 20, 120, 24);

        cbcaldir.setFont(cbcaldir.getFont().deriveFont((float)11));
        cbcaldir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbcaldir.setSelectedIndex(2);
        pnlrep1.add(cbcaldir);
        cbcaldir.setBounds(360, 20, 100, 24);

        cbcalstat.setFont(cbcalstat.getFont().deriveFont((float)11));
        cbcalstat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ABANDON", "ANSWERED", "PHANTOM", "--" }));
        cbcalstat.setSelectedIndex(3);
        cbcalstat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcalstatActionPerformed(evt);
            }
        });
        pnlrep1.add(cbcalstat);
        cbcalstat.setBounds(260, 20, 100, 24);

        cbagenirepcal.setFont(cbagenirepcal.getFont().deriveFont((float)11));
        cbagenirepcal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david" }));
        pnlrep1.add(cbagenirepcal);
        cbagenirepcal.setBounds(460, 20, 100, 24);

        cbcaltyperepcal.setFont(cbcaltyperepcal.getFont().deriveFont((float)11));
        cbcaltyperepcal.setMaximumRowCount(10);
        cbcaltyperepcal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Non-customer", "Customer-Driver", "Customer-User", "Customer-PIC", "Customer-Other", "Internal-ANJ", "Internal-CC", "Internal-CSO", "Internal-Driver", "Internal-Other", "--" }));
        cbcaltyperepcal.setSelectedIndex(10);
        pnlrep1.add(cbcaltyperepcal);
        cbcaltyperepcal.setBounds(460, 20, 100, 0);

        btnexportcall.setFont(btnexportcall.getFont().deriveFont(btnexportcall.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportcall.setText("Export");
        btnexportcall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcallActionPerformed(evt);
            }
        });
        pnlrep1.add(btnexportcall);
        btnexportcall.setBounds(10, 380, 90, 20);

        lblrepcalcount.setFont(lblrepcalcount.getFont().deriveFont((float)11));
        lblrepcalcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep1.add(lblrepcalcount);
        lblrepcalcount.setBounds(880, 0, 40, 10);

        lblrepticcount3.setFont(lblrepticcount3.getFont().deriveFont((float)11));
        lblrepticcount3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount3.setText("list");
        pnlrep1.add(lblrepticcount3);
        lblrepticcount3.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("Calls", pnlrep1);

        jTabbedPane1.setFont(jTabbedPane1.getFont().deriveFont((float)10));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(null);

        tblhourin.setFont(tblhourin.getFont().deriveFont((float)11));
        tblhourin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane39.setViewportView(tblhourin);

        jPanel17.add(jScrollPane39);
        jScrollPane39.setBounds(10, 40, 940, 310);

        jLabel78.setFont(jLabel78.getFont().deriveFont((float)11));
        jLabel78.setText("Date");
        jPanel17.add(jLabel78);
        jLabel78.setBounds(10, 10, 100, 10);

        dthi.setDateFormatString("dd/MM/yyyy");
        dthi.setFont(dthi.getFont().deriveFont((float)11));
        jPanel17.add(dthi);
        dthi.setBounds(10, 20, 120, 24);

        btnhi.setFont(btnhi.getFont().deriveFont(btnhi.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnhi.setText("Refresh");
        btnhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhiActionPerformed(evt);
            }
        });
        jPanel17.add(btnhi);
        btnhi.setBounds(150, 20, 115, 24);

        btnexportcall1.setFont(btnexportcall1.getFont().deriveFont(btnexportcall1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportcall1.setText("Export");
        btnexportcall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall1ActionPerformed(evt);
            }
        });
        jPanel17.add(btnexportcall1);
        btnexportcall1.setBounds(10, 350, 90, 20);

        jTabbedPane1.addTab("Inbound", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(null);

        tblhourout.setFont(tblhourout.getFont().deriveFont((float)11));
        tblhourout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane40.setViewportView(tblhourout);

        jPanel18.add(jScrollPane40);
        jScrollPane40.setBounds(10, 40, 940, 310);

        dtho.setDateFormatString("dd/MM/yyyy");
        dtho.setFont(dtho.getFont().deriveFont((float)11));
        jPanel18.add(dtho);
        dtho.setBounds(10, 20, 120, 24);

        jLabel79.setFont(jLabel79.getFont().deriveFont((float)11));
        jLabel79.setText("Date");
        jPanel18.add(jLabel79);
        jLabel79.setBounds(10, 10, 100, 10);

        btnho.setFont(btnho.getFont().deriveFont(btnho.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnho.setText("Refresh");
        btnho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoActionPerformed(evt);
            }
        });
        jPanel18.add(btnho);
        btnho.setBounds(150, 20, 115, 24);

        btnexportcall2.setFont(btnexportcall2.getFont().deriveFont(btnexportcall2.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportcall2.setText("Export");
        btnexportcall2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall2ActionPerformed(evt);
            }
        });
        jPanel18.add(btnexportcall2);
        btnexportcall2.setBounds(10, 350, 90, 20);

        jTabbedPane1.addTab("Outbound", jPanel18);

        tabbpanereport.addTab("Hourly Calls", jTabbedPane1);

        jTabbedPane4.setFont(jTabbedPane4.getFont().deriveFont((float)10));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(null);

        tbldailyin.setFont(tbldailyin.getFont().deriveFont((float)11));
        tbldailyin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane43.setViewportView(tbldailyin);

        jPanel21.add(jScrollPane43);
        jScrollPane43.setBounds(10, 40, 940, 310);

        jLabel82.setFont(jLabel82.getFont().deriveFont((float)11));
        jLabel82.setText("From");
        jPanel21.add(jLabel82);
        jLabel82.setBounds(10, 10, 100, 10);

        dtdi.setDateFormatString("dd/MM/yyyy");
        dtdi.setFont(dtdi.getFont().deriveFont((float)11));
        jPanel21.add(dtdi);
        dtdi.setBounds(10, 20, 120, 24);

        btndi.setFont(btndi.getFont().deriveFont(btndi.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btndi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btndi.setText("Refresh");
        btndi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndiActionPerformed(evt);
            }
        });
        jPanel21.add(btndi);
        btndi.setBounds(270, 20, 115, 24);

        jLabel84.setFont(jLabel84.getFont().deriveFont((float)11));
        jLabel84.setText("Until");
        jPanel21.add(jLabel84);
        jLabel84.setBounds(140, 10, 100, 10);

        dtdi1.setDateFormatString("dd/MM/yyyy");
        dtdi1.setFont(dtdi1.getFont().deriveFont((float)11));
        jPanel21.add(dtdi1);
        dtdi1.setBounds(140, 20, 120, 24);

        btnexportcall3.setFont(btnexportcall3.getFont().deriveFont(btnexportcall3.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportcall3.setText("Export");
        btnexportcall3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall3ActionPerformed(evt);
            }
        });
        jPanel21.add(btnexportcall3);
        btnexportcall3.setBounds(10, 350, 90, 20);

        jTabbedPane4.addTab("Inbound", jPanel21);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(null);

        tbldailyout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane44.setViewportView(tbldailyout);

        jPanel22.add(jScrollPane44);
        jScrollPane44.setBounds(10, 40, 940, 310);

        dtdo.setDateFormatString("dd/MM/yyyy");
        jPanel22.add(dtdo);
        dtdo.setBounds(10, 20, 120, 24);

        jLabel83.setFont(new java.awt.Font("Calibri", 0, 14));
        jLabel83.setText("From");
        jPanel22.add(jLabel83);
        jLabel83.setBounds(10, 10, 100, 10);

        btndo.setFont(new java.awt.Font("Calibri", 0, 14));
        btndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btndo.setText("Refresh");
        btndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndoActionPerformed(evt);
            }
        });
        jPanel22.add(btndo);
        btndo.setBounds(270, 20, 115, 24);

        jLabel85.setFont(new java.awt.Font("Calibri", 0, 14));
        jLabel85.setText("Until");
        jPanel22.add(jLabel85);
        jLabel85.setBounds(140, 10, 100, 10);

        dtdo1.setDateFormatString("dd/MM/yyyy");
        jPanel22.add(dtdo1);
        dtdo1.setBounds(140, 20, 120, 24);

        btnexportcall4.setFont(new java.awt.Font("Calibri", 0, 14));
        btnexportcall4.setText("Export");
        btnexportcall4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall4ActionPerformed(evt);
            }
        });
        jPanel22.add(btnexportcall4);
        btnexportcall4.setBounds(10, 350, 90, 20);

        jTabbedPane4.addTab("Outbound", jPanel22);

        tabbpanereport.addTab("Daily Calls", jTabbedPane4);

        jTabbedPane3.setFont(jTabbedPane3.getFont().deriveFont((float)10));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(null);

        tblperformin.setFont(tblperformin.getFont().deriveFont((float)11));
        tblperformin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane41.setViewportView(tblperformin);

        jPanel19.add(jScrollPane41);
        jScrollPane41.setBounds(10, 40, 940, 310);

        dtpi.setDateFormatString("dd/MM/yyyy");
        dtpi.setFont(dtpi.getFont().deriveFont((float)11));
        jPanel19.add(dtpi);
        dtpi.setBounds(10, 20, 120, 24);

        btnpi1.setFont(btnpi1.getFont().deriveFont(btnpi1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnpi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnpi1.setText("Refresh");
        btnpi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpi1ActionPerformed(evt);
            }
        });
        jPanel19.add(btnpi1);
        btnpi1.setBounds(270, 20, 115, 24);

        jLabel86.setFont(jLabel86.getFont().deriveFont((float)11));
        jLabel86.setText("Until");
        jPanel19.add(jLabel86);
        jLabel86.setBounds(140, 10, 100, 10);

        dtpi1.setDateFormatString("dd/MM/yyyy");
        dtpi1.setFont(dtpi1.getFont().deriveFont((float)11));
        jPanel19.add(dtpi1);
        dtpi1.setBounds(140, 20, 120, 24);

        jLabel88.setFont(jLabel88.getFont().deriveFont((float)11));
        jLabel88.setText("From");
        jPanel19.add(jLabel88);
        jLabel88.setBounds(10, 10, 100, 10);

        btnexportcall5.setFont(btnexportcall5.getFont().deriveFont(btnexportcall5.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportcall5.setText("Export");
        btnexportcall5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall5ActionPerformed(evt);
            }
        });
        jPanel19.add(btnexportcall5);
        btnexportcall5.setBounds(10, 350, 90, 20);

        jTabbedPane3.addTab("Inbound", jPanel19);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(null);

        tblperformout.setFont(tblperformout.getFont().deriveFont((float)11));
        tblperformout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane42.setViewportView(tblperformout);

        jPanel20.add(jScrollPane42);
        jScrollPane42.setBounds(10, 40, 940, 310);

        dtpo.setDateFormatString("dd/MM/yyyy");
        dtpo.setFont(dtpo.getFont().deriveFont((float)11));
        jPanel20.add(dtpo);
        dtpo.setBounds(10, 20, 120, 24);

        btnpo1.setFont(btnpo1.getFont().deriveFont(btnpo1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnpo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnpo1.setText("Refresh");
        btnpo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpo1ActionPerformed(evt);
            }
        });
        jPanel20.add(btnpo1);
        btnpo1.setBounds(270, 20, 115, 24);

        jLabel87.setFont(jLabel87.getFont().deriveFont((float)11));
        jLabel87.setText("Until");
        jPanel20.add(jLabel87);
        jLabel87.setBounds(140, 10, 100, 10);

        dtpo1.setDateFormatString("dd/MM/yyyy");
        dtpo1.setFont(dtpo1.getFont().deriveFont((float)11));
        jPanel20.add(dtpo1);
        dtpo1.setBounds(140, 20, 120, 24);

        jLabel89.setFont(jLabel89.getFont().deriveFont((float)11));
        jLabel89.setText("From");
        jPanel20.add(jLabel89);
        jLabel89.setBounds(10, 10, 100, 10);

        btnexportcall6.setFont(btnexportcall6.getFont().deriveFont(btnexportcall6.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportcall6.setText("Export");
        btnexportcall6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportcall6ActionPerformed(evt);
            }
        });
        jPanel20.add(btnexportcall6);
        btnexportcall6.setBounds(10, 350, 90, 20);

        jTabbedPane3.addTab("Outbound", jPanel20);

        tabbpanereport.addTab("Performance", jTabbedPane3);

        pnlrep2.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep2.setLayout(null);

        tblrepsms.setFont(tblrepsms.getFont().deriveFont((float)11));
        tblrepsms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblrepsms.setRowHeight(20);
        jScrollPane14.setViewportView(tblrepsms);

        pnlrep2.add(jScrollPane14);
        jScrollPane14.setBounds(10, 40, 950, 340);

        txtsmsstat.setFont(txtsmsstat.getFont().deriveFont((float)11));
        pnlrep2.add(txtsmsstat);
        txtsmsstat.setBounds(260, 20, 100, 24);

        jLabel25.setFont(jLabel25.getFont().deriveFont((float)11));
        jLabel25.setText("Status");
        pnlrep2.add(jLabel25);
        jLabel25.setBounds(260, 10, 100, 10);

        jLabel26.setFont(jLabel26.getFont().deriveFont((float)11));
        jLabel26.setText("Direction");
        pnlrep2.add(jLabel26);
        jLabel26.setBounds(360, 10, 100, 10);

        btnrepsms.setFont(btnrepsms.getFont().deriveFont(btnrepsms.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnrepsms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepsms.setText("Search Messages");
        btnrepsms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepsmsActionPerformed(evt);
            }
        });
        pnlrep2.add(btnrepsms);
        btnrepsms.setBounds(670, 20, 170, 24);

        jLabel28.setFont(jLabel28.getFont().deriveFont((float)11));
        jLabel28.setText("Agen");
        pnlrep2.add(jLabel28);
        jLabel28.setBounds(560, 10, 100, 10);

        jLabel43.setFont(jLabel43.getFont().deriveFont((float)11));
        jLabel43.setText("Open From");
        pnlrep2.add(jLabel43);
        jLabel43.setBounds(10, 10, 100, 10);

        dcsms1.setDateFormatString("dd/MM/yyyy");
        dcsms1.setFont(dcsms1.getFont().deriveFont((float)11));
        pnlrep2.add(dcsms1);
        dcsms1.setBounds(10, 20, 120, 24);

        jLabel44.setFont(jLabel44.getFont().deriveFont((float)11));
        jLabel44.setText("Until");
        pnlrep2.add(jLabel44);
        jLabel44.setBounds(130, 10, 100, 10);

        dcsms2.setDateFormatString("dd/MM/yyyy");
        dcsms2.setFont(dcsms2.getFont().deriveFont((float)11));
        pnlrep2.add(dcsms2);
        dcsms2.setBounds(130, 20, 120, 24);

        txtsmsticid.setFont(txtsmsticid.getFont().deriveFont((float)11));
        pnlrep2.add(txtsmsticid);
        txtsmsticid.setBounds(460, 20, 100, 24);

        jLabel27.setFont(jLabel27.getFont().deriveFont((float)11));
        jLabel27.setText("Ticket No");
        pnlrep2.add(jLabel27);
        jLabel27.setBounds(460, 10, 100, 10);

        cbdirrepsms.setFont(cbdirrepsms.getFont().deriveFont((float)11));
        cbdirrepsms.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbdirrepsms.setSelectedIndex(2);
        pnlrep2.add(cbdirrepsms);
        cbdirrepsms.setBounds(360, 20, 100, 24);

        cbagenirepcal1.setFont(cbagenirepcal1.getFont().deriveFont((float)11));
        cbagenirepcal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlrep2.add(cbagenirepcal1);
        cbagenirepcal1.setBounds(560, 20, 100, 24);

        btnexportsms.setFont(btnexportsms.getFont().deriveFont(btnexportsms.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportsms.setText("Export");
        btnexportsms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportsmsActionPerformed(evt);
            }
        });
        pnlrep2.add(btnexportsms);
        btnexportsms.setBounds(10, 380, 90, 20);

        lblrepsmscount.setFont(lblrepsmscount.getFont().deriveFont((float)11));
        lblrepsmscount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep2.add(lblrepsmscount);
        lblrepsmscount.setBounds(880, 0, 40, 10);

        lblrepticcount5.setFont(lblrepticcount5.getFont().deriveFont((float)11));
        lblrepticcount5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount5.setText("list");
        pnlrep2.add(lblrepticcount5);
        lblrepticcount5.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("SMS", pnlrep2);

        pnlrep3.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep3.setLayout(null);

        tblrepmail.setFont(tblrepmail.getFont().deriveFont((float)11));
        tblrepmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblrepmail.setRowHeight(20);
        jScrollPane15.setViewportView(tblrepmail);

        pnlrep3.add(jScrollPane15);
        jScrollPane15.setBounds(10, 40, 950, 340);

        jLabel29.setFont(jLabel29.getFont().deriveFont((float)11));
        jLabel29.setText("Direction");
        pnlrep3.add(jLabel29);
        jLabel29.setBounds(360, 10, 100, 10);

        jLabel30.setFont(jLabel30.getFont().deriveFont((float)11));
        jLabel30.setText("Agen");
        pnlrep3.add(jLabel30);
        jLabel30.setBounds(560, 10, 100, 10);

        txtmailticid.setFont(txtmailticid.getFont().deriveFont((float)11));
        pnlrep3.add(txtmailticid);
        txtmailticid.setBounds(460, 20, 100, 24);

        jLabel31.setFont(jLabel31.getFont().deriveFont((float)11));
        jLabel31.setText("Ticket no");
        pnlrep3.add(jLabel31);
        jLabel31.setBounds(460, 10, 100, 10);

        btnrepmail.setFont(btnrepmail.getFont().deriveFont(btnrepmail.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnrepmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepmail.setText("Search Mail");
        btnrepmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepmailActionPerformed(evt);
            }
        });
        pnlrep3.add(btnrepmail);
        btnrepmail.setBounds(670, 20, 140, 24);

        jLabel32.setFont(jLabel32.getFont().deriveFont((float)11));
        jLabel32.setText("Mail subject");
        pnlrep3.add(jLabel32);
        jLabel32.setBounds(260, 10, 100, 10);

        txtmailsub.setFont(txtmailsub.getFont().deriveFont((float)11));
        pnlrep3.add(txtmailsub);
        txtmailsub.setBounds(260, 20, 100, 24);

        jLabel45.setFont(jLabel45.getFont().deriveFont((float)11));
        jLabel45.setText("Open From");
        pnlrep3.add(jLabel45);
        jLabel45.setBounds(10, 10, 100, 10);

        dcmail1.setDateFormatString("dd/MM/yyyy");
        dcmail1.setFont(dcmail1.getFont().deriveFont((float)11));
        pnlrep3.add(dcmail1);
        dcmail1.setBounds(10, 20, 120, 24);

        jLabel46.setFont(jLabel46.getFont().deriveFont((float)11));
        jLabel46.setText("Until");
        pnlrep3.add(jLabel46);
        jLabel46.setBounds(130, 10, 100, 10);

        dcmail2.setDateFormatString("dd/MM/yyyy");
        dcmail2.setFont(dcmail2.getFont().deriveFont((float)11));
        pnlrep3.add(dcmail2);
        dcmail2.setBounds(130, 20, 120, 24);

        cbdirmail.setFont(cbdirmail.getFont().deriveFont((float)11));
        cbdirmail.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbdirmail.setSelectedIndex(2);
        pnlrep3.add(cbdirmail);
        cbdirmail.setBounds(360, 20, 100, 24);

        cbagenrepmail.setFont(cbagenrepmail.getFont().deriveFont((float)11));
        cbagenrepmail.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlrep3.add(cbagenrepmail);
        cbagenrepmail.setBounds(560, 20, 100, 24);

        btnexportmail.setFont(btnexportmail.getFont().deriveFont(btnexportmail.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportmail.setText("Export");
        btnexportmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportmailActionPerformed(evt);
            }
        });
        pnlrep3.add(btnexportmail);
        btnexportmail.setBounds(10, 380, 90, 20);

        lblrepmailcount.setFont(lblrepmailcount.getFont().deriveFont((float)11));
        lblrepmailcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep3.add(lblrepmailcount);
        lblrepmailcount.setBounds(880, 0, 40, 10);

        lblrepticcount7.setFont(lblrepticcount7.getFont().deriveFont((float)11));
        lblrepticcount7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount7.setText("list");
        pnlrep3.add(lblrepticcount7);
        lblrepticcount7.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("Email", pnlrep3);

        pnlrep4.setBackground(new java.awt.Color(255, 255, 255));
        pnlrep4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlrep4.setLayout(null);

        tblrepfax.setFont(tblrepfax.getFont().deriveFont((float)11));
        tblrepfax.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblrepfax.setRowHeight(20);
        jScrollPane16.setViewportView(tblrepfax);

        pnlrep4.add(jScrollPane16);
        jScrollPane16.setBounds(10, 40, 950, 340);

        txtfaxfinm.setFont(txtfaxfinm.getFont().deriveFont((float)11));
        pnlrep4.add(txtfaxfinm);
        txtfaxfinm.setBounds(460, 20, 100, 24);

        jLabel33.setFont(jLabel33.getFont().deriveFont((float)11));
        jLabel33.setText("Status");
        pnlrep4.add(jLabel33);
        jLabel33.setBounds(360, 10, 100, 10);

        jLabel34.setFont(jLabel34.getFont().deriveFont((float)11));
        jLabel34.setText("File name");
        pnlrep4.add(jLabel34);
        jLabel34.setBounds(460, 10, 100, 10);

        btnrepfax.setFont(btnrepfax.getFont().deriveFont(btnrepfax.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnrepfax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1245117595_001_37.png"))); // NOI18N
        btnrepfax.setText("Search Fax");
        btnrepfax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepfaxActionPerformed(evt);
            }
        });
        pnlrep4.add(btnrepfax);
        btnrepfax.setBounds(670, 20, 140, 24);

        jLabel36.setFont(jLabel36.getFont().deriveFont((float)11));
        jLabel36.setText("Username");
        pnlrep4.add(jLabel36);
        jLabel36.setBounds(560, 10, 100, 10);

        jLabel41.setFont(jLabel41.getFont().deriveFont((float)11));
        jLabel41.setText("Open From");
        pnlrep4.add(jLabel41);
        jLabel41.setBounds(10, 10, 100, 10);

        dcfax1.setDateFormatString("dd/MM/yyyy");
        dcfax1.setFont(dcfax1.getFont().deriveFont((float)11));
        pnlrep4.add(dcfax1);
        dcfax1.setBounds(10, 20, 120, 24);

        jLabel42.setFont(jLabel42.getFont().deriveFont((float)11));
        jLabel42.setText("Until");
        pnlrep4.add(jLabel42);
        jLabel42.setBounds(130, 10, 100, 10);

        dcfax2.setDateFormatString("dd/MM/yyyy");
        dcfax2.setFont(dcfax2.getFont().deriveFont((float)11));
        pnlrep4.add(dcfax2);
        dcfax2.setBounds(130, 20, 120, 24);

        cbstatusrepfax.setFont(cbstatusrepfax.getFont().deriveFont((float)11));
        pnlrep4.add(cbstatusrepfax);
        cbstatusrepfax.setBounds(360, 20, 100, 24);

        btnexportmail1.setFont(btnexportmail1.getFont().deriveFont(btnexportmail1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnexportmail1.setText("Export");
        btnexportmail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportmail1ActionPerformed(evt);
            }
        });
        pnlrep4.add(btnexportmail1);
        btnexportmail1.setBounds(10, 380, 90, 20);

        cbagenirepfax.setFont(cbagenirepfax.getFont().deriveFont((float)11));
        cbagenirepfax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "aan", "ramos", "john", "yusnita", "tri", "fitri", "mariana", "mitha", "dessy", "andrianto", "nurdin", "david", "yudho", "favel", "feronika", "oktaviani", "rudi" }));
        pnlrep4.add(cbagenirepfax);
        cbagenirepfax.setBounds(560, 20, 100, 24);

        jLabel64.setFont(jLabel64.getFont().deriveFont((float)11));
        jLabel64.setText("Direction");
        pnlrep4.add(jLabel64);
        jLabel64.setBounds(260, 10, 100, 10);

        cbdirfax.setFont(cbdirfax.getFont().deriveFont((float)11));
        cbdirfax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INBOUND", "OUTBOUND", "--" }));
        cbdirfax.setSelectedIndex(2);
        pnlrep4.add(cbdirfax);
        cbdirfax.setBounds(260, 20, 100, 24);

        lblrepfaxcount.setFont(lblrepfaxcount.getFont().deriveFont((float)11));
        lblrepfaxcount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pnlrep4.add(lblrepfaxcount);
        lblrepfaxcount.setBounds(880, 0, 40, 10);

        lblrepticcount9.setFont(lblrepticcount9.getFont().deriveFont((float)11));
        lblrepticcount9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrepticcount9.setText("list");
        pnlrep4.add(lblrepticcount9);
        lblrepticcount9.setBounds(920, 0, 40, 10);

        tabbpanereport.addTab("Fax", pnlrep4);

        jtab.addTab("Report", tabbpanereport);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(jTabbedPane2.getFont().deriveFont((float)10));
        jTabbedPane2.setOpaque(true);

        pninbox1.setBackground(new java.awt.Color(255, 255, 255));
        pninbox1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pninbox1.setLayout(null);

        tblmsin.setAutoCreateRowSorter(true);
        tblmsin.setFont(tblmsin.getFont().deriveFont((float)11));
        tblmsin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sender", "Read", "Replied", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmsin.setRowHeight(20);
        tblmsin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmsinMouseClicked(evt);
            }
        });
        jScrollPane33.setViewportView(tblmsin);

        pninbox1.add(jScrollPane33);
        jScrollPane33.setBounds(10, 40, 930, 180);

        dtmsi.setDateFormatString("dd/MM/yyyy");
        dtmsi.setFont(dtmsi.getFont().deriveFont((float)11));
        dtmsi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmsiPropertyChange(evt);
            }
        });
        pninbox1.add(dtmsi);
        dtmsi.setBounds(10, 20, 120, 24);

        dtmsi1.setDateFormatString("dd/MM/yyyy");
        dtmsi1.setFont(dtmsi1.getFont().deriveFont((float)11));
        dtmsi1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmsi1PropertyChange(evt);
            }
        });
        pninbox1.add(dtmsi1);
        dtmsi1.setBounds(130, 20, 120, 24);

        jLabel66.setFont(jLabel66.getFont().deriveFont((float)11));
        jLabel66.setText("From :");
        pninbox1.add(jLabel66);
        jLabel66.setBounds(10, 10, 100, 10);

        jLabel67.setFont(jLabel67.getFont().deriveFont((float)11));
        jLabel67.setText("Until :");
        pninbox1.add(jLabel67);
        jLabel67.setBounds(130, 10, 100, 10);

        jLabel70.setFont(jLabel70.getFont().deriveFont((float)11));
        jLabel70.setText("messages :");
        pninbox1.add(jLabel70);
        jLabel70.setBounds(10, 230, 100, 20);

        txtimsg3.setColumns(20);
        txtimsg3.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtimsg3.setLineWrap(true);
        txtimsg3.setRows(5);
        jScrollPane34.setViewportView(txtimsg3);

        pninbox1.add(jScrollPane34);
        jScrollPane34.setBounds(110, 230, 830, 76);

        btnreplymsg.setFont(btnreplymsg.getFont().deriveFont(btnreplymsg.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btnreplymsg.setText("Reply");
        btnreplymsg.setEnabled(false);
        btnreplymsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreplymsgActionPerformed(evt);
            }
        });
        pninbox1.add(btnreplymsg);
        btnreplymsg.setBounds(260, 20, 90, 24);

        btndelmsg.setFont(btndelmsg.getFont().deriveFont(btndelmsg.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btndelmsg.setText("Delete");
        btndelmsg.setEnabled(false);
        btndelmsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelmsgActionPerformed(evt);
            }
        });
        pninbox1.add(btndelmsg);
        btndelmsg.setBounds(360, 20, 90, 24);

        jTabbedPane2.addTab("InBox", pninbox1);

        pnoutbox1.setBackground(new java.awt.Color(255, 255, 255));
        pnoutbox1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnoutbox1.setLayout(null);

        tblmsou.setAutoCreateRowSorter(true);
        tblmsou.setFont(tblmsou.getFont().deriveFont((float)11));
        tblmsou.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sent Time", "Send by", "Recipient", "Messages"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblmsou.setRowHeight(20);
        tblmsou.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmsouMouseClicked(evt);
            }
        });
        jScrollPane35.setViewportView(tblmsou);

        pnoutbox1.add(jScrollPane35);
        jScrollPane35.setBounds(10, 40, 930, 180);

        jLabel71.setFont(jLabel71.getFont().deriveFont((float)11));
        jLabel71.setText("From :");
        pnoutbox1.add(jLabel71);
        jLabel71.setBounds(10, 10, 100, 10);

        dtmso.setDateFormatString("dd/MM/yyyy");
        dtmso.setFont(dtmso.getFont().deriveFont((float)11));
        dtmso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmsoPropertyChange(evt);
            }
        });
        pnoutbox1.add(dtmso);
        dtmso.setBounds(10, 20, 120, 24);

        jLabel72.setFont(jLabel72.getFont().deriveFont((float)11));
        jLabel72.setText("Until :");
        pnoutbox1.add(jLabel72);
        jLabel72.setBounds(130, 10, 100, 10);

        dtmso1.setDateFormatString("dd/MM/yyyy");
        dtmso1.setFont(dtmso1.getFont().deriveFont((float)11));
        dtmso1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtmso1PropertyChange(evt);
            }
        });
        pnoutbox1.add(dtmso1);
        dtmso1.setBounds(130, 20, 120, 24);

        jLabel74.setFont(jLabel74.getFont().deriveFont((float)11));
        jLabel74.setText("messages :");
        pnoutbox1.add(jLabel74);
        jLabel74.setBounds(10, 230, 100, 20);

        txtimsg4.setColumns(20);
        txtimsg4.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtimsg4.setLineWrap(true);
        txtimsg4.setRows(5);
        jScrollPane36.setViewportView(txtimsg4);

        pnoutbox1.add(jScrollPane36);
        jScrollPane36.setBounds(110, 230, 830, 90);

        btncomposemsg.setFont(btncomposemsg.getFont().deriveFont(btncomposemsg.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btncomposemsg.setText("Compose");
        btncomposemsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomposemsgActionPerformed(evt);
            }
        });
        pnoutbox1.add(btncomposemsg);
        btncomposemsg.setBounds(360, 20, 90, 24);

        btndelmsg1.setFont(btndelmsg1.getFont().deriveFont(btndelmsg1.getFont().getStyle() | java.awt.Font.BOLD, 11));
        btndelmsg1.setText("Delete");
        btndelmsg1.setEnabled(false);
        btndelmsg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelmsg1ActionPerformed(evt);
            }
        });
        pnoutbox1.add(btndelmsg1);
        btndelmsg1.setBounds(260, 20, 90, 24);

        jTabbedPane2.addTab("OutBox", pnoutbox1);

        jtab.addTab("Messaging", jTabbedPane2);

        pnlinf.setBackground(new java.awt.Color(255, 255, 255));
        pnlinf.setFont(pnlinf.getFont().deriveFont((float)10));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setLayout(null);

        jTextArea4.setColumns(20);
        jTextArea4.setEditable(false);
        jTextArea4.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea4.setRows(5);
        jTextArea4.setTabSize(5);
        jTextArea4.setText("1.\tASURANSI KENDARAAN BERMOTOR\n\t•\tAsuransi Kendaraan Bermotor \n\t•\tAswata Kencana \n\t•\tHeavy Equipment\n2.\tASURANSI PROPERTY\n\t•\tAsuransi Kebakaran \n\t•\tProperty All Risk/Industrial All Risk\n\t•\tAswata Kirana \n3.\tASURANSI PENGANGKUTAN (MARINE CARGO) \n4.\tASURANSI PERSONAL ACCIDENT \n5.\tASURANSI MONEY & BOND \n\t•\tAsuransi uang\n\t•\tCustom Bond\n\t•\tSurety Bond\n6.\tASURANSI RANGKA KAPAL / PESAWAT\n\t•\tMarine Hull (Rangka Kapal)\n\t•\tMarine Builder Risk\n\t•\tAviation Hull\n\t•\tLoss of License\n\t•\tAviation PA\n\n7.\tASURANSI REKAYASA / ENGINEERING\n\t•\tContractor’s All Risk (CAR)\n\t•\tErection All Risk (EAR)\n\t•\tMachinery Breakdown (MB)\n\t•\tElectronic Equipment Insurance (EEI)\n\n8.\tASURANSI MIGAS\n9.\tASURANSI LIABILITY\n\t•\tPublic Liability\n\t•\tProduct Liability\n\t•\tComprehensive General Liability (CGL) \n\t•\tMarine Liability\n\t•\tD&O (Directors and Officers) Liability\n\n10.\tASURANSI GENERAL ACCIDENT\n\t•\tGlass Insurance\n\t•\tBurglary\n\t•\tHole in one\n\t•\tFidelity\n\t•\tMoveable\n\t•\tBillboard Insurance\n\t•\tSatelite \n");
        jTextArea4.setBorder(null);
        jTextArea4.setOpaque(false);
        jScrollPane22.setViewportView(jTextArea4);

        jPanel6.add(jScrollPane22);
        jScrollPane22.setBounds(0, 0, 970, 410);

        pnlinf.addTab("PRODUK", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setLayout(null);

        jTextArea5.setColumns(20);
        jTextArea5.setEditable(false);
        jTextArea5.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea5.setLineWrap(true);
        jTextArea5.setRows(5);
        jTextArea5.setTabSize(5);
        jTextArea5.setText("PT. ASURANSI WAHANA TATA\n\n\nPT. Asuransi Wahana Tata merupakan nama baru dari PT. Maskapai Asuransi Madijo yang didirikan di Surabaya pada tanggal 25 Juli 1964. Perubahan nama perusahaan dan pengambilalihan kepemilikan pada tanggal 1 Mei 1975 ini menjadi awal dimulainya operasional perusahaan yang berkantor  pusat di Jakarta dengan 35 karyawan dan modal disetor Rp 100 Juta saat itu.\n\nSebagai salah satu perusahaan swasta nasional terkemuka yang bergerak di bidang jasa asuransi umum, PT. Asuransi Wahana Tata saat ini sudah memiliki modal disetor Rp 100 Milyar dan lebih dari 50 jaringan kantor yang tersebar hampir di seluruh wilayah Indonesia dengan lebih dari 1000 karyawan berdedikasi dan profesional.\n\nPerusahaan memiliki kapasitas untuk penutupan asuransi properti, asuransi kendaraan bermotor, asuransi pengangkutan, asuransi rangka kapal dan pesawat terbang, asuransi rekayasa, asuransi minyak & gas, asuransi uang, asuransi tanggung gugat, asuransi penjaminan, dan asuransi kecelakaan diri.\n\nDalam operasionalnya, perusahaan juga membangun hubungan kerja sama yang berkesinambungan dan saling menguntungkan dengan semua mitra usahanya, seperti : broker asuransi/reasuransi, reasuransi terkemuka, baik dalam dan luar negeri; professional loss adjuster; independent surveyor; agen-agen asuransi dan bengkel-bengkel mobil terbaik.\n\nSejak tahun 2005, perusahaan sudah mengembangkan sistem teknologi informasi terintegrasi LINTASWATA yang mampu melayani seluruh proses bisnis perusahaan di semua jaringan kantor secara on-line. Dengan LINTASWATA ini, perusahaan dapat semakin meningkatkan kemudahan, kecepatan dan keakuratan pelayanan kepada seluruh pemegang polisnya.\n\nPada tahun 2011 perusahaan meraih penghargaan \"Corporate Image Award 2011-Indonesia's Most Admired Companies (IMAC)\" dengan predikat : \"Excellence in Building and Managing Corporate Image\" untuk kategori \"Non-Vehicle Insurance\" dari Frontier Consulting Group yang bekerja sama dengan Bloomberg Businessweek.\nPenghargaan lainnya adalah dengan \"Predikat Sangat Bagus atas Kinerja Keuangan Selama Tahun 2010\" dalam Insurance Award 2011\" yang diperoleh dari Majalah Infobank. Perolehan penghargaan ini semakin memantapkan perusahaan sebagai market leader di industri asuransi umum di Indonesia.\n\nAlamat Kantor :\nKantor Pusat :\n\nGedung Asuransi Wahana Tata\nJl. HR Rasuna Said Kav C-4,\nJakarta 12920\n\nTelp\t: 520 3145, 520 3146\nFax\t: 520 3149, 520 5223\nEmail\t: aswata@aswata.co.id \n\nKantor Pemasaran : ada di file terpisah\n\n\nVISI :\n\nMENJADI 5 BESAR PERUSAHAAN   ASURANSI UMUM    DI INDONESIA YANG DIKENAL DI TINGKAT REGIONAL, MENJADI   PILIHAN UTAMA PELANGGAN DAN MITRA USAHA SERTA KEBANGGAAN KARYAWAN\n\nMISI :\n\nBERUSAHA   DI BIDANG ASURANSI   UMUM  DENGAN MEMBERIKAN PELAYANAN   YANG   TERBAIK,  BEREPUTASI, INOVATIF DAN TERPERCAYA\n\nNILAI - NILAI BUDAYA DASAR\n\n 1.  INTEGRITAS\n  \tMENCAKUP NILAI DISIPLIN, TANGGUNG JAWAB, BISA DIPERCAYA, KEJUJURAN DAN TULUS\n 2.  PROFESIONALISME \n\tYANG MENCAKUP ILMU PENGETAHUAN, KEAHLIAN DAN KOPENTENSI DIBIDANG ASURANSI, DAN SIKAP PROFESIONAL DALAM MENJALANKAN BISNIS ASURANSI\n 3.  TEAMWORK\n      \tYANG MENCAKUP : PAHAM AKAN TUJUAN YANG INGIN DICAPAI, TAHU DAN MAMPU   MENGERJAKAN TUGAS POKOK DALAM KOORDINASI DENGAN ORANG / BAGIAN LAIN, \t\t\tSALING MEMBANTU MENUJU TERCAPAINYA TUJUAN BERSAMA YANG SUDAH DITENTUKAN\n 4.  SELALU TERBAIK \n     \tYANG MENCAKUP BERUSAHA UNTUK PELAYANAN DAN HASIL YANG  TERBAIK  TIDAK PUAS DENGAN HASIL YANG BIASA ATAU STANDAR, NAMUN BERUSAHA UNTUK \t\t\t\tMENDAPATKAN HASIL YANG LEBIH BAIK.\n 5.  KREATIVITAS\n     \tSELALU MENCIPTAKAN DAN MENEMUKAN CARA BARU UNTUK MENGHASILKAN PRODUK DAN LAYANAN TERBAIK BAGI NASABAH INTERNAL DAN EXTERNAL\n\t\n\nSERVICE CAMPAIGN \n\nWE CARE adalah \"Service Campaign\" yang akan memandu perilaku para front liners dalam menyampaikan layanan Asuransi Wahana Tata kepada nasabahnya. \n \nWELCOME \tSelalu memberikan sapaan hangat di awal pertemuan dengan nasabah untuk menciptakan kesan profesional dan ramah \nEMPATHY \tBerusaha memahami kebutuhan dan harapan nasabah dengan semangat memberikan service terbaik \nCREATIVE \tBerusaha terus mencarikan manfaat dan nilai tambah bagi nasabah \nACCURATE \tBekerja, memberikan solusi dan mengambil keputusan yang tepat untuk mencapai kepuasan nasabah dan kesuksesan bisnis \nRESPONSIVE \tMenyegerakan segala urusan dan merespon dengan baik semua nasabah dan kebutuhannya. \nEXCELLENCE \tBerusaha sebaik mungkin selalu meningkatkan diri untuk menuju kepuasan dan loyalitas nasabah, juga antusiasme mereka merekomendasikan Asuransi \t\t\tWahana Tata \n\n\nPEMEGANG SAHAM\n-  PT. Pakarti Yoga\n-  Tn. Rudy Wanandi\n-  Ahli Waris Ny. S. Hartini \n-  PT. Trimulia Sarana Pratama \n-  Koperasi-Koperasi\t \n\n\nKOMISARIS\n\nKomisaris Utama\t\t: \tRudy Wanandi\nWakil Komisaris Utama\t:\tSofjan Wanandi\nKomisaris\t\t:\tA.R. Ramly \n\t\t\t\tJusuf Wanandi\n\t\t\t\tSiti Sudjaliah Soegiarso \n\t\t\t\tPingki Elka Pangestu   \n\nDIREKSI\nDirektur Utama\t\t:\tChristian W. Wanandi\nDirektur\t\t\t:\tEddy Chandra\n\t\t\t\tTri Wahono\n\t\t \t\tM. Th. Ratnawati\n\t\t\t\tS Y. Gana Adhitya T\n\nDAFTAR DEPARTEMEN & BAGIAN \nNON DEPARTEMEN\no\tMarketing (Corporate, Retail & Mkt Support)\no\tBroker Specialist Risk\no\tOil & Gas\no\tUnderwriting, Reinsurance, Claim & Survey\no\tAccounting\no\tFinance\no\tInternal Audit\no\tGeneral Affair\no\tHuman Resources  Dev.\no\tInformation Technology \no\tLegal\no\tCorporate Secretary\n\nDAFTAR BANK REKANAN  (terlampir)\nDAFTAR LEASING REKANAN (terlampir)\n");
        jTextArea5.setWrapStyleWord(true);
        jTextArea5.setBorder(null);
        jTextArea5.setOpaque(false);
        jScrollPane23.setViewportView(jTextArea5);

        jPanel7.add(jScrollPane23);
        jScrollPane23.setBounds(0, 0, 970, 410);

        pnlinf.addTab("PROFIL PERUSAHAAN", jPanel7);

        jTabbedPane5.setFont(new java.awt.Font("Calibri", 0, 11));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel24.setLayout(null);

        jTextArea12.setColumns(20);
        jTextArea12.setEditable(false);
        jTextArea12.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea12.setLineWrap(true);
        jTextArea12.setRows(5);
        jTextArea12.setTabSize(5);
        jTextArea12.setText("Dalam hal terjadi kebakaran/kerusakan, Tertanggung wajib :\n\n\t1.\tMemberitahukan secara tertulis kepada Penanggung dalam waktu 5 hari sejak kejadian mengenai :\n\t\t-\tTempat, tanggal dan jam terjadinya kebakaran / kerusakan \n\t\t-\tSebab-sebab kebakaran / kerusakan \n\t\t-\tBesarnya kerugian menurut taksiran Tertanggung (terbakar, rusak)\n\t\t-\tInformasi lain yang menurut Tertanggung perlu diketahui oleh Penanggung \n\n\t2.\tMenyerahkan dokumen pendukung klaim yaitu :\n\t\t-\tPolis dan semua endorsemen yang ada \n\t\t-\tFormulir Laporan Kerugian yang telah diisi dan ditandatangani Tertanggung \n\t\t-\tSurat tuntutan ganti rugi (klaim) disertai perincian kerugian \n\t\t-\tsurat keterangan dari kepolisian yg menyatakan sebab terjadinya kebakaran \n\t\t-\tGambar konstruksi / layout / struktur bangunan \n\t\t-\tsurat keterangan atau bukti-bukti lainnya yang diminta oleh Penanggung \n\n\t3.\tSetelah laporan diterima oleh Penanggung, akan dilakukan survei ke lokasi kejadian oleh Penanggung \n\n\t4.\tBerdasarkan laporan hasil survei dan kelengkapan dokumen yang disampaikan oleh Tertanggung, Penanggung akan memberikan keputusan mengenai :\n\t\t-\tapakah klaim dapat diganti karena termasuk dalam risiko yang dijamin polis \n\t\t-\tJika klaim terjamin dalam polis : jumlah penggantian yang akan diberikan oleh Penanggung kepada Tertanggung \n\n\t5.\tApabila Tertanggung sudah menyetujui jumlah penggantian yang ditawarkan oleh Penanggung, maka pembayaran ganti rugi akan dilakukan dalam waktu \t\t\t\t30 hari kalender sejak kesepakatan tertulis antara Penanggung dan Tertanggung dibuat.\n");
        jTextArea12.setWrapStyleWord(true);
        jTextArea12.setBorder(null);
        jTextArea12.setOpaque(false);
        jScrollPane46.setViewportView(jTextArea12);

        jPanel24.add(jScrollPane46);
        jScrollPane46.setBounds(0, 0, 970, 380);

        jTabbedPane5.addTab("PROSEDUR KLAIM ASURANSI KEBAKARAN", jPanel24);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel27.setLayout(null);

        jTextArea13.setColumns(20);
        jTextArea13.setEditable(false);
        jTextArea13.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea13.setLineWrap(true);
        jTextArea13.setRows(5);
        jTextArea13.setTabSize(5);
        jTextArea13.setText("1.\tTertanggung wajib segera melaporkan kepada Penanggung baik secara lisan maupun tulisan, selambat-lambatnya 3 x 24 jam sejak terjadinya kecelakaan \t\t\t\tyang kemudian disusulkan dengan laporan tertulis \n2.\tPenanggung akan melakukan survey ke tempat lokasi kecelakaan dan menemui tertanggung (yang mengalami kecelakaan) untuk mengetahui detail kejadian, \t\t\tpenyebab, serta cidera akibat kecelakaan.\n3.\tSetelah tertanggung melengkapi seluruh dokumen yang diperlukan maka Penanggung akan melakukan proses analisa klaim berdasarkan informasi dan dokumen \t\t\tpendukung yang ada.\n4.\tPenanggung akan menyampaikan hasil analisa dan perhitungan klaim kepada tertanggung jika klaim terjamin dalam polis.\n5.\tPenanggung akan melakukan pembayaran klaim kepada tertanggung selambat-lambatnya dalam waktu 30 hari setelah adanya kesepakatan antara tertanggung dan \tpenanggung mengenai besarnya ganti rugi. \n\n\tDokumen pendukung klaim Asuransi Kecelakaan Diri :\n\t1.\tSurat tuntutan klaim dari tertanggung kepada penanggung beserta rincian jumlah tuntutan klaimnya \n\t2.\tForm laporan kerugian yang sudah diisi oleh tertanggung \n\t3.\tKronologis kejadian diperkuat oleh keterangan saksi ditempat kejadian \n\t4.\tSurat tuntutan ganti rugi dari tertanggung kepada pihak ke-3 (jika kecelakaan disebabkan oleh pihak ke-3)\n\t5.\tManifest (surat keterangan penumpang/tiket) untuk kecelakaan bagi penumpang angkutan umum \n\t6.\tSurat keterangan kecelakaan dari pemilik/pengelola bangunan atau lokasi umum bila kecelakaan terjadi di bangunan, lokasi atau fasilitas umum, \t\t\t\tmisalnya mall, taman hiburan.\n\t7.\tFotocopy bukti identitas tertanggung  \n\t8.\tBukti laporan kecelakaan dari pihak kepolisian (bila kecelakaan terjadi di jalan raya)\n\t9.\tSurat keterangan pemeriksaan dokter \n\t10.\tKwitansi biaya pengobatan dari Rumah sakit atau dokter (asli)\n\t11.\tBila meninggal dunia :\n\t\ta.\tSurat keterangan kematian dari kelurahan \n\t\tb.\tFotocopy kartu keluarga \n\t\tc.\tSurat keterangan kematian dari rumah sakit \n\t\td.\tVisum et repertum dari Rumah Sakit  \n");
        jTextArea13.setWrapStyleWord(true);
        jTextArea13.setBorder(null);
        jTextArea13.setOpaque(false);
        jScrollPane48.setViewportView(jTextArea13);

        jPanel27.add(jScrollPane48);
        jScrollPane48.setBounds(0, 0, 970, 390);

        jTabbedPane5.addTab("PROSEDUR KLAIM KECELAKAAN DIRI", jPanel27);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel28.setLayout(null);

        jTextArea14.setColumns(20);
        jTextArea14.setEditable(false);
        jTextArea14.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea14.setLineWrap(true);
        jTextArea14.setRows(5);
        jTextArea14.setTabSize(5);
        jTextArea14.setText("1.\tTertanggung wajib segera melaporkan kepada Penanggung baik secara lisan maupun tulisan, setiap kejadian klaim yang kemudian disusulkan secara tertulis.\n2.\tPenanggung akan melakukan survey ke tempat lokasi kejadian klaim untuk mengetahui detail kerugian dan penyebabnya.\n3.\tPenanggung akan menunjuk Loss Adjuster dalam memproses / investigasi serta adjustment klaim.\n4.\tSetelah tertanggung melengkapi seluruh dokumen yang diperlukan maka Penanggung akan melakukan proses analisa klaim berdasarkan informasi dan dokumen \t\t\tpendukung yang ada.\n5.\tPenanggung akan menyampaikan hasil analisa dan perhitungan klaim kepada tertanggung jika klaim terjamin dalam polis.\n6.\tPenanggung akan melakukan pembayaran klaim kepada tertanggung selambat-lambatnya dalam waktu 30 hari setelah adanya kesepakatan antara tertanggung dan \tpenanggung mengenai besarnya ganti rugi. \n\nDokumen pendukung klaim Asuransi Marine Cargo:\n\t1.\tSurat tuntutan ke pihak asuransi yang menyebutkan sebab kerugian beserta perincian jumlah kerugian barang berikut nilainya.\n\t2.\tPolis asli \n\t3.\tOriginal invoice; B/L; Packing List\n\t4.\tLaporan survey saat pemuatan barang ke container dari independent surveyor di gudang supplier untuk memastikan kondisi barang prior to shipment.\n\t5.\tBerita acara serah terima dari supplier ke ekspedisi \n\t6.\tPort Clearance (jika ada)\n\t7.\tSP2 / EIR\n\t8.\tStowage plan\n\t9.\tSurat laporan kepolisian (jika terjadi kecelakaan lalu lintas/kehilangan) \n\t10.\tMaster note of Protest atau LKK (jika terjadi kejadian selama proses pelayaran)\n\t11.\tCertificate of quality and or quantity\n\t12.\tInformasi kedatangan kapal di pelabuhan bongkar / DO dari agent pelayaran \n\t13.\tBerita acara kerusakan di gudang pelabuhan (jika terjadi kerusakan di pelabuhan)\n\t14.\tSurat tuntutan klaim dari tertanggung ke pihak terkait (supplier, ekspedisi, EMKL/forwarder) beserta tanggapannya \n\t15.\tMoU antara tertanggung dengan pihak terkait (ekspedisi / forwarder)\n\t16.\tBerita acara kerusakan dari QC tertanggung yang menyatakan jumlah rusak serta tipenya \n\t17.\tHasil pengecekan laboratorium atas cargo yang mengalami kebasahan apakah karena air tawar atau air laut \n\t18.\tInformasi tertanggung apakah atas object pertanggungan yang rusak tersebut, tertanggung bersedia membeli kembali atas salvage tersebut.\n\t19.\tDokumen pendukung klaim lainnya yang berkaitan dengan kejadian. \n\t");
        jTextArea14.setWrapStyleWord(true);
        jTextArea14.setBorder(null);
        jTextArea14.setOpaque(false);
        jScrollPane49.setViewportView(jTextArea14);

        jPanel28.add(jScrollPane49);
        jScrollPane49.setBounds(0, 0, 970, 390);

        jTabbedPane5.addTab("PROSEDUR KLAIM ASURANSI MARINE CARGO", jPanel28);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel29.setLayout(null);

        jTextArea15.setColumns(20);
        jTextArea15.setEditable(false);
        jTextArea15.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea15.setLineWrap(true);
        jTextArea15.setRows(5);
        jTextArea15.setTabSize(5);
        jTextArea15.setText("1.\tTertanggung harus melaporkan klaimnya kepada Call Center / kantor cabang / Jakarta Claim Center Aswata dalam waktu selambat-lambatnya 5 x 24 jam sejak \t\t\tterjadinya peristiwa kecelakaan/kehilangan.\n2.\tTertanggung harus menyiapkan dokumen-dokumen persyaratan klaim yang diminta (lihat Daftar Dokumen Klaim)\n3.\tPetugas dari Asuransi Wahana Tata akan melakukan survey untuk melihat kerusakan yang ada.\n4.\tAsuransi Wahana Tata akan memberikan ganti rugi. Untuk partial loss, Tertanggung dapat membawa kendaraannya ke bengkel rekanan yang dipilih untuk \t\t\tdiperbaiki. Bengkel akan memberikan estimasi waktu perbaikan dan Tertanggung dapat mengecek ke bengkel apakah kendaraannya telah selesai diperbaiki dan \t\t\tdapat diambil kembali.\n5.\tPada saat pengambilan kendaraan, Tertanggung harus membayar risiko sendiri sesuai dengan nilai yang tercantum di polis.\n\nKERUSAKAN SEBAGIAN (PARTIAL LOSS)\nDokumen yang harus disampaikan pada saat klaim :\n\t•\tPolis Asuransi \n\t•\tCopy SIM Pengemudi \n\t•\tCopy Identitas Tertanggung (KTP/SIM)\n\t•\tSTNK Kendaraan \n\t•\tLaporan polisi jika diperlukan \n\t•\tMengisi Formulir Laporan Kerugian \n\nKERUSAKAN TOTAL (TOTAL LOSS)\nDokumen yang harus disampaikan pada saat klaim :\n\t•\tPolis Asuransi \n\t•\tFotocopy KTP Pemilik sesuai BPKB (bila pemilik adalah perorangan)\n\t•\tFotocopy SIUP-NPWP pemilik sesuai BPKB (bila pemilik adalah perusahaan atau instansi)\n\t•\tSurat Pelepasan Asset dari perusahaan pemilik sesuai BPKB (bila pemilik adalah perusahaan atau instansi)\n\t•\tKuitansi kosong bermaterai ditandatangani oleh pemilik kend. sesuai BPKB\n\t•\tBPKB asli \n\t•\tCopy asli faktur Pembelian kend. dari ATPM (bukan dealer)\n\t•\tSTNK asli (bila tidak hilang)\n\t•\tKunci kontak asli dan cadangan (bila tidak hilang)\n\t•\tSurat Keterangan Polisi\n\nKLAIM KEHILANGAN KENDARAAN\nDokumen yang harus disampaikan pada saat klaim :\n\t•\tAsli Polis Asuransi \n\t•\tAsli Form Laporan Kerugian PT. ASWATA \n\t•\tAsli Kronologis Kejadian yang dibuat oleh Tertanggung, dan atau pihak yang pertama kali mengetahui kejadian kehilangan tersebut disertai materai Rp. \t\t\t\t6.000,- (pada lembaran terpisah dengan LK) \n\t•\tFotocopy KTP Pemilik sesuai BPKB (bila pemilik adalah perorangan)\n\t•\tFotocopy SIUP-NPWP pemilik sesuai BPKB (bila pemilik adalah perusahaan atau instansi)\n\t•\tSurat Pelepasan Asset dari perusahaan pemilik sesuai BPKB (bila pemilik adalah perusahaan atau instansi)\n\t•\tKuitansi kosong bermaterai ditandatangan oleh nama pemilik kend. sesuai BPKB\n\t•\tBPKB asli \n\t•\tCopy asli faktur Pembelian Kend. dari ATPM \n\t•\tSTNK asli (bila tidak hilang)\n\t•\tKunci kontak asli dan cadangan (bila tidak hilang)\n\t•\tSurat Lapor Polsek / Surat Tanda Penerimaan Laporan (STPL)\n\t•\tSurat Keterangan (SKET) Hilang Kaditserse (tingkat POLDA) \n\t•\tSurat Pemblokiran Polda (asli)\n\nKLAIM TANGGUNG JAWAB HUKUM PIHAK KETIGA\nDokumen yang harus disampaikan pada saat klaim :\n\t•\tFoto copy Identitas tertanggung (KTP/SIM)\n\t•\tFoto copy SIM pengemudi \n\t•\tIdentitas pihak ketiga (KTP / SIM)\n\t•\tSIM & STNK pihak ketiga bila kerugian pihak ketiga melibatkan kendaraan bermotor.\n\t•\tSurat tuntutan dari pihak ketiga  dan surat pernyataan damai yang dilengkapi dengan meterai \n\t•\tLaporan Polisi bila dibutuhkan \n\t•\tBukti Pembayaran (Kuitansi) asli Biaya pengobatan dari Rumah Sakit \n\t•\tBila TJH III meninggal dunia : \n\t\ta.\tSurat Keterangan Kematian asli dari Rumah Sakit (bila Pihak ke III meninggal dunia) atau dari Kelurahan. \n\t\tb.\tFoto copy  KTP ahli waris dan Foto Copy  Kartu keluarga \n\t\tc.\tSurat Pernyataan bahwa Pihak ke III tidak menuntut kepada PT. Jasa Rahardja (bila diperlukan) \n\t\td.\tApabila Pihak Ke III melakukan tuntutan kepada PT. Jasa Rahardja maka wajib dilampirkan kuitansi penggantian dari PT. Jasa Rahadja \n\t\t\nKLAIM JIKA TIDAK MENGGUNAKAN BENGKEL REKANAN\nPersyaratan :\n\t•\tMengisi formulir SURAT PERNYATAAN PENGGUNAAN BENGKEL NON REKANAN dan FORMULIR PERMOHONAN PEMBAYARAN KLAIM REIMBURSEMENT  (lihat \t\t\t\tterlampir) \n\t•\tTertanggung harus membayar terlebih dahulu biaya perbaikan ke Bengkel Non Rekanan dan selanjutnya ditagih ke PT Asuransi Wahana Tata dengan \t\t\t\tmelampirkan kuitansi biaya perbaikan dan menyerahkan spare parts yang diganti oleh Bengkel Non Rekanan. \n\t•\tApabila terdapat selisih biaya perbaikan antara Bengkel Non Rekanan dengan Bengkel Rekanan Aswata, maka selisih biaya tersebut menjadi tanggung \t\t\t\tjawab tertanggung \n\t•\tPT Asuransi Wahana Tata tidak bertanggung jawab terhadap hasil perbaikan yang dilakukan oleh Bengkel Non Rekanan. \n\n \nPENERIMAAN LAPORAN KLAIM\nInformasi yang harus diperhatikan pada saat penerimaan laporan klaim :\n\t1.\t Siapa yang melaporkan klaim \n\t\ta.\tNama pelapor \n\t\tb.\tHubungan dengan pemegang polis\n\t\tc.\tNomor telepon yang dapat dihubungi \n \n\t2.\tCocokkan data pelapor dengan polis yang dimiliki. Tanya : \n\t\ta.\tNomor Polis, jika nomor polis tidak diketahui, cocokkan data dengan point b dan c\n\t\tb.\tJenis pertanggungan \n\t\tc.\tData Pemegang Polis (Nama, alamat, nomor telepon)\n\t\td.\tObjek Pertanggungan (asuransi kebakaran / kendaraan bermotor)\n\t\t\t-\tasuransi kendaraan : cek nomor polisi, jenis/merk/warna kendaraan, no chasis, no rangka.\n\t\t\t-\tasuransi kebakaran : cek lokasi pertanggungan (alamat), objek (rumah tinggal/ruko) \n\n\t3.\tTanya kerugian yang terjadi \n\t\tUntuk asuransi kendaraan bermotor :\n\t\ta.\tSiapa pengemudi saat kejadian, minta no SIM\n\t\tb.\tTanggal dan jam terjadinya kerugian \n\t\tc.\tTempat terjadinya kerugian \n\t\td.\tApakah ada pihak lain yang menuntut ganti rugi kepada tertanggung \n\t\te.\tJenis kerusakan / kerugian \n\n\t\tUntuk asuransi kebakaran :\n\t\ta.\tTanggal dan jam terjadinya kerugian \n\t\tb.\tDugaan sebab terjadinya kerugian \n\t\tc.\tBentuk dan jumlah kerugian \n\n\t4.\tTanya dimana lokasi Tertanggung dan lokasi kendaraan saat ini\n\t\tPada saat penerimaan Laporan Klaim, tanyakan lokasi Tertanggung untuk menginformasikan :\n\t\t-\tLokasi kantor cabang/perwakilan Asuransi Wahana Tata yang terdekat dengan lokasi tertanggung, untuk pengisian formulir laporan klaim \n\t\t-\tBengkel rekanan yang terdekat dengan lokasi Tertanggung.\n\t5.\tInformasikan ke Tertanggung bahwa setelah melaporkan klaimnya ke Call Center Asuransi Wahana Tata, Tertanggung harus datang ke Cabang terdekat \t\t\t\tuntuk mengisi formulir klaim dan membawa kelengkapan dokumen klaim selambat-lambatnya dalam waktu 1 x 24 jam, agar klaimnya dapat diproses \t\t\t\tlebih lanjut.\n\nHARAP DIPERHATIKAN\n•\tPetugas call center tidak diperbolehkan menunjuk salah satu bengkel rekanan Aswata untuk perbaikan kendaraan Tertanggung. Petugas call center hanya boleh menginformasikan daftar bengkel rekanan yang lokasinya berada dekat dengan lokasi Tertanggung, dan Tertanggung yang harus memilih bengkel rekanan yang akan didatanginya \n");
        jTextArea15.setWrapStyleWord(true);
        jTextArea15.setBorder(null);
        jTextArea15.setOpaque(false);
        jScrollPane50.setViewportView(jTextArea15);

        jPanel29.add(jScrollPane50);
        jScrollPane50.setBounds(0, 0, 970, 390);

        jTabbedPane5.addTab("PROSEDUR KLAIM ASURANSI KENDARAAN BERMOTOR", jPanel29);

        pnlinf.addTab("PROCEDUR CLAIM", jTabbedPane5);

        jPanel23.setLayout(null);

        jTextArea11.setColumns(20);
        jTextArea11.setEditable(false);
        jTextArea11.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea11.setLineWrap(true);
        jTextArea11.setRows(5);
        jTextArea11.setTabSize(5);
        jTextArea11.setText("PROSEDUR PENUTUPAN POLIS BARU\n1.\tCalon nasabah dihubungi oleh Marketing Asuransi Wahana Tata atau datang ke kantor cabang Aswata.\n2.\tIsi formulir permintaan penutupan polis\n3.\tJika diperlukan akan disurvei oleh tim survei Aswata \n4.\tBerdasarkan laporan survey, dibuatkan penawaran dan dikirim ke calon nasabah. Jika tidak ada survey, langsung dibuatkan penawaran \n5.\tJika nasabah setuju, polis akan diterbitkan dan dikirim ke nasabah \n6.\tJika nasabah tidak setuju, proposal dapat direvisi (negosiasi syarat & ketentuan polis, tarif premi, dll)\n7.\tSetelah polis diterima, nasabah membayar premi ke Aswata \n8.\tSetelah premi diterima, Aswata kirim kwitansi premi ke nasabah \n9.\tPenutupan polis selesai \n\n\n\nCARA PERHITUNGAN PREMI \n\nToyota Avanza 1.5 S tahun 2010.\nHarga Pertanggungan : Rp.151.000.000,- (masuk kategori 151-300 juta rupiah)\nPaket : Aswata Kencana - Komprehensif, Paket Kencana \nTarif premi : 2.35%\nPerhitungan Premi \t\t\t\nPremi asuransi      Rp.151.000.000,- x 2.35%\t\t= Rp.3.548.500,-\nBiaya polis dan materai\t\t= Rp.     35,000,- + \nTotal premi yang harus dibayar\t\t= Rp.3,583,500,-\n\n?\nDOKUMEN YANG DIPERLUKAN\n\nASURANSI KENDARAAN BERMOTOR\no\t Foto copy KTP Pemohon \no\t Foto copy STNK Kendaraan \no\tFormulir Surat Permintaan Pertanggungan Kendaraan Bermotor (SPPKB) yang sudah diisi dengan lengkap dan ditandatangani Pemohon \n \nASURANSI KEBAKARAN\no\t Formulir Surat Permintaan Pertanggungan Kebakaran (SPPK) - Non Industri yang sudah diisi dengan lengkap dan ditandatangani Pemohon \n\nASURANSI KECELAKAAN DIRI\no\t Formulir Surat Permintaan Pertanggungan Kecelakaan Diri yang sudah diisi dengan lengkap dan ditandatangani Pemohon \no\t Foto copy KTP Pemohon \n\nASURANSI MARINE CARGO\no\t Formulir Surat Permintaan Pertanggungan Marine Cargo yang sudah diisi dengan lengkap dan ditandatangani Pemohon \no\t Melampirkan dokumen :\n-\tBL(Konosemen) / AWB / Delivery Order\n-\tLC / PO / Invoice\n?\nMETODE PEMBAYARAN PREMI\no\tCOLLECTOR \nPetugas collector PT.Asuransi Wahana Tata mendatangi ke tempat nasabah untuk menerima pembayaran premi.\no\tSETOR TUNAI KE COUNTER BANK\nNasabah menyetor tunai ke Bank untuk pembayaran premi ke counter bank dengan menunjuk rekening PT.Asuransi Wahana Tata seperti yang tertera di nota tagihan polis asuransi\no\tTRANSFER BANK\nNasabah melakukan transfer bank dengan menunjuk  rekening PT.Asuransi Wahana Tata seperti yang tertera di nota tagihan polis asuransi.\no\tATM / INTERNET BANKING\nNasabah melakukan pembayaran melalui mesin ATM dan Internet Banking dengan menunjuk  rekening PT.Asuransi Wahana Tata seperti yang tertera di nota tagihan polis asuransi.\no\tCREDIT CARD : VISA, MASTER, BCA CARD \nNasabah melakukan pembayaran Kartu Kredit dengan memberikan informasi melalui telepon atau mengisi formulir pembayaran kartu kredit di kantor PT.Asuransi Wahana.\no\tVIRTUAL ACCOUNT \nNasabah melakukan pembayaran melalui fasilitas bank seperti : Setor tunai di counter, ATM, Internet Banking, Mobile Banking, dll. dengan menunjuk Nomor Virtual Account seperti yang tertera di nota tagihan polis asuransi \n\nPembayaran premi dengan Virtual Account dapat dilakukan melalui :\n1.\t Setoran Tunai ke Teller BCA\n2.\t ATM BCA\n3.\t E-Banking BCA (Internet Banking)\n4.\t Jaringan ATM Prima\n5.\tLalu Lintas Giro (LLG), mekanisme transfer antar bank dengan menggunakan fasilitas kliring \n6.\tReal Time Gross Settlement (RTGS), mekanisme transfer antar bank secara real time, dana langsung masuk ke rekening bank penerima \n\nPEMBAYARAN MELALUI VIRTUAL ACCOUNT BCA\nVIA ATM BCA \nProsedur Transfer Pembayaran Premi dengan BCA Virtual Account melalui ATM BCA :\n1.\tMasukkan kartu ATM dan PIN anda \n2.\tPilih menu \"TRANSAKSI LAINNYA\"\n3.\tPilih menu \"TRANSFER\"\n4.\tPilih menu \"KE REK BCA VIRTUAL ACCOUNT\"\n5.\tMasukkan NOMOR VIRTUAL ACCOUNT ANDA : 00029 diikuti dengan 16 digit nomor Debit Note Anda yang tercantum pada Polis. Jika nomor sudah sesuai, pilih \"BENAR\" \n6. \tPada layar berikutnya akan menampilkan \n\"\tNOMOR VIRTUAL ACCOUNT Anda, \n\"\tNAMA Anda \n\"\tPERUSAHAAN / PRODUK\n\"\tTOTAL TAGIHAN\nAnda diminta untuk memasukkan jumlah yang ingin dibayar. Jika sudah sesuai, klik \"BENAR\"\n7. \tLayar berikutnya akan menampilkan informasi pada point no.6 diatas beserta jumlah yang ingin dibayar. Jika sudah sesuai pilih \"YA\"\n8. \tATM akan meminta untuk memasukkan PIN kembali \n9. \tSetelah transfer berhasil, ATM akan mengeluarkan struk \n10.\tProses pembayaran selesai.\n\nVIA ATM PRIMA\nProsedur Transfer Pembayaran Premi dengan BCA Virtual Account melalui ATM Prima :\n1.\tMasukkan kartu ATM dan PIN anda \n2.\tPilih menu \"TRANSAKSI LAINNYA\"\n3.\tPilih menu \"TRANSFER\"\n4.\tPilih menu \"TRANSFER KE BANK LAIN\"\n5.\tMasukkan Kode Bank BCA (014) dan kode Prefix (00029) diikuti dengan 16 digit nomor Debit Note Anda yang tercantum pada Polis. Jika nomor sudah sesuai, pilih \"BENAR\" \n6.\tMasukkan jumlah premi yang ingin dibayarkan.\n7.\tSelanjutnya akan muncul konfirmasi Nama Pemilik Virtual Account (PT. Asuransi Wahana Tata) dan jumlah transaksi \n8. \tJika sudah sesuai, pilih \"YA\", dan transaksi akan segera diproses \n9. \tJika transaksi berhasil, struk akan muncul sebagai tanda bukti pembayaran \n10. \tProses pembayaran selesai.\n\n\nBUKTI PEMBAYARAN PREMI\no\tSetelah Pemegang Polis melakukan pembayaran premi, Pemegang Polis wajib menginformasikan kepada Penanggung bahwa pembayaran premi telah dilakukan. Jika diperlukan, Pemegang polis harus mengirimkan bukti pembayaran premi kepada Penanggung (melalui fax, email atau menyerahkan copynya), kecuali jika pembayaran dilakukan melalui Virtual account.\no\tSetelah premi diterima dan dibukukan di rekening Penanggung, Penanggung akan mengirimkan kwitansi kepada Pemegang Polis sebagai bukti bahwa premi sudah dibayar lunas.\n\nPREMI TIDAK DIBAYAR\nTagihan Premi yang sudah diterima oleh Tertanggung harus dibayar lunas dalam waktu 14 hari kalender sejak tanggal mulai berlakunya Polis. \nJika Tertanggung tidak memenuhi kewajibannya untuk membayar tagihan premi, maka Polis akan berakhir dengan sendirinya sejak berakhirnya tenggang waktu tersebut tanpa diterbitkannya endorsemen oleh Penanggung dan Penanggung dibebaskan dari semua tanggung jawab berdasarkan Polis.\nNamun Tertanggung tetap berkewajiban membayar premi untuk jaminan selama tenggang waktu pembayaran premi, sebesar 20% dari premi satu tahun. \nApabila terjadi kerugian yang dijamin polis selama tenggang waktu pembayaran (14 hari sejak mulai berlakunya polis) maka Penanggung akan bertanggung jawab terhadap kerugian tersebut jika premi telah dilunasi oleh Tertanggung dalam tenggang waktu yang bersangkutan.\n");
        jTextArea11.setWrapStyleWord(true);
        jTextArea11.setBorder(null);
        jTextArea11.setOpaque(false);
        jScrollPane45.setViewportView(jTextArea11);

        jPanel23.add(jScrollPane45);
        jScrollPane45.setBounds(0, 0, 970, 410);

        jTabbedPane6.addTab("PROSEDUR PENUTUPAN POLIS BARU", jPanel23);

        jTextArea16.setColumns(20);
        jTextArea16.setEditable(false);
        jTextArea16.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea16.setLineWrap(true);
        jTextArea16.setRows(5);
        jTextArea16.setTabSize(5);
        jTextArea16.setText("Endorsement adalah perubahan data pada polis asuransi.\nSetiap ada perubahan data polis harus segera dilaporkan oleh Tertanggung kepada Penanggung melalui Kantor Cabang Asuransi Wahana Tata.\n\nAda 2 jenis Endorsement :\n1.\tPerubahan yang mengakibatkan perubahan premi, yaitu \n\"\tPerubahan aksesoris kendaraan, perubahan perabotan rumah \n\"\tPerubahan harga pertanggungan \n\"\tPerubahan pertanggungan asuransi, misalnya dari TLO menjadi Comprehensive, atau perluasan jaminan tambahan \n2.\tPerubahan yang tidak mengakibatkan perubahan premi, yaitu \n\"\tPerubahan data pemegang polis (nama, alamat, no telp)\n\"\tPerubahan data kendaraan (No Polisi, warna kendaraan)\n\nPROSEDUR ENDORSEMENT POLIS\n1.\tNasabah menghubungi Marketing Asuransi Wahana Tata atau datang ke kantor cabang Aswata dan mengisi formulir perubahan polis.\n2.\tJika diperlukan akan disurvei oleh tim survei Aswata \n3.\tBerdasarkan laporan survey, dibuatkan penawaran dan dikirim ke Tertanggung. Jika tidak ada survey, langsung dibuatkan penawaran.\n4.\tJika Tertanggung setuju, endorsement polis akan diterbitkan dan dikirim ke Tertanggung \n5.\tJika Tertanggung tidak setuju, proposal dapat direvisi (negosiasi syarat & ketentuan polis, tarif premi, dll)\n6.\tSetelah endorsement polis diterima, Tertanggung membayar premi ke Aswata \n7.\tKwitansi premi akan dikirim setelah pembayaran premi diterima dan diinformasikan ke Aswata \n8.\tProses endorsement polis selesai \n\nDOKUMEN YANG DIPERLUKAN\nSurat tertulis dari Tertanggung yang memuat informasi :\n-\tNomor Polis\n-\tData Tertanggung \n-\tObjek pertanggungan \n-\tPerubahan yang diinginkan \n-\tTanggal perubahan \n-\tAlasan perubahan \n\nDOKUMEN YANG DITERBITKAN\nSetelah Penanggung menyetujui permintaan perubahan polis Tertanggung, Penanggung akan menerbitkan dokumen sbb :\n-\tEndorsement Polis\n-\tNota kredit/debet (jika perubahan tersebut mengakibatkan adanya perubahan premi)\n");
        jTextArea16.setWrapStyleWord(true);
        jTextArea16.setBorder(null);
        jTextArea16.setOpaque(false);
        jScrollPane51.setViewportView(jTextArea16);

        jTabbedPane6.addTab("ENDROSEMENT", jScrollPane51);

        jTextArea17.setColumns(20);
        jTextArea17.setEditable(false);
        jTextArea17.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea17.setLineWrap(true);
        jTextArea17.setRows(5);
        jTextArea17.setTabSize(5);
        jTextArea17.setText("PROSEDUR PERPANJANGAN POLIS \n1.\tSebelum polis berakhir, Asuransi Wahana Tata akan menerbitkan Surat Pemberitahuan Polis Jatuh Tempo (Renewal Notice) yang dikirim langsung kepada Tertanggung atau informasi disampaikan oleh Marketing Asuransi Wahana Tata melalui telepon.\n2.\tTertanggung diminta untuk memberikan tanggapan atas surat pemberitahuan yang telah dikirimkan tersebut :\n\"\tSetuju untuk memperpanjang tanpa ada perubahan kondisi \n\"\tSetuju untuk memperpanjang dengan perubahan (Uang pertanggungan, Terms & Conditions, Jenis Pertanggungan, dll)\n\"\tTidak setuju untuk memperpanjang \n3.\tJika diperlukan, akan dilakukan survey oleh Aswata \n4.\tBerdasarkan laporan hasil survey, underwriter menetapkan tarif premi dan Terms & Conditions atas objek pertanggungan tersebut, untuk selanjutnya dibuatkan penawaran oleh Marketing Aswata kepada Tertanggung.\n5.\tProposal dapat direvisi jika ada perubahan yang diminta oleh Tertanggung \n6.\tJika Tertanggung sudah setuju dengan penawaran yang diberikan Penanggung, polis perpanjangan akan diterbitkan dan dikirim ke Tertanggung.\n7.\tJika Tertanggung tidak setuju, maka perpanjangan tidak jadi dilakukan.\n8.\tSetelah polis diterima, Tertanggung membayar premi ke Aswata \n9.\tKwitansi premi akan dikirim setelah pembayaran diterima dan diinformasikan ke Penanggung \n10.\tProses perpanjangan polis selesai \n\nDOKUMEN YANG DIPERLUKAN\nKonfirmasi perpanjangan dari pemegang polis beserta data perubahannya (jika ada)\n\nDOKUMEN YANG DITERBITKAN\n\nPolis lengkap beserta invoice yang harus dibayar oleh Pemegang Polis\n");
        jTextArea17.setWrapStyleWord(true);
        jTextArea17.setBorder(null);
        jTextArea17.setOpaque(false);
        jScrollPane52.setViewportView(jTextArea17);

        jTabbedPane6.addTab("PROSEDUR PERPANJANGAN POLIS", jScrollPane52);

        jTextArea18.setColumns(20);
        jTextArea18.setEditable(false);
        jTextArea18.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea18.setLineWrap(true);
        jTextArea18.setRows(5);
        jTextArea18.setTabSize(5);
        jTextArea18.setText("PEMBATALAN POLIS\nPembatalan polis dapat dilakukan oleh Penanggung atau Tertanggung \n\nPEMBATALAN OLEH TERTANGGUNG\nTertanggung dapat mengajukan pembatalan polis asuransinya dengan mengajukan secara tertulis kepada Penanggung.\nAtas pembatalan polis asuransi tersebut, premi akan dikembalikan secara prorata untuk jangka waktu pertanggungan yang belum dilewati setelah dikurangi biaya akuisisi Penanggung. \nNamun apabila selama jangka waktu pertanggungan yang telah dilewati, telah terjadi klaim yang jumlahnya melebihi jumlah premi yang tercantum pada ikhtisar pertanggungan, maka Tertanggung tidak berhak atas pengembalian premi untuk jangka waktu pertanggungan yang belum dijalani.\n\nPROSEDUR PEMBATALAN POLIS\n1.\tTertanggung yang ingin melakukan pembatalan polisnya harus memberitahukan secara tertulis kepada Asuransi Wahana Tata dan mencantumkan : \n\ta.\tTanggal efektif pembatalan (tidak boleh backdate)\n\tb.\tAlasan pembatalan polis\n\tc.\tNo rekening untuk pengembalian premi \n2.\tTertanggung dapat mendatangi Kantor Cabang Asuransi Wahana Tata dengan membawa surat permintaan pembatalan polis beserta Polis Asuransi asli beserta \t\tEndorsementnya.\n3.\tBerdasarkan permintaan pembatalan yang diterima oleh Penanggung, Penanggung akan menerbitkan endorsemen pembatalan polis beserta nota kredit atas \t\trefund premi untuk sisa periode yang belum dilewati.\n\nDOKUMEN PEMBATALAN POLIS\nSetelah Penanggung menyetujui permintaan pembatalan polis Tertanggung, Penanggung akan menerbitkan dokumen sbb :\no\tEndorsemen pembatalan polis\no\tNota kredit, atas refund premi \n");
        jTextArea18.setWrapStyleWord(true);
        jTextArea18.setBorder(null);
        jTextArea18.setOpaque(false);
        jScrollPane53.setViewportView(jTextArea18);

        jTabbedPane6.addTab("PEMBATALAN POLIS", jScrollPane53);

        pnlinf.addTab("PROSEDUR POLIS", jTabbedPane6);

        jPanel30.setLayout(null);

        jTextArea19.setColumns(20);
        jTextArea19.setEditable(false);
        jTextArea19.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea19.setLineWrap(true);
        jTextArea19.setRows(5);
        jTextArea19.setTabSize(5);
        jTextArea19.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH JAKARTA SELATAN\t\n\t\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tARTHAS MOBILINDO SENTOSA\tJl.Kemang Raya No.11 Bangka Mampang.Jakarta Selatan\t7197655/8183840\t\t7191461\t\n2\tADI WAHYU SEJAHTERA\t\tJl.fatmawati No.25 Kebayoran Baru Jakarta Selatan\t\t7662002\t\t\t7666006\t\n3\tBUANASAKTI ANEKA MOTOR\t\tJl.Warung Buncit Raya No.109 Jakarta Selatan\t\t\t7996171\t\t\t7993572\t\n4\tGHIA TRIAS\t\t\tJl.Nangka No.16 TB Simatupang Jakarta Selatan\t\t7815646/78832748\t\t7815646\t\n5\tMERCINDO AUTORAMA\t\tJl. Mampang Prapatan Raya No. 69-70 \t\t\t(021) 7982304\t\t(021) 7989204\t\n");
        jTextArea19.setWrapStyleWord(true);
        jTextArea19.setBorder(null);
        jTextArea19.setOpaque(false);
        jScrollPane54.setViewportView(jTextArea19);

        jPanel30.add(jScrollPane54);
        jScrollPane54.setBounds(0, 0, 970, 410);

        jTabbedPane7.addTab("JAKARTA SELATAN", jPanel30);

        jTextArea20.setColumns(20);
        jTextArea20.setEditable(false);
        jTextArea20.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea20.setLineWrap(true);
        jTextArea20.setRows(5);
        jTextArea20.setTabSize(5);
        jTextArea20.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH JAKARTA UTARA\t\t\n\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tA LYANG MOTOR\t\t\tJl.Pluit mas V Blok E No.8A Komplek Pluit Mas Jakarta Utara\t\t6695406/6679350\t\t6681441\t\n2\tAREA (AREA PUTRA SENTOSA)\tJl Plumpang Semper No. 30, Jakarta Utara\t\t\t\t4300771 /  4300772\t\t43004970\t\n3\tVICTORIA JAYA MOTOR\t\tJl.Sunter Pulo Kecil No.42 A Sunter Hijau Jakarta Utara\t\t\t6509095/6509101\t\t6509101\t\n4\tRESTU JAYA MOTOR\t\tJl.Nusantara Timur Blok D/47 Jakarta Utara\t\t\t\t6505373/6516578\t\t6505374\t\n5\tROBBY MOTOR\t\t\tJl.Kepu Dalam 111 No.20-22 Jakarta\t\t\t\t\t4240070/4211062\t\t4252073\t\n");
        jTextArea20.setWrapStyleWord(true);
        jTextArea20.setBorder(null);
        jTextArea20.setOpaque(false);
        jScrollPane55.setViewportView(jTextArea20);

        jTabbedPane7.addTab("JAKARTA UTARA", jScrollPane55);

        jTextArea21.setColumns(20);
        jTextArea21.setEditable(false);
        jTextArea21.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea21.setLineWrap(true);
        jTextArea21.setRows(5);
        jTextArea21.setTabSize(5);
        jTextArea21.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH JAKARTA PUSAT\t\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tAUTO LOOK 1\t\tJl.Ahmad Yani No.50 Jakarta Pusat\t\t\t\t4202010/4216221\t4204612\t\n2\tSURYA JAYA MOTOR\tJl.Pemandangan 11 No.9 A Gunung Sahari Jakarta Pusat\t\t6456705/6456706\t6456706\t\n3\tMILLENIUM MOTOR\tJl.Letjen Suprapto N0.19 Cempaka Putih, Jakarta Pusat\t\t4267888\t\t4243971\t\n4\tLIEFS\t\t\tJl.Suryopranoto No.20 Harmoni Jakarta Pusat\t\t\t6325240/6324763\t6331619\t\n");
        jTextArea21.setWrapStyleWord(true);
        jTextArea21.setBorder(null);
        jTextArea21.setOpaque(false);
        jScrollPane56.setViewportView(jTextArea21);

        jTabbedPane7.addTab("JAKARTA PUSAT", jScrollPane56);

        jTextArea22.setColumns(20);
        jTextArea22.setEditable(false);
        jTextArea22.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea22.setLineWrap(true);
        jTextArea22.setRows(5);
        jTextArea22.setTabSize(5);
        jTextArea22.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH JAKARTA BARAT\t\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tAUTO LOOK 2\t\tJl.Raya Kebayoran Lama No.23 A Jakarta Barat\t\t\t5493993\t\t5483447\t\n2\tPRIMATAMA KHARISMA\tJl.Asem No.1 Sisi Tol Kebun Jeruk Jakarta Barat\t\t5349983\t\t5349984\t\n3\tMETRO MOTOR\t\tJl.Arteri Kedoya No.27 Jakarta Barat\t\t\t\t5821826\t\t5818368\t\n4\tBENINDO\t\t\tJl.Rawa Kepa VIII No 40 Tomang Jakarta Barat\t\t\t5680231\t\t5605120\t\n5\tMALIGLOSS\t\tJl.Daan Mogot Km 1 No.9 Jakarta Barat\t\t\t5662128/5819603\t56964727\t\n6\tOKTORA MOTOR\t\tJl.Raya Meruya Ilir No.60 Jakarta Barat\t\t\t5868029\t\t5868034\t\n");
        jTextArea22.setWrapStyleWord(true);
        jTextArea22.setBorder(null);
        jTextArea22.setOpaque(false);
        jScrollPane57.setViewportView(jTextArea22);

        jTabbedPane7.addTab("JAKARTA BARAT", jScrollPane57);

        jTextArea23.setColumns(20);
        jTextArea23.setEditable(false);
        jTextArea23.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea23.setLineWrap(true);
        jTextArea23.setRows(5);
        jTextArea23.setTabSize(5);
        jTextArea23.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH BEKASI\t\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tCAHAYA YOVAN MOTOR\tJl.Raya Caman No.19 Jatibening 11. Bekasi\t\t\t86901141\t\t\t8652712\t\n2\tDIAN MOBIL\t\tJl.Raya Hankam No.33-34 Jati Warna Pondok Gede Bekasi\t84593866\t\t\t84598977\t\n3\tGILGA PAINTING\t\tJl.Industri Cibarusah No 16 Jawa Barat.Cikarang Bekasi\t\t89901683\t\t\t89901830\t\n4\tGILANG MOTOR\t\tJl.Ki Mangun sarkoro No.6 Bekasi\t\t\t\t8812241\t\t\t8807198\t\n5\tSURYA MOTOR\t\tJl.Raya Kalimalang No.122 BEKASI\t\t\t\t86901333/86901332\t8640026\t\n6\tRAPERIND MOTOR\t\tJl.Raya Kalimalang Q/2s,E Jakarta TIMUR\t\t\t8643594/95\t\t8645008\t\n7\tSINAR SURYA MOTOR\tJl.Akses Tol Karawang Barat Jawa Barat\t\t\t0267.8457554/7\t\t0267.8457622\t\n");
        jTextArea23.setWrapStyleWord(true);
        jTextArea23.setBorder(null);
        jTextArea23.setOpaque(false);
        jScrollPane58.setViewportView(jTextArea23);

        jTabbedPane7.addTab("BEKASI", jScrollPane58);

        jTextArea24.setColumns(20);
        jTextArea24.setEditable(false);
        jTextArea24.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea24.setLineWrap(true);
        jTextArea24.setRows(5);
        jTextArea24.setTabSize(5);
        jTextArea24.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH TANGERANG\t\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tDUA SEKAWAN\t\tJl.ImamBonjol No.39 Karawaci Tanggerang\t\t\t5517669/5514349\t5514349\t\n2\tMARGA JAYA\t\tJl.M H Thamrin No.8 d/h Kebon Nanas Cikokol Tanggerang\t55753357/58/59\t55757267\t\n3\tMETRO AUTO SERPONG\tJl.Pahlawan Seribu Kav Komersil 111 A No.3 BSD City\t\t5380696\t\t5821826\t\n4\tBENGKEL MOTOREKO\tJl.RAYA SERPONG Km 7 No. 32\t\t\t\n");
        jTextArea24.setWrapStyleWord(true);
        jTextArea24.setBorder(null);
        jTextArea24.setOpaque(false);
        jScrollPane59.setViewportView(jTextArea24);

        jTabbedPane7.addTab("TANGGERANG", jScrollPane59);

        jTextArea25.setColumns(20);
        jTextArea25.setEditable(false);
        jTextArea25.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea25.setLineWrap(true);
        jTextArea25.setRows(5);
        jTextArea25.setTabSize(5);
        jTextArea25.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH BOGOR\t\t\n\t\t\t\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tCAHAYA NUSA CITRA ( CNC )\tJl.Raya Ciangsana ( dekat trisakti ) Alternatif Cibubur.Cibubur\t\t84931412\t\t\t84931396\t\n2\tEKA MOTOR\t\tJl.Raya Mayor Oking No.51 Cieteruep Bogor\t\t\t\t87941215\t\t\t8752603\t\n3\tHANGGAR\t\tJl.KH Soleh Iskandar Km.4 No.3 Bogor\t\t\t\t\t0251.7535239\t\t0251.7539712\t\n4\tMAKMUR JAYA MOTOR\tJl.Padjajaran No.15 A Bogor\t\t\t\t\t\t251.8310166\t\t0251.310139\t\n5\tKARINDA MOTOR\t\tJl. Raya Kranggan No. 42 Cibubur\t\t\t\t\t021.49034988\t\t\n");
        jTextArea25.setWrapStyleWord(true);
        jTextArea25.setBorder(null);
        jTextArea25.setOpaque(false);
        jScrollPane60.setViewportView(jTextArea25);

        jTabbedPane7.addTab("BOGOR", jScrollPane60);

        jTextArea26.setColumns(20);
        jTextArea26.setEditable(false);
        jTextArea26.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea26.setLineWrap(true);
        jTextArea26.setRows(5);
        jTextArea26.setTabSize(5);
        jTextArea26.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH BANTEN\t\n\t\t\t\t\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tANUGRAH MOTOR\t\tJl.Raya Temu putih No.55 Kav. Blok C Cilegon\t\t\t0254.391941\t0254.393799\t\n2\tKARYA AGUNG OTOMOTIF\tJl.Seneja No.45 C Cilegon Banten\t\t\t\t0254.389255\t0254.389256\t\n3\tSUBUR JAYA MOTOR\tJl.Lingkar Selatan No.79 Ciracas Serang Banten\t\t0254.21092\t0254.21092\t\n");
        jTextArea26.setWrapStyleWord(true);
        jTextArea26.setBorder(null);
        jTextArea26.setOpaque(false);
        jScrollPane61.setViewportView(jTextArea26);

        jTabbedPane7.addTab("BANTEN", jScrollPane61);

        pnlinf.addTab("BENGKEL REKANAN JABODETABEK", jTabbedPane7);

        jPanel31.setLayout(null);

        jTextArea27.setColumns(20);
        jTextArea27.setEditable(false);
        jTextArea27.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea27.setLineWrap(true);
        jTextArea27.setRows(5);
        jTextArea27.setTabSize(5);
        jTextArea27.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH BANDUNG\n\t\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tALFA MOTOR\t\t\tJl. Raya Cimindi No. 272, Celember, Cimahi\t\t\t022-2013058\t222013058\n2\tAUTO CEMERLANG\t\t\tJl. Soekarno Hatta No. 390, Bandung\t\t\t\t022-5221918\t\n3\tAUTO SPOT\t\t\tJl.Jend Sudirman No. 528, Bandung\t\t\t\t022-6012528\t\n4\tCIPENDAWA\t\t\tJl. Raya Sukabumi No. 77D, Cianjur\t\t\t\t0263-263355\t0263-263355\n5\tEMERALD (dh NAGATAMA CARINDO)\tJl. Soekarno Hatta No. 535, Bandung\t\t\t\t022-7312309\t022-7306681\n6\tGARUDA MOTOR - Bandung\t\tJl. Garuda No. 2, Bandung\t\t\t\t\t022-6016744\t\n7\tMEKAR JAYA - Bandung\t\tJl. Terusan Kiaracondong No. 1, Bandung\t\t\t022-7564944\t022-7564944\n8\tOTTO CEMERLANG\t\t\tJl. KU Supadio No. 30 (Jatayu), Bandung\t\t\t022-6031061\t022-6031061\n9\tPASIFIK MOTOR\t\t\tJl. Perintis Kemerdekaan No. 8, Cianjur\t\t\t0263-260358\t\n10\tSAPHIRE\t\t\t\tJl. M. Toha No. 264, Bandung\t\t\t\t022-5205695\t\n11\tTHE STATION\t\t\tJl. Setra Murni, Bandung\t\t\t\t\t022-91250460\t\n12\tWAHANA IWAN\t\t\tJl. Jurang No. 90, Bandung\t\t\t\t\t022-2031271\t022-2030287\n13\tVIRTUE\t\t\t\tJl. Wastukencana No. 39, Bandung\t\t\t\t022-4234524\t\n14\tMS AUTO BODY\t\t\tJl. Sukaraja II No. 19 Gunung Batu, Bandung\t\t\t022-6127342\t\n15\tPITSTOP\t\t\t\tJl. Holis No 246-248, Bandung\t\t\t\t022-6021621\t\n16\tMEGAH JAYA\t\t\tJl. BKR No. 28, Bandung\t\t\t\t\t022-7320903\t\n\n");
        jTextArea27.setWrapStyleWord(true);
        jTextArea27.setBorder(null);
        jTextArea27.setOpaque(false);
        jScrollPane62.setViewportView(jTextArea27);

        jPanel31.add(jScrollPane62);
        jScrollPane62.setBounds(0, 0, 970, 410);

        jTabbedPane9.addTab("BANDUNG", jPanel31);

        jTextArea28.setColumns(20);
        jTextArea28.setEditable(false);
        jTextArea28.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea28.setLineWrap(true);
        jTextArea28.setRows(5);
        jTextArea28.setTabSize(5);
        jTextArea28.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH CIREBON\n\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tALPINA MOTOR\t\t\tJl. Perjuangan Kav. 01, Jembar Agung Majasem, Cirebon\t\t\t0231-3351535\t\t0231-483378\n2\tANUGRAH PERKASA MOTOR\t\tJl. Cideng Jaya No. 87, Cirebon\t\t\t\t\t0231-486607\t\n3\tMEDI\t\t\t\tJl. Kalitanjung / P.Grenjeng No. 11, Cirebon\t\t\t\t0231-484523\t\n4\tSELECTA GRAGE JAYA\t\tJl. Kalijaga No. 108, Cirebon\t\t\t\t\t\t0231-203313/230518\t0231-230518\n5\tSUMBER REJEKI JABAR\t\tJl. BrigJend Dharsono No. 5, Cirebon\t\t\t\t\t0231-486606/486674\t0231-486663\n6\tLAMBANG PUTRA PERKASA MOTOR\tJl. Kalijaga No. 29, Cirebon\t\t\t\t\t\t0231-202353\t\n7\tUTAY Service\t\t\tJl. H.Z. Mustofa, Tasikmalaya\t\t\t\t\t0265-331029\t\n8\tSINAR CEMERLANG\t\t\tJl. Terusan Palem, Tasikmalaya\t\t\t\t\t0283-359677\t\n9\tMALADI JAYA\t\t\tJl. Teuku Cikditiro No. 24, Tegal\t\t\t\t\t0283-350348\t\n\n");
        jTextArea28.setWrapStyleWord(true);
        jTextArea28.setBorder(null);
        jTextArea28.setOpaque(false);
        jScrollPane63.setViewportView(jTextArea28);

        jTabbedPane9.addTab("CIREBON", jScrollPane63);

        jTextArea29.setColumns(20);
        jTextArea29.setEditable(false);
        jTextArea29.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea29.setLineWrap(true);
        jTextArea29.setRows(5);
        jTextArea29.setTabSize(5);
        jTextArea29.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH SEMARANG\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tABECE\t\t\tJl. Candi Prambanan Raya 10, Semarang\t\t\t024-7614112\t024-7615073\n2\tARIS MOTOR\t\tJl. Brotojoyo Timur III/27, Semarang\t\t\t\t024-3561578\t\n3\tBEMATAMA\t\tJl. Hasannudin G.63, Semarang\t\t\t\t024-3547190\t024-3547190\n4\tBEST AUTO\t\tJl. HOS Cokroaminoto 1-A, Kudus\t\t\t\t0291-436427\t0291-446327\n5\tDITICO\t\t\tJl. Kedungmundu Raya 138, Semarang\t\t\t024-6734486\t\n6\tDODDY JAYA\t\tJl. Majapahit 272, Semarang\t\t\t\t024-6723565\t024-6716709\n7\tSANTOSO\t\t\tJl. Pedalangan No.29, Semarang\t\t\t\t024-3519739\t\n8\tSERODJA\t\t\tJl. Bandarharjo Selatan 12A, Semarang\t\t\t024-3546567\t024-3569888\n9\tTUGU JAYA SAKTI\t\tJl. Walisongo No. 99, Semarang\t\t\t\t024-8662444\t\n10\tUTAMA SAKTI\t\tJl. Majapahit No.254, Semarang\t\t\t\t024-719539\t024-717281\n11\tLOTEC AUTOSPEED\t\tJl. Pemuda No. 74, Semarang\t\t\t\t024-3582114\t\n\n");
        jTextArea29.setWrapStyleWord(true);
        jTextArea29.setBorder(null);
        jTextArea29.setOpaque(false);
        jScrollPane64.setViewportView(jTextArea29);

        jTabbedPane9.addTab("SEMARANG", jScrollPane64);

        jTextArea30.setColumns(20);
        jTextArea30.setEditable(false);
        jTextArea30.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea30.setLineWrap(true);
        jTextArea30.setRows(5);
        jTextArea30.setTabSize(5);
        jTextArea30.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH SURAKARTA\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tABSOLUTE\t\tJl. Panglima Sudirman 82B, Madiun\t\t\t\t0351-499031\t\n2\tDARMAN\t\t\tJl. Yosodipuro No.32, Surakarta\t\t\t\t0271-641437\t\n3\tDWI DARMA\t\tJl. Raya Telukan, Grogol, Sukoharjo\t\t\t\t0271-621551\t\n4\tMANDIRI\t\t\tJl. Raya Baki No. 178, Solo Baru, Surakarta\t\t\t0271-621716\t\n5\tNITRO 1\t\t\tJl. Dr. Rajiman 452, Laweyan, Surakarta\t\t\t02761-724940\t\n6\tSURYA INDAH\t\tJl. Bali 113, Madiun\t\t\t\t\t0351-7816125\t\n7\tBANDUNG BONDOWOSO\tJl. Raya Tanjung Anom No. 7, Sukoharjo\t\t\t0271-7000777, 620631\t\n8\tMEGA MERAPI\t\tJl. Jogya No. 180, Kartasura\t\t\t\t\t0271-780080\t\n9\tASTRA DAIHATSU\t\t\t\n\n");
        jTextArea30.setWrapStyleWord(true);
        jTextArea30.setBorder(null);
        jTextArea30.setOpaque(false);
        jScrollPane65.setViewportView(jTextArea30);

        jTabbedPane9.addTab("SURAKARTA", jScrollPane65);

        jTextArea31.setColumns(20);
        jTextArea31.setEditable(false);
        jTextArea31.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea31.setLineWrap(true);
        jTextArea31.setRows(5);
        jTextArea31.setTabSize(5);
        jTextArea31.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH YOGYAKARTA\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tAUTO MOBILINDO\t\tJl. Kabupaten Km. 3, Sleman, Yogyakarta\t\t\t0274-6412968\t0274-6412969\n2\tNEW GENERAL\t\tJl. Magelang Km. 8,5, Yogyakarta\t\t\t\t0274-867807\t\n3\tATLANTA\t\t\tJl. Magelang Km. 5, Yogyakarta\t\t\t\t0274-522414\t\n4\tAUTO 1\t\t\tJl. Magelang Km. 5,2, Yogyakarta\t\t\t\t0274-623663\t\n5\tGRAND STAR\t\tJl. Palagan Tentara Pelajar Km 7,8, Yogyakarta\t\t\t0274-867755\t\n6\tSUMBER AUTO\t\tJl. Magelang Km. 5,5, Yogyakarta\t\t\t\t0274-621983\t\n7\tAGUNG CHOLIL\t\tJl. Mayor Human no. 11, Magelang\t\t\t\t0293-365520\t\n8\tAUTO 168 - Sokaraja\tJl Gatot Subroto, Ruko Sokaraja No. 27, Sokaraja\t\t0281-7633178\t\n9\tBERDIKARI\t\tJl. Gatot Subroto no. 113, Banyumas\t\t\t\t0281-796500\t\n10\tMAESTRO\t\tJl. Raya Imogiri Barat 155B, Yogyakarta\t\t\t0274-382417\t\n\n");
        jTextArea31.setWrapStyleWord(true);
        jTextArea31.setBorder(null);
        jTextArea31.setOpaque(false);
        jScrollPane66.setViewportView(jTextArea31);

        jTabbedPane9.addTab("YOGYAKARTA", jScrollPane66);

        jTextArea32.setColumns(20);
        jTextArea32.setEditable(false);
        jTextArea32.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea32.setLineWrap(true);
        jTextArea32.setRows(5);
        jTextArea32.setTabSize(5);
        jTextArea32.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH SURABAYA\t\t\n\nNO\tBENGKEL\t\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tAUTO BODY REPAIR\t\t\tJl. Raya Ngagel 95, Surabaya\t\t\t\t031-5044777\t\t031-5040209\n2\tCITRA CEMERLANG Body Repair\t\tJl. Raya Jemursari 22, Surabaya 60237\t\t\t031-8471099\t\t031-8471099\n3\tDIAMOND MOTOR\t\t\t\tJl. Ploso Baru No. 171-175, Surabaya\t\t\t\t031-3891756\t\t031-3815582\n4\tFRATON Body Repair & Painting\t\tJl. Alas Malang 11, Sambi Kerep - Surabaya\t\t\t031-8401633\t\t031-7101644\n5\tGILANG MOTOR Body Repair - Surabaya\tJl. Raya Semampir 89, Surabaya\t\t\t\t031-70656760\t\t031-5621639\n6\tHUTAN ALAM\t\t\t\tJL. Kenjeran 615\t\t\t\t\t\t031-3894858\t\t031-3893784\n7\tKARYA AGUNG SPET Auto Painting\t\tJl. Raya Waru 18, Sidoarjo\t\t\t\t\t031-8532057\t\t031-8532057\n8\tLEE\t\t\t\t\tJl. Jend. A. Yani No 36-38, Surabaya\t\t\t\t031-8280822/8280756\t031-5669620\n9\tMASAGUNG\t\t\t\tJl. Kedungsari 37, Surabaya\t031-5341574\t\t\t031-5314600\n10\tSAFARI MEGAH\t\t\t\tJl. A. Yani No. 36-38, Surabaya\t\t\t\t031-8280822\t\t031-8280705\n11\tSIP (Speed Indo Pratama)\t\t\tJl.Tenggilis tengah I No. 28, Surabaya\t\t\t031-8496648\t\t031-8419120\n12\tSPRINT AUTO CENTRE\t\t\tJl. Raya Dukuh Kupang Barat 147, Surabaya\t\t\t031-5688040\t\t031-5667235\n13\tSKIP\t\t\t\t\tJl. Raya Menur 34, Surabaya\t\t\t\t\t031-5017547\t\t031-5027547\n14\tWIN AUTO BODY SHOP\t\t\tJl. Mastrip 224, Surabaya\t\t\t\t\t031-7669900\t\t031-7666367\n\n");
        jTextArea32.setWrapStyleWord(true);
        jTextArea32.setBorder(null);
        jTextArea32.setOpaque(false);
        jScrollPane67.setViewportView(jTextArea32);

        jTabbedPane9.addTab("SURABAYA TUNJUNGAN", jScrollPane67);

        jTextArea33.setColumns(20);
        jTextArea33.setEditable(false);
        jTextArea33.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea33.setLineWrap(true);
        jTextArea33.setRows(5);
        jTextArea33.setTabSize(5);
        jTextArea33.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH MALANG\n\t\t\t\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tTOP MOBIL - Kediri\t\tJl. Supersemar No. 12, Kediri\t\t\t\t\t0354-7011099\t\n2\tTOP MOBIL - Malang\tJl. A. Yani No. 92-94, Malang\t\t\t\t\t0341-486799\t\t0341-407123\n3\tFRATON\t\t\tJl. Perusahaan 88 Ds. Tunjung Tirto , Karanglo - Malang\t\t\t0341-495105\t\t0341-495059\n4\tANICO BODY REPAIR\tJl. Tenaga Baru VI/8, Malang\t\t\t\t\t0341-4177818/2108868\t0341-417818\n\n");
        jTextArea33.setWrapStyleWord(true);
        jTextArea33.setBorder(null);
        jTextArea33.setOpaque(false);
        jScrollPane68.setViewportView(jTextArea33);

        jTabbedPane9.addTab("MALANG", jScrollPane68);

        jTextArea34.setColumns(20);
        jTextArea34.setEditable(false);
        jTextArea34.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea34.setLineWrap(true);
        jTextArea34.setRows(5);
        jTextArea34.setTabSize(5);
        jTextArea34.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH JEMBER\n\t\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tTOP MOBIL -  Jember\t\tJl. Imam Bonjol 18, Jember\t\t\t\t\t0331-339599\t\n2\tMAKMUR MOTOR\t\t\tJl. Mastrip, Probolinggo\t\t\t\t\t0335-421653\t\n3\tSURYA AGUNG MOTOR\t\tJl. Trunojoyo No. 49, Jember\t\t\t\t\t0331-425639\t\n4\tTR MOTOR\t\t\tJl. Sunan Ampel No. 69, Jember\t\t\t\t0331-423093\t\n5\tYUSAK Body Repair\t\tJl. Merak No. 7, Jember\t\t\t\t\t0331-7785959\t\n\n\n\n");
        jTextArea34.setWrapStyleWord(true);
        jTextArea34.setBorder(null);
        jTextArea34.setOpaque(false);
        jScrollPane69.setViewportView(jTextArea34);

        jTabbedPane9.addTab("JEMBER", jScrollPane69);

        jTextArea35.setColumns(20);
        jTextArea35.setEditable(false);
        jTextArea35.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea35.setLineWrap(true);
        jTextArea35.setRows(5);
        jTextArea35.setTabSize(5);
        jTextArea35.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH DENPASAR\n\t\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tAGUNG MITRA AUTORAYA (ASTRIDO)\tJl. Imam Bonjol No. 401A, Denpasar\t\t\t\t484948/484637\t0361-481211\n2\tKAWAN KITA\t\t\tJl. Gunung Agung No. 123A, Denpasar\t\t\t0361-425598\t\n3\tPARAMITHA AUTOGRAHA, PT.\tJl. Ahmad Yani Utara No. 999, Peguyangan, Badung - Bali\t0361-423188\t\n4\tSEPANJANG\t\t\tJl. Gatot Subroto, Denpasar\t\t\t\t\t0361-236407\t\n5\tAUTO METALIC\t\t\tJl. Raya Batu Bulan, Gianyar - Bali\t\t\t\t0361-299495\t\n6\tPANJI LARAS MOTOR\t\tJl. Pura Sakenan, Suwung, Denpasar\t\t\t\t0361-720965\t\n7\tMUJI MOTOR\t\t\tJl. Trengguli Gang XXII/5, Denpasar\t\t\t\t0361-464646\t\n\n");
        jTextArea35.setWrapStyleWord(true);
        jTextArea35.setBorder(null);
        jTextArea35.setOpaque(false);
        jScrollPane70.setViewportView(jTextArea35);

        jTabbedPane9.addTab("DENPASAR", jScrollPane70);

        jTabbedPane8.addTab("JAWA-DENPASAR", jTabbedPane9);

        jPanel32.setLayout(null);

        jTextArea36.setColumns(20);
        jTextArea36.setEditable(false);
        jTextArea36.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea36.setLineWrap(true);
        jTextArea36.setRows(5);
        jTextArea36.setTabSize(5);
        jTextArea36.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH JAMBI\n\t\t\t\t\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tSAFIR MOTOR\t\tJl. HOS Cokroaminoto No. 32, Jambi\t\t\t\t0741-63722\t0741-63722\n2\tSUMBER TEKNIK\t\tJl. Pangeran Hidayat No. 14, Jambi\t\t\t\t0741-40/41333\t0741-42333\n3\tMITRA JAYA\t\tJl. Pangeran Hidayat No. 59, Jambi\t\t\t\t0741-41240\t0741-42689\n4\tPANCA INDAH JAYA MOTOR\tJl. HM. Yusuf Nasri (belakang Persijam), \n\t\t\t\tRT.05/11 Kel. WijayaPura, Kec. Jambi Selatan, Jambi 36131\t0741-32788/89\t0741-32788\n");
        jTextArea36.setWrapStyleWord(true);
        jTextArea36.setBorder(null);
        jTextArea36.setOpaque(false);
        jScrollPane71.setViewportView(jTextArea36);

        jPanel32.add(jScrollPane71);
        jScrollPane71.setBounds(0, 0, 970, 410);

        jTabbedPane10.addTab("JAMBI", jPanel32);

        jTextArea37.setColumns(20);
        jTextArea37.setEditable(false);
        jTextArea37.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea37.setLineWrap(true);
        jTextArea37.setRows(5);
        jTextArea37.setTabSize(5);
        jTextArea37.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH BANDAR LAMPUNG\n\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tAUTO DESIGN\t\t\tJl. Hayam Wuruk No. 46A, Kedamaian Bandar Lampung\t\t\t0721-7443699\t\n2\tAUTOLINE\t\t\tJl. Gatot Subroto No. 104 Garuntang, Bandar Lampung\t\t\t0721-485222\t\n3\tGRAHA SERVICE CENTER\t\tJl. Raya Natar No. 125 Natar, Lampung Selatan\t\t\t\t0721-91662\t\n4\tLAUTAN BERLIAN UTAMA MOTOR, \tJl. Moch. Salim No. 29 Way Lunik, Bandar Lampung\t\t\t0721-341111\t\n\tPT. - Bandar Lampung\t\n5\tMANDIRI\t\t\t\tJl. Letjend Ryacudu No. 8, Sukarame. Bandar Lampung\t\t\t0721-773064\t\n6\tMONTECARLO - Bandar Lampung\tJl. Sultan Agung No. 21 Way Halim, Bandar Lampung\t\t\t0721-770475\t\n7\tCENTRAL \t\t\t\tJl. Urip Sumohardjo No. 17/145, Bandar Lampung\t\t\t0721-709000\t0721-781516\n8\tBUDI BERLIAN MOTOR\t\tJl. Raya Haji Mena Km. 15 Natar, Lampung Selatan\t\t\t0721-774367\t\n");
        jTextArea37.setWrapStyleWord(true);
        jTextArea37.setBorder(null);
        jTextArea37.setOpaque(false);
        jScrollPane72.setViewportView(jTextArea37);

        jTabbedPane10.addTab("BANDAR LAMPUNG", jScrollPane72);

        jTextArea38.setColumns(20);
        jTextArea38.setEditable(false);
        jTextArea38.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea38.setLineWrap(true);
        jTextArea38.setRows(5);
        jTextArea38.setTabSize(5);
        jTextArea38.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH PALEMBANG\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tAMAN\t\t\tJl. Sutan Syahrir No. 57, Palembang\t\t\t\t0711-715462\t\t0711-717465\n2\tBURWI JAYA, PT.\t\tJl. Kebun Karet No. 1318, Palembang\t\t\t\t0711-353511\t\t0711-313031\n3\tJERNIH\t\t\tJl. Jend. A. Yani No. 58, Palembang\t\t\t\t0711-511140\t\t0711-512310\n4\tCITRA TEKNIK\t\tJl. Letda A. Rozak No. 64/949, Palembang\t\t\t0711-711450\t\t0711-710658\n5\tGAT MOTOR\t\tJl. Demang Lebar Daun No. 4263, Palembang 30137\t\t0711-445787/445667\t0711-445622\n6\tCITRA JAYA\t\tJl. Basuki Rahmat No. 2141, Palembang\t\t\t0711-373323/7946888\t0711-375790\n\n");
        jTextArea38.setWrapStyleWord(true);
        jTextArea38.setBorder(null);
        jTextArea38.setOpaque(false);
        jScrollPane73.setViewportView(jTextArea38);

        jTabbedPane10.addTab("PALEMBANG", jScrollPane73);

        jTextArea39.setColumns(20);
        jTextArea39.setEditable(false);
        jTextArea39.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea39.setLineWrap(true);
        jTextArea39.setRows(5);
        jTextArea39.setTabSize(5);
        jTextArea39.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH PEKANBARU\t\t\t\t\n\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tARENGKA PRATAMA\t\tJl. Suka Jaya no. 28 B, Pekanbaru\t\t0761-7766000/561199\t0761-561288\n2\tAUTOBLITZ\t\t\tJl. Kuantan VII No. 72 C, Pekanbaru\t\t0761-7708388\t\t0761-39788\n3\tBINTANG UTAMA\t\t\tJl. Soekarno-Hatta No. 401, PekanBaru\t0761-7644418\t\n4\tCIPTA KARYA MOBIL\t\tJl. Garuda No.44, Pekanbaru\t\t0761-61494\t\n5\tCIPTA PRIMA MOBIL\t\tJl. Riau Ujung No. 80-C, Pekanbaru\t\t0761-7002597/7084947\t0761-27060\n6\tLEO OTOMOTIF - Pekanbaru\t\tJl. Kulim No. 213, Pekanbaru\t\t0761-859030\t\t0761-859070\n7\tNEW EKA MAGIC (d/h Eka Magic)\tJl. Garuda No. 24, Pekanbaru\t\t0761-23818\t\n8\tREGENT JAYA\t\t\tJl. Durian No. 39, Pekanbaru\t\t0761-857618\t\n9\tEKA PRIMA\t\t\tJl. Jend. Sudirman 203, Pekan baru\t\t0761-29605 / 33293\t\n10\tPT. JASA BARUTAMA PERKASA\tJl. Tuanku Tambusai No. 42, Pekanbaru\t85762090100\t\n\n");
        jTextArea39.setWrapStyleWord(true);
        jTextArea39.setBorder(null);
        jTextArea39.setOpaque(false);
        jScrollPane74.setViewportView(jTextArea39);

        jTabbedPane10.addTab("PEKANBARU", jScrollPane74);

        jTextArea40.setColumns(20);
        jTextArea40.setEditable(false);
        jTextArea40.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea40.setLineWrap(true);
        jTextArea40.setRows(5);
        jTextArea40.setTabSize(5);
        jTextArea40.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH BATAM\t\t\t\n\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tBATAM MAKMUR WAHANA MOTOR\tJl. Prambanan No. 21, (Depan Batam Kuring), Batam\t\t\t\t0778-423312\t0778-423341\n2\tCAR SERVICE\t\t\tKomplek Pelita Blok IV NO. 32, Jl. Sriwijaya - Batam\t\t\t\t0778-433609\t\n3\tJAYA MOTOR\t\t\tJl. Wonosari Km. 7 No. 1, Tanjung Pinang\t\t\t\t\t0771-314831\t\n4\tJAYA MAS MAKMUR\t\tJl. Komplek Tatseng Logistics No. 3A Sei. Panas, Batam\t\t\t\t0778-466510\t\n5\tMILLENIUM ASEAN MOTOR\t\tJl. Yos Sudarso (Depan Kodim Seraya), Batu Ampar - Batam\t\t\t0778-433382\t0788-433375\n6\tSURYA SEJAHTERA (SUJAK MOTOR)\tJl. Tenggiri Batu Ampar, Komplek Raperindo Industrial Park Blok BI No.5, Batam\t0778-411178\t0788-411179\n7\tSTAR AUTO CAR\t\t\tJl. Seraya No. 88, (Samping PT. Citra Buana Prakarsa), Lubuk Baja - Batam\t\t0778-7033567\t0788-456273\n8\tTOKO GALAXY\t\t\tJl. Teuku Umar Komplek PT. Asean No.32, Pasar Pelita - Batam\t\t\t0778-452222\t\n");
        jTextArea40.setWrapStyleWord(true);
        jTextArea40.setBorder(null);
        jTextArea40.setOpaque(false);
        jScrollPane75.setViewportView(jTextArea40);

        jTabbedPane10.addTab("BATAM", jScrollPane75);

        jTextArea41.setColumns(20);
        jTextArea41.setEditable(false);
        jTextArea41.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea41.setLineWrap(true);
        jTextArea41.setRows(5);
        jTextArea41.setTabSize(5);
        jTextArea41.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH PADANG\t\t\t\n\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tACIAK MOTOR\t\t\tJl. St Syahrir No. 309, Mata Air - Padang\t\t\t0751-63622\t\n2\tANDI'S PAINTING STUDIO\t\tJl. Ujung Pandan No.2, HangTuah. Padang\t\t\t0751-25743/25751\t0751-25794\n3\tANDI'S PAINTING STUDIO\t\tJl. Raya BukitTinggi Payakumbuh Km.7, Biaro. Agam\t\t\n4\tCV. DUA TIGA EMPAT\t\tJl. Guguak Randfah, Kecamatan IV Koto, Kabupaten Agam\t\t\n5\tMAKNA MOTOR\t\t\tJl. Arif Rahman Hakim No. 66, Padang\t\t\t0751-25271\t0751-29033\n6\tSTEL AUTOBODY\t\t\tJl. Alai Timur No. 88, Padang\t\t\t\t0751-444945\t\n7\tSUMATERA JAYA ABADI MOTOR\tJl. Batang Arau No. 84, Padang\t\t\t\t0751-36400\t\n8\tTOSHIO MOTOR\t\t\tJl. Purus III No. 8, Padang\t\t\t\t\t0751-39000/39111\t0751-810551\n\n");
        jTextArea41.setWrapStyleWord(true);
        jTextArea41.setBorder(null);
        jTextArea41.setOpaque(false);
        jScrollPane76.setViewportView(jTextArea41);

        jTabbedPane10.addTab("PADANG", jScrollPane76);

        jTextArea42.setColumns(20);
        jTextArea42.setEditable(false);
        jTextArea42.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea42.setLineWrap(true);
        jTextArea42.setRows(5);
        jTextArea42.setTabSize(5);
        jTextArea42.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH MEDAN\n\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\t26 MOBIL\t\t\t\tJl. Gaharu No. 110, Medan\t\t\t\t\t061-4146808\t\t061-4551747\n2\tSEJAHTERA SERVICE STATION\tJl. Gereja No. 18, Medan\t\t\t\t\t061-6631063\t\t061-6615705\n3\tMITRA JAYA SERVICE\t\tJl. Wahidin No. P 14, Medan\t\t\t\t061-4534614/77773977\t061-6620621\n4\tA KENG SERVICE\t\t\tJl. H.M. Jhoni No. 32, Medan\t\t\t\t061-7367879/7323765\t061-7323785\n5\tPELANGI SERVICE\t\t\tJl. Brigjend. Katamso No. 561, Medan\t\t\t\t061-7861333\t\t061-7869975\n6\tAUTO STAR\t\t\tJl. Kapten Muslim No. 131, Medan\t\t\t\t061-77845737\t\t061-8445408\n7\tSEHAT\t\t\t\tJl. Jend. Gatot Subroto No. 80, Medan\t\t\t061-4574892\t\t061-4147908\n8\tSTUDIO SERVICE\t\t\tJl. H. Adam Malik No. 33, Medan\t\t\t\t061-4570055\t\t061-8467115\n\t\n");
        jTextArea42.setWrapStyleWord(true);
        jTextArea42.setBorder(null);
        jTextArea42.setOpaque(false);
        jScrollPane77.setViewportView(jTextArea42);

        jTabbedPane10.addTab("MEDAN", jScrollPane77);

        jTabbedPane8.addTab("SUMATERA", jTabbedPane10);

        jPanel33.setLayout(null);

        jTextArea43.setColumns(20);
        jTextArea43.setEditable(false);
        jTextArea43.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea43.setLineWrap(true);
        jTextArea43.setRows(5);
        jTextArea43.setTabSize(5);
        jTextArea43.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH BALIKPAPAN\n\t\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tDIRGANTARA MOTOR\t\tJl. Kol. Syarifudin Yos No. 2, Balikpapan\t\t\t0812-5806222\t\n2\tINDRA JAYA\t\t\tJl. S. Parman 03 Rt. 028, Balikpapan\t\t\t\t0542-730495\t\n3\tLIBERTY (PT. ANEKA WARNA)\t\tJl. KH. Ahmad Dalan No. 8 RT. 26, Bontang\t\t\n4\tNEW BARONET\t\t\tJl Jend. Sudirman, Balikpapan\t\t\t\t0542-426110\t\n5\tPERDANA\t\t\t\tJl. S. Parman Rt. 30/14, Balikpapan\t\t\t\t0542-734403\t\n6\tDIAN CITRA\t\t\tJl. Patimura, Balikpapan\t\t\t\t\t0542-860767\t\n7\tAUTO SERVICE CENTER\t\tJl. M.T. Haryono No. 30, Balikpapan\t\t\t\t0542-876988\t0542-876989\n\n");
        jTextArea43.setWrapStyleWord(true);
        jTextArea43.setBorder(null);
        jTextArea43.setOpaque(false);
        jScrollPane78.setViewportView(jTextArea43);

        jPanel33.add(jScrollPane78);
        jScrollPane78.setBounds(0, 0, 970, 410);

        jTabbedPane11.addTab("BALIKPAPAN", jPanel33);

        jTextArea44.setColumns(20);
        jTextArea44.setEditable(false);
        jTextArea44.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea44.setLineWrap(true);
        jTextArea44.setRows(5);
        jTextArea44.setTabSize(5);
        jTextArea44.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH BANJARMASIN\t\n\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tMITRA ABADI MOTOR\t\tJl. Jend. A. Yani Km. 08, Banjarmasin\t\t\t\t\t0511-7487012\t0511-7471824\n2\tMITRA MEGAH PROFITAMAS\t\tJl. Jend. Achmad Yani Km. 2.5, Banjarmasin\t\t\t\t0511-253311\t0511-251200\n3\tNUSANTARA INDAH\t\tJl. Jend. Achmad Yani Km. 6.8, Banjarmasin\t\t\t\t0511-272112\t0511-272112\n4\tWIRA MEGAH PROFITAMAS\t\tJl. Jend. Achmad Yani Km. 10, Banjarmasin\t\t\t\t0511-272000\t0511-263000\n\n\n\n");
        jTextArea44.setWrapStyleWord(true);
        jTextArea44.setBorder(null);
        jTextArea44.setOpaque(false);
        jScrollPane79.setViewportView(jTextArea44);

        jTabbedPane11.addTab("BANJARMASIN", jScrollPane79);

        jTextArea45.setColumns(20);
        jTextArea45.setEditable(false);
        jTextArea45.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea45.setLineWrap(true);
        jTextArea45.setRows(5);
        jTextArea45.setTabSize(5);
        jTextArea45.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH PONTIANAK\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tATIE MOTOR\t\tJl. Sei Raya Dalam, Komp. Bumi Batara II No.55-56, Pontianak\t0811-564210\t\n2\tDAYA MOTOR\t\tJl. Adisucipto Km. 4 No. 6-7, Pontianak\t\t\t0561-737540\t\n\n");
        jTextArea45.setWrapStyleWord(true);
        jTextArea45.setBorder(null);
        jTextArea45.setOpaque(false);
        jScrollPane80.setViewportView(jTextArea45);

        jTabbedPane11.addTab("PONTIANAK", jScrollPane80);

        jTextArea46.setColumns(20);
        jTextArea46.setEditable(false);
        jTextArea46.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea46.setLineWrap(true);
        jTextArea46.setRows(5);
        jTextArea46.setTabSize(5);
        jTextArea46.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH SAMARINDA\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tJ & W\t\t\tJl. Mayjend. DI. Panjaitan No. 12 Rt. 4, Samarinda\t\t\t0541-282832/35\t0541-281274\n2\tMKS\t\t\tJl. Katamso No. 53, Samarinda\t\t\t\t\t0541-7072407\t0541-743930\n3\tTOMMY BODYWORKS\tJl. Juanda Kompleks Wijaya Kusuma 2, RT. 19 No.6  Samarinda\t\t0541 - 7773888\t0541 - 7773801\n4\tSUMBER 168\t\tJl. Mayjend. D.I Panjaitan, (Kesejahteraan) Rt.68 No. 49, Samarinda\t0541-280770\t0541-280771\n5\tMGT MOTOR\t\tJl. P. Suryanata No. 99 Rt. 42, Samarinda\t\t\t\t0541-290333\t0541-290594\n6\tTRINITY\t\t\tJl. Bung Tomo No. 49, Samarinda Seberang\t\t\t\t0541-262800\t0541-263842\n\n");
        jTextArea46.setWrapStyleWord(true);
        jTextArea46.setBorder(null);
        jTextArea46.setOpaque(false);
        jScrollPane81.setViewportView(jTextArea46);

        jTabbedPane11.addTab("SAMARINDA", jScrollPane81);

        jTabbedPane8.addTab("KALIMANTAN", jTabbedPane11);

        jPanel34.setLayout(null);

        jTextArea47.setColumns(20);
        jTextArea47.setEditable(false);
        jTextArea47.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea47.setLineWrap(true);
        jTextArea47.setRows(5);
        jTextArea47.setTabSize(5);
        jTextArea47.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH MAKASAR\n\t\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\t\tNO.FAX\t\n1\tADI KARYA\t\t\tJl. Cepa No. 5, Makassar\t\t\t\t\t0411-449087\t\n2\tADIPURA CAR SERVICE - STATION\tJl. Adipura No. 39, Makassar\t\t\t\t0411-412885\t\n3\tSMILE AUTO SERVICE\t\tJl. Veteran Selatan No. 248-253, Makassar\t\t\t0411-876879\t\n\n\n");
        jTextArea47.setWrapStyleWord(true);
        jTextArea47.setBorder(null);
        jTextArea47.setOpaque(false);
        jScrollPane82.setViewportView(jTextArea47);

        jPanel34.add(jScrollPane82);
        jScrollPane82.setBounds(0, 0, 970, 410);

        jTabbedPane12.addTab("MAKASAR", jPanel34);

        jTextArea48.setColumns(20);
        jTextArea48.setEditable(false);
        jTextArea48.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea48.setLineWrap(true);
        jTextArea48.setRows(5);
        jTextArea48.setTabSize(5);
        jTextArea48.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH MANADO\t\t\n\t\t\t\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tFOCUS AUTO\t\t\tJl. Wolter Monginsidi, Manado\t\t\t\t\t0431-833868\t0431-841557\n2\tKAWANUA SEJATI\t\t\tJl. Korengkeng No. 12, Manado\t\t\t\t\t08124406779\t0431-864034\n3\tTERATAI\t\t\t\tJl. Marthadinata 4 No. 14, Manado\t\t\t\t\t0431-852375\t\n\n\n");
        jTextArea48.setWrapStyleWord(true);
        jTextArea48.setBorder(null);
        jTextArea48.setOpaque(false);
        jScrollPane83.setViewportView(jTextArea48);

        jTabbedPane12.addTab("MANADO", jScrollPane83);

        jTextArea49.setColumns(20);
        jTextArea49.setEditable(false);
        jTextArea49.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea49.setLineWrap(true);
        jTextArea49.setRows(5);
        jTextArea49.setTabSize(5);
        jTextArea49.setText("\t\tBENGKEL REKANAN ASWATA (MOU) WILAYAH PALU\t\t\t\n\nNO\tBENGKEL\t\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\n1\tPATRAKO MOTOR ABADI, PT.\t\tJl. Emmy Saelan No. 45, Palu\t\t\t\t0451-482278\t\n2\tSAHABAT TEKHNIK, CV. - Palu\tJl. Hang Tuah No. 6B, Palu\t\t\t\t\t0451-423854\t\n\n");
        jTextArea49.setWrapStyleWord(true);
        jTextArea49.setBorder(null);
        jTextArea49.setOpaque(false);
        jScrollPane84.setViewportView(jTextArea49);

        jTabbedPane12.addTab("PALU", jScrollPane84);

        jTextArea50.setColumns(20);
        jTextArea50.setEditable(false);
        jTextArea50.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea50.setLineWrap(true);
        jTextArea50.setRows(5);
        jTextArea50.setTabSize(5);
        jTextArea50.setText("\t\tBENGKEL REKANAN ASWATA (MOU)WILAYAH KENDARI\t\t\t\t\n\nNO\tBENGKEL\t\t\tALAMAT\t\t\t\t\t\t\tNO.TELPON\tNO.FAX\t\t\t\n1\tPERDANA MOBIL\t\tJl. Letjend. R. Suprapto No. 63, Kendari\t\t\t0401-322447\t\n2\tSETIA BUDI MOTOR\tJl. Letjen. R.Soeprapto No. 86, Kendari\t\t\t0401-323840\t\n\n");
        jTextArea50.setWrapStyleWord(true);
        jTextArea50.setBorder(null);
        jTextArea50.setOpaque(false);
        jScrollPane85.setViewportView(jTextArea50);

        jTabbedPane12.addTab("KENDARI", jScrollPane85);

        jTextArea51.setColumns(20);
        jTextArea51.setEditable(false);
        jTextArea51.setFont(new java.awt.Font("Calibri", 0, 12));
        jTextArea51.setLineWrap(true);
        jTextArea51.setRows(5);
        jTextArea51.setTabSize(5);
        jTextArea51.setText("\t\tSORONG\t\n\t\t\t\n1\tSEKAR SARI\t\t\t\tJl. Sam Ratulangi No. , Sorong\t\t\n2\tTRISAKTI MEGA INDAH, PT.\t\t\tJl. Selat Rumberpon No. , Sorong\t0951-325700\t\n");
        jTextArea51.setWrapStyleWord(true);
        jTextArea51.setBorder(null);
        jTextArea51.setOpaque(false);
        jScrollPane86.setViewportView(jTextArea51);

        jTabbedPane12.addTab("SORONG", jScrollPane86);

        jTabbedPane8.addTab("SULAWESI", jTabbedPane12);

        pnlinf.addTab("BENGKEL REKANAN LUAR JABODETABEK", jTabbedPane8);

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kantor regional jakarta.png"))); // NOI18N
        jScrollPane20.setViewportView(jLabel99);

        jTabbedPane13.addTab("KANTOR REGIONAL JAKARTA", jScrollPane20);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kantor regional timur.png"))); // NOI18N
        jScrollPane2.setViewportView(jLabel10);

        jTabbedPane13.addTab("KANTOR REGIONAL TIMUR", jScrollPane2);

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kantor regional tengah.png"))); // NOI18N
        jScrollPane21.setViewportView(jLabel100);

        jTabbedPane13.addTab("KANTOR REGIONAL TENGAH", jScrollPane21);

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kantor regional barat.png"))); // NOI18N
        jScrollPane24.setViewportView(jLabel101);

        jTabbedPane13.addTab("KANTOR REGIONAL BARAT", jScrollPane24);

        pnlinf.addTab("CABANG", jTabbedPane13);

        jtab.addTab("Information", pnlinf);

        jPanel2.add(jtab);
        jtab.setBounds(6, 8, 980, 470);

        jPanel2.setBounds(10, 170, 990, 480);
        jdp.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jdp, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        getAccessibleContext().setAccessibleName("MPM RENT");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfaxActionPerformed
        // TODO add your handling code here:
        int z=0;
         try {
            sql = "select * from log_fax " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
            rs = jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                z++;
            }
            if (z!=0){
                ccasw_Fax_incoming fax = new ccasw_Fax_incoming();
                fax.setVisible(true);
                Fin.open();

            }else{
                sql="update log_fax set " +
                        "_status=1, " +
                        "username='"+lbluser.getText()+"' " +
                        "where _direction=0 " +
                        "and _status=0 " +
                        "order by rcvd_time limit 1";
                jconn.SQLExecute(sql, conn);
                // cek rebutan
                sql1="select * from log_fax " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql1, conn);
                z=0;
                while (rs.next()) {
                    z++;
                }
                if(z!=0){
                    s = "FAX|UPDATE\r\n";
                    kirimBroad();
                    ccasw_Fax_incoming fax = new ccasw_Fax_incoming();
                    fax.setVisible(true);
                    Fin.open();
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnfaxActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (ticshow==false){
            ccasw_ticket tic = new ccasw_ticket();
            tic.setVisible(true);

            Tic.newtic=true;
            Tic.claimReg=false;
            ticshow=true;
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    @SuppressWarnings("static-access")
    private void btncallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncallActionPerformed
        // TODO add your handling code here:
        btncall.setEnabled(false);
        lblactivity.setText("On line");
        btncall.setDebugGraphicsOptions(v);
        s = "PICKUP\r\n";
        kirimTele();
//          System.out.print("\nyang ini d PICKUP isi string s = "+ s);
        stop();
        delay();

        sql="update log_phone set delay='"+elapsed+"', _callstatus=1 where log_id='"+loid+"'";
        jconn.SQLExecute(sql, conn);
//                      System.out.print(sql);
        sql3="update user_account set _activity=3, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
        jconn.SQLExecute(sql3, conn);
        ccasw_InBoundCall inc = new ccasw_InBoundCall();
        inc.setVisible(true);
        Inc.cek();
    }//GEN-LAST:event_btncallActionPerformed

    private void btnsmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsActionPerformed
         // TODO add your handling code here:
        int z=0;
         try {
            sql = "select * from log_sms " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
            rs = jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                z++;
            }
            if (z!=0){
                ccasw_Sms_income sms = new ccasw_Sms_income();
                sms.setVisible(true);
                Sin.open();

            }else{
                sql="update log_sms set " +
                        "_status=1, " +
                        "username='"+lbluser.getText()+"' " +
                        "where _direction=0 " +
                        "and _status=0 " +
                        "order by sms_date, sms_time limit 1";
                jconn.SQLExecute(sql, conn);
                // cek rebutan
                sql1="select * from log_sms " +
                    "where _direction=0 " +
                    "and _status=1 " +
                    "and username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql1, conn);
                z=0;
                while (rs.next()) {
                    z++;
                }
                if(z!=0){
                    s = "SMS|UPDATE\r\n";
                    kirimBroad();
                    ccasw_Sms_income sms = new ccasw_Sms_income();
                    sms.setVisible(true);
                    Sin.open();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsmsActionPerformed

    private void btnmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailActionPerformed
        // TODO add your handling code here:
        int z=0;
         try {
            sql = "select * from log_mail " +
                    "where direction=0 " +
                    "and status=1 " +
                    "and username='"+ lbluser.getText()+"'";
            rs = jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                z++;
            }
            if (z!=0){
                ccasw_Email_incoming mail = new ccasw_Email_incoming();
                mail.setVisible(true);
                Ein.open();

            }else{
                sql="update log_mail set " +
                        "status=1, " +
                        "username='"+lbluser.getText()+"' " +
                        "where direction=0 " +
                        "and status=0 " +
                        "order by mail_date, mail_time limit 1";
                jconn.SQLExecute(sql, conn);
                // cek rebutan
                sql1="select * from log_mail " +
                    "where direction=0 " +
                    "and status=1 " +
                    "and username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql1, conn);
                z=0;
                while (rs.next()) {
                    z++;
                }
                if(z!=0){
                    s = "MAIL|UPDATE\r\n";
                    kirimBroad();
                    ccasw_Email_incoming mail = new ccasw_Email_incoming();
                    mail.setVisible(true);
                    Ein.open();
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnmailActionPerformed

    private void tblticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblticMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            btnsenddept.setEnabled(true);
            if (tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Details"))==null){
                txtdetail.setText("");
            }else{
                txtdetail.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Details")));
            }
            if (tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Solution"))==null){
                txtsolution.setText("");
            }else{
                txtsolution.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Solution")));
            }
            
        }
        if(evt.getClickCount()==2){
            if(ticshow==false){                
    //            System.out.print("debugging");
                ccasw_ticket tic = new ccasw_ticket();
                tic.setVisible(true);
                Tic.newtic=false;

                Tic.txtnotic.setText((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket No.")));
//                Tic.ass=((String)tbltic.getValueAt(tbltic.getSelectedRow(),3));
                Tic.id=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket Id")));
    //            System.out.print("isi dari txtnotic"+Tic.txtnotic);
                System.out.print("isi dari txtnotic"+Tic.id);
                System.out.print("isi dari ticno"+Tic.ticno);

                Tic.klik();
                ticshow=true;
            }
        }
    }//GEN-LAST:event_tblticMouseClicked

    private void tblminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblminMouseClicked
        // TODO add your handling code here:
        int row=0;
        row=tblmin.getSelectedRow();
        if(row>=0){
            txtfrom.setText(tabmin.getValueAt(row,2).toString());
            txtisu.setText(tabmin.getValueAt(row,3).toString());
            txtimsg.setText(tabmin.getValueAt(row,6).toString());
            mailid=tabmin.getValueAt(row,7).toString();
            DefaultListModel listModel = new DefaultListModel();
            jList2.setModel(listModel);
            try{
                jList2.setModel(listModel);
                sql1="select filename from mail_attachment where mail_id='"+mailid+"'";
                rs1=jconn.SQLExecuteRS(sql1,conn);
                while(rs1.next()){
                    listModel.addElement(rs1.getString("filename").toString());
                }
            }catch(Exception e){
                System.out.println(e);
            }
            cuscom1=tabmin.getValueAt(row,8).toString();
            cbcust1.setSelectedItem(cuscom1);
        }else{
            txtfrom.setText("");
            txtisu.setText("");
            txtimsg.setText("");
            cbcust1.setSelectedIndex(-1);
        }
}//GEN-LAST:event_tblminMouseClicked

    private void tblmouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmouMouseClicked
        // TODO add your handling code here:
            int row=0;
            row=tblmou.getSelectedRow();
            txtoto.setText(tabmou.getValueAt(row,2).toString());
            txtosu.setText(tabmou.getValueAt(row,4).toString());
            txtomsg.setText(tabmou.getValueAt(row,7).toString());
            if (tabmou.getValueAt(row,8)!=null){
                txtocc.setText(tabmou.getValueAt(row,8).toString());
            }
            if (tabmou.getValueAt(row,5)!=null){
                txtidti.setText(tabmou.getValueAt(row,5).toString());
            }
            mailid=tabmou.getValueAt(row,9).toString();
            DefaultListModel listModel1 = new DefaultListModel();
            jList3.setModel(listModel1);
            try{
                jList3.setModel(listModel1);
                sql1="select filename from mail_attachment where mail_id='"+mailid+"'";
                rs1=jconn.SQLExecuteRS(sql1,conn);
                while(rs1.next()){
                    listModel1.addElement(rs1.getString("filename").toString());
                }
            }catch(Exception e){
                System.out.println(e);
            }
}//GEN-LAST:event_tblmouMouseClicked

    private void panelmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelmailMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_panelmailMouseClicked

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        // TODO add your handling code here:
        int i=JOptionPane.showConfirmDialog(null, "Really want to Exit..?","Exit",JOptionPane.YES_NO_OPTION);
        if (i==JOptionPane.YES_OPTION){
        String input=JOptionPane.showInputDialog("Fill the reason");
            sql="update user_account set _status=0, _activity=0, host_addr='"+pabx+"',logout_time='"+ld+"',info='"+input+"'where password = md5('"+lblpas.getText()+"') and username= '" +lbluser.getText()+ "' limit 1";
            jconn.SQLExecute(sql, conn);            
//            System.out.println(sql);
            s = "CLOSE\r\n";
              kirimTele();
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(ccasw_InBoundCall.class.getName()).log(Level.SEVERE, null, ex);
            }
//            connectuploder();
            s = "CLOSE\r\n";
             kirimUplo();
             try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(ccasw_InBoundCall.class.getName()).log(Level.SEVERE, null, ex);
            }
//            connectuploder();
            s = "CLOSE\r\n";
             kirimUplo1();

              cleanUptele();
              cleanUpupload();
              cleanUpupload1();
              cleanbroad();
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            System.exit(0);            
        }
}//GEN-LAST:event_btnlogoutActionPerformed

    private void btnoutboundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoutboundActionPerformed
        // TODO add your handling code here:
        if(outbound==true){
            ccasw_OutBound out = new ccasw_OutBound();
            out.setVisible(true);
        }else{
            s = "REGISTER|"+pabx+"|"+out_ext+"|"+out_ext+"\r\n";
              kirimTele();
              int z=0;
             try {
                 //cek gantung
                sql = "select * from tickets " +
                        "where confirmed=1 " +
                        "and confirm_username='"+ lbluser.getText()+"'";
                rs = jconn.SQLExecuteRS(sql, conn);
                System.out.print("\ncek gantung : "+sql);
                while (rs.next()) {
                    z++;
                }
                System.out.print("\ncek counter : "+z);
                if (z!=0){
                    ccasw_OutBound out = new ccasw_OutBound();
                    out.setVisible(true);
                    Obc.klik2();

                }else{
                    sql="update tickets set " +
                            "confirmed=1, " +
                            "confirm_username='"+lbluser.getText()+"' " +
                            "where _status=2 and confirm=1 and confirm_by=0 and confirm_username is null and confirmed=0 " +
                            "order by close_date, close_time limit 1";
                    jconn.SQLExecute(sql, conn);
                     // cek rebutan
                    sql1="select * from tickets " +
                        "where confirmed=1 " +
                        "and confirm_username='"+ lbluser.getText()+"'";
                    rs = jconn.SQLExecuteRS(sql1, conn);
                    z=0;
                    while (rs.next()) {
                        z++;
                    }
                    if(z!=0){
                        s = "TICKET|CONFIRMED\r\n";
                        kirimBroad();
                        ccasw_OutBound out = new ccasw_OutBound();
                        out.setVisible(true);
                        Obc.klik2();
                    }
                }


            } catch (SQLException ex) {
                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        if (c==0){
//
//        }else{
//            c--;
//            txtcalnoti.setText(String.valueOf(c));
//        }
        
}//GEN-LAST:event_btnoutboundActionPerformed

    private void btnreadyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreadyMouseClicked
        // TODO add your handling code here:            
            
//            System.out.print(s);
    }//GEN-LAST:event_btnreadyMouseClicked

public static String clbk=null;
public static String cldt=null;
String tic0;
    private void tblinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblinMouseClicked
        // TODO add your handling code here:
        if(tabin.getRowCount()!=0){
            if(evt.getClickCount()==2&&lblactivity.getText().equals("Disconnected")){
                if(inshow==false){
                    
                    ccasw_InBoundCall inc = new ccasw_InBoundCall();
                    inc.setVisible(true);
                    Inc.loid=null;

                    Inc.loid=String.valueOf((String)tblin.getValueAt(tblin.getSelectedRow(),tblin.getTableHeader().getColumnModel().getColumnIndex("Log ID")));
                    Inc.lbldurasi.setText((String)tblin.getValueAt(tblin.getSelectedRow(),tblin.getTableHeader().getColumnModel().getColumnIndex("Duration")));

                    Inc.hangup=true;
                    Inc.cek();
                    inshow=true;
                }else{
                    JOptionPane.showMessageDialog(null,"Close the last form before");
                }
            }else if(evt.getClickCount()==2){
                JOptionPane.showMessageDialog(null,"You cannot open history if you registered");
            }
        }
    }//GEN-LAST:event_tblinMouseClicked

    private void tbloutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbloutMouseClicked
        // TODO add your handling code here:
//        if(evt.getClickCount()==1){
//            tabou.setRowCount(0);
//            tabelou();
//        }
//        if(evt.getClickCount()==2){
//           ccasw_OutBound obc = new ccasw_OutBound();
//           obc.setVisible(true);

//           Obc.t
//        }
}//GEN-LAST:event_tbloutMouseClicked

    private static String tic1,tic2,tic3,tic4,cal1,cal2,cal3,cal4,cal5,cal6,sms1,sms2,mail1,mail2,fax1,fax2;

    public static javax.swing.table.DefaultTableModel getDefaultTabelreptic(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Ticket Id","Ticket No","Priority","Status","Department","Source Ticket","Ticket Media"
                        ,"Ticket Type","Category","Category Detail","Sub Category Detail"
                        ,"Category Reason","Related Product","No Polis","Name"
                        ,"Sex","Work Phone","Cell Phone","Fax","Email"

                        ,"Address","Note","Deatils","Solution"
                        ,"Towing Code","Towing Name","Towing Phone","Towing Email"
                        ,"MV Jangka Waktu Pertanggungan","MV Luas Jaminan","MV Merk, Model dan Type","MV Nomor Polisi","MV Nomor Rangka/Nomor Mesin"
                        ,"MV Tahun Pembuatan/Warna","MV Harga Pertanggungan","Fire Jangka Waktu Pertanggungan","Fire Luas Jaminan","Fire Lokasi Pertanggungan"

                        ,"Fire Construction Class","Fire Harga Pertanggungan","Fire Kode Okupasi/Okupasi Bangunan","MC Jangka Waktu Pertanggungan","MC Luas Jaminan"
                        ,"MC Nama Kapal/Pesawat","MC Nomor Kapal/Pesawat","MC Tanggal Keberangkatan Pelabuhan Asal","MC Dokumen Pembelian dan Pengiriman","MC Nama Barang"
                        ,"MC Jumlah Barang","MC Nilai Pertanggungan","MC Consignee/Beneficiary","PA Nama Beneficiary","PA Luas Jaminan"
                        ,"PA Harga Pertanggugan","PA Ahli Waris","Open Date","Open time","Open duration"

                        ,"Open username","Process date","Process time","Process duration","process_username"
                        ,"pre_close_date","pre_close_time","pre_close_duration","pre_close_username","close_date"
                        ,"close_time","close_duration","close_days","close_username","Re-Open Date"
                        ,"Re-Open time","Re-Open duration","Re-Open username","Re-Process date","Re-Process time"

                        ,"Re-Process duration","Re-process_username","Re-close_date","Re-close_time","Re-close_duration"
                        ,"Re-close_days","Re-close_username","_submitted"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false                            ,false,false,false,false,false
                            ,false,false,false,false,false

                            ,false,false,false,false,false                            ,false,false,false,false,false
                            ,false,false,false,false,false                            ,false,false,false,false,false

                            ,false,false,false,false,false                            ,false,false,false,false,false
                            ,false,false,false,false,false                            ,false,false,false,false,false
                            
                            ,false,false,false,false,false                            ,false,false,false,false,false
                            ,false,false,false,false,false                            ,false,false,false,false,false

                            ,false,false,false,false,false                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    private void btnrepticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepticActionPerformed
        // TODO add your handling code here:
        int x=0;
        tabreptic.setRowCount(0);
        Date dt1 =dctic1.getDate();
        Date dt2 =dctic2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tic1 = sdf.format(dt1);
        tic2= sdf.format(dt2);
        try{
            int row=0;         

            sql="select * "
                    + ", _sendertype.data as source"
                    + ", _ticketmedia.data as media"
                    + ", _inboundtype.data as _type"
                    + ", _ticketcategory.category_name as category"
                    + ", _ticketdetailcategory.detail_category_name as category_detail"
                    + ", _ticketdetailsubcategory.detail_sub_category_name as category_subdetail"
                    + ", a.dept_name as dept_name"
                    + ", b.dept_name as ass_dept"
                    + ", _ticketpriority.data as prior"
                    + ", _ticketstatus.data as stt"
                    + ", _relatedproduct.data as prod"
                    + ", '' as bbb"
                    + " from tickets "
                    + " left join _sendertype on tickets.source_id=_sendertype.code"
                    + " left join _ticketmedia on tickets.media_id=_ticketmedia.code"
                    + " left join _inboundtype on tickets.type_id=_inboundtype.code"
                    + " left join _ticketcategory on tickets.category_id =_ticketcategory.category_id"
                    + " left join _ticketdetailcategory on tickets.category_detail_id =_ticketdetailcategory.detail_category_id"
                    + " left join _ticketdetailsubcategory on tickets.category_subdetail_id =_ticketdetailsubcategory.detail_sub_category_id"
                    + " left join _department a on tickets.dept_id =a.dept_id"
                    + " left join _department b on tickets.assign_dept =b.dept_id"
                    + " left join _ticketpriority on tickets._priority =_ticketpriority.code"
                    + " left join _ticketstatus on tickets._status =_ticketstatus.code"
                    + " left join _relatedproduct on tickets.product_id =_relatedproduct.code"
                    + " where open_date between '"+tic1+"' and '"+tic2+"' ";
            condition="";
//            if(!txtuser.getText().equals("")){
//                condition=condition+" and user like '%"+txtuser.getText()+"%'";
//            }
//            if(!txtcategory.getText().equals("")){
//                condition=condition+" and category_name like '%"+txtcategory.getText()+"%'";
//            }
//            if(!txtdriver.getText().equals("")){
//                condition=condition+" and driver_name like '%"+txtdriver.getText()+"%'";
//            }
//            if(!txtcustomer.getText().equals("")){
//                condition=condition+" and cust_name like '%"+txtcustomer.getText()+"%'";
//            }
//            if(!txtticno.getText().equals("")){
//                condition=condition+" and ticket_no like '%"+txtticno.getText()+"%'";
//            }
            if(!txtticno2.getText().equals("")){
                condition=condition+" and ticket_no like '%"+txtticno2.getText()+"%'";
            }
            if(!cbdept1.getSelectedItem().equals("--")){
                condition=condition+" and tickets.dept_id = '"+cbdept1.getSelectedIndex()+"'";
            }
            if(!cbticstatus1.getSelectedItem().equals("--")){
                if(!cbticstatus1.getSelectedItem().equals("CANCEL")){
                    condition=condition+" and _status = '"+cbticstatus1.getSelectedIndex()+"'";
                }else{
                    condition=condition+" and _status = '-1'";
                }
            }
            if(!cbFollowUp1.getSelectedItem().equals("--")){
                condition=condition+" and follow_up = '"+cbFollowUp1.getSelectedItem()+"'";
            }
            if(ckassign1.isSelected()==true){
                condition=condition+" and assign_dept=0";
            }
            if(ckstoring1.isSelected()==true){
                condition=condition+" and _storing=1";
            }
            if(cksubmit1.isSelected()==true){
                condition=condition+" and _submitted=0";
            }
            if(!txtcus1.getText().equals("")){
                condition=condition+" and cust_name like '%"+txtcus.getText()+"%'";
            }
            if(!cbcate1.getSelectedItem().equals("--")){
                condition=condition+" and category = '"+cbcate.getSelectedItem()+"'";
            }

            sql=sql+condition+" order by ticket_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                reptic[x]=rs.getString(1);x++;
                reptic[x]=rs.getString(2);x++;
                reptic[x]=rs.getString("prior");x++;
                reptic[x]=rs.getString("stt");x++;
                reptic[x]=rs.getString("dept_name").toString();x++;
                reptic[x]=rs.getString("source");x++;
                reptic[x]=rs.getString("media");x++;

                reptic[x]=rs.getString("_type");x++;
                reptic[x]=rs.getString("category");x++;
                reptic[x]=rs.getString("category_detail").toString();x++;
                reptic[x]=rs.getString("category_subdetail");x++;

                reptic[x]=rs.getString("category_reason");x++;
                reptic[x]=rs.getString("prod").toString();x++;
                reptic[x]=rs.getString("cust_id");x++;
                reptic[x]=rs.getString("cust_name");x++;

                reptic[x]=rs.getString("cust_gender");x++;
                reptic[x]=rs.getString("cust_work_phone");x++;
                reptic[x]=rs.getString("cust_mobile_phone");x++;
                reptic[x]=rs.getString("cust_fax");x++;
                reptic[x]=rs.getString("cust_email");x++;


                reptic[x]=rs.getString("cust_address");x++;
                reptic[x]=rs.getString("note");x++;
                reptic[x]=rs.getString("info");x++;
                reptic[x]=rs.getString("solution");x++;

                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;

                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;

                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;


                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;

                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;

                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;

                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("bbb");x++;
                reptic[x]=rs.getString("open_date");x++;
                reptic[x]=rs.getString("open_time");x++;


                reptic[x]=rs.getString("open_duration");x++;
                reptic[x]=rs.getString("open_username");x++;
                reptic[x]=rs.getString("process_date");x++;
                reptic[x]=rs.getString("process_time");x++;
                reptic[x]=rs.getString("process_duration");x++;

                reptic[x]=rs.getString("process_username");x++;
                reptic[x]=rs.getString("pre_close_date");x++;
                reptic[x]=rs.getString("pre_close_time");x++;
                reptic[x]=rs.getString("pre_close_duration");x++;
                reptic[x]=rs.getString("pre_close_username");x++;

                reptic[x]=rs.getString("close_date");x++;
                reptic[x]=rs.getString("close_time");x++;
                reptic[x]=rs.getString("close_duration");x++;
                reptic[x]=rs.getString("close_days");x++;
                reptic[x]=rs.getString("close_username");x++;

                reptic[x]=rs.getString("re_open_date");x++;
                reptic[x]=rs.getString("re_open_time");x++;
                reptic[x]=rs.getString("re_open_duration");x++;
                reptic[x]=rs.getString("re_open_username");x++;
                reptic[x]=rs.getString("re_process_date");x++;

                reptic[x]=rs.getString("re_process_time");x++;
                reptic[x]=rs.getString("re_process_duration");x++;
                reptic[x]=rs.getString("re_close_date");x++;
                reptic[x]=rs.getString("re_close_time");x++;
                reptic[x]=rs.getString("re_close_duration");x++;

                reptic[x]=rs.getString("re_close_days");x++;
                reptic[x]=rs.getString("re_close_username");x++;
                reptic[x]=rs.getString("_submitted");x++;
                tabreptic.addRow(reptic);
                x=0;
                row+=1;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Ticket with number ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" doesn't exsist");
            }
            lblrepticcount.setText(String.valueOf(tabreptic.getRowCount()));            
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepticActionPerformed
public static javax.swing.table.DefaultTableModel getDefaultTabelrepcal(){
    return new javax.swing.table.DefaultTableModel(
            new Object [][]{},
            new String [] {"log_id","log_date","log_time","username","shift"
                    ,"host_addr","line_number","_direction","_callstatus","duration"
                    ,"abandon","wait","Speed of answer","ACW","Inquiry"
                    ,"Detail","Complaint","Detail","Request","Detail"
                    ,"Feedback","Detail","Blankcall","Wrongnumber","Caller Number"
                    ,"Caller Name","Caller Type","Detail Caller Type","Produk","comment"
                    ,"filename","phone_number","ticket_id"}){
            boolean[] canEdit=new boolean[]{
                false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                    return canEdit[columnIndex];
            }
    };
}
    private void btnrepcalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepcalActionPerformed
        // TODO add your handling code here:
        tabrepcal.setRowCount(0);
        Date dt1 =dccal1.getDate();
        Date dt2 =dccal2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal1 = sdf.format(dt1);
        cal2= sdf.format(dt2);
        try{
            int row=0;            
            sql="select log_phone.*"
                    + ", _callstatus.data as cllstatus"
                    + ", shift.data as dshift"
                    + ", _direction.data as ddir"
                    + ", if(_inquiry=1,'YES','NO') as inq"
                    + ", if(_complaint=1,'YES','NO') as comp"
                    + ", if(_request=1,'YES','NO') as req"
                    + ", if(_feedback=1,'YES','NO') as feed"
                    + ", if(_blankcall=1,'YES','NO') as blank"
                    + ", if(_wrongnumber=1,'YES','NO') as wrong"
                    + ", tickets.ticket_no as notic "
                    + " from log_phone"
                    + " left join _callstatus on log_phone._callstatus=_callstatus.code"
                    + " left join tickets on log_phone.ticket_id=tickets.ticket_id"
                    + " join shift on log_phone.shift=shift.code"
                    + " join _direction on log_phone._direction=_direction.code"
                    + " where log_date between '"+cal1+"' and '"+cal2+"' ";
            condition="";
            if(!cbagenirepcal.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenirepcal.getSelectedItem()+"%'";
            }
            if(!cbcaldir.getSelectedItem().equals("--")){
                condition=condition+" and _direction = '"+cbcaldir.getSelectedIndex()+"'";
            }
            if(!cbcaltyperepcal.getSelectedItem().equals("--")){
                condition=condition+" and caller_type like '%"+cbcaltyperepcal.getSelectedItem()+"%'";
            }
            if(!cbcalstat.getSelectedItem().equals("--")){
                if(cbcalstat.getSelectedItem().equals("PHANTOM")){
                    condition=condition+" and _callstatus = '-1'";
                }else{
                    condition=condition+" and _callstatus = '"+cbcalstat.getSelectedIndex()+"'";
                }
            }

            sql=sql+condition+" order by log_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);

            while(rs.next()){
                repcal[x] = rs.getString("log_date"); x += 1;
                repcal[x] = rs.getString("log_time"); x += 1;
                repcal[x] = rs.getString("username"); x += 1;
                repcal[x] = rs.getString("dshift"); x += 1;

                repcal[x] = rs.getString("host_addr"); x += 1;
                repcal[x] = rs.getString("line_number"); x += 1;
                repcal[x] = rs.getString("ddir"); x += 1;
                repcal[x] = rs.getString("cllstatus"); x += 1;
                repcal[x] = rs.getString("duration"); x += 1;

                repcal[x] = rs.getString("abandon"); x += 1;
                repcal[x] = rs.getString("delay"); x += 1;
                repcal[x] = rs.getString("busy"); x += 1;
                repcal[x] = rs.getString("inq"); x += 1;

                repcal[x] = rs.getString("inquiry_detail"); x += 1;
                repcal[x] = rs.getString("comp"); x += 1;
                repcal[x] = rs.getString("complaint_detail"); x += 1;
                repcal[x] = rs.getString("req"); x += 1;
                repcal[x] = rs.getString("request_detail"); x += 1;

                repcal[x] = rs.getString("feed"); x += 1;
                repcal[x] = rs.getString("feedback_detail"); x += 1;
                repcal[x] = rs.getString("blank"); x += 1;
                repcal[x] = rs.getString("wrong"); x += 1;
                repcal[x] = rs.getString("caller_number"); x += 1;
                repcal[x] = rs.getString("caller_name"); x += 1;

                repcal[x] = rs.getString("caller_type"); x += 1;
                repcal[x] = rs.getString("category_detail"); x += 1;
                repcal[x] = rs.getString("inbound_type"); x += 1;
                repcal[x] = rs.getString("comment"); x += 1;
                repcal[x] = rs.getString("filename"); x += 1;

                repcal[x] = rs.getString("phone_number"); x += 1;
                repcal[x] = rs.getString("notic"); x += 1;
                tabrepcal.addRow(repcal);
                x = 0;
                row+=1;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Ticket with number ccasw_ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" doesn't exsist");
            }
            lblrepcalcount.setText(String.valueOf(tabrepcal.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepcalActionPerformed

    private void btnrepsmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepsmsActionPerformed
        // TODO add your handling code here:
        tabrepsms.setRowCount(0);
        Date dt1 =dcsms1.getDate();
        Date dt2 =dcsms2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sms1 = sdf.format(dt1);
        sms2= sdf.format(dt2);
        try{
            int row=0;            
             sql="select log_sms.* , " +
                     "_direction.data as dir," +
                     " tickets.ticket_no as notic" +
                     ",case log_sms._direction" +
                     " when 0 then (select rcvd_status.data from rcvd_status where code=log_sms._status) " +
                     " when 1 then (select send_status.data from send_status where code=log_sms._status)" +
                     " end stt" +
                     " from log_sms" +
                     " join _direction on log_sms._direction=_direction.code" +
                     " left join tickets on log_sms.ticket_id=tickets.ticket_id" +
                     " where sms_date between '"+sms1+"' and '"+sms2+"'";
            condition="";
            if(!cbagenirepcal1.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenirepcal1.getSelectedItem()+"%'";
            }
            if(!cbdirrepsms.getSelectedItem().equals("--")){
                condition=condition+" and _direction = '"+cbdirrepsms.getSelectedIndex()+"'";
            }
            if(!txtsmsstat.getText().equals("")){
                condition=condition+" and _status like '%"+txtsmsstat.getText()+"%'";
            }
            if(!txtsmsticid.getText().equals("")){
                condition=condition+" and ticket_no = '"+txtsmsticid.getText()+"'";
            }

            sql=sql+condition+" order by sms_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);

            while(rs.next()){
                repsms[0]=rs.getString(1);
                repsms[1]=rs.getString(2);
                repsms[2]=rs.getString(3);
                repsms[3]=rs.getString(4);
                repsms[4]=rs.getString("stt");
                repsms[5]=rs.getString("dir");
                repsms[6]=rs.getString(8);
                repsms[7]=rs.getString(9);
                repsms[8]=rs.getString(10);
                repsms[9]=rs.getString("notic");
                repsms[10]=rs.getString(13);
                repsms[11]=rs.getString("direction_type");
                repsms[12]=rs.getString("cust_name");
                tabrepsms.addRow(repsms);
                row+=1;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Sms with number ccasw_ticket "+txtuser.getText()+", categoty "+txtcategory.getText()+", with customer "+txtcustomer.getText()+", with driver "+txtdriver.getText()+" dosen't exsist");
            }
            lblrepsmscount.setText(String.valueOf(tabrepsms.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepsmsActionPerformed

    private void btnrepmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepmailActionPerformed
        // TODO add your handling code here:
        tabrepmail.setRowCount(0);
        Date dt1 =dcmail1.getDate();
        Date dt2 =dcmail2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mail1 = sdf.format(dt1);
        mail2= sdf.format(dt2);
        try{
            int row=0;
            
                sql="select log_mail.*, _direction.data as dir" +
                        ", tickets.ticket_no as notic" +
                        ",case log_mail.direction" +
                        " when 0 then (select rcvd_status.data from rcvd_status where code=log_mail.status) " +
                        " when 1 then (select send_status.data from send_status where code=log_mail.status)" +
                        " end stt" +
                        " from log_mail" +
                        " join _direction on log_mail.direction=_direction.code" +
                        " left join tickets on log_mail.ticket_id=tickets.ticket_id" +
                        " where mail_id is not null and mail_date between '"+mail1+"' and '"+mail2+"'";
            condition="";
            if(!cbagenrepmail.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenrepmail.getSelectedItem()+"%'";
            }
            if(!cbdirmail.getSelectedItem().equals("--")){
                condition=condition+" and direction = '"+cbdirmail.getSelectedIndex()+"'";
            }
            if(!txtmailsub.getText().equals("")){
                condition=condition+" and mail_subject like '%"+txtmailsub.getText()+"%'";
            }
            if(!txtmailticid.getText().equals("")){
                condition=condition+" and ticket_no = '"+txtmailticid.getText()+"'";
            }

            sql=sql+condition+" GROUP by mail_id order by mail_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                repmail[0]=rs.getString(1);
                repmail[1]=rs.getString(2);
                repmail[2]=rs.getString(3);
                repmail[3]=rs.getString(4);
                repmail[4]=rs.getString(5);
                repmail[5]=rs.getString(6);
                repmail[6]=rs.getString(7);
                repmail[7]=rs.getString(8);
                repmail[8]=rs.getString(9);
                repmail[9]=rs.getString(10);
                repmail[10]=rs.getString("stt");
                repmail[11]=rs.getString("notic");
                repmail[12]=rs.getString("dir");
                repmail[13]=rs.getString("direction_type");
                repmail[14]=rs.getString("cust_name");
                tabrepmail.addRow(repmail);
                row+=1;
            }if(row==0){
//                JOptionPane.showMessageDialog(null,"Mail with number subject "+txtmailsub.getText()+", username "+txtmailusr.getText()+", with direction "+txtmaildir.getText()+", with ticekt id "+txtmailticid.getText()+" dosen't exsist");
            }
            lblrepmailcount.setText(String.valueOf(tabrepmail.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepmailActionPerformed

    private void btnrepfaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepfaxActionPerformed
        // TODO add your handling code here:
        tabrepfax.setRowCount(0);
        Date dt1 =dcfax1.getDate();
        Date dt2 =dcfax2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        fax1 = sdf.format(dt1);
        fax2= sdf1.format(dt2);
        sql3="update log_fax set sent_time=null where sent_time like '0000%'";
        jconn.SQLExecute(sql3,conn);
        try{
            int row=0;
            sql="select log_fax.* , " +
                     "_direction.data as dir," +
                     " tickets.ticket_no as notic" +
                     ",case log_fax._direction" +
                     " when 0 then (select rcvd_status.data from rcvd_status where code=log_fax._status) " +
                     " when 1 then (select send_status.data from send_status where code=log_fax._status)" +
                     " end stt" +
                     " from log_fax" +
                     " join _direction on log_fax._direction=_direction.code" +
                     " left join tickets on log_fax.ticket_id=tickets.ticket_id" +
                     " where (sent_time between '"+fax1+"' and '"+fax2+"' or rcvd_time between '"+fax1+"' and '"+fax2+"')";
            condition="";
            if(!cbagenirepfax.getSelectedItem().equals("--")){
                condition=condition+" and username like '%"+cbagenirepfax.getSelectedItem()+"%'";
            }
            if(!cbdirfax.getSelectedItem().equals("--")){
                condition=condition+" and _direction = '"+cbdirfax.getSelectedIndex()+"'";
            }
            if(cbstatusrepfax.getSelectedIndex()!=-1){
                condition=condition+" and _status like '%"+cbstatusrepfax.getSelectedIndex()+"%'";
            }
            if(!txtfaxfinm.getText().equals("")){
                condition=condition+" and doc_name like '%"+txtfaxfinm.getText()+"%'";
            }

            sql=sql+condition+" order by fax_id";
            rs=jconn.SQLExecuteRS(sql, conn);
            System.out.println(sql);

            while(rs.next()){
                repfax[0]=rs.getString(1);
                repfax[1]=rs.getString(2);
                repfax[2]=rs.getString(3);
                repfax[3]=rs.getString(4);
                repfax[4]=rs.getString(5);
                repfax[5]=rs.getString(6);
                repfax[6]=rs.getString(7);
                repfax[7]=rs.getString("stt");
                repfax[8]=rs.getString("dir");
                repfax[9]=rs.getString("notic");
                repfax[10]=rs.getString("cust_name");
                tabrepfax.addRow(repfax);
                row+=1;
            }if(row==0){
                JOptionPane.showMessageDialog(null,"Data Fax doesn't exsist");
            }
            lblrepfaxcount.setText(String.valueOf(tabrepfax.getRowCount()));
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
}//GEN-LAST:event_btnrepfaxActionPerformed

    private void tblrepticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblrepticMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            ccasw_History hic = new ccasw_History();
            hic.setVisible(true);

            Hic.no=Integer.parseInt((String)tblreptic.getValueAt(tblreptic.getSelectedRow(),0));
            Hic.klik2();
        }
    }//GEN-LAST:event_tblrepticMouseClicked

    private void txtcalnotiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcalnotiMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtcalnotiMouseClicked

    private void tblticconfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblticconfMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==2){
            ccasw_OutBound out = new ccasw_OutBound();
            out.setVisible(true);

            Obc.txtnotic.setText((String)tblticconf.getValueAt(tblticconf.getSelectedRow(), 0));
            Obc.txtnotic1.setText((String)tblticconf.getValueAt(tblticconf.getSelectedRow(), 12));
            
            Obc.klik2();
        }
}//GEN-LAST:event_tblticconfMouseClicked

    private void tblactMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblactMouseEntered
        // TODO add your handling code here:
        inbo.start();
    }//GEN-LAST:event_tblactMouseEntered

    private void tblactMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblactMouseExited
        // TODO add your handling code here:
        inbo.stop();
    }//GEN-LAST:event_tblactMouseExited

    private void txtisuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtisuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtisuActionPerformed

    private void btnsmsinsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsinsrchActionPerformed
        // TODO add your handling code here:
        tabelsin();
}//GEN-LAST:event_btnsmsinsrchActionPerformed

    private void btnsmsoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmsoutsrchActionPerformed
        // TODO add your handling code here:
        tabelsou();
}//GEN-LAST:event_btnsmsoutsrchActionPerformed

    private void btnmailinsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailinsrchActionPerformed
        // TODO add your handling code here:
        tabelmin();
}//GEN-LAST:event_btnmailinsrchActionPerformed

    private void btnmailoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmailoutsrchActionPerformed
        // TODO add your handling code here:
        tabelmou();
}//GEN-LAST:event_btnmailoutsrchActionPerformed

    private void btnexportticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportticActionPerformed
        // TODO add your handling code here:
        tabex=tabreptic;
        createexcel();
}//GEN-LAST:event_btnexportticActionPerformed
public static String cuscom,cuscom1,cuscom2,smsid,mailid,faxid;
    private void tblsinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsinMouseClicked
        // TODO add your handling code here:
        int row=0;
        row=tblsin.getSelectedRow();
        if(row>=0){
            txtfrom2.setText(tblsin.getValueAt(row,1).toString());
            txtimsg2.setText(tblsin.getValueAt(row,3).toString());
            smsid=tblsin.getValueAt(row,5).toString();
            cuscom=tblsin.getValueAt(row,6).toString();
            cbcust.setSelectedItem(cuscom);
        }else{
            txtfrom2.setText("");
            txtimsg2.setText("");
            cbcust.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_tblsinMouseClicked

    private void tblsouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsouMouseClicked
        // TODO add your handling code here:
        int row=0;
        row=tblsou.getSelectedRow();
        if(row>=0){
            txtfrom1.setText(tblsou.getValueAt(row,1).toString());
            txtimsg1.setText(tblsou.getValueAt(row,3).toString());
        }else{
            txtfrom1.setText("");
            txtimsg1.setText("");
        }
    }//GEN-LAST:event_tblsouMouseClicked
    
    private void btnticsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnticsrchActionPerformed
        // TODO add your handling code here:
        tabeltic();
}//GEN-LAST:event_btnticsrchActionPerformed


    private void btninsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsrchActionPerformed
        // TODO add your handling code here:
        tabelin();
}//GEN-LAST:event_btninsrchActionPerformed
     
    private void btnoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnoutsrchActionPerformed
        // TODO add your handling code here:
        tabelou();
}//GEN-LAST:event_btnoutsrchActionPerformed

    private void btnexportcallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcallActionPerformed
        // TODO add your handling code here:
        tabex=tabrepcal;
        createexcel();
}//GEN-LAST:event_btnexportcallActionPerformed

    private void btnexportsmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportsmsActionPerformed
        // TODO add your handling code here:
        tabex=tabrepsms;
        createexcel();
}//GEN-LAST:event_btnexportsmsActionPerformed

    private void btnexportmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportmailActionPerformed
        // TODO add your handling code here:
        tabex=tabrepmail;
        createexcel();
}//GEN-LAST:event_btnexportmailActionPerformed

    private void btnsenddeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsenddeptActionPerformed
        // TODO add your handling code here:
        if (asshow==false){
            ccasw_Asdept asd = new ccasw_Asdept();
            asd.setVisible(true);

            Asd.id=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(), tbltic.getTableHeader().getColumnModel().getColumnIndex("Ticket Id")));
            Asd.notic=Integer.parseInt((String)tbltic.getValueAt(tbltic.getSelectedRow(), 0));

            btnsenddept.setEnabled(false);
            asshow=true;
        }        
}//GEN-LAST:event_btnsenddeptActionPerformed

    private void cktglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cktglActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cktglActionPerformed

    private void txtmailnotiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmailnotiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmailnotiActionPerformed

    private void cbcalstatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcalstatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcalstatActionPerformed

    private void cbdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdeptActionPerformed
        // TODO add your handling code here:
        if(!cbdept.getSelectedItem().equals("--")){            
            cbcate.removeAllItems();
            cbcate.addItem("--");
            try {
                sql="select category_name from _ticketcategory where dept_id='"+cbdept.getSelectedIndex()+"'";
                rs1=jconn.SQLExecuteRS(sql,conn);
                while(rs1.next()){
                    cbcate.addItem(rs1.getString(1));
                }
                cbcate.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            cbcate.removeAllItems();
            cbcate.addItem("--");
            cbcate.setEnabled(false);
        }
}//GEN-LAST:event_cbdeptActionPerformed

    private void btnexportmail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportmail1ActionPerformed
        // TODO add your handling code here:
        tabex=tabrepfax;
        createexcel();
    }//GEN-LAST:event_btnexportmail1ActionPerformed

    private void tblfinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblfinMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            int row=0;
            row=tblfin.getSelectedRow();
            lblview.setIcon(new ImageIcon("Z:/localhost/inbox/"+tabfin.getValueAt(row,1).toString()+""));
            faxid=tabfin.getValueAt(row,4).toString();
            cuscom2=tabfin.getValueAt(row,5).toString();
        }

        if(evt.getClickCount()==2){
//            Tampil1();
        }
}//GEN-LAST:event_tblfinMouseClicked

    private void btnfinsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinsrchActionPerformed
        // TODO add your handling code here:
        tabelfin();
}//GEN-LAST:event_btnfinsrchActionPerformed

    private void tblfouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblfouMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            int row=0;
            row=tblfou.getSelectedRow();
            lblview.setIcon(new ImageIcon("Z:/localhost/outbox/"+tabfou.getValueAt(row,2).toString()+""));
        }

        if(evt.getClickCount()==2){
//            Tampil1();
        }
}//GEN-LAST:event_tblfouMouseClicked

    private void btnfoutsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfoutsrchActionPerformed
        // TODO add your handling code here:
        tabelfou();
}//GEN-LAST:event_btnfoutsrchActionPerformed

    private void panelfaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelfaxMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_panelfaxMouseClicked

    private void btnreleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreleaseActionPerformed
        // TODO add your handling code here:
        sql="update user_account set _activity=0 where username= '" +cbagenrelease.getSelectedItem()+ "' limit 1";
        jconn.SQLExecute(sql, conn);
        JOptionPane.showMessageDialog(null, "AGEN "+cbagenrelease.getSelectedItem()+" HAS BEEN RELEASED", "SETTING",JOptionPane.WARNING_MESSAGE);
}//GEN-LAST:event_btnreleaseActionPerformed

    private void cbagenreleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbagenreleaseActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbagenreleaseActionPerformed

    private void tblmsinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmsinMouseClicked
        // TODO add your handling code here:
       if(tblmsin.getSelectedRow()>=0){
            btnreplymsg.setEnabled(true);
            btndelmsg.setEnabled(true);
            msgidin=(String)tblmsin.getValueAt(tblmsin.getSelectedRow(), 5);
            if (tblmsin.getValueAt(tblmsin.getSelectedRow(), 4)==null){
                txtimsg3.setText("");
            }else{
                txtimsg3.setText((String)tblmsin.getValueAt(tblmsin.getSelectedRow(), 4));
            }
            tu = (String)tblmsin.getValueAt(tblmsin.getSelectedRow(), 2);
            if(tblmsin.getValueAt(tblmsin.getSelectedRow(), 3).equals("0")){
                sql="update msg_inbox set _read = 1 where msg_id='"+msgidin+"'";
                jconn.SQLExecute(sql, conn);
                tabelmsin();
                tt--;
            }
        }
}//GEN-LAST:event_tblmsinMouseClicked

    private void btnreplymsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreplymsgActionPerformed
        // TODO add your handling code here:
        ccasw_Mssg mssg = new ccasw_Mssg();
        mssg.setVisible(true);

        Misg.txttu.setText(tu);
}//GEN-LAST:event_btnreplymsgActionPerformed

    private void tblmsouMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmsouMouseClicked
        // TODO add your handling code here:
        if(tblmsou.getSelectedRow()>=0){
            btndelmsg1.setEnabled(true);
            msgidou=(String)tblmsou.getValueAt(tblmsou.getSelectedRow(), 4);
            if (tblmsou.getValueAt(tblmsou.getSelectedRow(), 3)==null){
                txtimsg4.setText("");
            }else{
                txtimsg4.setText((String)tblmsou.getValueAt(tblmsou.getSelectedRow(), 3));
            }
        }
}//GEN-LAST:event_tblmsouMouseClicked

    private void btncomposemsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomposemsgActionPerformed
        // TODO add your handling code here:
        ccasw_Mssg mssg = new ccasw_Mssg();
        mssg.setVisible(true);
}//GEN-LAST:event_btncomposemsgActionPerformed

    private void btndelmsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelmsgActionPerformed
        // TODO add your handling code here:
        sql="update msg_inbox set _erased = 1 where msg_id='"+msgidin+"'";
        jconn.SQLExecute(sql, conn);
}//GEN-LAST:event_btndelmsgActionPerformed

    private void btndelmsg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelmsg1ActionPerformed
        // TODO add your handling code here:
        sql="update msg_outbox set _erased = 1 where msg_id='"+msgidou+"'";
        jconn.SQLExecute(sql, conn);
    }//GEN-LAST:event_btndelmsg1ActionPerformed

    private void ckstoringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckstoringActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_ckstoringActionPerformed

    private void cbticstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbticstatusActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbticstatusActionPerformed
int i;
int InboundCall;
int AnsweredCall ;
int AbandonCall;
int PhantomCall;
int BlankCall;
int Customer;
int Inquiry;
int Non_customer;
int Complain;
int CustDriver;
int CustUser;
int Towing;
int CustPIC;
int CustOther;
int IntANJ;
int IntCC;
int IntCSO;
int IntDriver;
int IntOther;

String TotalInbWait;
String TotalInbDelay;
String TotalInbBusy;
String TotalInbCall;

String AvgInbCall;
String AvgInbBusy;
String ASA ;

int OutAnsweredCall    ;
int OutNotAnsweredCall ;
int OutCall         ;
String TotalOutCall    ;
String AvgOutCall      ;
    //OtherCall       := 0;
int BCustCall       ;
int BDealerCall     ;
int BTechCall       ;
int BHPMCall        ;
int BOtherCall      ;
int OutgoingSMS     ;
String lpad            ;
String hi,ho,dayin1,dayin2,dayou1,dayou2,perfin1,perfin2,perfou1,perfou2;
    private void btnhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhiActionPerformed
        // TODO add your handling code here:
        tabhoin.setRowCount(0);
        Date dt1 =dthi.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        hi = sdf.format(dt1);
        sql="delete from report_inbound";
        jconn.SQLExecute(sql, conn);
        for(i=0;i<=23;i++){
            InboundCall = 0;
            AnsweredCall    = 0;
            AbandonCall     = 0;
            BlankCall       = 0;
            Inquiry         = 0;
            Complain        = 0;


            TotalInbWait    = "0";
            TotalInbDelay   = "0";
            TotalInbBusy    = "0";
            TotalInbCall    = "0";

            AvgInbCall      = "0";
            AvgInbBusy      = "0";
            ASA             = "0";
            try{
                sql="select lpad("+i+",2,00)";
                rs=jconn.SQLExecuteRS(sql, conn);
                while(rs.next()){
                    lpad=String.valueOf(rs.getString(1));
                }
                sql1="select count(username) as total_inbound " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql1, conn);
                while(rs.next()){
                    InboundCall=Integer.parseInt(rs.getString("total_inbound"));
                }
                sql2="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql2, conn);
                while(rs.next()){
                    OutCall=Integer.parseInt(rs.getString("total_outbound"));
                }
                sql3="select count(username) as total_answered " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0 " +
                        "and _callstatus=1";
                rs=jconn.SQLExecuteRS(sql3, conn);
                while(rs.next()){
                    AnsweredCall=Integer.parseInt(rs.getString("total_answered"));
                }
                sql4="select count(username) as total_abandon " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0 " +
                        "and _callstatus=0";
                rs=jconn.SQLExecuteRS(sql4, conn);
                while(rs.next()){
                    AbandonCall=Integer.parseInt(rs.getString("total_abandon"));
                }
                sql5="select ifnull(sec_to_time(sum(time_to_sec(delay))),0) as total_delay " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql5, conn);
                while(rs.next()){
                    TotalInbDelay=String.valueOf(rs.getString("total_delay"));
                }
                sql6="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql6, conn);
                while(rs.next()){
                    TotalInbCall=String.valueOf(rs.getString("total_duration"));
                }
                sql7="select ifnull(sec_to_time(sum(time_to_sec(busy))),0) as total_busy " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql7, conn);
                while(rs.next()){
                    TotalInbBusy=String.valueOf(rs.getString("total_busy"));
                }
                sql8="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_inbound " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql8, conn);
                while(rs.next()){
                    AvgInbCall=String.valueOf(rs.getString("avg_inbound"));
                }
                sql9="select ifnull(sec_to_time(avg(time_to_sec(busy))),0) as avg_busy " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql9, conn);
                while(rs.next()){
                    AvgInbBusy=String.valueOf(rs.getString("avg_busy"));
                }
                sql10="select ifnull(sum(_inquiry),0) as inquiry " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql10, conn);
                while(rs.next()){
                    Inquiry=Integer.parseInt(rs.getString("inquiry"));
                }
                sql11="select ifnull(sum(_complaint),0) as complain " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql11, conn);
                while(rs.next()){
                    Complain=Integer.parseInt(rs.getString("complain"));
                }
                sql12="select ifnull(sum(_blankcall),0) as _blankcall " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql12, conn);
                while(rs.next()){
                    BlankCall=Integer.parseInt(rs.getString("_blankcall"));
                }
                sql14="select ifnull(sec_to_time(avg(time_to_sec(delay))),0) as avg_delay " +
                        "from log_phone " +
                        "where log_date='"+hi+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=0";
                rs=jconn.SQLExecuteRS(sql14, conn);
                while(rs.next()){
                    ASA=String.valueOf(rs.getString("avg_delay"));
                }
            }catch(Exception e){
                System.out.print(e);
            }
            sql13="insert into report_inbound(" +
                    "hour " +
                    ",inb_call_count " +
                    ",abandon_call " +
                    ",answered_call " +
//                    ",total_inb_wait_time " +
                    ",total_inb_delay_time " +
                    ",total_inb_call_time " +
                    ",total_inb_busy_time " +
                    ",asa " +
                    ",avg_inb_call " +
                    ",avg_inb_busy " +
                    ",blank_call " +
//                    ",prank_call " +
                    ",inquiry " +
//                    ",inquiry_transfer " +
                    ",complain " +
//                    ",complain_transfer " +
//                    ",road_assistance " +
//                    ",towing) " +
                    ")values (" +
                    "'"+lpad+":00"+"' " +
                    ",'"+InboundCall+"'" +
                    ",'"+AbandonCall+"'" +
                    ",'"+AnsweredCall+"'" +      
                    ",'"+TotalInbDelay+"'" +
                    ",'"+TotalInbCall+"'" +
                    ",'"+TotalInbBusy+"'" +                    
                    ",'"+ASA+"'" +              
//           +  ','+QuotedStr(FormatDateTime('hh:nn:ss',ASA))
                    ",'"+AvgInbCall+"'" +
                    ",'"+AvgInbBusy+"'" +
                    ",'"+BlankCall+"'" +
                    ",'"+Inquiry+"'" +
                    ",'"+Complain+"'" +
                    ")";
            jconn.SQLExecute(sql13, conn);
        }
        try{
              sql="select * from report_inbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  hoin[0]=rs.getString("hour");
                  hoin[1]=rs.getString("inb_call_count");
                  hoin[2]=rs.getString("answered_call");
                  hoin[3]=rs.getString("abandon_call");
                  hoin[4]=rs.getString("total_inb_call_time");
                  hoin[5]=rs.getString("avg_inb_call");
                  hoin[6]=rs.getString("total_inb_busy_time");
                  hoin[7]=rs.getString("avg_inb_busy");
                  hoin[8]=rs.getString("total_inb_delay_time");
                  hoin[9]=rs.getString("asa");
                  hoin[10]=rs.getString("blank_call");
                  hoin[11]=rs.getString("inquiry");
                  hoin[12]=rs.getString("complain");
                  tabhoin.addRow(hoin);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnhiActionPerformed

    private void btnhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoActionPerformed
        // TODO add your handling code here:
        tabhoou.setRowCount(0);
        Date dt1 =dtho.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ho = sdf.format(dt1);
        sql="delete from report_outbound";
        jconn.SQLExecute(sql, conn);
        for(i=0;i<=23;i++){

            OutCall         = 0;
            TotalOutCall    = "0";
            AvgOutCall      = "0";
            Customer        = 0;
            Non_customer    = 0;
            CustDriver      = 0;
            CustUser        = 0;
            CustPIC         = 0;
            CustOther       = 0;
            IntANJ          = 0;
            IntCC           = 0;
            IntCSO          = 0;
            IntDriver       = 0;
            IntOther        = 0;

//            ASA             = "0";

            try{
                sql="select lpad("+i+",2,00)";
                rs=jconn.SQLExecuteRS(sql, conn);
                while(rs.next()){
                    lpad=String.valueOf(rs.getString(1));
                }
                sql1="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql1, conn);
                while(rs.next()){
                    OutCall=Integer.parseInt(rs.getString("total_outbound"));
                }
                sql2="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql2, conn);
                while(rs.next()){
                    TotalOutCall=String.valueOf(rs.getString("total_duration"));
                }
                sql3="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_outbound " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1";
                rs=jconn.SQLExecuteRS(sql3, conn);
                while(rs.next()){
                    AvgOutCall=String.valueOf(rs.getString("avg_outbound"));
                }
                sql4="select count(caller_type) as customer " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Customer' ";
                rs=jconn.SQLExecuteRS(sql4, conn);
                while(rs.next()){
                    Customer=Integer.parseInt(rs.getString("customer"));
                }
                sql5="select count(caller_type) as noncust " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Non-customer' ";
                rs=jconn.SQLExecuteRS(sql5, conn);
                while(rs.next()){
                    Non_customer=Integer.parseInt(rs.getString("noncust"));
                }
                sql6="select count(caller_type) as cdriver " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-Driver' ";
                rs=jconn.SQLExecuteRS(sql6, conn);
                while(rs.next()){
                    CustDriver=Integer.parseInt(rs.getString("cdriver"));
                }
                sql7="select count(caller_type) as cuser " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-User' ";
                rs=jconn.SQLExecuteRS(sql7, conn);
                while(rs.next()){
                    CustUser=Integer.parseInt(rs.getString("cuser"));
                }
                sql8="select count(caller_type) as cpic " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-PIC' ";
                rs=jconn.SQLExecuteRS(sql8, conn);
                while(rs.next()){
                    CustPIC=Integer.parseInt(rs.getString("cpic"));
                }
                sql9="select count(caller_type) as cother " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-Other' ";
                rs=jconn.SQLExecuteRS(sql9, conn);
                while(rs.next()){
                    CustOther=Integer.parseInt(rs.getString("cother"));
                }
                sql10="select count(caller_type) as ianj " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type = 'Internal-ANJ' ";
                rs=jconn.SQLExecuteRS(sql10, conn);
                while(rs.next()){
                    IntANJ=Integer.parseInt(rs.getString("ianj"));
                }
                sql11="select count(caller_type) as icc " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-CC' ";
                rs=jconn.SQLExecuteRS(sql11, conn);
                while(rs.next()){
                    IntCC=Integer.parseInt(rs.getString("icc"));
                }
                sql12="select count(caller_type) as icso " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-CSO' ";
                rs=jconn.SQLExecuteRS(sql12, conn);
                while(rs.next()){
                    IntCSO=Integer.parseInt(rs.getString("icso"));
                }
                sql13="select count(caller_type) as idriver " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-Driver' ";
                rs=jconn.SQLExecuteRS(sql13, conn);
                while(rs.next()){
                    IntDriver=Integer.parseInt(rs.getString("idriver"));
                }
                sql14="select count(caller_type) as iother " +
                        "from log_phone " +
                        "where log_date='"+ho+"' " +
                        "and log_time like '"+lpad+"%' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-Other' ";
                rs=jconn.SQLExecuteRS(sql14, conn);
                while(rs.next()){
                    IntOther=Integer.parseInt(rs.getString("iother"));
                }

            }catch(Exception e){
                System.out.print(e);
            }
            sql15="insert into report_outbound(" +
                    "hour " +
                    ",out_call_count " +
                    ",total_out_call_time " +
                    ",avg_out_call " +
                    ",customer_count " +
                    ",noncust_count " +
                    ",custdriver_count " +
                    ",custuser_count " +
                    ",custpic_count " +
                    ",custother_count " +
                    ",internalanj_count " +
                    ",internalcc_count " +
                    ",internalcso_count " +
                    ",internaldriver_count " +
                    ",internalother_count " +
                    ")values (" +
                    "'"+lpad+":00"+"' " +
                    ",'"+OutCall+"'" +
                    ",'"+TotalOutCall+"'" +
                    ",'"+AvgOutCall+"'" +
                    ",'"+Customer+"'" +
                    ",'"+Non_customer+"'" +
                    ",'"+CustDriver+"'" +
                    ",'"+CustUser+"'" +
                    ",'"+CustPIC+"'" +
                    ",'"+CustOther+"'" +
                    ",'"+IntANJ+"'" +
                    ",'"+IntCC+"'" +
                    ",'"+IntCSO+"'" +
                    ",'"+IntDriver+"'" +
                    ",'"+IntOther+"'" +
                    ")";
            jconn.SQLExecute(sql15, conn);
        }
        try{
              sql="select * from report_outbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  hoou[0]=rs.getString("hour");
                  hoou[1]=rs.getString("out_call_count");
                  hoou[2]=rs.getString("total_out_call_time");
                  hoou[3]=rs.getString("avg_out_call");
                  hoou[4]=rs.getString("customer_count");
                  hoou[5]=rs.getString("noncust_count");
                  hoou[6]=rs.getString("custdriver_count");
                  hoou[7]=rs.getString("custuser_count");
                  hoou[8]=rs.getString("custpic_count");
                  hoou[9]=rs.getString("custother_count");
                  hoou[10]=rs.getString("internalanj_count");
                  hoou[11]=rs.getString("internalcc_count");
                  hoou[12]=rs.getString("internalcso_count");
                  hoou[13]=rs.getString("internaldriver_count");
                  hoou[14]=rs.getString("internalother_count");
                  tabhoou.addRow(hoou);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnhoActionPerformed

    private void btnpi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpi1ActionPerformed
        // TODO add your handling code here:
        tabperfin.setRowCount(0);
        String tgl;
        String nama;
        Date dt1 =dtpi.getDate();
        Date dt2 =dtpi1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        perfin1 = sdf.format(dt1);
        perfin2 = sdf.format(dt2);
        sql="delete from report_inbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_inbound(date, username) " +
                "select distinct log_date, username from log_phone " +
                "where _direction=0 " +
                "and log_date between '"+perfin1+"' and '"+perfin2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date, username from report_inbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                nama=rs.getString(2);

                sql1="select count(username) as total_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    InboundCall=Integer.parseInt(rs1.getString("total_inbound"));
                }
                sql2="select count(username) as total_phantom " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0 " +
                        "and _callstatus=-1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    PhantomCall=Integer.parseInt(rs1.getString("total_phantom"));
                }
                sql3="select count(username) as total_answered " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0 " +
                        "and _callstatus=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AnsweredCall=Integer.parseInt(rs1.getString("total_answered"));
                }
                sql4="select count(username) as total_abandon " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0 " +
                        "and _callstatus=0";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    AbandonCall=Integer.parseInt(rs1.getString("total_abandon"));
                }
                sql5="select ifnull(sec_to_time(sum(time_to_sec(delay))),0) as total_delay " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    TotalInbDelay=String.valueOf(rs1.getString("total_delay"));
                }
                sql6="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql6, conn);
                while(rs1.next()){
                    TotalInbCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql7="select ifnull(sec_to_time(sum(time_to_sec(busy))),0) as total_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql7, conn);
                while(rs1.next()){
                    TotalInbBusy=String.valueOf(rs1.getString("total_busy"));
                }
                sql8="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    AvgInbCall=String.valueOf(rs1.getString("avg_inbound"));
                }
                sql9="select ifnull(sec_to_time(avg(time_to_sec(busy))),0) as avg_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql9, conn);
                while(rs1.next()){
                    AvgInbBusy=String.valueOf(rs1.getString("avg_busy"));
                }
                sql10="select ifnull(sum(_inquiry),0) as inquiry " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql10, conn);
                while(rs1.next()){
                    Inquiry=Integer.parseInt(rs1.getString("inquiry"));
                }
                sql11="select ifnull(sum(_complaint),0) as complain " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql11, conn);
                while(rs1.next()){
                    Complain=Integer.parseInt(rs1.getString("complain"));
                }
                sql12="select ifnull(sum(_blankcall),0) as _blankcall " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql12, conn);
                while(rs1.next()){
                    BlankCall=Integer.parseInt(rs1.getString("_blankcall"));
                }
                sql15="select ifnull(sec_to_time(avg(time_to_sec(delay))),0) as avg_delay " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql15, conn);
                while(rs1.next()){
                    ASA=String.valueOf(rs1.getString("avg_delay"));
                }
                sql13="update report_inbound set " +
                        "inb_call_count='"+InboundCall+"' " +
                        ",answered_call='"+AnsweredCall+"' " +
                        ",abandon_call='"+AbandonCall+"' " +
                        ",total_inb_delay_time='"+TotalInbDelay+"' " +
                        ",total_inb_call_time='"+TotalInbCall+"' " +
                        ",avg_inb_call='"+AvgInbCall+"' " +
                        ",total_inb_busy_time='"+TotalInbBusy+"' " +
                        ",asa='"+ASA+"' " +
                        ",avg_inb_busy='"+AvgInbBusy+"' " +
                        ",blank_call='"+BlankCall+"' " +
                        ",complain='"+Complain+"' " +
                        ",inquiry='"+Inquiry+"' " +
                        "where date='"+tgl+"' and username='"+nama+"' ";
                jconn.SQLExecute(sql13, conn);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_inbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  perfin[0]=rs.getString("Date");
                  perfin[1]=rs.getString("username");
                  perfin[2]=rs.getString("inb_call_count");
                  perfin[3]=rs.getString("answered_call");
                  perfin[4]=rs.getString("abandon_call");
                  perfin[5]=rs.getString("total_inb_call_time");
                  perfin[6]=rs.getString("avg_inb_call");
                  perfin[7]=rs.getString("total_inb_busy_time");
                  perfin[8]=rs.getString("avg_inb_busy");
                  perfin[9]=rs.getString("total_inb_delay_time");
                  perfin[10]=rs.getString("asa");
                  perfin[11]=rs.getString("blank_call");
                  perfin[12]=rs.getString("complain");
                  perfin[13]=rs.getString("inquiry");
                  tabperfin.addRow(perfin);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnpi1ActionPerformed

    private void btnpo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpo1ActionPerformed
        // TODO add your handling code here:
        tabperfou.setRowCount(0);
        String tgl;
        String nama;
        Date dt1 =dtpo.getDate();
        Date dt2 =dtpo1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        perfou1 = sdf.format(dt1);
        perfou2 = sdf.format(dt2);
        sql="delete from report_outbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_outbound(date, username) " +
                "select distinct log_date, username from log_phone " +
                "where _direction=1 " +
                "and log_date between '"+perfou1+"' and '"+perfou2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date, username from report_outbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                nama=rs.getString(2);

                sql1="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    OutCall=Integer.parseInt(rs1.getString("total_outbound"));
                }
                sql2="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    TotalOutCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql3="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AvgOutCall=String.valueOf(rs1.getString("avg_outbound"));
                }
                sql4="select count(caller_type) as customer " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer' ";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    Customer=Integer.parseInt(rs1.getString("customer"));
                }
                sql5="select count(caller_type) as noncust " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Non-customer' ";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    Non_customer=Integer.parseInt(rs1.getString("noncust"));
                }
                sql6="select count(caller_type) as cdriver " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-Driver' ";
                rs1=jconn.SQLExecuteRS(sql6, conn);
                while(rs1.next()){
                    CustDriver=Integer.parseInt(rs1.getString("cdriver"));
                }
                sql7="select count(caller_type) as cuser " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-User' ";
                rs1=jconn.SQLExecuteRS(sql7, conn);
                while(rs1.next()){
                    CustUser=Integer.parseInt(rs1.getString("cuser"));
                }
                sql8="select count(caller_type) as cpic " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-PIC' ";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    CustPIC=Integer.parseInt(rs1.getString("cpic"));
                }
                sql9="select count(caller_type) as cother " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-Other' ";
                rs1=jconn.SQLExecuteRS(sql9, conn);
                while(rs1.next()){
                    CustOther=Integer.parseInt(rs1.getString("cother"));
                }
                sql10="select count(caller_type) as ianj " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type = 'Internal-ANJ' ";
                rs1=jconn.SQLExecuteRS(sql10, conn);
                while(rs1.next()){
                    IntANJ=Integer.parseInt(rs1.getString("ianj"));
                }
                sql11="select count(caller_type) as icc " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-CC' ";
                rs1=jconn.SQLExecuteRS(sql11, conn);
                while(rs1.next()){
                    IntCC=Integer.parseInt(rs1.getString("icc"));
                }
                sql12="select count(caller_type) as icso " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-CSO' ";
                rs1=jconn.SQLExecuteRS(sql12, conn);
                while(rs1.next()){
                    IntCSO=Integer.parseInt(rs1.getString("icso"));
                }
                sql13="select count(caller_type) as idriver " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-Driver' ";
                rs1=jconn.SQLExecuteRS(sql13, conn);
                while(rs1.next()){
                    IntDriver=Integer.parseInt(rs1.getString("idriver"));
                }
                sql14="select count(caller_type) as iother " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and username='"+nama+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-Other' ";
                rs1=jconn.SQLExecuteRS(sql14, conn);
                while(rs1.next()){
                    IntOther=Integer.parseInt(rs1.getString("iother"));
                }

                sql15="update report_outbound set " +
                        "out_call_count='"+OutCall+"' " +
                        ",total_out_call_time='"+TotalOutCall+"' " +
                        ",avg_out_call='"+AvgOutCall+"' " +
                        ",customer_count='"+Customer+"' " +
                        ",noncust_count='"+Non_customer+"' " +
                        ",custdriver_count='"+CustDriver+"' " +
                        ",custuser_count='"+CustUser+"' " +
                        ",custpic_count='"+CustPIC+"' " +
                        ",custother_count='"+CustOther+"' " +
                        ",internalanj_count='"+IntANJ+"' " +
                        ",internalcc_count='"+IntCC+"' " +
                        ",internalcso_count='"+IntCSO+"' " +
                        ",internaldriver_count='"+IntDriver+"' " +
                        ",internalother_count='"+IntOther+"' " +
                        "where date='"+tgl+"' and username='"+nama+"' ";
                jconn.SQLExecute(sql15, conn);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_outbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  perfou[0]=rs.getString("Date");
                  perfou[1]=rs.getString("username");
                  perfou[2]=rs.getString("out_call_count");
                  perfou[3]=rs.getString("total_out_call_time");
                  perfou[4]=rs.getString("avg_out_call");
                  perfou[5]=rs.getString("customer_count");
                  perfou[6]=rs.getString("noncust_count");
                  perfou[7]=rs.getString("custdriver_count");
                  perfou[8]=rs.getString("custuser_count");
                  perfou[9]=rs.getString("custpic_count");
                  perfou[10]=rs.getString("custother_count");
                  perfou[11]=rs.getString("internalanj_count");
                  perfou[12]=rs.getString("internalcc_count");
                  perfou[13]=rs.getString("internalcso_count");
                  perfou[14]=rs.getString("internaldriver_count");
                  perfou[15]=rs.getString("internalother_count");
                  tabperfou.addRow(perfou);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btnpo1ActionPerformed

    private void btndiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndiActionPerformed
        // TODO add your handling code here:
        tabdayin.setRowCount(0);
        String tgl;
        Date dt1 =dtdi.getDate();
        Date dt2 =dtdi1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dayin1 = sdf.format(dt1);
        dayin2 = sdf.format(dt2);
        sql="delete from report_inbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_inbound(date) " +
                "select distinct log_date from log_phone " +
                "where _direction=0 " +
                "and log_date between '"+dayin1+"' and '"+dayin2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date from report_inbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                System.out.print("\n isi tgl = "+tgl);

                sql1="select count(username) as total_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    InboundCall=Integer.parseInt(rs1.getString("total_inbound"));
                }
                sql2="select count(username) as total_phantom " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0 " +
                        "and _callstatus=-1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    PhantomCall=Integer.parseInt(rs1.getString("total_phantom"));
                }
                sql3="select count(username) as total_answered " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0 " +
                        "and _callstatus=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AnsweredCall=Integer.parseInt(rs1.getString("total_answered"));
                }
                sql4="select count(username) as total_abandon " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0 " +
                        "and _callstatus=0";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    AbandonCall=Integer.parseInt(rs1.getString("total_abandon"));
                }
                sql5="select ifnull(sec_to_time(sum(time_to_sec(delay))),0) as total_delay " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    TotalInbDelay=String.valueOf(rs1.getString("total_delay"));
                }
                sql6="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql6, conn);
                while(rs1.next()){
                    TotalInbCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql7="select ifnull(sec_to_time(sum(time_to_sec(busy))),0) as total_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql7, conn);
                while(rs1.next()){
                    TotalInbBusy=String.valueOf(rs1.getString("total_busy"));
                }
                sql8="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_inbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    AvgInbCall=String.valueOf(rs1.getString("avg_inbound"));
                }
                sql9="select ifnull(sec_to_time(avg(time_to_sec(busy))),0) as avg_busy " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql9, conn);
                while(rs1.next()){
                    AvgInbBusy=String.valueOf(rs1.getString("avg_busy"));
                }
                sql10="select ifnull(sum(_inquiry),0) as inquiry " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql10, conn);
                while(rs1.next()){
                    Inquiry=Integer.parseInt(rs1.getString("inquiry"));
                }
                sql11="select ifnull(sum(_complaint),0) as complain " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql11, conn);
                while(rs1.next()){
                    Complain=Integer.parseInt(rs1.getString("complain"));
                }
                sql12="select ifnull(sum(_blankcall),0) as _blankcall " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql12, conn);
                while(rs1.next()){
                    BlankCall=Integer.parseInt(rs1.getString("_blankcall"));
                }
                sql14="select ifnull(sec_to_time(avg(time_to_sec(delay))),0) as avg_delay " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=0";
                rs1=jconn.SQLExecuteRS(sql14, conn);
                while(rs1.next()){
                    ASA=String.valueOf(rs1.getString("avg_delay"));
                }
                sql13="update report_inbound set " +
                        "inb_call_count='"+InboundCall+"' " +
                        ",answered_call='"+AnsweredCall+"' " +
                        ",abandon_call='"+AbandonCall+"' " +
                        ",total_inb_delay_time='"+TotalInbDelay+"' " +
                        ",total_inb_call_time='"+TotalInbCall+"' " +
                        ",avg_inb_call='"+AvgInbCall+"' " +
                        ",total_inb_busy_time='"+TotalInbBusy+"' " +
                        ",asa='"+ASA+"' " +
                        ",avg_inb_busy='"+AvgInbBusy+"' " +
                        ",blank_call='"+BlankCall+"' " +
                        ",complain='"+Complain+"' " +
                        ",inquiry='"+Inquiry+"' " +
                        "where date='"+tgl+"' ";
                jconn.SQLExecute(sql13, conn);
                System.out.print("\nisi sql13 = "+sql13);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_inbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  dayin[0]=rs.getString("Date");
                  dayin[1]=rs.getString("inb_call_count");
                  dayin[2]=rs.getString("answered_call");
                  dayin[3]=rs.getString("abandon_call");
                  dayin[4]=rs.getString("total_inb_call_time");
                  dayin[5]=rs.getString("avg_inb_call");
                  dayin[6]=rs.getString("total_inb_busy_time");
                  dayin[7]=rs.getString("avg_inb_busy");
                  dayin[8]=rs.getString("total_inb_delay_time");
                  dayin[9]=rs.getString("asa");
                  dayin[10]=rs.getString("blank_call");
                  dayin[11]=rs.getString("complain");
                  dayin[12]=rs.getString("inquiry");
//                  dayin[11]=rs.getString("internalcc_count");
//                  dayin[12]=rs.getString("internalcso_count");
//                  dayin[13]=rs.getString("internaldriver_count");
//                  dayin[14]=rs.getString("internalother_count");
                  tabdayin.addRow(dayin);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }

}//GEN-LAST:event_btndiActionPerformed

    private void btndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndoActionPerformed
        // TODO add your handling code here:
        tabdayou.setRowCount(0);
        String tgl;
        Date dt1 =dtdo.getDate();
        Date dt2 =dtdo1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dayou1 = sdf.format(dt1);
        dayou2 = sdf.format(dt2);
        sql="delete from report_outbound";
        jconn.SQLExecute(sql, conn);

        sql1="insert into report_outbound(date) " +
                "select distinct log_date from log_phone " +
                "where _direction=1 " +
                "and log_date between '"+dayou1+"' and '"+dayou2+"'";
        jconn.SQLExecute(sql1, conn);

        try{
            sql="select date from report_outbound";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                tgl=rs.getString(1);
                System.out.print("\n isi tgl = "+tgl);

                sql1="select count(username) as total_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql1, conn);
                while(rs1.next()){
                    OutCall=Integer.parseInt(rs1.getString("total_outbound"));
                }
                sql2="select ifnull(sec_to_time(sum(time_to_sec(duration))),0) as total_duration " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql2, conn);
                while(rs1.next()){
                    TotalOutCall=String.valueOf(rs1.getString("total_duration"));
                }
                sql3="select ifnull(sec_to_time(avg(time_to_sec(duration))),0) as avg_outbound " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1";
                rs1=jconn.SQLExecuteRS(sql3, conn);
                while(rs1.next()){
                    AvgOutCall=String.valueOf(rs1.getString("avg_outbound"));
                }
                sql4="select count(caller_type) as customer " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer' ";
                rs1=jconn.SQLExecuteRS(sql4, conn);
                while(rs1.next()){
                    Customer=Integer.parseInt(rs1.getString("customer"));
                }
                sql5="select count(caller_type) as noncust " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Non-customer' ";
                rs1=jconn.SQLExecuteRS(sql5, conn);
                while(rs1.next()){
                    Non_customer=Integer.parseInt(rs1.getString("noncust"));
                }
                sql6="select count(caller_type) as cdriver " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-Driver' ";
                rs1=jconn.SQLExecuteRS(sql6, conn);
                while(rs1.next()){
                    CustDriver=Integer.parseInt(rs1.getString("cdriver"));
                }
                sql7="select count(caller_type) as cuser " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-User' ";
                rs1=jconn.SQLExecuteRS(sql7, conn);
                while(rs1.next()){
                    CustUser=Integer.parseInt(rs1.getString("cuser"));
                }
                sql8="select count(caller_type) as cpic " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-PIC' ";
                rs1=jconn.SQLExecuteRS(sql8, conn);
                while(rs1.next()){
                    CustPIC=Integer.parseInt(rs1.getString("cpic"));
                }
                sql9="select count(caller_type) as cother " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Customer-Other' ";
                rs1=jconn.SQLExecuteRS(sql9, conn);
                while(rs1.next()){
                    CustOther=Integer.parseInt(rs1.getString("cother"));
                }
                sql10="select count(caller_type) as ianj " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type = 'Internal-ANJ' ";
                rs1=jconn.SQLExecuteRS(sql10, conn);
                while(rs1.next()){
                    IntANJ=Integer.parseInt(rs1.getString("ianj"));
                }
                sql11="select count(caller_type) as icc " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-CC' ";
                rs1=jconn.SQLExecuteRS(sql11, conn);
                while(rs1.next()){
                    IntCC=Integer.parseInt(rs1.getString("icc"));
                }
                sql12="select count(caller_type) as icso " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-CSO' ";
                rs1=jconn.SQLExecuteRS(sql12, conn);
                while(rs1.next()){
                    IntCSO=Integer.parseInt(rs1.getString("icso"));
                }
                sql13="select count(caller_type) as idriver " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-Driver' ";
                rs1=jconn.SQLExecuteRS(sql13, conn);
                while(rs1.next()){
                    IntDriver=Integer.parseInt(rs1.getString("idriver"));
                }
                sql14="select count(caller_type) as iother " +
                        "from log_phone " +
                        "where log_date='"+tgl+"' " +
                        "and _direction=1 " +
                        "and caller_type='Internal-Other' ";
                rs1=jconn.SQLExecuteRS(sql14, conn);
                while(rs1.next()){
                    IntOther=Integer.parseInt(rs1.getString("iother"));
                }

                sql15="update report_outbound set " +
                        "out_call_count='"+OutCall+"' " +
                        ",total_out_call_time='"+TotalOutCall+"' " +
                        ",avg_out_call='"+AvgOutCall+"' " +
                        ",customer_count='"+Customer+"' " +
                        ",noncust_count='"+Non_customer+"' " +
                        ",custdriver_count='"+CustDriver+"' " +
                        ",custuser_count='"+CustUser+"' " +
                        ",custpic_count='"+CustPIC+"' " +
                        ",custother_count='"+CustOther+"' " +
                        ",internalanj_count='"+IntANJ+"' " +
                        ",internalcc_count='"+IntCC+"' " +
                        ",internalcso_count='"+IntCSO+"' " +
                        ",internaldriver_count='"+IntDriver+"' " +
                        ",internalother_count='"+IntOther+"' " +
                        "where date='"+tgl+"' ";
                jconn.SQLExecute(sql15, conn);
            }
        }catch(Exception e){
            System.out.print(e);
        }

        try{
              sql="select * from report_outbound " ;
              rs = jconn.SQLExecuteRS(sql, conn);
              while(rs.next()){
                  dayou[0]=rs.getString("Date");
                  dayou[1]=rs.getString("out_call_count");
                  dayou[2]=rs.getString("total_out_call_time");
                  dayou[3]=rs.getString("avg_out_call");
                  dayou[4]=rs.getString("customer_count");
                  dayou[5]=rs.getString("noncust_count");
                  dayou[6]=rs.getString("custdriver_count");
                  dayou[7]=rs.getString("custuser_count");
                  dayou[8]=rs.getString("custpic_count");
                  dayou[9]=rs.getString("custother_count");
                  dayou[10]=rs.getString("internalanj_count");
                  dayou[11]=rs.getString("internalcc_count");
                  dayou[12]=rs.getString("internalcso_count");
                  dayou[13]=rs.getString("internaldriver_count");
                  dayou[14]=rs.getString("internalother_count");
                  tabdayou.addRow(dayou);
              }
          }catch(Exception exc){
              System.err.println(exc.getMessage());
          }
}//GEN-LAST:event_btndoActionPerformed

    private void cksubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cksubmitActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cksubmitActionPerformed

    private void pnlscrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlscrollMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            lblscroll.setText("");
            Scrol.stop();
        }
    }//GEN-LAST:event_pnlscrollMouseClicked

    private void btnexportcall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall1ActionPerformed
        // TODO add your handling code here:
        tabex=tabhoin;
        createexcel();
    }//GEN-LAST:event_btnexportcall1ActionPerformed

    private void btnexportcall2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall2ActionPerformed
        // TODO add your handling code here:
        tabex=tabhoou;
        createexcel();
    }//GEN-LAST:event_btnexportcall2ActionPerformed

    private void btnexportcall3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall3ActionPerformed
        // TODO add your handling code here:
        tabex=tabdayin;
        createexcel();
    }//GEN-LAST:event_btnexportcall3ActionPerformed

    private void btnexportcall4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall4ActionPerformed
        // TODO add your handling code here:
        tabex=tabdayou;
        createexcel();
    }//GEN-LAST:event_btnexportcall4ActionPerformed

    private void btnexportcall5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall5ActionPerformed
        // TODO add your handling code here:
        tabex=tabperfin;
        createexcel();
    }//GEN-LAST:event_btnexportcall5ActionPerformed

    private void btnexportcall6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportcall6ActionPerformed
        // TODO add your handling code here:
        tabex=tabperfou;
        createexcel();
    }//GEN-LAST:event_btnexportcall6ActionPerformed

    private void cbcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcustActionPerformed
        // TODO add your handling code here:
        if(cbcust.getSelectedIndex()==-1){
            btncussavesms.setEnabled(false);
        }else if(!cbcust.getSelectedItem().equals(cuscom)){
            btncussavesms.setEnabled(true);
        }else{
            btncussavesms.setEnabled(false);
        }
    }//GEN-LAST:event_cbcustActionPerformed

    private void cbcust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcust1ActionPerformed
        // TODO add your handling code here:
        if(cbcust1.getSelectedIndex()==-1){
            btncussaveEmail.setEnabled(false);
        }else if(!cbcust1.getSelectedItem().equals(cuscom1)){
            btncussaveEmail.setEnabled(true);
        }else{
            btncussaveEmail.setEnabled(false);
        }
    }//GEN-LAST:event_cbcust1ActionPerformed

    private void btncussavesmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncussavesmsActionPerformed
        // TODO add your handling code here:
        btncussavesms.setEnabled(false);
        sql="update log_sms set cust_name='"+cbcust.getSelectedItem()+"'"
                + ", ticket_no='"+txtnoticsms.getText()+"', ticket_id=(select ticket_id from tickets where ticket_no="+txtnoticsms.getText()+")"
                + ", contract_no=(select contract_no from tickets where ticket_no="+txtnoticsms.getText()+")"
                + " where sms_id='"+smsid+"' limit 1";
        jconn.SQLExecute(sql, conn);
        tabelsin();
    }//GEN-LAST:event_btncussavesmsActionPerformed

    private void btncussaveEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncussaveEmailActionPerformed
        // TODO add your handling code here:
        btncussaveEmail.setEnabled(false);
        sql="update log_mail set cust_name='"+cbcust1.getSelectedItem()+"'"
                + ", ticket_no='"+txtnoticmail.getText()+"', ticket_id=(select ticket_id from tickets where ticket_no="+txtnoticmail.getText()+")"
                + ", contract_no=(select contract_no from tickets where ticket_no="+txtnoticmail.getText()+")"
                + " where mail_id='"+mailid+"' limit 1";
        jconn.SQLExecute(sql, conn);
        tabelmin();
    }//GEN-LAST:event_btncussaveEmailActionPerformed

    private void btncussaveFaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncussaveFaxActionPerformed
        // TODO add your handling code here:
        btncussaveFax.setEnabled(false);
        sql="update log_fax set cust_name='"+cbcust2.getSelectedItem()+"'"
                + ", ticket_no='"+txtnoticfax.getText()+"', ticket_id=(select ticket_id from tickets where ticket_no="+txtnoticfax.getText()+")"
                + ", contract_no=(select contract_no from tickets where ticket_no="+txtnoticfax.getText()+")"
                + " where fax_id='"+faxid+"' limit 1";
        jconn.SQLExecute(sql, conn);
        tabelfin();
    }//GEN-LAST:event_btncussaveFaxActionPerformed

    private void cbcust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcust2ActionPerformed
        // TODO add your handling code here:
        if(cbcust2.getSelectedIndex()==-1){
            btncussaveFax.setEnabled(false);
        }else if(!cbcust2.getSelectedItem().equals(cuscom2)){
            btncussaveFax.setEnabled(true);
        }else{
            btncussaveFax.setEnabled(false);
        }
    }//GEN-LAST:event_cbcust2ActionPerformed

    private void dtmsiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmsiPropertyChange
        // TODO add your handling code here:
        tabelmsin();
    }//GEN-LAST:event_dtmsiPropertyChange

    private void dtmsi1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmsi1PropertyChange
        // TODO add your handling code here:
        tabelmsin();
    }//GEN-LAST:event_dtmsi1PropertyChange

    private void dtmsoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmsoPropertyChange
        // TODO add your handling code here:
        tabelmsou();
    }//GEN-LAST:event_dtmsoPropertyChange

    private void dtmso1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtmso1PropertyChange
        // TODO add your handling code here:
        tabelmsou();
    }//GEN-LAST:event_dtmso1PropertyChange
String nmfile, fullnmfile, nmfile1, fullnmfile1;
    private void btnAttachmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachmentActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser(att);
        chooser.setSelectedFile(new File(att));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            nmfile=(chooser.getSelectedFile().getName().toString());
            System.out.print("\nnamafile: " + nmfile + "\n");
            fullnmfile=(chooser.getSelectedFile().getAbsolutePath());
            s = "DOWNLOAD|EMAIL|"+mailid+"|0|"+att+"|"+fullnmfile+"|"+Log.ftpserver+"|"+Log.ftpuser+"\r\n";
            kirimUplo();
        }
}//GEN-LAST:event_btnAttachmentActionPerformed
String att,att1;
Object sel1,sel2;
    private void jList2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseReleased
        // TODO add your handling code here:
        att="";
        int selectedIx = jList2.getSelectedIndex();
        sel1 = jList2.getModel().getElementAt(selectedIx);
        att=String.valueOf(sel1);
        btnAttachment.setEnabled(true);
}//GEN-LAST:event_jList2MouseReleased

    private void jList3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MouseReleased
        // TODO add your handling code here:
        att1="";
        int selectedIx = jList3.getSelectedIndex();
        sel2 = jList3.getModel().getElementAt(selectedIx);
        att1=String.valueOf(sel2);
        btnAttachment1.setEnabled(true);
}//GEN-LAST:event_jList3MouseReleased

    private void btnAttachment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachment1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser(att1);
        chooser.setSelectedFile(new File(att1));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            nmfile1=(chooser.getSelectedFile().getName().toString());
            fullnmfile1=(chooser.getSelectedFile().getAbsolutePath());
            s = "DOWNLOAD|EMAIL|"+mailid+"|1|"+att1+"|"+fullnmfile1+"|"+Log.ftpserver+"|"+Log.ftpuser+"\r\n";
            kirimUplo();
        }
}//GEN-LAST:event_btnAttachment1ActionPerformed

    private void cbcateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcateActionPerformed

    private void btnreadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreadyActionPerformed
        // TODO add your handling code here:
        dataregis();
        if (counter==0){
            if(cbdirection.getSelectedItem().equals("INBOUND")){
                btnready.setText("Unregis");
                lblactivity.setText("Registered");
                cbdirection.setEnabled(false);
                btnlogout.setEnabled(false);
                s = "REGISTER|"+pabx+"|"+in_ext+"|"+in_ext+"\r\n";
                kirimTele();
                counter++;
                receiv.start();
                sql3="update user_account set _activity=4, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
                jconn.SQLExecute(sql3, conn);
            }else{
                btnready.setText("Unregis");
                lblactivity.setText("Registered");
                btnoutbound.setEnabled(true);
                cbdirection.setEnabled(false);
                btnlogout.setEnabled(false);
                s = "REGISTER|"+pabx+"|"+out_ext+"|"+out_ext+"\r\n";
                kirimTele();
                counter++;
                outbound=true;
            }
        }else{
           if(cbdirection.getSelectedItem().equals("INBOUND")){
                btnready.setText("Ready");
                lblactivity.setText("Disconnected");
                btncall.setEnabled(false);
                cbdirection.setEnabled(true);
                btnlogout.setEnabled(true);
                s = "UNREGISTER|"+pabx+"|"+in_ext+"|"+in_ext+"\r\n";
                kirimTele();
                counter=0;
                sql3="update user_account set _activity=1, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
                jconn.SQLExecute(sql3, conn);
           }else{
                btnready.setText("Ready");
                lblactivity.setText("Disconnected");
                cbdirection.setEnabled(true);
                btnlogout.setEnabled(true);
                s = "UNREGISTER|"+pabx+"|"+out_ext+"|"+out_ext+"\r\n";
                kirimTele();
                counter=0;
                outbound=false;
                if(txtcalnoti.getText().equals("")){
                    btnoutbound.setEnabled(false);
                }
           }
        }
    }//GEN-LAST:event_btnreadyActionPerformed

    private void cbFollowUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFollowUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFollowUpActionPerformed

    private void ckstoring1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckstoring1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckstoring1ActionPerformed

    private void cbticstatus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbticstatus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbticstatus1ActionPerformed

    private void cbdept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdept1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbdept1ActionPerformed

    private void cksubmit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cksubmit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cksubmit1ActionPerformed

    private void cbcate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcate1ActionPerformed

    private void cbFollowUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFollowUp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFollowUp1ActionPerformed

    private void createexcel(){
        int koltab=0;
        int counter=1;
        int kolom=0;
        int baris=0;
        int k;
        int b;

        JFileChooser chooser = new JFileChooser("");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try{

                //Membuat workbook baru dengan nama laporan.xls
                WritableWorkbook workBook = Workbook.createWorkbook(new File(chooser.getSelectedFile().getAbsolutePath()+".xls"));


                 //Membuat sheet dengan nama Sheet pertama
                 WritableSheet sheet = workBook.createSheet("First sheet ",0);
                 System.out.print("\n debug : pembuatan header");

            Label label;
            for(int q=0;q<tabex.getColumnCount();q++){
                label = new Label(q,0,(tabex.getColumnName(kolom).toString()));
                kolom++;
                sheet.addCell(label);
            }

            k=tabex.getColumnCount();
                k-=1;
            b=tabex.getRowCount();
    //            b+=1;

           while(counter<=b){
               if (koltab>=k){
                   koltab=0;
               }
               while(koltab<=k){
                   if(kolom>k){
                       kolom=0;
                   }
    //               System.out.print(tblreptic.getValueAt(baris, kolom));
                   if (tabex.getValueAt(baris, kolom)==null){
                       label = new Label(koltab,counter,"");
                   }else{
                       label = new Label(koltab,counter,(tabex.getValueAt(baris, kolom).toString()));
                   }
                   koltab++;
                   kolom++;

                   sheet.addCell(label);
               }
               counter++;
               baris++;
           }
            workBook.write();
            workBook.close();
            JOptionPane.showMessageDialog(null, "SUCCESSED EXPORTING TO EXCEL", "REPORTING",JOptionPane.INFORMATION_MESSAGE);


            } catch (WriteException ex) {
                System.out.print(ex);
            } catch (IOException ex) {
                System.out.print(ex);
            }
           catch (Exception ex){
               ex.printStackTrace();
           }
        }
    }
            
       public static javax.swing.table.DefaultTableModel getDefaultTabelticconf(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Ticket No.","Confirm username","Status","Category","Assign Dept.","Assign User","Customer","Phone number","Username","No. Plat","Type","Driver","Phone","Ticket Id"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
                
        public static javax.swing.table.DefaultTableModel getDefaultTabelrepsms(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Sms Id","Sms Date","Sms Time","Username","Status","Direction","Sms From","Sms To","Sms Text","Ticket Id",/*"ref_no","rpt_code",*/"Retry Count","Destination Type","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
        public static javax.swing.table.DefaultTableModel getDefaultTabelrepmail(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Mail Id","Mail Date","Mail Time","Mail From","Mail To","Mail Cc","Mail Subject","Mail Text","Mail Read","Username","Status","Ticket Id","Direction","Destination Type","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
        public static javax.swing.table.DefaultTableModel getDefaultTabelrepfax(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"fax_id","recipient","sender","username","doc_name","sent_time","rcvd_time","_status","_direction","ticket no","Cust Company"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
        public static javax.swing.table.DefaultTableModel getDefaultTabelact(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Username","Level","Activity","Info","Login","Host address","Line number"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }   
    
    public static javax.swing.table.DefaultTableModel getDefaultTabelmsin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","From","Read","Message",""}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelmsou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Time","Recipient","Message",""}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelhoin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Hour","Total Receive","Answered","Abandoned","Call Duration","Avg Call Duration"
                        ,"ACW Time","Avg ACW Time","Total S.A.","A.S.A.","Blank Call"
                        ,"Complain","Inquiry"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelhoou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Hour","Outgoing Call","Outbound Call Time","Avg Outbound Call Time","Customer"
                        ,"Non-customer","Customer-Driver","Customer-User","Customer-PIC","Customer-Other"
                        ,"Internal-ANJ","Internal-CC","Internal-CSO","Internal-Driver","Internal-Other"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabeldayin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Total Receive","Answered","Abandoned"/*,"Phantom"*/,"Call Duration","Avg Call Duration"
                        ,"ACW Time","Avg ACW Time","Total S.A.","A.S.A.","Blank Call"
                        ,"Complain","Inquiry"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabeldayou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Outgoing Call","Outbound Call Time","Avg Outbound Call Time","Customer"
                        ,"Non-customer","Customer-Driver","Customer-User","Customer-PIC","Customer-Other"
                        ,"Internal-ANJ","Internal-CC","Internal-CSO","Internal-Driver","Internal-Other"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelperfin(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Agent","Total Receive","Answered","Abandoned","Call Duration","Avg Call Duration"
                        ,"ACW Time","Avg ACW Time","Total S.A.","A.S.A.","Blank Call"
                        ,"Complain","Inquiry"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
    public static javax.swing.table.DefaultTableModel getDefaultTabelperfou(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"Date","Agent","Outgoing Call","Outbound Call Time","Avg Outbound Call Time","Customer"
                        ,"Non-customer","Customer-Driver","Customer-User","Customer-PIC","Customer-Other"
                        ,"Internal-ANJ","Internal-CC","Internal-CSO","Internal-Driver","Internal-Other"}){
                boolean[] canEdit=new boolean[]{
                    false,false,false,false,false,false
                            ,false,false,false,false,false
                            ,false,false,false,false,false
                };
                public boolean isCellEditable(int rowIndex, int columnIndex){
                        return canEdit[columnIndex];
                }
        };
    }
       private static void appendToChatBox(String s) {
         synchronized (toAppend) {
             toAppend.append(s);
      }
   }
       private static void sendString(String s) {
         synchronized (toSend) {
             toSend.append(s + "\r\n");
      }
   }
       private static void sending(String pik) {
         synchronized (toSend) {
             toSend.append(pik + "\r\n");
      }
   }
       private static void angkat(String pick) {
         synchronized (toSend) {
             toSend.append(pick + "\r\n");
      }
   }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactCenterASWATA().setVisible(true);
            }
        });
    }

    public static javax.swing.table.DefaultTableModel tabex;
    public static javax.swing.table.DefaultTableModel tabin=getDefaultTabelin();
    public static javax.swing.table.DefaultTableModel tabou=getDefaultTabelout();
    public static javax.swing.table.DefaultTableModel tabtic=getDefaultTabeltic();
    public static javax.swing.table.DefaultTableModel tabticconf=getDefaultTabelticconf();
    public static javax.swing.table.DefaultTableModel tabreptic=getDefaultTabelreptic();
    public static javax.swing.table.DefaultTableModel tabrepcal=getDefaultTabelrepcal();
    public static javax.swing.table.DefaultTableModel tabrepsms=getDefaultTabelrepsms();
    public static javax.swing.table.DefaultTableModel tabrepmail=getDefaultTabelrepmail();
    public static javax.swing.table.DefaultTableModel tabrepfax=getDefaultTabelrepfax();
    public static javax.swing.table.DefaultTableModel tabact=getDefaultTabelact();
    public static javax.swing.table.DefaultTableModel tabmin=getDefaultTabelmin();
    public static javax.swing.table.DefaultTableModel tabmou=getDefaultTabelmout();
    public static javax.swing.table.DefaultTableModel tabsin=getDefaultTabelsin();
    public static javax.swing.table.DefaultTableModel tabsou=getDefaultTabelsou();
    public static javax.swing.table.DefaultTableModel tabfin=getDefaultTabelfin();
    public static javax.swing.table.DefaultTableModel tabfou=getDefaultTabelfou();
    public static javax.swing.table.DefaultTableModel tabmsin=getDefaultTabelmsin();
    public static javax.swing.table.DefaultTableModel tabmsou=getDefaultTabelmsou();
    public static javax.swing.table.DefaultTableModel tabhoin=getDefaultTabelhoin();
    public static javax.swing.table.DefaultTableModel tabhoou=getDefaultTabelhoou();
    public static javax.swing.table.DefaultTableModel tabdayin=getDefaultTabeldayin();
    public static javax.swing.table.DefaultTableModel tabdayou=getDefaultTabeldayou();
    public static javax.swing.table.DefaultTableModel tabperfin=getDefaultTabelperfin();
    public static javax.swing.table.DefaultTableModel tabperfou=getDefaultTabelperfou();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAttachment;
    public static javax.swing.JButton btnAttachment1;
    public static javax.swing.JButton btncall;
    private javax.swing.JButton btncomposemsg;
    private javax.swing.JButton btncussaveEmail;
    private javax.swing.JButton btncussaveFax;
    private javax.swing.JButton btncussavesms;
    private javax.swing.JButton btndelmsg;
    private javax.swing.JButton btndelmsg1;
    private javax.swing.JButton btndi;
    private javax.swing.JButton btndo;
    private javax.swing.JButton btnexportcall;
    private javax.swing.JButton btnexportcall1;
    private javax.swing.JButton btnexportcall2;
    private javax.swing.JButton btnexportcall3;
    private javax.swing.JButton btnexportcall4;
    private javax.swing.JButton btnexportcall5;
    private javax.swing.JButton btnexportcall6;
    private javax.swing.JButton btnexportmail;
    private javax.swing.JButton btnexportmail1;
    private javax.swing.JButton btnexportsms;
    private javax.swing.JButton btnexporttic;
    public static javax.swing.JButton btnfax;
    private javax.swing.JButton btnfinsrch;
    private javax.swing.JButton btnfoutsrch;
    private javax.swing.JButton btnhi;
    private javax.swing.JButton btnho;
    private javax.swing.JButton btninsrch;
    public static javax.swing.JButton btnlogout;
    public static javax.swing.JButton btnmail;
    private javax.swing.JButton btnmailinsrch;
    private javax.swing.JButton btnmailoutsrch;
    public static javax.swing.JButton btnoutbound;
    private javax.swing.JButton btnoutsrch;
    private javax.swing.JButton btnpi1;
    private javax.swing.JButton btnpo1;
    public static javax.swing.JButton btnready;
    private javax.swing.JButton btnrelease;
    private javax.swing.JButton btnrepcal;
    private javax.swing.JButton btnrepfax;
    private javax.swing.JButton btnreplymsg;
    private javax.swing.JButton btnrepmail;
    private javax.swing.JButton btnrepsms;
    private javax.swing.JButton btnreptic;
    public static javax.swing.JButton btnsenddept;
    public static javax.swing.JButton btnsms;
    private javax.swing.JButton btnsmsinsrch;
    private javax.swing.JButton btnsmsoutsrch;
    private javax.swing.JButton btnticsrch;
    private javax.swing.JComboBox cbFollowUp;
    private javax.swing.JComboBox cbFollowUp1;
    public static javax.swing.JComboBox cbagenin;
    private javax.swing.JComboBox cbagenirepcal;
    private javax.swing.JComboBox cbagenirepcal1;
    private javax.swing.JComboBox cbagenirepfax;
    private javax.swing.JComboBox cbagenou;
    private javax.swing.JComboBox cbagenrelease;
    private javax.swing.JComboBox cbagenrepmail;
    private javax.swing.JComboBox cbcaldir;
    private javax.swing.JComboBox cbcalstat;
    public static javax.swing.JComboBox cbcalstatin;
    private javax.swing.JComboBox cbcaltyperepcal;
    private javax.swing.JComboBox cbcate;
    private javax.swing.JComboBox cbcate1;
    public static javax.swing.JComboBox cbcust;
    public static javax.swing.JComboBox cbcust1;
    public static javax.swing.JComboBox cbcust2;
    private javax.swing.JComboBox cbdept;
    private javax.swing.JComboBox cbdept1;
    public static javax.swing.JComboBox cbdirection;
    private javax.swing.JComboBox cbdirfax;
    private javax.swing.JComboBox cbdirmail;
    private javax.swing.JComboBox cbdirrepsms;
    private javax.swing.JComboBox cbstatusrepfax;
    private javax.swing.JComboBox cbticstatus;
    private javax.swing.JComboBox cbticstatus1;
    private javax.swing.JCheckBox ckassign;
    private javax.swing.JCheckBox ckassign1;
    private javax.swing.JCheckBox ckstoring;
    private javax.swing.JCheckBox ckstoring1;
    private javax.swing.JCheckBox cksubmit;
    private javax.swing.JCheckBox cksubmit1;
    private javax.swing.JCheckBox cktgl;
    private com.toedter.calendar.JDateChooser dccal1;
    private com.toedter.calendar.JDateChooser dccal2;
    private com.toedter.calendar.JDateChooser dcfax1;
    private com.toedter.calendar.JDateChooser dcfax2;
    private com.toedter.calendar.JDateChooser dcmail1;
    private com.toedter.calendar.JDateChooser dcmail2;
    private com.toedter.calendar.JDateChooser dcsms1;
    private com.toedter.calendar.JDateChooser dcsms2;
    private com.toedter.calendar.JDateChooser dctic1;
    private com.toedter.calendar.JDateChooser dctic2;
    public static com.toedter.calendar.JDateChooser dctic3;
    public static com.toedter.calendar.JDateChooser dctic4;
    public static com.toedter.calendar.JDateChooser dctic5;
    public static com.toedter.calendar.JDateChooser dctic6;
    public static com.toedter.calendar.JDateChooser dctic7;
    public static com.toedter.calendar.JDateChooser dctic8;
    private com.toedter.calendar.JDateChooser dtdi;
    private com.toedter.calendar.JDateChooser dtdi1;
    private com.toedter.calendar.JDateChooser dtdo;
    private com.toedter.calendar.JDateChooser dtdo1;
    public static com.toedter.calendar.JDateChooser dtfi;
    public static com.toedter.calendar.JDateChooser dtfi1;
    public static com.toedter.calendar.JDateChooser dtfo;
    public static com.toedter.calendar.JDateChooser dtfo1;
    private com.toedter.calendar.JDateChooser dthi;
    private com.toedter.calendar.JDateChooser dtho;
    public static com.toedter.calendar.JDateChooser dtmi;
    public static com.toedter.calendar.JDateChooser dtmi1;
    public static com.toedter.calendar.JDateChooser dtmo;
    public static com.toedter.calendar.JDateChooser dtmo1;
    public static com.toedter.calendar.JDateChooser dtmsi;
    public static com.toedter.calendar.JDateChooser dtmsi1;
    public static com.toedter.calendar.JDateChooser dtmso;
    public static com.toedter.calendar.JDateChooser dtmso1;
    private com.toedter.calendar.JDateChooser dtpi;
    private com.toedter.calendar.JDateChooser dtpi1;
    private com.toedter.calendar.JDateChooser dtpo;
    private com.toedter.calendar.JDateChooser dtpo1;
    public static com.toedter.calendar.JDateChooser dtsi;
    public static com.toedter.calendar.JDateChooser dtsi1;
    public static com.toedter.calendar.JDateChooser dtso;
    public static com.toedter.calendar.JDateChooser dtso1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    public static javax.swing.JList jList2;
    public static javax.swing.JList jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane48;
    private javax.swing.JScrollPane jScrollPane49;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane50;
    private javax.swing.JScrollPane jScrollPane51;
    private javax.swing.JScrollPane jScrollPane52;
    private javax.swing.JScrollPane jScrollPane53;
    private javax.swing.JScrollPane jScrollPane54;
    private javax.swing.JScrollPane jScrollPane55;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane58;
    private javax.swing.JScrollPane jScrollPane59;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane60;
    private javax.swing.JScrollPane jScrollPane61;
    private javax.swing.JScrollPane jScrollPane62;
    private javax.swing.JScrollPane jScrollPane63;
    private javax.swing.JScrollPane jScrollPane64;
    private javax.swing.JScrollPane jScrollPane65;
    private javax.swing.JScrollPane jScrollPane66;
    private javax.swing.JScrollPane jScrollPane67;
    private javax.swing.JScrollPane jScrollPane68;
    private javax.swing.JScrollPane jScrollPane69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane70;
    private javax.swing.JScrollPane jScrollPane71;
    private javax.swing.JScrollPane jScrollPane72;
    private javax.swing.JScrollPane jScrollPane73;
    private javax.swing.JScrollPane jScrollPane74;
    private javax.swing.JScrollPane jScrollPane75;
    private javax.swing.JScrollPane jScrollPane76;
    private javax.swing.JScrollPane jScrollPane77;
    private javax.swing.JScrollPane jScrollPane78;
    private javax.swing.JScrollPane jScrollPane79;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane80;
    private javax.swing.JScrollPane jScrollPane81;
    private javax.swing.JScrollPane jScrollPane82;
    private javax.swing.JScrollPane jScrollPane83;
    private javax.swing.JScrollPane jScrollPane84;
    private javax.swing.JScrollPane jScrollPane85;
    private javax.swing.JScrollPane jScrollPane86;
    private javax.swing.JScrollPane jScrollPane87;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane11;
    private javax.swing.JTabbedPane jTabbedPane12;
    private javax.swing.JTabbedPane jTabbedPane13;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTabbedPane jTabbedPane9;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTextArea jTextArea12;
    private javax.swing.JTextArea jTextArea13;
    private javax.swing.JTextArea jTextArea14;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextArea16;
    private javax.swing.JTextArea jTextArea17;
    private javax.swing.JTextArea jTextArea18;
    private javax.swing.JTextArea jTextArea19;
    private javax.swing.JTextArea jTextArea20;
    private javax.swing.JTextArea jTextArea21;
    private javax.swing.JTextArea jTextArea22;
    private javax.swing.JTextArea jTextArea23;
    private javax.swing.JTextArea jTextArea24;
    private javax.swing.JTextArea jTextArea25;
    private javax.swing.JTextArea jTextArea26;
    private javax.swing.JTextArea jTextArea27;
    private javax.swing.JTextArea jTextArea28;
    private javax.swing.JTextArea jTextArea29;
    private javax.swing.JTextArea jTextArea30;
    private javax.swing.JTextArea jTextArea31;
    private javax.swing.JTextArea jTextArea32;
    private javax.swing.JTextArea jTextArea33;
    private javax.swing.JTextArea jTextArea34;
    private javax.swing.JTextArea jTextArea35;
    private javax.swing.JTextArea jTextArea36;
    private javax.swing.JTextArea jTextArea37;
    private javax.swing.JTextArea jTextArea38;
    private javax.swing.JTextArea jTextArea39;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea40;
    private javax.swing.JTextArea jTextArea41;
    private javax.swing.JTextArea jTextArea42;
    private javax.swing.JTextArea jTextArea43;
    private javax.swing.JTextArea jTextArea44;
    private javax.swing.JTextArea jTextArea45;
    private javax.swing.JTextArea jTextArea46;
    private javax.swing.JTextArea jTextArea47;
    private javax.swing.JTextArea jTextArea48;
    private javax.swing.JTextArea jTextArea49;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea50;
    private javax.swing.JTextArea jTextArea51;
    private javax.swing.JDesktopPane jdp;
    private javax.swing.JTabbedPane jtab;
    public static javax.swing.JLabel lblactivity;
    private static javax.swing.JLabel lblcalincount;
    private static javax.swing.JLabel lblcaloutcount;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbllogo;
    public static javax.swing.JLabel lblpas;
    private javax.swing.JLabel lblrepcalcount;
    private javax.swing.JLabel lblrepfaxcount;
    private javax.swing.JLabel lblrepmailcount;
    private javax.swing.JLabel lblrepsmscount;
    private javax.swing.JLabel lblrepticcount;
    private javax.swing.JLabel lblrepticcount1;
    private javax.swing.JLabel lblrepticcount10;
    private javax.swing.JLabel lblrepticcount11;
    private javax.swing.JLabel lblrepticcount12;
    private javax.swing.JLabel lblrepticcount3;
    private javax.swing.JLabel lblrepticcount5;
    private javax.swing.JLabel lblrepticcount7;
    private javax.swing.JLabel lblrepticcount9;
    private javax.swing.JLabel lblscroll;
    public static javax.swing.JLabel lblshift;
    public static javax.swing.JLabel lblshift1;
    private static javax.swing.JLabel lblticcount;
    public static javax.swing.JLabel lbluser;
    public static javax.swing.JLabel lblview;
    public static javax.swing.JLabel lblview1;
    private javax.swing.JTabbedPane panelfax;
    private javax.swing.JTabbedPane panelmail;
    private javax.swing.JTabbedPane panelsms;
    private javax.swing.JPanel pninbox;
    private javax.swing.JPanel pninbox1;
    private javax.swing.JPanel pnlact;
    private javax.swing.JPanel pnlinbon;
    private javax.swing.JTabbedPane pnlinf;
    private javax.swing.JPanel pnlou;
    private javax.swing.JPanel pnlrep;
    private javax.swing.JPanel pnlrep1;
    private javax.swing.JPanel pnlrep2;
    private javax.swing.JPanel pnlrep3;
    private javax.swing.JPanel pnlrep4;
    private javax.swing.JPanel pnlscroll;
    private javax.swing.JPanel pnltic;
    private javax.swing.JPanel pnoutbox;
    private javax.swing.JPanel pnoutbox1;
    private javax.swing.JScrollPane scpCcList1;
    private javax.swing.JScrollPane scpCcList2;
    private javax.swing.JTabbedPane tabbpanereport;
    private javax.swing.JTable tblact;
    private javax.swing.JTable tbldailyin;
    private javax.swing.JTable tbldailyout;
    private javax.swing.JTable tblfin;
    private javax.swing.JTable tblfou;
    private javax.swing.JTable tblhourin;
    private javax.swing.JTable tblhourout;
    public static javax.swing.JTable tblin;
    private javax.swing.JTable tblmin;
    private javax.swing.JTable tblmou;
    private javax.swing.JTable tblmsin;
    private javax.swing.JTable tblmsou;
    private javax.swing.JTable tblout;
    private javax.swing.JTable tblperformin;
    private javax.swing.JTable tblperformout;
    private javax.swing.JTable tblrepcal;
    private javax.swing.JTable tblrepfax;
    private javax.swing.JTable tblrepmail;
    private javax.swing.JTable tblrepsms;
    private javax.swing.JTable tblreptic;
    private javax.swing.JTable tblsin;
    private javax.swing.JTable tblsou;
    public static javax.swing.JTable tbltic;
    private javax.swing.JTable tblticconf;
    public static javax.swing.JTextField txtcalnoti;
    private javax.swing.JTextField txtcus;
    private javax.swing.JTextField txtcus1;
    private javax.swing.JTextArea txtdetail;
    private javax.swing.JTextField txtdriv;
    private javax.swing.JTextField txtdriv1;
    private javax.swing.JTextField txtdrivcode;
    private javax.swing.JTextField txtdrivcode1;
    private javax.swing.JTextField txtfaxfinm;
    public static javax.swing.JTextField txtfaxnoti;
    private javax.swing.JTextField txtfrom;
    private javax.swing.JTextField txtfrom1;
    private javax.swing.JTextField txtfrom2;
    private javax.swing.JTextField txtidti;
    private javax.swing.JTextArea txtimsg;
    private javax.swing.JTextArea txtimsg1;
    private javax.swing.JTextArea txtimsg2;
    private javax.swing.JTextArea txtimsg3;
    private javax.swing.JTextArea txtimsg4;
    private javax.swing.JTextField txtisu;
    public static javax.swing.JTextField txtmailnoti;
    private javax.swing.JTextField txtmailsub;
    private javax.swing.JTextField txtmailticid;
    public static javax.swing.JTextField txtnoticfax;
    public static javax.swing.JTextField txtnoticmail;
    public static javax.swing.JTextField txtnoticsms;
    private javax.swing.JTextField txtocc;
    private javax.swing.JTextArea txtomsg;
    private javax.swing.JTextField txtosu;
    private javax.swing.JTextField txtoto;
    public static javax.swing.JTextField txtsmsnoti;
    private javax.swing.JTextField txtsmsstat;
    private javax.swing.JTextField txtsmsticid;
    private javax.swing.JTextArea txtsolution;
    private javax.swing.JTextField txtticno1;
    private javax.swing.JTextField txtticno2;
    // End of variables declaration//GEN-END:variables

    public static String sql;
    public static String sql1;
    public static String sql2;
    public static String sql3;
    public static String sql4;
    public static String sql5;
    public static String sql6;
    public static String sql7;
    public static String sql8;
    public static String sql9;
    public static String sql10;
    public static String sql11;
    public static String sql12;
    public static String sql13;
    public static String sql14;
    public static String sql15;
    public static String sqlid;
    public static String sqlld;
    public static String sqllt;
    public static String condition;
    public static ResultSet rs;
    public static ResultSet rs1;
    public static JavaConnector jconn=new JavaConnector();
    public static Connection conn;
    private Timer dateTimer;
    //private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy, HH:mm:ss");
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    private void tabelticconf(){
        tabticconf.setRowCount(0);
             try{
                 sql="select tickets.*" +
                      ", _department.dept_name as asdept" +
                      ", _ticketstatus.data as ticstat" +
                      " from tickets" +
                      " left join _department on tickets.assign_dept=_department.dept_id" +
                      " left join _ticketstatus on tickets._status=_ticketstatus.code" +
                      " where confirm=1 and confirm_by=0 and confirmed=1 order by ticket_no";
              rs=jconn.SQLExecuteRS(sql, conn);

            while(rs.next()){
                tic[0]=rs.getString("ticket_no");
                tic[1]=rs.getString("confirm_username");
                tic[2]=rs.getString("ticstat");
                tic[3]=rs.getString("category");
                tic[4]=rs.getString("asdept");
                tic[5]=rs.getString("assign_username");
                tic[6]=rs.getString("cust_name");
                tic[7]=rs.getString("cust_phone");
                tic[8]=rs.getString("user");
                tic[9]=rs.getString("vehicle_platno");
                tic[10]=rs.getString("vehicle_type");
                tic[11]=rs.getString("driver_name");
                tic[12]=rs.getString("driver_phone");
                tic[13]=rs.getString("ticket_id");
                tabticconf.addRow(tic);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }              

    private void tabelact(){
        tabact.setRowCount(0);
             try{
             int row=0;
              sql="select username, _userlevel.data, _activity.data, info, login_time, host_addr, line_number from user_account join _activity on user_account._activity=_activity.code join _userlevel on user_account._level=_userlevel.code order by username";
              rs=jconn.SQLExecuteRS(sql, conn);
//              System.out.println(sql);

            while(rs.next()){
                act[0]=rs.getString(1);
                act[1]=rs.getString(2);
                act[2]=rs.getString(3);
                act[3]=rs.getString(4);
                act[4]=rs.getString(5);
                act[5]=rs.getString(6);
                act[6]=rs.getString(7);
                tabact.addRow(act);
            }

        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }    
    
    public static void tabelmsin() {
        try{
            tabmsin.setRowCount(0);
            int row=0;
            Date dt1 =dtmsi.getDate();
            Date dt2 =dtmsi1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            msin = sdf.format(dt1);
            msin1= sdf.format(dt2);
            sql="select * " +
                    "from msg_inbox " +
                    "where msg_date between '"+msin+"' and '"+msin1+"' and _erased=0 and msg_to = '"+lbluser.getText()+"' order by msg_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);

            while(rs.next()){
                msn[0]=rs.getString("msg_date");
                msn[1]=rs.getString("msg_time");
                msn[2]=rs.getString("msg_from");
                msn[3]=rs.getString("_read");
                msn[4]=rs.getString("msg_text");
                msn[5]=rs.getString("msg_id");
                tabmsin.addRow(msn);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    public static void tabelmsou()        {
        try{
            tabmsou.setRowCount(0);
            int row=0;
            Date dt1 =dtmso.getDate();
            Date dt2 =dtmso1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            msou = sdf.format(dt1);
            msou1= sdf.format(dt2);
            sql="select * " +
                    "from msg_outbox " +
                    "where msg_date between '"+msou+"' and '"+msou1+"' and _erased=0 and msg_from = '"+lbluser.getText()+"' order by msg_id";
            rs=jconn.SQLExecuteRS(sql, conn);
//            System.out.println(sql);
            while(rs.next()){
                msu[0]=rs.getString("msg_date");
                msu[1]=rs.getString("msg_time");
                msu[2]=rs.getString("msg_to");
                msu[3]=rs.getString("msg_text");
                msu[4]=rs.getString("msg_id");
                tabmsou.addRow(msu);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    
    Action activ = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            tabelact();
        }
    };
           
    Action dating = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            ambilTgl();
        }
    };
    int pjg=750;
    Action tiscrol = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            pjg--;
            if(pjg<-tp){
                pjg=750;
            }
            lblscroll.setLocation(pjg,0);
//                if(lblscroll.LEFT > -lblscroll.WIDTH){
//                    lblscroll.setLocation(pjg,0);
//                }
        }
    };
    int tp;
    Action inbound = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {                
            try {
                String come;
                String[] tcp = new String[20];
                String conf;
                int p;
                int index;
                if (inbroad.ready()) {
                    come = inbroad.readLine();
//                    System.out.print("\nframe: " + come + "\n");
                    index = come.indexOf(':');
                    p = come.length();
                    if (index >= 0) {
//                        System.out.print("\n ada titik dua\n");
                        tcp[0] = come.substring(index + 1, p);
//                        System.out.print("\n tcp[0] (stlh buang :): " + tcp[0] + "\n");
                        if (tcp[0].length() != 0) {
                            index = tcp[0].indexOf('|');
                            p = tcp[0].length();
                            if (index != -1) {
                                tcp[1] = tcp[0].substring(0, index);
                                System.out.print("\n keyword " + tcp[1] + "\n");
                                tcp[2] = tcp[0].substring(index + 1, p);
                                System.out.print("\n String1 " + tcp[2] + "\n");
                                if (tcp[2].length() != 0) {
                                    index = tcp[2].indexOf('|');
                                    p = tcp[2].length();
                                    if (index != -1) {
                                        tcp[3] = tcp[2].substring(0, index);
                                        System.out.print("\nisi data1 " + tcp[3] + "\n");
                                        tcp[4] = tcp[2].substring(index + 1, p);
                                        System.out.print("\nisi String2 " + tcp[4] + "\n");
                                        if (tcp[4].length() != 0) {
                                            index = tcp[4].indexOf('|');
                                            p = tcp[4].length();
                                            if (index != -1) {
                                                tcp[5] = tcp[4].substring(0, index);
                                                System.out.print("\nisi data2 " + tcp[5] + "\n");
                                                tcp[6] = tcp[4].substring(index + 1, p);
                                                System.out.print("\nisi String3 " + tcp[6] + "\n");
                                                if (tcp[6].length() != 0) {
                                                    index = tcp[6].indexOf('|');
                                                    p = tcp[6].length();
                                                    if (index != -1) {
                                                        tcp[7] = tcp[6].substring(0, index);
                                                        System.out.print("\nisi data3 " + tcp[7] + "\n");
                                                        tcp[8] = tcp[6].substring(index + 1, p);
                                                        System.out.print("\nisi String4 " + tcp[8] + "\n");
                                                        if (tcp[8].length() != 0) {
                                                            index = tcp[8].indexOf('|');
                                                            p = tcp[8].length();
                                                            if (index != -1) {
                                                                tcp[9] = tcp[8].substring(0, index);
                                                                System.out.print("\nisi data4 " + tcp[9] + "\n");
                                                                tcp[10] = tcp[8].substring(index + 1, p);
                                                                System.out.print("\nisi String5 " + tcp[10] + "\n");
                                                            }else{
                                                                tcp[9] = tcp[8].substring(0, p);
                                                                System.out.print("\n data4: " + tcp[9] + "\n");
                                                            }
                                                        }
                                                    }else{
                                                        tcp[7] = tcp[6].substring(0, p);
                                                        System.out.print("\n data3: " + tcp[7] + "\n");
                                                    }
                                                }
                                            }else{
                                                tcp[5] = tcp[4].substring(0, p);
                                                System.out.print("\n data2: " + tcp[5] + "\n");
                                            }
                                        }
                                    }else{
                                        tcp[3] = tcp[2].substring(0, p);
                                        System.out.print("\n data1: " + tcp[3] + "\n");
                                    }
                                }
                            } else {
                                tcp[1] = tcp[0].substring(0, p);
                                System.out.print("\n keyword: " + tcp[1] + "\n");
                            }
                        } else {
                            System.out.print("\n ga da keyword\n");
                        }
                    } else {
                        System.out.print("\n ga da : ny\n");
                    }
                    if (tcp[0].length() != 0 && tcp[3] != null) {
                        if (tcp[1].equals("MSG")&&tcp[3].equals(lbluser.getText())) {
                            tt++;
                            oldtext = lblscroll.getText();
                            if(oldtext.equals("")){
                                lblscroll.setText("1 message received");
                                newtext = lblscroll.getText();
                                tp=newtext.length()*8;
                                lblscroll.setSize(tp, 20);
                                Scrol.start();
                            }else{
                                oldtext=oldtext.replaceAll("1 message received", "");
                                lblscroll.setText(tt+" messages received ,"+oldtext);
                                newtext = lblscroll.getText();
                                tp=newtext.length()*8;
                                lblscroll.setSize(tp, 20);
                            }
                        }
                    }
                    if (tcp[0].length() != 0 && tcp[3] != null) {
                        System.out.print("\n panjang tcp 3 = "+tcp[3].length());
                        if (tcp[1].equals("TICKET")||tcp[3].equals("CONFIRM")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from tickets where _status=2 and confirm=1 and confirm_by=0 and confirm_username is null and confirmed=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    c = Integer.parseInt(rs.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            btnoutbound.setEnabled(true);
                            txtcalnoti.setText(String.valueOf(c));
                            if(c==0){
                                btnoutbound.setEnabled(false);
                                txtcalnoti.setText("");
                            }else{
                                btnoutbound.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + c + " OUTBOUND CALL TO COMFIRM", "OUTBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("MAIL")||tcp[3].equals("INBOUND")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from log_mail where direction=0 and status=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    m = Integer.parseInt(rs.getString(1));
//                                    m++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            tabelticconf();
                            btnmail.setEnabled(true);
                            txtmailnoti.setText(String.valueOf(m));
                            if(m==0){
                                btnmail.setEnabled(false);
                                txtmailnoti.setText("");
                            }else{
                                btnmail.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + m + " INBOUND MAIL TO COMFIRM", "INBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("SMS")||tcp[3].equals("INBOUND")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from log_sms where _direction=0 and _status=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    sm = Integer.parseInt(rs.getString(1));
//                                    sm++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            tabelticconf();
                            txtsmsnoti.setText(String.valueOf(sm));
                            btnsms.setEnabled(true);
                            if(sm==0){
                                btnsms.setEnabled(false);
                                txtsmsnoti.setText("");
                            }else{
                                btnsms.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + sm + " INBOUND SMS ", "INBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("FAX")||tcp[3].equals("INBOUND")) {
//                            System.out.print("\n" + tcp[0] + "\n");
                            sql = "select count(*) from log_fax where _direction=0 and _status=0";
                            rs = jconn.SQLExecuteRS(sql, conn);
                            try {
                                while (rs.next()) {
                                    fx = Integer.parseInt(rs.getString(1));
//                                    sm++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            tabelticconf();
                            txtfaxnoti.setText(String.valueOf(fx));
                            btnsms.setEnabled(true);
                            if(fx==0){
                                btnfax.setEnabled(false);
                                txtfaxnoti.setText("");
                            }else{
                                btnfax.setEnabled(true);
                            }
//                            JOptionPane.showMessageDialog(null, "YOU'VE GOT " + sm + " INBOUND SMS ", "INBOUND CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                        }
                        if (tcp[1].equals("SMS")||tcp[3].equals("UPDATE")) {
                            sms();
                        }
                        if (tcp[1].equals("EMAIL")||tcp[3].equals("UPDATE")) {
                            mail();
                        }
                        if (tcp[1].equals("FAX")||tcp[3].equals("UPDATE")) {
                            fax();
                        }
//                        TICKET|ASSIGN|"+deptid+"|"+id+"\r\n            
                        if(tcp[5] != null ){
//                            if (tcp[1].equals("TICKET")&&tcp[3].equals("ASSIGN")&&tcp[5].equals("0")) {
                            if (tcp[1].equals("TICKET")){
                                if(tcp[3].equals("ASSIGN")&&tcp[5].equals("0")){
                                    oldtext = lblscroll.getText();
                                    if(oldtext.equals("")){
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Assigned to your department");
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                        Scrol.start();
                                    }else{
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Assigned to your department, "+oldtext);
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                    }
                                }else if(tcp[3].equals("UPDATE")&&tcp[5].equals("0")){
                                    oldtext = lblscroll.getText();
                                    if(oldtext.equals("")){
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Updated");
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                        Scrol.start();
                                    }else{
                                        lblscroll.setText("[Ticket No. "+tcp[7]+"] Updated, "+oldtext);
                                        newtext = lblscroll.getText();
                                        tp=newtext.length()*6;
                                        System.out.print("isi tp : "+tp);
                                        lblscroll.setSize(tp, 20);
                                    }
                                }
//                                tt++;
//                                JOptionPane.showMessageDialog(null, "YOU'VE GOT " + tt + " TICKET ", "TICKET CONFIRMATION", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    private void ambilTgl(){
        GregorianCalendar now=new GregorianCalendar();
        int tgl=now.get(now.DATE);
        int bln=now.get(now.MONTH)+1;
        int thn=now.get(now.YEAR);
        int h=now.get(now.HOUR);
        int m=now.get(now.MINUTE);
        int s=now.get(now.SECOND);
    
        lbldate.setText(String.valueOf(thn)+"-"+String.valueOf(bln)+"-"+String.valueOf(tgl)+"  "+String.valueOf(h)+":"+String.valueOf(m)+":"+String.valueOf(s));
    }
     
    public void dataregis(){
          try{
             int row=0;
              sql="select pabx_host, inbound_ext, outbound_ext from user_account where username='"+lbluser.getText()+"'";
              rs=jconn.SQLExecuteRS(sql, conn);
//              System.out.println(sql);

            while(rs.next()){
                pabx=rs.getString(1);
                in_ext=rs.getString(2);
                out_ext=rs.getString(3);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
        
         Action testing = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String ring;
                String come;
                String tcp[]=new String[20];
                String conf;
                int p;
                int index;
            try {
                if (intele.ready()) {
                    ring = intele.readLine();
                    if((ring.substring(0,7).equals("ABANDON") )) {
                        stop();
                        delay();
                        if(elapsed<=5){
                            sql="update log_phone set abandon='"+elapsed+"', _callstatus=-1 where log_id='"+loid+"'";
                            jconn.SQLExecute(sql, conn);
                        }else{
                            sql="update log_phone set abandon='"+elapsed+"', _callstatus=0 where log_id='"+loid+"'";
                            jconn.SQLExecute(sql, conn);
                        }
                        sql3="update user_account set _activity=4, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
                        jconn.SQLExecute(sql3, conn);
                        btncall.setEnabled(false);
                        btncall.setDebugGraphicsOptions(v);
                        JOptionPane.showMessageDialog(null, "CALL DISCONNECTED", "INCOMING CALL",JOptionPane.WARNING_MESSAGE);
                    }else if ((ring.substring(0,7).equals("RINGING") )) {
                            start();
                            p=ring.length();
                            callid=ring.substring(8,p);
                            show();
                            toFront();                            
//                            JOptionPane.showMessageDialog(null, callid +"\n"+"CALLING", "INCOMING CALL",JOptionPane.WARNING_MESSAGE);
//                            int i=JOptionPane.showConfirmDialog(null, callid +"\n"+"CALLING", "INCOMING CALL",JOptionPane.YES_NO_OPTION);
                            btncall.setEnabled(true);
//                            btncall.setBackground(Color.RED);
//                            btncall.setIcon(frameIcon);
                            System.out.print("udah nyampe testing"+ ring);
                            try{
                                sqllt="select CURRENT_TIME";
                                rs = jconn.SQLExecuteRS(sqllt, conn);
                                while(rs.next()){
                                    lt=rs.getString(1);
                                }
                                sql3="update user_account set _activity=2, time_activity=CURRENT_TIMESTAMP where username= '" +lbluser.getText()+ "' limit 1";
                                jconn.SQLExecute(sql3, conn);
//                                System.out.println(lt);

                                  sql="insert into log_phone (log_date,log_time,username,shift,host_addr,line_number,_direction,_callstatus,caller_number) values ('"+ld+"','"+lt+"','"+lbluser.getText()+"','"+Log.cbshift.getSelectedIndex()+"','"+Log.data[2]+"','"+in_ext+"',0,0,'"+callid+"')";
                                  jconn.SQLExecute(sql, conn);
//                                  System.out.println(sql);
                                  btncall.setEnabled(true);

                                  sqlid="select distinct last_insert_id() from log_phone";
                                  rs=jconn.SQLExecuteRS(sqlid, conn);
                                  while (rs.next()) {
                                    loid = rs.getString(1);
                                  }
                                  btncall.setEnabled(true);
                            }catch(Exception exc){
                                System.err.println(exc.getMessage());
                            }
                        }else{
                            btncall.setEnabled(false);
                            btncall.setDebugGraphicsOptions(v);
                        }                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
            }
            }        
       };


       public void start() {
        this.startTime = System.currentTimeMillis();
    }

    
    public void stop() {
        this.stopTime = System.currentTimeMillis();
    }

    
    //elaspsed time in milliseconds
    private void currentdate() {
        try {
            sqlld = "select CURRENT_DATE";
            rs = jconn.SQLExecuteRS(sqlld, conn);
            while (rs.next()) {
                ld = rs.getString(1);
            }
            System.out.println(ld);
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //elaspsed time in seconds
    public long delay() {
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);

        //System.out.print(elapsed);
        }
        return elapsed;
    }

    public static void call(){
        try {
            sql="select count(*) from tickets where _status=2 and confirm=1 and confirm_by=0 and confirm_username is null and confirmed=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                c = Integer.parseInt(rs.getString(1));
            }
            txtcalnoti.setText(String.valueOf(c));
            if(c==0){
                btnoutbound.setEnabled(false);
                txtcalnoti.setText("");
            }else{
                btnoutbound.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void sms(){
        try {
            sql="select count(*) from log_sms where _direction=0 and _status=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                sm = Integer.parseInt(rs.getString(1));
            }
            if(sm==0){
                btnsms.setEnabled(false);
                txtsmsnoti.setText("");
            }else{
                btnsms.setEnabled(true);
                txtsmsnoti.setText(String.valueOf(sm));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void mail(){
        try {
            sql="select count(*) from log_mail where direction=0 and status=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                m = Integer.parseInt(rs.getString(1));
            }            
            if(m==0){
                btnmail.setEnabled(false);
                txtmailnoti.setText("");
            }else{
                btnmail.setEnabled(true);
                txtmailnoti.setText(String.valueOf(m));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void fax(){
        try {
            sql="select count(*) from log_fax where _direction=0 and _status=0";
            rs=jconn.SQLExecuteRS(sql, conn);
            while (rs.next()) {
                fx = Integer.parseInt(rs.getString(1));
            }            
            if(fx==0){
                btnfax.setEnabled(false);
                txtfaxnoti.setText("");
            }else{
                btnfax.setEnabled(true);
                txtfaxnoti.setText(String.valueOf(fx));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactCenterASWATA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String optm, opdt;
    private void optm(){
        try{
            sql="select CURRENT_TIME";
            rs = jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                optm=rs.getString(1);
            }
            //            txtcalbac.setText(optm);
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    private void opdt(){
        try{
            sql="select CURRENT_DATE";
            rs = jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                opdt=rs.getString(1);
            }
        }catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    }
    private void usr(){
        cbagenrelease.removeAllItems();        cbagenin.removeAllItems();        cbagenirepcal.removeAllItems();
        cbagenirepcal1.removeAllItems();        cbagenirepfax.removeAllItems();        cbagenou.removeAllItems();
        cbagenrepmail.removeAllItems();

        cbagenrelease.addItem("--");        cbagenin.addItem("--");        cbagenirepcal.addItem("--");
        cbagenirepcal1.addItem("--");        cbagenirepfax.addItem("--");        cbagenou.addItem("--");
        cbagenrepmail.addItem("--");
        try{
            sql="select username from user_account where dept_id=0" ;
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                cbagenrelease.addItem(rs.getString(1));
                cbagenin.addItem(rs.getString(1));
                cbagenirepcal.addItem(rs.getString(1));
                cbagenirepcal1.addItem(rs.getString(1));
                cbagenirepfax.addItem(rs.getString(1));
                cbagenou.addItem(rs.getString(1));
                cbagenrepmail.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.print(e);
        }
        cbagenrelease.setSelectedItem("--");        cbagenin.setSelectedItem("--");        cbagenirepcal.setSelectedItem("--");
        cbagenirepcal1.setSelectedItem("--");        cbagenirepfax.setSelectedItem("--");        cbagenou.setSelectedItem("--");
        cbagenrepmail.setSelectedItem("--");
    }
    private void usrlvl(){
        try{
            sql="select _level from user_account where username='"+Log.txtnm.getText()+"'";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                usrlvl=Integer.parseInt(rs.getString(1));
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }
    private void showStatus(){
        try{
            cbticstatus.removeAllItems();
            cbticstatus1.removeAllItems();

            sql="select data from _ticketstatus where code !=-1 order by code";
            rs=Log.jconn.SQLExecuteRS(sql,Log.conn);
            while(rs.next()){
                cbticstatus.addItem(rs.getString(1));
                cbticstatus1.addItem(rs.getString(1));
            }
            cbticstatus.addItem("--");
            cbticstatus1.addItem("--");
            cbticstatus.setSelectedItem("--");
            cbticstatus1.setSelectedItem("--");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void showDept(){
        try{
            cbdept.removeAllItems();cbdept1.removeAllItems();

            sql="select dept_name from _department where _deleted=0 order by dept_id";
            rs=Log.jconn.SQLExecuteRS(sql,Log.conn);
            while(rs.next()){
                cbdept.addItem(rs.getString(1));
                cbdept1.addItem(rs.getString(1));
            }
            cbdept.addItem("--");
            cbdept1.addItem("--");
            cbdept.setSelectedItem("--");cbdept1.setSelectedItem("--");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void followUp(){
        cbFollowUp.removeAllItems();
        cbFollowUp.addItem("--");
        try{
            sql="select data from _followup ";
            rs=jconn.SQLExecuteRS(sql, conn);
            while(rs.next()){
                cbFollowUp.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.print(e);
        }
        cbFollowUp.setSelectedItem("--");
    }
    private void showCust(){
        try{
            cbcust.removeAllItems();cbcust1.removeAllItems();cbcust2.removeAllItems();
            cbcust.addItem("Others");cbcust1.addItem("Others");cbcust2.addItem("Others");

            sql="select distinct(cust_name) from customer_order order by cust_name";
            rs=jconn.SQLExecuteRS(sql,conn);
            while(rs.next()){
                cbcust.addItem(rs.getString(1));
                cbcust1.addItem(rs.getString(1));
                cbcust2.addItem(rs.getString(1));
            }            
            cbcust.setSelectedIndex(-1);cbcust1.setSelectedIndex(-1);cbcust2.setSelectedIndex(-1);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void connecttele()  {
         try {
             // If guest, try to connect to the server
             sockettele = new Socket(IPtele, porttele);
             intele = new BufferedReader(new
                     InputStreamReader(sockettele.getInputStream()));
             outtele = new PrintWriter(sockettele.getOutputStream(), true);
             //               System.out.print(socket);
             teleOn=true;
         }
         // If error, clean up and output an error message
         catch (IOException e) {
             int i=JOptionPane.showConfirmDialog(null,"Telephony did not work\n\nPlease activate your telephony application","Activate Telepohony",JOptionPane.YES_NO_OPTION);
             if (i==JOptionPane.YES_OPTION){
                 connecttele();
             }else{
                 teleOn=false;
                 sql="update user_account set _activity=0 where username= '" +Log.data[0]+ "' limit 1";
                 jconn.SQLExecute(sql, conn);
                 try {
                     Thread.sleep(5000);
                 } catch (InterruptedException ex) {
                     //                     Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 System.exit(0);
             }
         }
     }

    public static int bc=0;
    public static void connect()  {
        try {
            bc++;
            socketbroad = new Socket(IPbroad, portbroad);
            inbroad = new BufferedReader(new
                    InputStreamReader(socketbroad.getInputStream()));
            outbroad = new PrintWriter(socketbroad.getOutputStream(), true);
            msg.start();
            brcaOn=true;
//               System.out.print(socket1);
            }
            // If error, clean up and output an error message
            catch (IOException e) {
                cleanbroad();
                brcaOn=false;
                if(bc==1000||bc==1){
                    JOptionPane.showMessageDialog(null,"Broadcaster didnt work...");
                    connect();
                    if(bc==1000){
                        bc=0;
                    };
                }
                bc++;
            }
    }
    public static void connectuploder()  {
        try {
            socketupload = new Socket("localhost", 6018);
            inupload = new BufferedReader(new
                    InputStreamReader(socketupload.getInputStream()));
            outupload = new PrintWriter(socketupload.getOutputStream(), true);
            //               System.out.print(socket1);
            uploOn=true;
        }
        // If error, clean up and output an error message
        catch (IOException e) {
            int i=JOptionPane.showConfirmDialog(null,"Uploader did not work\n\nPlease activate your uploader application","Activate Uploader",JOptionPane.YES_NO_OPTION);
            if (i==JOptionPane.YES_OPTION){
                connectuploder();
            }else{
                uploOn=false;
                sql="update user_account set _activity=0 where username= '" +Log.data[0]+ "' limit 1";
                jconn.SQLExecute(sql, conn);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                        //                     Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
    }
    public static void connectuploder1()  {
        try {
            socketupload1 = new Socket("localhost", 6019);
            inupload1 = new BufferedReader(new
                    InputStreamReader(socketupload1.getInputStream()));
            outupload1 = new PrintWriter(socketupload1.getOutputStream(), true);
            //               System.out.print(socket1);
            uploOn1=true;
        }
        // If error, clean up and output an error message
        catch (IOException e) {
            int i=JOptionPane.showConfirmDialog(null,"Voice Uploader did not work\n\nPlease activate your Voice uploader application","Activate Voice Uploader",JOptionPane.YES_NO_OPTION);
            if (i==JOptionPane.YES_OPTION){
                connectuploder1();
            }else{
                uploOn1=false;
                sql="update user_account set _activity=0 where username= '" +Log.data[0]+ "' limit 1";
                jconn.SQLExecute(sql, conn);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                        //                     Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
    }
    public static void kirimTele(){
        if(teleOn==true){
            sendString(s);
            outtele.print(toSend); outtele.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    public static void kirimBroad(){
        if(brcaOn==true){
            sendString(s);
            outbroad.print(toSend); outbroad.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    public static void kirimUplo(){
        if(uploOn==true){
            sendString(s);
            outupload.print(toSend); outupload.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    public static void kirimUplo1(){
        if(uploOn1==true){
            sendString(s);
            outupload1.print(toSend); outupload1.flush();
            toSend.setLength(0);
            s=null;
        }
    }
    private static void cleanUptele() {
        try {
            if (hostServer != null) {
                hostServer.close();
                hostServer = null;
            }
        }
        catch (IOException e) { hostServer = null; }

        try {
            if (sockettele != null) {
                sockettele.close();
                sockettele = null;
            }
        }
        catch (IOException e) { sockettele = null; }

        try {
            if (intele != null) {
                intele.close();
                intele = null;
            }
        }
        catch (IOException e) { intele = null; }

        if (outtele != null) {
            outtele.close();
            outtele = null;
        }
    }
    private static void cleanbroad() {
        try {
            if (hostServer1 != null) {
                hostServer1.close();
                hostServer1 = null;
            }
        }
        catch (IOException e) { hostServer1 = null; }

        try {
            if (socketbroad != null) {
                socketbroad.close();
                socketbroad = null;
            }
        }
        catch (IOException e) { socketbroad = null; }

        try {
            if (inbroad != null) {
                inbroad.close();
                inbroad = null;
            }
        }
        catch (IOException e) { inbroad = null; }

        if (outbroad != null) {
            outbroad.close();
            outbroad = null;
        }
    }
    private static void cleanUpupload() {

      try {
         if (socketupload != null) {
            socketupload.close();
            socketupload = null;
         }
      }
      catch (IOException e) { socketupload = null; }

      try {
         if (inupload != null) {
            inupload.close();
            inupload = null;
         }
      }
      catch (IOException e) { inupload = null; }

      if (outupload != null) {
         outupload.close();
         outupload = null;
      }
   }
    private static void cleanUpupload1() {

      try {
         if (socketupload1 != null) {
            socketupload1.close();
            socketupload1 = null;
         }
      }
      catch (IOException e) { socketupload1 = null; }

      try {
         if (inupload1 != null) {
            inupload1.close();
            inupload1 = null;
         }
      }
      catch (IOException e) { inupload1 = null; }

      if (outupload1 != null) {
         outupload1.close();
         outupload1 = null;
      }
   }
}

