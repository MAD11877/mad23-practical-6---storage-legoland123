package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    List<User> userList;
    ImageView imageView, imageView_7;
    Context context;
    public UserAdapter(List<User> userList, Context context){

        this.userList = userList;
        this.context = context;
    }
    @NonNull
    @Override
    public int getItemViewType(int position){
        User user = userList.get(position);
        if (user.name.endsWith("7")) { return 1; }
        else { return 0;}
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.user_view,
                    parent,
                    false);
            return new UserViewHolder(item);
        }
        else {
            View item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.alternative_user_view,
                    parent,
                    false);
            return new UserViewHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User s = userList.get(position);
            holder.nameview.setText(s.name);
            holder.descriptionview.setText(s.description);

            imageView = holder.imageView.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Image Click", "Image has been clicked!");

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(s.name);
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        Intent activityName = new Intent(context, MainActivity.class);
                        activityName.putExtra("User", s);
                        context.startActivity(activityName);
                        Log.d("Image Click", "Postitive has been clicked!");
                    }
                });
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Log.d("Image Click", "Negative has been clicked!");
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
