package com.minsx.common.constant;

import java.time.LocalDateTime;
import java.util.Date;

public class Constant {

	/**
	 * system path
	 */
	public static final String SYSTEM_PATH = System.getProperty("user.dir").replace("\\", "/");
	/**
	 * system config path
	 */
	public static final String SYSTEM_CONFIG_PATH = SYSTEM_PATH + "/config/";

	/**
	 * system name
	 */
	public static final String SYSTEM_NAME = "米斯云平台";

	/**
	 * system version
	 */
	public static final String SYSTEM_VERSION = "2.0.0";

	/**
	 * system version
	 */
	public static final Date SYSTEM_START_TIME = new Date();



}
