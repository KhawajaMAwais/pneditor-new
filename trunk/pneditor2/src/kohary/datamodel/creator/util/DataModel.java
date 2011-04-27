/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.util;

import kohary.datamodel.creator.attribute.Attribute;
import kohary.datamodel.creator.home.ListEditorModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class DataModel {

    public String name;
    public int ID=99999;            ///default
    public Map<RoleDefinitionProperty, Set<Transition>> position = new HashMap<RoleDefinitionProperty, Set<Transition>>();
    public ListModel<Attribute> attributes = new ListModel<Attribute>() {
        @Override
        public void addNew(ListEditorModel list) {
        }
        @Override
        public void addNew() {        
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ListModel<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ListModel<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<RoleDefinitionProperty, Set<Transition>> getPosition() {
        return position;
    }

    public void setPosition(Map<RoleDefinitionProperty, Set<Transition>> position) {
        this.position = position;
    }



    public boolean containRole(RoleDefinitionProperty role) {
        return position.containsKey(role);
    }

    public Set<Transition> getPositionOfTransitionsForRole(RoleDefinitionProperty role) {
        return position.get(role);
    }

    @Override
    public String toString() {
        return name;
    }
}
