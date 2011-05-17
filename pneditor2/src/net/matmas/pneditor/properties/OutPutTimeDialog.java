package net.matmas.pneditor.properties;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;

public class OutPutTimeDialog extends JFrame implements ActionListener{

    private JButton CloseButton = new JButton("Close");
    private JTextField systemtime = new JTextField();
    private JTextField waitingtime = new JTextField();
    private JTextField arrivalRate = new JTextField();
    private PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
    private JTextArea textarea = new JTextArea();
    private JScrollPane scroll = new JScrollPane();
    private JLabel label = new JLabel("Troughtput system time(min) :");
    private JLabel labela = new JLabel("System waiting time(min) :");
    private JLabel labelb = new JLabel("System rate ks/h :");

    public OutPutTimeDialog(Double systime, Double waitime, Double lambda){
    JPanel panel = new JPanel(new GridLayout());
    scroll = new JScrollPane(textarea);
    textarea.setColumns(5);
    textarea.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    Font font = new Font("Times New Roman", Font.BOLD, 16);
    Font font1 = new Font("Times New Roman", Font.BOLD, 14);
    textarea.setFont(font);
    textarea.append("Type         ");
    textarea.append("NOS    ");
    textarea.append("Servis time(min)    ");
    textarea.append("  System time(min)   ");
    textarea.append("  System waiting time(min)   ");
    textarea.append("Utilization(%)   ");
    textarea.append("With probability(%)  ");
    textarea.append("Label   \n");
    textarea.setFont(font1);
    systemtime.disable();
    waitingtime.disable();
    arrivalRate.disable();
    //textarea.disable();
    systemtime.setText(systime.toString());
    waitingtime.setText(waitime.toString());
    arrivalRate.setText(lambda.toString());
    panel.add(label);
    panel.add(systemtime);
    panel.add(labela);
    panel.add(waitingtime);
    panel.add(labelb);
    panel.add(arrivalRate);
    Container contentPane = this.getContentPane();
    contentPane.add(panel, BorderLayout.NORTH);
    contentPane.add(scroll);
    contentPane.add(textarea, BorderLayout.CENTER);
    contentPane.add(CloseButton, BorderLayout.SOUTH);

    for (Transition tran : petriNet.getTransitions()) {
    DecimalFormat df= new DecimalFormat("#0.0000");

    
    if(tran.getMM1()){
       textarea.append("M/M/1         ");
       textarea.append("1                ");
    }
    else if(tran.getMMc()){
       Integer yash= tran.getNumberOfServers();
       textarea.append("M/M/c         ");
       if (tran.getNumberOfServers()<10){
       textarea.append(yash+"                ");
       }
       else{
       textarea.append(yash+"              ");
       }
    }
    else
    {
       textarea.append("Undefined                     ");
    }

       Double serr = tran.getServisRate();
       String yash= df.format(serr);
       if (tran.getServisRate()<10){
       textarea.append(yash+"                ");
       }
       else{
       textarea.append(yash+"              ");
       }
    
    Double systt = tran.getSystemTime()*tran.getProbability()*60;
    yash= df.format(systt);
    if(systt > 9){
    textarea.append(yash+"                          ");
    }
    else{
    textarea.append(yash+"                           ");
    }
    
    Double sysww = tran.getWaitingTime()*tran.getProbability()*60;
    yash= df.format(sysww);
    if(sysww > 9){
    textarea.append(yash+"                            ");
    }
    else{
    textarea.append(yash+"                              ");
    }
    
  
    Double utill = tran.getUtilization()*tran.getProbability()*100;
    yash= df.format(utill);
    if(utill > 9){
    textarea.append(yash+"                      ");
    }
    else{
    textarea.append(yash+"                       ");
    }
    double prob = tran.getProbability()*100;
    if(prob < 100){
    textarea.append(prob +"               ");
    }
    else{
    textarea.append(prob +"             ");
    }
    
    textarea.append(tran.getLabel().getText()+"\n");
    }
    CloseButton.addActionListener(this);
    this.setSize(850, 400);
    this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Close")){
                this.dispose();
            }
    }

}
