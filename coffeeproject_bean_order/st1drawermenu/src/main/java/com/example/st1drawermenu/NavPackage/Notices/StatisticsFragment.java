package com.example.st1drawermenu.NavPackage.Notices;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.st1drawermenu.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatisticsFragment() {

    }


    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ListView statisticsListView;
    private StatisticsListAdapter adapter;
    private List<Statistics> statisticsList;


    @Override

    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        statisticsListView = (ListView) getView().findViewById(R.id.statisticsListView);
        statisticsList = new ArrayList<Statistics>();
        adapter = new StatisticsListAdapter(getContext().getApplicationContext(),statisticsList);
        statisticsListView.setAdapter(adapter);


        Button search1Button = (Button) getView().findViewById(R.id.search1Button);
        search1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://givetest.cafe24.com/usermain/StatisticsList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "Wn");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();


            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String statisticsContent, statisticsName, statisticsDate;
                while (count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    statisticsContent = object.getString("statisticsContent");
                    statisticsName = object.getString("statisticsName");
                    statisticsDate = object.getString("statisticsDate");
                    Statistics statistics = new Statistics(statisticsContent, statisticsName, statisticsDate);
                    statisticsList.add(statistics);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
