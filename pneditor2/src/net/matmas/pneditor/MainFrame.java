package net.matmas.pneditor;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import net.matmas.pneditor.actions.SelectFireToolAction;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import net.matmas.pneditor.actions.WbmImportAction;
import net.matmas.pneditor.actions.AboutAction;
import net.matmas.pneditor.actions.Action;
import net.matmas.pneditor.actions.AnalyzeAction;
import net.matmas.pneditor.actions.ArisImportAction;
import net.matmas.pneditor.actions.CopyAction;
import net.matmas.pneditor.actions.SelectSubnetToolAction;
import net.matmas.pneditor.actions.CutAction;
import net.matmas.pneditor.actions.DataModelAction;
import net.matmas.pneditor.actions.DeleteAction;
import net.matmas.pneditor.actions.DeployToServerAction;
import net.matmas.pneditor.actions.DynSoundAction;
import net.matmas.pneditor.actions.ImportOldPflowAction;
import net.matmas.pneditor.actions.NewFileAction;
import net.matmas.pneditor.actions.OpenFileAction;
import net.matmas.pneditor.actions.OpenPropertiesAction;
import net.matmas.pneditor.actions.OpenTimePropertiesAction;
import net.matmas.pneditor.actions.PTInvariantAction;
import net.matmas.pneditor.actions.PasteAction;
import net.matmas.pneditor.actions.SelectArcToolAction;
import net.matmas.pneditor.actions.SelectPlaceToolAction;
import net.matmas.pneditor.actions.QuitAction;
import net.matmas.pneditor.actions.CoverabilityAction;
import net.matmas.pneditor.actions.LogAction;
import net.matmas.pneditor.actions.RedoAction;
import net.matmas.pneditor.actions.SaveFileAction;
import net.matmas.pneditor.actions.SaveFileAsAction;
import net.matmas.pneditor.actions.SelectAllAction;
import net.matmas.pneditor.actions.SelectSelectionToolAction;
import net.matmas.pneditor.actions.SelectTokenToolAction;
import net.matmas.pneditor.actions.SelectTransitionToolAction;
import net.matmas.pneditor.actions.SetArcMultiplicityAction;
import net.matmas.pneditor.actions.SetLabelAction;
import net.matmas.pneditor.actions.SetTokensAction;
import net.matmas.pneditor.actions.SignAction;
import net.matmas.pneditor.actions.StartServerAction;
import net.matmas.pneditor.actions.StopServerAction;
import net.matmas.pneditor.actions.UndoAction;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class MainFrame extends JFrame implements WindowListener {

    
	public MainFrame(String title) {
		super(title);
                this.setTitle("PETRIFLOW");
		setupLayout();
		setupFrameIcons();
		setupMainMenu();
		setupToolbar();
		setupPopupMenus();
                setupLogInfo();
		int width = preferences.getInt(Setting.width.name(), 600);
		int height = preferences.getInt(Setting.height.name(), 500);
		this.setSize(width, height);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				preferences.putInt(Setting.width.name(), getWidth());
				preferences.putInt(Setting.height.name(), getHeight());
				try {
					preferences.flush();
				} catch (BackingStoreException ex) {
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}));

		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.setVisible(true);
		drawingBoard.getCanvas().requestFocusInWindow();
	}
	
	private Preferences preferences = Preferences.userNodeForPackage(this.getClass());

	// -------------------------------------------------------------------------

	private DrawingBoard drawingBoard = new DrawingBoard();

	public DrawingBoard getDrawingBoard() {
		return drawingBoard;
	}

	// -------------------------------------------------------------------------

	private void setupLayout() {
		this.add(drawingBoard, BorderLayout.CENTER);
	}
        
         // log info
            
        public JLabel infolog;
        public JPanel panel;
        public JButton interuptlogging = new JButton("Interrupt");
        private void setupLogInfo() {
            panel = new JPanel();
            interuptlogging.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               PNEditor.getInstance().getMainFrame().panel.setVisible(false);
               PNEditor.getInstance().getMainFrame().panel.repaint();
               PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().logtable.show();
               PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setIsLogging(false);
            }
        });
            ImageIcon  icon = new ImageIcon(GraphicsTools.class.getResource("/resources/pneditor/eye.gif"));
            infolog = new JLabel(icon);
            infolog.setText("Logging");
            panel.add(infolog);
            panel.add(interuptlogging);
            this.add(panel,BorderLayout.SOUTH);
            panel.setVisible(false);
        }
        
        //log info
        
	private Action newFileAction = new NewFileAction();
	private Action openFileAction = new OpenFileAction();
	private Action saveFileAction = new SaveFileAction();
	private Action saveFileAsAction = new SaveFileAsAction();
        private Action deployToServerAction = new DeployToServerAction();
        private Action ptInvariant = new PTInvariantAction();
       
        private Action CoverabilityAction = new CoverabilityAction();
        private Action DynSoundAction = new DynSoundAction();
        private Action SignAction = new SignAction();
        private Action StartServerAction = new StartServerAction();
        private Action StopServerAction = new StopServerAction();
        private Action logAction = new LogAction();
        private Action ImporOldPflowAction = new ImportOldPflowAction();
        private Action WbmImport = new WbmImportAction();
        private Action ArisImport = new ArisImportAction();
	private Action quitAction = new QuitAction();
	
	public Action undoAction = new UndoAction();
	public Action redoAction = new RedoAction();
	private Action cutAction = new CutAction();
	private Action copyAction = new CopyAction();
	private Action pasteAction = new PasteAction();
	private Action deleteAction = new DeleteAction();
	private Action selectAllAction = new SelectAllAction();

	private Action openPropertiesAction = new OpenPropertiesAction();
        private Action openTimePropertiesAction = new OpenTimePropertiesAction();
	private Action setLabelAction = new SetLabelAction();
	private Action setTokensAction = new SetTokensAction();
	private Action setArcMultiplicityAction = new SetArcMultiplicityAction();

	private Action selectSelectionToolAction = new SelectSelectionToolAction();
	private Action selectPlaceToolAction = new SelectPlaceToolAction();
	private Action selectTransitionToolAction = new SelectTransitionToolAction();
	private Action selectArcToolAction = new SelectArcToolAction();
	private Action selectTokenToolAction = new SelectTokenToolAction();
	private Action selectSubnetToolAction = new SelectSubnetToolAction();
	private Action selectFireToolAction = new SelectFireToolAction();
        private Action selectTool_DataModelAction = new DataModelAction(this);

        ///////////////////////////////DATAMODEL, INVARIANT/////////////////////////////////////////
        public JToggleButton dataModel = new JToggleButton(selectTool_DataModelAction);

	public JToggleButton selectSelectionToolToggleButton = new JToggleButton(selectSelectionToolAction);
	public JToggleButton selectPlaceToolToggleButton = new JToggleButton(selectPlaceToolAction);
	public JToggleButton selectTransitionToolToggleButton = new JToggleButton(selectTransitionToolAction);
	public JToggleButton selectArcToolToggleButton = new JToggleButton(selectArcToolAction);
	public JToggleButton selectTokenToolToggleButton = new JToggleButton(selectTokenToolAction);
	public JToggleButton selectSubnetToolToggleButton = new JToggleButton(selectSubnetToolAction);
	public JToggleButton selectFireToolToggleButton = new JToggleButton(selectFireToolAction);

	public JPopupMenu placePopup = new JPopupMenu();
	public JPopupMenu transitionPopup = new JPopupMenu();
	public JPopupMenu arcPopup = new JPopupMenu();
	public JPopupMenu subnetPopup = new JPopupMenu();
	public JPopupMenu canvasPopup = new JPopupMenu();

	private Action aboutAction = new AboutAction();

	private void setupMainMenu() {
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		// ---------------------------------------------------------------------

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		menuBar.add(editMenu);

		JMenu drawMenu = new JMenu("Draw");
		drawMenu.setMnemonic('D');
		menuBar.add(drawMenu);

		JMenu elementMenu = new JMenu("Element");
		elementMenu.setMnemonic('l');
		menuBar.add(elementMenu);

                JMenu analyzeMenu = new JMenu("Analyze");
		elementMenu.setMnemonic('A');
		menuBar.add(analyzeMenu);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		menuBar.add(helpMenu);

		// ---------------------------------------------------------------------

		fileMenu.add(newFileAction);
		fileMenu.add(openFileAction);
		fileMenu.add(saveFileAction);
		fileMenu.add(saveFileAsAction);
                fileMenu.add(ImporOldPflowAction);
                fileMenu.add(WbmImport);
                fileMenu.add(ArisImport);
		fileMenu.add(quitAction);

		editMenu.add(undoAction);
		editMenu.add(redoAction);
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		editMenu.add(deleteAction);
		editMenu.add(selectAllAction);

		drawMenu.add(selectSelectionToolAction);
		drawMenu.add(selectPlaceToolAction);
		drawMenu.add(selectTransitionToolAction);
		drawMenu.add(selectArcToolAction);
		drawMenu.add(selectTokenToolAction);
		drawMenu.add(selectSubnetToolAction);
		drawMenu.add(selectFireToolAction);

		elementMenu.add(setLabelAction);
		elementMenu.add(setTokensAction);
		elementMenu.add(setArcMultiplicityAction);
		elementMenu.add(openPropertiesAction);
                elementMenu.add(openTimePropertiesAction);

                analyzeMenu.add(new AnalyzeAction());
                analyzeMenu.add(selectTool_DataModelAction);
                analyzeMenu.add(deployToServerAction);
                analyzeMenu.add(ptInvariant);
                analyzeMenu.add(CoverabilityAction);
                analyzeMenu.add(DynSoundAction);
                analyzeMenu.add(SignAction);
                analyzeMenu.add(StartServerAction);
                analyzeMenu.add(StopServerAction);
                analyzeMenu.add(logAction);

		helpMenu.add(aboutAction);
	}

	private void setupFrameIcons() {
		List<Image> icons = new LinkedList<Image>();
		icons.add(GraphicsTools.getBufferedImage("icon16.png"));
		icons.add(GraphicsTools.getBufferedImage("icon32.png"));
		icons.add(GraphicsTools.getBufferedImage("icon48.png"));
		this.setIconImages(icons);
	}

	private void setupToolbar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		toolBar.add(newFileAction);
		toolBar.add(openFileAction);
		toolBar.add(saveFileAction);
		toolBar.add(saveFileAsAction);
                toolBar.add(ImporOldPflowAction);
                toolBar.add(WbmImport);
                toolBar.add(ArisImport);
		toolBar.addSeparator();

		toolBar.add(cutAction);
		toolBar.add(copyAction);
		toolBar.add(pasteAction);

		toolBar.addSeparator();
		toolBar.add(undoAction);
		toolBar.add(redoAction);
		toolBar.add(deleteAction);

		toolBar.addSeparator();
		toolBar.add(selectSelectionToolToggleButton);	selectSelectionToolToggleButton.setText("");
		toolBar.add(selectPlaceToolToggleButton);		selectPlaceToolToggleButton.setText("");
		toolBar.add(selectTransitionToolToggleButton);	selectTransitionToolToggleButton.setText("");
		toolBar.add(selectArcToolToggleButton);			selectArcToolToggleButton.setText("");
		toolBar.add(selectTokenToolToggleButton);		selectTokenToolToggleButton.setText("");
		toolBar.add(selectSubnetToolToggleButton);		selectSubnetToolToggleButton.setText("");
		toolBar.add(selectFireToolToggleButton);		selectFireToolToggleButton.setText("");

                toolBar.addSeparator();
		toolBar.add(new AnalyzeAction());
                toolBar.add(selectTool_DataModelAction);
                toolBar.add(ptInvariant);
                toolBar.add(CoverabilityAction);
                toolBar.add(DynSoundAction);
                toolBar.add(SignAction);

                toolBar.addSeparator();
                toolBar.add(StartServerAction);
                toolBar.add(StopServerAction);
                toolBar.add(deployToServerAction);
                toolBar.addSeparator();
                toolBar.add(logAction);

		ButtonGroup selectToolButtonGroup = new ButtonGroup();
		selectToolButtonGroup.add(selectSelectionToolToggleButton);
		selectToolButtonGroup.add(selectPlaceToolToggleButton);
		selectToolButtonGroup.add(selectTransitionToolToggleButton);
		selectToolButtonGroup.add(selectArcToolToggleButton);
		selectToolButtonGroup.add(selectTokenToolToggleButton);
		selectToolButtonGroup.add(selectSubnetToolToggleButton);
		selectToolButtonGroup.add(selectFireToolToggleButton);
		selectSelectionToolToggleButton.setSelected(true);
		
		toolBar.addSeparator();
		
		this.add(toolBar, BorderLayout.NORTH);
	}

	private void setupPopupMenus() {
		placePopup.add(setLabelAction);
		placePopup.add(setTokensAction);
		placePopup.add(openPropertiesAction);
                placePopup.add(openTimePropertiesAction);
		placePopup.addSeparator();
		placePopup.add(cutAction);
		placePopup.add(copyAction);
		placePopup.add(pasteAction);
		placePopup.add(deleteAction);

		transitionPopup.add(setLabelAction);
		transitionPopup.add(openPropertiesAction);
		transitionPopup.add(openTimePropertiesAction);
                transitionPopup.addSeparator();
		transitionPopup.add(cutAction);
		transitionPopup.add(copyAction);
		transitionPopup.add(pasteAction);
		transitionPopup.add(deleteAction);

		arcPopup.add(setArcMultiplicityAction);
		arcPopup.add(openPropertiesAction);
		arcPopup.add(openTimePropertiesAction);
                arcPopup.addSeparator();
		arcPopup.add(cutAction);
		arcPopup.add(copyAction);
		arcPopup.add(pasteAction);
		arcPopup.add(deleteAction);

		subnetPopup.add(cutAction);
		subnetPopup.add(copyAction);
		subnetPopup.add(pasteAction);
		subnetPopup.add(deleteAction);

		canvasPopup.add(pasteAction);
		canvasPopup.add(openPropertiesAction);
	}

	private enum Setting {
		width, height
	}

	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		PNEditor.getInstance().quitApplication();
	}

	// -------------------------------------------------------------------------

	public void refreshActions() {
		newFileAction.refresh();
		openFileAction.refresh();
		saveFileAction.refresh();
		saveFileAsAction.refresh();
		aboutAction.refresh();
		quitAction.refresh();
		undoAction.refresh();
		redoAction.refresh();
		cutAction.refresh();
		copyAction.refresh();
		pasteAction.refresh();
		deleteAction.refresh();
		selectAllAction.refresh();
		openPropertiesAction.refresh();
		setLabelAction.refresh();
		setTokensAction.refresh();
		setArcMultiplicityAction.refresh();
		selectSelectionToolAction.refresh();
		selectPlaceToolAction.refresh();
		selectTransitionToolAction.refresh();
		selectArcToolAction.refresh();
		selectTokenToolAction.refresh();
		selectSubnetToolAction.refresh();
		selectFireToolAction.refresh();
		

		drawingBoard.getCanvas().repaint();
	}

}
