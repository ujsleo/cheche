package cheche.core.spi;

import org.springframework.stereotype.Component;

import cheche.core.dto.OaVariables;

/**
 * 普通流程流转类节点
 * 
 * @author jieli
 */
@Component("CommonOaTask")
public class CommonOaTask implements IOaTask {
    @Override
    public boolean canAuto() {
        return false;
    }

    @Override
    public void start(OaVariables variables) {
        // start
    }

    @Override
    public void pass(OaVariables variables) {
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
