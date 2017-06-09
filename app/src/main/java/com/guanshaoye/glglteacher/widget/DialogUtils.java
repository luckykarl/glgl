package com.guanshaoye.glglteacher.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.guanshaoye.glglteacher.R;
import com.guanshaoye.glglteacher.adapter.DialogAdapter;
import com.guanshaoye.glglteacher.adapter.OnItemClickListener;
import com.guanshaoye.glglteacher.bean.AttendClassBean;
import com.guanshaoye.glglteacher.bean.ItemListBean;
import com.guanshaoye.glglteacher.bean.ScheduleListBean;

import java.util.List;

/**
 * Created by ${张梦博} on 2017/3/6.
 * Whenever,Wherever,Whatever,I believe I can handle everything
 */
public class DialogUtils {
    public static Dialog dateDialog;
    /**
     * 约课
     * @param context
     * @param
     * @return
     */
    public static Dialog appointCourseDialog(Context context,String title, final ScheduleListBean scheduleBean, final DialogClickListener listener) {
        final Dialog dialog = new Dialog(context, R.style.dialog_normal);
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_appoint_course, null);
        dialog.setContentView(view);
        WindowManager wmManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wmManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth()*2/3; // 设置宽度
        dialog.getWindow().setAttributes(lp);
        Window window = dialog.getWindow();
        // window.setWindowAnimations(R.style.dialogWindowAnim);
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        TextView titleView = (TextView) view.findViewById(R.id.tv_alert_content);
        titleView.setText(title);
        view.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clicksure(scheduleBean);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }
    public interface DialogClickListener{
        void clicksure(ScheduleListBean scheduleBean);
    }
   /* *//**
     * 头像选取
     * @param context
     * @return
     */
    public static Dialog cameraMenuDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.dialog_normal);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_camera_picture, null);
        dialog.setContentView(view);
        WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wmManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        dialog.getWindow().setAttributes(lp);
        Window window = dialog.getWindow();
        // window.setWindowAnimations(R.style.dialogWindowAnim);
        window.setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog shaoDateDialog(final Context context,List<ItemListBean> list,OnItemClickListener itemClickListener ){
         dateDialog = new Dialog(context, R.style.dialog_normal);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_date_select_lay, null);
        dateDialog.setContentView(view);
        WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wmManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dateDialog.getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        dateDialog.getWindow().setAttributes(lp);
        Window window = dateDialog.getWindow();
        // window.setWindowAnimations(R.style.dialogWindowAnim);
        window.setGravity(Gravity.BOTTOM);
        dateDialog.setCancelable(true);
        dateDialog.setCanceledOnTouchOutside(true);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dialog_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DialogAdapter adapter = new DialogAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setDataList(list);
        adapter.setOnItemClickListener(itemClickListener);
        return dateDialog;
    }


}
