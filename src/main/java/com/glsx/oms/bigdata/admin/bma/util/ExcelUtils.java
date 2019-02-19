package com.glsx.oms.bigdata.admin.bma.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.oreframework.commons.office.poi.zslin.utils.ExcelHeader;
import org.oreframework.commons.office.poi.zslin.utils.ExcelResources;
import org.oreframework.commons.office.poi.zslin.utils.ExcelTemplate;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * excel工具类
 * 
 * @author 陈展鹏
 * 
 * 修改：张祥涛
 * 
 */
public class ExcelUtils {

	private static ExcelUtils eu = new ExcelUtils();

	private ExcelUtils() {
	}

	public static ExcelUtils getInstance() {
		return eu;
	}

	private ExcelTemplate handlerObj2Excel(FileInputStream inputStream, List objs, Class clz, boolean isClasspath) {
		ExcelTemplate et = ExcelTemplate.getInstance();

		try {
			if (isClasspath) {
				et.readTemplateByClasspath(inputStream);
			} else {
				et.readTemplateByPath(inputStream);
			}

			List<ExcelHeader> headers = this.getHeaderList(clz);
			Collections.sort(headers);
			et.createNewRow();
			Iterator var8 = headers.iterator();

			while(var8.hasNext()) {
				ExcelHeader eh = (ExcelHeader)var8.next();
				et.createCell(eh.getTitle());
			}

			var8 = objs.iterator();

			while(var8.hasNext()) {
				Object obj = var8.next();
				et.createNewRow();
				Iterator var10 = headers.iterator();

				while(var10.hasNext()) {
					ExcelHeader eh = (ExcelHeader)var10.next();
					et.createCell(BeanUtils.getProperty(obj, this.getMethodName(eh)));
				}
			}
		} catch (IllegalAccessException var11) {
			var11.printStackTrace();
		} catch (InvocationTargetException var12) {
			var12.printStackTrace();
		} catch (NoSuchMethodException var13) {
			var13.printStackTrace();
		}

		return et;
	}

	private String getMethodName(ExcelHeader eh) {
		String mn = eh.getMethodName().substring(3);
		mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
		return mn;
	}

	public void exportObj2ExcelByTemplate(Map<String, String> datas, FileInputStream inputStream, OutputStream os, List objs, Class clz, boolean isClasspath) {
		try {
			ExcelTemplate et = this.handlerObj2Excel(inputStream, objs, clz, isClasspath);
			et.replaceFinalData(datas);
			et.wirteToStream(os);
			os.flush();
			os.close();
		} catch (IOException var8) {
			var8.printStackTrace();
		}

	}

	public void exportObj2ExcelByTemplate(Map<String, String> datas, FileInputStream inputStream, String outPath, List objs, Class clz, boolean isClasspath) {
		ExcelTemplate et = this.handlerObj2Excel(inputStream, objs, clz, isClasspath);
		et.replaceFinalData(datas);
		et.writeToFile(outPath);
	}

	public void exportObj2ExcelByTemplate(Properties prop, FileInputStream inputStream, OutputStream os, List objs, Class clz, boolean isClasspath) {
		ExcelTemplate et = this.handlerObj2Excel(inputStream, objs, clz, isClasspath);
		et.replaceFinalData(prop);
		et.wirteToStream(os);
	}

	public void exportObj2ExcelByTemplate(Properties prop, FileInputStream inputStream, String outPath, List objs, Class clz, boolean isClasspath) {
		ExcelTemplate et = this.handlerObj2Excel(inputStream, objs, clz, isClasspath);
		et.replaceFinalData(prop);
		et.writeToFile(outPath);
	}

	private Workbook handleObj2Excel(List objs, Class clz) {
		XSSFWorkbook wb = new XSSFWorkbook();

		try {
			Sheet sheet = wb.createSheet();
			Row r = sheet.createRow(0);
			List<ExcelHeader> headers = this.getHeaderList(clz);
			Collections.sort(headers);

			for(int i = 0; i < headers.size(); ++i) {
				r.createCell(i).setCellValue(((ExcelHeader)headers.get(i)).getTitle());
			}

			Object obj = null;



			for(int i = 0; i < objs.size(); ++i) {
				r = sheet.createRow(i + 1);
				obj = objs.get(i);

				for(int j = 0; j < headers.size(); ++j) {
                    //r.createCell(j).setCellStyle();
					r.createCell(j).setCellValue(BeanUtils.getProperty(obj, this.getMethodName((ExcelHeader)headers.get(j))));
				}
			}
		} catch (IllegalAccessException var10) {
			var10.printStackTrace();
		} catch (InvocationTargetException var11) {
			var11.printStackTrace();
		} catch (NoSuchMethodException var12) {
			var12.printStackTrace();
		}

		return wb;
	}

	public void exportObj2Excel(String outPath, List objs, Class clz) {
		Workbook wb = this.handleObj2Excel(objs, clz);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(outPath);
			wb.write(fos);
		} catch (FileNotFoundException var17) {
			var17.printStackTrace();
		} catch (IOException var18) {
			var18.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException var16) {
				var16.printStackTrace();
			}

		}

	}

	public void exportObj2Excel(OutputStream os, List objs, Class clz) {
		try {
			Workbook wb = this.handleObj2Excel(objs, clz);
			wb.write(os);
		} catch (IOException var5) {
			var5.printStackTrace();
		}

	}

	public List<Object> readExcel2ObjsByClasspath(FileInputStream inputStream, Class clz, int readLine, int tailLine) {
		HSSFWorkbook wb = null;

		try {
			wb = new HSSFWorkbook(inputStream);
			return this.handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (IOException var7) {
			var7.printStackTrace();
			return null;
		}
	}

	public List<Object> readExcel2ObjsByPath(FileInputStream inputStream, Class clz, int readLine, int tailLine) {
		HSSFWorkbook wb = null;

		try {
			wb = new HSSFWorkbook(inputStream);
			return this.handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (IOException var7) {
			var7.printStackTrace();
			return null;
		}
	}

	public List<Object> readExcel2ObjsByClasspath(FileInputStream inputStream, Class clz) {
		return this.readExcel2ObjsByClasspath(inputStream, clz, 0, 0);
	}

	public List<Object> readExcel2ObjsByPath(FileInputStream inputStream, Class clz) {
		return this.readExcel2ObjsByPath(inputStream, clz, 0, 0);
	}

	public List<Object> readExcel2Objs(InputStream inputStream, Class clz, int readLine, int tailLine) {
		Workbook wb = null;

		try {
			wb = WorkbookFactory.create(inputStream);
			return this.handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (IOException var7) {
			var7.printStackTrace();
		} catch (InvalidFormatException var8) {
			var8.printStackTrace();
		}

		return null;
	}

	private String getCellValue(Cell c) {
		String o = null;
		switch(c.getCellType()) {
			case 0:
				o = String.valueOf(c.getNumericCellValue());
				break;
			case 1:
				o = c.getStringCellValue();
				break;
			case 2:
				o = String.valueOf(c.getCellFormula());
				break;
			case 3:
				o = "";
				break;
			case 4:
				o = String.valueOf(c.getBooleanCellValue());
				break;
			default:
				o = null;
		}

		return o;
	}

	private List<Object> handlerExcel2Objs(Workbook wb, Class clz, int readLine, int tailLine) {
		Sheet sheet = wb.getSheetAt(0);
		ArrayList objs = null;

		try {
			Row row = sheet.getRow(readLine);
			objs = new ArrayList();
			Map<Integer, String> maps = this.getHeaderMap(row, clz);
			if (maps == null || maps.size() <= 0) {
				throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");
			}

			for(int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; ++i) {
				row = sheet.getRow(i);
				Object obj = clz.newInstance();
				Iterator var12 = row.iterator();
				int clzNum = 0;
				while(var12.hasNext()) {// && clzNum < maps.size()
					Cell c = (Cell)var12.next();
					int ci = c.getColumnIndex();
					String tmp = maps.get(ci);
					if(tmp!= null){
						String mn = ((String)maps.get(ci)).substring(3);
						mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
						BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
					}
					clzNum ++;
				}

				objs.add(obj);
			}
		} catch (InstantiationException var15) {
			var15.printStackTrace();
		} catch (IllegalAccessException var16) {
			var16.printStackTrace();
		} catch (InvocationTargetException var17) {
			var17.printStackTrace();
		}

		return objs;
	}

	private List<ExcelHeader> getHeaderList(Class clz) {
		List<ExcelHeader> headers = new ArrayList();
		Method[] ms = clz.getDeclaredMethods();
		Method[] var7 = ms;
		int var6 = ms.length;

		for(int var5 = 0; var5 < var6; ++var5) {
			Method m = var7[var5];
			String mn = m.getName();
			if (mn.startsWith("get") && m.isAnnotationPresent(ExcelResources.class)) {
				ExcelResources er = (ExcelResources)m.getAnnotation(ExcelResources.class);
				headers.add(new ExcelHeader(er.title(), er.order(), mn));
			}
		}

		return headers;
	}

	private Map<Integer, String> getHeaderMap(Row titleRow, Class clz) {
		List<ExcelHeader> headers = this.getHeaderList(clz);
		Map<Integer, String> maps = new HashMap();
		Iterator var6 = titleRow.iterator();

		while(true) {
			while(var6.hasNext()) {
				Cell c = (Cell)var6.next();
				c.setCellType(Cell.CELL_TYPE_STRING);
				String title = c.getStringCellValue();
				Iterator var9 = headers.iterator();

				while(var9.hasNext()) {
					ExcelHeader eh = (ExcelHeader)var9.next();
					if (eh.getTitle().equals(title.trim())) {
						maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
						break;
					}
				}
			}

			return maps;
		}
	}
	
	/**
	 * @Title: downloadExcelTemplate
	 * @Description: 下载Excle模版文件
	 * @author: 杨培弘
	 * @param os
	 * @param path
	 * @throws Exception
	 */

	public static void downloadExcelTemplate(OutputStream os, String path) throws Exception {
		InputStream is = 
			Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		byte[] bs = new byte[1024];
		int len = -1;
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.flush();
		if (os != null) {
			os.close();
		}
		if (is != null) {
			is.close();
		}
	}
}
