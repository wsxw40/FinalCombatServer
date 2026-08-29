package com.pearl.fcw.core.service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.dao.StableCacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.core.service.GmLogService.GmLogReplaceBatch;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.JsonUtil;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.utils.ConfigurationUtil;

@Service
public class FileService {
    private static Logger logger = LoggerFactory.getLogger(FileService.class);
    @Resource
    private FileService fileService;
	/** 项目内继承DmModel的类 */
	private static final List<Class<?>> DMMODEL_CLASSES = new ArrayList<>();

    /**
	 * 确保临时文件夹存在
	 */
    private void init() {
        File file = new File(ConfigurationUtil.TMP_FILE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
		Date now = new Date();
		for (File f : file.listFiles()) {
			if (f.isFile() && DateUtil.before(new Date(f.lastModified()), now, Calendar.DATE, 7)) {//删除7天前的临时文件
				f.delete();
			}
		}
    }

    public void importFile(MultipartFile file, GmUser gmUser) throws Exception {
        init();
		//在临时目录生成一个随机文件再导入
        File tmpFile = new File(ConfigurationUtil.TMP_FILE_DIR + UUID.randomUUID().toString());
        file.transferTo(tmpFile);
        importFile(tmpFile, gmUser);
        tmpFile.deleteOnExit();
    }

    @SuppressWarnings("resource")
    public void importFile(File file, GmUser gmUser) throws Exception {
        Map<Class<? extends BaseEntity>, CacheDao<? extends BaseEntity, ? extends Serializable>> modelAndDaoMap = CacheDao.getModelAndDaoMap();
        XSSFWorkbook wb = new XSSFWorkbook(file);
        if (wb.getNumberOfSheets() < 1) {
            return;
        }
		int sheetCount = 0;//有效的表单数量，为0表示无导入，向gm客户端抛出异常提示
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
			//Sheet不够4行的转到下一次循环
            if (sheet.getLastRowNum() < 3) {
                continue;
            }
			//检查Excel该Sheet名称对应的pojo类是否存在，不存在则转到下一次循环
            String className = "W" + StringUtil.toPascal(sheet.getSheetName());
            Class<? extends BaseEntity> dmModelClass = modelAndDaoMap.keySet().stream().filter(p -> p.getSimpleName().equals(className)).findFirst().orElse(null);
            if (null == dmModelClass) {
                continue;
            }

			sheetCount++;
			//创建sheet中字段名和列索引的记录
            Map<String, Integer> sheetMap = new HashMap<>();
			//第一行为空白的列不参与导入
            for (int colIndex = 0; colIndex < sheet.getRow(0).getLastCellNum(); colIndex++) {
                sheetMap.put(sheet.getRow(1).getCell(colIndex).getStringCellValue(), colIndex);
            }

			//创建pojo类中字段名和set方法的记录;创建pojo类中字段名和字段类型的记录
            Map<String, Method> fieldMap = new HashMap<>();
            Map<String, Class<?>> fieldClassMap = new HashMap<>();
			//获取pojo类中对应数据库的字段（只用private修饰符并且有get，set方法的字段）
            Class<?> clazz = dmModelClass;
            while (null != clazz && !clazz.equals(DmModel.class)) {
                for (Field field : dmModelClass.getDeclaredFields()) {
                    try {
						//排除非基本类型衍生类的字段
                        if (!field.getType().equals(Boolean.class) && !field.getType().equals(Byte.class) && !field.getType().equals(Short.class) && !field.getType().equals(Integer.class)
                                && !field.getType().equals(Long.class) && !field.getType().equals(Float.class) && !field.getType().equals(Double.class) && !field.getType().equals(Date.class)
                                && !field.getType().equals(String.class) && !field.getType().equals(byte[].class)) {
                            continue;
                        }
						//排除不是只有private修饰符的字段
                        if (Modifier.PRIVATE != field.getModifiers()) {
                            continue;
                        }
                        PropertyDescriptor pd = null;
                        if (DmModel.PATTERN_ONLY_FIRST_ONE_LOWER.matcher(field.getName()).find()) {
                            pd = new PropertyDescriptor(field.getName(), dmModelClass, "get" + field.getName(), "set" + field.getName());
                        } else {
                            pd = new PropertyDescriptor(field.getName(), dmModelClass);
                        }
                        fieldMap.put(field.getName(), pd.getWriteMethod());
                        fieldClassMap.put(field.getName(), field.getType());
                    } catch (Exception e) {
                        continue;
                    }
                }
                clazz = clazz.getSuperclass();
            }

			//sheet和pojo类的字段必须交叉检查保证严格对应
            for (String str : sheetMap.keySet()) {
                if (!fieldMap.containsKey(str)) {
                    throw new Exception("sheet " + sheet.getSheetName() + " column " + str + " ,datatable " + sheet.getSheetName() + " has not contains this field!");
                }
            }
            for (String str : fieldMap.keySet()) {
                if (!sheetMap.containsKey(str)) {
                    throw new Exception("datatable " + sheet.getSheetName() + " field " + str + " ,sheet " + sheet.getSheetName() + " has not contains this column!");
                }
            }

			//将sheet的数据转化为pojo类数据
            List<BaseEntity> modelList = new ArrayList<>();
            for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                BaseEntity m = dmModelClass.newInstance();
                for (String fieldName : sheetMap.keySet()) {
					if (rowIndex == 4 && fieldName.equals("items")) {
						System.out.println(sheetMap.get(fieldName));
					}
                    try {
                        int colIndex = sheetMap.get(fieldName);
                        XSSFCell cell = row.getCell(colIndex);
                        Class<?> fieldClass = fieldClassMap.get(fieldName);
                        Method setMethod = fieldMap.get(fieldName);
                        if (fieldClass.equals(String.class)) {
                            try {
                                setMethod.invoke(m, cell.getStringCellValue());
                            } catch (Exception e) {
								try {
									setMethod.invoke(m, cell.getRawValue());
								} catch (Exception e1) {
									setMethod.invoke(m, "");
								}
                            }
                        } else if (fieldClass.equals(Byte.class)) {
                            try {
                                setMethod.invoke(m, (byte) cell.getNumericCellValue());
                            } catch (Exception e) {
                                setMethod.invoke(m, Byte.parseByte(cell.getStringCellValue()));
                            }
                        } else if (fieldClass.equals(Short.class)) {
                            try {
                                setMethod.invoke(m, (short) cell.getNumericCellValue());
                            } catch (Exception e) {
                                setMethod.invoke(m, Byte.parseByte(cell.getStringCellValue()));
                            }
                        } else if (fieldClass.equals(Integer.class)) {
                            try {
                                setMethod.invoke(m, (int) cell.getNumericCellValue());
                            } catch (Exception e) {
                                try {
									setMethod.invoke(m, Integer.parseInt(cell.getStringCellValue()));
								} catch (Exception e1) {
									setMethod.invoke(m, 0);
								}
                            }
                        } else if (fieldClass.equals(Long.class)) {
                            try {
                                setMethod.invoke(m, (long) cell.getNumericCellValue());
                            } catch (Exception e) {
								try {
									setMethod.invoke(m, Long.parseLong(cell.getStringCellValue()));
								} catch (Exception e1) {
									setMethod.invoke(m, 0L);
								}
                            }
                        } else if (fieldClass.equals(Float.class)) {
                            try {
                                setMethod.invoke(m, (float) cell.getNumericCellValue());
                            } catch (Exception e) {
                                setMethod.invoke(m, Float.parseFloat(cell.getStringCellValue()));
                            }
                        } else if (fieldClass.equals(Double.class)) {
                            try {
                                setMethod.invoke(m, cell.getNumericCellValue());
                            } catch (Exception e) {
                                setMethod.invoke(m, Double.parseDouble(cell.getStringCellValue()));
                            }
                        } else if (fieldClass.equals(Boolean.class)) {
                            try {
                                setMethod.invoke(m, cell.getBooleanCellValue());
                            } catch (Exception e) {
                                setMethod.invoke(m, Boolean.parseBoolean(cell.getStringCellValue()));
                            }
                        } else if (fieldClass.equals(Date.class)) {
                            try {
                                setMethod.invoke(m, cell.getDateCellValue());
                            } catch (Exception e) {
                                try {
                                    setMethod.invoke(m, DateUtil.parseString(cell.getStringCellValue()));
                                } catch (Exception e1) {
                                }
                            }
                        } else if (fieldClass.equals(byte[].class)) {
							//FIXME byte[]类型转换有问题
                        }
                    } catch (Exception e) {
                        logger.error("import excel has error at rowIndex : " + rowIndex + " fieldName : " + fieldName);
                        throw e;
                    }
                }

				//FIXME gunPropertyN将废弃，该段代码过渡期使用 start
				if (m instanceof WSysItem) {
					WSysItem wSysItem = (WSysItem) m;
					Map<String, String> map = new HashMap<>();
					map.put("1", wSysItem.getGunProperty1());
					map.put("2", wSysItem.getGunProperty2());
					map.put("3", wSysItem.getGunProperty3());
					map.put("4", wSysItem.getGunProperty4());
					map.put("5", wSysItem.getGunProperty5());
					map.put("6", wSysItem.getGunProperty6());
					map.put("7", wSysItem.getGunProperty7());
					map.put("8", wSysItem.getGunProperty8());
					wSysItem.setGunProperty(JsonUtil.writeAsString(map));
				}
				//FIXME gunPropertyN将废弃，该段代码过渡期使用 end
                modelList.add(m);
            }

			//获取本Sheet对应的dao，并导入数据库
            fileService.replaceBatchByGm(modelList, gmUser);
        }
		if (sheetCount == 0) {//有效表单为0，抛出异常提示
			throw new Exception(StringUtil.getI18nWord("web.gm.error.importNoSheet"));
		}
    }

    /**
	 * 根据表名导出文件
	 * @param clazz 表名对应的pojo类
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
    public File exportFile(Class<? extends DmModel> clazz) throws Exception {
        init();
		StableCacheDao dao = (StableCacheDao) CacheDao.getModelAndDaoMap().get(clazz);
		List<? extends BaseEntity> list = dao.findListContainsRemoved(null);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(clazz.getSimpleName().replaceAll("^(?i)w", ""));
        XSSFRow row0 = sheet.createRow(0);
        XSSFRow row1 = sheet.createRow(1);
        XSSFRow row2 = sheet.createRow(2);
        List<Method> readMethodList = new ArrayList<>();
		//前三行,第一行属性类型缩写的首字母，第二行属性名称，第三行预留空白
        int i = 0;
        Class<?> clazz1 = clazz;
        while (null != clazz1 && !clazz1.equals(DmModel.class)) {
            for (Field f : clazz1.getDeclaredFields()) {
                try {
					//排除非基本类型衍生类的字段
                    if (!f.getType().equals(Boolean.class) && !f.getType().equals(Byte.class) && !f.getType().equals(Short.class) && !f.getType().equals(Integer.class)
                            && !f.getType().equals(Long.class) && !f.getType().equals(Float.class) && !f.getType().equals(Double.class) && !f.getType().equals(Date.class)
                            && !f.getType().equals(String.class) && !f.getType().equals(byte[].class)) {
                        continue;
                    }
					//排除不是只有private修饰符的字段
                    if (Modifier.PRIVATE != f.getModifiers()) {
                        continue;
                    }
                    PropertyDescriptor pd = null;
                    if (DmModel.PATTERN_ONLY_FIRST_ONE_LOWER.matcher(f.getName()).find()) {
                        pd = new PropertyDescriptor(f.getName(), clazz1, "get" + f.getName(), "set" + f.getName());
                    } else {
                        pd = new PropertyDescriptor(f.getName(), clazz1);
                    }
                    readMethodList.add(pd.getReadMethod());
                    XSSFCell cell0 = row0.createCell(i);
                    cell0.setCellValue(f.getType().getSimpleName().substring(0, 1));
                    XSSFCell cell1 = row1.createCell(i);
                    cell1.setCellValue(f.getName());
                    XSSFCell cell2 = row2.createCell(i);
                    String i18nKey = clazz1.getSimpleName().replaceAll("^W", "");
                    i18nKey = "web.gm." + StringUtil.toHump(i18nKey) + "s.col." + f.getName() + ".toolTips";
                    try {
                        cell2.setCellValue(StringUtil.getI18nWord(i18nKey));
                    } catch (Exception e) {
                    }
                } catch (Exception e) {
                    logger.warn(f.getName() + " has not get or set method in " + clazz1.getName());
                    continue;
                }
                i++;
            }
            clazz1 = clazz1.getSuperclass();
        }
		//第四行开始是数据
        i = 0;
        for (BaseEntity m : list) {
            XSSFRow row = sheet.createRow(3 + i++);
            int j = 0;
            for (Method method : readMethodList) {
                XSSFCell cell = row.createCell(j++);
                Object value = method.invoke(m);
                if (null == value) {
                    cell.setCellValue("");
                } else if (value instanceof Date) {
                    cell.setCellValue(DateUtil.format((Date) value));
                } else {
                    cell.setCellValue(value.toString());
                }
            }
        }
		//储存文件
        File file = new File(ConfigurationUtil.TMP_FILE_DIR + UUID.randomUUID().toString());
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fo = new FileOutputStream(file);
        wb.write(fo);
        fo.close();
        return file;
    }

    /**
	 * 批量导入数据
	 * @param modelList
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogReplaceBatch
    public void replaceBatchByGm(List<BaseEntity> modelList, GmUser gmUser) throws Exception {
        if (!modelList.isEmpty()) {
            CacheDao<? extends BaseEntity, ? extends Serializable> dao = CacheDao.getModelAndDaoMap().get(modelList.get(0).getClass());
            dao.replaceBatch(modelList);
        }
    }

    /**
	 * 获取可导出的表
	 * Key：简单类名，去除W首字母
	 * Value：完整类名
	 * @return
	 */
    public Map<String, String> getExportTables() {
        Set<Class<?>> classes = getDmmodelClassList().stream().filter(p -> {
            return p.getAnnotation(GoSchema.class).type().equals(Schema.SYS);
        }).collect(Collectors.toSet());

        Map<String, String> map = classes.stream().collect(Collectors.toMap(p -> p.getSimpleName().replaceAll("^W", ""), Class::getName));
        Map<String, String> map1 = new LinkedHashMap<>();
        map.keySet().stream().sorted().forEach(p -> map1.put(p, map.get(p)));
        return map1;
    }

	/**
	 * 获取项目内继承了DmModel的类
	 * @return
	 */
    public static List<Class<?>> getDmmodelClassList() {
		if (!DMMODEL_CLASSES.isEmpty()) {
			return DMMODEL_CLASSES;
    	}
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        List<TypeFilter> filters = Arrays.asList(new AnnotationTypeFilter(GoSchema.class), new AssignableTypeFilter(DmModel.class));
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            for (TypeFilter f : filters) {
                if (!f.match(metadataReader, metadataReaderFactory)) {
                    return false;
                }
            }
            return true;
        });
        Set<BeanDefinition> bds = scanner.findCandidateComponents("com.pearl.fcw");
        List<Class<?>> classes = bds.stream().map(bd -> {
            try {
                return Class.forName(bd.getBeanClassName());
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());
		DMMODEL_CLASSES.addAll(classes);
		return DMMODEL_CLASSES;
    }
}
