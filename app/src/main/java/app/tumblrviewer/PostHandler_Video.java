package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Video implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("video-source"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("video", object.objectValue));

                    break;

                case ("video-caption"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;

            }

        }

        return dataObjectArrayToSend;
    }


    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay videoObject = new ObjectToDisplay("video", postXML.getAttributeValue("url"), "");

        List<Element> videoList = postXML.getChildren();

        for (int temp = 0; temp < videoList.size(); temp++) {

            Element videoElement = videoList.get(temp);

            switch (videoElement.getName()) {

                case "video-source":

                    videoObject.addChild(new ObjectToDisplay("video-source", videoElement.getValue(), ""));

                    break;

                case "video-caption":

                    videoObject.addChild(new ObjectToDisplay("video-caption", videoElement.getValue(), ""));

                    break;

                default:

                    break;

            }

        }

        return videoObject;
    }
}