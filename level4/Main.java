package com.level4;

import com.level1.squeezer.Squeezer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("加载中~~~~");
        Squeezer squeezer = new Squeezer();
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setName(getName(1));
        player2.setName(getName(2));
        System.out.println("一副新牌");
        for (int i = 0; i < squeezer.squeezer.length; i++) {
            System.out.println(squeezer.squeezer[i]);
        }
        System.out.println("\n\n\n去掉王后:");
        String[] squeezer2 = new String[52];
        for (int i = 0; i < squeezer2.length; i++) {
            squeezer2[i] = squeezer.squeezer[i];
            System.out.println(squeezer2[i]);
        }
        System.out.println("洗牌中~");
        List<String> squeezerList = Arrays.asList(squeezer2);
        Collections.shuffle(squeezerList);
        squeezerList.toArray(squeezer2);
        System.out.println("开始发牌~");
        System.out.println("玩家：" + player1.getName() + "开始摸牌");
        for (int i = 0; i < player1.count.length; i++) {
            player1.count[i] = squeezer2[i].substring(squeezer2[i].lastIndexOf(" ") + 1);
            player1.color[i] = squeezer2[i].substring(0, squeezer2[i].indexOf(" ") - 1);
        }
        player1.putCards();
        System.out.println("玩家：" + player2.getName() + "开始摸牌");
        for (int i = 0; i < player2.count.length; i++) {
            player2.count[i] = squeezer2[i + 5].substring(squeezer2[i + 5].lastIndexOf(" ") + 1);
            player2.color[i] = squeezer2[i + 5].substring(0, squeezer2[i + 5].indexOf(" ") - 1);
        }
        player2.putCards();
        winner(player1, player2);
    }

    public static String getName(int number) {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.println("输入玩家" + number + "的名字：");
        name = in.next();
        return name;
    }//获取玩家名字

    public static void winner(Player player1, Player player2) {
        if (player1.getCow() == true && player2.getCow() == false) {
            System.out.println("玩家：" + player1.getName() + "获胜");
        } else if (player1.getCow() == false && player2.getCow() == true) {
            System.out.println("玩家：" + player2.getName() + "获胜");
        } else if (player1.getCow() == true && player2.getCow() == true) {
            if (player1.getCowNumber() > player2.getCowNumber()) {
                System.out.println("玩家：" + player1.getName() + "获胜");
            } else if (player1.getCowNumber() < player2.getCowNumber()) {
                System.out.println("玩家：" + player2.getName() + "获胜");
            } else {
                if (player1.getCowNumber() > player2.getCowNumber()) {
                    System.out.println("玩家：" + player1.getName() + "获胜");
                } else if (player1.getCowNumber() < player2.getCowNumber()) {
                    System.out.println("玩家：" + player2.getName() + "获胜");
                } else {
                    Arrays.sort(player1.others);
                    Arrays.sort(player2.others);
                    if (player1.others[1] > player2.others[1]) {
                        System.out.println("玩家：" + player1.getName() + "获胜");
                    } else if (player1.others[1] < player2.others[1]) {
                        System.out.println("玩家：" + player2.getName() + "获胜");
                    } else {
                        System.out.println("平局");
                    }
                }
            }
        } else {
            Arrays.sort(player1.counts);
            Arrays.sort(player2.counts);
            for (int i = 4; i > -1; i--) {
                if (player1.counts[i] > player2.counts[i]) {
                    System.out.println("玩家：" + player1.getName() + "获胜");
                    break;
                } else if (player1.counts[i] < player2.counts[i]) {
                    System.out.println("玩家：" + player2.getName() + "获胜");
                    break;
                } else {
                    if (i == 0) {
                        System.out.println("平局");
                    }
                }
            }

        }


    }//判断赢家


}