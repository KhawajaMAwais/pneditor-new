package net.matmas.pneditor.filechooser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class Sidebar extends JPanel implements ActionListener, PropertyChangeListener {

	private JFileChooser fileChooser;
	private JButton delete = new JButton("Delete file", GraphicsTools.getIcon("pneditor/Delete16.gif"));

	public Sidebar(JFileChooser fileChooser) {
		super();
		this.fileChooser = fileChooser;
		Preview preview = new Preview(fileChooser);
		setLayout(new BorderLayout());
		add(preview, BorderLayout.CENTER);
		add(delete, BorderLayout.NORTH);
		delete.addActionListener(this);
		fileChooser.addPropertyChangeListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == delete) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile != null && JOptionPane.showOptionDialog(
				fileChooser,
				"Delete " + selectedFile.getName() + "?\nThis action is irreversible!",
				"Delete",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				new String[] {"Delete permanently", "Cancel"},
				"Cancel") == JOptionPane.YES_OPTION
			) {
				selectedFile.delete();
				fileChooser.setSelectedFile(new File(""));
				fileChooser.rescanCurrentDirectory();
			}
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		delete.setEnabled(fileChooser.getSelectedFile() != null);
	}
	
}
