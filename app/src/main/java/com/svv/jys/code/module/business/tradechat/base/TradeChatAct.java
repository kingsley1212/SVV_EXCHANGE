package com.svv.jys.code.module.business.tradechat.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpDataSocketActivity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.tradechat.base.model.ITradeChatModel;
import com.svv.jys.code.module.business.tradechat.base.mychat.MyDataParse;
import com.svv.jys.code.module.business.tradechat.base.presenter.TradeChatPresenter;
import com.svv.jys.code.module.business.tradechat.base.view.ITradeChatView;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.server.dataserver.event.TradeJsonEvent;
//import com.bbw.bbw.code.module.tradechat.base.mychat.CoupleChartGestureListener;
//import com.bbw.bbw.code.module.tradechat.base.mychat.VolFormatter;
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.charts.Chart;
//import com.github.mikephil.charting.charts.CombinedChart;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.components.YAxis;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.data.CandleData;
//import com.github.mikephil.charting.data.CandleDataSet;
//import com.github.mikephil.charting.data.CandleEntry;
//import com.github.mikephil.charting.data.CombinedData;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.highlight.Highlight;
//import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
//import com.github.mikephil.charting.utils.Utils;
//import com.github.mikephil.charting.utils.ViewPortHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lzj on 2018/5/30.
 */

public class TradeChatAct extends MvpDataSocketActivity<ITradeChatView, ITradeChatModel, TradeChatPresenter> implements
        ITradeChatView {

    private TextView toBuy, toSell;

    public static void startAct(Context mContext, MarketListEntity entity) {
        Intent intent = new Intent(mContext, TradeChatAct.class);
        intent.putExtra("MarketListEntity", entity);
//        mContext.startActivity(intent);
        ((Activity) mContext).startActivityForResult(intent, 500);
    }

    private TextView title, np_tv, highp_tv, lowp_tv, tradeamount_tv, cny_tv, cnyp_tv;


    private View min_rl, min15_rl, hour_rl, hour4_rl, day_rl, other_rl, leftImg_ly;
    private TextView min_tv, min15_tv, hour_tv, hour4_tv, day_tv, other_tv;
    private View min_vw, min15_vw, hour_vw, hour4_vw, day_vw, other_vw;
    private View[] rls = new View[6];
    private TextView[] tvs = new TextView[6];
    private View[] vws = new View[6];
    private String[] keys = new String[6];


//    private CombinedChart combinedchart;
//    private BarChart barChart;
//    private XAxis xAxisBar, xAxisK;
//    private YAxis axisLeftBar, axisLeftK;
//    private YAxis axisRightBar, axisRightK;
//
//    private ArrayList<KLineBean> kLineDatas;
//    private BarDataSet barDataSet;
//    private float sum = 0;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            barChart.setAutoScaleMinMaxEnabled(true);
//            combinedchart.setAutoScaleMinMaxEnabled(true);
//            combinedchart.notifyDataSetChanged();
//            barChart.notifyDataSetChanged();
//
//            combinedchart.invalidate();
//            barChart.invalidate();
//        }
//    };

    private boolean tradeDataGet = false;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public TradeChatPresenter initPresenter() {
        return new TradeChatPresenter();
    }

    @Override
    public void baseInitialization() {
        presenter.getDataFromIntent(getIntent());
        EventBus.getDefault().register(this);
    }

    @Override
    public void cSConttectSeccuss() {
        if (presenter.select_ml == null) {
            return;
        }
        mDataService.subscribeKLineTrade(presenter.select_ml.getName(), "1");
        mDataService.subscribeDept(presenter.select_ml.getName());
    }

    @Override
    public void cSConttectFail() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_tradechat;
    }

    @Override
    public void viewInitialization() {
//        combinedchart = (CombinedChart) $(R.id.combinedchart);
//        barChart = (BarChart) $(R.id.barchart);

        leftImg_ly = $(R.id.leftImg_ly);
        leftImg_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toBuy = (TextView) $(R.id.toBuy);
        toSell = (TextView) $(R.id.toSell);

        title = (TextView) $(R.id.title);
        np_tv = (TextView) $(R.id.np_tv);
        highp_tv = (TextView) $(R.id.highp_tv);
        lowp_tv = (TextView) $(R.id.lowp_tv);
        tradeamount_tv = (TextView) $(R.id.tradeamount_tv);
        cny_tv = (TextView) $(R.id.cny_tv);
        cnyp_tv = (TextView) $(R.id.cnyp_tv);

        leftImg_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("select_ml",presenter.select_ml);
                setResult(501,intent);
                finish();
            }
        });
        toSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("select_ml",presenter.select_ml);
                setResult(502,intent);
                finish();
            }
        });

        initChatrl();
        initChart();
    }

    private void initChatrl() {

        min_rl = $(R.id.min_rl);
        min15_rl = $(R.id.min15_rl);
        hour_rl = $(R.id.hour_rl);
        hour4_rl = $(R.id.hour4_rl);
        day_rl = $(R.id.day_rl);
        other_rl = $(R.id.other_rl);

        min_tv = (TextView) $(R.id.min_tv);
        min15_tv = (TextView) $(R.id.min15_tv);
        hour_tv = (TextView) $(R.id.hour_tv);
        hour4_tv = (TextView) $(R.id.hour4_tv);
        day_tv = (TextView) $(R.id.day_tv);
        other_tv = (TextView) $(R.id.other_tv);

        min_vw = $(R.id.min_vw);
        min15_vw = $(R.id.min15_vw);
        hour_vw = $(R.id.hour_vw);
        hour4_vw = $(R.id.hour4_vw);
        day_vw = $(R.id.day_vw);
        other_vw = $(R.id.other_vw);


        rls = new View[]{min_rl, min15_rl, hour_rl, hour4_rl, day_rl, other_rl};

        tvs = new TextView[]{min_tv, min15_tv, hour_tv, hour4_tv, day_tv, other_tv};

        vws = new View[]{min_vw, min15_vw, hour_vw, hour4_vw, day_vw, other_vw};
        keys = new String[]{"1", "15", "60", "240", "1440", "1440"};
        for (int i = 0; i < rls.length; i++) {
            rls[i].setTag(R.id.rls_pos, i);
            rls[i].setTag(R.id.rls_key, keys[i]);
            rls[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rlsClick(view);
                }
            });
        }

    }

    private void rlsClick(View view) {
        int pos = (int) view.getTag(R.id.rls_pos);
        for (int i = 0; i < rls.length; i++) {
            if (i == pos) {
                tvs[i].setTextColor(0xff989898);
                vws[i].setVisibility(View.VISIBLE);
                mDataService.subscribeKLineTrade(presenter.select_ml.getName(), keys[i]);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tradeDataGet = false;
                    }
                }, 3000);
            } else {
                tvs[i].setTextColor(0xff627b99);
                vws[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void doBusiness() {
//        presenter.getOffLineData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeJsonEvent(TradeJsonEvent tradeJsonEvent) {
        if (tradeJsonEvent != null) {
            if (!tradeDataGet) {
                tradeDataGet = true;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        tradeDataGet = false;
//                    }
//                }, 3000);
                presenter.setmData(tradeJsonEvent.myDataParse);
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null) {
            presenter.doHandleMarket(marketListEvent.getMarketListEntities());
        }
    }


    @Override
    public void setMarket() {
        if (presenter.select_ml != null) {

            title.setText(presenter.select_ml.getName().replace("_", "/").toUpperCase());

            ToolUtils.setMarketUpDownTvColor(presenter.select_ml, np_tv);
            ToolUtils.setMarketUpDownTvColor(presenter.select_ml, cnyp_tv);

            np_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(presenter.select_ml.getNew_price())));
            highp_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(presenter.select_ml.getMax_price())));
            lowp_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(presenter.select_ml.getMin_price())));
            tradeamount_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(presenter.select_ml.getVolume_day())));

            cny_tv.setText("≈ " + ToolUtils.doublePoint(ToolUtils.String2Double(presenter.select_ml.getCny_price())));

            double c = ToolUtils.String2Double(presenter.select_ml.getChange());
            if (c >= 0) {
                cnyp_tv.setText("+ " + ToolUtils.doublePoint(c) + "%");
            } else {
                cnyp_tv.setText(ToolUtils.doublePoint(c) + "%");
            }
        }
    }

    private void initChart() {
//        barChart.setDrawBorders(true);
//        barChart.setBorderWidth(1);
//        barChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
//        barChart.setDescription("");
//        barChart.setDragEnabled(true);
//        barChart.setScaleYEnabled(false);
//        barChart.setScaleXEnabled(true);
//        barChart.setNoDataText("暂无数据");
//        (barChart.getRenderer()).setDownColor(ToolUtils.getUpDownColor(this, 0));
//        (barChart.getRenderer()).setUpColor(ToolUtils.getUpDownColor(this, 1));
//        Legend barChartLegend = barChart.getLegend();
//        barChartLegend.setEnabled(false);
//
//        //BarYAxisFormatter  barYAxisFormatter=new BarYAxisFormatter();
//        //bar x y轴
//        xAxisBar = barChart.getXAxis();
//        xAxisBar.setDrawLabels(true);
//        xAxisBar.setDrawGridLines(false);
//        xAxisBar.setDrawAxisLine(false);
//        xAxisBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
//        xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxisBar.setGridColor(getResources().getColor(R.color.minute_grayLine));
//
//        axisLeftBar = barChart.getAxisLeft();
//        axisLeftBar.setAxisMinValue(0);
//        axisLeftBar.setDrawGridLines(false);
//        axisLeftBar.setDrawAxisLine(false);
//        axisLeftBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
//        axisLeftBar.setDrawLabels(true);
//        axisLeftBar.setSpaceTop(0);
//        axisLeftBar.setShowOnlyMinMax(true);
//        axisRightBar = barChart.getAxisRight();
//        axisRightBar.setDrawLabels(false);
//        axisRightBar.setDrawGridLines(false);
//        axisRightBar.setDrawAxisLine(false);
//        /****************************************************************/
//        combinedchart.setDrawBorders(true);
//        combinedchart.setBorderWidth(1);
//        combinedchart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
//        combinedchart.setDescription("");
//        combinedchart.setDragEnabled(true);
//        combinedchart.setScaleYEnabled(false);
//        combinedchart.setScaleXEnabled(true);
//        combinedchart.setNoDataText("暂无数据");
//
//
//        Legend combinedchartLegend = combinedchart.getLegend();
//        combinedchartLegend.setEnabled(false);
//        //bar x y轴
//        xAxisK = combinedchart.getXAxis();
//        xAxisK.setDrawLabels(true);
//        xAxisK.setDrawGridLines(false);
//        xAxisK.setDrawAxisLine(false);
//        xAxisK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
//        xAxisK.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxisK.setGridColor(getResources().getColor(R.color.minute_grayLine));
//        xAxisK.setSpaceBetweenLabels(1);
//        axisLeftK = combinedchart.getAxisLeft();
//        axisLeftK.setDrawGridLines(true);
//        axisLeftK.setDrawAxisLine(false);
//        axisLeftK.setDrawLabels(true);
//        axisLeftK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
//        axisLeftK.setGridColor(getResources().getColor(R.color.minute_grayLine));
//        axisLeftK.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        axisRightK = combinedchart.getAxisRight();
//        axisRightK.setDrawLabels(false);
//        axisRightK.setDrawGridLines(true);
//        axisRightK.setDrawAxisLine(false);
//        axisRightK.setGridColor(getResources().getColor(R.color.minute_grayLine));
//        combinedchart.setDragDecelerationEnabled(true);
//        barChart.setDragDecelerationEnabled(true);
//        combinedchart.setDragDecelerationFrictionCoef(0.2f);
//        barChart.setDragDecelerationFrictionCoef(0.2f);
//
//
//        // 将K线控的滑动事件传递给交易量控件
//        combinedchart.setOnChartGestureListener(new CoupleChartGestureListener(combinedchart, new Chart[]{barChart}));
//        // 将交易量控件的滑动事件传递给K线控件
//        barChart.setOnChartGestureListener(new CoupleChartGestureListener(barChart, new Chart[]{combinedchart}));
//        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//                combinedchart.highlightValues(new Highlight[]{h});
//            }
//
//            @Override
//            public void onNothingSelected() {
//                combinedchart.highlightValue(null);
//            }
//        });
//        combinedchart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//
//                barChart.highlightValues(new Highlight[]{h});
//            }
//
//            @Override
//            public void onNothingSelected() {
//                barChart.highlightValue(null);
//            }
//        });


    }

    @Override
    public void setKLineData(MyDataParse mData) {
//        kLineDatas = mData.getKLineDatas();
//        if (kLineDatas.size() <= 0) {
//            return;
//        }
//        int size = kLineDatas.size();   //点的个数
//        // axisLeftBar.setAxisMaxValue(mData.getVolmax());
//        String unit = ChatDataUtils.getVolUnit(mData.getVolmax());
//        int u = 1;
//        if ("万手".equals(unit)) {
//            u = 4;
//        } else if ("亿手".equals(unit)) {
//            u = 8;
//        }
//        axisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
//        // axisRightBar.setAxisMaxValue(mData.getVolmax());
//        Log.e("@@@", mData.getVolmax() + "da");
//
//        ArrayList<String> xVals = new ArrayList<>();
//        ArrayList<BarEntry> barEntries = new ArrayList<>();
//        ArrayList<CandleEntry> candleEntries = new ArrayList<>();
//        ArrayList<Entry> line5Entries = new ArrayList<>();
//        ArrayList<Entry> line10Entries = new ArrayList<>();
//        ArrayList<Entry> line30Entries = new ArrayList<>();
//        for (int i = 0, j = 0; i < mData.getKLineDatas().size(); i++, j++) {
//            xVals.add((int)(Math.random()*100) + "");
//            barEntries.add(new BarEntry(mData.getKLineDatas().get(i).vol, i));
//            candleEntries.add(new CandleEntry(i, mData.getKLineDatas().get(i).high, mData.getKLineDatas().get(i).low,
//                    mData.getKLineDatas().get(i).open, mData.getKLineDatas().get(i).close));
//            if (i >= 4) {
//                sum = 0;
//                line5Entries.add(new Entry(getSum(i - 4, i) / 5, i));
//            }
//            if (i >= 9) {
//                sum = 0;
//                line10Entries.add(new Entry(getSum(i - 9, i) / 10, i));
//            }
//            if (i >= 29) {
//                sum = 0;
//                line30Entries.add(new Entry(getSum(i - 29, i) / 30, i));
//            }
//
//        }
//
//        barDataSet = new BarDataSet(barEntries, "成交量");
//        barDataSet.setBarSpacePercent(50); //bar空隙
//        barDataSet.setHighlightEnabled(true);
//        barDataSet.setHighLightAlpha(255);
//        barDataSet.setHighLightColor(Color.WHITE);
//        barDataSet.setDrawValues(false);
//        barDataSet.setColor(Color.RED);
//        List listColor = new ArrayList<>();
//        listColor.add(ToolUtils.getUpDownColor(this, 0));
//        listColor.add(ToolUtils.getUpDownColor(this, 1));
//        barDataSet.setColors(listColor);
//
//        BarData barData = new BarData(xVals, barDataSet);
//        barChart.setData(barData);
//        final ViewPortHandler viewPortHandlerBar = barChart.getViewPortHandler();
//        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(xVals.size()));
//        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
//        final float xscale = 3;
//        touchmatrix.postScale(xscale, 1f);
//
//
//        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "KLine");
//        candleDataSet.setDrawHorizontalHighlightIndicator(false);
//        candleDataSet.setHighlightEnabled(true);
//        candleDataSet.setHighLightColor(Color.WHITE);
//        candleDataSet.setValueTextSize(10f);
//        candleDataSet.setDrawValues(false);
//        candleDataSet.setColor(Color.RED);
//        candleDataSet.setShadowWidth(1f);
//        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//        candleDataSet.setDecreasingColor(ToolUtils.getUpDownColor(this, 0));//下跌矩形的颜色
//        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);//是否是实线
//        candleDataSet.setIncreasingColor(ToolUtils.getUpDownColor(this, 1));//增长矩形的颜色
//        candleDataSet.setShadowColorSameAsCandle(true);//矩形两端的线是否和矩形颜色相同
//
//
//        CandleData candleData = new CandleData(xVals, candleDataSet);
//
//
//        ArrayList<ILineDataSet> sets = new ArrayList<>();
//
//        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
//        if (size >= 30) {
//            sets.add(setMaLine(5, xVals, line5Entries));
//            sets.add(setMaLine(10, xVals, line10Entries));
//            sets.add(setMaLine(30, xVals, line30Entries));
//        } else if (size >= 10 && size < 30) {
//            sets.add(setMaLine(5, xVals, line5Entries));
//            sets.add(setMaLine(10, xVals, line10Entries));
//        } else if (size >= 5 && size < 10) {
//            sets.add(setMaLine(5, xVals, line5Entries));
//        }
//
//
//        CombinedData combinedData = new CombinedData(xVals);
//        LineData lineData = new LineData(xVals, sets);
//        combinedData.setData(candleData);
//        combinedData.setData(lineData);
//        combinedchart.setData(combinedData);
//        combinedchart.moveViewToX(mData.getKLineDatas().size() - 1);
//        final ViewPortHandler viewPortHandlerCombin = combinedchart.getViewPortHandler();
//        viewPortHandlerCombin.setMaximumScaleX(culcMaxscale(xVals.size()));
//        Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();
//        final float xscaleCombin = 3;
//        matrixCombin.postScale(xscaleCombin, 1f);
//
//        combinedchart.moveViewToX(mData.getKLineDatas().size() - 1);
//        barChart.moveViewToX(mData.getKLineDatas().size() - 1);
//        setOffset();
///****************************************************************************************
// 此处解决方法来源于CombinedChartDemo，k线图y轴显示问题，图表滑动后才能对齐的bug，希望有人给出解决方法
// (注：此bug现已修复，感谢和chenguang79一起研究)
// ****************************************************************************************/
//
//        handler.sendEmptyMessageDelayed(0, 300);
    }


    /*设置量表对齐*/
    private void setOffset() {
//        float lineLeft = combinedchart.getViewPortHandler().offsetLeft();
//        float barLeft = barChart.getViewPortHandler().offsetLeft();
//        float lineRight = combinedchart.getViewPortHandler().offsetRight();
//        float barRight = barChart.getViewPortHandler().offsetRight();
//        float barBottom = barChart.getViewPortHandler().offsetBottom();
//        float offsetLeft, offsetRight;
//        float transLeft = 0, transRight = 0;
// /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
//        if (barLeft < lineLeft) {
//           /* offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
//            barChart.setExtraLeftOffset(offsetLeft);*/
//            transLeft = lineLeft;
//        } else {
//            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
//            combinedchart.setExtraLeftOffset(offsetLeft);
//            transLeft = barLeft;
//        }
//  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
//        if (barRight < lineRight) {
//          /*  offsetRight = Utils.convertPixelsToDp(lineRight);
//            barChart.setExtraRightOffset(offsetRight);*/
//            transRight = lineRight;
//        } else {
//            offsetRight = Utils.convertPixelsToDp(barRight);
//            combinedchart.setExtraRightOffset(offsetRight);
//            transRight = barRight;
//        }
//        barChart.setViewPortOffsets(transLeft, 15, transRight, barBottom);
    }

//    @NonNull
//    private LineDataSet setMaLine(int ma, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
//        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + ma);
//        if (ma == 5) {
//            lineDataSetMa.setHighlightEnabled(true);
//            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
//            lineDataSetMa.setHighLightColor(Color.WHITE);
//        } else {/*此处必须得写*/
//            lineDataSetMa.setHighlightEnabled(false);
//        }
//        lineDataSetMa.setDrawValues(false);
//        if (ma == 5) {
//            lineDataSetMa.setColor(Color.GREEN);
//        } else if (ma == 10) {
//            lineDataSetMa.setColor(Color.GRAY);
//        } else {
//            lineDataSetMa.setColor(Color.YELLOW);
//        }
//        lineDataSetMa.setLineWidth(1f);
//        lineDataSetMa.setDrawCircles(false);
//        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);
//        return lineDataSetMa;
//    }
//
//    private float getSum(Integer a, Integer b) {
//
//        for (int i = a; i <= b; i++) {
//            sum += presenter.mData.getKLineDatas().get(i).close;
//        }
//        return sum;
//    }
//
//    private float culcMaxscale(float count) {
//        float max = 1;
//        max = count / 127 * 5;
//        return max;
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
