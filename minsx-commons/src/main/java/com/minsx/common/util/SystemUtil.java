package com.minsx.common.util;


import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SystemUtil {

	/**
	 * 取CPU基本信息
	 * @return CPU基本信息
	 */
	public final static CpuInfo getCpuInfo() {
        CpuInfo info=null;
		try {
			Sigar sigar = new Sigar();
			CpuInfo infos[]=sigar.getCpuInfoList();
			info=infos[0];
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return info;
    }

	/**
	 * 取CPU使用率
	 * @return CPU使用率
	 */
	public final static double getCpuUsingRate() {
	    double rate = 0;
		CpuPerc cpuPerc = null;
		try {
			Sigar sigar = new Sigar();
			cpuPerc=sigar.getCpuPercList()[0];
			rate=(1-cpuPerc.getIdle());
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return rate;
	}

	/**
	 * 取内存使用率
	 * @return 内存使用率
	 */
	public final static double getMemoryUsingRate() {
	    double rate = 0;
        Mem mem= null;
		try {
			Sigar sigar = new Sigar();
			mem = sigar.getMem();
			rate=mem.getUsedPercent();
		} catch (SigarException e) {
			e.printStackTrace();
		}
        return rate/100;
	}

	/**
	 * 取磁盘使用率
	 * @return 磁盘使用率
	 */
	public final static FileSystem[] getDiskUsingRate() {
		FileSystem[] fsList= null;
		try {
			 Sigar sigar = new Sigar();
		     fsList= sigar.getFileSystemList();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return fsList;
	}


}
