package com.joker.demo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {
    private static final String TAG = "PathView";
    Path path=new Path();
    Paint paint=new Paint();

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

//        path.addRect(0,0,400,400,Path.Direction.CW);
    }



    @Override
    protected void onDraw(Canvas canvas) {
//        drawlineTo(canvas);
//        drawMoveTo2(canvas);
//         drawArcTo(canvas);
//        drawMoveTo(canvas);
//        drawLastPoint(canvas);
//        drawPathDirection(canvas);
          drawRect(canvas);

    }

    private void drawRect(Canvas canvas) {
        // 画一个矩形(蓝色)
        canvas.drawRect(100, 100, 150, 150, paint);

// 将画布的原点移动到(400,500)
        canvas.translate(400,500);
        paint.setColor(Color.RED);

// 再画一个矩形(红色)
        canvas.drawRect(100, 100, 150, 150, paint);
    }

    private void drawArcTo(Canvas canvas) {
        RectF rectF = new RectF(100,100,300,400);
        path.arcTo(rectF,0,180);
        canvas.drawPath(path,paint);
    }

    private void drawMoveTo2(Canvas canvas) {
        canvas.translate(350,500);
        path.moveTo(100,100);
        path.lineTo(300,300);
        canvas.drawPath(path,paint);
    }

    private void drawlineTo(Canvas canvas) {
        path.lineTo(300,300);
        canvas.drawPath(path,paint);
    }

    private void drawPathDirection(Canvas canvas) {
        canvas.translate(350,500);
        path.addRect(0,0,400,400,Path.Direction.CW);
        // 逆时针
//        path.addRect(0,0,400,400, Path.Direction.CCW);
//        path.moveTo(-100, -100);
//        path.lineTo(-100, 100);
//        path.lineTo(100, 100);
//        path.lineTo(100, -100);
//        path.lineTo(-100, -100);
//
//        path.moveTo(-50, -50);
//        path.lineTo(-50, 50);
//        path.lineTo(50, 50);
//        path.lineTo(50, -50);
//
//        path.moveTo(50, -50);
//        path.lineTo(-50, -50);
        canvas.drawPath(path,paint);
    }

    private void drawLastPoint(Canvas canvas) {
// 使用setLastPoint（）
// 起点默认是(0,0)
        //连接点(400,500)
        path.lineTo(400, 500);

        // 将当前点移动到(300, 300)
        // 会影响之前的操作
        // 但不将此设置为新起点
        path.setLastPoint(300, 300) ;

        //连接点(900,800)
        path.lineTo(900, 800);

        //连接点(200,700)
        path.lineTo(200, 700);

        // 闭合路径，即连接当前点和起点
        // 即连接(200,700)与起点(0，0)
        // 注:起点一直没变化
        path.close();

        // 画出路径
        canvas.drawPath(path, paint);
    }

    private void drawMoveTo(Canvas canvas) {
        // 使用moveTo（）
        // 起点默认是(0,0)
        //连接点(400,500)
        path.lineTo(400,500);
        // 将当前点移动到(300, 300)
        path.moveTo(300,300);
        //连接点(900, 800)
        path.lineTo(900,800);
        // 闭合路径，即连接当前点和起点
        // 即连接(200,700)与起点2(300, 300)
        path.lineTo(200, 700);
        // 注:此时起点已经进行变换
        path.close();
        canvas.drawPath(path,paint);
        path.reset();
//        path.rewind();
        Log.d(TAG,"draw...");
    }
}
