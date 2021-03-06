package com.opengl.youyang.myapplication;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by youyang on 16/5/24.
 */
public class PointRender implements GLSurfaceView.Renderer {

    private FloatBuffer mFloatBuffer;

    private Context mContext;

    public PointRender(Context context){
        mContext = context;
    }

//    float[] mVertex = new float[]{
//            0f,0f
//    } ;

    float aPoint = 0.8f;
    float[] mVertex = new float[]{
            -aPoint,aPoint,
            -aPoint,-aPoint,
            aPoint,-aPoint,

            aPoint,-aPoint,
            aPoint,aPoint,
            -aPoint,aPoint

    } ;

    private ColorShaderProgram mColorShaderProgram;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f,0.5f,0.3f,0.4f);

        mColorShaderProgram = new ColorShaderProgram(mContext);

        mFloatBuffer = ByteBuffer.allocateDirect(mVertex.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer().put(mVertex);
        mFloatBuffer.position(0);

        mColorShaderProgram.useProgram();
        mColorShaderProgram.setUniforms(0.9f,0.4f,0.9f);

        GLES20.glVertexAttribPointer(mColorShaderProgram.getPositionAttributionLocation(),2,GLES20.GL_FLOAT,false,2*4,mFloatBuffer);
        GLES20.glEnableVertexAttribArray(mColorShaderProgram.getPositionAttributionLocation());
//        mFloatBuffer.position(0);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置显示区域大小和位置
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

//        最终的绘制方法
//        GLES20.glDrawArrays(GLES20.GL_POINTS,0,1);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP,0,6);
    }

}
