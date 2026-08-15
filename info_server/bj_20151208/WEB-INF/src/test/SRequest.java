package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pearl.o2o.utils.BinaryOut;


public class SRequest extends Request {

    protected List<Object> parameters = new ArrayList<Object>();

    public SRequest(String url) {
        super(url);
    }

    public SRequest addParameter(Object parameter) {
        this.parameters.add(parameter);
        return this;
    }

    public SRequest addParameters(Object ... parameters) {
        if (parameters != null) {
            Collections.addAll(this.parameters, parameters);
        }
        return this;
    }

    public List<Object> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    @Override
    public byte getType() {
        return 1;
    }

    @Override
    protected void writerParameters(BinaryOut writer) throws IOException {
        for (Object o : parameters) {
            if (o == null)
                continue;

            if (o instanceof Byte) {
                writer.writeByte((Byte) o);
            } else if (o instanceof Integer) {
                writer.writeInt((Integer) o);
            } else if (o instanceof Float) {
                writer.writeFloat((Float) o);
            } else if (o instanceof String) {
                writer.writeString((String) o);
            } else {
                throw new RuntimeException("不支持类型：" + o.getClass());
            }
        }
    }

}
