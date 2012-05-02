/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import kohary.datamodel.dapi.DTextPane;
import kohary.datamodel.dapi.Text;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.FontSettings;

/**
 *
 * @author Godric
 */
public class FontSelectorPanel extends JToolBar implements ChangeListener, ItemListener {

    private JComboBox fonts, styles;
    private ColorToolbarIndicator colorIndicator;
    private JSpinner sizes;
    private String fontChoice;
    private int styleChoice;
    private int sizeChoice;
    private FontSettings fontSettings = null;
    private Color colorChoice;

    public FontSelectorPanel() {
        init();
    }

    public FontSelectorPanel(Text textElement) {
        this.fontSettings = textElement.getFontSettings();
        fontChoice = textElement.getFontSettings().getFamilyFont();
        sizeChoice = textElement.getFontSettings().getSizeFont();
        styleChoice = textElement.getFontSettings().getStyleFont();
        init();
    }

    public FontSelectorPanel(FontSettings fontSettings) {
        this.fontSettings = fontSettings;
        fontChoice = fontSettings.getFamilyFont();
        sizeChoice = fontSettings.getSizeFont();
        styleChoice = fontSettings.getStyleFont();
        init();
        setVisible(true);
    }

    public void setTextElement(Text textElement) {
        this.fontSettings = textElement.getFontSettings();
        fontChoice = textElement.getFontSettings().getFamilyFont();
        sizeChoice = textElement.getFontSettings().getSizeFont();
        styleChoice = textElement.getFontSettings().getStyleFont();
        colorChoice = textElement.getFontSettings().getColor();
        setUpItems();
        setVisible(true);
    }

    public void setDefaultFont(FontSettings fontSettings) {
        this.fontSettings = fontSettings;
        fontChoice = fontSettings.getFamilyFont();
        sizeChoice = fontSettings.getSizeFont();
        styleChoice = fontSettings.getStyleFont();
        colorChoice = fontSettings.getColor();
        setUpItems();
        setVisible(true);
    }

    public FontSettings getFontSettings() {
        return fontSettings;
    }

    private void setUpItems() {
        fonts.setSelectedItem(fontChoice);
        sizes.setValue(sizeChoice);
        styles.setSelectedItem(styleChoice);
        colorIndicator.setBackground(colorChoice);
    }

    private void init() {

        setName("Fonts");
        add(new JLabel("Font family:"));
        GraphicsEnvironment gEnv =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        fonts = new JComboBox(gEnv.getAvailableFontFamilyNames());

        fonts.addItemListener(this);
        add(fonts);
        add(new JLabel("Style:"));
        String[] styleNames = {"Plain", "Bold", "Italic", "Bold Italic"};

        styles = new JComboBox(styleNames);
        styles.addItemListener(this);
        add(styles);

        add(new JLabel("Size:"));

        sizes = new JSpinner(new SpinnerNumberModel(8, 6, 36, 1));
        //
        sizes.addChangeListener(this);
        add(sizes);
        add(new JLabel("Color:"));
        colorIndicator = new ColorToolbarIndicator(this);
        add(colorIndicator);
        setVisible(false);

    }

    public void stateChanged(ChangeEvent e) {
        try {
            String size = sizes.getModel().getValue().toString();
            sizeChoice = Integer.parseInt(size);
            fontSettings.setSizeFont(sizeChoice);
        } catch (NumberFormatException nfe) {
        }
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }
        if (e.getSource() == fonts) {
            this.fontChoice = (String) fonts.getSelectedItem();
            fontSettings.setFamilyFont(fontChoice);
        } else {
            this.styleChoice = (int) styles.getSelectedIndex();
            fontSettings.setStyleFont(styleChoice);
        }

        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }
}
