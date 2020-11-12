package cheche.core.spi;

import org.springframework.stereotype.Component;

import cheche.core.dto.OaVariables;

/**
 * 普通自动审批类节点
 * 
 * @author jieli
 */
@Component("CommonAutoOaTask")
public class CommonAutoOaTask implements IAutoOaTask {
    @Override
    public boolean canAuto() {
        return true;
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

    @Override
    public boolean canAutoPass() {
        return true;
    }
}
