package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Link implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("link-text"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<b>" + object.objectValue + "</b>"));

                    break;

                case ("link-url"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;

                case ("link-description"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;
            }

        }

        return dataObjectArrayToSend;
    }


    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay linkObject = new ObjectToDisplay("link", postXML.getAttributeValue("url"), "");

        List<Element> linkList = postXML.getChildren();

        for (int temp = 0; temp < linkList.size(); temp++) {

            Element linkElement = linkList.get(temp);

            switch (linkElement.getName()) {

                case "link-text":
                    linkObject.addChild(new ObjectToDisplay("link-text", linkElement.getValue(), ""));

                    break;

                case "link-url":
                    linkObject.addChild(new ObjectToDisplay("link-url", linkElement.getValue(), ""));

                    break;

                case "link-description":
                    linkObject.addChild(new ObjectToDisplay("link-description", linkElement.getValue(), ""));

                    break;

                default:

                    break;

            }

        }

        return linkObject;
    }
}