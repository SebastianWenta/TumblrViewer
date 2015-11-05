package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Audio implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType) {

                case ("audio-caption"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<p><b>Find link below or check Tumblr direct link. </b><p>" + object.objectValue));

                    break;

                case ("id3-artist"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<p>Artist: " + "<b>" + object.objectValue + "</b>"));

                    break;

                case ("id3-album"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<p>Album: " + "<b>" + object.objectValue + "</b>"));

                    break;

                case ("id3-title"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", "<p>Title: " + "<b>" +object.objectValue + "</b>"));

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

                case "audio-caption":

                    videoObject.addChild(new ObjectToDisplay("audio-caption", videoElement.getValue(), ""));

                    break;

                case "id3-artist":

                    videoObject.addChild(new ObjectToDisplay("id3-artist", videoElement.getValue(), ""));

                    break;

                case "id3-album":

                    videoObject.addChild(new ObjectToDisplay("id3-album", videoElement.getValue(), ""));

                    break;


                case "id3-title":

                    videoObject.addChild(new ObjectToDisplay("id3-title", videoElement.getValue(), ""));

                    break;


                default:

                    break;

            }

        }

        return videoObject;
    }
}