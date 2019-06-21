package cn.archko.test.item;

/**
 * @author: wushuyong 2019/5/30 :13:54
 */
public class StringBean2 implements IType {

    public String str;

    public StringBean2(String str) {
        this.str = str;
    }

    @Override
    public int getViewType() {
        return TestItem2.TYPE;
    }
}