package mr.robotto.engine.loader.file;

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
import java.util.concurrent.ConcurrentHashMap;

import mr.robotto.engine.collections.MrTreeMap;
import mr.robotto.engine.exceptions.MrParseException;
import mr.robotto.engine.loader.base.MrBaseLoader;
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

    public boolean check() throws JSONException, IOException, MrParseException {
        mIsValid = checkMagicNumber();
        mMetadata = loadMetaData();
        return mIsValid;
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

    private void loadJsonObject(Map<String, String> objects) throws IOException, MrParseException {
        ComposedTag nameTag = readComposedTag();
        if (!nameTag.getTagName().equals("NAME")) throw new MrParseException();
        int size = nameTag.getTagSize();
        final String jsonStr = readString(size);
        objects.put(nameTag.getIdentifier(), jsonStr);
    }

    private Map<String, String> loadJsonObjects() throws IOException, MrParseException {
        HashMap<String, String> objects = new HashMap<>();
        Tag sceneObjectsTag = readTag();
        if (!sceneObjectsTag.getTagName().equals("SOBJ")) throw new MrParseException();
        for (int i = 0; i < sceneObjectsTag.getTagSize(); i++) {
            loadJsonObject(objects);
        }
        return objects;
    }

    private void loadObject(final ConcurrentHashMap<String, MrObject> objects, final String jsonStr) throws IOException, MrParseException, JSONException {
        getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject objJson = convertToJsonObject(jsonStr);
                    MrObjectLoader loader = new MrObjectLoader(objJson);
                    MrObject object = loader.parse();
                    objects.put(object.getName(), object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Map<String, MrObject> loadObjects(Map<String, String> jsonObjects) throws JSONException, IOException, MrParseException, InterruptedException {
        ConcurrentHashMap<String, MrObject> objects = new ConcurrentHashMap<>();
        for (String key : jsonObjects.keySet()) {
            String jsonStr = jsonObjects.get(key);
            loadObject(objects, jsonStr);
        }
        getThreadPool().awaitAll();
        return objects;
    }

    private void loadTexture() throws IOException {
        ComposedTag textureTag = readComposedTag();
        int numBytes = textureTag.getTagSize();
        final String name = textureTag.getIdentifier();
        final byte[] image = new byte[numBytes];
        mStream.readFully(image);
        readEOL();
        getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                getResources().addTextureBitmap(name, bitmap);
            }
        });
    }

    private void loadTextures() throws IOException, MrParseException, InterruptedException {
        Tag texturesTag = readTag();
        if (!texturesTag.getTagName().equals("TEXT")) throw new MrParseException();
        for (int i = 0; i < texturesTag.getTagSize(); i++) {
            loadTexture();
        }
        getThreadPool().awaitAll();
    }

    private void loadFinish() throws IOException, MrParseException {
        String finish = readString(4);
        if (!finish.equals("FNSH")) throw new MrParseException();
    }

    public MrSceneTree parseSceneTree() throws IOException, MrParseException, JSONException, InterruptedException {
        if (!mIsValid) {
            throw new MrParseException("Loading without checking before is not allowed. Check you have called check() before call this method");
        }
        MrTreeMap<String, String> keyTree = loadHierarchy();
        Map<String, String> jsonObjects = loadJsonObjects();
        loadTextures();
        loadFinish();
        Map<String, MrObject> objects = loadObjects(jsonObjects);
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
        jsonObjects.clear();
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
