package com.joker.demo.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.joker.demo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PathView extends View {
    private static final String TAG = "PathView";
    Path path=new Path();
    Paint paint=new Paint();
    Paint paint2=new Paint();
    private int mWidth, mHeight;        // 宽高

    private int animCurrentPage = 0;       // 当前页码
    private int animMaxPage = 13;           // 总页数
    private int animDuration = 1000;         // 动画时长

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                animCurrentPage++;
                invalidate();
                this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                Log.d(TAG,"animCurrentPage+++++++:::"+animCurrentPage);
            }/*else{
                animCurrentPage--;
                invalidate();
                this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                Log.d(TAG,"animCurrentPage-------:::"+animCurrentPage);
            }*/

        }
    };

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


        paint2.setStrokeWidth(10f);
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.STROKE);
        animCurrentPage = 0;
         mHandler.sendEmptyMessage(0);


//        path.addRect(0,0,400,400,Path.Direction.CW);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawlineTo(canvas);
//        drawMoveTo2(canvas);
//         drawArcTo(canvas);
//        drawMoveTo(canvas);
//        drawLastPoint(canvas);
//        drawPathDirection(canvas);
//          drawRect(canvas);
//          drawPicture();//录制
//          drawBitmap(canvas);
          drawSkew(canvas);//错切


    }

    private void drawSkew(Canvas canvas) {
        // 参数 sx = tan a ，sx>0时表示向X正方向倾斜（即向左）
// 参数 sy = tan b ，sy>0时表示向Y正方向倾斜（即向下）

// 实例
        // 为了方便观察,我将坐标系移到屏幕中央
        canvas.translate(300, 500);
        // 初始矩形
        canvas.drawRect(20, 20, 400, 200, paint);

        // 向X正方向倾斜45度
        canvas.skew(1f, 0);
        canvas.drawRect(20, 20, 400, 200, paint2);

        //向X负方向倾斜45度
        canvas.skew(-1f, 0);
        canvas.drawRect(20, 20, 400, 200, paint2);

        // 向Y正方向倾斜45度
        canvas.skew(0, 1f);
        canvas.drawRect(20, 20, 400, 200, paint2);

        // 向Y负方向倾斜45度
        canvas.skew(0, -1f);
        canvas.drawRect(20, 20, 400, 200, paint2);

    }

    private void drawBitmap(Canvas canvas) {
        // 移动坐标系到画布中央
        canvas.translate(mWidth / 2, mHeight / 2);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_bingo);
        // 指定图片绘制区域
        // 仅绘制图片的二分之一
//        Rect src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());

        // 指定图片在屏幕上显示的区域
//        Rect dst = new Rect(100,100,450,450);



        // 绘制背景圆形
        canvas.drawCircle(0, 0, 240, paint);

        // 得出图像高
        int sideLength = bitmap.getHeight();
        //宽
        int sidewidth = bitmap.getWidth();





        // 得到图像选区 和 实际绘制位置
        Rect src = new Rect(0, 0, sidewidth * (animCurrentPage + 1)/animMaxPage, sideLength);
        Rect dst = new Rect(-sidewidth/2, -sideLength/2, sidewidth * (animCurrentPage + 1)/animMaxPage-sidewidth/2, sideLength-sideLength/2);

        // 绘制
        canvas.drawBitmap(bitmap, src, dst, null);

    }

    private void drawPicture() {
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
