package com.xiaoer.watermark.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.xiaoer.watermark.bean.WaterMarkConfig;
import com.xiaoer.watermark.util.DensityUtils;

public class WatermarkDrawable extends Drawable {
    public final Paint mPaint;
    /**
     * 水印文本
     */
    public String mText;
    /**
     * 字体颜色，十六进制形式，例如：0xAEAEAEAE
     */
    public int mTextColor = 0xAEAEAEAE;
    /**
     * 字体大小，单位为sp
     */
    public float mTextSize;
    /**
     * 旋转角度
     */
    public float mRotation;

    private final Context mContext;

    /**
     * 水印图片
     */
    public Bitmap mBitmap;
    /**
     * 图片透明度 (0-255)
     */
    public int mImageAlpha = 128;
    /**
     * 是否使用图片模式
     */
    public boolean mUseImage = false;

    public WatermarkDrawable(Context context) {
        this.mPaint = new Paint();
        this.mContext = context;
        loadDefaultImage();
    }

    private void loadDefaultImage() {
        // 加载内置图片资源
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.default_watermark);
    }

    public WatermarkDrawable(Context context, WaterMarkConfig waterMarkConfig) {
        this.mPaint = new Paint();
        this.mContext = context;
        this.mText = waterMarkConfig.getContent();
        this.mTextColor = waterMarkConfig.getTextColor();
        this.mTextSize = waterMarkConfig.getTextSize();
        this.mRotation = waterMarkConfig.getRotation();
        this.mUseImage = waterMarkConfig.isUseImage();
        this.mImageAlpha = waterMarkConfig.getImageAlpha();
        loadDefaultImage();
    }

    @Override
    public void draw(Canvas canvas) {
        int width = getBounds().right;
        int height = getBounds().bottom;
        int diagonal = (int) Math.sqrt(width * width + height * height);

        canvas.drawColor(0x00000000);
        canvas.translate(width / 2f, height / 2f);
        canvas.rotate(mRotation);
        canvas.translate(-width / 2f, -height / 2f);

        if (mUseImage && mBitmap != null) {
            drawImageWatermark(canvas, width, height, diagonal);
        } else {
            drawTextWatermark(canvas, width, height, diagonal);
        }

        canvas.save();
        canvas.restore();
    }

    private void drawImageWatermark(Canvas canvas, int width, int height, int diagonal) {
        Paint imagePaint = new Paint();
        imagePaint.setAlpha(mImageAlpha);
        imagePaint.setAntiAlias(true);
        
        float imageWidth = mBitmap.getWidth();
        float imageHeight = mBitmap.getHeight();
        
        int index = 0;
        float fromX;
        for (int positionY = diagonal / 10; positionY <= diagonal; positionY += diagonal / 10) {
            fromX = -width + (index++ % 2) * imageWidth;
            for (float positionX = fromX; positionX < width; positionX += imageWidth * 2) {
                canvas.drawBitmap(mBitmap, positionX, positionY - imageHeight, imagePaint);
            }
        }
    }

    private void drawTextWatermark(Canvas canvas, int width, int height, int diagonal) {
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(DensityUtils.dip2px(mContext, mTextSize));
        mPaint.setAntiAlias(true);
        float textWidth = mPaint.measureText(mText);

        int index = 0;
        float fromX;
        for (int positionY = diagonal / 10; positionY <= diagonal; positionY += diagonal / 10) {
            fromX = -width + (index++ % 2) * textWidth;
            for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                canvas.drawText(mText, positionX, positionY, mPaint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}





