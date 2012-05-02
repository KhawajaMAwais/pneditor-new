package kohary.datamodel.properties;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import kohary.datamodel.dapi.ComboBoxItem;
import kohary.datamodel.dapi.DComboBox;
import net.matmas.pnapi.properties.Property;

/**
 *
 * @author matmas
 */
public class Row {

	
	private JLabel comboboxItem,comboboxValue;
	private JButton itemDelete;
	private Table table;
        private ComboBoxItem item;

	public Row(String comboBoxItemString,String comboboxValueString, Table table) {
		this.table = table;			
		this.comboboxItem = new JLabel(comboBoxItemString);
		this.comboboxValue = new JLabel(comboBoxItemString);
		this.itemDelete = new JButton();	
	}
	public Row(ComboBoxItem item, Table table) {
		this.table = table;
                this.item = item;
		this.comboboxItem = new JLabel(item.getItem());
		this.comboboxValue = new JLabel(item.getValue());
		this.itemDelete = new JButton();	
	}

    public JLabel getComboboxItem() {
        return comboboxItem;
    }

    public void setComboboxItem(JLabel comboboxItem) {
        this.comboboxItem = comboboxItem;
    }

    public JLabel getComboboxValue() {
        return comboboxValue;
    }

    public void setComboboxValue(JLabel comboboxValue) {
        this.comboboxValue = comboboxValue;
    }

    public JButton getItemDelete() {
        return itemDelete;
    }

    public void setItemDelete(JButton itemDelete) {
        this.itemDelete = itemDelete;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ComboBoxItem getItem() {
        return item;
    }

    public void setItem(ComboBoxItem item) {
        this.item = item;
    }



	

}
