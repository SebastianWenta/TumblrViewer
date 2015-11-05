package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Answer implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("question"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<b><i>" + object.objectValue + "</b></i>"));

                    break;

                case ("answer"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;


            }

        }

        return dataObjectArrayToSend;
    }


    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay answerObject = new ObjectToDisplay("answer", postXML.getAttributeValue("url"), "");

        List<Element> answerList = postXML.getChildren();

        for (int temp = 0; temp < answerList.size(); temp++) {

            Element answerElement = answerList.get(temp);


            switch (answerElement.getName()) {

                case "question":
                    answerObject.addChild(new ObjectToDisplay("question", answerElement.getValue(), ""));

                    break;

                case "answer":
                    answerObject.addChild(new ObjectToDisplay("answer", answerElement.getValue(), ""));

                    break;

                default:

                    break;

            }

        }

        return answerObject;
    }
}