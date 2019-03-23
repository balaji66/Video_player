package com.durga.balaji66.wmirchi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class VideoFragment extends Fragment {

    private static final String TAG = VideoFragment.class.getName();

    private VideoView mVideoView;
    MediaController mediaController;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private  static String video_url ;
    String url ="https://egcashback.in/manage/api/video_ads/all/?X-Api-Key=B1271BD939B74CA8D5C9A183C53BACDD&field1=video_status&filter1=Active&field=shop_id&filter=1155";

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = new View(getContext());
        view = inflater.inflate(R.layout.fragment_video, container, false);
        sendAndRequestResponse();
        mVideoView= view.findViewById(R.id.videoView);
        mediaController= new MediaController(getContext());

        // Inflate the layout for this fragment
        return view;

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
                        if(obj1.has("video_ads")){
                            JSONArray jsonArray=obj1.getJSONArray("video_ads");
                            int length = jsonArray .length();
                            for(int i=0; i<length; i++) {
                                JSONObject jObj = jsonArray.getJSONObject(i);

                                video_url=jObj.getString("videos");
                                mVideoView.setVisibility(View.VISIBLE);
                                playVideo();

                            }
                        }
                    }

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            if (!isVisibleToUser)   // If we are becoming invisible, then...
            {
                //pause or stop video
                mVideoView.pause();
                mediaController.hide();
            }

            if (isVisibleToUser) {
                //play your video
                playVideo();
                mediaController.show();
            }
        }

    }

    public void playVideo()
    {
        Uri video = Uri.parse(video_url);
        mediaController.setAnchorView(mVideoView);
        mVideoView.requestFocus();
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(video);
        mVideoView.start();

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                String title = "Unable to read video";
                String message = "mp : "+mp + "\n what :"+what+"\n extra : "+extra;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(title);
                dialog.setMessage(message);
                dialog.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }
}
