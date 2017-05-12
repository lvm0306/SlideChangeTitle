package com.example.apple.testwp;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Bean> mList = new ArrayList<>();
    Context context;
    RvAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    List<String> stringList = new ArrayList<>();
    TextSwitcher ts;
    int num = 0;
    boolean flag = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ts = (TextSwitcher) findViewById(R.id.tv_main);
        ts.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(MainActivity.this);
                //设置文字大小
                tv.setTextSize(22);
                return tv;
            }
        });
        initPic();
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        adapter = new RvAdapter(context, mList);
        manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int fvip = layoutManager.findFirstVisibleItemPosition();
                if (num != fvip) {
                    ts.setText(mList.get(fvip).getTitle());
                    num = fvip;
                } else if (!flag) {
                    ts.setText(mList.get(fvip).getTitle());
                    flag = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        initData();
    }

    private void initPic() {
        stringList.add("http://pic.58pic.com/58pic/13/68/88/88V58PICteP_1024.jpg");
        stringList.add("http://pic62.nipic.com/file/20150326/14770777_194955298000_2.jpg");
        stringList.add("http://img17.3lian.com/d/file/201701/23/b8d31e0454e2dbab999b5818fafbae47.jpg");
        stringList.add("http://pic1.win4000.com/wallpaper/1/5718965445907.jpg");
        stringList.add("http://pic.58pic.com/58pic/13/75/13/01e58PICgPY_1024.jpg");
        stringList.add("http://pic12.nipic.com/20101226/4988562_091406003194_2.jpg");
        stringList.add("http://pic.58pic.com/58pic/17/30/00/58PIC9p58PICiBn_1024.jpg");
        stringList.add("http://img2.91.com/uploads/allimg/131010/32-131010150J8.jpg");
        stringList.add("http://pic.58pic.com/58pic/17/14/25/56N58PICCmT_1024.jpg");
        stringList.add("http://image.tianjimedia.com/uploadImages/2011/353/11MO893UB9R1.jpg");
        stringList.add("http://pic.90sjimg.com/back_pic/00/00/52/00/140b25269c394d63a6210004bec55776.jpg");
        stringList.add("http://pic1.win4000.com/wallpaper/6/578855a8e41e1.jpg");
        stringList.add("http://tupian.enterdesk.com/2013/mxy/12/11/4/3.jpg");
        stringList.add("http://img.pconline.com.cn/images/upload/upc/tx/itbbs/1312/01/c4/29178321_1385862629022_mthumb.jpg");
        stringList.add("http://pic.58pic.com/58pic/16/00/52/11J58PICD3S_1024.jpg");
        stringList.add("http://pic.58pic.com/58pic/17/14/25/43Y58PICfJB_1024.jpg");
        stringList.add("http://pic38.nipic.com/20140228/8821914_204428973000_2.jpg");
    }

    private void initData() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Bean bean = new Bean();
                    bean.setTitle("第" + i + "个");
                    List<String> ss = new ArrayList<String>();
                    for (int j = 0; j < 5; j++) {
                        ss.add(stringList.get(j));
                    }
                    bean.setList(stringList);
                    mList.add(bean);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
