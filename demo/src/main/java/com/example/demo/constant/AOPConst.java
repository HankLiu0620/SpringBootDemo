package com.example.demo.constant;

public class AOPConst {

    private static final String POINTCUT_DEFINITION_PATH =
            "com.example.demo.aop.pointcut.PointcutDefinition.";
    public static final String POINTCUT_SERVICELAYER =
            POINTCUT_DEFINITION_PATH + "serviceLayer()";
    public static final String POINTCUT_WEBLAYER =
            POINTCUT_DEFINITION_PATH + "webLayer()";
    public static final String POINTCUT_CONTROLLERLAYER =
            POINTCUT_DEFINITION_PATH + "controllerLayer()";

    public static final int LOGGER_ORDER = 1;
    public static final int VALIDATE_REQUEST_ORDER = 2;
    public static final int Controller_ORDER = 3;

}
