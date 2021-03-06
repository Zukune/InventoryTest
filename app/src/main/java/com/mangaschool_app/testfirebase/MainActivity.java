package com.mangaschool_app.testfirebase;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    
public static final String KEY_ITEM = "clicked item";
    private RecyclerView mBlogList;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler View
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        
        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setLayoutManager(mLayoutManager);
        mBlogList.setItemAnimator(new DefaultItemAnimator());

        // Send a Query to the database
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Data");
        myRef = FirebaseDatabase.getInstance().getReference().child("Data");
        myRef.keepSynced(true);

        //Picasso Codes
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

    }


    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<ModelClass, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelClass, BlogViewHolder>(
                        ModelClass.class,
                        R.layout.design_row,
                        BlogViewHolder.class,
                        myRef) {

                    @Override
                    protected void populateViewHolder(final BlogViewHolder viewHolder, final ModelClass model, final int position) {
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setImage(getApplicationContext(), model.getImage());
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(view.getContext(),""+position, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Slidershow.class);
                                i.putExtra(KEY_ITEM, clickedItem);
                                startActivity(i);

                            }
                        });
                    }
                };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    //View Holder For Recycler View
    //View Holder For Recycler View

    public static class BlogViewHolder extends RecyclerView.ViewHolder  {

        Context context;
        View mView;
        
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            context =itemView.getContext();


        }

        public void setTitle(String title) {

            TextView post_title = (TextView) mView.findViewById(R.id.titleText);
            post_title.setText(title);

        }

        public void setImage(final Context ctx, final String image) {

            final ImageView post_image = (ImageView) mView.findViewById(R.id.imageViewy);

            // We Need TO pass Context
            //Picasso.with(ctx).load(image).into(post_image);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(ctx).load(image).into(post_image);

                }

            });

        }

    }

}






