package com.lvtong.retrofitdemo.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lvtong.retrofitdemo.Constants;
import com.lvtong.retrofitdemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * @author tong.lv
 * @date 2020/1/11
 */
class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    private List<CSDNNews.Article> mNewsList;
    /**
     * 创建ImageLoader对象
     */
    private ImageLoader imageLoader = ImageLoader.getInstance();
    /**
     * 创建DisplayImageOptions对象并进行相关选项配置
     */
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            // 设置图片下载期间显示的图片
            .showImageOnLoading(R.drawable.img_loading)
            // 设置图片Uri为空或是错误的时候显示的图片
            .showImageForEmptyUri(R.drawable.img_load_fail)
            // 设置图片加载或解码过程中发生错误显示的图片
            .showImageOnFail(R.drawable.img_load_fail)
            // 设置下载的图片是否缓存在内存中
            .cacheInMemory(true)
            // 设置下载的图片是否缓存在SD卡中
            .cacheOnDisk(true)
            // 设置成圆角图片
            .displayer(new RoundedBitmapDisplayer(20))
            // 创建DisplayImageOptions对象
            .build();

    public void setNewsList(List<CSDNNews.Article> mNewsList) {
        this.mNewsList = mNewsList;
    }

    NewsAdapter(Context newsActivity, List<CSDNNews.Article> newsList) {
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
        CSDNNews.Article news = mNewsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDesc.setText(news.getDesc());
        holder.tvAuthor.setText(news.getNickname());

        imageLoader.displayImage(news.getAvatar(), holder.ivAuthorAvatar, options);
        holder.tvRead.setText(news.getViews());
        holder.tvLike.setText(news.getDigg());
        holder.tvComment.setText(news.getComments());
        holder.tvCreatedAt.setText(news.getCreated_at());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, NewsInfoActivity.class);
            intent.putExtra(Constants.USER_NAME, news.getUser_name());
            intent.putExtra(Constants.ARTICLE_ID, Integer.parseInt(news.getId()));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDesc;
        private TextView tvAuthor;
        private ImageView ivAuthorAvatar;
        private TextView tvTitle;
        private TextView tvCreatedAt;
        private TextView tvLike;
        private TextView tvComment;
        private TextView tvRead;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivAuthorAvatar = itemView.findViewById(R.id.iv_author_avatar);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreatedAt = itemView.findViewById(R.id.tv_created_at);
            tvLike = itemView.findViewById(R.id.tv_like);
            tvComment = itemView.findViewById(R.id.tv_comment);
            tvRead = itemView.findViewById(R.id.tv_read);
        }
    }
}
