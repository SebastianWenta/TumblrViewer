package app.tumblrviewer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seba on 04.11.15.
 */
public class XMLParser {

    private Document document;

    private List<Element> postsList;

    private ArrayList<Post> fullPostList;

    public XMLParser(String rawResponseXML){

        try {
            InputStream stream = new ByteArrayInputStream(rawResponseXML.getBytes("UTF-8"));

            SAXBuilder saxBuilder = new SAXBuilder();

            document = saxBuilder.build(stream);

            retrievePostsList();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void retrievePostsList(){

        Element rootElement = document.getRootElement();

        List<Element> mainList = rootElement.getChildren();

        for (int temp = 0; temp < mainList.size(); temp++) {

            Element mainElement = mainList.get(temp);

            switch (mainElement.getName()){

                case "posts":

                    postsList = mainElement.getChildren();
                    break;

                default:
                    break;

            }
        }

    }

    public ArrayList<Post> getFullPostList(){

        if (fullPostList!=null)

            return fullPostList;

         else

            return retrieveFullPostList();


    }

    private ArrayList<Post> retrieveFullPostList(){

        fullPostList =  new ArrayList<Post>();

        PostListView.postNumber = postsList.size();

        for (int temp = 0; temp < postsList.size(); temp++) {

            Element post = postsList.get(temp);

            fullPostList.add(new Post(post));

        }

        return fullPostList;

    }

    public ArrayList<ShortPostTumblr> getShortPostList(){

        ArrayList<ShortPostTumblr> shortPostList =  new ArrayList<ShortPostTumblr>();

        for (int temp = 0; temp < postsList.size(); temp++) {

            Element post = postsList.get(temp);

            shortPostList.add(new ShortPostTumblr(
                    post.getAttributeValue("id"),
                    post.getAttributeValue("type"),
                    post.getAttributeValue("slug").replace('-', ' '),
                    post.getAttributeValue("url")
            ));

        }

        return shortPostList;

    }

}


class ShortPostTumblr {

    private String type;
    private String slug;
    private String id;
    private String url;

    public ShortPostTumblr(String id, String type, String slug, String url){

        this.id = id;
        this.type = type;
        this.slug = slug;
        this.url = url;

    }

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}

