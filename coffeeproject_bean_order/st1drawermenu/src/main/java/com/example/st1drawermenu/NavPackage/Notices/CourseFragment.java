package com.example.st1drawermenu.NavPackage.Notices;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.st1drawermenu.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
public class CourseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {

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

    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;


    private String courseUniversity = "";

    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;


    @Override

    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        final RadioGroup courseUniversityGruop = (RadioGroup) getView().findViewById(R.id.courseUniverityGroup);
        yearSpinner = (Spinner) getView().findViewById(R.id.yearSpinner);
        termSpinner = (Spinner) getView().findViewById(R.id.termSpinner);
        areaSpinner = (Spinner) getView().findViewById(R.id.areaSpinner);
        majorSpinner = (Spinner) getView().findViewById(R.id.majorSpinner);

        courseUniversityGruop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton courseButton = (RadioButton) getView().findViewById(i);
                courseUniversity = courseButton.getText().toString();

                yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.year, android.R.layout.simple_spinner_dropdown_item);
                yearSpinner.setAdapter(yearAdapter);

                termAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.term, android.R.layout.simple_spinner_dropdown_item);
                termSpinner.setAdapter(termAdapter);

                if (courseUniversity.equals("학부")) {
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityArea, android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areaAdapter);
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityRefinementMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);


                } else if (courseUniversity.equals("대학원")) {
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateArea, android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areaAdapter);
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }
            }
        });

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long I) {
                if (areaSpinner.getSelectedItem().equals("교양및기타")) {
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityRefinementMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);

                }
                if (areaSpinner.getSelectedItem().equals("전공")) {
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);

                }
                if (areaSpinner.getSelectedItem().equals("일반대학원")) {
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        courseListView = (ListView) getView().findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        adapter = new CourseListAdapter(getContext().getApplicationContext(),courseList);
        courseListView.setAdapter(adapter);


        Button searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
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
        return inflater.inflate(R.layout.fragment_course, container, false);
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
            try {

                target = "http://givetest.cafe24.com/usermain/CourseList.php?courseUniversity=" + URLEncoder.encode(courseUniversity, "UTF-8") +
                        "&courseYear=" + URLEncoder.encode(yearSpinner.getSelectedItem().toString().substring(0, 4), "UTF-8") + "&courseTerm=" + URLEncoder.encode(termSpinner.getSelectedItem().toString(), "UTF-8") +
                        "&courseArea=" + URLEncoder.encode(areaSpinner.getSelectedItem().toString(), "UTF-8") + "&courseMajor=" + URLEncoder.encode(majorSpinner.getSelectedItem().toString(), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                return stringBuilder.toString().trim();


            } catch (Exception e) {
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

                courseList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("respone");
                int count = 0;
                int courseID; //강의 고유 번호
                String courseUniversity; //학부 혹은 대학원
                int courseYear;  //해당 년도
                String courseTerm;  //해당 학기
                String courseArea;  // 강의 영역
                String courseMajor;  //해당 학과
                String courseGrade;  //해당 학년
                String courseTitle;  //강의 제목
                int courseCredit;  //강의 학점
                int courseDivide;  //강의 분반
                int coursePersonnel;  //강의 제한 인원
                String courseProfessor;  //강의 교수
                String courseTime;  //강의 시간대
                String courseRoom;  //강의실
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);

                    courseID = object.getInt("courseID"); //강의 고유 번호
                    courseUniversity = object.getString("courseUniversity"); //학부 혹은 대학원
                    courseYear = object.getInt("courseYear"); //해당 년도
                    courseTerm = object.getString("courseTerm");  //해당 학기
                    courseArea = object.getString("courseArea");  // 강의 영역
                    courseMajor = object.getString("courseMajor");  //해당 학과
                    courseGrade = object.getString("courseGrade");  //해당 학년
                    courseTitle = object.getString("courseTitle");  //강의 제목
                    courseCredit = object.getInt("courseCredit");  //강의 학점
                    courseDivide = object.getInt("courseDivide");  //강의 분반
                    coursePersonnel = object.getInt("coursePersonnel");  //강의 제한 인원
                    courseProfessor = object.getString("courseProfessor");  //강의 교수
                    courseTime = object.getString("courseTime");  //강의 시간대
                    courseRoom = object.getString("courseRoom");  //강의실

                    Course course = new Course(courseID, courseUniversity, courseYear, courseTerm, courseArea, courseMajor, courseGrade, courseTitle, courseCredit, courseDivide, coursePersonnel, courseProfessor, courseTime, courseRoom);
                    courseList.add(course);
                    count++;
             }
             if(count==0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseFragment.this.getActivity());
                    dialog = builder.setMessage("조회된 강의가 없습니다.")
                         .setPositiveButton("확인", null)
                         .create();
                    dialog.show();
             }

             adapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
