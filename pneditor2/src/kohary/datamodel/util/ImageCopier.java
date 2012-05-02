/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public final class ImageCopier {

    public static final String NAME_ROOT_DIRECTORY = "DataModelImages";
    
    
    public static String copyImage(File fromFile)  {
           
        
    
            DataModel dataModel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
            
            createRootImageDirectory(NAME_ROOT_DIRECTORY); 
            createRootImageDirectory(NAME_ROOT_DIRECTORY+"/"+String.valueOf(dataModel.getId()));
            String imagePath = NAME_ROOT_DIRECTORY+"/"+dataModel.getId()+"/"+fromFile.getName();
            File outputFile = new File(imagePath);
            
              copy(fromFile,outputFile);
   
      

                return imagePath;
    }
    
    
    public static void copy(File from, File to){
          try {
              File sourceFile=from;
          
              BufferedInputStream bis = new BufferedInputStream(new FileInputStream(from), 4096);
           File targetFile =to;
           BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile), 4096);
           int theChar;
           while ((theChar = bis.read()) != -1) {
              bos.write(theChar);
           }
        bos.close();
        bis.close();
        
     
          }
  catch (Exception ex) {
   ex.printStackTrace();
  }  
    
}
    private static boolean exist(String name) {
        return (new File(name)).exists();
    }

    private static void createRootImageDirectory(String name) {
        if (!exist(name)) {
            File file = new File(name);
            file.mkdir();
        }
    }
}
