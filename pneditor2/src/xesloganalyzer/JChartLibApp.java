/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

/**
 *
 * @author XY
 */
import com.bitagentur.chart.JChartLibBarchart;
import com.bitagentur.chart.JChartLibBaseChart;
import com.bitagentur.data.JChartLibDataSet;

import com.bitagentur.data.JChartLibSerie;
import com.bitagentur.renderer.JChartLibPanel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;



public class JChartLibApp extends JFrame implements WindowListener {

private ArrayList<XESEvent> events = new ArrayList<XESEvent>();
private ArrayList<XESResource> resources = new ArrayList<XESResource>();
private String charttitle="";
private String xtitle="";
private String ytitle="";
/**
 * Creates a new Application Frame
 */
public JChartLibApp(ArrayList<XESEvent> events,String chattittle,String xtitle,String ytitle) {
    super("JChartLib Demo Appllication");
    this.events = events;
    this.charttitle= chattittle;
    this.xtitle = xtitle;
    this.ytitle = ytitle;
    final JChartLibDataSet dataset = createDataset();
    final JChartLibBarchart chart = (JChartLibBarchart) createChart(dataset);
    final JChartLibPanel chartPanel = new JChartLibPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(1400, 800));
    
    setContentPane(chartPanel);
}



/**
 * Creates a sample dataset.
 * 
 * @return a sample dataset.
 */
private JChartLibDataSet createDataset() {
    //Dataseries can be added with int arrays
//    int[] values1 = new int[5];
//    values1[0] = 1;
//    values1[1] = 3;
//    values1[2] = 4;
//    values1[3] = 7;
//    values1[4] = 2;

    //or by generating a Dataserie object
    final JChartLibDataSet dataset = new JChartLibDataSet();
    
    for(XESEvent event: events){
        JChartLibSerie serie = new JChartLibSerie(event.getName());
        serie.addValue(event.getCount());
        dataset.addDataSerie(serie);
    }
//    dataset.addDataSerie("Apple", values1);   //adds the Apples
//    dataset.addDataSerie(values2);            //adds the Bananas
    
    return dataset;
}

/**
 * Creates a chart
 * 
 * @param dataset the data for the chart.
 * @return a new chart
 */
private JChartLibBaseChart createChart(final JChartLibDataSet dataset) {

    // create the chart with title and axis names
    final JChartLibBarchart chart =  new JChartLibBarchart(
            charttitle, // chart title
            xtitle, // x axis text
            ytitle, // y axis text
            dataset // data
            //true // legend on
            );
    try {
        chart.saveAsJPEG("grafE.jpg", 600, 400);
    } catch (IOException ex) {
        Logger.getLogger(JChartLibApp.class.getName()).log(Level.SEVERE, null, ex);
    }

    return chart;
}

/**
 * Main method - DEMO Application for JChartLib
 *
 * @param args the command line arguments
 */
//public static void main(final String[] args) {
//    System.out.println("JChartLibApp started");
//    final JChartLibApp app = new JChartLibApp();
//    app.addWindowListener(app);
//    app.pack();
//    app.setVisible(true);
//    
//}

@Override
public void windowOpened(WindowEvent e) {
    //Nothing to do
}

@Override
public void windowClosing(WindowEvent e) {
    //Exit and goodby
    if (e.getWindow() == this) {
        dispose();
        
    }
}

@Override
public void windowClosed(WindowEvent e) {
    //Nothing to do

}

@Override
public void windowIconified(WindowEvent e) {
    //Nothing to do
}

@Override
public void windowDeiconified(WindowEvent e) {
    //Nothing to do
}

@Override
public void windowActivated(WindowEvent e) {
    //Nothing to do
}

@Override
public void windowDeactivated(WindowEvent e) {
    //Nothing to do
}
}