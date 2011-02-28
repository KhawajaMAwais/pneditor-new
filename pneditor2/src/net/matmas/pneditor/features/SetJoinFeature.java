package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import net.matmas.pnapi.Element;
import net.matmas.pneditor.properties.TimePropertiesDialog;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;
import sun.awt.RepaintArea;

/**
 *
 * @author matmas
 */
public class SetJoinFeature extends Feature implements MouseListener {

	TimePropertiesDialog dialog;
	public SetJoinFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);

		if (canvas.SetOrJoin) {
				if (e.getButton() == MouseEvent.BUTTON1) {
                                    if (clickedElement instanceof Place) {
                                        Place placeKam = (Place)clickedElement;
                                        dialog = new TimePropertiesDialog(PNEditor.getInstance().getMainFrame(), placeKam, placeKam, null);
                                        Place place = dialog.choosenPlace;

                                        canvas.pomp.setORJoin(placeKam);
                                        canvas.SetOrJoin = false;
                                        dialog.show();
                                    }
                                }
		}

                if (canvas.SetiterJoin) {
				if (e.getButton() == MouseEvent.BUTTON1) {
                                    if (clickedElement instanceof Place) {
                                        Place placeKam = (Place)clickedElement;
                                        dialog = new TimePropertiesDialog(PNEditor.getInstance().getMainFrame(), placeKam, placeKam, null);
                                        Place place = dialog.choosenPlace;

                                        canvas.pomp.setIterationEnd(placeKam);
                                        canvas.SetiterJoin = false;
                                        dialog.show();
                                    }
                                }
		}

                if (canvas.SetAndJoin) {
				if (e.getButton() == MouseEvent.BUTTON1) {
                                    if (clickedElement instanceof Transition) {
                                        Transition placeKam = (Transition)clickedElement;
                                        dialog = new TimePropertiesDialog(PNEditor.getInstance().getMainFrame(), placeKam, placeKam, null);
                                        Transition trans = dialog.choosenTran;

                                        canvas.pompt.setANDJoin(placeKam);
                                        canvas.SetAndJoin = false;
                                        dialog.show();
                                    }
                                }
		}
	}

	

	public void drawMainLayer(Graphics g) {

	}
	public void drawBackground(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
        public void mouseEntered(MouseEvent e) {}


        public void mouseExited(MouseEvent e) {}

    @Override
    public void drawForeground(Graphics g) {}
}
