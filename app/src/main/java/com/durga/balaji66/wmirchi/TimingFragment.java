package com.durga.balaji66.wmirchi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimingFragment  extends Fragment {

    private static final String TAG = TimingFragment.class.getName();

    private TextView mTextViewMondayOpen, mTextViewMondayOpenTime,mTextViewMondayClose,mTextViewMondayCloseTime;
    private TextView mTextViewTuesdayOpen, mTextViewTuesdayOpenTime,mTextViewTuesdayClose,mTextViewTuesdayCloseTime;
    private TextView mTextViewWednesdayOpen, mTextViewWednesdayOpenTime,mTextViewWednesdayClose,mTextViewWednesdayCloseTime;
    private TextView mTextViewThursdayOpen, mTextViewThursdayOpenTime,mTextViewThursdayClose,mTextViewThursdayCloseTime;
    private TextView mTextViewFridayOpen, mTextViewFridayOpenTime,mTextViewFridayClose,mTextViewFridayCloseTime;
    private TextView mTextViewSaturdayOpen, mTextViewSaturdayOpenTime,mTextViewSaturdayClose,mTextViewSaturdayCloseTime;
    private TextView mTextViewSundayOpen, mTextViewSundayOpenTime,mTextViewSundayClose,mTextViewSundayCloseTime;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url ="https://www.egcashback.in/manage/api/timimgs/all/?X-Api-Key=B1271BD939B74CA8D5C9A183C53BACDD&field=shop_id&filter=2";

    public TimingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = new View(getContext());
        view =inflater.inflate(R.layout.fragment_timing, container, false);
        initializeViews(view);
        sendAndRequestResponse();
        return view;
    }

    public void initializeViews(View view)
    {
        mTextViewMondayOpen =view.findViewById(R.id.textViewMondayOpen);
        mTextViewMondayOpenTime =view.findViewById(R.id.textViewMondayOpenTime);
        mTextViewMondayClose =view.findViewById(R.id.textViewMondayClose);
        mTextViewMondayCloseTime =view.findViewById(R.id.textViewMondayCloseTime);

        mTextViewTuesdayOpen =view.findViewById(R.id.textViewTuesdayOpen);
        mTextViewTuesdayOpenTime =view.findViewById(R.id.textViewTuesdayOpenTime);
        mTextViewTuesdayClose =view.findViewById(R.id.textViewTuesdayClose);
        mTextViewTuesdayCloseTime =view.findViewById(R.id.textViewTuesdayCloseTime);

        mTextViewWednesdayOpen =view.findViewById(R.id.textViewWednesdayOpen);
        mTextViewWednesdayOpenTime =view.findViewById(R.id.textViewWednesdayOpenTime);
        mTextViewWednesdayClose =view.findViewById(R.id.textViewWednesdayClose);
        mTextViewWednesdayCloseTime =view.findViewById(R.id.textViewWednesdayCloseTime);

        mTextViewThursdayOpen =view.findViewById(R.id.textViewThursdayOpen);
        mTextViewThursdayOpenTime =view.findViewById(R.id.textViewThursdayOpenTime);
        mTextViewThursdayClose =view.findViewById(R.id.textViewThursdayClose);
        mTextViewThursdayCloseTime =view.findViewById(R.id.textViewThursdayCloseTime);

        mTextViewFridayOpen =view.findViewById(R.id.textViewFridayOpen);
        mTextViewFridayOpenTime =view.findViewById(R.id.textViewFridayOpenTime);
        mTextViewFridayClose =view.findViewById(R.id.textViewFridayClose);
        mTextViewFridayCloseTime =view.findViewById(R.id.textViewFridayCloseTime);

        mTextViewSaturdayOpen =view.findViewById(R.id.textViewSaturdayOpen);
        mTextViewSaturdayOpenTime =view.findViewById(R.id.textViewSaturdayOpenTime);
        mTextViewSaturdayClose =view.findViewById(R.id.textViewSaturdayClose);
        mTextViewSaturdayCloseTime =view.findViewById(R.id.textViewSaturdayCloseTime);

        mTextViewSundayOpen =view.findViewById(R.id.textViewSundayOpen);
        mTextViewSundayOpenTime =view.findViewById(R.id.textViewSundayOpenTime);
        mTextViewSundayClose =view.findViewById(R.id.textViewSundayClose);
        mTextViewSundayCloseTime =view.findViewById(R.id.textViewSundayCloseTime);


    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj =new JSONObject(response);
                    if(obj.has("data")){
                        JSONObject obj1= obj.getJSONObject("data");
                        if(obj1.has("timimgs")){
                            JSONArray jsonArray=obj1.getJSONArray("timimgs");
                            int length = jsonArray .length();
                            for(int i=0; i<length; i++) {
                                JSONObject jObj = jsonArray.getJSONObject(i);
                                mTextViewMondayOpen.setText(jObj.getString("a1"));
                                mTextViewMondayOpenTime.setText(jObj.getString("a2"));
                                mTextViewMondayClose.setText(jObj.getString("a3"));
                                mTextViewMondayCloseTime.setText(jObj.getString("a4"));

                                mTextViewTuesdayOpen.setText(jObj.getString("b1"));
                                mTextViewTuesdayOpenTime.setText(jObj.getString("b2"));
                                mTextViewTuesdayClose.setText(jObj.getString("b3"));
                                mTextViewTuesdayCloseTime.setText(jObj.getString("b4"));

                                mTextViewWednesdayOpen.setText(jObj.getString("c1"));
                                mTextViewWednesdayOpenTime.setText(jObj.getString("c2"));
                                mTextViewWednesdayClose.setText(jObj.getString("c3"));
                                mTextViewWednesdayCloseTime.setText(jObj.getString("c4"));

                                mTextViewThursdayOpen.setText(jObj.getString("d1"));
                                mTextViewThursdayOpenTime.setText(jObj.getString("d2"));
                                mTextViewThursdayClose.setText(jObj.getString("d3"));
                                mTextViewThursdayCloseTime.setText(jObj.getString("d4"));

                                mTextViewFridayOpen.setText(jObj.getString("e1"));
                                mTextViewFridayOpenTime.setText(jObj.getString("e2"));
                                mTextViewFridayClose.setText(jObj.getString("e3"));
                                mTextViewFridayCloseTime.setText(jObj.getString("e4"));

                                mTextViewSaturdayOpen.setText(jObj.getString("f1"));
                                mTextViewSaturdayOpenTime.setText(jObj.getString("f2"));
                                mTextViewSaturdayClose.setText(jObj.getString("f3"));
                                mTextViewSaturdayCloseTime.setText(jObj.getString("f4"));

                                mTextViewSundayOpen.setText(jObj.getString("g1"));
                                mTextViewSundayOpenTime.setText(jObj.getString("g2"));
                                mTextViewSundayClose.setText(jObj.getString("g3"));
                                mTextViewSundayCloseTime.setText(jObj.getString("g4"));

                            }
                        }
                    }
                    //playVideo();


                }
                catch (JSONException e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());

            }
        });

        mRequestQueue.add(mStringRequest);
    }

    }

