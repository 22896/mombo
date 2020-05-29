package com.example.mombo.chatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mombo.R;
import com.example.mombo.Util.PreferenceUtil;
import com.example.mombo.domain.MyMessage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyAdapter> {

    ArrayList<MyMessage> list = new ArrayList<>();
    final static int MY_MESSAGE = 0;
    final static int YOUR_MESSAGE = 1;
    Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(ArrayList<MyMessage> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        /**
         * SharedPreference에 저장된 나의 정보와 채팅에 담겨있는 user_num이 같을 경우 MY_MESSAGE를 반환한다.
         * 아닐 경우 YOUR_MESSAGE를 반환한다.
         */
        if ((list.get(position).user_num).equals(PreferenceUtil.getStringValue(context, "myNum"))) {
            return MY_MESSAGE;
        } else {
            return YOUR_MESSAGE;
        }
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        // ViewType에 따라 어떤 view를 뿌려줄지 정한다.
        if (viewType == MY_MESSAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_chat_box, parent, false);
            return new MyAdapter(view);
        } else if (viewType == YOUR_MESSAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_chat_box, parent, false);
            return new MyAdapter(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        holder.textViewMyChat.setText(list.get(position).message);
        holder.textViewTime.setText(list.get(position).messageTime);
        holder.profileUrl = list.get(position).profileUrl;
        URL url = null;
        try {
            url = new URL(holder.profileUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Glide.with(context).load(url).into(holder.imageProfile);


        holder.textViewDivideLine.setText("------------------------ " + list.get(position).messageDate + "-----------------------");
        if (position >= 1 && list.get(position).messageDate.equals(list.get(position - 1).messageDate)) {
            holder.textViewDivideLine.setVisibility(View.GONE);
        } else {
            holder.textViewDivideLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyAdapter extends RecyclerView.ViewHolder {
        TextView textViewMyChat;
        TextView textViewTime;
        TextView textViewDivideLine;
        ImageView imageProfile;
        String profileUrl;


        public MyAdapter(View itemView) {
            super(itemView);

            textViewMyChat = itemView.findViewById(R.id.textViewMyChat);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewDivideLine = itemView.findViewById(R.id.textViewDivideLine);
            imageProfile = itemView.findViewById(R.id.imageProfile);


        }
    }
}
