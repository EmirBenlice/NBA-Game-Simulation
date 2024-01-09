package Player;

import java.util.Random;

public class SmallForward extends Player {
    private static final Random random = new Random();

    public SmallForward(String name, double pts, double trb, double ast, double blk, double stl) {
        super(name, pts, trb, ast, blk, stl);
    }

    @Override
    public int calculateScore() {
        double ptsWeight = 0.25;
        double trbWeight = 0.25;
        double astWeight = 0.2;
        double blkWeight = 0.15;
        double stlWeight = 0.15;

        int n = 5;
        double ptsScore = calculateStatScore(pts, ptsWeight, n);
        double trbScore = calculateStatScore(trb, trbWeight, n);
        double astScore = calculateStatScore(ast, astWeight, n);
        double blkScore = calculateStatScore(blk, blkWeight, n);
        double stlScore = calculateStatScore(stl, stlWeight, n);

        return (int) Math.round(ptsScore + trbScore + astScore + blkScore + stlScore);
    }

    private double calculateStatScore(double value, double weight, int n) {
        int randomValue = random.nextInt(2 * n + 1) - n;
        double adjustedValue = Math.max(value + randomValue, 0);
        return adjustedValue * weight;
    }
}
