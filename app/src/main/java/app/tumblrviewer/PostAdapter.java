package app.tumblrviewer;

import android.content.ClipData;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by seba on 04.11.15.
 */
public class PostAdapter extends ArrayAdapter<ClipData.Item> {

    Context context;
    ArrayList<ShortObjectTumblr> values;

    public PostAdapter(Context context, ArrayList items) {
        super(context, R.layout.one_post_layout, items);


        this.context = context;
        this.values = items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.one_post_layout, parent, false);




        switch (values.get(position).type.toString()){

            case "text":

                TextView textView = (TextView) rowView.findViewById(R.id.post_text);
                textView.setVisibility(View.VISIBLE);
                textView.setText(Html.fromHtml(values.get(position).value));
                textView.setMovementMethod(LinkMovementMethod.getInstance());

                break;

            case "photo":

                ImageView imageView = (ImageView) rowView.findViewById(R.id.post_image);
                imageView.setVisibility(View.VISIBLE);
                String url = values.get(position).value;
                Picasso.with(context).load(url).into(imageView);

                break;

           case "video":

                TextView videoView = (TextView) rowView.findViewById(R.id.post_video);
                videoView.setVisibility(View.VISIBLE);
                String url_video = values.get(position).value;

                if  (url_video.contains("http") && !(url_video.contains("<"))){
                    videoView.setText(Html.fromHtml("<p><b><a href=\"" + url_video + "\">Click to see this video</a></b>"));
                    videoView.setMovementMethod(LinkMovementMethod.getInstance());

                } else {
                    videoView.setText(Html.fromHtml("<p><b>Find video in below link(s) or check it on Tumblr</b>"));
                }


                break;

            default:

                break;


        }

        return rowView;
    }


}
