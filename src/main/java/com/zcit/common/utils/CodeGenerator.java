package com.zcit.common.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyifeng on 2018/9/23.
 */
public class CodeGenerator {

    public static void main(String[] args) {
        new CodeGenerator().generateByTables("billmember", "t_bill_member");
    }

    private void generateByTables(String moduleName, String... tableNames) {
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java")
                .setDateType(DateType.ONLY_DATE)
                .setAuthor("luf")
                .setFileOverride(false)
                .setOpen(false);


        String dbUrl = "jdbc:mysql://47.112.199.185:3306/bill?useTimezone=true&serverTimezone=GMT%2B8&characterEncoding=utf-8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setUrl(dbUrl)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("develop")
                .setPassword("develop");

        PackageConfig packageConfig = new PackageConfig()
                .setParent("com.zcit.bill")
                .setModuleName(moduleName);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setEntityBuilderModel(true)
                .setControllerMappingHyphenStyle(true)
                .setEntityColumnConstant(true)
                .setLogicDeleteFieldName("deleted")
                .setTablePrefix("t_")
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("com.zcit.common.base.BaseEntity")
                //.setSuperControllerClass("BaseController")
                .setSuperEntityColumns("id", "create_time", "creator_id", "update_time", "deleted")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mappers/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);

        new AutoGenerator()
                .setCfg(cfg)
                .setGlobalConfig(gc)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }
}
