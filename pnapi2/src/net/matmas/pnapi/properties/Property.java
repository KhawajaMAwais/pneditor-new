package net.matmas.pnapi.properties;

import java.awt.Component;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.PetriNet;
import org.simpleframework.xml.Attribute;

/**
 *
 * @author matmas
 */
public abstract class Property {

	public Property() {
	}

	public Property(WithProperties withProperties) {
		this.withProperties = withProperties;
	}

	// -------------------------------------------------------------------------

	protected PetriNet getPetriNet() {
		if (withProperties instanceof PetriNet) {
			return (PetriNet)withProperties;
		}
		else if (withProperties instanceof Element) {
			return ((Element)withProperties).getPetriNet();
		}
		return null;
	}

	// -------------------------------------------------------------------------

	protected WithProperties withProperties;

	public WithProperties getWithProperties() {
		return withProperties;
	}

	public void setWithProperties(WithProperties withProperties) {
		this.withProperties = withProperties;
	}

	// -------------------------------------------------------------------------

	@Attribute(required=false)
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// -------------------------------------------------------------------------

	@Attribute
	private boolean idEditable = true;

	public boolean isIdEditable() {
		return idEditable;
	}

	public void setIdEditable(boolean idEditingDisabled) {
		this.idEditable = idEditingDisabled;
	}
	
	// -------------------------------------------------------------------------

	public abstract String getPropertyTypeName();
	public abstract Component getEditor();
}
