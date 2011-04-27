/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import javax.swing.JComboBox;

/**
 *
 * @author Godric
 */
public class DataInterpreterBox extends JComboBox {

    private final String[] integerDouble = {"TextField"};
    private final String[] string = {"TextField", "TextArea", "Label"};
    private final String[] date = {"TextField"};
    private final String[] booLean = {"CheckBox", "RadioButtons", "Label"};

    public DataInterpreterBox(String type) {
        super();
        setInterpreter(type);
    }
    public DataInterpreterBox() {
        super();
    }

    public void setInterpreter(String type) {
        this.removeAllItems();
        if (type.equals("Integer") || (type.equals("Double"))) {
            for (String var : integerDouble) {
                this.addItem(var);
            }
        } else if (type.equals("String")) {

            for (String var : string) {
                this.addItem(var);
            }
        } else if (type.equals("Date")) {
            for (String var : date) {
                this.addItem(var);
            }
        } else if (type.equals("Boolean")) {

            for (String var : booLean) {
                this.addItem(var);
            }

        }

    }
}
