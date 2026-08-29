package test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.pearl.o2o.utils.BinaryOut;

public class CRequest extends Request {

    protected Map<String, String> parameters = new HashMap<String, String>();

    public CRequest(String url) {
        super(url);
    }

    public CRequest putParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public CRequest putParameters(Map<String, String> parameters) {
        if (parameters != null) {
            this.parameters.putAll(parameters);
        }
        return this;
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    @Override
    public byte getType() {
        return 0;
    }

    @Override
    protected void writerParameters(BinaryOut writer) throws IOException {
        for (Entry<String, String> e : parameters.entrySet()) {
            writer.writeString(e.getKey());
            writer.writeString(e.getValue());
        }
    }

}
