package mr.robotto.engine.loader.proposed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mr.robotto.engine.collections.MrTreeMap;
import mr.robotto.engine.exceptions.MrParseException;
import mr.robotto.engine.loader.core.MrObjectLoader;
import mr.robotto.sceneobjects.MrObject;
import mr.robotto.sceneobjects.MrSceneTree;

/**
 * Created by aaron on 03/11/2015.
 */
public class MrMrrLoader extends MrBaseLoader {

    private DataInputStream mStream;
    private MrMrrMetadata mMetadata;
    private boolean mIsValid;

    public MrMrrLoader(InputStream stream) {
        mStream = new DataInputStream(stream);
    }

    public void check() throws JSONException, IOException, MrParseException {
        mIsValid = checkMagicNumber();
        mMetadata = loadMetaData();
    }

    public boolean isValid() {
        return mIsValid;
    }

    public boolean hasHierarchy() {
        return mMetadata.hasHierarchy();
    }

    private String readString(int strLen) throws IOException {
        byte[] strBytes = new byte[strLen];
        mStream.readFully(strBytes);
        return new String(strBytes, "US-ASCII");
    }

    private int readInt32BE() throws IOException {
        return mStream.readInt();
    }

    private void readEOL() throws IOException {
        mStream.readByte();
    }

    private Tag readTag() throws IOException {
        String tagName = readString(4);
        int tagSize = readInt32BE();
        readEOL();
        return new Tag(tagName, tagSize);
    }

    private ComposedTag readComposedTag() throws IOException {
        String tagName = readString(4);
        int idSize = readInt32BE();
        String identifier = readString(idSize);
        int tagSize = readInt32BE();
        readEOL();
        return new ComposedTag(tagName, tagSize, identifier);
    }

    private boolean checkMagicNumber() throws IOException {
        String magicStr = readString(13);
        readEOL();
        return magicStr.equals("MRROBOTTOFILE");
    }

    private JSONObject convertToJsonObject(String jsonStr) throws JSONException {
        JSONTokener tokener = new JSONTokener(jsonStr);
        return (JSONObject) tokener.nextValue();
    }

    private MrMrrMetadata loadMetaData() throws IOException, JSONException, MrParseException {
        Tag tag = readTag();
        if (!tag.getTagName().equals("META")) throw new MrParseException();
        int size = tag.getTagSize();
        String jsonStr = readString(size);
        JSONObject metadataJson = convertToJsonObject(jsonStr);
        MrMrrMetadataLoader loader = new MrMrrMetadataLoader(metadataJson);
        return loader.parse();
    }

    private MrTreeMap<String, String> loadHierarchy() throws MrParseException, IOException, JSONException {
        Tag tag = readTag();
        if (!tag.getTagName().equals("HIER")) throw new MrParseException();
        int size = tag.getTagSize();
        String jsonStr = readString(size);
        JSONObject hierJson = convertToJsonObject(jsonStr);
        MrHierarchyLoader loader = new MrHierarchyLoader(hierJson);
        return loader.parse();
    }

    private MrObject loadObject() throws IOException, MrParseException, JSONException {
        ComposedTag nameTag = readComposedTag();
        if (!nameTag.getTagName().equals("NAME")) throw new MrParseException();
        int size = nameTag.getTagSize();
        String jsonStr = readString(size);
        JSONObject objJson = convertToJsonObject(jsonStr);
        MrObjectLoader loader = new MrObjectLoader(objJson);
        return loader.parse();
    }

    private Map<String, MrObject> loadObjects() throws JSONException, IOException, MrParseException {
        HashMap<String, MrObject> objectDataList = new HashMap<>();
        Tag sceneObjectsTag = readTag();
        if (!sceneObjectsTag.getTagName().equals("SOBJ")) throw new MrParseException();
        for (int i = 0; i < sceneObjectsTag.getTagSize(); i++) {
            MrObject object = loadObject();
            objectDataList.put(object.getName(), object);
        }
        return objectDataList;
    }

    private Bitmap loadTexture(int numBytes) throws IOException {
        byte[] image = new byte[numBytes];
        mStream.readFully(image);
        readEOL();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return bitmap;
    }

    private void loadTextures() throws IOException, MrParseException {
        Tag texturesTag = readTag();
        if (!texturesTag.getTagName().equals("TEXT")) throw new MrParseException();
        for (int i = 0; i < texturesTag.getTagSize(); i++) {
            ComposedTag textureTag = readComposedTag();
            int numBytes = textureTag.getTagSize();
            String name = textureTag.getIdentifier();
            Bitmap bitmap = loadTexture(numBytes);
            //TODO: Has de cambiar esta guarrada... mejor pasárselos al objeto luego
            getResources().addTextureBitmap(name, bitmap);
        }
    }

    private void loadFinish() throws IOException, MrParseException {
        String finish = readString(4);
        if (!finish.equals("FNSH")) throw new MrParseException();
    }

    public MrSceneTree parseSceneTree() throws IOException, MrParseException, JSONException {
        MrTreeMap<String, String> keyTree = loadHierarchy();
        loadTextures();
        Map<String, MrObject> objects = loadObjects();
        loadFinish();
        MrObject rootData = objects.get(keyTree.getRoot());
        MrSceneTree tree = new MrSceneTree(rootData);
        Iterator<Map.Entry<String, String>> it = keyTree.parentKeyChildValueTraversal();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            //Gets the value
            MrObject objectData = objects.get(entry.getValue());
            //Gets the root key via entry.getKey
            tree.addChildByKey(entry.getKey(), objectData);
        }
        mStream.close();
        return tree;
    }

    public MrObject parseObject() {
        return null;
    }

    private class Tag {
        private String mTagName;
        private int mTagSize;

        public Tag(String tagName, int tagSize) {
            mTagName = tagName;
            mTagSize = tagSize;
        }

        public String getTagName() {
            return mTagName;
        }

        public int getTagSize() {
            return mTagSize;
        }
    }

    private class ComposedTag extends Tag {

        private String mIdentifier;

        public ComposedTag(String tagName, int tagSize, String identifier) {
            super(tagName, tagSize);
            mIdentifier = identifier;
        }

        public String getIdentifier() {
            return mIdentifier;
        }
    }
}
