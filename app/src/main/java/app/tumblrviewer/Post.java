package app.tumblrviewer;

import org.jdom2.Element;

import java.util.ArrayList;

/**
 * Created by seba on 04.11.15.
 */
public class Post {

    PostHandler handlerInterface;
    Element rawData;
    String id;
    Enum type;

    ObjectToDisplay objectToDisplay;

    public Post(Element data){

        this.rawData = data;

        id = rawData.getAttributeValue("id");

        defineTypeAndHandler();

        objectToDisplay = handlerInterface.decode(rawData);

    }

    private void defineTypeAndHandler() {

        switch (rawData.getAttributeValue("type")){

            case "regular":
                this.type = PostType.REGULAR;
                handlerInterface = new PostHandler_Regular();
                break;

            case "photo":
                this.type = PostType.PHOTO;
                handlerInterface = new PostHandler_Photo();
                break;

            case "conversation":
                this.type = PostType.DIALOG;
                handlerInterface = new PostHandler_Dialog();
                break;

            case "link":
                this.type = PostType.LINK;
                handlerInterface = new PostHandler_Link();
                break;

            case "quote":
                this.type = PostType.QUOTE;
                handlerInterface = new PostHandler_Quote();
                break;

            case "audio":
                this.type = PostType.AUDIO;
                handlerInterface = new PostHandler_Audio();
                break;

            case "video":
                this.type = PostType.VIDEO;
                handlerInterface = new PostHandler_Video();
                break;

            case "answer":
                this.type = PostType.ANSWER;
                handlerInterface = new PostHandler_Answer();
                break;

            default:
                this.type = PostType.OTHER;
                handlerInterface = new PostHandler_Default();
                break;

        }


    }

    public ArrayList<ShortObjectTumblr> getDataToDisplay(){

        return handlerInterface.getShortObjectsToDisplay(objectToDisplay);

    }


}

enum PostType{
    REGULAR, PHOTO, DIALOG, LINK,
    QUOTE, AUDIO, VIDEO, ANSWER, OTHER
}

class ObjectToDisplay{

    String objectType;
    String objectValue;
    String objectExtra;
    ArrayList<ObjectToDisplay> objectChildren;

    public ObjectToDisplay(String type, String value, String extra){

        this.objectType = type;
        this.objectValue = value;
        this.objectExtra = extra;

    }

    public void addChild(ObjectToDisplay child){

        if (objectChildren == null)
            objectChildren = new  ArrayList<ObjectToDisplay>();

        objectChildren.add(child);

    }

    public void printPost(int deepValue){

        System.out.print("PP: ");
        for (int i = 0; i< deepValue; i++) {
            System.out.print("____");
        }

        System.out.println("Type: " + objectType);

        System.out.print("PP: ");
        for (int i = 0; i< deepValue; i++) {
            System.out.print("____");
        }

        System.out.println("Value: " + objectValue);

        System.out.print("PP: ");
        for (int i = 0; i< deepValue; i++) {
            System.out.print("____");
        }

        System.out.println("Extra: " + objectExtra);

        if (objectChildren != null){

            for (ObjectToDisplay child: objectChildren){

                child.printPost(deepValue + 1);

            }

        }

    }

}

