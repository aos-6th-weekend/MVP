package com.example.rathana.retrofit_demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rathana.retrofit_demo.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articles;
    private Context context;

    public ArticleAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
        this.callback= (ArticleCallback) context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article article= articles.get(i);
        // TODO: 2/3/19

        viewHolder.title.setText(article.getTitle());
        viewHolder.authorName.setText(article.getAuthor().getName());
        viewHolder.date.setText(article.getCreatedDate());
        Glide.with(context).load(article.getImage())
                .into(viewHolder.thumb);

        viewHolder.btnDelete.setOnClickListener(v->{
            callback.onDelete(article,viewHolder.getAdapterPosition());
        });
        viewHolder.itemView.setOnClickListener(v->{
            callback.onArticleItemClicked(article);
        });

    }

    @Override
    public int getItemCount() {
        return this.articles.size();
    }

    public void updateArticles(List<Article> articles) {
        this.articles.addAll(articles);
        notifyDataSetChanged();

    }

    public void setArticle(Article article) {
        this.articles.add(0,article);
        notifyItemInserted(0);
    }

    public void deleteArticle(Article article, int pos) {
        this.articles.remove(article);
        notifyItemRemoved(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumb,btnDelete;
        TextView title,date,authorName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb=itemView.findViewById(R.id.thumb);
            title=itemView.findViewById(R.id.title);
            date=itemView.findViewById(R.id.date);
            authorName=itemView.findViewById(R.id.authorName);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }


    private  ArticleCallback callback;
    public  interface ArticleCallback{
        void onDelete(Article article,int pos);

        void onArticleItemClicked(Article article);

    }
}
