/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import kohary.datamodel.actions.Action;
import kohary.datamodel.actions.SelectDButtonAction;
import kohary.datamodel.actions.SelectDTextAreaAction;
import kohary.datamodel.actions.SelectDTextFieldAction;
import kohary.datamodel.actions.SelectDTextPaneAction;
import kohary.datamodel.util.WaitLayerUI;
import javax.swing.*;
import kohary.datamodel.actions.AddDImageAction;
import kohary.datamodel.actions.CreateHtmlAction;
import kohary.datamodel.actions.DRadiobuttonSetValueAction;
import kohary.datamodel.actions.DefaultFontsAction;
import kohary.datamodel.actions.DeleteAction;
import kohary.datamodel.actions.DisabledAction;
import kohary.datamodel.actions.OpenDocumentAction;
import kohary.datamodel.actions.OpenPropertiesAction;
import kohary.datamodel.actions.OpenSelectEditorAction;
import kohary.datamodel.actions.PlaceSettingAction;
import kohary.datamodel.actions.RedoAction;
import kohary.datamodel.actions.RulerSwitchAction;
import kohary.datamodel.actions.SaveDocumentAction;
import kohary.datamodel.actions.SelectDCheckBoxAction;
import kohary.datamodel.actions.SelectDComboBoxAction;
import kohary.datamodel.actions.SelectDRadioButtonAction;
import kohary.datamodel.actions.SelectSelectionAction;
import kohary.datamodel.actions.SelectVariableToInputAction;
import kohary.datamodel.actions.SetBackgroundColorAction;
import kohary.datamodel.actions.SetDataTypeAction;
import kohary.datamodel.actions.SetDefaultBooleanValueAction;
import kohary.datamodel.actions.SetDefaultRadioButtonInGroupAction;
import kohary.datamodel.actions.SetFixedImageAction;
import kohary.datamodel.actions.SetImageTypeAction;
import kohary.datamodel.actions.SetLabelAction;
import kohary.datamodel.actions.SetNextDataModelAfterFireTransition;
import kohary.datamodel.actions.UndoAction;
import kohary.datamodel.actions.VariableEditingAction;

/**
 *
 * @author Godric
 */
public class MainFrame extends JFrame {

    public MainFrame() {

        int width = preferences.getInt("width", 600);
        int height = preferences.getInt("height", 500);
        setSize(new Dimension(width, height));
        setupLayout();
        setUpToolbar();
        setUpMenuBar();
//        setVisible(false);
        setupPopupMenus();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                preferences.putInt("width", getWidth());
                preferences.putInt("height", getHeight());
                try {
                    preferences.flush();
                } catch (BackingStoreException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
        disableToolBar();
        validate();
        setTitleUndefined();
    }
    //------------------------------------------------------------
    private ModellingBoard modellingBoard = new ModellingBoard();

    public ModellingBoard getModellingBoard() {
        return modellingBoard;
    }
    //------------------------------------------------------------
    private SelectDatamodelPanel selectDataModelPanel = new SelectDatamodelPanel();

    public SelectDatamodelPanel getSelectDataModelPanel() {
        return selectDataModelPanel;
    }
    //--------------------------------------------------------------
    final WaitLayerUI layerUI = new WaitLayerUI();

    public WaitLayerUI getLayerUI() {
        return layerUI;
    }

    //-------------------------------------------------------------
    private void setupLayout() {


        JLayer<ModellingBoard> jlayer = new JLayer<ModellingBoard>(modellingBoard, layerUI);
        this.add(jlayer, BorderLayout.CENTER);
        //this.add(modellingBoard, BorderLayout.CENTER);
        this.add(selectDataModelPanel, BorderLayout.WEST);


    }
    //-----------------------------------------------------------
    private Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    //actions
    //
    private void setUpMenuBar() {
        JMenuBar menubar = new JMenuBar();

        JMenu view = new JMenu("View");
        JMenu edit = new JMenu("Edit");
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu tools = new JMenu("Tools");

        //-------------------FIle------------------------------
        JMenuItem newProject = new JMenuItem("New project");
        JMenuItem saveProject = new JMenuItem(saveProjectAction);
        JMenuItem openProject = new JMenuItem(openProjectAction);
        JMenuItem exportHtml = new JMenuItem(createHtmlAction);

        file.add(newProject);
        file.addSeparator();
        file.add(saveProject);
        file.add(openProject);
        file.addSeparator();
        file.add(exportHtml);
        //-------------------Edit---------------------------------
        JMenuItem undo = new JMenuItem(undoAction);
        JMenuItem redo = new JMenuItem(redoAction);
        JMenuItem delete = new JMenuItem(deleteAction);

        edit.add(undo);
        edit.add(redo);
        edit.addSeparator();
        edit.add(delete);

        //----------------------View-----------------------------

        JMenuItem ruler = new JMenuItem(rulerSwitch);
        view.add(ruler);

        //----------------------Tools------------------

        JMenuItem associacionMenu = new JMenuItem(associacionSrtting);
        tools.add(associacionMenu);
        JMenuItem variableMenu = new JMenuItem(variableEditingAction);
        tools.add(variableMenu);


        menubar.add(file);
        menubar.add(edit);
        menubar.add(view);
        menubar.add(tools);
        menubar.add(help);
        this.setJMenuBar(menubar);
    }
    public Action openComboBoxEditor = new OpenSelectEditorAction();
    public Action setValueAction = new DRadiobuttonSetValueAction();
    public Action openProjectAction = new OpenDocumentAction();
    public Action saveProjectAction = new SaveDocumentAction();
    public Action choosingVariableAction = new SelectVariableToInputAction();
    private Action variableEditingAction = new VariableEditingAction();
    private Action setImageTypeAction = new SetImageTypeAction();
    private Action fixedImageAction = new SetFixedImageAction();
    private Action disabledAction = new DisabledAction();
    private Action addImageAction = new AddDImageAction();
    private Action setNextDataModelAction = new SetNextDataModelAfterFireTransition();
    private Action associacionSrtting = new PlaceSettingAction();
    private Action createHtmlAction = new CreateHtmlAction();
    private Action setBackGroundColorAction = new SetBackgroundColorAction();
    private Action defaultFontSettingAction = new DefaultFontsAction();
    private Action openPropertiesAction = new OpenPropertiesAction();
    private Action setDefaultRadioButtonAction = new SetDefaultRadioButtonInGroupAction();
    private Action setDefaultBooleanAction = new SetDefaultBooleanValueAction();
    private Action setDatatype = new SetDataTypeAction();
    private Action setLabelAction = new SetLabelAction();
    public Action deleteAction = new DeleteAction();
    private Action rulerSwitch = new RulerSwitchAction();
    private Action selectDCombobox = new SelectDComboBoxAction();
    private Action selectDCheckBox = new SelectDCheckBoxAction();
    private Action selectDRadioButton = new SelectDRadioButtonAction();
    private Action selectDTextField = new SelectDTextFieldAction();
    private Action selectDTextArea = new SelectDTextAreaAction();
    private Action selectDButton = new SelectDButtonAction();
    private Action selectTextPane = new SelectDTextPaneAction();
    private Action selectSelection = new SelectSelectionAction();
    public Action undoAction = new UndoAction();
    public Action redoAction = new RedoAction();
    public JButton undoButton = new JButton(undoAction);
    public JButton redoButton = new JButton(redoAction);
    public JButton addImage = new JButton(addImageAction);
    public JButton setBackGroundButton = new JButton(setBackGroundColorAction);
    public JButton deleteAttributeButton = new JButton(deleteAction);
    public JToggleButton dTextAreaButton = new JToggleButton(selectDTextArea);
    public JToggleButton dTextFieldButton = new JToggleButton(selectDTextField);
    public JToggleButton dButtonButton = new JToggleButton(selectDButton);
    public JToggleButton dTextPaneButton = new JToggleButton(selectTextPane);
    public JToggleButton selectButton = new JToggleButton(selectSelection);
    public JToggleButton rulerButton = new JToggleButton(rulerSwitch);
    public JToggleButton setLabelButton = new JToggleButton(setLabelAction);
    public JToggleButton dComboBoxButton = new JToggleButton(selectDCombobox);
    public JToggleButton dCheckBoxButton = new JToggleButton(selectDCheckBox);
    public JToggleButton dRadioButton = new JToggleButton(selectDRadioButton);
    public JToggleButton defaultFontSettingButton = new JToggleButton(defaultFontSettingAction);
    public JToggleButton variableButton = new JToggleButton(variableEditingAction);
    JToolBar toolbar = new JToolBar();

    private void setUpToolbar() {

        ButtonGroup buttonGroupTools = new ButtonGroup();

        toolbar.setFloatable(false);
        toolbar.setOrientation(JToolBar.VERTICAL);

        buttonGroupTools.add(dTextFieldButton);
        buttonGroupTools.add(dTextAreaButton);
        buttonGroupTools.add(dButtonButton);
        buttonGroupTools.add(selectButton);
        buttonGroupTools.add(dTextPaneButton);
        buttonGroupTools.add(dComboBoxButton);
        buttonGroupTools.add(dCheckBoxButton);
        buttonGroupTools.add(dRadioButton);


        toolbar.add(selectButton);
        toolbar.add(deleteAttributeButton);
        toolbar.add(undoButton);
        toolbar.add(redoButton);
        toolbar.add(deleteAttributeButton);
        toolbar.add(rulerButton);

        toolbar.addSeparator();
        toolbar.add(dTextFieldButton);
        toolbar.add(dTextAreaButton);
        toolbar.add(dButtonButton);
        toolbar.add(dComboBoxButton);
        toolbar.add(dCheckBoxButton);
        toolbar.add(dRadioButton);
        toolbar.addSeparator();
        toolbar.add(dTextPaneButton);
        toolbar.add(defaultFontSettingButton);
        toolbar.addSeparator();
        toolbar.add(setBackGroundButton);
        toolbar.add(addImage);


        this.add(toolbar, BorderLayout.EAST);
    }

    public final void disableToolBar() {
        for (Component component : toolbar.getComponents()) {
            if (component instanceof JButton || component instanceof JToggleButton) {
                AbstractButton button = (AbstractButton) component;
                if (!(button.getAction() instanceof DeleteAction || button.getAction() instanceof UndoAction || button.getAction() instanceof RedoAction)) {
                    button.setEnabled(false);
                }
            }

        }
    }

    public final void enableToolBar() {
        for (Component component : toolbar.getComponents()) {
            if (component instanceof JButton || component instanceof JToggleButton) {
                AbstractButton button = (AbstractButton) component;
                if (!(button.getAction() instanceof DeleteAction || button.getAction() instanceof UndoAction || button.getAction() instanceof RedoAction)) {
                    button.setEnabled(true);
                }
            }

        }
    }
    public JPopupMenu checkBoxPopup = new JPopupMenu();
    public JPopupMenu comboBoxPopup = new JPopupMenu();
    public JPopupMenu radioButtonPopup = new JPopupMenu();
    public JPopupMenu textfieldPopup = new JPopupMenu();
    public JPopupMenu textareaPopup = new JPopupMenu();
    public JPopupMenu labelPopup = new JPopupMenu();
    public JPopupMenu buttonPopup = new JPopupMenu();
    public JPopupMenu textpanePopup = new JPopupMenu();
    public JPopupMenu imagePopup = new JPopupMenu();
    public JCheckBoxMenuItem setFixItem = new JCheckBoxMenuItem(fixedImageAction);
    public JCheckBoxMenuItem setDisabledTextField = new JCheckBoxMenuItem(disabledAction);
    public JCheckBoxMenuItem setDisabledTextArea = new JCheckBoxMenuItem(disabledAction);
    public JCheckBoxMenuItem setDisabledCheckBox = new JCheckBoxMenuItem(disabledAction);
    public JCheckBoxMenuItem setDisabledComboBox = new JCheckBoxMenuItem(disabledAction);
    public JCheckBoxMenuItem setDisabledRadioButton = new JCheckBoxMenuItem(disabledAction);

    private void setupPopupMenus() {

        textfieldPopup.add(setDatatype);
        textfieldPopup.add(setLabelAction);
        textfieldPopup.add(choosingVariableAction);
        textfieldPopup.add(setDisabledTextField);
        textareaPopup.addSeparator();
        textfieldPopup.add(deleteAction);

        textareaPopup.add(setDatatype);
        textareaPopup.add(setLabelAction);
        textareaPopup.add(choosingVariableAction);
        textareaPopup.add(setDisabledTextArea);
        textareaPopup.addSeparator();
        textareaPopup.add(deleteAction);

        checkBoxPopup.add(setDefaultBooleanAction);
        checkBoxPopup.add(setLabelAction);
        checkBoxPopup.add(choosingVariableAction);
        checkBoxPopup.add(setDisabledCheckBox);
        checkBoxPopup.addSeparator();
        checkBoxPopup.add(deleteAction);

        radioButtonPopup.add(setDefaultRadioButtonAction);
        radioButtonPopup.add(setLabelAction);
        radioButtonPopup.add(choosingVariableAction);
        radioButtonPopup.add(setValueAction);
        radioButtonPopup.add(setDisabledRadioButton);
        radioButtonPopup.addSeparator();
        radioButtonPopup.add(deleteAction);

        comboBoxPopup.add(setLabelAction);
        comboBoxPopup.add(choosingVariableAction);
        comboBoxPopup.add(openComboBoxEditor);
        comboBoxPopup.add(setDisabledComboBox);
        comboBoxPopup.addSeparator();
        comboBoxPopup.add(deleteAction);

        buttonPopup.add(setNextDataModelAction);
        buttonPopup.add(setImageTypeAction);
        buttonPopup.add(deleteAction);


        imagePopup.add(setImageTypeAction);
        imagePopup.add(deleteAction);
        imagePopup.add(setFixItem);



    }

    public JToolBar getToolbar() {
        return toolbar;
    }

    @Override
    public void setTitle(String projectName) {
        super.setTitle("DatamodelCreator - " + projectName);
    }

    public final void setTitleUndefined() {
        super.setTitle("DatamodelCreator - [undefined]");
    }
}
