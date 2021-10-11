package com.example.androidassignments;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.View;

//Chat window class
public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";

    //Variables
    ListView v;
    EditText t;
    Button b;
    ArrayList<String> list = new ArrayList<String>();
    @Override

    //Creating the window
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //More variables
        v = (ListView) findViewById(R.id.chatView);
        t = (EditText) findViewById(R.id.text);
        b = (Button) findViewById(R.id.sendButton);
        ChatAdapter messageAdapter = new ChatAdapter( this );
        v.setAdapter(messageAdapter);

        //Button listener
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting string
                String w = (t.getText()).toString();

                //Sending string
                list.add(w);
                int position = messageAdapter.getCount();
                messageAdapter.notifyDataSetChanged();
                messageAdapter.getView(position - 1, null, null);
                t.setText("");
            }
        });
    }
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    //Chat class
    private class ChatAdapter extends ArrayAdapter<String> {

        //Variables
        public ChatAdapter(Context ctx) { super(ctx,0); }
        public int getCount() { return list.size(); }
        public String getItem(int positions){ return (list.get(positions)); }
        public View getView(int position, View convertView, ViewGroup parent){

            //Setting layout
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View r = null ;

            //Checking position
            if(position % 2 == 0){
                r = inflater.inflate(R.layout.chat_row_outgoing, null);}
            else{
                r = inflater.inflate(R.layout.chat_row_incoming, null);}

            //Setting message
            TextView message = (TextView)r.findViewById(R.id.messageText);
            message.setText(getItem(position));
            return r;
        }
    }
}