package cheche.core.dto.template;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cheche.core.dto.constant.ChecheConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 审批的参与者
 * 
 * @author jieli
 *
 */
@ApiModel(value = "审批的参与者")
public abstract class Participant {
	@ApiModelProperty(value = "域账号列表，以逗号,分隔")
	private String user;
	@ApiModelProperty(value = "角色列表，以逗号,分隔")
	private String role;
	@ApiModelProperty(value = "管理员域账号列表，以逗号,分隔")
	private String admin;

	/** 域账号的集合 */
	public Set<String> users() {
		if (StringUtils.isNotEmpty(user)) {
			List<String> lst = Arrays.asList(StringUtils.split(user, ChecheConst.SEPARATOR));
			return new HashSet<>(lst);
		}
		return new HashSet<>();
	}

	/** 所有处理人的域账号集合 */
	public Set<String> participant() {
		Set<String> ret = new HashSet<>();
		ret.addAll(users());
		// TODO ret.addAll(角色列表);
		return ret;
	}

	/** 管理员域账号的集合 */
	public Set<String> admins() {
		if (StringUtils.isNotEmpty(admin)) {
			List<String> lst = Arrays.asList(StringUtils.split(admin, ChecheConst.SEPARATOR));
			return new HashSet<>(lst);
		}
		return new HashSet<>();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
