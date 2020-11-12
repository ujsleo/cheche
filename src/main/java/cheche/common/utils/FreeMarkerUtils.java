package cheche.common.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarker工具集
 * 
 * @author Jie
 */
public class FreeMarkerUtils {
    /**
     * 根据模板生成字符串
     * 
     * @param code 模板CODE
     * @param content 模板内容
     * @param dataModel 数据模型
     * @return
     */
    public static String generate(final String code, final String content, final Map<String, String> dataModel) {
        Configuration CONFIGURATION = cfg(code, content);
        try {
            // 获取模板
            Template template = CONFIGURATION.getTemplate(code);
            // 合并模板和数据模型
            Writer out = new StringWriter();
            template.process(dataModel, out);
            return out.toString();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Configuration cfg(final String code, final String content) {
        Configuration ret = new Configuration(Configuration.VERSION_2_3_29);
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(code, content);
        ret.setTemplateLoader(stringTemplateLoader);
        ret.setDefaultEncoding("UTF-8");
        return ret;
    }
}
