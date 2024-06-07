/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package saw;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LABKOM
 */
public class MainApp extends javax.swing.JFrame {

    /**
     * Creates new form MainApp
     */
    public MainApp() {
        initComponents();
        SAW();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(422, 489));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
// Method: Menemukan nilai min-max
// Method: Menemukan label kriteria, cost/benefit
// Method: Menemukan bobot kriteria
// Normalisasi
// Perhitungan bobot akhir
    private String SAW() {
        try {
            Connection k1 = Koneksi.Go();
            Statement s1 = k1.createStatement();

            var q1 = "TRUNCATE TABLE hasilsaw";
            s1.executeUpdate(q1);
        } catch (Exception e) {
        }

        String data = "";

        try {
            Connection k = Koneksi.Go();
            Statement s = k.createStatement();

            var q = "SELECT * FROM datanilai";

            ResultSet r = s.executeQuery(q);
            while (r.next()) {
                int id = r.getInt("id");
                int alt_id = r.getInt("alt_id");
                double c1 = r.getInt("c1");
                double c2 = r.getInt("c2");
                double c3 = r.getInt("c3");
                double c4 = r.getInt("c4");

                //normalisasi
                double n_c1 = label("c1").equals("cost") ? min_max("c1", 1) / c1 : c1 / min_max("c1", 2);
                double n_c2 = label("c2").equals("cost") ? min_max("c2", 1) / c2 : c2 / min_max("c2", 2);
                double n_c3 = label("c3").equals("cost") ? min_max("c3", 1) / c3 : c3 / min_max("c3", 2);
                double n_c4 = label("c4").equals("cost") ? min_max("c4", 1) / c4 : c4 / min_max("c4", 2);

                double hasil = (n_c1 * bobot("c1"))
                        + (n_c2 * bobot("c2"))
                        + (n_c3 * bobot("c3"))
                        + (n_c4 * bobot("c4"));

                //insert data hasil pembobotan
                Connection k2 = Koneksi.Go();
                Statement s2 = k2.createStatement();

                var q2 = "INSERT INTO hasilsaw (alt_id,bobot) VALUES ('" + alt_id + "','" + hasil + "')";
                s2.executeUpdate(q2);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return data;
    }

    private double bobot(String kriteria) {
        double data = 0;

        try {
            Connection k = Koneksi.Go();
            Statement s = k.createStatement();

            var q = "SELECT bobot FROM kriteria WHERE flag='" + kriteria + "'";

            ResultSet r = s.executeQuery(q);
            while (r.next()) {
                data = r.getDouble("bobot");
            }

        } catch (SQLException e) {
        }

        return data;
    }

    /**
     *
     * @param kolom
     * @param opsi 1=minimum, 2=maximum
     * @return
     */
    private String label(String kolom) {
        String data = "";

        try {
            Connection k = Koneksi.Go();
            Statement s = k.createStatement();

            var q = "SELECT label FROM kriteria WHERE flag='" + kolom + "'";

            ResultSet r = s.executeQuery(q);
            while (r.next()) {
                data = r.getString("label");
            }

        } catch (SQLException e) {
        }

        return data;
    }

    private double min_max(String kolom, int opsi) {
        double data = 0;

        try {
            Connection k = Koneksi.Go();
            Statement s = k.createStatement();

            var q = "";
            if (opsi == 1) {
                q = "SELECT MIN(" + kolom + ") AS val FROM datanilai";
            } else {
                q = "SELECT MAX(" + kolom + ") AS val FROM datanilai";
            }

            ResultSet r = s.executeQuery(q);
            while (r.next()) {
                data = r.getDouble("val");
            }

        } catch (SQLException e) {
        }

        return data;
    }

}