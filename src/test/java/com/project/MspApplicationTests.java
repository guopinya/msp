package com.project;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MspApplicationTests {

    @Autowired
    private DataSourceProperties dsp;

    /**
     * 全局配置
     *
     * @param ag
     */
    private static void globalConfig(AutoGenerator ag) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor("zhuyifa");
        gc.setOpen(false);

        ag.setGlobalConfig(gc);
    }

    /**
     * 数据源配置
     *
     * @param ag
     * @param dsp
     */
    private static void dataSourceConfig(AutoGenerator ag, DataSourceProperties dsp) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(dsp.getDriverClassName());
        dsc.setUrl(dsp.getUrl());
        dsc.setUsername(dsp.getUsername());
        dsc.setPassword(dsp.getPassword());

        ag.setDataSource(dsc);
    }

    /**
     * 包配置
     *
     * @param moduleName
     * @param ag
     */
    private static void packageConfig(String moduleName, AutoGenerator ag) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.project");

        ag.setPackageInfo(pc);
    }

    /**
     * 自定义配置
     *
     * @param ag
     * @param moduleName
     */
    private static void injectionConfig(AutoGenerator ag, String moduleName) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        String projectPath = System.getProperty("user.dir");

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(ConstVal.TEMPLATE_XML + ".vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        ag.setCfg(cfg);
    }

    /**
     * 策略配置
     *
     * @param ag
     * @param tableName
     */
    private static void strategyConfig(AutoGenerator ag, String tableName) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(false);
        //strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");

        ag.setStrategy(strategy);
    }

    @Test
    public void contextLoads() {
        generate("system", "company");
    }

    public void generate(String moduleName, String tableName) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        globalConfig(generator);

        // 数据源配置
        dataSourceConfig(generator, dsp);

        // 包配置
        packageConfig(moduleName, generator);

        // 自定义配置
        injectionConfig(generator, moduleName);

        // 策略配置
        strategyConfig(generator, tableName);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        generator.setTemplate(new TemplateConfig().setXml(null));
        generator.execute();
    }

}
