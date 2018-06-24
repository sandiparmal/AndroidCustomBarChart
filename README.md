# AndroidCustomBarChart
Android Line/Bar chart example without any third party library

Initialize BarChartView  

    BarChartView barChartView = new BarChartView(this, arrayList);  
    barChartView.setRxRadiusCorner(25f);
    barChartView.setRyRadiusCorner(25f);
    barChartView.setTextLabelColor(Color.DKGRAY);
    barChartView.setLineColor(ContextCompat.getColor(this,R.color.lineBarColor));
    
Add BarchartView into any view or layout  

    graphView.addView(barChartView);
    
Screenshot
![alt text](https://github.com/sandiparmal/AndroidCustomBarChart/blob/master/app/release/screenshot.png)


