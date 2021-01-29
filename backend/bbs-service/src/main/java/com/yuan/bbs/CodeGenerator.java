package com.yuan.bbs;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器 单模块
 * https://mybatis.plus/guide/generator.html
 * 需要的依赖：
 * 1. mybatis-plus-boot-starter
 * 2. mybatis-plus-generator
 * 3. mysql-connector-java
 * 4. freemarker、lombok
 */
public class CodeGenerator {

    // 开发人员
    private static final String author = "yuan";
    // 表前缀
    private static final String table_prefix = "t_";
    // 数据库ip和数据库名
    private static final String db_ip = "127.0.0.1", db_name = "test";
    // 数据库用户名和密码
    private static final String db_username = "root", db_password = "root";
    // 父包名
    private static final String packageName = CodeGenerator.class.getPackageName();

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(projectPath + "/study-service/src/main/java");
        gc.setAuthor(author);           // 添加开发人员 @author xxx
        gc.setOpen(false);              // 不打开输出目录
        gc.setSwagger2(true);           // 实体属性 Swagger2 注解
        gc.setFileOverride(false);      // 不覆盖已有文件
        gc.setServiceName("%sService"); // Service接口不带前缀I
        mpg.setGlobalConfig(gc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);  //下划线转驼峰命名 下同
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);                    // 实体类添加lombok相关注解
        strategy.setRestControllerStyle(true);                  // Controller使用@RestController
        strategy.setControllerMappingHyphenStyle(true);         // 驼峰转连字符
        strategy.setTablePrefix(table_prefix);                  // 表前缀
        strategy.setInclude(scanner("请输入表名，多个表用英文逗号分割：").split(","));
        mpg.setStrategy(strategy);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + db_ip + ":3306/" + db_name + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(db_username);
        dsc.setPassword(db_password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);     // 设置模块名
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 模板引擎 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板 不在mapper包下生成xml包
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tip);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的表名！");
    }
}
