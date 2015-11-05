package app.tumblrviewer;

import org.jdom2.Element;

import java.util.ArrayList;

/**
 * Created by seba on 04.11.15.
 */
public class PostHandler_Default implements PostHandler {

    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        return new ArrayList<ShortObjectTumblr>();
    }

    @Override
    public ObjectToDisplay decode(Element postXML) {

        return new ObjectToDisplay("init","init","init");
    }
}
