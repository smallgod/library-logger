/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.sglogger;

import com.library.utilities.DateUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.openide.util.MapFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author smallgod
 */
public final class SGLogger {

    private Logger LOGGER;
    

    public SGLogger() throws FileNotFoundException, Exception {
        //this.configureLog4J(log4jFile);
    }

    public <T> void addLogger(final Class<T> loggerClass) {
        this.LOGGER = LoggerFactory.getLogger(loggerClass);
    }

    public void debug(String debugLogText) {

        String log = formatLog(debugLogText);

        if (this.LOGGER != null) {
            this.LOGGER.debug(log);
        } else {
            System.out.println(log);
        }
    }

    public void info(String infoLogText) {

        String log = formatLog(infoLogText);

        if (LOGGER != null) {
            LOGGER.info(log);
        } else {
            System.out.println(log);
        }
    }

    public void error(String errorLogText) {

        String log = formatLog(errorLogText);

        if (LOGGER != null) {
            LOGGER.error(log);
        } else {
            System.err.println(log);
        }
    }

    public void warn(String warnLogText) {

        String log = formatLog(warnLogText);

        if (LOGGER != null) {
            LOGGER.warn(log);
        } else {
            System.out.println(log);
        }
    }

    public void fatal(String fatalLogText) {

        String log = formatLog(fatalLogText);

        if (LOGGER != null) {
            LOGGER.error(log);
            System.exit(-1);
        } else {
            System.err.println(log);
            System.exit(-1);
        }

    }

    /**
     * Format Log message
     *
     * @param logText
     * @return
     */
    private String formatLog(String logText) {

        String log = "[{dateTime}] [{logger}] - {logText}";

        Map<String, Object> map = new HashMap<>();

        map.put("dateTime", DateUtils.getDefaultDateTimeNow());
        map.put("logger", this.LOGGER != null ? this.LOGGER.getClass().getSimpleName() : "LOGGER is NULL");
        map.put("logText", logText);

        //return (MapFormat.format(log, map));
        return logText;

    }

    /**
     * Configure the log4J2.xml file
     *
     * @param log4jFile
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     */
    public static void configureLog4J(String log4jFile) throws FileNotFoundException, Exception {

        System.out.println("configureLog4J Called, yay!");
        
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File(log4jFile);
        if (file.exists()) {
            context.setConfigLocation(file.toURI());
        } else {
            throw new FileNotFoundException("Failed to find file @: " + log4jFile);
        }

        //Properties props = new Properties();
        //props.put("logsFolder", paramsToPass);
        //DOMConfigurator.setParameter(elem, propSetter, props);
        //DOMConfigurator.configure(log4jFile); //XML configurator
        //MapLookup.setMainArguments(paramsToPass);
        //PropertyConfigurator.configure(log4jPropsFileLoc);//Property file configurator
    }
}
