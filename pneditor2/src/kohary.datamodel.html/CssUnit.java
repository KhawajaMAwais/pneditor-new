/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Godric
 */
public class CssUnit {

    private Map<String, TagType> tags = new HashMap<String, TagType>();
    private List<CssAttribute> cssAttributes = new LinkedList<CssAttribute>();
    private boolean isHover = false;

    public CssUnit() {
    }

    public List<CssAttribute> getAttributes() {
        return cssAttributes;
    }

    public Map<String, TagType> getTags() {
        return tags;
    }

    public void setIsHover(boolean isHover) {
        this.isHover = isHover;
    }

    public boolean isIsHover() {
        return isHover;
    }
   

    @Override
    public String toString() {
        String ouput = "";
int counter = 0;
        // Iterator<String> iterator = tags.keySet().iterator();
        for (String tag : tags.keySet()) {
            if (tags.get(tag).equals(TagType.clasz)) {
                ouput += ".";
            }
            if (tags.get(tag).equals(TagType.id)) {
                ouput += "#";
            }
            ouput += tag;

            if (counter < tags.keySet().size() - 1) {
                ouput += ",";
            } else {

                ouput += (isHover) ? ":hover {\n" : "{\n";
            }
            counter++;
            //  iterator.next();
        }
        for (CssAttribute cssAttribute : cssAttributes) {
            ouput += cssAttribute.toString();
        }
        ouput += "\n}";
        return ouput;
    }
}
