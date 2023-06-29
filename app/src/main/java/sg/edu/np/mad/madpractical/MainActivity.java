package sg.edu.np.mad.madpractical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final String TITLE = "Main Activity";
    Button myButton;
    MyDBHandler myDBHandler = new MyDBHandler(this,null,null,1);

    User myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");
        TextView wlctext = findViewById(R.id.textView);
        TextView longtext = findViewById(R.id.textView2);
        myButton = findViewById(R.id.button);

        Intent intent = getIntent();
        myUser = intent.getParcelableExtra("User");

        wlctext.setText(myUser.name);
        longtext.setText(myUser.description);
        if (myUser.followed) {
            myButton.setText("UNFOLLOW");
        }
        else {
            myButton.setText("FOLLOW");
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE, "On Pause!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TITLE, "On Resume!");

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TITLE, "Follow Button is pressed");
                if (myUser.followed) {
                    myUser.followed = false;
                    myButton.setText("FOLLOW");
                    Toast.makeText(getApplicationContext(), "UNFOLLWED", Toast.LENGTH_SHORT).show();
                    myDBHandler.updateUser(myUser);
                }
                else if (!myUser.followed){
                    myUser.followed = true;
                    myButton.setText("UNFOLLOW");
                    Toast.makeText(getApplicationContext(), "FOLLOWED", Toast.LENGTH_SHORT).show();
                    myDBHandler.updateUser(myUser);
                }
            }
        });

        Button myButton2 = findViewById(R.id.button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TITLE, "Message Button is pressed");
                Intent activityName = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(activityName);
            }

        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TITLE, "On Stop!");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TITLE, "On Destroy!");
    }
}