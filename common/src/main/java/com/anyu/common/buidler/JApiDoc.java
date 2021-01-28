package com.anyu.common.buidler;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

import java.util.ArrayList;
import java.util.List;

public class JApiDoc {
    public static void main(String[] args) {
        List<String> micServiceNames = new ArrayList<>();
        micServiceNames.add("article-service");
        micServiceNames.add("video-service");
        micServiceNames.forEach(name -> {
            DocsConfig config = new DocsConfig();
            config.setProjectPath("E:\\IdeaProject\\cms-parent\\" + name); // 项目根目录
            config.setProjectName("Cms-Parent"); // 项目名称
            config.setApiVersion("V1.0");       // 声明该API的版本
            config.setDocsPath("document\\" + name); // 生成API 文档所在目录
            config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
            Docs.buildHtmlDocs(config); // 执行生成文档
        });

    }
}
