package chapter01;

import java.util.Arrays;
import java.util.List;

public class Config {
    private int stickLength;
    private int antVelocity;
    private List<Integer> antPositions;

    Config(int stickLength, int antVelocity, List<Integer> antPositions) throws Exception {
        this.stickLength = stickLength;
        this.antPositions = antPositions;
        this.antVelocity = antVelocity;
        for (int i : antPositions) {
            if (i >= stickLength || i <= 0)
                throw new Exception("Ant Position Error");
        }
    }


    public int getStickLength() {
        return stickLength;
    }

    public int getAntNumber() {
        return antPositions.size();
    }

    public List<Integer> getAntPositions() {
        return antPositions;
    }

    public int getAntVelocity() {
        return antVelocity;
    }

    public static Config createDemoConfig() throws Exception {
        return new Config(300, 5, Arrays.asList(30, 80, 110, 160, 250));
    }
    public static Config createConfig(int stickLength, int antVelocity, List<Integer> antPositions) throws Exception {
        return new Config(stickLength, antVelocity, antPositions);
    }

}