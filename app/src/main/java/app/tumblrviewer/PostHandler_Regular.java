package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Regular implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("regular-title"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<b>" + object.objectValue + "</b"));

                    break;

                case ("regular-body"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;
            }

        }

        return dataObjectArrayToSend;
    }


    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay regularObject = new ObjectToDisplay("regular", postXML.getAttributeValue("url"), "");

        List<Element> regularList = postXML.getChildren();

        for (int temp = 0; temp < regularList.size(); temp++) {

            Element regularElement = regularList.get(temp);

            switch (regularElement.getName()) {

                case "regular-title":
                    regularObject.addChild(new ObjectToDisplay("regular-title", regularElement.getValue(), ""));

                    break;

                case "regular-body":
                    regularObject.addChild(new ObjectToDisplay("regular-body", regularElement.getValue(), ""));

                    break;

                default:

                    break;

            }

        }

        return regularObject;
    }
}