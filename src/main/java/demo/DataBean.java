package demo;

import java.util.List;

public class DataBean {

    private final List<String> data;
    private final int index;

    public DataBean(List<String> data, int index) {
        this.data = data;
        this.index = index;
    }

    public String getValue() {
        return this.data.get(index);
    }

    public void setValue(String value) {
        this.data.set(index, value);
    }

}