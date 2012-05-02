/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.DTextArea;
import kohary.datamodel.dapi.DTextField;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class InputPopUpFeature extends Feature implements MouseListener {

    JPopupMenu popUpMenu;
    Element element;

    public InputPopUpFeature(Canvas canvas) {
        super(canvas);
        canvas.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            Element clickedElement = getClickedElement(e.getPoint());
            MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();


            setSelection(clickedElement);
            if (clickedElement instanceof DTextField) {

                DTextField textfield = (DTextField) clickedElement;
                mainFrame.setDisabledTextField.setState(textfield.isDisabled());
                showPopup(mainFrame.textfieldPopup, e.getPoint().x, e.getPoint().y);
            } else if (clickedElement instanceof DTextArea) {
                DTextArea textArea = (DTextArea) clickedElement;
                mainFrame.setDisabledTextArea.setState(textArea.isDisabled());
                showPopup(mainFrame.textareaPopup, e.getPoint().x, e.getPoint().y);
            } else if (clickedElement instanceof DCheckBox) {
                DCheckBox checkBox = (DCheckBox) clickedElement;
                mainFrame.setDisabledCheckBox.setState(checkBox.isDisabled());
                showPopup(mainFrame.checkBoxPopup, e.getPoint().x, e.getPoint().y);
            } else if (clickedElement instanceof DRadioButton) {
                DRadioButton radiobutton = (DRadioButton) clickedElement;
                mainFrame.setDisabledRadioButton.setState(radiobutton.isDisabled());
                showPopup(mainFrame.radioButtonPopup, e.getPoint().x, e.getPoint().y);
            } else if (clickedElement instanceof DComboBox) {
                DComboBox comboBox = (DComboBox) clickedElement;
                mainFrame.setDisabledComboBox.setState(comboBox.isDisabled());
                showPopup(mainFrame.comboBoxPopup, e.getPoint().x, e.getPoint().y);
            } else if (clickedElement instanceof DButton) {
                showPopup(mainFrame.buttonPopup, e.getPoint().x, e.getPoint().y);
            } else if (clickedElement instanceof DImage) {
                DImage image = (DImage) clickedElement;
                mainFrame.setFixItem.setState(image.isFixed());
                showPopup(mainFrame.imagePopup, e.getPoint().x, e.getPoint().y);
            }
        }

    }

    private void setSelection(Element clickedElement) {

        if (clickedElement != null) {
            canvas.getSelection().clear();
            canvas.getSelection().add(clickedElement);
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    private void showPopup(JPopupMenu popupMenu, int clickedX, int clickedY) {
        popupMenu.show(canvas, clickedX + 1, clickedY + 1);
    }

    @Override
    public void drawBackground(Graphics g) {
    }

    @Override
    public void drawMainLayer(Graphics g) {
    }

    @Override
    public void drawForeground(Graphics g) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
