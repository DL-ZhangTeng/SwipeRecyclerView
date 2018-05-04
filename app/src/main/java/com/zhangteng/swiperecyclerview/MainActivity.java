package com.zhangteng.swiperecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.TextView;

import com.zhangteng.swiperecyclerview.adapter.HeaderOrFooterAdapter;
import com.zhangteng.swiperecyclerview.widget.SlideMenuRecyclerViewItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyViewHolder(new SlideMenuRecyclerViewItem(parent.getContext()));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                //添加内容布局&菜单布局
                ((MyViewHolder) holder).item.addContentView(R.layout.content_item);
                ((MyViewHolder) holder).item.addMenuView(R.layout.menu_item);
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class MyViewHolder extends RecyclerView.ViewHolder {
                public SlideMenuRecyclerViewItem item;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    item = (SlideMenuRecyclerViewItem) itemView;
                }
            }
        };
        //添加头部&脚步布局
        HeaderOrFooterAdapter headerOrFooterAdapter = new HeaderOrFooterAdapter(adapter) {
            @Override
            public RecyclerView.ViewHolder createHeaderOrFooterViewHolder(Context context, View view) {
                return new MyViewHolder(view);
            }

            @Override
            public void onBindHeaderOrFooterViewHolder(@NonNull RecyclerView.ViewHolder holder, int viewType) {
                if (viewType == 100000) {
                    ((TextView) holder.itemView).setText("1111111111");
                } else if (viewType == 200000) {
                    ((TextView) holder.itemView).setText("2222222222");
                }
            }

            class MyViewHolder extends RecyclerView.ViewHolder {

                public MyViewHolder(View itemView) {
                    super(itemView);
                }
            }
        };
        headerOrFooterAdapter.addFootView(new TextView(this));
        headerOrFooterAdapter.addHeaderView(new TextView(this));
        recyclerView.setAdapter(headerOrFooterAdapter);
    }
}
