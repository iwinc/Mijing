package com.example.mijing.UI;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mijing.Itme.unit;
import com.example.mijing.R;
import com.example.mijing.Tools.BaseActivity;
import com.example.mijing.Tools.ReflectToDrawable;
import com.example.mijing.adapter.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Music extends BaseActivity {
    private ListView lv_units;
    private ImageView iv_recycleviewImage;
    private ArrayList<unit> arr_units;
    private RecyclerView recv_imagListview;
    List<Integer> drawableList;
    private RecyclerviewAdapter recyclerviewAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_units= (ListView) findViewById(R.id.lv_units);
        /*假数据*/
        arr_units=new ArrayList<>();
        arr_units.add(new unit("单元一",R.mipmap.green2 ));
        arr_units.add(new unit("单元二",R.mipmap.green2 ));
        arr_units.add(new unit("单元三",R.mipmap.green2 ));
        arr_units.add(new unit("单元四",R.mipmap.green2 ));
        arr_units.add(new unit("单元五",R.mipmap.green2 ));
        arr_units.add(new unit("单元六",R.mipmap.green2 ));
        lv_units.setAdapter(new BaseAdapter(getApplicationContext(),arr_units));
        HorizaontalimagList();
        lv_units.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (position==0){
                   setrecycleviewData(818,824);
               }
                if (position==1){
                    setrecycleviewData(825,831);
                }
                if (position==2){
                    setrecycleviewData(832,833);
                }
                if (position==3){
                    setrecycleviewData(834,838);
                }
                if (position==4){
                    setrecycleviewData(839,849);
                }
                if (position==5){
                    setrecycleviewData(850,855);
                }
            }
        });



    }
/*给recycleview设置数据*/
    private void setrecycleviewData(int start,int stop) {
        List<Integer> imageIdList=ReflectToDrawable.getDrawableList(start,stop);
        drawableList.clear();
        drawableList.addAll(imageIdList);
        recyclerviewAdapter.notifyDataSetChanged();
    }
 /*给recycleview相关*/
    private void HorizaontalimagList() {
        recv_imagListview= (RecyclerView) findViewById(R.id.recv_data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recv_imagListview.setLayoutManager(linearLayoutManager);
        drawableList = ReflectToDrawable.getDrawableList(818, 819);
        recyclerviewAdapter = new RecyclerviewAdapter(drawableList, this);
        recv_imagListview.setAdapter(recyclerviewAdapter);

    }
/*单元的适配器*/
    static class BaseAdapter extends android.widget.BaseAdapter{
        public BaseAdapter(Context comtent, ArrayList<unit> arr_units) {
            this.comtent = comtent;
            this.arr_units = arr_units;
        }
        private Context comtent;
        private ArrayList<unit>arr_units;
        @Override
        public int getCount() {
            return arr_units.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder ViewHolder=null;
            if (convertView==null){
                ViewHolder=new ViewHolder();
                convertView= LayoutInflater.from(comtent).inflate(R.layout.itme_units,null);
                ViewHolder.tv_unit= (TextView) convertView.findViewById(R.id.tv_unit_itme);
                ViewHolder.iv_unit= (ImageView) convertView.findViewById(R.id.iv_unit_itme);
                convertView.setTag(ViewHolder);
            }else{
                ViewHolder= (BaseAdapter.ViewHolder) convertView.getTag();
            }
            ViewHolder.iv_unit.setBackgroundResource(arr_units.get(position).getUnit_iv());
            ViewHolder.tv_unit.setText(arr_units.get(position).getUnit());
            return convertView;
        }
         static class ViewHolder{
            private TextView tv_unit;
            private ImageView iv_unit;
        }
    }
}
