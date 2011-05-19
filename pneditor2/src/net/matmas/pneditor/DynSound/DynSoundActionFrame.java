/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.DynSound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author samo
 */
public class DynSoundActionFrame extends JFrame {

    private Container pane = getContentPane();
    private Container pane1 = new JPanel();
    private Container pane2 = new JPanel();
    private Container pane3 = new JPanel();
    private Container pane0 = new JPanel();
    private JLabel infoLabel = new JLabel();
    private JLabel infoLabel2 = new JLabel();
    private JLabel timeLabel = new JLabel();
    private JLabel errorInc = new JLabel();
    private JLabel errorInv = new JLabel();
    private JLabel pokrytieT = new JLabel();

    public DynSoundActionFrame(int i, int inst, long executionTime) throws Exception{
        errorInc.setForeground(Color.red);
        errorInv.setForeground(Color.red);
        errorInc.setText("");
        errorInv.setText("");
        pokrytieT.setText("");
        pokrytieT.setForeground(Color.blue);

        setTitle("Dynamical soundness");

        pane1.setLayout(new GridBagLayout());
        GridBagConstraints a = new GridBagConstraints();
        a.fill = GridBagConstraints.BOTH;
        a.gridx = 0;
        a.gridy = 0;
        infoLabel.setText("Analysis of dynamical soundness");
        Font big = new Font("SansSerif", Font.BOLD, 16);
        infoLabel.setFont(big);
        pane1.add(infoLabel, a);
        a.gridy = 1;

        a.gridy = 2;
        pane1.add(errorInc,a);

        pane2.setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.BOTH;
        b.gridx = 0;
        b.gridy = 0;

        System.out.println("Result " + i);
        if(i==1) infoLabel2.setText("Result: Given Petri net is dynamically sound!");
        if(i==-1) infoLabel2.setText("Result: Given Petri net has no static places.");
        if(i==-2) infoLabel2.setText("<html>Result: Given Petri net has none or too many input" + "<br>" + "or output places - it is not a correct RCWF net!</html>");
        if(i==-3) infoLabel2.setText("Result: Given Petri net is not sound for 1 instance!");
        if(i==-4) infoLabel2.setText("<html>Result: Given Petri net is not dynamically sound, deadlock" + "<br>" + "or livelock is reachable within " + inst + " instances!</html>");
        if (i==0) infoLabel2.setText("Result: unknown");
        if (i<-4 || i>1) infoLabel2.setText("Result: unknown");
        infoLabel2.setFont(big);
        pane2.add(infoLabel2, b);
        b.gridy = 1;

        b.gridy = 2;
        pane2.add(errorInv,b);
        b.gridy = 3;
        pane2.add(pokrytieT,b);

        pane3.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;

        timeLabel.setText("Execution time: "+executionTime+" ms");
        pane3.add(timeLabel, c);


        pane0.setLayout(new BorderLayout());
        pane0.add(pane1, BorderLayout.NORTH);
        pane0.add(pane2, BorderLayout.CENTER);
        pane0.add(pane3, BorderLayout.SOUTH);

        JScrollPane scrollpane = new JScrollPane(pane0);
        pane.setLayout(new BorderLayout());
        pane.add(scrollpane, BorderLayout.CENTER);
        pack();
        setSize(550,270);

    }
   

}
