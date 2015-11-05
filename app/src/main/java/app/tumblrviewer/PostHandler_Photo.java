package app.tumblrviewer;

/**
 * Created by seba on 04.11.15.
 */

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class PostHandler_Photo implements PostHandler {


    public ArrayList<ShortObjectTumblr> getShortObjectsToDisplay(ObjectToDisplay objectToDisplay) {

        ArrayList<ShortObjectTumblr> dataObjectArrayToSend = new ArrayList<ShortObjectTumblr>();

        for (ObjectToDisplay object: objectToDisplay.objectChildren){

            switch (object.objectType){

                case ("photo-caption"):

                    dataObjectArrayToSend.add(new ShortObjectTumblr("text", object.objectValue));

                    break;

                case ("photo-url"):

                    if (object.objectExtra.equals("1280")){

                        dataObjectArrayToSend.add(new ShortObjectTumblr("photo", object.objectValue));

                    }

                    break;

                case ("photoset"):


                    for (ObjectToDisplay objectPhotoSet: object.objectChildren){

                        if (!objectPhotoSet.objectExtra.equals("o1")){

                            for (ObjectToDisplay objectPhotoSetPhoto: objectPhotoSet.objectChildren){

                                if (objectPhotoSetPhoto.objectExtra.equals("1280")){

                                    dataObjectArrayToSend.add(new ShortObjectTumblr("photo", objectPhotoSetPhoto.objectValue));

                                }

                            }

                        }

                    }


                    break;

            }

        }

        return dataObjectArrayToSend;

    }

    @Override
    public ObjectToDisplay decode(Element postXML) {

        ObjectToDisplay photoObject = new ObjectToDisplay("photo", postXML.getAttributeValue("url"), "");

        List<Element> photoList = postXML.getChildren();

        for (int temp = 0; temp < photoList.size(); temp++) {

            Element photoElement = photoList.get(temp);


            switch (photoElement.getName()){
                case "photo-caption":
                    photoObject.addChild(new ObjectToDisplay("photo-caption", photoElement.getValue(), ""));

                    break;

                case "photo-link-url":
                    photoObject.addChild(new ObjectToDisplay("photo-link-url", photoElement.getValue(), ""));
                    break;

                case "photo-url":
                    photoObject.addChild(new ObjectToDisplay("photo-url", photoElement.getValue(), photoElement.getAttributeValue("max-width")));
                    break;

                case "photoset":

                    List<Element> photoSetList = photoElement.getChildren();

                    ObjectToDisplay photoSetObject = new ObjectToDisplay("photoset", "" , "");

                    for (int photoSetItearator = 0; photoSetItearator < photoSetList.size(); photoSetItearator++) {

                        Element photoSetElement = photoSetList.get(photoSetItearator);

                        switch (photoSetElement.getName()){

                            case ("photo"):

                                ObjectToDisplay photoSetPhotoObject = new ObjectToDisplay("photo", photoSetElement.getAttributeValue("caption"), photoSetElement.getAttributeValue("offset"));

                                List<Element> photoSetPhotoList = photoSetElement.getChildren();

                                for (int photoSetPhotoItearator = 0; photoSetPhotoItearator < photoSetPhotoList.size(); photoSetPhotoItearator++) {

                                    Element photoSetPhotoElement = photoSetPhotoList.get(photoSetPhotoItearator);

                                    switch (photoSetPhotoElement.getName()) {

                                        case ("photo-url"):

                                            photoSetPhotoObject.addChild(new ObjectToDisplay("photo-url", photoSetPhotoElement.getValue(), photoSetPhotoElement.getAttributeValue("max-width")));

                                        break;

                                        default:


                                        break;

                                    }


                                }

                                photoSetObject.addChild(photoSetPhotoObject);

                                break;

                            default:

                                break;

                        }


                    }

                    photoObject.addChild(photoSetObject);

                    break;

                default:

                    break;


            }


        }

        return photoObject;

    }

}


