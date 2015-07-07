package org.ssm.crm520.test;

import java.io.File;
import java.io.FileWriter;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

public class CreateTemplate {

	//	1.那些domain需要生成代码
	//	2.定义固定的目录路径:都是使用相对路径,规范：路径前面都不加/,路径的后面都加/
	//	3.有那些模板需要生成
	//	4.模板文件对应的生成文件路径
	//	5.外循环：domains
	//	6.处理domain首字母小写
	//	7.内循环：templates和files
	//	8.实例化文件存放的路径
	//	9.处理特殊的文件名称
	//	10.判断父目录是否存在
	//	11.必须关闭流，写入内容
	//	12.开关：默认情况下已经存在的文件不需要生成代码 true:覆盖所有代码
	private static final String PATH = "src/";
	private static final String PACKAGE = "org/ssm/crm520/";
	private static final String RESOURCES = "resources/";
	private static final String TEST = "test/";
//	private static final String WEBAPP = "webapp/";
	//是否覆盖之前的文件;
	private boolean toggle =false;
	
	private String[] domains = {"Role_Menu"};
	private String[] names = { "角色_菜单"};
	
	private String[] templates = {"Domain.java","Mapper.java","Service.java", "ServiceImpl.java",
			"Query.java", "Controller.java", "ServiceTest.java","Mapper.xml"};
	
	private String[] files = { PATH + PACKAGE + "domain/",PATH + PACKAGE + "mapper/",PATH + PACKAGE + "service/",
			PATH + PACKAGE + "service/impl/", PATH + PACKAGE + "page/",
			PATH + PACKAGE + "web/controller/", TEST + PACKAGE + "test/service/", RESOURCES +PACKAGE+ "domain/"};

	@Test
	public void testCreate() throws Exception {
		Assert.assertEquals(templates.length, files.length);
		VelocityContext context= new VelocityContext();
		for (int i = 0; i < domains.length; i++) {
			context.put("domain_name", names[i]);
			context.put("domain", domains[i]);
			String domainsLower  =StringUtils.uncapitalize(domains[i]);
			context.put("domainLower", domainsLower);
			for (int j = 0; j < templates.length; j++) {
				File file = new File(files[j]+domains[i]+templates[j]);
				if("Domain.java".equals(templates[j])){
					file = new File(files[j]+domains[i]+".java");
				}else if("Mapper.java".equals(templates[j])){
					file = new File(files[j]+domains[i]+templates[j]);
				}else if("Service.java".equals(templates[j])){
					file = new File(files[j]+"I"+domains[i]+templates[j]);
				}else if("Mapper.xml".equals(templates[j])){
					file = new File(files[j]+domains[i]+templates[j]);
				}
				
				if(!toggle&&file.exists()){
					continue;
				}
				
				File parentFile = file.getParentFile();
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
				
				Template template = Velocity.getTemplate("template/"+templates[j], "UTF-8");
				FileWriter writer = new FileWriter(file);
				template.merge(context, writer);
				writer.close();
				System.out.println(file);
			}
			System.out.println(domains[i]+"创建成功!请刷新工程");
		}
	}
}
