package com.base.production;

import java.io.File;
import java.sql.Connection;

import com.base.production.domain.Table;
import com.base.production.factory.BaseFactory;
import com.base.production.glocal.ClassNames;
import com.base.production.glocal.GlobalParam;
import com.base.production.jdbc.JDBCHandle;
import com.base.production.jdbc.JDBCTemplate;
import com.base.production.tools.AssistTools;

public class Production {
	static final String webroot = "webapp";

	public static void main(String[] args) throws Exception {
		ClassNames[] classNameses = { ClassNames.DAO, ClassNames.CONTROLLER, ClassNames.SQL, ClassNames.MODEL, ClassNames.SERVICE, ClassNames.JSP_INDEX, ClassNames.JSP_ADD,
				ClassNames.JSP_UPDATE };

		JDBCTemplate.DBNAME = "peixun";
		JDBCTemplate.IP = "rm-bp17061sm262c811so.mysql.rds.aliyuncs.com";
		JDBCTemplate.PORT = "3306";
		JDBCTemplate.USERNAME = "yoitree";
		JDBCTemplate.PASSWORD = "Yoi20160616";
		
		String projectRoot = "G:\\demo\\yoitree";
		String domainName = "yyUserFunction";
		String tableName = "yy_user_function";
		String packName = "com.yy";
		String description = "@author	wzw";
		String encoding = "UTF-8";

		operater(classNameses, tableName, projectRoot, domainName, packName, null, description, encoding);

		ClassNames[] movePath = { ClassNames.JSP_ADD, ClassNames.JSP_INDEX, ClassNames.JSP_UPDATE };
		copyFile(movePath, packName, projectRoot, domainName);

		 delete(packName, projectRoot, "page", domainName);
	}

	public static void copyFile(ClassNames[] classNameses, String packName, String projectRoot, String domainName) {
		for (ClassNames classNames : classNameses) {
			String sourcePath = projectRoot + File.separator + "src" + File.separator + packName.replace(".", File.separator) + File.separator + domainName + File.separator
					+ classNames.getPakName();
			String toFilePath = projectRoot + File.separator + webroot + File.separator + "WEB-INF" + File.separator + classNames.getPakName() + File.separator + domainName;
			AssistTools.copyFile(new File(sourcePath), new File(toFilePath));
		}
	}

	public static void delete(String packName, String projectRoot, String directory, String domainName) {
		String sourcePath = projectRoot + File.separator + "src" + File.separator + packName.replace(".", File.separator) + File.separator + domainName + File.separator
				+ directory;
		AssistTools.deleteAllFile(new File(sourcePath));
	}

	public static void operater(ClassNames[] classNameses, String tableName, String projectRoot, String domainName, String packName, String templateDirectory, String description,
			String encoding) throws Exception {
		Connection connection = JDBCTemplate.openConnection();
		String packNames = packName + File.separator + domainName;
		Table table = new Table();
		table.setTableName(tableName);
		table = JDBCHandle.getColumns(table, connection);

		table.setDescription(description);
		table.setPackageName(packNames.replace(File.separator, "."));
		table.setDomainName(domainName);
		table.setEncoding(encoding);
		table.setProjectRoot(projectRoot);
		table.setTemplateDirectory(templateDirectory);
		
		BaseFactory actionFactory = new BaseFactory();
		for (ClassNames classNames : classNameses) {

			ClassNames names = classNames;
			table.setTemplateName(GlobalParam.getTemplateName(names.getVal()));

			String pak = names.getPakName();

			String filePrex = AssistTools.toUppercase(domainName);
			if (names.getMethodName().endsWith("jsp") || pak.equals("sql")) {
				filePrex = domainName;
			}
			String outFilePath = projectRoot + File.separator + "src" + File.separator + packNames.replace(".", File.separator) + File.separator + pak + File.separator + filePrex
					+ AssistTools.toUppercase(names.getMethodName());

			table.setOutFilePath(outFilePath);
			table.setPak(pak);

			table.setConnection(connection);
			boolean s = actionFactory.create(table);
			if(!s){
				break;
			}
		}
	}

}
