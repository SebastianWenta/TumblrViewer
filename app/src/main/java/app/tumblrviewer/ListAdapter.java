package app.tumblrviewer;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by seba on 03.11.15.
 */
public class ListAdapter extends ArrayAdapter<ClipData.Item> {

    Context context;
    ArrayList<ShortPostTumblr> values;

    public ListAdapter(Context context, ArrayList items) {
        super(context, R.layout.list_item_layout, items);

        this.context = context;
        this.values = items;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        String slugText = values.get(position).getSlug().toString();



        switch (values.get(position).getType().toString()){
            case("photo"):
                imageView.setImageResource(R.drawable.photo_logo);
                if (slugText.equals("")){
                    textView.setText("Photo");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                break;

            case("regular"):
                imageView.setImageResource(R.drawable.text_logo);
                if (slugText.equals("")){
                    textView.setText("Regular Text");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                break;

            case("conversation"):
                imageView.setImageResource(R.drawable.dialog_logo);
                if (slugText.equals("")){
                    textView.setText("Dialog");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                break;

            case("link"):
                imageView.setImageResource(R.drawable.link_logo);
                if (slugText.equals("")){
                    textView.setText("Link");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }

                break;
            case("quote"):
                imageView.setImageResource(R.drawable.quot_logo);
                if (slugText.equals("")){
                    textView.setText("Quote");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                break;

            case("video"):
                imageView.setImageResource(R.drawable.video_logo);
                if (slugText.equals("")){
                    textView.setText("Video");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                break;

            case("audio"):
                if (slugText.equals("")){
                    textView.setText("Audio");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                imageView.setImageResource(R.drawable.audio_logo);
                break;

            default:
                if (slugText.equals("")){
                    textView.setText("Some text");
                } else {
                    textView.setText(values.get(position).getSlug().toString());
                }
                imageView.setImageResource(R.drawable.t_logo);
                break;

        }






        return rowView;
    }




}
