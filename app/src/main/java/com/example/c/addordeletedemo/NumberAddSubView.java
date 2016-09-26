package com.example.c.addordeletedemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by C罗 on 2016/9/26.
 */
public class NumberAddSubView extends LinearLayout implements View.OnClickListener{

    private LayoutInflater mInflater;
    private Button addBtn,subBtn;
    private TextView mNumtv;

    private int value;
    private int minValue;
    private int maxValue;
    private OnButtonClickListener mOnButtonClickListener;

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_btn){
           numadd();
            if(mOnButtonClickListener!=null){
                mOnButtonClickListener.onButtonAddClick(v,value);
            }
        }else if(v.getId()==R.id.sub_btn){
             numSub();
            if(mOnButtonClickListener!=null){
                mOnButtonClickListener.onButtonAddClick(v,value);
            }
        }
    }

    public int getValues(){
        String val=mNumtv.getText().toString();
        if(val!=null && !"".equals(val)){
            this.value=Integer.parseInt(val);
        }
        return value;
    }

    public void setValu(int value){
        mNumtv.setText(value+"");
        this.value=value;
    }

    private void numadd(){
        if(value<maxValue){
            value++;
        }
        mNumtv.setText(value+"");
    }

    private void numSub(){
        if(value>minValue){
            value--;
        }
        mNumtv.setText(value+"");
    }

    public interface OnButtonClickListener{
        void onButtonAddClick(View view,int value);
        void onButtonSubClick(View view,int value);
    }

    public void setmOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.mOnButtonClickListener=onButtonClickListener;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NumberAddSubView(Context context) {
        this(context,null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater=LayoutInflater.from(context);
        initView();
        if(attrs!=null){
            TintTypedArray tta=TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.NumberAddSubView,defStyleAttr,0);
            int val=tta.getInt(R.styleable.NumberAddSubView_value,0);
            setValu(val);
            int minval=tta.getInt(R.styleable.NumberAddSubView_minvalue,0);
            setMinValue(minval);
            int maxval=tta.getInt(R.styleable.NumberAddSubView_maxvalue,0);
            setMaxValue(maxval);

            Drawable addBtnDrawable=tta.getDrawable(R.styleable.NumberAddSubView_addBtnBg);
            setAddBtnBackground(addBtnDrawable);
            Drawable subBtnDrawable=tta.getDrawable(R.styleable.NumberAddSubView_subBtnBg);
            setSubBtnBackground(subBtnDrawable);

            int numTvDraeable=tta.getResourceId(R.styleable.NumberAddSubView_numTvBg,R.color.white);
            setNumTvBackground(numTvDraeable);

            tta.recycle();
        }
    }

    private void setNumTvBackground(int numTvDraeable) {
        this.mNumtv.setBackgroundResource(numTvDraeable);
    }

    private void setSubBtnBackground(Drawable subBtnDrawable) {
        this.subBtn.setBackground(subBtnDrawable);
    }

    private void setAddBtnBackground(Drawable addBtnDrawable) {
        this.addBtn.setBackground(addBtnDrawable);
    }

    private void initView(){
        View view=mInflater.inflate(R.layout.wieght_number_add_sub,this,true);
        addBtn= (Button) view.findViewById(R.id.add_btn);
        subBtn= (Button) view.findViewById(R.id.sub_btn);
        mNumtv= (TextView) view.findViewById(R.id.num_tv);

        addBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);
    }
}
