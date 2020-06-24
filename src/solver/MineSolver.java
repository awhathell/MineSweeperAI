/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import javafx.util.Pair;

public class MineSolver {

    private int SUMLIMIT = 10000000;
    private int DEEPDFSLIMIT = 1000;

    private int row = 16;
    private int col = 30;
    private int mine = 99;

    private boolean flag;

    private double[][] mineProb;

    private class SumInteger {

        public int value;

        public SumInteger(int value) {
            this.value = value;
        }

    }

    public MineSolver() {
        mineProb = new double[row * col + 1][mine + 2];
        mineProb[0][0] = 1.0;
        for (int i = 0; i < row * col; i++) {
            for (int j = 0; j < mine + 1; j++) {
                mineProb[i + 1][j] += mineProb[i][j];
                mineProb[i + 1][j + 1] += mineProb[i][j];
            }
        }
    }

    private void simpleDetect(int[][] board) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] > 0) {
                    int s = 0;
                    for (int k = Integer.max(0, i - 1); k < Integer.min(row, i + 2); k++) {
                        for (int m = Integer.max(0, j - 1); m < Integer.min(col, j + 2); m++) {
                            if (board[k][m] < 0) {
                                s++;
                            }
                        }
                    }
                    if (s == board[i][j]) {
                        for (int k = Integer.max(0, i - 1); k < Integer.min(row, i + 2); k++) {
                            for (int m = Integer.max(0, j - 1); m < Integer.min(col, j + 2); m++) {
                                if (board[k][m] == -1) {
                                    board[k][m]--;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void DFS(Vector<Pair<Integer, Pair<Integer, Integer>>> cnt, Vector<Vector<Integer>> edges,
            int n, int i, int[] s, int[] cur, SumInteger sum, int mineMin, int mineMax,
            HashMap<Integer, Double> mineCnt, Vector<HashMap<Integer, Double>> perBoardCnt,
            Vector<Vector<Integer>> allStatus, boolean saveAllStatus) {
        if (sum.value == SUMLIMIT) {
            flag = false;
            return;
        }
        if (mineMax < 0 || mineMin > (n - i)) {
            return;
        }
        for (int k = 0; k < cnt.size(); k++) {
            if (cnt.get(k).getKey() < 0 || cnt.get(k).getValue().getKey() + cnt.get(k).getValue().getValue()
                    < cnt.get(k).getKey()) {
                return;
            }
        }
        if (i == n) {
            sum.value++;
            int mineCur = 0;
            for (int j = 0; j < s.length; j++) {
                s[j] += cur[j];
                mineCur += cur[j];
            }
            mineCnt.replace(mineCur, mineCnt.get(mineCur) + 1);
            for (int j = 0; j < s.length; j++) {
                if (cur[j] != 0) {
                    HashMap<Integer, Double> boardCnt = perBoardCnt.get(j);
                    boardCnt.replace(mineCur, boardCnt.get(mineCur) + 1);
                    perBoardCnt.set(j, boardCnt);
                }
            }
            if (saveAllStatus) {
                Vector<Integer> statusTemp = new Vector<>();
                for (int j : cur) {
                    statusTemp.add(j);
                }
                allStatus.add(statusTemp);
                for (int j = 0; j < allStatus.lastElement().size(); j++) {
                    if (allStatus.lastElement().get(j) == 1) {
                        Vector<Integer> status = allStatus.lastElement();
                        status.set(j, -2);
                        allStatus.set(allStatus.size() - 1, status);
                    }
                }
            }
            return;
        }
        for (int k = 0; k < edges.get(i).size(); k++) {
            Pair<Integer, Pair<Integer, Integer>> cntTemp;
            cntTemp = cnt.get(edges.get(i).get(k));
            Pair<Integer, Integer> pairTemp = cntTemp.getValue();
            pairTemp = new Pair<>(pairTemp.getKey() - 1, pairTemp.getValue());
            cntTemp = new Pair<>(cntTemp.getKey(), pairTemp);
            cnt.set(edges.get(i).get(k), cntTemp);
        }
        cur[i] = 0;
        DFS(cnt, edges, n, i + 1, s, cur, sum, mineMin, mineMax, mineCnt,
                perBoardCnt, allStatus, saveAllStatus);
        for (int k = 0; k < edges.get(i).size(); k++) {
            Pair<Integer, Pair<Integer, Integer>> cntTemp;
            cntTemp = cnt.get(edges.get(i).get(k));
            Pair<Integer, Integer> pairTemp = cntTemp.getValue();
            pairTemp = new Pair<>(pairTemp.getKey() + 1, pairTemp.getValue());
            cntTemp = new Pair<>(cntTemp.getKey(), pairTemp);
            cnt.set(edges.get(i).get(k), cntTemp);
        }
        cur[i] = 1;
        for (int k = 0; k < edges.get(i).size(); k++) {
            Pair<Integer, Pair<Integer, Integer>> cntTemp;
            cntTemp = cnt.get(edges.get(i).get(k));
            Pair<Integer, Integer> pairTemp = cntTemp.getValue();
            pairTemp = new Pair<>(pairTemp.getKey() - 1, pairTemp.getValue());
            cntTemp = new Pair<>(cntTemp.getKey() - 1, pairTemp);
            cnt.set(edges.get(i).get(k), cntTemp);
        }
        DFS(cnt, edges, n, i + 1, s, cur, sum, mineMin - 1, mineMax - 1, mineCnt,
                perBoardCnt, allStatus, saveAllStatus);
        cur[i] = 0;
        for (int k = 0; k < edges.get(i).size(); k++) {
            Pair<Integer, Pair<Integer, Integer>> cntTemp;
            cntTemp = cnt.get(edges.get(i).get(k));
            Pair<Integer, Integer> pairTemp = cntTemp.getValue();
            pairTemp = new Pair<>(pairTemp.getKey() + 1, pairTemp.getValue());
            cntTemp = new Pair<>(cntTemp.getKey() + 1, pairTemp);
            cnt.set(edges.get(i).get(k), cntTemp);
        }
    }

    private void DFSDetect(int[][] board, Vector<Pair<Integer, Integer>> points,
            Vector<Pair<Integer, Integer>> safeCells, Vector<Pair<Integer, Integer>> unknownCells,
            double[][] prob, int mineMin, int mineMax,
            HashMap<Integer, Double> mineCnt, Vector<HashMap<Integer, Double>> perBoardCnt,
            Vector<Vector<Integer>> allStatus, boolean saveAllStatus) {
        HashSet<Pair<Integer, Integer>> unknownSet = new HashSet<>();
        unknownSet.addAll(unknownCells);
        Vector<Pair<Integer, Pair<Integer, Integer>>> cnt = new Vector<>();
        Vector<Vector<Integer>> edges = new Vector<>();
        for (int i = 0; i < safeCells.size(); i++) {
            Pair<Integer, Integer> it = safeCells.get(i);
            int temp = board[it.getKey()][it.getValue()];
            int pairFirst = 0, pairSecond = 0;
            for (int k = Integer.max(0, it.getKey() - 1); k < Integer.min(row, it.getKey() + 2); k++) {
                for (int m = Integer.max(0, it.getValue() - 1); m < Integer.min(col, it.getValue() + 2); m++) {
                    if (board[k][m] == -2) {
                        temp--;
                    } else if (board[k][m] == -1) {
                        if (!unknownSet.contains(new Pair<>(k, m))) {
                            pairSecond++;
                        } else {
                            pairFirst++;
                        }
                    }
                }
            }
            cnt.add(new Pair<>(temp, new Pair<>(pairFirst, pairSecond)));
        }
        for (int i = 0; i < unknownCells.size(); i++) {
            edges.add(new Vector<>());
            for (int j = 0; j < safeCells.size(); j++) {
                if (Math.abs(safeCells.get(j).getKey() - unknownCells.get(i).getKey()) <= 1
                        && Math.abs(safeCells.get(j).getValue() - unknownCells.get(i).getValue()) <= 1) {
                    Vector<Integer> edgesTemp = edges.get(i);
                    edgesTemp.add(j);
                    edges.set(i, edgesTemp);
                }
            }
        }
        int cells = unknownCells.size();
        int sum = 0;
        int[] s = new int[cells];
        int[] cur = new int[cells];
        mineCnt.clear();
        perBoardCnt.clear();
        for (int i = 0; i < cells; i++) {
            mineCnt.put(i, 0.0);
            HashMap<Integer, Double> hashMap = new HashMap<>();
            for (int j = 0; j < cells; j++) {
                hashMap.put(j, 0.0);
            }
            perBoardCnt.add(hashMap);
        }
        DFS(cnt, edges, cells, 0, s, cur, new SumInteger(sum), mineMin, mineMax, mineCnt, perBoardCnt, allStatus, saveAllStatus);
        for (int i = 0; i < cells; i++) {
            if (s[i] == 0) {
                points.add(unknownCells.get(i));
            } else if (s[i] == sum) {
                board[unknownCells.get(i).getKey()][unknownCells.get(i).getValue()] = -2;
            } else {
                prob[unknownCells.get(i).getKey()][unknownCells.get(i).getValue()] = (double) s[i] / sum;
            }
        }
        if (saveAllStatus) {
            for (int i = 0; i < allStatus.size(); i++) {
                Vector<Integer> curStatus = allStatus.get(i);
                for (int j = 0; j < curStatus.size(); j++) {
                    if (curStatus.get(j) == 0) {
                        for (int k = Integer.max(0, unknownCells.get(j).getKey() - 1);
                                k < Integer.min(row, unknownCells.get(j).getKey() + 2); k++) {
                            for (int m = Integer.max(0, unknownCells.get(j).getValue() - 1);
                                    m < Integer.min(col, unknownCells.get(j).getValue() + 2); m++) {
                                if (board[k][m] == -2) {
                                    curStatus.set(j, curStatus.get(j) + 1);
                                }
                            }
                        }
                        for (int k = 0; k < curStatus.size(); k++) {
                            if (curStatus.get(k) == -2
                                    && Math.abs(unknownCells.get(j).getKey() - unknownCells.get(k).getKey()) <= 1
                                    && Math.abs(unknownCells.get(j).getValue() - unknownCells.get(k).getValue()) <= 1) {
                                curStatus.set(j, curStatus.get(j) + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    private void floodFill(int[][] board, HashSet<Pair<Integer, Integer>> useSet,
            Vector<Pair<Integer, Integer>> safeCells, Vector<Pair<Integer, Integer>> unknownCells,
            int i, int j) {
        useSet.add(new Pair<>(i, j));
        if (board[i][j] == -1) {
            unknownCells.add(new Pair<>(i, j));
        } else {
            safeCells.add(new Pair<>(i, j));
        }
        for (int k = Integer.max(0, i - 1); k < Integer.min(row, i + 2); k++) {
            for (int m = Integer.max(0, j - 1); m < Integer.min(col, j + 2); m++) {
                if (board[k][m] != -2 && !useSet.contains(new Pair<>(k, m))
                        && board[k][m] * board[i][j] < 0) {
                    floodFill(board, useSet, safeCells, unknownCells, k, m);
                }
            }
        }
    }

    private void statusSplit(Vector<Vector<Integer>> allStatus, Vector<Integer> ids,
            Vector<Vector<Integer>> idsVector) {
        if (ids.isEmpty()) {
            return;
        }
        for (int i = 0; i < allStatus.get(0).size(); i++) {
            boolean allSame = true, noMine = true;
            for (Integer id : ids) {
                if (Objects.equals(allStatus.get(id).get(i), allStatus.get(ids.get(0)).get(i))) {
                    allSame &= true;
                } else {
                    allSame &= false;
                }
                if (allStatus.get(id).get(i) >= 0) {
                    noMine &= true;
                } else {
                    noMine &= false;
                }
            }
            if (noMine && !allSame) {
                HashMap<Integer, Vector<Integer>> idsDict = new HashMap<>();
                for (Integer id : ids) {
                    Vector<Integer> idsTemp = new Vector<>();
                    idsTemp.add(id);
                    idsDict.put(allStatus.get(id).get(i), idsTemp);
                    idsTemp = null;
                }
                for (Vector<Integer> value : idsDict.values()) {
                    statusSplit(allStatus, value, idsVector);
                }
                return;
            }
        }
        idsVector.add(ids);
    }

 /*   private double deepDFS(Vector<Vector<Integer>> allStatus, Vector<Integer> ids,
            double[] prob, HashMap<Vector<Integer>, Double> f) {
        if (f.containsKey(ids)) {
            return f.get(ids);
        }
        if (ids.size() == 1) {
            for (int i = 0; i < prob.length; i++) {
                if (allStatus.get(ids.get(0)).get(i) < 0) {
                    prob[i] = 1.0;
                }
            }
            if (!f.containsKey(ids)) {
                f.put(ids, 1.0);
            } else {
                f.replace(ids, 1.0);
            }
            return f.get(ids);
        }
        double ret = 0.0;
        Vector<Pair<Integer, Integer>> order = new Vector<>();
        for (int i = 0; i < prob.length; i++) {
            int mine = 0;
            for (Integer id : ids) {
                if (allStatus.get(id).get(i) < 0) {
                    mine++;
                }
            }
            order.add(new Pair(mine, i));
        }
        for (Pair<Integer, Integer> pair : order) {
            int i = pair.getValue();
            Vector<Vector<Integer>> idsVector = new Vector<>();
            Vector<Integer> cur = new Vector<>();
            for (Integer id : ids) {
                if (allStatus.get(id).get(i) >= 0) {
                    cur.add(id);
                }
            }
            if (cur.size() == 0) {
                prob[i] = 0;
                continue;
            }
            if (cur.size() == ids.size()) {
                prob[i] = 1;
                continue;
            }
            if ((double) cur.size() / ids.size() < ret - 1e-8) {
                prob[i] = 0;
                continue;
            }
            statusSplit(allStatus, cur, idsVector);
            double[] p = new double[prob.length];
            prob[i] = 0.0;
            for (int j = 0; j < idsVector.size(); j++) {
                prob[i] += deepDFS(allStatus, idsVector.get(j), p, f) * idsVector.get(j).size() / ids.size();
            }
            ret = Double.max(ret, prob[i]);
        }
        f.replace(ids, ret);
        return ret;
    }*/

    private boolean DFSDetect(int[][] board, Vector<Pair<Integer, Integer>> points,
            double[][] prob) {
        int unknown = 0;
        int surMine = mine;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == -1) {
                    unknown++;
                }
                if (board[i][j] == -2) {
                    surMine--;
                }
            }
        }

        HashMap<Integer, Double> mineCnt = new HashMap<>();
        Vector<HashMap<Integer, Double>> perBoardCnt = new Vector<>();
        Vector<Vector<Integer>> allStatus = new Vector<>();

        Vector<Vector<Pair<Integer, Integer>>> safeCellsVector = new Vector<>();
        Vector<Vector<Pair<Integer, Integer>>> unknownCellsVector = new Vector<>();
        Vector<HashMap<Integer, Double>> mineCntVector = new Vector<>();
        Vector<Vector<HashMap<Integer, Double>>> perBoardCntVector = new Vector<>();
        HashSet<Pair<Integer, Integer>> useSet = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != -2 && !useSet.contains(new Pair<>(i, j))) {
                    Vector<Pair<Integer, Integer>> safeCells = new Vector<>();
                    Vector<Pair<Integer, Integer>> unknownCells = new Vector<>();
                    floodFill(board, useSet, safeCells, unknownCells, i, j);
                    if (safeCells.size() >= 1 && unknownCells.size() >= 1) {
                        DFSDetect(board, points, safeCells, unknownCells, prob, 0,
                                surMine, mineCnt, perBoardCnt, allStatus, false);
                        if (points.size() > 0) {
                            return true;
                        }
                        safeCellsVector.add(new Vector<>(safeCells));
                        unknownCellsVector.add(new Vector<>(unknownCells));
                        mineCntVector.add(new HashMap<>(mineCnt));
                        perBoardCntVector.add(new Vector<>(perBoardCnt));
                    }
                }
            }
        }

        int last = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == -1 && prob[i][j] < -0.5) {
                    last++;
                }
            }
        }

        boolean doDeepDFS = false;
        for (int i = 0; i < safeCellsVector.size(); i++) {
            double[] f = mineProb[last].clone();
            for (int j = 0; j < safeCellsVector.size(); j++) {
                if (i != j) {
                    for (int k = surMine; k >= 0; k--) {
                        double s = 0.0;
                        for (Map.Entry<Integer, Double> entry : mineCntVector.get(j).entrySet()) {
                            if (k >= entry.getKey()) {
                                s += f[k - entry.getKey()] * entry.getValue();
                            }
                        }
                        f[k] = s;
                    }
                }
            }
            double s = 0.0;
            for (Map.Entry<Integer, Double> entry : mineCntVector.get(i).entrySet()) {
                if (entry.getKey() <= surMine) {
                    s += f[surMine - entry.getKey()] * entry.getValue();
                }
            }

            if (s <= DEEPDFSLIMIT) {
                doDeepDFS = true;
                break;
            }

            for (int j = 0; j < unknownCellsVector.get(i).size(); j++) {
                double curS = 0.0;
                for (Map.Entry<Integer, Double> entry : perBoardCntVector.get(i).get(j).entrySet()) {
                    if (entry.getKey() <= surMine) {
                        curS += f[surMine - entry.getKey()] * entry.getValue();
                    }
                }
                prob[unknownCellsVector.get(i).get(j).getKey()][unknownCellsVector.get(i).get(j).getValue()]
                        = curS / s;
            }
        }

        if (doDeepDFS) {
            Vector<Pair<Integer, Integer>> safeCells = new Vector<>();
            Vector<Pair<Integer, Integer>> unknownCells = new Vector<>();
            surMine = mine;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == -1) {
                        unknownCells.add(new Pair<>(i, j));
                    } else if (board[i][j] > 0) {
                        safeCells.add(new Pair<>(i, j));
                    } else if (board[i][j] == -2) {
                        surMine--;
                    }
                }
            }
            DFSDetect(board, points, safeCells, unknownCells, prob, surMine, surMine, mineCnt, perBoardCnt, allStatus, true);
            for (int i = 0; i < unknownCells.size(); i++) {
                boolean noMine = true;
                for (int j = 0; j < allStatus.size(); j++) {
                    if (allStatus.get(j).get(i) < 0) {
                        noMine = false;
                        break;
                    }
                }
                if (noMine) {
                    points.add(unknownCells.get(i));
                }
            }
            if (points.size() > 0) {
                return true;
            }

            double[] deepProb = new double[unknownCells.size()];
            Vector<Integer> ids = new Vector<>();
            for (int i = 0; i < allStatus.size(); i++) {
                ids.add(i);
            }
            HashMap<Vector<Integer>, Double> f = new HashMap<>();
        //    deepDFS(allStatus, ids, deepProb, f);
            for (int i = 0; i < unknownCells.size(); i++) {
                prob[unknownCells.get(i).getKey()][unknownCells.get(i).getValue()] = 1 - deepProb[i];
            }
        } else {
            double pSum = 0.0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == -1 && prob[i][j] > -0.5) {
                        pSum += prob[i][j];
                    }
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == -1 && prob[i][j] < -0.5) {
                        prob[i][j] = (surMine - pSum) / last;
                    }
                }
            }
        }
        return false;
    }

    private boolean getSafePoints(int[][] board, Vector<Pair<Integer, Integer>> points) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] >= 0) {
                    int s = 0;
                    for (int k = Integer.max(0, i - 1); k < Integer.min(row, i + 2); k++) {
                        for (int m = Integer.max(0, j - 1); m < Integer.min(col, j + 2); m++) {
                            if (board[k][m] == -2) {
                                s++;
                            }
                        }
                    }
                    if (s == board[i][j]) {
                        for (int k = Integer.max(0, i - 1); k < Integer.min(row, i + 2); k++) {
                            for (int m = Integer.max(0, j - 1); m < Integer.min(col, j + 2); m++) {
                                if (board[k][m] == -1) {
                                    points.add(new Pair<>(k, m));
                                }
                            }
                        }
                    }
                }
            }
        }
        return !points.isEmpty();
    }

    private int getValue(int i, int j, int[][] board) {
        if ((i == 0 || i == row - 1) && (j == 0 || j == col - 1)) {
            return -1000;
        } else {
            int s = 0;
            for (int k = Integer.max(0, i - 2); k < Integer.min(row, i + 3); k++) {
                for (int m = Integer.max(0, j - 2); m < Integer.min(col, j + 3); m++) {
                    if (board[k][m] >= 0) {
                        s++;
                    }
                }
            }
            return -s;
        }
    }

    public boolean getCLKPoints(int[][] board, Vector<Pair<Integer, Integer>> points) {
        flag = true;
        points.clear();
        //    System.out.println("pointsµÄ³ß´çÎª"+points.size());
        simpleDetect(board);
        if (getSafePoints(board, points)) {
            return true;
        }
        double[][] prob = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                prob[i][j] = -1;
            }
        }
        if (DFSDetect(board, points, prob)) {
            return flag;
        }
        if (board[0][0] == -1) {
            points.add(new Pair<>(0, 0));
            return true;
        }
        double minP = 1e100;
        int si = -1, sj = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == -1
                        && (prob[i][j] < minP - 1e-8
                        || (Math.abs(prob[i][j] - minP) < 1e-8 && getValue(i, j, board) < getValue(si, sj, board)))) {
                    minP = prob[i][j];
                    si = i;
                    sj = j;
                }
            }
        }
        points.add(new Pair<>(si, sj));
        return (minP < 1e-8) && flag;
    }
}
