package sg.edu.np.mad.madpractical;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    final String TITLE = "Main Activity";
    private List<User> userList;
    MyDBHandler myDBHandler = new MyDBHandler(this,null,null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        userList = new ArrayList<User>();

        // Generate and insert 20 User data
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            String name = "Name" + (random.nextInt(100000));
            String description = "Description " + (random.nextInt(100000));
            boolean bool = random.nextBoolean();
            myDBHandler.addUser(new User(name, description, i, bool));
        }

//      Week 3
//        ImageView imageView1 = findViewById(R.id.imageView);
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
//                builder.setTitle("Profile");
//                builder.setMessage("MADness");
//                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int id){
//                        Random random = new Random();
//                        int randomNumber = random.nextInt(100000);
//                        Intent activityName = new Intent(ListActivity.this, MainActivity.class);
//                        activityName.putExtra("randomint", String.valueOf(randomNumber));
//                        startActivity(activityName);
//                    }
//                });
//                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog, int id){
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        userList = myDBHandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter mAdapter =
                new UserAdapter(userList,this);
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}