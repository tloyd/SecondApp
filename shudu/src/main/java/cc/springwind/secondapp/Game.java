package cc.springwind.secondapp;

import java.util.Random;

/**
 * Created by HeFan on 2016/5/23.
 */
public class Game {
    private String source[] = {
            "000006002" +
                    "904000300" +
                    "007000000" +
                    "006000040" +
                    "070005000" +
                    "000102000" +
                    "000090070" +
                    "020000005" +
                    "000040000",

            "600000100" +
                    "018005030" +
                    "034007000" +
                    "200006000" +
                    "000500002" +
                    "001090080" +
                    "500400600" +
                    "020000000" +
                    "003200809",

            "000000200" +
                    "030050000" +
                    "006000700" +
                    "000007000" +
                    "850000004" +
                    "000000003" +
                    "009702000" +
                    "000006000" +
                    "400000085"
    };
    private int[] numbers = new int[9 * 9];

    public Game() {
        Random random = new Random();
        int r = random.nextInt(source.length);
        numbers = getNumbersFromString(source[r]);
        calcAllUsedNum();
    }

    public int getTile(int x, int y) {
        return numbers[y * 9 + x];
    }

    /**
     * 得到给UI设置text的字符串
     *
     * @param x 坐标
     * @param y 坐标
     * @return 对应的字符串
     */
    public String getTileString(int x, int y) {
        int n = getTile(x, y);
        if (n == 0) {
            return "";
        } else {
            return String.valueOf(n);
        }
    }

    /**
     * 从字符串中取回数独初始化时方格内对应的数值
     *
     * @param source 源字符串
     * @return 数值数组
     */
    public int[] getNumbersFromString(String source) {

        int[] result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            result[i] = source.charAt(i) - '0';
        }
        return result;
    }

    /**
     * 计算所点击的单元格已经使用的数字
     *
     * @param x 点击的单元格的横坐标
     * @param y 点击的单元格的纵坐标
     * @return 使用的数字数组
     */
    private int[] calcTileUsedNum(int x, int y) {
        int c[] = new int[9];

        for (int i = 0; i < 9; i++) {
            if (i == y) {
                continue;
            }
            int n = getTile(x, i);
            if (n != 0) {
                c[n - 1] = n;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i == x) {
                continue;
            }
            int n = getTile(i, y);
            if (n != 0) {
                c[n - 1] = n;
            }
        }

        int a = x / 3;
        int b = y / 3;
        for (int i = 3 * a; i < 3 * (a + 1); i++) {
            for (int j = 3 * b; j < 3 * (b + 1); j++) {
                if (i == x && j == y) {
                    continue;
                }
                int n = getTile(i, j);
                if (n != 0)
                    c[n - 1] = n;
            }
        }

        int count = 0;
        for (int n : c) {
            if (n != 0) {
                count++;
            }
        }

        int s[] = new int[count];

        count = 0;
        for (int n : c) {
            if (n != 0) {
                s[count++] = n;
            }
        }
        return s;
    }

    public int usedNum[][][] = new int[9][9][];

    /**
     * 计算所有格子各自的已用数字
     */
    public void calcAllUsedNum() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                usedNum[i][j] = calcTileUsedNum(i, j);
            }
        }
    }

    public int[] getUsedNumByCoor(int x, int y) {
        return usedNum[x][y];
    }

    public boolean setTileIfValid(int x, int y, int n) {
        int w[] = getUsedNumByCoor(x, y);
        if (n != 0) {
            for (int l : w) {
                if (n == l) {
                    return false;
                }
            }
        }
        setTile(x, y, n);
        calcAllUsedNum();
        return true;
    }

    private void setTile(int x, int y, int n) {
        numbers[y * 9 + x] = n;
    }
}
