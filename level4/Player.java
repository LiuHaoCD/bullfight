package com.level4;


public class Player {
    private String name;
    String[] count = new String[5];//包含J,Q,K,A的牌型
    String[] color = new String[5];
    int[] counts = new int[5];//将J,Q,K,A转成数字后的牌型
    private int[] numbers = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[] others = new int[2];//有牛后剩下的两个点数
    private int cowNumber;
    private boolean cow;

    public int getCowNumber() {
        return cowNumber;
    }

    public boolean getCow() {
        return cow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void putCards() {
        changeCount();
        judgeCow();
        judgeNumber();
        System.out.println("玩家：" + getName() + "的牌为：");
        for (int i = 0; i < 5; i++) {
            System.out.println(this.color[i] + " " + this.count[i]);
        }
        if (cow) {
            if (cowNumber == 10) {

                System.out.println("满牛");
            } else {
                System.out.println("牛" + cowNumber);
            }
        } else {
            System.out.println("无牛");
        }
        changeToNumber();
        otherNumbers();
    }//输出牌型

    private void changeCount() {
        for (int i = 0; i < 5; i++) {
            switch (this.count[i]) {
                case "J":
                case "Q":
                case "K":
                    this.counts[i] = 10;
                    break;
                case "A":
                    this.counts[i] = 1;
                    break;
                default:
                    this.counts[i] = Integer.parseInt(this.count[i]);
                    break;
            }
        }
    }//将JQK转成10(判牛时使用)

    private void changeToNumber() {
        for (int i = 0; i < 5; i++) {
            switch (this.count[i]) {
                case "J":
                    counts[i] = 11;
                    break;
                case "Q":
                    counts[i] = 12;
                    break;
                case "K":
                    counts[i] = 13;
                    break;
                case "A":
                    counts[i] = 1;
                    break;
                default:
                    this.counts[i] = Integer.parseInt(this.count[i]);
                    break;
            }
        }
    }//将JQK转成10,11,12(比大小时使用)

    private void judgeCow() {
        if ((this.counts[0] + this.counts[1] + this.counts[2]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[0] + this.counts[1] + this.counts[3]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[0] + this.counts[1] + this.counts[4]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[0] + this.counts[2] + this.counts[3]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[0] + this.counts[2] + this.counts[4]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[0] + this.counts[3] + this.counts[4]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[1] + this.counts[2] + this.counts[3]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[1] + this.counts[2] + this.counts[4]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[1] + this.counts[3] + this.counts[4]) % 10 == 0) {
            this.cow = true;
        } else if ((this.counts[2] + this.counts[3] + this.counts[4]) % 10 == 0) {
            this.cow = true;
        } else {
            this.cow = false;
        }
    }//判断是否有牛

    private void judgeNumber() {
        if ((this.counts[0] + this.counts[1] + this.counts[2]) % 10 == 0) {
            numbers[0] = zeroTurnToTen((this.counts[3] + this.counts[4]) % 10);
        }
        if ((this.counts[0] + this.counts[1] + this.counts[3]) % 10 == 0) {
            numbers[1] = zeroTurnToTen((this.counts[2] + this.counts[4]) % 10);
        }
        if ((this.counts[0] + this.counts[1] + this.counts[4]) % 10 == 0) {
            numbers[2] = zeroTurnToTen((this.counts[2] + this.counts[3]) % 10);
        }
        if ((this.counts[0] + this.counts[2] + this.counts[3]) % 10 == 0) {
            numbers[3] = zeroTurnToTen((this.counts[1] + this.counts[4]) % 10);
        }
        if ((this.counts[0] + this.counts[2] + this.counts[4]) % 10 == 0) {
            numbers[4] = zeroTurnToTen((this.counts[1] + this.counts[3]) % 10);
        }
        if ((this.counts[0] + this.counts[3] + this.counts[4]) % 10 == 0) {
            numbers[5] = zeroTurnToTen((this.counts[1] + this.counts[2]) % 10);
        }
        if ((this.counts[1] + this.counts[2] + this.counts[3]) % 10 == 0) {
            numbers[6] = zeroTurnToTen((this.counts[0] + this.counts[4]) % 10);
        }
        if ((this.counts[1] + this.counts[2] + this.counts[4]) % 10 == 0) {
            numbers[7] = zeroTurnToTen((this.counts[0] + this.counts[3]) % 10);
        }
        if ((this.counts[1] + this.counts[3] + this.counts[4]) % 10 == 0) {
            numbers[8] = zeroTurnToTen((this.counts[0] + this.counts[2]) % 10);
        }
        if ((this.counts[2] + this.counts[3] + this.counts[4]) % 10 == 0) {
            numbers[9] = zeroTurnToTen((this.counts[0] + this.counts[1]) % 10);
        }
        this.cowNumber = maxNumber(numbers)[0];
    }//判断牛几

    private int[] maxNumber(int[] numbers) {
        int[] num = new int[2];
        num[0] = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > num[0]) {
                num[0] = numbers[i];
                num[1] = i;
            }
        }
        return num;

    }//输出数组的最大值,以及位置

    private int zeroTurnToTen(int number) {
        if (number == 0) {
            return 10;
        } else {
            return number;
        }

    }//如果值为0将它转成10

    private void otherNumbers() {
        changeToNumber();
        if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[3] + this.counts[4]) % 10)) {
            others[0] = this.counts[3];
            others[1] = this.counts[4];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[2] + this.counts[4]) % 10)) {
            others[0] = this.counts[2];
            others[1] = this.counts[4];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[2] + this.counts[3]) % 10)) {
            others[0] = this.counts[2];
            others[1] = this.counts[3];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[1] + this.counts[4]) % 10)) {
            others[0] = this.counts[1];
            others[1] = this.counts[4];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[1] + this.counts[3]) % 10)) {
            others[0] = this.counts[1];
            others[1] = this.counts[3];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[1] + this.counts[2]) % 10)) {
            others[0] = this.counts[1];
            others[1] = this.counts[2];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[0] + this.counts[4]) % 10)) {
            others[0] = this.counts[0];
            others[1] = this.counts[4];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[0] + this.counts[3]) % 10)) {
            others[0] = this.counts[0];
            others[1] = this.counts[3];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[0] + this.counts[2]) % 10)) {
            others[0] = this.counts[0];
            others[1] = this.counts[1];
        } else if (maxNumber(numbers)[1] == zeroTurnToTen((this.counts[0] + this.counts[1]) % 10)) {
            others[0] = this.counts[0];
            others[1] = this.counts[1];
        }

    }

}