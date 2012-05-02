/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.Datamodels;
import kohary.datamodel.MainFrame;
import kohary.datamodel.commands.AddButtonAttributeCommand;
import kohary.datamodel.commands.AddCheckBoxAttributeCommand;
import kohary.datamodel.commands.AddComboBoxCommand;
import kohary.datamodel.commands.AddRadioButtonCommand;
import kohary.datamodel.commands.AddTextAreaAttributeCommand;
import kohary.datamodel.commands.AddTextFieldAttributeCommand;
import kohary.datamodel.commands.AddTextPaneCommand;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.RadioButtonGroup;
import kohary.datamodel.dapi.RadioButtonGroups;

/**
 *
 * @author Godric
 */
public class ElementMakerFeature extends Feature implements MouseListener {

    public ElementMakerFeature(Canvas canvas) {
        super(canvas);
        canvas.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        Point clickedPoint = getClickedPoint(e);
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectTextField()) {
            if (e.getButton() == MouseEvent.BUTTON1) {

                DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddTextFieldAttributeCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel()));
                canvas.repaint();
                Datamodels datamodel = DatamodelCreator.getInstance().getDocument().getDataModels();

            }
        }
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectTextArea()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddTextAreaAttributeCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel()));
                canvas.repaint();
                Datamodels datamodel = DatamodelCreator.getInstance().getDocument().getDataModels();


            }
        }
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectButton()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddButtonAttributeCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel()));
                canvas.repaint();
                Datamodels datamodel = DatamodelCreator.getInstance().getDocument().getDataModels();


            }
        }
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectCheckBox()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddCheckBoxAttributeCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel()));
                canvas.repaint();
                Datamodels datamodel = DatamodelCreator.getInstance().getDocument().getDataModels();


            }
        }
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectComboBox()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddComboBoxCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel()));
                canvas.repaint();
                Datamodels datamodel = DatamodelCreator.getInstance().getDocument().getDataModels();


            }
        }
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectTextPane()) {
            MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
            if (e.getButton() == MouseEvent.BUTTON1) {

                String text = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Your text:",
                        "TextPane",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                if (text != null && !text.equals("")) {
                    DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddTextPaneCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel(), text));
                    canvas.repaint();
                    Datamodels datamodel = DatamodelCreator.getInstance().getDocument().getDataModels();
                }

            }
        }
        if (DatamodelCreator.getInstance().getSelectionManager().isSelectRadioButton()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();

                List<Attribute> attributes = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributes();
                RadioButtonGroups radiobuttonGroups = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getRadioButtonGroups();
                RadioButtonGroup radioButtonGroup = null;

                if (radiobuttonGroups.getRadioButtonGroups().isEmpty()) {
//
//
                    int n = JOptionPane.showConfirmDialog(
                            mainFrame,
                            "There is no RadioButtonGroup, do you want create one?",
                            "No RadioButtonGroup",
                            JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.YES_OPTION == n) {
                        radioButtonGroup = radiobuttonGroups.AddNewWithReturn();
                    }
                } else {
                    Object[] options = {"Create new group",
                        "Select from existing",
                        "Cancel"};
                    int n = JOptionPane.showOptionDialog(mainFrame,
                            "",
                            "Radiobutton group",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[2]);

                    if (n == JOptionPane.NO_OPTION) {


                        radioButtonGroup = (RadioButtonGroup) JOptionPane.showInputDialog(
                                mainFrame,
                                "Select radiobuttonGroup:\n",
                                "Radiobutton group",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                radiobuttonGroups.getRadioButtonGroups().toArray(),
                                0);
                    } else if (n == JOptionPane.YES_OPTION) {
                        radioButtonGroup = radiobuttonGroups.AddNewWithReturn();
                    }
                }


                if (radioButtonGroup != null) {
                    DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddRadioButtonCommand(clickedPoint, (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel(), radioButtonGroup));
                    canvas.repaint();
                }


            }
        }

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void drawMainLayer(Graphics g) {
    }

    @Override
    public void drawForeground(Graphics g) {
    }

    @Override
    public void drawBackground(Graphics g) {
    }
}
