package common.util.tools;

import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.collections.MapUtils;

import common.util.tools.context.ContextUtils;

/**
 * Groovy脚本工具集
 * 
 * @author jieli
 *
 */
public class GroovyUtils {
	private static final String CONTEXT_IMPORTS = "import org.springframework.context.ApplicationContext; import common.util.tools.context.ContextUtils;";
	private static final String UTIL_IMPORTS = "import org.apache.commons.lang3.StringUtils;";
	/** Groovy脚本import包 */
	public static final String GROOVY_IMPORTS = CONTEXT_IMPORTS + UTIL_IMPORTS;

	private static ScriptEngine engine;

	/** Groovy脚本默认参数 */
	private final static Map<String, Object> DEFAULT_PARAM = new HashMap<>();
	static {
		DEFAULT_PARAM.put("context", ContextUtils.getContext());
	}

	/**
	 * 执行Groovy脚本
	 * 
	 * @param script
	 * @param param
	 * @return
	 * @throws ScriptException
	 */
	public static Object executeGroovyScript(String script, Map<String, Object> param) throws ScriptException {
		CheckUtils.checkNotNull(script, "Groovy script can NOT be empty or null");
		// Groovy脚本import包 + 执行脚本
		String evalScript = GROOVY_IMPORTS + script;

		ScriptEngine engine = groovyScriptEngine();
		Bindings bindings = engine.createBindings();
		bindings.putAll(DEFAULT_PARAM);
		if (MapUtils.isNotEmpty(param))
			bindings.putAll(param);
		return engine.eval(evalScript, bindings);
	}

	/**
	 * 获取ScriptEngine
	 * 
	 * @return ScriptEngine
	 */
	private static ScriptEngine groovyScriptEngine() {
		if (engine == null) {
			ScriptEngineManager engineManager = new ScriptEngineManager();
			engine = engineManager.getEngineByName("groovy");
		}
		return engine;
	}
}
