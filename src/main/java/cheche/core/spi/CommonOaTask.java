package cheche.core.spi;

import org.springframework.stereotype.Component;

/**
 * 普通流程流转类节点
 * 
 * @author jieli
 *
 */
@Component("CommonOaTask")
public class CommonOaTask implements IOaTask {
	@Override
	public boolean canAuto() {
		return false;
	}

	@Override
	public void start() {
		// start
	}

	@Override
	public void pass() {
		// pass
	}

	@Override
	public void reject() {
		// reject
	}

	@Override
	public void back() {
		// back
	}
}
