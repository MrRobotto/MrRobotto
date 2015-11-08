package mr.robotto.engine.loader.proposed;

import mr.robotto.engine.loader.core.MrJsonBaseLoader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aaron on 03/11/2015.
 */
public class MrMrrMetadataLoader extends MrJsonBaseLoader<MrMrrMetadata> {


    public MrMrrMetadataLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrMrrMetadata parse() throws JSONException {
        return null;
    }
}
