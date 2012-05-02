/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.export;

import java.io.*;
import java.util.zip.*;
import org.apache.commons.io.IOUtils;

//Zdroj: http://www.lalitmehta.com/home/wp-content/uploads/2007/09/zipfile.txt

public class Ziper {

    private File rootDirectory;

    public Ziper(File rootDirectory) throws Exception {
        this.rootDirectory = rootDirectory;
       		try {
			//name of zip file to create
			String outFilename = rootDirectory+".zip";
			
			//create ZipOutputStream object
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
			
			//path to the folder to be zipped
			File zipFolder = rootDirectory;
			
			//get path prefix so that the zip file does not contain the whole path
			// eg. if folder to be zipped is /home/lalit/test
			// the zip file when opened will have test folder and not home/lalit/test folder
			int len = zipFolder.getAbsolutePath().lastIndexOf(File.separator);
			String baseName = zipFolder.getAbsolutePath().substring(0,len+1);
			
			addFolderToZip(zipFolder, out, baseName);
			
			out.close();
                        removeCommonFolder();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

      
    
    }
    
        private void removeCommonFolder(){
            rootDirectory.delete();
        }

	private static void addFolderToZip(File folder, ZipOutputStream zip, String baseName) throws IOException {
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				addFolderToZip(file, zip, baseName);
			} else {
				String name = file.getAbsolutePath().substring(baseName.length());
				ZipEntry zipEntry = new ZipEntry(name);
				zip.putNextEntry(zipEntry);
				IOUtils.copy(new FileInputStream(file), zip);
				zip.closeEntry();
			}
		}
	}
}