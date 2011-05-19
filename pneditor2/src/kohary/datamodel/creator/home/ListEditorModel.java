/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import kohary.datamodel.creator.util.*;
import kohary.datamodel.creator.attribute.AttributeCreatorFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Godric
 */
public class ListEditorModel<E> extends JPanel implements InterfaceOfDataModels, ActionListener, ListSelectionListener {

    private JScrollPane scrollPane;
    private JList list;
    private DataModels dataModels;
    private JPanel listPanel, buttonPanel;
    private JButton editButton, deleteButton, createButton;
    private DataModel dataModel; //pomocny
    private boolean isEditable, isDeletable; // isCreatable = true;
    private DataModelSelectPanel dataModelSelectPanel;

    public ListEditorModel(DataModels dataModels, DataModelSelectPanel dataModelSelectPanel) {
        dataModel = new DataModel();
        this.dataModelSelectPanel = dataModelSelectPanel;
        this.dataModels = dataModels;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 320));
        revalidate();
        list = new JList(dataModels);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);


        scrollPane = new JScrollPane(list);

        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        createButton = new JButton("Create");


        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);


        listPanel = new JPanel(new BorderLayout());

        listPanel.add(buttonPanel, BorderLayout.SOUTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        add(listPanel);

        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        createButton.addActionListener(this);

        list.addListSelectionListener(this);
    }

    public void addNew() {

        dataModels.addNew(this);
        list.setSelectedIndex(dataModels.getSize() - 1);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
 

            addNew();

        } else if (e.getSource() == editButton) {
            editableOff();
            dataModel = (DataModel) list.getSelectedValue();
            AttributeCreatorFrame atbFrame = new AttributeCreatorFrame(dataModel, this);
            atbFrame.getAtbEditor().getAtPanel().setAttributes(dataModel.getAttributes());
            atbFrame.setTitle(dataModel.name);
            atbFrame.getAtbEditor().getAtPanel().revalidate();
            atbFrame.getAtbEditor().getAtPanel().repaint();


            // System.out.println(dataModels.getSize());
        } else if (e.getSource() == deleteButton) {
            dataModels.delete(list.getSelectedIndices());
            list.setSelectedIndex(dataModels.getSize() - 1);
            //obnova show panelu po vymazani vsetkych datamodelov


        }

    }

    public void valueChanged(ListSelectionEvent e) {

        if (list.getSelectedIndices().length > 0) {
            setupShowPanel();
            System.out.println("tralal1");

        } else // dataModelSelectPanel.getShowModelPanel().getShowAtPanel().fillShowPanel((DataModel)list.getSelectedValue());
        {
            System.out.println("tralal2");
            removeShowPanelComponent();
        }
       isEditable = list.getSelectedIndices().length == 1;
        
        editButton.setEnabled(isEditable);

        isDeletable = list.getSelectedIndices().length > 0;
        deleteButton.setEnabled(isDeletable);

    }

    public void editableOff() {
        createButton.setEnabled(false);
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    public void editableOn() {
        createButton.setEnabled(true);
        editButton.setEnabled(true);
        deleteButton.setEnabled(true);

    }

    public void setupShowPanel() {
        dataModel = (DataModel) list.getSelectedValue();

        if (dataModel.getAttributes().getSize() > 0) {
            dataModelSelectPanel.getShowModelPanel().getShowAtPanel().fillShowPanel((DataModel) list.getSelectedValue());
            dataModelSelectPanel.getShowModelPanel().getPlace().upDatePlace((DataModel) list.getSelectedValue());
            dataModelSelectPanel.revalidate();

        }else{
            dataModelSelectPanel.getShowModelPanel().getShowAtPanel().fillShowPanel((DataModel) list.getSelectedValue());
             dataModelSelectPanel.revalidate();
        }

    }

    public void removeShowPanelComponent() {
        dataModelSelectPanel.getShowModelPanel().getShowAtPanel().removeAll();
        dataModelSelectPanel.getShowModelPanel().getShowAtPanel().revalidate();
        dataModelSelectPanel.getShowModelPanel().getShowAtPanel().repaint();

    }

    public JList getList() {
        return list;
    }

    public DataModels getDataModels() {
        return dataModels;
    }

    public void setDataModels(DataModels dataModels) {
        this.dataModels = dataModels;
    }

    public DataModel getSelectedDataModel() {
        return (DataModel) list.getSelectedValue();
    }


    public DataModelSelectPanel getDataModelSelectPanel() {
        return dataModelSelectPanel;
    }
}
