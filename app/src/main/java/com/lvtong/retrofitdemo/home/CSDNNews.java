package com.lvtong.retrofitdemo.home;

import java.io.Serializable;
import java.util.List;

/**
 * @author tong.lv
 * @date 2020/1/14
 */
class CSDNNews implements Serializable {
    private static final long serialVersionUID = 1398492434130711121L;
    private Boolean status;
    private String last_view_time;
    private long shown_offset;
    private Boolean no_wartchers;
    private List<Article> articles;

    @Override
    public String toString() {
        return "CSDNNews{" +
                "status=" + status +
                ", last_view_time='" + last_view_time + '\'' +
                ", shown_offset=" + shown_offset +
                ", no_wartchers=" + no_wartchers +
                ", articles=" + articles +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLast_view_time() {
        return last_view_time;
    }

    public void setLast_view_time(String last_view_time) {
        this.last_view_time = last_view_time;
    }

    public long getShown_offset() {
        return shown_offset;
    }

    public void setShown_offset(long shown_offset) {
        this.shown_offset = shown_offset;
    }

    public Boolean getNo_wartchers() {
        return no_wartchers;
    }

    public void setNo_wartchers(Boolean no_wartchers) {
        this.no_wartchers = no_wartchers;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public class Article implements Serializable{

        private static final long serialVersionUID = 7593089566079017426L;
        private String tace_code;
        private long shown_offset;
        private String user_name;
        private String views;
        private String type;
        private String cache_time;
        private String strategy;
        private String summary;
        private String comments;
        private String digg;
        private String created_at;
        private String title;
        private String id;
        private String commits;
        private String category_id;
        private String recommend;
        private String desc;
        private String channelId;
        private String strategy_id;
        private String nickname;
        private String category;
        private String shown_time;
        private String url;
        private String user_url;
        private String avatar;
        private Boolean is_watched;

        @Override
        public String toString() {
            return "Article{" +
                    "tace_code='" + tace_code + '\'' +
                    ", shown_offset=" + shown_offset +
                    ", user_name='" + user_name + '\'' +
                    ", views='" + views + '\'' +
                    ", type='" + type + '\'' +
                    ", cache_time='" + cache_time + '\'' +
                    ", strategy='" + strategy + '\'' +
                    ", summary='" + summary + '\'' +
                    ", comments='" + comments + '\'' +
                    ", digg='" + digg + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", title='" + title + '\'' +
                    ", id='" + id + '\'' +
                    ", commits='" + commits + '\'' +
                    ", category_id='" + category_id + '\'' +
                    ", recommend='" + recommend + '\'' +
                    ", desc='" + desc + '\'' +
                    ", channelId='" + channelId + '\'' +
                    ", strategy_id='" + strategy_id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", category='" + category + '\'' +
                    ", shown_time='" + shown_time + '\'' +
                    ", url='" + url + '\'' +
                    ", user_url='" + user_url + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", is_watched=" + is_watched +
                    '}';
        }

        public String getTace_code() {
            return tace_code;
        }

        public void setTace_code(String tace_code) {
            this.tace_code = tace_code;
        }

        public long getShown_offset() {
            return shown_offset;
        }

        public void setShown_offset(long shown_offset) {
            this.shown_offset = shown_offset;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCache_time() {
            return cache_time;
        }

        public void setCache_time(String cache_time) {
            this.cache_time = cache_time;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getDigg() {
            return digg;
        }

        public void setDigg(String digg) {
            this.digg = digg;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCommits() {
            return commits;
        }

        public void setCommits(String commits) {
            this.commits = commits;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getStrategy_id() {
            return strategy_id;
        }

        public void setStrategy_id(String strategy_id) {
            this.strategy_id = strategy_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getShown_time() {
            return shown_time;
        }

        public void setShown_time(String shown_time) {
            this.shown_time = shown_time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser_url() {
            return user_url;
        }

        public void setUser_url(String user_url) {
            this.user_url = user_url;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Boolean getIs_watched() {
            return is_watched;
        }

        public void setIs_watched(Boolean is_watched) {
            this.is_watched = is_watched;
        }
    }
}
