package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Dialog implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("conversation-title"):


                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<b>" + object.objectValue + "</b"));

                    break;

                case ("conversation-text"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;

            }

        }

        return dataObjectArrayToSend;
    }


    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay dialogObject = new ObjectToDisplay("dialog", postXML.getAttributeValue("url"), "");

        List<Element> dialogList = postXML.getChildren();

        for (int temp = 0; temp < dialogList.size(); temp++) {

            Element dialogElement = dialogList.get(temp);

            switch (dialogElement.getName()) {

                case "conversation-title":
                    dialogObject.addChild(new ObjectToDisplay("conversation-title", dialogElement.getValue(), ""));

                    break;

                case "conversation-text":
                    dialogObject.addChild(new ObjectToDisplay("conversation-text", dialogElement.getValue(), ""));

                    break;

                default:

                    break;

            }

        }

        return dialogObject;
    }
}