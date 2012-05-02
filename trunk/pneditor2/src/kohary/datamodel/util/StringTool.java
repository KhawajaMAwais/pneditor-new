/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.awt.FontMetrics;
import javax.swing.SwingUtilities;

/**
 *
 * @author Godric
 */
public class StringTool {
    
          public static int getStringWidth(FontMetrics font,String string ){
          return SwingUtilities.computeStringWidth(font, string); 
      }
      
      public static int getStringHeight(FontMetrics font){
          return font.getHeight();
      }
}
