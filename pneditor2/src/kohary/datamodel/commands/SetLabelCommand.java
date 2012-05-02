package kohary.datamodel.commands;

import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.Text;

/**
 * Set label to clicked element
 * @author matmas
 */
public class SetLabelCommand implements Command {

    private Attribute attribute;
    private String newLabel;
    private String oldLabel;

    public SetLabelCommand(Attribute attribute, String newLabel) {
        this.attribute = attribute;
        this.newLabel = newLabel;
    }

    public void execute() {
        this.oldLabel = attribute.getLabel().getText();
        attribute.getLabel().setText(newLabel);
    }

    public void undo() {
        attribute.getLabel().setText(oldLabel);
    }

    public void redo() {
        execute();
    }

    public String actionName() {
        return "Set label to " + newLabel;
    }
}
