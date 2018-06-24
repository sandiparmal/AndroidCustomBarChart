package com.infosys.androidbarchartassignment.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.infosys.androidbarchartassignment.retrofit.data.Graph;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by sandy on 6/22/2018.
 */

public class BarChartView extends View {

    public static boolean BAR = true;

    private Paint paint;

    public void setRxRadiusCorner(float rxRadiusCorner) {
        this.rxRadiusCorner = rxRadiusCorner;
    }

    public void setRyRadiusCorner(float ryRadiusCorner) {
        this.ryRadiusCorner = ryRadiusCorner;
    }

    public void setLineColor(int color) {
        this.lineColor = color;
    }

    public void setTextLabelColor(int textLabelColor) {
        this.textLabelColor = textLabelColor;
    }

    float rxRadiusCorner = 0.0f;
    float ryRadiusCorner = 0.0f;
    int lineColor = Color.BLUE;
    int textLabelColor = Color.DKGRAY;

    Context context;
    ArrayList<Graph> arrayList;

    public BarChartView(Context context, ArrayList<Graph> arrayList) {
        super(context);

        this.arrayList = arrayList;
        paint = new Paint();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        context = getContext();
        float margin = 25;
        float horizontalMargin = margin * 2;
        float height = getHeight();
        float width = getWidth();

        // height and width of the graph
        float graphHeight = height - (2 * margin);
        float graphWidth = width - (2 * margin);
        // vertical graph value
        paint.setTextAlign(Paint.Align.LEFT);
        int arrayListSize = arrayList.size();

        // graph divided in to 10 parts vertically
        int verticalColHeight = 10;

        // minimum and maximum values from arraylist
        float minimumValueFromArray = getMin();
        float maximumValueFromArray = getMax();
        float difference = (maximumValueFromArray - minimumValueFromArray) / 10;

        for (int i = 0; i <= verticalColHeight; i++) {
            paint.setColor(Color.DKGRAY);
            float y = ((graphHeight / arrayListSize) * i) + margin;
            if (i == verticalColHeight) {
                canvas.drawLine(horizontalMargin, y, width, y, paint);
            }

            paint.setColor(textLabelColor);
            paint.setTextSize(20f);

            String valueToSet = new DecimalFormat("##.#").format(maximumValueFromArray);
            canvas.drawText(valueToSet, 0, y, paint);
            maximumValueFromArray = maximumValueFromArray - difference;
        }

        // horizontal graph value
        for (int i = 0; i < arrayListSize; i++) {
            paint.setColor(Color.DKGRAY);
            float x = ((graphWidth / arrayListSize) * i) + horizontalMargin;
            if (i == 0) {
                canvas.drawLine(x, height - margin, x, margin, paint);
            }
            paint.setTextAlign(Paint.Align.CENTER);
            if (i == arrayListSize)
                paint.setTextAlign(Paint.Align.RIGHT);
            if (i == 0)
                paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(textLabelColor);
            paint.setTextSize(20f);
            canvas.drawText(String.valueOf(arrayList.get(i).getIndex()), x + 30, height - 5, paint);
        }


        if (maximumValueFromArray != minimumValueFromArray) {
            paint.setColor(lineColor);
            paint.setStyle(Paint.Style.FILL);

            if (BAR) {
                float listSize = arrayList.size();
                float colWidth = (width - (2 * margin)) / listSize;
                for (int iCount = 0; iCount < listSize; iCount++) {
                    float graph_h = getHeight() - (margin * 2);

                    float ind_h = graph_h / 1;
                    float t = arrayList.get(iCount).getValue() / 5;
                    float top = (graph_h - ind_h * (t));
                    float acc = ind_h / 5;
                    acc = acc * (arrayList.get(iCount).getValue() % 5);
                    canvas.drawRoundRect((iCount * colWidth) + horizontalMargin, top + margin - acc, ((iCount * colWidth) + horizontalMargin) + (colWidth - 1), graph_h + margin, rxRadiusCorner, ryRadiusCorner, paint);
                }
            }
        }
    }


    private float getMax() {
        float largest = Integer.MIN_VALUE;
        for (int i = 0; i < arrayList.size(); i++)
            if (arrayList.get(i).getValue() > largest)
                largest = arrayList.get(i).getValue();
        return largest;
    }

    private float getMin() {
        float smallest = Integer.MAX_VALUE;
        for (int i = 0; i < arrayList.size(); i++)
            if (arrayList.get(i).getValue() < smallest)
                smallest = arrayList.get(i).getValue();
        return smallest;
    }

}