package com.ridhwaan.any.tugo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static com.ridhwaan.any.tugo.R.attr.paddingEnd;
import static com.ridhwaan.any.tugo.R.attr.toolbarId;

/**
 * Created by Ridhwaan on 2/3/17.
 */

public class EventListFragment extends Fragment {

    private boolean mInDialog = false;
    private static final String DIALOG_ID = "AYY LMAO";
    private static final int REQUEST_EVENT_DATA = 1;
    public static final int REQUEST_EVENT_FINAL = 2;
    private FloatingActionButton mCreateEventButton;
    private ArrayList<EventObject> mListOfEvents;
    private EventStash sEventStash = EventStash.getInstance();
    private final long mAnimationLength = 400;


    private EventAdapter mEventAdapter;

    private RecyclerView mRecyclerView;

    private ImageView mDefaultImageView;
    private TextView mDefualtTextView;

    private Button mEditButton;
    private CardView mCardView;











    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_list_fragment_layout,container, false);



        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);



        mDefaultImageView = (ImageView) v.findViewById(R.id.no_event_image);
        mDefualtTextView = (TextView)v.findViewById(R.id.no_event_text);




        mCardView = (CardView)v.findViewById(R.id.edit_card_view);



            setHasOptionsMenu(true);


        mCreateEventButton = (FloatingActionButton) v.findViewById(R.id.create_event_button);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));












        mCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          for(EventObject e: sEventStash.sListOfEvents ) {
         Log.d("TAG", "Array" + "  " + e.getmEventName());
      }
                beginEventCreationAnimation();
                createDialog();


                //To create dialog first get manager, then instantiate the dialog class, then show with
                //fg and unique ID and set targetFragment

            }
        });

















       //updateUI();

           return v;


    }



    private void createDialog(){

        FragmentManager fg = getFragmentManager();
        BeginEventCreationDialog dialog = new BeginEventCreationDialog();
        dialog.show(fg,DIALOG_ID);
        //dialog.setTargetFragment(EventListFragment.this ,REQUEST_EVENT_DATA);
        dialog.setTargetFragment(EventListFragment.this ,REQUEST_EVENT_DATA);

    }

    public void checkStashSize(){

        if(EventStash.getInstance().sListOfEvents.size() == 0){

            mDefualtTextView.setVisibility(View.VISIBLE);
            mDefaultImageView.setVisibility(View.VISIBLE);

        }
    }

    private void handleEditButton(Button b, final CardView cardView){

        if(EventStash.getInstance().sListOfEvents.size() != 0) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    cardView.setVisibility(View.VISIBLE);

                    if (Build.VERSION.SDK_INT >= 21) {



                        Animator anim;
                        int cx = cardView.getWidth() / 2;
                        int cy = cardView.getHeight() / 2;

                        float initialRad = (float) Math.hypot(cx, cy);


                        //first get center


                        anim = ViewAnimationUtils.createCircularReveal(cardView, cx, cy, 0, initialRad);
                        anim.setDuration(mAnimationLength);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                cardView.setVisibility(View.VISIBLE);
                            }
                        });


                        anim.start();


                    }

                }
            });


        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {



        Log.d("TAG", "ANIMATION CALLED");
        endEventCreation();

        if(data == null){
            return;
        }

        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_EVENT_DATA && data != null){
            EventObject e = (EventObject) data.getSerializableExtra(BeginEventCreationDialog.ARG_TITLE_EVENT);
           // sEventStash.addEvent(e);
            Intent intent = CreateEventActivity.newInstance(getActivity(),e);
            startActivityForResult(intent,REQUEST_EVENT_FINAL);

        }

        if(requestCode == REQUEST_EVENT_FINAL && data != null){
            Log.d("TAG", "EVENT CREATION CALLED");
            EventObject e = (EventObject) data.getSerializableExtra(CreateEventFragment.RESULT_ID);
            EventStash.getInstance().addEvent(e);

            checkStashSize();
            Log.d("TAG", "   ARRAY SIZE   " + EventStash.getInstance().sListOfEvents.size());
            updateUI();

        }


    }



    private void beginEventCreationAnimation() {
        if (Build.VERSION.SDK_INT >= 21) {

            Animator anim;
            int cx = mCreateEventButton.getWidth() / 2;
            int cy = mCreateEventButton.getHeight() / 2;

            float initialRad = (float) Math.hypot(cx, cy);


            //first get center


            anim = ViewAnimationUtils.createCircularReveal(mCreateEventButton, cx, cy, initialRad, 0);
            anim.setDuration(mAnimationLength);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mCreateEventButton.setVisibility(View.INVISIBLE);
                }
            });


            anim.start();


        }
    }





    
    
    private void endEventCreation (){

        mCreateEventButton.setVisibility(View.VISIBLE);

        if(Build.VERSION.SDK_INT >= 21){


            Animator anim;
            int cx = mCreateEventButton.getWidth() /2;
            int cy = mCreateEventButton.getHeight()/2;

            float initialRad = (float) Math.hypot(cx,cy);



                //first get center



                anim = ViewAnimationUtils.createCircularReveal(mCreateEventButton,cx,cy,0,initialRad);
                anim.setDuration(mAnimationLength);



                anim.start();
                




            }
    }

    public void updateUI(){


        mEventAdapter = new EventAdapter();
        mRecyclerView.setAdapter(mEventAdapter  );
    }


    @Override
    public void onResume() {
        super.onResume();
        checkStashSize();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_main_menu,menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_item_new_post){

            Toast.makeText(getActivity(),"Search Feature Not Developed", Toast.LENGTH_LONG).show();
            return true;
        } if (item.getItemId() == R.id.menu_item_clear_all){

            for(int i = 0; i < EventStash.getInstance().sListOfEvents.size(); i++){
                EventStash.getInstance().sListOfEvents.remove(i);


            }
            mEventAdapter.notifyDataSetChanged();

        }
        return false;
    }

    private class EventHolder extends RecyclerView.ViewHolder{

        private TextView titleOfText;
        private TextView dayCreated;
        private TextView daysLeft;
        private Button editButton;
        private CardView cardView;
        private ImageView mShareImageView;
        private ImageView mBackgroundImage;

        private int mPosition;



        public EventHolder(View itemView) {
            super(itemView);



            titleOfText = (TextView) itemView.findViewById(R.id.title_of_event);
            dayCreated = (TextView) itemView.findViewById(R.id.date_created);
            daysLeft = (TextView)itemView.findViewById(R.id.days_left);
            editButton = (Button) itemView.findViewById(R.id.edit_button);
            mBackgroundImage = (ImageView) itemView.findViewById(R.id.background_image);
            cardView = (CardView)itemView.findViewById(R.id.edit_card_view);
            Random rand = new Random();
            Toast.makeText(getActivity(),"Randomizing Images", Toast.LENGTH_SHORT).show();

            int randomInt = rand.nextInt(5);

            mShareImageView = (ImageView) itemView.findViewById(R.id.remove_event_id);

            mShareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.d("TAG", "ADAPTER POS" + " " + getAdapterPosition());
                    String link = EventStash.getInstance().sListOfEvents.get(getAdapterPosition()).getmLink();


                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, EventStash.getInstance().sListOfEvents.get(getAdapterPosition()).getmEventName()


                    + "  Check it out @  " + link

                    );
                    Log.d("TAG", "YEAH" + link);

                    startActivity(i);



                    if (Build.VERSION.SDK_INT >= 21) {



                        Animator anim;
                        final int cx = cardView.getWidth() / 2;
                        int cy = cardView.getHeight() / 2;

                        float initialRad = (float) Math.hypot(cx, cy);


                        //first get center


                        anim = ViewAnimationUtils.createCircularReveal(cardView,cx, cy, initialRad, 0);
                        anim.setDuration(400);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                cardView.setVisibility(View.INVISIBLE);
                            }
                        });


                        anim.start();



                    }





                }
            });

            Log.d("TAG", "RANDOM INT   " + randomInt );

            switch(randomInt){

                case 0:{

                    mBackgroundImage.setImageResource(R.drawable.computerimage);
                        break;
                }

                case 1:{
                    mBackgroundImage.setImageResource(R.drawable.party_image);
                        break;
                }

                case 2:{
                    mBackgroundImage.setImageResource(R.drawable.random_image);
                        break;
                }

                case 3:{
                    mBackgroundImage.setImageResource(R.drawable.randome_image6);
                        break;
                }

                case 4:{
                    mBackgroundImage.setImageResource(R.drawable.party_image);
                        break;
                }

                default:
                    mBackgroundImage.setImageResource(R.drawable.no_event_image);
                        break;




            }








            handleEditButton(editButton,cardView);


        }

        public void bindHolder(EventObject obj, int pos){

            this.mPosition = pos;

            titleOfText.setText(obj.getmEventName());
            dayCreated.setText(obj.getmDateCreated());
            daysLeft.setText(String.valueOf(obj.getmDaysRemaining()) + " days left");
            this.mPosition = pos;
        }






    }


    private class EventAdapter extends RecyclerView.Adapter<EventHolder>{

                private ArrayList<EventObject> e;


        public EventAdapter(){


          this.e = EventStash.getInstance().sListOfEvents;



        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
                EventObject eventObj =  e.get(position);
                holder.bindHolder(eventObj,  position);

        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
               View v = inflater.inflate(R.layout.single_event_design,parent,false);

                return new EventHolder(v);

        }


        @Override
        public int getItemCount() {
            return e.size();
        }
    }
}
