package cheche.core.dto.constant;

/**
 * Hook回调类型。1-发起start 2-通过pass 4-驳回reject 8-撤回back
 * 
 * @author jieli
 */
public class HookCallbackType {
    /** 发起 */
    public final static int START  = 1;
    /** 通过 */
    public final static int PASS   = 2;
    /** 驳回 */
    public final static int REJECT = 4;
    /** 撤回 */
    public final static int BACK   = 8;
}
