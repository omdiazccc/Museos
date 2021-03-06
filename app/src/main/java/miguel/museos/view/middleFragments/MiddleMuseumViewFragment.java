package miguel.museos.view.middleFragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import miguel.museos.R;
import miguel.museos.data.Museum;


/**
 * Created by miguel on 14/07/16.
 */
public class MiddleMuseumViewFragment extends Fragment  {

    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_museum_view, container, false);
        TextView name = (TextView) view.findViewById(R.id.textView_name);
        TextView address = (TextView) view.findViewById(R.id.textView_address);
        TextView description = (TextView) view.findViewById(R.id.textView_description);
        TextView telephone = (TextView) view.findViewById(R.id.textView_telephone);
        TextView schedule = (TextView) view.findViewById(R.id.textView_schedule);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_image);



        name.setText(museum.getName());
        address.setText(museum.getAddress1());
        description.setText(museum.getDescription());
        telephone.setText(museum.getTelephone());
        schedule.setText(museum.getSchedule());



        imageView.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), museum.getid()));
        return view;

    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }


    public interface OnFragmentInteractionListener {

        public void closeMuseumVIew();


        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private Museum museum;


}
