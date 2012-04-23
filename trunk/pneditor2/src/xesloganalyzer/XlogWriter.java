/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.deckfour.xes.classification.XEventAttributeClassifier;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.classification.XEventLifeTransClassifier;
import org.deckfour.xes.classification.XEventNameClassifier;
import org.deckfour.xes.classification.XEventResourceClassifier;
import org.deckfour.xes.extension.XExtension;
import org.deckfour.xes.extension.XExtensionManager;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
/**
 *
 * @author XY
 */
public class XlogWriter {

    private XESLog xeslog;
    private XLog log;
    
    public XlogWriter(XESLog xeslog) {
        this.xeslog = xeslog;
        
    }
    
    public XLog getXlog(){
        
         XFactory factoryy = XFactoryRegistry.instance().currentDefault();    
         XAttribute atribute = null;
         
         log = factoryy.createLog();       
         XEvent eventfors = factoryy.createEvent();
         XTrace tracefors = factoryy.createTrace(); 
         
        for(XESTrace tracein:xeslog.getTraces()){

            XTrace trace = factoryy.createTrace();    
            
            String name = "UNDEFINED";
            if(!tracein.getName().isEmpty()){
                name = tracein.getName();
            }
            atribute = factoryy.createAttributeLiteral("concept:name", name, null);
            trace.getAttributes().put("concept:name", atribute);
            
            String des="UNDEFINED";
            if(!tracein.getDescription().isEmpty()){
                des = tracein.getDescription();
            }
            atribute = factoryy.createAttributeLiteral("description", des, null);
            trace.getAttributes().put("description", atribute);

            for(XESEvent event:tracein.getEvents()){
            
            XEvent xevent = factoryy.createEvent();
            
            String namee = "UNDEFINED";
            if(!event.getName().isEmpty()){
                namee = event.getName();
            }
            
            atribute = factoryy.createAttributeLiteral("concept:name", namee, null);
            xevent.getAttributes().put("concept:name", atribute);
            atribute = factoryy.createAttributeLiteral("org:resource", event.getResource(), null);
            xevent.getAttributes().put("org:resource", atribute);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date timest = null;        
            try {
                         timest = sdf.parse(event.getTime());
                    } catch (ParseException ex) {
                        Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            atribute = factoryy.createAttributeTimestamp("time:timestamp", timest, null);
            xevent.getAttributes().put("ime:timestamp", atribute);
            atribute = factoryy.createAttributeLiteral("lifecycle:transition", event.getLifecycle(), null);
            xevent.getAttributes().put("lifecycle:transition", atribute);
            trace.add(xevent);
            eventfors = xevent;
            
        }
        tracefors = trace;
        log.add(trace);
        
        }
        
        
        XExtension extension = XExtensionManager.instance().getByPrefix("lifecycle");
        log.getExtensions().add(extension);
        
        extension = XExtensionManager.instance().getByPrefix("org");
        log.getExtensions().add(extension);
        
        extension = XExtensionManager.instance().getByPrefix("time");
        log.getExtensions().add(extension);
        
        extension = XExtensionManager.instance().getByPrefix("concept");
        log.getExtensions().add(extension);
        
        extension = XExtensionManager.instance().getByPrefix("semantic");
        log.getExtensions().add(extension);

        XEventClassifier classifierName = new XEventNameClassifier();
        classifierName.getClassIdentity(eventfors);        
        log.getClassifiers().add(classifierName);
        
        XEventClassifier classifierResource = new XEventResourceClassifier();
        classifierResource.getClassIdentity(eventfors);        
        log.getClassifiers().add(classifierResource);
        
        XEventClassifier classifierLife = new XEventLifeTransClassifier();
        classifierLife.getClassIdentity(eventfors);        
        log.getClassifiers().add(classifierLife);
        
        XEventClassifier classifierCustom = new XEventAttributeClassifier("Type Action", "type:action");
        classifierCustom.getClassIdentity(eventfors);        
        log.getClassifiers().add(classifierCustom);
        
        XAttribute atributee = factoryy.createAttributeLiteral("concept:name", "INVALID", null);
        
        log.getGlobalEventAttributes().add(atributee);
        log.getGlobalTraceAttributes().add(atributee);
        
        atribute = factoryy.createAttributeLiteral("source", "Log generated from PNEditor", null);
        log.getAttributes().put("source", atribute);
        atribute = factoryy.createAttributeLiteral("concept:name", xeslog.getName(), null);
        log.getAttributes().put("concept:name", atribute);
        
        atribute = factoryy.createAttributeLiteral("lifecycle:model", "standard", null);
        log.getAttributes().put("lifecycle:model", atribute);
        
        atribute = factoryy.createAttributeLiteral("description", "Log from PNEditor", null);
        log.getAttributes().put("description", atribute);
        
        
        return log;
    }
    
}
