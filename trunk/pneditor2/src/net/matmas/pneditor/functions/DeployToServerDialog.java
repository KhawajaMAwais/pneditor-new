package net.matmas.pneditor.functions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import kohary.datamodel.creator.home.CanvasPanel;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pnapi.xml.XmlDocument;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.actions.OpenXmlDatamodelAction;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author matmas
 */
public class DeployToServerDialog extends JDialog {

    private String stringXmlDataModelDocument="";
    private OpenXmlDatamodelAction openAction = new OpenXmlDatamodelAction(stringXmlDataModelDocument,this);    
    private JTextField server = new JTextField();
    private JTextField username = new JTextField("admin");
    private JTextField dataModel = new JTextField();
    private JTextField password = new JPasswordField("admin");
    private JButton button = new JButton("Deploy");
    private JButton browseDataModel = new JButton("Import DataModel");


	public DeployToServerDialog(JFrame parentFrame, WithProperties withProperties) {
		super(parentFrame);
           
		this.setTitle("Deploy to server");

		this.setSize(320, 150);
		this.setLocationRelativeTo(getParent());
        this.getContentPane().setLayout(new GridLayout(5, 2));
        
        // form elements
        this.add(new JLabel("Server: "));
        server.setPreferredSize(new Dimension(240, 25));
        server.setText("http://localhost:8080/wfms");
        this.add(server);
        

        this.add(new JLabel("User:"));
        username.setPreferredSize(new Dimension(240, 25));
        this.add(username);

        this.add(new JLabel("Password:"));
        password.setPreferredSize(new Dimension(240, 25));
        this.add(password);


        this.add(new JLabel("Datamodel"));
        browseDataModel.addActionListener(openAction);
        this.add(browseDataModel);
        
        this.add(button);

        this.setVisible(true);

        final DeployToServerDialog dialog = this;

        // action
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // odoslanie XML ako POST
                URL url = null;
                URLConnection urlConn = null;
                DataOutputStream    printout;
                
                try {
                    // URL of server
                    url = new URL (dialog.server.getText() + "/UploadServlet");
                    // URL connection channel.
                    urlConn = url.openConnection();
                    // Let the run-time system (RTS) know that we dont want input.
                    urlConn.setDoInput (true);
                    // Let the RTS know that we want to do output.
                    urlConn.setDoOutput (true);
                    // No caching, we want the real thing.
                    urlConn.setUseCaches (false);
                    // Specify the content type.
                    urlConn.setRequestProperty
                    ("Content-Type", "application/x-www-form-urlencoded");

                  
                    // vygenerovanie XML
                    ByteArrayOutputStream xmlOutput = new ByteArrayOutputStream();
                    Serializer serializer = new Persister();
                    serializer.write(new XmlDocument(PNEditor.getInstance().getDocument()), xmlOutput);

                    // Send POST output.
                    printout = new DataOutputStream(urlConn.getOutputStream());
                    printout.writeBytes ("username="+dialog.username.getText()+"&password="+dialog.password.getText()
                            +"&xml="+URLEncoder.encode(xmlOutput.toString("UTF-8"), "UTF-8")
                            +"&xmldatamodel="+dialog.openAction.getOutput());
                    printout.flush ();
                    printout.close ();

                    // Get response data.
                    BufferedReader input = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    String str = input.readLine();
                    System.out.println(str);
                    if(str.equalsIgnoreCase("Authentication failed!")) {
                        JOptionPane.showMessageDialog(rootPane, str);
                        return;
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
                

                // otvorenie browsra
                if( !java.awt.Desktop.isDesktopSupported() ) {
                    JOptionPane.showMessageDialog(rootPane, "Desktop is not supported (fatal)");
                    return;
                }

                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

                if( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {
                    JOptionPane.showMessageDialog(rootPane, "Desktop doesn't support the browse action (fatal)");
                    return;
                }

                try {
                    java.net.URI uri = new java.net.URI( dialog.server.getText() );
                    desktop.browse( uri );
                }
                catch ( Exception exp ) {
                    JOptionPane.showMessageDialog(rootPane, exp.getMessage());
                }

                dialog.setVisible(false);
            }
        });

        
	}
}
