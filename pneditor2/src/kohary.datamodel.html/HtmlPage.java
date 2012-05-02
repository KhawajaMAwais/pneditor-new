/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import kohary.datamodel.dapi.Resolution;
import java.util.*;
import javax.swing.JOptionPane;

import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.DTextArea;
import kohary.datamodel.dapi.DTextField;
import kohary.datamodel.dapi.DTextPane;
import kohary.datamodel.dapi.Design;
import kohary.datamodel.dapi.RadioButtonGroup;
import kohary.datamodel.html.element.ButtonHtmlElement;
import kohary.datamodel.html.element.CheckboxHtmlElement;
import kohary.datamodel.html.element.ImageHtmlElement;
import kohary.datamodel.html.element.RadioButtonHtmlElement;
import kohary.datamodel.html.element.SelectHtmlElement;
import kohary.datamodel.html.element.TextAreaHtmlElement;
import kohary.datamodel.html.element.TextFieldHtmlElement;
import kohary.datamodel.html.element.TextPaneHtmlElement;
import kohary.datamodel.util.ImageCopier;

/**
 *
 * @author Godric
 */
public class HtmlPage {

    private int id;
    private String name;
    private Resolution resolution;
    private DataModel dataModel;
    private CssDocument cssDocument;
    private List<Tag> tags = new LinkedList<Tag>();
    private String directory,imageDirectory;
    private Tag html;

    public HtmlPage(DataModel dataModel, String directory, String imageDirectory) {
        this.dataModel = dataModel;
        this.imageDirectory = imageDirectory;
        this.directory = directory;
        cssDocument = new CssDocument(dataModel);
        setUpTags();
    }

    private void setUpTags() {
        resolution = dataModel.getPage().getResolution();
        name = dataModel.getName();
        //-----------------------------------------------
        html = new Tag("html");
        Tag body = new Tag("body");
        //------------------------------------------------
        Tag head = new Tag("head");
        Tag title = new Tag("title");
        Tag linkCss = new Tag("link", "rel=stylesheet type=text/css href=" + dataModel.toString() + ".css");
        Tag meta1 = new Tag("meta", "http-equiv=content-type content=text/html; charset=utf-8");
        Tag meta2 = new Tag("meta", "name=generator content=DataModelCreator(R) All rights reserved 2012");

        title.add(name);
        head.add(linkCss);
        head.add(meta1);
        //------------------------------------------------
        Tag form = new Tag("form", "action=${postaction} method=post");
        
        Tag hiddenTransitionInputTag = new Tag("input", "type=hidden name=transitionId value=${TRANSITION_ID}" ,true );
        form.add(hiddenTransitionInputTag);
        for (Attribute attribute : dataModel.getAttributes()) {
            Tag div = new Tag("div", "id=" + "attr" + Integer.toString(attribute.getId()));



            if (attribute.getInput() instanceof DTextField) {
                new TextFieldHtmlElement(attribute, cssDocument, div);
                tags.add(div);
            }
            if (attribute.getInput() instanceof DTextArea) {
                new TextAreaHtmlElement(attribute, cssDocument, div);
                tags.add(div);
            }
            if (attribute.getInput() instanceof DButton) {
                new ButtonHtmlElement(attribute, cssDocument, div);
                tags.add(div);
            }
            if (attribute.getInput() instanceof DCheckBox) {
                new CheckboxHtmlElement(attribute, cssDocument, div);
                tags.add(div);
            }

            if (attribute.getInput() instanceof DComboBox) {
                new SelectHtmlElement(attribute, cssDocument, div);
                tags.add(div);
            }

        }

        //--------------------radiobuton groups///////////////////////

        if (!dataModel.getRadioButtonGroups().getRadioButtonGroups().isEmpty()) {
            for (RadioButtonGroup group : dataModel.getRadioButtonGroups().getRadioButtonGroups()) {
                for (DRadioButton radioButton : group.getRadioButtons()) {                    
                    Attribute attribute = dataModel.getAttributeByElement(radioButton);
                    if(attribute != null){
                    Tag div = new Tag("div", "id=" + "attr" + Integer.toString(attribute.getId()));
                    // div.add(attribute.getLabel().getText());
                    new RadioButtonHtmlElement(attribute, cssDocument, div, group);
                    tags.add(div);
                    }
                }
            }
        }

        //----------------------text -------------------------
        for (Design designElement : dataModel.getPage().getDesignElements()) {

            if (designElement instanceof DTextPane) {
                DTextPane textPane = (DTextPane) designElement;
                Tag div = new Tag("div", "id=" + "text" + Integer.toString(textPane.getIdCounter()));
                new TextPaneHtmlElement(textPane, cssDocument, div);
                tags.add(div);
            }
            if(designElement instanceof DImage){
                DImage image = (DImage) designElement;
                Tag div = new Tag("img", "id=" + "image" + Integer.toString(image.getId())+" src=../images/"+image.getName());
                new ImageHtmlElement(image, cssDocument, div);
                tags.add(div);
                //-------------------------------------------
                ImageCopier.copy(image.getPathFile(), new File(imageDirectory+"/"+image.getName()));
                
            }

        }
        //----------------------design----------------------------
        
        
        


        form.addAll(tags);
        head.add(title);
        body.add(form);

        html.add(head);
        html.add(body);
        System.out.println(html);
        System.out.println(cssDocument.toString());
        createPage();
        createCSS();
    }

    public void createPage() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(directory + "/" + dataModel.getName() + ".html"));
            writer.write(html.toString());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, dataModel.getName() + ".html cannot be created! Write error");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }

    }

    private void createCSS() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(directory + "/" + dataModel.getName() + ".css"));
            writer.write(cssDocument.toString());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, dataModel.getName() + ".css cannot be created! Write error");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }

    }

    public CssDocument getCssDocument() {
        return cssDocument;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
