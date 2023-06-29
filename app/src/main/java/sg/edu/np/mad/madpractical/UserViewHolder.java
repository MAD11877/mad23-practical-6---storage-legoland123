package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView nameview, descriptionview;
    ImageView imageView, imageView_7;
    Context context;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        nameview = itemView.findViewById(R.id.text_name);
        descriptionview = itemView.findViewById(R.id.text_description);
        imageView = itemView.findViewById(R.id.imageView);
        imageView_7 = itemView.findViewById(R.id.imageView_7);
    }
}
