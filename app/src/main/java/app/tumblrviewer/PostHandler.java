package app.tumblrviewer;

import org.jdom2.Element;

import java.util.ArrayList;

/**
 * Created by seba on 04.11.15.
 */
public interface PostHandler {

    public ObjectToDisplay decode(Element postXML);

    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay);

}

class ShortObjectTumblr {

    String type;
    String value;

    ShortObjectTumblr(String type, String value){

        this.type = type;
        this.value = value;

    }

}