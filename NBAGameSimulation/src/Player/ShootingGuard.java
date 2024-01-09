package Player;

import java.util.Random;

public class ShootingGuard extends Player {
    private static final Random random = new Random();

    public ShootingGuard(String name, double pts, double trb, double ast, double blk, double stl) {
        super(name, pts, trb, ast, blk, stl);
    }

    @Override
    public int calculateScore() {
        double ptsWeight = 0.35;
        double trbWeight = 0.1;
        double astWeight = 0.15;
        double blkWeight = 0.1;
        double stlWeight = 0.3;

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


