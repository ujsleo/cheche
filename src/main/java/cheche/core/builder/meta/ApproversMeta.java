package cheche.core.builder.meta;

import java.util.ArrayList;
import java.util.List;

import cheche.core.builder.TemplateBuilder;
import cheche.core.dto.template.ApproverItem;
import common.util.tools.CheckUtils;

/**
 * 模板流程元信息
 * 
 * @author jieli
 *
 */
public class ApproversMeta {
	/** 持有上下文 */
	private TemplateBuilder ctx;
	/** 当前级次 */
	private Integer step = 1;
	private List<ApproverItem> lst = new ArrayList<>();
	private ApproverItem current;

	public ApproversMeta(TemplateBuilder ctx) {
		this.ctx = ctx;
	}

	/** 控制权交回上下文 */
	public TemplateBuilder and() {
		return ctx;
	}

	/** 添加节点 */
	public ApproversMeta add() {
		current = new ApproverItem();
		current.setStep(step++);
		lst.add(current);
		return this;
	}

	/** 设置审批节点的类名，框架反射用 */
	public ApproversMeta className(String className) {
		CheckUtils.checkNotNull(className, "className can NOT be empty or null");
		current.setClassName(className);
		return this;
	}

	/** 设置域账号列表，以逗号,分隔 */
	public ApproversMeta user(String user) {
		CheckUtils.checkNotNull(user, "user can NOT empty or be null");
		current.setUser(user);
		return this;
	}

	/** 设置角色列表，以逗号,分隔 */
	public ApproversMeta role(String role) {
		CheckUtils.checkNotNull(role, "role can NOT empty or be null");
		current.setRole(role);
		return this;
	}

	/** 设置管理员域账号列表，以逗号,分隔 */
	public ApproversMeta admin(String admin) {
		CheckUtils.checkNotNull(admin, "admin can NOT empty or be null");
		current.setAdmin(admin);
		return this;
	}

	public List<ApproverItem> getLst() {
		return lst;
	}
}
