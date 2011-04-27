/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.home;

import kohary.datamodel.creator.actions.AboutAction;
import kohary.datamodel.creator.actions.ExitAction;
import kohary.datamodel.creator.actions.LoadAction;
import kohary.datamodel.creator.actions.PlaceSettingAction;
import kohary.datamodel.creator.actions.SaveAction;
import kohary.datamodel.creator.actions.SaveSepareAction;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.actions.Action;



/**
 *
 * @author Godric
 */
public class MainDataModelFrame extends JFrame{
    private DataModelPositionPanel dmpPanel;
    private CanvasPanel canvas;
   /// private SelectAndEditModelPanel<DataModel> semPanel;
  private PetriNet petriNet;
  private JMenuBar menuBar;
  private JMenu menu,help,setPositions;
  private JMenuItem about,save,saveWith,close,visual,manual,load,exit;
  private JToolBar toolBar;
  private Action aboutAction,positionVAction,positionMAction,saveAction,saveSepareAction,loadAction,exitAction;
    
    public MainDataModelFrame(PetriNet petriNet) {
        this.petriNet = petriNet;
        setSize(550, 400);
        setLocation(300,300);
        setLayout(new BorderLayout());
        setTitle("Datamodel Creator");

        canvas = new CanvasPanel(petriNet);
        add(canvas,BorderLayout.CENTER);

      //  ImageIcon iconClose = new ImageIcon("src/resources/datamodel/exit.png");
     //   ImageIcon iconAbout = new ImageIcon("src/resources/pneditor/About16.gif");
       // ImageIcon iconSave = new ImageIcon("src/resources/datamodel/save16.gif");
      //  ImageIcon iconLoad = new ImageIcon("src/resources/datamodel/import.gif");
      //  ImageIcon iconHelp = new ImageIcon("src/resources/datamodel/about2.gif");
       // ImageIcon iconFile = new ImageIcon("src/resources/datamodel/file.gif");
      //  ImageIcon iconPosition = new ImageIcon("src/resources/datamodel/formsIcon.png");
      //  ImageIcon iconVisual = new ImageIcon("src/resources/datamodel/arc.gif");
      //  ImageIcon iconManual = new ImageIcon("src/resources/datamodel/hamer.gif");
         aboutAction = new AboutAction(this);
         positionVAction = new PlaceSettingAction(canvas, 1); //visual
         positionMAction = new PlaceSettingAction(canvas, 2); //manual
         saveAction = new SaveAction(canvas);
         saveSepareAction = new SaveSepareAction(canvas);
         loadAction = new LoadAction(canvas, petriNet);
         exitAction = new ExitAction(this);

        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);

        setPositions = new JMenu("Setting positions");
        setPositions.setMnemonic(KeyEvent.VK_P);

        help = new JMenu("Help");        
        help.setMnemonic(KeyEvent.VK_H);

        menu.add(saveAction);
        menu.add(saveSepareAction);
        menu.add(loadAction);
        menu.add(exitAction);

        setPositions.add(positionMAction);
        setPositions.add(positionVAction);

        help.add(aboutAction);

        //about = new JMenuItem("About");
        //about.setMnemonic(KeyEvent.VK_A);
        //about.addActionListener(new AboutAction(this));

              
       // menu.add(saveWith);
        
        menuBar.add(menu);
        menuBar.add(setPositions);
        menuBar.add(help);


        
        setJMenuBar(menuBar);
        
        validate();
        setVisible(true);
    }


}
