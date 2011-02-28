package net.matmas.pneditor;

import java.awt.Frame;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Document;

/**
 *
 * @author matmas
 */
public class PNEditor {

	public PNEditor() {
		Preferences preferences = Preferences.userNodeForPackage(this.getClass());
		setCurrentDirectory(new File(preferences.get("current directory", System.getProperty("user.home"))));
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				Preferences preferences = Preferences.userNodeForPackage(this.getClass());
				preferences.put("current directory", getCurrentDirectory().toString());
				try {
					preferences.flush();
				} catch (BackingStoreException ex) {
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}));
	}

	// Singleton ---------------------------------------------------------------

	private static PNEditor instance = new PNEditor();
	
	public static PNEditor getInstance() {
		return instance;
	}

	// -------------------------------------------------------------------------

	private UndoManager undoManager = new UndoManager();

	public UndoManager getUndoManager() {
		return undoManager;
	}

	// -------------------------------------------------------------------------

	private MainFrame mainFrame = new MainFrame(getNewWindowTitle());

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	// -------------------------------------------------------------------------

	private Document document = new Document();

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	// -------------------------------------------------------------------------

	private ToolSelector toolSelector = new ToolSelector();

	public ToolSelector getToolSelector() {
		return toolSelector;
	}

	// -------------------------------------------------------------------------

	private LocalClipboard clipboard = new LocalClipboard();

	public LocalClipboard getClipboard() {
		return clipboard;
	}
	
	// -------------------------------------------------------------------------

	private File currentFile = null;

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
		mainFrame.setTitle(getNewWindowTitle());
	}

	// -------------------------------------------------------------------------

	protected File currentDirectory;

	public File getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	// -------------------------------------------------------------------------

	public void quitApplication() {
		if (!undoManager.isDocumentModified()) {
			System.exit(0);
		}
		mainFrame.setState(Frame.NORMAL);
		mainFrame.setVisible(true);
		int answer = JOptionPane.showOptionDialog(
			mainFrame,
			"Any unsaved changes will be lost. Really quit?",
			"Quit",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null,
			new String[] {"Quit", "Cancel"},
			"Cancel");
		if (answer == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// -------------------------------------------------------------------------

	private static final String APP_NAME = "PNEditor";
	private static final String APP_VERSION = "20100105";
	
	public static String getAppShortName() {
		return APP_NAME;
	}

	public static String getAppLongName() {
		return APP_NAME + ", development version " + APP_VERSION;
	}
	
	// -------------------------------------------------------------------------

	public String getNewWindowTitle() {
		String windowTitle = "";
		if (getCurrentFile() != null) {
			windowTitle += getCurrentFile().getName();
		}
		else {
			windowTitle += "Untitled";
		}
		if (undoManager.isDocumentModified()) {
			windowTitle += " [modified]";
		}
		windowTitle += " - " + getAppShortName();
		return windowTitle;
	}

	// -------------------------------------------------------------------------
}
