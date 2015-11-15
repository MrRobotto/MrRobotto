package mr.robotto.engine.loader.file;

import org.json.JSONException;
import org.json.JSONObject;

import mr.robotto.engine.loader.base.MrJsonBaseLoader;

/**
 * Created by aaron on 03/11/2015.
 */
class MrMrrMetadataLoader extends MrJsonBaseLoader<MrMrrMetadata> {


    public MrMrrMetadataLoader(JSONObject obj) {
        super(obj);
    }

    @Override
    public MrMrrMetadata parse() throws JSONException {
        return null;
    }
}
