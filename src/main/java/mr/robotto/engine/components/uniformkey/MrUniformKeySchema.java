package mr.robotto.engine.components.uniformkey;

/**
 * Created by aaron on 27/12/2015.
 */
public class MrUniformKeySchema {

    private String mUniform;
    private String mGeneratorName;
    private int mLevel;
    private int mCount;

    public MrUniformKeySchema(String uniform, String generatorName, int level, int count) {
        mGeneratorName = generatorName;
        mUniform = uniform;
        mLevel = level;
        mCount = count;
    }

    public String getUniform() {
        return mUniform;
    }

    public String getGeneratorName() {
        return mGeneratorName;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getCount() {
        return mCount;
    }
}
