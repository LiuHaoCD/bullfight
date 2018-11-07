package com.level4.squeezer;

public class Squeezer {
    private String[] joker = new String[]{"小王", "大王"};
    private String[] color = new String[]{"spades", "clubs", "hearts", "diamonds"};
    private String[] cout = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public String[] squeezer = new String[54];


    public Squeezer() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                squeezer[i*13+j] = (this.color[i] + " " + this.cout[j]);

            }
        }
        for (int i = 0; i < 2; i++) {
            squeezer[i + 52] = this.joker[i];
        }

    }
}
