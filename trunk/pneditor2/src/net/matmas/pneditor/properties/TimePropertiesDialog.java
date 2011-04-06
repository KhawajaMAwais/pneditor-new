package net.matmas.pneditor.properties;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import net.matmas.pnapi.Node;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.matmas.pnapi.Arc;
import net.matmas.pneditor.Canvas;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pneditor.PNEditor;


public class TimePropertiesDialog extends JFrame implements ActionListener {
        public Place choosenPlace;
        public Transition choosenTran;
	private JButton OkButton = new JButton("OK");
	private JButton CancelButton = new JButton("Cancel");
        private JCheckBox start = new JCheckBox("Start");
        private JCheckBox end = new JCheckBox("End");
        private JButton orsplit = new JButton("Set Or Split");
        private JButton andsplit = new JButton("Set And Split");
        private JCheckBox mm1 = new JCheckBox("M/M/1");
        private JCheckBox mmc = new JCheckBox("M/M/c");
        private JButton iterstart = new JButton("Set Iteration Start");
        private JCheckBox iterationarc = new JCheckBox("Iteration arc");
        private JTextField probability = new JTextField();
        private JTextField arrivalrate = new JTextField();
        private JTextField servisrate = new JTextField();
        private JTextField serversnumber = new JTextField();
        private JLabel label = new JLabel();
        private JLabel labela = new JLabel();
        private Node node;
        private Arc arc;
        private WithProperties typ;
        private PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
        private Integer pomocna;
        private double pomocnap;
        private DecimalFormat df = new DecimalFormat();
        private Canvas canvas = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas();

	public TimePropertiesDialog(JFrame parentFrame, WithProperties withProperties, Node nodee, Arc arcc) {
		node = nodee;
                typ = withProperties;
                arc = arcc;
                String title = "Time properties";
                JPanel panel = new JPanel(new GridLayout());
                JPanel panelf = new JPanel(new GridLayout());
                JPanel panelb = new JPanel(new GridLayout());
                JPanel panelc = new JPanel(new GridLayout());
                JPanel panelnad = new JPanel(new GridLayout(0,1));
		if (withProperties instanceof Place) {
                        for (Place place : petriNet.getPlaces()) {
                            if(place.getId().equals(node.getId())){
                                start.setSelected(place.getIsStartPlace());
                                end.setSelected(place.getIsEndPlace());
                                orsplit.setSelected(place.getIsORSplit());
                                iterstart.setSelected(place.getIsIterationStart());
                                Double text = place.getArrivalRate();
                                arrivalrate.setText(text.toString());
                            }
                        }
                        title += " of place";
                        Border border = BorderFactory.createTitledBorder("Zadajte pozíciu");
                        Border borderf = BorderFactory.createTitledBorder("Zadajte logickú funkciu");
                        panel.setBorder(border);
                        panelf.setBorder(borderf);
                        panel.add(start);
                        panel.add(end);
                        label.setText("Intenzita príchodu ks/hod :");
                        panel.add(label);
                        start.addActionListener(this);
                        arrivalrate.disable();
                        panel.add(arrivalrate, BorderLayout.SOUTH);
                        orsplit.addActionListener(this);
                        panelf.add(orsplit);
                        iterstart.addActionListener(this);
                        panelf.add(iterstart);
                        panelb.add(OkButton);
                        panelb.add(CancelButton);
                        Container contentPane = this.getContentPane();
                        contentPane.add(panel, BorderLayout.CENTER);
                        contentPane.add(panelf, BorderLayout.NORTH);
                        contentPane.add(panelb, BorderLayout.SOUTH);
                        this.setSize(550, 160);

		}
		else if (withProperties instanceof Transition) {
                        for (Transition transition : petriNet.getTransitions()) {
                            if(transition.getId().equals(node.getId())){
                                andsplit.setSelected(transition.getIsANDSplit());
                                mm1.setSelected(transition.getMM1());
                                mmc.setSelected(transition.getMMc());
                                Double text = transition.getServisRate();
                                servisrate.setText(text.toString());
                                Integer textt = transition.getNumberOfServers();
                                serversnumber.setText(textt.toString());
                            }
                        }
			title += " of transition";
                        Border border = BorderFactory.createTitledBorder("Zadajte logickú funkciu");
                        Border borders = BorderFactory.createTitledBorder("Zadajte servisný čas v minútach");
                        Border borderf = BorderFactory.createTitledBorder("Zadajte typ servera");
                        panel.setBorder(border);
                        panelb.setBorder(borders);
                        panelf.setBorder(borderf);
                        andsplit.addActionListener(this);
                        panel.add(andsplit);
                        label.setText("Servisný čas na jednotku v min. :");
                        labela.setText("Zadajte počet serverov :");
                        panelb.add(label);
                        panelb.add(servisrate);
                        serversnumber.disable();
                        mmc.addActionListener(this);
                        mm1.addActionListener(this);
                        panelf.add(mm1);
                        panelf.add(mmc);
                        panelf.add(labela);
                        panelf.add(serversnumber);
                        panelc.add(OkButton);
                        panelc.add(CancelButton);
                        panelnad.add(panel);
                        panelnad.add(panelf);
                        Container contentPane = this.getContentPane();
                        contentPane.add(panelb, BorderLayout.NORTH);
                        contentPane.add(panelnad, BorderLayout.CENTER);
                        contentPane.add(panelc, BorderLayout.SOUTH);
                        this.setSize(520, 200);
		}
		else if (withProperties instanceof Arc) {
                        for (Arc arca : petriNet.getArcs()) {
                            if(arca.equals(arc)){
                            iterationarc.setSelected(arca.getIsIterationArc());
                            Double text = arca.getProbability();
                            probability.setText(text.toString());
                            }
                        }
			title += " of arc";
                        Border border = BorderFactory.createTitledBorder("Označte ak je vetva iteračná");
                        Border borders = BorderFactory.createTitledBorder("Zadajte pravdepodobnosť pre vetvu");
                        panel.setBorder(border);
                        label.setText("Pravdepodobnosť pre vetvu v <0,1>:");
                        panel.add(iterationarc);
                        panelb.setBorder(borders);
                        panelb.add(label);
                        panelb.add(probability);
                        panelc.add(OkButton);
                        panelc.add(CancelButton);
                        Container contentPane = this.getContentPane();
                        contentPane.add(panelb, BorderLayout.NORTH);
                        contentPane.add(panel, BorderLayout.CENTER);
                        contentPane.add(panelc, BorderLayout.SOUTH);
                        this.setSize(400, 160);
		}
		this.setTitle(title);				
		this.setLocationRelativeTo(getParent());
                this.setVisible(true);
		OkButton.addActionListener(this);
		CancelButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
            if(start.isSelected()){
                            arrivalrate.enable();
                        }
                    else{
                            arrivalrate.disable();
                        }
            if(mmc.isSelected()){
                            serversnumber.enable();
                        }
                    else{
                            serversnumber.disable();
                        }
            if(mm1.isSelected()){
                            serversnumber.setText("1");
                        }

            if(e.getActionCommand().equals("OK")){
                if (typ instanceof Place) {
                    for (Place place : petriNet.getPlaces()) {
                        if(place.getId().equals(node.getId())){
                            if(start.isSelected()){
                             String text = arrivalrate.getText();
                                try {
                            pomocnap = df.parse(text).doubleValue();
                        } catch (ParseException ex) {
                            Logger.getLogger(TimePropertiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            place.setArrivalRate(pomocnap);
                            }
                            else{pomocnap=0;place.setArrivalRate(pomocnap);}
                            place.setIsStartPlace(start.isSelected());
                            place.setIsEndPlace(end.isSelected());
                            place.setIsIterationStart(iterstart.isSelected());
                            place.setIsORSplit(orsplit.isSelected());
                        }
                    }
                }
                if (typ instanceof Transition) {
                    for (Transition transition : petriNet.getTransitions()) {
                       if(transition.getId().equals(node.getId())){
                            transition.setIsANDSplit(andsplit.isSelected());
                            transition.setMM1(mm1.isSelected());
                            if(mmc.isSelected()){
                            transition.setMMc(mmc.isSelected());
                            pomocna = Integer.parseInt(serversnumber.getText());
                            transition.setNumberOfServers(pomocna);
                            }
                           String text = servisrate.getText();
                                try {
                            pomocnap = df.parse(text).doubleValue();
                        } catch (ParseException ex) {
                            Logger.getLogger(TimePropertiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            transition.setServisRate(pomocnap);
                        }
                    }
                }
                if (typ instanceof Arc) {
                    for (Arc arcc : petriNet.getArcs()) {
                        if(arcc.equals(arc)){
                           arcc.setIsIterationArc(iterationarc.isSelected());
                           System.out.println("Iteracna vetva: " + arc.getIsIterationArc());
                           String text = probability.getText();
                        try {
                            pomocnap = df.parse(text).doubleValue();
                        } catch (ParseException ex) {
                            Logger.getLogger(TimePropertiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           arcc.setProbability(pomocnap);
                           System.out.println("Pravdepodobnost : " + arc.getProbability());
                        }
                    }
                }
                this.dispose();
            }

            if(e.getActionCommand().equals("Cancel")){
                this.dispose();
                System.out.println(petriNet.getPetrinetName());
            }
            if(e.getActionCommand().equals("Set Or Split")){
              for (Place place : petriNet.getPlaces()) {
                        if(place.getId().equals(node.getId())){
                            place.setIsORSplit(true);
                            canvas.pomp = place;
                        }  
              }
              canvas.SetOrJoin = true;
              this.hide();
            }
            if(e.getActionCommand().equals("Set And Split")){
              for (Transition tran : petriNet.getTransitions()) {
                        if(tran.getId().equals(node.getId())){
                            tran.setIsANDSplit(true);
                            canvas.pompt = tran;
                        }
              }
              canvas.SetAndJoin = true;
              this.hide();
            }

            if(e.getActionCommand().equals("Set Iteration Start")){
              for (Place place : petriNet.getPlaces()) {
                        if(place.getId().equals(node.getId())){
                            place.setIsIterationStart(true);
                            canvas.pomp = place;
                        }
              }
              canvas.SetiterJoin = true;
              this.hide();
            }

	}
	
}

