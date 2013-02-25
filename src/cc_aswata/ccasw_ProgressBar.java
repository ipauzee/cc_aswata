/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cc_aswata;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author Alienware
 */
public class ccasw_ProgressBar {
    public static int value=0;
    final static JProgressBar pasw = new JProgressBar();
    final static Object[] rr = {"waiting for server\n", pasw};
    final static JOptionPane pane = new JOptionPane(rr, JOptionPane.WARNING_MESSAGE);
    final static JDialog dialog = pane.createDialog(null, "Requesting Web Services");

    public static void main(String[] args) throws Exception {
        dialog.show();
    }
}
