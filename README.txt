# SwipeRecyclerView
封装头部脚部快速添加，自定义左滑菜单item，长按移动item
1.正常使用recyclerview，adater使用BaseAdapter（SlideMenuRecyclerViewItem实现侧滑菜单）
 RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List list = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        RecyclerView.Adapter adapter = new BaseAdapter<Integer>(list) {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyViewHolder(new SlideMenuRecyclerViewItem(parent.getContext()));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                View contentView = LayoutInflater.from(SwipeRecyclerViewActivity.this).inflate(R.layout.content_item, ((MyViewHolder) holder).item, false);
                //添加内容布局&菜单布局
                ((TextView) contentView.findViewById(R.id.show)).setText(String.valueOf(data.get(position)));
                ((MyViewHolder) holder).item.addContentView(contentView);
                ((MyViewHolder) holder).item.addMenuView(R.layout.menu_item);
            }

            @Override
            public int getItemCount() {
                return data.size();
            }

            class MyViewHolder extends RecyclerView.ViewHolder {
                public SlideMenuRecyclerViewItem item;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    item = (SlideMenuRecyclerViewItem) itemView;
                }
            }
        };
        
        
2.使用HeaderOrFooterAdapter添加头脚布局（使用多个子item实现，在已有的代码基础上快速添加）
        //添加头部&脚步布局
        HeaderOrFooterAdapter headerOrFooterAdapter = new HeaderOrFooterAdapter((BaseAdapter) adapter) {
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
3.使用ItemMoveTouchHelper，MoveTouchCallback添加拖曳处理（交换，完成后的处理重写onSwiped实现）
        ItemMoveTouchHelper itemMoveTouchHelper = new ItemMoveTouchHelper(new ItemMoveTouchHelper.MoveTouchCallback());
        itemMoveTouchHelper.attachToRecyclerView(recyclerView);
