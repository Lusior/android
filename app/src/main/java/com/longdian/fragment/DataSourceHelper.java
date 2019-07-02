package com.longdian.fragment;

import com.longdian.fragment.base.model.StandDataExtra;

/**
 * Created by fanwei on 2019/2/28.
 */

public class DataSourceHelper {

    private final static String[] STAND_EXTRA_1 = {"桥北站低区", "2.8", "12x3", "3", "32", "45", "2", "17", "3", "2"};
    private final static String[] STAND_EXTRA_2 = {"桥北站高区", "2.5", "105x2", "2", "27.33", "18.5", "2", "10", "3", "2"};
    private final static String[] STAND_EXTRA_3 = {"工程小区", "2.9", "125x2", "2", "24.98", "22", "2", "10", "1.5", "2"};
    private final static String[] STAND_EXTRA_4 = {"春明小区", "2.35", "100x3", "3", "21", "37", "2", "17", "3", "2"};
    private final static String[] STAND_EXTRA_5 = {"电台低区", "2.9", "125x2", "2", "24.98", "22", "2", "10", "1.5", "2"};
    private final static String[] STAND_EXTRA_6 = {"电台高区", "0.3", "30.25x1", "1", "24", "3", "2", "2", "1.5", "2"};
    private final static String[] STAND_EXTRA_7 = {"电台中区", "0.3", "30.25x1", "1", "20", "5.5", "2", "2", "1.1", "2"};
    private final static String[] STAND_EXTRA_8 = {"教化小区", "2.5", "105x2", "2", "27.3", "18.5", "2", "10", "1.5", "2"};
    private final static String[] STAND_EXTRA_9 = {"曲线小区", "4.2", "170x3", "3", "27.4", "55", "2", "21", "4", "2"};
    private final static String[] STAND_EXTRA_10 = {"工务机械段", "0.7", "30x2", "2", "20.7", "5.5", "2", "3", "0.55", "2"};
    private final static String[] STAND_EXTRA_11 = {"教授家园北区", "-", "55.64x2", "2", "29", "30", "2", "10", "1.1", "2"};
    private final static String[] STAND_EXTRA_12 = {"教授家园南区", "-", "58x2", "2", "29", "30", "2", "10", "1.1", "2"};
    private final static String[] STAND_EXTRA_13 = {"西桥小区", "3.25", "140x2", "2", "33.07", "30", "2", "10", "1.5", "2"};
    private final static String[] STAND_EXTRA_14 = {"西桥低区", "", "", "", "", "", "", "", "", ""};
    private final static String[] STAND_EXTRA_15 = {"西桥高区", "", "", "", "", "", "", "", "", ""};

    private final static String[][] STAND_EXTRAS = {STAND_EXTRA_1, STAND_EXTRA_2, STAND_EXTRA_3,
            STAND_EXTRA_4, STAND_EXTRA_5, STAND_EXTRA_6, STAND_EXTRA_7, STAND_EXTRA_8, STAND_EXTRA_9,
            STAND_EXTRA_10, STAND_EXTRA_11, STAND_EXTRA_12, STAND_EXTRA_13, STAND_EXTRA_14, STAND_EXTRA_15};


    public static StandDataExtra getStandDataExtra(String standName) {
        for (String[] extras : STAND_EXTRAS) {
            if (extras[0].equals(standName)) {
                return buildStandDataExtra(extras);
            }
        }
        return null;
    }

    private static StandDataExtra buildStandDataExtra(String[] extra) {
        return new StandDataExtra()
                .setHeatTransfer(extra[1])
                .setHeatTransferArea(extra[2])
                .setBoardsOfNumber(extra[3])
                .setCirculatoryPumpLift(extra[4])
                .setCirculatoryPumpPower(extra[5])
                .setCirculatoryPumpNumber(extra[6])
                .setFillingPumpFlow(extra[7])
                .setPumpPower(extra[8])
                .setQuantityOfPump(extra[9]);

    }
}
