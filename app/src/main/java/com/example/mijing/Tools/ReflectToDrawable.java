package com.example.mijing.Tools;
import com.example.mijing.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016-08-11.
 */
public class ReflectToDrawable {
    public static List<Integer> getDrawableList(int start,int end){
        List<Integer> list=new ArrayList<>();
        try {
            R.drawable d=new R.drawable();
            for (int i=start;i<=end;i++){
                Field fieldId=d.getClass().getDeclaredField("u"+i);
                int imageId= (int) fieldId.get(d);
                list.add(imageId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
