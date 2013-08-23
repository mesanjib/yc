/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpst.yc.chart;
import com.rpst.yc.database.JDBCConnection;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author sanjib
 */
public class DisplayInChart extends javax.swing.JFrame {

    public DisplayInChart() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_click = new javax.swing.JButton();
        btn_barchart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_click.setText("Click");
        btn_click.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clickActionPerformed(evt);
            }
        });

        btn_barchart.setText("Bar Chart");
        btn_barchart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_barchartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(btn_click)
                .addGap(18, 18, 18)
                .addComponent(btn_barchart)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_click)
                    .addComponent(btn_barchart))
                .addGap(78, 78, 78))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clickActionPerformed
        
        TimeSeriesCollection dataset=new TimeSeriesCollection();
        TimeSeries s=new TimeSeries("");
        s.add(new Minute(1,1,1,1,2013),10);
        s.add(new Minute(5,1,1,1,2013),11);
        s.add(new Minute(10,1,1,1,2013),9);
        s.add(new Minute(15,1,1,1,2013),15);
        s.add(new Minute(20,1,1,1,2013),5);
        s.add(new Minute(25,1,1,1,2013),25);
        s.add(new Minute(30,1,1,1,2013),8);
        dataset.addSeries(s);
        JFreeChart chart=null;
        //chart=ChartFactory.createLineChart("Paramaeter Value","Parameter","values", dataset, PlotOrientation.VERTICAL, true, true, true);
        chart=ChartFactory.createTimeSeriesChart("Time Chart","UserID","Volume", dataset, true, true, true);
        chart.setBackgroundPaint(Color.YELLOW);
     
      // Set the background colour of the chart
      chart.getTitle().setPaint(Color.red);
      XYPlot p=chart.getXYPlot();
      p.setRangeGridlinePaint(Color.blue);
      ChartFrame frame=new ChartFrame("Line cart frame", chart);
      frame.setVisible(true);
      frame.setSize(500,500);
    }//GEN-LAST:event_btn_clickActionPerformed

    private void btn_barchartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_barchartActionPerformed
        try {
            JDBCConnection conn=new JDBCConnection();
            ResultSet rs=null;
            rs=conn.GetChartValues();
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();
            while(rs.next()){
                dataset.setValue(rs.getInt("volume"),rs.getString("user"),"Volume");  
            }
            JFreeChart chart=null;
            chart=ChartFactory.createBarChart("Bar Chart","User", "Volume", dataset, PlotOrientation.VERTICAL,true,true, false);
        chart.setBackgroundPaint(Color.YELLOW);
     
      // Set the background colour of the chart
      chart.getTitle().setPaint(Color.red);
      CategoryPlot p=chart.getCategoryPlot();
      p.setRangeGridlinePaint(Color.blue);
      ChartFrame frame=new ChartFrame("Line cart frame", chart);
      frame.setVisible(true);
      frame.setSize(500,500);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayInChart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayInChart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_barchartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayInChart().setVisible(true);
            }
        });

            }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_barchart;
    private javax.swing.JButton btn_click;
    // End of variables declaration//GEN-END:variables
}
