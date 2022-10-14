package utils;

public class ValuesValidation {

    public static float validateX(String xValue) {

        if (xValue.length() > 9) {
            throw new NumberFormatException();
        }

        return Float.parseFloat(xValue);
    }

    public static float validateY(String yValue) {

        if (yValue.length() > 9) {
            throw new NumberFormatException();
        }

        return Float.parseFloat(yValue);
    }

    public static float validateR(String rValue) {

        if (rValue.length() > 9) {
            throw new NumberFormatException();
        }

        float rResult = Float.parseFloat(rValue);

        if (rResult <= 2 || rResult >= 5) {
            throw new NumberFormatException();
        }

        return rResult;
    }

    public static boolean isHit(float x, float y, float r) {
        return rectangleHit(x, y, r) || triangleHit(x, y, r) || circleHit(x, y, r);
    }

    private static boolean rectangleHit(float x, float y, float r) {
        return x <= 0 && y >= 0 && x > -r && y < r/2;
    }

    private static boolean triangleHit(float x, float y, float r) {
        return x >= 0 && y >= 0 && y <= -x + r;
    }

    private static boolean circleHit(float x, float y, float r) {
        return x >= 0 && y <= 0 && (x * x + y * y) <= r/2 * r/2;
    }

}
