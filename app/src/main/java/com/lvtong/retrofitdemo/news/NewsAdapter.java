package com.lvtong.retrofitdemo.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lvtong.retrofitdemo.Constants;
import com.lvtong.retrofitdemo.R;

import java.util.List;

/**
 * @author tong.lv
 * @date 2020/1/11
 */
class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    private List<News> mNewsList;

    NewsAdapter(Context newsActivity, List<News> newsList) {
        mContext = newsActivity;
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.tvPostId.setText(news.getPost_id());
        holder.tvAuthorAvatar.setText(news.getAuthor_avatar());
        holder.tvTitle.setText(news.getTitle());
        holder.tvCreatedAt.setText(news.getCreated_at());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, NewsInfoActivity.class);
            intent.putExtra(Constants.NEWS_POST_ID, news.getPost_id());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPostId;
        private TextView tvAuthorAvatar;
        private TextView tvTitle;
        private TextView tvCreatedAt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostId = itemView.findViewById(R.id.tv_post_id);
            tvAuthorAvatar = itemView.findViewById(R.id.tv_author_avatar);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreatedAt = itemView.findViewById(R.id.tv_created_at);
        }
    }
}
