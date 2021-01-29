package com.yuan.bbs;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库文档生成器
 * https://gitee.com/leshalv/screw
 * 需要：screw-core,freemarker,hikari依赖
 */
public class DocumentGenerator {
    // 生成的所处目录
    static String output_dir = "D:/doc";
    // 生成的文档类型
    static EngineFileType output_type = EngineFileType.MD;
    // 要忽略的表
    static List<String> table_ignore = Arrays.asList("t_test0", "t_test1");
    // 文档描述
    static String description = "生成文档信息描述";
    // 数据库ip和数据库名
    static String db_ip = "127.0.0.1", db_name = "test";
    // 数据库用户名和密码
    static String db_username = "root", db_password = "root";

    public static void main(String[] args) {

        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://" + db_ip + ":3306/" + db_name + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useInformationSchema=true");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername(db_username);
        hikariConfig.setPassword(db_password);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaximumPoolSize(2);
        DataSource dataSource = new HikariDataSource(hikariConfig);

        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(output_dir)
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(output_type)
                //自定义文件名称
                //.fileName("文件名")
                //生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        ProcessConfig processConfig = ProcessConfig.builder()
                //忽略表名
                .ignoreTableName(table_ignore)
                .build();

        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0")
                //描述
                .description(description)
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig).build();
        //执行生成
        new DocumentationExecute(config).execute();
    }
}
