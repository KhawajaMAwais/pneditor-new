/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.export;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.Datamodels;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.variables.Dvariable;
import net.matmas.pnapi.Transition;
import xml.core.SettingsManager;
import xml.model.Settings;
import xml.model.Variable;

/**
 *
 * @author Godric
 */
public class XmlExporter {

    private List<Dvariable> variables;
    private Datamodels dataModels;
    private ArrayList<Variable> XMLvariables = new ArrayList<Variable>();
    private ArrayList<xml.model.DataModel> XMLdataModels = new ArrayList<xml.model.DataModel>();
    private File directory;
    private File file;

    public XmlExporter(File directory) throws Exception {
        this.directory = directory;
        file = new File(directory.getAbsolutePath() + "/settings.xml");
        loadVariablesToXML();
        loadDataModelsToXML();
        export();
    }

    private void export() throws Exception {
        SettingsManager manager = new SettingsManager();
        Settings settings = new Settings(XMLvariables, XMLdataModels);
        manager.write(settings, file);
    }

    private void loadVariablesToXML() {
        variables = DatamodelCreator.getInstance().getDocument().getVariables();
        for (Dvariable variable : variables) {
            XMLvariables.add(new Variable(variable.getLabel(), variable.getType()));
        }
    }

    private void loadDataModelsToXML() {
        dataModels = DatamodelCreator.getInstance().getDocument().getDataModels();

        for (DataModel dataModel : dataModels) {
            List<String> transitionsStringList = new ArrayList<String>();
            for (String transitionId : dataModel.getPosition()) {
                transitionsStringList.add(transitionId);
            }

            XMLdataModels.add(new xml.model.DataModel(dataModel.getName(), transitionsStringList));
            // 
        }

    }
}
