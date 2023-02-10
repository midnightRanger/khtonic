package khtonic.khtonic.client;

public class ClientInsightData {
    private static int playerInsight;

    public static void set(int insight) {
        ClientInsightData.playerInsight = insight;
    }

    public static int getPlayerInsight() {
        return playerInsight;
    }
}
