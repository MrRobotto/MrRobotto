/*
 * MrRobotto Engine
 * Copyright (c) 2015, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.loader.file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import mr.robotto.MrRobotto;
import mr.robotto.scenetree.MrSceneTree;

/**
 * Created by aaron on 03/05/2015.
 */
public class MrRobottoFileLoader {

    private DataInputStream mStream;

    public MrRobottoFileLoader(InputStream inputStream) {
        mStream = new DataInputStream(inputStream);
    }

    private String readString(int strLen) throws IOException {
        byte[] strBytes = new byte[strLen];
        mStream.readFully(strBytes);
        //System.out.println(read);
        //if (read < strLen) {
        //    throw new IOException();
        //}
        return new String(strBytes,"US-ASCII");
    }

    private int readInt32BE() throws IOException {
        return mStream.readInt();
    }

    private void readEOL() throws IOException {
        mStream.readByte();
    }

    private boolean checkMagic() throws IOException {
        String magicStr = readString(13);
        readEOL();
        return magicStr.equals("MRROBOTTOFILE");
    }

    private String loadSectionName() throws IOException {
        return readString(4);
    }

    private Bitmap loadTextureData() throws IOException {
        int numBytes = readInt32BE();
        readEOL();
        byte[] image = new byte[numBytes];
        mStream.readFully(image);
        readEOL();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return bitmap;
    }

    private String loadTextureName() throws IOException {
        String nameTag = readString(4);
        //TODO: Check nameTag
        int strLen = readInt32BE();
        String name = readString(strLen);
        return name;
    }

    private String loadJson() throws IOException {
        int lenJson = readInt32BE();
        readEOL();
        final String json = readString(lenJson);
        readEOL();
        return json;
    }

    private void loadTextures() throws IOException {
        int numTextures = readInt32BE();
        readEOL();
        HashMap<String, byte[]> textures = new HashMap<String, byte[]>();
        for (int i = 0; i < numTextures; i++) {
            String name = loadTextureName();
            Bitmap bitmap = loadTextureData();
            MrRobotto.getsResources().addTextureBitmap(name, bitmap);
        }
    }

    public MrSceneTree parse() throws IOException, JSONException {
        if (!checkMagic()) {
            //TODO: Exception
            return null;
        }
        String section = loadSectionName();
        String json = "";
        while (!section.equals("FNSH")) {
            if (section.equals("JSON")) {
                json = loadJson();
            } else if (section.equals("TEXT")) {
                loadTextures();
                break;
            }
            section = loadSectionName();
        }
        mStream.close();
        JSONTokener tokener = new JSONTokener(json);
        JSONObject jsonObject = (JSONObject) tokener.nextValue();
        MrRobottoJsonLoader loader = new MrRobottoJsonLoader(jsonObject);
        MrRobottoJson resources = loader.parse();
        MrRobottoJson.Builder builder = new MrRobottoJson.Builder(resources);
        MrSceneTree tree = builder.buildSceneObjectsTree();
        return tree;
    }
}
