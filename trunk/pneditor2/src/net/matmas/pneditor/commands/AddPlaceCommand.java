package net.matmas.pneditor.commands;

import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class AddPlaceCommand implements Command {

	private Point location;
	private Place createdPlace;
	private PetriNet petriNet;

	public AddPlaceCommand(Point location, PetriNet petriNet) {
		this.location = location;
		this.petriNet = petriNet;
	}
	
	public void execute() {
		createdPlace = new Place();
		createdPlace.setCenter(location);
		petriNet.addPlace(createdPlace);
	}

	public void undo() {
		new DeleteElementCommand(createdPlace).execute();
	}

	public void redo() {
		petriNet.addPlace(createdPlace);
	}

	public String getName() {
		return "Add place";
	}
	
	public Place getCreatedPlace() {
		return createdPlace;
	}
}
