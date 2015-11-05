package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Quote implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("quote-text"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<i>" + object.objectValue + "</i>"));

                    break;

                case ("quote-source"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;
            }

        }

        return dataObjectArrayToSend;
    }


    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay quoteObject = new ObjectToDisplay("quote", postXML.getAttributeValue("url"), "");

        List<Element> quoteList = postXML.getChildren();

        for (int temp = 0; temp < quoteList.size(); temp++) {

            Element quoteElement = quoteList.get(temp);

            switch (quoteElement.getName()) {

                case "quote-text":
                    quoteObject.addChild(new ObjectToDisplay("quote-text", quoteElement.getValue(), ""));

                    break;

                case "quote-source":
                    quoteObject.addChild(new ObjectToDisplay("quote-source", quoteElement.getValue(), ""));

                    break;

                default:

                    break;

            }

        }

        return quoteObject;
    }
}