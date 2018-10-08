package com.lizikj.api.utils;

import com.lizikj.api.enums.ReportTargetEnum;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.reporting.*;
import com.lizikj.api.vo.reporting.param.ReportCommonParam;
import com.lizikj.common.util.DateUtils;
import com.lizikj.reporting.dto.ShopOrderReportDTO;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;

/**
 * 统计工具类
 * Created by liangxiaolin on 2017/8/10.
 */
public class ReportUtil {
    private final static Logger log = LoggerFactory.getLogger(ReportUtil.class);
    private final static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static PageVO buildEmptyPage(){
        PageVO pageVO = new PageVO<>(0,0,0,0);
        pageVO.setList(new ArrayList());
        return pageVO;
    }
    /**
     * 查询对象路由
     * @param merchantId
     * @param shopId
     * @param reportCommonParam
     * @return
     */
    public static ReportCommonParam routeReportTarget(Long merchantId,Long shopId,ReportCommonParam reportCommonParam){
        ReportCommonParam result = new ReportCommonParam();
        result.setShopId(shopId);
        result.setMerchantId(merchantId);
        if (reportCommonParam != null) {
            if(reportCommonParam.getReportType() != null){
                DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
                result.setEndDate(dateParam.getEndDate());
                result.setStartDate(dateParam.getStartDate());
            }else{
                if(reportCommonParam.getStartDate() != null){
                    result.setStartDate(DateUtils.getStartOfDay(reportCommonParam.getStartDate()));
                }
                if(reportCommonParam.getEndDate() != null){
                    result.setEndDate(DateUtils.getEndOfDay(reportCommonParam.getEndDate()));
                }
            }
            if(reportCommonParam.getStartTime() != null){
                result.setStartDate(reportCommonParam.getStartTime());
            }
            if(reportCommonParam.getEndTime() != null){
                result.setEndDate(reportCommonParam.getEndTime());
            }
            if (reportCommonParam.getTarget() != null) {
                //查询所有交易
                if(reportCommonParam.getTarget() == ReportTargetEnum.PLATFORM.getCode()){
                    result.setShopId(null);
                    result.setMerchantId(null);
                }
                //查询商户交易
                if(reportCommonParam.getTarget() == ReportTargetEnum.MERCHANT.getCode()){
                    result.setShopId(null);
                }
                //查询店铺交易
                if(reportCommonParam.getTarget() == ReportTargetEnum.SHOP.getCode()){
                    result.setMerchantId(null);
                }
            }
            if(reportCommonParam.getMerchantId() != null){
                result.setMerchantId(reportCommonParam.getMerchantId());
            }
            if(reportCommonParam.getShopId() != null){
                result.setShopId(reportCommonParam.getShopId());
            }
            if(reportCommonParam.getCurrencyTypeReportEnum() != null){
                result.setCurrencyTypeReportEnum(reportCommonParam.getCurrencyTypeReportEnum());
            }
        }
        return result;
    }

    /**
     * 获取起始和结束时间
     * @param dateType 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）
     * @return
     */
    @Deprecated
    public static DateParam getDateParam(int dateType){
        Date today = DateUtils.getCurrentDate();
        Date startToday = DateUtils.getStartOfDay(today);
        Date endToday = DateUtils.getEndOfDay(today);
        DateParam dateParam = new DateParam();
        switch (dateType){
            case 8400:
                dateParam.setEndDate(endToday);
                dateParam.setStartDate(startToday);
                break;
            case 8500:
                dateParam.setEndDate(DateUtils.addDays(endToday,-1));
                dateParam.setStartDate(DateUtils.addDays(startToday,-8));
                break;
            case 8600:
                dateParam.setEndDate(DateUtils.addDays(endToday,-1));
                dateParam.setStartDate(DateUtils.addDays(startToday,-31));
                break;
        }
        return dateParam;
    }
    /**
     * 获取起始和结束时间
     *
     * @param reportingTypeEnum 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）
     * @param dateStr
     * @return
     */
    public static DateParam getDateParam(ReportingTypeEnum reportingTypeEnum,String dateStr){
        Date today = DateUtils.getCurrentDate();
        Date startToday = DateUtils.getStartOfDay(today);
        Date endToday = DateUtils.getEndOfDay(today);
        DateParam dateParam = new DateParam();
        if(dateStr == null){
            dateStr = DateUtils.format(today,DateUtils.FULL_SMALL_PATTERN);
        }
        switch (reportingTypeEnum){
            case YESTERDAY:
                dateParam.setStartDate(DateUtils.addDays(startToday,-1));
                dateParam.setEndDate(DateUtils.addDays(endToday,-1));
                break;
            case CURRENT_DAY:
                dateParam.setEndDate(endToday);
                dateParam.setStartDate(startToday);
                break;
            case NEARLY_SEVEN_DAY:
                dateParam.setEndDate(DateUtils.addDays(endToday,-1));
                dateParam.setStartDate(DateUtils.addDays(startToday,-7));
                break;
            case NEARLY_THIRTY_DAY:
                dateParam.setEndDate(DateUtils.addDays(endToday,-1));
                dateParam.setStartDate(DateUtils.addDays(startToday,-30));
                break;
            case DAY:
                Date temp = DateUtils.parse(dateStr,DateUtils.FULL_SMALL_PATTERN);
                dateParam.setStartDate(DateUtils.getStartOfDay(temp));
                dateParam.setEndDate(DateUtils.getEndOfDay(temp));
                break;
            case MONTH:
                String firstDateStr = dateStr.substring(0,7) + "-01";
                Date monthFirsDate = DateUtils.parse(firstDateStr,DateUtils.FULL_SMALL_PATTERN);
                dateParam.setStartDate(DateUtils.getStartOfDay(monthFirsDate));
                Date monthEndDate = DateUtils.addDays(DateUtils.addMonths(monthFirsDate,1),-1); //月末
                dateParam.setEndDate(DateUtils.getEndOfDay(monthEndDate));
                break;
            case YEAR:
                String firstYearDateStr = dateStr.substring(0,4) + "-01-01";
                Date yearFirsDate = DateUtils.parse(firstYearDateStr,DateUtils.FULL_SMALL_PATTERN);
                dateParam.setStartDate(DateUtils.getStartOfDay(yearFirsDate));
                Date yearEndDate = DateUtils.addDays(DateUtils.addMonths(yearFirsDate,12),-1); //年末
                dateParam.setEndDate(DateUtils.getEndOfDay(yearEndDate));
                break;
            case ALL:
                //查询全部 startDate = null endDate = now
                dateParam.setStartDate(null);
                dateParam.setEndDate(new Date());
                break;
        }
        return dateParam;
    }
    /**
     * 转换金额格式
     * @param value
     * @return
     */
    public static String toYuan(final Object value){
        Double temp = null;
        if(value instanceof Integer || value instanceof Long || value instanceof Double){
            temp = Double.valueOf(String.valueOf(value));
        }
        if(temp != null){
            return decimalFormat.format(temp/100);
        }
        return "0";
    }
    public static <T> PieChartVO buildPieChart(List<T> sources, String chartOwner, String itemMethodName, String itemValueMethodName,ValueTypeEnum valueTypeEnum){
        PieChartVO pieChartVO = new PieChartVO();
        pieChartVO.setOwner(chartOwner);
        List<PieItemVO> pieItemVOS = new ArrayList<>();
        List<Object> subjects = new ArrayList<>();
        for(T source:sources){
            if(source == null){
                continue;
            }
            Class cls = source.getClass();
            try{
                Method itemMethod = cls.getMethod(itemMethodName);
                Object itemObj = itemMethod.invoke(source);
                if(itemObj == null)
                    continue;
                //subjects.add(itemObj);
                PieItemVO pieItemVO = new PieItemVO();
                Method itemValueMethod = cls.getMethod(itemValueMethodName);
                Object itemValue = itemValueMethod.invoke(source);
                if(itemValue == null)
                    continue;
                subjects.add(itemObj);
                pieItemVO.setName(itemObj);
                pieItemVO.setValue(itemValue);
                if(valueTypeEnum != null && valueTypeEnum == ValueTypeEnum.AMOUNT){
                    pieItemVO.setValue(toYuan(itemValue));
                }
                pieItemVOS.add(pieItemVO);
            }catch (Exception e){
                e.printStackTrace();
                if(log.isInfoEnabled()){
                    log.info("饼图构建失败 {}",e);
                }
            }
        }
        pieChartVO.setSubjects(subjects);
        pieChartVO.setData(pieItemVOS);
        return pieChartVO;
    }
    /**
     * 构建单纵坐标趋势图
     * @param sources
     * @param chartOwner
     * @param xAxisMethodName
     * @param yAxisMethodName
     * @param <T>
     * @return
     */
    public static <T> TrendsChartVO buildTrendsChart(List<T> sources, String chartOwner, String xAxisMethodName, String yAxisMethodName,ValueTypeEnum valueTypeEnum){
        List<String> xAxis = new ArrayList<>();
        List<String> yAxis = new ArrayList<>();
        for(T source:sources){
            if(source == null){
                continue;
            }
            Class cls = source.getClass();
            try{
                Method xValueMethod = cls.getMethod(xAxisMethodName);
                Object xValueObj = xValueMethod.invoke(source);
                Method yValueMethod = cls.getMethod(yAxisMethodName);
                Object yValueObj = yValueMethod.invoke(source);
                if(xValueObj == null || yValueObj == null)
                    continue;
                if(xValueObj instanceof Date)
                    xValueObj = DateUtils.format((Date) xValueObj,DateUtils.FULL_SMALL_PATTERN);
                xAxis.add(String.valueOf(xValueObj));
                String yValue = String.valueOf(yValueObj);
                //如果是金额 ，除以100
                if(valueTypeEnum != null && valueTypeEnum == ValueTypeEnum.AMOUNT){
                    yValue = toYuan(yValueObj);
                }
                yAxis.add(String.valueOf(yValue));
            }catch (Exception e){
                e.printStackTrace();
                if(log.isInfoEnabled()){
                    log.info("趋势图构建失败 {}",e);
                }
            }
        }
        TrendsChartVO trendsChartVO = new TrendsChartVO();
        trendsChartVO.setHorizontalAxis(xAxis);
        trendsChartVO.setVerticalAxis(yAxis);
        trendsChartVO.setOwner(chartOwner);
        return trendsChartVO;
    }
    /**
     * 构建多纵坐标趋势图
     * @param sources
     * @param chartMethodName
     * @param xAxisMethodName
     * @param yAxisMethodName
     * @param <T>
     * @return
     */
    public static <T> TrendsMultiChartVO buildMultiChart(List<T> sources, String chartMethodName, String xAxisMethodName, String yAxisMethodName,ValueTypeEnum valueTypeEnum){
        TrendsMultiChartVO multiChartVO = new TrendsMultiChartVO();
        Map<String,SingleAxisVO> yAxisMap = new HashMap<>();
        List<String> xAxis = new ArrayList<>();
        for(T source : sources){
            if(source == null){
                continue;
            }
            Class cls = source.getClass();
            try {
                Method chartOwnerMethod = cls.getMethod(chartMethodName);
                Object chartNameObj = chartOwnerMethod.invoke(source);
//                if(chartNameObj == null)
//                    continue;
                String chartName = String.valueOf(chartNameObj);
                SingleAxisVO singleAxisVO = yAxisMap.get(chartName);
                if(singleAxisVO == null){
                    singleAxisVO = new SingleAxisVO();
                    singleAxisVO.setOwner(chartName);
                    List<String> yAxisVals = new ArrayList<>();
                    singleAxisVO.setAxisValues(yAxisVals);
                    yAxisMap.put(chartName,singleAxisVO);
                }
                Method xAxisMethod = cls.getMethod(xAxisMethodName);
                Object xAxisPointObj = xAxisMethod.invoke(source);
                if(xAxisPointObj == null)
                    continue;
                String xAxisPointValue = null;
                if(xAxisPointObj instanceof Date){
                    xAxisPointValue = DateUtils.format((Date)xAxisPointObj,DateUtils.FULL_SMALL_PATTERN);
                }else{
                    xAxisPointValue = String.valueOf(xAxisPointObj);
                }
                if(xAxisPointValue == null)
                    continue;
                if(!xAxis.contains(xAxisPointValue))
                    xAxis.add(xAxisPointValue);
                List<String> axisValues = singleAxisVO.getAxisValues();
                Method yAxisMethod = cls.getMethod(yAxisMethodName);
                Object yAxisPointObj = yAxisMethod.invoke(source);
                if(yAxisPointObj == null)
                    continue;
                //除以100 金额
                String yAxisPoint = String.valueOf(yAxisPointObj);
                if(valueTypeEnum != null && valueTypeEnum == ValueTypeEnum.AMOUNT){
                    yAxisPoint = toYuan(yAxisPointObj);
                }
                axisValues.add(yAxisPoint);
            } catch (Exception e) {
                e.printStackTrace();
                if(log.isInfoEnabled()){
                    log.info("多纵趋势图 构建失败 {}",e);
                }
            }
        }
        multiChartVO.setVerticalAxis(new ArrayList<SingleAxisVO>(yAxisMap.values()));
        multiChartVO.setHorizontalAxisValues(xAxis);
        return multiChartVO;
    }
    /**
     * 将数据装进趋势图（反射会影响效率）
     * @param sources
     * @param chartMethodName
     * @param xAxisMethodName
     * @param yAxisMethodKV
     * @param <T>
     * @return
     */
    public static <T> List<ChartVO> buildChartList(List<T> sources, String chartMethodName, String xAxisMethodName,String yAxisMethodName,Map<String,String> yAxisMethodKV,ValueTypeEnum valueTypeEnum){
        Map<String,ChartVO> chartMap = new HashMap<>();
        for(T source : sources){
            if(source == null){
                continue;
            }
            Class cls = source.getClass();
            try {
                Method chartOwnerMethod = cls.getMethod(chartMethodName);
                Object chartNameObj = chartOwnerMethod.invoke(source);
//                if(chartNameObj == null)
//                    continue;
                String chartName = String.valueOf(chartNameObj);
                ChartVO chartVO = chartMap.get(chartName);
                if(chartVO == null){
                    chartVO = new ChartVO();
                    chartVO.setOwner(chartName);
                    List<ChartAxisVO>  yNewAxis = new ArrayList<>();
                    List<String> xNewAxis = new ArrayList<>();
                    chartVO.setVerticalAxis(yNewAxis);
                    chartVO.setHorizontalAxis(xNewAxis);
                    chartMap.put(chartName,chartVO);
                }
                List<String> xAxis = chartVO.getHorizontalAxis();
                Method xAxisMethod = cls.getMethod(xAxisMethodName);
                Object xAxisPointObj = xAxisMethod.invoke(source);
                if(xAxisPointObj == null)
                    continue;
                String xAxisPointValue = null;
                if(xAxisPointObj instanceof Date){
                    xAxisPointValue = DateUtils.format((Date)xAxisPointObj,DateUtils.FULL_SMALL_PATTERN);
                }else{
                    xAxisPointValue = String.valueOf(xAxisPointObj);
                }
                if(xAxisPointValue == null || xAxis.contains(xAxisPointValue))
                    continue;
                xAxis.add(xAxisPointValue);
                List<ChartAxisVO> chartAxisVOS = chartVO.getVerticalAxis();
                ChartAxisVO chartAxisVO = new ChartAxisVO();
                chartAxisVO.setAxisOwner(xAxisPointValue);
                List<AxisItemVO> axisItemVOS = new ArrayList<>();
                Method yAxisMethod = cls.getMethod(yAxisMethodName);
                Object yAxisPointObj = yAxisMethod.invoke(source);
                if(yAxisPointObj == null)
                    continue;
                String yAxisPoint = String.valueOf(yAxisPointObj);
                if(valueTypeEnum != null && valueTypeEnum == ValueTypeEnum.AMOUNT){
                    yAxisPoint = toYuan(yAxisPointObj);
                }
                chartAxisVO.setAxisValue(yAxisPoint);
                //yAxisMethodKV 格式  key : methodName
                for(Map.Entry<String,String> entry:yAxisMethodKV.entrySet()){
                    Method valueMethod = cls.getMethod(entry.getValue());
                    Object valueObj = valueMethod.invoke(source);
                    if(valueObj == null){
                        continue;
                    }
                    axisItemVOS.add(new AxisItemVO(entry.getKey(),String.valueOf(valueObj)));
                }
                chartAxisVO.setAxisItems(axisItemVOS);
                chartAxisVOS.add(chartAxisVO);
            } catch (Exception e) {
                e.printStackTrace();
                if(log.isInfoEnabled()){
                    log.info("趋势图构建失败 {}",e);
                }
            }
        }
        return new ArrayList<>(chartMap.values());
    }
    public enum ValueTypeEnum{
        AMOUNT(1,"金额"),
        NUMBER(2,"笔数"),
        RATE(3,"率");
        private int type;
        private String description;
        ValueTypeEnum(int type, String description) {
            this.type = type;
            this.description = description;
        }
    }

    /**
     * 抽取实体字段集合 不包括NULL
     * @param sources
     * @param keyFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> extractNoNullKeys(List<T> sources,Function<T,R> keyFunc){
        List<R> keys = new ArrayList<>();
        if(sources != null){
            sources.forEach(t -> {
                if (t != null) {
                    R apply = keyFunc.apply(t);
                    if (apply != null) {
                        keys.add(keyFunc.apply(t));
                    }
                }
            });
        }

        return keys;
    }

    /**
     * 抽取实体字段集合
     * @param sources
     * @param keyFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> extractKeys(List<T> sources,Function<T,R> keyFunc){
        List<R> keys = new ArrayList<>();
        if(sources != null){
            sources.forEach(t -> {
                if(t != null){
                    keys.add(keyFunc.apply(t));
                }
            });
        }

        return keys;
    }

    /**
     * 集合分组
     * @param sources
     * @param keyFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Map<R,List<T>> groupByKey(List<T> sources,Function<T,R> keyFunc){
        Map<R,List<T>> result = new HashMap<>();
        if(sources != null){
            sources.forEach(t -> {
                if (t != null) {
                    R key = keyFunc.apply(t);
                    List<T> ts = result.get(key);
                    if(ts == null){
                        ts = new ArrayList<>();
                        result.put(key,ts);
                    }

                    ts.add(t);
                }
            });
        }

        return result;
    }

    /**
     * list to map
     * @param sources
     * @param keyFunc
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Map<R,T> listToMap2(List<T> sources,Function<T,R> keyFunc){
        Map<R,T> result = new HashMap<>();
        if (sources != null) {
            sources.forEach(t -> {
                if (t != null) {
                    result.put(keyFunc.apply(t),t);
                }
            });
        }

        return result;
    }

    /**
     * list to map
     * @param sources
     * @param keyFunc
     * @param <T>
     * @return
     */
    public static <T> Map<Object,T> listToMap(List<T> sources, Function<T,Object> keyFunc){
        Map<Object,T> result = new HashMap<>();
        if (sources != null) {
            sources.forEach(t -> {
                result.put(keyFunc.apply(t),t);
            });
        }

        return result;
    }
}
